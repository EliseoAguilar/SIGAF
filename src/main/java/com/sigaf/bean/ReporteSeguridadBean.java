/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IUsuarioBo;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TUsuario;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
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
@SessionScoped

public class ReporteSeguridadBean extends Actividad {

    /**
     * Creates a new instance of ReporteSeguridadBean
     */
    private IBitacoraBo bitacoraBo;

    private IUsuarioBo usuarioBo;

    private List<TUsuario> listaUsuarios;

    private Integer idReporte;

    private Integer idSubRep;

    private Date fechaActual;

    private Date fecha1;

    private Date fecha2;

    private Integer idUsuario;

    private String msgIdUsuario;

    private String msgIdReporte;

    private String msgFecha1;

    private String msgFecha2;

    private Boolean estadoReporte;

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public Boolean getEstadoReporte() {
        return estadoReporte;
    }

    public void setEstadoReporte(Boolean estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMsgIdUsuario() {
        return msgIdUsuario;
    }

    public void setMsgIdUsuario(String msgIdUsuario) {
        this.msgIdUsuario = msgIdUsuario;
    }

    public String getMsgIdReporte() {
        return msgIdReporte;
    }

    public void setMsgIdReporte(String msgIdReporte) {
        this.msgIdReporte = msgIdReporte;
    }

    public String getMsgFecha1() {
        return msgFecha1;
    }

    public void setMsgFecha1(String msgFecha1) {
        this.msgFecha1 = msgFecha1;
    }

    public String getMsgFecha2() {
        return msgFecha2;
    }

    public void setMsgFecha2(String msgFecha2) {
        this.msgFecha2 = msgFecha2;
    }

    public void init() {
        idReporte = 0;
        idSubRep = 0;
        estadoReporte = false;
         try {
            this.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public IUsuarioBo getUsuarioBo() {
        return usuarioBo;
    }

    public void setUsuarioBo(IUsuarioBo usuarioBo) {
        this.usuarioBo = usuarioBo;
    }

    public List<TUsuario> getListaUsuarios() {
        listaUsuarios = this.usuarioBo.listUsuario();
        return listaUsuarios;
    }

    public void setListaUsuarios(List<TUsuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Date getFechaActual() {
        this.fechaActual = new Date();
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    public Integer getIdSubRep() {
        return idSubRep;
    }

    public void setIdSubRep(Integer idSubRep) {
        this.idSubRep = idSubRep;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

  

    public void validarBitacoraRep() {

        this.estadoReporte = true;

        if (idReporte == 0) {
            this.msgIdReporte = "El tipo de reporte es requerido";
            this.estadoReporte = false;
        } else {
            this.msgIdReporte = "";
        }

        if (idUsuario == 0) {
            this.msgIdUsuario = "El usuario es requerido";
            this.estadoReporte = false;
        } else {
            this.msgIdUsuario = "";
        }

        if (fecha1 == null) {
            this.msgFecha1 = "La fecha de inicio es requerida";
            this.estadoReporte = false;
        } else {
            this.msgFecha1 = "";
        }

        if (fecha2 == null) {
            this.msgFecha2 = "La fecha de fin es requerida";
            this.estadoReporte = false;
        } else {
            this.msgFecha2 = "";
        }

    }

    public void llamarTipoReporte() {

        if (idReporte == 0) {
            this.msgIdReporte = "El tipo de reporte es requerido";
            this.estadoReporte = false;
        } else {
            this.msgIdReporte = "";
        }

        if (idReporte == 1) {
            try {
                if (idSubRep == 0) {
                    this.generarUsuariosTodos();
                } else {
                    this.generarUsuariosEstado();

                }
            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 2) {
            try {
                this.validarBitacoraRep();
                if (this.estadoReporte) {
                    this.generarUsuariosBitacora();

                }
            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void llamarTipoReportePDF() {

        if (idReporte == 0) {
            this.msgIdReporte = "El tipo de reporte es requerido";
            this.estadoReporte = false;
        } else {
            this.msgIdReporte = "";
        }

        if (idReporte == 1) {
            try {
                if (idSubRep == 0) {
                    this.generarUsuariosTodosPDF();
                } else {
                    this.generarUsuariosEstadoPDF();

                }
            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 2) {
            try {
                this.validarBitacoraRep();
                if (this.estadoReporte) {
                    this.generarUsuariosBitacoraPDF();

                }
            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void generarUsuariosTodos() throws Exception {

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/seguridad/UsuariosTodos.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null,this.getConn());

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();

        FacesContext.getCurrentInstance().responseComplete();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_usuario");
        auxBitacora.setAccionBitacora("Generar reporte de usuarios");
        auxBitacora.setDatosBitacora("---");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

        bitacoraBo.create(auxBitacora);

    }

    public void generarUsuariosEstado() throws Exception {

        Map<String, Object> estadoUsuario = new HashMap();

        if (idSubRep == 1) {
            estadoUsuario.put("estado_usuario", true);
        } else {
            estadoUsuario.put("estado_usuario", false);
        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/seguridad/UsuariosEstado.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), estadoUsuario, this.getConn());

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();

        FacesContext.getCurrentInstance().responseComplete();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_usuario");
        if (idSubRep == 1) {
            auxBitacora.setAccionBitacora("Generar reporte de usuarios activos");
        } else {
            auxBitacora.setAccionBitacora("Generar reporte de usuarios inactivos");
        }

        auxBitacora.setDatosBitacora("---");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

        bitacoraBo.create(auxBitacora);

    }

    public void generarUsuariosBitacora() throws Exception {

        Map<String, Object> estadoUsuario = new HashMap();

        TUsuario axuUsuario = null;

        for (TUsuario listaUsuario : listaUsuarios) {
            if (idUsuario == listaUsuario.getIdUsuario()) {
                axuUsuario = listaUsuario;
            }
        }

        estadoUsuario.put("id_usuario", idUsuario);

        estadoUsuario.put("fecha1", fecha1);

        estadoUsuario.put("fecha2", fecha2);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/seguridad/BitacoraUsuario.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), estadoUsuario, this.getConn());

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();

        FacesContext.getCurrentInstance().responseComplete();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_usuario");
        auxBitacora.setAccionBitacora("Generar reporte bitacora de usuario");
        auxBitacora.setDatosBitacora("Usuario: " + axuUsuario.getNombreUsuario()
                + ", Empleado:" + axuUsuario.getTEmpleado().getNombreEmpleado()
                + " " + axuUsuario.getTEmpleado().getApellidoEmpleado()
                + ", Fecha inicio: " + dateFormat.format(fecha1) + ", Fecha fin: " + dateFormat.format(fecha2));

        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

        bitacoraBo.create(auxBitacora);

    }

    public void generarUsuariosTodosPDF() throws Exception {

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/seguridad/UsuariosTodos.jasper"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, this.getConn());

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Usuarios.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

        FacesContext.getCurrentInstance().responseComplete();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_usuario");
        auxBitacora.setAccionBitacora("Descargar reporte de usuarios");
        auxBitacora.setDatosBitacora("---");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

        bitacoraBo.create(auxBitacora);

    }

    public void generarUsuariosEstadoPDF() throws Exception {

        Map<String, Object> estadoUsuario = new HashMap();

        if (idSubRep == 1) {
            estadoUsuario.put("estado_usuario", true);
        } else {
            estadoUsuario.put("estado_usuario", false);
        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/seguridad/UsuariosEstado.jasper"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), estadoUsuario, this.getConn());

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        if (idSubRep == 1) {
            response.addHeader("Content-disposition", "attachment; filename=UsuariosActivos.pdf");
        } else {
            response.addHeader("Content-disposition", "attachment; filename=UsuariosInactivos.pdf");
        }
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_usuario");
        if (idSubRep == 1) {
            auxBitacora.setAccionBitacora("Descargar reporte de usuarios activos");
        } else {
            auxBitacora.setAccionBitacora("Descargar reporte de usuarios inactivos");
        }

        auxBitacora.setDatosBitacora("---");
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

        bitacoraBo.create(auxBitacora);

    }

    public void generarUsuariosBitacoraPDF() throws Exception {

        Map<String, Object> estadoUsuario = new HashMap();

        TUsuario axuUsuario = null;

        for (TUsuario listaUsuario : listaUsuarios) {
            if (idUsuario == listaUsuario.getIdUsuario()) {
                axuUsuario = listaUsuario;
            }
        }

        estadoUsuario.put("id_usuario", idUsuario);

        estadoUsuario.put("fecha1", fecha1);

        estadoUsuario.put("fecha2", fecha2);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/seguridad/BitacoraUsuario.jasper"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), estadoUsuario, this.getConn());

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=BitacoraUsuario.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_usuario");
        auxBitacora.setAccionBitacora("Descargar reporte bitacora de usuario");
        auxBitacora.setDatosBitacora("Usuario: " + axuUsuario.getNombreUsuario()
                + ", Empleado:" + axuUsuario.getTEmpleado().getNombreEmpleado()
                + " " + axuUsuario.getTEmpleado().getApellidoEmpleado()
                + ", Fecha inicio: " + dateFormat.format(fecha1) + ", Fecha fin: " + dateFormat.format(fecha2));

        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

        bitacoraBo.create(auxBitacora);

    }

}
