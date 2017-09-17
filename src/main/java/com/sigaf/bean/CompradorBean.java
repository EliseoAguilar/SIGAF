/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.ICompradorBo;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TComprador;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

;

/**
 *
 * @author yonat
 */
@Named(value = "compradorBean")
@RequestScoped
@ManagedBean

public class CompradorBean extends Actividad {

    private TComprador Comprador;
    private TComprador CompradorSeleccionado;
    private List<TComprador> listaComprador;
    private ICompradorBo compradorBo;
    private IBitacoraBo bitacoraBo;
    private boolean estadoFormulario;
    private String msgNombres;
    private String msgApellidos;
    private String msgFecha;
    private String msgDui;
    private String msgNit;
    private String msgTipo;
    private String msgTelefono;
    private String msgMovil;
    private String duiAux;

    public String getDuiAux() {
        return duiAux;
    }

    public void setDuiAux(String duiAux) {
        this.duiAux = duiAux;
    }

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
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
    private String msgDireccion;

    public boolean isEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public String getMsgNombres() {
        return msgNombres;
    }

    public void setMsgNombres(String msgNombres) {
        this.msgNombres = msgNombres;
    }

    public String getMsgApellidos() {
        return msgApellidos;
    }

    public void setMsgApellidos(String msgApellidos) {
        this.msgApellidos = msgApellidos;
    }

    public String getMsgFecha() {
        return msgFecha;
    }

    public void setMsgFecha(String msgFecha) {
        this.msgFecha = msgFecha;
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

    public String getMsgTipo() {
        return msgTipo;
    }

    public void setMsgTipo(String msgTipo) {
        this.msgTipo = msgTipo;
    }
    
     public TComprador getComprador() {
        return Comprador;
    }

    public void setComprador(TComprador Comprador) {
        this.Comprador = Comprador;
    }

    public TComprador getCompradorSeleccionado() {
        return CompradorSeleccionado;
    }

    public void setCompradorSeleccionado(TComprador CompradorSeleccionado) {
        this.CompradorSeleccionado = CompradorSeleccionado;
    }

    public List<TComprador> getListaComprador() {
        return listaComprador;        
    }

    public void setListaComprador(List<TComprador> listaComprador) {
        this.listaComprador = listaComprador;
    }

    public ICompradorBo getCompradorBo() {
        return compradorBo;
    }

    public void setCompradorBo(ICompradorBo compradorBo) {
        this.compradorBo = compradorBo;
    }
    
    public void updateListaComprador(){
        
        this.listaComprador = compradorBo.listComprador();  
        
    }

    public void init() {
       
        this.Comprador = new TComprador();
        this.Comprador.setSexoComprador("Masculino");
        super.enableShowData();
    
    } 
    
    public void crear() {

        this.Comprador.setEstadoComprador(true);
        compradorBo.create(this.Comprador);
        
          TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_comprador");
        auxBitacora.setAccionBitacora("Agregar comprador");
        
            
        auxBitacora.setDatosBitacora("Nombres:" + this.Comprador.getNombresComprador()
                + ", Apellidos: " + this.Comprador.getApellidosComprador()                
                + ", DUI: " + this.Comprador.getDuiComprador()
               );
        
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.Comprador.getIdComprador());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        this.listaComprador = compradorBo.listComprador();
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comprador guardado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        limpiar();

    }

