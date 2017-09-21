package com.sigaf.bean;

import com.sigaf.Ibo.IAreaBo;
import com.sigaf.Ibo.IAsesoriaBo;
import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IClienteProyectoBo;
import com.sigaf.Ibo.ICompradorBo;
import com.sigaf.Ibo.IEmpleadoAreaBo;
import com.sigaf.Ibo.IEmpleadoBo;
import com.sigaf.Ibo.IEntidadProyectoBo;
import com.sigaf.Ibo.IProyectoBo;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TAsesoria;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TClienteProyecto;
import com.sigaf.pojo.TComprador;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEmpleadoArea;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TEntidadProyecto;
import com.sigaf.pojo.TProyecto;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

;

/**
 *
 * @author yonat
 */
@Named(value = "asesoriaBean")
@RequestScoped
@ManagedBean

public class AsesoriaBean extends Actividad {

    private List<TProyecto> listaProyecto;
    private ICompradorBo compradorBo;
  
    private IProyectoBo proyectoBo;
    private IBitacoraBo bitacoraBo;
    private TProyecto proyectoSeleccionado;
    private IAsesoriaBo asesoriaBo;
    private IClienteProyectoBo iclienteProyectoBo;
    private IEntidadProyectoBo ientidadProyectoBo;
    private List<TArea> listaArea;
    private List<TArea> listaAreaActivos;
    private List<TEmpleadoArea> listaEmpleadoAreas;
    private List<TEmpleado> listaEmpleados;
    private TEmpleado empleadoSelecionada;
    private List<TEmpleado> listaEmpleadosActivos;
    private TAsesoria asesoria;
    private TAsesoria asesoriaSeleccionada;
    private List<TEntidadProyecto> listaEntidadProyecto;
    private List<TClienteProyecto> listaClienteProyecto;
    private Date hoy;
    private boolean estadoFormulario;
    private String msgDui;
    private String msgProyecto;
    private String msgFecha;
    private String msgDescripcion;
    private String msgBeneficiarios;
    private String msgPrecio;
    private IEmpleadoAreaBo empleadoAreaBo;
    private Boolean proyectoPersona;
    private Boolean proyectoCooperativa;
    private Integer idArea;
    private IEmpleadoBo iempleadoBo;
    private IAreaBo areaBo;
    private List<TArea> listaAreas;
    private List<TAsesoria> listaAsesoria;

   

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public List<TProyecto> getListaProyecto() {
        return listaProyecto;
    }

    public void setListaProyecto(List<TProyecto> listaProyecto) {
        this.listaProyecto = listaProyecto;
    }

    public List<TArea> getListaAreaActivos() {
        return listaAreaActivos;
    }

