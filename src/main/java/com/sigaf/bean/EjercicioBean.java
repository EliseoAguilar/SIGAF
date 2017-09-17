/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IActivoFijoBo;
import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.ICuentaBo;
import com.sigaf.Ibo.IDepreciacionBo;
import com.sigaf.Ibo.IDetalleParidaBo;
import com.sigaf.Ibo.IEjercicioBo;
import com.sigaf.Ibo.IEmpleadoAreaBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Ibo.IEstructuraBo;
import com.sigaf.Ibo.IPartidaBo;
import com.sigaf.Ibo.IPeriodoBo;
import com.sigaf.Ibo.ITipoActivoBo;
import com.sigaf.Ibo.IValorActivoBo;
import com.sigaf.bo.AreaBo;
import com.sigaf.pojo.TActivoFijo;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TCuenta;
import com.sigaf.pojo.TDepreciacion;
import com.sigaf.pojo.TDetallePartida;
import com.sigaf.pojo.TEjercicio;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEmpleadoArea;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TEstructura;
import com.sigaf.pojo.TPartida;
import com.sigaf.pojo.TPeriodo;
import com.sigaf.pojo.TTipoActivo;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Eliseo
 */
@SessionScoped
@ManagedBean
public class EjercicioBean extends Actividad {

    private TArea area;
    
    private IBitacoraBo bitacoraBo;

    private TEntidad entidad;
    
     private List<TArea> listaArea;
     
     private AreaBo areaBo;
     
    /* para estructura Balance General*/
    private TEstructura estructuraActivo;

    private TEstructura estructuraPasivo;

    private TEstructura estructuraPatrimonio;

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

    /* Acceso para  datos  estructura*/
    private IEstructuraBo estructuraBo;

    /*Lista para Guardar la Depreciacion*/
    private List<TDepreciacion> listaDepreciacion;

    List<TPeriodo> listaPeriodoEjercicio;

    private Boolean estadoPredeterminado;

    private IEmpleadoAreaBo empleadoAreaBo;

    private Integer idEjer;

    private Integer idEjerCierre;

    private TPeriodo periodoNuevo;

    private TPeriodo periodoViejo;

    private List<TDetallePartida> listaDetallePartida;

    private TPartida partida;

    private BigDecimal totalDebe;

    private BigDecimal totalHaber;

    private IPartidaBo partidaBo;

    private IDetalleParidaBo detallePartidaBo;

    private IDepreciacionBo depreciacionBo;

    private IActivoFijoBo activoFijoBo;

    private IValorActivoBo valorActivoBo;

    private ITipoActivoBo tipoActivoBo;

    private ICuentaBo cuentaBo;

    private List< TTipoActivo> listaTipoActivo;

    private List<TEntidad> listaEntidades;

    private IEntidadBo entidadBo;

    private Integer idEntidad;

    private IEjercicioBo ejercicioBo;

    private List<TEjercicio> listaEjercicios;

    private IPeriodoBo periodoBo;

    private TPeriodo periodoSeleccionado;

    private List<String> mesPeriodo;

    private TEjercicio ejercicio;

    private TEjercicio ejercicioViejo;

    /**
     * Atributos para validar el formulario
     */
    private Boolean estadoValido;

    private String msgPeriodo;

    private String msgEjercicio;

    private Boolean mesCierre;

    private BigDecimal Utilidad;

    private BigDecimal Reserva;

    private BigDecimal Renta;

    private List<TEstructura> auxEstBalace;

    private List<TEstructura> auxEstado;

    private List<Integer> listAhos;

    private Boolean pasarSaldos;
    
    private List<TActivoFijo> listActios;
    
    private TTipoActivo tipoActivoSeleccionado;
    

    public TTipoActivo getTipoActivoSeleccionado() {
        return tipoActivoSeleccionado;
    }

    public void setTipoActivoSeleccionado(TTipoActivo tipoActivoSeleccionado) {
        
        this.tipoActivoSeleccionado = tipoActivoSeleccionado;
        
        this.listActios.clear();
        
        this.listActios = this.activoFijoBo.listActivoFijoTipo( this.tipoActivoSeleccionado.getIdTipo());

        List<TActivoFijo> activosDeprelista = new ArrayList<>();

        List<TDepreciacion> listaDepre;

        Integer mesesVidaUtil = 0;

        /* Si la lista no esta vacia calculamos la Vida Util en Meses */
        if (!this.listActios .isEmpty()) {
            mesesVidaUtil = this.tipoActivoSeleccionado.getVidaUtilTipo() * 12;
        }

        for (TActivoFijo tActivoFijo : this.listActios) {

           tActivoFijo.setTTipoActivo(tipoActivoSeleccionado);
           
            /*Si el Activo esta ACTIVO y Ya se realizo el Asiento contable*/
            if (tActivoFijo.getEstadoActivoFijo().equals("Activo") && null != this.valorActivoBo.getTValorActivo(tActivoFijo.getIdActivoFijo()).getTPartida()) {

                
                listaDepre = this.depreciacionBo.listDepreciacion(tActivoFijo.getIdActivoFijo());

                /* Si es la primera depreciacion se verifica segun la politica (Registo <15 aplicar depreciacion) */
                if (listaDepre.isEmpty()) {

                    String[] aFechaIng = tActivoFijo.getRegistroActivoFijo().toString().split("-");

                    Integer diaInicio = Integer.parseInt(aFechaIng[2]);

                    java.util.Date fecha = new Date();

                    int diferenciaMes = fecha.getMonth() - tActivoFijo.getRegistroActivoFijo().getMonth();

                    if (diferenciaMes >= 1) {

                        activosDeprelista.add(tActivoFijo);

                    } else if (diaInicio <= 15) {

                        activosDeprelista.add(tActivoFijo);

                    }

                }/* Si aun se puede Depreciar se agrega a la lista para depreciar*/ 
                else if (listaDepre.size() < mesesVidaUtil) {
                    activosDeprelista.add(tActivoFijo);
                }

            }
        }

        if (!activosDeprelista.isEmpty()) {
            this.cargarParida(activosDeprelista);
        }
    }

    public List<TActivoFijo> getListActios() {
        return listActios;
    }

    public void setListActios(List<TActivoFijo> listActios) {
        this.listActios = listActios;
    }

    
    
    public AreaBo getAreaBo() {
        return areaBo;
    }

    public void setAreaBo(AreaBo areaBo) {
        this.areaBo = areaBo;
    }

    
     public void updateListaArea() {
        listaArea = this.areaBo.listArea(idEntidad);
    }
     
