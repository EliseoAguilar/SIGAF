/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IAreaBo;
import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TEntidad;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.FileInputStream;
//import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.HibernateException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Genoves
 */
@Named(value = "entidadBean")

@SessionScoped
@ManagedBean
public class EntidadBean extends Actividad {

    private TEntidad Entidad;
    private TArea areaSocio;
    private TEntidad Entidadeleccionada;
    private List<TEntidad> listaEntidades;
    private IEntidadBo ientidadBo;
    private IAreaBo iareaBo;
    private Boolean estadoFormularioE;
    private String codigoAuxiliar;
    private boolean estadoActa;
    private boolean estadoLogo;
    //ENTIDAD VALIDACION
    private String msgCodigoE; //validar nombre    
    private String msgNombreE; //validar nombre
    private String msgGiroE; //validar mision
    private String msgCorreoE; //validar vision
    private String msgDireccionE; //validar telefono
    private String msgNitE; //validar movil
    private String msgCalificacionE; //validar direccion
    private String msgRepresentanteE; //validar nombre    
    private String msgTelefonoE; //validar nombre
    private String msgMovilE; //validar mision
    private String msgMisionE; //validar vision
    private String msgVisionE; //validar telefono
    private String msgActaE; //validar movil
    private String msgCredencialE; //validar direccion
    private String msgLogoE; //validar nombre    
    private String msgDuiRepresentanteE; //validar nombre
    private String msgNitRepresentanteE; //validar mision
    private String msgAbreviacionE; //validar vision
    private String coficacionE;
    private List<SelectItem> selectOneItemEntidades;
    //FIND DE ENTIDAD VALIDACION
    //PERFIL
    private String msgNombre; //validar nombre
    private String msgMision; //validar mision
    private String msgVision; //validar vision
    private String msgTelefono; //validar telefono
    private String msgMovil; //validar movil
    private String msgDireccion; //validar direccion
    private String msgCorreo; //validar correo
    private TEntidad datosFundacion;
    private TEntidad datos;
    private Boolean showImagen;
    private Boolean showImagenLogo;
    private Boolean showImagenActa;
    private Boolean showImagenRepre;
    private Boolean showImagenCredencial;
    private Boolean estadoFormulario;
    private String logo;
    //final File foto = new File();
    private UploadedFile img;
    private IBitacoraBo bitacoraBo;

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public TArea getAreaSocio() {
        return areaSocio;
    }

    public void setAreaSocio(TArea areaSocio) {
        this.areaSocio = areaSocio;
    }

    public IAreaBo getIareaBo() {
        return iareaBo;
    }

    public void setIareaBo(IAreaBo iareaBo) {
        this.iareaBo = iareaBo;
    }

    public boolean isEstadoActa() {
        return estadoActa;
    }

    public void setEstadoActa(boolean estadoActa) {
        this.estadoActa = estadoActa;
    }

    public boolean isEstadoLogo() {
        return estadoLogo;
    }

    public void setEstadoLogo(boolean estadoLogo) {
        this.estadoLogo = estadoLogo;
    }

    public String getCodigoAuxiliar() {
        return codigoAuxiliar;
    }

    public void setCodigoAuxiliar(String codigoAuxiliar) {
        this.codigoAuxiliar = codigoAuxiliar;
    }

    public Boolean getEstadoFormularioE() {
        return estadoFormularioE;
    }

    public void setEstadoFormularioE(Boolean estadoFormularioE) {
        this.estadoFormularioE = estadoFormularioE;
    }

    public TEntidad getEntidad() {
        return Entidad;
    }

    public void setEntidad(TEntidad Entidad) {
        this.Entidad = Entidad;
    }

    public TEntidad getEntidadeleccionada() {
        return Entidadeleccionada;
    }

    public void setEntidadeleccionada(TEntidad Entidadeleccionada) {
        this.Entidadeleccionada = Entidadeleccionada;
    }

