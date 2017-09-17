/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IAreaCultivoBo;
import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.pojo.TAreaCultivo;
import com.sigaf.pojo.TBitacora;
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
import javax.faces.model.SelectItem;
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
@Named(value = "areaCultivoBean")
@RequestScoped
@ManagedBean

public class AreaCultivoBean extends Actividad {

    private TAreaCultivo AreaCultivo;
    private TAreaCultivo AreaCultivoSeleccionado;
    private List<TAreaCultivo> listaAreaCultivo;
    private List<SelectItem> selecteOneItemAreas;
    private IAreaCultivoBo areaCultivoBo;
    private IBitacoraBo bitacoraBo;    
    private boolean estadoFormulario;
    private String msgNombre;
    private String nombreAux;

    public String getNombreAux() {
        return nombreAux;
    }

    public void setNombreAux(String nombreAux) {
        this.nombreAux = nombreAux;
    }
    
    
    
    public List<SelectItem> getSelecteOneItemAreas() {

        this.selecteOneItemAreas = new ArrayList<SelectItem>();
        List<TAreaCultivo> cultivos = areaCultivoBo.listAreaCultivoActivos();
        for (TAreaCultivo cultivo : cultivos) {
            SelectItem selectItem = new SelectItem(cultivo.getIdAreaCultivo(), cultivo.getNombreAreaCultivo());
            this.selecteOneItemAreas.add(selectItem);
        }
        return selecteOneItemAreas;
    }
    
    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public void setSelecteOneItemAreas(List<SelectItem> selecteOneItemAreas) {
        this.selecteOneItemAreas = selecteOneItemAreas;
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
    
    public void updateListaAreasCultivos(){
        
        this.listaAreaCultivo = areaCultivoBo.listAreaCultivo();  
        
    }

    public TAreaCultivo getAreaCultivo() {
        return AreaCultivo;
    }

    public void setAreaCultivo(TAreaCultivo AreaCultivo) {
        this.AreaCultivo = AreaCultivo;
    }

    public TAreaCultivo getAreaCultivoSeleccionado() {
        return AreaCultivoSeleccionado;
    }

    public void setAreaCultivoSeleccionado(TAreaCultivo AreaCultivoSeleccionado) {
        this.AreaCultivoSeleccionado = AreaCultivoSeleccionado;
    }

    public List<TAreaCultivo> getListaAreaCultivo() {
        return listaAreaCultivo;
    }

    public void setListaAreaCultivo(List<TAreaCultivo> listaAreaCultivo) {
        this.listaAreaCultivo = listaAreaCultivo;
    }

    public IAreaCultivoBo getAreaCultivoBo() {
        return areaCultivoBo;
    }

    public void setAreaCultivoBo(IAreaCultivoBo areaCultivoBo) {
        this.areaCultivoBo = areaCultivoBo;
    }   
    
    public void init() {
        this.AreaCultivo = new TAreaCultivo();       
        super.enableShowData();
    }
    
    public void crear() {
        
        this.AreaCultivo.setEstadoAreaCultivo(true);
        areaCultivoBo.create(this.AreaCultivo);
        this.listaAreaCultivo = areaCultivoBo.listAreaCultivo();
        
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_area_cultivo");
        auxBitacora.setAccionBitacora("Crear área de cultivo");
        auxBitacora.setDatosBitacora("Nombre:" + this.AreaCultivo.getNombreAreaCultivo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.AreaCultivo.getIdAreaCultivo());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);       
        
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Area de cultivo guardada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        limpiar();

    }

