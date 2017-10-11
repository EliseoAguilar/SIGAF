/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IAreaBo;
import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IConfiguracionBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TConfiguracion;
import com.sigaf.pojo.TEntidad;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
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
import org.hibernate.HibernateException;

/**
 *
 * @author Genoves
 */
@ManagedBean
@RequestScoped

public class AreaBean extends Actividad {

    /**
     * Creates a new instance of AreaBean
     */
    private List<TArea> listaArea;
    private IAreaBo areaBo;
    private TArea areaSeleccionada;
    private TArea area;
    private IEntidadBo ientidadBo;
    private Boolean estadoFormulario;
    private String msgNombre;
    private String msgCodigo;
    private List<TEntidad> listaEntidades;
  
    private Integer idEntidadSeleccionada;
    private boolean mostrarTabla;
    private IBitacoraBo bitacoraBo;

    public void setArea(TArea area) {
        this.area = area;
    }

    public List<TArea> getListaArea() {
        return listaArea;
    }

    public void setListaArea(List<TArea> listaArea) {
        this.listaArea = listaArea;
    }

    public TArea getAreaSeleccionada() {
        return areaSeleccionada;
    }

    public void setAreaSeleccionada(TArea areaSeleccionada) {
        this.areaSeleccionada = areaSeleccionada;
    }

    //Validar campos
    public Boolean getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public String getMsgCodigo() {
        return msgCodigo;
    }

    public void setMsgCodigo(String msgCodigo) {
        this.msgCodigo = msgCodigo;
    }

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public IEntidadBo getIentidadBo() {
        return ientidadBo;
    }

    public void setIentidadBo(IEntidadBo ientidadBo) {
        this.ientidadBo = ientidadBo;
    }

    public void updateListaAreas() {
        this.listaArea = this.areaBo.listArea(1);
    }

    public List<TEntidad> getListaEntidades() {
        return listaEntidades = this.ientidadBo.listTEndidad();
    }

    public void setListaEntidades(List<TEntidad> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }
    private List<TArea> listaAreaFiltradas;

    public List<TArea> getListaAreaFiltradas() {
        return listaAreaFiltradas;
    }

    public void setListaAreaFiltradas(List<TArea> listaAreaFiltradas) {
        this.listaAreaFiltradas = listaAreaFiltradas;
    }

    public Integer getIdEntidadSeleccionada() {
        return idEntidadSeleccionada;
    }

    public void setIdEntidadSeleccionada(Integer idEntidadSeleccionada) {
        this.idEntidadSeleccionada = idEntidadSeleccionada;
    }

    public boolean isMostrarTabla() {
        return mostrarTabla;
    }

    public void setMostrarTabla(boolean mostrarTabla) {
        this.mostrarTabla = mostrarTabla;
    }

    public void Init() {
        this.estadoFormulario = false;
        this.area = new TArea();
        this.area.setTEntidad(new TEntidad(1));
        this.mostrarTabla = false;
        super.enableShowData();
    }



    public IAreaBo getAreaBo() {
        return areaBo;
    }

    public void setAreaBo(IAreaBo areaBo) {
        this.areaBo = areaBo;
    }

    public TArea getArea() {
        return area;
    }

