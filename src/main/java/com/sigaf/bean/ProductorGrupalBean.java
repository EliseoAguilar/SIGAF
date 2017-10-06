/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IProductorGrupalBo;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TProductorGrupal;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

/**
 *
 * @author yonat
 */
@Named(value = "productorGrupalBean")
@RequestScoped
@ManagedBean
public class ProductorGrupalBean extends Actividad {

    private TProductorGrupal productorGrupal;
    private TProductorGrupal ProductorGrupalSeleccionado;
    private List<TProductorGrupal> listaProductorGrupal;
    private IProductorGrupalBo productorGrupalBo;
    private String msgNombre;
    private String msgParticipantesHombres;
    private String msgParticipantesMujeres;
    private String msgContacto;
    private String msgDui;
    private String msgNit;
    private String msgCorreo;
    private String msgTelefono;
    private String msgMovil;
    private String msgUbicacion;
    private boolean estadoFormulario;
    private int contador;
    private IBitacoraBo bitacoraBo;
    private Boolean nombreAux;

    public Boolean getNombreAux() {
        return nombreAux;
    }

    public void setNombreAux(Boolean nombreAux) {
        this.nombreAux = nombreAux;
    }

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public boolean isEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public String getMsgParticipantesHombres() {
        return msgParticipantesHombres;
    }

    public void setMsgParticipantesHombres(String msgParticipantesHombres) {
        this.msgParticipantesHombres = msgParticipantesHombres;
    }

    public String getMsgParticipantesMujeres() {
        return msgParticipantesMujeres;
    }

    public void setMsgParticipantesMujeres(String msgParticipantesMujeres) {
        this.msgParticipantesMujeres = msgParticipantesMujeres;
    }

    public String getMsgContacto() {
        return msgContacto;
    }

    public void setMsgContacto(String msgContacto) {
        this.msgContacto = msgContacto;
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

    public String getMsgCorreo() {
        return msgCorreo;
    }

    public void setMsgCorreo(String msgCorreo) {
        this.msgCorreo = msgCorreo;
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

    public String getMsgUbicacion() {
        return msgUbicacion;
    }

    public void setMsgUbicacion(String msgUbicacion) {
        this.msgUbicacion = msgUbicacion;
    }

    public TProductorGrupal getProductorGrupal() {
        return productorGrupal;
    }

    public void setProductorGrupal(TProductorGrupal productorGrupal) {
        this.productorGrupal = productorGrupal;
    }

    public TProductorGrupal getProductorGrupalSeleccionado() {
        return ProductorGrupalSeleccionado;
    }

    public void setProductorGrupalSeleccionado(TProductorGrupal productorGrupalSeleccionado) {
        this.ProductorGrupalSeleccionado = productorGrupalSeleccionado;
    }

    public List<TProductorGrupal> getListaProductorGrupal() {
        return listaProductorGrupal;
    }

    public void setListaProductorGrupal(List<TProductorGrupal> listaProductorGrupal) {
        this.listaProductorGrupal = listaProductorGrupal;
    }

    public IProductorGrupalBo getProductorGrupalBo() {
        return productorGrupalBo;
    }

    public void setProductorGrupalBo(IProductorGrupalBo productorGrupalBo) {
        this.productorGrupalBo = productorGrupalBo;
    }

    public void updateListaGrupos() {
        this.listaProductorGrupal = productorGrupalBo.listProductorGrupal();

    }

    public void crear() {

        this.productorGrupal.setEstadoProductorGrupal(true);
        productorGrupalBo.create(this.productorGrupal);
        this.listaProductorGrupal = productorGrupalBo.listProductorGrupal();
        this.estadoFormulario = false;
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_productor_grupal");
        auxBitacora.setAccionBitacora("Agregar productor grupal");           
        auxBitacora.setDatosBitacora("Nombre:" + this.productorGrupal.getNombreProductorGrupal()                                          
               );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.ProductorGrupalSeleccionado.getIdProductorGrupal());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupo de productores guardado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        limpiar();

    }

    public void init() {

        this.productorGrupal = new TProductorGrupal();
        this.ProductorGrupalSeleccionado= new TProductorGrupal();
        super.enableShowData();

    }

    public void modificar() {

        productorGrupalBo.update(this.ProductorGrupalSeleccionado);
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_productor_grupal");
        auxBitacora.setAccionBitacora("Modificar productor grupal");           
        auxBitacora.setDatosBitacora("Nombre:" + this.ProductorGrupalSeleccionado.getNombreProductorGrupal()                                          
               );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.ProductorGrupalSeleccionado.getIdProductorGrupal());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupo de productores modificado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaProductorGrupal = productorGrupalBo.listProductorGrupal();
        super.enableShowData();
        limpiar();

    }

