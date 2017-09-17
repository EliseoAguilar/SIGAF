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
import com.sigaf.Ibo.ICooperativaBo;
import com.sigaf.Ibo.IDesembolsoBo;
import com.sigaf.Ibo.IEgresoBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Ibo.IEntidadProyectoBo;
import com.sigaf.Ibo.IGarantiaBo;
import com.sigaf.Ibo.IIngresoBo;
import com.sigaf.Ibo.ILisiadoBo;
import com.sigaf.Ibo.IPagoBo;
import com.sigaf.Ibo.IPoliticaBo;
import com.sigaf.Ibo.IProyectoBo;
import com.sigaf.Ibo.IReferenciaBo;
import com.sigaf.Ibo.ITrabajoBo;
import com.sigaf.pojo.TActivo;
import com.sigaf.pojo.TAgropecuario;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TCliente;
import com.sigaf.pojo.TClienteProyecto;
import com.sigaf.pojo.TCooperativa;
import com.sigaf.pojo.TDesembolso;
import com.sigaf.pojo.TEgreso;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TEntidadProyecto;
import com.sigaf.pojo.TGarantia;
import com.sigaf.pojo.TIngreso;
import com.sigaf.pojo.TLisiado;
import com.sigaf.pojo.TPago;
import com.sigaf.pojo.TPolitica;
import com.sigaf.pojo.TProyecto;
import com.sigaf.pojo.TReferencia;
import com.sigaf.pojo.TTipoCredito;
import com.sigaf.pojo.TTrabajo;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Genoves
 */
@Named(value = "carteraBean")

@SessionScoped
@ManagedBean
public class CarteraBean extends Actividad {

    private TEntidad Entidad;
    private TCooperativa cooperativa;
    private TCooperativa cooperativaSeleccionada;
    private TPago pago;
    private TPago pagoMora;
    private TCliente cliente;
    private TCliente clienteSeleccionado;
    private TIngreso ingresoSeleccionado;
    private TEgreso egresoSeleccionado;
    private Integer tipoCliente;
    private Boolean showNitCliente;
    private Boolean showDuiCliente;
    private Integer edad;
    private Integer numeroSeleccion;
    private TPago pagoSeleccionado;
    private Integer cantidadCuotas;
    private Boolean mostrarHistorialCooperativa;
    private Boolean mostrarHistorial;
    private List<TDesembolso> listaDesembolso;
    private BigDecimal monto;
    private BigDecimal intereses;
    private BigDecimal total;
    private BigDecimal couto;
    private BigDecimal totalIngreso;
    private BigDecimal totalEgreso;
    private BigDecimal totalActivo;
    private BigDecimal totalGarantia;
    private int numeroCuota;
    private Integer numeroCuotasRetraso;
    private BigDecimal totalRetraso;
    private BigDecimal valorRedondeado;
    private BigDecimal valorRedondeadoIngreso;
    private BigDecimal valorRedondeadoEgreso;
    private BigDecimal valorRedondeadoActivo;
    private BigDecimal valorRedondeadoGarantia;
    private Date fechaEstipulada;
    private Date fechaAplicacion;
    private Integer tipoMora;
    private Integer numeroDesembolso;
    private TReferencia referencia;
    private TReferencia referenciaSeleccionada;
    private TActivo activo;
    private List<TActivo> listaActivos;
    private List<TIngreso> listaIngreso;
    private TIngreso ingreso;
    private List<TEgreso> listaEgreso;
    private TEgreso egreso;
    private List<TGarantia> listaGarantia;
    private List<TPago> listaPago;
    private String mostrarTipoCliente;
    private String calificacion;
    private Boolean mostrarMorosos;
    private Boolean mostrarNoMorosos;
    private List<TReferencia> listaReferencia;
    private List<TPago> listaPagos;
    private List<TPago> listaPagosMora;
    private List<TClienteProyecto> listaClienteProyectos;
    private List<TIngreso> listaIngresoSeleccionada;
    private List<TEgreso> listaEgresoSeleccionada;
    private List<TGarantia> listaGarantiaSeleccionada;
    private List<TActivo> listaActivoSeleccionada;
    private List<TEntidad> listaEntidades;
    private List<TEntidadProyecto> listaEntidadProyectos;
    private List<TEntidadProyecto> listaEntidadProyectosReducida;
    private List<TEntidadProyecto> listaEntidadProyectosFiltrada;
    private String tipo;
    private BigDecimal valorRedondeadoP;
    private List<TEntidad> listaEntidadesMorosas;
    private List<TCliente> listaClientesMorosos;
    private List<TClienteProyecto> listaClienteProyectoReducida;
    private List<TClienteProyecto> listaClienteProyectoFiltrada;
    private List<TClienteProyecto> listaClienteProyectoMorosos;
    private List<TClienteProyecto> listaClienteProyectoNoMorosos;
    private List<TEntidadProyecto> listaEntidadProyectoMorosos;
    private List<TEntidadProyecto> listaEntidadProyectoNoMorosos;
    private TEntidadProyecto entidadProyecto;
    private TEntidad Entidadeleccionada;
    private TProyecto proyecto;
    private TProyecto proyectoSeleccionado;
    private TPolitica politica;
    private TPolitica politicaSeleccionada;
    private Boolean calculos;
    private Boolean mostrarPersonas;
    private Boolean mostrarCooperativas;
    private Integer diasRetraso;
    private Boolean mostrarMorososCooperativas;
    private String msgTelefonoMayor;
    private String msgMovilMayor;
    private String msgInstitucion;
    private String msgFechaNacimiento;
    private String msgSexo;
    private String msgEstadoFamiliar;
    private String msgTelefono;
    private String msgMovil;
    private String msgDireccion;
    private String msgedadConyugue;
    private String msgLesion;
    private String msgApellidoConyugue;
    private String msgTelefonoConyugue;
    private String msgNombreConyugue;
    private String msgHijosConyugue;
    private String msgNombreMayor;
    private String msgApellidoMayor;
    private String msgEdadMayor;
    private String msgEdadConyugue;
    private Boolean showConstancia;
    private Boolean estadoFormulario;
    private Integer tipoCredito;
    private TLisiado lisiadoSeleccionado;
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
    private IPagoBo ipagoBo;
    private ILisiadoBo ilisiadoBo;
    private Boolean showImagenCarta;
    private TGarantia garantia;
    private TAgropecuario agropecuario;
    private TClienteProyecto clienteProyecto;
    private BigDecimal totalMora;
    private BigDecimal mora;
    private List<TReferencia> listaReferenciaSeleccionada;
    private Integer tipoCred;
    private IDesembolsoBo idesembolsoBo;
    private Boolean showPresupuesto;
    private Boolean showBalance;
    private String msgMontoLisiado;
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
    private Boolean mostrarTabla;
    private String msgNombre;
    private String msgApellido;
    private String msgDui;
    private String msgNit;
    private IAreaBo iareaBo;
    private Boolean modificarClienteLisiado;
    private ITrabajoBo itrabajoBo;
    private TTrabajo trabajoSeleccionado;
    private Boolean modificarClienteEmpleado;
    public String msgFotoDui;
    public String msgFotoNit;
    public String msgCarta;
    private String msgNombreEmpresa;
    private String msgDireccionEmpresa;
    private String msgTelefonoEmpresa;
    private String msgCargoEmpresa;
    private String msgTiempoEmpresa;
    private String msgConstanciaEmpresa;
    private BigDecimal totalDesembolsos;
    private BigDecimal totalMoraGenerada;
    private IBitacoraBo bitacoraBo;
    private BigDecimal totalCuotas;
    private BigDecimal totalInteres;
    private BigDecimal totalAbono;
    private BigDecimal totalAmortizado;
    private BigDecimal totalCapital;
    private Boolean estadoConyugue;
    private Boolean estadoHijos;
    private String formaPago;
    private String estadoCredito;
    private String duiAux;
    private String nitAux;

    public String getNitAux() {
        return nitAux;
    }

    public void setNitAux(String nitAux) {
        this.nitAux = nitAux;
    }
    private TCliente clienteAux;

    public TCliente getClienteAux() {
        return clienteAux;
    }

    public void setClienteAux(TCliente clienteAux) {
        this.clienteAux = clienteAux;
    }

    public String getDuiAux() {
        return duiAux;
    }

