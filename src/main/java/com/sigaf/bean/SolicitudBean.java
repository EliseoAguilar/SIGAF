/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.sigaf.pojo.TTipoGarantia;
import com.sigaf.pojo.TTrabajo;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
@Named(value = "solicitudBean")
@SessionScoped
@ManagedBean

public class SolicitudBean extends Actividad {

    ILisiadoBo ilisiadoBo;
    IGarantiaProBo igarantiaProBo;
    IPropiedadLisiadoBo ipropiedadLisiadoBo;
    IComercioBo icomercioBo;
    private List<TClienteProyecto> listaCliente;
    private List<TPropiedadLisiado> listaPropiedadLisiado;
    private Boolean tablaAmortizacion;
    private BigDecimal cuotaPago;
    private Integer idDepartamento;
    private TLisiado lisiados;
    private TCliente cliente;
    private TIngreso ingresos;
    private TEgreso egresos;
    private TGarantia garantias;
    private TReferencia referencias;
    private TPropiedadLisiado propiedadLisiado;
    private TComercio comercio;
    private Integer idGarantia;
    private Integer idMunicipio;
    private String posesiones;
    private List<String> listaPosesiones;
    private List<TEgreso> listaEgresos;
    private Boolean banderaEgreso;
    private boolean showImagen;
    private boolean showImagenCarta;
    private boolean ocultar;
    private boolean ocultarEgresos;
    private Boolean nuevo;
    private Boolean existente;
    private Boolean estadoHabilitar;
    private Boolean mostrarResumen;
    private Boolean mostrarEmpleados;
    private Boolean mostrarCrearEmpleado;
    private List<TClienteProyecto> listaClienteProyectoComercio;
    private List<TClienteProyecto> listaClienteProyectoAgropecuario;
    private List<TEntidadProyecto> listaClienteProyectoCooperativa;
    private List<TClienteProyecto> listaClienteProyectoLisiados;
    private List<TClienteProyecto> listaClienteProyectoEmpleados;
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
    private TComentario comentario;
    private TSugerencia sugerencia;
    private Integer formaPago;
    private String municipio;
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
    private Integer tipoResultado;
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
    private TEntidadProyecto entidadProyectoEliminar;
    private IEntidadBo ientidadBo;
    private ITrabajoBo itrabajoBo;
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
    private List<TIngreso> listaIngresoSeleccionada;
    private List<TEgreso> listaEgresoSeleccionada;
    private List<TGarantia> listaGarantiaSeleccionada;
    private List<TActivo> listaActivoSeleccionada;
    private List<TComentario> listaComentarios;
    private List<TSugerencia> listaSugerencias;
    private BigDecimal valorRedondeado;
    private BigDecimal valorRedondeadoIngreso;
    private BigDecimal valorRedondeadoEgreso;
    private BigDecimal valorRedondeadoActivo;
    private BigDecimal valorRedondeadoGarantia;
    private List<TClienteProyecto> listaClienteProyectos;
    private List<TCliente> listaClientes;
    private List<TClienteProyecto> listaClienteProyectoReducida;
    private List<TClienteProyecto> listaClienteProyectoFiltrada;
    private List<TEntidad> listaEntidades;
    private List<TEntidadProyecto> listaEntidadProyectos;
    private List<TEntidadProyecto> listaEntidadProyectosReducida;
    private List<TEntidadProyecto> listaEntidadProyectosFiltrada;
    private List<TEntidadProyecto> listaEntidadProyectosAprobadas;
    private List<TEntidadProyecto> listaEntidadProyectosNoAprobadas;
    private List<TClienteProyecto> listaClienteProyectoAprobadas;
    private List<TClienteProyecto> listaClienteProyectoNoAprobadas;
    private Boolean showConstancia;
    private TReferencia referenciaSeleccionada;
    private TReferencia referencia;
    private TTrabajo trabajo;
    private TActivo activo;
    private List<TActivo> listaActivos;
    private List<TIngreso> listaIngreso;
    private TIngreso ingreso;
    private List<TEgreso> listaEgreso;
    private TEgreso egreso;
    private List<TGarantia> listaGarantia;
    private TGarantia garantia;
    private TAgropecuario agropecuario;
    private TAgropecuario agropecuario2;
    private TClienteProyecto clienteProyecto;
    private List<TReferencia> listaReferencia;
    private List<TPago> listaPagos;
    private Boolean showNitCliente;
    private Boolean showDuiCliente;
    private Boolean showDuiFiador;
    private Boolean showNitFiador;
    private Boolean showRespaldoPrendaria;
    private String formaPagos;
    private TClienteProyecto clienteProyectoEliminar;
    private Boolean showPresupuesto;
    private Boolean showBalance;
    private Boolean showAprobacion;
    private Boolean mostrarTabla;
    private String codigoSolicitud;
    private TGarantia garantiaSeleccionada;
    private Boolean mostrarGarantiaPrendaria;
    private Boolean mostrarGarantiaSolidaria;
    private Boolean mostrarGarantiaHipotecaria;
    private Boolean mostrarResultadoCooperativas;
    private Boolean mostrarResultadoPersona;
    private String modalidad;
    private BigDecimal totalBalanceActivo;
    private BigDecimal totalBalancePasivo;
    private BigDecimal TotalBalancePatrimonio;
    private String msgConstanciaLisiado;
    private Boolean verDatosCooperativa;
    private Boolean verDatosLisiado;
    private Boolean verDatosAgropecuario;
    private Boolean verDatosEmpleado;
    private Boolean verDatosComercio;
    private Boolean VerResolucion;
    private IAreaBo iareaBo;
    private UploadedFile img;
    private Boolean estadoFormulario;
    private Integer tipoCredito;
    private Integer tipoCred;
    private String msgCooperativaPatrimonio;
    private String msgEntidad;
    private String msgCooperativaActivoCo;
    private String msgCooperativaActivoNo;
    private String msgCooperativaPasivoCo;
    private String msgCooperativaPasivoNo;
    private String msgGarantiaDescripcionPrendaria;
    private String msgGarantiaValorPrendaria;
    private String msgGarantiaTipoHipoteca;
    private String msgGarantiaValorHipoteca;
    private String msgGarantiaDescripcionHipoteca;
    private String msgNombreFiador;
    private String msgApellidosFiador;
    private String msgDuiFiador;
    private String msgNitFiador;
    private String msgProfesionFiador;
    private String msgLugarFiador;
    private String msgIngresosFiador;
    private String msgTelefonotFiador;
    private String msgTelefonofFiador;
    private String msgDescripcionProyecto;
    private String msgFormaPago;
    private String msgModalidad;
    private String msgLugar;
    private String msgFecha;
    private String msgNombreProyecto;
    private String msgArea;
    private String msgDestino;
    private String msgPlazo;
    private String msgMonto;
    private String msgBalance;
    private String msgPresupuesto;
    private String msgPrendaria;
    public String msgCopiaDui;
    public String msgCopiaNit;
    private String tipo;
    private BigDecimal valorRedondeadoP;
    private Integer edad;
    private TComercio comercioSeleccionado;
    private String msgActivoNombre;
    private String msgActivoValor;
    private String msgActivoDescripcion;
    private String msgIngresoNombre;
    private String msgIngresoValor;
    private String msgIngresoDescripcion;
    private String msgEgresoNombre;
    private String msgEgresoValor;
    private String msgEgresoDescripcion;
    private String msgReferenciaNombre;
    private String msgReferenciaNumero;
    private String msgNombreEmpresa;
    private String msgDireccionEmpresa;
    private String msgTelefonoEmpresa;
    private String msgCargoEmpresa;
    private String msgTiempoEmpresa;
    private String msgConstanciaEmpresa;
    private String msgMontoLisiado;
    private String msgLesion;
    private String msgApellidoConyugue;
    private String msgTelefonoConyugue;
    private String msgNombreConyugue;
    private String msgHijosConyugue;
    private String msgNombreMayor;
    private String msgApellidoMayor;
    private String msgEdadMayor;
    private String msgTelefonoMayor;
    private String msgMovilMayor;
    private String msgInstitucion;
    private String msgNombreComercio;
    private String msgRegistroComercio;
    private String msgTiempoComercio;
    private String msgEmpleadosComercio;
    private String msgTelefonoComercio;
    private String msgGiroComercio;
    private String msgDireccionComercio;
    private String msgClienteSeleccionado;
    private String msgFechaNacimiento;
    private String msgSexo;
    private String msgEstadoFamiliar;
    private String msgTelefono;
    private String msgMovil;
    private String msgDireccion;
    private String msgMunicipio;
    private String msgCorreo;
    private String msgIngreso;
    private String msgEgreso;
    private String msgActivo;
    private String msgReferencia;
    private String msgedadConyugue;
    private String msgActividad;
    private String msgtiempo;
    private String msgNitCopiaFiador;
    private String msgDuiCopiaFiador;
    private IBitacoraBo bitacoraBo;
    private Boolean estadoConyugue;
    private Boolean estadoHijos;
    private String msgEdad;

    public String getMsgEdad() {
        return msgEdad;
    }

    public void setMsgEdad(String msgEdad) {
        this.msgEdad = msgEdad;
    }
    private Date hoy;

    public Date getHoy() {
        return new Date();
    }

    public void setHoy(Date hoy) {
        this.hoy = hoy;
    }

    public Boolean getEstadoHijos() {
        return estadoHijos;
    }

    public void setEstadoHijos(Boolean estadoHijos) {
        this.estadoHijos = estadoHijos;
    }

    public Boolean getEstadoConyugue() {
        return estadoConyugue;
    }

    public void setEstadoConyugue(Boolean estadoConyugue) {
        this.estadoConyugue = estadoConyugue;
    }

    public String getMsgNitCopiaFiador() {
        return msgNitCopiaFiador;
    }

    public void setMsgNitCopiaFiador(String msgNitCopiaFiador) {
        this.msgNitCopiaFiador = msgNitCopiaFiador;
    }

    public String getMsgDuiCopiaFiador() {
        return msgDuiCopiaFiador;
    }

    public void setMsgDuiCopiaFiador(String msgDuiCopiaFiador) {
        this.msgDuiCopiaFiador = msgDuiCopiaFiador;
    }

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public IEntidadBo getIentidadBo() {
        return ientidadBo;
    }

    public void setIentidadBo(IEntidadBo ientidadBo) {
        this.ientidadBo = ientidadBo;
    }

    public BigDecimal getCuotaPago() {
        return cuotaPago;
    }

    public void setCuotaPago(BigDecimal cuotaPago) {
        this.cuotaPago = cuotaPago;
    }

    public IComercioBo getIcomercioBo() {
        return icomercioBo;
    }

    public void setIcomercioBo(IComercioBo icomercioBo) {
        this.icomercioBo = icomercioBo;
    }

    public Boolean getTablaAmortizacion() {
        return tablaAmortizacion;
    }

    public void setTablaAmortizacion(Boolean tablaAmortizacion) {
        this.tablaAmortizacion = tablaAmortizacion;
    }

    public TComercio getComercio() {
        return comercio;
    }

    public void setComercio(TComercio comercio) {
        this.comercio = comercio;
    }

    public Boolean getMostrarEmpleados() {
        return mostrarEmpleados;
    }

    public void setMostrarEmpleados(Boolean mostrarEmpleados) {
        this.mostrarEmpleados = mostrarEmpleados;
    }

    public Boolean getMostrarCrearEmpleado() {
        return mostrarCrearEmpleado;
    }

    public void setMostrarCrearEmpleado(Boolean mostrarCrearEmpleado) {
        this.mostrarCrearEmpleado = mostrarCrearEmpleado;
    }

    public List<TClienteProyecto> getListaClienteProyectoEmpleados() {
        return listaClienteProyectoEmpleados;
    }

    public void setListaClienteProyectoEmpleados(List<TClienteProyecto> listaClienteProyectoEmpleados) {
        this.listaClienteProyectoEmpleados = listaClienteProyectoEmpleados;
    }

    public List<TProyecto> getListaSolicitudesProyectos() {
        return listaSolicitudesProyectos;
    }

    public void setListaSolicitudesProyectos(List<TProyecto> listaSolicitudesProyectos) {
        this.listaSolicitudesProyectos = listaSolicitudesProyectos;
    }

    public Boolean getMostrarResumen() {
        return mostrarResumen;
    }