    public List<Integer> getListAhos() {
        return listAhos;
    }

    public void setListAhos(List<Integer> listAhos) {
        this.listAhos = listAhos;
    }

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
     * Metodo que actuliza la lista de ejercicos por Entidad y determina si se
     * mostrara el formulario para una nueva contabilidad o la lista de
     * ejercicios
     */
    public void updatelistaEjercicios() {

        this.listaEjercicios = this.ejercicioBo.listEjercicio(idEntidad);

        if (this.listaEjercicios.isEmpty()) {
            this.limpiar();
            Date dateTemp = new Date();
            this.listAhos.clear();
            for (int aho = dateTemp.getYear() - 5; aho <= dateTemp.getYear() + 5; aho++) {
                this.listAhos.add(aho);
            }
            super.enableShowCreate();
        } else {
            super.enableShowData();
        }

    }

    /**
     * Metodo para determinar si el mes actual corresponde al cierre del
     * ejercicio
     *
     * @param idEjer
     */
    public void updateMesCierre(Integer idEjer) {

        this.periodoViejo = this.periodoBo.getPeriodoAbierto(idEjer);

        this.mesCierre = periodoViejo.getMesPeriodo().equals("Diciembre");

    }

    /**
     * Asigna el nombre del mes al periodo nuevo segun el perido acual
     */
    public void llenarMesPeriodoAux(String mes) {

        switch (mes) {
            case "Enero":
                this.periodoNuevo.setMesPeriodo("Febrero");
                break;
            case "Febrero":
                this.periodoNuevo.setMesPeriodo("Marzo");
                break;
            case "Marzo":
                this.periodoNuevo.setMesPeriodo("Abril");
                break;
            case "Abril":
                this.periodoNuevo.setMesPeriodo("Mayo");
                break;
            case "Mayo":
                this.periodoNuevo.setMesPeriodo("Junio");
                break;
            case "Junio":
                this.periodoNuevo.setMesPeriodo("Julio");
                break;
            case "Julio":
                this.periodoNuevo.setMesPeriodo("Agosto");
                break;
            case "Agosto":
                this.periodoNuevo.setMesPeriodo("Septiembre");
                break;
            case "Septiembre":
                this.periodoNuevo.setMesPeriodo("Octubre");
                break;
            case "Octubre":
                this.periodoNuevo.setMesPeriodo("Noviembre");
                break;
            case "Noviembre":
                this.periodoNuevo.setMesPeriodo("Diciembre");
                break;
            default:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Imposible agregar otro periodo a este ejercicio."));
                break;
        }

    }