    public void setDuiAux(String duiAux) {
        this.duiAux = duiAux;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getEstadoCredito() {
        return estadoCredito;
    }

    public void setEstadoCredito(String estadoCredito) {
        this.estadoCredito = estadoCredito;
    }

    public Boolean getEstadoConyugue() {
        return estadoConyugue;
    }

    public void setEstadoConyugue(Boolean estadoConyugue) {
        this.estadoConyugue = estadoConyugue;
    }

    public Boolean getEstadoHijos() {
        return estadoHijos;
    }

    public void setEstadoHijos(Boolean estadoHijos) {
        this.estadoHijos = estadoHijos;
    }

    public BigDecimal getTotalCuotas() {
        return totalCuotas;
    }

    public void setTotalCuotas(BigDecimal totalCuotas) {
        this.totalCuotas = totalCuotas;
    }

    public BigDecimal getTotalInteres() {
        return totalInteres;
    }

    public void setTotalInteres(BigDecimal totalInteres) {
        this.totalInteres = totalInteres;
    }

    public BigDecimal getTotalAbono() {
        return totalAbono;
    }

    public void setTotalAbono(BigDecimal totalAbono) {
        this.totalAbono = totalAbono;
    }

    public BigDecimal getTotalAmortizado() {
        return totalAmortizado;
    }

    public void setTotalAmortizado(BigDecimal totalAmortizado) {
        this.totalAmortizado = totalAmortizado;
    }

    public BigDecimal getTotalCapital() {
        return totalCapital;
    }

    public void setTotalCapital(BigDecimal totalCapital) {
        this.totalCapital = totalCapital;
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

    public BigDecimal getTotalMoraGenerada() {
        return totalMoraGenerada;
    }

    public void setTotalMoraGenerada(BigDecimal totalMoraGenerada) {
        this.totalMoraGenerada = totalMoraGenerada;
    }

    public BigDecimal getTotalDesembolsos() {
        return totalDesembolsos;
    }

    public void setTotalDesembolsos(BigDecimal totalDesembolsos) {
        this.totalDesembolsos = totalDesembolsos;
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

    public String getMsgCarta() {
        return msgCarta;
    }

    public void setMsgCarta(String msgCarta) {
        this.msgCarta = msgCarta;
    }

    public String getMsgFotoDui() {
        return msgFotoDui;
    }

    public void setMsgFotoDui(String msgFotoDui) {
        this.msgFotoDui = msgFotoDui;
    }

    public String getMsgFotoNit() {
        return msgFotoNit;
    }

    public void setMsgFotoNit(String msgFotoNit) {
        this.msgFotoNit = msgFotoNit;
    }

    public ITrabajoBo getItrabajoBo() {
        return itrabajoBo;
    }

    public void setItrabajoBo(ITrabajoBo itrabajoBo) {
        this.itrabajoBo = itrabajoBo;
    }

    public TTrabajo getTrabajoSeleccionado() {
        return trabajoSeleccionado;
    }

    public void setTrabajoSeleccionado(TTrabajo trabajoSeleccionado) {
        this.trabajoSeleccionado = trabajoSeleccionado;
    }

    public Boolean getModificarClienteEmpleado() {
        return modificarClienteEmpleado;
    }

    public void setModificarClienteEmpleado(Boolean modificarClienteEmpleado) {
        this.modificarClienteEmpleado = modificarClienteEmpleado;
    }

    public Boolean getModificarClienteLisiado() {
        return modificarClienteLisiado;
    }

    public void setModificarClienteLisiado(Boolean modificarClienteLisiado) {
        this.modificarClienteLisiado = modificarClienteLisiado;
    }

    public TPago getPagoSeleccionado() {
        return pagoSeleccionado;
    }

    public void setPagoSeleccionado(TPago pagoSeleccionado) {
        this.pagoSeleccionado = pagoSeleccionado;
    }

    public Integer getNumeroSeleccion() {
        return numeroSeleccion;
    }

    public void setNumeroSeleccion(Integer numeroSeleccion) {
        this.numeroSeleccion = numeroSeleccion;
    }

    public Boolean getMostrarHistorialCooperativa() {
        return mostrarHistorialCooperativa;
    }

    public void setMostrarHistorialCooperativa(Boolean mostrarHistorialCooperativa) {
        this.mostrarHistorialCooperativa = mostrarHistorialCooperativa;
    }

    public Boolean getMostrarHistorial() {
        return mostrarHistorial;
    }

    public void setMostrarHistorial(Boolean mostrarHistorial) {
        this.mostrarHistorial = mostrarHistorial;
    }

    public Integer getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(Integer cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
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

    public Boolean getMostrarMorososCooperativas() {
        return mostrarMorososCooperativas;
    }

    public void setMostrarMorososCooperativas(Boolean mostrarMorososCooperativas) {
        this.mostrarMorososCooperativas = mostrarMorososCooperativas;
    }

    public IDesembolsoBo getIdesembolsoBo() {
        return idesembolsoBo;
    }

    public void setIdesembolsoBo(IDesembolsoBo idesembolsoBo) {
        this.idesembolsoBo = idesembolsoBo;
    }

    public List<TDesembolso> getListaDesembolso() {
        return listaDesembolso;
    }

    public void setListaDesembolso(List<TDesembolso> listaDesembolso) {
        this.listaDesembolso = listaDesembolso;
    }

    public Integer getDiasRetraso() {
        return diasRetraso;
    }

    public void setDiasRetraso(Integer diasRetraso) {
        this.diasRetraso = diasRetraso;
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

    public Integer getNumeroCuotasRetraso() {
        return numeroCuotasRetraso;
    }

    public void setNumeroCuotasRetraso(Integer numeroCuotasRetraso) {
        this.numeroCuotasRetraso = numeroCuotasRetraso;
    }

    public BigDecimal getTotalRetraso() {
        return totalRetraso;
    }

    public void setTotalRetraso(BigDecimal totalRetraso) {
        this.totalRetraso = totalRetraso;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Integer getTipoMora() {
        return tipoMora;
    }

    public void setTipoMora(Integer tipoMora) {
        this.tipoMora = tipoMora;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public Date getFechaEstipulada() {
        return fechaEstipulada;
    }

    public void setFechaEstipulada(Date fechaEstipulada) {
        this.fechaEstipulada = fechaEstipulada;
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

    public List<TEntidad> getListaEntidadesMorosas() {
        return listaEntidadesMorosas;
    }

    public void setListaEntidadesMorosas(List<TEntidad> listaEntidadesMorosas) {
        this.listaEntidadesMorosas = listaEntidadesMorosas;
    }

    public List<TCliente> getListaClientesMorosos() {
        return listaClientesMorosos;
    }

    public void setListaClientesMorosos(List<TCliente> listaClientesMorosos) {
        this.listaClientesMorosos = listaClientesMorosos;
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

    public List<TEntidadProyecto> getListaEntidadProyectoMorosos() {
        return listaEntidadProyectoMorosos;
    }

    public void setListaEntidadProyectoMorosos(List<TEntidadProyecto> listaEntidadProyectoMorosos) {
        this.listaEntidadProyectoMorosos = listaEntidadProyectoMorosos;
    }

    public List<TEntidadProyecto> getListaEntidadProyectoNoMorosos() {
        return listaEntidadProyectoNoMorosos;
    }

    public void setListaEntidadProyectoNoMorosos(List<TEntidadProyecto> listaEntidadProyectoNoMorosos) {
        this.listaEntidadProyectoNoMorosos = listaEntidadProyectoNoMorosos;
    }

    public List<TClienteProyecto> getListaClienteProyectoNoMorosos() {
        return listaClienteProyectoNoMorosos;
    }

    public void setListaClienteProyectoNoMorosos(List<TClienteProyecto> listaClienteProyectoNoMorosos) {
        this.listaClienteProyectoNoMorosos = listaClienteProyectoNoMorosos;
    }

    public List<TClienteProyecto> getListaClienteProyectoMorosos() {
        return listaClienteProyectoMorosos;
    }

    public void setListaClienteProyectoMorosos(List<TClienteProyecto> listaClienteProyectoMorosos) {
        this.listaClienteProyectoMorosos = listaClienteProyectoMorosos;
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

    public TPago getPagoMora() {
        return pagoMora;
    }

    public void setPagoMora(TPago pagoMora) {
        this.pagoMora = pagoMora;
    }

    public List<TPago> getListaPagosMora() {
        return listaPagosMora;
    }

    public void setListaPagosMora(List<TPago> listaPagosMora) {
        this.listaPagosMora = listaPagosMora;
    }

    public List<TPago> getListaPagos() {
        return listaPagos;
    }

    public void setListaPagos(List<TPago> listaPagos) {
        this.listaPagos = listaPagos;
    }

    public Integer getNumeroDesembolso() {
        return numeroDesembolso;
    }

    public void setNumeroDesembolso(Integer numeroDesembolso) {
        this.numeroDesembolso = numeroDesembolso;
    }

    public Boolean getMostrarNoMorosos() {
        return mostrarNoMorosos;
    }

    public void setMostrarNoMorosos(Boolean mostrarNoMorosos) {
        this.mostrarNoMorosos = mostrarNoMorosos;
    }

    public Boolean getMostrarMorosos() {
        return mostrarMorosos;
    }

    public void setMostrarMorosos(Boolean mostrarMorosos) {
        this.mostrarMorosos = mostrarMorosos;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getMostrarTipoCliente() {
        return mostrarTipoCliente;
    }

    public void setMostrarTipoCliente(String mostrarTipoCliente) {
        this.mostrarTipoCliente = mostrarTipoCliente;
    }

    public List<TPago> getListaPago() {
        return listaPago;
    }

    public void setListaPago(List<TPago> listaPago) {
        this.listaPago = listaPago;
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

    public Boolean getShowImagenCarta() {
        return showImagenCarta;
    }

    public void setShowImagenCarta(Boolean showImagenCarta) {
        this.showImagenCarta = showImagenCarta;
    }

    public TLisiado getLisiadoSeleccionado() {
        return lisiadoSeleccionado;
    }

    public void setLisiadoSeleccionado(TLisiado lisiadoSeleccionado) {
        this.lisiadoSeleccionado = lisiadoSeleccionado;
    }

    public ILisiadoBo getIlisiadoBo() {
        return ilisiadoBo;
    }

    public void setIlisiadoBo(ILisiadoBo ilisiadoBo) {
        this.ilisiadoBo = ilisiadoBo;
    }

    public IPagoBo getIpagoBo() {
        return ipagoBo;
    }

    public void setIpagoBo(IPagoBo ipagoBo) {
        this.ipagoBo = ipagoBo;
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

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public String getMsgApellido() {
        return msgApellido;
    }

    public void setMsgApellido(String msgApellido) {
        this.msgApellido = msgApellido;
    }

    public String getMsgDui() {
        return msgDui;
    }

    public void setMsgDui(String msgDui) {
        this.msgDui = msgDui;
    }

    public String getMsgNit() {
        return msgNit;
    }

    public void setMsgNit(String msgNit) {
        this.msgNit = msgNit;
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

    public String getMsgedadConyugue() {
        return msgedadConyugue;
    }

    public void setMsgedadConyugue(String msgedadConyugue) {
        this.msgedadConyugue = msgedadConyugue;
    }

    public Boolean getShowConstancia() {
        return showConstancia;
    }

    public void setShowConstancia(Boolean showConstancia) {
        this.showConstancia = showConstancia;
    }

    public String getMsgMontoLisiado() {
        return msgMontoLisiado;
    }

    public void setMsgMontoLisiado(String msgMontoLisiado) {
        this.msgMontoLisiado = msgMontoLisiado;
    }

    public String getMsgEdadConyugue() {
        return msgEdadConyugue;
    }

    public void setMsgEdadConyugue(String msgEdadConyugue) {
        this.msgEdadConyugue = msgEdadConyugue;
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

    private UploadedFile img;

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

    public BigDecimal getTotalMora() {
        return totalMora;
    }

    public void setTotalMora(BigDecimal totalMora) {
        this.totalMora = totalMora;
    }

    public BigDecimal getMora() {
        return mora;
    }

    public void setMora(BigDecimal mora) {
        this.mora = mora;
    }

    public void Init() {

        this.Entidad = new TEntidad();
        this.Entidadeleccionada = new TEntidad();
        this.politica = new TPolitica();
        this.proyecto = new TProyecto();
        this.proyecto.setTTipoCredito(new TTipoCredito());
        this.showConstancia = true;
        this.proyectoSeleccionado = new TProyecto();
        this.proyectoSeleccionado.setTTipoCredito(new TTipoCredito());
        this.ingresoSeleccionado = new TIngreso();
        this.egresoSeleccionado = new TEgreso();
        this.lisiadoSeleccionado = new TLisiado();
        this.cooperativa = new TCooperativa();
        this.agropecuario = new TAgropecuario();
        this.entidadProyecto = new TEntidadProyecto();
        this.cliente = new TCliente();
        this.clienteAux = new TCliente();
        this.clienteSeleccionado = new TCliente();
        super.setShowData(true);

        DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
        inFormat.setTimeZone(TimeZone.getTimeZone("America/Guatemala"));
        Date purchaseDate = new Date();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.fechaAplicacion = formatoDelTexto.parse(inFormat.format(purchaseDate));
        } catch (ParseException ex) {
            Logger.getLogger(CarteraBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tipoCredito = 0;
        this.showPresupuesto = true;
        this.showAprobacion = true;
        this.showBalance = true;
        this.listaEntidadProyectosReducida = new ArrayList<TEntidadProyecto>();
        this.listaClienteProyectoReducida = new ArrayList<TClienteProyecto>();
        this.listaClienteProyectoMorosos = new ArrayList<TClienteProyecto>();
        this.listaClienteProyectoNoMorosos = new ArrayList<TClienteProyecto>();
        this.listaEntidadProyectoMorosos = new ArrayList<TEntidadProyecto>();
        this.listaEntidadProyectoNoMorosos = new ArrayList<TEntidadProyecto>();
        this.mostrarMorososCooperativas = false;
        this.calculos = false;
        this.referencia = new TReferencia();
        this.listaReferencia = new ArrayList<TReferencia>();
        this.activo = new TActivo();
        this.listaActivos = new ArrayList<TActivo>();
        this.ingreso = new TIngreso();
        this.listaIngreso = new ArrayList<TIngreso>();
        this.egreso = new TEgreso();
        this.listaEgreso = new ArrayList<TEgreso>();
        this.calificacion = "No presenta mora";
        this.garantia = new TGarantia();
        this.listaGarantia = new ArrayList<TGarantia>();
        this.clienteProyecto = new TClienteProyecto();
        this.clienteProyecto.setTCliente(new TCliente());
        this.clienteProyecto.setTProyecto(new TProyecto());
        this.mostrarTabla = false;
        this.showNitCliente = true;
        this.showDuiCliente = true;
        this.showImagenCarta = true;

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

    public void enableShowNitCliente() {

        this.estadoFormulario = false;
        this.setShowNitCliente(!this.getShowNitCliente());

    }

    public void enableShowDuiCliente() {

        this.estadoFormulario = false;
        this.setShowDuiCliente(!this.getShowDuiCliente());

    }

    public void enableShowConstancia() {

        this.estadoFormulario = false;
        this.setShowConstancia(!this.getShowConstancia());

    }

    public void limpiar() {

        this.estadoFormulario = false;
        this.modificarClienteLisiado = false;
        this.modificarClienteEmpleado = false;
        this.lisiadoSeleccionado = new TLisiado();
        this.msgNombre = "";
        this.msgApellido = "";
        this.msgDui = "";
        this.msgNit = "";
        this.msgFechaNacimiento = "";
        this.msgSexo = "";
        this.msgEstadoFamiliar = "";
        this.msgTelefono = "";
        this.msgMovil = "";
        this.msgDireccion = "";
        this.msgMontoLisiado = "";
        this.msgInstitucion = "";
        this.msgEdadMayor = "";
        this.msgApellidoMayor = "";
        this.msgNombreMayor = "";
        this.msgHijosConyugue = "";
        this.msgedadConyugue = "";
        this.msgApellidoConyugue = "";
        this.msgNombreConyugue = "";
        this.msgLesion = "";
        this.msgFotoNit = "";
        this.msgFotoDui = "";

        super.enableShowData();

    }

    public void cargarPolitica() {

        if (this.proyecto.getTTipoCredito().getIdTipoCredito() != 5) {
            this.politica = this.ipoliticaBo.getPolitica(this.proyecto.getTTipoCredito().getIdTipoCredito());
        }

    }

    public void validarFormulario() {

        this.estadoFormulario = true;

    }

    public void validarFormularioLisiado() {

        this.estadoFormulario = true;

    }

    public void enableShowImagenCarta() {

        this.showImagenCarta = true;

    }

    public void guardarImagenCarta(FileUploadEvent event) throws IOException {

        String tipo = event.getFile().getContentType();
        byte[] content = event.getFile().getContents();
        String b64 = Base64.encode(content);
        String guardar = "data:" + tipo + ";base64," + b64;
        this.lisiadoSeleccionado.setCartaLisiado(guardar);
        this.showImagenCarta = false;
        FacesMessage message = new FacesMessage("Carta Cargada");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarConstancia(FileUploadEvent event) throws IOException {

        String tipo = event.getFile().getContentType();
        byte[] content = event.getFile().getContents();
        String b64 = Base64.encode(content);
        String guardar = "data:" + tipo + ";base64," + b64;
        this.trabajoSeleccionado.setConstancia(guardar);
        this.showConstancia = true;
        FacesMessage message = new FacesMessage("Constancia cargada");
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
        FacesMessage message = new FacesMessage("Balance cargado cargado");
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

    public void guardarDuiCliente(FileUploadEvent event) throws IOException {

        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.clienteSeleccionado.setDuiCopiaCliente(guardarP);
        this.showDuiCliente = true;
        FacesMessage message = new FacesMessage("DUI cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarNitCliente(FileUploadEvent event) throws IOException {

        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.clienteSeleccionado.setNitCopiaCliente(guardarP);
        this.showNitCliente = true;
        FacesMessage message = new FacesMessage("Nit cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void modificarCliente() {

        this.iclienteBo.update(this.clienteSeleccionado);
        if (this.lisiadoSeleccionado != null) {
            this.ilisiadoBo.update(this.lisiadoSeleccionado);
        }
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cliente");
        auxBitacora.setAccionBitacora("Modificar cliente");

        auxBitacora.setDatosBitacora("Código:" + this.clienteSeleccionado.getCodigoCliente()
                + ", Nombre: " + this.clienteSeleccionado.getNombreCliente() + " " + this.clienteSeleccionado.getApellidoCliente()
                + ", DUI: " + this.clienteSeleccionado.getDuiCliente()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.clienteSeleccionado.getIdCliente());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente modificado correctamente"));

        this.estadoFormulario = false;
        super.enableShowData();

    }

    public void enableShowDataBean() {

        super.enableShowData();

    }

    public void mostrarEntidades() {

        this.listaEntidades = ientidadBo.listTEndidad();

    }

    public void correlativo() {

        Integer correlativo = this.iproyectoBo.getCorrelativo() + 1;
        if (correlativo < 10) {
            this.proyecto.setCodigo("0000" + correlativo);
        } else if (correlativo < 100) {
            this.proyecto.setCodigo("000" + correlativo);
        } else if (correlativo < 1000) {
            this.proyecto.setCodigo("00" + correlativo);
        } else if (correlativo < 10000) {
            this.proyecto.setCodigo("0" + correlativo);
        } else {
            this.proyecto.setCodigo("" + correlativo);
        }

    }

    public void cargarVista() {

        this.listaEntidadProyectosFiltrada = new ArrayList<TEntidadProyecto>();
        this.listaEntidadProyectos = this.ientidadProyectoBo.listTEndidadProyectosEjecutandose(0);
        for (int i = 0; i < this.listaEntidadProyectos.size(); i++) {
            if (this.Entidadeleccionada.getIdEntidad() == this.listaEntidadProyectos.get(i).getTEntidad().getIdEntidad() && (this.listaEntidadProyectos.get(i).getTProyecto().getEstado() == 3 || this.listaEntidadProyectos.get(i).getTProyecto().getEstado() == 5 || this.listaEntidadProyectos.get(i).getTProyecto().getEstado() == 6 || this.listaEntidadProyectos.get(i).getTProyecto().getEstado() == 7)) {
                this.listaEntidadProyectosFiltrada.add(this.listaEntidadProyectos.get(i));
            }
        }
        this.enableShowCreate();

    }

    public void cargarVista2() {

        this.listaClienteProyectoFiltrada = new ArrayList<TClienteProyecto>();
        this.listaClienteProyectos = this.iclienteProyectoBo.listTClienteProyectoEjecutandose(0);
        for (int i = 0; i < this.listaClienteProyectos.size(); i++) {
            if (this.clienteSeleccionado.getIdCliente() == this.listaClienteProyectos.get(i).getTCliente().getIdCliente() && (this.listaClienteProyectos.get(i).getTProyecto().getEstado() == 5 || this.listaClienteProyectos.get(i).getTProyecto().getEstado() == 6 || this.listaClienteProyectos.get(i).getTProyecto().getEstado() == 7 || this.listaClienteProyectos.get(i).getTProyecto().getEstado() == 3)) {
                this.listaClienteProyectoFiltrada.add(this.listaClienteProyectos.get(i));
            }
        }
        this.enableShowView();

    }

    public void cargarVistaPagosPersonas() {

        this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        this.numeroCuota = this.listaPago.size();

    }

    public void generarDistribucion() {

        BigDecimal cal;
        this.totalMoraGenerada = new BigDecimal("0");
        this.monto = this.proyectoSeleccionado.getMonto();
        this.politicaSeleccionada = this.ipoliticaBo.getPolitica(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
        this.intereses = (this.monto.multiply(this.politicaSeleccionada.getTasaInteres().divide(new BigDecimal("100")).multiply(new BigDecimal(this.proyectoSeleccionado.getPlazo()).divide(new BigDecimal("12")))));
        Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
        this.couto = new BigDecimal(cuota);
        this.total = this.intereses.add(this.monto);
        int meses = 0;
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            meses = 1;
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            meses = 3;
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            meses = 6;
        } else {
            meses = 12;
        }
        TDesembolso desembolsoInicial = this.idesembolsoBo.getDesembolso(this.proyectoSeleccionado.getIdproyecto());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(desembolsoInicial.getFechaDesembolso());
        if (meses == 1) {
            if (this.listaPago.size() == 0) {
                calendar.add(Calendar.MONTH, meses);
            } else {
                for (int y = 0; y < this.listaPago.size(); y++) {
                    calendar.add(Calendar.MONTH, meses);
                }
            }
        } else if (meses == 3) {
            if (this.listaPago.size() == 0) {
                calendar.add(Calendar.MONTH, meses);
            } else {
                for (int y = 0; y < this.listaPago.size(); y++) {
                    calendar.add(Calendar.MONTH, meses);
                }
            }
        } else if (meses == 6) {
            if (this.listaPago.size() == 0) {
                calendar.add(Calendar.MONTH, meses);
            } else {
                for (int y = 0; y < this.listaPago.size(); y++) {
                    calendar.add(Calendar.MONTH, meses);
                }
            }
        } else if (this.listaPago.size() == 0) {
            calendar.add(Calendar.MONTH, meses);
        } else {
            for (int y = 0; y < this.listaPago.size(); y++) {
                calendar.add(Calendar.MONTH, meses);
            }
        }
        this.fechaEstipulada = calendar.getTime();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(this.fechaEstipulada);
        int mesesAux = 0;
        int numeroCuotasRetraso = 0;
        this.listaPagosMora = new ArrayList<TPago>();
        this.pagoMora = new TPago();
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            mesesAux = 1;
            while (this.fechaAplicacion.compareTo(calendar2.getTime()) >= 0) {
                int dia = 0;
                dia = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (dia));
                numeroCuotasRetraso++;
                this.pagoMora.setFecha(this.fechaEstipulada);
                this.pagoMora.setIdpago(dia);
                this.pagoMora.setCuota(this.couto);
                this.pagoMora.setAbono(new BigDecimal(moraGenerada));
                this.pagoMora.setInteres(this.pagoMora.getAbono().add(this.couto));
                this.listaPagosMora.add(this.pagoMora);
                calendar2.add(Calendar.MONTH, mesesAux);
                this.fechaEstipulada = calendar2.getTime();
            }
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            mesesAux = 3;
            while (this.fechaAplicacion.compareTo(calendar2.getTime()) >= 0) {
                int dia = 0;
                dia = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (dia));
                numeroCuotasRetraso++;
                this.pagoMora.setFecha(this.fechaEstipulada);
                this.pagoMora.setIdpago(dia);
                this.pagoMora.setCuota(this.couto);
                this.pagoMora.setAbono(new BigDecimal(moraGenerada));
                this.pagoMora.setInteres(this.pagoMora.getAbono().add(this.couto));
                this.listaPagosMora.add(this.pagoMora);
                calendar2.add(Calendar.MONTH, mesesAux);
                this.fechaEstipulada = calendar2.getTime();
            }
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            mesesAux = 6;
            while (this.fechaAplicacion.compareTo(calendar2.getTime()) >= 0) {
                int dia = 0;
                dia = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (dia));
                numeroCuotasRetraso++;
                this.pagoMora.setFecha(this.fechaEstipulada);
                this.pagoMora.setIdpago(dia);
                this.pagoMora.setCuota(this.couto);
                this.pagoMora.setAbono(new BigDecimal(moraGenerada));
                this.pagoMora.setInteres(this.pagoMora.getAbono().add(this.couto));
                this.listaPagosMora.add(this.pagoMora);
                calendar2.add(Calendar.MONTH, mesesAux);
                this.fechaEstipulada = calendar2.getTime();
            }
        } else {
            mesesAux = 12;
            while (this.fechaAplicacion.compareTo(calendar2.getTime()) >= 0) {
                int dia = 0;
                dia = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (dia));
                numeroCuotasRetraso++;
                this.pagoMora.setFecha(this.fechaEstipulada);
                this.pagoMora.setIdpago(dia);
                this.pagoMora.setCuota(this.couto);
                this.pagoMora.setAbono(new BigDecimal(moraGenerada));
                this.pagoMora.setInteres(this.pagoMora.getAbono().add(this.couto));
                this.listaPagosMora.add(this.pagoMora);
                calendar2.add(Calendar.MONTH, mesesAux);
                this.fechaEstipulada = calendar2.getTime();
            }
        }
        for (int i = 0; i < this.listaPagosMora.size(); i++) {
            this.totalMoraGenerada = this.totalMoraGenerada.add(this.listaPagosMora.get(i).getInteres());
        }
        this.numeroCuotasRetraso = numeroCuotasRetraso;

    }

    private BigDecimal totalGeneral;

    public BigDecimal getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(BigDecimal totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    public void cargarVistaPagos2(int tipo) {

        if (this.proyectoSeleccionado.getEstado() == 3) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("No hay historial de para este crédito"));
            this.estadoFormulario = false;

        } else {

            this.totalDesembolsos = new BigDecimal("0");
            this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());

            this.totalCuotas = new BigDecimal("0");
            this.totalInteres = new BigDecimal("0");
            this.totalAbono = new BigDecimal("0");
            this.totalAmortizado = new BigDecimal("0");
            this.totalCapital = new BigDecimal("0");

            for (int i = 0; i < this.listaPago.size(); i++) {
                this.totalCuotas = this.totalCuotas.add(this.listaPago.get(i).getCuota());
                this.totalInteres = this.totalInteres.add(this.listaPago.get(i).getInteres());
                this.totalAbono = this.totalAbono.add(this.listaPago.get(i).getAbono());
                this.totalAmortizado = this.totalAmortizado.add(this.listaPago.get(i).getCapitalamortizado());
                this.totalCapital = this.totalCapital.add(this.listaPago.get(i).getSaldocapital());
            }

            this.totalGeneral = this.totalAbono.add(this.totalInteres);

            this.cambiar(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
            this.cambiarForma(this.proyectoSeleccionado.getFormaPagoProyecto());
            this.cambiarEstado(this.proyectoSeleccionado.getEstado());

            this.numeroCuota = this.listaPago.size();
            this.totalCuotas();
            this.mostrarTipoCliente = cambiar(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
            this.listaDesembolso = this.idesembolsoBo.listDesembolso(this.proyectoSeleccionado.getIdproyecto());
            this.numeroDesembolso = this.listaDesembolso.size();
            for (int i = 0; i < this.listaDesembolso.size(); i++) {
                this.totalDesembolsos = this.totalDesembolsos.add(this.listaDesembolso.get(i).getMontoDesembolso());
            }
            generarDistribucion();
            if (tipo == 1) {
                this.mostrarHistorialCooperativa = true;
                this.mostrarHistorial = false;
            } else {
                this.mostrarHistorialCooperativa = false;
                this.mostrarHistorial = true;
            }
        }

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
            return this.tipo = "Crédito para producción agropecuario";
        } else if (valor == 3) {
            return this.tipo = "Crédito para producción cooperativa";
        } else if (valor == 2) {
            return this.tipo = "Crédito para inversión cooperativa";
        } else {
            return this.tipo = "Crédito para empleados";
        }

    }

    public String cambiarEstado(Integer valor) {

        if (valor == 1) {
            return this.estadoCredito = "Solicitud";
        } else if (valor == 2) {
            return this.estadoCredito = "Resolucion";
        } else if (valor == 3) {
            return this.estadoCredito = "Aprobado/proceso";
        } else if (valor == 4) {
            return this.estadoCredito = "No aprovado";
        } else if (valor == 5) {
            return this.estadoCredito = "Aprobado/ejecutandose";
        } else if (valor == 6) {
            return this.estadoCredito = "Finalizado";
        } else {
            return this.estadoCredito = "Credito incobrable";
        }

    }

    public String cambiarForma(Integer valor) {

        if (valor == 1) {
            return this.formaPago = "Mensual";
        } else if (valor == 2) {
            return this.formaPago = "Trimestral";
        } else if (valor == 3) {
            return this.formaPago = "Semestral";
        } else {
            return this.formaPago = "Anual";
        }

    }

    public void updateListaTipos() {

        listaClienteProyectoReducida.clear();
        listaEntidadProyectosReducida.clear();
        this.tipoMora = 0;
        if (this.tipoCliente == 1) {
            this.listaClienteProyectoReducida.clear();
            boolean esta2 = false;
            this.listaClienteProyectos = this.iclienteProyectoBo.listTClienteProyectoEjecutandose(0);
            for (int i = 0; i < this.listaClienteProyectos.size(); i++) {
                if (i == 0) {
                    this.listaClienteProyectoReducida.add(this.listaClienteProyectos.get(i));
                } else {
                    for (int y = 0; y < this.listaClienteProyectoReducida.size(); y++) {
                        if (this.listaClienteProyectos.get(i).getTCliente().getIdCliente() == this.listaClienteProyectoReducida.get(y).getTCliente().getIdCliente()) {
                            esta2 = true;
                        }
                    }
                    if (esta2 == false) {
                        this.listaClienteProyectoReducida.add(this.listaClienteProyectos.get(i));
                    }
                }
                esta2 = false;
            }
        } else if (this.tipoCliente == 2) {
            this.listaEntidadProyectosReducida.clear();
            boolean esta = false;
            this.listaEntidadProyectos = this.ientidadProyectoBo.listTEndidadProyectosEjecutandose(0);
            for (int i = 0; i < this.listaEntidadProyectos.size(); i++) {
                if (i == 0) {
                    this.listaEntidadProyectosReducida.add(this.listaEntidadProyectos.get(i));
                } else {
                    for (int y = 0; y < this.listaEntidadProyectosReducida.size(); y++) {
                        if (this.listaEntidadProyectos.get(i).getTEntidad().getIdEntidad() == this.listaEntidadProyectosReducida.get(y).getTEntidad().getIdEntidad()) {
                            esta = true;
                        }
                    }
                    if (esta == false) {
                        this.listaEntidadProyectosReducida.add(this.listaEntidadProyectos.get(i));
                    }
                }
                esta = false;
            }
        } else {
        }

    }

    public void mostrarTipos() {

        listaClienteProyectoReducida.clear();
        listaEntidadProyectosReducida.clear();
        this.tipoMora = 0;
        if (this.tipoCliente == 1) {
            this.mostrarCooperativas = false;
            this.mostrarPersonas = true;
            this.mostrarMorosos = false;
            this.mostrarMorososCooperativas = false;
        } else if (this.tipoCliente == 2) {
            this.mostrarCooperativas = true;
            this.mostrarPersonas = false;
            this.mostrarMorosos = false;
            this.mostrarMorososCooperativas = false;
        } else {
            this.mostrarCooperativas = false;
            this.mostrarPersonas = false;
            this.mostrarMorosos = false;
            this.mostrarMorososCooperativas = false;
        }

    }

    public void morosos() {

        if (this.tipoCliente == 1) {
            if (this.tipoMora == 1) {
                this.mostrarMorosos = true;
                this.mostrarCooperativas = false;
                this.mostrarPersonas = false;
                this.mostrarMorososCooperativas = false;
            } else if (this.tipoMora == 2) {
                this.mostrarMorosos = true;
                this.mostrarCooperativas = false;
                this.mostrarPersonas = false;
                this.mostrarMorososCooperativas = false;
            } else {
                this.mostrarCooperativas = false;
                this.mostrarPersonas = false;
                this.mostrarMorosos = false;
                this.mostrarMorososCooperativas = false;
                this.mostrarTipos();
            }
        } else if (this.tipoCliente == 2) {
            if (this.tipoMora == 1) {
                this.mostrarMorosos = false;
                this.mostrarCooperativas = false;
                this.mostrarPersonas = false;
                this.mostrarMorososCooperativas = true;
            } else if (this.tipoMora == 2) {
                this.mostrarMorosos = false;
                this.mostrarCooperativas = false;
                this.mostrarPersonas = false;
                this.mostrarMorososCooperativas = true;
            } else {
                this.mostrarCooperativas = false;
                this.mostrarPersonas = false;
                this.mostrarMorosos = false;
                this.mostrarMorososCooperativas = false;
                this.mostrarTipos();
            }
        } else {
            this.mostrarMorosos = false;
            this.mostrarCooperativas = false;
            this.mostrarPersonas = false;
            this.mostrarMorososCooperativas = false;

        }

    }

    public void updateListaMorosos() {

        if (this.tipoCliente == 1) {
            this.listaClienteProyectoMorosos.clear();
            this.listaClienteProyectoNoMorosos.clear();
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyectoAprovadosEjecutandose();
            for (int i = 0; i < this.listaClienteProyectoReducida.size(); i++) {
                this.listaPago = this.ipagoBo.listTPago(this.listaClienteProyectoReducida.get(i).getTProyecto().getIdproyecto());
                if (this.listaClienteProyectoReducida.get(i).getTProyecto().getEstado() == 5) {
                    int meses = 0;
                    if (this.listaClienteProyectoReducida.get(i).getTProyecto().getFormaPagoProyecto() == 1) {
                        meses = 1;
                    } else if (this.listaClienteProyectoReducida.get(i).getTProyecto().getFormaPagoProyecto() == 2) {
                        meses = 3;
                    } else if (this.listaClienteProyectoReducida.get(i).getTProyecto().getFormaPagoProyecto() == 3) {
                        meses = 6;
                    } else {
                        meses = 12;
                    }
                    TDesembolso desembolsoInicial = this.idesembolsoBo.getDesembolso(this.listaClienteProyectoReducida.get(i).getTProyecto().getIdproyecto());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(desembolsoInicial.getFechaDesembolso()); // Configuramos la fecha que se recibe
                    if (meses == 1) {
                        if (this.listaPago.size() == 0) {
                            calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0
                        } else {
                            for (int y = 0; y <= this.listaPago.size(); y++) {
                                calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0
                            }
                        }
                    } else if (meses == 3) {
                        if (this.listaPago.size() == 0) {
                            calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0
                        } else {
                            for (int y = 0; y <= this.listaPago.size(); y++) {
                                calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0
                            }
                        }
                    } else if (meses == 6) {
                        if (this.listaPago.size() == 0) {
                            calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0
                        } else {
                            for (int y = 0; y <= this.listaPago.size(); y++) {
                                calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0
                            }
                        }
                    } else if (this.listaPago.size() == 0) {
                        calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0
                    } else {
                        for (int y = 0; y <= this.listaPago.size(); y++) {
                            calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0
                        }
                    }
                    this.fechaEstipulada = calendar.getTime();
                    System.out.println(this.fechaEstipulada);
                    DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
                    inFormat.setTimeZone(TimeZone.getTimeZone("America/Guatemala"));
                    Date purchaseDate = new Date();
                    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        this.fechaAplicacion = formatoDelTexto.parse(inFormat.format(purchaseDate));
                    } catch (ParseException ex) {
                        Logger.getLogger(CarteraBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(this.fechaAplicacion);
                    int dia = 0;
                    if (this.fechaAplicacion.compareTo(this.fechaEstipulada) > 0) {
                        dia = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                        this.diasRetraso = dia;
                    } else {
                        this.mora = BigDecimal.ZERO;
                        this.diasRetraso = 0;
                    }
                    if (this.diasRetraso == 0) {
                        this.listaClienteProyectoNoMorosos.add(this.listaClienteProyectoReducida.get(i));
                    } else {
                        this.listaClienteProyectoMorosos.add(this.listaClienteProyectoReducida.get(i));
                    }
                }
            }
            if (this.tipoMora == 1) {
                this.mostrarMorosos = true;
                this.mostrarCooperativas = false;
                this.mostrarPersonas = false;
                this.mostrarMorososCooperativas = false;
            } else if (this.tipoMora == 2) {
                this.mostrarMorosos = true;
                this.mostrarCooperativas = false;
                this.mostrarPersonas = false;
                this.mostrarMorososCooperativas = false;
            } else {
                this.mostrarCooperativas = false;
                this.mostrarPersonas = false;
                this.mostrarMorosos = false;
                this.mostrarMorososCooperativas = false;
            }
        } else if (this.tipoCliente == 2) {
            this.listaEntidadProyectoMorosos.clear();
            this.listaEntidadProyectoNoMorosos.clear();
            this.listaEntidadProyectosReducida = this.ientidadProyectoBo.listTEndidadProyectosAprovadosEjecutandose(0);
            for (int i = 0; i < this.listaEntidadProyectosReducida.size(); i++) {
                if (this.listaEntidadProyectosReducida.get(i).getTProyecto().getEstado() == 5) {
                    this.listaPago = this.ipagoBo.listTPago(this.listaEntidadProyectosReducida.get(i).getTProyecto().getIdproyecto());
                    int meses = 0;
                    if (this.listaEntidadProyectosReducida.get(i).getTProyecto().getFormaPagoProyecto() == 1) {
                        meses = 1;
                    } else if (this.listaEntidadProyectosReducida.get(i).getTProyecto().getFormaPagoProyecto() == 2) {
                        meses = 3;
                    } else if (this.listaEntidadProyectosReducida.get(i).getTProyecto().getFormaPagoProyecto() == 3) {
                        meses = 6;
                    } else {
                        meses = 12;
                    }
                    TDesembolso desembolsoInicial = this.idesembolsoBo.getDesembolso(this.listaEntidadProyectosReducida.get(i).getTProyecto().getIdproyecto());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(desembolsoInicial.getFechaDesembolso()); // Configuramos la fecha que se recibe
                    if (meses == 1) {
                        if (this.listaPago.size() == 0) {
                            calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0
                        } else {
                            for (int y = 0; y <= this.listaPago.size(); y++) {
                                calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0                         
                            }
                        }
                    } else if (meses == 3) {
                        if (this.listaPago.size() == 0) {
                            calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0
                        } else {
                            for (int y = 0; y <= this.listaPago.size(); y++) {
                                calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0                       
                            }
                        }
                    } else if (meses == 6) {
                        if (this.listaPago.size() == 0) {
                            calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0
                        } else {
                            for (int y = 0; y <= this.listaPago.size(); y++) {
                                calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0                         
                            }
                        }
                    } else if (this.listaPago.size() == 0) {
                        calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0
                    } else {
                        for (int y = 0; y <= this.listaPago.size(); y++) {
                            calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0                    
                        }
                    }
                    this.fechaEstipulada = calendar.getTime();
                    DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
                    inFormat.setTimeZone(TimeZone.getTimeZone("America/Guatemala"));
                    Date purchaseDate = new Date();
                    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        this.fechaAplicacion = formatoDelTexto.parse(inFormat.format(purchaseDate));
                    } catch (ParseException ex) {
                        Logger.getLogger(CarteraBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int dia = 0;
                    if (this.fechaAplicacion.compareTo(this.fechaEstipulada) > 0) {
                        dia = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                        this.diasRetraso = dia;
                    } else {
                        this.mora = BigDecimal.ZERO;
                        this.diasRetraso = 0;
                    }
                    if (this.diasRetraso == 0) {
                        this.listaEntidadProyectoNoMorosos.add(this.listaEntidadProyectosReducida.get(i));
                    } else {
                        this.listaEntidadProyectoMorosos.add(this.listaEntidadProyectosReducida.get(i));
                    }
                }
            }
            if (this.tipoMora == 1) {
                this.mostrarMorosos = false;
                this.mostrarCooperativas = false;
                this.mostrarPersonas = false;
                this.mostrarMorososCooperativas = true;
            } else if (this.tipoMora == 2) {
                this.mostrarMorosos = false;
                this.mostrarCooperativas = false;
                this.mostrarPersonas = false;
                this.mostrarMorososCooperativas = true;
            } else {
                this.mostrarCooperativas = false;
                this.mostrarPersonas = false;
                this.mostrarMorosos = false;
                this.mostrarMorososCooperativas = false;
            }
        } else {
            this.mostrarMorosos = false;
            this.mostrarCooperativas = false;
            this.mostrarPersonas = false;
            this.mostrarMorososCooperativas = false;
        }

    }

    public void cargarVistaModificar() {

        this.lisiadoSeleccionado = new TLisiado();

        this.lisiadoSeleccionado = this.ilisiadoBo.getLisiado(this.clienteSeleccionado.getIdCliente());

        if (this.lisiadoSeleccionado != null) {

            if (this.lisiadoSeleccionado.getNombreConyugue() != "") {

                this.estadoConyugue = true;
            } else {

                this.estadoConyugue = false;
            }

            if (this.lisiadoSeleccionado.getNombreHijoMayor() != "") {
                this.estadoHijos = true;

            } else {
                this.estadoHijos = false;

            }

        }

        this.setShowUpdate(true);
        this.setShowData(false);

    }

    public void cargarVistaEmpleado() {

        this.trabajoSeleccionado = this.itrabajoBo.getTrabajo(this.clienteSeleccionado.getIdCliente());
        this.setShowUpdate(false);
        this.modificarClienteEmpleado = true;

    }

    public void ocultar() {

        if (this.lisiadoSeleccionado.getExperienciaCrediticia() == false) {
            this.lisiadoSeleccionado.setMontoCreditoExperiencia(BigDecimal.ZERO);
            this.lisiadoSeleccionado.setInstitucionCrediticia("");
        } else {
        }

    }

    public void cargarVistaCliente() {

        calcularEdad();
        this.lisiadoSeleccionado = this.ilisiadoBo.getLisiado(this.clienteSeleccionado.getIdCliente());

    }

    public void totalCuotas() {

        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            this.cantidadCuotas = this.proyectoSeleccionado.getPlazo() / 1;
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            this.cantidadCuotas = this.proyectoSeleccionado.getPlazo() / 3;
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            this.cantidadCuotas = this.proyectoSeleccionado.getPlazo() / 6;
        }
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 4) {
            this.cantidadCuotas = this.proyectoSeleccionado.getPlazo() / 12;
        }

    }

    public void validarCliente() {

        this.estadoFormulario = true;
        if (this.clienteSeleccionado.getNombreCliente() == "") {
            this.msgNombre = "Debe introducir los nombres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        if (this.clienteSeleccionado.getApellidoCliente() == "") {
            this.msgApellido = "Debe introducir los apellidos";
            this.estadoFormulario = false;
        } else {
            this.msgApellido = "";
        }
        if (this.clienteSeleccionado.getDuiCliente() != "") {

            Boolean estaBd = this.iclienteBo.getDuiCliente(this.clienteSeleccionado.getDuiCliente());

            if (!(this.clienteSeleccionado.getDuiCliente().equals(this.duiAux))) {

                System.out.println(this.clienteSeleccionado.getDuiCliente());
                System.out.println(this.duiAux);

                if (estaBd) {
                    this.msgDui = "El DUI introducido ya fue registrado";
                    this.estadoFormulario = false;

                }

            } else {

                this.msgDui = "";
            }
        } else {

            this.msgDui = "Debe introducir el DUI";
            this.estadoFormulario = false;

        }

        if (this.clienteSeleccionado.getNitCliente() != "") {

            Boolean estaBd = this.iclienteBo.getNitCliente(this.clienteSeleccionado.getNitCliente());

            if (!(this.clienteSeleccionado.getNitCliente().equals(this.nitAux))) {

                if (estaBd) {
                    this.msgNit = "El NIT introducido ya fue registrado";
                    this.estadoFormulario = false;

                }

            } else {

                this.msgNit = "";
            }
        } else {

            this.msgNit = "Debe introducir el NIT";
            this.estadoFormulario = false;

        }

        if (this.clienteSeleccionado.getFechaNacimiento() == null) {
            this.msgFechaNacimiento = "Debe introducir la fecha de nacimiento";
            this.estadoFormulario = false;
        } else {
            this.msgFechaNacimiento = "";
        }
        if (this.clienteSeleccionado.getSexoCliente() == "") {
            this.msgSexo = "Debe seleccionar el sexo";
            this.estadoFormulario = false;
        } else {
            this.msgSexo = "";
        }
        if (this.clienteSeleccionado.getEstadoFamiliar() == "") {
            this.msgEstadoFamiliar = "Debe seleccionar el estado familiar";
            this.estadoFormulario = false;
        } else {
            this.msgEstadoFamiliar = "";
        }
        if (this.clienteSeleccionado.getTelefonoCliente() == "") {
            this.msgTelefono = "Debe introducir el teléfono";
            this.estadoFormulario = false;
        } else {
            this.msgTelefono = "";
        }
        if (this.clienteSeleccionado.getCelularCliente() == "") {
            this.msgMovil = "Debe introducir el móvil";
            this.estadoFormulario = false;
        } else {
            this.msgMovil = "";
        }
        if (this.clienteSeleccionado.getDireccionCliente() == "") {
            this.msgDireccion = "Debe introducir la dirección";
            this.estadoFormulario = false;
        } else {
            this.msgDireccion = "";
        }
        if (this.clienteSeleccionado.getDuiCopiaCliente() == "") {
            this.msgFotoDui = "Debe introducir la imagen del DUI";
            this.estadoFormulario = false;
        } else {
            this.msgFotoDui = "";
        }
        if (this.clienteSeleccionado.getNitCopiaCliente() == "") {
            this.msgFotoNit = "Debe introducir la imagen del NIT";
            this.estadoFormulario = false;
        } else {
            this.msgFotoNit = "";

        }
        if (this.lisiadoSeleccionado != null) {
            validarClientePersonaLisiado();
        }

    }

    public void validarClientePersonaLisiado() {

        if (this.lisiadoSeleccionado.getTipoLesion() == "") {
            this.msgLesion = "Debe introducir el tipo de lesión";
            this.estadoFormulario = false;
        } else {
            this.msgLesion = "";

        }

        if (this.estadoConyugue == true) {

            if (this.lisiadoSeleccionado.getNombreConyugue() == "") {
                this.msgNombreConyugue = "Debe introducir los nombres";
                this.estadoFormulario = false;
            } else {
                this.msgNombreConyugue = "";

            }
            if (this.lisiadoSeleccionado.getApellidoConyugue() == "") {
                this.msgApellidoConyugue = "Debe introducir los apellidos";
                this.estadoFormulario = false;
            } else {
                this.msgApellidoConyugue = "";

            }
            if (this.lisiadoSeleccionado.getEdadConyugue() == null) {
                this.msgedadConyugue = "Debe introducir la edad";
                this.estadoFormulario = false;
            } else {
                this.msgedadConyugue = "";

            }

        }

        if (this.estadoHijos == true) {

            if (this.lisiadoSeleccionado.getNumeroHijos() == null) {
                this.msgHijosConyugue = "Debe introducir el numero de hijos";
                this.estadoFormulario = false;
            } else {
                this.msgHijosConyugue = "";

            }
            if (this.lisiadoSeleccionado.getNombreHijoMayor() == "") {
                this.msgNombreMayor = "Debe introducir los nombres";
                this.estadoFormulario = false;
            } else {
                this.msgNombreMayor = "";

            }
            if (this.lisiadoSeleccionado.getApellidoHijo() == "") {
                this.msgApellidoMayor = "Debe introducir los apellidos";
                this.estadoFormulario = false;
            } else {
                this.msgApellidoMayor = "";

            }
            if (this.lisiadoSeleccionado.getEdadHijo() == null) {
                this.msgEdadMayor = "Debe introducir la edad";
                this.estadoFormulario = false;
            } else {
                this.msgEdadMayor = "";

            }

        }

        if (this.lisiadoSeleccionado.getExperienciaCrediticia() == true) {
            if (this.lisiadoSeleccionado.getInstitucionCrediticia() == "") {
                this.msgInstitucion = "Debe introducir el nombre";
                this.estadoFormulario = false;
            } else {
                this.msgInstitucion = "";

            }
            if (this.lisiadoSeleccionado.getMontoCreditoExperiencia() == null || this.lisiadoSeleccionado.getMontoCreditoExperiencia().compareTo(BigDecimal.ZERO) <= 0) {
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

    public void verMorosos() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clienteCooperativa.jasper"));
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
        auxBitacora.setTableBitacora("t_cliente");
        auxBitacora.setAccionBitacora("Generar reporte de morosos");
        auxBitacora.setDatosBitacora("");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.Entidadeleccionada.getIdEntidad());
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

    public void verDetalleMora() throws SQLException, JRException, IOException {

        Map<String, Object> parametros = new HashMap();
        TEntidad auxEntidad = ientidadBo.getTEntidad(1);
        parametros.put("nombreEntidad", auxEntidad.getNombreEntidad());
        parametros.put("direccionEntidad", auxEntidad.getDireccionEntidad());
        parametros.put("telEntidad", auxEntidad.getTelefonoEntidad());
        parametros.put("logoEntidad", auxEntidad.getLogoEntidad());
        parametros.put("codigoCliente", this.clienteSeleccionado.getCodigoCliente());
        parametros.put("nombreCliente", this.clienteSeleccionado.getNombreCliente());
        parametros.put("codigoCredito", this.proyectoSeleccionado.getCodigoProyecto());
        parametros.put("modalidadCredito", this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        parametros.put("apellido", this.clienteSeleccionado.getApellidoCliente());
        parametros.put("dui", this.clienteSeleccionado.getDuiCliente());
        parametros.put("monto", this.proyectoSeleccionado.getMonto());
        parametros.put("plazo", this.proyectoSeleccionado.getPlazo());
        parametros.put("forma", this.proyectoSeleccionado.getFormaPagoProyecto());
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaPagosMora);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/detalleMoraPersona.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, dataSource);
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
        auxBitacora.setTableBitacora("t_pago");
        auxBitacora.setAccionBitacora("Generar reporte de mora");
        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Código cliente: " + this.clienteSeleccionado.getCodigoCliente()
                + ", Nombre: " + this.clienteSeleccionado.getNombreCliente()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.Entidadeleccionada.getIdEntidad());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verDetalleMoraCooperativa() throws SQLException, JRException, IOException {

        Map<String, Object> parametros = new HashMap();
        TEntidad auxEntidad = ientidadBo.getTEntidad(1);
        parametros.put("nombreEntidad", auxEntidad.getNombreEntidad());
        parametros.put("direccionEntidad", auxEntidad.getDireccionEntidad());
        parametros.put("telEntidad", auxEntidad.getTelefonoEntidad());
        parametros.put("logoEntidad", auxEntidad.getLogoEntidad());
        parametros.put("codigoCliente", this.Entidadeleccionada.getCodigoClienteEntidad());
        parametros.put("nombreCliente", this.Entidadeleccionada.getNombreEntidad());
        parametros.put("codigoCredito", this.proyectoSeleccionado.getCodigoProyecto());
        parametros.put("modalidadCredito", this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        parametros.put("cooperativa", this.Entidadeleccionada.getCodigoEntidad());
        parametros.put("monto", this.proyectoSeleccionado.getMonto());
        parametros.put("plazo", this.proyectoSeleccionado.getPlazo());
        parametros.put("forma", this.proyectoSeleccionado.getFormaPagoProyecto());
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaPagosMora);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/detalleMoraCooperativa.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, dataSource);
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
        auxBitacora.setTableBitacora("t_pago");
        auxBitacora.setAccionBitacora("Generar reporte de mora");
        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Código cliente: " + this.Entidadeleccionada.getCodigoClienteEntidad()
                + ", Nombre: " + this.Entidadeleccionada.getNombreEntidad()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.Entidadeleccionada.getIdEntidad());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verDetalleMoraPDF() throws SQLException, JRException, IOException {

        Map<String, Object> parametros = new HashMap();
        TEntidad auxEntidad = ientidadBo.getTEntidad(1);
        parametros.put("nombreEntidad", auxEntidad.getNombreEntidad());
        parametros.put("direccionEntidad", auxEntidad.getDireccionEntidad());
        parametros.put("telEntidad", auxEntidad.getTelefonoEntidad());
        parametros.put("logoEntidad", auxEntidad.getLogoEntidad());
        parametros.put("codigoCliente", this.clienteSeleccionado.getCodigoCliente());
        parametros.put("nombreCliente", this.clienteSeleccionado.getNombreCliente());
        parametros.put("codigoCredito", this.proyectoSeleccionado.getCodigoProyecto());
        parametros.put("modalidadCredito", this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        parametros.put("apellido", this.clienteSeleccionado.getApellidoCliente());
        parametros.put("dui", this.clienteSeleccionado.getDuiCliente());
        parametros.put("monto", this.proyectoSeleccionado.getMonto());
        parametros.put("plazo", this.proyectoSeleccionado.getPlazo());
        parametros.put("forma", this.proyectoSeleccionado.getFormaPagoProyecto());
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaPagosMora);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/detalleMoraPersona.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, dataSource);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Detalle de mora.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_pago");
        auxBitacora.setAccionBitacora("Descargar reporte de mora");
        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Código cliente: " + this.clienteSeleccionado.getCodigoCliente()
                + ", Nombre: " + this.clienteSeleccionado.getNombreCliente()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.Entidadeleccionada.getIdEntidad());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verDetalleMoraCooperativaPDF() throws SQLException, JRException, IOException {

        Map<String, Object> parametros = new HashMap();
        TEntidad auxEntidad = ientidadBo.getTEntidad(1);
        parametros.put("nombreEntidad", auxEntidad.getNombreEntidad());
        parametros.put("direccionEntidad", auxEntidad.getDireccionEntidad());
        parametros.put("telEntidad", auxEntidad.getTelefonoEntidad());
        parametros.put("logoEntidad", auxEntidad.getLogoEntidad());
        parametros.put("codigoCliente", this.Entidadeleccionada.getCodigoClienteEntidad());
        parametros.put("nombreCliente", this.Entidadeleccionada.getNombreEntidad());
        parametros.put("codigoCredito", this.proyectoSeleccionado.getCodigoProyecto());
        parametros.put("modalidadCredito", this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        parametros.put("cooperativa", this.Entidadeleccionada.getCodigoEntidad());
        parametros.put("monto", this.proyectoSeleccionado.getMonto());
        parametros.put("plazo", this.proyectoSeleccionado.getPlazo());
        parametros.put("forma", this.proyectoSeleccionado.getFormaPagoProyecto());
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaPagosMora);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/detalleMoraCooperativa.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, dataSource);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Detalle de mora.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_pago");
        auxBitacora.setAccionBitacora("Descargar reporte de mora");
        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Código cliente: " + this.Entidadeleccionada.getCodigoClienteEntidad()
                + ", Nombre: " + this.Entidadeleccionada.getNombreEntidad()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.Entidadeleccionada.getIdEntidad());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void reporteClientesMorosos() throws SQLException, JRException, IOException {

        this.listaEntidadesMorosas = new ArrayList<TEntidad>();
        this.listaClientesMorosos = new ArrayList<TCliente>();
        for (int i = 0; i < this.listaClienteProyectoMorosos.size(); i++) {
            this.listaClientesMorosos.add(this.listaClienteProyectoMorosos.get(i).getTCliente());
        }
        Map<String, Object> parametros = new HashMap();
        TEntidad auxEntidad = ientidadBo.getTEntidad(1);
        parametros.put("nombreEntidad", auxEntidad.getNombreEntidad());
        parametros.put("direccionEntidad", auxEntidad.getDireccionEntidad());
        parametros.put("telEntidad", auxEntidad.getTelefonoEntidad());
        parametros.put("logoEntidad", auxEntidad.getLogoEntidad());
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaClientesMorosos);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientesPersonasNoMorosos.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, dataSource);
        System.out.println(bytes.length);
        System.out.println(this.listaClientesMorosos.size());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cliente");
        auxBitacora.setAccionBitacora("Generar reporte de clientes morosos");
        auxBitacora.setDatosBitacora("");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void reporteClientesNoMorosos() throws SQLException, JRException, IOException {

        this.listaEntidadesMorosas = new ArrayList<TEntidad>();
        this.listaClientesMorosos = new ArrayList<TCliente>();
        for (int i = 0; i < this.listaClienteProyectoNoMorosos.size(); i++) {
            this.listaClientesMorosos.add(this.listaClienteProyectoNoMorosos.get(i).getTCliente());
        }
        Map<String, Object> parametros = new HashMap();
        TEntidad auxEntidad = ientidadBo.getTEntidad(1);
        parametros.put("nombreEntidad", auxEntidad.getNombreEntidad());
        parametros.put("direccionEntidad", auxEntidad.getDireccionEntidad());
        parametros.put("telEntidad", auxEntidad.getTelefonoEntidad());
        parametros.put("logoEntidad", auxEntidad.getLogoEntidad());
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaClientesMorosos);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientesPersonasMorosos.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, dataSource);
        System.out.println(bytes.length);
        System.out.println(this.listaClientesMorosos.size());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cliente");
        auxBitacora.setAccionBitacora("Generar reporte de clientes no morosos");
        auxBitacora.setDatosBitacora("");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void reporteClientesMorososPDF() throws SQLException, JRException, IOException {

        this.listaEntidadesMorosas = new ArrayList<TEntidad>();
        this.listaClientesMorosos = new ArrayList<TCliente>();
        for (int i = 0; i < this.listaClienteProyectoMorosos.size(); i++) {
            this.listaClientesMorosos.add(this.listaClienteProyectoMorosos.get(i).getTCliente());
        }
        Map<String, Object> parametros = new HashMap();
        TEntidad auxEntidad = ientidadBo.getTEntidad(1);
        parametros.put("nombreEntidad", auxEntidad.getNombreEntidad());
        parametros.put("direccionEntidad", auxEntidad.getDireccionEntidad());
        parametros.put("telEntidad", auxEntidad.getTelefonoEntidad());
        parametros.put("logoEntidad", auxEntidad.getLogoEntidad());
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaClientesMorosos);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientesPersonasNoMorosos.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, dataSource);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Clientes con mora.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cliente");
        auxBitacora.setAccionBitacora("Descargar reporte de clientes morosos");
        auxBitacora.setDatosBitacora("");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void reporteClientesNoMorososPDF() throws SQLException, JRException, IOException {

        this.listaEntidadesMorosas = new ArrayList<TEntidad>();
        this.listaClientesMorosos = new ArrayList<TCliente>();
        for (int i = 0; i < this.listaClienteProyectoNoMorosos.size(); i++) {
            this.listaClientesMorosos.add(this.listaClienteProyectoNoMorosos.get(i).getTCliente());
        }
        Map<String, Object> parametros = new HashMap();
        TEntidad auxEntidad = ientidadBo.getTEntidad(1);
        parametros.put("nombreEntidad", auxEntidad.getNombreEntidad());
        parametros.put("direccionEntidad", auxEntidad.getDireccionEntidad());
        parametros.put("telEntidad", auxEntidad.getTelefonoEntidad());
        parametros.put("logoEntidad", auxEntidad.getLogoEntidad());
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaClientesMorosos);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientesPersonasMorosos.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, dataSource);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Clientes sin mora.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cliente");
        auxBitacora.setAccionBitacora("Descargar reporte de clientes no morosos");
        auxBitacora.setDatosBitacora("");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void reporteEntidadesMorosos() throws SQLException, JRException, IOException {

        this.listaEntidadesMorosas = new ArrayList<TEntidad>();
        this.listaClientesMorosos = new ArrayList<TCliente>();
        for (int i = 0; i < this.listaEntidadProyectoMorosos.size(); i++) {
            this.listaEntidadesMorosas.add(this.listaEntidadProyectoMorosos.get(i).getTEntidad());
        }
        Map<String, Object> parametros = new HashMap();
        TEntidad auxEntidad = ientidadBo.getTEntidad(1);
        parametros.put("nombreEntidad", auxEntidad.getNombreEntidad());
        parametros.put("direccionEntidad", auxEntidad.getDireccionEntidad());
        parametros.put("telEntidad", auxEntidad.getTelefonoEntidad());
        parametros.put("logoEntidad", auxEntidad.getLogoEntidad());
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaEntidadesMorosas);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientesEntidadesMorosos.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, dataSource);
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
        auxBitacora.setTableBitacora("t_entidad");
        auxBitacora.setAccionBitacora("Generar reporte de clientes morosos");
        auxBitacora.setDatosBitacora("");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void reporteEntidadesNoMorosos() throws SQLException, JRException, IOException {

        this.listaEntidadesMorosas = new ArrayList<TEntidad>();
        this.listaClientesMorosos = new ArrayList<TCliente>();
        for (int i = 0; i < this.listaEntidadProyectoMorosos.size(); i++) {
            this.listaEntidadesMorosas.add(this.listaEntidadProyectoNoMorosos.get(i).getTEntidad());
        }
        Map<String, Object> parametros = new HashMap();
        TEntidad auxEntidad = ientidadBo.getTEntidad(1);
        parametros.put("nombreEntidad", auxEntidad.getNombreEntidad());
        parametros.put("direccionEntidad", auxEntidad.getDireccionEntidad());
        parametros.put("telEntidad", auxEntidad.getTelefonoEntidad());
        parametros.put("logoEntidad", auxEntidad.getLogoEntidad());
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaEntidadesMorosas);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientesEntidadesMorosos.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, dataSource);
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
        auxBitacora.setTableBitacora("t_entidad");
        auxBitacora.setAccionBitacora("Generar reporte de clientes no morosos");
        auxBitacora.setDatosBitacora("");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void reporteEntidadesMorososPDF() throws SQLException, JRException, IOException {

        this.listaEntidadesMorosas = new ArrayList<TEntidad>();
        this.listaClientesMorosos = new ArrayList<TCliente>();
        for (int i = 0; i < this.listaEntidadProyectoMorosos.size(); i++) {
            this.listaEntidadesMorosas.add(this.listaEntidadProyectoMorosos.get(i).getTEntidad());
        }
        Map<String, Object> parametros = new HashMap();
        TEntidad auxEntidad = ientidadBo.getTEntidad(1);
        parametros.put("nombreEntidad", auxEntidad.getNombreEntidad());
        parametros.put("direccionEntidad", auxEntidad.getDireccionEntidad());
        parametros.put("telEntidad", auxEntidad.getTelefonoEntidad());
        parametros.put("logoEntidad", auxEntidad.getLogoEntidad());
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaEntidadesMorosas);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientesEntidadesMorosos.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, dataSource);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Clientes con mora.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_entidad");
        auxBitacora.setAccionBitacora("Descargar reporte de clientes morosos");
        auxBitacora.setDatosBitacora("");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void reporteEntidadesNoMorososPDF() throws SQLException, JRException, IOException {

        this.listaEntidadesMorosas = new ArrayList<TEntidad>();
        this.listaClientesMorosos = new ArrayList<TCliente>();
        for (int i = 0; i < this.listaEntidadProyectoMorosos.size(); i++) {
            this.listaEntidadesMorosas.add(this.listaEntidadProyectoNoMorosos.get(i).getTEntidad());
        }
        Map<String, Object> parametros = new HashMap();
        TEntidad auxEntidad = ientidadBo.getTEntidad(1);
        parametros.put("nombreEntidad", auxEntidad.getNombreEntidad());
        parametros.put("direccionEntidad", auxEntidad.getDireccionEntidad());
        parametros.put("telEntidad", auxEntidad.getTelefonoEntidad());
        parametros.put("logoEntidad", auxEntidad.getLogoEntidad());
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaEntidadesMorosas);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientesEntidadesMorosos.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, dataSource);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Clientes sin mora.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_entidad");
        auxBitacora.setAccionBitacora("Descarga reporte de clientes no morosos");
        auxBitacora.setDatosBitacora("");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void generarDui() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.clienteSeleccionado.getIdCliente());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/documentoDuiCliente.jasper"));
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
        auxBitacora.setTableBitacora("t_cliente");
        auxBitacora.setAccionBitacora("Generar DUI");

        auxBitacora.setDatosBitacora("Cliente:" + this.clienteSeleccionado.getCodigoCliente()
                + ", Nombre: " + this.clienteSeleccionado.getNombreCliente() + " " + this.clienteSeleccionado.getApellidoCliente()
                + ", DUI: " + this.clienteSeleccionado.getDuiCliente()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.clienteSeleccionado.getIdCliente());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void generarNit() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.clienteSeleccionado.getIdCliente());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/documentoNitCliente.jasper"));
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
        auxBitacora.setTableBitacora("t_cliente");
        auxBitacora.setAccionBitacora("Generar NIT");

        auxBitacora.setDatosBitacora("Cliente:" + this.clienteSeleccionado.getCodigoCliente()
                + ", Nombre: " + this.clienteSeleccionado.getNombreCliente() + " " + this.clienteSeleccionado.getApellidoCliente()
                + ", DUI: " + this.clienteSeleccionado.getDuiCliente()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.clienteSeleccionado.getIdCliente());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verReportePagosPersonaPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/pagosCreditoPersona.jasper"));
        if (this.proyectoSeleccionado.getEstado() == 6 || this.proyectoSeleccionado.getEstado() == 7) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/pagosCreditoPersonaTodos.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/pagosCreditoPersona.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Desembolsos.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_pago");
        auxBitacora.setAccionBitacora("Descargar reporte de pagos");

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
        auxBitacora.setIdTableBitacora(this.Entidadeleccionada.getIdEntidad());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verReportePagosCooperativaPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/pagosCreditoCooperativa.jasper"));
        if (this.proyectoSeleccionado.getEstado() == 6 || this.proyectoSeleccionado.getEstado() == 7) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/pagosCreditoCooperativaTodos.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/pagosCreditoCooperativa.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Pagos.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_pago");
        auxBitacora.setAccionBitacora("Descargar reporte de pagos");

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
        auxBitacora.setIdTableBitacora(this.Entidadeleccionada.getIdEntidad());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verDesembolsosCooperativaPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/desembolsosCooperativas.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Desembolsos.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_desembolso");
        auxBitacora.setAccionBitacora("Descargar reporte de desembolsos");

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
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verDesembolsosPersonasPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/desembolsosPersonas.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Desembolsos.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_desembolso");
        auxBitacora.setAccionBitacora("Descargar reporte de desembolsos");

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
        auxBitacora.setTableBitacora("t_lisiado");
        auxBitacora.setAccionBitacora("Generar carte de fondo de lisiado");

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

    public void verReporteHistorialCrediticio() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_cleinte", this.clienteSeleccionado.getIdCliente());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/historialCreditosPersona.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de historial crediticio");

        auxBitacora.setDatosBitacora("Cliente:" + this.clienteSeleccionado.getCodigoCliente()
                + ", Nombre: " + this.clienteSeleccionado.getNombreCliente() + " " + this.clienteSeleccionado.getApellidoCliente()
                + ", DUI: " + this.clienteSeleccionado.getDuiCliente()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verReporteHistorialCrediticioPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_cleinte", this.clienteSeleccionado.getIdCliente());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/historialCreditosPersona.jasper"));
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
        auxBitacora.setAccionBitacora("Descargar reporte de historial crediticio");

        auxBitacora.setDatosBitacora("Cliente:" + this.clienteSeleccionado.getCodigoCliente()
                + ", Nombre: " + this.clienteSeleccionado.getNombreCliente() + " " + this.clienteSeleccionado.getApellidoCliente()
                + ", DUI: " + this.clienteSeleccionado.getDuiCliente()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verReporteHistorialCrediticioCooperativa() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_entidad", this.Entidadeleccionada.getIdEntidad());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/historialCreditosCooperativas.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de historial crediticio");

        auxBitacora.setDatosBitacora("Cliente:" + this.Entidadeleccionada.getCodigoClienteEntidad()
                + ", Nombre: " + this.Entidadeleccionada.getNombreEntidad()
                + ", Nit: " + this.Entidadeleccionada.getNitEntidad()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verReporteHistorialCrediticioCooperativaPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_entidad", this.Entidadeleccionada.getIdEntidad());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/historialCreditosCooperativas.jasper"));
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
        auxBitacora.setAccionBitacora("Descargar reporte de historial crediticio");