    public void modificar() {

        compradorBo.update(this.CompradorSeleccionado);
        
          TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_comprador");
        auxBitacora.setAccionBitacora("Modificar comprador");
        
            
        auxBitacora.setDatosBitacora("Nombres:" + this.CompradorSeleccionado.getNombresComprador()
                + ", Apellidos: " + this.CompradorSeleccionado.getApellidosComprador()                
                + ", DUI: " + this.CompradorSeleccionado.getDuiComprador()
               );
        
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.CompradorSeleccionado.getIdComprador());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comprador modificado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaComprador = compradorBo.listComprador();
        super.enableShowData();
        limpiar();
    }

    public void darDeBaja() {

        this.CompradorSeleccionado.setEstadoComprador(false);
        compradorBo.update(this.CompradorSeleccionado);
        
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_comprador");
        auxBitacora.setAccionBitacora("Dar de  baja comprador");           
        auxBitacora.setDatosBitacora("Nombres:" + this.CompradorSeleccionado.getNombresComprador()
                + ", Apellidos: " + this.CompradorSeleccionado.getApellidosComprador()                
                + ", DUI: " + this.CompradorSeleccionado.getDuiComprador()
               );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.CompradorSeleccionado.getIdComprador());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comprador dado de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaComprador = compradorBo.listComprador();
        super.enableShowData();

    }

    public void darDeAlta() {

        this.CompradorSeleccionado.setEstadoComprador(true);
        compradorBo.update(this.CompradorSeleccionado);
        
          TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_comprador");
        auxBitacora.setAccionBitacora("Dar de alta comprador");
        
            
        auxBitacora.setDatosBitacora("Nombres:" + this.CompradorSeleccionado.getNombresComprador()
                + ", Apellidos: " + this.CompradorSeleccionado.getApellidosComprador()                
                + ", DUI: " + this.CompradorSeleccionado.getDuiComprador()
               );
        
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.CompradorSeleccionado.getIdComprador());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comprador dado de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaComprador = compradorBo.listComprador();
        super.enableShowData();

    }

    public String verEstado(boolean estado) {
   
        if (estado) {
            return "Activo";
        } else {
            return "Inactivo";
        }
    
    }

    public void validarFormulario() {

        this.estadoFormulario = true;
        if (this.Comprador.getNombresComprador()=="") {
            this.msgNombres = "Debe introducir los nombres";
            this.estadoFormulario = false;
        } else {
            this.msgNombres = "";
        }
        if (this.Comprador.getApellidosComprador()=="") {
            this.msgApellidos = "Debe introducir los apellidos";
            this.estadoFormulario = false;
        } else {
            this.msgApellidos = "";
        }
        if (this.Comprador.getFechanacimientoComprador() == null) {
            this.msgFecha = "Debe introducir la fecha";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }
        if (this.Comprador.getDuiComprador()=="") {
            this.msgDui = "Debe introducir el DUI";
            this.estadoFormulario = false;
        } else if (this.compradorBo.getTCompradorCodigo(this.Comprador.getDuiComprador())) {
            this.estadoFormulario = false;
            this.msgDui = "El DUI introducido ya fue registrado";
        } else {
            this.msgDui = "";
        }
        if (this.Comprador.getNitComprador()=="") {
            this.msgNit = "Debe introducir el NIT";
            this.estadoFormulario = false;
        } else {
            this.msgNit = "";
        }
        if (this.Comprador.getTipoComprador() == "") {
            this.msgTipo = "Debe seleccionar el tipo";
            this.estadoFormulario = false;
        } else {
            this.msgTipo = "";
        }
        if (this.Comprador.getTelefonoComprador()=="") {
            this.msgTelefono = "Debe introducir el teléfono";
            this.estadoFormulario = false;
        } else {
            this.msgTelefono = "";
        }
        if (this.Comprador.getMovilComprador()=="") {
            this.msgMovil = "Debe introducir el móvil";
            this.estadoFormulario = false;
        } else {
            this.msgMovil = "";
        }
        if (this.Comprador.getDireccionComprador()=="") {
            this.msgDireccion = "Debe introducir la dirección";
            this.estadoFormulario = false;
        } else {
            this.msgDireccion = "";
        }

    }

    public void validarFormularioModificar() {
    
        this.estadoFormulario = true;
        if (this.CompradorSeleccionado.getNombresComprador()=="") {
            this.msgNombres = "Debe introducir los nombres";
            this.estadoFormulario = false;
        } else {
            this.msgNombres = "";
        }
        if (this.CompradorSeleccionado.getApellidosComprador()=="") {
            this.msgApellidos = "Debe introducir los apellidos";
            this.estadoFormulario = false;
        } else {
            this.msgApellidos = "";
        }
        if (this.CompradorSeleccionado.getFechanacimientoComprador() == null) {
            this.msgFecha = "Debe introducir la fecha";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }
       
        
         if (this.CompradorSeleccionado.getDuiComprador() != "") {

                Boolean estaBd = this.compradorBo.getTCompradorCodigo(this.CompradorSeleccionado.getDuiComprador());

                if (!(this.CompradorSeleccionado.getDuiComprador().equals(this.duiAux))) {

                System.out.println(this.CompradorSeleccionado.getDuiComprador());
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
        
        
        
        
        
        if (this.CompradorSeleccionado.getNitComprador()=="") {
            this.msgNit = "Debe introducir el NIT";
            this.estadoFormulario = false;
        } else {
            this.msgNit = "";
        }
        if (this.CompradorSeleccionado.getTipoComprador() == "") {
            this.msgTipo = "Debe seleccionar el tipo";
            this.estadoFormulario = false;
        } else {
            this.msgTipo = "";
        }
        if (this.CompradorSeleccionado.getTelefonoComprador()=="") {
            this.msgTelefono = "Debe introducir el teléfono";
            this.estadoFormulario = false;
        } else {
            this.msgTelefono = "";
        }
        if (this.CompradorSeleccionado.getMovilComprador()=="") {
            this.msgMovil = "Debe introducir el móvil";
            this.estadoFormulario = false;
        } else {
            this.msgMovil = "";
        }
        if (this.CompradorSeleccionado.getDireccionComprador().length() < 5) {
            this.msgDireccion = "Debe introducir la dirección";
            this.estadoFormulario = false;
        } else {
            this.msgDireccion = "";
        }

    }

    public void limpiar() {

        this.estadoFormulario = false;
        this.msgNombres = "";
        this.msgApellidos = "";
        this.msgDireccion = "";
        this.msgDui = "";
        this.msgNit = "";
        this.msgTelefono = "";
        this.msgMovil = "";
        this.msgDireccion = "";
        this.msgFecha = "";
        this.msgDireccion = "";
        this.msgTipo = "";
        this.Comprador = new TComprador();
        this.Comprador.setSexoComprador("Masculino");
        this.listaComprador = compradorBo.listComprador();

    }

    public void enableShowDataBean() {

        limpiar();
        super.enableShowData();

    }

    public String calcularEdad(String fecha) throws ParseException {
       
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        Date fechaNac = null;
        String nueva = "";
        int año = 0;
        try {       
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            nueva = (formatter.format(fechaNac));
            Calendar fechaActual = Calendar.getInstance();
            fechaNacimiento.setTime(fechaNac);
            año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
            int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
            int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
            if (mes < 0 || (mes == 0 && dia < 0)) {
                año--;
            }
        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        }
        return "" + año;

    }

    public void verReporteComprador() throws SQLException, JRException, IOException {

        this.getConexion();        
        Map<String, Object> parametros = new HashMap();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        Date fechaNac = null;
        String nueva = "";
        int año = 0;         
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(""+sdf.format(this.CompradorSeleccionado.getFechanacimientoComprador()));
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
        parametros.put("id_comprador", this.CompradorSeleccionado.getIdComprador());
        parametros.put("edad", año );        
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/comprador.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
         TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_comprador");
        auxBitacora.setAccionBitacora("Generar reporte de comprador");
        
            
        auxBitacora.setDatosBitacora("Nombres:" + this.CompradorSeleccionado.getNombresComprador()
                + ", Apellidos: " + this.CompradorSeleccionado.getApellidosComprador()                
                + ", DUI: " + this.CompradorSeleccionado.getDuiComprador()
               );
        
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.CompradorSeleccionado.getIdComprador());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        

    }
      
    public void verReporteCompradorPDF() throws SQLException, JRException, IOException{
           
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        Date fechaNac = null;
        String nueva = "";
        int año = 0;    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(""+sdf.format(this.CompradorSeleccionado.getFechanacimientoComprador()));
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
        parametros.put("id_comprador", this.CompradorSeleccionado.getIdComprador());    
        parametros.put("edad", año );        
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/comprador.jasper"));     
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Comprador.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();  
        
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_comprador");
        auxBitacora.setAccionBitacora("Descargar reporte de comprador");
        
            
        auxBitacora.setDatosBitacora("Nombres:" + this.CompradorSeleccionado.getNombresComprador()
                + ", Apellidos: " + this.CompradorSeleccionado.getApellidosComprador()                
                + ", DUI: " + this.CompradorSeleccionado.getDuiComprador()
               );
        
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.CompradorSeleccionado.getIdComprador());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        
        
        
        
    }
    
}
