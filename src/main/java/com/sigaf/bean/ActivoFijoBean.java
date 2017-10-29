/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IActivoFijoBo;
import com.sigaf.Ibo.IBajaActivoFijoBo;
import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.ICuentaBo;
import com.sigaf.Ibo.IDepreciacionBo;
import com.sigaf.Ibo.IDetalleParidaBo;
import com.sigaf.Ibo.IEjercicioBo;
import com.sigaf.Ibo.IEmpleadoBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Ibo.IPartidaBo;
import com.sigaf.Ibo.IPeriodoBo;
import com.sigaf.Ibo.IValorActivoBo;
import com.sigaf.bo.AreaBo;
import com.sigaf.bo.ConfiguracionBo;
import com.sigaf.bo.ProveedorBo;
import com.sigaf.bo.TipoActivoBo;
import com.sigaf.pojo.TActivoFijo;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TBajaActivoFijo;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TConfiguracion;
import com.sigaf.pojo.TCuenta;
import com.sigaf.pojo.TDepreciacion;
import com.sigaf.pojo.TDetallePartida;
import com.sigaf.pojo.TEjercicio;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEmpleadoArea;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TPartida;
import com.sigaf.pojo.TPeriodo;
import com.sigaf.pojo.TProveedor;
import com.sigaf.pojo.TTipoActivo;
import com.sigaf.pojo.TValorActivo;
import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
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
@ManagedBean
@RequestScoped
public class ActivoFijoBean extends Actividad {

    private IBitacoraBo bitacoraBo;

    private IBajaActivoFijoBo bajaActivoFijoBo;

    private IDepreciacionBo depreciacionBo;

    private List<TDepreciacion> listaDepreciaciones;

    private BigDecimal valorLibro;

    private BigDecimal valorVenta;

    private BigDecimal valorDepre;

    private BigDecimal valorActivoBaja;

    private IEntidadBo entidadBo;

    private IValorActivoBo valorActivoBo;

    private TValorActivo valorActivo;

    private IPartidaBo partidaBo;

    private IDetalleParidaBo detallePartidaBo;

    private IPeriodoBo periodoBo;

    private IEjercicioBo ejercicioBo;

    private ICuentaBo cuentaBo;

    private IEmpleadoBo empleadoBo;

    private IActivoFijoBo activoFijoBo;

    private ProveedorBo proveedorBo;

    private TipoActivoBo tipoActivoBo;

    private ConfiguracionBo configuracionBo;

    private TPartida partida;

    private TDetallePartida detallePartida;

    private List<TDetallePartida> listaDetallePartida;

    private List<TCuenta> listaCuentas;

    private TPeriodo perido;

    private TEjercicio ejercicio;

    private TCuenta cuenta;

    private BigDecimal totalDebe;

    private BigDecimal totalHaber;

    private Boolean estadoFormulario;

    private String msgConcepto;

    private String msgNo;

    private String msgCantidad;

    private String msgConceptoDetalle;

    private String msgDetallePar;

    private String msgCuadre;

    private Integer numPartida;

    private String codigoActivoSeleccionado;

    /**
     * Activo datos
     */
    private TEntidad entidadSeleccionada;

    private TConfiguracion configuracion;

    private AreaBo areaBo;

    private List<TArea> listaArea;

    private List<TTipoActivo> ListaTipos;

    private List<TProveedor> listaProveedor;

    private List<TEmpleado> listaEmpleado;

    private TActivoFijo activoFijo;

    private TActivoFijo activoFijoSeleccionado;

    private Integer idArea;

    private Integer idTipoActivo;

    private TArea area;

    private TTipoActivo tipoActivo;

    private String codTip;

    private Boolean estadoValido;

    private String msgArea;

    private String msgEmpleado;

    private String msgTipo;

    private String msgDesc;

    private String msgValor;

    private String msgProv;

    private String msgCod;

    private String msgTipoBaja;

    private Boolean showBaja;

    private Boolean showRegistro;

    private Boolean showRegistroModificar;

    private Integer idTab;

    private String tipoBaja;

    private String msgValorActivoBaja;

    private Date fechaActual;

    private Date fechaMinima;

    private Date fechaMaxima;

    private String msgFechaComp;

    private String msgFechaReg;

    public String getMsgFechaComp() {
        return msgFechaComp;
    }

    public void setMsgFechaComp(String msgFechaComp) {
        this.msgFechaComp = msgFechaComp;
    }

    public String getMsgFechaReg() {
        return msgFechaReg;
    }

