package com.sigaf.bean;

import com.sigaf.Ibo.IActivoBo;
import com.sigaf.Ibo.IAgropecuarioBo;
import com.sigaf.Ibo.IAreaBo;
import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IClienteBo;
import com.sigaf.Ibo.IClienteProyectoBo;
import com.sigaf.Ibo.IComercioBo;
import com.sigaf.Ibo.ICooperativaBo;
import com.sigaf.Ibo.IDesembolsoBo;
import com.sigaf.Ibo.IEgresoBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Ibo.IEntidadProyectoBo;
import com.sigaf.Ibo.IGarantiaBo;
import com.sigaf.Ibo.IGarantiaProBo;
import com.sigaf.Ibo.IIngresoBo;
import com.sigaf.Ibo.ILisiadoBo;
import com.sigaf.Ibo.IPagoAgronegocioBo;
import com.sigaf.Ibo.IPagoBo;
import com.sigaf.Ibo.IParametroBo;
import com.sigaf.Ibo.IParametroSeguimientoBo;
import com.sigaf.Ibo.IPoliticaBo;
import com.sigaf.Ibo.IPropiedadLisiadoBo;
import com.sigaf.Ibo.IProyectoBo;
import com.sigaf.Ibo.IReferenciaBo;
import com.sigaf.Ibo.ISeguimientoBo;
import com.sigaf.Ibo.ITrabajoBo;
import com.sigaf.pojo.TActivo;
import com.sigaf.pojo.TAgropecuario;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TCliente;
import com.sigaf.pojo.TClienteProyecto;
import com.sigaf.pojo.TComercio;
import com.sigaf.pojo.TCooperativa;
import com.sigaf.pojo.TDesembolso;
import com.sigaf.pojo.TEgreso;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TEntidadProyecto;
import com.sigaf.pojo.TGarantia;
import com.sigaf.pojo.TIngreso;
import com.sigaf.pojo.TLisiado;
import com.sigaf.pojo.TPago;
import com.sigaf.pojo.TParametro;
import com.sigaf.pojo.TParametroseguimiento;
import com.sigaf.pojo.TPolitica;
import com.sigaf.pojo.TPropiedadLisiado;
import com.sigaf.pojo.TProyecto;
import com.sigaf.pojo.TReferencia;
import com.sigaf.pojo.TSeguimiento;
import com.sigaf.pojo.TTipoCredito;
import com.sigaf.pojo.TTrabajo;
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
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Genoves
 */
@Named(value = "proyectoBean")
@SessionScoped
@ManagedBean

public class ProyectoBean extends Actividad {

    ILisiadoBo ilisiadoBo;
    IGarantiaProBo igarantiaProBo;
    IPagoAgronegocioBo ipagoAgronegociobo;
    IParametroSeguimientoBo iparametroSeguimientoBo;
    private Boolean mostrarIncobrabilidadPersona;
    private Boolean mostrarIncobrabilidadCooperativa;
    private BigDecimal totalDesembolsos;
    private Boolean estadoFormulario;
    private Integer tipoCredito;
    private List<TReferencia> listaReferenciaSeleccionada;
    private Integer tipoCred;
    private BigDecimal valorRedondeadoP;
    private Boolean mostrarCierrePersona;
    private Boolean mostrarRefinanciamientoCooperativa;
    private Boolean mostrarRefinanciamientoPersona;
    private String msgCredito;
    private String msgActivoC;
    private String msgActivoN;
    private String msgPasivoC;
    private String msgPasivoN;
    private String msgPatrimonio;
    private String msgMonto;
    private String msgPlazo;
    private Boolean mostrarTabla;
    private Boolean mostrarClienteCodigo;
    private Boolean mostrarCooperativaCodigo;
    private Integer numeroDesembolso;
    private BigDecimal totalCapitalMora;
    private TDesembolso desembolsoSeleccionado;
    private TGarantia garantiaSeleccionada;
    private Boolean showPresupuesto;
    private Boolean showBalance;
    private Boolean showAprobacion;
    private String msgNumero;
    private String msgLugar;
    private String msgFecha;
    private String msgCodigoEntidad;
    private String msgNombreProyecto;
    private String msgDescripcion;
    private String msgDestino;
    private String msgArea;
    private Boolean mostrarResumen;
    private Boolean mostrarGarantiaPrendaria;
    private Boolean mostrarGarantiaSolidaria;
    private Boolean mostrarGarantiaHipotecaria;
    private BigDecimal AbonoExtra;
    private List<TPago> listaPagosComprobacion;
    private Integer numeroPagos;
    private Date fechaInicio;
    private IAreaBo iareaBo;
    private BigDecimal interesFinal;
    private BigDecimal moraFinal;
    private BigDecimal totalFinal;
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
    private List<TPago> listaPagos;
    private Boolean seleecionarExtra;
    private List<TEntidadProyecto> listaEntidadProyectos;
    private List<TEntidadProyecto> listaEntidadProyectosReducida;
    private List<TEntidadProyecto> listaEntidadProyectosFiltrada;
    private String codigoCliente;
    private TPago pagoSeleccionado;
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
    private List<TClienteProyecto> listaClienteProyectoFiltrada;
    private List<TEntidad> listaEntidades;
    private List<TParametro> listaParametros;
    private IAgropecuarioBo iagropecuarioBo;
    private IActivoBo iactivoBo;
    private IClienteProyectoBo iclienteProyectoBo;
    private IDesembolsoBo idesembolsoBo;
    private IComercioBo icomercioBo;
    private TComercio comercio;
    private IEntidadBo ientidadBo;
    private IEntidadProyectoBo ientidadProyectoBo;
    private ICooperativaBo icooperativaBo;
    private IProyectoBo iproyectoBo;
    private ITrabajoBo itrabajoBo;
    private IPoliticaBo ipoliticaBo;
    private IClienteBo iclienteBo;
    private IIngresoBo iingresoBo;
    private IEgresoBo iegresoBo;
    private IGarantiaBo igarantiaBo;
    private IReferenciaBo ireferenciaBo;
    private TTrabajo trabajo;
    private BigDecimal monto;
    private BigDecimal intereses;
    private BigDecimal total;
    private BigDecimal couto;
    private BigDecimal totalIngreso;
    private BigDecimal totalEgreso;
    private BigDecimal totalActivo;
    private BigDecimal totalGarantia;
    private Boolean mostrarTipoCreditoCooper;
    private Boolean mostrarTipoCreditoPerso;
    private Boolean mostrarComercio;
    private BigDecimal totalBalanceActivo;
    private BigDecimal totalBalancePasivo;
    private BigDecimal TotalBalancePatrimonio;
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
    private String formaPagos;
    private Boolean mostrarDesembolsos;
    private Boolean banderaEgreso;
    private boolean showImagen;
    private boolean showImagenCarta;
    private boolean ocultar;
    private boolean ocultarEgresos;
    private Integer numeroSeleccion;
    private Boolean mostrarPagosCooperativa;
    private List<String> listaPosesiones;
    private List<TEgreso> listaEgresos;
    private List<TDesembolso> listaDesembolso;
    private List<TPago> listaPagosMora;
    private TReferencia referencias;
    private TPropiedadLisiado propiedadLisiado;
    private Integer idGarantia;
    private Integer idMunicipio;
    private String posesiones;
    private String nombreTipoCredito;
    private String estadoProyecto;
    private Boolean mostrarDesembolso;
    IPagoBo ipagoBo;
    private List<TPago> listaPago;
    private Date fechaEstipulada;
    private Integer diasRetraso;
    private Integer numeroCuota;
    private BigDecimal moraCuota;
    private BigDecimal interesCouta;
    private BigDecimal abonoCuota;
    private BigDecimal TotalCuota;
    private BigDecimal auxcuota;
    private BigDecimal totalMora;
    private BigDecimal mora;
    private BigDecimal valorRedondeadoCuota;
    private Date fechaAplicacion;
    private TPago pagoMora;
    private Boolean mostrarListaSeguimientoTabla;
    private List<TSeguimiento> listaProyectoSeguimiento;
    private Integer idParametro;
    private List<TSeguimiento> listaSeguimientoGuardar;
    private List<TClienteProyecto> listaClienteProyectoLisiados;
    private List<TClienteProyecto> listaClienteProyectoComercio;
    private List<TClienteProyecto> listaClienteProyectoAgropecuario;
    private List<TEntidadProyecto> listaClienteProyectoCooperativa;
    private TIngreso ingresos;
    private TEgreso egresos;
    private TGarantia garantias;
    private TDesembolso desembolso;
    private String nombreTipo;
    private Boolean mostrarSeguimientoPersona;
    private Boolean mostrarSeguimientoCooperativa;
    private Integer tipoSeguimiento;
    private String formaPago;
    private String modalidad;
    private BigDecimal diferenciaDesembolso;
    private Boolean mostrarCierre;
    private Boolean mostrarRefinanciamiento;
    private TParametroseguimiento parametroSeguimiento;
    private TParametro parametrosLista;
    private Boolean mostrarEmpleados;
    private Integer idDepartamento;
    private TLisiado lisiados;
    private TCliente cliente;
    private Boolean mostrarPagos;
    private Boolean estadoFormularioPago;
    private TSeguimiento seguimientoSeleccionado;
    private Boolean mostrarExtra;
    private List<TProyecto> listaSolicitudesProyectos;
    ISeguimientoBo iseguimientoBo;
    IParametroBo iparametroBo;
    IPropiedadLisiadoBo ipropiedadLisiadoBo;
    private Boolean mostrarPagoTotalPersona;
    private Boolean mostrarPagoTotalCooperativa;
    private TSeguimiento seguimiento;
    private BigDecimal cuotaPago;
    private List<TClienteProyecto> listaCliente;
    private List<TPropiedadLisiado> listaPropiedadLisiado;
    private List<TParametroseguimiento> listaParametrosSeguimiento;
    private Boolean mostrarParametroSeguimiento;
    private String msgFechaSeguimiento;
    private String msgDescripcionSeguimiento;
    private Boolean estadoFormularioSeguimiento;
    private String msgDesembolso;
    private Boolean estadoFormularioPagoTotal;
    private Boolean estadoFormularioDesembolso;
    private Boolean finalizacionDesembolso;
    private String msgValor;
    private String msgParametro;
    private String msgDescripcionParametro;
    private Boolean estadoParametro;
    private List<TParametro> listaParametros2;
    private TSeguimiento seguimientoAux;
    private BigDecimal totalMoraGenerada;
    private Integer tipoClienteDesembolso;
    private Boolean modificarSeguimiento;
    private Boolean modificarParametroIndividual;
    private BigDecimal totalCuotas;
    private BigDecimal totalInteres;
    private BigDecimal totalAbono;
    private BigDecimal totalAmortizado;
    private BigDecimal totalCapital;
    private Boolean agregarParametro;
    private String unidades;
    private TParametro parametroSeleccionado;
    private Boolean modificarParametros;
    private Boolean estadoFormulario2;
    private Integer edad;
    private IBitacoraBo bitacoraBo;
    private Date hoy;

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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Boolean getModificarParametros() {
        return modificarParametros;
    }

    public void setModificarParametros(Boolean modificarParametros) {
        this.modificarParametros = modificarParametros;
    }

    public TParametro getParametroSeleccionado() {
        return parametroSeleccionado;
    }

