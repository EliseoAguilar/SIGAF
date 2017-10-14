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

import com.sigaf.Ibo.ITipoActivoBo;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TConfiguracion;
import com.sigaf.pojo.TCuenta;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TTipoActivo;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
@Named(value = "tipoActivoBean")
@RequestScoped
@ManagedBean
public class TipoActivoBean extends Actividad {

    private IBitacoraBo bitacoraBo;

    

    private IEntidadBo entidadBo;


    private IConfiguracionBo configuracionBo;

    private TConfiguracion configuracion;

    private List<TCuenta> listaCuentas;

    private ICuentaBo cuentaBo;

    private ITipoActivoBo tipoActivoBo;

    private TTipoActivo tipoActivo;

    private TTipoActivo tipoActivoSeleccionado;

    private List< TTipoActivo> listaTipoActivo;

    private TCuenta cuentaActivo;

    private TCuenta cuentaGasto;

    private TCuenta cuentaVenta;

    private TCuenta cuentaDepre;

    private Boolean estadoValido;

    private String msgCodigo;

    private String msgNombre;

    private String msgDescripcion;

    private String msgVidaUtil;

    private String msgGastos;

    private String msgVenta;

    private String msgActivo;

    private String msgDepre;

    private String msgTipo;
   
    private TEntidad entidadSeleccionada;

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

    
    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public void updateConfiguracion() {
        this.configuracion = this.configuracionBo.getConfiguracion(entidadSeleccionada.getIdEntidad());
    }

    public void updateListaCuentas() {
        this.listaCuentas = this.cuentaBo.listCuentaEntActTip(entidadSeleccionada.getIdEntidad(), true);
    }


    public void updateListaTipoActivo() {
        this.listaTipoActivo = this.tipoActivoBo.listTipoActivo(entidadSeleccionada.getIdEntidad());
    }


    
    public String getMsgTipo() {
        return msgTipo;
    }

    public void setMsgTipo(String msgTipo) {
        this.msgTipo = msgTipo;
    }

    public IEntidadBo getEntidadBo() {
        return entidadBo;
    }

    public void setEntidadBo(IEntidadBo entidadBo) {
        this.entidadBo = entidadBo;
    }

    

   

