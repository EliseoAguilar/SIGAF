/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Ibo.IProveedorBo;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TProveedor;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;

import javax.inject.Named;
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
@Named(value = "proveedorBean")
@SessionScoped
@ManagedBean
public class ProveedorBean extends Actividad {

    private IBitacoraBo bitacoraBo;

   

    private IEntidadBo entidadBo;

    private TEntidad entidadSeleccionada;

    private IProveedorBo proveedorBo;

    private TProveedor provedor;

    private TProveedor provedorSeleccionado;

    private List< TProveedor> ListaProveedores;

    private Boolean estadoValido;

    private String msgNombre;

    private String msgGiro;

    private String msgNIT;

    private String msgNRC;

    private String msgTel1;

    private String msgTel2;

    private String msgDireccion;

    private String msgCorreo;

    public TEntidad getEntidadSeleccionada() {
        return entidadSeleccionada;
    }

    public void setEntidadSeleccionada(TEntidad entidadSeleccionada) {
        this.entidadSeleccionada = entidadSeleccionada;
    }

    
    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }



    /**
     * Metodo que actuliza la lista de proveedores
     */
    public void updateListaProveedores() {
        this.ListaProveedores = this.proveedorBo.listProveedor(entidadSeleccionada.getIdEntidad());
    }

    public void getContabilidadPredeterminado() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        ContablidadPredeterminarBean ContPreBean = (ContablidadPredeterminarBean) request.getSession().getAttribute("contablidadPredeterminarBean");

        this.entidadSeleccionada = ContPreBean.getEntidadSeleccionada();
    }

    
    public String getMsgCorreo() {
        return msgCorreo;
    }

    public void setMsgCorreo(String msgCorreo) {
        this.msgCorreo = msgCorreo;
    }

    public IEntidadBo getEntidadBo() {
        return entidadBo;
    }

    public void setEntidadBo(IEntidadBo entidadBo) {
        this.entidadBo = entidadBo;
    }

  

 

    public void init() {

        this.limpiar();
        
        super.enableShowData();
        try {
            this.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getMsgNIT() {
        return msgNIT;
    }

    public void setMsgNIT(String msgNIT) {
        this.msgNIT = msgNIT;
    }

    public String getMsgNRC() {
        return msgNRC;
    }

    public void setMsgNRC(String msgNRC) {
        this.msgNRC = msgNRC;
    }

    public String getMsgTel1() {
        return msgTel1;
    }

    public void setMsgTel1(String msgTel1) {
        this.msgTel1 = msgTel1;
    }

    public String getMsgTel2() {
        return msgTel2;
    }

    public void setMsgTel2(String msgTel2) {
        this.msgTel2 = msgTel2;
    }

    public String getMsgDireccion() {
        return msgDireccion;
    }

    public void setMsgDireccion(String msgDireccion) {
        this.msgDireccion = msgDireccion;
    }

    public IProveedorBo getProveedorBo() {
        return proveedorBo;
    }

    public void setProveedorBo(IProveedorBo proveedorBo) {
        this.proveedorBo = proveedorBo;
    }

    public TProveedor getProvedor() {
        return provedor;
    }

    public void setProvedor(TProveedor provedor) {
        this.provedor = provedor;
    }

    public TProveedor getProvedorSeleccionado() {
        return provedorSeleccionado;
    }

    public void setProvedorSeleccionado(TProveedor provedorSeleccionado) {
        this.provedorSeleccionado = provedorSeleccionado;
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proveedor");
        auxBitacora.setAccionBitacora("Ver información proveedor");
        auxBitacora.setDatosBitacora("Nombre:" + provedorSeleccionado.getNombreProveedor()
                + ", NIT:" + provedorSeleccionado.getNitProveedor() + ", NRC:" + provedorSeleccionado.getNrcProveedor() + ", Entidad:" + entidadSeleccionada.getNombreEntidad());
        auxBitacora.setIdTableBitacora(provedorSeleccionado.getIdProveedor());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);
    }

    public List<TProveedor> getListaProveedores() {
        return ListaProveedores;
    }

    public void setListaProveedores(List<TProveedor> ListaProveedores) {
        this.ListaProveedores = ListaProveedores;
    }

    public Boolean getEstadoValido() {
        return estadoValido;
    }

    public void setEstadoValido(Boolean estadoValido) {
        this.estadoValido = estadoValido;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public String getMsgGiro() {
        return msgGiro;
    }

    public void setMsgGiro(String msgGiro) {
        this.msgGiro = msgGiro;
    }

    public void crearProv() {

        try {

            this.provedor.setTEntidad(entidadSeleccionada);
            provedor.setEstadoProveedor(true);
            this.proveedorBo.create(provedor);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_proveedor");
            auxBitacora.setAccionBitacora("Agregar proveedor");
            auxBitacora.setDatosBitacora("Nombre:" + provedor.getNombreProveedor()
                    + ", NIT:" + provedor.getNitProveedor() + ", NRC:"
                    + provedor.getNrcProveedor()
                    + ", Entidad:" + entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(provedor.getIdProveedor());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            this.limpiar();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proveedor registrado correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El  Proveedor no pudo ser registrado.", ""));
        }
    }

    public void validarFormulario() {

        this.estadoValido = true;

        if (this.provedor.getNombreProveedor().length() < 6) {
            this.msgNombre = "El nombre debe contener como mínimo 6 caracteres";
            this.estadoValido = false;
        } else {
            this.msgNombre = "";
        }

        if (this.provedor.getGiroProveedor().length() < 6) {
            this.msgGiro = "El giro debe contener como mínimo 6 caracteres";
            this.estadoValido = false;
        } else {
            this.msgGiro = "";
        }

        if (this.provedor.getNitProveedor().length() == 0) {
            this.msgNIT = "NIT requerido";
            this.estadoValido = false;
        } else if (this.proveedorBo.getProveedorRepNit(entidadSeleccionada.getIdEntidad(), this.provedor.getNitProveedor()) != null) {
            this.msgNIT = "El NIT ya fue asignado a otro proveedor";
            this.estadoValido = false;
        } else {
            this.msgNIT = "";
        }

        if (this.provedor.getNrcProveedor().length() == 0) {
            this.msgNRC = "NRC requrido.";
            this.estadoValido = false;
        } else if (this.proveedorBo.getProveedorRepNrc(entidadSeleccionada.getIdEntidad(), this.provedor.getNrcProveedor()) != null) {
            this.msgNRC = "El NRC ya fue asignado a otro proveedor";
            this.estadoValido = false;
        } else {
            this.msgNRC = "";
        }

        if (this.provedor.getTelefono1Proveedor().length() < 9) {
            this.msgTel1 = "Teléfono invalido";
            this.estadoValido = false;
        } else {
            this.msgTel1 = "";
        }

        if (this.provedor.getDireccionProveedor().length() < 10) {
            this.msgDireccion = "La dirección debe contener como mínimo 10 caracteres";
            this.estadoValido = false;
        } else {
            this.msgDireccion = "";
        }

        if (this.provedor.getCorreoProveedor().length() < 10) {
            this.msgCorreo = "El correo debe contener como mínimo 10 caracteres";
            this.estadoValido = false;
        } else {
            if (this.proveedorBo.getProveedorRepCorreo(entidadSeleccionada.getIdEntidad(), this.provedor.getCorreoProveedor()) != null) {
                this.msgCorreo = "El correo ya fue asignado a otro proveedor";
                this.estadoValido = false;
            } else {
                this.msgCorreo = "";
            }
        }

    }

    public void validarFormularioModificar() {

        this.estadoValido = true;

        if (this.provedorSeleccionado.getNombreProveedor().length() < 6) {
            this.msgNombre = "El nombre debe contener como mínimo 6 caracteres";
            this.estadoValido = false;
        } else {
            this.msgNombre = "";
        }

        if (this.provedorSeleccionado.getGiroProveedor().length() < 6) {
            this.msgGiro = "El giro debe contener como mínimo 6 caracteres";
            this.estadoValido = false;
        } else {
            this.msgGiro = "";
        }

        if (this.provedorSeleccionado.getNitProveedor().length() == 0) {
            this.msgNIT = "NIT requerido";
            this.estadoValido = false;
        } else if (this.proveedorBo.getProveedorRepActNit(entidadSeleccionada.getIdEntidad(), this.provedorSeleccionado.getIdProveedor(), this.provedorSeleccionado.getNitProveedor()) != null) {
            this.msgNIT = "ElNIT ya fue asignado a otro proveedor";
            this.estadoValido = false;
        } else {
            this.msgNIT = "";
        }

        if (this.provedorSeleccionado.getNrcProveedor().length() == 0) {
            this.msgNRC = "NRC requrido";
            this.estadoValido = false;
        } else if (this.proveedorBo.getProveedorRepActNrc(entidadSeleccionada.getIdEntidad(), this.provedorSeleccionado.getIdProveedor(), this.provedorSeleccionado.getNrcProveedor()) != null) {
            this.msgNRC = "El NRC ya fue asignado a otro proveedor";
            this.estadoValido = false;
        } else {
            this.msgNRC = "";
        }

        if (this.provedorSeleccionado.getTelefono1Proveedor().length() < 9) {
            this.msgTel1 = "Teléfono invalido";
            this.estadoValido = false;
        } else {
            this.msgTel1 = "";
        }

        if (this.provedorSeleccionado.getDireccionProveedor().length() < 10) {
            this.msgDireccion = "La dirección debe contener como minimo 10 caracteres";
            this.estadoValido = false;
        } else {
            this.msgDireccion = "";
        }

        if (this.provedorSeleccionado.getCorreoProveedor().length() < 10) {
            this.msgCorreo = "El correo debe contener como mínimo 10 caracteres";
            this.estadoValido = false;
        } else {
            if (this.proveedorBo.getProveedorRepActCorreo(entidadSeleccionada.getIdEntidad(), this.provedorSeleccionado.getIdProveedor(), this.provedorSeleccionado.getCorreoProveedor()) != null) {
                this.msgCorreo = "El correo ya fue asignado a otro proveedor";
                this.estadoValido = false;
            } else {
                this.msgCorreo = "";
            }
        }

    }

    public void enableShowDataBean() {
        super.enableShowData();
        this.limpiar();
    }

    public void limpiar() {
        this.provedor = new TProveedor();
        this.estadoValido = false;
        this.msgCorreo = "";
        this.msgDireccion = "";
        this.msgGiro = "";
        this.msgNRC = "";
        this.msgNIT = "";
        this.msgNombre = "";
        this.msgTel1 = "";
        this.msgTel2 = "";
    }

    public void actualizar() {
        try {
            this.proveedorBo.update(provedorSeleccionado);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_proveedor");
            auxBitacora.setAccionBitacora("Modificar  proveedor");
            auxBitacora.setDatosBitacora("Nombre:" + provedorSeleccionado.getNombreProveedor()
                    + ", NIT:" + provedorSeleccionado.getNitProveedor()
                    + ", NRC:" + provedorSeleccionado.getNrcProveedor()
                    + ", Entidad:" + entidadSeleccionada.getNombreEntidad()
            );
            auxBitacora.setIdTableBitacora(provedorSeleccionado.getIdProveedor());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            this.enableShowDataBean();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proveedor modificado correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El proveedor no pudo ser modificado.", ""));
        }

    }

    public void darDeAlta() {
        try {
            this.provedorSeleccionado.setEstadoProveedor(true);
            this.proveedorBo.update(provedorSeleccionado);
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_proveedor");
            auxBitacora.setAccionBitacora("Dar de alta proveedor");
            auxBitacora.setDatosBitacora("Nombre:" + provedorSeleccionado.getNombreProveedor()
                    + ", NIT:" + provedorSeleccionado.getNitProveedor()
                    + ", NRC:" + provedorSeleccionado.getNrcProveedor()
                    + ", Entidad:" + entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(provedorSeleccionado.getIdProveedor());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proveedor dado de alta correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El proveedor no pudo ser dado de alta.", ""));
        }

    }

    public void darDeBaja() {

        try {

            this.provedorSeleccionado.setEstadoProveedor(false);
            this.proveedorBo.update(provedorSeleccionado);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_proveedor");
            auxBitacora.setAccionBitacora("Dar de baja proveedor");
            auxBitacora.setDatosBitacora("Nombre:" + provedorSeleccionado.getNombreProveedor()
                    + ", NIT:" + provedorSeleccionado.getNitProveedor()
                    + ", NRC:" + provedorSeleccionado.getNrcProveedor()
                    + ", Entidad:" + entidadSeleccionada.getNombreEntidad());
            auxBitacora.setIdTableBitacora(provedorSeleccionado.getIdProveedor());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proveedor dado de baja correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El proveedor no pudo ser dado de baja.", ""));

        }
    }

    public void generarReporteProveedor() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idProveedor", this.provedorSeleccionado.getIdProveedor());
        estadoUsuario.put("idEntidad", entidadSeleccionada.getIdEntidad());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("/Reportes/contabilidad/ReporteProveedorIndividual.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), estadoUsuario, this.getConn());

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proveedor");
        auxBitacora.setAccionBitacora("Generar reporte de proveedor");
        auxBitacora.setDatosBitacora("Nombre:" + provedorSeleccionado.getNombreProveedor()
                + ", NIT:" + provedorSeleccionado.getNitProveedor()
                + ", NRC:" + provedorSeleccionado.getNrcProveedor()
                + ", Entidad:" + entidadSeleccionada.getNombreEntidad());
        auxBitacora.setIdTableBitacora(provedorSeleccionado.getIdProveedor());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void generarReporteProveedorPDF() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idProveedor", this.provedorSeleccionado.getIdProveedor());
        estadoUsuario.put("idEntidad", entidadSeleccionada.getIdEntidad());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("/Reportes/contabilidad/ReporteProveedorIndividual.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), estadoUsuario, this.getConn());

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_proveedor");
        auxBitacora.setAccionBitacora("Descargar reporte de proveedor");
        auxBitacora.setDatosBitacora("Nombre:" + provedorSeleccionado.getNombreProveedor()
                + ", NIT:" + provedorSeleccionado.getNitProveedor()
                + ", NRC:" + provedorSeleccionado.getNrcProveedor()
                + ", Entidad:" + entidadSeleccionada.getNombreEntidad());
        auxBitacora.setIdTableBitacora(provedorSeleccionado.getIdProveedor());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        bitacoraBo.create(auxBitacora);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Proveedor-" + this.provedorSeleccionado.getNitProveedor() + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

}