    public void modificar() {
        
        areaCultivoBo.update(this.AreaCultivoSeleccionado);
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_area_cultivo");
        auxBitacora.setAccionBitacora("Modificar área de cultivo");
        auxBitacora.setDatosBitacora("Nombre:" + this.AreaCultivoSeleccionado.getNombreAreaCultivo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.AreaCultivoSeleccionado.getIdAreaCultivo());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);  
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Area de cultivo modificada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaAreaCultivo = areaCultivoBo.listAreaCultivo();
        limpiar();
        super.enableShowData();
    
    }

    public void darDeBaja() {
    
        this.AreaCultivoSeleccionado.setEstadoAreaCultivo(false);
        areaCultivoBo.update(this.AreaCultivoSeleccionado);
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_area_cultivo");
        auxBitacora.setAccionBitacora("Dar de baja área de cultivo");
        auxBitacora.setDatosBitacora("Nombre:" + this.AreaCultivoSeleccionado.getNombreAreaCultivo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.AreaCultivoSeleccionado.getIdAreaCultivo());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Area de cultivo dada de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaAreaCultivo = areaCultivoBo.listAreaCultivo();
        super.enableShowData();
    
    }

    public void darDeAlta() {
    
        this.AreaCultivoSeleccionado.setEstadoAreaCultivo(true);
        areaCultivoBo.update(this.AreaCultivoSeleccionado);
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_area_cultivo");
        auxBitacora.setAccionBitacora("Dar de alta área de cultivo");
        auxBitacora.setDatosBitacora("Nombre:" + this.AreaCultivoSeleccionado.getNombreAreaCultivo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.AreaCultivoSeleccionado.getIdAreaCultivo());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Area de cultivo dada de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaAreaCultivo = areaCultivoBo.listAreaCultivo();
        super.enableShowData();
    
    }  

    public void validarFormulario() {
        
        this.estadoFormulario = true;      
        if (this.AreaCultivo.getNombreAreaCultivo()=="") {
            this.msgNombre = "Debe introducir el nombre";
            this.estadoFormulario = false;
        } else if (this.areaCultivoBo.getTAreaCultivoNombre(this.AreaCultivo.getNombreAreaCultivo())) {
            this.estadoFormulario = false;
           this.msgNombre = "El nombre introducido ya fue registrado";
        } else {
            this.msgNombre = "";
        }

    }

    public void validarFormularioModificar() {

        this.estadoFormulario = true;
          
        
        if (this.AreaCultivoSeleccionado.getNombreAreaCultivo() != "") {

                Boolean estaBd = this.areaCultivoBo.getTAreaCultivoNombre(this.AreaCultivoSeleccionado.getNombreAreaCultivo());

                if (!(this.AreaCultivoSeleccionado.getNombreAreaCultivo().equals(this.nombreAux))) {

                if (estaBd) {
                    
                    this.msgNombre = "El nombre introducido ya fue registrado";
                    this.estadoFormulario = false;

                }

            } else {

                this.msgNombre = "";
            }
            } else {

                this.msgNombre = "Debe introducir el nombre";
                this.estadoFormulario = false;

            }
        
        

    }

    public void limpiar() {

        this.estadoFormulario = false;
        this.msgNombre = "";
        this.AreaCultivo = new TAreaCultivo();
        this.listaAreaCultivo = areaCultivoBo.listAreaCultivo();

    }

    public void enableShowDataBean() {
        limpiar();
        super.enableShowData();

    }
   
    public void verReporteArea() throws SQLException, JRException, IOException{        
        
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_tipo", this.AreaCultivoSeleccionado.getIdAreaCultivo());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/Area.jasper"));   
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
         TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_area_cultivo");
        auxBitacora.setAccionBitacora("Generar reporte de área de cultivo");
        auxBitacora.setDatosBitacora("Nombre:" + this.AreaCultivoSeleccionado.getNombreAreaCultivo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.AreaCultivoSeleccionado.getIdAreaCultivo());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        
        
        
        
    }
      
    public void verReporteAreaPDF() throws SQLException, JRException, IOException{
      
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_tipo", this.AreaCultivoSeleccionado.getIdAreaCultivo());     
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/Area.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Area de cultivo.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
          TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_area_cultivo");
        auxBitacora.setAccionBitacora("Descargar reporte de área de cultivo");
        auxBitacora.setDatosBitacora("Nombre:" + this.AreaCultivoSeleccionado.getNombreAreaCultivo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.AreaCultivoSeleccionado.getIdAreaCultivo());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
       
    }

}