    public void create() {
        try {
            this.estadoFormulario = false;
            this.area.setTEntidad(new TEntidad(1));
            this.areaBo.create(this.area);
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_area");
            auxBitacora.setAccionBitacora("Agregar área");
            auxBitacora.setDatosBitacora("Nombre: " + this.area.getNombreArea() + ", Código: " + this.area.getCodigoArea());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.area.getIdArea());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Área guardada correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(e.getMessage()));
        }
        this.area.setNombreArea("");
        this.area.setCodigoArea("");
    }

    public void createCooperativa() {
        try {
            this.estadoFormulario = false;
            this.area.setTEntidad(new TEntidad(this.idEntidadSeleccionada));
            this.areaBo.create(this.area);
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_area");
            auxBitacora.setAccionBitacora("Agregar área");
            auxBitacora.setDatosBitacora("Nombre: " + this.area.getNombreArea() + ", Código: " + this.area.getCodigoArea());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.area.getIdArea());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Área guardada correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(e.getMessage()));
        }
        this.area.setNombreArea("");
        this.area.setCodigoArea("");
        mostrarDatosFiltrados();

    }

    public void modificar() {
        try {
            this.areaBo.update(this.areaSeleccionada);
            this.setEstadoFormulario(false);
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_area");
            auxBitacora.setAccionBitacora("Modificar área");
            auxBitacora.setDatosBitacora("Nombre: " + this.areaSeleccionada.getNombreArea() + ", Código: " + this.areaSeleccionada.getCodigoArea());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.areaSeleccionada.getIdArea());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);
            super.enableShowData();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Área modificada correctamente"));
        } catch (HibernateException he) {
        }
    }

    public void showUpdateBean() {
        this.estadoFormulario = false;
        super.enableShowUpdate();
    }

    public void darDeBaja() {
        this.areaSeleccionada.setEstadoArea(Boolean.FALSE);
        try {
            areaBo.update(this.areaSeleccionada);
            this.estadoFormulario = false;
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_area");
            auxBitacora.setAccionBitacora("Dar de baja área");
            auxBitacora.setDatosBitacora("Nombre: " + this.areaSeleccionada.getNombreArea() + ", Código: " + this.areaSeleccionada.getCodigoArea());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.areaSeleccionada.getIdArea());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);
            super.enableShowData();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Área dada de baja correctamente"));
        } catch (HibernateException he) {
        }
    }

    public void darDeAlta() {
        this.areaSeleccionada.setEstadoArea(Boolean.TRUE);
        try {
            areaBo.update(this.areaSeleccionada);
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_area");
            auxBitacora.setAccionBitacora("Dar de alta área");
            auxBitacora.setDatosBitacora("Nombre: " + this.areaSeleccionada.getNombreArea() + ", Código: " + this.areaSeleccionada.getCodigoArea());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.areaSeleccionada.getIdArea());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Área dada de alta correctamente: "));
        } catch (HibernateException he) {
        }
        super.enableShowData();
    }

    public void validarFormulario() {
        this.estadoFormulario = true;
        if (this.area.getNombreArea().length() < 5) {
            this.msgNombre = "El nombre debe contener como minimo 5 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        if (this.area.getCodigoArea().length() == 0) {
            this.msgCodigo = "El código es requerido";
            this.estadoFormulario = false;
        } else if (this.areaBo.getTAreaRep(1, this.area.getCodigoArea()) != null) {
            this.msgCodigo = "El código ya fue asignado a otra area";
            this.estadoFormulario = false;
        } else {
            this.msgCodigo = "";
        }
    }

    public void validarFormularioCooperativas() {
        this.estadoFormulario = true;
        if (this.area.getNombreArea().length() < 5) {
            this.msgNombre = "El nombre debe contener como minimo 5 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        if (this.area.getCodigoArea().length() == 0) {
            this.msgCodigo = "El código es requerido";
            this.estadoFormulario = false;
        } else if (this.areaBo.getTAreaRep(this.idEntidadSeleccionada, this.area.getCodigoArea()) != null) {
            this.msgCodigo = "El código ya fue asignado a otra area";
            this.estadoFormulario = false;
        } else {
            this.msgCodigo = "";
        }
    }

    public void validarFormularioModificar(int x) {
        this.estadoFormulario = true;
        if (this.areaSeleccionada.getNombreArea().length() < 5) {
            this.msgNombre = "El nombre debe contener como minimo 5 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        if (this.areaSeleccionada.getCodigoArea().length() == 0) {
            this.msgCodigo = "El código es requerido";
            this.estadoFormulario = false;
        } else if (x == 1) {
            if (this.areaBo.getTAreaRepAct(1, areaSeleccionada.getIdArea(), areaSeleccionada.getCodigoArea()) != null) {
                this.msgCodigo = "El código ya fue asignado a otra area";
                this.estadoFormulario = false;
            }
        } else if (x == 2) {
            System.out.println("opc 2-id " + this.idEntidadSeleccionada);
            if (this.areaBo.getTAreaRepAct(this.idEntidadSeleccionada, areaSeleccionada.getIdArea(), areaSeleccionada.getCodigoArea()) != null) {
                this.msgCodigo = "El código ya fue asignado a otra area";
                this.estadoFormulario = false;
            }
        } else {
            this.msgCodigo = "";
        }
    }

    public void limpiar(int i) {
        this.msgCodigo = "";
        this.msgNombre = "";
        this.area = new TArea();
        this.area.setTEntidad(new TEntidad(1));
        if (i == 0) {
            this.estadoFormulario = false;
            super.enableShowData();
        } else {
            this.estadoFormulario = false;
        }
    }

    public void mostrarDatosFiltrados() {
        if (this.idEntidadSeleccionada < 1) {
            this.mostrarTabla = false;
        } else {
            this.mostrarTabla = true;
            this.listaAreaFiltradas = this.areaBo.listArea(this.idEntidadSeleccionada);
        }
    }

    public void verReporteArea() throws SQLException, JRException, IOException {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_area", this.areaSeleccionada.getIdArea());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/area.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        System.out.println(bytes.length);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_area");
        auxBitacora.setAccionBitacora("Generar reporte de área");
        auxBitacora.setDatosBitacora("Nombre: " + this.areaSeleccionada.getNombreArea() + ", Código: " + this.areaSeleccionada.getCodigoArea());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.areaSeleccionada.getIdArea());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void verReporteAreaPDF() throws SQLException, JRException, IOException {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_area", this.areaSeleccionada.getIdArea());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/area.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Area.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_area");
        auxBitacora.setAccionBitacora("Generar PDF de área");
        auxBitacora.setDatosBitacora("Nombre: " + this.areaSeleccionada.getNombreArea() + ", Código: " + this.areaSeleccionada.getCodigoArea());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.areaSeleccionada.getIdArea());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void verReporteAreaFundacion() throws SQLException, JRException, IOException {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_area", this.areaSeleccionada.getIdArea());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/areaEntidad.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte de área");
        auxBitacora.setDatosBitacora("Nombre: " + this.areaSeleccionada.getNombreArea() + ", Código: " + this.areaSeleccionada.getCodigoArea());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.areaSeleccionada.getIdArea());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void verReporteAreaFundacionPDF() throws SQLException, JRException, IOException {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_area", this.areaSeleccionada.getIdArea());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/areaEntidad.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Area.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_area");
        auxBitacora.setAccionBitacora("Generar PDF de área");
        auxBitacora.setDatosBitacora("Nombre: " + this.areaSeleccionada.getNombreArea() + ", Código: " + this.areaSeleccionada.getCodigoArea());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.areaSeleccionada.getIdArea());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

}