    public void setListaAreaActivos(List<TArea> listaAreaActivos) {
        this.listaAreaActivos = listaAreaActivos;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public IEmpleadoBo getIempleadoBo() {
        return iempleadoBo;
    }

    public void setIempleadoBo(IEmpleadoBo iempleadoBo) {
        this.iempleadoBo = iempleadoBo;
    }

    public IAsesoriaBo getAsesoriaBo() {
        return asesoriaBo;
    }

    public void setAsesoriaBo(IAsesoriaBo asesoriaBo) {
        this.asesoriaBo = asesoriaBo;
    }

    public IProyectoBo getProyectoBo() {
        return proyectoBo;
    }

    public void setProyectoBo(IProyectoBo proyectoBo) {
        this.proyectoBo = proyectoBo;
    }

    public TProyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(TProyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }

    public List<TArea> getListaArea() {
        return this.listaArea = this.areaBo.listAreaActivas(1);
    }

    public void setListaArea(List<TArea> listaArea) {
        this.listaArea = listaArea;
    }

    public List<TEmpleado> getListaEmpleadosActivos() {
        return listaEmpleadosActivos;
    }

    public void setListaEmpleadosActivos(List<TEmpleado> listaEmpleadosActivos) {
        this.listaEmpleadosActivos = listaEmpleadosActivos;
    }

    public Boolean getProyectoPersona() {
        return proyectoPersona;
    }

    public void setProyectoPersona(Boolean proyectoPersona) {
        this.proyectoPersona = proyectoPersona;
    }

    public Boolean getProyectoCooperativa() {
        return proyectoCooperativa;
    }

    public void setProyectoCooperativa(Boolean proyectoCooperativa) {
        this.proyectoCooperativa = proyectoCooperativa;
    }

    public IClienteProyectoBo getIclienteProyectoBo() {
        return iclienteProyectoBo;
    }

    public void setIclienteProyectoBo(IClienteProyectoBo iclienteProyectoBo) {
        this.iclienteProyectoBo = iclienteProyectoBo;
    }

    public IEntidadProyectoBo getIentidadProyectoBo() {
        return ientidadProyectoBo;
    }

    public void setIentidadProyectoBo(IEntidadProyectoBo ientidadProyectoBo) {
        this.ientidadProyectoBo = ientidadProyectoBo;
    }

    public List<TEntidadProyecto> getListaEntidadProyecto() {
        return listaEntidadProyecto;
    }

    public void setListaEntidadProyecto(List<TEntidadProyecto> listaEntidadProyecto) {
        this.listaEntidadProyecto = listaEntidadProyecto;
    }

    public List<TClienteProyecto> getListaClienteProyecto() {
        return listaClienteProyecto;
    }

    public void setListaClienteProyecto(List<TClienteProyecto> listaClienteProyecto) {
        this.listaClienteProyecto = listaClienteProyecto;
    }

    public List<TAsesoria> getListaAsesoria() {
        return listaAsesoria;
    }

    public void setListaAsesoria(List<TAsesoria> listaAsesoria) {
        this.listaAsesoria = listaAsesoria;
    }

    public TAsesoria getAsesoria() {
        return asesoria;
    }

    public void setAsesoria(TAsesoria asesoria) {
        this.asesoria = asesoria;
    }

    public TAsesoria getAsesoriaSeleccionada() {
        return asesoriaSeleccionada;
    }

    public void setAsesoriaSeleccionada(TAsesoria asesoriaSeleccionada) {
        this.asesoriaSeleccionada = asesoriaSeleccionada;
    }

    public TEmpleado getEmpleadoSelecionada() {
        return empleadoSelecionada;
    }

    public void setEmpleadoSelecionada(TEmpleado empleadoSelecionada) {
        this.empleadoSelecionada = empleadoSelecionada;
    }

    public List<TArea> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<TArea> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public List<TEmpleadoArea> getListaEmpleadoAreas() {
        return listaEmpleadoAreas;
    }

    public void setListaEmpleadoAreas(List<TEmpleadoArea> listaEmpleadoAreas) {
        this.listaEmpleadoAreas = listaEmpleadoAreas;
    }

    public List<TEmpleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<TEmpleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public IAreaBo getAreaBo() {
        return areaBo;
    }

    public void setAreaBo(IAreaBo areaBo) {
        this.areaBo = areaBo;
    }

    public Date getHoy() {
        return new Date();
    }

    public void setHoy(Date hoy) {
        this.hoy = hoy;
    }

    public IEmpleadoAreaBo getEmpleadoAreaBo() {
        return empleadoAreaBo;
    }

    public void setEmpleadoAreaBo(IEmpleadoAreaBo empleadoAreaBo) {
        this.empleadoAreaBo = empleadoAreaBo;
    }

    private IEmpleadoBo empleadoBo;

    public IEmpleadoBo getEmpleadoBo() {
        return empleadoBo;
    }

    public void setEmpleadoBo(IEmpleadoBo empleadoBo) {
        this.empleadoBo = empleadoBo;
    }

    public String getMsgDui() {
        return msgDui;
    }

    public void setMsgDui(String msgDui) {
        this.msgDui = msgDui;
    }

    public String getMsgProyecto() {
        return msgProyecto;
    }

    public void setMsgProyecto(String msgProyecto) {
        this.msgProyecto = msgProyecto;
    }

    public String getMsgFecha() {
        return msgFecha;
    }

    public void setMsgFecha(String msgFecha) {
        this.msgFecha = msgFecha;
    }

    public String getMsgDescripcion() {
        return msgDescripcion;
    }

    public void setMsgDescripcion(String msgDescripcion) {
        this.msgDescripcion = msgDescripcion;
    }

    public String getMsgBeneficiarios() {
        return msgBeneficiarios;
    }

    public void setMsgBeneficiarios(String msgBeneficiarios) {
        this.msgBeneficiarios = msgBeneficiarios;
    }

    public String getMsgPrecio() {
        return msgPrecio;
    }

    public void setMsgPrecio(String msgPrecio) {
        this.msgPrecio = msgPrecio;
    }

    public boolean isEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public ICompradorBo getCompradorBo() {
        return compradorBo;
    }

    public void setCompradorBo(ICompradorBo compradorBo) {
        this.compradorBo = compradorBo;
    }

    public void updateListaAsesorias() {

        this.listaAsesoria = this.asesoriaBo.listAsesoria();

    }

    public void init() {

        this.empleadoSelecionada = new TEmpleado();
        this.proyectoSeleccionado = new TProyecto();
        this.proyectoSeleccionado.setCodigoProyecto("");
        this.empleadoSelecionada.setDuiEmpleado("");
        this.asesoriaSeleccionada = new TAsesoria();
        this.asesoriaSeleccionada.setTEmpleado(new TEmpleado());
        this.asesoriaSeleccionada.setTProyecto(new TProyecto());
        this.asesoria = new TAsesoria();
        this.asesoria.setTEmpleado(new TEmpleado());
        this.asesoria.setTProyecto(new TProyecto());
        super.enableShowData();

    }
    
    public void crear() {

        this.asesoria.setTEmpleado(empleadoSelecionada);
        this.asesoria.setTProyecto(proyectoSeleccionado);
        this.asesoria.setEstado(true);
        this.asesoriaBo.create(asesoria);
        
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_asesoria");
        auxBitacora.setAccionBitacora("Agregar asesoria");
        auxBitacora.setDatosBitacora("Precio:" + this.asesoria.getPrecio()
                + ", Benficiarios: " + this.asesoria.getBeneficiarios()
                + ", Proyecto: " + this.asesoria.getTProyecto().getCodigoProyecto()
                );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.asesoriaSeleccionada.getIdasesoria());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Asesoria guardada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        limpiar();

    }