    public List<TEntidad> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(List<TEntidad> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    public String getCoficacionE() {
        return coficacionE;
    }

    public void setCoficacionE(String coficacionE) {
        this.coficacionE = coficacionE;
    }

    public String getMsgCodigoE() {
        return msgCodigoE;
    }

    public void setMsgCodigoE(String msgCodigoE) {
        this.msgCodigoE = msgCodigoE;
    }

    public String getMsgNombreE() {
        return msgNombreE;
    }

    public void setMsgNombreE(String msgNombreE) {
        this.msgNombreE = msgNombreE;
    }

    public String getMsgGiroE() {
        return msgGiroE;
    }

    public void setMsgGiroE(String msgGiroE) {
        this.msgGiroE = msgGiroE;
    }

    public String getMsgCorreoE() {
        return msgCorreoE;
    }

    public void setMsgCorreoE(String msgCorreoE) {
        this.msgCorreoE = msgCorreoE;
    }

    public String getMsgDireccionE() {
        return msgDireccionE;
    }

    public void setMsgDireccionE(String msgDireccionE) {
        this.msgDireccionE = msgDireccionE;
    }

    public String getMsgNitE() {
        return msgNitE;
    }

    public void setMsgNitE(String msgNitE) {
        this.msgNitE = msgNitE;
    }

    public String getMsgCalificacionE() {
        return msgCalificacionE;
    }

    public void setMsgCalificacionE(String msgCalificacionE) {
        this.msgCalificacionE = msgCalificacionE;
    }

    public String getMsgRepresentanteE() {
        return msgRepresentanteE;
    }

    public void setMsgRepresentanteE(String msgRepresentanteE) {
        this.msgRepresentanteE = msgRepresentanteE;
    }

    public String getMsgTelefonoE() {
        return msgTelefonoE;
    }

    public void setMsgTelefonoE(String msgTelefonoE) {
        this.msgTelefonoE = msgTelefonoE;
    }

    public String getMsgMovilE() {
        return msgMovilE;
    }

    public void setMsgMovilE(String msgMovilE) {
        this.msgMovilE = msgMovilE;
    }

    public String getMsgMisionE() {
        return msgMisionE;
    }

    public void setMsgMisionE(String msgMisionE) {
        this.msgMisionE = msgMisionE;
    }

    public String getMsgVisionE() {
        return msgVisionE;
    }

    public void setMsgVisionE(String msgVisionE) {
        this.msgVisionE = msgVisionE;
    }

    public String getMsgActaE() {
        return msgActaE;
    }

    public void setMsgActaE(String msgActaE) {
        this.msgActaE = msgActaE;
    }

    public String getMsgCredencialE() {
        return msgCredencialE;
    }

    public void setMsgCredencialE(String msgCredencialE) {
        this.msgCredencialE = msgCredencialE;
    }

    public String getMsgLogoE() {
        return msgLogoE;
    }

    public void setMsgLogoE(String msgLogoE) {
        this.msgLogoE = msgLogoE;
    }

    public String getMsgDuiRepresentanteE() {
        return msgDuiRepresentanteE;
    }

    public void setMsgDuiRepresentanteE(String msgDuiRepresentanteE) {
        this.msgDuiRepresentanteE = msgDuiRepresentanteE;
    }

    public String getMsgNitRepresentanteE() {
        return msgNitRepresentanteE;
    }

    public void setMsgNitRepresentanteE(String msgNitRepresentanteE) {
        this.msgNitRepresentanteE = msgNitRepresentanteE;
    }

    public String getMsgAbreviacionE() {
        return msgAbreviacionE;
    }

    public void setMsgAbreviacionE(String msgAbreviacionE) {
        this.msgAbreviacionE = msgAbreviacionE;
    }

    public Boolean getShowImagenRepre() {
        return showImagenRepre;
    }

    public void setShowImagenRepre(Boolean showImagenRepre) {
        this.showImagenRepre = showImagenRepre;
    }

    public Boolean getShowImagenActa() {
        return showImagenActa;
    }

    public void setShowImagenActa(Boolean showImagenActa) {
        this.showImagenActa = showImagenActa;
    }

    public Boolean getShowImagenLogo() {
        return showImagenLogo;
    }

    public void setShowImagenLogo(Boolean showImagenLogo) {
        this.showImagenLogo = showImagenLogo;
    }

    public Boolean getShowImagenCredencial() {
        return showImagenCredencial;
    }

    public void setShowImagenCredencial(Boolean showImagenCredencial) {
        this.showImagenCredencial = showImagenCredencial;
    }

    // FIN PERFIL
    public Boolean getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
    }

