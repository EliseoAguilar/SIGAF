package com.sigaf.bean;

import com.sigaf.Ibo.IActivoBo;
import com.sigaf.Ibo.IAgropecuarioBo;
import com.sigaf.Ibo.IAreaBo;
import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IClienteBo;
import com.sigaf.Ibo.IClienteProyectoBo;
import com.sigaf.Ibo.IComentarioBo;
import com.sigaf.Ibo.IComercioBo;
import com.sigaf.Ibo.ICooperativaBo;
import com.sigaf.Ibo.IEgresoBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Ibo.IEntidadProyectoBo;
import com.sigaf.Ibo.IGarantiaBo;
import com.sigaf.Ibo.IGarantiaProBo;
import com.sigaf.Ibo.IIngresoBo;
import com.sigaf.Ibo.ILisiadoBo;
import com.sigaf.Ibo.IPoliticaBo;
import com.sigaf.Ibo.IPropiedadLisiadoBo;
import com.sigaf.Ibo.IProyectoBo;
import com.sigaf.Ibo.IReferenciaBo;
import com.sigaf.Ibo.ISugerenciaBo;
import com.sigaf.Ibo.ITrabajoBo;
import com.sigaf.pojo.TActivo;
import com.sigaf.pojo.TAgropecuario;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TCliente;
import com.sigaf.pojo.TClienteProyecto;
import com.sigaf.pojo.TComentario;
import com.sigaf.pojo.TComercio;
import com.sigaf.pojo.TCooperativa;
import com.sigaf.pojo.TEgreso;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TEntidadProyecto;
import com.sigaf.pojo.TGarantia;
import com.sigaf.pojo.TIngreso;
import com.sigaf.pojo.TLisiado;
import com.sigaf.pojo.TPago;
import com.sigaf.pojo.TPolitica;
import com.sigaf.pojo.TPropiedadLisiado;
import com.sigaf.pojo.TProyecto;
import com.sigaf.pojo.TReferencia;
import com.sigaf.pojo.TSugerencia;
import com.sigaf.pojo.TTipoCredito;
import com.sigaf.pojo.TTrabajo;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Genoves
 */
@Named(value = "resolucionBean")
@SessionScoped
@ManagedBean

public class ResolucionBean extends Actividad {

    ILisiadoBo ilisiadoBo;
    IGarantiaProBo igarantiaProBo;
    IPropiedadLisiadoBo ipropiedadLisiadoBo;
    private List<TClienteProyecto> listaCliente;
    private List<TPropiedadLisiado> listaPropiedadLisiado;
    private List<TComentario> listaComentarios;
    private List<TSugerencia> listaSugerencia;
    private List<TCliente> listaClientes;
    private TClienteProyecto clienteProyectoEliminar;
    private Boolean banderaEgreso;
    private Integer idDepartamento;
    private TLisiado lisiados;
    private TCliente cliente;
    private List<TProyecto> listaAprobado;
    private TComentario comentario;
    private TSugerencia sugerencia;
    private TIngreso ingresos;
    private TEgreso egresos;
    private TGarantia garantias;
    private TReferencia referencias;
    private TPropiedadLisiado propiedadLisiado;
    private Integer idGarantia;
    private Integer idMunicipio;
    private String posesiones;
    private List<String> listaPosesiones;
    private List<TEgreso> listaEgresos;
    private Boolean mostrarEmpleados;
    private boolean showImagen;
    private boolean showImagenCarta;
    private boolean ocultar;
    private boolean ocultarEgresos;
    private Boolean aprobar;
    private List<TClienteProyecto> listaClienteProyectoLisiados;
    private List<TClienteProyecto> listaClienteProyectoComercio;
    private List<TClienteProyecto> listaClienteProyectoAgropecuario;
    private List<TEntidadProyecto> listaClienteProyectoCooperativa;
    private List<TProyecto> listaSolicitudesProyectos;
    private boolean estadoHipotecaria;
    private boolean estadoPrendaria;
    private boolean estadoSolidaria;
    private String msgNombre;
    private String msgNombreCliente;
    private String msgApellidoCliente;
    private String msgDuiCliente;
    private String msgNitCliente;
    private String msgTelefonoCliente;
    private String msgCelularCliente;
    private TEntidad Entidad;
    private TCooperativa cooperativa;
    private TCooperativa cooperativaSeleccionada;
    private TPago pago;
    private TCliente clienteSeleccionado;
    private TIngreso ingresoSeleccionado;
    private TEgreso egresoSeleccionado;
    private Integer tipoCliente;
    private Boolean mostrarLisiados;
    private TEntidadProyecto entidadProyecto;
    private TEntidad Entidadeleccionada;
    private TProyecto proyecto;
    private TProyecto proyectoSeleccionado;
    private TPolitica politica;
    private TPolitica politicaSeleccionada;
    private Boolean calculos;
    private Boolean mostrarPersonas;
    private Boolean mostrarCooperativas;
    private Boolean mostrarAgropecuarios;
    private Boolean mostrarComercio;
    private String formaPagos;
    private BigDecimal monto;
    private BigDecimal intereses;
    private BigDecimal total;
    private BigDecimal couto;
    private BigDecimal totalIngreso;
    private BigDecimal totalEgreso;
    private BigDecimal totalActivo;
    private BigDecimal totalGarantia;
    private boolean mostrarTipoCreditoCooper;
    private boolean mostrarTipoCreditoPerso;
    private IEntidadBo ientidadBo;
    private IEntidadProyectoBo ientidadProyectoBo;
    private ICooperativaBo icooperativaBo;
    private IProyectoBo iproyectoBo;
    private IPoliticaBo ipoliticaBo;
    private IClienteBo iclienteBo;
    private IIngresoBo iingresoBo;
    private IEgresoBo iegresoBo;
    private IGarantiaBo igarantiaBo;
    private IReferenciaBo ireferenciaBo;
    private IAgropecuarioBo iagropecuarioBo;
    private IActivoBo iactivoBo;
    private IClienteProyectoBo iclienteProyectoBo;
    private IComentarioBo icomentarioBo;
    private ISugerenciaBo isugerenciaBo;
    private IComercioBo icomercioBo;
    private TComercio comercio;
    private ITrabajoBo itrabajoBo;
    private TTrabajo trabajo;
    private List<TClienteProyecto> listaClienteProyectoFiltrada;
    private List<TEntidad> listaEntidades;
    private List<TEntidadProyecto> listaEntidadProyectos;
    private List<TEntidadProyecto> listaEntidadProyectosReducida;
    private List<TEntidadProyecto> listaEntidadProyectosFiltrada;
    private List<TEntidadProyecto> listaComprobacioCliente;
    private List<TEntidadProyecto> listaEntidadClienteProyectos;
    private List<TEntidadProyecto> listaEntidadClienteProyectosFiltrada;
    private BigDecimal valorRedondeado;
    private BigDecimal valorRedondeadoIngreso;
    private BigDecimal valorRedondeadoEgreso;
    private BigDecimal valorRedondeadoActivo;
    private BigDecimal valorRedondeadoGarantia;
    private List<TClienteProyecto> listaClienteProyectos;
    private List<TIngreso> listaIngresoSeleccionada;
    private List<TEgreso> listaEgresoSeleccionada;
    private List<TGarantia> listaGarantiaSeleccionada;
    private List<TActivo> listaActivoSeleccionada;
    private List<TClienteProyecto> listaClienteProyectoReducida;
    private List<TClienteProyecto> listaClienteProyecto;
    private List<TPago> listaPagos;
    private Boolean showPresupuesto;
    private TGarantia garantiaSeleccionada;
    private Boolean showBalance;
    private TReferencia referencia;
    private TReferencia referenciaSeleccionada;
    private TActivo activo;
    private List<TActivo> listaActivos;
    private List<TIngreso> listaIngreso;
    private TIngreso ingreso;
    private List<TEgreso> listaEgreso;
    private TEgreso egreso;
    private List<TGarantia> listaGarantia;
    private TGarantia garantia;
    private TAgropecuario agropecuario;
    private TClienteProyecto clienteProyecto;
    private List<TReferencia> listaReferencia;
    private List<TEntidad> listaEntidadesClientes;
    private Boolean showAprobacion;
    private String msgNumero;
    private String msgLugar;
    private String msgFecha;
    private String msgCodigoEntidad;
    private String msgNombreProyecto;
    private String msgDescripcion;
    private String msgDestino;
    private String msgArea;
    private String msgCredito;
    private String msgActivoC;
    private String msgActivoN;
    private String msgPasivoC;
    private String msgPasivoN;
    private String msgPatrimonio;
    private String msgMonto;
    private String msgPlazo;
     private Date hoy;
    private String codigoSolicitud;
    private Boolean mostrarGarantiaPrendaria;
    private Boolean mostrarGarantiaSolidaria;
    private Boolean mostrarGarantiaHipotecaria;
    private BigDecimal totalIngresoRazon;
    private BigDecimal totalEgresoRazon;
    private BigDecimal resultadoPagoRazon;
    private BigDecimal ResultadoRazonCapacidadPago;
    private Boolean mensajeNoAprobado;
    private UploadedFile img;
    private Boolean mensajeAprobado;
    private Boolean mensajeNoAprobadoPago;
    private Boolean mensajeAprobadoPago;
    private BigDecimal totalRazonActivos;
    private BigDecimal totalRazonPasivos;
    private BigDecimal resultadoRazonEndeudamiento;
    private String modalidad;
    private BigDecimal totalBalanceActivo;
    private BigDecimal totalBalancePasivo;
    private BigDecimal TotalBalancePatrimonio;
    private Boolean mostrarResumen;
    private IAreaBo iareaBo;
    private Boolean estadoFormulario;
    private Integer tipoCredito;
    private Integer tipoCred;
    private Boolean mostrarTabla;
    private Integer edad;
    private List<SelectItem> selectOneItemEntidades;
    private String msgDescripcionComertario;
    private String msgDescripcionSugerencia;
    private IBitacoraBo bitacoraBo;

    public Date getHoy() {
        return new Date();
    }

    public void setHoy(Date hoy) {
        this.hoy = hoy;
    }

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public String getMsgDescripcionComertario() {
        return msgDescripcionComertario;
    }

    public void setMsgDescripcionComertario(String msgDescripcionComertario) {
        this.msgDescripcionComertario = msgDescripcionComertario;
    }

    public String getMsgDescripcionSugerencia() {
        return msgDescripcionSugerencia;
    }