    public void setMsgFechaReg(String msgFechaReg) {
        this.msgFechaReg = msgFechaReg;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public Date getFechaMinima() {
        return fechaMinima;
    }

    public void setFechaMinima(Date fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    public TEntidad getEntidadSeleccionada() {
        return entidadSeleccionada;
    }

    public void setEntidadSeleccionada(TEntidad entidadSeleccionada) {
        this.entidadSeleccionada = entidadSeleccionada;
    }

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public void updateConfiguracion() {
        configuracion = this.configuracionBo.getConfiguracion(entidadSeleccionada.getIdEntidad());
    }

    public void updateListaProveedor() {
        listaProveedor = this.proveedorBo.listProveedor(entidadSeleccionada.getIdEntidad());
    }

    public void updateListaArea() {
        listaArea = this.areaBo.listArea(entidadSeleccionada.getIdEntidad());
    }

    public void updateListaTipos() {
        ListaTipos = this.tipoActivoBo.listTipoActivo(entidadSeleccionada.getIdEntidad());
    }

    public void updateListaActivoFijo() {
        this.listaActivoFijo = this.activoFijoBo.listActivoFijo(entidadSeleccionada.getIdEntidad());
    }

    public void updateListaCuentas() {
        this.listaCuentas = this.cuentaBo.listCuentaEntActTip(entidadSeleccionada.getIdEntidad(), true);
    }

    public void setTipoBaja(String tipoBaja) {
        this.valorVenta = BigDecimal.ZERO;
        this.tipoBaja = tipoBaja;
    }

    /*
    * Se recorre la lista dado que necesitamos toda la informacion 
    * del area. 
     */
    public void setIdArea(Integer idArea) {

        this.idArea = idArea;

        for (TArea tArea : listaArea) {

            if (tArea.getIdArea() == idArea) {
                this.area = tArea;
                /* Consultamos las los empleados atrabes de EmpleadoArea 1 = Empleados 2- Socios*/
                this.listaEmpleado = this.empleadoBo.listEmpleadosOrSocios(idArea, 1);

                return;
            }

        }

        /* Si no Encontro el Area */
        this.area.setCodigoArea("");
        this.listaEmpleado = null;

    }

    /*
    * Se recorre la lista dado que necesitamos toda la informacion 
    * del tipo. 
     */
    public void setIdTipoActivo(Integer idTipoActivo) {

        this.idTipoActivo = idTipoActivo;

        for (TTipoActivo tTipo : ListaTipos) {

            if (tTipo.getIdTipo() == idTipoActivo) {
                this.tipoActivo = tTipo;
                return;
            }
        }
        /* Si no encontro el Tipo */
        this.tipoActivo.setCodigoTipo("");
    }

    public void getContabilidadPredeterminado() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        ContablidadPredeterminarBean ContPreBean = (ContablidadPredeterminarBean) request.getSession().getAttribute("contablidadPredeterminarBean");

        this.entidadSeleccionada = ContPreBean.getEntidadSeleccionada();
    }

    /**
     * Metodo que Busca el Area al que corresponde El usuario
     *
     * @param emp
     */
    public void buscarEmpleadoArea(TEmpleado emp) {

        List<TEmpleadoArea> listEmpleAreAct = new ArrayList(emp.getTEmpleadoAreas());

        for (TArea tArea : listaArea) {
            if (tArea.getIdArea() == listEmpleAreAct.get(0).getTArea().getIdArea()) {
                this.area = tArea;
                return;
            }
        }
    }

    /**
     * Arma el codigo del ACtivo Fijo
     *
     * @param activo
     * @return
     */
    public String cogigoActivoFijo(TActivoFijo activo) {

        this.buscarEmpleadoArea(activo.getTEmpleado());

        return this.entidadSeleccionada.getCodigoEntidad() + "-" + this.area.getCodigoArea() + "-" + activo.getTTipoActivo().getCodigoTipo() + "-" + activo.getCodigoActivoFijo();

    }

    public String cogigoActivoFijoSeleccionado() {

        return this.entidadSeleccionada.getCodigoEntidad() + "-" + area.getCodigoArea() + "-" + activoFijoSeleccionado.getTTipoActivo().getCodigoTipo() + "-" + activoFijoSeleccionado.getCodigoActivoFijo();
    }

    /*
    *Verifica Si el Registro contable ya se hizo
    *de ser asi retorna false indicando que no se puede dar 
    *de baja o de alta normalmente, de lo contrario false
     */
    public Boolean estadoRegistroContable(Set tValorActivos) {

        List<TValorActivo> listValorActivo = new ArrayList(tValorActivos);

        if (null != listValorActivo.get(0).getTPartida()) {
            return false;
        } else {
            return true;
        }
    }

    /*
    *Verifica Si el Registro contable ya se hizo
    *de ser asi retorna false indicando que no se puede dar 
    *de baja o de alta normalmente, de lo contrario false
     */
    public Boolean estadoRegistroContableModificar() {

        /* Sacando el Valor del Activo Fijo*/
        List<TValorActivo> listValorActivo = new ArrayList(this.activoFijoSeleccionado.getTValorActivos());

        if (null != listValorActivo.get(0).getTPartida()) {
            return true;
        } else {
            return false;
        }
    }

    public void setActivoFijoSeleccionado(TActivoFijo activoFijoSeleccionado) {

        this.activoFijoSeleccionado = activoFijoSeleccionado;

        /* Asigna el Area */
        this.buscarEmpleadoArea(activoFijoSeleccionado.getTEmpleado());

        this.setIdArea(this.area.getIdArea());

        this.codigoActivoSeleccionado = this.cogigoActivoFijoSeleccionado();

        this.setIdTipoActivo(this.activoFijoSeleccionado.getTTipoActivo().getIdTipo());

        /* Sacando el Valor del Activo Fijo*/
        List<TValorActivo> listValorActivo = new ArrayList(activoFijoSeleccionado.getTValorActivos());

        this.valorActivo = listValorActivo.get(0);

        /*
        * si no se le dara de baja
         */
        if (!this.showBaja) {

            this.totalDebe = BigDecimal.ZERO;

            this.totalHaber = BigDecimal.ZERO;

            /*
            * si ya tiene registro  se caraga la partida
             */
            if (null != valorActivo.getTPartida()) {

                this.partida = this.partidaBo.getPartida(this.valorActivo.getTPartida().getIdPartida());

                this.listaDetallePartida = this.detallePartidaBo.listDetallePartida(partida.getIdPartida());

                for (TDetallePartida tDetallePartida : this.listaDetallePartida) {

                    if (tDetallePartida.getTipoSaldoDetallePartida().equals("Debe")) {
                        this.totalDebe = this.totalDebe.add(tDetallePartida.getSaldoDetallePartida());
                    } else {
                        this.totalHaber = this.totalHaber.add(tDetallePartida.getSaldoDetallePartida());
                    }
                }

                this.perido = this.periodoBo.getPeriodo(partida.getTPeriodo().getIdPeriodo());

                this.ejercicio = this.ejercicioBo.getEjercicio(this.perido.getTEjercicio().getIdEjercicio());

                int mes = llenarMesPeriodoAux(perido.getMesPeriodo());

                int dia = obtenerUltimoDiaMes(ejercicio.getAhoEjercicio(), mes);

                this.fechaMaxima = new Date(ejercicio.getAhoEjercicio() - 1900, mes, dia);

                this.fechaMinima = new Date(ejercicio.getAhoEjercicio() - 1900, mes, 1);

                numPartida = this.partidaBo.numeroPartida(ejercicio.getIdEjercicio());

            } else {

                this.partida.setConceptoPartida("Registro del activo fijo " + this.codigoActivoSeleccionado);

                this.prepararRegistro();

            }

        } else {

            this.listaDepreciaciones = this.depreciacionBo.listDepreciacion(activoFijoSeleccionado.getIdActivoFijo());

            if (null != listaDepreciaciones) {
                this.valorLibro = BigDecimal.ZERO;
                this.valorDepre = BigDecimal.ZERO;
                valorActivoBaja = valorActivo.getValorActivo();
                for (TDepreciacion listaDepreciacione : listaDepreciaciones) {
                    valorDepre = valorDepre.add(listaDepreciacione.getValorDepreciacion());
                }
                valorLibro = valorActivoBaja.subtract(valorDepre);

            }

            this.partida.setConceptoPartida("Por baja del activo " + this.codigoActivoSeleccionado + " " + this.activoFijoSeleccionado.getDescripcionActivoFijo());

        }

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_activo_fijo");
        auxBitacora.setAccionBitacora("Ver información de activo fijo");
        auxBitacora.setDatosBitacora("Descripción:" + activoFijoSeleccionado.getDescripcionActivoFijo()
                + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
        auxBitacora.setIdTableBitacora(activoFijoSeleccionado.getIdActivoFijo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

    }

    public void prepararRegistro() {

        listaDetallePartida.clear();

        TDetallePartida det = new TDetallePartida();
        /* El tipo de Activo ya Recupera las cuentas en la Consulta */
        det.setTCuenta(tipoActivo.getTCuentaByIdCuentaActivoTipo());

        det.setTipoSaldoDetallePartida("Debe");

        det.setSaldoDetallePartida(this.valorActivo.getValorActivo());

        this.totalDebe = totalDebe.add(this.valorActivo.getValorActivo());

        this.listaDetallePartida.add(0, det);

        this.prepararGeneral();

    }

    /**
     * Asigna el nombre del mes al periodo nuevo segun el perido acual
     */
    public int llenarMesPeriodoAux(String mes) {

        switch (mes) {
            case "Enero":
                return 0;
            case "Febrero":
                return 1;
            case "Marzo":
                return 2;
            case "Abril":
                return 3;
            case "Mayo":
                return 4;
            case "Junio":
                return 5;
            case "Julio":
                return 6;
            case "Agosto":
                return 7;
            case "Septiembre":
                return 8;
            case "Octubre":
                return 9;
            case "Noviembre":
                return 10;
            default:
                return 11;
        }

    }

    public int obtenerUltimoDiaMes(int anio, int mes) {

        Calendar calendario = Calendar.getInstance();
        calendario.set(anio, mes, 1);
        return calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

    }

    public void prepararGeneral() {

        this.ejercicio = this.ejercicioBo.getEjercicioAbierto(entidadSeleccionada.getIdEntidad());

        this.perido = this.periodoBo.getPeriodoAbierto(this.ejercicio.getIdEjercicio());

        int mes = llenarMesPeriodoAux(perido.getMesPeriodo());

        int dia = obtenerUltimoDiaMes(ejercicio.getAhoEjercicio(), mes);

        this.fechaMaxima = new Date(ejercicio.getAhoEjercicio() - 1900, mes, dia);

        this.fechaMinima = new Date(ejercicio.getAhoEjercicio() - 1900, mes, 1);

        numPartida = this.partidaBo.numeroPartida(ejercicio.getIdEjercicio());

        partida.setFechaPartida(this.fechaMinima);
    }

    public void prepararRegistroBAja() {

        listaDetallePartida.clear();

        this.totalDebe = BigDecimal.ZERO;

        this.totalHaber = BigDecimal.ZERO;

        TDetallePartida detDep = new TDetallePartida();

        /* El tipo de Activo ya Recupera las cuentas en la Consulta */
        detDep.setTCuenta(tipoActivo.getTCuentaByIdCuentaDepreciacionTipo());

        detDep.setTipoSaldoDetallePartida("Debe");

        detDep.setSaldoDetallePartida(this.valorDepre);

        this.totalDebe = totalDebe.add(this.valorDepre);

        this.listaDetallePartida.add(detDep);

        TDetallePartida detAc = new TDetallePartida();

        /* El tipo de Activo ya Recupera las cuentas en la Consulta */
        detAc.setTCuenta(tipoActivo.getTCuentaByIdCuentaActivoTipo());

        detAc.setTipoSaldoDetallePartida("Haber");

        detAc.setSaldoDetallePartida(this.valorActivoBaja);

        this.totalHaber = totalHaber.add(this.valorActivoBaja);

        this.listaDetallePartida.add(detAc);

        this.prepararGeneral();
    }

    public IBajaActivoFijoBo getBajaActivoFijoBo() {
        return bajaActivoFijoBo;
    }

    public void setBajaActivoFijoBo(IBajaActivoFijoBo bajaActivoFijoBo) {
        this.bajaActivoFijoBo = bajaActivoFijoBo;
    }

    public IEmpleadoBo getEmpleadoBo() {
        return empleadoBo;
    }

    public void setEmpleadoBo(IEmpleadoBo empleadoBo) {
        this.empleadoBo = empleadoBo;
    }

    public List<TEmpleado> getListaEmpleado() {
        return listaEmpleado;
    }

    public void setListaEmpleado(List<TEmpleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
    }

    public String getMsgValorActivoBaja() {
        return msgValorActivoBaja;
    }

    public void setMsgValorActivoBaja(String msgValorActivoBaja) {
        this.msgValorActivoBaja = msgValorActivoBaja;
    }

    public String getMsgTipoBaja() {
        return msgTipoBaja;
    }

    public void setMsgTipoBaja(String msgTipoBaja) {
        this.msgTipoBaja = msgTipoBaja;
    }

    public String getTipoBaja() {
        return tipoBaja;
    }

    public Integer getIdTab() {
        return idTab;
    }

    public void setIdTab(Integer idTab) {
        this.idTab = idTab;
    }

    public BigDecimal getValorActivoBaja() {
        return valorActivoBaja;
    }

    public void setValorActivoBaja(BigDecimal valorActivoBaja) {
        this.valorActivoBaja = valorActivoBaja;
    }

    public BigDecimal getValorDepre() {
        return valorDepre;
    }

    public void setValorDepre(BigDecimal valorDepre) {
        this.valorDepre = valorDepre;
    }

    public BigDecimal getValorLibro() {
        return valorLibro;
    }

    public void setValorLibro(BigDecimal valorLibro) {
        this.valorLibro = valorLibro;
    }

    public BigDecimal getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(BigDecimal valorVenta) {
        this.valorVenta = valorVenta;
    }

    public List<TDepreciacion> getListaDepreciaciones() {
        return listaDepreciaciones;
    }

    public void setListaDepreciaciones(List<TDepreciacion> listaDepreciaciones) {
        this.listaDepreciaciones = listaDepreciaciones;
    }

    public IDepreciacionBo getDepreciacionBo() {
        return depreciacionBo;
    }

    public void setDepreciacionBo(IDepreciacionBo depreciacionBo) {
        this.depreciacionBo = depreciacionBo;
    }

    public Boolean getShowRegistroModificar() {
        return showRegistroModificar;
    }

    public void setShowRegistroModificar(Boolean showRegistroModificar) {
        this.showRegistroModificar = showRegistroModificar;
    }

    public void enableShowCreateBean() {

        super.enableShowCreate();
        this.limpiar();
        this.limpiarRegistro();
    }

    public void enableShowDataBean() {
        this.showBaja = false;
        this.showRegistro = false;
        this.showRegistroModificar = false;
        super.enableShowData();
        this.limpiar();
        this.limpiarRegistro();
    }

    public void enableShowRegistro() {
        this.showRegistroModificar = false;
        this.showRegistro = true;
        this.setShowCreate(false);
        this.setShowData(false);
        this.setShowUpdate(false);
        this.setShowView(false);
        this.showBaja = false;
    }

    public void enableShowRegistroModificar() {
        this.showRegistroModificar = true;
        this.showRegistro = false;
        this.setShowCreate(false);
        this.setShowData(false);
        this.setShowUpdate(false);
        this.setShowView(false);
        this.showBaja = false;
    }

    public void enableShowBaja() {
        this.showRegistroModificar = false;
        this.showRegistro = false;
        this.setShowCreate(false);
        this.setShowData(false);
        this.setShowUpdate(false);
        this.setShowView(false);
        this.showBaja = true;
    }

    public Boolean getShowRegistro() {
        return showRegistro;
    }

    public String getCodigoActivoSeleccionado() {
        return codigoActivoSeleccionado;
    }

    public void setCodigoActivoSeleccionado(String codigoActivoSeleccionado) {
        this.codigoActivoSeleccionado = codigoActivoSeleccionado;
    }

    public void setShowRegistro(Boolean showRegistro) {
        this.showRegistro = showRegistro;
    }

    public Boolean getShowBaja() {
        return showBaja;
    }

    public void setShowBaja(Boolean showBaja) {
        this.showBaja = showBaja;
    }

    public TActivoFijo getActivoFijoSeleccionado() {
        return activoFijoSeleccionado;
    }

    public TValorActivo getValorActivo() {
        return valorActivo;
    }

    public void setValorActivo(TValorActivo valorActivo) {
        this.valorActivo = valorActivo;
    }

    public IValorActivoBo getValorActivoBo() {
        return valorActivoBo;
    }

    public void setValorActivoBo(IValorActivoBo valorActivoBo) {
        this.valorActivoBo = valorActivoBo;
    }

    public TEjercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(TEjercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public void editListaDetallePartida(Integer i) {

        TDetallePartida detParAux = this.listaDetallePartida.get(i);

        if (detParAux.getTipoSaldoDetallePartida().equals("Debe")) {
            this.totalDebe = this.totalDebe.subtract(detParAux.getSaldoDetallePartida());
        } else {
            this.totalHaber = this.totalHaber.subtract(detParAux.getSaldoDetallePartida());
        }

        this.cuenta = detParAux.getTCuenta();

        this.detallePartida = detParAux;

        this.listaDetallePartida.remove(detParAux);

    }

    public void addListaDetallePartida() {

        Boolean estado = true;

        if (detallePartida.getSaldoDetallePartida().equals(BigDecimal.ZERO)) {
            estado = false;
            this.msgCantidad = "Cantidad  invalida";
        } else {
            this.msgCantidad = "";
        }

        if (cuenta.getCodigoCuenta().length() == 0) {
            estado = false;
            this.msgNo = "Código  requerido";
        } else {
            this.msgNo = "";
        }

        if (estado) {
            this.detallePartida.setTCuenta(cuenta);
            this.detallePartida.setSaldoDetallePartida(detallePartida.getSaldoDetallePartida());
            this.listaDetallePartida.add(detallePartida);

            if (detallePartida.getTipoSaldoDetallePartida().equals("Debe")) {
                this.totalDebe = this.totalDebe.add(detallePartida.getSaldoDetallePartida());
            } else {
                this.totalHaber = this.totalHaber.add(detallePartida.getSaldoDetallePartida());
            }

            detallePartida = new TDetallePartida();
            detallePartida.setSaldoDetallePartida(BigDecimal.ZERO);
            detallePartida.setTipoSaldoDetallePartida("Debe");
            cuenta = new TCuenta();
            cuenta.setCodigoCuenta("");

        }

    }

    public void removeDetalle(Integer i) {

        TDetallePartida detParAux = this.listaDetallePartida.get(i);

        if (detParAux.getTipoSaldoDetallePartida().equals("Debe")) {
            this.totalDebe = this.totalDebe.subtract(detParAux.getSaldoDetallePartida());
        } else {
            this.totalHaber = this.totalHaber.subtract(detParAux.getSaldoDetallePartida());
        }

        this.listaDetallePartida.remove(detParAux);

    }

    public List<TCuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(List<TCuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public IPartidaBo getPartidaBo() {
        return partidaBo;
    }

    public void setPartidaBo(IPartidaBo partidaBo) {
        this.partidaBo = partidaBo;
    }

    public IDetalleParidaBo getDetallePartidaBo() {
        return detallePartidaBo;
    }

    public void setDetallePartidaBo(IDetalleParidaBo detallePartidaBo) {
        this.detallePartidaBo = detallePartidaBo;
    }

    public IPeriodoBo getPeriodoBo() {
        return periodoBo;
    }

    public void setPeriodoBo(IPeriodoBo periodoBo) {
        this.periodoBo = periodoBo;
    }

    public IEjercicioBo getEjercicioBo() {
        return ejercicioBo;
    }

    public void setEjercicioBo(IEjercicioBo ejercicioBo) {
        this.ejercicioBo = ejercicioBo;
    }

    public ICuentaBo getCuentaBo() {
        return cuentaBo;
    }

    public void setCuentaBo(ICuentaBo cuentaBo) {
        this.cuentaBo = cuentaBo;
    }

    public List<TDetallePartida> getListaDetallePartida() {
        return listaDetallePartida;
    }

    public void setListaDetallePartida(List<TDetallePartida> listaDetallePartida) {
        this.listaDetallePartida = listaDetallePartida;
    }

    public TPeriodo getPerido() {
        return perido;
    }

    public void setPerido(TPeriodo perido) {
        this.perido = perido;
    }

    public TPartida getPartida() {
        return partida;
    }

    public void setPartida(TPartida partida) {
        this.partida = partida;
    }

    public TDetallePartida getDetallePartida() {
        return detallePartida;
    }

    public void setDetallePartida(TDetallePartida detallePartida) {
        this.detallePartida = detallePartida;
    }

    public TCuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(TCuenta cuenta) {
        this.cuenta = cuenta;
    }

    public BigDecimal getTotalDebe() {
        return totalDebe;
    }

    public void setTotalDebe(BigDecimal totalDebe) {
        this.totalDebe = totalDebe;
    }

    public BigDecimal getTotalHaber() {
        return totalHaber;
    }

    public void setTotalHaber(BigDecimal totalHaber) {
        this.totalHaber = totalHaber;
    }

    public Boolean getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public String getMsgConcepto() {
        return msgConcepto;
    }

    public void setMsgConcepto(String msgConcepto) {
        this.msgConcepto = msgConcepto;
    }

    public String getMsgNo() {
        return msgNo;
    }

    public void setMsgNo(String msgNo) {
        this.msgNo = msgNo;
    }

    public String getMsgCantidad() {
        return msgCantidad;
    }

    public void setMsgCantidad(String msgCantidad) {
        this.msgCantidad = msgCantidad;
    }

    public String getMsgConceptoDetalle() {
        return msgConceptoDetalle;
    }

    public void setMsgConceptoDetalle(String msgConceptoDetalle) {
        this.msgConceptoDetalle = msgConceptoDetalle;
    }

    public String getMsgDetallePar() {
        return msgDetallePar;
    }

    public void setMsgDetallePar(String msgDetallePar) {
        this.msgDetallePar = msgDetallePar;
    }

    public String getMsgCuadre() {
        return msgCuadre;
    }

    public void setMsgCuadre(String msgCuadre) {
        this.msgCuadre = msgCuadre;
    }

    public Integer getNumPartida() {
        return numPartida;
    }

    public void setNumPartida(Integer numPartida) {
        this.numPartida = numPartida;
    }

    public String getMsgCod() {
        return msgCod;
    }

    public void setMsgCod(String msgCod) {
        this.msgCod = msgCod;
    }

    public String getMsgArea() {
        return msgArea;
    }

    public void setMsgArea(String msgArea) {
        this.msgArea = msgArea;
    }

    public String getMsgEmpleado() {
        return msgEmpleado;
    }

    public void setMsgEmpleado(String msgEmpleado) {
        this.msgEmpleado = msgEmpleado;
    }

    public String getMsgTipo() {
        return msgTipo;
    }

    public void setMsgTipo(String msgTipo) {
        this.msgTipo = msgTipo;
    }

    public String getMsgDesc() {
        return msgDesc;
    }

    public void setMsgDesc(String msgDesc) {
        this.msgDesc = msgDesc;
    }

    public String getMsgValor() {
        return msgValor;
    }

    public void setMsgValor(String msgValor) {
        this.msgValor = msgValor;
    }

    public String getMsgProv() {
        return msgProv;
    }

    public void setMsgProv(String msgProv) {
        this.msgProv = msgProv;
    }

    public Boolean getEstadoValido() {
        return estadoValido;
    }

    public void setEstadoValido(Boolean estadoValido) {
        this.estadoValido = estadoValido;
    }

    public Integer getIdTipoActivo() {
        return idTipoActivo;
    }

    public TArea getArea() {
        return area;
    }

    public void setArea(TArea area) {
        this.area = area;
    }

    public TTipoActivo getTipoActivo() {
        return tipoActivo;
    }

    public void setTipoActivo(TTipoActivo tipoActivo) {
        this.tipoActivo = tipoActivo;
    }

    public String getCodTip() {
        return codTip;
    }

    public void setCodTip(String codTip) {
        this.codTip = codTip;
    }

    public IEntidadBo getEntidadBo() {
        return entidadBo;
    }

    public void setEntidadBo(IEntidadBo entidadBo) {
        this.entidadBo = entidadBo;
    }

    public TConfiguracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(TConfiguracion configuracion) {
        this.configuracion = configuracion;
    }

    public ConfiguracionBo getConfiguracionBo() {
        return configuracionBo;
    }

    public void setConfiguracionBo(ConfiguracionBo configuracionBo) {
        this.configuracionBo = configuracionBo;
    }

    public List<TProveedor> getListaProveedor() {

        return listaProveedor;
    }

    public void setListaProveedor(List<TProveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public ProveedorBo getProveedorBo() {
        return proveedorBo;
    }

    public void setProveedorBo(ProveedorBo proveedorBo) {
        this.proveedorBo = proveedorBo;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public TActivoFijo getActivoFijo() {
        return activoFijo;
    }

    public void setActivoFijo(TActivoFijo activoFijo) {
        this.activoFijo = activoFijo;
    }

    public List<TArea> getListaArea() {
        return listaArea;
    }

    public void setListaArea(List<TArea> listaArea) {
        this.listaArea = listaArea;
    }

    public List<TTipoActivo> getListaTipos() {
        return ListaTipos;
    }

    public void setListaTipos(List<TTipoActivo> ListaTipos) {
        this.ListaTipos = ListaTipos;
    }

    private List<TActivoFijo> listaActivoFijo;

    public List<TActivoFijo> getListaActivoFijo() {
        return listaActivoFijo;
    }

    public void setListaActivoFijo(List<TActivoFijo> listaActivoFijo) {
        this.listaActivoFijo = listaActivoFijo;
    }

    public IActivoFijoBo getActivoFijoBo() {
        return activoFijoBo;
    }

    public void setActivoFijoBo(IActivoFijoBo activoFijoBo) {
        this.activoFijoBo = activoFijoBo;
    }

    public TipoActivoBo getTipoActivoBo() {
        return tipoActivoBo;
    }

    public void setTipoActivoBo(TipoActivoBo tipoActivoBo) {
        this.tipoActivoBo = tipoActivoBo;
    }

    public AreaBo getAreaBo() {
        return areaBo;
    }

    public void setAreaBo(AreaBo areaBo) {
        this.areaBo = areaBo;
    }

    public void init() {

        this.listaDetallePartida = new ArrayList<>();
        this.limpiar();
        this.limpiarRegistro();
        this.enableShowDataBean();
        try {
            this.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void registro() {

        try {

            //Actualizo la fecha de registro
            this.activoFijoSeleccionado.setRegistroActivoFijo(this.partida.getFechaPartida());
            this.activoFijoBo.update(this.activoFijoSeleccionado);
            
            this.partida.setEstadoPartida(true);

            this.partida.setNumeroPartida(numPartida);
            this.partida.setTPeriodo(perido);
            this.partidaBo.create(this.partida);

            for (TDetallePartida tDetallePartida : listaDetallePartida) {
                tDetallePartida.setTPartida(partida);

                this.detallePartidaBo.create(tDetallePartida);
            }

            valorActivo.setTPartida(partida);
            this.valorActivoBo.update(valorActivo);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_partida, t_valor_activo");
            auxBitacora.setAccionBitacora("Agregar registro contable activo fijo");
            auxBitacora.setDatosBitacora("Numero:" + partida.getNumeroPartida()
                    + ", Concepto:" + partida.getConceptoPartida() + ", Entidad:" + entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(partida.getIdPartida());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            this.enableShowDataBean();
            super.enableShowData();
            this.estadoFormulario = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo fijo resgistrado correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El activo fijo no pudo ser resgistrado.", ""));
        }

    }

    public void registroActulizar() {

        try {
            
             
            
            this.partidaBo.update(this.partida);

            this.detallePartidaBo.delete(this.partida.getIdPartida());

            for (TDetallePartida tDetallePartida : listaDetallePartida) {
                tDetallePartida.setTPartida(this.partida);
                this.detallePartidaBo.create(tDetallePartida);

            }

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_partida, t_valor_activo");
            auxBitacora.setAccionBitacora("Modificar registro contable activo fijo");
            auxBitacora.setDatosBitacora("Numero:" + partida.getNumeroPartida()
                    + ", Concepto:" + partida.getConceptoPartida() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(partida.getIdPartida());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            this.enableShowDataBean();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo fijo modificado correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El activo fijo no pudo ser modificado.", ""));
        }

    }

    public void crear() {

        try {

            /**
             * Activo fijo
             */
            this.activoFijo.setEstadoActivoFijo("Activo");
            this.activoFijo.setTTipoActivo(this.tipoActivo);
            this.activoFijoBo.create(this.activoFijo);

            /**
             * Partida activo valor
             */
            this.valorActivo.setTActivoFijo(this.activoFijo);

            this.valorActivoBo.create(valorActivo);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_activo_fijo");
            auxBitacora.setAccionBitacora("Agregar activo fijo");
            auxBitacora.setDatosBitacora("Descripción:" + activoFijo.getDescripcionActivoFijo() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(activoFijo.getIdActivoFijo());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            this.limpiar();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo fijo registrado correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El activo fijo no pudo ser registrado.", ""));

        }

    }

    public void actualizar() {

        try {

            /**
             * Activo fijo
             */
            this.activoFijoSeleccionado.setTTipoActivo(this.tipoActivo);
            this.activoFijoBo.update(this.activoFijoSeleccionado);

            /**
             * Partida activo valor
             */
            this.valorActivoBo.update(valorActivo);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_activo_fijo");
            auxBitacora.setAccionBitacora("Modificar activo fijo");
            auxBitacora.setDatosBitacora("Descripción:" + activoFijoSeleccionado.getDescripcionActivoFijo() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(activoFijoSeleccionado.getIdActivoFijo());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            this.limpiar();
            this.enableShowDataBean();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo fijo modificado correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El activo fijo no pudo ser modificado.", ""));

        }

    }

    public void limpiar() {

        this.activoFijo = new TActivoFijo();

        this.activoFijo.setTProveedor(new TProveedor());
        this.activoFijo.setTEmpleado(new TEmpleado());
        this.valorActivo = new TValorActivo();
        this.valorActivo.setValorActivo(BigDecimal.ZERO);

        this.area = new TArea();
        this.tipoActivo = new TTipoActivo();
        this.idArea = 0;
        this.idTipoActivo = 0;


        this.activoFijo.setCompraActivoFijo(new Date());

        this.estadoValido = false;
        this.fechaActual = new Date();
    }

    public void limpiarRegistro() {

        partida = new TPartida();

        detallePartida = new TDetallePartida();
        detallePartida.setSaldoDetallePartida(BigDecimal.ZERO);
        detallePartida.setTipoSaldoDetallePartida("Debe");

        cuenta = new TCuenta();
        cuenta.setCodigoCuenta("");

        this.listaDetallePartida.clear();
        this.totalDebe = BigDecimal.ZERO;
        this.totalHaber = BigDecimal.ZERO;

        this.estadoFormulario = false;

        this.idTab = 0;
        this.tipoBaja = "";

    }

    public Boolean validarFormularioActivoModificar() {

        this.estadoValido = true;


        if (this.idArea == 0) {
            msgArea = "Área requerida";
            this.estadoValido = false;
        } else {
            msgArea = "";
        }

        if (this.activoFijoSeleccionado.getCodigoActivoFijo().length() == 0) {
            msgCod = "Código del activo requerido";
            this.estadoValido = false;
        } else {
            msgCod = "";
        }

        if (this.activoFijoSeleccionado.getTEmpleado().getIdEmpleado() == 0) {
            msgEmpleado = "Empleado requerido";
            this.estadoValido = false;
        } else {
            msgEmpleado = "";
        }

        if (this.idTipoActivo == 0) {
            msgTipo = "Tipo requerido";
            this.estadoValido = false;
        } else {
            msgTipo = "";
        }

        if (this.activoFijoSeleccionado.getDescripcionActivoFijo().length() <= 3) {
            msgDesc = "La descripción debe contener como mínimo 3 caracteres";
            this.estadoValido = false;
        } else {
            msgDesc = "";
        }

        if (this.valorActivo.equals(BigDecimal.ZERO)) {
            msgValor = "Valor del activo requerido";
            this.estadoValido = false;
        } else {
            msgValor = "";
        }

        if (this.activoFijoSeleccionado.getTProveedor().getIdProveedor() == 0) {
            msgProv = "Proveedor requerido";
            this.estadoValido = false;
        } else {
            msgProv = "";
        }

        return this.estadoValido;

    }

    public Boolean validarFormularioActivo() {

        this.estadoValido = true;


        if (this.idArea == 0) {
            msgArea = "Área requerida";
            this.estadoValido = false;
        } else {
            msgArea = "";
        }

        if (this.activoFijo.getCodigoActivoFijo().length() == 0) {
            msgCod = "Código del activo requerido";
            this.estadoValido = false;
        } else {
            msgCod = "";
        }

        if (this.activoFijo.getTEmpleado().getIdEmpleado() == 0) {
            msgEmpleado = "Empleado requerido";
            this.estadoValido = false;
        } else {
            msgEmpleado = "";
        }

        if (this.idTipoActivo == 0) {
            msgTipo = "Tipo requerido";
            this.estadoValido = false;
        } else {
            msgTipo = "";
        }

        if (this.activoFijo.getDescripcionActivoFijo().length() <= 3) {
            msgDesc = "La descripción debe contener como mínimo 3 caracteres";
            this.estadoValido = false;
        } else {
            msgDesc = "";
        }

        if (this.valorActivo.equals(BigDecimal.ZERO)) {
            msgValor = "Valor del activo requerido";
            this.estadoValido = false;
        } else {
            msgValor = "";
        }

        if (this.activoFijo.getTProveedor().getIdProveedor() == 0) {
            msgProv = "Proveedor requerido";
            this.estadoValido = false;
        } else {
            msgProv = "";
        }

        return this.estadoValido;

    }

    public void validarFormulario() {

        this.estadoFormulario = true;

        if (partida.getConceptoPartida().length() < 5) {
            this.msgConcepto = "El concepto debe contener como mínimo 5 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgConcepto = "";
        }

        if (this.listaDetallePartida.size() == 0) {
            this.estadoFormulario = false;
            this.msgDetallePar = "No hay transacciones";
        } else {
            this.msgDetallePar = "";
        }

        this.totalDebe = this.totalDebe.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.totalHaber = this.totalHaber.setScale(2, BigDecimal.ROUND_HALF_UP);

        if (!this.totalDebe.equals(this.totalHaber)) {
            this.estadoFormulario = false;
            this.msgCuadre = "La transacción no cuadra";
        } else {
            this.msgCuadre = "";

        }

    }

    public void validarFormularioModificar() {

        this.estadoFormulario = true;

        if (partida.getConceptoPartida().length() < 5) {
            this.msgConcepto = "El concepto debe contener como mínimo 5 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgConcepto = "";
        }

        if (this.listaDetallePartida.size() == 0) {
            this.estadoFormulario = false;
            this.msgDetallePar = "No hay transacciones";
        } else {
            this.msgDetallePar = "";
        }

        this.totalDebe = this.totalDebe.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.totalHaber = this.totalHaber.setScale(2, BigDecimal.ROUND_HALF_UP);

        if (!this.totalDebe.equals(this.totalHaber)) {
            this.estadoFormulario = false;
            this.msgCuadre = "La transacción no cuadra ";
        } else {
            this.msgCuadre = "";

        }

    }

    public void darDeAlta() {
        try {

            this.activoFijoSeleccionado.setEstadoActivoFijo("Activo");
            this.activoFijoBo.update(activoFijoSeleccionado);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_activo_fijo");
            auxBitacora.setAccionBitacora("Dar de alta activo fijo");
            auxBitacora.setDatosBitacora("Descripción:" + activoFijoSeleccionado.getDescripcionActivoFijo() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(activoFijoSeleccionado.getIdActivoFijo());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo fijo dado de alta correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El  activo fijo no pudo ser dado de alta.", ""));

        }

    }

    public void darDeBaja() {

        try {
            this.activoFijoSeleccionado.setEstadoActivoFijo("Inactivo");
            this.activoFijoBo.update(activoFijoSeleccionado);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_activo_fijo");
            auxBitacora.setAccionBitacora("Dar de baja activo fijo");
            auxBitacora.setDatosBitacora("Descripción:" + activoFijoSeleccionado.getDescripcionActivoFijo() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(activoFijoSeleccionado.getIdActivoFijo());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo fijo dado de baja correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El  activo fijo no pudo ser dado de baja.", ""));

        }
    }

    public final void siguienteBaja() {

        if (this.tipoBaja.equals("")) {

            this.msgTipoBaja = "Motivo requerido";

        } else {

            this.msgTipoBaja = "";

            if (this.tipoBaja.equals("Venta") && this.valorVenta.equals(BigDecimal.ZERO)) {
                this.msgValorActivoBaja = "Valor de venta invalido";
            } else {

                this.msgValorActivoBaja = "";

                if (this.tipoBaja.equals("Venta") && !this.valorVenta.equals(BigDecimal.ZERO)) {

                    this.prepararRegistroBAja();

                    TCuenta cueDep = this.cuentaBo.getCuenta(tipoActivo.getTCuentaByIdCuentaVentaTipo().getIdCuenta());

                    TDetallePartida detVenta = new TDetallePartida();

                    detVenta.setTCuenta(cueDep);

                    detVenta.setTipoSaldoDetallePartida("Haber");

                    detVenta.setSaldoDetallePartida(this.valorVenta.subtract(this.valorLibro));

                    this.totalHaber = totalHaber.add(this.valorVenta.subtract(this.valorLibro));

                    this.listaDetallePartida.add(detVenta);
                } else {

                    this.prepararRegistroBAja();

                }

                this.idTab = 1;
            }
        }

    }

    public void darDeBajaContable() {
        try {

            this.activoFijoSeleccionado.setEstadoActivoFijo("Inactivo");

            this.activoFijoBo.update(activoFijoSeleccionado);

            this.partida.setEstadoPartida(true);

            this.partida.setNumeroPartida(numPartida);

            this.partida.setTPeriodo(perido);

            this.partidaBo.create(this.partida);

            for (TDetallePartida tDetallePartida : listaDetallePartida) {
                tDetallePartida.setTPartida(partida);

                this.detallePartidaBo.create(tDetallePartida);
            }

            TBajaActivoFijo bajaActivo = new TBajaActivoFijo();

            bajaActivo.setTActivoFijo(activoFijoSeleccionado);

            bajaActivo.setTPartida(partida);

            bajaActivo.setTipoBajaActivoFijo(this.tipoBaja);

            bajaActivo.setValorBajaActivoFijo(valorActivoBaja);

            this.bajaActivoFijoBo.create(bajaActivo);

            this.valorActivoBo.update(valorActivo);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_activo_fijo, t_partida, t_baja_activo_fijo, t_valor_activo");
            auxBitacora.setAccionBitacora("Baja contable  de activo fijo");
            auxBitacora.setDatosBitacora("Descripción:" + activoFijoSeleccionado.getDescripcionActivoFijo() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(activoFijoSeleccionado.getIdActivoFijo());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            this.enableShowDataBean();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo fijo dado de baja correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El activo fijo no pudo ser dado de baja.", ""));

        }

    }

    public void generarReporteActivo() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idActivoFijo", this.activoFijoSeleccionado.getIdActivoFijo());
        estadoUsuario.put("idEntidad", this.entidadSeleccionada.getIdEntidad());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/reporteActivoFijoIndividual.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), estadoUsuario, this.getConn());

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_activo_fijo");
        auxBitacora.setAccionBitacora("Generar reporte de activo fijo");
        auxBitacora.setDatosBitacora("Descripción:" + activoFijoSeleccionado.getDescripcionActivoFijo() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
        auxBitacora.setIdTableBitacora(activoFijoSeleccionado.getIdActivoFijo());
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

    public void generarReporteActivoPDF() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idActivoFijo", this.activoFijoSeleccionado.getIdActivoFijo());
        estadoUsuario.put("idEntidad", this.entidadSeleccionada.getIdEntidad());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/reporteActivoFijoIndividual.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), estadoUsuario, this.getConn());

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_activo_fijo");
        auxBitacora.setAccionBitacora("Descargar reporte de activo fijo");
        auxBitacora.setDatosBitacora("Descripción:" + activoFijoSeleccionado.getDescripcionActivoFijo() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
        auxBitacora.setIdTableBitacora(activoFijoSeleccionado.getIdActivoFijo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Activo-" + activoFijoSeleccionado.getDescripcionActivoFijo() + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void generarReporteActivoDepre() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idActivoFijo", this.activoFijoSeleccionado.getIdActivoFijo());
        estadoUsuario.put("idEntidad", this.entidadSeleccionada.getIdEntidad());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/reporteActivoFijoDepreAmort.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), estadoUsuario, this.getConn());

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_activo_fijo");
        auxBitacora.setAccionBitacora("Generar reporte de depreciación/amortizacion  activo fijo");
        auxBitacora.setDatosBitacora("Descripción:" + activoFijoSeleccionado.getDescripcionActivoFijo() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
        auxBitacora.setIdTableBitacora(activoFijoSeleccionado.getIdActivoFijo());
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

    public void generarReporteActivoDeprePDF() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idActivoFijo", this.activoFijoSeleccionado.getIdActivoFijo());
        estadoUsuario.put("idEntidad", this.entidadSeleccionada.getNombreEntidad());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/reporteActivoFijoDepreAmort.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), estadoUsuario, this.getConn());

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_activo_fijo");
        auxBitacora.setAccionBitacora("Descargar reporte de depreciación/amortizacion  activo fijo");
        auxBitacora.setDatosBitacora("Descripción:" + activoFijoSeleccionado.getDescripcionActivoFijo() + ", Entidad:" + this.entidadSeleccionada.getNombreEntidad());
        auxBitacora.setIdTableBitacora(activoFijoSeleccionado.getIdActivoFijo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Activo-Dpre-Amortiz" + activoFijoSeleccionado.getDescripcionActivoFijo() + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
}
