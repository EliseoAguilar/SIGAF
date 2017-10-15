/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IConfiguracionBo;
import com.sigaf.Ibo.ICuentaBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TConfiguracion;
import com.sigaf.pojo.TCuenta;
import com.sigaf.pojo.TEntidad;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Eliseo
 */
@SessionScoped
@ManagedBean
public class CuentaBean extends Actividad {

    private IBitacoraBo bitacoraBo;

    private String codigo;

    private TEntidad entidadSeleccionada;

    private List<TEntidad> listaEntidades;

    private IEntidadBo entidadBo;

    private IConfiguracionBo configuracionBo;

    private TConfiguracion configuracion;

    private TConfiguracion configuracionDestino;

    private List<TCuenta> listaCuentas;

    private List<TCuenta> listaCuentasAct;

    private TEntidad entidadDestino;

    public TEntidad getEntidadDestino() {
        return entidadDestino;
    }

    public void setEntidadDestino(TEntidad entidadDestino) {
        this.entidadDestino = entidadDestino;
    }

    public TConfiguracion getConfiguracionDestino() {
        return configuracionDestino;
    }

    public void setConfiguracionDestino(TConfiguracion configuracionDestino) {
        this.configuracionDestino = configuracionDestino;
    }

    /**
     * Objeto utilizado para guardar nueva cuenta
     */
    private TCuenta cuenta;
    /**
     * Objeto utilizado para guardar la cuenta selecionada de la tabla principal
     */
    private TCuenta cuentaSelecciona;
    /**
     * Objeto utilizado para guardar la cuenta selecionada de la tabla padre
     * para guardar nueva cuenta
     */
    private TCuenta cuentaSeleccionaPadre;
    /**
     * Interfas para la capa de negocio
     */
    private ICuentaBo cuentaBo;
    /**
     * Bandera para hablitar el panel de cuenta padre
     */
    private Boolean principal;
    /**
     * Atributos para validar el formulario
     */
    private Boolean estadoValido;

    private String msgCuentaPadre;

    private String msgNumero;

    private String msgNombre;

    private String msgTipo;

    private String msgEntidad;

    private String msgEstructura;

    private List<TCuenta> listaDestino;

    public List<TCuenta> getListaDestino() {
        return listaDestino;
    }

    public void setListaDestino(List<TCuenta> listaDestino) {
        this.listaDestino = listaDestino;
    }

    public String getMsgEntidad() {
        return msgEntidad;
    }

    public void setMsgEntidad(String msgEntidad) {
        this.msgEntidad = msgEntidad;
    }

    public String getMsgEstructura() {
        return msgEstructura;
    }

    public void setMsgEstructura(String msgEstructura) {
        this.msgEstructura = msgEstructura;
    }

    private int idEntidad;

    public int getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(int idEntidad) {
        this.idEntidad = idEntidad;
        this.estadoValido = false;

        if (idEntidad != 0) {
            listaDestino = this.cuentaBo.listCuentaEnt(idEntidad);
            if (listaDestino.isEmpty()) {
                this.msgEntidad = "La entidad no dispone de cuentas";
                this.msgEstructura = "";
                this.configuracionDestino = new TConfiguracion();
            } else {
                this.msgEntidad = "";
                this.configuracionDestino = configuracionBo.getConfiguracion(idEntidad);
                this.msgEstructura = "La estructura de cuentas actual es: " + configuracion.getCuentaConfiguracion() + ", la cual será sustituida por la estructura de cuentas de la entidad de origen";

            }

        } else {
            this.msgEntidad = "";
            this.msgEstructura = "";
            this.configuracionDestino = new TConfiguracion();

        }

    }

