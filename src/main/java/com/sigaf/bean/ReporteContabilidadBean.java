/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.ICuentaBo;
import com.sigaf.Ibo.IEjercicioBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Ibo.IEstructuraBo;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TEjercicio;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TEstructura;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Clase para generar reportes del modulo de contabilidad
 *
 * @author Eliseo
 */
@ManagedBean
@RequestScoped
public class ReporteContabilidadBean extends Actividad {

    private IBitacoraBo bitacoraBo;

    //Objeto capa de negocio para Cuentas 
    private ICuentaBo cuentaBo;

    //Objeto capa de negocio Ejercicios Contables 
    private IEjercicioBo ejercicioBo;

    // Lista  de Ejercicos Contables
    private List<TEjercicio> listaEjercicio;

    //Identificador de Ejercicio Contable
    private Integer idEjercicio;

    

    //Objeto capa de negocio Entidad
    private IEntidadBo entidadBo;

    //Identificador Reporte
    private Integer idReporte;

    //Identificador SubReporte
    private Integer idSubReporte;

    //Mensaje error reporte filtro Reporte
    private String msgReporte;

    //Mensaje error reporte filtro SubReporte
    private String msgSubReporte;

    /* Acceso para  datos  estructura*/
    private IEstructuraBo estructuraBo;

    /* objeto para la renta*/
    private TEstructura rentaEstructura;

    /* objeto para  la reserva legal*/
    private TEstructura ReservaEstructura;

    /*Para  Utilidad*/
    private TEstructura utilidadEstructura;

    /*Lista para ingresos */
    private TEstructura listaEstructuraIngresos;

    /*Lista para costos */
    private TEstructura listaEstructuraCostos;

    /*Lista para Gastos */
    private TEstructura listaEstructuraGastos;

    /*Lista para Otros  Gastos */
    private TEstructura listaEstructuraOtrosGastos;

    /*Lista para Otros  Ingresos */
    private TEstructura listaEstructuraOtrosIngresos;

    /* para estructura  Activos*/
    private TEstructura estructuraActivo;

    /* para estructura  Pasivos*/
    private TEstructura estructuraPasivo;

    /* para estructura  Capital*/
    private TEstructura estructuraPatrimonio;

   

    private String msgEjer;

    private BigDecimal valor1;
    private BigDecimal valor2;
    private BigDecimal valor3;
    private BigDecimal valor4;
    private BigDecimal valor5;
    private BigDecimal valor6;
    private BigDecimal valor7;
    private BigDecimal valor8;
    private BigDecimal valor9;
    private BigDecimal valor10;
    private BigDecimal valor11;
    private BigDecimal valor12;

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

    public String getMsgEjer() {
        return msgEjer;
    }

    public void setMsgEjer(String msgEjer) {
        this.msgEjer = msgEjer;
    }

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    /* para determinar si hay estructura de estado de resultado */
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

    public TEstructura getEstructuraPatrimonio() {
        return estructuraPatrimonio;
    }

    public void setEstructuraPatrimonio(TEstructura estructuraPatrimonio) {
        this.estructuraPatrimonio = estructuraPatrimonio;
    }

    public IEstructuraBo getEstructuraBo() {
        return estructuraBo;
    }