    public void modificar() {

        this.asesoriaSeleccionada.setTEmpleado(empleadoSelecionada);
        this.asesoriaSeleccionada.setTProyecto(proyectoSeleccionado);
        this.asesoriaBo.update(this.asesoriaSeleccionada);
         TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_asesoria");
        auxBitacora.setAccionBitacora("Modificar asesoria");
        auxBitacora.setDatosBitacora("Precio:" + this.asesoriaSeleccionada.getPrecio()
                + ", Benficiarios: " + this.asesoriaSeleccionada.getBeneficiarios()
                + ", Proyecto: " + this.asesoriaSeleccionada.getTProyecto().getCodigoProyecto()
                );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.asesoriaSeleccionada.getIdasesoria());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Asesoria modificada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        super.enableShowData();
        limpiar();
    }

    public void darDeBaja() {

        this.asesoriaSeleccionada.setEstado(false);
        this.asesoriaBo.update(this.asesoriaSeleccionada);
         TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_asesoria");
        auxBitacora.setAccionBitacora("Dar de baja asesoria");
        auxBitacora.setDatosBitacora("Precio:" + this.asesoriaSeleccionada.getPrecio()
                + ", Benficiarios: " + this.asesoriaSeleccionada.getBeneficiarios()
                + ", Proyecto: " + this.asesoriaSeleccionada.getTProyecto().getCodigoProyecto()
                );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.asesoriaSeleccionada.getIdasesoria());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Asesoria dada de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        super.enableShowData();

    }

    public void darDeAlta() {

        this.asesoriaSeleccionada.setEstado(true);
        this.asesoriaBo.update(this.asesoriaSeleccionada);
        
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_asesoria");
        auxBitacora.setAccionBitacora("Dar de alta asesoria");
        auxBitacora.setDatosBitacora("Precio:" + this.asesoriaSeleccionada.getPrecio()
                + ", Benficiarios: " + this.asesoriaSeleccionada.getBeneficiarios()
                + ", Proyecto: " + this.asesoriaSeleccionada.getTProyecto().getCodigoProyecto()
                );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.asesoriaSeleccionada.getIdasesoria());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Asesoria dada de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
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
        if (this.proyectoSeleccionado.getCodigoProyecto().equals("")) {
            this.msgProyecto = "Debe seleccionar un proyecto";
            this.estadoFormulario = false;
        } else {
            this.msgProyecto = "";
        }
        if (this.asesoria.getDescripcion() == "") {
            this.msgDescripcion = "Debe introducir una descripción";
            this.estadoFormulario = false;
        } else {
            this.msgDescripcion = "";
        }
        if (this.asesoria.getPrecio() == null || this.asesoria.getPrecio().compareTo(BigDecimal.ZERO) < 0) {
            this.msgPrecio = "Debe introducir un precio";
            this.estadoFormulario = false;
        } else {
            this.msgPrecio = "";
        }
        if (this.asesoria.getBeneficiarios() == null || this.asesoria.getBeneficiarios() <= 0) {
            this.msgBeneficiarios = "Debe introducir los beneficiarios";
            this.estadoFormulario = false;
        } else {
            this.msgBeneficiarios = "";
        }
        if (this.empleadoSelecionada.getDuiEmpleado().equals("")) {
            this.msgDui = "Debe seleccionar un encargado";
            this.estadoFormulario = false;
        } else {
            this.msgDui = "";
        }
        if (this.asesoria.getFecha() == null) {
            this.msgFecha = "Debe introducir la fecha";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }

    }

    public void validarFormularioModificar() {

        this.estadoFormulario = true;
        if (this.proyectoSeleccionado.getCodigoProyecto().equals("")) {
            this.msgProyecto = "Debe seleccionar un proyecto";
            this.estadoFormulario = false;
        } else {
            this.msgProyecto = "";
        }
        if (this.asesoriaSeleccionada.getDescripcion() == "") {
            this.msgDescripcion = "Debe introducir una descripción";
            this.estadoFormulario = false;
        } else {
            this.msgDescripcion = "";
        }
        if (this.asesoriaSeleccionada.getPrecio() == null || this.asesoriaSeleccionada.getPrecio().compareTo(BigDecimal.ZERO) < 0) {
            this.msgPrecio = "Debe introducir un precio";
            this.estadoFormulario = false;
        } else {
            this.msgPrecio = "";
        }
        if (this.asesoriaSeleccionada.getBeneficiarios() == null || this.asesoriaSeleccionada.getBeneficiarios() <= 0) {
            this.msgBeneficiarios = "Debe introducir los beneficiarios";
            this.estadoFormulario = false;
        } else {
            this.msgBeneficiarios = "";
        }
        if (this.empleadoSelecionada.getDuiEmpleado().equals("")) {
            this.msgDui = "Debe seleccionar un encargado";
            this.estadoFormulario = false;
        } else {
            this.msgDui = "";
        }
        if (this.asesoriaSeleccionada.getFecha() == null) {
            this.msgFecha = "Debe introducir la fecha";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }

    }

    public void limpiar() {

        this.estadoFormulario = false;
        this.msgDui = "";
        this.msgBeneficiarios = "";
        this.msgPrecio = "";
        this.msgFecha = "";
        this.msgDescripcion = "";
        this.msgProyecto = "";
        this.proyectoSeleccionado = new TProyecto();
        this.proyectoSeleccionado.setCodigoProyecto("");
        this.empleadoSelecionada = new TEmpleado();
        this.empleadoSelecionada.setDuiEmpleado("");
        this.asesoria = new TAsesoria();
        this.idArea = 0;

    }

    public void enableShowDataBean() {

        limpiar();
        super.enableShowData();

    }

    public void mostrarProyectos() {

        this.listaEntidadProyecto = this.ientidadProyectoBo.listTEndidadProyectosAprovados(0);

    }

    public void mostrarAsesorias() {

        this.listaAsesoria = this.asesoriaBo.listAsesoria();
        this.enableShowData();

    }

    public void mostrarEmpleados() {

        this.listaEmpleados = new ArrayList<TEmpleado>();
        this.listaEmpleados = this.empleadoBo.listEmpleadosActivos(this.idArea);

    }

    public void verReporteAsesoria() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_cliente", this.asesoriaSeleccionada.getIdasesoria());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/asesoria.jasper"));
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
        auxBitacora.setTableBitacora("t_asesoria");
        auxBitacora.setAccionBitacora("Generar reporte de asesoria");
        auxBitacora.setDatosBitacora("Precio:" + this.asesoriaSeleccionada.getPrecio()
                + ", Benficiarios: " + this.asesoriaSeleccionada.getBeneficiarios()
                + ", Proyecto: " + this.asesoriaSeleccionada.getTProyecto().getCodigoProyecto()
                );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.asesoriaSeleccionada.getIdasesoria());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verReporteAsesoriaPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_cliente", this.asesoriaSeleccionada.getIdasesoria());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/asesoria.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Asesoria.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_asesoria");
        auxBitacora.setAccionBitacora("Descargar reporte de asesoria");
        auxBitacora.setDatosBitacora("Precio:" + this.asesoriaSeleccionada.getPrecio()
                + ", Benficiarios: " + this.asesoriaSeleccionada.getBeneficiarios()
                + ", Proyecto: " + this.asesoriaSeleccionada.getTProyecto().getCodigoProyecto()
                );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.asesoriaSeleccionada.getIdasesoria());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }
    
    private TEmpleadoArea empleadoArea;

    public TEmpleadoArea getEmpleadoArea() {
        return empleadoArea;
    }

    public void setEmpleadoArea(TEmpleadoArea empleadoArea) {
        this.empleadoArea = empleadoArea;
    }
    
    public void cargarAsesoria(){
        
        
        this.enableShowUpdate();
        this.empleadoArea = this.empleadoAreaBo.getTEmpleadoArea(1, this.empleadoSelecionada.getIdEmpleado());
        this.idArea= this.empleadoArea.getTArea().getIdArea();
        
        
        
    }

}