    public Boolean getShowImagen() {
        return showImagen;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setShowImagen(Boolean showImagen) {
        this.showImagen = showImagen;
    }

    public void updateDatos() {
        this.datos = this.ientidadBo.getTEntidad(1);

    }

    public void Init() {
        this.Entidad = new TEntidad();
        this.Entidadeleccionada = new TEntidad();
        super.setShowCreate(true);
        super.setShowData(false);
        super.setShowUpdate(false);
        //PERFIL
        this.datosFundacion = new TEntidad();
        //this.img = new UploadedFile();
        this.showImagen = false;
        this.showImagenCredencial = false;
        this.showImagenLogo = false;
        this.showImagenActa = false;
        this.showImagenRepre = false;
        this.codigoAuxiliar = "";
        //FIN PERFIL
    }

    public List<SelectItem> getSelectOneItemEntidades() {
        this.selectOneItemEntidades = new ArrayList<SelectItem>();
        List<TEntidad> entidades = ientidadBo.listTEndidad();
        for (TEntidad entidad : entidades) {
            if (entidad.getEstadoEntidad() == true) {
                SelectItem selectItem = new SelectItem(entidad.getIdEntidad(), entidad.getNombreEntidad());
                this.selectOneItemEntidades.add(selectItem);
            }
        }
        return selectOneItemEntidades;
    }

    public void setSelectOneItemEntidades(List<SelectItem> selectOneItemEntidades) {
        this.selectOneItemEntidades = selectOneItemEntidades;
    }

    public TEntidad getDatos() {

        return datos;
    }

    public void setDatos(TEntidad datos) {
        this.datos = datos;
    }

    public TEntidad getDatosFundacion() {
        return datosFundacion;
    }

    public void setDatosFundacion(TEntidad datosFundacion) {
        this.datosFundacion = datosFundacion;

    }

    public IEntidadBo getIentidadBo() {
        return ientidadBo;
    }

    public void setIentidadBo(IEntidadBo ientidadBo) {
        this.ientidadBo = ientidadBo;
    }

    public void enableShowImagen() {
        this.estadoFormulario = false;
        this.setShowImagen(!this.getShowImagen());
    }

    public void enableShowImagenCredencial() {
        this.estadoFormularioE = false;
        this.setShowImagenCredencial(!this.getShowImagenCredencial());
    }

    public void enableShowImagenRepre() {
        this.estadoFormularioE = false;
        this.setShowImagenRepre(!this.getShowImagenRepre());
    }

    public void enableShowImagenLogo() {
        this.estadoFormularioE = false;
        this.setShowImagenLogo(!this.getShowImagenLogo());
    }

    public void enableShowImagenActa() {
        this.estadoFormularioE = false;
        this.setShowImagenActa(!this.getShowImagenActa());
    }
    //VALIDACIONES

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public String getMsgMision() {
        return msgMision;
    }

    public void setMsgMision(String msgMision) {
        this.msgMision = msgMision;
    }

    public String getMsgVision() {
        return msgVision;
    }

    public void setMsgVision(String msgVision) {
        this.msgVision = msgVision;
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

    public String getMsgCorreo() {
        return msgCorreo;
    }

    public void setMsgCorreo(String msgCorreo) {
        this.msgCorreo = msgCorreo;
    }

    public void crear() {
        this.Entidad.setEstadoEntidad(true);
        ientidadBo.create(this.Entidad);
        this.areaSocio = new TArea();
        this.areaSocio.setNombreArea("Socios");
        this.areaSocio.setEstadoArea(true);
        this.areaSocio.setCodigoArea("");
        this.areaSocio.setTEntidad(this.Entidad);
        this.iareaBo.create(this.areaSocio);
        this.listaEntidades = ientidadBo.listTEndidad();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_entidad");
        auxBitacora.setAccionBitacora("Crear entidad");
        auxBitacora.setDatosBitacora("Nombre: " + this.Entidad.getNombreEntidad() + ", Código: " + this.Entidad.getCodigoEntidad());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.Entidad.getIdEntidad());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cooperativa guardada correctamente", null);
        FacesContext.getCurrentInstance().addMessage("" + this.Entidad.getCredencialEntidad(), mensaje);
        limpiarEntidad();
    }

    public void modificarPerfil() {
        //Copies bytes to destination.
        this.showImagen = false;
        try {
            this.ientidadBo.update(datos);
            this.estadoFormulario = false;
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_entidad");
            auxBitacora.setAccionBitacora("Modificar perfil");
            auxBitacora.setDatosBitacora("Nombre: " + this.datos.getNombreEntidad() + ", Código: " + this.datos.getCodigoEntidad());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.datos.getIdEntidad());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Perfil modificado correctamente"));
        } catch (HibernateException he) {
        }
    }