    public void setEstructuraBo(IEstructuraBo estructuraBo) {
        this.estructuraBo = estructuraBo;
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

    public TEstructura getUtilidadEstructura() {
        return utilidadEstructura;
    }

    public void setUtilidadEstructura(TEstructura utilidadEstructura) {
        this.utilidadEstructura = utilidadEstructura;
    }

    public ICuentaBo getCuentaBo() {
        return cuentaBo;
    }

    public void setCuentaBo(ICuentaBo cuentaBo) {
        this.cuentaBo = cuentaBo;
    }
    /**
     * Retorna Instancia
     *
     * @return msgReporte
     */
    public String getMsgReporte() {
        return msgReporte;
    }

    /**
     * Cambia Instancia
     *
     * @param msgReporte
     */
    public void setMsgReporte(String msgReporte) {
        this.msgReporte = msgReporte;
    }

    /**
     * Retorna Instancia
     *
     * @return msgSubReporte
     */
    public String getMsgSubReporte() {
        return msgSubReporte;
    }

    /**
     * Cambia Instancia
     *
     * @param msgSubReporte
     */
    public void setMsgSubReporte(String msgSubReporte) {
        this.msgSubReporte = msgSubReporte;
    }

    /**
     * Retorna Instancia
     *
     * @return idSubReporte
     */
    public Integer getIdSubReporte() {
        return idSubReporte;
    }

    /**
     * Cambia Instancia
     *
     * @param idSubReporte
     */
    public void setIdSubReporte(Integer idSubReporte) {
        this.idSubReporte = idSubReporte;
    }

    /**
     * Establece valores iniciales a los atributos idReporte = 0 idEntidad = 0
     * idSubReporte = 0
     */
    public void init() {

        idReporte = 0;
        idSubReporte = 0;
        try {
            this.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



    public void updateListaEjercicio() {
        this.listaEjercicio = ejercicioBo.listEjercicio(this.entidadSeleccionada.getIdEntidad());
    }





    /**
     * Retorna Instancia
     *
     * @return entidadBo
     */
    public IEntidadBo getEntidadBo() {
        return entidadBo;
    }

    /**
     * Cambia Instancia
     *
     * @param entidadBo
     */
    public void setEntidadBo(IEntidadBo entidadBo) {
        this.entidadBo = entidadBo;
    }

    /**
     * Retorna Instancia
     *
     * @return idEjercicio
     */
    public Integer getIdEjercicio() {
        return idEjercicio;
    }

    /**
     * Cambia Instancia
     *
     * @param idEjercicio
     */
    public void setIdEjercicio(Integer idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    /**
     * Retorna Instancia
     *
     * @return listaEjercicio
     */
    public List<TEjercicio> getListaEjercicio() {
        this.listaEjercicio = ejercicioBo.listEjercicio(this.entidadSeleccionada.getIdEntidad());
        return listaEjercicio;
    }

    /**
     * Cambia Instancia
     *
     * @param listaEjercicio
     */
    public void setListaEjercicio(List<TEjercicio> listaEjercicio) {

        this.listaEjercicio = listaEjercicio;
    }

    /**
     * Retorna Instancia
     *
     * @return ejercicioBo
     */
    public IEjercicioBo getEjercicioBo() {
        return ejercicioBo;
    }

    /**
     * Cambia Instancia
     *
     * @param ejercicioBo
     */
    public void setEjercicioBo(IEjercicioBo ejercicioBo) {
        this.ejercicioBo = ejercicioBo;
    }

    /**
     * Retorna Instancia
     *
     * @return idReporte
     */
    public Integer getIdReporte() {
        return idReporte;
    }

    /**
     * Cambia Instancia
     *
     * @param idReporte
     */
    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    /**
     * Crea una conexion con la base de datos para generar reportes
     *
     * @return conn
     * @throws SQLException
     */
    /**
     *
     */
    public void llamarTipoReporte(Integer type) {

        Boolean estadoReporte;

        estadoReporte = true;

        if (idReporte == 0) {
            this.msgReporte = "Tipo de reporte requerido";
            estadoReporte = false;
        } else {
            this.msgReporte = "";
        }

        if (idReporte > 4) {

            if (idEjercicio == 0) {
                this.msgEjer = "Ejercicio requerido";
                estadoReporte = false;
            } else {
                this.msgEjer = "";
            }
        }

        if (estadoReporte) {

            if (idReporte == 1) {
                try {
                    if (idSubReporte == 0) {
                        this.generarProveedores(type);
                    } else {
                        this.generarProveedoresEstado(type);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 2) {
                try {
                    if (idSubReporte == 0) {
                        this.generarTipoActivos(type);
                    } else {
                        this.generarTipoActivosEstado(type);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 3) {
                try {
                    if (idSubReporte == 0) {
                        this.generarActivos(type);
                    } else {
                        this.generarActivosEstado(type);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 4) {
                try {
                    if (idSubReporte == 0) {
                        this.generarCatalogo(type);
                    } else {
                        this.generarCatalogoEstado(type);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 5) {
                try {
                    this.generarLibro(type);
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 6) {
                try {
                    this.generarLibroMayor(type);
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 7) {
                try {
                    this.generarBalaza(type);
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 8) {
                try {
                    this.generarBalanceGenral(type);
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 9) {
                try {
                    this.generarEstadoResultado(type);
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    /**
     *
     * @throws Exception
     */
    public void generarProveedores(Integer type) throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.entidadSeleccionada.getIdEntidad());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/ReporteProveedoresGeneral.jasper"));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proveedor");

        if (type.equals(1)) {

            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, this.getConn());
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            auxBitacora.setAccionBitacora("Generar reporte de proveedores");
        } else {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), idEntidad, this.getConn());
            response.addHeader("Content-disposition", "attachment; filename=Proveedores.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();

            auxBitacora.setAccionBitacora("Descargar reporte de proveedores");
        }

        FacesContext.getCurrentInstance().responseComplete();

        auxBitacora.setDatosBitacora("Entidad:" + this.entidadSeleccionada.getNombreEntidad());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

    }

    /**
     *
     * @throws Exception
     */
    public void generarProveedoresEstado(Integer type) throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.entidadSeleccionada.getIdEntidad());

        if (this.idSubReporte == 1) {
            idEntidad.put("estado", true);

        } else {
            idEntidad.put("estado", false);

        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/ReporteProveedoresGeneralEstado.jasper"));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proveedor");

        if (type == 1) {

            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, this.getConn());

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            if (this.idSubReporte == 1) {

                auxBitacora.setAccionBitacora("Generar reporte de proveedores activos");
            } else {

                auxBitacora.setAccionBitacora("Generar reporte de proveedores inactivos");
            }
        } else {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), idEntidad, this.getConn());

            if (this.idSubReporte == 1) {
                response.addHeader("Content-disposition", "attachment; filename=ProveedoresActivos.pdf");
                auxBitacora.setAccionBitacora("Descargar reporte de proveedores activos");
            } else {
                response.addHeader("Content-disposition", "attachment; filename=ProveedoresInactivos.pdf");
                auxBitacora.setAccionBitacora("Descargar reporte de proveedores inactivos");
            }

            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();

        }

        FacesContext.getCurrentInstance().responseComplete();

        auxBitacora.setDatosBitacora("Entidad:" + this.entidadSeleccionada.getNombreEntidad());

        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

    }

    /**
     *
     * @throws Exception
     */
    public void generarTipoActivos(Integer type) throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.entidadSeleccionada.getIdEntidad());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/ReporteTiposActivos.jasper"));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_tipo_activo");

        if (type == 1) {

            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, this.getConn());

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
            auxBitacora.setAccionBitacora("Generar reporte de tipos de activos");
        } else {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), idEntidad, this.getConn());
            response.addHeader("Content-disposition", "attachment; filename=TiposActivosFijos.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            auxBitacora.setAccionBitacora("Descargar reporte de tipos de activos");
        }

        FacesContext.getCurrentInstance().responseComplete();

        auxBitacora.setDatosBitacora("Entidad:" + this.entidadSeleccionada.getNombreEntidad());

        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

    }

    /**
     *
     * @throws Exception
     */
    public void generarTipoActivosEstado(Integer type) throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.entidadSeleccionada.getIdEntidad());

        if (this.idSubReporte == 1) {
            idEntidad.put("estado", true);

        } else {
            idEntidad.put("estado", false);

        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/ReporteTiposActivosEstado.jasper"));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_tipo_activo");

        if (type == 1) {

            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, this.getConn());

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            if (this.idSubReporte == 1) {
                auxBitacora.setAccionBitacora("Generar reporte de tipos de activos activos");
            } else {
                auxBitacora.setAccionBitacora("Generar reporte de tipos de activos inactivos");
            }

        } else {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), idEntidad, this.getConn());

            if (this.idSubReporte == 1) {
                response.addHeader("Content-disposition", "attachment; filename=TiposActivosFijosActivos.pdf");
                auxBitacora.setAccionBitacora("Descargar reporte de tipos de activos inactivos");
            } else {
                response.addHeader("Content-disposition", "attachment; filename=TiposActivosFijosInactivos.pdf");
                auxBitacora.setAccionBitacora("Descargar reporte de tipos de activos inactivos");
            }

            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
        }

        FacesContext.getCurrentInstance().responseComplete();

        auxBitacora.setDatosBitacora("Entidad:" + this.entidadSeleccionada.getNombreEntidad());

        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

    }

