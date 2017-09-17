/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IEmpleadoBo;
import com.sigaf.Ibo.IPoliticaBo;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TPolitica;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
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
 * @author Genovés
 */
@Named(value = "reporteConfiguracionBean")
@RequestScoped
@ManagedBean
public class ReporteConfiguracionBean extends Actividad {

    /**
     * Creates a new instance of ReporteConfiguracion
     */
    private Integer idReporte;
    private Integer idTipoCredito;
    private Integer tipoCredito;
    private Integer idEmpleadoAux;
    private IPoliticaBo ipoliticaBo;
    private List<TPolitica> listaPolitica;
    private IEmpleadoBo iempleadoBo;
    private List<TEmpleado> listaEmpleado;
    private Boolean estado;
    private IBitacoraBo bitacoraBo;

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(Integer tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public IPoliticaBo getIpoliticaBo() {
        return ipoliticaBo;
    }

    public void setIpoliticaBo(IPoliticaBo ipoliticaBo) {
        this.ipoliticaBo = ipoliticaBo;
    }

    public List<TPolitica> getListaPolitica() {
        return listaPolitica;
    }

    public void setListaPolitica(List<TPolitica> listaPolitica) {
        this.listaPolitica = listaPolitica;
    }

    public Integer getIdEmpleadoAux() {
        return idEmpleadoAux;
    }

    public void setIdEmpleadoAux(Integer idEmpleadoAux) {
        this.idEmpleadoAux = idEmpleadoAux;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public Integer getIdTipoCredito() {
        return idTipoCredito;
    }

    public void setIdTipoCredito(Integer idTipoCredito) {
        this.idTipoCredito = idTipoCredito;
    }

    public IEmpleadoBo getIempleadoBo() {
        return iempleadoBo;
    }

    public void setIempleadoBo(IEmpleadoBo iempleadoBo) {
        this.iempleadoBo = iempleadoBo;
    }

    public List<TEmpleado> getListaEmpleado() {
        this.listaEmpleado = this.iempleadoBo.listTEmpleado();
        return listaEmpleado;
    }

    public void setListaEmpleado(List<TEmpleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
    }

    public void init() {
        idReporte = 0;
        this.idEmpleadoAux = 0;
        this.listaEmpleado = new ArrayList<>();
    }

    public void llamarTipoReporte() {
        if (this.idReporte == 1) {
            try {
                this.generarCargos();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 2) {
            try {
                this.generarAreas();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 3) {
            
            System.out.println(this.idEmpleadoAux);
            try {
                this.generarEmpleados();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 4) {
            try {
                this.generarPoliticas();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
          if (this.idReporte == 6) {
            try {
                this.generarPoliticasVigentes();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 5) {
            try {
                this.generarPerfil();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void llamarTipoReportePDF() {
        if (this.idReporte == 1) {
            try {
                this.generarCargosPDF();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 2) {
            try {
                this.generarAreasPDF();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 3) {
            try {
                this.generarEmpleadosPDF();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 4) {
            try {
                this.generarPoliticasPDF();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
              if (this.idReporte == 6) {
            try {
                this.generarPoliticasVigentesPDF();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 5) {
            try {
                this.generarPerfilPDF();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void generarCargos() throws Exception {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        if (this.idEmpleadoAux == 1) {
            parametros.put("estado", 1);
        }
        if (this.idEmpleadoAux == 2) {
            parametros.put("estado", 0);
        }
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/cargosActivos.jasper"));
        if (this.idEmpleadoAux == 0) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/cargosTodos.jasper"));
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
        auxBitacora.setTableBitacora("t_cargo");
        auxBitacora.setAccionBitacora("Generar reporte de cargos");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void generarEmpleados() throws Exception {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        if (this.idEmpleadoAux == 1) {
            parametros.put("estado", true);
        }
        if (this.idEmpleadoAux == 2) {
            parametros.put("estado", false);
        }
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/empleadosTodos.jasper"));
        if (this.idEmpleadoAux == 0) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/empleadosTodos.jasper"));
        }
         if (this.idEmpleadoAux !=0) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/empleadosActivos.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de empleados");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void generarPoliticas() throws Exception {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("idTipoCredito", this.idTipoCredito);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReportePolitica.jasper"));
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
        auxBitacora.setTableBitacora("t_politica");
        auxBitacora.setAccionBitacora("Generar reporte de políticas");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }
    
    public void generarPoliticasVigentes() throws Exception {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/politicasVigentesTodos.jasper"));
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
        auxBitacora.setTableBitacora("t_politica");
        auxBitacora.setAccionBitacora("Generar reporte de políticas");
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
        if (this.idEmpleadoAux == 1) {
            parametros.put("estado", true);
        }
        if (this.idEmpleadoAux == 2) {
            parametros.put("estado", false);
        }
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReporteAreasActivas.jasper"));
        if (this.idEmpleadoAux == 0) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReporteAreasTodas.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de áreas");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void generarPerfil() throws Exception {
        this.getConexion();
        Map<String, Object> parametro = new HashMap();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReportePerfil.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametro, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_perfil");
        auxBitacora.setAccionBitacora("Generar reporte de perfil");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void generarCargosPDF() throws Exception {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        if (this.idEmpleadoAux == 1) {
            parametros.put("estado", 1);
        }
        if (this.idEmpleadoAux == 2) {
            parametros.put("estado", 0);
        }
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/cargosActivos.jasper"));
        if (this.idEmpleadoAux == 0) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/cargosTodos.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Cargos.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cargo");
        auxBitacora.setAccionBitacora("Generar PDF de cargos");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void generarEmpleadosPDF() throws Exception {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        if (this.idEmpleadoAux == 1) {
            parametros.put("estado", true);
        }
        if (this.idEmpleadoAux == 2) {
            parametros.put("estado", false);
        }
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/empleadosActivos.jasper"));
        if (this.idEmpleadoAux == 0) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/empleadosTodos.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Empleados.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_empleado");
        auxBitacora.setAccionBitacora("Generar PDF de empleado");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void generarPoliticasPDF() throws Exception {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("idTipoCredito", this.idTipoCredito);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReportePolitica.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Politicas.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_politica");
        auxBitacora.setAccionBitacora("Generar PDF de política");
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
        if (this.idEmpleadoAux == 1) {
            parametros.put("estado", true);
        }
        if (this.idEmpleadoAux == 2) {
            parametros.put("estado", false);
        }
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReporteAreasActivas.jasper"));
        if (this.idEmpleadoAux == 0) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReporteAreasTodas.jasper"));
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
        auxBitacora.setAccionBitacora("Generar PDF de área");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void generarPerfilPDF() throws Exception {
        this.getConexion();
        Map<String, Object> parametro = new HashMap();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReportePerfil.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Perfil.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_entidad");
        auxBitacora.setAccionBitacora("Generar PDF de perfil");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }
    
     public void generarPoliticasVigentesPDF() throws Exception {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/politicasVigentesTodos.jasper"));
           JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Políticas vigentes.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_politica");
        auxBitacora.setAccionBitacora("Generar PDF de politicas vigentes");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }
    

}