        auxBitacora.setDatosBitacora("Cliente:" + this.Entidadeleccionada.getCodigoClienteEntidad()
                + ", Nombre: " + this.Entidadeleccionada.getNombreEntidad()
                + ", Nit: " + this.Entidadeleccionada.getNitEntidad()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verReportePagosPersona() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/pagosCreditoPersona.jasper"));
        if (this.proyectoSeleccionado.getEstado() == 6 || this.proyectoSeleccionado.getEstado() == 7) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/pagosCreditoPersonaTodos.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/pagosCreditoPersona.jasper"));
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
        auxBitacora.setTableBitacora("t_pago");
        auxBitacora.setAccionBitacora("Generar reporte de pagos");

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
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verReportePagosCooperativa() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/pagosCreditoCooperativa.jasper"));
        if (this.proyectoSeleccionado.getEstado() == 6 || this.proyectoSeleccionado.getEstado() == 7) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/pagosCreditoCooperativaTodos.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/pagosCreditoCooperativa.jasper"));
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
        auxBitacora.setTableBitacora("t_pago");
        auxBitacora.setAccionBitacora("Generar reporte de pagos");

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
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verClienteBasico() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_cliente", this.clienteSeleccionado.getIdCliente());
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientePersona.jasper"));
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
        auxBitacora.setTableBitacora("t_cliente");
        auxBitacora.setAccionBitacora("Generar reporte de cliente persona natural");
        auxBitacora.setDatosBitacora("Codigo:" + this.clienteSeleccionado.getCodigoCliente()
                + ", Nombre: " + this.clienteSeleccionado.getNombreCliente()
                + ", NIT: " + this.clienteSeleccionado.getNitCliente()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.clienteSeleccionado.getIdCliente());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verClienteLisiado() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_cliente", this.clienteSeleccionado.getIdCliente());
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientePersonaLisiado.jasper"));
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
        auxBitacora.setTableBitacora("t_cliente");
        auxBitacora.setAccionBitacora("Generar reporte de cliente persona natural");
        auxBitacora.setDatosBitacora("Codigo:" + this.clienteSeleccionado.getCodigoCliente()
                + ", Nombre: " + this.clienteSeleccionado.getNombreCliente()
                + ", NIT: " + this.clienteSeleccionado.getNitCliente()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.clienteSeleccionado.getIdCliente());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verClienteBasicoPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_cliente", this.clienteSeleccionado.getIdCliente());
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientePersona.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Cliente.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cliente");
        auxBitacora.setAccionBitacora("Descargar reporte de cliente persona natural");
        auxBitacora.setDatosBitacora("Codigo:" + this.clienteSeleccionado.getCodigoCliente()
                + ", Nombre: " + this.clienteSeleccionado.getNombreCliente()
                + ", NIT: " + this.clienteSeleccionado.getNitCliente()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.clienteSeleccionado.getIdCliente());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verClienteLisiadoPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_cliente", this.clienteSeleccionado.getIdCliente());
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientePersonaLisiado.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Cliente.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cliente");
        auxBitacora.setAccionBitacora("Descargar reporte de cliente persona natural");
        auxBitacora.setDatosBitacora("Codigo:" + this.clienteSeleccionado.getCodigoCliente()
                + ", Nombre: " + this.clienteSeleccionado.getNombreCliente()
                + ", NIT: " + this.clienteSeleccionado.getNitCliente()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.clienteSeleccionado.getIdCliente());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verDesembolsosCooperativa() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/desembolsosCooperativas.jasper"));
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
        auxBitacora.setTableBitacora("t_desembolso");
        auxBitacora.setAccionBitacora("Generar reporte de desembolsos");

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
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verDesembolsosPersonas() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/desembolsosPersonas.jasper"));
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
        auxBitacora.setTableBitacora("t_desembolso");
        auxBitacora.setAccionBitacora("Generar reporte de desembolsos");

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

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verClienteCooperativa() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_entidad", this.Entidadeleccionada.getIdEntidad());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clienteCooperativa.jasper"));
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
        auxBitacora.setTableBitacora("t_entidad");
        auxBitacora.setAccionBitacora("Generar reporte de cliente cooperativa");
        auxBitacora.setDatosBitacora("Codigo:" + this.Entidadeleccionada.getCodigoClienteEntidad()
                + ", Nombre: " + this.Entidadeleccionada.getNombreEntidad()
                + ", NIT: " + this.Entidadeleccionada.getNitEntidad()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.Entidadeleccionada.getIdEntidad());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verClienteCooperativaPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_entidad", this.Entidadeleccionada.getIdEntidad());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clienteCooperativa.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Cliente cooperativa.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_entidad");
        auxBitacora.setAccionBitacora("Descargar reporte de cliente cooperativa");
        auxBitacora.setDatosBitacora("Codigo:" + this.Entidadeleccionada.getCodigoClienteEntidad()
                + ", Nombre: " + this.Entidadeleccionada.getNombreEntidad()
                + ", NIT: " + this.Entidadeleccionada.getNitEntidad()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.Entidadeleccionada.getIdEntidad());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

}