    public void setMostrarResumen(Boolean mostrarResumen) {
        this.mostrarResumen = mostrarResumen;
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

    public Boolean getEstadoHabilitar() {
        return estadoHabilitar;
    }

    public void setEstadoHabilitar(Boolean estadoHabilitar) {
        this.estadoHabilitar = estadoHabilitar;
    }

    public Boolean getNuevo() {
        return nuevo;
    }

    public void setNuevo(Boolean nuevo) {
        this.nuevo = nuevo;
    }

    public Boolean getExistente() {
        return existente;
    }

    public void setExistente(Boolean existente) {
        this.existente = existente;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Integer getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(Integer formaPago) {
        this.formaPago = formaPago;
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

    public Integer getTipoResultado() {
        return tipoResultado;
    }

    public void setTipoResultado(Integer tipoResultado) {
        this.tipoResultado = tipoResultado;
    }

    public Boolean getMostrarComercio() {
        return mostrarComercio;
    }

    public void setMostrarComercio(Boolean mostrarComercio) {
        this.mostrarComercio = mostrarComercio;
    }

    public TEntidadProyecto getEntidadProyectoEliminar() {
        return entidadProyectoEliminar;
    }

    public void setEntidadProyectoEliminar(TEntidadProyecto entidadProyectoEliminar) {
        this.entidadProyectoEliminar = entidadProyectoEliminar;
    }

    public ITrabajoBo getItrabajoBo() {
        return itrabajoBo;
    }

    public void setItrabajoBo(ITrabajoBo itrabajoBo) {
        this.itrabajoBo = itrabajoBo;
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

    public List<TCliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<TCliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<TComentario> getListaComentarios() {
        return listaComentarios;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setListaComentarios(List<TComentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public List<TSugerencia> getListaSugerencias() {
        return listaSugerencias;
    }

    public void setListaSugerencias(List<TSugerencia> listaSugerencias) {
        this.listaSugerencias = listaSugerencias;
    }

    public List<TClienteProyecto> getListaClienteProyectoAprobadas() {
        return listaClienteProyectoAprobadas;
    }

    public void setListaClienteProyectoAprobadas(List<TClienteProyecto> listaClienteProyectoAprobadas) {
        this.listaClienteProyectoAprobadas = listaClienteProyectoAprobadas;
    }

    public List<TClienteProyecto> getListaClienteProyectoNoAprobadas() {
        return listaClienteProyectoNoAprobadas;
    }

    public void setListaClienteProyectoNoAprobadas(List<TClienteProyecto> listaClienteProyectoNoAprobadas) {
        this.listaClienteProyectoNoAprobadas = listaClienteProyectoNoAprobadas;
    }

    public List<TEntidadProyecto> getListaEntidadProyectosAprobadas() {
        return listaEntidadProyectosAprobadas;
    }

    public void setListaEntidadProyectosAprobadas(List<TEntidadProyecto> listaEntidadProyectosAprobadas) {
        this.listaEntidadProyectosAprobadas = listaEntidadProyectosAprobadas;
    }

    public List<TEntidadProyecto> getListaEntidadProyectosNoAprobadas() {
        return listaEntidadProyectosNoAprobadas;
    }

    public void setListaEntidadProyectosNoAprobadas(List<TEntidadProyecto> listaEntidadProyectosNoAprobadas) {
        this.listaEntidadProyectosNoAprobadas = listaEntidadProyectosNoAprobadas;
    }

    public TTrabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(TTrabajo trabajo) {
        this.trabajo = trabajo;
    }

    public Boolean getShowConstancia() {
        return showConstancia;
    }

    public void setShowConstancia(Boolean showConstancia) {
        this.showConstancia = showConstancia;
    }

    public TAgropecuario getAgropecuario2() {
        return agropecuario2;
    }

    public void setAgropecuario2(TAgropecuario agropecuario2) {
        this.agropecuario2 = agropecuario2;
    }

    public TClienteProyecto getClienteProyectoEliminar() {
        return clienteProyectoEliminar;
    }

    public void setClienteProyectoEliminar(TClienteProyecto clienteProyectoEliminar) {
        this.clienteProyectoEliminar = clienteProyectoEliminar;
    }

    public String getFormaPagos() {
        return formaPagos;
    }

    public void setFormaPagos(String formaPagos) {
        this.formaPagos = formaPagos;
    }

    public Boolean getShowRespaldoPrendaria() {
        return showRespaldoPrendaria;
    }

    public void setShowRespaldoPrendaria(Boolean showRespaldoPrendaria) {
        this.showRespaldoPrendaria = showRespaldoPrendaria;
    }

    public Boolean getShowNitFiador() {
        return showNitFiador;
    }

    public void setShowNitFiador(Boolean showNitFiador) {
        this.showNitFiador = showNitFiador;
    }

    public Boolean getShowDuiFiador() {
        return showDuiFiador;
    }

    public void setShowDuiFiador(Boolean showDuiFiador) {
        this.showDuiFiador = showDuiFiador;
    }

    public Boolean getShowNitCliente() {
        return showNitCliente;
    }

    public void setShowNitCliente(Boolean showNitCliente) {
        this.showNitCliente = showNitCliente;
    }

    public Boolean getShowDuiCliente() {
        return showDuiCliente;
    }

    public void setShowDuiCliente(Boolean showDuiCliente) {
        this.showDuiCliente = showDuiCliente;
    }

    public Boolean getVerResolucion() {
        return VerResolucion;
    }

    public void setVerResolucion(Boolean VerResolucion) {
        this.VerResolucion = VerResolucion;
    }

    public Boolean getVerDatosCooperativa() {
        return verDatosCooperativa;
    }

    public void setVerDatosCooperativa(Boolean verDatosCooperativa) {
        this.verDatosCooperativa = verDatosCooperativa;
    }

    public Boolean getVerDatosLisiado() {
        return verDatosLisiado;
    }

    public void setVerDatosLisiado(Boolean verDatosLisiado) {
        this.verDatosLisiado = verDatosLisiado;
    }

    public Boolean getVerDatosAgropecuario() {
        return verDatosAgropecuario;
    }

    public void setVerDatosAgropecuario(Boolean verDatosAgropecuario) {
        this.verDatosAgropecuario = verDatosAgropecuario;
    }

    public Boolean getVerDatosEmpleado() {
        return verDatosEmpleado;
    }

    public void setVerDatosEmpleado(Boolean verDatosEmpleado) {
        this.verDatosEmpleado = verDatosEmpleado;
    }

    public Boolean getVerDatosComercio() {
        return verDatosComercio;
    }

    public void setVerDatosComercio(Boolean verDatosComercio) {
        this.verDatosComercio = verDatosComercio;
    }

    public String getMsgConstanciaLisiado() {
        return msgConstanciaLisiado;
    }

    public void setMsgConstanciaLisiado(String msgConstanciaLisiado) {
        this.msgConstanciaLisiado = msgConstanciaLisiado;
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

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Boolean getMostrarResultadoPersona() {
        return mostrarResultadoPersona;
    }

    public void setMostrarResultadoPersona(Boolean mostrarResultadoPersona) {
        this.mostrarResultadoPersona = mostrarResultadoPersona;
    }

    public Boolean getMostrarResultadoCooperativas() {
        return mostrarResultadoCooperativas;
    }

    public void setMostrarResultadoCooperativas(Boolean mostrarResultadoCooperativas) {
        this.mostrarResultadoCooperativas = mostrarResultadoCooperativas;
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

    public TGarantia getGarantiaSeleccionada() {
        return garantiaSeleccionada;
    }

    public void setGarantiaSeleccionada(TGarantia garantiaSeleccionada) {
        this.garantiaSeleccionada = garantiaSeleccionada;
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

    public Boolean getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
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

    public String getMsgBalance() {
        return msgBalance;
    }

    public void setMsgBalance(String msgBalance) {
        this.msgBalance = msgBalance;
    }

    public String getMsgPresupuesto() {
        return msgPresupuesto;
    }

    public void setMsgPresupuesto(String msgPresupuesto) {
        this.msgPresupuesto = msgPresupuesto;
    }

    public String getMsgCooperativaPatrimonio() {
        return msgCooperativaPatrimonio;
    }

    public void setMsgCooperativaPatrimonio(String msgCooperativaPatrimonio) {
        this.msgCooperativaPatrimonio = msgCooperativaPatrimonio;
    }

    public String getMsgEntidad() {
        return msgEntidad;
    }

    public void setMsgEntidad(String msgEntidad) {
        this.msgEntidad = msgEntidad;
    }

    public String getMsgCooperativaActivoCo() {
        return msgCooperativaActivoCo;
    }

    public void setMsgCooperativaActivoCo(String msgCooperativaActivoCo) {
        this.msgCooperativaActivoCo = msgCooperativaActivoCo;
    }

    public String getMsgCooperativaActivoNo() {
        return msgCooperativaActivoNo;
    }

    public void setMsgCooperativaActivoNo(String msgCooperativaActivoNo) {
        this.msgCooperativaActivoNo = msgCooperativaActivoNo;
    }

    public String getMsgCooperativaPasivoCo() {
        return msgCooperativaPasivoCo;
    }

    public void setMsgCooperativaPasivoCo(String msgCooperativaPasivoCo) {
        this.msgCooperativaPasivoCo = msgCooperativaPasivoCo;
    }

    public String getMsgCooperativaPasivoNo() {
        return msgCooperativaPasivoNo;
    }

    public void setMsgCooperativaPasivoNo(String msgCooperativaPasivoNo) {
        this.msgCooperativaPasivoNo = msgCooperativaPasivoNo;
    }

    public String getMsgGarantiaDescripcionPrendaria() {
        return msgGarantiaDescripcionPrendaria;
    }

    public void setMsgGarantiaDescripcionPrendaria(String msgGarantiaDescripcionPrendaria) {
        this.msgGarantiaDescripcionPrendaria = msgGarantiaDescripcionPrendaria;
    }

    public String getMsgGarantiaValorPrendaria() {
        return msgGarantiaValorPrendaria;
    }

    public void setMsgGarantiaValorPrendaria(String msgGarantiaValorPrendaria) {
        this.msgGarantiaValorPrendaria = msgGarantiaValorPrendaria;
    }

    public String getMsgGarantiaTipoHipoteca() {
        return msgGarantiaTipoHipoteca;
    }

    public void setMsgGarantiaTipoHipoteca(String msgGarantiaTipoHipoteca) {
        this.msgGarantiaTipoHipoteca = msgGarantiaTipoHipoteca;
    }

    public String getMsgGarantiaValorHipoteca() {
        return msgGarantiaValorHipoteca;
    }

    public void setMsgGarantiaValorHipoteca(String msgGarantiaValorHipoteca) {
        this.msgGarantiaValorHipoteca = msgGarantiaValorHipoteca;
    }

    public String getMsgGarantiaDescripcionHipoteca() {
        return msgGarantiaDescripcionHipoteca;
    }

    public void setMsgGarantiaDescripcionHipoteca(String msgGarantiaDescripcionHipoteca) {
        this.msgGarantiaDescripcionHipoteca = msgGarantiaDescripcionHipoteca;
    }

    public String getMsgNombreFiador() {
        return msgNombreFiador;
    }

    public void setMsgNombreFiador(String msgNombreFiador) {
        this.msgNombreFiador = msgNombreFiador;
    }

    public String getMsgApellidosFiador() {
        return msgApellidosFiador;
    }

    public void setMsgApellidosFiador(String msgApellidosFiador) {
        this.msgApellidosFiador = msgApellidosFiador;
    }

    public String getMsgDuiFiador() {
        return msgDuiFiador;
    }

    public void setMsgDuiFiador(String msgDuiFiador) {
        this.msgDuiFiador = msgDuiFiador;
    }

    public String getMsgNitFiador() {
        return msgNitFiador;
    }

    public void setMsgNitFiador(String msgNitFiador) {
        this.msgNitFiador = msgNitFiador;
    }

    public String getMsgProfesionFiador() {
        return msgProfesionFiador;
    }

    public void setMsgProfesionFiador(String msgProfesionFiador) {
        this.msgProfesionFiador = msgProfesionFiador;
    }

    public String getMsgLugarFiador() {
        return msgLugarFiador;
    }

    public void setMsgLugarFiador(String msgLugarFiador) {
        this.msgLugarFiador = msgLugarFiador;
    }

    public String getMsgIngresosFiador() {
        return msgIngresosFiador;
    }

    public void setMsgIngresosFiador(String msgIngresosFiador) {
        this.msgIngresosFiador = msgIngresosFiador;
    }

    public String getMsgTelefonotFiador() {
        return msgTelefonotFiador;
    }

    public void setMsgTelefonotFiador(String msgTelefonotFiador) {
        this.msgTelefonotFiador = msgTelefonotFiador;
    }

    public String getMsgTelefonofFiador() {
        return msgTelefonofFiador;
    }

    public void setMsgTelefonofFiador(String msgTelefonofFiador) {
        this.msgTelefonofFiador = msgTelefonofFiador;
    }

    public String getMsgDescripcionProyecto() {
        return msgDescripcionProyecto;
    }

    public void setMsgDescripcionProyecto(String msgDescripcionProyecto) {
        this.msgDescripcionProyecto = msgDescripcionProyecto;
    }

    public String getMsgFormaPago() {
        return msgFormaPago;
    }

    public void setMsgFormaPago(String msgFormaPago) {
        this.msgFormaPago = msgFormaPago;
    }

    public String getMsgModalidad() {
        return msgModalidad;
    }

    public void setMsgModalidad(String msgModalidad) {
        this.msgModalidad = msgModalidad;
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

    public String getMsgNombreProyecto() {
        return msgNombreProyecto;
    }

    public void setMsgNombreProyecto(String msgNombreProyecto) {
        this.msgNombreProyecto = msgNombreProyecto;
    }

    public String getMsgArea() {
        return msgArea;
    }

    public void setMsgArea(String msgArea) {
        this.msgArea = msgArea;
    }

    public String getMsgDestino() {
        return msgDestino;
    }

    public void setMsgDestino(String msgDestino) {
        this.msgDestino = msgDestino;
    }

    public String getMsgPlazo() {
        return msgPlazo;
    }

    public void setMsgPlazo(String msgPlazo) {
        this.msgPlazo = msgPlazo;
    }

    public String getMsgMonto() {
        return msgMonto;
    }

    public void setMsgMonto(String msgMonto) {
        this.msgMonto = msgMonto;
    }

    public String getMsgPrendaria() {
        return msgPrendaria;
    }

    public void setMsgPrendaria(String msgPrendaria) {
        this.msgPrendaria = msgPrendaria;
    }

    public String getMsgCopiaDui() {
        return msgCopiaDui;
    }

    public void setMsgCopiaDui(String msgCopiaDui) {
        this.msgCopiaDui = msgCopiaDui;
    }

    public String getMsgCopiaNit() {
        return msgCopiaNit;
    }

    public void setMsgCopiaNit(String msgCopiaNit) {
        this.msgCopiaNit = msgCopiaNit;
    }

    public String getMsgActividad() {
        return msgActividad;
    }

    public void setMsgActividad(String msgActividad) {
        this.msgActividad = msgActividad;
    }

    public String getMsgtiempo() {
        return msgtiempo;
    }

    public void setMsgtiempo(String msgtiempo) {
        this.msgtiempo = msgtiempo;
    }

    public String getMsgedadConyugue() {
        return msgedadConyugue;
    }

    public void setMsgedadConyugue(String msgedadConyugue) {
        this.msgedadConyugue = msgedadConyugue;
    }

    public TComercio getComercioSeleccionado() {
        return comercioSeleccionado;
    }

    public void setComercioSeleccionado(TComercio comercioSeleccionado) {
        this.comercioSeleccionado = comercioSeleccionado;
    }

    public String getMsgClienteSeleccionado() {
        return msgClienteSeleccionado;
    }

    public void setMsgClienteSeleccionado(String msgClienteSeleccionado) {
        this.msgClienteSeleccionado = msgClienteSeleccionado;
    }

    public String getMsgFechaNacimiento() {
        return msgFechaNacimiento;
    }

    public void setMsgFechaNacimiento(String msgFechaNacimiento) {
        this.msgFechaNacimiento = msgFechaNacimiento;
    }

    public String getMsgSexo() {
        return msgSexo;
    }

    public void setMsgSexo(String msgSexo) {
        this.msgSexo = msgSexo;
    }

    public String getMsgEstadoFamiliar() {
        return msgEstadoFamiliar;
    }

    public void setMsgEstadoFamiliar(String msgEstadoFamiliar) {
        this.msgEstadoFamiliar = msgEstadoFamiliar;
    }

    public String getMsgTelefono() {
        return msgTelefono;
    }

    public void setMsgTelefono(String msgTelefono) {
        this.msgTelefono = msgTelefono;
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

    public String getMsgMovil() {
        return msgMovil;
    }

    public void setMsgMovil(String msgMovil) {
        this.msgMovil = msgMovil;
    }

    public String getMsgDireccion() {
        return msgDireccion;
    }

    public void setMsgDireccion(String msgDireccion) {
        this.msgDireccion = msgDireccion;
    }

    public String getMsgMunicipio() {
        return msgMunicipio;
    }

    public void setMsgMunicipio(String msgMunicipio) {
        this.msgMunicipio = msgMunicipio;
    }

    public String getMsgCorreo() {
        return msgCorreo;
    }

    public void setMsgCorreo(String msgCorreo) {
        this.msgCorreo = msgCorreo;
    }

    public String getMsgIngreso() {
        return msgIngreso;
    }

    public void setMsgIngreso(String msgIngreso) {
        this.msgIngreso = msgIngreso;
    }

    public String getMsgEgreso() {
        return msgEgreso;
    }

    public void setMsgEgreso(String msgEgreso) {
        this.msgEgreso = msgEgreso;
    }

    public String getMsgActivo() {
        return msgActivo;
    }

    public void setMsgActivo(String msgActivo) {
        this.msgActivo = msgActivo;
    }

    public String getMsgReferencia() {
        return msgReferencia;
    }

    public void setMsgReferencia(String msgReferencia) {
        this.msgReferencia = msgReferencia;
    }

    public String getMsgNombreComercio() {
        return msgNombreComercio;
    }

    public void setMsgNombreComercio(String msgNombreComercio) {
        this.msgNombreComercio = msgNombreComercio;
    }

    public String getMsgRegistroComercio() {
        return msgRegistroComercio;
    }

    public void setMsgRegistroComercio(String msgRegistroComercio) {
        this.msgRegistroComercio = msgRegistroComercio;
    }

    public String getMsgTiempoComercio() {
        return msgTiempoComercio;
    }

    public void setMsgTiempoComercio(String msgTiempoComercio) {
        this.msgTiempoComercio = msgTiempoComercio;
    }

    public String getMsgEmpleadosComercio() {
        return msgEmpleadosComercio;
    }

    public void setMsgEmpleadosComercio(String msgEmpleadosComercio) {
        this.msgEmpleadosComercio = msgEmpleadosComercio;
    }

    public String getMsgTelefonoComercio() {
        return msgTelefonoComercio;
    }

    public void setMsgTelefonoComercio(String msgTelefonoComercio) {
        this.msgTelefonoComercio = msgTelefonoComercio;
    }

    public String getMsgGiroComercio() {
        return msgGiroComercio;
    }

    public void setMsgGiroComercio(String msgGiroComercio) {
        this.msgGiroComercio = msgGiroComercio;
    }

    public String getMsgDireccionComercio() {
        return msgDireccionComercio;
    }

    public void setMsgDireccionComercio(String msgDireccionComercio) {
        this.msgDireccionComercio = msgDireccionComercio;
    }

    public String getMsgLesion() {
        return msgLesion;
    }

    public void setMsgLesion(String msgLesion) {
        this.msgLesion = msgLesion;
    }

    public String getMsgApellidoConyugue() {
        return msgApellidoConyugue;
    }

    public void setMsgApellidoConyugue(String msgApellidoConyugue) {
        this.msgApellidoConyugue = msgApellidoConyugue;
    }

    public String getMsgTelefonoConyugue() {
        return msgTelefonoConyugue;
    }

    public void setMsgTelefonoConyugue(String msgTelefonoConyugue) {
        this.msgTelefonoConyugue = msgTelefonoConyugue;
    }

    public String getMsgNombreConyugue() {
        return msgNombreConyugue;
    }

    public void setMsgNombreConyugue(String msgNombreConyugue) {
        this.msgNombreConyugue = msgNombreConyugue;
    }

    public String getMsgHijosConyugue() {
        return msgHijosConyugue;
    }

    public void setMsgHijosConyugue(String msgHijosConyugue) {
        this.msgHijosConyugue = msgHijosConyugue;
    }

    public String getMsgNombreMayor() {
        return msgNombreMayor;
    }

    public void setMsgNombreMayor(String msgNombreMayor) {
        this.msgNombreMayor = msgNombreMayor;
    }

    public String getMsgApellidoMayor() {
        return msgApellidoMayor;
    }

    public void setMsgApellidoMayor(String msgApellidoMayor) {
        this.msgApellidoMayor = msgApellidoMayor;
    }

    public String getMsgEdadMayor() {
        return msgEdadMayor;
    }

    public void setMsgEdadMayor(String msgEdadMayor) {
        this.msgEdadMayor = msgEdadMayor;
    }

    public String getMsgTelefonoMayor() {
        return msgTelefonoMayor;
    }

    public void setMsgTelefonoMayor(String msgTelefonoMayor) {
        this.msgTelefonoMayor = msgTelefonoMayor;
    }

    public String getMsgMovilMayor() {
        return msgMovilMayor;
    }

    public void setMsgMovilMayor(String msgMovilMayor) {
        this.msgMovilMayor = msgMovilMayor;
    }

    public String getMsgInstitucion() {
        return msgInstitucion;
    }

    public void setMsgInstitucion(String msgInstitucion) {
        this.msgInstitucion = msgInstitucion;
    }

    public String getMsgMontoLisiado() {
        return msgMontoLisiado;
    }

    public void setMsgMontoLisiado(String msgMontoLisiado) {
        this.msgMontoLisiado = msgMontoLisiado;
    }

    public String getMsgNombreEmpresa() {
        return msgNombreEmpresa;
    }

    public void setMsgNombreEmpresa(String msgNombreEmpresa) {
        this.msgNombreEmpresa = msgNombreEmpresa;
    }

    public String getMsgDireccionEmpresa() {
        return msgDireccionEmpresa;
    }

    public void setMsgDireccionEmpresa(String msgDireccionEmpresa) {
        this.msgDireccionEmpresa = msgDireccionEmpresa;
    }

    public String getMsgTelefonoEmpresa() {
        return msgTelefonoEmpresa;
    }

    public void setMsgTelefonoEmpresa(String msgTelefonoEmpresa) {
        this.msgTelefonoEmpresa = msgTelefonoEmpresa;
    }

    public String getMsgCargoEmpresa() {
        return msgCargoEmpresa;
    }

    public void setMsgCargoEmpresa(String msgCargoEmpresa) {
        this.msgCargoEmpresa = msgCargoEmpresa;
    }

    public String getMsgTiempoEmpresa() {
        return msgTiempoEmpresa;
    }

    public void setMsgTiempoEmpresa(String msgTiempoEmpresa) {
        this.msgTiempoEmpresa = msgTiempoEmpresa;
    }

    public String getMsgConstanciaEmpresa() {
        return msgConstanciaEmpresa;
    }

    public void setMsgConstanciaEmpresa(String msgConstanciaEmpresa) {
        this.msgConstanciaEmpresa = msgConstanciaEmpresa;
    }

    public String getMsgIngresoNombre() {
        return msgIngresoNombre;
    }

    public void setMsgIngresoNombre(String msgIngresoNombre) {
        this.msgIngresoNombre = msgIngresoNombre;
    }

    public String getMsgIngresoValor() {
        return msgIngresoValor;
    }

    public void setMsgIngresoValor(String msgIngresoValor) {
        this.msgIngresoValor = msgIngresoValor;
    }

    public String getMsgIngresoDescripcion() {
        return msgIngresoDescripcion;
    }

    public void setMsgIngresoDescripcion(String msgIngresoDescripcion) {
        this.msgIngresoDescripcion = msgIngresoDescripcion;
    }

    public String getMsgEgresoNombre() {
        return msgEgresoNombre;
    }

    public void setMsgEgresoNombre(String msgEgresoNombre) {
        this.msgEgresoNombre = msgEgresoNombre;
    }

    public String getMsgEgresoValor() {
        return msgEgresoValor;
    }

    public void setMsgEgresoValor(String msgEgresoValor) {
        this.msgEgresoValor = msgEgresoValor;
    }

    public String getMsgEgresoDescripcion() {
        return msgEgresoDescripcion;
    }

    public void setMsgEgresoDescripcion(String msgEgresoDescripcion) {
        this.msgEgresoDescripcion = msgEgresoDescripcion;
    }

    public String getMsgReferenciaNombre() {
        return msgReferenciaNombre;
    }

    public void setMsgReferenciaNombre(String msgReferenciaNombre) {
        this.msgReferenciaNombre = msgReferenciaNombre;
    }

    public String getMsgReferenciaNumero() {
        return msgReferenciaNumero;
    }

    public void setMsgReferenciaNumero(String msgReferenciaNumero) {
        this.msgReferenciaNumero = msgReferenciaNumero;
    }

    public String getMsgActivoNombre() {
        return msgActivoNombre;
    }

    public void setMsgActivoNombre(String msgActivoNombre) {
        this.msgActivoNombre = msgActivoNombre;
    }

    public String getMsgActivoValor() {
        return msgActivoValor;
    }

    public void setMsgActivoValor(String msgActivoValor) {
        this.msgActivoValor = msgActivoValor;
    }

    public String getMsgActivoDescripcion() {
        return msgActivoDescripcion;
    }

    public void setMsgActivoDescripcion(String msgActivoDescripcion) {
        this.msgActivoDescripcion = msgActivoDescripcion;
    }

    public void updateListaResumen() {

        this.listaSolicitudesProyectos = this.iproyectoBo.listTProyecto(1);

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
        this.proyectoSeleccionado.setTPolitica(new TPolitica());
        this.ingresoSeleccionado = new TIngreso();
        this.egresoSeleccionado = new TEgreso();
        this.cooperativa = new TCooperativa();
        this.trabajo = new TTrabajo();
        this.trabajo.setTCliente(new TCliente());
        this.agropecuario = new TAgropecuario();
        this.entidadProyecto = new TEntidadProyecto();
        this.cliente = new TCliente();
        this.entidadProyectoEliminar = new TEntidadProyecto();
        this.mostrarResumen = true;
        this.showConstancia = true;
        this.clienteProyectoEliminar = new TClienteProyecto();
        this.clienteSeleccionado = new TCliente();
        this.clienteSeleccionado.setCodigoCliente("");
        super.setShowData(true);
        this.tipoCredito = 0;
        this.showPresupuesto = true;
        this.showAprobacion = true;
        this.showNitCliente = true;
        this.showDuiCliente = true;
        this.showBalance = true;
        this.tablaAmortizacion = false;
        this.listaEntidadProyectosReducida = new ArrayList<TEntidadProyecto>();
        this.listaClienteProyectoReducida = new ArrayList<TClienteProyecto>();
        this.listaClienteProyectoAprobadas = new ArrayList<TClienteProyecto>();
        this.listaClienteProyectoNoAprobadas = new ArrayList<TClienteProyecto>();
        this.listaEntidadProyectosAprobadas = new ArrayList<TEntidadProyecto>();
        this.listaEntidadProyectosNoAprobadas = new ArrayList<TEntidadProyecto>();
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
        this.comercio = new TComercio();
        this.agropecuario2 = new TAgropecuario();
        this.comercio.setTProyecto(new TProyecto());
        this.mostrarTipoCreditoCooper = true;
        this.mostrarTabla = false;
        this.ingresos = new TIngreso();
        this.egresos = new TEgreso();
        this.lisiados = new TLisiado();
        this.trabajo.setConstancia("");
        this.garantias = new TGarantia();
        this.propiedadLisiado = new TPropiedadLisiado();
        this.referencias = new TReferencia();
        this.banderaEgreso = false;
        this.showImagen = true;
        this.showDuiFiador = true;
        this.cliente.setSexoCliente("Masculino");
        this.showNitFiador = true;
        this.showRespaldoPrendaria = true;
        this.showImagenCarta = true;
        this.estadoHabilitar = false;
        this.Entidadeleccionada.setCodigoEntidad("");
        this.existente = false;
        this.nuevo = true;
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
        this.agropecuario = new TAgropecuario();
        this.trabajo = new TTrabajo();

    }

    public void enableShowPresupuesto() {

        this.estadoFormulario = false;
        this.setShowPresupuesto(!this.getShowPresupuesto());

    }

    public void enableShowBalance() {

        this.estadoFormulario = false;
        this.setShowBalance(!this.getShowBalance());

    }

    public void enableShowNitCliente() {

        this.estadoFormulario = false;
        this.setShowNitCliente(!this.getShowNitCliente());

    }

    public void enableShowDuiCliente() {

        this.estadoFormulario = false;
        this.setShowDuiCliente(!this.getShowDuiCliente());

    }

    public void enableShowDuiFiador() {

        this.estadoFormulario = false;
        this.setShowDuiFiador(!this.getShowDuiFiador());

    }

    public void enableShowNitFiador() {

        this.estadoFormulario = false;
        this.setShowNitFiador(!this.getShowNitFiador());

    }

    public void enableShowConstancia() {

        this.estadoFormulario = false;
        this.setShowConstancia(!this.getShowConstancia());

    }

    public void enableShowRespaldoPrendaria() {

        this.estadoFormulario = false;
        this.setShowRespaldoPrendaria(!this.getShowRespaldoPrendaria());

    }

    public void enableShowAprobacion() {

        this.estadoFormulario = false;
        this.setShowAprobacion(!this.getShowAprobacion());

    }

    public void cancelarSolicitudCooperativa() {

        this.limpiarCooperativas();
        this.enableShowData();

    }

    public void cancelarSolicitudLisiado() {

        this.limpiarLisiado();
        this.enableShowData();

    }

    public void cancelarSolicitudAgropecuario() {

        this.limpiarAgropecuario();
        this.enableShowData();

    }

    public void cancelarSolicitudEmpleado() {

        this.limpiarEmpleado();
        this.mostrarCrearEmpleado = false;
        this.enableShowData();

    }

    public void cancelarSolicitudComercio() {

        this.limpiarComercio();
        this.enableShowData();

    }

    public void limpiarCooperativas() {

        this.limpiarVer();
        this.estadoFormulario = false;
        this.mostrarCooperativas = true;
        this.msgCooperativaActivoCo = "";
        this.msgCooperativaActivoNo = "";
        this.msgCooperativaPasivoCo = "";
        this.msgCooperativaPasivoNo = "";
        this.msgGarantiaDescripcionPrendaria = "";
        this.msgGarantiaValorPrendaria = "";
        this.msgPrendaria = "";
        this.msgGarantiaTipoHipoteca = "";
        this.msgGarantiaValorHipoteca = "";
        this.msgGarantiaDescripcionHipoteca = "";
        this.msgNombreFiador = "";
        this.msgApellidosFiador = "";
        this.msgDuiFiador = "";
        this.msgNitFiador = "";
        this.msgDuiCopiaFiador = "";
        this.msgNitCopiaFiador = "";
        this.msgProfesionFiador = "";
        this.msgLugarFiador = "";
        this.msgIngresosFiador = "";
        this.msgTelefonotFiador = "";
        this.msgTelefonofFiador = "";
        this.msgDescripcionProyecto = "";
        this.msgFormaPago = "";
        this.msgModalidad = "";
        this.msgLugar = "";
        this.msgCopiaDui = "";
        this.msgCopiaNit = "";
        this.msgFecha = "";
        this.msgNombreProyecto = "";
        this.msgArea = "";
        this.msgDestino = "";
        this.msgPlazo = "";
        this.msgMonto = "";
        this.msgClienteSeleccionado = "";
        this.msgFechaNacimiento = "";
        this.msgSexo = "";
        this.msgEstadoFamiliar = "";
        this.msgTelefono = "";
        this.msgMovil = "";
        this.msgDireccion = "";
        this.msgMunicipio = "";
        this.cliente.setSexoCliente("Masculino");
        this.msgCorreo = "";
        this.msgIngreso = "";
        this.msgPlazo = "";
        this.msgMonto = "";
        this.msgEgreso = "";
        this.msgActivo = "";
        this.msgReferencia = "";
        this.msgNombreComercio = "";
        this.msgRegistroComercio = "";
        this.msgTiempoComercio = "";
        this.msgEmpleadosComercio = "";
        this.msgTelefonoComercio = "";
        this.msgGiroComercio = "";
        this.msgDireccionComercio = "";
        this.msgLesion = "";
        this.msgApellidoConyugue = "";
        this.msgTelefonoConyugue = "";
        this.msgNombreConyugue = "";
        this.msgHijosConyugue = "";
        this.msgNombreMayor = "";
        this.msgApellidoMayor = "";
        this.msgEdadMayor = "";
        this.msgTelefonoMayor = "";
        this.msgMovilMayor = "";
        this.msgInstitucion = "";
        this.msgEntidad = "";
        this.msgCooperativaPatrimonio = "";
        this.msgPresupuesto = "";
        this.showBalance = true;
        this.showPresupuesto = true;
        this.showDuiFiador = true;
        this.showNitFiador = true;
        this.msgPrendaria = "";
        this.showRespaldoPrendaria = true;
        this.tipoCredito = 2;
        this.proyecto = new TProyecto();
        this.proyecto.setTTipoCredito(new TTipoCredito());
        this.clienteSeleccionado = new TCliente();
        this.cliente = new TCliente();
        this.garantia = new TGarantia();
        this.listaIngreso.clear();
        this.listaEgreso.clear();
        this.listaActivos.clear();
        this.comercio = new TComercio();
        this.listaReferencia.clear();
        this.mostrarTabla = false;
        this.cooperativa = new TCooperativa();
        this.Entidadeleccionada = new TEntidad();
        this.Entidadeleccionada.setCodigoEntidad("");
        mostrarSolicitudes();
        this.msgBalance = "";
        this.politicaSeleccionada = new TPolitica();
        this.correlativo();

    }

    public void limpiarAgropecuario() {

        this.limpiarVer();
        this.estadoFormulario = false;
        this.estadoFormulario = false;
        this.mostrarCooperativas = true;
        this.msgCooperativaActivoCo = "";
        this.msgCooperativaActivoNo = "";
        this.msgCooperativaPasivoCo = "";
        this.msgCooperativaPasivoNo = "";
        this.msgGarantiaDescripcionPrendaria = "";
        this.msgGarantiaValorPrendaria = "";
        this.msgGarantiaTipoHipoteca = "";
        this.msgGarantiaValorHipoteca = "";
        this.msgGarantiaDescripcionHipoteca = "";
        this.msgNombreFiador = "";
        this.msgApellidosFiador = "";
        this.msgDuiFiador = "";
        this.msgNitFiador = "";
        this.msgProfesionFiador = "";
        this.msgLugarFiador = "";
        this.msgIngresosFiador = "";
        this.msgTelefonotFiador = "";
        this.msgTelefonofFiador = "";
        this.msgDescripcionProyecto = "";
        this.msgFormaPago = "";
        this.msgModalidad = "";
        this.msgLugar = "";
        this.msgDuiCopiaFiador = "";
        this.msgNitCopiaFiador = "";
        this.msgPrendaria = "";
        this.msgFecha = "";
        this.msgNombreProyecto = "";
        this.msgArea = "";
        this.msgDestino = "";
        this.msgPlazo = "";
        this.msgMonto = "";
        this.msgCopiaDui = "";
        this.msgCopiaNit = "";
        this.msgClienteSeleccionado = "";
        this.msgFechaNacimiento = "";
        this.msgSexo = "";
        this.msgEstadoFamiliar = "";
        this.msgTelefono = "";
        this.msgMovil = "";
        this.msgDireccion = "";
        this.msgMunicipio = "";
        this.msgCorreo = "";
        this.msgIngreso = "";
        this.msgPlazo = "";
        this.msgMonto = "";
        this.msgEgreso = "";
        this.msgActivo = "";
        this.msgReferencia = "";
        this.msgNombreComercio = "";
        this.msgRegistroComercio = "";
        this.msgTiempoComercio = "";
        this.msgEmpleadosComercio = "";
        this.msgTelefonoComercio = "";
        this.msgGiroComercio = "";
        this.msgActividad = "";
        this.msgtiempo = "";
        this.msgDireccionComercio = "";
        this.msgLesion = "";
        this.msgApellidoConyugue = "";
        this.msgTelefonoConyugue = "";
        this.msgNombreConyugue = "";
        this.msgHijosConyugue = "";
        this.msgNombreMayor = "";
        this.msgApellidoMayor = "";
        this.msgEdadMayor = "";
        this.msgTelefonoMayor = "";
        this.msgMovilMayor = "";
        this.msgInstitucion = "";
        this.msgNombreCliente = "";
        this.msgApellidoCliente = "";
        this.msgDuiCliente = "";
        this.msgNitCliente = "";
        this.showDuiCliente = true;
        this.showNitCliente = true;
        this.showDuiFiador = true;
        this.showNitFiador = true;
        this.showRespaldoPrendaria = true;
        mostrarSolicitudes();
        this.mostrarAgropecuarios = true;
        this.mostrarCooperativas = false;
        this.tipoCredito = 8;
        this.proyecto = new TProyecto();
        this.agropecuario = new TAgropecuario();
        this.proyecto.setTTipoCredito(new TTipoCredito());
        this.clienteSeleccionado = new TCliente();
        this.clienteSeleccionado.setCodigoCliente("");
        this.cliente = new TCliente();
        this.garantia = new TGarantia();
        this.listaIngreso.clear();
        this.listaEgreso.clear();
        this.listaActivos.clear();
        this.comercio = new TComercio();
        this.listaReferencia.clear();
        this.mostrarTabla = false;
        this.cliente.setSexoCliente("Masculino");
        this.correlativo();

    }

    public void limpiarComercio() {

        this.limpiarVer();
        this.estadoFormulario = false;
        this.estadoFormulario = false;
        this.mostrarCooperativas = true;
        this.msgCooperativaActivoCo = "";
        this.msgCooperativaActivoNo = "";
        this.msgCooperativaPasivoCo = "";
        this.msgCooperativaPasivoNo = "";
        this.msgGarantiaDescripcionPrendaria = "";
        this.msgGarantiaValorPrendaria = "";
        this.msgGarantiaTipoHipoteca = "";
        this.msgGarantiaValorHipoteca = "";
        this.msgGarantiaDescripcionHipoteca = "";
        this.msgNombreFiador = "";
        this.msgApellidosFiador = "";
        this.msgDuiFiador = "";
        this.msgPrendaria = "";
        this.msgNitFiador = "";
        this.msgProfesionFiador = "";
        this.msgLugarFiador = "";
        this.msgIngresosFiador = "";
        this.msgTelefonotFiador = "";
        this.msgTelefonofFiador = "";
        this.msgDescripcionProyecto = "";
        this.msgFormaPago = "";
        this.msgModalidad = "";
        this.msgLugar = "";
        this.msgFecha = "";
        this.msgDuiCopiaFiador = "";
        this.msgNitCopiaFiador = "";
        this.msgNombreProyecto = "";
        this.msgArea = "";
        this.msgDestino = "";
        this.msgPlazo = "";
        this.msgMonto = "";
        this.msgClienteSeleccionado = "";
        this.msgFechaNacimiento = "";
        this.msgSexo = "";
        this.msgEstadoFamiliar = "";
        this.msgTelefono = "";
        this.msgMovil = "";
        this.msgDireccion = "";
        this.msgMunicipio = "";
        this.msgCorreo = "";
        this.msgIngreso = "";
        this.msgPlazo = "";
        this.msgMonto = "";
        this.msgEgreso = "";
        this.msgActivo = "";
        this.msgReferencia = "";
        this.msgNombreComercio = "";
        this.msgRegistroComercio = "";
        this.msgTiempoComercio = "";
        this.msgEmpleadosComercio = "";
        this.msgTelefonoComercio = "";
        this.msgGiroComercio = "";
        this.msgDireccionComercio = "";
        this.msgLesion = "";
        this.msgApellidoConyugue = "";
        this.msgTelefonoConyugue = "";
        this.msgNombreConyugue = "";
        this.msgHijosConyugue = "";
        this.msgNombreMayor = "";
        this.msgApellidoMayor = "";
        this.msgEdadMayor = "";
        this.msgTelefonoMayor = "";
        this.msgMovilMayor = "";
        this.msgInstitucion = "";
        this.msgNombreCliente = "";
        this.listaActivos.clear();
        this.msgApellidoCliente = "";
        this.msgDuiCliente = "";
        this.msgNitCliente = "";
        this.msgCopiaDui = "";
        this.msgCopiaNit = "";
        mostrarSolicitudes();
        this.mostrarAgropecuarios = false;
        this.mostrarCooperativas = false;
        this.mostrarLisiados = false;
        this.mostrarComercio = true;
        this.tipoCredito = 1;
        this.proyecto = new TProyecto();
        this.proyecto.setTTipoCredito(new TTipoCredito());
        this.clienteSeleccionado = new TCliente();
        this.clienteSeleccionado.setCodigoCliente("");
        this.cliente = new TCliente();
        this.garantia = new TGarantia();
        this.listaIngreso.clear();
        this.listaEgreso.clear();
        this.comercio = new TComercio();
        this.listaReferencia.clear();
        this.mostrarTabla = false;
        this.showDuiCliente = true;
        this.showNitCliente = true;
        this.showDuiFiador = true;
        this.showNitFiador = true;
        this.showRespaldoPrendaria = true;
        this.cliente.setSexoCliente("Masculino");
        this.correlativo();

    }

    public void limpiarEmpleado() {

        this.limpiarVer();
        this.estadoFormulario = false;
        this.estadoFormulario = false;
        this.mostrarCooperativas = true;
        this.msgCooperativaActivoCo = "";
        this.msgCooperativaActivoNo = "";
        this.msgCooperativaPasivoCo = "";
        this.msgCooperativaPasivoNo = "";
        this.msgGarantiaDescripcionPrendaria = "";
        this.msgGarantiaValorPrendaria = "";
        this.msgGarantiaTipoHipoteca = "";
        this.msgGarantiaValorHipoteca = "";
        this.msgGarantiaDescripcionHipoteca = "";
        this.msgNombreFiador = "";
        this.msgDuiCopiaFiador = "";
        this.msgNitCopiaFiador = "";
        this.msgApellidosFiador = "";
        this.msgDuiFiador = "";
        this.msgNitFiador = "";
        this.msgProfesionFiador = "";
        this.msgLugarFiador = "";
        this.msgPrendaria = "";
        this.msgIngresosFiador = "";
        this.msgTelefonotFiador = "";
        this.msgTelefonofFiador = "";
        this.msgDescripcionProyecto = "";
        this.msgFormaPago = "";
        this.msgModalidad = "";
        this.msgLugar = "";
        this.msgFecha = "";
        this.msgNombreProyecto = "";
        this.msgArea = "";
        this.msgDestino = "";
        this.msgPlazo = "";
        this.msgMonto = "";
        this.msgClienteSeleccionado = "";
        this.msgFechaNacimiento = "";
        this.msgSexo = "";
        this.msgEstadoFamiliar = "";
        this.msgTelefono = "";
        this.msgMovil = "";
        this.msgDireccion = "";
        this.msgMunicipio = "";
        this.msgCorreo = "";
        this.msgIngreso = "";
        this.msgPlazo = "";
        this.msgMonto = "";
        this.msgEgreso = "";
        this.msgActivo = "";
        this.msgReferencia = "";
        this.msgNombreComercio = "";
        this.msgRegistroComercio = "";
        this.msgTiempoComercio = "";
        this.msgEmpleadosComercio = "";
        this.msgTelefonoComercio = "";
        this.msgGiroComercio = "";
        this.msgDireccionComercio = "";
        this.msgLesion = "";
        this.msgApellidoConyugue = "";
        this.msgTelefonoConyugue = "";
        this.msgNombreConyugue = "";
        this.msgHijosConyugue = "";
        this.msgNombreMayor = "";
        this.msgApellidoMayor = "";
        this.msgEdadMayor = "";
        this.msgTelefonoMayor = "";
        this.msgMovilMayor = "";
        this.msgInstitucion = "";
        this.msgNombreCliente = "";
        this.msgApellidoCliente = "";
        this.msgDuiCliente = "";
        this.msgNitCliente = "";
        this.msgCopiaDui = "";
        this.msgCopiaNit = "";
        this.showDuiCliente = true;
        this.showNitCliente = true;
        this.showDuiFiador = true;
        this.showNitFiador = true;
        this.showRespaldoPrendaria = true;
        this.showConstancia = true;
        this.msgTiempoEmpresa = "";
        this.msgNombreEmpresa = "";
        this.msgTelefonoEmpresa = "";
        this.msgDireccionEmpresa = "";
        this.msgCargoEmpresa = "";
        this.msgConstanciaEmpresa = "";
        this.mostrarAgropecuarios = false;
        this.mostrarCooperativas = false;
        this.mostrarLisiados = false;
        this.mostrarComercio = false;
        this.mostrarEmpleados = true;
        this.tipoCredito = 7;
        this.proyecto = new TProyecto();
        this.proyecto.setTTipoCredito(new TTipoCredito());
        this.clienteSeleccionado = new TCliente();
        this.clienteSeleccionado.setCodigoCliente("");
        this.cliente = new TCliente();
        this.garantia = new TGarantia();
        this.listaIngreso.clear();
        this.listaEgreso.clear();
        this.listaActivos.clear();
        this.comercio = new TComercio();
        this.listaReferencia.clear();
        this.mostrarTabla = false;
        this.trabajo = new TTrabajo();
        this.cliente.setSexoCliente("Masculino");
        mostrarSolicitudes();
        this.correlativo();

    }

    public void limpiarLisiado() {

        this.limpiarVer();
        this.estadoFormulario = false;
        this.estadoFormulario = false;
        this.mostrarCooperativas = true;
        this.msgCooperativaActivoCo = "";
        this.msgCooperativaActivoNo = "";
        this.msgCooperativaPasivoCo = "";
        this.msgCooperativaPasivoNo = "";
        this.msgGarantiaDescripcionPrendaria = "";
        this.msgGarantiaValorPrendaria = "";
        this.msgGarantiaTipoHipoteca = "";
        this.msgDuiCopiaFiador = "";
        this.msgNitCopiaFiador = "";
        this.msgGarantiaValorHipoteca = "";
        this.msgGarantiaDescripcionHipoteca = "";
        this.msgNombreFiador = "";
        this.msgApellidosFiador = "";
        this.msgDuiFiador = "";
        this.msgCopiaDui = "";
        this.msgCopiaNit = "";
        this.msgNitFiador = "";
        this.msgProfesionFiador = "";
        this.msgLugarFiador = "";
        this.msgIngresosFiador = "";
        this.msgTelefonotFiador = "";
        this.msgTelefonofFiador = "";
        this.msgDescripcionProyecto = "";
        this.msgFormaPago = "";
        this.msgModalidad = "";
        this.msgLugar = "";
        this.msgPrendaria = "";
        this.msgFecha = "";
        this.msgNombreProyecto = "";
        this.msgArea = "";
        this.msgDestino = "";
        this.msgedadConyugue = "";
        this.msgPlazo = "";
        this.msgMonto = "";
        this.msgClienteSeleccionado = "";
        this.msgFechaNacimiento = "";
        this.msgSexo = "";
        this.msgEstadoFamiliar = "";
        this.msgTelefono = "";
        this.msgMovil = "";
        this.msgDireccion = "";
        this.msgMunicipio = "";
        this.msgCorreo = "";
        this.msgIngreso = "";
        this.msgPlazo = "";
        this.msgMonto = "";
        this.msgEgreso = "";
        this.msgActivo = "";
        this.msgReferencia = "";
        this.msgNombreComercio = "";
        this.msgRegistroComercio = "";
        this.msgTiempoComercio = "";
        this.msgEmpleadosComercio = "";
        this.msgTelefonoComercio = "";
        this.msgGiroComercio = "";
        this.msgDireccionComercio = "";
        this.msgLesion = "";
        this.msgApellidoConyugue = "";
        this.msgTelefonoConyugue = "";
        this.msgNombreConyugue = "";
        this.msgHijosConyugue = "";
        this.msgNombreMayor = "";
        this.msgApellidoMayor = "";
        this.msgEdadMayor = "";
        this.msgTelefonoMayor = "";
        this.msgMovilMayor = "";
        this.msgInstitucion = "";
        this.msgEntidad = "";
        this.msgNombreCliente = "";
        this.msgApellidoCliente = "";
        this.msgDuiCliente = "";
        this.msgNitCliente = "";
        this.msgDuiFiador = "";
        this.msgNitFiador = "";
        this.msgConstanciaLisiado = "";
        this.showDuiCliente = true;
        this.showNitCliente = true;
        this.showDuiFiador = true;
        this.showNitFiador = true;
        this.showRespaldoPrendaria = true;
        this.showImagenCarta = true;
        mostrarSolicitudes();
        this.mostrarAgropecuarios = false;
        this.mostrarCooperativas = false;
        this.mostrarLisiados = true;
        this.cliente.setSexoCliente("Masculino");
        this.tipoCredito = 5;
        this.proyecto = new TProyecto();
        this.proyecto.setTTipoCredito(new TTipoCredito());
        this.clienteSeleccionado = new TCliente();
        this.clienteSeleccionado.setCodigoCliente("");
        this.cliente = new TCliente();
        this.garantia = new TGarantia();
        this.listaIngreso.clear();
        this.listaEgreso.clear();
        this.listaActivos.clear();
        this.comercio = new TComercio();
        this.listaReferencia.clear();
        this.mostrarTabla = false;
        this.lisiados = new TLisiado();
        this.lisiados.setExperienciaCrediticia(false);
        this.cliente.setSexoCliente("Masculino");
        this.correlativo();

    }

    public void validarFormularioCooperativa() {

        this.estadoFormulario = true;
        if (this.Entidadeleccionada.getCodigoEntidad() == "") {
            this.msgEntidad = "Debe seleccionar una cooperativa";
            this.estadoFormulario = false;
        } else {
            this.msgEntidad = "";
        }
        if (this.cooperativa.getActivoCorriente() == null || this.cooperativa.getActivoCorriente().compareTo(BigDecimal.ZERO) < 0) {
            this.msgCooperativaActivoCo = "Debe introducir el valor del activo corriente";
            this.estadoFormulario = false;
        } else {
            this.msgCooperativaActivoCo = "";
        }
        if (this.cooperativa.getActivoNocorriente() == null || this.cooperativa.getActivoNocorriente().compareTo(BigDecimal.ZERO) < 0) {
            this.msgCooperativaActivoNo = "Debe introducir el valor del activo no corriente";
            this.estadoFormulario = false;
        } else {
            this.msgCooperativaActivoNo = "";
        }
        if (this.cooperativa.getBalanceGeneral() == null || this.cooperativa.getBalanceGeneral() == "") {
            this.msgBalance = "Debe introducir el balance general";
            this.estadoFormulario = false;
        } else {
            this.msgBalance = "";
        }
        if (this.cooperativa.getPresupuesto() == null || this.cooperativa.getPresupuesto() == "") {
            this.msgPresupuesto = "Debe introducir un presupuesto o cotización";
            this.estadoFormulario = false;
        } else {
            this.msgPresupuesto = "";
        }
        if (this.cooperativa.getPasivoCorriente() == null || this.cooperativa.getPasivoCorriente().compareTo(BigDecimal.ZERO) < 0) {
            this.msgCooperativaPasivoCo = "Debe introducir el valor del pasivo corriente";
            this.estadoFormulario = false;
        } else {
            this.msgCooperativaPasivoCo = "";
        }
        if (this.cooperativa.getPasivoNocorriente() == null || this.cooperativa.getPasivoNocorriente().compareTo(BigDecimal.ZERO) < 0) {
            this.msgCooperativaPasivoNo = "Debe introducir el valor del pasivo no corriente";
            this.estadoFormulario = false;
        } else {
            this.msgCooperativaPasivoNo = "";
        }
        if (this.cooperativa.getPatrimonio() == null || this.cooperativa.getPatrimonio().compareTo(BigDecimal.ZERO) < 0) {
            this.msgCooperativaPatrimonio = "Debe introducir el valor del patrimonio";
            this.estadoFormulario = false;
        } else {
            this.msgCooperativaPatrimonio = "";
        }
        if (this.estadoPrendaria == true) {
            if (this.garantia.getDescripcionPrendariaGarantia() == "") {
                this.msgGarantiaDescripcionPrendaria = "Debe introducir la descripción de la garantia prendaria";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaDescripcionPrendaria = "";
            }
            if (this.garantia.getValorPrendariaGarantia() == null || this.garantia.getValorPrendariaGarantia().compareTo(BigDecimal.ZERO) < 0) {
                this.msgGarantiaValorPrendaria = "Debe introducir el valor de la garantia prendaria";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaValorPrendaria = "";
            }
            if (this.garantia.getDocumentoPrendariaGarantia() == null) {
                this.msgPrendaria = "Debe introducir el comprobante de la garantia prendaria";
                this.estadoFormulario = false;
            } else {
                this.msgPrendaria = "";
            }
        }
        if (this.estadoHipotecaria == true) {
            if (this.garantia.getHipotecaHipotecariaGarantia() == "") {
                this.msgGarantiaTipoHipoteca = "Debe seleccionar el tipo de hipoteca";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaTipoHipoteca = "";
            }
            if (this.garantia.getValorHipotecaGarantia() == null || this.garantia.getValorHipotecaGarantia().compareTo(BigDecimal.ZERO) < 0) {
                this.msgGarantiaValorHipoteca = "Debe introducir el valor de la hipoteca";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaValorHipoteca = "";
            }
            if (this.garantia.getUbicacionHipotecaGarantia() == "") {
                this.msgGarantiaDescripcionHipoteca = "Debe introducir la descripción de la hipoteca";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaDescripcionHipoteca = "";
            }
        }
        if (this.estadoSolidaria == true) {
            if (this.garantia.getNombresSolidariaGarantia() == "") {
                this.msgNombreFiador = "Debe introducir los nombres";
                this.estadoFormulario = false;
            } else {
                this.msgNombreFiador = "";
            }
            if (this.garantia.getApellidosSolidariaGarantia() == "") {
                this.msgApellidosFiador = "Debe introducir los apellidos";
                this.estadoFormulario = false;
            } else {
                this.msgApellidosFiador = "";
            }
            if (this.garantia.getDuiSolidariaGarantia() == "") {
                this.msgDuiFiador = "Debe introducir el DUI";
                this.estadoFormulario = false;
            } else {
                this.msgDuiFiador = "";
            }
            if (this.garantia.getNitSolidariaGarantia() == "") {
                this.msgNitFiador = "Debe introducir el NIT";
                this.estadoFormulario = false;
            } else {
                this.msgNitFiador = "";
            }
            if (this.garantia.getProfesionSolidariaGarantia() == "") {
                this.msgProfesionFiador = "Debe introducir la profesión";
                this.estadoFormulario = false;
            } else {
                this.msgProfesionFiador = "";
            }
            if (this.garantia.getLugarSolidariaGarantia() == "") {
                this.msgLugarFiador = "Debe introducir el lugar";
                this.estadoFormulario = false;
            } else {
                this.msgLugarFiador = "";
            }
            if (this.garantia.getIngresosSolidariaGarantia() == null) {
                this.msgIngresosFiador = "Debe introducir el valor del ingreso";
                this.estadoFormulario = false;
            } else {
                this.msgIngresosFiador = "";
            }
            if (this.garantia.getTelefonotSolidariaGarantia() == "") {
                this.msgTelefonotFiador = "Debe introducir el teléfono del lugar de trabajo";
                this.estadoFormulario = false;
            } else {
                this.msgTelefonotFiador = "";
            }
            if (this.garantia.getFotocopiaDuiSolidariaGarantia() == null) {
                this.msgDuiCopiaFiador = "Debe introducir la imagen del DUI";
                this.estadoFormulario = false;
            } else {
                this.msgDuiCopiaFiador = "";
            }
            if (this.garantia.getFotocopiaNitSolidariaGarantia() == null) {
                this.msgNitCopiaFiador = "Debe introducir la imagen del NIT";
                this.estadoFormulario = false;
            } else {
                this.msgNitCopiaFiador = "";
            }
        }
        if (this.proyecto.getLugar() == "") {
            this.msgLugar = "Debe introducir el lugar";
            this.estadoFormulario = false;
        } else {
            this.msgLugar = "";
        }
        if (this.proyecto.getFecha() == null) {
            this.msgFecha = "Debe introducir la fecha";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }
        if (this.proyecto.getNombre() == "") {
            this.msgNombreProyecto = "Debe introducir el nombre";
            this.estadoFormulario = false;
        } else {
            this.msgNombreProyecto = "";
        }
        if (this.proyecto.getDescripcion() == "") {
            this.msgDescripcionProyecto = "Debe introducir la descripción";
            this.estadoFormulario = false;
        } else {
            this.msgDescripcionProyecto = "";
        }
        if (this.cooperativa.getAreaProducida() == null) {
            this.msgArea = "Debe introducir el área producida";
            this.estadoFormulario = false;
        } else {
            this.msgArea = "";
        }
        if (this.proyecto.getTTipoCredito().getIdTipoCredito() == 0) {
            this.msgModalidad = "Debe seleccionar una modalidad";
            this.estadoFormulario = false;
        } else {
            this.msgModalidad = "";
        }
        if (this.proyecto.getDestinoProyecto() == "") {
            this.msgDestino = "Debe introducir el destino";
            this.estadoFormulario = false;
        } else {
            this.msgDestino = "";
        }
        if (this.proyecto.getTTipoCredito().getIdTipoCredito() != 0) {
            if ((this.proyecto.getMonto() == null) || (this.proyecto.getMonto().compareTo(this.politica.getMontoMinimo()) <= 0 || this.proyecto.getMonto().compareTo(this.politica.getMontoMaximo()) >= 0)) {
                this.msgMonto = "Debe introducir un monto entre " + this.politica.getMontoMinimo() + " y " + this.politica.getMontoMaximo() + "";
                this.estadoFormulario = false;
            } else {
                this.msgMonto = "";
            }
        } else {
            this.msgMonto = "Debe introducir un monto";
        }
        if (this.proyecto.getFormaPagoProyecto() == 0) {
            this.msgFormaPago = "Debe seleccionar una forma de pago";
            this.estadoFormulario = false;
        } else {
            this.msgFormaPago = "";
        }
        if (this.proyecto.getPlazo() == null) {
            this.msgPlazo = "Debe introducir el plazo del proyecto";
            this.estadoFormulario = false;
        } else if (this.proyecto.getPlazo()%12!=0 || this.proyecto.getPlazo()==0 ){
           
             this.msgPlazo = "Debe introducir un plazo multiplo de 12";
             this.estadoFormulario = false;
        }else{
             this.msgPlazo = "";
        }

    }

    public void limpiarVer() {

        this.verDatosAgropecuario = false;
        this.verDatosComercio = false;
        this.verDatosCooperativa = false;
        this.verDatosEmpleado = false;
        this.verDatosLisiado = false;
        this.VerResolucion = false;

    }

    public void validarFormularioAgropecuario() {

        this.estadoFormulario = true;
        if (this.existente == true) {
            if (this.clienteSeleccionado.getCodigoCliente() == "") {
                this.msgClienteSeleccionado = "Debe seleccionar un cliente";
                this.estadoFormulario = false;
            } else {
                this.msgClienteSeleccionado = "";
            }
        } else {
            if (this.cliente.getNombreCliente() == "") {
                this.msgNombreCliente = "Debe introducir los nombres";
                this.estadoFormulario = false;
            } else {
                this.msgNombreCliente = "";
            }
            if (this.cliente.getApellidoCliente() == "") {
                this.msgApellidoCliente = "Debe introducir los apellidos";
                this.estadoFormulario = false;
            } else {
                this.msgApellidoCliente = "";
            }

            if (this.cliente.getDuiCliente() != "") {

                Boolean estaBd = this.iclienteBo.getDuiCliente(this.cliente.getDuiCliente());

                if (estaBd) {
                    this.msgDuiCliente = "El DUI introducido ya fue registrado";
                    this.estadoFormulario = false;

                } else {

                    this.msgDuiCliente = "";
                }
            } else {

                this.msgDuiCliente = "Debe introducir el DUI";
                this.estadoFormulario = false;

            }
            
            if (this.cliente.getNitCliente() != "") {

                Boolean estaBd = this.iclienteBo.getNitCliente(this.cliente.getNitCliente());

                if (estaBd) {
                    this.msgNitCliente = "El NIT introducido ya fue registrado";
                    this.estadoFormulario = false;

                } else {

                    this.msgNitCliente = "";
                }
            } else {

                this.msgNitCliente = "Debe introducir el NIT";
                this.estadoFormulario = false;

            }
            
            

          
            

            if (this.cliente.getFechaNacimiento() == null) {
                this.msgFechaNacimiento = "Debe introducir una fecha de nacimiento";
                this.estadoFormulario = false;
            } else if (this.cliente.getFechaNacimiento() != null) {

                this.edad = 0;
                this.calcularEdadSolicitud();

                if (this.edad < this.politica.getEdadMinima() || this.edad > this.politica.getEdadMaxima()) {
                    this.msgFechaNacimiento = "La edad tiene que estar entre " + this.politica.getEdadMinima() + " y " + this.politica.getEdadMaxima() + " años inclusive";
                    this.estadoFormulario = false;

                } else {
                    this.msgFechaNacimiento = "";
                }
            }

            if (this.cliente.getEstadoFamiliar() == "") {
                this.msgEstadoFamiliar = "Debe seleccionar un estado familiar";
                this.estadoFormulario = false;
            } else {
                this.msgEstadoFamiliar = "";
            }
            if (this.cliente.getTelefonoCliente() == "") {
                this.msgTelefono = "Debe introducir el télefono";
                this.estadoFormulario = false;
            } else {
                this.msgTelefono = "";
            }

            if (this.cliente.getCelularCliente() == "") {
                this.msgMovil = "Debe introducir el movil";
                this.estadoFormulario = false;
            } else {
                this.msgMovil = "";
            }

            if (this.cliente.getDireccionCliente() == "") {
                this.msgDireccion = "Debe introducir una dirección";
                this.estadoFormulario = false;
            } else {
                this.msgDireccion = "";
            }
            if (this.cliente.getDuiCopiaCliente() == null) {
                this.msgCopiaDui = "Debe introducir la imagen DUI";
                this.estadoFormulario = false;
            } else {
                this.msgCopiaDui = "";
            }
            if (this.cliente.getNitCopiaCliente() == null) {
                this.msgCopiaNit = "Debe introducir la imagen del NIT";
                this.estadoFormulario = false;
            } else {
                this.msgCopiaNit = "";
            }
        }
        if (this.listaIngreso.size() == 0) {
            this.msgIngreso = "Debe introducir por lo menos un ingreso";
            this.estadoFormulario = false;
        } else {
            this.msgIngreso = "";
        }
        if (this.listaEgreso.size() == 0) {
            this.msgEgreso = "Debe introducir por lo menos un egreso";
            this.estadoFormulario = false;
        } else {
            this.msgEgreso = "";
        }
        if (this.listaActivos.size() == 0) {
            this.msgActivo = "Debe introducir por lo menos un activo";
            this.estadoFormulario = false;
        } else {
            this.msgActivo = "";
        }
        if (this.listaReferencia.size() == 0) {
            this.msgReferencia = "Debe introducir por lo menos una referencia";
            this.estadoFormulario = false;
        } else {
            this.msgReferencia = "";
        }
        if (this.estadoHipotecaria == true) {
            if (this.garantia.getHipotecaHipotecariaGarantia() == "") {
                this.msgGarantiaTipoHipoteca = "Debe seleccionar el tipo";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaTipoHipoteca = "";
            }
            if (this.garantia.getValorHipotecaGarantia() == null || this.garantia.getValorHipotecaGarantia().compareTo(BigDecimal.ZERO) < 0) {
                this.msgGarantiaValorHipoteca = "Debe introducir el valor";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaValorHipoteca = "";
            }
            if (this.garantia.getUbicacionHipotecaGarantia() == "") {
                this.msgGarantiaDescripcionHipoteca = "Debe introducir la descripción";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaDescripcionHipoteca = "";
            }
        }
        if (this.estadoPrendaria == true) {
            if (this.garantia.getDescripcionPrendariaGarantia() == "") {
                this.msgGarantiaDescripcionPrendaria = "Debe introducir la descripción";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaDescripcionPrendaria = "";
            }
            if (this.garantia.getValorPrendariaGarantia() == null || this.garantia.getValorPrendariaGarantia().compareTo(BigDecimal.ZERO) < 0) {
                this.msgGarantiaValorPrendaria = "Debe introducir el valor";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaValorPrendaria = "";
            }
            if (this.garantia.getDocumentoPrendariaGarantia() == null) {
                this.msgPrendaria = "Debe introducir el comprobante";
                this.estadoFormulario = false;
            } else {
                this.msgPrendaria = "";
            }
        }
        if (this.estadoSolidaria == true) {
            if (this.garantia.getNombresSolidariaGarantia() == "") {
                this.msgNombreFiador = "Debe introducir los nombres";
                this.estadoFormulario = false;
            } else {
                this.msgNombreFiador = "";
            }
            if (this.garantia.getApellidosSolidariaGarantia() == "") {
                this.msgApellidosFiador = "Debe introducir los apellidos";
                this.estadoFormulario = false;
            } else {
                this.msgApellidosFiador = "";
            }
            if (this.garantia.getDuiSolidariaGarantia() == "") {
                this.msgDuiFiador = "Debe introducir el DUI";
                this.estadoFormulario = false;
            } else {
                this.msgDuiFiador = "";
            }
            if (this.garantia.getNitSolidariaGarantia() == "") {
                this.msgNitFiador = "Debe introducir el NIT";
                this.estadoFormulario = false;
            } else {
                this.msgNitFiador = "";
            }
            if (this.garantia.getProfesionSolidariaGarantia() == "") {
                this.msgProfesionFiador = "Debe introducir la profesión";
                this.estadoFormulario = false;
            } else {
                this.msgProfesionFiador = "";
            }
            if (this.garantia.getLugarSolidariaGarantia() == "") {
                this.msgLugarFiador = "Debe introducir el lugar";
                this.estadoFormulario = false;
            } else {
                this.msgLugarFiador = "";
            }
            if (this.garantia.getIngresosSolidariaGarantia() == null) {
                this.msgIngresosFiador = "Debe introducir el valor";
                this.estadoFormulario = false;
            } else {
                this.msgIngresosFiador = "";
            }
            if (this.garantia.getTelefonotSolidariaGarantia() == "") {
                this.msgTelefonotFiador = "Debe introducir el teléfono";
                this.estadoFormulario = false;
            } else {
                this.msgTelefonotFiador = "";
            }

            if (this.garantia.getFotocopiaDuiSolidariaGarantia() == null) {
                this.msgDuiCopiaFiador = "Debe introducir la imagen del DUI";
                this.estadoFormulario = false;
            } else {
                this.msgDuiCopiaFiador = "";
            }
            if (this.garantia.getFotocopiaNitSolidariaGarantia() == null) {
                this.msgNitCopiaFiador = "Debe introducir la imagen del NIT";
                this.estadoFormulario = false;
            } else {
                this.msgNitCopiaFiador = "";
            }
        }
        if (this.proyecto.getLugar() == "") {
            this.msgLugar = "Debe introducir el lugar";
            this.estadoFormulario = false;
        } else {
            this.msgLugar = "";
        }
        if (this.proyecto.getFecha() == null) {
            this.msgFecha = "Debe introducir la fecha";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }
        if (this.proyecto.getTTipoCredito().getIdTipoCredito() == 0) {
            this.msgModalidad = "Debe seleccionar una modalidad";
            this.estadoFormulario = false;
        } else {
            this.msgModalidad = "";
        }
        if (this.proyecto.getDestinoProyecto() == "") {
            this.msgDestino = "Debe introducir el destino";
            this.estadoFormulario = false;
        } else {
            this.msgDestino = "";
        }
        if (this.proyecto.getTTipoCredito().getIdTipoCredito() != 0) {
            if ((this.proyecto.getMonto() == null) || (this.proyecto.getMonto().compareTo(this.politica.getMontoMinimo()) <= 0 || this.proyecto.getMonto().compareTo(this.politica.getMontoMaximo()) >= 0)) {
                this.msgMonto = "Debe introducir un monto entre " + this.politica.getMontoMinimo() + " y " + this.politica.getMontoMaximo() + "";
                this.estadoFormulario = false;
            } else {
                this.msgMonto = "";
            }
        } else {
            this.msgMonto = "Debe de seleccionar una modalidad";
        }
        if (this.proyecto.getFormaPagoProyecto() == 0) {
            this.msgFormaPago = "Debe seleccionar una forma de pago";
            this.estadoFormulario = false;
        } else {
            this.msgFormaPago = "";
        }
        if (this.proyecto.getPlazo() == null) {
            this.msgPlazo = "Debe introducir el plazo del proyecto";
            this.estadoFormulario = false;
        } else {
            this.msgPlazo = "";
        }
        if (this.agropecuario2.getActividadAgropecuario() == "") {
            this.msgActividad = "Debe introducir el nombre";
            this.estadoFormulario = false;
        } else {
            this.msgActividad = "";
        }
        if (this.agropecuario2.getTiempoAgropecuario() == null) {
            this.msgtiempo = "Debe introducir el tiempo";
            this.estadoFormulario = false;
        } else {
            this.msgtiempo = "";
        }

    }

    public void validarFormularioComercio() {

        this.estadoFormulario = true;
        if (this.existente == true) {
            if (this.clienteSeleccionado.getCodigoCliente() == "") {
                this.msgClienteSeleccionado = "Debe seleccionar el cliente";
                this.estadoFormulario = false;
            } else {
                this.msgClienteSeleccionado = "";
            }
        } else {
            if (this.cliente.getNombreCliente() == "") {
                this.msgNombreCliente = "Debe introducir los nombres";
                this.estadoFormulario = false;
            } else {
                this.msgNombreCliente = "";
            }
            if (this.cliente.getApellidoCliente() == "") {
                this.msgApellidoCliente = "Debe introducir el apellidos";
                this.estadoFormulario = false;
            } else {
                this.msgApellidoCliente = "";
            }
           
            if (this.cliente.getDuiCliente() != "") {

                Boolean estaBd = this.iclienteBo.getDuiCliente(this.cliente.getDuiCliente());

                if (estaBd) {
                    this.msgDuiCliente = "El DUI introducido ya fue registrado";
                    this.estadoFormulario = false;

                } else {

                    this.msgDuiCliente = "";
                }
            } else {

                this.msgDuiCliente = "Debe introducir el DUI";
                this.estadoFormulario = false;

            }
            
            if (this.cliente.getNitCliente() != "") {

                Boolean estaBd = this.iclienteBo.getNitCliente(this.cliente.getNitCliente());

                if (estaBd) {
                    this.msgNitCliente = "El NIT introducido ya fue registrado";
                    this.estadoFormulario = false;

                } else {

                    this.msgNitCliente = "";
                }
            } else {

                this.msgNitCliente = "Debe introducir el NIT";
                this.estadoFormulario = false;

            }
            
            
            if (this.cliente.getFechaNacimiento() == null) {
                this.msgFechaNacimiento = "Debe introducir una fecha de nacimiento";
                this.estadoFormulario = false;
            } else if (this.cliente.getFechaNacimiento() != null) {

                this.edad = 0;
                this.calcularEdadSolicitud();

                if (this.edad < this.politica.getEdadMinima() || this.edad > this.politica.getEdadMaxima()) {
                    this.msgFechaNacimiento = "La edad tiene que estar entre " + this.politica.getEdadMinima() + " y " + this.politica.getEdadMaxima() + " años inclusive";
                    this.estadoFormulario = false;

                } else {
                    this.msgFechaNacimiento = "";
                }
            }
            if (this.cliente.getEstadoFamiliar() == "") {
                this.msgEstadoFamiliar = "Debe seleccionar un estado familiar";
                this.estadoFormulario = false;
            } else {
                this.msgEstadoFamiliar = "";
            }
            if (this.cliente.getTelefonoCliente() == "") {
                this.msgTelefono = "Debe introducir el teléfono";
                this.estadoFormulario = false;
            } else {
                this.msgTelefono = "";
            }
            if (this.cliente.getCelularCliente() == "") {
                this.msgMovil = "Debe introducir el movil";
                this.estadoFormulario = false;
            } else {
                this.msgMovil = "";
            }

            if (this.cliente.getDireccionCliente() == "") {
                this.msgDireccion = "Debe introducir una dirección";
                this.estadoFormulario = false;
            } else {
                this.msgDireccion = "";
            }
            if (this.cliente.getDuiCopiaCliente() == null) {
                this.msgCopiaDui = "Debe introducir la imagen del DUI";
                this.estadoFormulario = false;
            } else {
                this.msgCopiaDui = "";
            }
            if (this.cliente.getNitCopiaCliente() == null) {
                this.msgCopiaNit = "Debe introducir la imagen del NIT";
                this.estadoFormulario = false;
            } else {
                this.msgCopiaNit = "";
            }
        }
        if (this.listaIngreso.size() == 0) {
            this.msgIngreso = "Debe introducir por lo menos un ingreso";
            this.estadoFormulario = false;
        } else {
            this.msgIngreso = "";
        }
        if (this.listaEgreso.size() == 0) {
            this.msgEgreso = "Debe introducir por lo menos un egreso";
            this.estadoFormulario = false;
        } else {
            this.msgEgreso = "";
        }
        if (this.listaActivos.size() == 0) {
            this.msgActivo = "Debe introducir por lo menos un activo";
            this.estadoFormulario = false;
        } else {
            this.msgActivo = "";
        }
        if (this.listaReferencia.size() == 0) {
            this.msgReferencia = "Debe introducir por lo menos una referencia";
            this.estadoFormulario = false;
        } else {
            this.msgReferencia = "";
        }
        if (this.comercio.getNombreComercio() == "") {
            this.msgNombreComercio = "Debe introducir el nombre";
            this.estadoFormulario = false;
        } else {
            this.msgNombreComercio = "";
        }
        if (this.comercio.getGiroComercio() == "") {
            this.msgGiroComercio = "Debe introducir el giro";
            this.estadoFormulario = false;
        } else {
            this.msgGiroComercio = "";
        }
        if (this.comercio.getRegistroComercio() == "") {
            this.msgRegistroComercio = "Debe introducir el numero de registro";
            this.estadoFormulario = false;
        } else {
            this.msgRegistroComercio = "";
        }
        if (this.comercio.getTiempoComercio() == null) {
            this.msgTiempoComercio = "Debe introducir el tiempo";
            this.estadoFormulario = false;
        } else {
            this.msgTiempoComercio = "";
        }
        if (this.comercio.getEmpleadosComercio() == null) {
            this.msgEmpleadosComercio = "Debe introducir el numero de empleados";
            this.estadoFormulario = false;
        } else {
            this.msgEmpleadosComercio = "";
        }
        if (this.comercio.getTelefonoComercio() == "") {
            this.msgTelefonoComercio = "Debe introducir el numero de teléfono";
            this.estadoFormulario = false;
        } else {
            this.msgTelefonoComercio = "";
        }
        if (this.comercio.getDireccionComercio() == "") {
            this.msgDireccionComercio = "Debe introducir la dirección";
            this.estadoFormulario = false;
        } else {
            this.msgDireccionComercio = "";
        }
        if (this.estadoPrendaria == true) {
            if (this.garantia.getDescripcionPrendariaGarantia() == "") {
                this.msgGarantiaDescripcionPrendaria = "Debe ingresar la descripción";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaDescripcionPrendaria = "";
            }
            if (this.garantia.getValorPrendariaGarantia() == null || this.garantia.getValorPrendariaGarantia().compareTo(BigDecimal.ZERO) < 0) {
                this.msgGarantiaValorPrendaria = "Debe ingresar el valor";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaValorPrendaria = "";
            }
            if (this.garantia.getDocumentoPrendariaGarantia() == null) {
                this.msgPrendaria = "Debe introducir el comprobante";
                this.estadoFormulario = false;
            } else {
                this.msgPrendaria = "";
            }
        }
        if (this.estadoHipotecaria == true) {
            if (this.garantia.getHipotecaHipotecariaGarantia() == "") {
                this.msgGarantiaTipoHipoteca = "Debe seleccionar el tipo";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaTipoHipoteca = "";
            }
            if (this.garantia.getValorHipotecaGarantia() == null || this.garantia.getValorHipotecaGarantia().compareTo(BigDecimal.ZERO) < 0) {
                this.msgGarantiaValorHipoteca = "Debe ingresar el valor";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaValorHipoteca = "";
            }
            if (this.garantia.getUbicacionHipotecaGarantia() == "") {
                this.msgGarantiaDescripcionHipoteca = "Debe ingresar la descripción";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaDescripcionHipoteca = "";
            }
        }
        if (this.estadoSolidaria == true) {
            if (this.garantia.getNombresSolidariaGarantia() == "") {
                this.msgNombreFiador = "Debe introducir los nombres";
                this.estadoFormulario = false;
            } else {
                this.msgNombreFiador = "";
            }
            if (this.garantia.getApellidosSolidariaGarantia() == "") {
                this.msgApellidosFiador = "Debe introducir los apellidos";
                this.estadoFormulario = false;
            } else {
                this.msgApellidosFiador = "";
            }
            if (this.garantia.getDuiSolidariaGarantia() == "") {
                this.msgDuiFiador = "Debe introducir el DUI";
                this.estadoFormulario = false;
            } else {
                this.msgDuiFiador = "";
            }
            if (this.garantia.getNitSolidariaGarantia() == "") {
                this.msgNitFiador = "Debe introducir el NIT";
                this.estadoFormulario = false;
            } else {
                this.msgNitFiador = "";
            }
            if (this.garantia.getProfesionSolidariaGarantia() == "") {
                this.msgProfesionFiador = "Debe introducir la profesión";
                this.estadoFormulario = false;
            } else {
                this.msgProfesionFiador = "";
            }
            if (this.garantia.getLugarSolidariaGarantia() == "") {
                this.msgLugarFiador = "Debe introducir el lugar";
                this.estadoFormulario = false;
            } else {
                this.msgLugarFiador = "";
            }
            if (this.garantia.getIngresosSolidariaGarantia() == null) {
                this.msgIngresosFiador = "Debe introducir el valor";
                this.estadoFormulario = false;
            } else {
                this.msgIngresosFiador = "";
            }
            if (this.garantia.getTelefonotSolidariaGarantia() == "") {
                this.msgTelefonotFiador = "Debe introducir el teléfono";
                this.estadoFormulario = false;
            } else {
                this.msgTelefonotFiador = "";
            }
            if (this.garantia.getTelefonofSolidariaGarantia() == "") {
                this.msgTelefonofFiador = "Debe introducir el teléfonoo";
                this.estadoFormulario = false;
            } else {
                this.msgTelefonofFiador = "";
            }
            if (this.garantia.getDuiSolidariaGarantia() == "") {
                this.msgDuiCopiaFiador = "Debe introducir la imagen del DUI";
                this.estadoFormulario = false;
            } else {
                this.msgDuiCopiaFiador = "";
            }
            if (this.garantia.getFotocopiaDuiSolidariaGarantia() == null) {
                this.msgNitCopiaFiador = "Debe introducir la imagen del NIT";
                this.estadoFormulario = false;
            } else {
                this.msgNitCopiaFiador = "";
            }
        }
        if (this.proyecto.getLugar() == "") {
            this.msgLugar = "Debe introducir el lugar";
            this.estadoFormulario = false;
        } else {
            this.msgLugar = "";
        }
        if (this.proyecto.getFecha() == null) {
            this.msgFecha = "Debe introducir la fecha";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }
        if (this.proyecto.getDestinoProyecto() == "") {
            this.msgDestino = "Debe introducir el destino";
            this.estadoFormulario = false;
        } else {
            this.msgDestino = "";
        }
        if ((this.proyecto.getMonto() == null) || (this.proyecto.getMonto().compareTo(this.politica.getMontoMinimo()) <= 0 || this.proyecto.getMonto().compareTo(this.politica.getMontoMaximo()) >= 0)) {
            this.msgMonto = "Debe introducir un monto entre " + this.politica.getMontoMinimo() + " y " + this.politica.getMontoMaximo() + "";
            this.estadoFormulario = false;
        } else {
            this.msgMonto = "";
        }
        if (this.proyecto.getFormaPagoProyecto() == 0) {
            this.msgFormaPago = "Debe seleccionar una forma de pago";
            this.estadoFormulario = false;
        } else {
            this.msgFormaPago = "";
        }
        if (this.proyecto.getPlazo() == null) {
            this.msgPlazo = "Debe introducir el plazo del proyecto";
            this.estadoFormulario = false;
        } else {
            this.msgPlazo = "";
        }

    }

    public void validarFormularioLisiado() {

        this.estadoFormulario = true;
        if (this.existente == true) {
            if (this.clienteSeleccionado.getCodigoCliente() == "") {
                this.msgClienteSeleccionado = "Debe seleccionar el cliente";
                this.estadoFormulario = false;
            } else {
                this.msgClienteSeleccionado = "";
            }
        } else {
            if (this.cliente.getNombreCliente() == "") {
                this.msgNombreCliente = "Debe introducir los nombres";
                this.estadoFormulario = false;
            } else {
                this.msgNombreCliente = "";
            }
            if (this.cliente.getApellidoCliente() == "") {
                this.msgApellidoCliente = "Debe introducir los apellidos";
                this.estadoFormulario = false;
            } else {
                this.msgApellidoCliente = "";
            }
           
            if (this.cliente.getDuiCliente() != "") {

                Boolean estaBd = this.iclienteBo.getDuiCliente(this.cliente.getDuiCliente());

                if (estaBd) {
                    this.msgDuiCliente = "El DUI introducido ya fue registrado";
                    this.estadoFormulario = false;

                } else {

                    this.msgDuiCliente = "";
                }
            } else {

                this.msgDuiCliente = "Debe introducir el DUI";
                this.estadoFormulario = false;

            }
            
            if (this.cliente.getNitCliente() != "") {

                Boolean estaBd = this.iclienteBo.getNitCliente(this.cliente.getNitCliente());

                if (estaBd) {
                    this.msgNitCliente = "El NIT introducido ya fue registrado";
                    this.estadoFormulario = false;

                } else {

                    this.msgNitCliente = "";
                }
            } else {

                this.msgNitCliente = "Debe introducir el NIT";
                this.estadoFormulario = false;

            }
            
            
            if (this.cliente.getFechaNacimiento() == null) {
                this.msgFechaNacimiento = "Debe introducir una fecha de nacimiento";
                this.estadoFormulario = false;
            } else if (this.cliente.getFechaNacimiento() != null) {

                this.edad = 0;
                this.calcularEdadSolicitud();

                if (this.edad < this.politica.getEdadMinima() || this.edad > this.politica.getEdadMaxima()) {
                    this.msgFechaNacimiento = "La edad tiene que estar entre " + this.politica.getEdadMinima() + " y " + this.politica.getEdadMaxima() + " años inclusive";
                    this.estadoFormulario = false;

                } else {
                    this.msgFechaNacimiento = "";
                }
            }
            if (this.cliente.getEstadoFamiliar() == "") {
                this.msgEstadoFamiliar = "Debe seleccionar un estado familiar";
                this.estadoFormulario = false;
            } else {
                this.msgEstadoFamiliar = "";
            }
            if (this.cliente.getTelefonoCliente() == "") {
                this.msgTelefono = "Debe introducir el teléfono";
                this.estadoFormulario = false;
            } else {
                this.msgTelefono = "";
            }
            if (this.cliente.getCelularCliente() == "") {
                this.msgMovil = "Debe introducir el movil";
                this.estadoFormulario = false;
            } else {
                this.msgMovil = "";
            }

            if (this.cliente.getDireccionCliente() == "") {
                this.msgDireccion = "Debe introducir la dirección";
                this.estadoFormulario = false;
            } else {
                this.msgDireccion = "";
            }
            if (this.lisiados.getTipoLesion() == "") {
                this.msgLesion = "Debe introducir el tipo de lesión";
                this.estadoFormulario = false;
            } else {
                this.msgLesion = "";
            }

            if (this.estadoConyugue == true) {

                if (this.lisiados.getNombreConyugue() == "") {
                    this.msgNombreConyugue = "Debe introducir los nombres";
                    this.estadoFormulario = false;
                } else {
                    this.msgNombreConyugue = "";
                }
                if (this.lisiados.getApellidoConyugue() == "") {
                    this.msgApellidoConyugue = "Debe introducir los apellidos";
                    this.estadoFormulario = false;
                } else {
                    this.msgApellidoConyugue = "";
                }
                if (this.lisiados.getEdadConyugue() == null) {
                    this.msgedadConyugue = "Debe introducir la edad";
                    this.estadoFormulario = false;
                } else {
                    this.msgedadConyugue = "";
                }

            }

            if (this.estadoHijos == true) {

                if (this.lisiados.getNumeroHijos() == null) {
                    this.msgHijosConyugue = "Debe introducir el numero de hijos";
                    this.estadoFormulario = false;
                } else {
                    this.msgHijosConyugue = "";
                }
                if (this.lisiados.getNombreHijoMayor() == "") {
                    this.msgNombreMayor = "Debe introducir los nombres";
                    this.estadoFormulario = false;
                } else {
                    this.msgNombreMayor = "";
                }
                if (this.lisiados.getApellidoHijo() == "") {
                    this.msgApellidoMayor = "Debe introducir los apellidos";
                    this.estadoFormulario = false;
                } else {
                    this.msgApellidoMayor = "";
                }
                if (this.lisiados.getEdadHijo() == null) {
                    this.msgEdadMayor = "Debe introducir la edad";
                    this.estadoFormulario = false;
                } else {
                    this.msgEdadMayor = "";
                }

            }

            if (this.cliente.getDuiCopiaCliente() == null) {
                this.msgCopiaDui = "Debe introducir la imagen del DUI";
                this.estadoFormulario = false;
            } else {
                this.msgCopiaDui = "";
            }
            if (this.cliente.getNitCopiaCliente() == null) {
                this.msgCopiaNit = "Debe introducir la imagen del NIT";
                this.estadoFormulario = false;
            } else {
                this.msgCopiaNit = "";
            }
            if (this.lisiados.getCartaLisiado() == null) {
                this.msgConstanciaLisiado = "Debe introducir la imagen de la carta";
                this.estadoFormulario = false;
            } else {
                this.msgConstanciaLisiado = "";
            }
            if (this.lisiados.getExperienciaCrediticia() == true) {
                if (this.lisiados.getInstitucionCrediticia() == "") {
                    this.msgInstitucion = "Debe introducir el nombre";
                    this.estadoFormulario = false;
                } else {
                    this.msgInstitucion = "";
                }
                if (this.lisiados.getMontoCreditoExperiencia() == null || this.lisiados.getMontoCreditoExperiencia().compareTo(BigDecimal.ZERO) <= 0) {
                    this.msgMontoLisiado = "Debe introducir el monto";
                    this.estadoFormulario = false;
                } else {
                    this.msgMontoLisiado = "";
                }
            } else {
                this.msgInstitucion = "";
                this.msgMontoLisiado = "";
            }
        }
        if (this.listaIngreso.size() == 0) {
            this.msgIngreso = "Debe introducir por lo menos un ingreso";
            this.estadoFormulario = false;
        } else {
            this.msgIngreso = "";
        }
        if (this.listaEgreso.size() == 0) {
            this.msgEgreso = "Debe introducir por lo menos un egreso";
            this.estadoFormulario = false;
        } else {
            this.msgEgreso = "";
        }
        if (this.listaActivos.size() == 0) {
            this.msgActivo = "Debe introducir por lo menos un activo";
            this.estadoFormulario = false;
        } else {
            this.msgActivo = "";
        }
        if (this.listaReferencia.size() == 0) {
            this.msgReferencia = "Debe introducir por lo menos una referencia";
            this.estadoFormulario = false;
        } else {
            this.msgReferencia = "";
        }
        if (this.estadoPrendaria == true) {
            if (this.garantia.getDescripcionPrendariaGarantia() == "") {
                this.msgGarantiaDescripcionPrendaria = "Debe introducir la descripción";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaDescripcionPrendaria = "";
            }
            if (this.garantia.getValorPrendariaGarantia() == null || this.garantia.getValorPrendariaGarantia().compareTo(BigDecimal.ZERO) < 0) {
                this.msgGarantiaValorPrendaria = "Debe introducir el valor";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaValorPrendaria = "";
            }
            if (this.garantia.getDocumentoPrendariaGarantia() == null) {
                this.msgPrendaria = "Debe introducir el comprobante";
                this.estadoFormulario = false;
            } else {
                this.msgPrendaria = "";
            }
        }
        if (this.estadoHipotecaria == true) {
            if (this.garantia.getHipotecaHipotecariaGarantia() == "") {
                this.msgGarantiaTipoHipoteca = "Debe seleccionar el tipo";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaTipoHipoteca = "";
            }
            if (this.garantia.getValorHipotecaGarantia() == null || this.garantia.getValorHipotecaGarantia().compareTo(BigDecimal.ZERO) < 0) {
                this.msgGarantiaValorHipoteca = "Debe introducir el valor";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaValorHipoteca = "";
            }
            if (this.garantia.getUbicacionHipotecaGarantia() == "") {
                this.msgGarantiaDescripcionHipoteca = "Debe introducir la descripción";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaDescripcionHipoteca = "";
            }
        }
        if (this.estadoSolidaria == true) {
            if (this.garantia.getNombresSolidariaGarantia() == "") {
                this.msgNombreFiador = "Debe introducir los nombres";
                this.estadoFormulario = false;
            } else {
                this.msgNombreFiador = "";
            }
            if (this.garantia.getApellidosSolidariaGarantia() == "") {
                this.msgApellidosFiador = "Debe introducir los apellidos";
                this.estadoFormulario = false;
            } else {
                this.msgApellidosFiador = "";
            }
            if (this.garantia.getDuiSolidariaGarantia() == "") {
                this.msgDuiFiador = "Debe introducir el DUI";
                this.estadoFormulario = false;
            } else {
                this.msgDuiFiador = "";
            }
            if (this.garantia.getNitSolidariaGarantia() == "") {
                this.msgNitFiador = "Debe introducir el NIT";
                this.estadoFormulario = false;
            } else {
                this.msgNitFiador = "";
            }
            if (this.garantia.getProfesionSolidariaGarantia() == "") {
                this.msgProfesionFiador = "Debe introducir la profesión";
                this.estadoFormulario = false;
            } else {
                this.msgProfesionFiador = "";
            }
            if (this.garantia.getLugarSolidariaGarantia() == "") {
                this.msgLugarFiador = "Debe introducir el lugar";
                this.estadoFormulario = false;
            } else {
                this.msgLugarFiador = "";
            }
            if (this.garantia.getIngresosSolidariaGarantia() == null) {
                this.msgIngresosFiador = "Debe introducir el valor";
                this.estadoFormulario = false;
            } else {
                this.msgIngresosFiador = "";
            }
            if (this.garantia.getTelefonotSolidariaGarantia() == "") {
                this.msgTelefonotFiador = "Debe introducir el teléfono";
                this.estadoFormulario = false;
            } else {
                this.msgTelefonotFiador = "";
            }

            if (this.garantia.getFotocopiaDuiSolidariaGarantia() == null) {
                this.msgDuiCopiaFiador = "Debe introducir la imagen del DUI";
                this.estadoFormulario = false;
            } else {
                this.msgDuiCopiaFiador = "";
            }
            if (this.garantia.getFotocopiaNitSolidariaGarantia() == null) {
                this.msgNitCopiaFiador = "Debe introducir la imagen del NIT";
                this.estadoFormulario = false;
            } else {
                this.msgNitCopiaFiador = "";
            }
        }
        if (this.proyecto.getLugar() == "") {
            this.msgLugar = "Debe introducir el lugar";
            this.estadoFormulario = false;
        } else {
            this.msgLugar = "";
        }
        if (this.proyecto.getFecha() == null) {
            this.msgFecha = "Debe introducir la fecha";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }
        if (this.proyecto.getDestinoProyecto() == "") {
            this.msgDestino = "Debe introducir el destino";
            this.estadoFormulario = false;
        } else {
            this.msgDestino = "";
        }
        if ((this.proyecto.getMonto() == null) || (this.proyecto.getMonto().compareTo(this.politica.getMontoMinimo()) <= 0 || this.proyecto.getMonto().compareTo(this.politica.getMontoMaximo()) >= 0)) {
            this.msgMonto = "Debe introducir un monto entre " + this.politica.getMontoMinimo() + " y " + this.politica.getMontoMaximo() + "";
            this.estadoFormulario = false;
        } else {
            this.msgMonto = "";
        }

        if (this.proyecto.getFormaPagoProyecto() == 0) {
            this.msgFormaPago = "Debe seleccionar una forma de pago";
            this.estadoFormulario = false;
        } else {
            this.msgFormaPago = "";
        }
        if (this.proyecto.getPlazo() == null) {
            this.msgPlazo = "Debe introducir el plazo del proyecto";
            this.estadoFormulario = false;
        } else {
            this.msgPlazo = "";
        }

    }

    public void validarFormularioEmpleado() {

        this.estadoFormulario = true;
        if (this.existente == true) {
            if (this.clienteSeleccionado.getCodigoCliente() == "") {
                this.msgClienteSeleccionado = "Debe seleccionar el cliente";
                this.estadoFormulario = false;
            } else {
                this.msgClienteSeleccionado = "";
            }
        } else {
            if (this.cliente.getNombreCliente() == "") {
                this.msgNombreCliente = "Debe introducir los nombres";
                this.estadoFormulario = false;
            } else {
                this.msgNombreCliente = "";
            }
            if (this.cliente.getApellidoCliente() == "") {
                this.msgApellidoCliente = "Debe introducir los apellidos";
                this.estadoFormulario = false;
            } else {
                this.msgApellidoCliente = "";
            }
           if (this.cliente.getDuiCliente() != "") {

                Boolean estaBd = this.iclienteBo.getDuiCliente(this.cliente.getDuiCliente());

                if (estaBd) {
                    this.msgDuiCliente = "El DUI introducido ya fue registrado";
                    this.estadoFormulario = false;

                } else {

                    this.msgDuiCliente = "";
                }
            } else {

                this.msgDuiCliente = "Debe introducir el DUI";
                this.estadoFormulario = false;

            }
            
            if (this.cliente.getNitCliente() != "") {

                Boolean estaBd = this.iclienteBo.getNitCliente(this.cliente.getNitCliente());

                if (estaBd) {
                    this.msgNitCliente = "El NIT introducido ya fue registrado";
                    this.estadoFormulario = false;

                } else {

                    this.msgNitCliente = "";
                }
            } else {

                this.msgNitCliente = "Debe introducir el NIT";
                this.estadoFormulario = false;

            }
            if (this.cliente.getFechaNacimiento() == null) {
                this.msgFechaNacimiento = "Debe introducir una fecha de nacimiento";
                this.estadoFormulario = false;
            } else if (this.cliente.getFechaNacimiento() != null) {

                this.edad = 0;
                this.calcularEdadSolicitud();

                if (this.edad < this.politica.getEdadMinima() || this.edad > this.politica.getEdadMaxima()) {
                    this.msgFechaNacimiento = "La edad tiene que estar entre " + this.politica.getEdadMinima() + " y " + this.politica.getEdadMaxima() + " años inclusive";
                    this.estadoFormulario = false;

                } else {
                    this.msgFechaNacimiento = "";
                }
            }
            if (this.cliente.getEstadoFamiliar() == "") {
                this.msgEstadoFamiliar = "Debe seleccionar un estado familiar";
                this.estadoFormulario = false;
            } else {
                this.msgEstadoFamiliar = "";
            }
            if (this.cliente.getTelefonoCliente() == "") {
                this.msgTelefono = "Debe introducir el teléfono";
                this.estadoFormulario = false;
            } else {
                this.msgTelefono = "";
            }
            if (this.cliente.getCelularCliente() == "") {
                this.msgMovil = "Debe introducir el teléfono movil";
                this.estadoFormulario = false;
            } else {
                this.msgMovil = "";
            }

            if (this.cliente.getDireccionCliente() == "") {
                this.msgDireccion = "Debe introducir la dirección";
                this.estadoFormulario = false;
            } else {
                this.msgDireccion = "";
            }
            if (this.cliente.getDuiCopiaCliente() == null) {
                this.msgCopiaDui = "Debe introducir la imagen del DUI";
                this.estadoFormulario = false;
            } else {
                this.msgCopiaDui = "";
            }
            if (this.cliente.getNitCopiaCliente() == null) {
                this.msgCopiaNit = "Debe introducir la imagen del NIT";
                this.estadoFormulario = false;
            } else {
                this.msgCopiaNit = "";
            }
        }
        if (this.listaIngreso.size() == 0) {
            this.msgIngreso = "Debe introducir por lo menos un ingreso";
            this.estadoFormulario = false;
        } else {
            this.msgIngreso = "";
        }
        if (this.listaEgreso.size() == 0) {
            this.msgEgreso = "Debe introducir por lo menos un egreso";
            this.estadoFormulario = false;
        } else {
            this.msgEgreso = "";
        }
        if (this.listaActivos.size() == 0) {
            this.msgActivo = "Debe introducir por lo menos un activo";
            this.estadoFormulario = false;
        } else {
            this.msgActivo = "";
        }
        if (this.listaReferencia.size() == 0) {
            this.msgReferencia = "Debe introducir por lo menos una referencia";
            this.estadoFormulario = false;
        } else {
            this.msgReferencia = "";
        }
        if (this.estadoPrendaria == true) {
            if (this.garantia.getDescripcionPrendariaGarantia() == "") {
                this.msgGarantiaDescripcionPrendaria = "Debe introducir la descripción";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaDescripcionPrendaria = "";
            }
            if (this.garantia.getValorPrendariaGarantia() == null || this.garantia.getValorPrendariaGarantia().compareTo(BigDecimal.ZERO) < 0) {
                this.msgGarantiaValorPrendaria = "Debe introducir el valor";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaValorPrendaria = "";
            }
            if (this.garantia.getDocumentoPrendariaGarantia() == null) {
                this.msgPrendaria = "Debe introducir el comprobante";
                this.estadoFormulario = false;
            } else {
                this.msgPrendaria = "";
            }
        }
        if (this.estadoHipotecaria == true) {
            if (this.garantia.getHipotecaHipotecariaGarantia() == "") {
                this.msgGarantiaTipoHipoteca = "Debe seleccionar el tipo";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaTipoHipoteca = "";
            }
            if (this.garantia.getValorHipotecaGarantia() == null || this.garantia.getValorHipotecaGarantia().compareTo(BigDecimal.ZERO) < 0) {
                this.msgGarantiaValorHipoteca = "Debe introducir el valor";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaValorHipoteca = "";
            }
            if (this.garantia.getUbicacionHipotecaGarantia() == "") {
                this.msgGarantiaDescripcionHipoteca = "Debe introducir la descripción";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaDescripcionHipoteca = "";
            }
        }
        if (this.estadoHipotecaria == true) {
            if (this.garantia.getHipotecaHipotecariaGarantia() == "") {
                this.msgGarantiaTipoHipoteca = "Debe seleccionar el tipo";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaTipoHipoteca = "";
            }
            if (this.garantia.getValorHipotecaGarantia() == null || this.garantia.getValorHipotecaGarantia().compareTo(BigDecimal.ZERO) < 0) {
                this.msgGarantiaValorHipoteca = "Debe introducir el valor";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaValorHipoteca = "";
            }
            if (this.garantia.getUbicacionHipotecaGarantia() == "") {
                this.msgGarantiaDescripcionHipoteca = "Debe introducir la descripción";
                this.estadoFormulario = false;
            } else {
                this.msgGarantiaDescripcionHipoteca = "";
            }
        }
        if (this.estadoSolidaria == true) {
            if (this.garantia.getNombresSolidariaGarantia() == "") {
                this.msgNombreFiador = "Debe introducir los nombres";
                this.estadoFormulario = false;
            } else {
                this.msgNombreFiador = "";
            }
            if (this.garantia.getApellidosSolidariaGarantia() == "") {
                this.msgApellidosFiador = "Debe introducir los apellidos";
                this.estadoFormulario = false;
            } else {
                this.msgApellidosFiador = "";
            }
            if (this.garantia.getDuiSolidariaGarantia() == "") {
                this.msgDuiFiador = "Debe introducir el DUI";
                this.estadoFormulario = false;
            } else {
                this.msgDuiFiador = "";
            }
            if (this.garantia.getNitSolidariaGarantia() == "") {
                this.msgNitFiador = "Debe introducir el NIT";
                this.estadoFormulario = false;
            } else {
                this.msgNitFiador = "";
            }
            if (this.garantia.getProfesionSolidariaGarantia() == "") {
                this.msgProfesionFiador = "Debe introducir la profesión";
                this.estadoFormulario = false;
            } else {
                this.msgProfesionFiador = "";
            }
            if (this.garantia.getLugarSolidariaGarantia() == "") {
                this.msgLugarFiador = "Debe introducir el lugar";
                this.estadoFormulario = false;
            } else {
                this.msgLugarFiador = "";
            }
            if (this.garantia.getIngresosSolidariaGarantia() == null) {
                this.msgIngresosFiador = "Debe introducir el valor";
                this.estadoFormulario = false;
            } else {
                this.msgIngresosFiador = "";
            }

            if (this.garantia.getTelefonofSolidariaGarantia() == "") {
                this.msgTelefonofFiador = "Debe introducir el teléfono";
                this.estadoFormulario = false;
            } else {
                this.msgTelefonofFiador = "";
            }
            if (this.garantia.getFotocopiaDuiSolidariaGarantia() == null) {
                this.msgDuiCopiaFiador = "Debe introducir la imagen del DUI";
                this.estadoFormulario = false;
            } else {
                this.msgDuiCopiaFiador = "";
            }
            if (this.garantia.getFotocopiaNitSolidariaGarantia() == null) {
                this.msgNitCopiaFiador = "Debe introducir la imagen del NIT";
                this.estadoFormulario = false;
            } else {
                this.msgNitCopiaFiador = "";
            }
        }
        if (this.proyecto.getLugar() == "") {
            this.msgLugar = "Debe introducir el lugar";
            this.estadoFormulario = false;
        } else {
            this.msgLugar = "";
        }
        if (this.proyecto.getFecha() == null) {
            this.msgFecha = "Debe introducir la fecha";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }
        if (this.proyecto.getDestinoProyecto() == "") {
            this.msgDestino = "Debe introducir el destino";
            this.estadoFormulario = false;
        } else {
            this.msgDestino = "";
        }
        if ((this.proyecto.getMonto() == null) || (this.proyecto.getMonto().compareTo(this.politica.getMontoMinimo()) <= 0 || this.proyecto.getMonto().compareTo(this.politica.getMontoMaximo()) >= 0)) {
            this.msgMonto = "Debe introducir un monto entre " + this.politica.getMontoMinimo() + " y " + this.politica.getMontoMaximo() + "";
            this.estadoFormulario = false;
        } else {
            this.msgMonto = "";
        }
        if (this.proyecto.getFormaPagoProyecto() == 0) {
            this.msgFormaPago = "Debe seleccionar una forma de pago";
            this.estadoFormulario = false;
        } else {
            this.msgFormaPago = "";
        }
        if (this.proyecto.getPlazo() == null) {
            this.msgPlazo = "Debe introducir el plazo del proyecto";
            this.estadoFormulario = false;
        } else {
            this.msgPlazo = "";
        }
        if (this.trabajo.getNombreEmpresa() == "") {
            this.msgNombreEmpresa = "Debe introducir el nombre";
            this.estadoFormulario = false;
        } else {
            this.msgNombreEmpresa = "";
        }
        if (this.trabajo.getTelefonoEmpresa() == "") {
            this.msgTelefonoEmpresa = "Debe introducir el teléfono";
            this.estadoFormulario = false;
        } else {
            this.msgTelefonoEmpresa = "";
        }
        if (this.trabajo.getDireccionEmpresa() == "") {
            this.msgDireccionEmpresa = "Debe introducir la dirección";
            this.estadoFormulario = false;
        } else {
            this.msgDireccionEmpresa = "";
        }
        if (this.trabajo.getCargoEmpresa() == "") {
            this.msgCargoEmpresa = "Debe introducir el cargo desempeñado";
            this.estadoFormulario = false;
        } else {
            this.msgCargoEmpresa = "";
        }
        if (this.trabajo.getTiempoEmpresa() == null || this.trabajo.getTiempoEmpresa() < 1) {
            this.msgTiempoEmpresa = "Debe introducir el tiempo";
            this.estadoFormulario = false;
        } else {
            this.msgTiempoEmpresa = "";
        }
        if (this.trabajo.getConstancia() == null) {
            this.msgConstanciaEmpresa = "Debe introducir la constancia del trabajo";
            this.estadoFormulario = false;
        } else {
            this.msgConstanciaEmpresa = "";
        }

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

    public void guardarConstancia(FileUploadEvent event) throws IOException {

        String tipo = event.getFile().getContentType();
        byte[] content = event.getFile().getContents();
        String b64 = Base64.encode(content);
        String guardar = "data:" + tipo + ";base64," + b64;
        this.trabajo.setConstancia(guardar);
        this.showConstancia = false;
        FacesMessage message = new FacesMessage("Constancia cargada");
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

    public void guardarDuiCliente(FileUploadEvent event) throws IOException {

        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.cliente.setDuiCopiaCliente(guardarP);
        this.showDuiCliente = false;
        FacesMessage message = new FacesMessage("DUI cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarDuiFiador(FileUploadEvent event) throws IOException {

        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.garantia.setFotocopiaDuiSolidariaGarantia(guardarP);
        this.showDuiFiador = false;
        FacesMessage message = new FacesMessage("DUI cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarNitFiador(FileUploadEvent event) throws IOException {

        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.garantia.setFotocopiaNitSolidariaGarantia(guardarP);
        this.showNitFiador = false;
        FacesMessage message = new FacesMessage("Nit cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarRespaldoPrendaria(FileUploadEvent event) throws IOException {

        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.garantia.setDocumentoPrendariaGarantia(guardarP);
        this.showRespaldoPrendaria = false;
        FacesMessage message = new FacesMessage("Respaldo cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarNitCliente(FileUploadEvent event) throws IOException {

        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.cliente.setNitCopiaCliente(guardarP);
        this.showNitCliente = false;
        FacesMessage message = new FacesMessage("Nit cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarBalanceModificar(FileUploadEvent event) throws IOException {

        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.cooperativaSeleccionada.setBalanceGeneral(guardarP);
        this.showBalance = true;
        FacesMessage message = new FacesMessage("Balance cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarAcuerdo(FileUploadEvent event) throws IOException {

        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.cooperativa.setAcuerdoSolicitud(guardarP);
        this.showAprobacion = false;
        FacesMessage message = new FacesMessage("Acuerdo cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarAcuerdoModificar(FileUploadEvent event) throws IOException {

        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.cooperativaSeleccionada.setAcuerdoSolicitud(guardarP);
        this.showAprobacion = false;
        FacesMessage message = new FacesMessage("Acuerdo cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void modificarCliente() {

        this.iclienteBo.update(this.clienteSeleccionado);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente modificado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.estadoFormulario = false;
        super.enableShowData();

    }

    public void enviarSolicitud() {

        this.proyectoSeleccionado.setEstado(2);
        System.out.println(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        this.iproyectoBo.update(this.proyectoSeleccionado);

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Enviar solicitud");
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

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud enviada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        mostrarSolicitudes();
        super.enableShowData();

    }

    public void darDeBaja() {

        this.Entidadeleccionada.setEstadoEntidad(false);
        ientidadBo.update(this.Entidadeleccionada);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cooperativa dada de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaEntidades = ientidadBo.listTEndidad();
        super.enableShowData();

    }

    public void darDeAlta() {

        this.Entidadeleccionada.setEstadoEntidad(true);
        ientidadBo.update(this.Entidadeleccionada);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cooperativa dada de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaEntidades = ientidadBo.listTEndidad();
        super.enableShowData();

    }

    public void crearCooperativa() {

        this.mostrarCalculos();
        this.proyecto.setEstado(1);
        this.proyecto.setTPolitica(this.politica);
        this.iproyectoBo.create(proyecto);
        this.entidadProyecto.setTEntidad(this.Entidadeleccionada);
        this.entidadProyecto.setTProyecto(this.proyecto);
        this.ientidadProyectoBo.create(entidadProyecto);
        this.cooperativa.setTProyecto(this.proyecto);
        this.icooperativaBo.create(this.cooperativa);
        if (this.estadoPrendaria == true) {
            this.garantia.setTTipoGarantia(new TTipoGarantia(3));
        } else if (this.estadoHipotecaria == true) {
            this.garantia.setTTipoGarantia(new TTipoGarantia(1));
        } else {
            this.garantia.setTTipoGarantia(new TTipoGarantia(2));
        }
        this.garantia.setTProyecto(this.proyecto);
        this.igarantiaBo.create(this.garantia);

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Agregar solicitud para crédito de cooperativa");
        String formaPagosCredito = "";
        if (this.proyecto.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyecto.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyecto.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyecto.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }
        auxBitacora.setDatosBitacora("Crédito:" + this.proyecto.getCodigoProyecto()
                + ", Monto: " + this.proyecto.getMonto()
                + ", Plazo: " + this.proyecto.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyecto.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        this.limpiarCooperativas();
        correlativo();
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud guardada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    public void crearAgropecuario() {

        this.proyecto.setEstado(1);
        this.proyecto.setTPolitica(this.politica);
        this.iproyectoBo.create(this.proyecto);
        if (this.estadoHabilitar == true) {
            this.clienteSeleccionado.setTipoCliente1(1);//normal
            this.iclienteBo.update(this.clienteSeleccionado);
            this.clienteProyecto.setTCliente(this.clienteSeleccionado);
        } else {
            this.cliente.setTipoCliente1(1);//normal
            this.iclienteBo.create(this.cliente);
            this.clienteProyecto.setTCliente(cliente);
        }
        this.clienteProyecto.setTProyecto(proyecto);
        this.iclienteProyectoBo.create(clienteProyecto);
        this.agropecuario2.setTProyecto(proyecto);
        this.iagropecuarioBo.create(agropecuario2);
        for (int i = 0; i < this.listaIngreso.size(); i++) {
            this.listaIngreso.get(i).setTProyecto(proyecto);
            this.iingresoBo.create(this.listaIngreso.get(i));
        }
        for (int i = 0; i < this.listaEgreso.size(); i++) {
            this.listaEgreso.get(i).setTProyecto(proyecto);
            this.iegresoBo.create(this.listaEgreso.get(i));
        }
        for (int i = 0; i < this.listaActivos.size(); i++) {
            this.listaActivos.get(i).setTProyecto(proyecto);
            this.iactivoBo.create(this.listaActivos.get(i));
        }
        if (this.estadoPrendaria == true) {
            this.garantia.setTTipoGarantia(new TTipoGarantia(3));
        } else if (this.estadoHipotecaria == true) {
            this.garantia.setTTipoGarantia(new TTipoGarantia(1));
        } else {
            this.garantia.setTTipoGarantia(new TTipoGarantia(2));
        }
        this.garantia.setTProyecto(this.proyecto);
        this.igarantiaBo.create(this.garantia);
        for (int i = 0; i < this.listaReferencia.size(); i++) {
            this.listaReferencia.get(i).setTProyecto(proyecto);
            this.ireferenciaBo.create(this.listaReferencia.get(i));
        }

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Agregar solicitud para crédito agropecuario");
        String formaPagosCredito = "";

        if (this.proyecto.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyecto.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyecto.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyecto.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }
        auxBitacora.setDatosBitacora("Crédito:" + this.proyecto.getCodigoProyecto()
                + ", Monto: " + this.proyecto.getMonto()
                + ", Plazo: " + this.proyecto.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyecto.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud guardada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.limpiarAgropecuario();
        correlativo();

    }

    public void crearEmpleado() {

        TTipoCredito tipoCredito = new TTipoCredito();
        tipoCredito.setIdTipoCredito(1);
        this.proyecto.setEstado(1);
        this.cliente.setTipoCliente2(1);
        this.proyecto.setTTipoCredito(tipoCredito);
        this.proyecto.setTPolitica(this.politica);
        this.iproyectoBo.create(this.proyecto);
        if (this.estadoHabilitar == true) {
            this.clienteSeleccionado.setTipoCliente3(1);//empleado
            this.iclienteBo.update(this.clienteSeleccionado);
            this.clienteProyecto.setTCliente(this.clienteSeleccionado);
            this.trabajo.setTCliente(this.clienteSeleccionado);
        } else {
            this.cliente.setTipoCliente3(1);//empleado
            this.iclienteBo.create(this.cliente);
            this.trabajo.setTCliente(this.cliente);
            this.clienteProyecto.setTCliente(cliente);
        }
        this.itrabajoBo.create(this.trabajo);
        this.clienteProyecto.setTProyecto(proyecto);
        this.iclienteProyectoBo.create(clienteProyecto);
        for (int i = 0; i < this.listaIngreso.size(); i++) {
            this.listaIngreso.get(i).setTProyecto(proyecto);
            this.iingresoBo.create(this.listaIngreso.get(i));
        }
        for (int i = 0; i < this.listaEgreso.size(); i++) {
            this.listaEgreso.get(i).setTProyecto(proyecto);
            this.iegresoBo.create(this.listaEgreso.get(i));
        }
        for (int i = 0; i < this.listaActivos.size(); i++) {
            this.listaActivos.get(i).setTProyecto(proyecto);
            this.iactivoBo.create(this.listaActivos.get(i));
        }
        if (this.estadoPrendaria == true) {
            this.garantia.setTTipoGarantia(new TTipoGarantia(3));
        } else if (this.estadoHipotecaria == true) {
            this.garantia.setTTipoGarantia(new TTipoGarantia(1));
        } else {
            this.garantia.setTTipoGarantia(new TTipoGarantia(2));
        }
        this.garantia.setTProyecto(this.proyecto);
        this.igarantiaBo.create(this.garantia);
        for (int i = 0; i < this.listaReferencia.size(); i++) {
            this.listaReferencia.get(i).setTProyecto(proyecto);
            this.ireferenciaBo.create(this.listaReferencia.get(i));
        }

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Agregar solicitud para crédito de empleado");
        String formaPagosCredito = "";
        if (this.proyecto.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyecto.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyecto.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyecto.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }
        auxBitacora.setDatosBitacora("Crédito:" + this.proyecto.getCodigoProyecto()
                + ", Monto: " + this.proyecto.getMonto()
                + ", Plazo: " + this.proyecto.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyecto.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        this.limpiarEmpleado();
        correlativo();
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud guardada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    public void crearComercio() {

        TTipoCredito tipoCredito = new TTipoCredito();
        this.proyecto.setEstado(1);
        tipoCredito.setIdTipoCredito(7);
        this.proyecto.setEstado(1);
        this.proyecto.setTTipoCredito(tipoCredito);
        this.proyecto.setTPolitica(this.politica);
        this.iproyectoBo.create(this.proyecto);
        if (this.estadoHabilitar == true) {
            this.clienteSeleccionado.setTipoCliente1(1);
            this.iclienteBo.update(this.clienteSeleccionado);
            this.clienteProyecto.setTCliente(this.clienteSeleccionado);
        } else {
            this.cliente.setTipoCliente1(1);
            this.iclienteBo.create(this.cliente);
            this.clienteProyecto.setTCliente(cliente);
        }
        this.clienteProyecto.setTProyecto(proyecto);
        this.iclienteProyectoBo.create(clienteProyecto);
        this.comercio.setTProyecto(this.proyecto);
        this.icomercioBo.create(comercio);
        for (int i = 0; i < this.listaIngreso.size(); i++) {
            this.listaIngreso.get(i).setTProyecto(proyecto);
            this.iingresoBo.create(this.listaIngreso.get(i));
        }
        for (int i = 0; i < this.listaEgreso.size(); i++) {
            this.listaEgreso.get(i).setTProyecto(proyecto);
            this.iegresoBo.create(this.listaEgreso.get(i));
        }
        for (int i = 0; i < this.listaActivos.size(); i++) {
            this.listaActivos.get(i).setTProyecto(proyecto);
            this.iactivoBo.create(this.listaActivos.get(i));
        }
        if (this.estadoPrendaria == true) {
            this.garantia.setTTipoGarantia(new TTipoGarantia(3));
        } else if (this.estadoHipotecaria == true) {
            this.garantia.setTTipoGarantia(new TTipoGarantia(1));
        } else {
            this.garantia.setTTipoGarantia(new TTipoGarantia(2));
        }
        this.garantia.setTProyecto(this.proyecto);
        this.igarantiaBo.create(this.garantia);
        for (int i = 0; i < this.listaReferencia.size(); i++) {
            this.listaReferencia.get(i).setTProyecto(proyecto);
            this.ireferenciaBo.create(this.listaReferencia.get(i));
        }

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Agregar solicitud de crédito para comercio");
        String formaPagosCredito = "";
        if (this.proyecto.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyecto.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyecto.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyecto.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }
        auxBitacora.setDatosBitacora("Crédito:" + this.proyecto.getCodigoProyecto()
                + ", Monto: " + this.proyecto.getMonto()
                + ", Plazo: " + this.proyecto.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyecto.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud guardada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.limpiarComercio();
        correlativo();

    }

    public void crearLisiado() {

        this.mostrarCalculos();
        this.proyecto.setTTipoCredito(new TTipoCredito(5));
        this.proyecto.setEstado(1);
        this.proyecto.setTPolitica(this.politica);
        this.iproyectoBo.create(proyecto);
        if (this.estadoHabilitar == true) {
            this.clienteSeleccionado.setTipoCliente2(1);
            this.iclienteBo.update(this.clienteSeleccionado);
            this.clienteProyecto.setTCliente(this.clienteSeleccionado);
            this.lisiados.setTCliente(this.clienteSeleccionado);
        } else {
            this.cliente.setTipoCliente2(1);
            this.iclienteBo.create(this.cliente);
            this.clienteProyecto.setTCliente(cliente);
            this.lisiados.setTCliente(this.cliente);
        }
        this.clienteProyecto.setTProyecto(proyecto);
        this.iclienteProyectoBo.create(clienteProyecto);
        this.ilisiadoBo.create(lisiados);
        if (this.estadoPrendaria == true) {
            this.garantia.setTTipoGarantia(new TTipoGarantia(3));
        } else if (this.estadoHipotecaria == true) {
            this.garantia.setTTipoGarantia(new TTipoGarantia(1));
        } else {
            this.garantia.setTTipoGarantia(new TTipoGarantia(2));
        }
        this.garantia.setTProyecto(this.proyecto);
        this.igarantiaBo.create(this.garantia);
        for (int i = 0; i < this.listaActivos.size(); i++) {
            this.listaActivos.get(i).setTProyecto(proyecto);
            this.iactivoBo.create(this.listaActivos.get(i));
        }
        for (int i = 0; i < this.listaIngreso.size(); i++) {
            this.listaIngreso.get(i).setTProyecto(proyecto);
            this.iingresoBo.create(this.listaIngreso.get(i));
        }
        for (int i = 0; i < this.listaEgreso.size(); i++) {
            this.listaEgreso.get(i).setTProyecto(proyecto);
            this.iegresoBo.create(this.listaEgreso.get(i));
        }
        for (int i = 0; i < this.listaReferencia.size(); i++) {
            this.listaReferencia.get(i).setTProyecto(proyecto);
            this.ireferenciaBo.create(this.listaReferencia.get(i));
        }

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Agregar solicitud de crédito para lisiado");
        String formaPagosCredito = "";
        if (this.proyecto.getFormaPagoProyecto() == 1) {
            formaPagosCredito = "Mensual";
        }
        if (this.proyecto.getFormaPagoProyecto() == 2) {
            formaPagosCredito = "Trimestral";
        }
        if (this.proyecto.getFormaPagoProyecto() == 3) {
            formaPagosCredito = "Semestral";
        }
        if (this.proyecto.getFormaPagoProyecto() == 4) {
            formaPagosCredito = "Anual";
        }
        auxBitacora.setDatosBitacora("Crédito:" + this.proyecto.getCodigoProyecto()
                + ", Monto: " + this.proyecto.getMonto()
                + ", Plazo: " + this.proyecto.getPlazo()
                + ", Forma de pago: " + formaPagosCredito
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.proyecto.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        limpiarLisiado();
        correlativo();

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud guardada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    public void enableShowDataBean() {

        super.enableShowData();

    }

    public void mostrarEntidades() {

        this.listaEntidades = ientidadBo.listTEndidad();

    }

    public void cargarPosesiones() {

        this.listaPosesiones.add(this.getPosesiones());
        this.setPosesiones("");

    }

    public void cargarEgresos() {

        this.listaEgresos.add(this.egresos);
        this.egresos = new TEgreso();

    }

    public void cargarReferencias() {

        this.listaReferencia.add(this.referencias);
        this.referencias = new TReferencia();

    }

    public void enableShowImagen() {
        this.showImagen = true;
    }

    public void enableShowImagenCarta() {
        this.showImagenCarta = true;
    }

    public void ocultar() {

        ocultar = !this.lisiados.getExperienciaCrediticia().equals(true);
        ocultarEgresos = !this.banderaEgreso.equals(true);
        if (ocultar == false) {
            this.lisiados.setMontoCreditoExperiencia(BigDecimal.ZERO);
            this.lisiados.setInstitucionCrediticia("");
        }
        if (ocultarEgresos == true) {
            egresos = new TEgreso();
            this.listaEgresos.clear();
        }

    }

    public void eliminar(int i, int opc) {

        if (opc == 0) {
            this.listaPosesiones.remove(i);
        }
        if (opc == 1) {
            this.listaEgresos.remove(i);
        }
        if (opc == 2) {
            this.listaReferencia.remove(i);
        }

    }

    public void editar(int i, int opc) {

        if (opc == 0) {
            this.posesiones = listaPosesiones.get(i);
            this.listaPosesiones.remove(i);
        }
        if (opc == 1) {
            this.egresos.setDescripcion(listaEgresos.get(i).getDescripcion());
            this.egresos.setValor(this.listaEgresos.get(i).getValor());
            this.listaEgresos.remove(i);
        }
        if (opc == 2) {
            this.referencias.setNombre(this.listaReferencia.get(i).getNombre());
            this.referencias.setTelefono(this.listaReferencia.get(i).getTelefono());
            this.listaReferencia.remove(i);
        }
    }

    public void correlativo() {

        Integer correlativo = this.iproyectoBo.getCorrelativo() + 1;
        if (correlativo < 10) {
            this.proyecto.setCodigo("SCT-0000" + correlativo);
        } else if (correlativo < 100) {
            this.proyecto.setCodigo("SCT-000" + correlativo);
        } else if (correlativo < 1000) {
            this.proyecto.setCodigo("SCT-00" + correlativo);
        } else if (correlativo < 10000) {
            this.proyecto.setCodigo("SCT-0" + correlativo);
        } else {
            this.proyecto.setCodigo("SCT" + correlativo);
        }

    }

    public void cargarVistaCooperativa() {

        this.trabajo = new TTrabajo();
        this.lisiados = new TLisiado();
        this.garantiaSeleccionada = new TGarantia();
        this.cooperativaSeleccionada = new TCooperativa();
        this.clienteSeleccionado = new TCliente();
        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        this.tipoCred = this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito();
        this.cooperativaSeleccionada = this.icooperativaBo.getTCooperativa(this.proyectoSeleccionado.getIdproyecto());
        this.garantiaSeleccionada = this.igarantiaBo.getTGarantia(this.proyectoSeleccionado.getIdproyecto());
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
        this.verDatosCooperativa = true;
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
        this.listaActivoSeleccionada = this.iactivoBo.listActivo(this.proyectoSeleccionado.getIdproyecto());
        this.totalActivo = BigDecimal.ZERO;
        for (int i = 0; i < this.listaActivoSeleccionada.size(); i++) {
            this.totalActivo = this.totalActivo.add(this.listaActivoSeleccionada.get(i).getValor());
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
        this.verDatosLisiado = true;
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
        this.verDatosAgropecuario = true;
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
        this.verDatosComercio = true;
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
        this.verDatosEmpleado = true;
        calcularEdad();
        calculosVista();

    }

    public String cambiar(Integer valor) {

        if (valor == 8) {
            return this.tipo = "Crédito para capital de trabajo agropecuario";
        } else if (valor == 9) {
            return this.tipo = "Crédito para inversión agropecuario";
        } else if (valor == 7) {
            return this.tipo = "Crédito para comercio";
        } else if (valor == 6) {
            return this.tipo = "Crédito personal";
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

    public void llenarReferencia() {

        Boolean validado = true;
        if (this.referencia.getNombre() == "") {
            this.msgReferenciaNombre = "Debe introducir el nombre";
            validado = false;
        } else {
            this.msgReferenciaNombre = "";
        }
        if (this.referencia.getTelefono() == "") {
            this.msgReferenciaNumero = "Debe introducir el telefono";
            validado = false;
        } else {
            this.msgReferenciaNumero = "";
        }
        if (validado == true) {
            this.listaReferencia.add(this.referencia);
            this.referencia = new TReferencia();
        }

    }

    public void removerReferencia(int posicion) {
        this.listaReferencia.remove(posicion);
    }

    public void llenarActivos() {

        Boolean validado = true;
        if (this.activo.getNombre() == "") {
            this.msgActivoNombre = "Debe introducir el nombre";
            validado = false;
        } else {
            this.msgActivoNombre = "";
        }
        if (this.activo.getValor() == null) {
            this.msgActivoValor = "Debe introducir el valor";
            validado = false;
        } else {
            this.msgActivoValor = "";
        }
        if (this.activo.getDescripcion() == "") {
            this.msgActivoDescripcion = "Debe introducir la descripcion";
            validado = false;
        } else {
            this.msgActivoDescripcion = "";
        }
        if (validado == true) {
            this.listaActivos.add(this.activo);
            this.activo = new TActivo();
        }

    }

    public void removerActivos(int posicion) {

        this.listaActivos.remove(posicion);

    }

    public void llenarEgreso() {

        Boolean validado = true;
        if (this.egreso.getNombre() == "") {
            this.msgEgresoNombre = "Debe introducir el nombre";
            validado = false;
        } else {
            this.msgEgresoNombre = "";
        }
        if (this.egreso.getValor() == null) {
            this.msgEgresoValor = "Debe introducir el valor";
            validado = false;
        } else {
            this.msgEgresoValor = "";
        }
        if (this.egreso.getDescripcion() == "") {
            this.msgEgresoDescripcion = "Debe introducir la descripcion";
            validado = false;
        } else {
            this.msgEgresoDescripcion = "";
        }
        if (validado == true) {
            this.listaEgreso.add(this.egreso);
            this.egreso = new TEgreso();
        }

    }

    public void removerEgresos(int posicion) {

        this.listaEgreso.remove(posicion);

    }

    public void llenarIngreso() {

        Boolean validado = true;
        if (this.ingreso.getNombre() == "") {
            this.msgIngresoNombre = "Debe introducir el nombre";
            validado = false;
        } else {
            this.msgIngresoNombre = "";
        }
        if (this.ingreso.getValor() == null) {
            this.msgIngresoValor = "Debe introducir el valor";
            validado = false;
        } else {
            this.msgIngresoValor = "";
        }
        if (this.ingreso.getDescripcion() == "") {
            this.msgIngresoDescripcion = "Debe introducir la descripcion";
            validado = false;
        } else {
            this.msgIngresoDescripcion = "";
        }
        if (validado == true) {
            this.listaIngreso.add(this.ingreso);
            this.ingreso = new TIngreso();
        }

    }

    public void removerIngreso(int posicion) {

        this.listaIngreso.remove(posicion);

    }

    public void llenarGarantia() {

        this.listaGarantia.add(this.garantia);
        this.garantia = new TGarantia();

    }

    public void removerGarantia(int posicion) {

        this.listaGarantia.remove(posicion);

    }

    public void updateListaCreditos() {

        this.listaEntidadProyectosReducida.clear();
        this.listaClienteProyectoReducida.clear();
        if (this.tipoCredito == 0) {
        }
        if (this.tipoCredito == 2) {
            this.listaEntidadProyectosReducida = this.ientidadProyectoBo.listTEndidadProyectos(2);
        }
        if (this.tipoCredito == 8) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyecto(8);
        }
        if (this.tipoCredito == 6) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyecto(6);
        }
        if (this.tipoCredito == 1) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyecto(7);
        }
        if (this.tipoCredito == 7) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyecto(1);
        }
        if (this.tipoCredito == 5) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyecto(5);
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
            this.mostrarResultadoCooperativas = false;
            this.mostrarResultadoPersona = false;
            this.mostrarEmpleados = false;
            this.tipoResultado = 0;

        }
        if (this.tipoCredito == 2) {
            this.mostrarCooperativas = true;
            this.mostrarPersonas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarResultadoCooperativas = false;
            this.mostrarResultadoPersona = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = false;
            this.tipoResultado = 0;
        }
        if (this.tipoCredito == 8) {
            this.mostrarAgropecuarios = true;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarResultadoCooperativas = false;
            this.mostrarResultadoPersona = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = false;
            this.tipoResultado = 0;
        }
        if (this.tipoCredito == 6) {
            this.mostrarPersonas = true;
            this.mostrarCooperativas = false;
            this.mostrarComercio = false;
            this.mostrarLisiados = false;
            this.mostrarAgropecuarios = false;
            this.mostrarResultadoCooperativas = false;
            this.mostrarResultadoPersona = false;
            this.mostrarResumen = false;
            this.tipoResultado = 0;
        }
        if (this.tipoCredito == 1) {
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarComercio = true;
            this.mostrarLisiados = false;
            this.mostrarAgropecuarios = false;
            this.mostrarResultadoCooperativas = false;
            this.mostrarResultadoPersona = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = false;
            this.tipoResultado = 0;
        }
        if (this.tipoCredito == 7) {
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarComercio = false;
            this.mostrarLisiados = false;
            this.mostrarAgropecuarios = false;
            this.mostrarResultadoCooperativas = false;
            this.mostrarResultadoPersona = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = true;
            this.tipoResultado = 0;
        }
        if (this.tipoCredito == 5) {
            this.mostrarLisiados = true;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarComercio = false;
            this.mostrarResultadoCooperativas = false;
            this.mostrarResultadoPersona = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = false;
            this.tipoResultado = 0;
        }

    }

    public void mostrarResultados() {

        this.limpiarVer();
        this.listaEntidadProyectosAprobadas.clear(); //todas
        this.listaEntidadProyectosNoAprobadas.clear();
        this.listaClienteProyectoAprobadas.clear();//todas
        this.listaClienteProyectoNoAprobadas.clear();
        this.mostrarCooperativas = false;
        this.mostrarPersonas = false;
        this.mostrarAgropecuarios = false;
        this.mostrarLisiados = false;
        this.mostrarComercio = false;
        this.mostrarResumen = false;
        this.mostrarEmpleados = false;
        if (this.tipoCredito == 2) {
            this.mostrarResultadoCooperativas = true;
            this.mostrarResultadoPersona = false;
            if (this.tipoResultado == 0) {
                mostrarSolicitudes();
            } else if (this.tipoResultado == 1) {
                this.listaEntidadProyectosAprobadas = this.ientidadProyectoBo.listTEndidadProyectosAprovados(0);
            } else {
                this.listaEntidadProyectosAprobadas = this.ientidadProyectoBo.listTEndidadProyectosNoAprobados();
            }
        } else if (this.tipoCredito == 5) {
            this.mostrarResultadoCooperativas = false;
            this.mostrarResultadoPersona = true;
            if (this.tipoResultado == 0) {
                mostrarSolicitudes();
            } else if (this.tipoResultado == 1) {
                this.listaClienteProyectoAprobadas = this.iclienteProyectoBo.listTClienteProyectoAprovados(5);
            } else {
                this.listaClienteProyectoAprobadas = this.iclienteProyectoBo.listTClienteProyectoNoAprobados(5);
            }
        } else if (this.tipoCredito == 1) {
            this.mostrarResultadoCooperativas = false;
            this.mostrarResultadoPersona = true;
            if (this.tipoResultado == 0) {
                mostrarSolicitudes();
            } else if (this.tipoResultado == 1) {
                this.listaClienteProyectoAprobadas = this.iclienteProyectoBo.listTClienteProyectoAprovados(7);
            } else {
                this.listaClienteProyectoAprobadas = this.iclienteProyectoBo.listTClienteProyectoNoAprobados(7);
            }
        } else if (this.tipoCredito == 7) {
            this.mostrarResultadoCooperativas = false;
            this.mostrarResultadoPersona = true;
            if (this.tipoResultado == 0) {
                mostrarSolicitudes();
            } else if (this.tipoResultado == 1) {
                this.listaClienteProyectoAprobadas = this.iclienteProyectoBo.listTClienteProyectoAprovados(1);
            } else {
                this.listaClienteProyectoAprobadas = this.iclienteProyectoBo.listTClienteProyectoNoAprobados(1);
            }
        } else if (this.tipoCredito == 6) {
            this.mostrarResultadoCooperativas = false;
            this.mostrarResultadoPersona = true;
            if (this.tipoResultado == 0) {
                mostrarSolicitudes();
            } else if (this.tipoResultado == 1) {
                this.listaClienteProyectoAprobadas = this.iclienteProyectoBo.listTClienteProyectoAprovados(6);
            } else {
                this.listaClienteProyectoAprobadas = this.iclienteProyectoBo.listTClienteProyectoNoAprobados(6);
            }
        } else if (this.tipoCredito == 8) {
            this.mostrarResultadoCooperativas = false;
            this.mostrarResultadoPersona = true;
            if (this.tipoResultado == 0) {
                mostrarSolicitudes();
            } else if (this.tipoResultado == 1) {
                this.listaClienteProyectoAprobadas = this.iclienteProyectoBo.listTClienteProyectoAprovados(8);
            } else {
                this.listaClienteProyectoAprobadas = this.iclienteProyectoBo.listTClienteProyectoNoAprobados(8);
            }
        } else {
            this.mostrarCooperativas = false;
            this.mostrarPersonas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarResultadoCooperativas = false;
            this.mostrarResultadoPersona = false;
            this.mostrarEmpleados = false;
            mostrarSolicitudes();
        }

    }

    public void seleccionGarantia(int opc) {
        this.idGarantia = 0;
        switch (opc) {
            case 0:
                this.estadoPrendaria = true;
                this.estadoHipotecaria = false;
                this.estadoSolidaria = false;
                this.idGarantia = 3;
                break;

            case 1:
                this.estadoPrendaria = false;
                this.estadoHipotecaria = true;
                this.estadoSolidaria = false;
                this.idGarantia = 1;
                break;
            case 2:
                this.estadoPrendaria = false;
                this.estadoHipotecaria = false;
                this.estadoSolidaria = true;
                this.idGarantia = 2;
                break;
            default:
                break;
        }
    }

    public void mostrarFiltroSolicitud() {

        int estaCooperativa = 0;
        int estaPersona = 0;
        this.listaEntidadProyectosReducida.clear();
        this.listaClienteProyectoReducida.clear();
        this.listaEntidadProyectosFiltrada = this.ientidadProyectoBo.listTEndidadProyectos(2);
        for (int i = 0; i < this.listaEntidadProyectosFiltrada.size(); i++) {
            if (this.listaEntidadProyectosFiltrada.get(i).getTProyecto().getCodigo().equals(this.codigoSolicitud)) {
                estaCooperativa = this.listaEntidadProyectosFiltrada.get(i).getTProyecto().getTTipoCredito().getIdTipoCredito();
                this.listaEntidadProyectosReducida.add(this.listaEntidadProyectosFiltrada.get(i));
            }
        }
        this.listaClienteProyectoFiltrada = this.iclienteProyectoBo.listTClienteProyecto(0);
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
        }
        if (estaPersona == 8) {
            this.mostrarAgropecuarios = true;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarLisiados = false;
        }
        if (estaPersona == 7) {
            this.mostrarAgropecuarios = false;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = true;
            this.mostrarResultadoPersona = false;
            this.mostrarResultadoCooperativas = false;
        }
        if (estaPersona == 6) {
            this.mostrarPersonas = true;
            this.mostrarCooperativas = false;
        }
        if (estaPersona == 1) {
            this.mostrarPersonas = true;
            this.mostrarCooperativas = false;
        }
        if (estaPersona == 5) {
            this.mostrarLisiados = true;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarComercio = false;
            this.mostrarResultadoPersona = false;
            this.mostrarResultadoCooperativas = false;
        }

    }

    public void habilitarDatos() {

        if (estadoHabilitar == true) {
            this.existente = true;
            this.nuevo = false;
        } else {
            this.existente = false;
            this.nuevo = true;
        }

    }

    public void enableShowLisiadoBean() {

        this.politica = this.ipoliticaBo.getPolitica(5);
        this.limpiarLisiado();
        correlativo();
        this.enableShowCreateLisiados();

    }

    public void enableShowComercioBean() {

        this.politica = this.ipoliticaBo.getPolitica(7);
        this.limpiarComercio();
        correlativo();
        this.enableShowCreateComercio();

    }

    public void enableShowEmpleadosBean() {

        this.politica = this.ipoliticaBo.getPolitica(1);
        correlativo();
        this.mostrarCrearEmpleado = true;
        this.setShowData(false);

    }

    public void enableShowCooperativaBean() {

        this.limpiarCooperativas();
        correlativo();
        this.enableShowCreateCooperativa();

    }

    public void enableShowAgropecuarioBean() {

        this.limpiarAgropecuario();
        correlativo();
        this.enableShowCreateAgropecuario();

    }

    public void cargarPolitica() {

        this.politica = this.ipoliticaBo.getPolitica(this.proyecto.getTTipoCredito().getIdTipoCredito());

    }

    public void mostrarResolucion() {

        this.listaComentarios = this.icomentarioBo.listComentario(this.proyectoSeleccionado.getIdproyecto());
        this.listaSugerencias = this.isugerenciaBo.listSugerencia(this.proyectoSeleccionado.getIdproyecto());

    }

    public void mostrarClientes() {

        this.listaClientes = this.iclienteBo.listClienteCodigo();

    }

    public void verReporteResolucion() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/resolucionSolicitud.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de resolución");

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

    }

    public void verReporteResolucionPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/resolucionSolicitud.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Resolucion.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de resolución");

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

    }

    public void validarTablaAgropecuario() {

        Boolean validado = true;
        if (this.proyecto.getTTipoCredito().getIdTipoCredito() == 0) {
            this.msgModalidad = "Debe seleccionar una modalidad";
            this.estadoFormulario = false;
        } else {
            this.msgModalidad = "";
        }
        if (this.proyecto.getDestinoProyecto() == "") {
            this.msgDestino = "Debe introducir el destino";
            this.estadoFormulario = false;
        } else {
            this.msgDestino = "";
        }
        if (this.proyecto.getTTipoCredito().getIdTipoCredito() != 0) {
            if ((this.proyecto.getMonto() == null) || (this.proyecto.getMonto().compareTo(this.politica.getMontoMinimo()) <= 0 || this.proyecto.getMonto().compareTo(this.politica.getMontoMaximo()) >= 0)) {
                this.msgMonto = "Debe introducir un monto entre " + this.politica.getMontoMinimo() + " y " + this.politica.getMontoMaximo() + "";
                this.estadoFormulario = false;
            } else {
                this.msgMonto = "";
            }
        } else {
            this.msgMonto = "Debe de seleccionar una modalidad";
        }
        if (this.proyecto.getFormaPagoProyecto() == 0) {
            this.msgFormaPago = "Debe seleccionar una forma de pago";
            validado = false;
        } else {
            this.msgFormaPago = "";
        }
 
            
        
         if (this.proyecto.getPlazo() == null) {
            this.msgPlazo = "Debe introducir el plazo del proyecto";
            validado = false;
        } else if (this.proyecto.getPlazo()%12!=0 || this.proyecto.getPlazo()==0 ){
           
             this.msgPlazo = "Debe introducir un plazo multiplo de 12";
             validado = false;
        }else{
             this.msgPlazo = "";
        }
        
        
        if (validado == true) {
            this.mostrarCalculos();
        }else{
            this.mostrarTabla=false;
        }

    }

    public void validarTablaLisiado() {

        Boolean validado = true;
        if (this.proyecto.getDestinoProyecto() == "") {
            this.msgDestino = "Debe introducir el destino";
            validado = false;
        } else {
            this.msgDestino = "";
        }
        if ((this.proyecto.getMonto() == null) || (this.proyecto.getMonto().compareTo(this.politica.getMontoMinimo()) <= 0 || this.proyecto.getMonto().compareTo(this.politica.getMontoMaximo()) >= 0)) {
            this.msgMonto = "Debe introducir un monto entre " + this.politica.getMontoMinimo() + " y " + this.politica.getMontoMaximo() + "";
            validado = false;
        } else {
            this.msgMonto = "";
        }
        if (this.proyecto.getFormaPagoProyecto() == 0) {
            this.msgFormaPago = "Debe seleccionar una forma de pago";
            validado = false;
        } else {
            this.msgFormaPago = "";
        }
       
          
         if (this.proyecto.getPlazo() == null) {
            this.msgPlazo = "Debe introducir el plazo del proyecto";
            validado = false;
        } else if (this.proyecto.getPlazo()%12!=0 || this.proyecto.getPlazo()==0 ){
           
             this.msgPlazo = "Debe introducir un plazo multiplo de 12";
             validado = false;
        }else{
             this.msgPlazo = "";
        }
        
        if (validado == true) {
            this.mostrarCalculos();
        }else{
            this.mostrarTabla=false;
            
        }

    }

    public void mostrarCalculos() {

        BigDecimal interesAnual;
        BigDecimal interesMensual;
        if (this.proyecto.getFormaPagoProyecto() == 1) {
            this.couto = BigDecimal.ZERO;
            this.intereses = BigDecimal.ZERO;
            this.total = BigDecimal.ZERO;
            this.calculos = true;
            this.monto = this.proyecto.getMonto();
            Double aux = Math.exp(-this.proyecto.getPlazo() * Math.log((1 + (this.politica.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            Double cuota = ((((this.politica.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyecto.getMonto().doubleValue());
            this.couto = new BigDecimal(cuota);
            this.cuotaPago = this.couto;
            BigDecimal interesA;
            BigDecimal abonoA = new BigDecimal("0");
            BigDecimal capital = this.proyecto.getMonto();
            BigDecimal capitalA = new BigDecimal("0");
            this.listaPagos = new ArrayList<TPago>();
            for (int i = 0; i < this.proyecto.getPlazo(); i++) {
                this.pago = new TPago();
                interesA = (capital.multiply((this.politica.getTasaInteres().divide(new BigDecimal("100")).multiply(new BigDecimal(this.proyecto.getPlazo())))));
                interesAnual = interesA.divide(new BigDecimal(this.proyecto.getPlazo()), 5, RoundingMode.HALF_UP);
                interesMensual = interesAnual.divide(new BigDecimal("12"), 5, RoundingMode.HALF_UP);
                this.intereses = this.intereses.add(interesMensual);
                abonoA = this.couto.subtract(interesMensual);
                capital = capital.subtract(abonoA);
                BigDecimal diferencia;
                capitalA = capitalA.add(abonoA);
                if (i == (this.proyecto.getPlazo() - 1)) {
                    capital = new BigDecimal("0");
                }
                this.pago.setCuota(this.couto);
                this.pago.setInteres(interesMensual);
                this.pago.setAbono(abonoA);
                this.pago.setCapitalamortizado(capitalA);
                this.pago.setSaldocapital(capital);
                this.listaPagos.add(this.pago);
            }
            this.total = this.intereses.add(this.monto);
            this.mostrarTabla = true;
        } else if (this.proyecto.getFormaPagoProyecto() == 2) {
            this.couto = BigDecimal.ZERO;
            this.intereses = BigDecimal.ZERO;
            this.total = BigDecimal.ZERO;
            this.calculos = true;
            this.monto = this.proyecto.getMonto();
            Double aux = Math.exp(-this.proyecto.getPlazo() * Math.log((1 + (this.politica.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            Double cuota = ((((this.politica.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyecto.getMonto().doubleValue());
            this.couto = new BigDecimal(cuota);
            this.cuotaPago = this.couto;
            BigDecimal interesA;
            BigDecimal abonoA = new BigDecimal("0");
            BigDecimal capital = this.proyecto.getMonto();
            BigDecimal capitalA = new BigDecimal("0");
            BigDecimal interesAnual2;
            BigDecimal interesMensual2;
            this.calculos = true;
            BigDecimal cuotaTrimestral = this.couto.add(this.couto).add(this.couto);
            this.cuotaPago = cuotaTrimestral;
            BigDecimal cuotaT = this.cuotaPago;
            this.listaPagos = new ArrayList<TPago>();
            BigDecimal interesM = new BigDecimal("0");
            BigDecimal abonoM = new BigDecimal("0");
            BigDecimal capitalM = this.proyecto.getMonto();
            BigDecimal capitalAM = new BigDecimal("0");
            int x = 3;
            for (int i = 1; i <= this.proyecto.getPlazo(); i++) {
                this.pago = new TPago();
                interesA = (capital.multiply((this.politica.getTasaInteres().divide(new BigDecimal("100")).multiply(new BigDecimal(this.proyecto.getPlazo())))));
                interesAnual2 = interesA.divide(new BigDecimal(this.proyecto.getPlazo()), 5, RoundingMode.HALF_UP);
                interesMensual2 = interesAnual2.divide(new BigDecimal("12"), 5, RoundingMode.HALF_UP);
                this.intereses = this.intereses.add(interesMensual2);
                abonoA = this.couto.subtract(interesMensual2);
                capital = capital.subtract(abonoA);
                capitalA = capitalA.add(abonoA);
                interesM = interesM.add(interesMensual2);
                abonoM = abonoM.add(abonoA);
                capitalM = capitalA;
                capitalAM = capital;
                if (x == i) {
                    BigDecimal diferencia;
                    if (i == this.proyecto.getPlazo()) {
                        if (i == (this.proyecto.getPlazo() - 1)) {
                            capitalAM = new BigDecimal("0");
                        }
                    }
                    this.pago = new TPago();
                    this.pago.setCuota(cuotaT);
                    this.pago.setInteres(interesM);
                    this.pago.setAbono(abonoM);
                    this.pago.setCapitalamortizado(capitalM);
                    this.pago.setSaldocapital(capitalAM);
                    this.listaPagos.add(this.pago);
                    System.out.println(capitalAM);
                    x = x + 3;
                    interesM = new BigDecimal("0");
                    abonoM = new BigDecimal("0");
                    capitalM = new BigDecimal("0");
                    capitalAM = new BigDecimal("0");
                }
            }
            this.total = this.intereses.add(this.monto);
            this.mostrarTabla = true;
        } else if (this.proyecto.getFormaPagoProyecto() == 3) {
            this.couto = BigDecimal.ZERO;
            this.intereses = BigDecimal.ZERO;
            this.total = BigDecimal.ZERO;
            this.calculos = true;
            this.monto = this.proyecto.getMonto();
            Double aux = Math.exp(-this.proyecto.getPlazo() * Math.log((1 + (this.politica.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            Double cuota = ((((this.politica.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyecto.getMonto().doubleValue());
            this.couto = new BigDecimal(cuota);
            this.cuotaPago = this.couto;
            BigDecimal interesA;
            BigDecimal abonoA = new BigDecimal("0");
            BigDecimal capital = this.proyecto.getMonto();
            BigDecimal capitalA = new BigDecimal("0");
            BigDecimal interesAnual2;
            BigDecimal interesMensual2;
            this.calculos = true;
            BigDecimal cuotaTrimestral = this.couto.add(this.couto).add(this.couto).add(this.couto).add(this.couto).add(this.couto);
            this.cuotaPago = cuotaTrimestral;
            BigDecimal cuotaT = this.cuotaPago;
            this.listaPagos = new ArrayList<TPago>();
            BigDecimal interesM = new BigDecimal("0");
            BigDecimal abonoM = new BigDecimal("0");
            BigDecimal capitalM = this.proyecto.getMonto();
            BigDecimal capitalAM = new BigDecimal("0");
            int x = 6;
            for (int i = 1; i <= this.proyecto.getPlazo(); i++) {
                this.pago = new TPago();
                interesA = (capital.multiply((this.politica.getTasaInteres().divide(new BigDecimal("100")).multiply(new BigDecimal(this.proyecto.getPlazo())))));
                interesAnual2 = interesA.divide(new BigDecimal(this.proyecto.getPlazo()), 5, RoundingMode.HALF_UP);
                interesMensual2 = interesAnual2.divide(new BigDecimal("12"), 5, RoundingMode.HALF_UP);
                this.intereses = this.intereses.add(interesMensual2);
                abonoA = this.couto.subtract(interesMensual2);
                capital = capital.subtract(abonoA);
                capitalA = capitalA.add(abonoA);
                interesM = interesM.add(interesMensual2);
                abonoM = abonoM.add(abonoA);
                capitalM = capitalA;
                capitalAM = capital;
                if (x == i) {
                    BigDecimal diferencia;
                    if (i == this.proyecto.getPlazo()) {
                        if (i == (this.proyecto.getPlazo() - 1)) {
                            capitalAM = new BigDecimal("0");
                        }
                    }
                    this.pago = new TPago();
                    this.pago.setCuota(cuotaT);
                    this.pago.setInteres(interesM);
                    this.pago.setAbono(abonoM);
                    this.pago.setCapitalamortizado(capitalM);
                    this.pago.setSaldocapital(capitalAM);
                    this.listaPagos.add(this.pago);
                    System.out.println(capitalAM);
                    x = x + 6;
                    interesM = new BigDecimal("0");
                    abonoM = new BigDecimal("0");
                    capitalM = new BigDecimal("0");
                    capitalAM = new BigDecimal("0");
                }
            }
            this.total = this.intereses.add(this.monto);
            this.mostrarTabla = true;
        } else {
            this.couto = BigDecimal.ZERO;
            this.intereses = BigDecimal.ZERO;
            this.total = BigDecimal.ZERO;
            this.calculos = true;
            this.monto = this.proyecto.getMonto();
            Double aux = Math.exp(-this.proyecto.getPlazo() * Math.log((1 + (this.politica.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            Double cuota = ((((this.politica.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyecto.getMonto().doubleValue());
            this.couto = new BigDecimal(cuota);
            this.cuotaPago = this.couto;
            BigDecimal interesA;
            BigDecimal abonoA = new BigDecimal("0");
            BigDecimal capital = this.proyecto.getMonto();
            BigDecimal capitalA = new BigDecimal("0");
            BigDecimal interesAnual2;
            BigDecimal interesMensual2;
            this.calculos = true;
            BigDecimal cuotaTrimestral = this.couto.add(this.couto).add(this.couto).add(this.couto).add(this.couto).add(this.couto).add(this.couto).add(this.couto).add(this.couto).add(this.couto).add(this.couto).add(this.couto);
            this.cuotaPago = cuotaTrimestral;
            BigDecimal cuotaT = this.cuotaPago;

            this.listaPagos = new ArrayList<TPago>();
            BigDecimal interesM = new BigDecimal("0");
            BigDecimal abonoM = new BigDecimal("0");
            BigDecimal capitalM = this.proyecto.getMonto();
            BigDecimal capitalAM = new BigDecimal("0");
            int x = 12;
            for (int i = 1; i <= this.proyecto.getPlazo(); i++) {
                this.pago = new TPago();
                interesA = (capital.multiply((this.politica.getTasaInteres().divide(new BigDecimal("100")).multiply(new BigDecimal(this.proyecto.getPlazo())))));
                interesAnual2 = interesA.divide(new BigDecimal(this.proyecto.getPlazo()), 5, RoundingMode.HALF_UP);
                interesMensual2 = interesAnual2.divide(new BigDecimal("12"), 5, RoundingMode.HALF_UP);
                this.intereses = this.intereses.add(interesMensual2);
                abonoA = this.couto.subtract(interesMensual2);
                capital = capital.subtract(abonoA);
                capitalA = capitalA.add(abonoA);
                interesM = interesM.add(interesMensual2);
                abonoM = abonoM.add(abonoA);
                capitalM = capitalA;
                capitalAM = capital;
                if (x == i) {
                    BigDecimal diferencia;
                    if (i == this.proyecto.getPlazo()) {
                        if (i == (this.proyecto.getPlazo() - 1)) {
                            capitalAM = new BigDecimal("0");
                        }
                    }
                    this.pago = new TPago();
                    this.pago.setCuota(cuotaT);
                    this.pago.setInteres(interesM);
                    this.pago.setAbono(abonoM);
                    this.pago.setCapitalamortizado(capitalM);
                    this.pago.setSaldocapital(capitalAM);
                    this.listaPagos.add(this.pago);
                    System.out.println(capitalAM);
                    x = x + 12;
                    interesM = new BigDecimal("0");
                    abonoM = new BigDecimal("0");
                    capitalM = new BigDecimal("0");
                    capitalAM = new BigDecimal("0");
                }
            }
            this.total = this.intereses.add(this.monto);
            this.mostrarTabla = true;
        }

    }

    public void cargarModificarComerci() {

        this.comercioSeleccionado = this.icomercioBo.getComercio(this.proyectoSeleccionado.getIdproyecto());

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
        mostrarSolicitudes();

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

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud eliminada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    public void eliminarSolicitudCooperativa() {

        this.ientidadProyectoBo.delete(this.entidadProyectoEliminar);
        TCooperativa cooperativa = this.icooperativaBo.getTCooperativa(this.proyectoSeleccionado.getIdproyecto());
        this.icooperativaBo.delete(cooperativa);
        TGarantia garantia = this.igarantiaBo.getTGarantia(this.proyectoSeleccionado.getIdproyecto());
        this.igarantiaBo.delete(garantia);
        this.iproyectoBo.delete(this.proyectoSeleccionado);
        mostrarSolicitudes();
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

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud eliminada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

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

    }

    public void calcularEdad() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        Date fechaNac = null;
        String nueva = "";
        int año = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("La fecha de hoy es: " + sdf.format(this.clienteSeleccionado.getFechaNacimiento()));
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

    public void calcularEdadSolicitud() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        Date fechaNac = null;
        String nueva = "";
        int año = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("La fecha de hoy es: " + sdf.format(this.cliente.getFechaNacimiento()));
        try {
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse("" + sdf.format(this.cliente.getFechaNacimiento()));
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

    public void actualizarActivo(int posicion) {

        this.activo = this.listaActivos.get(posicion);
        this.listaActivos.remove(posicion);

    }

    public void actualizarIngreso(int posicion) {

        this.ingreso = this.listaIngreso.get(posicion);
        this.listaIngreso.remove(posicion);

    }

    public void actualizarEgreso(int posicion) {

        this.egreso = this.listaEgreso.get(posicion);
        this.listaEgreso.remove(posicion);

    }

    public void actualizarReferencia(int posicion) {

        this.referencia = this.listaReferencia.get(posicion);
        this.listaReferencia.remove(posicion);

    }

}
