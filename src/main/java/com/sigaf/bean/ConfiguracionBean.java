/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IActivoFijoBo;
import com.sigaf.Ibo.IAreaBo;
import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IConfiguracionBo;
import com.sigaf.Ibo.ICuentaBo;
import com.sigaf.Ibo.IEjercicioBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Ibo.IEstructuraBo;
import com.sigaf.Ibo.ITipoActivoBo;
import com.sigaf.pojo.TActivoFijo;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TConfiguracion;
import com.sigaf.pojo.TCuenta;
import com.sigaf.pojo.TEjercicio;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TEstructura;
import com.sigaf.pojo.TTipoActivo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Eliseo
 */
@Named(value = "configuracionBean")
@RequestScoped
@ManagedBean
public class ConfiguracionBean extends Actividad {

    private IBitacoraBo bitacoraBo;

    /* Para saber el Ejercicio abierto */
    private TEjercicio ejerAbierto;

    /* Acceso a datos ejercicios */
    private IEjercicioBo ejercicioBo;

    /* para determinar si el formulario es valido*/
    private Boolean estadoValidoEstado;

    /*Para  Renta */
    private Boolean considerarRenta;

    /*Para  Reserva*/
    private Boolean considerarReserva;

    /* objeto para la renta*/
    private TEstructura rentaEstructura;

    /* objeto para  la reserva legal*/
    private TEstructura ReservaEstructura;

    /*Para  Utilidad*/
    private TEstructura utilidadEstructura;

    /*Lista para ingresos */
    private List<TEstructura> listaEstructuraIngresos;

    /*Lista para costos */
    private List<TEstructura> listaEstructuraCostos;

    /*Lista para Gastos */
    private List<TEstructura> listaEstructuraGastos;

    /*Lista para Otros  Gastos */
    private List<TEstructura> listaEstructuraOtrosGastos;

    /*Lista para Otros  Ingresos */
    private List<TEstructura> listaEstructuraOtrosIngresos;

    /*Para  guardar temporalmente la cuenta*/
    private TCuenta cuentaAuxEstado;

    /*Para la entidad predeterminada*/
    private Boolean estadoPredeterminado;

    /*para indicar grupo de cuentas*/
    private Integer idGrupo;

    /* msg para Estado  de Resultado*/
    private String msgIngresos;

    private String msgCostos;

    private String msgGastos;

    private String msgOtrosGastos;

    private String msgOtrosIngresos;

    private String msgRenta;

    private String msgValorRef;

    private String msgValorMin;

    private String msgValorMax;

    private String msgReserva;

    private String msgValorRes;

    private String msgUtilidad;

    /* lista de entidades*/
    private List<TEntidad> listaEntidades;

    /*Acceso a datos de las entidades*/
    private IEntidadBo entidadBo;

    /* Atributos para configuracion codigos*/
    private IActivoFijoBo activoFijoBo;

    /* id entidad seleccionada*/
    private Integer idEntidad;

    /* Acceso para  datos  estructura*/
    private IEstructuraBo estructuraBo;

    /* Acceso para datos configuracion */
    private IConfiguracionBo configuracionBo;

    /* Atributo  para  datos de la Configuracion Nueva*/
    private TConfiguracion configuracion;

    /* Atributo  para  datos de la Configuracion Actual*/
    private TConfiguracion configuracionActual;

    /* Acceso para datos Cuentas*/
    private ICuentaBo cuentaBo;

    /* Acceso para datos Activos*/
    private ITipoActivoBo tipoActivoBo;

    /* Acceso para datos Areas*/
    private IAreaBo areaBo;

    /* Lista de Cuentas*/
    private List<TCuenta> listaCuentas;

    /* Lista de Cuentas*/
    private List<TCuenta> listaCuentasBalance;

    /* Para validacion de Configuracion de codigos*/
    private Boolean estadoValido;

    private String msgCuenta;

    private String msgTipo;

    private String msgArea;

    private String msgActivo;

    /* para estructura Balance General*/
    private TEstructura estructuraActivo;

    private TEstructura estructuraPasivo;

    private TEstructura estructuraCapital;

    private Boolean estadoValidoBalance;

    private String msgEstActivo;

    private String msgEstPasivo;

    private String msgEstCapital;

    private String msgEstActivoNivel;

    private String msgEstPasivoNivel;

    private String msgEstCapitalNivel;

    private Integer idTab;

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public void updateListaEntidades() {
        this.listaEntidades = this.entidadBo.listTEndidadTodos();
    }

    public void updateListaCuentas() {
        this.listaCuentas = this.cuentaBo.listCuentaEnt(idEntidad);
    }

    public void updateListaCuentasBalance() {

        this.updateListaCuentas();
        this.listaCuentasBalance.clear();
        for (TCuenta cuenta : listaCuentas) {
            if (cuenta.getTCuenta() == null) {
                listaCuentasBalance.add(cuenta);
            }
        }
    }