    /**
     * Metodo para Registar nueva contabilidad, se crea el primer ejercicio y
     * primer perido del ejercicio
     */
    public void agregarInicio() {

        try {

            TEntidad auxEnt = new TEntidad(idEntidad);
            ejercicio.setEstadoEjercicio(true);
            ejercicio.setTEntidad(auxEnt);
            this.ejercicioBo.create(ejercicio);
            this.periodoNuevo.setTEjercicio(ejercicio);
            this.periodoNuevo.setEstadoPeriodo(true);
            this.periodoBo.create(periodoNuevo);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_ejercicio, t_periodo ");
            auxBitacora.setAccionBitacora("Agregar ejercicio y periodo");
            auxBitacora.setDatosBitacora("Ejercicio:" + ejercicio.getAhoEjercicio()
                    + ", Periodo:" + periodoNuevo.getMesPeriodo() + ", Entidad:" + this.idEntidad);
            auxBitacora.setIdTableBitacora(ejercicio.getIdEjercicio());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            super.enableShowData();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Periodo registrado correctamente.", ""));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El periodo no pudo ser registrado.", ""));
        }

    }

    /**
     * Metodo para Guardar en la base de Datos la Partida de Asiento
     */
    public void agregarPartidaAsiento() {
        if (!listaDetallePartida.isEmpty()) {

            this.partida.setEstadoPartida(true);
            this.partidaBo.create(this.partida);

            /* Registrando la depreciacion */
            for (TDepreciacion depre : listaDepreciacion) {
                depre.setTPartida(partida);
                depreciacionBo.create(depre);
            }

            /*Registrando el Detalle de Partidas*/
            for (TDetallePartida tDetallePartida : listaDetallePartida) {

                tDetallePartida.setTPartida(partida);

                this.detallePartidaBo.create(tDetallePartida);
            }

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_partida, t_depreciacion");
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

        }
    }

    /**
     * Metodo para Registar el cierre de un periodo actual y apertura del nuevo.
     */
    public void agregarCierrePeriodo() {

        try {

            /* Creando la partida de Asiento*/
            this.agregarPartidaAsiento();

            /* cambiando el estado a  False para el cierre de periodo*/
            this.periodoViejo.setEstadoPeriodo(false);
            this.periodoBo.update(periodoViejo);

            /* creando el nuevo periodo */
            this.periodoNuevo.setTEjercicio(new TEjercicio(idEjer));
            this.periodoNuevo.setEstadoPeriodo(true);
            this.periodoBo.create(periodoNuevo);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_periodo");
            auxBitacora.setAccionBitacora("Cerrar y agregar periodo");
            auxBitacora.setDatosBitacora("Periodo cierre:" + periodoViejo.getMesPeriodo()
                    + ", Periodo nuevo:" + periodoNuevo.getMesPeriodo() + ", Entidad:" + this.idEntidad);
            auxBitacora.setIdTableBitacora(periodoViejo.getIdPeriodo());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            this.enableShowData();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Periodo  registrado correctamente.", ""));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El periodo no pudo ser registrado.", ""));
        }

    }

    /**
     * Metodo para preparar la apertura de un nuevo periodo y cierre del
     * anterior
     */
    public void setIdEjer(Integer idEjer) {
        /**
         * Asigna el numero de partida correlativo para la partida de ajuste
         */

        this.partida = new TPartida();
        this.partida.setNumeroPartida(this.partidaBo.numeroPartida(idEjer));
        this.partida.setFechaPartida(new Date());
        this.partida.setTPeriodo(periodoViejo);

        this.partida.setConceptoPartida("Depreciación/Amortización " + periodoViejo.getMesPeriodo());
        this.listaDepreciacion.clear();
        this.listaDetallePartida.clear();
        this.totalDebe = BigDecimal.ZERO;
        this.totalHaber = BigDecimal.ZERO;

        this.periodoNuevo = new TPeriodo();
        this.llenarMesPeriodoAux(periodoViejo.getMesPeriodo());

        /*Consulta de tipos de activos que  tienen la entidad*/
        this.listaTipoActivo = this.tipoActivoBo.listTipoActivo(idEntidad);

        this.idEjer = idEjer;

    }

    /**
     * Metodo para preparar el cierre del ejercicio y apertura del nuevo
     */
    public void setIdEjerCierre(Integer idEjerCierre) {
        this.pasarSaldos = true;
        this.partida = new TPartida();
        this.partida.setNumeroPartida(this.partidaBo.numeroPartida(idEjerCierre));
        this.partida.setFechaPartida(new Date());
        this.partida.setTPeriodo(periodoViejo);

        this.partida.setConceptoPartida("Depreciación/Amortización " + periodoViejo.getMesPeriodo());
        this.listaDepreciacion.clear();
        this.listaDetallePartida.clear();
        this.totalDebe = BigDecimal.ZERO;
        this.totalHaber = BigDecimal.ZERO;
        /*Sacando el ejercico que se va a cerrar*/
        this.ejercicioViejo = this.ejercicioBo.getEjercicio(idEjerCierre);
        this.ejercicioViejo.setFechaCierre(new Date());
        
        

        /*Se saca el periodo viejo desde la bd para tener otra instancia y poder modificarla para el nuevo */
        this.ejercicio = this.ejercicioBo.getEjercicio(idEjerCierre);
        this.ejercicio.setAhoEjercicio(this.ejercicio.getAhoEjercicio() + 1);
        
        this.listAhos.clear();
        for (int aho = this.ejercicio.getAhoEjercicio() ; aho <= this.ejercicio.getAhoEjercicio() + 5; aho++) {
            this.listAhos.add(aho);
        }

        this.periodoNuevo = new TPeriodo();
        this.periodoNuevo.setMesPeriodo("Enero");

        /*Consulta de tipos de activos que  tienen la entidad*/
        this.listaTipoActivo = this.tipoActivoBo.listTipoActivo(idEntidad);

        this.idEjerCierre = idEjerCierre;

    }

    /**
     * Metodo que calcula la depreciacion mensual de un activo fijo
     */
    public BigDecimal depreMensual(TActivoFijo act) {

        BigDecimal valorActivoFijo = this.valorActivoBo.getTValorActivo(act.getIdActivoFijo()).getValorActivo();

        BigDecimal valorDepreActivoFijo;

        Integer mesesVidaUtil = 12 * act.getTTipoActivo().getVidaUtilTipo();

        valorDepreActivoFijo = valorActivoFijo.divide(new BigDecimal(mesesVidaUtil), 2, RoundingMode.HALF_UP);

        return valorDepreActivoFijo;

    }


    /**
     * Llena el detalle de la partida para el Asiento contable
     */
    public void cargarParida(List<TActivoFijo> listaAux) {

        BigDecimal sum = new BigDecimal(0.00);

        /*
        * Sacando la cuenta de Depreciacion
         */
        TCuenta depre = listaAux.get(0).getTTipoActivo().getTCuentaByIdCuentaDepreciacionTipo();

        /*
        * Sacando la cuenta de  Gastos de Depreciacion
         */
        TCuenta gasto = listaAux.get(0).getTTipoActivo().getTCuentaByIdCuentaGastoTipo();

        TDetallePartida detallePartidaDepre = new TDetallePartida();

        TDetallePartida detallePartidaGasto = new TDetallePartida();

        detallePartidaDepre.setTCuenta(depre);

        detallePartidaGasto.setTCuenta(gasto);

        for (TActivoFijo tActivoFijo : listaAux) {
            /* Cargando la lista con las Depreciaciones*/
            BigDecimal depreMensualTemp = depreMensual(tActivoFijo);
            TDepreciacion depreciacionTemp = new TDepreciacion();
            depreciacionTemp.setTActivoFijo(tActivoFijo);
            depreciacionTemp.setValorDepreciacion(depreMensualTemp);
            this.listaDepreciacion.add(depreciacionTemp);
            /*Acumulando las depreciaciones*/
            sum = sum.add(depreMensualTemp);
        }

        detallePartidaDepre.setSaldoDetallePartida(sum);
        detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
        detallePartidaGasto.setSaldoDetallePartida(sum);
        detallePartidaGasto.setTipoSaldoDetallePartida("Debe");

        this.totalDebe = totalDebe.add(sum);
        this.totalHaber = totalHaber.add(sum);

        this.listaDetallePartida.add(detallePartidaGasto);
        this.listaDetallePartida.add(detallePartidaDepre);

    }
    
    
 
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
     * Genera el codigo del Activo Fijo
     */
    public String cogigoGenerar(TActivoFijo activo) {

        this.buscarEmpleadoArea(activo.getTEmpleado());

        return this.entidad.getCodigoEntidad() + "-" + this.area.getCodigoArea() + "-" + activo.getTTipoActivo().getCodigoTipo() + "-" + activo.getCodigoActivoFijo();

    }

    /**
     * Metodo para Saber que accion ejecutar al cancelar la creacion de un nuevo
     * ejercicio (Nueva contabilidad), tomando en cuenta que si se a establecido
     * una entidad predeterminada dejara esa selecciona, sino establecera
     * [SELECCIONE]
     */
    public Integer tipoCancelar() {
        if (estadoPredeterminado) {
            return idEntidad;
        } else {
            return 0;
        }

    }

    /*
    * Actualiza la lista de Periodos por Ejercicio
     */
    public void updateListaPeriodoEjercicio(Integer id) {

        this.listaPeriodoEjercicio = periodoBo.listPeriodo(id);

    }

    public void init() {
        this.idEntidad = 0;
        this.estadoPredeterminado = false;
        this.listaDepreciacion = new ArrayList<>();
        this.listaDetallePartida = new ArrayList<>();
        this.listAhos= new ArrayList<>();
        this.listActios= new ArrayList<>();
    }

    public void limpiar() {
        this.estadoValido = false;
        this.msgPeriodo = "";
        this.msgEjercicio = "";
        this.periodoNuevo = new TPeriodo();
        this.ejercicio = new TEjercicio();
       
    }

    public void validarFormularioInicio() {

        this.estadoValido = true;

        if (this.periodoNuevo.getMesPeriodo().equals("0")) {
            this.msgPeriodo = "Seleccione el periodo";
            this.estadoValido = false;
        } else {
            this.msgPeriodo = "";
        }
        if (this.ejercicio.getAhoEjercicio() == 0) {
            this.msgEjercicio = "Seleccione el ejercicio";
            this.estadoValido = false;
        } else {
            this.msgEjercicio = "";
        }

    }
    
    
        public void validarFormularioCierre() {

        this.estadoValido = true;

        if (this.periodoNuevo.getMesPeriodo().equals("0") && this.pasarSaldos) {
            this.msgPeriodo = "Seleccione el periodo";
            this.estadoValido = false;
        } else {
            this.msgPeriodo = "";
        }
        if (this.ejercicio.getAhoEjercicio() == 0 && this.pasarSaldos) {
            this.msgEjercicio = "Seleccione el ejercicio";
            this.estadoValido = false;
        } else {
            this.msgEjercicio = "";
        }

    }

    public Boolean getMesCierre() {
        return mesCierre;
    }

    public void setMesCierre(Boolean mesCierre) {
        this.mesCierre = mesCierre;
    }

    public List<TPeriodo> getListaPeriodoEjercicio() {
        return listaPeriodoEjercicio;
    }

    public void setListaPeriodoEjercicio(List<TPeriodo> listaPeriodoEjercicio) {
        this.listaPeriodoEjercicio = listaPeriodoEjercicio;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
       

        for (TEntidad tentidad : listaEntidades) {

            if (tentidad.getIdEntidad() == idEntidad) {
                this.entidad = tentidad;
                return;
            }
        }
    }

    public List<TDepreciacion> getListaDepreciacion() {
        return listaDepreciacion;
    }

    public void setListaDepreciacion(List<TDepreciacion> listaDepreciacion) {
        this.listaDepreciacion = listaDepreciacion;
    }

    public void cambiarPredeterminado() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
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

    public IEstructuraBo getEstructuraBo() {
        return estructuraBo;
    }

    public void setEstructuraBo(IEstructuraBo estructuraBo) {
        this.estructuraBo = estructuraBo;
    }

    public Integer getIdEjerCierre() {
        return idEjerCierre;
    }

    public TEjercicio getEjercicioViejo() {
        return ejercicioViejo;
    }

    public void setEjercicioViejo(TEjercicio ejercicioViejo) {
        this.ejercicioViejo = ejercicioViejo;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public IEmpleadoAreaBo getEmpleadoAreaBo() {
        return empleadoAreaBo;
    }

    public void setEmpleadoAreaBo(IEmpleadoAreaBo empleadoAreaBo) {
        this.empleadoAreaBo = empleadoAreaBo;
    }

    public Integer getIdEjer() {
        return idEjer;
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

    public List<TDetallePartida> getListaDetallePartida() {
        return listaDetallePartida;
    }

    public void setListaDetallePartida(List<TDetallePartida> listaDetallePartida) {
        this.listaDetallePartida = listaDetallePartida;
    }

    public TPartida getPartida() {
        return partida;
    }

    public void setPartida(TPartida partida) {
        this.partida = partida;
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

    public IDepreciacionBo getDepreciacionBo() {
        return depreciacionBo;
    }

    public void setDepreciacionBo(IDepreciacionBo depreciacionBo) {
        this.depreciacionBo = depreciacionBo;
    }

    public IActivoFijoBo getActivoFijoBo() {
        return activoFijoBo;
    }

    public void setActivoFijoBo(IActivoFijoBo activoFijoBo) {
        this.activoFijoBo = activoFijoBo;
    }

    public IValorActivoBo getValorActivoBo() {
        return valorActivoBo;
    }

    public void setValorActivoBo(IValorActivoBo valorActivoBo) {
        this.valorActivoBo = valorActivoBo;
    }

    public ITipoActivoBo getTipoActivoBo() {
        return tipoActivoBo;
    }

    public void setTipoActivoBo(ITipoActivoBo tipoActivoBo) {
        this.tipoActivoBo = tipoActivoBo;
    }

    public void setListaTipoActivo(List<TTipoActivo> listaTipoActivo) {
        this.listaTipoActivo = listaTipoActivo;
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

    public String getMsgEjercicio() {
        return msgEjercicio;
    }

    public void setMsgEjercicio(String msgEjercicio) {
        this.msgEjercicio = msgEjercicio;
    }

    public Boolean getEstadoValido() {
        return estadoValido;
    }

    public void setEstadoValido(Boolean estadoValido) {
        this.estadoValido = estadoValido;
    }

    public String getMsgPeriodo() {
        return msgPeriodo;
    }

    public void setMsgPeriodo(String msgPeriodo) {
        this.msgPeriodo = msgPeriodo;
    }

    public TEjercicio getEjercicio() {
        return ejercicio;
    }

    public List<TEjercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    public void setListaEjercicios(List<TEjercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    public TPeriodo getPeriodoSeleccionado() {
        return periodoSeleccionado;
    }

    public void setPeriodoSeleccionado(TPeriodo periodoSeleccionado) {
        this.periodoSeleccionado = periodoSeleccionado;
    }

    public void setEjercicio(TEjercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public List<String> getMesPeriodo() {
        return mesPeriodo;
    }

    public void setMesPeriodo(List<String> mesPeriodo) {
        this.mesPeriodo = mesPeriodo;
    }

    public TPeriodo getPeriodoNuevo() {
        return periodoNuevo;
    }

    public void setPeriodoNuevo(TPeriodo periodoNuevo) {
        this.periodoNuevo = periodoNuevo;
    }

    public TPeriodo getPeriodoViejo() {
        return periodoViejo;
    }

    public void setPeriodoViejo(TPeriodo periodoViejo) {
        this.periodoViejo = periodoViejo;
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

    public List<TTipoActivo> getListaTipoActivo() {
        return listaTipoActivo;
    }

    public ICuentaBo getCuentaBo() {
        return cuentaBo;
    }

    public void setCuentaBo(ICuentaBo cuentaBo) {
        this.cuentaBo = cuentaBo;
    }

    public Boolean getPasarSaldos() {
        return pasarSaldos;
    }

    public void setPasarSaldos(Boolean pasarSaldos) {
        this.pasarSaldos = pasarSaldos;
    }

    public String subCodidoCuenta(String codigoCuenta) {

        String[] partsOr = codigoCuenta.split("-");

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

        for (int i = 1; i <= (partsOr.length - inicio); i++) {
            strOr += "-";
            strOr += partsOr[i];

        }

        return strOr;
    }

    public void estadoResultado() {

        this.listaEstructuraCostos = new ArrayList<>();
        this.listaEstructuraGastos = new ArrayList<>();
        this.listaEstructuraIngresos = new ArrayList<>();
        this.listaEstructuraOtrosGastos = new ArrayList<>();
        this.listaEstructuraOtrosIngresos = new ArrayList<>();
        this.rentaEstructura = null;
        this.ReservaEstructura = null;
        this.utilidadEstructura = null;

        if (!auxEstado.isEmpty()) {

            for (TEstructura tEstructura : auxEstado) {
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
                        this.rentaEstructura = tEstructura;
                        break;
                    case 7:
                        this.ReservaEstructura = tEstructura;
                        break;
                    case 8:
                        this.utilidadEstructura = tEstructura;
                        break;
                }
            }

            this.listaDetallePartida.clear();
            Utilidad = BigDecimal.ZERO;
            TDetallePartida detallePartidaDepre;
            BigDecimal debe;
            BigDecimal haber;

            /* 
            * Datos para el total de Ingresos
             */
            BigDecimal totalIngre = BigDecimal.ZERO;

            for (TEstructura ingre : listaEstructuraIngresos) {

                List<TCuenta> CuentasIngre = this.cuentaBo.listCuentaSubDet(ingre.getTCuenta().getIdCuenta(), ejercicioViejo.getTEntidad().getIdEntidad(), ejercicioViejo.getIdEjercicio(), this.subCodidoCuenta(ingre.getTCuenta().getCodigoCuenta()));

                for (TCuenta CuentaIngre : CuentasIngre) {

                    debe = this.cuentaBo.saldoCuenta(CuentaIngre.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Debe");

                    haber = this.cuentaBo.saldoCuenta(CuentaIngre.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Haber");

                    detallePartidaDepre = new TDetallePartida();

                    detallePartidaDepre.setTCuenta(CuentaIngre);

                    BigDecimal saldo;

                    if (CuentaIngre.getNaturalezaCuenta().equals("Deudora")) {
                        saldo = debe.subtract(haber);
                        if (saldo.compareTo(BigDecimal.ZERO) == 1) {

                            detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        } else if (saldo.compareTo(BigDecimal.ZERO) == -1) {
                            detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        }

                    } else {
                        saldo = haber.subtract(debe);
                        if (saldo.compareTo(BigDecimal.ZERO) == 1) {

                            detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        } else if (saldo.compareTo(BigDecimal.ZERO) == -1) {
                            detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        }

                    }

                    totalIngre = totalIngre.add(saldo);
                }

            }

            totalIngre = totalIngre.setScale(2, BigDecimal.ROUND_HALF_UP);

            /* 
            * Datos para el total de Costos
             */
            BigDecimal totalCost = BigDecimal.ZERO;

            for (TEstructura cost : listaEstructuraCostos) {

                List<TCuenta> CuentasCost = this.cuentaBo.listCuentaSubDet(cost.getTCuenta().getIdCuenta(), ejercicioViejo.getTEntidad().getIdEntidad(), ejercicioViejo.getIdEjercicio(), this.subCodidoCuenta(cost.getTCuenta().getCodigoCuenta()));

                for (TCuenta CuentaCost : CuentasCost) {

                    debe = this.cuentaBo.saldoCuenta(CuentaCost.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Debe");

                    haber = this.cuentaBo.saldoCuenta(CuentaCost.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Haber");

                    detallePartidaDepre = new TDetallePartida();

                    detallePartidaDepre.setTCuenta(CuentaCost);

                    BigDecimal saldo;

                    if (CuentaCost.getNaturalezaCuenta().equals("Deudora")) {
                        saldo = debe.subtract(haber);
                        if (saldo.compareTo(BigDecimal.ZERO) == 1) {

                            detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        } else if (saldo.compareTo(BigDecimal.ZERO) == -1) {
                            detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        }

                    } else {
                        saldo = haber.subtract(debe);
                        if (saldo.compareTo(BigDecimal.ZERO) == 1) {

                            detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        } else if (saldo.compareTo(BigDecimal.ZERO) == -1) {
                            detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        }

                    }
                    totalCost = totalCost.add(saldo);

                }
            }
            totalCost = totalCost.setScale(2, BigDecimal.ROUND_HALF_UP);

            /* 
            * Datos para Utilidad Bruta
             */
            Utilidad = totalIngre.subtract(totalCost);
            Utilidad = Utilidad.setScale(2, BigDecimal.ROUND_HALF_UP);

            /* 
            * Datos para el total de Gastos
             */
            BigDecimal totalGast = BigDecimal.ZERO;

            for (TEstructura gast : listaEstructuraGastos) {

                List<TCuenta> CuentasGast = this.cuentaBo.listCuentaSubDet(gast.getTCuenta().getIdCuenta(), ejercicioViejo.getTEntidad().getIdEntidad(), ejercicioViejo.getIdEjercicio(), this.subCodidoCuenta(gast.getTCuenta().getCodigoCuenta()));

                for (TCuenta CuentaGast : CuentasGast) {

                    debe = this.cuentaBo.saldoCuenta(CuentaGast.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Debe");

                    haber = this.cuentaBo.saldoCuenta(CuentaGast.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Haber");

                    detallePartidaDepre = new TDetallePartida();

                    detallePartidaDepre.setTCuenta(CuentaGast);

                    BigDecimal saldo;

                    if (CuentaGast.getNaturalezaCuenta().equals("Deudora")) {
                        saldo = debe.subtract(haber);
                        if (saldo.compareTo(BigDecimal.ZERO) == 1) {

                            detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        } else if (saldo.compareTo(BigDecimal.ZERO) == -1) {
                            detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        }

                    } else {
                        saldo = haber.subtract(debe);
                        if (saldo.compareTo(BigDecimal.ZERO) == 1) {

                            detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        } else if (saldo.compareTo(BigDecimal.ZERO) == -1) {
                            detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        }

                    }
                    totalGast = totalGast.add(saldo);
                }
            }

            totalGast = totalGast.setScale(2, BigDecimal.ROUND_HALF_UP);

            /* 
            * Datos para Utilidad de Operacion
             */
            Utilidad = Utilidad.subtract(totalGast);

            /* 
            * Datos para Otros Ingresos
             */
            BigDecimal totalOtrIngre = BigDecimal.ZERO;

            for (TEstructura otrosIngre : listaEstructuraOtrosIngresos) {

                List<TCuenta> CuentasOtrosIngre = this.cuentaBo.listCuentaSubDet(otrosIngre.getTCuenta().getIdCuenta(), ejercicioViejo.getTEntidad().getIdEntidad(), ejercicioViejo.getIdEjercicio(), this.subCodidoCuenta(otrosIngre.getTCuenta().getCodigoCuenta()));

                for (TCuenta CuentaOtroIngre : CuentasOtrosIngre) {

                    debe = this.cuentaBo.saldoCuenta(CuentaOtroIngre.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Debe");

                    haber = this.cuentaBo.saldoCuenta(CuentaOtroIngre.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Haber");

                    detallePartidaDepre = new TDetallePartida();

                    detallePartidaDepre.setTCuenta(CuentaOtroIngre);

                    BigDecimal saldo;

                    if (CuentaOtroIngre.getNaturalezaCuenta().equals("Deudora")) {
                        saldo = debe.subtract(haber);
                        if (saldo.compareTo(BigDecimal.ZERO) == 1) {

                            detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        } else if (saldo.compareTo(BigDecimal.ZERO) == -1) {
                            detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        }

                    } else {
                        saldo = haber.subtract(debe);
                        if (saldo.compareTo(BigDecimal.ZERO) == 1) {

                            detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        } else if (saldo.compareTo(BigDecimal.ZERO) == -1) {
                            detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        }

                    }
                    totalOtrIngre = totalOtrIngre.add(saldo);
                }
            }

            totalOtrIngre = totalOtrIngre.setScale(2, BigDecimal.ROUND_HALF_UP);

            /* 
                         * Datos para Otros Gastos
             */
            BigDecimal totalOtrGast = BigDecimal.ZERO;

            for (TEstructura otrGast : listaEstructuraOtrosGastos) {

                List<TCuenta> CuentasOtrosGast = this.cuentaBo.listCuentaSubDet(otrGast.getTCuenta().getIdCuenta(), ejercicioViejo.getTEntidad().getIdEntidad(), ejercicioViejo.getIdEjercicio(), this.subCodidoCuenta(otrGast.getTCuenta().getCodigoCuenta()));

                for (TCuenta CuentaOtroGast : CuentasOtrosGast) {

                    debe = this.cuentaBo.saldoCuenta(CuentaOtroGast.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Debe");

                    haber = this.cuentaBo.saldoCuenta(CuentaOtroGast.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Haber");

                    detallePartidaDepre = new TDetallePartida();

                    detallePartidaDepre.setTCuenta(CuentaOtroGast);

                    BigDecimal saldo;

                    if (CuentaOtroGast.getNaturalezaCuenta().equals("Deudora")) {
                        saldo = debe.subtract(haber);
                        if (saldo.compareTo(BigDecimal.ZERO) == 1) {

                            detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        } else if (saldo.compareTo(BigDecimal.ZERO) == -1) {
                            detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        }

                    } else {
                        saldo = haber.subtract(debe);
                        if (saldo.compareTo(BigDecimal.ZERO) == 1) {

                            detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        } else if (saldo.compareTo(BigDecimal.ZERO) == -1) {
                            detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                            detallePartidaDepre.setSaldoDetallePartida(saldo);
                            listaDetallePartida.add(detallePartidaDepre);
                        }

                    }
                    totalOtrGast = totalOtrGast.add(saldo);

                }
            }

            totalOtrGast = totalOtrGast.setScale(2, BigDecimal.ROUND_HALF_UP);

            /* 
            * Datos para Utilidad de Ates de Impuestos
             */
            Utilidad = Utilidad.add(totalOtrIngre).subtract(totalOtrGast);

            Renta = BigDecimal.ZERO;
            Reserva = BigDecimal.ZERO;

            /*
            Si hay perdida no se paga inpuestos  ni se hace reserva
             */
            if (Utilidad.compareTo(BigDecimal.ZERO) > 0) {

                if (this.rentaEstructura != null) {

                    BigDecimal porRent;

                    if (Utilidad.compareTo(rentaEstructura.getValorRango()) <= 0) {
                        porRent = rentaEstructura.getPorMinEstructura().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
                    } else {
                        porRent = rentaEstructura.getPorMaxEstructura().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
                    }

                    Renta = Utilidad.multiply(porRent);
                    Renta = Renta.setScale(2, BigDecimal.ROUND_HALF_UP);

                    detallePartidaDepre = new TDetallePartida();
                    detallePartidaDepre.setTCuenta(rentaEstructura.getTCuenta());

                    if (rentaEstructura.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                        detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                    } else {
                        detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                    }

                    detallePartidaDepre.setSaldoDetallePartida(Renta);
                    listaDetallePartida.add(detallePartidaDepre);

                    Utilidad = Utilidad.subtract(Renta);

                }

                if (this.ReservaEstructura != null) {

                    BigDecimal porRese;
                    porRese = ReservaEstructura.getPorMinEstructura().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
                    Reserva = Utilidad.multiply(porRese);
                    Reserva = Reserva.setScale(2, BigDecimal.ROUND_HALF_UP);
                    detallePartidaDepre = new TDetallePartida();
                    detallePartidaDepre.setTCuenta(ReservaEstructura.getTCuenta());

                    if (ReservaEstructura.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                        detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                    } else {
                        detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                    }

                    detallePartidaDepre.setSaldoDetallePartida(Reserva);
                    listaDetallePartida.add(detallePartidaDepre);

                    Utilidad = Utilidad.subtract(Reserva);
                }

                detallePartidaDepre = new TDetallePartida();

                detallePartidaDepre.setTCuenta(utilidadEstructura.getTCuenta());

                if (utilidadEstructura.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                    detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                } else {
                    detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                }

                detallePartidaDepre.setSaldoDetallePartida(Utilidad);
                listaDetallePartida.add(detallePartidaDepre);

            }

            this.partida = new TPartida();
            this.partida.setNumeroPartida(this.partidaBo.numeroPartida(this.ejercicioViejo.getIdEjercicio()));
            this.partida.setFechaPartida(new Date());
            this.partida.setConceptoPartida("Traspaso de saldos para determinar resultados del ejercicio" + this.ejercicioViejo.getAhoEjercicio());
            this.partida.setEstadoPartida(false);
            this.partida.setTPeriodo(periodoViejo);
            this.partidaBo.create(this.partida);

            /*Registrando el Detalle de Partidas*/
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

        }
    }

    public void agregarCierreEjercicio() {

        /* Accesando a datos de configuracion Balance General*/
        auxEstBalace = this.estructuraBo.listEstructura(this.ejercicioViejo.getIdEjercicio(), 2);

        /* Accesando a datos de configuracion Estado de Resultado*/
        auxEstado = this.estructuraBo.listEstructura(this.idEjerCierre, 1);

        if (!auxEstado.isEmpty() && !auxEstBalace.isEmpty()) {

            try {

                /* Creando la partida de Asiento*/
                this.agregarPartidaAsiento();

                this.estadoResultado();

                /* cambiando el estao a FALSE para cierre de ejercicio*/
                this.ejercicioViejo.setEstadoEjercicio(false);
                this.ejercicioBo.update(ejercicioViejo);
                /* cambiando el estado a  False para el cierre de periodo*/
                this.periodoViejo.setEstadoPeriodo(false);
                this.periodoBo.update(periodoViejo);

                TBitacora auxBitacora = new TBitacora();
                auxBitacora.setTableBitacora("t_ejercicio, t_periodo");
                auxBitacora.setAccionBitacora("Cerrar ejercicio y  periodo");
                auxBitacora.setDatosBitacora("Ejercicio:" + ejercicioViejo.getAhoEjercicio() + ", Periodo:" + periodoViejo.getMesPeriodo() + ", Entidad:" + this.idEntidad);
                auxBitacora.setIdTableBitacora(ejercicioViejo.getIdEjercicio());
                auxBitacora.setHoraBitacora(new Date());
                auxBitacora.setFechaBitacora(new Date());
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                // Get the loginBean from session attribute
                LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
                auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
                bitacoraBo.create(auxBitacora);


                /*
                * Parte donde se procesa el cierre y la apertura 
                 */
                for (TEstructura tauxEstBalace : auxEstBalace) {
                    switch (tauxEstBalace.getGrupoReporte()) {
                        case 11:
                            this.estructuraActivo = tauxEstBalace;

                            break;
                        case 12:
                            this.estructuraPasivo = tauxEstBalace;

                            break;
                        case 13:
                            this.estructuraPatrimonio = tauxEstBalace;

                            break;

                    }

                }

                List<TCuenta> CuentasActivos = this.cuentaBo.listCuentaSubDet(estructuraActivo.getTCuenta().getIdCuenta(), ejercicioViejo.getTEntidad().getIdEntidad(), ejercicioViejo.getIdEjercicio(), this.estructuraActivo.getTCuenta().getCodigoCuenta().split("-")[0]);
                this.listaDetallePartida.clear();
                TDetallePartida detallePartidaDepre;
                BigDecimal saldoDebe;
                BigDecimal saldoHaber;

                for (TCuenta CuentasActivo : CuentasActivos) {

                    saldoDebe = this.cuentaBo.saldoCuentaCierre(CuentasActivo.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Debe");
                    saldoHaber = this.cuentaBo.saldoCuentaCierre(CuentasActivo.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Haber");
                    detallePartidaDepre = new TDetallePartida();

                    detallePartidaDepre.setTCuenta(CuentasActivo);

                    if (saldoDebe.compareTo(saldoHaber) == 1) {

                        detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                        detallePartidaDepre.setSaldoDetallePartida(saldoDebe.subtract(saldoHaber));
                        listaDetallePartida.add(detallePartidaDepre);
                    } else if (saldoDebe.compareTo(saldoHaber) == -1) {

                        detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                        detallePartidaDepre.setSaldoDetallePartida(saldoHaber.subtract(saldoDebe));
                        listaDetallePartida.add(detallePartidaDepre);
                    }

                }

                List<TCuenta> CuentasPasivos = this.cuentaBo.listCuentaSubDet(estructuraPasivo.getTCuenta().getIdCuenta(), ejercicioViejo.getTEntidad().getIdEntidad(), ejercicioViejo.getIdEjercicio(), this.estructuraPasivo.getTCuenta().getCodigoCuenta().split("-")[0]);

                for (TCuenta CuentasPasivo : CuentasPasivos) {

                    saldoDebe = this.cuentaBo.saldoCuentaCierre(CuentasPasivo.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Debe");
                    saldoHaber = this.cuentaBo.saldoCuentaCierre(CuentasPasivo.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Haber");
                    detallePartidaDepre = new TDetallePartida();
                    detallePartidaDepre.setTCuenta(CuentasPasivo);

                    if (saldoDebe.compareTo(saldoHaber) == 1) {

                        detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                        detallePartidaDepre.setSaldoDetallePartida(saldoDebe.subtract(saldoHaber));
                        listaDetallePartida.add(detallePartidaDepre);
                    } else if (saldoDebe.compareTo(saldoHaber) == -1) {

                        detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                        detallePartidaDepre.setSaldoDetallePartida(saldoHaber.subtract(saldoDebe));
                        listaDetallePartida.add(detallePartidaDepre);
                    }
                }

                List<TCuenta> CuentasPatrimonios = this.cuentaBo.listCuentaSubDet(estructuraPatrimonio.getTCuenta().getIdCuenta(), ejercicioViejo.getTEntidad().getIdEntidad(), ejercicioViejo.getIdEjercicio(), this.estructuraPatrimonio.getTCuenta().getCodigoCuenta().split("-")[0]);

                for (TCuenta CuentasPatrimonio : CuentasPatrimonios) {

                    saldoDebe = this.cuentaBo.saldoCuentaCierre(CuentasPatrimonio.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Debe");
                    saldoHaber = this.cuentaBo.saldoCuentaCierre(CuentasPatrimonio.getIdCuenta(), ejercicioViejo.getIdEjercicio(), "Haber");
                    detallePartidaDepre = new TDetallePartida();
                    detallePartidaDepre.setTCuenta(CuentasPatrimonio);

                    if (saldoDebe.compareTo(saldoHaber) == 1) {

                        detallePartidaDepre.setTipoSaldoDetallePartida("Debe");
                        detallePartidaDepre.setSaldoDetallePartida(saldoDebe.subtract(saldoHaber));
                        listaDetallePartida.add(detallePartidaDepre);
                    } else if (saldoDebe.compareTo(saldoHaber) == -1) {

                        detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
                        detallePartidaDepre.setSaldoDetallePartida(saldoHaber.subtract(saldoDebe));
                        listaDetallePartida.add(detallePartidaDepre);
                    }

                }

                if (this.pasarSaldos) {
                    /* Creando el nuevo ejercicio, los datos ya fueron cargado en el metodo setIdEjerCierre*/
                    this.ejercicioBo.create(ejercicio);

                    /*creando el nuevo periodo que corresponde a Enero para el nuevo Ejercicio*/
                    this.periodoNuevo.setTEjercicio(ejercicio);
                    this.periodoNuevo.setEstadoPeriodo(true);
                    this.periodoBo.create(periodoNuevo);

                    auxBitacora = new TBitacora();
                    auxBitacora.setTableBitacora("t_ejercicio, t_periodo");
                    auxBitacora.setAccionBitacora("Agregar ejercicio y periodo");
                    auxBitacora.setDatosBitacora("Ejercicio:" + ejercicio.getAhoEjercicio()
                            + ", Periodo:" + periodoNuevo.getMesPeriodo() + ", Entidad:" + this.idEntidad);
                    auxBitacora.setIdTableBitacora(ejercicio.getIdEjercicio());
                    auxBitacora.setHoraBitacora(new Date());
                    auxBitacora.setFechaBitacora(new Date());
                    request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    // Get the loginBean from session attribute
                    loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
                    auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
                    bitacoraBo.create(auxBitacora);


                    /*Inserto la lista de cuentas para Activo*/
                    this.estructuraActivo.setTEjercicio(ejercicio);
                    this.estructuraBo.create(this.estructuraActivo);

                    /*Inserto la lista de cuentas para Pasivo*/
                    this.estructuraPasivo.setTEjercicio(ejercicio);
                    this.estructuraBo.create(this.estructuraPasivo);

                    /*Inserto la lista de cuentas para Capital*/
                    this.estructuraPatrimonio.setTEjercicio(ejercicio);
                    this.estructuraBo.create(this.estructuraPatrimonio);

                    for (TEstructura listaEstructuraIngreso : listaEstructuraIngresos) {
                        listaEstructuraIngreso.setTEjercicio(ejercicio);
                        this.estructuraBo.create(listaEstructuraIngreso);
                    }

                    for (TEstructura listaEstructuraCost : listaEstructuraCostos) {
                        listaEstructuraCost.setTEjercicio(ejercicio);
                        this.estructuraBo.create(listaEstructuraCost);
                    }

                    for (TEstructura listaEstructuraGast : listaEstructuraGastos) {
                        listaEstructuraGast.setTEjercicio(ejercicio);
                        this.estructuraBo.create(listaEstructuraGast);
                    }

                    for (TEstructura listaEstructuraOtIn : listaEstructuraOtrosIngresos) {
                        listaEstructuraOtIn.setTEjercicio(ejercicio);
                        this.estructuraBo.create(listaEstructuraOtIn);
                    }

                    for (TEstructura listaEstructuraOtroGas : listaEstructuraOtrosGastos) {
                        listaEstructuraOtroGas.setTEjercicio(ejercicio);
                        this.estructuraBo.create(listaEstructuraOtroGas);
                    }

                    this.rentaEstructura.setTEjercicio(ejercicio);
                    this.estructuraBo.create(this.rentaEstructura);

                    this.ReservaEstructura.setTEjercicio(ejercicio);
                    this.estructuraBo.create(this.ReservaEstructura);

                    this.utilidadEstructura.setTEjercicio(ejercicio);
                    this.estructuraBo.create(this.utilidadEstructura);

                    if (!listaDetallePartida.isEmpty()) {

                        this.partida = new TPartida();
                        this.partida.setFechaPartida(new Date());
                        this.partida.setConceptoPartida("Inicio de operaciones del ejercicio " + this.ejercicio.getAhoEjercicio());
                        this.partida.setEstadoPartida(true);
                        /*Directamente es la partida Numero 1*/
                        this.partida.setNumeroPartida(1);
                        this.partida.setTPeriodo(periodoNuevo);
                        this.partidaBo.create(this.partida);

                        /*Registrando el Detalle de Partidas*/
                        for (TDetallePartida tDetallePartida : listaDetallePartida) {
                            tDetallePartida.setTPartida(partida);
                            this.detallePartidaBo.create(tDetallePartida);
                        }

                        auxBitacora = new TBitacora();
                        auxBitacora.setTableBitacora("t_partida");
                        auxBitacora.setAccionBitacora("Agregar partida");
                        auxBitacora.setDatosBitacora("Numero:" + partida.getNumeroPartida()
                                + ", Concepto:" + partida.getConceptoPartida() + ", Entidad:" + this.idEntidad);
                        auxBitacora.setIdTableBitacora(partida.getIdPartida());
                        auxBitacora.setHoraBitacora(new Date());
                        auxBitacora.setFechaBitacora(new Date());
                        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                        // Get the loginBean from session attribute
                        loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
                        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
                        bitacoraBo.create(auxBitacora);

                    }

                }

                if (!listaDetallePartida.isEmpty()) {

                    this.partida = new TPartida();
                    this.partida.setNumeroPartida(this.partidaBo.numeroPartida(this.ejercicioViejo.getIdEjercicio()));
                    this.partida.setFechaPartida(new Date());
                    this.partida.setConceptoPartida("Cierre de operaciones del ejercicio " + this.ejercicioViejo.getAhoEjercicio());
                    this.partida.setEstadoPartida(false);
                    this.partida.setTPeriodo(periodoViejo);
                    this.partidaBo.create(this.partida);

                    /*Registrando el Detalle de Partidas*/
                    for (TDetallePartida tDetallePartida : listaDetallePartida) {
                        tDetallePartida.setTPartida(partida);

                        if (tDetallePartida.getTipoSaldoDetallePartida().equals("Debe")) {
                            tDetallePartida.setTipoSaldoDetallePartida("Haber");
                        } else {
                            tDetallePartida.setTipoSaldoDetallePartida("Debe");
                        }
                        this.detallePartidaBo.create(tDetallePartida);
                    }

                    auxBitacora = new TBitacora();
                    auxBitacora.setTableBitacora("t_partida");
                    auxBitacora.setAccionBitacora("Agregar partida");
                    auxBitacora.setDatosBitacora("Numero:" + partida.getNumeroPartida()
                            + ", Concepto:" + partida.getConceptoPartida() + ", Entidad:" + this.idEntidad);
                    auxBitacora.setIdTableBitacora(partida.getIdPartida());
                    auxBitacora.setHoraBitacora(new Date());
                    auxBitacora.setFechaBitacora(new Date());
                    request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    // Get the loginBean from session attribute
                    loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
                    auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
                    bitacoraBo.create(auxBitacora);

                }

                this.enableShowData();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Periodo registrado correctamente.", ""));

            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El periodo no pudo ser registrado.", ex.getMessage()));
            }

        }

    }

}
