/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.ICuentaBo;
import com.sigaf.Ibo.IDetalleParidaBo;
import com.sigaf.Ibo.IEjercicioBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Ibo.IPartidaBo;
import com.sigaf.Ibo.IPeriodoBo;
import com.sigaf.pojo.TBitacora;

import com.sigaf.pojo.TCuenta;
import com.sigaf.pojo.TDetallePartida;
import com.sigaf.pojo.TEjercicio;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TPartida;
import com.sigaf.pojo.TPeriodo;
import java.io.File;
import java.math.BigDecimal;
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

public class PartidaBean extends Actividad {

    /**
     * Creates a new instance of PartidaBean
     */
    private IBitacoraBo bitacoraBo;

    private Boolean estadoPredeterminado;

    private List<TCuenta> listaCuentas;

    private List<TEntidad> listaEntidades;

    private IEntidadBo entidadBo;

    private Integer idEntidad;

    private TPeriodo periodo;

    private Integer idPeriodo;

    private IPartidaBo partidaBo;

    private IDetalleParidaBo detallePartidaBo;

    private IPeriodoBo periodoBo;

    private IEjercicioBo ejercicioBo;

    private List<TEjercicio> listaEjercicios;

    private Integer idEjer;

    private ICuentaBo cuentaBo;

    private List<TDetallePartida> listaDetallePartida;

    private List<TPartida> listaPartida;

    private List<TPeriodo> listaPeriodos;

    private TPartida partida;

    private TPartida partidaSeleccionada;

    private TDetallePartida detallePartida;

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

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    /**
     * Metodo que actuliza la lista de entidades consultado a la Base de Datos
     */
    public void updateListaEntidades() {
        this.listaEntidades = this.entidadBo.listTEndidadTodos();
    }

    /**
     * Metodo que actuliza la lista de Partidas consultado a la Base de Datos
     */
    public void updateListaPartida() {
        this.listaPartida = this.partidaBo.listPartida(idPeriodo);

    }

    /**
     * Metodo que actuliza la lista de Ejercicios consultado a la Base de Datos
     */
    public void updateListaEjercicios() {
        this.listaEjercicios = this.ejercicioBo.listEjercicio(idEntidad);
    }

    /**
     * Metodo que actuliza la lista de Periodos consultado a la Base de Datos
     */
    public void updateListaPeriodos() {
        this.listaPeriodos = this.periodoBo.listPeriodo(idEjer);
    }

    /**
     * Metodo que actuliza la lista de Periodos consultado a la Base de Datos
     */
    public void updateListaCuentas() {
        this.listaCuentas = this.cuentaBo.listCuentaEntActTip(idEntidad, true);
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
            this.msgCantidad = "Cantidad invalida";
        } else {
            this.msgCantidad = "";
        }

        if (cuenta.getCodigoCuenta().length() == 0) {
            estado = false;
            this.msgNo = "Cuenta requerida";
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

    public void cambiarPredeterminado() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        loginBean.setIdEntidad(this.idEntidad);
        loginBean.setPredeterminado(estadoPredeterminado);

    }

    public Boolean getEstadoPredeterminado() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
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