    public void setParametroSeleccionado(TParametro parametroSeleccionado) {
        this.parametroSeleccionado = parametroSeleccionado;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public Boolean getAgregarParametro() {
        return agregarParametro;
    }

    public void setAgregarParametro(Boolean agregarParametro) {
        this.agregarParametro = agregarParametro;
    }

    public Boolean getEstadoFormulario2() {
        return estadoFormulario2;
    }

    public void setEstadoFormulario2(Boolean estadoFormulario2) {
        this.estadoFormulario2 = estadoFormulario2;
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

    public Boolean getModificarParametroIndividual() {
        return modificarParametroIndividual;
    }

    public void setModificarParametroIndividual(Boolean modificarParametroIndividual) {
        this.modificarParametroIndividual = modificarParametroIndividual;
    }

    public Boolean getModificarSeguimiento() {
        return modificarSeguimiento;
    }

    public void setModificarSeguimiento(Boolean modificarSeguimiento) {
        this.modificarSeguimiento = modificarSeguimiento;
    }

    public Integer getTipoClienteDesembolso() {
        return tipoClienteDesembolso;
    }

    public void setTipoClienteDesembolso(Integer tipoClienteDesembolso) {
        this.tipoClienteDesembolso = tipoClienteDesembolso;
    }

    public BigDecimal getTotalMoraGenerada() {
        return totalMoraGenerada;
    }

    public void setTotalMoraGenerada(BigDecimal totalMoraGenerada) {
        this.totalMoraGenerada = totalMoraGenerada;
    }

    public TSeguimiento getSeguimientoAux() {
        return seguimientoAux;
    }

    public void setSeguimientoAux(TSeguimiento seguimientoAux) {
        this.seguimientoAux = seguimientoAux;
    }

    public List<TParametro> getListaParametros2() {
        return listaParametros2;
    }

    public void setListaParametros2(List<TParametro> listaParametros2) {
        this.listaParametros2 = listaParametros2;
    }

    public String getMsgValor() {
        return msgValor;
    }

    public void setMsgValor(String msgValor) {
        this.msgValor = msgValor;
    }

    public String getMsgParametro() {
        return msgParametro;
    }

    public void setMsgParametro(String msgParametro) {
        this.msgParametro = msgParametro;
    }

    public String getMsgDescripcionParametro() {
        return msgDescripcionParametro;
    }

    public void setMsgDescripcionParametro(String msgDescripcionParametro) {
        this.msgDescripcionParametro = msgDescripcionParametro;
    }

    public Boolean getEstadoParametro() {
        return estadoParametro;
    }

    public void setEstadoParametro(Boolean estadoParametro) {
        this.estadoParametro = estadoParametro;
    }

    public Boolean getFinalizacionDesembolso() {
        return finalizacionDesembolso;
    }

    public void setFinalizacionDesembolso(Boolean finalizacionDesembolso) {
        this.finalizacionDesembolso = finalizacionDesembolso;
    }

    public Boolean getEstadoFormularioPagoTotal() {
        return estadoFormularioPagoTotal;
    }

    public void setEstadoFormularioPagoTotal(Boolean estadoFormularioPagoTotal) {
        this.estadoFormularioPagoTotal = estadoFormularioPagoTotal;
    }

    public Boolean getEstadoFormularioDesembolso() {
        return estadoFormularioDesembolso;
    }

    public void setEstadoFormularioDesembolso(Boolean estadoFormularioDesembolso) {
        this.estadoFormularioDesembolso = estadoFormularioDesembolso;
    }

    public String getMsgDesembolso() {
        return msgDesembolso;
    }

    public void setMsgDesembolso(String msgDesembolso) {
        this.msgDesembolso = msgDesembolso;
    }

    public Boolean getEstadoFormularioSeguimiento() {
        return estadoFormularioSeguimiento;
    }

    public void setEstadoFormularioSeguimiento(Boolean estadoFormularioSeguimiento) {
        this.estadoFormularioSeguimiento = estadoFormularioSeguimiento;
    }

    public String getMsgFechaSeguimiento() {
        return msgFechaSeguimiento;
    }

    public void setMsgFechaSeguimiento(String msgFechaSeguimiento) {
        this.msgFechaSeguimiento = msgFechaSeguimiento;
    }

    public String getMsgDescripcionSeguimiento() {
        return msgDescripcionSeguimiento;
    }

    public void setMsgDescripcionSeguimiento(String msgDescripcionSeguimiento) {
        this.msgDescripcionSeguimiento = msgDescripcionSeguimiento;
    }

    public BigDecimal getTotalDesembolsos() {
        return totalDesembolsos;
    }

    public void setTotalDesembolsos(BigDecimal totalDesembolsos) {
        this.totalDesembolsos = totalDesembolsos;
    }

    public Boolean getMostrarIncobrabilidadPersona() {
        return mostrarIncobrabilidadPersona;
    }

    public void setMostrarIncobrabilidadPersona(Boolean mostrarIncobrabilidadPersona) {
        this.mostrarIncobrabilidadPersona = mostrarIncobrabilidadPersona;
    }

    public Boolean getMostrarIncobrabilidadCooperativa() {
        return mostrarIncobrabilidadCooperativa;
    }

    public void setMostrarIncobrabilidadCooperativa(Boolean mostrarIncobrabilidadCooperativa) {
        this.mostrarIncobrabilidadCooperativa = mostrarIncobrabilidadCooperativa;
    }

    public IParametroSeguimientoBo getIparametroSeguimientoBo() {
        return iparametroSeguimientoBo;
    }

    public void setIparametroSeguimientoBo(IParametroSeguimientoBo iparametroSeguimientoBo) {
        this.iparametroSeguimientoBo = iparametroSeguimientoBo;
    }

    public IPagoAgronegocioBo getIpagoAgronegociobo() {
        return ipagoAgronegociobo;
    }

    public void setIpagoAgronegociobo(IPagoAgronegocioBo ipagoAgronegociobo) {
        this.ipagoAgronegociobo = ipagoAgronegociobo;
    }

    public BigDecimal getCuotaPago() {
        return cuotaPago;
    }

    public void setCuotaPago(BigDecimal cuotaPago) {
        this.cuotaPago = cuotaPago;
    }

    public Boolean getMostrarPagoTotalPersona() {
        return mostrarPagoTotalPersona;
    }

    public void setMostrarPagoTotalPersona(Boolean mostrarPagoTotalPersona) {
        this.mostrarPagoTotalPersona = mostrarPagoTotalPersona;
    }

    public Boolean getMostrarPagoTotalCooperativa() {
        return mostrarPagoTotalCooperativa;
    }

    public void setMostrarPagoTotalCooperativa(Boolean mostrarPagoTotalCooperativa) {
        this.mostrarPagoTotalCooperativa = mostrarPagoTotalCooperativa;
    }

    public BigDecimal getMoraCuota() {
        return moraCuota;
    }

    public void setMoraCuota(BigDecimal moraCuota) {
        this.moraCuota = moraCuota;
    }

    public BigDecimal getDiferenciaDesembolso() {
        return diferenciaDesembolso;
    }

    public void setDiferenciaDesembolso(BigDecimal diferenciaDesembolso) {
        this.diferenciaDesembolso = diferenciaDesembolso;
    }

    public BigDecimal getInteresCouta() {
        return interesCouta;
    }

    public void setInteresCouta(BigDecimal interesCouta) {
        this.interesCouta = interesCouta;
    }

    public BigDecimal getAbonoCuota() {
        return abonoCuota;
    }

    public void setAbonoCuota(BigDecimal abonoCuota) {
        this.abonoCuota = abonoCuota;
    }

    public BigDecimal getTotalCuota() {
        return TotalCuota;
    }

    public void setTotalCuota(BigDecimal TotalCuota) {
        this.TotalCuota = TotalCuota;
    }

    public BigDecimal getAuxcuota() {
        return auxcuota;
    }

    public void setAuxcuota(BigDecimal auxcuota) {
        this.auxcuota = auxcuota;
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

    public List<TParametroseguimiento> getListaParametrosSeguimiento() {
        return listaParametrosSeguimiento;
    }

    public void setListaParametrosSeguimiento(List<TParametroseguimiento> listaParametrosSeguimiento) {
        this.listaParametrosSeguimiento = listaParametrosSeguimiento;
    }

    public List<TProyecto> getListaSolicitudesProyectos() {
        return listaSolicitudesProyectos;
    }

    public void setListaSolicitudesProyectos(List<TProyecto> listaSolicitudesProyectos) {
        this.listaSolicitudesProyectos = listaSolicitudesProyectos;
    }

    public TSeguimiento getSeguimientoSeleccionado() {
        return seguimientoSeleccionado;
    }

    public void setSeguimientoSeleccionado(TSeguimiento seguimientoSeleccionado) {
        this.seguimientoSeleccionado = seguimientoSeleccionado;
    }

    public Boolean getEstadoFormularioPago() {
        return estadoFormularioPago;
    }

    public void setEstadoFormularioPago(Boolean estadoFormularioPago) {
        this.estadoFormularioPago = estadoFormularioPago;
    }

    public Boolean getMostrarPagos() {
        return mostrarPagos;
    }

    public void setMostrarPagos(Boolean mostrarPagos) {
        this.mostrarPagos = mostrarPagos;
    }

    public Boolean getMostrarEmpleados() {
        return mostrarEmpleados;
    }

    public void setMostrarEmpleados(Boolean mostrarEmpleados) {
        this.mostrarEmpleados = mostrarEmpleados;
    }

    public TParametroseguimiento getParametroSeguimiento() {
        return parametroSeguimiento;
    }

    public void setParametroSeguimiento(TParametroseguimiento parametroSeguimiento) {
        this.parametroSeguimiento = parametroSeguimiento;
    }

    public Boolean getMostrarExtra() {
        return mostrarExtra;
    }

    public void setMostrarExtra(Boolean mostrarExtra) {
        this.mostrarExtra = mostrarExtra;
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

    public Boolean getMostrarCierre() {
        return mostrarCierre;
    }

    public void setMostrarCierre(Boolean mostrarCierre) {
        this.mostrarCierre = mostrarCierre;
    }

    public Boolean getMostrarRefinanciamiento() {
        return mostrarRefinanciamiento;
    }

    public void setMostrarRefinanciamiento(Boolean mostrarRefinanciamiento) {
        this.mostrarRefinanciamiento = mostrarRefinanciamiento;
    }

    public Boolean getMostrarParametroSeguimiento() {
        return mostrarParametroSeguimiento;
    }

    public void setMostrarParametroSeguimiento(Boolean mostrarParametroSeguimiento) {
        this.mostrarParametroSeguimiento = mostrarParametroSeguimiento;
    }

    public List<TSeguimiento> getListaProyectoSeguimiento() {
        return listaProyectoSeguimiento;
    }

    public void setListaProyectoSeguimiento(List<TSeguimiento> listaProyectoSeguimiento) {
        this.listaProyectoSeguimiento = listaProyectoSeguimiento;
    }

    public Boolean getMostrarListaSeguimientoTabla() {
        return mostrarListaSeguimientoTabla;
    }

    public void setMostrarListaSeguimientoTabla(Boolean mostrarListaSeguimientoTabla) {
        this.mostrarListaSeguimientoTabla = mostrarListaSeguimientoTabla;
    }

    public List<TSeguimiento> getListaSeguimientoGuardar() {
        return listaSeguimientoGuardar;
    }

    public void setListaSeguimientoGuardar(List<TSeguimiento> listaSeguimientoGuardar) {
        this.listaSeguimientoGuardar = listaSeguimientoGuardar;
    }

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public IParametroBo getIparametroBo() {
        return iparametroBo;
    }

    public void setIparametroBo(IParametroBo iparametroBo) {
        this.iparametroBo = iparametroBo;
    }

    public TSeguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(TSeguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

    public ISeguimientoBo getIseguimientoBo() {
        return iseguimientoBo;
    }

    public void setIseguimientoBo(ISeguimientoBo iseguimientoBo) {
        this.iseguimientoBo = iseguimientoBo;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Integer getTipoSeguimiento() {
        return tipoSeguimiento;
    }

    public void setTipoSeguimiento(Integer tipoSeguimiento) {
        this.tipoSeguimiento = tipoSeguimiento;
    }

    public Boolean getMostrarSeguimientoPersona() {
        return mostrarSeguimientoPersona;
    }

    public void setMostrarSeguimientoPersona(Boolean mostrarSeguimientoPersona) {
        this.mostrarSeguimientoPersona = mostrarSeguimientoPersona;
    }

    public Boolean getMostrarSeguimientoCooperativa() {
        return mostrarSeguimientoCooperativa;
    }

    public void setMostrarSeguimientoCooperativa(Boolean mostrarSeguimientoCooperativa) {
        this.mostrarSeguimientoCooperativa = mostrarSeguimientoCooperativa;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public TDesembolso getDesembolso() {
        return desembolso;
    }

    public void setDesembolso(TDesembolso desembolso) {
        this.desembolso = desembolso;
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

    public Boolean getMostrarDesembolso() {
        return mostrarDesembolso;
    }

    public void setMostrarDesembolso(Boolean mostrarDesembolso) {
        this.mostrarDesembolso = mostrarDesembolso;
    }

    public IPagoBo getIpagoBo() {
        return ipagoBo;
    }

    public void setIpagoBo(IPagoBo ipagoBo) {
        this.ipagoBo = ipagoBo;
    }

    public List<TPago> getListaPago() {
        return listaPago;
    }

    public void setListaPago(List<TPago> listaPago) {
        this.listaPago = listaPago;
    }

    public Date getFechaEstipulada() {
        return fechaEstipulada;
    }

    public void setFechaEstipulada(Date fechaEstipulada) {
        this.fechaEstipulada = fechaEstipulada;
    }

    public Integer getDiasRetraso() {
        return diasRetraso;
    }

    public void setDiasRetraso(Integer diasRetraso) {
        this.diasRetraso = diasRetraso;
    }

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public BigDecimal getValorRedondeadoCuota() {
        return valorRedondeadoCuota;
    }

    public void setValorRedondeadoCuota(BigDecimal valorRedondeadoCuota) {
        this.valorRedondeadoCuota = valorRedondeadoCuota;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getEstadoProyecto() {
        return estadoProyecto;
    }

    public void setEstadoProyecto(String estadoProyecto) {
        this.estadoProyecto = estadoProyecto;
    }

    public String getNombreTipoCredito() {
        return nombreTipoCredito;
    }

    public void setNombreTipoCredito(String nombreTipoCredito) {
        this.nombreTipoCredito = nombreTipoCredito;
    }

    public List<TDesembolso> getListaDesembolso() {
        return listaDesembolso;
    }

    public void setListaDesembolso(List<TDesembolso> listaDesembolso) {
        this.listaDesembolso = listaDesembolso;
    }

    public Boolean getMostrarPagosCooperativa() {
        return mostrarPagosCooperativa;
    }

    public void setMostrarPagosCooperativa(Boolean mostrarPagosCooperativa) {
        this.mostrarPagosCooperativa = mostrarPagosCooperativa;
    }

    public Integer getNumeroSeleccion() {
        return numeroSeleccion;
    }

    public void setNumeroSeleccion(Integer numeroSeleccion) {
        this.numeroSeleccion = numeroSeleccion;
    }

    public Boolean getMostrarDesembolsos() {
        return mostrarDesembolsos;
    }

    public void setMostrarDesembolsos(Boolean mostrarDesembolsos) {
        this.mostrarDesembolsos = mostrarDesembolsos;
    }

    public String getFormaPagos() {
        return formaPagos;
    }

    public void setFormaPagos(String formaPagos) {
        this.formaPagos = formaPagos;
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

    public IDesembolsoBo getIdesembolsoBo() {
        return idesembolsoBo;
    }

    public void setIdesembolsoBo(IDesembolsoBo idesembolsoBo) {
        this.idesembolsoBo = idesembolsoBo;
    }

    public List<TParametro> getListaParametros() {
        return listaParametros;
    }

    public void setListaParametros(List<TParametro> listaParametros) {
        this.listaParametros = listaParametros;
    }

    public TPago getPagoSeleccionado() {
        return pagoSeleccionado;
    }

    public void setPagoSeleccionado(TPago pagoSeleccionado) {
        this.pagoSeleccionado = pagoSeleccionado;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Boolean getSeleecionarExtra() {
        return seleecionarExtra;
    }

    public void setSeleecionarExtra(Boolean seleecionarExtra) {
        this.seleecionarExtra = seleecionarExtra;
    }

    public Integer getNumeroPagos() {
        return numeroPagos;
    }

    public void setNumeroPagos(Integer numeroPagos) {
        this.numeroPagos = numeroPagos;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public BigDecimal getInteresFinal() {
        return interesFinal;
    }

    public IEntidadBo getIentidadBo() {
        return ientidadBo;
    }

    public void setIentidadBo(IEntidadBo ientidadBo) {
        this.ientidadBo = ientidadBo;
    }

    public void setInteresFinal(BigDecimal interesFinal) {
        this.interesFinal = interesFinal;
    }

    public BigDecimal getMoraFinal() {
        return moraFinal;
    }

    public void setMoraFinal(BigDecimal moraFinal) {
        this.moraFinal = moraFinal;
    }

    public BigDecimal getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(BigDecimal totalFinal) {
        this.totalFinal = totalFinal;
    }

    public List<TPago> getListaPagosComprobacion() {
        return listaPagosComprobacion;
    }

    public void setListaPagosComprobacion(List<TPago> listaPagosComprobacion) {
        this.listaPagosComprobacion = listaPagosComprobacion;
    }

    public BigDecimal getAbonoExtra() {
        return AbonoExtra;
    }

    public void setAbonoExtra(BigDecimal AbonoExtra) {
        this.AbonoExtra = AbonoExtra;
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

    public Boolean getMostrarResumen() {
        return mostrarResumen;
    }

    public void setMostrarResumen(Boolean mostrarResumen) {
        this.mostrarResumen = mostrarResumen;
    }

    public TDesembolso getDesembolsoSeleccionado() {
        return desembolsoSeleccionado;
    }

    public void setDesembolsoSeleccionado(TDesembolso desembolsoSeleccionado) {
        this.desembolsoSeleccionado = desembolsoSeleccionado;
    }

    public BigDecimal getTotalCapitalMora() {
        return totalCapitalMora;
    }

    public void setTotalCapitalMora(BigDecimal totalCapitalMora) {
        this.totalCapitalMora = totalCapitalMora;
    }

    public Boolean getMostrarCierrePersona() {
        return mostrarCierrePersona;
    }

    public void setMostrarCierrePersona(Boolean mostrarCierrePersona) {
        this.mostrarCierrePersona = mostrarCierrePersona;
    }

    public Boolean getMostrarRefinanciamientoCooperativa() {
        return mostrarRefinanciamientoCooperativa;
    }

    public void setMostrarRefinanciamientoCooperativa(Boolean mostrarRefinanciamientoCooperativa) {
        this.mostrarRefinanciamientoCooperativa = mostrarRefinanciamientoCooperativa;
    }

    public Boolean getMostrarRefinanciamientoPersona() {
        return mostrarRefinanciamientoPersona;
    }

    public void setMostrarRefinanciamientoPersona(Boolean mostrarRefinanciamientoPersona) {
        this.mostrarRefinanciamientoPersona = mostrarRefinanciamientoPersona;
    }

    public Integer getNumeroDesembolso() {
        return numeroDesembolso;
    }

    public void setNumeroDesembolso(Integer numeroDesembolso) {
        this.numeroDesembolso = numeroDesembolso;
    }

    public Boolean getMostrarCooperativaCodigo() {
        return mostrarCooperativaCodigo;
    }

    public void setMostrarCooperativaCodigo(Boolean mostrarCooperativaCodigo) {
        this.mostrarCooperativaCodigo = mostrarCooperativaCodigo;
    }

    public Boolean getMostrarClienteCodigo() {
        return mostrarClienteCodigo;
    }

    public void setMostrarClienteCodigo(Boolean mostrarClienteCodigo) {
        this.mostrarClienteCodigo = mostrarClienteCodigo;
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

    public TParametro getParametrosLista() {
        return parametrosLista;
    }

    public void setParametrosLista(TParametro parametrosLista) {
        this.parametrosLista = parametrosLista;
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

    public Boolean getMostrarTipoCreditoCooper() {
        return mostrarTipoCreditoCooper;
    }

    public void setMostrarTipoCreditoCooper(Boolean mostrarTipoCreditoCooper) {
        this.mostrarTipoCreditoCooper = mostrarTipoCreditoCooper;
    }

    public Boolean getMostrarTipoCreditoPerso() {
        return mostrarTipoCreditoPerso;
    }

    public void setMostrarTipoCreditoPerso(Boolean mostrarTipoCreditoPerso) {
        this.mostrarTipoCreditoPerso = mostrarTipoCreditoPerso;
    }

    public void updateListaParametros() {

        this.listaParametrosSeguimiento = this.iparametroBo.listParametro();

    }

    public void updateListaResumen() {

        this.listaSolicitudesProyectos = this.iproyectoBo.listTProyecto(3);

    }

    public void Init() {

        this.Entidad = new TEntidad();
        this.Entidadeleccionada = new TEntidad();
        this.AbonoExtra = new BigDecimal("0");
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
        this.pagoSeleccionado = new TPago();
        this.trabajo = new TTrabajo();
        this.trabajo.setTCliente(new TCliente());
        this.clienteSeleccionado = new TCliente();
        this.finalizacionDesembolso = false;
        super.setShowData(true);
        this.tipoCredito = 0;
        this.showPresupuesto = true;
        this.showAprobacion = true;
        this.showBalance = true;
        this.seguimientoSeleccionado = new TSeguimiento();
        this.listaEntidadProyectosReducida = new ArrayList<TEntidadProyecto>();
        this.listaParametros = new ArrayList<TParametro>();
        this.listaClienteProyectoReducida = new ArrayList<TClienteProyecto>();
        this.calculos = false;
        this.parametrosLista = new TParametro();
        this.parametroSeleccionado = new TParametro();
        this.parametroSeleccionado.setTParametroseguimiento(new TParametroseguimiento());
        this.referencia = new TReferencia();
        this.listaReferencia = new ArrayList<TReferencia>();
        this.activo = new TActivo();
        this.listaActivos = new ArrayList<TActivo>();
        this.ingreso = new TIngreso();
        this.listaIngreso = new ArrayList<TIngreso>();
        this.egreso = new TEgreso();
        this.desembolso = new TDesembolso();
        this.desembolso.setTProyecto(new TProyecto());
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
        this.mostrarCierre = false;
        this.mostrarRefinanciamiento = false;
        this.desembolsoSeleccionado = new TDesembolso();
        this.mostrarResumen = true;
        this.garantias = new TGarantia();
        this.propiedadLisiado = new TPropiedadLisiado();
        this.referencias = new TReferencia();
        this.banderaEgreso = false;
        this.unidades = "Unidades";
        this.showImagen = true;
        this.showImagenCarta = true;
        this.ocultar = true;
        this.ocultarEgresos = true;
        this.parametroSeguimiento = new TParametroseguimiento();
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
        this.seguimiento = new TSeguimiento();
        this.listaSeguimientoGuardar = new ArrayList<>();
        this.clienteSeleccionado = new TCliente();

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

    public void validarFormulario() {

        this.estadoFormulario = true;

    }

    public void validarFormularioPago() {

        this.estadoFormularioPago = true;

    }

    public void validarFormularioPagoTotal() {
        this.estadoFormularioPagoTotal = true;

    }

    public void validarFormularioSeguimientoModifiar() {

        this.estadoFormularioSeguimiento = true;
        if (this.seguimientoSeleccionado.getFechaseguimiento() == null) {
            this.msgFechaSeguimiento = "La fecha es requerida";
            this.estadoFormularioSeguimiento = false;
        } else {
            this.msgFechaSeguimiento = "";
        }
        if (this.seguimientoSeleccionado.getDescripcion().length() < 5) {
            this.msgDescripcionSeguimiento = "La descripción en requerida";
            this.estadoFormularioSeguimiento = false;
        } else {
            this.msgDescripcionSeguimiento = "";
        }

    }

    public void validarFormularioSeguimiento() {

        this.estadoFormularioSeguimiento = true;
        if (this.seguimiento.getFechaseguimiento() == null) {
            this.msgFechaSeguimiento = "La fecha es requerida";
            this.estadoFormularioSeguimiento = false;
        } else {
            this.msgFechaSeguimiento = "";
        }
        if (this.seguimiento.getDescripcion().length() < 5) {
            this.msgDescripcionSeguimiento = "La descripción en requerida";
            this.estadoFormularioSeguimiento = false;
        } else {
            this.msgDescripcionSeguimiento = "";
        }

    }

    public void validarFormularioDesembolso() {

        this.estadoFormularioDesembolso = true;
        if (this.desembolso.getMontoDesembolso().doubleValue() == 0 || this.desembolso.getMontoDesembolso().doubleValue() > this.diferenciaDesembolso.doubleValue()) {
            this.msgDesembolso = "El monto del desembolso debe ser mayor a cero y menor o igual a la cantidad restante";
            this.estadoFormularioDesembolso = false;
        } else {
            this.msgDesembolso = "";
        }
        if (this.desembolso.getFechaDesembolso() == null) {
            this.msgFecha = "Debe introducir una fecha";
            this.estadoFormularioDesembolso = false;
        } else {
            this.msgFecha = "";
        }

    }

    public void enableShowDataBean() {

        this.mostrarSeguimientoCooperativa = false;
        this.mostrarSeguimientoPersona = false;
        this.enableShowData();

    }

    public void mostrarEntidades() {

        this.listaEntidades = ientidadBo.listTEndidad();

    }

    public void enableShowImagen() {
        this.showImagen = true;
    }

    public void enableShowImagenCarta() {
        this.showImagenCarta = true;
    }

    public void cargarVista() {

        this.cooperativaSeleccionada = this.icooperativaBo.getTCooperativa(this.proyectoSeleccionado.getIdproyecto());
        this.politicaSeleccionada = this.ipoliticaBo.getPolitica(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        System.out.println(this.cooperativaSeleccionada.getAreaProducida());

    }

    public void cargarDesembolso() {

        cargarPoliticaProyecto();
        this.msgDesembolso = "";
        this.listaDesembolso = this.idesembolsoBo.listDesembolso(this.proyectoSeleccionado.getIdproyecto());
        BigDecimal sumaDesembolso = BigDecimal.ZERO;
        for (int i = 0; i < this.listaDesembolso.size(); i++) {
            sumaDesembolso = sumaDesembolso.add(this.listaDesembolso.get(i).getMontoDesembolso());
        }
        this.diferenciaDesembolso = (this.proyectoSeleccionado.getMonto().subtract(sumaDesembolso));
        if (sumaDesembolso == this.proyectoSeleccionado.getMonto()) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ya se registro el desembolso", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {
            this.listaDesembolso = this.idesembolsoBo.listDesembolso(this.proyectoSeleccionado.getIdproyecto());
            this.numeroDesembolso = this.listaDesembolso.size();
            if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 8) {
                this.nombreTipoCredito = "Capital de trabajo agropecuario";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 9) {
                this.nombreTipoCredito = "Inversión agropecuario";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 7) {
                this.nombreTipoCredito = "Comercio";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 6) {
                this.nombreTipoCredito = "Personal";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 5) {
                this.nombreTipoCredito = "Lisiados de guerra";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 4) {
                this.nombreTipoCredito = "Producción agropecuario";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 3) {
                this.nombreTipoCredito = "Producción cooperativa";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 2) {
                this.nombreTipoCredito = "Inversión cooperativa";
            } else {
                this.nombreTipoCredito = "Empleados";
            }
            DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
            inFormat.setTimeZone(TimeZone.getTimeZone("America/Guatemala"));
            Date purchaseDate = new Date();
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            try {
                this.desembolso.setFechaDesembolso(formatoDelTexto.parse(inFormat.format(purchaseDate)));
            } catch (ParseException ex) {
                Logger.getLogger(ProyectoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.desembolso.setMontoDesembolso(BigDecimal.ZERO);
            this.desembolso.setTProyecto(this.proyectoSeleccionado);
            this.politicaSeleccionada = this.ipoliticaBo.getPolitica(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
            this.mostrarDesembolso = true;
            this.mostrarSeguimientoCooperativa = false;
            this.mostrarSeguimientoPersona = false;
            this.setShowData(false);
        }

    }

    public void cargarDesembolsoPersona() {

        this.msgDesembolso = "";
        this.listaDesembolso = this.idesembolsoBo.listDesembolso(this.proyectoSeleccionado.getIdproyecto());
        cargarPoliticaProyecto();
        BigDecimal sumaDesembolso = BigDecimal.ZERO;
        for (int i = 0; i < this.listaDesembolso.size(); i++) {
            sumaDesembolso = sumaDesembolso.add(this.listaDesembolso.get(i).getMontoDesembolso());
        }
        this.diferenciaDesembolso = (this.proyectoSeleccionado.getMonto().subtract(sumaDesembolso));
        if (sumaDesembolso == this.proyectoSeleccionado.getMonto()) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ya se registro el desembolso", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {
            this.listaDesembolso = this.idesembolsoBo.listDesembolso(this.proyectoSeleccionado.getIdproyecto());
            this.numeroDesembolso = this.listaDesembolso.size();
            if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 8) {
                this.nombreTipoCredito = "Capital de trabajo agropecuario";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 9) {
                this.nombreTipoCredito = "Inversión agropecuario";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 7) {
                this.nombreTipoCredito = "Comercio";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 6) {
                this.nombreTipoCredito = "Personal";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 5) {
                this.nombreTipoCredito = "Lisiados de guerra";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 4) {
                this.nombreTipoCredito = "Producción agropecuario";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 3) {
                this.nombreTipoCredito = "Producción cooperativa";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 2) {
                this.nombreTipoCredito = "Inversión cooperativa";
            } else {
                this.nombreTipoCredito = "Empleados";
            }
            DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
            inFormat.setTimeZone(TimeZone.getTimeZone("America/Guatemala"));
            Date purchaseDate = new Date();
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            try {
                this.desembolso.setFechaDesembolso(formatoDelTexto.parse(inFormat.format(purchaseDate)));
            } catch (ParseException ex) {
                Logger.getLogger(ProyectoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.desembolso.setMontoDesembolso(BigDecimal.ZERO);
            this.desembolso.setTProyecto(this.proyectoSeleccionado);
            this.politicaSeleccionada = this.ipoliticaBo.getPolitica(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
            this.mostrarDesembolso = false;
            this.mostrarSeguimientoCooperativa = false;
            this.mostrarSeguimientoPersona = false;
            this.enableShowCreateComercio();
        }

    }

    public void createDesembolso() throws SQLException, JRException, IOException {

        if (this.finalizacionDesembolso == true) {
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
            Calendar calendar = Calendar.getInstance();
            int sumMeses = 0;
            calendar.setTime(this.proyectoSeleccionado.getFechaAprovacion());
            for (int i = 0; i <= this.proyectoSeleccionado.getPlazo() / meses; i++) {
                calendar.add(Calendar.MONTH, meses);
                sumMeses = sumMeses + meses;
            }
            this.listaDesembolso = this.idesembolsoBo.listDesembolso(this.proyectoSeleccionado.getIdproyecto());
            Double sumaDesembolsos = 0.0;
            for (int i = 0; i < this.listaDesembolso.size(); i++) {
                sumaDesembolsos = sumaDesembolsos + this.listaDesembolso.get(i).getMontoDesembolso().doubleValue();
            }
            this.idesembolsoBo.create(this.desembolso);
            this.proyectoSeleccionado.setEstado(5);
            this.proyectoSeleccionado.setMonto(new BigDecimal(sumaDesembolsos));
            this.proyectoSeleccionado.setFechaVencimientoProyecto(calendar.getTime());
            this.iproyectoBo.update(this.proyectoSeleccionado);

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registros de desembolsos finalizados correctamente", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {
            this.idesembolsoBo.create(this.desembolso);
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
            if (this.desembolso.getMontoDesembolso().doubleValue() == this.diferenciaDesembolso.doubleValue()) {
                Calendar calendar = Calendar.getInstance();
                int sumMeses = 0;
                calendar.setTime(this.desembolso.getFechaDesembolso());
                for (int i = 0; i < this.proyectoSeleccionado.getPlazo() / meses; i++) {
                    calendar.add(Calendar.MONTH, meses);  // numero de días a añadir, o restar en caso de días<0
                    sumMeses = sumMeses + meses;
                }
                this.proyectoSeleccionado.setEstado(5);
                this.proyectoSeleccionado.setFechaVencimientoProyecto(calendar.getTime());
                this.iproyectoBo.update(this.proyectoSeleccionado);
            }
            this.listaDesembolso = this.idesembolsoBo.listDesembolso(this.proyectoSeleccionado.getIdproyecto());
            this.numeroDesembolso = this.listaDesembolso.size();
            Double sumaDesembolsos = 0.0;
            for (int i = 0; i < this.listaDesembolso.size(); i++) {
                sumaDesembolsos = sumaDesembolsos + this.listaDesembolso.get(i).getMontoDesembolso().doubleValue();
            }
            this.estadoFormulario = false;
            this.desembolso.setMontoDesembolso(BigDecimal.ZERO);
            this.diferenciaDesembolso = new BigDecimal(this.proyectoSeleccionado.getMonto().doubleValue() - sumaDesembolsos);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_desembolso");
            auxBitacora.setAccionBitacora("Agregar desembolso");
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
                    + ", Monto del desembolso: " + this.desembolso.getMontoDesembolso()
            );
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.desembolso.getIdDesembolso());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Desembolso creado correctamente", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
        this.enableShowSeguimiento();

    }

    public void cargarVista2() {

        this.politicaSeleccionada = this.ipoliticaBo.getPolitica(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        this.tipoCred = this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito();
        this.listaIngresoSeleccionada = this.iingresoBo.listIngreso(this.proyectoSeleccionado.getIdproyecto());
        for (int i = 0; i < this.listaIngresoSeleccionada.size(); i++) {
            this.totalIngreso = this.totalIngreso.add(this.listaIngresoSeleccionada.get(i).getValor());
        }
        this.listaEgresoSeleccionada = this.iegresoBo.listEgreso(this.proyectoSeleccionado.getIdproyecto());
        for (int i = 0; i < this.listaEgresoSeleccionada.size(); i++) {
            this.totalEgreso = this.totalEgreso.add(this.listaEgresoSeleccionada.get(i).getValor());
        }
        this.listaActivoSeleccionada = this.iactivoBo.listActivo(this.proyectoSeleccionado.getIdproyecto());

        for (int i = 0; i < this.listaActivoSeleccionada.size(); i++) {

            this.totalActivo = this.totalActivo.add(this.listaActivoSeleccionada.get(i).getValor());

        }
        this.listaGarantiaSeleccionada = this.igarantiaBo.listTGarantia(this.proyectoSeleccionado.getIdproyecto());
        this.listaReferenciaSeleccionada = this.ireferenciaBo.listReferencia(this.proyectoSeleccionado.getIdproyecto());
        BigDecimal cal;
        this.calculos = true;
        this.monto = this.proyectoSeleccionado.getMonto();
        this.intereses = (this.monto.multiply(this.politicaSeleccionada.getTasaInteres().divide(new BigDecimal(100)).multiply(new BigDecimal(this.proyectoSeleccionado.getPlazo()).divide(new BigDecimal(12)))));
        this.total = this.monto.add(this.intereses);
        cal = new BigDecimal(Math.exp(new BigDecimal(-this.proyectoSeleccionado.getPlazo()).multiply(new BigDecimal(Math.log((new BigDecimal(1).add((this.politicaSeleccionada.getTasaInteres().divide(new BigDecimal(100).divide(new BigDecimal(12)))))).doubleValue()))).doubleValue()));
        this.couto = ((((this.politicaSeleccionada.getTasaInteres().divide(new BigDecimal(100).divide(new BigDecimal(12).divide((new BigDecimal(1.0).subtract(cal)).multiply(this.proyectoSeleccionado.getMonto()))))))));

    }

    public String cambiar(Integer valor) {

        if (valor == 8) {
            return this.tipo = "Capital de trabajo agropecuario";
        } else if (valor == 9) {
            return this.tipo = "Inversión agropecuario";
        } else if (valor == 7) {
            return this.tipo = "Comercio";
        } else if (valor == 6) {
            return this.tipo = "Personal";
        } else if (valor == 5) {
            return this.tipo = "Lisiados de guerra";
        } else if (valor == 4) {
            return this.tipo = "Producción agropecuario";
        } else if (valor == 3) {
            return this.tipo = "Producción cooperativa";
        } else if (valor == 2) {
            return this.tipo = "Inversión cooperativa";
        } else {
            return this.tipo = "Empleados";
        }

    }

    public String cambiarEstado(Integer valor) {

        if (valor == 3) {
            this.estadoProyecto = "Aprobado/Proceso";
        }
        if (valor == 5) {
            this.estadoProyecto = "Aprobado/Ejecutandose";
        }
        if (valor == 6) {
            this.estadoProyecto = "Finalizado";
        }
        if (valor == 7) {
            this.estadoProyecto = "Crédito incobrable";
        }
        return this.estadoProyecto;

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
            this.listaEntidadProyectosReducida = this.ientidadProyectoBo.listTEndidadProyectosAprovados(2);
        }
        if (this.tipoCredito == 8) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyectoAprovados(8);
        }
        if (this.tipoCredito == 6) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyectoAprovados(6);
        }
        if (this.tipoCredito == 1) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyectoAprovados(7);
        }
        if (this.tipoCredito == 7) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyectoAprovados(1);
        }
        if (this.tipoCredito == 5) {
            this.listaClienteProyectoReducida = this.iclienteProyectoBo.listTClienteProyectoAprovados(5);
        }

    }

    public void mostrarSolicitudes() {

        if (this.tipoCredito == 0) {
            this.mostrarLisiados = false;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarAgropecuarios = false;
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
        if (this.tipoCredito == 6) {
            this.mostrarPersonas = true;
            this.mostrarCooperativas = false;
            this.mostrarResumen = false;
            this.mostrarEmpleados = false;
        }
        if (this.tipoCredito == 1) {
            this.mostrarPersonas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarCooperativas = false;
            this.mostrarComercio = true;
            this.mostrarLisiados = false;
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

    public void mostrarFiltroProyecto() {

        int estaCooperativa = 0;
        int estaPersona = 0;
        this.listaEntidadProyectosReducida.clear();
        this.listaClienteProyectoReducida.clear();
        this.listaEntidadProyectosFiltrada = this.ientidadProyectoBo.listTEndidadProyectosAprovados(2);
        for (int i = 0; i < this.listaEntidadProyectosFiltrada.size(); i++) {
            if (this.listaEntidadProyectosFiltrada.get(i).getTProyecto().getCodigoProyecto().equals(this.codigoCliente)) {
                estaCooperativa = this.listaEntidadProyectosFiltrada.get(i).getTProyecto().getTTipoCredito().getIdTipoCredito();
                this.listaEntidadProyectosReducida.add(this.listaEntidadProyectosFiltrada.get(i));
            }
        }
        this.listaClienteProyectoFiltrada = this.iclienteProyectoBo.listTClienteProyectoAprovados(0);
        for (int i = 0; i < this.listaClienteProyectoFiltrada.size(); i++) {
            if (this.listaClienteProyectoFiltrada.get(i).getTProyecto().getCodigoProyecto().equals(this.codigoCliente)) {
                estaPersona = listaClienteProyectoFiltrada.get(i).getTProyecto().getTTipoCredito().getIdTipoCredito();
                this.listaClienteProyectoReducida.add(listaClienteProyectoFiltrada.get(i));
            }
        }
        if (estaCooperativa == 2 || estaCooperativa == 3) {
            this.mostrarCooperativas = true;
            this.mostrarPersonas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarResumen = false;
        }
        if (estaPersona == 8) {
            this.mostrarAgropecuarios = true;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarResumen = false;
        }
        if (estaPersona == 6) {
            this.mostrarPersonas = true;
            this.mostrarCooperativas = false;
            this.mostrarComercio = false;
            this.mostrarResumen = false;
        }
        if (estaPersona == 1) {
            this.mostrarPersonas = true;
            this.mostrarCooperativas = false;
            this.mostrarComercio = false;
            this.mostrarResumen = false;
        }
        if (estaPersona == 5) {
            this.mostrarLisiados = true;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarComercio = false;
            this.mostrarResumen = false;
        }
        if (estaPersona == 7) {
            this.mostrarLisiados = false;
            this.mostrarPersonas = false;
            this.mostrarCooperativas = false;
            this.mostrarAgropecuarios = false;
            this.mostrarComercio = true;
            this.mostrarResumen = false;
        }
    }

    public void mostrarSeguimientoCooperativa() {

        this.politicaSeleccionada = this.ipoliticaBo.getPolitica(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        this.mostrarSeguimientoCooperativa = true;
        this.mostrarSeguimientoPersona = false;
        this.tipoSeguimiento = 1;
        cambiarFormaPago();
        this.setShowData(false);

    }

    public void cargarPoliticaProyecto() {

        this.politicaSeleccionada = this.ipoliticaBo.getPolitica(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        cambiarFormaPago();

    }

    public void enableShowSeguimiento() {

        this.mostrarPagos = false;
        this.mostrarDesembolso = false;
        this.mostrarPagosCooperativa = false;
        this.mostrarPagoTotalCooperativa = false;
        this.mostrarCierre = false;
        this.mostrarDesembolsos = false;
        this.mostrarCierrePersona = false;
        this.mostrarRefinanciamientoPersona = false;
        this.mostrarRefinanciamientoCooperativa = false;
        this.mostrarPagoTotalPersona = false;
        this.mostrarPagoTotalCooperativa = false;
        this.setShowData(true);
        this.mostrarPagos = false;
        this.mostrarCierre = false;
        this.setShowCreateComercio(false);
        this.mostrarCierrePersona = false;
        this.mostrarRefinanciamientoPersona = false;
        this.mostrarRefinanciamientoCooperativa = false;
        this.mostrarPagoTotalPersona = false;
        this.mostrarPagoTotalCooperativa = false;
        this.mostrarIncobrabilidadPersona = false;
        this.mostrarIncobrabilidadCooperativa = false;

    }

    public void cargarVistaPagos(int tipo) {

        cargarPoliticaProyecto();
        Integer formaPlazoPago;
        DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
        inFormat.setTimeZone(TimeZone.getTimeZone("America/Guatemala"));
        Date purchaseDate = new Date();
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            formaPlazoPago = this.proyectoSeleccionado.getPlazo() / 1;
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            formaPlazoPago = this.proyectoSeleccionado.getPlazo() / 3;
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            formaPlazoPago = this.proyectoSeleccionado.getPlazo() / 6;
        } else {
            formaPlazoPago = this.proyectoSeleccionado.getPlazo() / 12;
        }
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.fechaAplicacion = formatoDelTexto.parse(inFormat.format(purchaseDate));
        } catch (ParseException ex) {
            Logger.getLogger(ProyectoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        this.numeroCuota = this.listaPago.size();
        if (this.numeroCuota == formaPlazoPago) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay pagos para este crédito", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {
            this.numeroCuota = this.listaPago.size() + 1;
            System.out.println(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
            if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 8) {
                this.nombreTipoCredito = "Capital de trabajo";
            } else if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 4) {
                this.nombreTipoCredito = "Produción agropecuario";
            } else {
                this.nombreTipoCredito = "Credito para lisiados";
            }
            generarDistribucion();
            cambiarFormaPago();
            this.setShowData(false);
            if (tipo == 1) {
                this.mostrarPagos = false;
                this.mostrarPagosCooperativa = true;
            } else {
                this.mostrarPagos = true;
                this.mostrarPagosCooperativa = false;

            }
        }

    }

    public void generarDistribucion() {

        BigDecimal interesAnual;
        BigDecimal interesMensual = null;
        this.monto = this.proyectoSeleccionado.getMonto();
        this.politicaSeleccionada = this.ipoliticaBo.getPolitica(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        BigDecimal interesA;
        BigDecimal abonoA = new BigDecimal("0");
        BigDecimal capital = this.proyectoSeleccionado.getMonto();
        BigDecimal capitalA = new BigDecimal("0");
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
            this.calcularInteres();
            this.couto = new BigDecimal(cuota);
            this.total = this.intereses.add(this.monto);
            this.auxcuota = this.couto;
            System.out.println(this.couto);
            int meses = 1;
            TDesembolso desembolsoInicial = this.idesembolsoBo.getDesembolso(this.proyectoSeleccionado.getIdproyecto());
            int sumMeses = meses;
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i <= this.listaPago.size(); i++) {
                calendar.setTime(desembolsoInicial.getFechaDesembolso()); // Configuramos la fecha que se recibe
                calendar.add(Calendar.MONTH, sumMeses);  // numero de días a añadir, o restar en caso de días<0
                sumMeses = sumMeses + meses;
            }
            this.fechaEstipulada = calendar.getTime();
            if (this.listaPago.size() > 0) {
                interesA = this.listaPago.get(this.listaPago.size() - 1).getInteres();
                abonoA = this.listaPago.get(this.listaPago.size() - 1).getAbono();
                capital = this.listaPago.get(this.listaPago.size() - 1).getSaldocapital();
                capitalA = this.listaPago.get(this.listaPago.size() - 1).getCapitalamortizado();
            }
            interesA = (capital.multiply((this.politicaSeleccionada.getTasaInteres().divide(new BigDecimal("100")).multiply(new BigDecimal(this.proyectoSeleccionado.getPlazo())))));
            interesAnual = interesA.divide(new BigDecimal(this.proyectoSeleccionado.getPlazo()), 5, RoundingMode.HALF_UP);
            interesMensual = interesAnual.divide(new BigDecimal("12"), 5, RoundingMode.HALF_UP);
            if (capital.compareTo(this.couto) < 0) {
                BigDecimal diferencia = this.couto.subtract(capital);
                this.abonoCuota = this.couto.subtract(diferencia).add(interesMensual);
            } else {
                this.abonoCuota = this.couto;
            }
            this.pago = new TPago();
            abonoA = this.abonoCuota.subtract(interesMensual);
            capitalA = capitalA.add(abonoA);
            capital = capital.subtract(abonoA);
            this.pago.setCuota(this.abonoCuota);
            this.pago.setInteres(interesMensual);
            this.pago.setAbono(abonoA);
            this.pago.setCapitalamortizado(capitalA);
            this.pago.setSaldocapital(capital);
            this.pago.setFecha(new Date());
            Calendar c = Calendar.getInstance();
            this.diasRetraso = 0;
            if (this.fechaAplicacion.compareTo(this.fechaEstipulada) > 0) {
                this.diasRetraso = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (this.diasRetraso));
                this.mora = new BigDecimal(moraGenerada);
            } else {
                this.diasRetraso = 0;
                this.mora = BigDecimal.ZERO;
            }
            this.pago.setMora(this.mora);
            BigDecimal extra = this.auxcuota.subtract(this.couto);
            this.pago.setSaldoadicional(extra);
            this.totalMora = (this.mora.add(this.pago.getInteres()).add(this.pago.getAbono()));
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
            this.calcularInteres();
            this.abonoCuota = new BigDecimal("0");
            this.couto = new BigDecimal(cuota);
            this.auxcuota = this.couto;
            this.total = this.intereses.add(this.monto);
            System.out.println(this.couto);
            int meses = 3;
            TDesembolso desembolsoInicial = this.idesembolsoBo.getDesembolso(this.proyectoSeleccionado.getIdproyecto());
            int sumMeses = meses;
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i <= this.listaPago.size(); i++) {
                calendar.setTime(desembolsoInicial.getFechaDesembolso()); // Configuramos la fecha que se recibe
                calendar.add(Calendar.MONTH, sumMeses);  // numero de días a añadir, o restar en caso de días<0
                sumMeses = sumMeses + meses;
            }
            this.fechaEstipulada = calendar.getTime();
            if (this.listaPago.size() > 0) {
                interesA = this.listaPago.get(this.listaPago.size() - 1).getInteres();
                abonoA = this.listaPago.get(this.listaPago.size() - 1).getAbono();
                capital = this.listaPago.get(this.listaPago.size() - 1).getSaldocapital();
                capitalA = this.listaPago.get(this.listaPago.size() - 1).getCapitalamortizado();
            }
            this.couto = this.couto.multiply(new BigDecimal("3"));
            this.abonoCuota = this.couto;
            BigDecimal auxCapitalAmortizado = new BigDecimal("0");
            if (capital.compareTo(this.couto) < 0) {
                this.abonoCuota = capital;
                auxCapitalAmortizado = capitalA;
            } else {
                this.abonoCuota = this.couto;
            }
            BigDecimal interesM = new BigDecimal("0");
            BigDecimal abonoM = new BigDecimal("0");
            BigDecimal capitalM = this.proyecto.getMonto();
            BigDecimal capitalAM = new BigDecimal("0");
            for (int j = 0; j < 3; j++) {
                interesA = (capital.multiply((this.politicaSeleccionada.getTasaInteres().divide(new BigDecimal("100")).multiply(new BigDecimal(this.proyectoSeleccionado.getPlazo())))));
                interesAnual = interesA.divide(new BigDecimal(this.proyectoSeleccionado.getPlazo()), 5, RoundingMode.HALF_UP);
                interesMensual = interesAnual.divide(new BigDecimal("12"), 5, RoundingMode.HALF_UP);
                abonoA = this.auxcuota.subtract(interesMensual);
                capitalA = capitalA.add(abonoA);
                capital = capital.subtract(abonoA);
                interesM = interesM.add(interesMensual);
                abonoM = abonoM.add(abonoA);
                capitalM = capitalA;
                capitalAM = capital;
            }
            this.pago = new TPago();
            if (this.abonoCuota.compareTo(this.couto) < 0) {
                this.abonoCuota = this.abonoCuota.add(interesM);
                this.pago.setCuota(this.abonoCuota);
                this.pago.setInteres(interesM);
                this.pago.setAbono(this.abonoCuota.subtract(interesM));
                this.pago.setCapitalamortizado(auxCapitalAmortizado.add(this.pago.getAbono()));
                this.pago.setSaldocapital(this.abonoCuota.subtract(interesM).subtract(this.pago.getAbono()));
                this.pago.setFecha(new Date());
            } else {
                this.pago.setCuota(this.abonoCuota);
                this.pago.setInteres(interesM);
                this.pago.setAbono(abonoM);
                this.pago.setCapitalamortizado(capitalM);
                this.pago.setSaldocapital(capitalAM);
                this.pago.setFecha(new Date());
            }
            Calendar c = Calendar.getInstance();
            this.diasRetraso = 0;
            if (this.fechaAplicacion.compareTo(this.fechaEstipulada) > 0) {
                this.diasRetraso = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (this.diasRetraso));
                this.mora = new BigDecimal(moraGenerada);
            } else {
                this.diasRetraso = 0;
                this.mora = BigDecimal.ZERO;
            }
            this.pago.setMora(this.mora);
            this.pago.setSaldoadicional(new BigDecimal("0"));
            this.totalMora = (this.mora.add(this.pago.getInteres()).add(this.pago.getAbono()));
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
            this.calcularInteres();
            this.abonoCuota = new BigDecimal("0");
            this.couto = new BigDecimal(cuota);
            this.auxcuota = this.couto;
            this.total = this.intereses.add(this.monto);
            System.out.println(this.couto);
            int meses = 6;
            TDesembolso desembolsoInicial = this.idesembolsoBo.getDesembolso(this.proyectoSeleccionado.getIdproyecto());
            int sumMeses = meses;
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i <= this.listaPago.size(); i++) {
                calendar.setTime(desembolsoInicial.getFechaDesembolso()); // Configuramos la fecha que se recibe
                calendar.add(Calendar.MONTH, sumMeses);  // numero de días a añadir, o restar en caso de días<0
                sumMeses = sumMeses + meses;
            }
            this.fechaEstipulada = calendar.getTime();
            if (this.listaPago.size() > 0) {
                interesA = this.listaPago.get(this.listaPago.size() - 1).getInteres();
                abonoA = this.listaPago.get(this.listaPago.size() - 1).getAbono();
                capital = this.listaPago.get(this.listaPago.size() - 1).getSaldocapital();
                capitalA = this.listaPago.get(this.listaPago.size() - 1).getCapitalamortizado();
            }
            this.couto = this.couto.multiply(new BigDecimal("6"));
            this.abonoCuota = this.couto;
            BigDecimal auxCapitalAmortizado = new BigDecimal("0");
            if (capital.compareTo(this.couto) < 0) {
                this.abonoCuota = capital;
                auxCapitalAmortizado = capitalA;
            } else {
                this.abonoCuota = this.couto;
            }
            BigDecimal interesM = new BigDecimal("0");
            BigDecimal abonoM = new BigDecimal("0");
            BigDecimal capitalM = this.proyecto.getMonto();
            BigDecimal capitalAM = new BigDecimal("0");
            for (int j = 0; j < 6; j++) {
                interesA = (capital.multiply((this.politicaSeleccionada.getTasaInteres().divide(new BigDecimal("100")).multiply(new BigDecimal(this.proyectoSeleccionado.getPlazo())))));
                interesAnual = interesA.divide(new BigDecimal(this.proyectoSeleccionado.getPlazo()), 5, RoundingMode.HALF_UP);
                interesMensual = interesAnual.divide(new BigDecimal("12"), 5, RoundingMode.HALF_UP);
                abonoA = this.auxcuota.subtract(interesMensual);
                capitalA = capitalA.add(abonoA);
                capital = capital.subtract(abonoA);
                interesM = interesM.add(interesMensual);
                abonoM = abonoM.add(abonoA);
                capitalM = capitalA;
                capitalAM = capital;
            }
            this.pago = new TPago();
            if (this.abonoCuota.compareTo(this.couto) < 0) {
                this.abonoCuota = this.abonoCuota.add(interesM);
                this.pago.setCuota(this.abonoCuota);
                this.pago.setInteres(interesM);
                this.pago.setAbono(this.abonoCuota.subtract(interesM));
                this.pago.setCapitalamortizado(auxCapitalAmortizado.add(this.pago.getAbono()));
                this.pago.setSaldocapital(this.abonoCuota.subtract(interesM).subtract(this.pago.getAbono()));
                this.pago.setFecha(new Date());
            } else {
                this.pago.setCuota(this.abonoCuota);
                this.pago.setInteres(interesM);
                this.pago.setAbono(abonoM);
                this.pago.setCapitalamortizado(capitalM);
                this.pago.setSaldocapital(capitalAM);
                this.pago.setFecha(new Date());
            }
            Calendar c = Calendar.getInstance();
            this.diasRetraso = 0;
            if (this.fechaAplicacion.compareTo(this.fechaEstipulada) > 0) {
                this.diasRetraso = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (this.diasRetraso));
                this.mora = new BigDecimal(moraGenerada);
            } else {
                this.diasRetraso = 0;
                this.mora = BigDecimal.ZERO;
            }
            this.pago.setMora(this.mora);
            this.pago.setSaldoadicional(new BigDecimal("0"));
            this.totalMora = (this.mora.add(this.pago.getInteres()).add(this.pago.getAbono()));
        } else {
            Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
            this.calcularInteres();
            this.abonoCuota = new BigDecimal("0");
            this.couto = new BigDecimal(cuota);
            this.auxcuota = this.couto;
            this.total = this.intereses.add(this.monto);
            System.out.println(this.couto);
            int meses = 12;
            TDesembolso desembolsoInicial = this.idesembolsoBo.getDesembolso(this.proyectoSeleccionado.getIdproyecto());
            int sumMeses = meses;
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i <= this.listaPago.size(); i++) {
                calendar.setTime(desembolsoInicial.getFechaDesembolso()); // Configuramos la fecha que se recibe
                calendar.add(Calendar.MONTH, sumMeses);  // numero de días a añadir, o restar en caso de días<0
                sumMeses = sumMeses + meses;
            }
            this.fechaEstipulada = calendar.getTime();
            if (this.listaPago.size() > 0) {
                interesA = this.listaPago.get(this.listaPago.size() - 1).getInteres();
                abonoA = this.listaPago.get(this.listaPago.size() - 1).getAbono();
                capital = this.listaPago.get(this.listaPago.size() - 1).getSaldocapital();
                capitalA = this.listaPago.get(this.listaPago.size() - 1).getCapitalamortizado();
            }
            this.couto = this.couto.multiply(new BigDecimal("12"));
            this.abonoCuota = this.couto;
            BigDecimal auxCapitalAmortizado = new BigDecimal("0");
            if (capital.compareTo(this.couto) < 0) {
                this.abonoCuota = capital;
                auxCapitalAmortizado = capitalA;
            } else {
                this.abonoCuota = this.couto;
            }
            BigDecimal interesM = new BigDecimal("0");
            BigDecimal abonoM = new BigDecimal("0");
            BigDecimal capitalM = this.proyecto.getMonto();
            BigDecimal capitalAM = new BigDecimal("0");
            for (int j = 0; j < 12; j++) {
                interesA = (capital.multiply((this.politicaSeleccionada.getTasaInteres().divide(new BigDecimal("100")).multiply(new BigDecimal(this.proyectoSeleccionado.getPlazo())))));
                interesAnual = interesA.divide(new BigDecimal(this.proyectoSeleccionado.getPlazo()), 5, RoundingMode.HALF_UP);
                interesMensual = interesAnual.divide(new BigDecimal("12"), 5, RoundingMode.HALF_UP);
                abonoA = this.auxcuota.subtract(interesMensual);
                capitalA = capitalA.add(abonoA);
                capital = capital.subtract(abonoA);
                interesM = interesM.add(interesMensual);
                abonoM = abonoM.add(abonoA);
                capitalM = capitalA;
                capitalAM = capital;
            }
            this.pago = new TPago();
            if (this.abonoCuota.compareTo(this.couto) < 0) {
                this.abonoCuota = this.abonoCuota.add(interesM);
                this.pago.setCuota(this.abonoCuota);
                this.pago.setInteres(interesM);
                this.pago.setAbono(this.abonoCuota.subtract(interesM));
                this.pago.setCapitalamortizado(auxCapitalAmortizado.add(this.pago.getAbono()));
                this.pago.setSaldocapital(this.abonoCuota.subtract(interesM).subtract(this.pago.getAbono()));
                this.pago.setFecha(new Date());
            } else {
                this.pago.setCuota(this.abonoCuota);
                this.pago.setInteres(interesM);
                this.pago.setAbono(abonoM);
                this.pago.setCapitalamortizado(capitalM);
                this.pago.setSaldocapital(capitalAM);
                this.pago.setFecha(new Date());
            }
            Calendar c = Calendar.getInstance();
            this.diasRetraso = 0;
            if (this.fechaAplicacion.compareTo(this.fechaEstipulada) > 0) {
                this.diasRetraso = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (this.diasRetraso));
                this.mora = new BigDecimal(moraGenerada);
            } else {
                this.diasRetraso = 0;
                this.mora = BigDecimal.ZERO;
            }
            this.pago.setMora(this.mora);
            this.pago.setSaldoadicional(new BigDecimal("0"));
            this.totalMora = (this.mora.add(this.pago.getInteres()).add(this.pago.getAbono()));
        }

    }

    public void createPago() {

        Integer d;
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            d = 1;
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            d = 3;
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            d = 6;
        } else {
            d = 12;
        }
        this.abonoCuota = this.abonoCuota.add(this.AbonoExtra);
        this.pago.setTProyecto(this.proyectoSeleccionado);
        BigDecimal diferencia = abonoCuota.subtract(this.couto);
        System.out.println(diferencia);
        System.out.println(this.couto.setScale(2, RoundingMode.UP));
        if (this.abonoCuota.compareTo(this.couto.setScale(2, RoundingMode.UP)) > 0) {
            this.pago.setSaldoadicional(diferencia);
            BigDecimal abonoCapital = this.pago.getAbono();
            abonoCapital = abonoCapital.add(diferencia);
            BigDecimal saldoCapital = this.pago.getSaldocapital();
            saldoCapital = saldoCapital.subtract(diferencia);
            BigDecimal saldoAmortizado = this.pago.getCapitalamortizado();
            saldoAmortizado = saldoAmortizado.add(diferencia);
            this.pago.setCapitalamortizado(saldoAmortizado);
            this.pago.setAbono(abonoCapital);
            this.pago.setSaldocapital(saldoCapital);
            this.pago.setCuota(this.abonoCuota);
        } else if (this.abonoCuota.compareTo(this.couto.setScale(2, RoundingMode.UP)) < 0) {
            this.pago.setCuota(this.abonoCuota);
        } else {
            this.pago.setCuota(this.couto);
        }
        Integer numero = this.ipagoBo.getCorrelativo();
        Integer numero2 = this.ipagoAgronegociobo.getCorrelativo();
        if (numero > numero2) {
            numero++;
            this.pago.setReferencia(numero);
        } else {
            numero2++;
            this.pago.setReferencia(numero2);
        }
        this.pago.setDestino("Pago de cuota");
        this.ipagoBo.create(pago);
        this.estadoFormulario = false;
        this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        if (this.listaPago.size() == this.proyectoSeleccionado.getPlazo() / d || this.pago.getCuota().compareTo(this.couto) < 0) {
            this.proyectoSeleccionado.setEstado(6);
            this.listaPagosComprobacion = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
            Integer presenta = 0;
            for (int j = 0; j < this.listaPagosComprobacion.size(); j++) {
                if (this.listaPagosComprobacion.get(j).getMora().doubleValue() > 0) {
                    presenta++;
                }
            }
            if (presenta == 0) {
                this.proyectoSeleccionado.setCalificacion("Excelente");
            }
            if (presenta > 0 && presenta < 3) {
                this.proyectoSeleccionado.setCalificacion("Muy bueno");
            }
            if (presenta > 3) {
                this.proyectoSeleccionado.setCalificacion("Regular");
            }
            this.proyectoSeleccionado.setFechaFinalizacionProyecto(this.pago.getFecha());
            this.iproyectoBo.update(this.proyectoSeleccionado);
            this.estadoFormulario = false;
        }
        this.diasRetraso = 0;
        this.AbonoExtra = new BigDecimal("0");
        this.seleecionarExtra = false;
        this.estadoFormularioPago = false;
        this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        this.enableShowSeguimiento();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_pago");
        auxBitacora.setAccionBitacora("Agregar pago");
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
                + ", Destino: " + this.pago.getDestino()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.pago.getIdpago());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pago realizado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

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
        this.listaActivoSeleccionada = this.iactivoBo.listActivo(this.proyectoSeleccionado.getIdproyecto());
        this.totalActivo = BigDecimal.ZERO;
        for (int i = 0; i < this.listaActivoSeleccionada.size(); i++) {
            this.totalActivo = this.totalActivo.add(this.listaActivoSeleccionada.get(i).getValor());
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
        for (int i = 0; i < this.listaIngresoSeleccionada.size(); i++) {
            this.totalIngreso = this.totalIngreso.add(this.listaIngresoSeleccionada.get(i).getValor());
        }
        this.totalEgreso = BigDecimal.ZERO;
        this.listaEgresoSeleccionada = this.iegresoBo.listEgreso(this.proyectoSeleccionado.getIdproyecto());
        for (int i = 0; i < this.listaEgresoSeleccionada.size(); i++) {
            this.totalEgreso = this.totalEgreso.add(this.listaEgresoSeleccionada.get(i).getValor());
        }
        this.totalActivo = BigDecimal.ZERO;
        this.listaActivoSeleccionada = this.iactivoBo.listActivo(this.proyectoSeleccionado.getIdproyecto());
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
        this.totalEgreso = BigDecimal.ZERO;
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

    public void verReporteSolicitudComercio() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("cuota", Double.parseDouble("" + this.couto));
        parametros.put("interes", Double.parseDouble("" + this.intereses));
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoComercio.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoComercio.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoComercioH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoComercioS.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de crédito para comercio");
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
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoComercio.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoComercio.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoComercioH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoComercioS.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Crédito para comercio.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de crédito para comercio");
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
        parametros.put("cuota", Double.parseDouble("" + this.couto));
        parametros.put("Interes", Double.parseDouble("" + this.intereses));
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoAgropecuario.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoAgropecuario.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoAgropecuarioH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoAgropecuarioS.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de crédito agropecuario");
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
        parametros.put("cuota", Double.parseDouble("" + this.couto));
        parametros.put("Interes", Double.parseDouble("" + this.intereses));
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoAgropecuario.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoAgropecuario.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoAgropecuarioH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoAgropecuarioS.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Crédito agropecuario.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de crédito agropecuario");
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
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoCooperativa.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoCooperativa.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoCooperativaH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoCooperativaS.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de crédito para cooperativa");
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
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoCooperativa.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoCooperativa.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoCooperativaH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoCooperativaS.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Crédito para cooperativa.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de crédito para cooperativa");
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
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoLisiado.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoLisiado.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoLisiadoH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoLisiadoS.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de crédito para lisiado");
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
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoLisiado.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoLisiado.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoLisiadoH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoLisiadoS.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Crédito para lisiado.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar reporte de crédito para lisiado");
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
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoEmpleado.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoEmpleado.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoEmpleadoH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoEmpleadoS.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de crédito para empleado");
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
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoEmpleado.jasper"));
        if (this.garantiaSeleccionada.getValorPrendariaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoEmpleado.jasper"));
        } else if (this.garantiaSeleccionada.getValorHipotecaGarantia() != null) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoEmpleadoH.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditoEmpleadoS.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Crédito para empleado.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de crédito para empleado");
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

    public void verConstanciaFinalizacionPersona() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/constanciaCancelacion.jasper"));
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
        auxBitacora.setAccionBitacora("Generar constancia de finalización");
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

    public void verConstanciaFinalizacionCooperativa() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/constanciaCancelacionCooperativa.jasper"));
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
        auxBitacora.setAccionBitacora("Generar constancia de finalización");
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

    public void verConstanciaFinalizacionPersonaPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/constanciaCancelacion.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Constancia finalizacion (Persona natural).pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar constancia de finalización");
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

    public void verConstanciaFinalizacionCooperativaPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/constanciaCancelacionCooperativa.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Constancia finalizacion (Cooperativa).pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar constancia de finalización");
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

    public void verResultadosPersona() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("fechaInicio", this.fechaInicio);
        parametros.put("numeroPagos", this.numeroPagos);
        parametros.put("cuota", this.couto.doubleValue());
        this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        BigDecimal sumaInteres = new BigDecimal("0");
        BigDecimal sumaMora = new BigDecimal("0");
        BigDecimal sumaAdicional = new BigDecimal("0");
        for (int x = 0; x < this.listaPago.size(); x++) {
            sumaInteres = sumaInteres.add(this.listaPago.get(x).getInteres());
            sumaMora = sumaMora.add(this.listaPago.get(x).getMora());
            sumaAdicional = sumaAdicional.add(this.listaPago.get(x).getSaldoadicional());
        }
        parametros.put("adicional", sumaAdicional.doubleValue());
        parametros.put("mora", sumaMora.doubleValue());
        parametros.put("interes", sumaInteres.doubleValue());
        parametros.put("total", sumaMora.add(sumaInteres).add(this.proyectoSeleccionado.getMonto()).doubleValue());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/resultadosPersona.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de resultados");
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

    public void verResultadosCooperativa() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("fechaInicio", this.fechaInicio);
        parametros.put("numeroPagos", this.numeroPagos);
        parametros.put("cuota", this.couto.doubleValue());
        this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        BigDecimal sumaInteres = new BigDecimal("0");
        BigDecimal sumaMora = new BigDecimal("0");
        BigDecimal sumaAdicional = new BigDecimal("0");
        for (int x = 0; x < this.listaPago.size(); x++) {
            sumaInteres = sumaInteres.add(this.listaPago.get(x).getInteres());
            sumaMora = sumaMora.add(this.listaPago.get(x).getMora());
            sumaAdicional = sumaAdicional.add(this.listaPago.get(x).getSaldoadicional());
        }
        parametros.put("adicional", sumaAdicional.doubleValue());
        parametros.put("mora", sumaMora.doubleValue());
        parametros.put("interes", sumaInteres.doubleValue());
        parametros.put("total", sumaMora.add(sumaInteres).add(this.proyectoSeleccionado.getMonto()).doubleValue());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/resultadosCooperativa.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de resultados");
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

    public void verResultadosPersonaPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("fechaInicio", this.fechaInicio);
        parametros.put("numeroPagos", this.numeroPagos);
        parametros.put("cuota", this.couto.doubleValue());
        this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        BigDecimal sumaInteres = new BigDecimal("0");
        BigDecimal sumaMora = new BigDecimal("0");
        BigDecimal sumaAdicional = new BigDecimal("0");
        for (int x = 0; x < this.listaPago.size(); x++) {
            sumaInteres = sumaInteres.add(this.listaPago.get(x).getInteres());
            sumaMora = sumaMora.add(this.listaPago.get(x).getMora());
            sumaAdicional = sumaAdicional.add(this.listaPago.get(x).getSaldoadicional());
        }
        parametros.put("adicional", sumaAdicional.doubleValue());
        parametros.put("mora", sumaMora.doubleValue());
        parametros.put("interes", sumaInteres.doubleValue());
        parametros.put("total", sumaMora.add(sumaInteres).add(this.proyectoSeleccionado.getMonto()).doubleValue());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/resultadosPersona.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Resultados de credito (Persona natural).pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar reporte de resultados");
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

    public void verResultadosCooperativaPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("fechaInicio", this.fechaInicio);
        parametros.put("numeroPagos", this.numeroPagos);
        parametros.put("cuota", this.couto.doubleValue());
        this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        BigDecimal sumaInteres = new BigDecimal("0");
        BigDecimal sumaMora = new BigDecimal("0");
        BigDecimal sumaAdicional = new BigDecimal("0");
        for (int x = 0; x < this.listaPago.size(); x++) {
            sumaInteres = sumaInteres.add(this.listaPago.get(x).getInteres());
            sumaMora = sumaMora.add(this.listaPago.get(x).getMora());
            sumaAdicional = sumaAdicional.add(this.listaPago.get(x).getSaldoadicional());
        }
        parametros.put("adicional", sumaAdicional.doubleValue());
        parametros.put("mora", sumaMora.doubleValue());
        parametros.put("interes", sumaInteres.doubleValue());
        parametros.put("total", sumaMora.add(sumaInteres).add(this.proyectoSeleccionado.getMonto()).doubleValue());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/resultadosCooperativa.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Resultados del credito (Cooperativas).pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar reporte de resultados");
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

    public void mostrarSeguimiento() {

        this.mostrarParametroSeguimiento = true;
        this.mostrarListaSeguimientoTabla = false;

    }

    public void cargarSeguimiento() {

        this.parametrosLista.setTParametroseguimiento(new TParametroseguimiento(this.idParametro));
        this.listaParametros.add(this.parametrosLista);
        this.parametrosLista = new TParametro();
        this.setShowData(false);

    }

    public void cargarModal() {

        this.estadoFormulario = true;

    }

    public void eliminarSeguimiento(int i) {

        this.listaParametros.remove(i);

    }

    public void validarParamentros() {

        this.estadoParametro = true;
        if (this.parametrosLista.getValor() == null) {
            this.msgValor = "El valor del parámetro es requerido";
            this.estadoParametro = false;
        } else {
            this.msgValor = "";
        }
        if (this.idParametro == 0) {
            this.msgParametro = "Debe seleccionar un parámetro";
            this.estadoParametro = false;
        } else {
            this.msgParametro = "";
        }
        if (this.parametrosLista.getDescripcion().length() < 5) {
            this.msgDescripcionParametro = "La descripción es requerida";
            this.estadoParametro = false;
        } else {
            this.msgDescripcionParametro = "";
        }

    }

    public void cargarParametros() {

        this.listaParametros2 = this.iparametroBo.listParametro2(this.seguimientoSeleccionado.getIdseguimiento());
        this.setShowData(false);

    }

    public void crearSeguimiento() {

        this.seguimiento.setTProyecto(this.proyectoSeleccionado);
        this.seguimiento.setEstado(1);
        this.iseguimientoBo.create(this.seguimiento);
        this.estadoFormulario = false;

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_seguimiento");
        auxBitacora.setAccionBitacora("Agregar seguimiento");
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
                + ", Descripción: " + this.seguimiento.getDescripcion()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.seguimiento.getIdseguimiento());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Seguimiento guardado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaProyectoSeguimiento = this.iseguimientoBo.listaProyectoSeguimiento(this.proyectoSeleccionado.getIdproyecto());

    }

    public void crearParametro() {

        this.parametrosLista.setTParametroseguimiento(new TParametroseguimiento(this.idParametro));
        this.parametrosLista.setTSeguimiento(this.seguimientoSeleccionado);
        this.parametrosLista.setEstado(true);
        this.iparametroBo.create(this.parametrosLista);
        this.estadoFormulario = false;
        this.parametrosLista = new TParametro();
        cargarParametros();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_parametro");
        auxBitacora.setAccionBitacora("Agregar parametro");

        auxBitacora.setDatosBitacora("Valor:" + this.parametrosLista.getValor()
                + ", Descripción: " + this.parametrosLista.getDescripcion()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.parametrosLista.getIdParametro());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Parámetro guardado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    public void modificarSeguimiento() {

        this.iseguimientoBo.update(this.seguimientoSeleccionado);
        this.modificarSeguimiento = false;
        this.listaProyectoSeguimiento = this.iseguimientoBo.listaProyectoSeguimiento(this.proyectoSeleccionado.getIdproyecto());

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_seguimiento");
        auxBitacora.setAccionBitacora("Modificar seguimiento");

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Descripción: " + this.seguimientoSeleccionado.getDescripcion()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.seguimientoSeleccionado.getIdseguimiento());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Seguimiento modificado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    public void bajaSeguimiento() {

        this.seguimientoSeleccionado.setEstado(0);
        this.iseguimientoBo.update(this.seguimientoSeleccionado);
        this.listaProyectoSeguimiento = this.iseguimientoBo.listaProyectoSeguimiento(this.proyectoSeleccionado.getIdproyecto());

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_seguimiento");
        auxBitacora.setAccionBitacora("Dar de baja seguimiento");

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Descripción: " + this.seguimientoSeleccionado.getDescripcion()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.seguimientoSeleccionado.getIdseguimiento());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Seguimiento dado de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    public void altaSeguimiento() {

        this.seguimientoSeleccionado.setEstado(1);
        this.iseguimientoBo.update(this.seguimientoSeleccionado);
        this.listaProyectoSeguimiento = this.iseguimientoBo.listaProyectoSeguimiento(this.proyectoSeleccionado.getIdproyecto());

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_seguimiento");
        auxBitacora.setAccionBitacora("Dar de alta seguimiento");

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Descripción: " + this.seguimientoSeleccionado.getDescripcion()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.seguimientoSeleccionado.getIdseguimiento());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Seguimiento dado de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    public void mostrarListaSeguimiento() {

        this.listaProyectoSeguimiento = this.iseguimientoBo.listaProyectoSeguimiento(this.proyectoSeleccionado.getIdproyecto());
        this.mostrarListaSeguimientoTabla = true;
        this.mostrarSeguimientoCooperativa = false;
        this.mostrarParametroSeguimiento = false;
        this.modificarSeguimiento = false;
        this.msgFechaSeguimiento = "";
        this.msgDescripcionSeguimiento = "";
        this.setShowData(false);

    }

    public String nombreParametro(int id) {

        return "hola";

    }

    public void cargarCierreCooperativa() {

        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        Double sumaMora = 0.0;
        Double sumaInteres = 0.0;
        for (int i = 0; i < this.listaPago.size(); i++) {
            sumaMora = sumaMora + this.listaPago.get(i).getMora().doubleValue();
            sumaInteres = sumaInteres + this.listaPago.get(i).getInteres().doubleValue();
        }
        this.totalMora = new BigDecimal(sumaMora);
        this.numeroPagos = this.listaPago.size();
        TDesembolso desembolso = this.idesembolsoBo.getDesembolso(this.proyectoSeleccionado.getIdproyecto());
        this.fechaInicio = desembolso.getFechaDesembolso();
        this.monto = this.proyectoSeleccionado.getMonto();
        Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
        Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            this.couto = new BigDecimal(cuota * 1);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            this.couto = new BigDecimal(cuota * 3);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            this.couto = new BigDecimal(cuota * 6);
        } else {
            this.couto = new BigDecimal(cuota * 12);
        }
        this.intereses = new BigDecimal(sumaInteres);
        this.total = this.intereses.add(this.monto);
        this.totalFinal = this.total.add(this.totalMora);
        cambiarFormaPago();
        this.tipo = this.cambiar(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        this.mostrarCierre = true;
        this.mostrarCierrePersona = false;
        this.mostrarSeguimientoCooperativa = false;
        this.mostrarSeguimientoPersona = false;
        this.setShowData(false);

    }

    public void cargarCierrePersona() {

        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        Double sumaMora = 0.0;
        Double sumaInteres = 0.0;
        for (int i = 0; i < this.listaPago.size(); i++) {
            sumaMora = sumaMora + this.listaPago.get(i).getMora().doubleValue();
            sumaInteres = sumaInteres + this.listaPago.get(i).getInteres().doubleValue();
        }
        this.totalMora = new BigDecimal(sumaMora);
        this.numeroPagos = this.listaPago.size();
        TDesembolso desembolso = this.idesembolsoBo.getDesembolso(this.proyectoSeleccionado.getIdproyecto());
        this.fechaInicio = desembolso.getFechaDesembolso();
        this.monto = this.proyectoSeleccionado.getMonto();
        Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
        Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            this.couto = new BigDecimal(cuota * 1);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            this.couto = new BigDecimal(cuota * 3);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            this.couto = new BigDecimal(cuota * 6);
        } else {
            this.couto = new BigDecimal(cuota * 12);
        }
        this.intereses = new BigDecimal(sumaInteres);
        this.total = this.intereses.add(this.monto);
        this.totalFinal = this.total.add(this.totalMora);
        cambiarFormaPago();
        this.tipo = this.cambiar(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        this.mostrarCierre = false;
        this.mostrarCierrePersona = true;
        this.mostrarSeguimientoCooperativa = false;
        this.mostrarSeguimientoPersona = false;
        this.setShowData(false);

    }

    public void cargarRefinanciamientoCooperativa() {

        this.totalCuotas = new BigDecimal("0");
        this.totalInteres = new BigDecimal("0");
        this.totalAbono = new BigDecimal("0");
        this.totalAmortizado = new BigDecimal("0");
        this.totalCapital = new BigDecimal("0");

        this.pago = new TPago();
        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        this.listaPagos = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        this.NumeroTotalPagos = this.listaPagos.size();
        this.numeroPagos = this.listaPagos.size();
        cambiarFormaPago();
        for (int i = 0; i < this.listaPagos.size(); i++) {
            this.totalCuotas = this.totalCuotas.add(this.listaPagos.get(i).getCuota());
            this.totalInteres = this.totalInteres.add(this.listaPagos.get(i).getInteres());
            this.totalAbono = this.totalAbono.add(this.listaPagos.get(i).getAbono());
            this.totalAmortizado = this.totalAmortizado.add(this.listaPagos.get(i).getCapitalamortizado());
            this.totalCapital = this.totalCapital.add(this.listaPagos.get(i).getSaldocapital());
        }
        this.totalGeneral= this.totalAbono.add(this.totalInteres);
        this.tipo = this.cambiar(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        this.mostrarCierre = false;
        this.mostrarCierrePersona = false;
        this.mostrarRefinanciamientoCooperativa = true;
        this.mostrarRefinanciamientoPersona = false;
        this.mostrarSeguimientoCooperativa = false;
        this.mostrarSeguimientoPersona = false;
        this.setShowData(false);

    }

    public void cargarRefinanciamientoPersona() {

        this.totalCuotas = new BigDecimal("0");
        this.totalInteres = new BigDecimal("0");
        this.totalAbono = new BigDecimal("0");
        this.totalAmortizado = new BigDecimal("0");
        this.totalCapital = new BigDecimal("0");

        this.pago = new TPago();
        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());

        this.listaPagos = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        this.numeroPagos = this.listaPagos.size();
        this.NumeroTotalPagos = this.listaPagos.size();

        for (int i = 0; i < this.listaPagos.size(); i++) {
            this.totalCuotas = this.totalCuotas.add(this.listaPagos.get(i).getCuota());
            this.totalInteres = this.totalInteres.add(this.listaPagos.get(i).getInteres());
            this.totalAbono = this.totalAbono.add(this.listaPagos.get(i).getAbono());
            this.totalAmortizado = this.totalAmortizado.add(this.listaPagos.get(i).getCapitalamortizado());
            this.totalCapital = this.totalCapital.add(this.listaPagos.get(i).getSaldocapital());
        }
           this.totalGeneral= this.totalAbono.add(this.totalInteres);
        
        cambiarFormaPago();
        this.tipo = this.cambiar(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        this.mostrarCierre = false;
        this.mostrarCierrePersona = false;
        this.mostrarRefinanciamientoPersona = true;
        this.mostrarRefinanciamientoCooperativa = false;
        this.mostrarSeguimientoCooperativa = false;
        this.mostrarSeguimientoPersona = false;
        this.setShowData(false);

    }

    public void createRefinanciamiento() {

        TPago pagoRefinanciamiento = new TPago();
        pagoRefinanciamiento.setCuota((this.pago.getSaldocapital().add(this.mora)));
        pagoRefinanciamiento.setInteres(new BigDecimal("0"));
        pagoRefinanciamiento.setAbono(new BigDecimal("0"));
        pagoRefinanciamiento.setCapitalamortizado(this.pago.getSaldocapital());
        pagoRefinanciamiento.setSaldocapital(new BigDecimal("0"));
        pagoRefinanciamiento.setSaldoadicional(new BigDecimal("0"));
        pagoRefinanciamiento.setMora(this.mora);
        this.proyectoSeleccionado.setEstado(6);
        this.proyectoSeleccionado.setFechaFinalizacionProyecto(this.pago.getFecha());
        this.proyectoSeleccionado.setEstadoProyecto("Refinanciado");
        this.proyectoSeleccionado.setCalificacion("Muy bueno");
        this.iproyectoBo.update(this.proyectoSeleccionado);
        pagoRefinanciamiento.setFecha(new Date());
        pagoRefinanciamiento.setTProyecto(this.proyectoSeleccionado);
        Integer numero = this.ipagoBo.getCorrelativo();
        Integer numero2 = this.ipagoAgronegociobo.getCorrelativo();
        if (numero > numero2) {
            numero++;
            this.pago.setReferencia(numero);
        } else {
            numero2++;
            this.pago.setReferencia(numero2);
        }
        this.pago.setDestino("Refinanciamiento");
        this.ipagoBo.create(pagoRefinanciamiento);
        this.estadoFormulario = false;
        this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Refinaciamiento registrado correctamente "));

    }

    public void createPagoTotal() {

        TPago pagoTotal = new TPago();
        pagoTotal.setCuota((this.pago.getSaldocapital().add(this.mora)));
        pagoTotal.setInteres(new BigDecimal("0"));
        pagoTotal.setAbono(new BigDecimal("0"));
        pagoTotal.setCapitalamortizado(this.pago.getSaldocapital());
        pagoTotal.setSaldocapital(new BigDecimal("0"));
        pagoTotal.setSaldoadicional(new BigDecimal("0"));
        pagoTotal.setMora(this.mora);
        pagoTotal.setFecha(new Date());
        this.proyectoSeleccionado.setEstado(6);
        this.proyectoSeleccionado.setFechaFinalizacionProyecto(pagoTotal.getFecha());
        this.proyectoSeleccionado.setCalificacion("Muy bueno");
        this.iproyectoBo.update(this.proyectoSeleccionado);
        pagoTotal.setTProyecto(this.proyectoSeleccionado);
        Integer numero = this.ipagoBo.getCorrelativo();
        Integer numero2 = this.ipagoAgronegociobo.getCorrelativo();
        if (numero > numero2) {
            numero++;
            pagoTotal.setReferencia(numero);
        } else {
            numero2++;
            pagoTotal.setReferencia(numero2);
        }
        pagoTotal.setDestino("Liquidacion");
        this.ipagoBo.create(pagoTotal);
        this.estadoFormulario = false;
        this.enableShowSeguimiento();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_pago");
        auxBitacora.setAccionBitacora("Agregar pago total");

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Destino: " + pagoTotal.getDestino()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(pagoTotal.getIdpago());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Liquidacion guardada correctamente "));

    }

    public void generarProyectoSeguimiento() throws Exception {

        this.getConexion();
        Map<String, Object> parametro = new HashMap();
        parametro.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/ReporteProyectoSeguimiento.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametro, this.getConn());
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
        auxBitacora.setAccionBitacora("Generar reporte de seguimiento");
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

    public void generarProyectoSeguimientoPDF() throws Exception {

        this.getConexion();
        Map<String, Object> parametro = new HashMap();
        parametro.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/ReporteProyectoSeguimiento.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Seguimientos.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de seguimiento");
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

    public void generarSeguimiento() throws Exception {

        this.getConexion();
        Map<String, Object> parametro = new HashMap();
        parametro.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametro.put("id_seguimiento", this.seguimientoSeleccionado.getIdseguimiento());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/ReporteSeguimiento.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametro, this.getConn());
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
        auxBitacora.setAccionBitacora("Descargar reporte de parámetros");
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

    public void generarSeguimientoPDF() throws Exception {

        this.getConexion();
        Map<String, Object> parametro = new HashMap();
        parametro.put("id_proyecto", this.proyectoSeleccionado.getIdproyecto());
        parametro.put("id_seguimiento", this.seguimientoSeleccionado.getIdseguimiento());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/ReporteSeguimiento.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Parametros.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de parametros");
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

    public void generarDesembolsoPersona() throws Exception {

        Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
        Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            this.couto = new BigDecimal(cuota * 1);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            this.couto = new BigDecimal(cuota * 3);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            this.couto = new BigDecimal(cuota * 6);
        } else {
            this.couto = new BigDecimal(cuota * 12);
        }
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("cuota", this.couto.doubleValue());
        parametros.put("desembolso", this.desembolsoSeleccionado.getIdDesembolso());
        parametros.put("fechaPago", this.desembolsoSeleccionado.getFechaDesembolso());
        parametros.put("fechaVencimiento", this.proyectoSeleccionado.getFechaVencimientoProyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/desembolsoCooperativaIndividual.jasper"));
        if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 2 || this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 3) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/desembolsoCooperativaIndividual.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/desembolsoPersonaIndividual.jasper"));
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
        auxBitacora.setTableBitacora("t_desembolso");
        auxBitacora.setAccionBitacora("Generar reporte de desembolso");

        auxBitacora.setDatosBitacora("Crédito:" + this.proyectoSeleccionado.getCodigoProyecto()
                + ", Monto del desembolso: " + this.desembolsoSeleccionado.getMontoDesembolso()
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.desembolsoSeleccionado.getIdDesembolso());
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

    public void cargarPagoTotalPersona() {

        this.totalMoraGenerada = new BigDecimal("0");
        this.pago = new TPago();
        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        this.numeroPagos = this.listaPago.size();
        this.monto = this.proyectoSeleccionado.getMonto();
        Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
        this.calcularInteres();
        Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            this.couto = new BigDecimal(cuota * 1);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            this.couto = new BigDecimal(cuota * 3);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            this.couto = new BigDecimal(cuota * 6);
        } else {
            this.couto = new BigDecimal(cuota * 12);
        }
        this.total = this.intereses.add(this.monto);
        cambiarFormaPago();
        this.tipo = this.cambiar(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        this.pago = this.ipagoBo.getTPago(this.proyectoSeleccionado.getIdproyecto());
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
        System.out.println(this.fechaAplicacion);
        System.out.println(this.fechaEstipulada);
        int mesesAux = 0;
        int numeroCuotasRetraso = 0;
        this.listaPagosMora = new ArrayList<TPago>();
        this.pagoMora = new TPago();
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            mesesAux = 1;
            while (this.fechaAplicacion.compareTo(calendar.getTime()) >= 0) {
                int dia = 0;
                dia = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (dia));
                numeroCuotasRetraso++;
                this.pagoMora.setFecha(this.fechaEstipulada);
                this.pagoMora.setIdpago(dia);
                this.pagoMora.setCuota(this.couto);
                this.pagoMora.setAbono(new BigDecimal(moraGenerada));
                this.listaPagosMora.add(this.pagoMora);
                calendar.add(Calendar.MONTH, mesesAux);  // numero de días a añadir, o restar en caso de días<0
                this.fechaEstipulada = calendar.getTime();
            }
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            mesesAux = 3;
            while (this.fechaAplicacion.compareTo(calendar.getTime()) >= 0) {
                int dia = 0;
                dia = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (dia));
                numeroCuotasRetraso++;
                this.pagoMora.setFecha(this.fechaEstipulada);
                this.pagoMora.setIdpago(dia);
                this.pagoMora.setCuota(this.couto);
                this.pagoMora.setAbono(new BigDecimal(moraGenerada));
                this.listaPagosMora.add(this.pagoMora);
                calendar.add(Calendar.MONTH, mesesAux);  // numero de días a añadir, o restar en caso de días<0
                this.fechaEstipulada = calendar.getTime();
            }
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            mesesAux = 6;
            while (this.fechaAplicacion.compareTo(calendar.getTime()) >= 0) {
                int dia = 0;
                dia = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (dia));
                numeroCuotasRetraso++;
                this.pagoMora.setFecha(this.fechaEstipulada);
                this.pagoMora.setIdpago(dia);
                this.pagoMora.setCuota(this.couto);
                this.pagoMora.setAbono(new BigDecimal(moraGenerada));
                this.listaPagosMora.add(this.pagoMora);
                calendar.add(Calendar.MONTH, mesesAux);  // numero de días a añadir, o restar en caso de días<0
                this.fechaEstipulada = calendar.getTime();
            }
        } else {
            mesesAux = 12;
            while (this.fechaAplicacion.compareTo(calendar.getTime()) >= 0) {
                int dia = 0;
                dia = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (dia));
                numeroCuotasRetraso++;
                this.pagoMora.setFecha(this.fechaEstipulada);
                this.pagoMora.setIdpago(dia);
                this.pagoMora.setCuota(this.couto);
                this.pagoMora.setAbono(new BigDecimal(moraGenerada));
                this.listaPagosMora.add(this.pagoMora);
                calendar.add(Calendar.MONTH, mesesAux);  // numero de días a añadir, o restar en caso de días<0
                this.fechaEstipulada = calendar.getTime();
            }
        }
        //this.totalCapitalMora = this.mora.add(this.pago.getSaldocapital());
        if (this.listaPagosMora.size() == 0) {
            this.mora = new BigDecimal("0");
            this.totalCapitalMora = this.pago.getSaldocapital();
        } else {
            this.mora = new BigDecimal("0");
            for (int i = 0; i < this.listaPagosMora.size(); i++) {
                this.mora = this.mora.add(this.listaPagosMora.get(i).getAbono());
            }
            this.totalCapitalMora = this.pago.getSaldocapital().add(this.mora);
        }
        for (int i = 0; i < this.listaPagosMora.size(); i++) {
            this.totalMoraGenerada = this.totalMoraGenerada.add(this.listaPagosMora.get(i).getInteres());
        }
        this.mostrarCierre = false;
        this.mostrarCierrePersona = false;
        this.mostrarRefinanciamientoPersona = false;
        this.mostrarRefinanciamientoCooperativa = false;
        this.mostrarSeguimientoCooperativa = false;
        this.mostrarSeguimientoPersona = false;
        this.mostrarPagoTotalPersona = true;
        this.setShowData(false);
    }

    public void cargarPagoTotalCooperativa() {

        this.totalMoraGenerada = new BigDecimal("0");
        this.pago = new TPago();
        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        this.numeroPagos = this.listaPago.size();
        this.monto = this.proyectoSeleccionado.getMonto();
        Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
        this.calcularInteres();
        Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            this.couto = new BigDecimal(cuota * 1);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            this.couto = new BigDecimal(cuota * 3);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            this.couto = new BigDecimal(cuota * 6);
        } else {
            this.couto = new BigDecimal(cuota * 12);
        }
        this.total = this.intereses.add(this.monto);
        cambiarFormaPago();
        this.tipo = this.cambiar(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        this.pago = this.ipagoBo.getTPago(this.proyectoSeleccionado.getIdproyecto());
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
        this.pago.setFecha(this.fechaAplicacion);
        System.out.println(this.fechaAplicacion);
        System.out.println(this.fechaEstipulada);
        int mesesAux = 0;
        int numeroCuotasRetraso = 0;
        this.listaPagosMora = new ArrayList<TPago>();
        this.pagoMora = new TPago();
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            mesesAux = 1;
            while (this.fechaAplicacion.compareTo(calendar.getTime()) >= 0) {
                int dia = 0;
                dia = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (dia));
                numeroCuotasRetraso++;
                this.pagoMora.setFecha(this.fechaEstipulada);
                this.pagoMora.setIdpago(dia);
                this.pagoMora.setCuota(this.couto);
                this.pagoMora.setAbono(new BigDecimal(moraGenerada));
                this.listaPagosMora.add(this.pagoMora);
                calendar.add(Calendar.MONTH, mesesAux);  // numero de días a añadir, o restar en caso de días<0
                this.fechaEstipulada = calendar.getTime();
            }
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            mesesAux = 3;
            while (this.fechaAplicacion.compareTo(calendar.getTime()) >= 0) {
                int dia = 0;
                dia = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (dia));
                numeroCuotasRetraso++;
                this.pagoMora.setFecha(this.fechaEstipulada);
                this.pagoMora.setIdpago(dia);
                this.pagoMora.setCuota(this.couto);
                this.pagoMora.setAbono(new BigDecimal(moraGenerada));
                this.listaPagosMora.add(this.pagoMora);
                calendar.add(Calendar.MONTH, mesesAux);  // numero de días a añadir, o restar en caso de días<0
                this.fechaEstipulada = calendar.getTime();
            }
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            mesesAux = 6;
            while (this.fechaAplicacion.compareTo(calendar.getTime()) >= 0) {
                int dia = 0;
                dia = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (dia));
                numeroCuotasRetraso++;
                this.pagoMora.setFecha(this.fechaEstipulada);
                this.pagoMora.setIdpago(dia);
                this.pagoMora.setCuota(this.couto);
                this.pagoMora.setAbono(new BigDecimal(moraGenerada));
                this.listaPagosMora.add(this.pagoMora);
                calendar.add(Calendar.MONTH, mesesAux);  // numero de días a añadir, o restar en caso de días<0
                this.fechaEstipulada = calendar.getTime();
            }
        } else {
            mesesAux = 12;
            while (this.fechaAplicacion.compareTo(calendar.getTime()) >= 0) {
                int dia = 0;
                dia = (int) ((this.fechaAplicacion.getTime() - this.fechaEstipulada.getTime()) / 86400000);
                Double moraGenerada;
                moraGenerada = ((this.couto.doubleValue()) * ((this.politicaSeleccionada.getTasaInteresMora().doubleValue() / 100) / 30) * (dia));
                numeroCuotasRetraso++;
                this.pagoMora.setFecha(this.fechaEstipulada);
                this.pagoMora.setIdpago(dia);
                this.pagoMora.setCuota(this.couto);
                this.pagoMora.setAbono(new BigDecimal(moraGenerada));
                this.listaPagosMora.add(this.pagoMora);
                calendar.add(Calendar.MONTH, mesesAux);  // numero de días a añadir, o restar en caso de días<0
                this.fechaEstipulada = calendar.getTime();
            }
        }
        if (this.listaPagosMora.size() == 0) {
            this.mora = new BigDecimal("0");
            this.totalCapitalMora = this.pago.getSaldocapital();
        } else {
            this.mora = new BigDecimal("0");
            for (int i = 0; i < this.listaPagosMora.size(); i++) {
                this.mora = this.mora.add(this.listaPagosMora.get(i).getAbono());
            }
            this.totalCapitalMora = this.pago.getSaldocapital().add(this.mora);
        }
        for (int i = 0; i < this.listaPagosMora.size(); i++) {
            this.totalMoraGenerada = this.totalMoraGenerada.add(this.listaPagosMora.get(i).getInteres());
        }
        this.mostrarCierre = false;
        this.mostrarCierrePersona = false;
        this.mostrarRefinanciamientoPersona = false;
        this.mostrarRefinanciamientoCooperativa = false;
        this.mostrarSeguimientoCooperativa = false;
        this.mostrarSeguimientoPersona = false;
        this.mostrarPagoTotalCooperativa = true;
        this.setShowData(false);

    }
    
    private Integer numeroTotalDesembolsos;

    public Integer getNumeroTotalDesembolsos() {
        return numeroTotalDesembolsos;
    }
    public void setNumeroTotalDesembolsos(Integer numeroTotalDesembolsos) {
        this.numeroTotalDesembolsos = numeroTotalDesembolsos;
    }

    public void cargarDesembolsos(int x) {

        this.tipoClienteDesembolso = x;
        if (x == 1) {
            this.Entidadeleccionada = new TEntidad();
        } else {
            this.clienteSeleccionado = new TCliente();
        }
        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        cambiarFormaPago();
        this.tipo = this.cambiar(this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito());
        this.listaDesembolso = this.idesembolsoBo.listDesembolso(this.proyectoSeleccionado.getIdproyecto());
        this.numeroTotalDesembolsos = this.listaDesembolso.size();
        BigDecimal sumaDesembolso = BigDecimal.ZERO;
        for (int i = 0; i < this.listaDesembolso.size(); i++) {
            sumaDesembolso = sumaDesembolso.add(this.listaDesembolso.get(i).getMontoDesembolso());
        }
        this.totalDesembolsos = sumaDesembolso;
        this.mostrarDesembolsos = true;
        this.setShowData(false);

    }

    public void generarLiquidacion() throws Exception {

        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        this.desembolsoSeleccionado = this.idesembolsoBo.getDesembolso(this.proyectoSeleccionado.getIdproyecto());
        Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
        Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            this.couto = new BigDecimal(cuota * 1);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            this.couto = new BigDecimal(cuota * 3);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            this.couto = new BigDecimal(cuota * 6);
        } else {
            this.couto = new BigDecimal(cuota * 12);
        }
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("cuota", this.couto.doubleValue());
        parametros.put("id_credito", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("fechaPago", this.desembolsoSeleccionado.getFechaDesembolso());
        parametros.put("fechaVencimiento", this.proyectoSeleccionado.getFechaVencimientoProyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/liquidacionPersona.jasper"));
        if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 2 || this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 3) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/liquidacionCooperativa.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/liquidacionPersona.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de desembolso total");
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

    public void generarLiquidacionPDF() throws Exception {

        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        this.desembolsoSeleccionado = this.idesembolsoBo.getDesembolso(this.proyectoSeleccionado.getIdproyecto());
        Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
        Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            this.couto = new BigDecimal(cuota * 1);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            this.couto = new BigDecimal(cuota * 3);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            this.couto = new BigDecimal(cuota * 6);
        } else {
            this.couto = new BigDecimal(cuota * 12);
        }
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("cuota", this.couto.doubleValue());
        parametros.put("id_credito", this.proyectoSeleccionado.getIdproyecto());
        parametros.put("fechaPago", this.desembolsoSeleccionado.getFechaDesembolso());
        parametros.put("fechaVencimiento", this.proyectoSeleccionado.getFechaVencimientoProyecto());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/liquidacionPersona.jasper"));
        if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 2 || this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 3) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/liquidacionCooperativa.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/liquidacionPersona.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Desembolso total.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de desembolso total");
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

    public void generarRecibo() throws Exception {

        Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
        Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            this.couto = new BigDecimal(cuota * 1);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            this.couto = new BigDecimal(cuota * 3);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            this.couto = new BigDecimal(cuota * 6);
        } else {
            this.couto = new BigDecimal(cuota * 12);
        }
        this.listaPagos = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        BigDecimal saldoAnterior;
        if (this.listaPagos.size() == 1) {
            saldoAnterior = this.proyectoSeleccionado.getMonto();
        } else if (this.numeroSeleccion == 1) {
            saldoAnterior = this.proyectoSeleccionado.getMonto();
        } else {
            saldoAnterior = this.listaPagos.get(this.numeroSeleccion - 2).getSaldocapital();
        }
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_pago", this.pagoSeleccionado.getIdpago());
        parametros.put("saldo_anterior", saldoAnterior);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/reciboCooperativa.jasper"));
        if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 2 || this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 3) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/reciboCooperativa.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/reciboPersona.jasper"));
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
        auxBitacora.setAccionBitacora("Generar recibo de pago");
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