    /**
     *
     * @throws Exception
     */
    public void generarActivos(Integer type) throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.entidadSeleccionada.getIdEntidad());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/reporteActivosFijos.jasper"));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_activo_fijo");

        if (type == 1) {

            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, this.getConn());
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
            auxBitacora.setAccionBitacora("Generar reporte de activos fijos");

        } else {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), idEntidad, this.getConn());
            response.addHeader("Content-disposition", "attachment; filename=ActivosFijos.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            auxBitacora.setAccionBitacora("Descargar reporte de activos fijos");
        }

        FacesContext.getCurrentInstance().responseComplete();

        auxBitacora.setDatosBitacora("Entidad:" + this.entidadSeleccionada.getNombreEntidad());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

    }

    /**
     *
     * @throws Exception
     */
    public void generarActivosEstado(Integer type) throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.entidadSeleccionada.getIdEntidad());

        if (this.idSubReporte == 1) {
            idEntidad.put("estado", "Activo");

        } else {
            idEntidad.put("estado", "Inactivo");

        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/reporteActivosFijosEstado.jasper"));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_activo_fijo");

        if (type == 1) {

            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, this.getConn());

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            if (this.idSubReporte == 1) {

                auxBitacora.setAccionBitacora("Generar reporte de activos fijos activos");
            } else {

                auxBitacora.setAccionBitacora("Generar reporte de activos fijos inactivos");
            }

        } else {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), idEntidad, this.getConn());

            if (this.idSubReporte == 1) {
                response.addHeader("Content-disposition", "attachment; filename=ActivosFijosActivos.pdf");
                auxBitacora.setAccionBitacora("Descargar reporte de activos fijos activos");
            } else {
                response.addHeader("Content-disposition", "attachment; filename=ActivosFijosInactivos.pdf");
                auxBitacora.setAccionBitacora("Descargar reporte de activos fijos inactivos");
            }

            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();

        }

        FacesContext.getCurrentInstance().responseComplete();

        auxBitacora.setDatosBitacora("Entidad:" + this.entidadSeleccionada.getNombreEntidad());

        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

    }

    /**
     *
     * @throws Exception
     */
    public void generarCatalogo(Integer type) throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.entidadSeleccionada.getIdEntidad());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/CatalogoCuentas.jasper"));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cuenta");

        if (type == 1) {

            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, this.getConn());

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            auxBitacora.setAccionBitacora("Generar reporte de catalogo de cuentas");

        } else {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), idEntidad, this.getConn());
            response.addHeader("Content-disposition", "attachment; filename=CatalogoCuentas.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();

            auxBitacora.setAccionBitacora("Generar reporte de catalogo de cuentas");
        }

        FacesContext.getCurrentInstance().responseComplete();

        auxBitacora.setDatosBitacora("Entidad:" + this.entidadSeleccionada.getNombreEntidad());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

    }

    /**
     *
     * @throws Exception
     */
    public void generarCatalogoEstado(Integer type) throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.entidadSeleccionada.getIdEntidad());

        if (this.idSubReporte == 1) {
            idEntidad.put("estado", true);

        } else {
            idEntidad.put("estado", false);

        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/CatalogoCuentasEstado.jasper"));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cuenta");

        if (type == 1) {

            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, this.getConn());

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            if (this.idSubReporte == 1) {
                auxBitacora.setAccionBitacora("Generar reporte de catalogo de cuentas activas");
            } else {
                auxBitacora.setAccionBitacora("Generar reporte de catalogo de cuentas inactivas");
            }

        } else {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), idEntidad, this.getConn());

            if (this.idSubReporte == 1) {
                response.addHeader("Content-disposition", "attachment; filename=CatalogoCuentasActivas.pdf");
                auxBitacora.setAccionBitacora("Descargar reporte de catalogo de cuentas activas");
            } else {
                response.addHeader("Content-disposition", "attachment; filename=CatalogoCuentasInactivas.pdf");
                auxBitacora.setAccionBitacora("Descargar reporte de catalogo de cuentas inactivas");
            }

            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
        }

        FacesContext.getCurrentInstance().responseComplete();

        auxBitacora.setDatosBitacora("Entidad:" + this.entidadSeleccionada.getNombreEntidad());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);
    }

    /**
     *
     * @throws Exception
     */
    public void generarLibro(Integer type) throws Exception {

        Map<String, Object> parametros = new HashMap();

        parametros.put("idEntidad", this.entidadSeleccionada.getIdEntidad());

        parametros.put("idEjercicio", this.idEjercicio);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/LibroDiario.jasper"));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("---");

        if (type == 1) {

            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            auxBitacora.setAccionBitacora("Generar reporte de libro diario");

        } else {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
            response.addHeader("Content-disposition", "attachment; filename=LibroDiario.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();

            auxBitacora.setAccionBitacora("Descargar reporte de libro diario");
        }

        FacesContext.getCurrentInstance().responseComplete();

        auxBitacora.setDatosBitacora("Entidad:" + this.entidadSeleccionada.getNombreEntidad() + ", Ejercicio:" + this.idEjercicio);
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

    }

    /**
     *
     * @throws Exception
     */
    public void generarLibroMayor(Integer type) throws Exception {

        Map<String, Object> parametros = new HashMap();

        parametros.put("idEntidad", this.entidadSeleccionada.getIdEntidad());

        parametros.put("idEjercicio", this.idEjercicio);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/LibroMayor.jasper"));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("---");

        if (type == 1) {

            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            auxBitacora.setAccionBitacora("Generar reporte de libro mayor");

        } else {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
            response.addHeader("Content-disposition", "attachment; filename=LibroMayor.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();

            auxBitacora.setAccionBitacora("Descargar reporte de libro mayor");
        }

        FacesContext.getCurrentInstance().responseComplete();

        auxBitacora.setDatosBitacora("Entidad:" + this.entidadSeleccionada.getNombreEntidad() + ", Ejercicio:" + this.idEjercicio);

        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

    }

    /**
     *
     * @throws Exception
     */
    public void generarBalaza(Integer type) throws Exception {

        Map<String, Object> parametros = new HashMap();

        parametros.put("idEjercicio", this.idEjercicio);

        parametros.put("idEntidad", this.entidadSeleccionada.getIdEntidad());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/balanzaComprobacion.jasper"));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("---");

        if (type == 1) {

            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            auxBitacora.setAccionBitacora("Generar reporte de balanza de comprobación");

        } else {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
            response.addHeader("Content-disposition", "attachment; filename=BalazaDeComprobacion.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();

            auxBitacora.setAccionBitacora("Descargar reporte de balanza de comprobación");
        }

        FacesContext.getCurrentInstance().responseComplete();

        auxBitacora.setDatosBitacora("Entidad:" + this.entidadSeleccionada.getNombreEntidad() + ", Ejercicio:" + this.idEjercicio);

        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

    }

    public Boolean estadoResultado() {

        /* Accesando a datos de configuracion Estado de Resultado*/
        List<TEstructura> auxEst = this.estructuraBo.listEstructura(this.idEjercicio, 1);
       

        this.listaEstructuraCostos = null;
        this.listaEstructuraGastos = null;
        this.listaEstructuraIngresos = null;
        this.listaEstructuraOtrosGastos = null;
        this.listaEstructuraOtrosIngresos = null;
        this.rentaEstructura = null;
        this.ReservaEstructura = null;
        this.utilidadEstructura = null;

        if (!auxEst.isEmpty()) {

            for (TEstructura tEstructura : auxEst) {
                switch (tEstructura.getGrupoReporte()) {
                    case 1:
                        this.listaEstructuraIngresos = tEstructura;
                        break;
                    case 2:
                        this.listaEstructuraCostos = tEstructura;
                        break;
                    case 3:
                        this.listaEstructuraGastos = tEstructura;
                        break;
                    case 4:
                        this.listaEstructuraOtrosIngresos = tEstructura;
                        break;
                    case 5:
                        this.listaEstructuraOtrosGastos = tEstructura;
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

           
            BigDecimal debe;
            BigDecimal haber;
            /* 
            * Datos para el total de Ingresos
             */
            valor1 = BigDecimal.ZERO;
            

            debe = this.cuentaBo.saldoCuenta(listaEstructuraIngresos.getTCuenta().getIdCuenta(), idEjercicio, "Debe");
            haber = this.cuentaBo.saldoCuenta(listaEstructuraIngresos.getTCuenta().getIdCuenta(), idEjercicio, "Haber");

            if (listaEstructuraIngresos.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                valor1 = debe.subtract(haber);
            } else {
                valor1 = haber.subtract(debe);
            }
            valor1 = valor1.setScale(2, BigDecimal.ROUND_HALF_UP);
            

            /* 
            * Datos para el total de Costos
             */
          
            valor2 = BigDecimal.ZERO;
            debe = this.cuentaBo.saldoCuenta(listaEstructuraCostos.getTCuenta().getIdCuenta(), idEjercicio, "Debe");
            haber = this.cuentaBo.saldoCuenta(listaEstructuraCostos.getTCuenta().getIdCuenta(), idEjercicio, "Haber");

            if (listaEstructuraCostos.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                valor2 = debe.subtract(haber);
            } else {
                valor2 = haber.subtract(debe);
            }
            valor2 = valor2.setScale(2, BigDecimal.ROUND_HALF_UP);
            


            /* 
            * Datos para Utilidad Bruta
             */
            valor9 = valor1.subtract(valor2);
            
            valor9 = valor9.setScale(2, BigDecimal.ROUND_HALF_UP);

            /* 
            * Datos para el total de Gastos
             */
        
            valor3 = BigDecimal.ZERO;
            
            debe = this.cuentaBo.saldoCuenta(listaEstructuraGastos.getTCuenta().getIdCuenta(), idEjercicio, "Debe");
            haber = this.cuentaBo.saldoCuenta(listaEstructuraGastos.getTCuenta().getIdCuenta(), idEjercicio, "Haber");

            if (listaEstructuraGastos.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                valor3 = debe.subtract(haber);
            } else {
                valor3 = haber.subtract(debe);
            }
            valor3 = valor3.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            
            /* 
            * Datos para Utilidad de Operacion
             */
            valor10 = valor9.subtract(valor3);
           
            valor10 = valor10.setScale(2, BigDecimal.ROUND_HALF_UP);
   

            /* 
            * Datos para Otros Ingresos
            */
            
            valor4 = BigDecimal.ZERO;
             
            debe = this.cuentaBo.saldoCuenta(listaEstructuraOtrosIngresos.getTCuenta().getIdCuenta(), idEjercicio, "Debe");
            haber = this.cuentaBo.saldoCuenta(listaEstructuraOtrosIngresos.getTCuenta().getIdCuenta(), idEjercicio, "Haber");

            if (listaEstructuraOtrosIngresos.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                valor4 = debe.subtract(haber);
            } else {
                valor4 = haber.subtract(debe);
            }
            valor4 = valor4.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            

            /* 
            * Datos para Otros Gastos
             */
           valor5 = BigDecimal.ZERO;
             
           
            debe = this.cuentaBo.saldoCuenta(listaEstructuraOtrosGastos.getTCuenta().getIdCuenta(), idEjercicio, "Debe");
            haber = this.cuentaBo.saldoCuenta(listaEstructuraOtrosGastos.getTCuenta().getIdCuenta(), idEjercicio, "Haber");

            if (listaEstructuraOtrosGastos.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                valor5 = debe.subtract(haber);
            } else {
                valor5 = haber.subtract(debe);
            }
            valor5 = valor5.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            /* 
            * Datos para Utilidad  antes de Impuestos
            
            */
            valor11 = valor10.add(valor4.subtract(valor5));
            
            valor11 = valor11.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            valor8 = valor11;
        
            valor6= BigDecimal.ZERO;
            valor7= BigDecimal.ZERO;
            valor12 = BigDecimal.ZERO;
       

            /*
            Si hay perdida no se paga inpuestos se hace ni reserva
             */
            
            if (valor8.compareTo(BigDecimal.ZERO) > 0) {
                
                if (this.rentaEstructura != null) {

                   
                    BigDecimal porRent;

                    if (valor8.compareTo(rentaEstructura.getValorRango()) <= 0) {
                        porRent = rentaEstructura.getPorMinEstructura().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
                    } else {
                        porRent = rentaEstructura.getPorMaxEstructura().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
                    }

                    valor6 = valor8.multiply(porRent);
                    valor6 = valor6.setScale(2, BigDecimal.ROUND_HALF_UP);
                    
                    valor8 = valor11.subtract(valor6);
                    valor12 = valor8;

                }

                if (this.ReservaEstructura != null) {

                    BigDecimal porRese;
                    porRese = ReservaEstructura.getPorMinEstructura().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
                    valor7 = valor8.multiply(porRese);
                    valor7 = valor7.setScale(2, BigDecimal.ROUND_HALF_UP);
                    valor8 = valor8.subtract(valor7);
                }
            }
        }

        return !auxEst.isEmpty();

    }

    public void generarEstadoResultado(Integer type) throws Exception {

        if (this.estadoResultado()) {

            Map<String, Object> parametros = new HashMap();

            parametros.put("idEntidad", this.entidadSeleccionada.getIdEntidad());

            parametros.put("idEjercicio", this.idEjercicio);

            parametros.put("valor1", this.valor1);

            parametros.put("valor2", this.valor2);

            parametros.put("valor3", this.valor3);

            parametros.put("valor4", this.valor4);

            parametros.put("valor5", this.valor5);

            parametros.put("valor6", this.valor6);

            parametros.put("valor7", this.valor7);

            parametros.put("valor8", this.valor8);

            parametros.put("valor9", this.valor9);

            parametros.put("valor10", this.valor10);

            parametros.put("valor11", this.valor11);

            parametros.put("valor12", this.valor12);

            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/estadoResultado.jasper"));

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("---");

            if (type == 1) {

                byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());

                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream outStream = response.getOutputStream();
                outStream.write(bytes, 0, bytes.length);
                outStream.flush();
                outStream.close();

                auxBitacora.setAccionBitacora("Generar reporte de estado de resultados");

            } else {

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
                response.addHeader("Content-disposition", "attachment; filename=EstadoResultado.pdf");
                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
                stream.close();

                auxBitacora.setAccionBitacora("Generar reporte de estado de resultados");
            }

            FacesContext.getCurrentInstance().responseComplete();

            auxBitacora.setDatosBitacora("Entidad:" + this.entidadSeleccionada.getNombreEntidad() + ", Ejercicio:" + this.idEjercicio);

            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

        }

    }

    public void generarBalanceGenral(Integer type) throws Exception {

        /* Accesando a datos de configuracion Balance General*/
        List<TEstructura> auxEst = this.estructuraBo.listEstructura(this.idEjercicio, 2);

        if (this.estadoResultado() && !auxEst.isEmpty()) {

            Map<String, Object> parametros = new HashMap();

            parametros.put("idEntidad", this.entidadSeleccionada.getIdEntidad());

            parametros.put("idEjercicio", this.idEjercicio);

            parametros.put("idCuentaResultado", this.utilidadEstructura.getTCuenta().getIdCuenta());

            if (this.valor8.compareTo(BigDecimal.ZERO) > 0) {
                parametros.put("debeResultado", BigDecimal.ZERO);
                parametros.put("haberResultado", this.valor8);
            } else if (this.valor8.compareTo(BigDecimal.ZERO) < 0) {
                parametros.put("debeResultado", this.valor8.multiply(valor8.multiply(new BigDecimal(-1))));
                parametros.put("haberResultado", BigDecimal.ZERO);
            }

            if (this.valor6.compareTo(BigDecimal.ZERO) > 0 && this.rentaEstructura != null) {
                parametros.put("idCuentaRenta", rentaEstructura.getTCuenta().getIdCuenta());

                if (rentaEstructura.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                    parametros.put("haberRenta", BigDecimal.ZERO);
                    parametros.put("debeRenta", this.valor6);
                } else {
                    parametros.put("debeRenta", BigDecimal.ZERO);
                    parametros.put("haberRenta", this.valor6);
                }

            }

            if (this.valor7.compareTo(BigDecimal.ZERO) > 0 && this.ReservaEstructura != null) {

                parametros.put("idCuentaReserva", ReservaEstructura.getTCuenta().getIdCuenta());

                if (ReservaEstructura.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                    parametros.put("haberReserva", BigDecimal.ZERO);
                    parametros.put("debeReserva", this.valor7);
                } else {
                    parametros.put("debeReserva", BigDecimal.ZERO);
                    parametros.put("haberReserva", this.valor7);
                }

            }


            /*
                * Parte donde se procesa el cierre y la apertura 
             */
            for (TEstructura tauxEstBalace : auxEst) {
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

            BigDecimal debe = this.cuentaBo.saldoCuenta(estructuraPasivo.getTCuenta().getIdCuenta(), idEjercicio, "Debe");
            BigDecimal haber = this.cuentaBo.saldoCuenta(estructuraPasivo.getTCuenta().getIdCuenta(), idEjercicio, "Haber");
            BigDecimal saldo;

            if (estructuraPasivo.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                saldo = debe.subtract(haber);
            } else {
                saldo = haber.subtract(debe);
            }

            debe = this.cuentaBo.saldoCuenta(estructuraPatrimonio.getTCuenta().getIdCuenta(), idEjercicio, "Debe");
            haber = this.cuentaBo.saldoCuenta(estructuraPatrimonio.getTCuenta().getIdCuenta(), idEjercicio, "Haber");

            if (estructuraPatrimonio.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                saldo = saldo.add(debe.subtract(haber));
            } else {
                saldo = saldo.add(haber.subtract(debe));
            }

            saldo = saldo.add(this.valor8);
            saldo = saldo.add(this.valor6);
            saldo = saldo.add(this.valor7);

            parametros.put("totalPasivoCapital", saldo);

            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/balanceGeneral.jasper"));

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("---");

            if (type == 1) {

                byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());

                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream outStream = response.getOutputStream();
                outStream.write(bytes, 0, bytes.length);
                outStream.flush();
                outStream.close();

                auxBitacora.setAccionBitacora("Generar reporte de balance general");

            } else {

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
                response.addHeader("Content-disposition", "attachment; filename=BalanceGeneral.pdf");
                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
                stream.close();

                auxBitacora.setAccionBitacora("Generar reporte de balance general");
            }

            FacesContext.getCurrentInstance().responseComplete();

            auxBitacora.setDatosBitacora("Entidad:" + this.entidadSeleccionada.getNombreEntidad() + ", Ejercicio:" + this.idEjercicio);

            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

        }

    }

}
