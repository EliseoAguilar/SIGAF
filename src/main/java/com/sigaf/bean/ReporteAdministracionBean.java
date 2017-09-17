/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IEjercicioBo;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TEjercicio;
import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
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

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;

/**
 *
 * @author Eliseo
 */
@ManagedBean
@RequestScoped
public class ReporteAdministracionBean extends Actividad {

    /**
     * Creates a new instance of ReporteContabilidadBean
     */
    private Integer idReporte;
    private Integer estadoReporte;
    private Integer idEntidad;
    private Date hoy;
    private IBitacoraBo bitacoraBo;

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }
    
    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public Integer getEstadoReporte() {
        return estadoReporte;
    }

    public void setEstadoReporte(Integer estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public Date getHoy() {
        return new Date();
    }

    public void setHoy(Date hoy) {
        this.hoy = hoy;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public void init() {
        idReporte = 0;
        this.estadoReporte = 0;
    }

    public void llamarTipoReporte() {
        if (this.estadoReporte == 1 || this.estadoReporte == 2 || this.estadoReporte == 0) {
            if (idReporte == 1) {
                try {
                    this.generarCooperativas();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (idReporte == 2) {
                try {
                    this.generarAreas();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (idReporte == 3) {
                try {
                    this.generarEmpleadosCooperativa();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (idReporte == 4) {
                try {
                    this.generarSocios();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void llamarTipoReportePDF() {

        if (this.estadoReporte == 1 || this.estadoReporte == 2 || this.estadoReporte == 0) {
            if (idReporte == 1) {
                try {
                    this.generarCooperativasPDF();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (idReporte == 2) {
                try {
                    this.generarAreasPDF();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (idReporte == 3) {
                try {
                    this.generarEmpleadosCooperativaPDF();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (idReporte == 4) {
                try {
                    this.generarSociosPDF();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public void generarCooperativas() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        if (this.estadoReporte == 1) {
            parametros.put("estado_entidad", true);
        } else {
            parametros.put("estado_entidad", false);
        }
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/cooperativas.jasper"));
        if (this.estadoReporte == 0) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/cooperativasTodas.jasper"));
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
        auxBitacora.setTableBitacora("t_entidad");
        auxBitacora.setAccionBitacora("Generar reporte Cooperativas");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void generarSocios() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();

        System.out.println(this.estadoReporte);
        System.out.println(this.idEntidad);
        if (this.estadoReporte == 1) {
            parametros.put("estado", true);
        } else {
            parametros.put("estado", false);
        }
        parametros.put("id_entidad", this.idEntidad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/socios.jasper"));
        if (this.estadoReporte == 1 || this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/socios.jasper"));
        }
        if (this.estadoReporte == 0) {
            System.out.println(this.estadoReporte);
            System.out.println(this.idEntidad);
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/sociosTodos.jasper"));
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
        auxBitacora.setTableBitacora("t_empleados");
        auxBitacora.setAccionBitacora("Generar reporte socios");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void generarAreas() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        if (this.estadoReporte == 1) {
            parametros.put("estado", true);
        } else {
            parametros.put("estado", false);
        }
        parametros.put("id_entidad", this.idEntidad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/areasCooperativas.jasper"));
        if (this.estadoReporte == 1 || this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/areasCooperativas.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/areasCooperativasTodas.jasper"));
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
        auxBitacora.setTableBitacora("t_area");
        auxBitacora.setAccionBitacora("Generar reporte áreas");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void generarEmpleadosCooperativa() throws Exception {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        if (this.estadoReporte == 1) {
            parametros.put("estado", true);
        } else {
            parametros.put("estado", false);
        }
        parametros.put("id_entidad", this.idEntidad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/empleadosActivos.jasper"));
        if (this.estadoReporte == 0) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/empleadosTodos.jasper"));
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
        auxBitacora.setTableBitacora("t_empleado");
        auxBitacora.setAccionBitacora("Generar reporte empleados");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void generarCooperativasPDF() throws Exception {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        if (this.estadoReporte == 1) {
            parametros.put("estado_entidad", true);
        } else {
            parametros.put("estado_entidad", false);
        }
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/cooperativas.jasper"));
        if (this.estadoReporte == 0) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/cooperativasTodas.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Cooperativas.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_entidad");
        auxBitacora.setAccionBitacora("Generar PDF Cooperativas");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void generarSociosPDF() throws Exception {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        System.out.println(this.estadoReporte);
        System.out.println(this.idEntidad);
        if (this.estadoReporte == 1) {
            parametros.put("estado", true);
        } else {
            parametros.put("estado", false);
        }
        parametros.put("id_entidad", this.idEntidad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/socios.jasper"));
        if (this.estadoReporte == 1 || this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/socios.jasper"));
        }
        if (this.estadoReporte == 0) {
            System.out.println(this.estadoReporte);
            System.out.println(this.idEntidad);
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/sociosTodos.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Socios.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_empleado");
        auxBitacora.setAccionBitacora("Generar PDF socios");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void generarAreasPDF() throws Exception {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        if (this.estadoReporte == 1) {
            parametros.put("estado", true);
        } else {
            parametros.put("estado", false);
        }
        parametros.put("id_entidad", this.idEntidad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/areasCooperativas.jasper"));
        if (this.estadoReporte == 1 || this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/areasCooperativas.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/areasCooperativasTodas.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Areas.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_area");
        auxBitacora.setAccionBitacora("Generar PDF áreas");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void generarEmpleadosCooperativaPDF() throws Exception {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        if (this.estadoReporte == 1) {
            parametros.put("estado", true);
        } else {
            parametros.put("estado", false);
        }
        parametros.put("id_entidad", this.idEntidad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/empleadosActivos.jasper"));
        if (this.estadoReporte == 0) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/empleadosTodos.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Empleados cooperativas.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_entidad");
        auxBitacora.setAccionBitacora("Generar reporte Cooperativas");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

}