    public void generarReciboPDF() throws Exception {

        Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
        Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            this.couto = new BigDecimal(cuota * 1);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            this.couto = new BigDecimal(cuota * 3);
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            this.couto = new BigDecimal(cuota * 6);
        } else {
            this.couto = new BigDecimal(cuota * 12);
        }
        this.listaPagos = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        BigDecimal saldoAnterior;
        if (this.listaPagos.size() == 1) {
            saldoAnterior = this.proyectoSeleccionado.getMonto();
        } else if (this.numeroSeleccion == 1) {
            saldoAnterior = this.proyectoSeleccionado.getMonto();
        } else {
            saldoAnterior = this.listaPagos.get(this.numeroSeleccion - 2).getSaldocapital();
        }
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_pago", this.pagoSeleccionado.getIdpago());
        parametros.put("saldo_anterior", saldoAnterior);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/reciboCooperativa.jasper"));
        if (this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 2 || this.proyectoSeleccionado.getTTipoCredito().getIdTipoCredito() == 3) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/reciboCooperativa.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/reciboPersona.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Recibo de pago.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar recibo de pago");
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

    public void habilitarExtra() {

        if (this.seleecionarExtra == true) {
            this.mostrarExtra = true;
        } else {
            this.mostrarExtra = false;

        }

    }