    public void limpiarCopia() {

        this.idEntidad = 0;
        this.configuracionDestino = new TConfiguracion();
        listaDestino.clear();
        this.msgEstructura = "";
        this.msgEntidad = "";
    }

    
    public void eliminarCuenta(){
    
    try {
            
            this.cuentaBo.delete(cuentaSelecciona);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_cuenta");
            auxBitacora.setAccionBitacora("Eliminar cuenta");
            auxBitacora.setDatosBitacora("Código:" + cuentaSelecciona.getCodigoCuenta() + ", Nombre:" + cuentaSelecciona.getNombreCuenta()
                    + ", Naturaleza:" + cuentaSelecciona.getNaturalezaCuenta() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(cuentaSelecciona.getIdCuenta());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuenta eliminada correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cuenta no puede ser eliminada.", ""));
        }
    
    }
            
    public void duplicarCatalog() {

        try {

            configuracion.setCuentaConfiguracion(this.configuracionDestino.getCuentaConfiguracion());

            this.configuracionBo.update(configuracion);

            this.entidadDestino = entidadBo.getTEntidad(idEntidad);

            List<TCuenta> listaCuentasInser = new ArrayList<>();

            /*Lo que dice destino, es en realidad el origen*/
            for (TCuenta tCuenta : listaDestino) {

                if (tCuenta.getTCuenta() != null) {
                    for (TCuenta tCuenta1 : listaCuentasInser) {
                        if (tCuenta1.getCodigoCuenta().equals(tCuenta.getTCuenta().getCodigoCuenta())) {
                            tCuenta.setTCuenta(tCuenta1);
                        }
                    }
                }

                tCuenta.setFechaCuenta(new Date());

                tCuenta.setTEntidad(entidadSeleccionada);

                this.cuentaBo.create(tCuenta);

                listaCuentasInser.add(tCuenta);

            }

            this.limpiarCopia();
            this.enableShowDataBean();
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_cuenta");
            auxBitacora.setAccionBitacora("Duplicar cuentas");
            auxBitacora.setDatosBitacora("Entidad origen:" + this.entidadDestino.getNombreEntidad() + ", Entidad destino:" + this.entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(cuenta.getIdCuenta());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuentas registradas correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "la cuentas no pudieron ser registrada.", ""));
        }

    }

    public void validarCopia() {

        this.estadoValido = true;

        if (this.cuentaBo.listCuentaEnt(idEntidad).isEmpty()) {
            this.estadoValido = false;
        }

    }

    public List<TEntidad> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(List<TEntidad> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    public void updateListaEntidades() {
        this.listaEntidades = this.entidadBo.listTEndidadTodos();
    }

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public TEntidad getEntidadSeleccionada() {
        return entidadSeleccionada;
    }

    public void setEntidadSeleccionada(TEntidad entidadSeleccionada) {
        this.entidadSeleccionada = entidadSeleccionada;
    }

    public void getContabilidadPredeterminado() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        ContablidadPredeterminarBean ContPreBean = (ContablidadPredeterminarBean) request.getSession().getAttribute("contablidadPredeterminarBean");

        this.entidadSeleccionada = ContPreBean.getEntidadSeleccionada();
    }