    public String getMsgEstActivoNivel() {
        return msgEstActivoNivel;
    }

    public void setMsgEstActivoNivel(String msgEstActivoNivel) {
        this.msgEstActivoNivel = msgEstActivoNivel;
    }

    public String getMsgEstPasivoNivel() {
        return msgEstPasivoNivel;
    }

    public void setMsgEstPasivoNivel(String msgEstPasivoNivel) {
        this.msgEstPasivoNivel = msgEstPasivoNivel;
    }

    public String getMsgEstCapitalNivel() {
        return msgEstCapitalNivel;
    }

    public void setMsgEstCapitalNivel(String msgEstCapitalNivel) {
        this.msgEstCapitalNivel = msgEstCapitalNivel;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {

        this.ejerAbierto = this.ejercicioBo.getEjercicioAbierto(idEntidad);

        if (this.ejerAbierto != null) {

            /* Accesando a datos de configuracion Estado de Resultado*/
            List<TEstructura> auxEst = this.estructuraBo.listEstructura(this.ejerAbierto.getIdEjercicio(), 1);

            this.listaEstructuraCostos = new ArrayList<>();
            this.listaEstructuraGastos = new ArrayList<>();
            this.listaEstructuraIngresos = new ArrayList<>();
            this.listaEstructuraOtrosGastos = new ArrayList<>();
            this.listaEstructuraOtrosIngresos = new ArrayList<>();
            this.rentaEstructura = new TEstructura();
            this.rentaEstructura.setValorRango(BigDecimal.ZERO);
            this.rentaEstructura.setPorMaxEstructura(BigDecimal.ZERO);
            this.rentaEstructura.setPorMinEstructura(BigDecimal.ZERO);
            this.ReservaEstructura = new TEstructura();
            this.ReservaEstructura.setPorMinEstructura(BigDecimal.ZERO);
            this.utilidadEstructura = new TEstructura();

            if (!auxEst.isEmpty()) {

                for (TEstructura tEstructura : auxEst) {
                    switch (tEstructura.getGrupoReporte()) {
                        case 1:
                            this.listaEstructuraIngresos.add(tEstructura);
                            break;
                        case 2:
                            this.listaEstructuraCostos.add(tEstructura);
                            break;
                        case 3:
                            this.listaEstructuraGastos.add(tEstructura);
                            break;
                        case 4:
                            this.listaEstructuraOtrosIngresos.add(tEstructura);
                            break;
                        case 5:
                            this.listaEstructuraOtrosGastos.add(tEstructura);
                            break;

                        case 6:
                            this.considerarRenta = true;
                            this.rentaEstructura = tEstructura;
                            break;
                        case 7:
                            this.considerarReserva = true;
                            this.ReservaEstructura = tEstructura;
                            break;
                        case 8:
                            this.utilidadEstructura = tEstructura;
                            break;
                    }
                }
            }

            /* Accesando a datos de configuracion Balance General*/
            List<TEstructura> auxEstBalace = this.estructuraBo.listEstructura(this.ejerAbierto.getIdEjercicio(), 2);

            this.estructuraActivo = new TEstructura();
            this.estructuraPasivo = new TEstructura();
            this.estructuraCapital = new TEstructura();

            if (!auxEstBalace.isEmpty()) {
                for (TEstructura tauxEstBalace : auxEstBalace) {
                    switch (tauxEstBalace.getGrupoReporte()) {
                        case 11:
                            this.estructuraActivo = tauxEstBalace;
                            break;
                        case 12:
                            this.estructuraPasivo = tauxEstBalace;
                            break;
                        case 13:
                            this.estructuraCapital = tauxEstBalace;
                            break;

                    }
                }
            }

        }

        this.configuracionActual = this.configuracionBo.getConfiguracion(idEntidad);
        this.configuracion = new TConfiguracion();
        if (configuracionActual != null) {

            this.configuracion.setIdConfiguracion(configuracionActual.getIdConfiguracion());
            this.configuracion.setTEntidad(configuracionActual.getTEntidad());
            this.configuracion.setCuentaConfiguracion(configuracionActual.getCuentaConfiguracion());
            this.configuracion.setTipoConfiguracion(configuracionActual.getTipoConfiguracion());
            this.configuracion.setAreaConfiguracion(configuracionActual.getAreaConfiguracion());
            this.configuracion.setActivoConfiguracion(configuracionActual.getActivoConfiguracion());

        }

        this.idEntidad = idEntidad;
        this.idTab = 0;
    }

    /**
     * Metodos Configuracion Estado de Resultado INICIO
     *
     */
    public TEjercicio getEjerAbierto() {

        return ejerAbierto;
    }

    public void setEjerAbierto(TEjercicio ejerAbierto) {
        this.ejerAbierto = ejerAbierto;
    }

    public String getMsgValorRef() {
        return msgValorRef;
    }

    public void setMsgValorRef(String msgValorRef) {
        this.msgValorRef = msgValorRef;
    }

    public String getMsgValorMin() {
        return msgValorMin;
    }

    public void setMsgValorMin(String msgValorMin) {
        this.msgValorMin = msgValorMin;
    }

    public String getMsgValorMax() {
        return msgValorMax;
    }

    public void setMsgValorMax(String msgValorMax) {
        this.msgValorMax = msgValorMax;
    }

    public String getMsgValorRes() {
        return msgValorRes;
    }

    public void setMsgValorRes(String msgValorRes) {
        this.msgValorRes = msgValorRes;
    }

    public IEjercicioBo getEjercicioBo() {
        return ejercicioBo;
    }

    public void setEjercicioBo(IEjercicioBo ejercicioBo) {
        this.ejercicioBo = ejercicioBo;
    }

    public Boolean getEstadoValidoEstado() {
        return estadoValidoEstado;
    }

    public void setEstadoValidoEstado(Boolean estadoValidoEstado) {
        this.estadoValidoEstado = estadoValidoEstado;
    }

    public TEstructura getUtilidadEstructura() {
        return utilidadEstructura;
    }

    public void setUtilidadEstructura(TEstructura utilidadEstructura) {
        this.utilidadEstructura = utilidadEstructura;
    }

    public List<TEstructura> getListaEstructuraIngresos() {
        return listaEstructuraIngresos;
    }

    public void setListaEstructuraIngresos(List<TEstructura> listaEstructuraIngresos) {
        this.listaEstructuraIngresos = listaEstructuraIngresos;
    }

    public List<TEstructura> getListaEstructuraCostos() {
        return listaEstructuraCostos;
    }

    public void setListaEstructuraCostos(List<TEstructura> listaEstructuraCostos) {
        this.listaEstructuraCostos = listaEstructuraCostos;
    }

    public List<TEstructura> getListaEstructuraGastos() {
        return listaEstructuraGastos;
    }

    public void setListaEstructuraGastos(List<TEstructura> listaEstructuraGastos) {
        this.listaEstructuraGastos = listaEstructuraGastos;
    }

    public List<TEstructura> getListaEstructuraOtrosGastos() {
        return listaEstructuraOtrosGastos;
    }

    public void setListaEstructuraOtrosGastos(List<TEstructura> listaEstructuraOtrosGastos) {
        this.listaEstructuraOtrosGastos = listaEstructuraOtrosGastos;
    }

    public List<TEstructura> getListaEstructuraOtrosIngresos() {
        return listaEstructuraOtrosIngresos;
    }

    public void setListaEstructuraOtrosIngresos(List<TEstructura> listaEstructuraOtrosIngresos) {
        this.listaEstructuraOtrosIngresos = listaEstructuraOtrosIngresos;
    }

    public String getMsgIngresos() {
        return msgIngresos;
    }

    public void setMsgIngresos(String msgIngresos) {
        this.msgIngresos = msgIngresos;
    }

    public String getMsgCostos() {
        return msgCostos;
    }

    public void setMsgCostos(String msgCostos) {
        this.msgCostos = msgCostos;
    }

    public String getMsgGastos() {
        return msgGastos;
    }

    public void setMsgGastos(String msgGastos) {
        this.msgGastos = msgGastos;
    }

    public String getMsgOtrosGastos() {
        return msgOtrosGastos;
    }

    public void setMsgOtrosGastos(String msgOtrosGastos) {
        this.msgOtrosGastos = msgOtrosGastos;
    }

    public String getMsgOtrosIngresos() {
        return msgOtrosIngresos;
    }

    public void setMsgOtrosIngresos(String msgOtrosIngresos) {
        this.msgOtrosIngresos = msgOtrosIngresos;
    }

    public String getMsgRenta() {
        return msgRenta;
    }

    public void setMsgRenta(String msgRenta) {
        this.msgRenta = msgRenta;
    }

    public String getMsgReserva() {
        return msgReserva;
    }

    public void setMsgReserva(String msgReserva) {
        this.msgReserva = msgReserva;
    }

    public String getMsgUtilidad() {
        return msgUtilidad;
    }

    public void setMsgUtilidad(String msgUtilidad) {
        this.msgUtilidad = msgUtilidad;
    }

    public Boolean getConsiderarRenta() {
        return considerarRenta;
    }

    public void setConsiderarRenta(Boolean considerarRenta) {
        this.considerarRenta = considerarRenta;
    }

    public Boolean getConsiderarReserva() {
        return considerarReserva;
    }

    public void setConsiderarReserva(Boolean considerarReserva) {
        this.considerarReserva = considerarReserva;
    }

    public TEstructura getRentaEstructura() {
        return rentaEstructura;
    }

    public void setRentaEstructura(TEstructura rentaEstructura) {
        this.rentaEstructura = rentaEstructura;
    }

    public TEstructura getReservaEstructura() {
        return ReservaEstructura;
    }

    public void setReservaEstructura(TEstructura ReservaEstructura) {
        this.ReservaEstructura = ReservaEstructura;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public TCuenta getCuentaAuxEstado() {
        return cuentaAuxEstado;
    }

    public void setCuentaAuxEstado(TCuenta cuentaAuxEstado) {

        TEstructura tempEst = new TEstructura();
        tempEst.setTCuenta(cuentaAuxEstado);
        tempEst.setGrupoReporte(this.idGrupo);
        tempEst.setTEjercicio(ejerAbierto);
        if (this.idGrupo <= 8) {
            tempEst.setTipoReporte(1);
        } else {
            tempEst.setTipoReporte(2);
        }

        switch (this.idGrupo) {
            case 1:
                this.listaEstructuraIngresos.add(tempEst);
                break;
            case 2:
                this.listaEstructuraCostos.add(tempEst);
                break;
            case 3:
                this.listaEstructuraGastos.add(tempEst);
                break;
            case 4:
                this.listaEstructuraOtrosIngresos.add(tempEst);
                break;
            case 5:
                this.listaEstructuraOtrosGastos.add(tempEst);
                break;

            case 6:
                this.rentaEstructura = tempEst;
                break;
            case 7:
                this.ReservaEstructura = tempEst;
                break;
            case 8:
                this.utilidadEstructura = tempEst;
                break;
            case 11:
                this.estructuraActivo = tempEst;
                break;
            case 12:
                this.estructuraPasivo = tempEst;
                break;
            case 13:
                this.estructuraCapital = tempEst;
                break;
        }

        this.cuentaAuxEstado = cuentaAuxEstado;
    }

    public void removeListEstado(Integer i, Integer idGrupo) {

        switch (idGrupo) {
            case 1:
                this.listaEstructuraIngresos.remove(this.listaEstructuraIngresos.get(i));
                break;
            case 2:
                this.listaEstructuraCostos.remove(this.listaEstructuraCostos.get(i));
                break;
            case 3:
                this.listaEstructuraGastos.remove(this.listaEstructuraGastos.get(i));
                break;
            case 4:
                this.listaEstructuraOtrosIngresos.remove(this.listaEstructuraOtrosIngresos.get(i));
                break;
            case 5:
                this.listaEstructuraOtrosGastos.remove(this.listaEstructuraOtrosGastos.get(i));
                break;

        }

    }

    public void validarFormularioEstado() {

        this.estadoValidoEstado = true;

        if (this.listaEstructuraIngresos.isEmpty()) {
            this.estadoValidoEstado = false;
            this.msgIngresos = "Cuenta requerida";
        } else {
            this.msgIngresos = "";
        }

        if (this.listaEstructuraCostos.isEmpty()) {
            this.estadoValidoEstado = false;
            this.msgCostos = "Cuenta requerida";
        } else {
            this.msgCostos = "";
        }

        if (this.listaEstructuraGastos.isEmpty()) {
            this.estadoValidoEstado = false;
            this.msgGastos = "Cuenta requerida";
        } else {
            this.msgGastos = "";
        }

        if (this.listaEstructuraOtrosGastos.isEmpty()) {
            this.estadoValidoEstado = false;
            this.msgOtrosGastos = "Cuenta requerida";
        } else {
            this.msgOtrosGastos = "";
        }

        if (this.listaEstructuraOtrosIngresos.isEmpty()) {
            this.estadoValidoEstado = false;
            this.msgOtrosIngresos = "Cuenta  requerida";
        } else {
            this.msgOtrosIngresos = "";
        }

        if (this.rentaEstructura.getTCuenta() == null && this.considerarRenta) {
            this.estadoValidoEstado = false;
            this.msgRenta = "Cuenta  requerida";
        } else {
            this.msgRenta = "";
        }

        if (this.ReservaEstructura.getTCuenta() == null && this.considerarReserva) {
            this.estadoValidoEstado = false;
            this.msgReserva = "Cuenta  requerida";
        } else {
            this.msgReserva = "";
        }

        if (this.utilidadEstructura.getTCuenta() == null) {
            this.estadoValidoEstado = false;
            this.msgUtilidad = "Cuenta requerida";
        } else {
            this.msgUtilidad = "";
        }

        if (this.rentaEstructura.getValorRango().equals(BigDecimal.ZERO) && this.considerarRenta) {
            this.estadoValidoEstado = false;
            this.msgValorRef = "Valor requerido";
        } else {
            this.msgValorRef = "";
        }

        if (this.rentaEstructura.getPorMaxEstructura().equals(BigDecimal.ZERO) && this.considerarRenta) {
            this.estadoValidoEstado = false;
            this.msgValorMax = "Valor requerido";
        } else {
            this.msgValorMax = "";
        }

        if (this.rentaEstructura.getPorMinEstructura().equals(BigDecimal.ZERO) && this.considerarRenta) {
            this.estadoValidoEstado = false;
            this.msgValorMin = "Valor requerido";
        } else {
            this.msgValorMin = "";
        }

        if (this.ReservaEstructura.getPorMinEstructura().equals(BigDecimal.ZERO) && this.considerarReserva) {
            this.estadoValidoEstado = false;
            this.msgValorRes = "Valor requerido";
        } else {
            this.msgValorRes = "";
        }

    }

    public void limpiarEstado() {

        this.estadoValidoEstado = false;
        this.msgIngresos = "";
        this.msgCostos = "";
        this.msgGastos = "";
        this.msgOtrosGastos = "";
        this.msgOtrosGastos = "";
        this.msgRenta = "";
        this.msgReserva = "";
        this.msgUtilidad = "";

    }

    public void registrarEstado() {

        try {

            /* Elimino de la BD la configuracion Actual si la hay*/
 /* parametro 1 indica tipo de reporte Estado de Resultado*/
            this.estructuraBo.delete(ejerAbierto.getIdEjercicio(), 1);

            /*Inserto la lista de cuentas para ingresos*/
            for (TEstructura listaEstructuraIngreso : listaEstructuraIngresos) {

                this.estructuraBo.create(listaEstructuraIngreso);

            }

            /*Inserto la lista de cuentas para costos*/
            for (TEstructura listaEstructuraCosto : listaEstructuraCostos) {

                this.estructuraBo.create(listaEstructuraCosto);

            }

            /*Inserto la lista de cuentas para gastos*/
            for (TEstructura listaEstructuraGasto : listaEstructuraGastos) {

                this.estructuraBo.create(listaEstructuraGasto);

            }

            /*Inserto la lista de cuentas para otros ingresos*/
            for (TEstructura listaEstructuraOtroIngreso : listaEstructuraOtrosIngresos) {

                this.estructuraBo.create(listaEstructuraOtroIngreso);

            }

            /*Inserto la lista de cuentas para otros gastos*/
            for (TEstructura listaEstructuraOtroGasto : listaEstructuraOtrosGastos) {

                this.estructuraBo.create(listaEstructuraOtroGasto);

            }

            /*Inserto la cuenta renta*/
            if (this.considerarRenta) {
                this.estructuraBo.create(rentaEstructura);
            }

            /*Inserto la cuenta reserva*/
            if (this.considerarReserva) {
                this.estructuraBo.create(ReservaEstructura);
            }

            /*Inserto la cuenta utilidad*/
            this.estructuraBo.create(utilidadEstructura);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_estructura");
            auxBitacora.setAccionBitacora("Configurar estado de resultados");
            auxBitacora.setDatosBitacora("Ejercicio :" + ejerAbierto.getAhoEjercicio() + "Entidad:" + this.idEntidad);
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            limpiarEstado();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado de resultado configurado correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Estado de resultado no pudo ser configurado.", ""));
        }

    }

    /**
     * Metodos Configuracion Estado de Resultado FIN
     */
    /**
     * Metodos Entidad predeterminada
     */
    public void cambiarPredeterminado() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        loginBean.setIdEntidad(this.idEntidad);

        loginBean.setPredeterminado(estadoPredeterminado);
    }

    public Boolean getEstadoPredeterminado() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        estadoPredeterminado = loginBean.getPredeterminado();

        if (estadoPredeterminado) {
            setIdEntidad(loginBean.getIdEntidad());
        }

        return estadoPredeterminado;

    }