    public void darDeBaja() {

        this.ProductorGrupalSeleccionado.setEstadoProductorGrupal(false);
        productorGrupalBo.update(this.ProductorGrupalSeleccionado);
        
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_productor_grupal");
        auxBitacora.setAccionBitacora("Dar de baja productor grupal");           
        auxBitacora.setDatosBitacora("Nombre:" + this.ProductorGrupalSeleccionado.getNombreProductorGrupal()                                          
               );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.ProductorGrupalSeleccionado.getIdProductorGrupal());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupo de productores dado de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaProductorGrupal = productorGrupalBo.listProductorGrupal();
        super.enableShowData();

    }

    public void darDeAlta() {

        this.ProductorGrupalSeleccionado.setEstadoProductorGrupal(true);
        productorGrupalBo.update(this.ProductorGrupalSeleccionado);
        
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_productor_grupal");
        auxBitacora.setAccionBitacora("Dar de alta productor grupal");           
        auxBitacora.setDatosBitacora("Nombre:" + this.ProductorGrupalSeleccionado.getNombreProductorGrupal()                                          
               );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.ProductorGrupalSeleccionado.getIdProductorGrupal());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupo de productores dado de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaProductorGrupal = productorGrupalBo.listProductorGrupal();
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
        if (this.productorGrupal.getNombreProductorGrupal()=="") {
            this.msgNombre = "Debe introducir el nombre del grupo";
            this.estadoFormulario = false;
        } else if (this.productorGrupalBo.getTProductorGrupalNombre(this.productorGrupal.getNombreProductorGrupal())) {
            this.estadoFormulario = false;
            this.msgNombre = "El nombre introducido ya fue registrado";
        } else {
            this.msgNombre = "";
        }
        if (this.productorGrupal.getUbicacionProductorGrupal().length() < 5) {
            this.msgUbicacion = "Debe introducir la ubicación";
            this.estadoFormulario = false;
        } else {
            this.msgUbicacion = "";
        }

    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;

        if (this.ProductorGrupalSeleccionado.getNombreProductorGrupal()=="") {
            this.msgNombre = "Debe introducir el nombre del grupo";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        
        if (this.ProductorGrupalSeleccionado.getNombreProductorGrupal() != "") {

                Boolean estaBd = this.productorGrupalBo.getTProductorGrupalNombre(this.ProductorGrupalSeleccionado.getNombreProductorGrupal());

                if (!(this.ProductorGrupalSeleccionado.getNombreProductorGrupal().equals(this.nombreAux))) {

                if (estaBd) {
                    
                    this.msgNombre = "El nombre introducido ya fue registrado";
                    this.estadoFormulario = false;

                }

            } else {

                this.msgNombre = "";
            }
            } else {

                this.msgNombre = "Debe introducir el nombre del grupo";
                this.estadoFormulario = false;

            }
        
        
        
        
        
        

        if (this.ProductorGrupalSeleccionado.getUbicacionProductorGrupal().length() < 5) {
            this.msgUbicacion = "Debe introducir la ubicación";
            this.estadoFormulario = false;
        } else {
            this.msgUbicacion = "";
        }

    }

    public void limpiar() {

        this.estadoFormulario = false;
        this.msgNombre = "";
        this.msgParticipantesHombres = "";
        this.msgParticipantesMujeres = "";
        this.msgContacto = "";
        this.msgDui = "";
        this.msgNit = "";
        this.msgTelefono = "";
        this.msgMovil = "";
        this.msgUbicacion = "";
        this.productorGrupal = new TProductorGrupal();
        this.listaProductorGrupal = productorGrupalBo.listProductorGrupal();

    }

    public void enableShowDataBean() {

        limpiar();
        super.enableShowData();

    }

    public void verReporteGrupo() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_productor", this.ProductorGrupalSeleccionado.getIdProductorGrupal());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productorGrupal.jasper"));
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
        auxBitacora.setTableBitacora("t_productor_grupal");
        auxBitacora.setAccionBitacora("Generar reporte de productor grupal");           
        auxBitacora.setDatosBitacora("Nombre:" + this.ProductorGrupalSeleccionado.getNombreProductorGrupal()                                          
               );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.ProductorGrupalSeleccionado.getIdProductorGrupal());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        

    }

    public void verReporteGrupoPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_productor", this.ProductorGrupalSeleccionado.getIdProductorGrupal());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productorGrupal.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Grupo de productores.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();        
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_productor_grupal");
        auxBitacora.setAccionBitacora("Descargar reporte de productor grupal");           
        auxBitacora.setDatosBitacora("Nombre:" + this.ProductorGrupalSeleccionado.getNombreProductorGrupal()                                          
               );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.ProductorGrupalSeleccionado.getIdProductorGrupal());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        

    }

}