    /*
    *Metodo que inicializa el bean
     */
    public void init() {
        this.listaDestino = new ArrayList<>();
        this.limpiar();
        try {
            this.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        super.enableShowData();
    }

    /*
    *Metodo que limpia todos los objetos
     */
    public void limpiar() {
        this.cuenta = new TCuenta();
        this.cuenta.setFechaCuenta(new Date());
        this.cuenta.setNaturalezaCuenta("Deudora");
        this.cuentaSeleccionaPadre = new TCuenta();
        this.principal = true;
        this.estadoValido = false;
        this.msgCuentaPadre = "";
        this.msgNombre = "";
        this.msgNumero = "";
        this.msgTipo = "";
    }

    /**
     * Metodo que actuliza la lista de Cuentas consultado a la Base de Datos
     */
    public void updateListaCuentas() {
        this.listaCuentas = this.cuentaBo.listCuentaEnt(entidadSeleccionada.getIdEntidad());
    }

    /**
     * Metodo que actuliza la lista de Cuentas utilizadas para el formulario
     * Agregar cuenta consultado a la Base de Datos
     *
     */
    public void updateListaCuentasAct() {
        this.listaCuentasAct = this.cuentaBo.listCuentaEntAct(entidadSeleccionada.getIdEntidad(), true);

    }

    /**
     * Metodo que actuliza la configuracion para el formulario Agregar cuenta
     * consultado a la Base de Datos
     *
     */
    public void updateConfiguracion() {
        this.configuracion = configuracionBo.getConfiguracion(entidadSeleccionada.getIdEntidad());

    }

    /**
     * Metodo que limpia la cuenta padre al desaccitvar el check ¿Subcuenta?
     * Este metodo se llama antes que cambie el valor por lo cual se Verifica
     * que el estado anterior sea true
     */
    public void limpiarCuentaSub() {
        if (this.principal) {
            this.cuentaSeleccionaPadre = new TCuenta();
            this.msgCuentaPadre = "";
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<TCuenta> getListaCuentasAct() {
        return listaCuentasAct;
    }

    public void setListaCuentasAct(List<TCuenta> listaCuentasAct) {
        this.listaCuentasAct = listaCuentasAct;
    }

    public TConfiguracion getConfiguracion() {

        return configuracion;
    }

    public void setConfiguracion(TConfiguracion configuracion) {
        this.configuracion = configuracion;
    }

    public IConfiguracionBo getConfiguracionBo() {
        return configuracionBo;
    }

    public void setConfiguracionBo(IConfiguracionBo configuracionBo) {
        this.configuracionBo = configuracionBo;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public TCuenta getCuentaSeleccionaPadre() {

        return cuentaSeleccionaPadre;
    }

    public void setCuentaSeleccionaPadre(TCuenta cuentaSeleccionaPadre) {
        this.cuentaSeleccionaPadre = cuentaSeleccionaPadre;
        this.msgNumero = "";
        if (this.principal) {

            boolean fin = true;
            String srtOr = this.cuentaSeleccionaPadre.getCodigoCuenta();
            String[] partsOr = srtOr.split("-");
            int inicioOr = 0;

            for (int i = partsOr.length - 1; i >= 0; i--) {
                inicioOr++;
                for (int j = 0; j < partsOr[i].length(); j++) {
                    if (partsOr[i].charAt(j) != '0') {
                        fin = false;
                        break;
                    }
                }
                if (!fin) {
                    break;
                }

            }

            int seg = (partsOr.length - inicioOr) + 1;

            if (seg >= partsOr.length) {

                this.msgNumero = "La configuración actual no soporta más subcuentas.";

                this.cuenta.setCodigoCuenta("");

            } else {

                String numero = this.cuentaBo.numeroCuenta(this.cuentaSeleccionaPadre.getIdCuenta(), seg + 1).toString();

                if (partsOr[seg].length() < numero.length()) {

                    this.msgNumero = "La configuración actual no soporta más subcuentas.";

                    this.cuenta.setCodigoCuenta("");

                } else {

                    String ceros = "";

                    int cantidad = partsOr[seg].length() - numero.length();

                    for (int i = 0; i < cantidad; i++) {
                        ceros += "0";
                    }

                    ceros = ceros + numero;

                    String strDe = partsOr[0];

                    for (int i = 1; i <= (partsOr.length - 1); i++) {

                        if (seg == i) {
                            strDe += "-";
                            strDe += ceros;
                        } else {
                            strDe += "-";
                            strDe += partsOr[i];
                        }
                    }

                    this.cuenta.setCodigoCuenta(strDe);
                }

            }

        }

    }

    public IEntidadBo getEntidadBo() {
        return entidadBo;
    }

    public void setEntidadBo(IEntidadBo entidadBo) {
        this.entidadBo = entidadBo;
    }

    public ICuentaBo getCuentaBo() {
        return cuentaBo;
    }

    public void setCuentaBo(ICuentaBo cuentaBo) {
        this.cuentaBo = cuentaBo;
    }

    public List<TCuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(List<TCuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public TCuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(TCuenta cuenta) {
        this.cuenta = cuenta;
    }

    public TCuenta getCuentaSelecciona() {
        return cuentaSelecciona;
    }

    public void setCuentaSelecciona(TCuenta cuentaSelecciona) {
        this.cuentaSelecciona = cuentaSelecciona;
        this.codigo = cuentaSelecciona.getCodigoCuenta();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cuenta");
        auxBitacora.setAccionBitacora("Ver información de cuenta");
        auxBitacora.setDatosBitacora("Código:" + cuentaSelecciona.getCodigoCuenta() + ", Nombre:" + cuentaSelecciona.getNombreCuenta()
                + ", Naturaleza:" + cuentaSelecciona.getNaturalezaCuenta() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
        auxBitacora.setIdTableBitacora(cuentaSelecciona.getIdCuenta());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);
    }

    public void crearCuenta() {

        try {
            this.cuenta.setTEntidad(new TEntidad(this.entidadSeleccionada.getIdEntidad()));
            if (this.principal) {
                this.cuenta.setEstadoCuenta(true);
                this.cuenta.setTCuenta(cuentaSeleccionaPadre);
                this.cuentaBo.create(cuenta);

            } else {
                this.cuenta.setEstadoCuenta(true);
                this.cuenta.setTCuenta(null);
                this.cuentaBo.create(cuenta);

            }

            this.limpiar();

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_cuenta");
            auxBitacora.setAccionBitacora("Agregar cuenta");
            auxBitacora.setDatosBitacora("Código:" + cuenta.getCodigoCuenta() + ", Nombre:" + cuenta.getNombreCuenta()
                    + ", Naturaleza:" + cuenta.getNaturalezaCuenta() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(cuenta.getIdCuenta());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuenta registrada correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "la cuenta no pudo ser registrada.", ""));
        }
    }

    public void enableShowDataBean() {

        super.enableShowData();
        this.limpiar();

    }

    public void darDeAlta() {
        try {
            this.cuentaSelecciona.setEstadoCuenta(true);
            this.cuentaBo.update(cuentaSelecciona);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_cuenta");
            auxBitacora.setAccionBitacora("Dar de alta cuenta");
            auxBitacora.setDatosBitacora("Código:" + cuentaSelecciona.getCodigoCuenta() + ", Nombre:" + cuentaSelecciona.getNombreCuenta()
                    + ", Naturaleza:" + cuentaSelecciona.getNaturalezaCuenta() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(cuentaSelecciona.getIdCuenta());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuenta dada de alta correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cuenta no pudo ser de alta.", ""));
        }

    }

    public void darDeBaja() {

        try {

            this.cuentaSelecciona.setEstadoCuenta(false);
            this.cuentaBo.update(cuentaSelecciona);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_cuenta");
            auxBitacora.setAccionBitacora("Dar de baja cuenta");
            auxBitacora.setDatosBitacora("Código:" + cuentaSelecciona.getCodigoCuenta() + ", Nombre:" + cuentaSelecciona.getNombreCuenta()
                    + ", Naturaleza:" + cuentaSelecciona.getNaturalezaCuenta() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(cuentaSelecciona.getIdCuenta());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuenta dada de baja correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cuenta no pudo ser de baja.", ""));
        }

    }

    public void actulizar() {
        try {

            this.cuentaBo.update(cuentaSelecciona);

            String[] partsOr = this.codigo.split("-");
            String[] partsDe = this.cuentaSelecciona.getCodigoCuenta().split("-");

            int inicio = 0;
            Boolean fin = true;

            for (int i = partsOr.length - 1; i >= 0; i--) {
                inicio++;
                for (int j = 0; j < partsOr[i].length(); j++) {
                    if (partsOr[i].charAt(j) != '0') {
                        fin = false;
                        break;
                    }
                }
                if (!fin) {
                    break;
                }

            }

            String strOr = partsOr[0];

            String strDe = partsDe[0];

            for (int i = 1; i <= (partsOr.length - inicio); i++) {
                strOr += "-";
                strDe += "-";
                strOr += partsOr[i];
                strDe += partsDe[i];
            }

            this.cuentaBo.updateCode(this.entidadSeleccionada.getIdEntidad(), strOr, strDe);

            this.enableShowDataBean();

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_cuenta");
            auxBitacora.setAccionBitacora("Modificar cuenta");
            auxBitacora.setDatosBitacora("Código:" + cuentaSelecciona.getCodigoCuenta() + ", Nombre:" + cuentaSelecciona.getNombreCuenta()
                    + ", Naturaleza:" + cuentaSelecciona.getNaturalezaCuenta()
                    + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(cuentaSelecciona.getIdCuenta());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuenta modificada correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cuenta no pudo ser modificada.", ""));
        }
    }

    public Boolean getEstadoValido() {
        return estadoValido;
    }

    public void setEstadoValido(Boolean estadoValido) {
        this.estadoValido = estadoValido;
    }

    public String getMsgCuentaPadre() {
        return msgCuentaPadre;
    }

    public void setMsgCuentaPadre(String msgCuentaPadre) {
        this.msgCuentaPadre = msgCuentaPadre;
    }

    public String getMsgNumero() {
        return msgNumero;
    }

    public void setMsgNumero(String msgNumero) {
        this.msgNumero = msgNumero;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public String getMsgTipo() {
        return msgTipo;
    }

    public void setMsgTipo(String msgTipo) {
        this.msgTipo = msgTipo;
    }

    public Boolean verificarCodigoSegmentos() {

        String srtDe = this.cuenta.getCodigoCuenta();
        String[] partsDe = srtDe.split("-");
        int inicioOr = 0;
        int inicioDe = 0;
        Boolean fin = true;

        for (int i = partsDe.length - 1; i >= 0; i--) {
            inicioDe++;
            for (int j = 0; j < partsDe[i].length(); j++) {
                if (partsDe[i].charAt(j) != '0') {
                    fin = false;
                    break;
                }
            }
            if (!fin) {
                break;
            }

        }

        if (this.principal) {
            fin = true;
            String srtOr = this.cuentaSeleccionaPadre.getCodigoCuenta();
            String[] partsOr = srtOr.split("-");
            for (int i = partsOr.length - 1; i >= 0; i--) {
                inicioOr++;
                for (int j = 0; j < partsOr[i].length(); j++) {
                    if (partsOr[i].charAt(j) != '0') {
                        fin = false;
                        break;
                    }
                }
                if (!fin) {
                    break;
                }

            }
            return (inicioOr - inicioDe) == 1;
        } else {
            return (partsDe.length - inicioDe) == 0;
        }

    }

    public Boolean vericarCodigoPadreHijo() {

        if (this.principal) {

            String[] partsCuPa = this.cuentaSeleccionaPadre.getCodigoCuenta().split("-");
            String[] partsCu = this.cuenta.getCodigoCuenta().split("-");

            int inicio = 0;
            Boolean fin = true;

            for (int i = partsCuPa.length - 1; i >= 0; i--) {
                inicio++;
                for (int j = 0; j < partsCuPa[i].length(); j++) {
                    if (partsCuPa[i].charAt(j) != '0') {
                        fin = false;
                        break;
                    }
                }
                if (!fin) {
                    break;
                }

            }

            Boolean iguales = true;
            for (int i = 0; i <= (partsCuPa.length - inicio); i++) {
                for (int j = 0; j < partsCuPa[i].length(); j++) {
                    if (partsCuPa[i].charAt(j) != partsCu[i].charAt(j)) {
                        iguales = false;

                        break;
                    }
                }
                if (!iguales) {
                    break;
                }
            }

            return iguales;
        } else {

            return true;
        }

    }

    public void validarFormulario() {

        this.estadoValido = true;

        if (this.cuenta.getNombreCuenta().length() < 3) {
            this.msgNombre = "El nombre debe contener como mínimo 3 caracteres";
            this.estadoValido = false;
        } else {
            this.msgNombre = "";
        }

        if (this.cuenta.getCodigoCuenta().length() == 0) {
            this.msgNumero = "Código requerido";
            this.estadoValido = false;
        } else if (this.cuentaBo.getCuentaRep(this.entidadSeleccionada.getIdEntidad(), this.cuenta.getCodigoCuenta()) != null) {
            this.msgNumero = "El código ya fue asignado a otra cuenta";
            this.estadoValido = false;
        } else if (!vericarCodigoPadreHijo()) {
            this.msgNumero = "El código no corresponde a la cuenta principal";
            this.estadoValido = false;
        } else if (!verificarCodigoSegmentos()) {
            this.msgNumero = "El código no puede ocupar dos segmentos después de la cuenta principal";
            this.estadoValido = false;
        } else {
            this.msgNumero = "";
        }

        if (this.principal && this.cuentaSeleccionaPadre.getCodigoCuenta() == null) {
            this.msgCuentaPadre = "Seleccione la cuenta principal";
            this.estadoValido = false;
        } else {
            this.msgCuentaPadre = "";

        }

    }

    public Boolean vericarCodigoPadreHijoModificar() {

        if (cuentaSelecciona.getTCuenta() != null) {

            String[] partsCuPa = this.cuentaSelecciona.getTCuenta().getCodigoCuenta().split("-");
            String[] partsCu = this.cuentaSelecciona.getCodigoCuenta().split("-");

            int inicio = 0;
            Boolean fin = true;

            for (int i = partsCuPa.length - 1; i >= 0; i--) {
                inicio++;
                for (int j = 0; j < partsCuPa[i].length(); j++) {
                    if (partsCuPa[i].charAt(j) != '0') {
                        fin = false;
                        break;
                    }
                }
                if (!fin) {
                    break;
                }

            }

            Boolean iguales = true;
            for (int i = 0; i <= (partsCuPa.length - inicio); i++) {
                for (int j = 0; j < partsCuPa[i].length(); j++) {
                    if (partsCuPa[i].charAt(j) != partsCu[i].charAt(j)) {
                        iguales = false;
                        break;
                    }
                }
                if (!iguales) {
                    break;
                }
            }
            return iguales;
        } else {
            return true;
        }

    }

    public Boolean verificarCodigoSegmentosModificar() {

        String srtDe = this.cuentaSelecciona.getCodigoCuenta();
        String[] partsDe = srtDe.split("-");
        int inicioOr = 0;
        int inicioDe = 0;
        Boolean fin = true;

        for (int i = partsDe.length - 1; i >= 0; i--) {
            inicioDe++;
            for (int j = 0; j < partsDe[i].length(); j++) {
                if (partsDe[i].charAt(j) != '0') {
                    fin = false;
                    break;
                }
            }
            if (!fin) {
                break;
            }

        }

        if (cuentaSelecciona.getTCuenta() != null) {
            fin = true;
            String srtOr = this.cuentaSelecciona.getTCuenta().getCodigoCuenta();
            String[] partsOr = srtOr.split("-");
            for (int i = partsOr.length - 1; i >= 0; i--) {
                inicioOr++;
                for (int j = 0; j < partsOr[i].length(); j++) {
                    if (partsOr[i].charAt(j) != '0') {
                        fin = false;
                        break;
                    }
                }
                if (!fin) {
                    break;
                }

            }
            return (inicioOr - inicioDe) == 1;
        } else {
            return (partsDe.length - inicioDe) == 0;
        }

    }

    public void validarFormularioModificar() {

        this.estadoValido = true;

        if (this.cuentaSelecciona.getNombreCuenta().length() < 3) {
            this.msgNombre = "El nombre debe contener como mínimo 3 caracteres";
            this.estadoValido = false;
        } else {
            this.msgNombre = "";
        }

        if (this.cuentaSelecciona.getCodigoCuenta().length() == 0) {
            this.msgNumero = "Código requerido.";
            this.estadoValido = false;
        } else if (this.cuentaBo.getCuentaRepAct(this.entidadSeleccionada.getIdEntidad(), this.cuentaSelecciona.getIdCuenta(), this.cuentaSelecciona.getCodigoCuenta()) != null) {
            this.msgNumero = "El código ya fue asignado a otra cuenta";
            this.estadoValido = false;
        } else if (!vericarCodigoPadreHijoModificar()) {
            this.msgNumero = "El código no corresponde a la cuenta principal";
            this.estadoValido = false;
        } else if (!verificarCodigoSegmentosModificar()) {
            this.msgNumero = "El código no puede ocupar dos segmentos después de la cuenta principal";
            this.estadoValido = false;
        } else {

            this.msgNumero = "";
        }

    }

    public void generarReporteCuenta() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idCuenta", this.cuentaSelecciona.getIdCuenta());
        estadoUsuario.put("idEntidad", this.entidadSeleccionada.getIdEntidad());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/CuentaIndividual.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), estadoUsuario, this.getConn());

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cuenta");
        auxBitacora.setAccionBitacora("Generar reporte de cuenta");
        auxBitacora.setDatosBitacora("Código:" + cuentaSelecciona.getCodigoCuenta() + ", Nombre:" + cuentaSelecciona.getNombreCuenta()
                + ", Naturaleza:" + cuentaSelecciona.getNaturalezaCuenta() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
        auxBitacora.setIdTableBitacora(cuentaSelecciona.getIdCuenta());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void generarReporteCuentaPDF() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idCuenta", this.cuentaSelecciona.getIdCuenta());
        estadoUsuario.put("idEntidad", this.entidadSeleccionada.getIdEntidad());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/CuentaIndividual.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), estadoUsuario, this.getConn());

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cuenta");
        auxBitacora.setAccionBitacora("Descargar reporte de cuenta");
        auxBitacora.setDatosBitacora("Código:" + cuentaSelecciona.getCodigoCuenta() + ", Nombre:" + cuentaSelecciona.getNombreCuenta()
                + ", Naturaleza:" + cuentaSelecciona.getNaturalezaCuenta() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
        auxBitacora.setIdTableBitacora(cuentaSelecciona.getIdCuenta());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Cuenta-" + cuentaSelecciona.getCodigoCuenta() + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
}