    public void cargarModificarSeguimiento() {

        this.mostrarListaSeguimientoTabla = false;
        this.modificarSeguimiento = true;

    }

    public void cargarModificarParametro() {

        this.modificarParametros = true;
        this.modificarSeguimiento = false;
        this.mostrarListaSeguimientoTabla = false;

    }

    public void limpiarParametro() {

        this.msgParametro = "";
        this.msgValor = "";
        this.msgDescripcionParametro = "";

    }

    public void cargarUnidad() {

        if (this.idParametro == 0) {
            this.unidades = "Unidades";
        }
        TParametroseguimiento parametroCargado = new TParametroseguimiento();
        parametroCargado = this.iparametroSeguimientoBo.getParametroSeguimientoUnidad(this.idParametro);
        this.unidades = parametroCargado.getUnidad();

    }

    public void cargarParametroIndividual() {

        limpiarParametro();
        this.modificarParametroIndividual = true;
        this.modificarParametros = false;
        this.idParametro = this.parametroSeleccionado.getTParametroseguimiento().getIdparametro();
        this.unidades = this.parametroSeleccionado.getTParametroseguimiento().getUnidad();

    }

    public void validarParametros2() {

        this.estadoParametro = true;
        if (this.idParametro == 0) {
            this.msgParametro = "Debe seleccionar un parámetro";
            this.estadoParametro = false;
        } else {
            this.msgParametro = "";
        }
        if (this.parametroSeleccionado.getValor() == null) {
            this.msgValor = "El valor del parámetro es requerido";
            this.estadoParametro = false;
        } else {
            this.msgValor = "";
        }
        if (this.parametroSeleccionado.getDescripcion().length() < 5) {
            this.msgDescripcionParametro = "La descripción es requerida";
            this.estadoParametro = false;
        } else {
            this.msgDescripcionParametro = "";
        }

    }