    public void setMsgDescripcionSugerencia(String msgDescripcionSugerencia) {
        this.msgDescripcionSugerencia = msgDescripcionSugerencia;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public TClienteProyecto getClienteProyectoEliminar() {
        return clienteProyectoEliminar;
    }

    public void setClienteProyectoEliminar(TClienteProyecto clienteProyectoEliminar) {
        this.clienteProyectoEliminar = clienteProyectoEliminar;
    }

    public List<TProyecto> getListaAprobado() {
        return listaAprobado;
    }

    public void setListaAprobado(List<TProyecto> listaAprobado) {
        this.listaAprobado = listaAprobado;
    }

    public List<TCliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<TCliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<TComentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<TComentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public List<TSugerencia> getListaSugerencia() {
        return listaSugerencia;
    }

    public void setListaSugerencia(List<TSugerencia> listaSugerencia) {
        this.listaSugerencia = listaSugerencia;
    }

    public TComentario getComentario() {
        return comentario;
    }

    public void setComentario(TComentario comentario) {
        this.comentario = comentario;
    }

    public TSugerencia getSugerencia() {
        return sugerencia;
    }

    public void setSugerencia(TSugerencia sugerencia) {
        this.sugerencia = sugerencia;
    }

    public Boolean getMostrarEmpleados() {
        return mostrarEmpleados;
    }

    public void setMostrarEmpleados(Boolean mostrarEmpleados) {
        this.mostrarEmpleados = mostrarEmpleados;
    }

    public List<TProyecto> getListaSolicitudesProyectos() {
        return listaSolicitudesProyectos;
    }

    public void setListaSolicitudesProyectos(List<TProyecto> listaSolicitudesProyectos) {
        this.listaSolicitudesProyectos = listaSolicitudesProyectos;
    }

    public List<TClienteProyecto> getListaClienteProyectoLisiados() {
        return listaClienteProyectoLisiados;
    }

    public void setListaClienteProyectoLisiados(List<TClienteProyecto> listaClienteProyectoLisiados) {
        this.listaClienteProyectoLisiados = listaClienteProyectoLisiados;
    }

    public List<TClienteProyecto> getListaClienteProyectoComercio() {
        return listaClienteProyectoComercio;
    }

    public void setListaClienteProyectoComercio(List<TClienteProyecto> listaClienteProyectoComercio) {
        this.listaClienteProyectoComercio = listaClienteProyectoComercio;
    }

    public List<TClienteProyecto> getListaClienteProyectoAgropecuario() {
        return listaClienteProyectoAgropecuario;
    }

    public void setListaClienteProyectoAgropecuario(List<TClienteProyecto> listaClienteProyectoAgropecuario) {
        this.listaClienteProyectoAgropecuario = listaClienteProyectoAgropecuario;
    }

    public List<TEntidadProyecto> getListaClienteProyectoCooperativa() {
        return listaClienteProyectoCooperativa;
    }

    public void setListaClienteProyectoCooperativa(List<TEntidadProyecto> listaClienteProyectoCooperativa) {
        this.listaClienteProyectoCooperativa = listaClienteProyectoCooperativa;
    }

    public Boolean getAprobar() {
        return aprobar;
    }

    public void setAprobar(Boolean aprobar) {
        this.aprobar = aprobar;
    }

    public Boolean getMostrarComercio() {
        return mostrarComercio;
    }

    public void setMostrarComercio(Boolean mostrarComercio) {
        this.mostrarComercio = mostrarComercio;
    }

    public ITrabajoBo getItrabajoBo() {
        return itrabajoBo;
    }

    public void setItrabajoBo(ITrabajoBo itrabajoBo) {
        this.itrabajoBo = itrabajoBo;
    }

    public TTrabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(TTrabajo trabajo) {
        this.trabajo = trabajo;
    }

    public IComercioBo getIcomercioBo() {
        return icomercioBo;
    }

    public void setIcomercioBo(IComercioBo icomercioBo) {
        this.icomercioBo = icomercioBo;
    }

    public TComercio getComercio() {
        return comercio;
    }

    public void setComercio(TComercio comercio) {
        this.comercio = comercio;
    }

    public IComentarioBo getIcomentarioBo() {
        return icomentarioBo;
    }

    public void setIcomentarioBo(IComentarioBo icomentarioBo) {
        this.icomentarioBo = icomentarioBo;
    }

    public ISugerenciaBo getIsugerenciaBo() {
        return isugerenciaBo;
    }

    public void setIsugerenciaBo(ISugerenciaBo isugerenciaBo) {
        this.isugerenciaBo = isugerenciaBo;
    }

    public List<TClienteProyecto> getListaClienteProyecto() {
        return listaClienteProyecto;
    }

    public void setListaClienteProyecto(List<TClienteProyecto> listaClienteProyecto) {
        this.listaClienteProyecto = listaClienteProyecto;
    }

    public List<TEntidadProyecto> getListaEntidadClienteProyectos() {
        return listaEntidadClienteProyectos;
    }

    public void setListaEntidadClienteProyectos(List<TEntidadProyecto> listaEntidadClienteProyectos) {
        this.listaEntidadClienteProyectos = listaEntidadClienteProyectos;
    }

    public List<TEntidadProyecto> getListaEntidadClienteProyectosFiltrada() {
        return listaEntidadClienteProyectosFiltrada;
    }

    public void setListaEntidadClienteProyectosFiltrada(List<TEntidadProyecto> listaEntidadClienteProyectosFiltrada) {
        this.listaEntidadClienteProyectosFiltrada = listaEntidadClienteProyectosFiltrada;
    }

    public List<TEntidadProyecto> getListaComprobacioCliente() {
        return listaComprobacioCliente;
    }

    public void setListaComprobacioCliente(List<TEntidadProyecto> listaComprobacioCliente) {
        this.listaComprobacioCliente = listaComprobacioCliente;
    }

    public List<TEntidad> getListaEntidadesClientes() {
        return listaEntidadesClientes;
    }

    public void setListaEntidadesClientes(List<TEntidad> listaEntidadesClientes) {
        this.listaEntidadesClientes = listaEntidadesClientes;
    }

    public TGarantia getGarantiaSeleccionada() {
        return garantiaSeleccionada;
    }

    public void setGarantiaSeleccionada(TGarantia garantiaSeleccionada) {
        this.garantiaSeleccionada = garantiaSeleccionada;
    }

    public Boolean getMensajeNoAprobadoPago() {
        return mensajeNoAprobadoPago;
    }

    public void setMensajeNoAprobadoPago(Boolean mensajeNoAprobadoPago) {
        this.mensajeNoAprobadoPago = mensajeNoAprobadoPago;
    }

    public Boolean getMensajeAprobadoPago() {
        return mensajeAprobadoPago;
    }

    public void setMensajeAprobadoPago(Boolean mensajeAprobadoPago) {
        this.mensajeAprobadoPago = mensajeAprobadoPago;
    }

    public Boolean getMensajeNoAprobado() {
        return mensajeNoAprobado;
    }

    public void setMensajeNoAprobado(Boolean mensajeNoAprobado) {
        this.mensajeNoAprobado = mensajeNoAprobado;
    }

    public Boolean getMensajeAprobado() {
        return mensajeAprobado;
    }

    public void setMensajeAprobado(Boolean mensajeAprobado) {
        this.mensajeAprobado = mensajeAprobado;
    }

    public Boolean getMostrarResumen() {
        return mostrarResumen;
    }

    public void setMostrarResumen(Boolean mostrarResumen) {
        this.mostrarResumen = mostrarResumen;
    }

    public Boolean getMostrarGarantiaPrendaria() {
        return mostrarGarantiaPrendaria;
    }

    public void setMostrarGarantiaPrendaria(Boolean mostrarGarantiaPrendaria) {
        this.mostrarGarantiaPrendaria = mostrarGarantiaPrendaria;
    }

    public Boolean getMostrarGarantiaSolidaria() {
        return mostrarGarantiaSolidaria;
    }

    public void setMostrarGarantiaSolidaria(Boolean mostrarGarantiaSolidaria) {
        this.mostrarGarantiaSolidaria = mostrarGarantiaSolidaria;
    }

    public Boolean getMostrarGarantiaHipotecaria() {
        return mostrarGarantiaHipotecaria;
    }

    public void setMostrarGarantiaHipotecaria(Boolean mostrarGarantiaHipotecaria) {
        this.mostrarGarantiaHipotecaria = mostrarGarantiaHipotecaria;
    }

    public String getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(String codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public ILisiadoBo getIlisiadoBo() {
        return ilisiadoBo;
    }

    public void setIlisiadoBo(ILisiadoBo ilisiadoBo) {
        this.ilisiadoBo = ilisiadoBo;
    }

    public IGarantiaProBo getIgarantiaProBo() {
        return igarantiaProBo;
    }

    public void setIgarantiaProBo(IGarantiaProBo igarantiaProBo) {
        this.igarantiaProBo = igarantiaProBo;
    }

    public IPropiedadLisiadoBo getIpropiedadLisiadoBo() {
        return ipropiedadLisiadoBo;
    }

    public void setIpropiedadLisiadoBo(IPropiedadLisiadoBo ipropiedadLisiadoBo) {
        this.ipropiedadLisiadoBo = ipropiedadLisiadoBo;
    }

    public List<TClienteProyecto> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<TClienteProyecto> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public List<TPropiedadLisiado> getListaPropiedadLisiado() {
        return listaPropiedadLisiado;
    }

    public void setListaPropiedadLisiado(List<TPropiedadLisiado> listaPropiedadLisiado) {
        this.listaPropiedadLisiado = listaPropiedadLisiado;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public TLisiado getLisiados() {
        return lisiados;
    }

    public void setLisiados(TLisiado lisiados) {
        this.lisiados = lisiados;
    }

    public TIngreso getIngresos() {
        return ingresos;
    }

    public void setIngresos(TIngreso ingresos) {
        this.ingresos = ingresos;
    }

    public TEgreso getEgresos() {
        return egresos;
    }

    public void setEgresos(TEgreso egresos) {
        this.egresos = egresos;
    }

    public TGarantia getGarantias() {
        return garantias;
    }

    public void setGarantias(TGarantia garantias) {
        this.garantias = garantias;
    }

    public TReferencia getReferencias() {
        return referencias;
    }

    public void setReferencias(TReferencia referencias) {
        this.referencias = referencias;
    }

    public TPropiedadLisiado getPropiedadLisiado() {
        return propiedadLisiado;
    }

    public void setPropiedadLisiado(TPropiedadLisiado propiedadLisiado) {
        this.propiedadLisiado = propiedadLisiado;
    }

    public Integer getIdGarantia() {
        return idGarantia;
    }

    public void setIdGarantia(Integer idGarantia) {
        this.idGarantia = idGarantia;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getPosesiones() {
        return posesiones;
    }

    public void setPosesiones(String posesiones) {
        this.posesiones = posesiones;
    }

    public List<String> getListaPosesiones() {
        return listaPosesiones;
    }

    public void setListaPosesiones(List<String> listaPosesiones) {
        this.listaPosesiones = listaPosesiones;
    }

    public List<TEgreso> getListaEgresos() {
        return listaEgresos;
    }

    public void setListaEgresos(List<TEgreso> listaEgresos) {
        this.listaEgresos = listaEgresos;
    }

    public Boolean getBanderaEgreso() {
        return banderaEgreso;
    }

    public void setBanderaEgreso(Boolean banderaEgreso) {
        this.banderaEgreso = banderaEgreso;
    }

    public boolean isShowImagen() {
        return showImagen;
    }

    public void setShowImagen(boolean showImagen) {
        this.showImagen = showImagen;
    }

    public boolean isShowImagenCarta() {
        return showImagenCarta;
    }

    public void setShowImagenCarta(boolean showImagenCarta) {
        this.showImagenCarta = showImagenCarta;
    }

    public boolean isOcultar() {
        return ocultar;
    }

    public void setOcultar(boolean ocultar) {
        this.ocultar = ocultar;
    }

    public boolean isOcultarEgresos() {
        return ocultarEgresos;
    }

    public void setOcultarEgresos(boolean ocultarEgresos) {
        this.ocultarEgresos = ocultarEgresos;
    }

    public boolean isEstadoHipotecaria() {
        return estadoHipotecaria;
    }

    public void setEstadoHipotecaria(boolean estadoHipotecaria) {
        this.estadoHipotecaria = estadoHipotecaria;
    }

    public boolean isEstadoPrendaria() {
        return estadoPrendaria;
    }

    public void setEstadoPrendaria(boolean estadoPrendaria) {
        this.estadoPrendaria = estadoPrendaria;
    }

    public boolean isEstadoSolidaria() {
        return estadoSolidaria;
    }

    public void setEstadoSolidaria(boolean estadoSolidaria) {
        this.estadoSolidaria = estadoSolidaria;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public String getMsgNombreCliente() {
        return msgNombreCliente;
    }

    public void setMsgNombreCliente(String msgNombreCliente) {
        this.msgNombreCliente = msgNombreCliente;
    }

    public String getMsgApellidoCliente() {
        return msgApellidoCliente;
    }

    public void setMsgApellidoCliente(String msgApellidoCliente) {
        this.msgApellidoCliente = msgApellidoCliente;
    }

    public String getMsgDuiCliente() {
        return msgDuiCliente;
    }

    public void setMsgDuiCliente(String msgDuiCliente) {
        this.msgDuiCliente = msgDuiCliente;
    }

    public String getMsgNitCliente() {
        return msgNitCliente;
    }

    public void setMsgNitCliente(String msgNitCliente) {
        this.msgNitCliente = msgNitCliente;
    }

    public String getMsgTelefonoCliente() {
        return msgTelefonoCliente;
    }

    public void setMsgTelefonoCliente(String msgTelefonoCliente) {
        this.msgTelefonoCliente = msgTelefonoCliente;
    }

    public String getMsgCelularCliente() {
        return msgCelularCliente;
    }

    public void setMsgCelularCliente(String msgCelularCliente) {
        this.msgCelularCliente = msgCelularCliente;
    }

    public Boolean getMostrarLisiados() {
        return mostrarLisiados;
    }

    public void setMostrarLisiados(Boolean mostrarLisiados) {
        this.mostrarLisiados = mostrarLisiados;
    }

    public Integer getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getFormaPagos() {
        return formaPagos;
    }

    public void setFormaPagos(String formaPagos) {
        this.formaPagos = formaPagos;
    }

    public TIngreso getIngresoSeleccionado() {
        return ingresoSeleccionado;
    }

    public void setIngresoSeleccionado(TIngreso ingresoSeleccionado) {
        this.ingresoSeleccionado = ingresoSeleccionado;
    }

    public TEgreso getEgresoSeleccionado() {
        return egresoSeleccionado;
    }

    public void setEgresoSeleccionado(TEgreso egresoSeleccionado) {
        this.egresoSeleccionado = egresoSeleccionado;
    }

    public TCliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(TCliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public TCliente getCliente() {
        return cliente;
    }

    public void setCliente(TCliente cliente) {
        this.cliente = cliente;
    }

    public TPago getPago() {
        return pago;
    }

    public void setPago(TPago pago) {
        this.pago = pago;
    }

    public TCooperativa getCooperativaSeleccionada() {
        return cooperativaSeleccionada;
    }

    public void setCooperativaSeleccionada(TCooperativa cooperativaSeleccionada) {
        this.cooperativaSeleccionada = cooperativaSeleccionada;
    }

    public TProyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(TProyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }

    public Boolean getMostrarAgropecuarios() {
        return mostrarAgropecuarios;
    }

    public void setMostrarAgropecuarios(Boolean mostrarAgropecuarios) {
        this.mostrarAgropecuarios = mostrarAgropecuarios;
    }

    public Boolean getMostrarPersonas() {
        return mostrarPersonas;
    }

    public void setMostrarPersonas(Boolean mostrarPersonas) {
        this.mostrarPersonas = mostrarPersonas;
    }

    public Boolean getMostrarCooperativas() {
        return mostrarCooperativas;
    }

    public void setMostrarCooperativas(Boolean mostrarCooperativas) {
        this.mostrarCooperativas = mostrarCooperativas;
    }

    public boolean isMostrarTipoCreditoCooper() {
        return mostrarTipoCreditoCooper;
    }

    public void setMostrarTipoCreditoCooper(boolean mostrarTipoCreditoCooper) {
        this.mostrarTipoCreditoCooper = mostrarTipoCreditoCooper;
    }

    public boolean isMostrarTipoCreditoPerso() {
        return mostrarTipoCreditoPerso;
    }

    public void setMostrarTipoCreditoPerso(boolean mostrarTipoCreditoPerso) {
        this.mostrarTipoCreditoPerso = mostrarTipoCreditoPerso;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getIntereses() {
        return intereses;
    }

    public void setIntereses(BigDecimal intereses) {
        this.intereses = intereses;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getCouto() {
        return couto;
    }

    public void setCouto(BigDecimal couto) {
        this.couto = couto;
    }

    public BigDecimal getTotalIngreso() {
        return totalIngreso;
    }

    public void setTotalIngreso(BigDecimal totalIngreso) {
        this.totalIngreso = totalIngreso;
    }

    public BigDecimal getTotalEgreso() {
        return totalEgreso;
    }

    public void setTotalEgreso(BigDecimal totalEgreso) {
        this.totalEgreso = totalEgreso;
    }

    public BigDecimal getTotalActivo() {
        return totalActivo;
    }

    public void setTotalActivo(BigDecimal totalActivo) {
        this.totalActivo = totalActivo;
    }

    public BigDecimal getTotalGarantia() {
        return totalGarantia;
    }

    public void setTotalGarantia(BigDecimal totalGarantia) {
        this.totalGarantia = totalGarantia;
    }

    public BigDecimal getTotalIngresoRazon() {
        return totalIngresoRazon;
    }

    public void setTotalIngresoRazon(BigDecimal totalIngresoRazon) {
        this.totalIngresoRazon = totalIngresoRazon;
    }

    public BigDecimal getTotalEgresoRazon() {
        return totalEgresoRazon;
    }

    public void setTotalEgresoRazon(BigDecimal totalEgresoRazon) {
        this.totalEgresoRazon = totalEgresoRazon;
    }

    public BigDecimal getResultadoPagoRazon() {
        return resultadoPagoRazon;
    }

    public void setResultadoPagoRazon(BigDecimal resultadoPagoRazon) {
        this.resultadoPagoRazon = resultadoPagoRazon;
    }

    public BigDecimal getResultadoRazonCapacidadPago() {
        return ResultadoRazonCapacidadPago;
    }

    public void setResultadoRazonCapacidadPago(BigDecimal ResultadoRazonCapacidadPago) {
        this.ResultadoRazonCapacidadPago = ResultadoRazonCapacidadPago;
    }

    public BigDecimal getTotalRazonActivos() {
        return totalRazonActivos;
    }

    public void setTotalRazonActivos(BigDecimal totalRazonActivos) {
        this.totalRazonActivos = totalRazonActivos;
    }

    public BigDecimal getTotalRazonPasivos() {
        return totalRazonPasivos;
    }

    public void setTotalRazonPasivos(BigDecimal totalRazonPasivos) {
        this.totalRazonPasivos = totalRazonPasivos;
    }

    public BigDecimal getResultadoRazonEndeudamiento() {
        return resultadoRazonEndeudamiento;
    }

    public void setResultadoRazonEndeudamiento(BigDecimal resultadoRazonEndeudamiento) {
        this.resultadoRazonEndeudamiento = resultadoRazonEndeudamiento;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public BigDecimal getTotalBalanceActivo() {
        return totalBalanceActivo;
    }

    public void setTotalBalanceActivo(BigDecimal totalBalanceActivo) {
        this.totalBalanceActivo = totalBalanceActivo;
    }

    public BigDecimal getTotalBalancePasivo() {
        return totalBalancePasivo;
    }

    public void setTotalBalancePasivo(BigDecimal totalBalancePasivo) {
        this.totalBalancePasivo = totalBalancePasivo;
    }

    public BigDecimal getTotalBalancePatrimonio() {
        return TotalBalancePatrimonio;
    }

    public void setTotalBalancePatrimonio(BigDecimal TotalBalancePatrimonio) {
        this.TotalBalancePatrimonio = TotalBalancePatrimonio;
    }

    public BigDecimal getValorRedondeadoActivo() {
        return valorRedondeadoActivo;
    }

    public void setValorRedondeadoActivo(BigDecimal valorRedondeadoActivo) {
        this.valorRedondeadoActivo = valorRedondeadoActivo;
    }

    public BigDecimal getValorRedondeadoGarantia() {
        return valorRedondeadoGarantia;
    }

    public void setValorRedondeadoGarantia(BigDecimal valorRedondeadoGarantia) {
        this.valorRedondeadoGarantia = valorRedondeadoGarantia;
    }

    public BigDecimal getValorRedondeadoIngreso() {
        return valorRedondeadoIngreso;
    }

    public void setValorRedondeadoIngreso(BigDecimal valorRedondeadoIngreso) {
        this.valorRedondeadoIngreso = valorRedondeadoIngreso;
    }

    public BigDecimal getValorRedondeadoEgreso() {
        return valorRedondeadoEgreso;
    }

    public void setValorRedondeadoEgreso(BigDecimal valorRedondeadoEgreso) {
        this.valorRedondeadoEgreso = valorRedondeadoEgreso;
    }

    private BigDecimal valorRedondeadoP;

    public BigDecimal getValorRedondeadoP() {
        return valorRedondeadoP;
    }

    public void setValorRedondeadoP(BigDecimal valorRedondeadoP) {
        this.valorRedondeadoP = valorRedondeadoP;
    }

    public BigDecimal getValorRedondeado() {
        return valorRedondeado;
    }

    public void setValorRedondeado(BigDecimal valorRedondeado) {
        this.valorRedondeado = valorRedondeado;
    }
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getCalculos() {
        return calculos;
    }

    public void setCalculos(Boolean calculos) {
        this.calculos = calculos;
    }

    public TPolitica getPoliticaSeleccionada() {
        return politicaSeleccionada;
    }

    public void setPoliticaSeleccionada(TPolitica politicaSeleccionada) {
        this.politicaSeleccionada = politicaSeleccionada;
    }

    public Integer getTipoCred() {
        return tipoCred;
    }

    public void setTipoCred(Integer tipoCred) {
        this.tipoCred = tipoCred;
    }

    public TPolitica getPolitica() {
        return politica;
    }

    public void setPolitica(TPolitica politica) {
        this.politica = politica;
    }

    public List<TEntidadProyecto> getListaEntidadProyectosFiltrada() {
        return listaEntidadProyectosFiltrada;
    }

    public void setListaEntidadProyectosFiltrada(List<TEntidadProyecto> listaEntidadProyectosFiltrada) {
        this.listaEntidadProyectosFiltrada = listaEntidadProyectosFiltrada;
    }

    public List<TEntidadProyecto> getListaEntidadProyectosReducida() {
        return listaEntidadProyectosReducida;
    }

    public void setListaEntidadProyectosReducida(List<TEntidadProyecto> listaEntidadProyectosReducida) {
        this.listaEntidadProyectosReducida = listaEntidadProyectosReducida;
    }

    public List<TClienteProyecto> getListaClienteProyectoFiltrada() {
        return listaClienteProyectoFiltrada;
    }

    public void setListaClienteProyectoFiltrada(List<TClienteProyecto> listaClienteProyectoFiltrada) {
        this.listaClienteProyectoFiltrada = listaClienteProyectoFiltrada;
    }

    public List<TClienteProyecto> getListaClienteProyectoReducida() {
        return listaClienteProyectoReducida;
    }

    public void setListaClienteProyectoReducida(List<TClienteProyecto> listaClienteProyectoReducida) {
        this.listaClienteProyectoReducida = listaClienteProyectoReducida;
    }

    public List<TGarantia> getListaGarantiaSeleccionada() {
        return listaGarantiaSeleccionada;
    }

    public void setListaGarantiaSeleccionada(List<TGarantia> listaGarantiaSeleccionada) {
        this.listaGarantiaSeleccionada = listaGarantiaSeleccionada;
    }

    public List<TActivo> getListaActivoSeleccionada() {
        return listaActivoSeleccionada;
    }

    public void setListaActivoSeleccionada(List<TActivo> listaActivoSeleccionada) {
        this.listaActivoSeleccionada = listaActivoSeleccionada;
    }

    public List<TReferencia> getListaReferenciaSeleccionada() {
        return listaReferenciaSeleccionada;
    }

    public void setListaReferenciaSeleccionada(List<TReferencia> listaReferenciaSeleccionada) {
        this.listaReferenciaSeleccionada = listaReferenciaSeleccionada;
    }
    private List<TReferencia> listaReferenciaSeleccionada;

    public List<TIngreso> getListaIngresoSeleccionada() {
        return listaIngresoSeleccionada;
    }

    public void setListaIngresoSeleccionada(List<TIngreso> listaIngresoSeleccionada) {
        this.listaIngresoSeleccionada = listaIngresoSeleccionada;
    }

    public List<TEgreso> getListaEgresoSeleccionada() {
        return listaEgresoSeleccionada;
    }

    public void setListaEgresoSeleccionada(List<TEgreso> listaEgresoSeleccionada) {
        this.listaEgresoSeleccionada = listaEgresoSeleccionada;
    }

    public List<TClienteProyecto> getListaClienteProyectos() {
        return listaClienteProyectos;
    }

    public void setListaClienteProyectos(List<TClienteProyecto> listaClienteProyectos) {
        this.listaClienteProyectos = listaClienteProyectos;
    }

    public List<TPago> getListaPagos() {
        return listaPagos;
    }

    public void setListaPagos(List<TPago> listaPagos) {
        this.listaPagos = listaPagos;
    }

    public TClienteProyecto getClienteProyecto() {
        return clienteProyecto;
    }

    public void setClienteProyecto(TClienteProyecto clienteProyecto) {
        this.clienteProyecto = clienteProyecto;
    }

    public TAgropecuario getAgropecuario() {
        return agropecuario;
    }

    public void setAgropecuario(TAgropecuario agropecuario) {
        this.agropecuario = agropecuario;
    }

    public TIngreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(TIngreso ingreso) {
        this.ingreso = ingreso;
    }

    public TEgreso getEgreso() {
        return egreso;
    }

    public void setEgreso(TEgreso egreso) {
        this.egreso = egreso;
    }

    public TGarantia getGarantia() {
        return garantia;
    }

    public void setGarantia(TGarantia garantia) {
        this.garantia = garantia;
    }

    public List<TIngreso> getListaIngreso() {
        return listaIngreso;
    }

    public void setListaIngreso(List<TIngreso> listaIngreso) {
        this.listaIngreso = listaIngreso;
    }

    public List<TEgreso> getListaEgreso() {
        return listaEgreso;
    }

    public void setListaEgreso(List<TEgreso> listaEgreso) {
        this.listaEgreso = listaEgreso;
    }

    public List<TGarantia> getListaGarantia() {
        return listaGarantia;
    }

    public void setListaGarantia(List<TGarantia> listaGarantia) {
        this.listaGarantia = listaGarantia;
    }

    public TActivo getActivo() {
        return activo;
    }

    public void setActivo(TActivo activo) {
        this.activo = activo;
    }

    public List<TActivo> getListaActivos() {
        return listaActivos;
    }

    public void setListaActivos(List<TActivo> listaActivos) {
        this.listaActivos = listaActivos;
    }

    public TReferencia getReferenciaSeleccionada() {
        return referenciaSeleccionada;
    }

    public void setReferenciaSeleccionada(TReferencia referenciaSeleccionada) {
        this.referenciaSeleccionada = referenciaSeleccionada;
    }

    public List<TReferencia> getListaReferencia() {
        return listaReferencia;
    }

    public void setListaReferencia(List<TReferencia> listaReferencia) {
        this.listaReferencia = listaReferencia;
    }

    public TReferencia getReferencia() {
        return referencia;
    }

    public void setReferencia(TReferencia referencia) {
        this.referencia = referencia;
    }

    public List<TEntidadProyecto> getListaEntidadProyectos() {
        return listaEntidadProyectos;
    }

    public void setListaEntidadProyectos(List<TEntidadProyecto> listaEntidadProyectos) {
        this.listaEntidadProyectos = listaEntidadProyectos;
    }

    public IClienteProyectoBo getIclienteProyectoBo() {
        return iclienteProyectoBo;
    }

    public void setIclienteProyectoBo(IClienteProyectoBo iclienteProyectoBo) {
        this.iclienteProyectoBo = iclienteProyectoBo;
    }

    public IActivoBo getIactivoBo() {
        return iactivoBo;
    }

    public void setIactivoBo(IActivoBo iactivoBo) {
        this.iactivoBo = iactivoBo;
    }

    public IAgropecuarioBo getIagropecuarioBo() {
        return iagropecuarioBo;
    }

    public void setIagropecuarioBo(IAgropecuarioBo iagropecuarioBo) {
        this.iagropecuarioBo = iagropecuarioBo;
    }

    public IReferenciaBo getIreferenciaBo() {
        return ireferenciaBo;
    }

    public void setIreferenciaBo(IReferenciaBo ireferenciaBo) {
        this.ireferenciaBo = ireferenciaBo;
    }

    public IGarantiaBo getIgarantiaBo() {
        return igarantiaBo;
    }

    public void setIgarantiaBo(IGarantiaBo igarantiaBo) {
        this.igarantiaBo = igarantiaBo;
    }

    public IEgresoBo getIegresoBo() {
        return iegresoBo;
    }

    public void setIegresoBo(IEgresoBo iegresoBo) {
        this.iegresoBo = iegresoBo;
    }

    public IIngresoBo getIingresoBo() {
        return iingresoBo;
    }

    public void setIingresoBo(IIngresoBo iingresoBo) {
        this.iingresoBo = iingresoBo;
    }

    public IClienteBo getIclienteBo() {
        return iclienteBo;
    }

    public void setIclienteBo(IClienteBo iclienteBo) {
        this.iclienteBo = iclienteBo;
    }

    public IPoliticaBo getIpoliticaBo() {
        return ipoliticaBo;
    }

    public void setIpoliticaBo(IPoliticaBo ipoliticaBo) {
        this.ipoliticaBo = ipoliticaBo;
    }

    public Integer getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(Integer tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public Boolean getMostrarTabla() {
        return mostrarTabla;
    }

    public void setMostrarTabla(Boolean mostrarTabla) {
        this.mostrarTabla = mostrarTabla;
    }

    public String getMsgNumero() {
        return msgNumero;
    }

    public void setMsgNumero(String msgNumero) {
        this.msgNumero = msgNumero;
    }

    public String getMsgLugar() {
        return msgLugar;
    }

    public void setMsgLugar(String msgLugar) {
        this.msgLugar = msgLugar;
    }

    public String getMsgFecha() {
        return msgFecha;
    }

    public void setMsgFecha(String msgFecha) {
        this.msgFecha = msgFecha;
    }

    public String getMsgCodigoEntidad() {
        return msgCodigoEntidad;
    }

    public void setMsgCodigoEntidad(String msgCodigoEntidad) {
        this.msgCodigoEntidad = msgCodigoEntidad;
    }

    public String getMsgNombreProyecto() {
        return msgNombreProyecto;
    }

    public void setMsgNombreProyecto(String msgNombreProyecto) {
        this.msgNombreProyecto = msgNombreProyecto;
    }

    public String getMsgDescripcion() {
        return msgDescripcion;
    }

    public void setMsgDescripcion(String msgDescripcion) {
        this.msgDescripcion = msgDescripcion;
    }

    public String getMsgDestino() {
        return msgDestino;
    }

    public void setMsgDestino(String msgDestino) {
        this.msgDestino = msgDestino;
    }

    public String getMsgArea() {
        return msgArea;
    }

    public void setMsgArea(String msgArea) {
        this.msgArea = msgArea;
    }

    public String getMsgCredito() {
        return msgCredito;
    }

    public void setMsgCredito(String msgCredito) {
        this.msgCredito = msgCredito;
    }

    public String getMsgActivoC() {
        return msgActivoC;
    }

    public void setMsgActivoC(String msgActivoC) {
        this.msgActivoC = msgActivoC;
    }

    public String getMsgActivoN() {
        return msgActivoN;
    }

    public void setMsgActivoN(String msgActivoN) {
        this.msgActivoN = msgActivoN;
    }

    public String getMsgPasivoC() {
        return msgPasivoC;
    }

    public void setMsgPasivoC(String msgPasivoC) {
        this.msgPasivoC = msgPasivoC;
    }

    public String getMsgPasivoN() {
        return msgPasivoN;
    }

    public void setMsgPasivoN(String msgPasivoN) {
        this.msgPasivoN = msgPasivoN;
    }

    public String getMsgPatrimonio() {
        return msgPatrimonio;
    }

    public void setMsgPatrimonio(String msgPatrimonio) {
        this.msgPatrimonio = msgPatrimonio;
    }

    public String getMsgMonto() {
        return msgMonto;
    }

    public void setMsgMonto(String msgMonto) {
        this.msgMonto = msgMonto;
    }

    public String getMsgPlazo() {
        return msgPlazo;
    }

    public void setMsgPlazo(String msgPlazo) {
        this.msgPlazo = msgPlazo;
    }

    public Boolean getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public List<SelectItem> getSelectOneItemEntidades() {
        this.selectOneItemEntidades = new ArrayList<SelectItem>();
        List<TEntidad> entidades = ientidadBo.listTEndidad();
        for (TEntidad entidad : entidades) {
            if (entidad.getEstadoEntidad() == true) {
                SelectItem selectItem = new SelectItem(entidad.getIdEntidad(), entidad.getNombreEntidad());
                this.selectOneItemEntidades.add(selectItem);
            }
        }
        return selectOneItemEntidades;
    }

    public void setSelectOneItemEntidades(List<SelectItem> selectOneItemEntidades) {
        this.selectOneItemEntidades = selectOneItemEntidades;
    }

    public IEntidadBo getIentidadBo() {
        return ientidadBo;
    }

    public void setIentidadBo(IEntidadBo ientidadBo) {
        this.ientidadBo = ientidadBo;
    }

    public Boolean getShowPresupuesto() {
        return showPresupuesto;
    }

    public void setShowPresupuesto(Boolean showPresupuesto) {
        this.showPresupuesto = showPresupuesto;
    }

    public Boolean getShowBalance() {
        return showBalance;
    }

    public void setShowBalance(Boolean showBalance) {
        this.showBalance = showBalance;
    }

    public Boolean getShowAprobacion() {
        return showAprobacion;
    }

    public void setShowAprobacion(Boolean showAprobacion) {
        this.showAprobacion = showAprobacion;
    }

    public TProyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(TProyecto proyecto) {
        this.proyecto = proyecto;
    }

    public TCooperativa getCooperativa() {
        return cooperativa;
    }

    public void setCooperativa(TCooperativa cooperativa) {
        this.cooperativa = cooperativa;
    }

    public TEntidadProyecto getEntidadProyecto() {
        return entidadProyecto;
    }

    public void setEntidadProyecto(TEntidadProyecto entidadProyecto) {
        this.entidadProyecto = entidadProyecto;
    }

    public IProyectoBo getIproyectoBo() {
        return iproyectoBo;
    }

    public void setIproyectoBo(IProyectoBo iproyectoBo) {
        this.iproyectoBo = iproyectoBo;
    }

    public ICooperativaBo getIcooperativaBo() {
        return icooperativaBo;
    }

    public void setIcooperativaBo(ICooperativaBo icooperativaBo) {
        this.icooperativaBo = icooperativaBo;
    }

    public IEntidadProyectoBo getIentidadProyectoBo() {
        return ientidadProyectoBo;
    }

    public void setIentidadProyectoBo(IEntidadProyectoBo ientidadProyectoBo) {
        this.ientidadProyectoBo = ientidadProyectoBo;
    }

    public IAreaBo getIareaBo() {
        return iareaBo;
    }

    public void setIareaBo(IAreaBo iareaBo) {
        this.iareaBo = iareaBo;
    }

    public TEntidad getEntidad() {
        return Entidad;
    }

    public void setEntidad(TEntidad Entidad) {
        this.Entidad = Entidad;
    }

    public TEntidad getEntidadeleccionada() {
        return Entidadeleccionada;
    }

    public void setEntidadeleccionada(TEntidad Entidadeleccionada) {
        this.Entidadeleccionada = Entidadeleccionada;
    }

    public List<TEntidad> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(List<TEntidad> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
    }

    public void updateListaResumen() {

        listaSolicitudesProyectos = this.iproyectoBo.listTProyecto(2);

    }

    public void Init() {

        this.Entidad = new TEntidad();
        this.Entidadeleccionada = new TEntidad();
        this.cooperativaSeleccionada = new TCooperativa();
        this.politica = new TPolitica();
        this.proyecto = new TProyecto();
        this.proyecto.setTTipoCredito(new TTipoCredito());
        this.proyectoSeleccionado = new TProyecto();
        this.proyectoSeleccionado.setTTipoCredito(new TTipoCredito());
        this.ingresoSeleccionado = new TIngreso();
        this.egresoSeleccionado = new TEgreso();
        this.cooperativa = new TCooperativa();
        this.agropecuario = new TAgropecuario();
        this.entidadProyecto = new TEntidadProyecto();
        this.cliente = new TCliente();
        this.mostrarResumen = true;
        this.clienteSeleccionado = new TCliente();
        super.setShowData(true);
        this.tipoCredito = 0;
        this.showPresupuesto = true;
        this.showAprobacion = true;
        this.showBalance = true;
        this.listaEntidadProyectosReducida = new ArrayList<TEntidadProyecto>();
        this.listaEntidadClienteProyectosFiltrada = new ArrayList<TEntidadProyecto>();
        this.listaClienteProyectoReducida = new ArrayList<TClienteProyecto>();
        this.listaComentarios = new ArrayList<TComentario>();
        this.listaSugerencia = new ArrayList<TSugerencia>();
        this.comentario = new TComentario();
        this.sugerencia = new TSugerencia();
        this.calculos = false;
        this.referencia = new TReferencia();
        this.listaReferencia = new ArrayList<TReferencia>();
        this.activo = new TActivo();
        this.listaActivos = new ArrayList<TActivo>();
        this.ingreso = new TIngreso();
        this.listaIngreso = new ArrayList<TIngreso>();
        this.egreso = new TEgreso();
        this.listaEgreso = new ArrayList<TEgreso>();
        this.garantia = new TGarantia();
        this.listaGarantia = new ArrayList<TGarantia>();
        this.clienteProyecto = new TClienteProyecto();
        this.clienteProyecto.setTCliente(new TCliente());
        this.clienteProyecto.setTProyecto(new TProyecto());
        this.tipoCliente = 2;
        this.mostrarTipoCreditoCooper = true;
        this.mostrarTabla = false;
        this.ingresos = new TIngreso();
        this.egresos = new TEgreso();
        this.lisiados = new TLisiado();
        this.comercio = new TComercio();
        this.trabajo = new TTrabajo();
        this.trabajo.setTCliente(new TCliente());
        this.garantias = new TGarantia();
        this.propiedadLisiado = new TPropiedadLisiado();
        this.referencias = new TReferencia();
        this.banderaEgreso = false;
        this.showImagen = true;
        this.showImagenCarta = true;
        this.ocultar = true;
        this.ocultarEgresos = true;
        this.banderaEgreso = false;
        this.lisiados.setExperienciaCrediticia(false);
        this.estadoFormulario = false;
        this.estadoHipotecaria = true;
        this.estadoPrendaria = false;
        this.estadoSolidaria = false;
        this.listaPropiedadLisiado = new ArrayList<>();
        this.listaEgresos = new ArrayList<>();
        this.listaPosesiones = new ArrayList<String>();
        this.idGarantia = 0;
        this.aprobar = true;

    }

    public void enableShowPresupuesto() {

        this.estadoFormulario = false;
        this.setShowPresupuesto(!this.getShowPresupuesto());

    }

    public void enableShowBalance() {

        this.estadoFormulario = false;
        this.setShowBalance(!this.getShowBalance());

    }

    public void enableShowAprobacion() {

        this.estadoFormulario = false;
        this.setShowAprobacion(!this.getShowAprobacion());

    }

    public void cargarPolitica() {

        if (this.proyecto.getTTipoCredito().getIdTipoCredito() != 5) {
            this.politica = this.ipoliticaBo.getPolitica(this.proyecto.getTTipoCredito().getIdTipoCredito());
        }

    }

    private String msgFechaAprobacion;

    public String getMsgFechaAprobacion() {
        return msgFechaAprobacion;
    }

    public void setMsgFechaAprobacion(String msgFechaAprobacion) {
        this.msgFechaAprobacion = msgFechaAprobacion;
    }

    public void limpiarResolucion() {

        this.listaSugerencia = new ArrayList<TSugerencia>();
        this.listaComentarios = new ArrayList<TComentario>();
        this.comentario = new TComentario();
        this.sugerencia = new TSugerencia();
        this.msgFechaAprobacion = "";
        this.msgDescripcionComertario="";
        this.msgDescripcionSugerencia="";

    }

    public void validarFormulario() {

        this.estadoFormulario = true;
        if (this.proyectoSeleccionado.getFechaAprovacion() == null) {
            this.msgFechaAprobacion = "Debe introducir la fecha";
            this.estadoFormulario = false;
        } else {
            this.msgFechaAprobacion = "";
        }

    }

    public void createResolucionCooperativa() {

        if (this.aprobar == true) {
            this.proyectoSeleccionado.setEstado(3);
            this.proyectoSeleccionado.setCalificacion("Pendiente");
            
            
            if(this.Entidadeleccionada.getCodigoClienteEntidad().equals("")){
                
                this.listaEntidadesClientes = this.ientidadBo.listTEndidadCodigo();
                int correlativo = this.listaEntidadesClientes.size() + 1;
                if (correlativo < 10) {
                    this.Entidadeleccionada.setCodigoClienteEntidad("COP-0000" + correlativo);
                } else if (correlativo < 100) {
                    this.Entidadeleccionada.setCodigoClienteEntidad("COP-000" + correlativo);
                } else if (correlativo < 1000) {
                    this.Entidadeleccionada.setCodigoClienteEntidad("COP-00" + correlativo);
                } else if (correlativo < 10000) {
                    this.Entidadeleccionada.setCodigoClienteEntidad("COP-0" + correlativo);
                } else {
                    this.Entidadeleccionada.setCodigoClienteEntidad("COP-" + correlativo);
                }
                this.ientidadBo.update(this.Entidadeleccionada);
                
            }
            
                
           
            this.listaAprobado = this.iproyectoBo.listTCreditos();
            int codigoProyecto = this.listaAprobado.size() + 1;
            if (codigoProyecto < 10) {
                this.proyectoSeleccionado.setCodigoProyecto("CRED-0000" + codigoProyecto);
            } else if (codigoProyecto < 100) {
                this.proyectoSeleccionado.setCodigoProyecto("CRED-000" + codigoProyecto);
            } else if (codigoProyecto < 1000) {
                this.proyectoSeleccionado.setCodigoProyecto("CRED-00" + codigoProyecto);
            } else if (codigoProyecto < 10000) {
                this.proyectoSeleccionado.setCodigoProyecto("CRED-0" + codigoProyecto);
            } else {
                this.proyectoSeleccionado.setCodigoProyecto("CRED-" + codigoProyecto);
            }
            this.iproyectoBo.update(this.proyectoSeleccionado);
            for (int i = 0; i < this.listaComentarios.size(); i++) {
                this.listaComentarios.get(i).setTProyecto(this.proyectoSeleccionado);
                this.icomentarioBo.create(this.listaComentarios.get(i));
            }
            for (int y = 0; y < this.listaSugerencia.size(); y++) {
                this.listaSugerencia.get(y).setTProyecto(this.proyectoSeleccionado);
                this.isugerenciaBo.create(this.listaSugerencia.get(y));
            }
            this.aprobar = true;
            this.listaComentarios.clear();
            this.listaSugerencia.clear();
            this.estadoFormulario = false;
            TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Agregar resolución");
        String formaPagosCredito = "";
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }
        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
                + ", Resolución: Aprobado "
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud aprobada", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);

        } else {

            this.proyectoSeleccionado.setEstado(4);
            this.iproyectoBo.update(this.proyectoSeleccionado);
            this.aprobar = true;
            this.listaComentarios.clear();
            this.listaSugerencia.clear();
            this.estadoFormulario = false;
            TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Agregar resolución");
        String formaPagosCredito = "";
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }
        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
                + ", Resolución: No aprobado "
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud no aprobada", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);

        }
        
        
        
        
        
        
        mostrarSolicitudes();
        this.enableShowData();

    }

    public void createResolucionPersona() {

        if (this.aprobar == true) {
            this.proyectoSeleccionado.setEstado(3);
            this.proyectoSeleccionado.setCalificacion("Pendiente");
           
            //this.listaCliente = this.iclienteProyectoBo.listTClienteProyectoAprovadosComprabacion(this.clienteSeleccionado.getIdCliente());
            
            
            if (this.clienteSeleccionado.getCodigoCliente().equals("")) {//generamos el codigo del cliente
                this.listaClientes = this.iclienteBo.listClienteCodigo();
                int correlativo = this.listaClientes.size() + 1;
                if (correlativo < 10) {
                    this.clienteSeleccionado.setCodigoCliente("PERS-0000" + correlativo);
                } else if (correlativo < 100) {
                    this.clienteSeleccionado.setCodigoCliente("PERS-000" + correlativo);
                } else if (correlativo < 1000) {
                    this.clienteSeleccionado.setCodigoCliente("PERS-00" + correlativo);
                } else if (correlativo < 10000) {
                    this.clienteSeleccionado.setCodigoCliente("PERS-0" + correlativo);
                } else {
                    this.clienteSeleccionado.setCodigoCliente("PERS-" + correlativo);
                }
                
                this.iclienteBo.update(this.clienteSeleccionado);
                
            }
           
            this.listaAprobado = this.iproyectoBo.listTCreditos();
            int codigoProyecto = this.listaAprobado.size() + 1;
            if (codigoProyecto < 10) {
                this.proyectoSeleccionado.setCodigoProyecto("CRED-0000" + codigoProyecto);
            } else if (codigoProyecto < 100) {
                this.proyectoSeleccionado.setCodigoProyecto("CRED-000" + codigoProyecto);
            } else if (codigoProyecto < 1000) {
                this.proyectoSeleccionado.setCodigoProyecto("CRED-00" + codigoProyecto);
            } else if (codigoProyecto < 10000) {
                this.proyectoSeleccionado.setCodigoProyecto("CRED-0" + codigoProyecto);
            } else {
                this.proyectoSeleccionado.setCodigoProyecto("CRED-" + codigoProyecto);
            }
            
            
            
            this.iproyectoBo.update(this.proyectoSeleccionado);
            TEmpleado empleado = new TEmpleado();
            empleado.setIdEmpleado(24);
            for (int i = 0; i < this.listaComentarios.size(); i++) {
                this.listaComentarios.get(i).setTProyecto(this.proyectoSeleccionado);
                this.icomentarioBo.create(this.listaComentarios.get(i));
            }
            for (int y = 0; y < this.listaSugerencia.size(); y++) {
                this.listaSugerencia.get(y).setTProyecto(this.proyectoSeleccionado);
                this.isugerenciaBo.create(this.listaSugerencia.get(y));
            }
            this.aprobar = true;
            this.listaComentarios.clear();
            this.listaSugerencia.clear();
            this.estadoFormulario = false;
            TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Agregar resolución");
        String formaPagosCredito = "";
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }
        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
                + ", Resolución: Aprobado "
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud aprobada", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);

        } else {

            this.proyectoSeleccionado.setEstado(4);
            this.iproyectoBo.update(this.proyectoSeleccionado);
            this.aprobar = true;
            this.listaComentarios.clear();
            this.listaSugerencia.clear();
            this.estadoFormulario = false;
            TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Agregar resolución");
        String formaPagosCredito = "";
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }
        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
                + ", Resolución: No aprobado "
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud no aprobada", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);

        }
        mostrarSolicitudes();
        this.enableShowData();

    }

    public void guardarImagenCarta(FileUploadEvent event) throws IOException {

        String tipo = event.getFile().getContentType();
        byte[] content = event.getFile().getContents();
        String b64 = Base64.encode(content);
        String guardar = "data:" + tipo + ";base64," + b64;
        this.lisiados.setCartaLisiado(guardar);
        this.showImagenCarta = false;
        FacesMessage message = new FacesMessage("Foto Cargada");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarPresupuesto(FileUploadEvent event) throws IOException {

        String tipo = event.getFile().getContentType();
        byte[] content = event.getFile().getContents();
        String b64 = Base64.encode(content);
        String guardar = "data:" + tipo + ";base64," + b64;
        this.cooperativa.setPresupuesto(guardar);
        this.showPresupuesto = false;
        FacesMessage message = new FacesMessage("Presupuesto cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarPresupuestoModificar(FileUploadEvent event) throws IOException {

        String tipo = event.getFile().getContentType();
        byte[] content = event.getFile().getContents();
        String b64 = Base64.encode(content);
        String guardar = "data:" + tipo + ";base64," + b64;
        this.cooperativaSeleccionada.setPresupuesto(guardar);
        this.showPresupuesto = true;
        FacesMessage message = new FacesMessage("Presupuesto cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarBalance(FileUploadEvent event) throws IOException {

        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.cooperativa.setBalanceGeneral(guardarP);
        this.showBalance = false;
        FacesMessage message = new FacesMessage("Balance cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarBalanceModificar(FileUploadEvent event) throws IOException {

        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.cooperativaSeleccionada.setBalanceGeneral(guardarP);
        this.showBalance = true;
        FacesMessage message = new FacesMessage("Balance cargado cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarAcuerdo(FileUploadEvent event) throws IOException {

        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.cooperativa.setAcuerdoSolicitud(guardarP);
        this.showAprobacion = false;
        FacesMessage message = new FacesMessage("Balance cargado cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarAcuerdoModificar(FileUploadEvent event) throws IOException {

        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.cooperativaSeleccionada.setAcuerdoSolicitud(guardarP);
        this.showAprobacion = false;
        FacesMessage message = new FacesMessage("Balance cargado cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void enableShowDataBean() {

        super.enableShowData();

    }

    public void correlativoProyecto() {

        Integer correlativo = this.iproyectoBo.getCorrelativoProyecto() + 1;
        if (correlativo < 10) {
            this.proyectoSeleccionado.setCodigoProyecto("CRED-0000" + correlativo);
        } else if (correlativo < 100) {
            this.proyectoSeleccionado.setCodigoProyecto("CRED-000" + correlativo);
        } else if (correlativo < 1000) {
            this.proyectoSeleccionado.setCodigoProyecto("CRED-00" + correlativo);
        } else if (correlativo < 10000) {
            this.proyectoSeleccionado.setCodigoProyecto("CRED-0" + correlativo);
        } else {
            this.proyectoSeleccionado.setCodigoProyecto("CRED-" + correlativo);
        }

    }

    public void cargarResolucionCooperativas() {

        this.cooperativaSeleccionada = this.icooperativaBo.getTCooperativa(this.proyectoSeleccionado.getIdproyecto());
        this.politicaSeleccionada = this.ipoliticaBo.getPolitica(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        System.out.println(this.cooperativaSeleccionada.getAreaProducida());
        DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
       
        BigDecimal totalActivos;
        BigDecimal totalPasivos;
        totalActivos = this.cooperativaSeleccionada.getActivoCorriente().add(this.cooperativaSeleccionada.getActivoNocorriente());
        totalPasivos = this.cooperativaSeleccionada.getPasivoCorriente().add(this.cooperativaSeleccionada.getPasivoNocorriente());
        BigDecimal razonEndeudamiento = totalPasivos.divide(totalActivos, 5, RoundingMode.HALF_UP);
        this.totalRazonActivos = totalActivos;
        this.totalRazonPasivos = totalPasivos;
        this.resultadoRazonEndeudamiento = razonEndeudamiento;
        if (this.resultadoRazonEndeudamiento.compareTo(this.politicaSeleccionada.getCapacidadEndeudamientoMinimo()) >= 0) {//mayor que el 60% por ejemplo
            this.mensajeAprobado = true;
            this.mensajeNoAprobado = false;
        } else {
            this.mensajeNoAprobado = true;
            this.mensajeAprobado = false;
        }
        this.ResultadoRazonCapacidadPago = this.cooperativaSeleccionada.getActivoCorriente().divide(this.cooperativaSeleccionada.getPasivoCorriente(), 5, RoundingMode.HALF_UP);
        if (this.ResultadoRazonCapacidadPago.compareTo(this.politicaSeleccionada.getCapacidadPagoMinimo()) >= 0) {//mayor que 1
            this.mensajeAprobadoPago = true;
            this.mensajeNoAprobadoPago = false;
        } else {
            this.mensajeNoAprobadoPago = true;
            this.mensajeAprobadoPago = false;
        }
        this.enableShowCreate();
    }

    public void cargarResolucionAgropecuario() {

        
        BigDecimal sumaIngresos = BigDecimal.ZERO;
        BigDecimal sumaEgresos = BigDecimal.ZERO;
        this.listaIngreso = this.iingresoBo.listIngreso(this.proyectoSeleccionado.getIdproyecto());
        this.listaEgreso = this.iegresoBo.listEgreso(this.proyectoSeleccionado.getIdproyecto());
        for (int i = 0; i < this.listaIngreso.size(); i++) {
            sumaIngresos = sumaIngresos.add(this.listaIngreso.get(i).getValor());
        }
        for (int i = 0; i < this.listaEgreso.size(); i++) {
            sumaEgresos = sumaEgresos.add(this.listaEgreso.get(i).getValor());
        }
        this.totalIngresoRazon = sumaIngresos;
        this.totalEgresoRazon = sumaEgresos;
        this.resultadoPagoRazon = this.totalIngresoRazon.subtract(this.totalEgresoRazon);
        this.enableShowPersona();

    }

    public void cargarResolucionComercio() {

        
        BigDecimal sumaIngresos = BigDecimal.ZERO;
        BigDecimal sumaEgresos = BigDecimal.ZERO;
        this.listaIngreso = this.iingresoBo.listIngreso(this.proyectoSeleccionado.getIdproyecto());
        this.listaEgreso = this.iegresoBo.listEgreso(this.proyectoSeleccionado.getIdproyecto());
        for (int i = 0; i < this.listaIngreso.size(); i++) {
            sumaIngresos = sumaIngresos.add(this.listaIngreso.get(i).getValor());
        }
        for (int i = 0; i < this.listaEgreso.size(); i++) {
            sumaEgresos = sumaEgresos.add(this.listaEgreso.get(i).getValor());
        }
        this.totalIngresoRazon = sumaIngresos;
        this.totalEgresoRazon = sumaEgresos;
        this.resultadoPagoRazon = this.totalIngresoRazon.subtract(this.totalEgresoRazon);
        this.enableShowPersona();

    }

    public void cargarResolucionEmpleado() {

       
        BigDecimal sumaIngresos = BigDecimal.ZERO;
        BigDecimal sumaEgresos = BigDecimal.ZERO;
        this.listaIngreso = this.iingresoBo.listIngreso(this.proyectoSeleccionado.getIdproyecto());
        this.listaEgreso = this.iegresoBo.listEgreso(this.proyectoSeleccionado.getIdproyecto());
        for (int i = 0; i < this.listaIngreso.size(); i++) {
            sumaIngresos = sumaIngresos.add(this.listaIngreso.get(i).getValor());
        }
        for (int i = 0; i < this.listaEgreso.size(); i++) {
            sumaEgresos = sumaEgresos.add(this.listaEgreso.get(i).getValor());
        }
        this.totalIngresoRazon = sumaIngresos;
        this.totalEgresoRazon = sumaEgresos;
        this.resultadoPagoRazon = this.totalIngresoRazon.subtract(this.totalEgresoRazon);
        this.enableShowPersona();

    }

    public void cargarResolucionLisiado() {

       
        BigDecimal sumaIngresos = BigDecimal.ZERO;
        BigDecimal sumaEgresos = BigDecimal.ZERO;
        this.listaIngreso = this.iingresoBo.listIngreso(this.proyectoSeleccionado.getIdproyecto());
        this.listaEgreso = this.iegresoBo.listEgreso(this.proyectoSeleccionado.getIdproyecto());
        for (int i = 0; i < this.listaIngreso.size(); i++) {
            sumaIngresos = sumaIngresos.add(this.listaIngreso.get(i).getValor());
        }
        for (int i = 0; i < this.listaEgreso.size(); i++) {
            sumaEgresos = sumaEgresos.add(this.listaEgreso.get(i).getValor());
        }
        this.totalIngresoRazon = sumaIngresos;
        this.totalEgresoRazon = sumaEgresos;
        this.resultadoPagoRazon = this.totalIngresoRazon.subtract(this.totalEgresoRazon);
        this.enableShowPersona();

    }

    public String cambiar(Integer valor) {

        if (valor == 8) {
            return this.tipo = "Crédito para capital de trabajo agropecuario";
        } else if (valor == 9) {
            return this.tipo = "Crédito para inversión agropecuaria";
        } else if (valor == 7) {
            return this.tipo = "Crédito para comercio";
        } else if (valor == 6) {
            return this.tipo = "Crécito personal";
        } else if (valor == 5) {
            return this.tipo = "Crédito para lisiados de guerra";
        } else if (valor == 4) {
            return this.tipo = "Crédito para producción agropecuaria";
        } else if (valor == 3) {
            return this.tipo = "Crédito para producción cooperativa";
        } else if (valor == 2) {
            return this.tipo = "Crédito para inversión cooperativa";
        } else {
            return this.tipo = "Credito para empleados";
        }

    }

    public void mostrarTipos() {

        if (this.tipoCliente == 1) {
            this.mostrarTipoCreditoCooper = false;
            this.mostrarTipoCreditoPerso = true;
        } else if (this.tipoCliente == 2) {
            this.mostrarTipoCreditoCooper = true;
            this.mostrarTipoCreditoPerso = false;
        } else {
            this.mostrarTipoCreditoCooper = false;
            this.mostrarTipoCreditoPerso = false;
        }

    }

    public void updateListaCreditos() {

        this.listaEntidadProyectosReducida.clear();
        this.listaClienteProyectoReducida.clear();
        if (this.tipoCredito == 0) {
        }
        if (this.tipoCredito == 2) {
            this.listaEntidadProyectosReducida = this.ientidadProyectoBo.listTEndidadProyectosResolucion(2);
        }
        if (this.tipoCredito == 8) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyectoResolucion(8);
        }
        if (this.tipoCredito == 7) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyectoResolucion(1);
        }
        if (this.tipoCredito == 6) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyectoResolucion(6);
        }
        if (this.tipoCredito == 1) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyectoResolucion(7);
        }
        if (this.tipoCredito == 5) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyectoResolucion(5);
        }

    }

    public void mostrarSolicitudes() {

        if (this.tipoCredito == 0) {
            this.mostrarCooperativas = false;
            this.mostrarPersonas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarResumen = true;
            this.mostrarEmpleados = false;
        }
        if (this.tipoCredito == 2) {
            this.mostrarCooperativas = true;
            this.mostrarPersonas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = false;
        }
        if (this.tipoCredito == 8) {
            this.mostrarAgropecuarios = true;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = false;
        }
        if (this.tipoCredito == 7) {
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarComercio = false;
            this.mostrarLisiados = false;
            this.mostrarAgropecuarios = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = true;
        }
        if (this.tipoCredito == 6) {
            this.mostrarAgropecuarios = false;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = false;
        }
        if (this.tipoCredito == 1) {
            this.mostrarComercio = true;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarLisiados = false;
            this.mostrarAgropecuarios = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = false;
        }
        if (this.tipoCredito == 5) {
            this.mostrarLisiados = true;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarComercio = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = false;
        }
    }

    public void aprovarSolicitud() {

        this.proyectoSeleccionado.setEstado(3);
        this.iproyectoBo.update(this.proyectoSeleccionado);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud aprovada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    public void eliminarSolicitud() {

        if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 7) {
            TComercio comercio = this.icomercioBo.getComercio(this.proyectoSeleccionado.getIdproyecto());
            this.icomercioBo.delete(comercio);
        }
        if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 9 || this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 8 || this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 4) {
            TAgropecuario agropecuario = this.iagropecuarioBo.getAgropecuario(this.proyectoSeleccionado.getIdproyecto());
            this.iagropecuarioBo.delete(agropecuario);
        }
        if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 5) {
            TLisiado lisiado = this.ilisiadoBo.getLisiado(this.clienteSeleccionado.getIdCliente());
            this.ilisiadoBo.delete(lisiado);
        }
        this.listaIngreso = this.iingresoBo.listIngreso(this.proyectoSeleccionado.getIdproyecto());
        for (int i = 0; i < this.listaIngreso.size(); i++) {
            this.iingresoBo.delete(this.listaIngreso.get(i));
        }
        this.listaEgreso = this.iegresoBo.listEgreso(this.proyectoSeleccionado.getIdproyecto());
        for (int i = 0; i < this.listaEgreso.size(); i++) {
            this.iegresoBo.delete(this.listaEgreso.get(i));
        }
        this.listaReferencia = this.ireferenciaBo.listReferencia(this.proyectoSeleccionado.getIdproyecto());
        for (int i = 0; i < this.listaReferencia.size(); i++) {
            this.ireferenciaBo.delete(this.listaReferencia.get(i));
        }
        this.listaActivos = this.iactivoBo.listActivo(this.proyectoSeleccionado.getIdproyecto());
        for (int i = 0; i < this.listaActivos.size(); i++) {
            this.iactivoBo.delete(this.listaActivos.get(i));
        }
        this.iclienteProyectoBo.delete(this.clienteProyectoEliminar);
        TGarantia garantia = this.igarantiaBo.getTGarantia(this.proyectoSeleccionado.getIdproyecto());
        this.igarantiaBo.delete(garantia);
        this.iproyectoBo.delete(this.proyectoSeleccionado);
        if (this.clienteSeleccionado.getCodigoCliente() == "") {
            this.iclienteBo.delete(this.clienteSeleccionado);
        }
        
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Eliminar solicitud");
        String formaPagosCredito = "";
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }
        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        mostrarSolicitudes();
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud eliminada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    private TEntidadProyecto entidadProyectoEliminar;

    public TEntidadProyecto getEntidadProyectoEliminar() {
        return entidadProyectoEliminar;
    }

    public void setEntidadProyectoEliminar(TEntidadProyecto entidadProyectoEliminar) {
        this.entidadProyectoEliminar = entidadProyectoEliminar;
    }

    public void eliminarSolicitudCooperativa() {

        this.ientidadProyectoBo.delete(this.entidadProyectoEliminar);
        TCooperativa cooperativa = this.icooperativaBo.getTCooperativa(this.proyectoSeleccionado.getIdproyecto());
        this.icooperativaBo.delete(cooperativa);
        TGarantia garantia = this.igarantiaBo.getTGarantia(this.proyectoSeleccionado.getIdproyecto());
        this.igarantiaBo.delete(garantia);
        this.iproyectoBo.delete(this.proyectoSeleccionado);
        
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Eliminar solicitud");
        String formaPagosCredito = "";
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }
        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
             
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        mostrarSolicitudes();
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud eliminada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    public void llenarComentario() {

        Boolean validar = true;
        if (this.comentario.getDescripcion() == "") {
            this.msgDescripcionComertario = "Debe introducir una descripcion";
            validar = false;
        } else {
            this.msgDescripcionComertario = "";
        }
        if (validar == true) {
            this.listaComentarios.add(this.comentario);
            this.comentario = new TComentario();
        }

    }

    public void removerComentario(int posicion) {
        this.listaComentarios.remove(posicion);
    }
    
      public void actualizarComentario(int posicion) {
          
        this.comentario= this.listaComentarios.get(posicion);
        this.listaComentarios.remove(posicion);
        
    }
      
          public void actualizarSugerencia(int posicion) {
          
        this.sugerencia= this.listaSugerencia.get(posicion);
        this.listaSugerencia.remove(posicion);
        
    }


    public void llenarSugerencia() {

        Boolean validar = true;
        if (this.sugerencia.getDescripcion() == "") {
            this.msgDescripcionSugerencia = "Debe introducir una descripcion";
            validar = false;
        } else {
            this.msgDescripcionSugerencia = "";
        }
        if (validar == true) {
            this.listaSugerencia.add(this.sugerencia);
            this.sugerencia = new TSugerencia();
        }

    }

    public void removerSugerencia(int posicion) {
        this.listaSugerencia.remove(posicion);
    }

    public void mostrarFiltroSolicitud() {

        int estaCooperativa = 0;
        int estaPersona = 0;
        this.listaEntidadProyectosReducida.clear();
        this.listaClienteProyectoReducida.clear();
        this.listaEntidadProyectosFiltrada = this.ientidadProyectoBo.listTEndidadProyectosResolucion(2);
        for (int i = 0; i < this.listaEntidadProyectosFiltrada.size(); i++) {
            if (this.listaEntidadProyectosFiltrada.get(i).getTProyecto().getCodigo().equals(this.codigoSolicitud)) {
                estaCooperativa = this.listaEntidadProyectosFiltrada.get(i).getTProyecto().getTTipoCredito().getIdTipoCredito();
                this.listaEntidadProyectosReducida.add(this.listaEntidadProyectosFiltrada.get(i));
            }
        }

        this.listaClienteProyectoFiltrada = this.iclienteProyectoBo.listTClienteProyectoResolucion(0);
        for (int i = 0; i < this.listaClienteProyectoFiltrada.size(); i++) {
            if (this.listaClienteProyectoFiltrada.get(i).getTProyecto().getCodigo().equals(this.codigoSolicitud)) {
                estaPersona = listaClienteProyectoFiltrada.get(i).getTProyecto().getTTipoCredito().getIdTipoCredito();
                this.listaClienteProyectoReducida.add(listaClienteProyectoFiltrada.get(i));
            }
        }
        if (estaCooperativa == 2 || estaCooperativa == 3) {
            this.mostrarCooperativas = true;
            this.mostrarPersonas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarLisiados = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = false;
        }
        if (estaPersona == 8) {
            this.mostrarAgropecuarios = true;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarLisiados = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = false;
        }
        if (estaPersona == 6) {
            this.mostrarPersonas = true;
            this.mostrarCooperativas = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = false;
        }
        if (estaPersona == 1) {
            this.mostrarCooperativas = false;
            this.mostrarPersonas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarLisiados = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = true;
        }
        if (estaPersona == 5) {
            this.mostrarLisiados = true;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = false;
        }
        if (estaPersona == 7) {
            this.mostrarLisiados = false;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = true;
        }

    }

    public void cargarVistaCooperativa() {

        this.trabajo = new TTrabajo();
        this.lisiados = new TLisiado();
        this.garantiaSeleccionada = new TGarantia();
        this.cooperativaSeleccionada = new TCooperativa();
        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        this.tipoCred = this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito();
        this.cooperativaSeleccionada = this.icooperativaBo.getTCooperativa(this.proyectoSeleccionado.getIdproyecto());
        this.garantiaSeleccionada = this.igarantiaBo.getTGarantia(this.proyectoSeleccionado.getIdproyecto());
        this.totalBalanceActivo = BigDecimal.ZERO;
        this.totalBalancePasivo = BigDecimal.ZERO;
        this.TotalBalancePatrimonio = BigDecimal.ZERO;
        this.totalBalanceActivo = this.cooperativaSeleccionada.getActivoCorriente().add(this.cooperativaSeleccionada.getActivoNocorriente());
        this.totalBalancePasivo = this.cooperativaSeleccionada.getPasivoCorriente().add(this.cooperativaSeleccionada.getPasivoNocorriente());
        this.TotalBalancePatrimonio = this.cooperativaSeleccionada.getPatrimonio();
        if (this.garantiaSeleccionada.getTTipoGarantia().getIdtipogarantia() == 1) {
            this.mostrarGarantiaSolidaria = false;
            this.mostrarGarantiaHipotecaria = true;
            this.mostrarGarantiaPrendaria = false;
        } else if (this.garantiaSeleccionada.getTTipoGarantia().getIdtipogarantia() == 2) {
            this.mostrarGarantiaSolidaria = true;
            this.mostrarGarantiaHipotecaria = false;
            this.mostrarGarantiaPrendaria = false;
        } else {
            this.mostrarGarantiaSolidaria = false;
            this.mostrarGarantiaHipotecaria = false;
            this.mostrarGarantiaPrendaria = true;
        }
        calculosVista();

    }

    public void cargarVistaLisiado() {

        this.trabajo = new TTrabajo();
        this.lisiados = new TLisiado();
        this.garantiaSeleccionada = new TGarantia();
        this.cooperativaSeleccionada = new TCooperativa();
        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        this.tipoCred = this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito();
        this.tipoCred = this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito();
        this.lisiados = this.ilisiadoBo.getLisiado(this.clienteSeleccionado.getIdCliente());
        this.listaPropiedadLisiado = this.ipropiedadLisiadoBo.listTPropiedadLisiado(this.clienteSeleccionado.getIdCliente());
        this.listaReferencia = this.ireferenciaBo.listReferencia(this.proyectoSeleccionado.getIdproyecto());
        this.listaIngresoSeleccionada = this.iingresoBo.listIngreso(this.proyectoSeleccionado.getIdproyecto());
        this.totalIngreso = BigDecimal.ZERO;
        for (int i = 0; i < this.listaIngresoSeleccionada.size(); i++) {
            this.totalIngreso = this.totalIngreso.add(this.listaIngresoSeleccionada.get(i).getValor());
        }
        this.listaEgresoSeleccionada = this.iegresoBo.listEgreso(this.proyectoSeleccionado.getIdproyecto());
        this.totalEgreso = BigDecimal.ZERO;
        for (int i = 0; i < this.listaEgresoSeleccionada.size(); i++) {
            this.totalEgreso = this.totalEgreso.add(this.listaEgresoSeleccionada.get(i).getValor());
        }
        this.garantiaSeleccionada = this.igarantiaBo.getTGarantia(this.proyectoSeleccionado.getIdproyecto());
        if (this.garantiaSeleccionada.getTTipoGarantia().getIdtipogarantia() == 1) {
            this.mostrarGarantiaHipotecaria = true;
        } else if (this.garantiaSeleccionada.getTTipoGarantia().getIdtipogarantia() == 2) {
            this.mostrarGarantiaSolidaria = true;
        } else {
            this.mostrarGarantiaPrendaria = true;
        }
        this.listaReferenciaSeleccionada = this.ireferenciaBo.listReferencia(this.proyectoSeleccionado.getIdproyecto());
        calcularEdad();
        calculosVista();

    }

    public void cargarVistaAgropecuario() {

        this.trabajo = new TTrabajo();
        this.lisiados = new TLisiado();
        this.garantiaSeleccionada = new TGarantia();
        this.cooperativaSeleccionada = new TCooperativa();
        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        this.tipoCred = this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito();
        this.listaIngresoSeleccionada = this.iingresoBo.listIngreso(this.proyectoSeleccionado.getIdproyecto());
        this.totalIngreso = BigDecimal.ZERO;
        this.totalEgreso = BigDecimal.ZERO;
        for (int i = 0; i < this.listaIngresoSeleccionada.size(); i++) {
            this.totalIngreso = this.totalIngreso.add(this.listaIngresoSeleccionada.get(i).getValor());
        }
        this.listaEgresoSeleccionada = this.iegresoBo.listEgreso(this.proyectoSeleccionado.getIdproyecto());
        for (int i = 0; i < this.listaEgresoSeleccionada.size(); i++) {
            this.totalEgreso = this.totalEgreso.add(this.listaEgresoSeleccionada.get(i).getValor());
        }
        this.listaActivoSeleccionada = this.iactivoBo.listActivo(this.proyectoSeleccionado.getIdproyecto());
        this.totalActivo = BigDecimal.ZERO;
        for (int i = 0; i < this.listaActivoSeleccionada.size(); i++) {
            this.totalActivo = this.totalActivo.add(this.listaActivoSeleccionada.get(i).getValor());
        }
        this.listaReferenciaSeleccionada = this.ireferenciaBo.listReferencia(this.proyectoSeleccionado.getIdproyecto());
        this.garantiaSeleccionada = this.igarantiaBo.getTGarantia(this.proyectoSeleccionado.getIdproyecto());
        if (this.garantiaSeleccionada.getTTipoGarantia().getIdtipogarantia() == 1) {
            this.mostrarGarantiaSolidaria = false;
            this.mostrarGarantiaHipotecaria = true;
            this.mostrarGarantiaPrendaria = false;
        } else if (this.garantiaSeleccionada.getTTipoGarantia().getIdtipogarantia() == 2) {
            this.mostrarGarantiaSolidaria = true;
            this.mostrarGarantiaHipotecaria = false;
            this.mostrarGarantiaPrendaria = false;
        } else {
            this.mostrarGarantiaSolidaria = false;
            this.mostrarGarantiaHipotecaria = false;
            this.mostrarGarantiaPrendaria = true;
        }
        calcularEdad();
        calculosVista();

    }

    public void calculosVista() {

        BigDecimal cal;
        this.modalidad = cambiar(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        cambiarFormaPago();
        this.monto = this.proyectoSeleccionado.getMonto();
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            this.calcularInteres();
            Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
            this.couto = new BigDecimal(cuota);
            this.total = this.intereses.add(this.monto);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            this.calcularInteres();
            Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
            this.couto = new BigDecimal(cuota * 3);
            this.total = this.intereses.add(this.monto);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            this.calcularInteres();
            Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
            this.couto = new BigDecimal(cuota * 6);
            this.total = this.intereses.add(this.monto);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            this.calcularInteres();
            Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
            this.couto = new BigDecimal(cuota * 12);
            this.total = this.intereses.add(this.monto);
        } else {

        }

    }

    public void cambiarFormaPago() {

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            this.formaPagos = "Mensual";
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            this.formaPagos = "Trimestral";
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            this.formaPagos = "Semestral";
        } else {
            this.formaPagos = "Anual";
        }

    }

    public void cargarVistaComercio() {

        this.trabajo = new TTrabajo();
        this.lisiados = new TLisiado();
        this.garantiaSeleccionada = new TGarantia();
        this.cooperativaSeleccionada = new TCooperativa();
        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        this.tipoCred = this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito();
        this.comercio = this.icomercioBo.getComercio(this.proyectoSeleccionado.getIdproyecto());
        this.listaIngresoSeleccionada = this.iingresoBo.listIngreso(this.proyectoSeleccionado.getIdproyecto());
        this.totalIngreso = BigDecimal.ZERO;
        for (int i = 0; i < this.listaIngresoSeleccionada.size(); i++) {
            this.totalIngreso = this.totalIngreso.add(this.listaIngresoSeleccionada.get(i).getValor());
        }
        this.listaEgresoSeleccionada = this.iegresoBo.listEgreso(this.proyectoSeleccionado.getIdproyecto());
        this.totalEgreso = BigDecimal.ZERO;
        for (int i = 0; i < this.listaEgresoSeleccionada.size(); i++) {
            this.totalEgreso = this.totalEgreso.add(this.listaEgresoSeleccionada.get(i).getValor());
        }
        this.listaActivoSeleccionada = this.iactivoBo.listActivo(this.proyectoSeleccionado.getIdproyecto());
        this.totalActivo = BigDecimal.ZERO;
        for (int i = 0; i < this.listaActivoSeleccionada.size(); i++) {
            this.totalActivo = this.totalActivo.add(this.listaActivoSeleccionada.get(i).getValor());
        }
        this.listaReferenciaSeleccionada = this.ireferenciaBo.listReferencia(this.proyectoSeleccionado.getIdproyecto());
        this.garantiaSeleccionada = this.igarantiaBo.getTGarantia(this.proyectoSeleccionado.getIdproyecto());
        if (this.garantiaSeleccionada.getTTipoGarantia().getIdtipogarantia() == 1) {
            this.mostrarGarantiaSolidaria = false;
            this.mostrarGarantiaHipotecaria = true;
            this.mostrarGarantiaPrendaria = false;
        } else if (this.garantiaSeleccionada.getTTipoGarantia().getIdtipogarantia() == 2) {
            this.mostrarGarantiaSolidaria = true;
            this.mostrarGarantiaHipotecaria = false;
            this.mostrarGarantiaPrendaria = false;
        } else {
            this.mostrarGarantiaSolidaria = false;
            this.mostrarGarantiaHipotecaria = false;
            this.mostrarGarantiaPrendaria = true;
        }
        calcularEdad();
        calculosVista();

    }

    public void cargarVistaEmpleado() {

        this.trabajo = new TTrabajo();
        this.lisiados = new TLisiado();
        this.garantiaSeleccionada = new TGarantia();
        this.cooperativaSeleccionada = new TCooperativa();
        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        this.tipoCred = this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito();
        this.trabajo = this.itrabajoBo.getTrabajo(this.clienteSeleccionado.getIdCliente());
        this.listaIngresoSeleccionada = this.iingresoBo.listIngreso(this.proyectoSeleccionado.getIdproyecto());
        this.totalIngreso = BigDecimal.ZERO;
        for (int i = 0; i < this.listaIngresoSeleccionada.size(); i++) {
            this.totalIngreso = this.totalIngreso.add(this.listaIngresoSeleccionada.get(i).getValor());
        }
        this.listaEgresoSeleccionada = this.iegresoBo.listEgreso(this.proyectoSeleccionado.getIdproyecto());
        this.totalEgreso = BigDecimal.ZERO;
        for (int i = 0; i < this.listaEgresoSeleccionada.size(); i++) {
            this.totalEgreso = this.totalEgreso.add(this.listaEgresoSeleccionada.get(i).getValor());
        }
        this.listaActivoSeleccionada = this.iactivoBo.listActivo(this.proyectoSeleccionado.getIdproyecto());
        this.totalActivo = BigDecimal.ZERO;
        for (int i = 0; i < this.listaActivoSeleccionada.size(); i++) {
            this.totalActivo = this.totalActivo.add(this.listaActivoSeleccionada.get(i).getValor());
        }
        this.listaReferenciaSeleccionada = this.ireferenciaBo.listReferencia(this.proyectoSeleccionado.getIdproyecto());
        this.garantiaSeleccionada = this.igarantiaBo.getTGarantia(this.proyectoSeleccionado.getIdproyecto());
        if (this.garantiaSeleccionada.getTTipoGarantia().getIdtipogarantia() == 1) {
            this.mostrarGarantiaSolidaria = false;
            this.mostrarGarantiaHipotecaria = true;
            this.mostrarGarantiaPrendaria = false;
        } else if (this.garantiaSeleccionada.getTTipoGarantia().getIdtipogarantia() == 2) {
            this.mostrarGarantiaSolidaria = true;
            this.mostrarGarantiaHipotecaria = false;
            this.mostrarGarantiaPrendaria = false;
        } else {
            this.mostrarGarantiaSolidaria = false;
            this.mostrarGarantiaHipotecaria = false;
            this.mostrarGarantiaPrendaria = true;
        }
        calcularEdad();
        calculosVista();

    }

    public void verReporteSolicitudComercio() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("cuota", Double.parseDouble("" + this.couto));
        parametros.put("interes", Double.parseDouble("" + this.intereses));
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudComercio.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudComercio.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudComercioH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudComercioS.jasper"));
        }
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        System.out.println(bytes.length);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
           TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar reporte de solicitud para crédito de comercio");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }
    
     public void verReporteSolicitudComercioPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("cuota", Double.parseDouble("" + this.couto));
        parametros.put("interes", Double.parseDouble("" + this.intereses));
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudComercio.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudComercio.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudComercioH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudComercioS.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Solicitud de crédito para comercio.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
           TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de solicitud para crédito de comercio");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void verReporteSolicitudAgropecuario() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("Interes", Double.parseDouble("" + this.intereses));
        parametros.put("cuota", Double.parseDouble("" + this.couto));
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudAgropecuario.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudAgropecuario.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudAgropecuarioH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudAgropecuarioS.jasper"));
        }
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        System.out.println(bytes.length);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
           TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar reporte de solicitud para crédito agropecuario");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }
    
    public void verReporteSolicitudAgropecuarioPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("Interes", Double.parseDouble("" + this.intereses));
        parametros.put("cuota", Double.parseDouble("" + this.couto));
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudAgropecuario.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudAgropecuario.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudAgropecuarioH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudAgropecuarioS.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Solicitud de crédito agropecuario.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
           TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de solicitud para crédito agropecuario");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }
    
    

    public void verReporteSolicitudCooperativa() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("cuota", Double.parseDouble("" + this.couto));
        parametros.put("interes", Double.parseDouble("" + this.intereses));
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudCooperativa.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudCooperativa.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudCooperativaH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudCooperativaS.jasper"));
        }
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        System.out.println(bytes.length);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
           TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar reporte de solicitud para crédito de cooperativa");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }
    
     public void verReporteSolicitudCooperativaPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("cuota", Double.parseDouble("" + this.couto));
        parametros.put("interes", Double.parseDouble("" + this.intereses));
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudCooperativa.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudCooperativa.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudCooperativaH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudCooperativaS.jasper"));
        }
         JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Solicitud de crédito para cooperativa.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
           TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de solicitud para crédito de cooperativa");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }
    

    public void verReporteSolicitudLisiado() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("cuota", Double.parseDouble("" + this.couto));
        parametros.put("interes", Double.parseDouble("" + this.intereses));
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudLisiado.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudLisiado.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudLisiadoH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudLisiadoS.jasper"));
        }
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        System.out.println(bytes.length);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
           TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar reporte de solicitud para crédito de lisiado");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void verReporteSolicitudLisiadoPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("cuota", Double.parseDouble("" + this.couto));
        parametros.put("interes", Double.parseDouble("" + this.intereses));
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudLisiado.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudLisiado.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudLisiadoH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudLisiadoS.jasper"));
        }
       JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Solicitud de crédito para lisiado.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
           TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de solicitud para crédito de lisiado");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    
    public void verReporteSolicitudEmpleado() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("cuota", Double.parseDouble("" + this.couto));
        parametros.put("interes", Double.parseDouble("" + this.intereses));
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudEmpleado.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudEmpleado.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudEmpleadoH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudEmpleadoS.jasper"));
        }
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        System.out.println(bytes.length);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
           TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar reporte de solicitud de crédito para empleados");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }
    
    public void verReporteSolicitudEmpleadoPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("cuota", Double.parseDouble("" + this.couto));
        parametros.put("interes", Double.parseDouble("" + this.intereses));
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudEmpleado.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudEmpleado.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudEmpleadoH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudEmpleadoS.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Solicitud de crédito para empleado.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de solicitud de crédito para empleados");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void calcularInteres() {

        BigDecimal interesAnual;
        BigDecimal interesMensual;
        this.intereses = BigDecimal.ZERO;
        this.monto = this.proyectoSeleccionado.getMonto();
        Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
        Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
        this.couto = new BigDecimal(cuota);
        BigDecimal interesA;
        BigDecimal abonoA = new BigDecimal("0");
        BigDecimal capital = this.proyectoSeleccionado.getMonto();
        BigDecimal capitalA = new BigDecimal("0");
        this.listaPagos = new ArrayList<TPago>();
        for (int i = 0; i < this.proyectoSeleccionado.getPlazo(); i++) {
            this.pago = new TPago();
            interesA = (capital.multiply((this.politicaSeleccionada.getTasaInteres().divide(new BigDecimal("100")).multiply(new BigDecimal(this.proyectoSeleccionado.getPlazo())))));
            interesAnual = interesA.divide(new BigDecimal(this.proyectoSeleccionado.getPlazo()), 5, RoundingMode.HALF_UP);
            interesMensual = interesAnual.divide(new BigDecimal("12"), 5, RoundingMode.HALF_UP);
            this.intereses = this.intereses.add(interesMensual);
            abonoA = this.couto.subtract(interesMensual);
            capital = capital.subtract(abonoA);
            BigDecimal diferencia;
            capitalA = capitalA.add(abonoA);
            if (i == (this.proyectoSeleccionado.getPlazo() - 1)) {
                if (capital.compareTo(new BigDecimal(0)) < 0) {
                    diferencia = capital.subtract(new BigDecimal("0"));
                    this.intereses = this.intereses.subtract(diferencia);
                } else if (capital.compareTo(new BigDecimal(0)) > 0) {
                    diferencia = capital.subtract(new BigDecimal("0"));
                    abonoA = abonoA.subtract(diferencia);
                    capital = new BigDecimal("0");
                    capitalA = capitalA.add(abonoA);
                } else {
                }
            }
        }
    }

    public void generarDui() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/documentoDui.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        System.out.println(bytes.length);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
           TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar DUI");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarNit() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/documentoNit.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        System.out.println(bytes.length);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
           TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar NIT");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarDuiFiador() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/documentoDuiFiador.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        System.out.println(bytes.length);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
           TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar DUI de fiador");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

        

    }

    public void generarNitFiador() throws SQLException, JRException, IOException {

       this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/documentoNitFiador.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        System.out.println(bytes.length);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
          TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar NIT de fiador");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void generarCarta() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/documentoCarta.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        System.out.println(bytes.length);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
          TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar carta de fondo de lisiado");

        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.clienteSeleccionado.getIdCliente());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void generarConstancia() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/documentoConstancia.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        System.out.println(bytes.length);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
               TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar constancia de trabajo");
        
        String formaPagosCredito="";

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }

        
        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto: " + this.proyectoSeleccionado.getMonto()
                + ", Plazo: " + this.proyectoSeleccionado.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void calcularEdad() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        Date fechaNac = null;
        String nueva = "";
        int año = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse("" + sdf.format(this.clienteSeleccionado.getFechaNacimiento()));
        } catch (ParseException ex) {
            Logger.getLogger(CompradorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        nueva = (formatter.format(fechaNac));
        Calendar fechaActual = Calendar.getInstance();
        fechaNacimiento.setTime(fechaNac);
        año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        if (mes < 0 || (mes == 0 && dia < 0)) {
            año--;
        }
        this.edad = año;
    }

}