    public void limpiar() {
        System.out.println("limpiando");
        this.datos.setLogoEntidad(this.datos.getLogoEntidad());
        this.estadoFormulario = false;
    }

    public void guardarImagen(FileUploadEvent event) throws IOException {
        String tipo = event.getFile().getContentType();
        byte[] content = event.getFile().getContents();
        String b64 = Base64.encode(content);
        String guardar = "data:" + tipo + ";base64," + b64;
        this.datos.setLogoEntidad(guardar);
        this.showImagen = false;
        FacesMessage message = new FacesMessage("Logo Cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void guardarCredencial(FileUploadEvent event) throws IOException {
        String tipoL = event.getFile().getContentType();
        byte[] contentL = event.getFile().getContents();
        String b64L = Base64.encode(contentL);
        String guardarL = "data:" + tipoL + ";base64," + b64L;
        this.Entidad.setCredencialEntidad(guardarL);
        this.showImagenCredencial = true;
        FacesMessage message = new FacesMessage("Credencia cargada");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void guardarLogo(FileUploadEvent event) throws IOException {
        String tipoP = event.getFile().getContentType();
        byte[] contentP = event.getFile().getContents();
        String b64P = Base64.encode(contentP);
        String guardarP = "data:" + tipoP + ";base64," + b64P;
        this.Entidad.setLogoEntidad(guardarP);
        this.showImagenLogo = true;
        FacesMessage message = new FacesMessage("Logo cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void guardarActa(FileUploadEvent event) throws IOException {
        String tipoA = event.getFile().getContentType();
        byte[] contentA = event.getFile().getContents();
        String b64A = Base64.encode(contentA);
        String guardarA = "data:" + tipoA + ";base64," + b64A;
        String base64encodedString = Base64.encode(contentA);
        System.out.println("Base64 Encoded String (Basic) :" + b64A);
        this.Entidad.setActaEntidad(guardarA);
        this.showImagenActa = true;
        FacesMessage message = new FacesMessage("Acta cargada");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void guardarCredencialModificar(FileUploadEvent event) throws IOException {
        String tipoM = event.getFile().getContentType();
        byte[] contentM = event.getFile().getContents();
        String b64M = Base64.encode(contentM);
        String guardarM = "data:" + tipoM + ";base64," + b64M;
        this.Entidadeleccionada.setCredencialEntidad(guardarM);
        this.showImagenCredencial = false;
        FacesMessage message = new FacesMessage("Credencial cargada");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void guardarLogoModificar(FileUploadEvent event) throws IOException {
        String tipoLM = event.getFile().getContentType();
        byte[] contentLM = event.getFile().getContents();
        String b64LM = Base64.encode(contentLM);
        String guardarLM = "data:" + tipoLM + ";base64," + b64LM;
        this.Entidadeleccionada.setLogoEntidad(guardarLM);
        this.showImagenLogo = false;
        FacesMessage message = new FacesMessage("Logo cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void guardarActaModificar(FileUploadEvent event) throws IOException {
        String tipoAM = event.getFile().getContentType();
        byte[] contentAM = event.getFile().getContents();
        String b64AM = Base64.encode(contentAM);
        String guardarAM = "data:" + tipoAM + ";base64," + b64AM;
        this.Entidadeleccionada.setActaEntidad(guardarAM);
        this.showImagenActa = false;
        FacesMessage message = new FacesMessage("Acta cargada");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //INICIO DE METODOS DE ENTIDAD
    public void modificar() {
        ientidadBo.update(this.Entidadeleccionada);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cooperativa modificada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaEntidades = ientidadBo.listTEndidad();
        limpiarEntidad();
        super.enableShowData();
    }

    public void darDeBaja() {
        this.Entidadeleccionada.setEstadoEntidad(false);
        ientidadBo.update(this.Entidadeleccionada);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cooperativa dada de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaEntidades = ientidadBo.listTEndidad();
        super.enableShowData();
    }

    public void darDeAlta() {
        this.Entidadeleccionada.setEstadoEntidad(true);
        ientidadBo.update(this.Entidadeleccionada);
        
        
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cooperativa dada de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaEntidades = ientidadBo.listTEndidad();
        super.enableShowData();
    }

    //
    public void validarFormulario() {
        this.estadoFormulario = true;
        if (this.datos.getNombreEntidad().length() < 5) {
            this.msgNombre = "El nombre debe contener como minimo 5 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        if (this.datos.getMisionEntidad().length() < 30) {
            this.msgMision = "La misión debe contener como mínimo 30 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgMision = "";
        }
        if (this.datos.getVisionEntidad().length() < 30) {
            this.msgVision = "La visión debe contener como mínimo 30 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgVision = "";
        }
        if (this.datos.getCorreoEntidad().length() < 15) {
            this.msgCorreo = "El correo debe contener como minimo 15 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgCorreo = "";
        }
        if (this.datos.getTelefonoEntidad().length() < 9) {
            this.msgTelefono = "Complete el número de teléfono";
            this.estadoFormulario = false;
        } else {
            this.msgTelefono = "";
        }
        if (this.datos.getCelularEntidad().length() < 9) {
            this.msgMovil = "Complete el número de móvil";
            this.estadoFormulario = false;
        } else {
            this.msgMovil = "";
        }
        if (this.datos.getDireccionEntidad().length() < 15) {
            this.msgDireccion = "La dirección debe contener como mínimo 15 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgDireccion = "";
        }
    }

    public void validarFormularioEntidad() {
        this.estadoFormularioE = true;
        if (this.Entidad.getCodigoEntidad().length() == 0) {
            this.msgCodigoE = "El codigo es requerido";
            this.estadoFormularioE = false;
        } else if (this.ientidadBo.getTEntidadCodigo(this.Entidad.getCodigoEntidad(), "")) {
            this.msgCodigoE = "Ya se registro una entidad con ese codigo";
            this.estadoFormularioE = false;

        } else {
            this.msgCodigoE = "";
        }
        if (this.Entidad.getNombreEntidad().length() < 3) {
            this.msgNombreE = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgNombreE = "";
        }
        if (this.Entidad.getAbreviacionEntidad().length() < 3) {
            this.msgAbreviacionE = "El nombre corto debe contener como minimo 3 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgAbreviacionE = "";
        }
        if (this.Entidad.getNitEntidad().length() < 14) {
            this.msgNitE = "El NIT es requerido";
            this.estadoFormularioE = false;
        } else {
            this.msgNitE = "";
        }
        if (this.Entidad.getGiroEntidad().length() < 3) {
            this.msgGiroE = "El giro debe contener como minimo 3 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgGiroE = "";
        }
        if (this.Entidad.getNombreRepresentanteEntidad().length() < 3) {
            this.msgRepresentanteE = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgRepresentanteE = "";
        }
        if (this.Entidad.getDuiRepresentanteEntidad().length() < 9) {
            this.msgDuiRepresentanteE = "El DUI debe contener 9 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgDuiRepresentanteE = "";
        }
        if (this.Entidad.getNitRepresentanteEntidad().length() < 14) {
            this.msgNitRepresentanteE = "El NIT debe contener 14 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgNitRepresentanteE = "";
        }
        if (this.Entidad.getMisionEntidad().length() < 10) {
            this.msgMisionE = "La mision es requerida";
            this.estadoFormularioE = false;
        } else {
            this.msgMisionE = "";
        }
        if (this.Entidad.getVisionEntidad().length() < 10) {
            this.msgVisionE = "La vision es requerida";
            this.estadoFormularioE = false;
        } else {
            this.msgVisionE = "";
        }
        if (this.Entidad.getTelefonoEntidad().length() < 8) {
            this.msgTelefonoE = "El telefono debe contener 8 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgTelefonoE = "";
        }
        if (this.Entidad.getCelularEntidad().length() < 8) {
            this.msgMovilE = "El movil debe contener 8 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgMovilE = "";
        }
        if (this.Entidad.getDireccionEntidad().length() < 8) {
            this.msgDireccionE = "La direcion es requeridad";
            this.estadoFormularioE = false;
        } else {
            this.msgDireccionE = "";
        }
    }

    public void validarFormularioModificar() {
        this.estadoFormularioE = true;
        if (this.Entidadeleccionada.getCodigoEntidad().length() == 0) {
            this.msgCodigoE = "El codigo es requerido";
            this.estadoFormularioE = false;
        } else if (this.codigoAuxiliar.equals(this.Entidadeleccionada.getCodigoEntidad())) {
            this.msgCodigoE = "";
        } else if (this.ientidadBo.getTEntidadCodigo(this.Entidadeleccionada.getCodigoEntidad(), this.codigoAuxiliar)) {
            this.msgCodigoE = "Ya se registro una entidad con ese codigo";
            this.estadoFormularioE = false;
        }
        if (this.Entidadeleccionada.getNombreEntidad()
                .length() < 3) {
            this.msgNombreE = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgNombreE = "";
        }
        if (this.Entidadeleccionada.getAbreviacionEntidad()
                .length() < 3) {
            this.msgAbreviacionE = "El nombre corto debe contener como minimo 3 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgAbreviacionE = "";
        }
        if (this.Entidadeleccionada.getNitEntidad()
                .length() < 14) {
            this.msgNitE = "El NIT es requerido";
            this.estadoFormularioE = false;
        } else {
            this.msgNitE = "";
        }
        if (this.Entidadeleccionada.getGiroEntidad()
                .length() < 3) {
            this.msgGiroE = "El giro debe contener como minimo 3 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgGiroE = "";
        }
        if (this.Entidadeleccionada.getNombreRepresentanteEntidad()
                .length() < 3) {
            this.msgRepresentanteE = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgRepresentanteE = "";
        }
        if (this.Entidadeleccionada.getDuiRepresentanteEntidad()
                .length() < 9) {
            this.msgDuiRepresentanteE = "El DUI debe contener 9 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgDuiRepresentanteE = "";
        }
        if (this.Entidadeleccionada.getNitRepresentanteEntidad()
                .length() < 14) {
            this.msgNitRepresentanteE = "El NIT debe contener 14 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgNitRepresentanteE = "";
        }
        if (this.Entidadeleccionada.getMisionEntidad()
                .length() < 10) {
            this.msgMisionE = "La mision es requerida";
            this.estadoFormularioE = false;
        } else {
            this.msgMisionE = "";
        }

        if (this.Entidadeleccionada.getVisionEntidad()
                .length() < 10) {
            this.msgVisionE = "La vision es requerida";
            this.estadoFormularioE = false;
        } else {
            this.msgVisionE = "";
        }
        if (this.Entidadeleccionada.getTelefonoEntidad()
                .length() < 8) {
            this.msgTelefonoE = "El telefono debe contener 8 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgTelefonoE = "";
        }
        if (this.Entidadeleccionada.getCelularEntidad()
                .length() < 8) {
            this.msgMovilE = "El movil debe contener 8 caracteres";
            this.estadoFormularioE = false;
        } else {
            this.msgMovilE = "";
        }
        if (this.Entidadeleccionada.getDireccionEntidad()
                .length() < 8) {
            this.msgDireccionE = "La direcion es requeridad";
            this.estadoFormularioE = false;
        } else {
            this.msgDireccionE = "";
        }
    }

    public void limpiarEntidad() {
        this.estadoFormularioE = false;
        this.msgCodigoE = "";
        this.msgNombreE = "";
        this.msgAbreviacionE = "";
        this.msgNitE = "";
        this.msgGiroE = "";
        this.msgRepresentanteE = "";
        this.msgDuiRepresentanteE = "";
        this.msgNitRepresentanteE = "";
        this.msgMisionE = "";
        this.msgVisionE = "";
        this.msgTelefonoE = "";
        this.msgMovilE = "";
        this.msgDireccionE = "";
        this.Entidad = new TEntidad();
        this.showImagenActa = false;
        this.showImagenCredencial = false;
        this.showImagenLogo = false;
        this.showImagenRepre = false;
        this.codigoAuxiliar = "";
        this.listaEntidades = ientidadBo.listTEndidad();
    }

    public void enableShowDataBean() {
        super.enableShowData();
        limpiarEntidad();
    }

    public void enableShowUpdateBean() {

    }
}
