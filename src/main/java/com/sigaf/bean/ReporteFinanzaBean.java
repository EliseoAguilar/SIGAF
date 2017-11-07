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
import javax.faces.application.FacesMessage;
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
public class ReporteFinanzaBean extends Actividad {

    private Integer idReporte;
    private Integer estadoReporte;
    private Integer estadoMensual;
    private Date fechaInicial;
    private Date fechaFin;
    private Date fechaActual;

    public Date getFechaActual() {
        fechaActual = new Date();
        return fechaActual;
    }
    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }
    private IBitacoraBo bitacoraBo;

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public Integer getEstadoReporte() {
        return estadoReporte;
    }

    public void setEstadoReporte(Integer estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFin() {
        return fechaFin;
    }


    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getEstadoMensual() {
        return estadoMensual;
    }

    public void setEstadoMensual(Integer estadoMensual) {
        this.estadoMensual = estadoMensual;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public void init() {

        idReporte = 0;

    }

    public void llamarTipoReporte() {

        if (this.idReporte == 1) {
            try {
                this.generarClientesPersonas();
            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 2) {
            try {
                this.generarClientesCooperativas();
            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (idReporte == 3) {
            if (this.fechaInicial.compareTo(this.fechaFin) > 0) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                try {
                    this.generarSolicitudesCooperativas();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (idReporte == 4) {
            if (this.fechaInicial.compareTo(this.fechaFin) > 0) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                try {
                    this.generarSolicitudesPersonas();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (idReporte == 5) {
            if (this.fechaInicial.compareTo(this.fechaFin) > 0) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                try {
                    this.generarCreditosCooperativa();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (idReporte == 6) {
            if (this.fechaInicial.compareTo(this.fechaFin) > 0) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                try {
                    this.generarCreditosPersonas();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (idReporte == 7) {
            if (this.fechaInicial.compareTo(this.fechaFin) > 0) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                try {
                    this.generarAsesorias();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (idReporte == 8) {
            if (this.fechaInicial.compareTo(this.fechaFin) > 0) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                try {
                    this.generarCapacitaciones();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (idReporte == 9) {
            System.out.println(this.idReporte);
            if (this.fechaInicial.compareTo(this.fechaFin) > 0) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                try {
                    this.generarIncobrabilidad();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void llamarTipoReportePDF() {

        if (this.idReporte == 1) {
            try {
                this.generarClientesPersonasPDF();
            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 2) {
            try {
                this.generarClientesCooperativasPDF();
            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (idReporte == 3) {
            if (this.fechaInicial.compareTo(this.fechaFin) > 0) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                try {
                    this.generarSolicitudesCooperativasPDF();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (idReporte == 4) {
            if (this.fechaInicial.compareTo(this.fechaFin) > 0) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                try {
                    this.generarSolicitudesPersonasPDF();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (idReporte == 5) {
            if (this.fechaInicial.compareTo(this.fechaFin) > 0) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                try {
                    this.generarCreditosCooperativaPDF();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (idReporte == 6) {
            if (this.fechaInicial.compareTo(this.fechaFin) > 0) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                try {
                    this.generarCreditosPersonasPDF();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (idReporte == 7) {
            if (this.fechaInicial.compareTo(this.fechaFin) > 0) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                try {
                    this.generarAsesoriasPDF();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (idReporte == 9) {
            if (this.fechaInicial.compareTo(this.fechaFin) > 0) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                try {
                    this.generarIncobrabilidadPDF();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void generarClientesPersonas() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientesPersonas.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de clientes (Personas naturales)");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarClientesCooperativas() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientesCooperativas.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de clientes (Cooperativas)");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarAsesorias() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaInicial", this.fechaInicial);
        parametros.put("fechaFin", this.fechaFin);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/asesoriasEmpleado.jasper"));
        if (this.estadoReporte == 1) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/asesoriasEmpleado.jasper"));
        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/asesoriasEmpleado.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/asesoriasTodas.jasper"));
        }
        if (this.estadoReporte == 1) {
            parametros.put("estado", true);
        } else if (this.estadoReporte == 2) {
            parametros.put("estado", false);
        } else {
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
        auxBitacora.setAccionBitacora("Generar reporte de asesorias");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarCapacitaciones() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaInicial", this.fechaInicial);
        parametros.put("fechaFin", this.fechaFin);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/capacitacionesEmpleado.jasper"));
        if (this.estadoReporte == 1) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/capacitacionesEmpleado.jasper"));
        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/capacitacionesEmpleado.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/capacitacionesTodas.jasper"));
        }
        if (this.estadoReporte == 1){
            parametros.put("estado", true);
        } else if (this.estadoReporte == 2){
            parametros.put("estado", false);
        } else {
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
        auxBitacora.setAccionBitacora("Generar reporte de capacitaciones");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        


    }

    public void generarSolicitudesCooperativas() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaInicial", this.fechaInicial);
        parametros.put("fechaFinal", this.fechaFin);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesAprobadasCooperativas.jasper"));
        if (this.estadoReporte == 1) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesAprobadasCooperativas.jasper"));
        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesNoAprobadasCooperativas.jasper"));
        } else if (this.estadoReporte == 3) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesPendientesCooperativas.jasper"));
        } else if (this.estadoReporte == 4) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesCreadasCooperativas.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesAprobadasCooperativasTodas.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de solicitudes (Cooperativas)");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarSolicitudesPersonas() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaIncial", this.fechaInicial);
        parametros.put("fechaFin", this.fechaFin);
        System.out.println("entra");
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesAprobadasPersonas.jasper"));
        if (this.estadoReporte == 1) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesAprobadasPersonas.jasper"));
        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesNoAprobadasPersonas.jasper"));
        } else if (this.estadoReporte == 3) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesPendientesPersonas.jasper"));
        } else if (this.estadoReporte == 4) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesCreadasPersonas.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesAprobadasPersonasTodas.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de solicitudes (Personas naturales)");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarCreditosPersonas() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaInicial", this.fechaInicial);
        parametros.put("fechaFinal", this.fechaFin);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosFinalizadosPersona.jasper"));
        if (this.estadoReporte == 1) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosFinalizadosPersona.jasper"));
        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosAprobadosProcesoPersona.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosAprobadosEjecutandosePersona.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de créditos (Personas naturales)");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarCreditosCooperativa() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaInicial", this.fechaInicial);
        parametros.put("fechaFinal", this.fechaFin);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosFinalizadosCooperativa.jasper"));
        if (this.estadoReporte == 1) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosFinalizadosCooperativa.jasper"));
        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosAprobadosProcesoCooperativa.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosAprobadosEjecutandoseCooperativa.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de créditos (Cooperativa)");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarCatalogoPDF() throws Exception {

        this.getConexion();
        Map<String, Object> idEntidad = new HashMap();
        idEntidad.put("id_entidad", 1);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/CatalogoCuentas.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), idEntidad, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=jsfReporte.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarClientesPersonasPDF() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientesPersonas.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Clientes naturales.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
          TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de clientes (Personas naturales)");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarClientesCooperativasPDF() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/clientesCooperativas.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Clientes cooperativas.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
          TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de clientes (Cooperativas)");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarAsesoriasPDF() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaInicial", this.fechaInicial);
        parametros.put("fechaFinal", this.fechaFin);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/asesoriasEmpleado.jasper"));
        if (this.estadoReporte == 1) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/asesoriasEmpleado.jasper"));
        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/asesoriasEmpleado.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/asesoriasTodas.jasper"));
        }
        if (this.estadoReporte == 1) {
            parametros.put("estado", true);
        } else if (this.estadoReporte == 2) {
            parametros.put("estado", false);
        } else {
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Asesorias.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
          TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de asesorias");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarCapacitacionesPDF() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaInicial", this.fechaInicial);
        parametros.put("fechaFinal", this.fechaFin);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/capacitacionesEmpleado.jasper"));
        if (this.estadoReporte == 1) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/capacitacionesEmpleado.jasper"));
        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/capacitacionesEmpleado.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/capacitacionesTodas.jasper"));
        }
        if (this.estadoReporte == 1) {
            parametros.put("estado", true);
        } else if (this.estadoReporte == 2) {
            parametros.put("estado", false);
        } else {
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Capacitaciones.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
          TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de capacitaciones");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarSolicitudesCooperativasPDF() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaInicial", this.fechaInicial);
        parametros.put("fechaFinal", this.fechaFin);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesAprobadasCooperativas.jasper"));
        if (this.estadoReporte == 1) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesAprobadasCooperativas.jasper"));
        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesNoAprobadasCooperativas.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesAprobadasCooperativasTodas.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Solicitudes cooperativas.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
          TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de solicitudes (Cooperativas)");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarSolicitudesPersonasPDF() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaInicial", this.fechaInicial);
        parametros.put("fechaFinal", this.fechaFin);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesAprobadasPersonas.jasper"));
        if (this.estadoReporte == 1) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesAprobadasPersonas.jasper"));
        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesNoAprobadasPersonas.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/solicitudesAprobadasPersonasTodas.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Solicitudes personas naturales.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
          TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de solicitudes (Personas naturales)");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarCreditosPersonasPDF() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaInicial", this.fechaInicial);
        parametros.put("fechaFinal", this.fechaFin);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosFinalizadosPersona.jasper"));
        if (this.estadoReporte == 1) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosFinalizadosPersona.jasper"));
        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosAprobadosProcesoPersona.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosAprobadosEjecutandosePersona.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Creditos personas naturales.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
          TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de créditos (Personas naturales)");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarCreditosCooperativaPDF() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaInicial", this.fechaInicial);
        parametros.put("fechaFinal", this.fechaFin);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosFinalizadosCooperativa.jasper"));
        if (this.estadoReporte == 1) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosFinalizadosCooperativa.jasper"));
        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosAprobadosProcesoCooperativa.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosAprobadosEjecutandoseCooperativa.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Creditos cooperativas.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
          TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de créditos (Cooperativas)");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarIncobrabilidad() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaInicial", this.fechaInicial);
        parametros.put("fechaFinal", this.fechaFin);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosIncobrables.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de créditos incobrables");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);


    }

    public void generarIncobrabilidadPDF() throws Exception {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaInicial", this.fechaInicial);
        parametros.put("fechaFinal", this.fechaFin);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/creditosIncobrables.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Creditos incobrables.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proyecto");
        auxBitacora.setAccionBitacora("Descargar reporte de créditos incobrables");
        auxBitacora.setDatosBitacora(""
        );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

}
