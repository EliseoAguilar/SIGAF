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

public class BitacoraBean extends Actividad {

    /**
     * Creates a new instance of BitacoraBean
     */
    private IBitacoraBo bitacoraBo;

    private IUsuarioBo usuarioBo;

    private List<TUsuario> listaUsuarios;

    private List<TBitacora> listaBitacora;

    private TBitacora bitacoraSelecionada;

    private Integer idUsuario;

    private TUsuario usuarioSelecionado;

    private Date fechaActual;

    private Date fecha1;

    private Date fecha2;

    private String msgIdUsuario;

    private String msgFecha1;

    private String msgFecha2;

    private Boolean estadoReporte;

    public TUsuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(TUsuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public String getMsgIdUsuario() {
        return msgIdUsuario;
    }

    public void setMsgIdUsuario(String msgIdUsuario) {
        this.msgIdUsuario = msgIdUsuario;
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

    public Boolean getEstadoReporte() {
        return estadoReporte;
    }

    public void setEstadoReporte(Boolean estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public void validarBitacoraRep() {

        this.estadoReporte = true;

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

    public TBitacora getBitacoraSelecionada() {
        return bitacoraSelecionada;
    }

    public void setBitacoraSelecionada(TBitacora bitacoraSelecionada) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_bitacora");
        auxBitacora.setAccionBitacora("Ver información de acción");
        auxBitacora.setDatosBitacora("Usuario: " + usuarioSelecionado.getNombreUsuario()
                + ", Empleado:" + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado()
                + ", Acción: " + bitacoraSelecionada.getAccionBitacora()
                + ", Tabla: " + bitacoraSelecionada.getTableBitacora()
                + ", Facha: " + dateFormat.format(bitacoraSelecionada.getFechaBitacora())
                + ", Hora: " + bitacoraSelecionada.getHoraBitacora());
        auxBitacora.setIdTableBitacora(  bitacoraSelecionada.getIdBitacora() );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

        bitacoraBo.create(auxBitacora);

        this.bitacoraSelecionada = bitacoraSelecionada;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<TBitacora> getListaBitacora() {
  
        return listaBitacora;
    }

    public void setListaBitacora(List<TBitacora> listaBitacora) {

        this.listaBitacora = listaBitacora;
    }

    public List<TUsuario> getListaUsuarios() {
        listaUsuarios = this.usuarioBo.listUsuario();
        return listaUsuarios;
    }

    public void setListaUsuarios(List<TUsuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
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

    public Date getFechaActual() {
        fechaActual = new Date();
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public IUsuarioBo getUsuarioBo() {
        return usuarioBo;
    }

    public void setUsuarioBo(IUsuarioBo usuarioBo) {
        this.usuarioBo = usuarioBo;
    }

    public void init() {

        this.enableShowData();
         try {
            this.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargaBitacora() {

        validarBitacoraRep();
        
        if (estadoReporte) {
            
              
            usuarioSelecionado = usuarioBo.getUsuario(idUsuario);
            
            this.listaBitacora = bitacoraBo.listBitacoraFechas(idUsuario, fecha1, fecha2);
          

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("---");
            auxBitacora.setAccionBitacora("Ver bitácora de usuario");
            auxBitacora.setDatosBitacora("Usuario: " + usuarioSelecionado.getNombreUsuario()
                    + ", Empleado:" + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                    + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado()
                    + ", Fecha inicio: " + dateFormat.format(fecha1) + ", Fecha fin: " + dateFormat.format(fecha2));
            auxBitacora.setIdTableBitacora(  usuarioSelecionado.getIdUsuario());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

            bitacoraBo.create(auxBitacora);

        } else {
            usuarioSelecionado = null;
        }

    }

    public String tipoUser(Integer tipo) {

        if (tipo != null) {
            switch (tipo) {
                case 1:
                    return "Contador";
                case 2:
                    return "Asesor de crédito";
                default:
                    return "Administrador";
            }
        } else {
            return "";
        }

    }



    public void generarUsuario() throws Exception {

        Map<String, Object> estadoUsuario = new HashMap();

        estadoUsuario.put("id_usuario", usuarioSelecionado.getIdUsuario());

        estadoUsuario.put("id_bitacora", bitacoraSelecionada.getIdBitacora());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/seguridad/BitacoraIndividual.jasper"));

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
        auxBitacora.setTableBitacora("t_bitacora");
        auxBitacora.setAccionBitacora("Generar reporte de acción");
        auxBitacora.setDatosBitacora("Usuario: " + usuarioSelecionado.getNombreUsuario()
                + ", Empleado:" + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado()
                + ", Acción: " + bitacoraSelecionada.getAccionBitacora()
                + ", Tabla: " + bitacoraSelecionada.getTableBitacora()
                + ", Facha: " + dateFormat.format(bitacoraSelecionada.getFechaBitacora())
                + ", Hora: " + bitacoraSelecionada.getHoraBitacora());
        auxBitacora.setIdTableBitacora( bitacoraSelecionada.getIdBitacora());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

        bitacoraBo.create(auxBitacora);

    }

    public void generarUsuarioPDF() throws Exception {

        Map<String, Object> estadoUsuario = new HashMap();

        estadoUsuario.put("id_usuario", usuarioSelecionado.getIdUsuario());

        estadoUsuario.put("id_bitacora", bitacoraSelecionada.getIdBitacora());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/seguridad/BitacoraIndividual.jasper"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), estadoUsuario, this.getConn());

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=AccionUsuario.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_bitacora");
        auxBitacora.setAccionBitacora("Descargar reporte de acción");
        auxBitacora.setDatosBitacora("Usuario: " + usuarioSelecionado.getNombreUsuario()
                + ", Empleado:" + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado()
                + ", Acción: " + bitacoraSelecionada.getAccionBitacora()
                + ", Tabla: " + bitacoraSelecionada.getTableBitacora()
                + ", Facha: " + dateFormat.format(bitacoraSelecionada.getFechaBitacora())
                + ", Hora: " + bitacoraSelecionada.getHoraBitacora());
        auxBitacora.setIdTableBitacora(  bitacoraSelecionada.getIdBitacora());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

        bitacoraBo.create(auxBitacora);

    }

}