    public void setEstadoPredeterminado(Boolean estadoPredeterminado) {
        this.estadoPredeterminado = estadoPredeterminado;
    }

    /**
     * Metodos Entidad predeterminada FIN
     */
    public Integer getIdTab() {
        return idTab;
    }

    public void setIdTab(Integer idTab) {
        this.idTab = idTab;
    }

    public IActivoFijoBo getActivoFijoBo() {
        return activoFijoBo;
    }

    public void setActivoFijoBo(IActivoFijoBo activoFijoBo) {
        this.activoFijoBo = activoFijoBo;
    }

    public List<TEntidad> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(List<TEntidad> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    public IEntidadBo getEntidadBo() {
        return entidadBo;
    }

    public void setEntidadBo(IEntidadBo entidadBo) {
        this.entidadBo = entidadBo;
    }

    public Boolean getEstadoValidoBalance() {
        return estadoValidoBalance;
    }

    public void setEstadoValidoBalance(Boolean estadoValidoBalance) {
        this.estadoValidoBalance = estadoValidoBalance;
    }

    public String getMsgEstActivo() {
        return msgEstActivo;
    }

    public void setMsgEstActivo(String msgEstActivo) {
        this.msgEstActivo = msgEstActivo;
    }

    public String getMsgEstPasivo() {
        return msgEstPasivo;
    }

    public void setMsgEstPasivo(String msgEstPasivo) {
        this.msgEstPasivo = msgEstPasivo;
    }

    public String getMsgEstCapital() {
        return msgEstCapital;
    }

    public void setMsgEstCapital(String msgEstCapital) {
        this.msgEstCapital = msgEstCapital;
    }

    public TEstructura getEstructuraActivo() {
        return estructuraActivo;
    }

    public void setEstructuraActivo(TEstructura estructuraActivo) {
        this.estructuraActivo = estructuraActivo;
    }

    public TEstructura getEstructuraPasivo() {
        return estructuraPasivo;
    }

    public void setEstructuraPasivo(TEstructura estructuraPasivo) {
        this.estructuraPasivo = estructuraPasivo;
    }

    public TEstructura getEstructuraCapital() {
        return estructuraCapital;
    }

    public void setEstructuraCapital(TEstructura estructuraCapital) {
        this.estructuraCapital = estructuraCapital;
    }

    public List<TCuenta> getListaCuentas() {

        return listaCuentas;
    }

    public List<TCuenta> getListaCuentasBalance() {
        return listaCuentasBalance;
    }

    public void setListaCuentasBalance(List<TCuenta> listaCuentasBalance) {
        this.listaCuentasBalance = listaCuentasBalance;
    }

    public void setListaCuentas(List<TCuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public IEstructuraBo getEstructuraBo() {
        return estructuraBo;
    }

    public void setEstructuraBo(IEstructuraBo estructuraBo) {
        this.estructuraBo = estructuraBo;
    }

    public ITipoActivoBo getTipoActivoBo() {
        return tipoActivoBo;
    }

    public void setTipoActivoBo(ITipoActivoBo tipoActivoBo) {
        this.tipoActivoBo = tipoActivoBo;
    }

    public IAreaBo getAreaBo() {
        return areaBo;
    }

    public void setAreaBo(IAreaBo areaBo) {
        this.areaBo = areaBo;
    }

    public ICuentaBo getCuentaBo() {
        return cuentaBo;
    }

    public void setCuentaBo(ICuentaBo cuentaBo) {
        this.cuentaBo = cuentaBo;
    }

    public TConfiguracion getConfiguracionActual() {
        return configuracionActual;
    }

    public void setConfiguracionActual(TConfiguracion configuracionActual) {
        this.configuracionActual = configuracionActual;
    }

    public String getMsgCuenta() {
        return msgCuenta;
    }

    public void setMsgCuenta(String msgCuenta) {
        this.msgCuenta = msgCuenta;
    }

    public String getMsgTipo() {
        return msgTipo;
    }

    public void setMsgTipo(String msgTipo) {
        this.msgTipo = msgTipo;
    }

    public String getMsgArea() {
        return msgArea;
    }

    public void setMsgArea(String msgArea) {
        this.msgArea = msgArea;
    }

    public String getMsgActivo() {
        return msgActivo;
    }

    public void setMsgActivo(String msgActivo) {
        this.msgActivo = msgActivo;
    }

    public Boolean getEstadoValido() {
        return estadoValido;
    }

    public void setEstadoValido(Boolean estadoValido) {
        this.estadoValido = estadoValido;
    }

    public IConfiguracionBo getConfiguracionBo() {
        return configuracionBo;
    }

    public void setConfiguracionBo(IConfiguracionBo configuracionBo) {
        this.configuracionBo = configuracionBo;
    }

    public void init() {
        idEntidad = 0;
        idTab = 0;
        this.estadoValido = false;
        this.estadoValidoBalance = false;
        this.estadoValidoEstado = false;
        this.listaCuentasBalance = new ArrayList<>();

    }

    public TConfiguracion getConfiguracion() {

        return configuracion;
    }

    public void setConfiguracion(TConfiguracion configuracion) {
        this.configuracion = configuracion;
    }

    public final void onTabChange(final TabChangeEvent event) {
        TabView tv = (TabView) event.getComponent();
        this.idTab = tv.getActiveIndex();

    }

    /*Configuracion para Balance General*/
    public void registrarBalance() {
        try {

            /* Elimino de la BD la configuracion Actual si la hay*/
 /* parametro 2 indica tipo de reporte Balance General*/
            this.estructuraBo.delete(ejerAbierto.getIdEjercicio(), 2);

            /*Inserto la lista de cuentas para Activo*/
            this.estructuraBo.create(this.estructuraActivo);

            /*Inserto la lista de cuentas para Pasivo*/
            this.estructuraBo.create(this.estructuraPasivo);

            /*Inserto la lista de cuentas para Capital*/
            this.estructuraBo.create(this.estructuraCapital);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_estructura");
            auxBitacora.setAccionBitacora("Configurar balance general");
            auxBitacora.setDatosBitacora("Ejercicio :" + ejerAbierto.getAhoEjercicio() + "Entidad:" + this.idEntidad);
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            this.limpiarBalance();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estructura configurada correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La estructura  no pudo ser configurada.", ""));
        }
    }

    public void validarFormularioBalance() {

        this.estadoValidoBalance = true;

        if (this.estructuraActivo.getTCuenta() == null) {
            msgEstActivo = "Seleccione la cuenta";
            this.estadoValidoBalance = false;
        } else {
            msgEstActivo = "";
        }

        if (this.estructuraPasivo.getTCuenta() == null) {
            msgEstPasivo = "Seleccione la cuenta ";
            this.estadoValidoBalance = false;
        } else {
            msgEstPasivo = "";
        }

        if (this.estructuraCapital.getTCuenta() == null) {
            msgEstCapital = "Seleccione la cuenta ";
            this.estadoValidoBalance = false;
        } else {
            msgEstCapital = "";
        }

        if (this.estructuraActivo.getNivelReporte() == null || this.estructuraActivo.getNivelReporte() < 1) {
            msgEstActivoNivel = "El nivel de detalle  invalido";
            this.estadoValidoBalance = false;
        } else {
            msgEstActivoNivel = "";
        }

        if (this.estructuraPasivo.getNivelReporte() == null || this.estructuraPasivo.getNivelReporte() < 1) {
            msgEstPasivoNivel = "El nivel de detalle  invalido";
            this.estadoValidoBalance = false;
        } else {
            msgEstPasivoNivel = "";
        }

        if (this.estructuraCapital.getNivelReporte() == null || this.estructuraCapital.getNivelReporte() < 1) {
            msgEstCapitalNivel = "El nivel de detalle invalido";
            this.estadoValidoBalance = false;
        } else {
            msgEstCapitalNivel = "";
        }

    }

    public void limpiarBalance() {

        estadoValidoBalance = false;

        msgEstActivo = "";

        msgEstPasivo = "";

        msgEstCapital = "";

    }

    /*Configuracion para la estructura de codigos*/
    public void registrar() {
        try {

            if (configuracionActual != null) {

                if (!this.configuracionActual.getCuentaConfiguracion().equals(this.configuracion.getCuentaConfiguracion())) {

                    List<TCuenta> listaCuentas = this.cuentaBo.listCuentaEnt(idEntidad);

                    for (TCuenta listaCuenta : listaCuentas) {
                        listaCuenta.setCodigoCuenta(this.cambiaCodigo(listaCuenta.getCodigoCuenta(), this.configuracion.getCuentaConfiguracion()));
                        this.cuentaBo.update(listaCuenta);
                    }
                }

                if (!this.configuracionActual.getAreaConfiguracion().equals(this.configuracion.getAreaConfiguracion())) {

                    List<TArea> listaAreas = this.areaBo.listArea(idEntidad);

                    for (TArea listaArea : listaAreas) {
                        listaArea.setCodigoArea(this.cambiaCodigo(listaArea.getCodigoArea(), this.configuracion.getAreaConfiguracion()));
                        this.areaBo.update(listaArea);
                    }
                }

                if (!this.configuracionActual.getTipoConfiguracion().equals(this.configuracion.getTipoConfiguracion())) {

                    List<TTipoActivo> listaTipoActivos = this.tipoActivoBo.listTipoActivo(idEntidad);

                    for (TTipoActivo listaTipoActivo : listaTipoActivos) {
                        listaTipoActivo.setCodigoTipo(this.cambiaCodigo(listaTipoActivo.getCodigoTipo(), this.configuracion.getTipoConfiguracion()));
                        this.tipoActivoBo.update(listaTipoActivo);
                    }
                }

                if (!this.configuracionActual.getActivoConfiguracion().equals(this.configuracion.getActivoConfiguracion())) {

                    List<TActivoFijo> listaActivos = this.activoFijoBo.listActivoFijo(idEntidad);

                    for (TActivoFijo Activo : listaActivos) {
                        Activo.setCodigoActivoFijo(this.cambiaCodigo(Activo.getCodigoActivoFijo(), this.configuracion.getActivoConfiguracion()));
                        this.activoFijoBo.update(Activo);
                    }
                }

                this.configuracionBo.update(configuracion);
                TBitacora auxBitacora = new TBitacora();
                auxBitacora.setTableBitacora("t_configuracion");
                auxBitacora.setAccionBitacora("Agregar estructura de códigos: cuentas, tipos de activos, áreas y activos");
                auxBitacora.setDatosBitacora("Entidad:" + this.idEntidad);
                auxBitacora.setHoraBitacora(new Date());
                auxBitacora.setFechaBitacora(new Date());
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                // Get the loginBean from session attribute
                LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
                auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
                bitacoraBo.create(auxBitacora);

                this.limpiar();
            } else {
                this.configuracion.setTEntidad(new TEntidad(idEntidad));
                this.configuracionBo.create(configuracion);
                TBitacora auxBitacora = new TBitacora();
                auxBitacora.setTableBitacora("t_configuracion");
                auxBitacora.setAccionBitacora("Modificar estructura de códigos: cuentas, tipos de activos, áreas y activos");
                auxBitacora.setDatosBitacora("Entidad:" + this.idEntidad);
                auxBitacora.setHoraBitacora(new Date());
                auxBitacora.setFechaBitacora(new Date());
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                // Get the loginBean from session attribute
                LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
                auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
                bitacoraBo.create(auxBitacora);
                this.limpiar();

            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Configuración  realizada correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La configuración  no pudo ser realizada.", ""));
        }
    }

    public void validarFormulario() {

        this.estadoValido = true;

        if (configuracionActual != null) {
            this.msgCuenta = this.validaCodigo(this.configuracionActual.getCuentaConfiguracion(), this.configuracion.getCuentaConfiguracion());
            if (!this.msgCuenta.equals("")) {
                this.estadoValido = false;
            }

            this.msgArea = this.validaCodigo(this.configuracionActual.getAreaConfiguracion(), this.configuracion.getAreaConfiguracion());
            if (!this.msgArea.equals("")) {
                this.estadoValido = false;
            }

            this.msgTipo = this.validaCodigo(this.configuracionActual.getTipoConfiguracion(), this.configuracion.getTipoConfiguracion());
            if (!this.msgTipo.equals("")) {
                this.estadoValido = false;
            }

            this.msgActivo = this.validaCodigo(this.configuracionActual.getActivoConfiguracion(), this.configuracion.getActivoConfiguracion());
            if (!this.msgActivo.equals("")) {
                this.estadoValido = false;
            }

        } else {

            this.msgCuenta = this.validaCodigo(" ", this.configuracion.getCuentaConfiguracion());
            if (!this.msgCuenta.equals("")) {
                this.estadoValido = false;
            }

            this.msgArea = this.validaCodigo(" ", this.configuracion.getAreaConfiguracion());
            if (!this.msgArea.equals("")) {
                this.estadoValido = false;
            }

            this.msgTipo = this.validaCodigo(" ", this.configuracion.getTipoConfiguracion());
            if (!this.msgTipo.equals("")) {
                this.estadoValido = false;
            }

            this.msgActivo = this.validaCodigo(" ", this.configuracion.getActivoConfiguracion());
            if (!this.msgActivo.equals("")) {
                this.estadoValido = false;
            }

        }
    }

    public void limpiar() {

        estadoValido = false;

        msgCuenta = "";

        msgTipo = "";

        msgArea = "";

        msgActivo = "";

    }

    public String validaCodigo(String actual, String nuevo) {
        String msj = "";

        if (!actual.equals(nuevo)) {

            String auxActual[] = actual.split("-");
            String aux[] = nuevo.split("-");
            String codigo = nuevo;
            int longitud = nuevo.length();
            int longitudAut = actual.length();

            if (longitud == 0) {

                msj = "Estructura requerida";
            } else if (codigo.substring(0, 1).equals("-")) {
                msj = "La estructura no puede iniciar con guion";
            } else if (codigo.substring(longitud - 1, longitud).equals("-")) {
                msj = "La estructura no puede finalizar con guion";
            } else if (auxActual.length > aux.length) {
                msj = "Imposible borrar segmentos";
            } else if (longitud < longitudAut) {

                int index = 0;
                for (String auxAct : auxActual) {
                    if (aux[index].length() < auxAct.length()) {
                        msj = "La estructura no puede cambiar de " + auxAct + " a " + aux[index] + " en el segmento " + new Integer(index + 1);

                        break;
                    }
                    index++;
                }
            } else {

                int ini = 0;
                for (int fin = 0; fin < longitud - 1; fin++) {
                    if (codigo.substring(ini, fin + 1).equals(codigo.substring(ini + 1, fin + 2)) && codigo.substring(ini, fin + 1).equals("-")) {
                        msj = "La estructura no puede contener dos guiones consecutivos";

                    }
                    ini++;
                }

                ini = 0;
                for (int fin = 1; fin <= longitud; fin++) {
                    if (!codigo.substring(ini, fin).equals("9") && !codigo.substring(ini, fin).equals("-")) {
                        msj = "La estructura debe estar formada por 9 y - ";

                    }
                    ini++;
                }

            }

        }
        return msj;

    }

    public String cambiaCodigo(String actual, String nuevo) {

        String auxActual[] = actual.split("-");
        String aux[] = nuevo.split("-");
        String codigo = "";
        String agradado = "";

        int index = 0;
        for (String auxAct : auxActual) {
            if (aux[index].length() > auxAct.length()) {

                agradado = "";
                for (int i = 0; i < aux[index].length() - auxAct.length(); i++) {

                    agradado = agradado + "0";

                }
                if (index == 0) {
                    codigo = codigo + agradado + auxAct;
                } else {
                    codigo = codigo + "-" + agradado + auxAct;
                }
            } else if (index == 0) {
                codigo = codigo + auxAct;
            } else {
                codigo = codigo + "-" + auxAct;
            }
            index++;
        }

        String complemento = nuevo.substring(codigo.length());
        String complementoAux = "";
        int ini = 0;
        for (int fin = 0; fin < complemento.length(); fin++) {
            if (complemento.substring(ini, fin + 1).equals("9")) {
                complementoAux = complementoAux + "0";
            } else {
                complementoAux = complementoAux + "-";
            }
            ini++;
        }

        return codigo + complementoAux;

    }

}