    public TPeriodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(TPeriodo periodo) {

        this.periodo = periodo;
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

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {

        if (!estadoPredeterminado) {
            this.idEjer = 0;
            this.idPeriodo = 0;
        }

        this.idEntidad = idEntidad;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        /* se saca el periodo abierto cuando se selecciona el que se 
        * desea ver, para decidir si se muestra el boton Agregar Partida
         */
        this.periodo = this.periodoBo.getPeriodoAbierto(idEjer);
        this.idPeriodo = idPeriodo;
    }

    public void setListaPartida(List<TPartida> listaPartida) {
        this.listaPartida = listaPartida;
    }

    public Integer getNumPartida() {
        numPartida = this.partidaBo.numeroPartida(idEjer);
        return numPartida;

    }

    public void setNumPartida(Integer numPartida) {
        this.numPartida = numPartida;
    }

    public IEjercicioBo getEjercicioBo() {
        return ejercicioBo;
    }

    public void setEjercicioBo(IEjercicioBo ejercicioBo) {
        this.ejercicioBo = ejercicioBo;
    }

    public List<TEjercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    public void setListaEjercicios(List<TEjercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    public Integer getIdEjer() {

        return idEjer;
    }

    public void setIdEjer(Integer idEjer) {
        this.idPeriodo = 0;

        this.idEjer = idEjer;
    }

    public String getMsgCuadre() {
        return msgCuadre;
    }

    public void setMsgCuadre(String msgCuadre) {
        this.msgCuadre = msgCuadre;
    }

    public String getMsgDetallePar() {
        return msgDetallePar;
    }

    public void setMsgDetallePar(String msgDetallePar) {
        this.msgDetallePar = msgDetallePar;
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

    public BigDecimal getTotalDebe() {
        return totalDebe;
    }

    public void setTotalDebe(BigDecimal totalDebe) {
        this.totalDebe = totalDebe;
    }

    public BigDecimal getTotalHaber() {
        return totalHaber;
    }

    public List<TPartida> getListaPartida() {
        return listaPartida;
    }

    public void setTotalHaber(BigDecimal totalHaber) {
        this.totalHaber = totalHaber;
    }

    public List<TDetallePartida> getListaDetallePartida() {
        return listaDetallePartida;
    }

    public void setListaDetallePartida(List<TDetallePartida> listaDetallePartida) {
        this.listaDetallePartida = listaDetallePartida;
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

    public TDetallePartida getDetallePartida() {
        return detallePartida;
    }

    public void setDetallePartida(TDetallePartida detallePartida) {
        this.detallePartida = detallePartida;
    }

    public Boolean getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public TPartida getPartidaSeleccionada() {
        return partidaSeleccionada;
    }

    public void setPartidaSeleccionada(TPartida partidaSeleccionada) {

        this.totalDebe = BigDecimal.ZERO;
        this.totalHaber = BigDecimal.ZERO;
        this.listaDetallePartida = detallePartidaBo.listDetallePartida(partidaSeleccionada.getIdPartida());

        for (TDetallePartida tDetallePartida : listaDetallePartida) {

            if (tDetallePartida.getTipoSaldoDetallePartida().equals("Debe")) {
                this.totalDebe = this.totalDebe.add(tDetallePartida.getSaldoDetallePartida());
            } else {
                this.totalHaber = this.totalHaber.add(tDetallePartida.getSaldoDetallePartida());
            }
        }

        this.partidaSeleccionada = partidaSeleccionada;

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_partida");
        auxBitacora.setAccionBitacora("Ver información de partida");
        auxBitacora.setDatosBitacora("Numero:" + partidaSeleccionada.getNumeroPartida()
                + ", Concepto:" + partidaSeleccionada.getConceptoPartida() + ", Entidad:" + this.idEntidad);
        auxBitacora.setIdTableBitacora(partidaSeleccionada.getIdPartida());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

    }

    public TPartida getPartida() {
        return partida;
    }

    public void setPartida(TPartida partida) {
        this.partida = partida;
    }

    public List<TPeriodo> getListaPeriodos() {
        return listaPeriodos;
    }

    public void setListaPeriodos(List<TPeriodo> listaPeriodos) {
        this.listaPeriodos = listaPeriodos;
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

    public void init() {
        super.enableShowData();
        listaDetallePartida = new ArrayList<>();
        this.idEjer = 0;
        this.idEntidad = 0;
        this.idPeriodo = 0;
        this.limpiar();
        try {
            this.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void crear() {
        try {
            this.partida.setEstadoPartida(true);
            this.partida.setNumeroPartida(numPartida);
            this.partida.setTPeriodo(new TPeriodo(idPeriodo));
            this.partidaBo.create(this.partida);

            for (TDetallePartida tDetallePartida : listaDetallePartida) {
                tDetallePartida.setTPartida(partida);

                this.detallePartidaBo.create(tDetallePartida);
            }

            

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_partida");
            auxBitacora.setAccionBitacora("Agregar partida");
            auxBitacora.setDatosBitacora("Numero:" + partida.getNumeroPartida()
                    + ", Concepto:" + partida.getConceptoPartida() + ", Entidad:" + this.idEntidad);
            auxBitacora.setIdTableBitacora(partida.getIdPartida());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            this.limpiar();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Partida registrada correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La partida no pudo ser registrado.", ""));
        }

    }

    public void actualizar() {

        try {

            this.partidaBo.update(this.partidaSeleccionada);

            this.detallePartidaBo.delete(this.partidaSeleccionada.getIdPartida());

            for (TDetallePartida tDetallePartida : listaDetallePartida) {
                tDetallePartida.setTPartida(this.partidaSeleccionada);
                this.detallePartidaBo.create(tDetallePartida);

            }

            

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_partida");
            auxBitacora.setAccionBitacora("Modificar partida");
            auxBitacora.setDatosBitacora("Numero:" + partidaSeleccionada.getNumeroPartida()
                    + ", Concepto:" + partidaSeleccionada.getConceptoPartida() + ", Entidad:" + this.idEntidad);
            auxBitacora.setIdTableBitacora(partidaSeleccionada.getIdPartida() );
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            super.enableShowData();
            this.estadoFormulario = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Partida modificada correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La partida no pudo ser modificada.", ""));
        }

    }

    public void limpiar() {
        this.estadoFormulario = false;

        partida = new TPartida();
        partida.setFechaPartida(new Date());

        detallePartida = new TDetallePartida();
        detallePartida.setSaldoDetallePartida(BigDecimal.ZERO);
        detallePartida.setTipoSaldoDetallePartida("Debe");

        cuenta = new TCuenta();
        cuenta.setCodigoCuenta("");

        this.listaDetallePartida.clear();
        this.totalDebe = BigDecimal.ZERO;
        this.totalHaber = BigDecimal.ZERO;
        this.msgCantidad = "";
        this.msgConcepto = "";
        this.msgConceptoDetalle = "";
        this.msgCuadre = "";
        this.msgDetallePar = "";
        this.msgNo = "";

    }

    public void enableShowDataBean() {
        super.enableShowData();
        this.limpiar();

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

        this.totalDebe=this.totalDebe.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.totalHaber=this.totalHaber.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        if (!this.totalDebe.equals(this.totalHaber)) {
            this.estadoFormulario = false;
            this.msgCuadre = "La transacción no cuadra";
        } else {
            this.msgCuadre = "";

        }

    }

    public void validarFormularioModificar() {

        this.estadoFormulario = true;

        if (partidaSeleccionada.getConceptoPartida().length() < 5) {
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

        this.totalDebe=this.totalDebe.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.totalHaber=this.totalHaber.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        if (!this.totalDebe.equals(this.totalHaber)) {
            this.estadoFormulario = false;
            this.msgCuadre = "La transacción no cuadra";
        } else {
            this.msgCuadre = "";

        }

    }

    public void eliminar() {

        try {

            this.detallePartidaBo.delete(this.partidaSeleccionada.getIdPartida());

            this.partidaBo.delete(this.partidaSeleccionada, idEjer);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_partida");
            auxBitacora.setAccionBitacora("Eliminar partida");
            auxBitacora.setDatosBitacora("Numero:" + partidaSeleccionada.getNumeroPartida()
                    + ", Concepto:" + partidaSeleccionada.getConceptoPartida() + ", Entidad:" + this.idEntidad);
            auxBitacora.setIdTableBitacora(partidaSeleccionada.getIdPartida());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Partida eliminada correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La partida no pudo ser eliminada.", ""));
        }
    }

    public void enableShowCreateBean() {
        super.enableShowCreate();
        limpiar();
    }



    public void generarReportePartida() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idPartida", this.partidaSeleccionada.getIdPartida());
        estadoUsuario.put("idEntidad", this.idEntidad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/LibroDiarioIndividual.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), estadoUsuario, this.getConn());

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_partida");
        auxBitacora.setAccionBitacora("Generar reporte de partida");
        auxBitacora.setDatosBitacora("Numero:" + partidaSeleccionada.getNumeroPartida()
                + ", Concepto:" + partidaSeleccionada.getConceptoPartida() + ", Entidad:" + this.idEntidad);
        auxBitacora.setIdTableBitacora(partidaSeleccionada.getIdPartida());
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

    public void generarReportePartidaPDF() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idPartida", this.partidaSeleccionada.getIdPartida());
        estadoUsuario.put("idEntidad", this.idEntidad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/LibroDiarioIndividual.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), estadoUsuario, this.getConn());
        
        TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_partida");
            auxBitacora.setAccionBitacora("Descargar reporte de partida");
            auxBitacora.setDatosBitacora("Numero:" + partidaSeleccionada.getNumeroPartida()
                    + ", Concepto:" + partidaSeleccionada.getConceptoPartida() + ", Entidad:" + this.idEntidad);
            auxBitacora.setIdTableBitacora(partidaSeleccionada.getIdPartida());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);
            
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Partida-" + partidaSeleccionada.getNumeroPartida() + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
}