    public void modificarParametroIndividual() {

        this.estadoFormulario2 = false;
        this.parametroSeleccionado.setTParametroseguimiento(new TParametroseguimiento(this.idParametro));
        this.iparametroBo.update(this.parametroSeleccionado);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Parametro modificado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.modificarParametroIndividual = false;
        this.modificarParametros = true;

    }

    public void bajaParametro() {

        this.parametroSeleccionado.setEstado(false);
        this.iparametroBo.update(this.parametroSeleccionado);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Parametro eliminado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    public void altaParametro() {

        this.parametroSeleccionado.setEstado(true);
        this.iparametroBo.update(this.parametroSeleccionado);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Parametro eliminado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    public void mostrarCalculos() {

        cambiarFormaPago();
        BigDecimal interesAnual;
        BigDecimal interesMensual;
        totalCuotas = new BigDecimal("0");
        totalInteres = new BigDecimal("0");
        totalAbono = new BigDecimal("0");
        totalAmortizado = new BigDecimal("0");
        totalCapital = new BigDecimal("0");
        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        TDesembolso desembolsoInicial = this.idesembolsoBo.getDesembolso(this.proyectoSeleccionado.getIdproyecto());
        if (this.proyectoSeleccionado.getFormaPagoProyecto() == 1) {
            this.couto = BigDecimal.ZERO;
            this.intereses = BigDecimal.ZERO;
            this.total = BigDecimal.ZERO;
            int sumMeses = 1;
            this.calculos = true;
            this.monto = this.proyectoSeleccionado.getMonto();
            Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
            this.couto = new BigDecimal(cuota);
            this.cuotaPago = this.couto;
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
                    capital = new BigDecimal("0");
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(desembolsoInicial.getFechaDesembolso()); // Configuramos la fecha que se recibe
                calendar.add(Calendar.MONTH, sumMeses);  // numero de días a añadir, o restar en caso de días<0
                sumMeses = sumMeses + 1;
                this.pago.setCuota(this.couto);
                this.pago.setFecha(calendar.getTime());
                this.pago.setInteres(interesMensual);
                this.pago.setAbono(abonoA);
                this.pago.setCapitalamortizado(capitalA);
                this.pago.setSaldocapital(capital);
                this.listaPagos.add(this.pago);
            }
            this.total = this.intereses.add(this.monto);
            this.mostrarTabla = true;
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 2) {
            this.couto = BigDecimal.ZERO;
            this.intereses = BigDecimal.ZERO;
            this.total = BigDecimal.ZERO;
            int sumMeses = 3;
            this.calculos = true;
            this.monto = this.proyectoSeleccionado.getMonto();
            Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
            this.couto = new BigDecimal(cuota);
            this.cuotaPago = this.couto;
            BigDecimal interesA;
            BigDecimal abonoA = new BigDecimal("0");
            BigDecimal capital = this.proyectoSeleccionado.getMonto();
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
            BigDecimal capitalM = this.proyectoSeleccionado.getMonto();
            BigDecimal capitalAM = new BigDecimal("0");
            int x = 3;
            for (int i = 1; i <= this.proyectoSeleccionado.getPlazo(); i++) {
                this.pago = new TPago();
                interesA = (capital.multiply((this.politicaSeleccionada.getTasaInteres().divide(new BigDecimal("100")).multiply(new BigDecimal(this.proyectoSeleccionado.getPlazo())))));
                interesAnual2 = interesA.divide(new BigDecimal(this.proyectoSeleccionado.getPlazo()), 5, RoundingMode.HALF_UP);
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
                    if (i == (this.proyectoSeleccionado.getPlazo() - 1)) {
                        capitalAM = new BigDecimal("0");
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(desembolsoInicial.getFechaDesembolso()); // Configuramos la fecha que se recibe
                    calendar.add(Calendar.MONTH, sumMeses);  // numero de días a añadir, o restar en caso de días<0
                    sumMeses = sumMeses + 3;
                    this.pago = new TPago();
                    this.pago.setFecha(calendar.getTime());
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
        } else if (this.proyectoSeleccionado.getFormaPagoProyecto() == 3) {
            this.couto = BigDecimal.ZERO;
            this.intereses = BigDecimal.ZERO;
            this.total = BigDecimal.ZERO;
            int sumMeses = 6;
            this.calculos = true;
            this.monto = this.proyectoSeleccionado.getMonto();
            Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
            this.couto = new BigDecimal(cuota);
            this.cuotaPago = this.couto;
            BigDecimal interesA;
            BigDecimal abonoA = new BigDecimal("0");
            BigDecimal capital = this.proyectoSeleccionado.getMonto();
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
            BigDecimal capitalM = this.proyectoSeleccionado.getMonto();
            BigDecimal capitalAM = new BigDecimal("0");
            int x = 6;
            for (int i = 1; i <= this.proyectoSeleccionado.getPlazo(); i++) {
                this.pago = new TPago();
                interesA = (capital.multiply((this.politicaSeleccionada.getTasaInteres().divide(new BigDecimal("100")).multiply(new BigDecimal(this.proyectoSeleccionado.getPlazo())))));
                interesAnual2 = interesA.divide(new BigDecimal(this.proyectoSeleccionado.getPlazo()), 5, RoundingMode.HALF_UP);
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
                    if (i == (this.proyectoSeleccionado.getPlazo() - 1)) {
                        capitalAM = new BigDecimal("0");
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(desembolsoInicial.getFechaDesembolso()); // Configuramos la fecha que se recibe
                    calendar.add(Calendar.MONTH, sumMeses);  // numero de días a añadir, o restar en caso de días<0
                    sumMeses = sumMeses + 6;
                    this.pago = new TPago();
                    this.pago.setFecha(calendar.getTime());
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
            int sumMeses = 12;
            this.calculos = true;
            this.monto = this.proyectoSeleccionado.getMonto();
            Double aux = Math.exp(-this.proyectoSeleccionado.getPlazo() * Math.log((1 + (this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0)));
            Double cuota = ((((this.politicaSeleccionada.getTasaInteres().doubleValue() / 100.0) / 12.0) / (1.0 - aux)) * this.proyectoSeleccionado.getMonto().doubleValue());
            this.couto = new BigDecimal(cuota);
            this.cuotaPago = this.couto;
            BigDecimal interesA;
            BigDecimal abonoA = new BigDecimal("0");
            BigDecimal capital = this.proyectoSeleccionado.getMonto();
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
            BigDecimal capitalM = this.proyectoSeleccionado.getMonto();
            BigDecimal capitalAM = new BigDecimal("0");
            int x = 12;
            for (int i = 1; i <= this.proyectoSeleccionado.getPlazo(); i++) {
                this.pago = new TPago();
                interesA = (capital.multiply((this.politicaSeleccionada.getTasaInteres().divide(new BigDecimal("100")).multiply(new BigDecimal(this.proyectoSeleccionado.getPlazo())))));
                interesAnual2 = interesA.divide(new BigDecimal(this.proyectoSeleccionado.getPlazo()), 5, RoundingMode.HALF_UP);
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
                    if (i == (this.proyectoSeleccionado.getPlazo() - 1)) {
                        capitalAM = new BigDecimal("0");
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(desembolsoInicial.getFechaDesembolso()); // Configuramos la fecha que se recibe
                    calendar.add(Calendar.MONTH, sumMeses);  // numero de días a añadir, o restar en caso de días<0
                    sumMeses = sumMeses + 12;
                    this.pago.setFecha(calendar.getTime());
                    this.pago = new TPago();
                    this.pago.setFecha(calendar.getTime());
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

        }
        for (int i = 0; i < this.listaPagos.size(); i++) {
            this.totalCuotas = this.totalCuotas.add(this.listaPagos.get(i).getCuota());
            this.totalInteres = this.totalInteres.add(this.listaPagos.get(i).getInteres());
            this.totalAbono = this.totalAbono.add(this.listaPagos.get(i).getAbono());
            this.totalAmortizado = this.totalAmortizado.add(this.listaPagos.get(i).getCapitalamortizado());
            this.totalCapital = this.totalCapital.add(this.listaPagos.get(i).getSaldocapital());
        }
        
           this.totalGeneral= this.totalAbono.add(this.totalInteres);

    }
    
      private BigDecimal totalGeneral;

    public BigDecimal getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(BigDecimal totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    public void reportePlan() throws SQLException, JRException, IOException {

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
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaPagos);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/planPagoPersona.jasper"));
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
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar plan de pago");
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

    public void reportePlanPDF() throws SQLException, JRException, IOException {

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
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaPagos);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/planPagoPersona.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, dataSource);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Plan de pago.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar plan de pago");
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

    public void reportePlanCooperativa() throws SQLException, JRException, IOException {

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
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaPagos);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/planPagoCooperativa.jasper"));
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
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Generar plan de pago");
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

    public void reportePlanCooperativaPDF() throws SQLException, JRException, IOException {

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
        JRDataSource dataSource = new JRBeanCollectionDataSource(this.listaPagos);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/planPagoCooperativa.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, dataSource);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Plan de pago.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar plan de pago");
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

    public void moverIncobrable() {

        this.proyectoSeleccionado.setEstado(7);
        this.proyectoSeleccionado.setCalificacion("Malo");
        this.iproyectoBo.update(this.proyectoSeleccionado);
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Calificar por incobrabilidad");
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

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Credito calificado como incobrable correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.mostrarIncobrabilidadPersona = false;
        this.mostrarIncobrabilidadCooperativa = false;
        mostrarSolicitudes();
        this.setShowData(true);

    }

    public void llamarIncobrables() {

        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        cambiarFormaPago();
        cambiarEstado(this.proyectoSeleccionado.getEstado());
        this.mostrarCierre = false;
        this.mostrarCierrePersona = false;
        this.mostrarRefinanciamientoPersona = false;
        this.mostrarRefinanciamientoCooperativa = false;
        this.mostrarSeguimientoCooperativa = false;
        this.mostrarSeguimientoPersona = false;
        this.mostrarPagoTotalPersona = false;
        this.mostrarIncobrabilidadPersona = true;
        this.mostrarIncobrabilidadCooperativa = false;
        this.setShowData(false);

    }

    public void llamarIncobrables2() {

        this.politicaSeleccionada = this.ipoliticaBo.getPoliticaHistorica(this.proyectoSeleccionado.getTPolitica().getIdPolitica());
        cambiarFormaPago();
        cambiarEstado(this.proyectoSeleccionado.getEstado());
        this.mostrarCierre = false;
        this.mostrarCierrePersona = false;
        this.mostrarRefinanciamientoPersona = false;
        this.mostrarRefinanciamientoCooperativa = false;
        this.mostrarSeguimientoCooperativa = false;
        this.mostrarSeguimientoPersona = false;
        this.mostrarPagoTotalPersona = false;
        this.mostrarIncobrabilidadPersona = false;
        this.mostrarIncobrabilidadCooperativa = true;
        this.setShowData(false);

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
        auxBitacora.setAccionBitacora("Generar DUI fiador");
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
        auxBitacora.setAccionBitacora("Generar NIT fiador");
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
        auxBitacora.setAccionBitacora("Generar carta del fondo de lisiado");
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
        auxBitacora.setTableBitacora("t_proyecto");
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
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
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
        auxBitacora.setTableBitacora("t_proyecto");
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
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
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
        auxBitacora.setTableBitacora("t_proyecto");
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
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
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
        auxBitacora.setTableBitacora("t_proyecto");
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
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
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
        response.addHeader("Content-disposition", "attachment; filename=Pagos.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
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
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
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
        auxBitacora.setTableBitacora("t_proyecto");
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
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
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
        auxBitacora.setTableBitacora("t_proyecto");
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
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
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
        auxBitacora.setTableBitacora("t_proyecto");
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
        auxBitacora.setIdTableBitacora(this.proyectoSeleccionado.getIdproyecto());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    private Integer NumeroTotalPagos;

    public Integer getNumeroTotalPagos() {
        return NumeroTotalPagos;
    }

    public void setNumeroTotalPagos(Integer NumeroTotalPagos) {
        this.NumeroTotalPagos = NumeroTotalPagos;
    }

    public void eliminarPago() {

        if (this.proyectoSeleccionado.getEstado() == 7) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede eliminar el pago", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {
            this.ipagoBo.delete(this.pagoSeleccionado);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_pago");
            auxBitacora.setAccionBitacora("Eliminar pago");
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
                    + ", Destino: " + this.pagoSeleccionado.getDestino()
            );
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.pagoSeleccionado.getIdpago());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);

            this.listaPagos = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());

            if (this.proyectoSeleccionado.getEstado() == 6) {
                this.proyectoSeleccionado.setFechaFinalizacionProyecto(null);
                this.proyectoSeleccionado.setEstado(5);
                this.proyectoSeleccionado.setCalificacion("Pendiente");
                this.iproyectoBo.update(this.proyectoSeleccionado);
            }
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pago eliminado correctamente", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            if (this.mostrarRefinanciamientoCooperativa = true) {
                this.cargarRefinanciamientoCooperativa();
            } else {
                this.cargarRefinanciamientoPersona();
            }
        }

    }

    public void eliminarDesembolso() {

        this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
        if (this.listaPago.size() == 0) {
            this.idesembolsoBo.delete(this.desembolsoSeleccionado);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_desembolso");
            auxBitacora.setAccionBitacora("Eliminar desembolso");
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
                    + ", Monto del desembolso: " + this.desembolsoSeleccionado.getMontoDesembolso()
            );
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.desembolsoSeleccionado.getIdDesembolso());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);

            this.listaPago = this.ipagoBo.listTPago(this.proyectoSeleccionado.getIdproyecto());
            if (this.proyectoSeleccionado.getEstado() == 5) {
                this.proyectoSeleccionado.setFechaVencimientoProyecto(null);
                this.proyectoSeleccionado.setEstado(3);
                this.iproyectoBo.update(this.proyectoSeleccionado);
                cargarDesembolsos(this.tipoClienteDesembolso);
                cambiarEstado(proyectoSeleccionado.getEstado());

                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Desembolso eliminado correctamente", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            }
            
            this.cargarDesembolsos(this.tipoClienteDesembolso);
            
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede eliminar este desembolso", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }

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