    public void init() {
        super.enableShowData();
    
        try {
            this.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.limpiar();
    }

    public IConfiguracionBo getConfiguracionBo() {
        return configuracionBo;
    }

    public void setConfiguracionBo(IConfiguracionBo configuracionBo) {
        this.configuracionBo = configuracionBo;
    }

    public TConfiguracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(TConfiguracion configuracion) {
        this.configuracion = configuracion;
    }

    public String getMsgCodigo() {
        return msgCodigo;
    }

    public void setMsgCodigo(String msgCodigo) {
        this.msgCodigo = msgCodigo;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public String getMsgDescripcion() {
        return msgDescripcion;
    }

    public void setMsgDescripcion(String msgDescripcion) {
        this.msgDescripcion = msgDescripcion;
    }

    public String getMsgVidaUtil() {
        return msgVidaUtil;
    }

    public void setMsgVidaUtil(String msgVidaUtil) {
        this.msgVidaUtil = msgVidaUtil;
    }

    public String getMsgGastos() {
        return msgGastos;
    }

    public void setMsgGastos(String msgGastos) {
        this.msgGastos = msgGastos;
    }

    public String getMsgVenta() {
        return msgVenta;
    }

    public void setMsgVenta(String msgVenta) {
        this.msgVenta = msgVenta;
    }

    public String getMsgActivo() {
        return msgActivo;
    }

    public void setMsgActivo(String msgActivo) {
        this.msgActivo = msgActivo;
    }

    public String getMsgDepre() {
        return msgDepre;
    }

    public void setMsgDepre(String msgDepre) {
        this.msgDepre = msgDepre;
    }

    public Boolean getEstadoValido() {
        return estadoValido;
    }

    public void setEstadoValido(Boolean estadoValido) {
        this.estadoValido = estadoValido;
    }

    public List<TCuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(List<TCuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public ICuentaBo getCuentaBo() {
        return cuentaBo;
    }

    public void setCuentaBo(ICuentaBo cuentaBo) {
        this.cuentaBo = cuentaBo;
    }

    public TTipoActivo getTipoActivo() {
        return tipoActivo;
    }

    public void setTipoActivo(TTipoActivo tipoActivo) {
        this.tipoActivo = tipoActivo;
    }

    public TTipoActivo getTipoActivoSeleccionado() {
        return tipoActivoSeleccionado;
    }

    public void setTipoActivoSeleccionado(TTipoActivo tipoActivoSeleccionado) {
        this.tipoActivoSeleccionado = tipoActivoSeleccionado;

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_tipo_activo");
        auxBitacora.setAccionBitacora("Ver  información de tipo de activo fijo");
        auxBitacora.setDatosBitacora("Código :" + tipoActivoSeleccionado.getCodigoTipo()
                + ", Nombre:" + tipoActivoSeleccionado.getNombreTipo()
                + ", Tipo:" + tipoActivoSeleccionado.getCalculoTipoActivo()
                + ", Descripción :" + tipoActivoSeleccionado.getDescripcionTipo()
                + ", Entidad:" + entidadSeleccionada.getNombreEntidad());
        auxBitacora.setIdTableBitacora(tipoActivoSeleccionado.getIdTipo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);
    }

    public List<TTipoActivo> getListaTipoActivo() {
        return listaTipoActivo;
    }

    public void setListaTipoActivo(List<TTipoActivo> listaTipoActivo) {
        this.listaTipoActivo = listaTipoActivo;
    }

    public ITipoActivoBo getTipoActivoBo() {
        return tipoActivoBo;
    }

    public void setTipoActivoBo(ITipoActivoBo tipoActivoBo) {
        this.tipoActivoBo = tipoActivoBo;
    }

    public TCuenta getCuentaActivo() {
        return cuentaActivo;
    }

    public void setCuentaActivo(TCuenta cuentaActivo) {
        this.cuentaActivo = cuentaActivo;
    }

    public TCuenta getCuentaGasto() {
        return cuentaGasto;
    }

    public void setCuentaGasto(TCuenta cuentaGasto) {
        this.cuentaGasto = cuentaGasto;
    }

    public TCuenta getCuentaVenta() {
        return cuentaVenta;
    }

    public void setCuentaVenta(TCuenta cuentaVenta) {
        this.cuentaVenta = cuentaVenta;
    }

    public TCuenta getCuentaDepre() {
        return cuentaDepre;
    }

    public void setCuentaDepre(TCuenta cuentaDepre) {
        this.cuentaDepre = cuentaDepre;
    }

    public void enableShowDataBean() {
        super.enableShowData();
        this.limpiar();
    }

    public void limpiar() {

        this.tipoActivo = new TTipoActivo();
        this.tipoActivo.setVidaUtilTipo(0);

        this.estadoValido = false;
        this.cuentaActivo = new TCuenta();
        this.cuentaGasto = new TCuenta();
        this.cuentaVenta = new TCuenta();
        this.cuentaDepre = new TCuenta();
        this.cuentaActivo.setCodigoCuenta("");
        this.cuentaGasto.setCodigoCuenta("");
        this.cuentaVenta.setCodigoCuenta("");
        this.cuentaDepre.setCodigoCuenta("");

        this.msgActivo = "";
        this.msgCodigo = "";
        this.msgDepre = "";
        this.msgDescripcion = "";
        this.msgNombre = "";
        this.msgTipo = "";
        this.msgVenta = "";
        this.msgVidaUtil = "";
        this.msgGastos = "";

    }

    public void validarFormulario() {

        this.estadoValido = true;

        if (this.tipoActivo.getCodigoTipo().length() == 0) {
            this.msgCodigo = "Código  requerido";
            this.estadoValido = false;
        } else if (this.tipoActivoBo.getActivoRep(1, this.tipoActivo.getCodigoTipo()) != null) {
            this.msgCodigo = "El código ya fue asignado a otro tipo";
            this.estadoValido = false;
        } else {
            this.msgCodigo = "";
        }

        if (this.tipoActivo.getNombreTipo().length() < 6) {
            this.msgNombre = "El nombre debe contener como mínimo 6 caracteres";
            this.estadoValido = false;
        } else {
            this.msgNombre = "";
        }

        if (this.tipoActivo.getVidaUtilTipo() < 1) {
            this.msgVidaUtil = "Vida útil requerida";
            this.estadoValido = false;
        } else {
            this.msgVidaUtil = "";
        }

        if (this.tipoActivo.getCalculoTipoActivo().equals("")) {
            this.msgTipo = "Tipo requerido";
            this.estadoValido = false;
        } else {
            this.msgTipo = "";
        }

        if (this.cuentaGasto.getCodigoCuenta().length() == 0) {
            this.msgGastos = "Cuenta requerida";
            this.estadoValido = false;
        } else if (this.cuentaGasto.getIdCuenta() == this.cuentaActivo.getIdCuenta() || this.cuentaGasto.getIdCuenta() == this.cuentaVenta.getIdCuenta() || this.cuentaGasto.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgGastos = "La cuenta no puede asignarse dos veces";
            this.estadoValido = false;
        } else {
            this.msgGastos = "";
        }

        if (this.cuentaActivo.getCodigoCuenta().length() == 0) {
            this.msgActivo = "Cuenta requerida";
            this.estadoValido = false;
        } else if (this.cuentaActivo.getIdCuenta() == this.cuentaGasto.getIdCuenta() || this.cuentaActivo.getIdCuenta() == this.cuentaVenta.getIdCuenta() || this.cuentaActivo.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgActivo = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgActivo = "";
        }

        if (this.cuentaVenta.getCodigoCuenta().length() == 0) {
            this.msgVenta = "Cuenta requerida";
            this.estadoValido = false;
        } else if (this.cuentaVenta.getIdCuenta() == this.cuentaGasto.getIdCuenta() || this.cuentaVenta.getIdCuenta() == this.cuentaActivo.getIdCuenta() || this.cuentaVenta.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgVenta = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgVenta = "";
        }

        if (this.cuentaDepre.getCodigoCuenta().length() == 0) {
            this.msgDepre = "Cuenta  requerida";
            this.estadoValido = false;
        } else if (this.cuentaDepre.getIdCuenta() == this.cuentaGasto.getIdCuenta() || this.cuentaDepre.getIdCuenta() == this.cuentaActivo.getIdCuenta() || this.cuentaVenta.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgDepre = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgDepre = "";
        }
    }

    public void validarFormularioModificar() {

        this.estadoValido = true;

        if (this.tipoActivoSeleccionado.getCodigoTipo().length() == 0) {
            this.msgCodigo = "Código  requerido";
            this.estadoValido = false;
        } else if (this.tipoActivoBo.getActivoRepAct(1, this.tipoActivoSeleccionado.getIdTipo(), this.tipoActivoSeleccionado.getCodigoTipo()) != null) {
            this.msgCodigo = "El código ya fue asignado a otro tipo";
            this.estadoValido = false;
        } else {
            this.msgCodigo = "";
        }

        if (this.tipoActivoSeleccionado.getNombreTipo().length() < 6) {
            this.msgNombre = "El nombre debe contener como mínimo 6 caracteres";
            this.estadoValido = false;
        } else {
            this.msgNombre = "";
        }

        if (this.tipoActivoSeleccionado.getVidaUtilTipo() < 1) {
            this.msgVidaUtil = "Vida útil requerida";
            this.estadoValido = false;
        } else {
            this.msgVidaUtil = "";
        }

        if (this.tipoActivoSeleccionado.getCalculoTipoActivo().equals("")) {
            this.msgTipo = "Tipo requerido";
            this.estadoValido = false;
        } else {
            this.msgTipo = "";
        }

        if (this.cuentaGasto.getCodigoCuenta().length() == 0) {
            this.msgGastos = "Cuenta requerida";
            this.estadoValido = false;
        } else if (this.cuentaGasto.getIdCuenta() == this.cuentaActivo.getIdCuenta() || this.cuentaGasto.getIdCuenta() == this.cuentaVenta.getIdCuenta() || this.cuentaGasto.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgGastos = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgGastos = "";
        }

        if (this.cuentaActivo.getCodigoCuenta().length() == 0) {
            this.msgActivo = "Cuenta requerida";
            this.estadoValido = false;
        } else if (this.cuentaActivo.getIdCuenta() == this.cuentaGasto.getIdCuenta() || this.cuentaActivo.getIdCuenta() == this.cuentaVenta.getIdCuenta() || this.cuentaActivo.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgActivo = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgActivo = "";
        }

        if (this.cuentaVenta.getCodigoCuenta().length() == 0) {
            this.msgVenta = "Cuenta requerida";
            this.estadoValido = false;
        } else if (this.cuentaVenta.getIdCuenta() == this.cuentaGasto.getIdCuenta() || this.cuentaVenta.getIdCuenta() == this.cuentaActivo.getIdCuenta() || this.cuentaVenta.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgVenta = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgVenta = "";
        }

        if (this.cuentaDepre.getCodigoCuenta().length() == 0) {
            this.msgDepre = "Cuenta requerida";
            this.estadoValido = false;
        } else if (this.cuentaDepre.getIdCuenta() == this.cuentaGasto.getIdCuenta() || this.cuentaDepre.getIdCuenta() == this.cuentaActivo.getIdCuenta() || this.cuentaVenta.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgDepre = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgDepre = "";
        }

    }

    public void crearTipo() {
        try {
            this.tipoActivo.setTEntidad(entidadSeleccionada);
            this.tipoActivo.setTCuentaByIdCuentaVentaTipo(cuentaVenta);
            this.tipoActivo.setTCuentaByIdCuentaActivoTipo(cuentaActivo);
            this.tipoActivo.setTCuentaByIdCuentaDepreciacionTipo(cuentaDepre);
            this.tipoActivo.setTCuentaByIdCuentaGastoTipo(cuentaGasto);
            this.tipoActivo.setEstadoTipo(true);
            this.tipoActivoBo.create(tipoActivo);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_tipo_activo");
            auxBitacora.setAccionBitacora("Agregar tipo de activo fijo");
            auxBitacora.setDatosBitacora("Código :" + tipoActivo.getCodigoTipo()
                    + ", Nombre:" + tipoActivo.getNombreTipo()
                    + ", Tipo:" + tipoActivo.getCalculoTipoActivo()
                    + ", Descripción :" + tipoActivo.getDescripcionTipo()
                    + ", Entidad:" + entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(tipoActivo.getIdTipo());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            this.limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de activo registrado correctamente.", ""));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El  tipo de activo no pudo ser registrado.", ""));
        }
    }

    public void actualizar() {
        try {
            this.tipoActivoSeleccionado.setTCuentaByIdCuentaVentaTipo(cuentaVenta);
            this.tipoActivoSeleccionado.setTCuentaByIdCuentaActivoTipo(cuentaActivo);
            this.tipoActivoSeleccionado.setTCuentaByIdCuentaDepreciacionTipo(cuentaDepre);
            this.tipoActivoSeleccionado.setTCuentaByIdCuentaGastoTipo(cuentaGasto);
            this.tipoActivoBo.update(tipoActivoSeleccionado);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_tipo_activo");
            auxBitacora.setAccionBitacora("Modificar tipo de activo fijo");
            auxBitacora.setDatosBitacora("Código :" + tipoActivoSeleccionado.getCodigoTipo() + ", Nombre:" + tipoActivoSeleccionado.getNombreTipo()
                    + ", Tipo:" + tipoActivoSeleccionado.getCalculoTipoActivo()
                    + ", Descripción :" + tipoActivoSeleccionado.getDescripcionTipo()
                    + ", Entidad:" + entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(tipoActivoSeleccionado.getIdTipo());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            this.enableShowDataBean();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de activo  modificado correctamente.", ""));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El  tipo de activo no pudo ser modificado.", ""));
        }
    }

    public void cargarDatosCeuntas() {
        this.cuentaActivo = tipoActivoSeleccionado.getTCuentaByIdCuentaActivoTipo();
        this.cuentaDepre = tipoActivoSeleccionado.getTCuentaByIdCuentaDepreciacionTipo();
        this.cuentaVenta = tipoActivoSeleccionado.getTCuentaByIdCuentaVentaTipo();
        this.cuentaGasto = tipoActivoSeleccionado.getTCuentaByIdCuentaGastoTipo();
    }

    public void darDeAlta() {
        try {
            this.tipoActivoSeleccionado.setEstadoTipo(true);
            this.tipoActivoBo.update(tipoActivoSeleccionado);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_tipo_activo");
            auxBitacora.setAccionBitacora("Dar de alta tipo de activo fijo");
            auxBitacora.setDatosBitacora("Código :" + tipoActivoSeleccionado.getCodigoTipo() + ", Nombre:" + tipoActivoSeleccionado.getNombreTipo()
                    + ", Tipo:" + tipoActivoSeleccionado.getCalculoTipoActivo()
                    + ", Descripción :" + tipoActivoSeleccionado.getDescripcionTipo()
                    + ", Entidad:" + entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(tipoActivoSeleccionado.getIdTipo());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de activo dado de alta correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tipo de activo no pudo ser dado de alta.", ""));
        }

    }

    public void darDeBaja() {

        try {
            this.tipoActivoSeleccionado.setEstadoTipo(false);
            this.tipoActivoBo.update(tipoActivoSeleccionado);
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_tipo_activo");
            auxBitacora.setAccionBitacora("Dar de baja tipo de activo fijo");
            auxBitacora.setDatosBitacora("Código :" + tipoActivoSeleccionado.getCodigoTipo() + ", Nombre:" + tipoActivoSeleccionado.getNombreTipo()
                    + ", Tipo:" + tipoActivoSeleccionado.getCalculoTipoActivo()
                    + ", Descripción :" + tipoActivoSeleccionado.getDescripcionTipo()
                    + ", Entidad:" + entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(tipoActivoSeleccionado.getIdTipo());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de activor dado de baja correctamente.", ""));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tipo de activo no pudo ser dado de baja.", ""));

        }
    }

    public void generarReporteTipo() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idTipoActivo", this.tipoActivoSeleccionado.getIdTipo());
        estadoUsuario.put("idEntidad", entidadSeleccionada.getIdEntidad());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/ReporteTiposActivoIndividual.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), estadoUsuario, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_tipo_activo");
        auxBitacora.setAccionBitacora("Generar reporte de tipo de activo fijo");
        auxBitacora.setDatosBitacora("Código :" + tipoActivoSeleccionado.getCodigoTipo() + ", Nombre:" + tipoActivoSeleccionado.getNombreTipo()
                + ", Tipo:" + tipoActivoSeleccionado.getCalculoTipoActivo()
                + ", Descripción :" + tipoActivoSeleccionado.getDescripcionTipo()
                + ", Entidad:" + entidadSeleccionada.getIdEntidad());
        auxBitacora.setIdTableBitacora(tipoActivoSeleccionado.getIdTipo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void generarReporteTipoPDF() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idTipoActivo", this.tipoActivoSeleccionado.getIdTipo());
        estadoUsuario.put("idEntidad", entidadSeleccionada.getIdEntidad());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/ReporteTiposActivoIndividual.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), estadoUsuario, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Tipo-Activo-" + this.tipoActivoSeleccionado.getNombreTipo() + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_tipo_activo");
        auxBitacora.setAccionBitacora("Descargar reporte de tipo de activo fijo");
        auxBitacora.setDatosBitacora("Código :" + tipoActivoSeleccionado.getCodigoTipo() + ", Nombre:" + tipoActivoSeleccionado.getNombreTipo()
                + ", Tipo:" + tipoActivoSeleccionado.getCalculoTipoActivo()
                + ", Descripción :" + tipoActivoSeleccionado.getDescripcionTipo()
                + ", Entidad:" + entidadSeleccionada.getIdEntidad());
        auxBitacora.setIdTableBitacora(tipoActivoSeleccionado.getIdTipo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

        FacesContext.getCurrentInstance().responseComplete();
    }
}
