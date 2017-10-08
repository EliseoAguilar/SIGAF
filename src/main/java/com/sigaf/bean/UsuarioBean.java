/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IEmpleadoBo;
import com.sigaf.Ibo.IUsuarioBo;
import com.sigaf.bo.AreaBo;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEmpleadoArea;
import com.sigaf.pojo.TUsuario;
import com.sun.mail.iap.Response;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
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
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Eliseo
 */
@ManagedBean
@SessionScoped
public class UsuarioBean extends Actividad {

    private IBitacoraBo bitacoraBo;

    private TUsuario usuario;

    private TEmpleado empleado;

    private TUsuario usuarioSelecionado;

    private IEmpleadoBo empleadoBo;

    private IUsuarioBo usuarioBo;

    private AreaBo areaBo;

    private List<TArea> listaArea;

    private List<TEmpleado> listaEmpleados;

    private List<TUsuario> listaUsuarios;

    private Integer idEmpleado;

    private Integer idArea;

    private String msgEmpleado;

    private String msgArea;

    private String msgTipo;

    private String msgNombre;

    private String msgClave;

    private String msgClaveConfir;
    
    private String msgImagen;

    private Boolean estadoFormulario;

    private String claveConfir;

    private String clave;

    private String Nombrbe;

    private Boolean editPass;

    private Boolean showImagen;
    
    private Boolean imagenEmpty;

    public void updateListaUsuarios() {
        this.listaUsuarios = this.usuarioBo.listUsuario();
    }

    public void updateListaArea() {
        /* Dado que la fundacion tiene el ID 1 */
        this.listaArea = this.areaBo.listArea(1);
    }

    public void setIdArea(Integer idArea) {

        this.idArea = idArea;
        /* Consultamos las los empleados atrabes de EmpleadoArea 1 = Empleados 2- Socios*/
        this.listaEmpleados = this.empleadoBo.listEmpleadosOrSocios(idArea, 1);

    }

    /**
     * Metodo que Busca el Area al que corresponde El usuario
     *
     * @param emp
     */
    public void buscarEmpleadoArea(TEmpleado emp) {

        List<TEmpleadoArea> listEmpleAreAct = new ArrayList(emp.getTEmpleadoAreas());
        /* Asignamos el ID del area */
        this.idArea = listEmpleAreAct.get(0).getTArea().getIdArea();
        /* Consultamos las los empleados atrabes de EmpleadoArea 1 = Empleados 2- Socios*/
        this.listaEmpleados = this.empleadoBo.listEmpleadosOrSocios(idArea, 1);

    }

    public void setUsuarioSelecionado(TUsuario usuarioSelecionado) {

        this.usuarioSelecionado = usuarioSelecionado;

        this.editPass = false;

        this.buscarEmpleadoArea(usuarioSelecionado.getTEmpleado());

        this.claveConfir = "";

        this.clave = "";

        this.Nombrbe = usuarioSelecionado.getNombreUsuario();
        
        this.empleado = this.usuarioSelecionado.getTEmpleado();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_usuario");
        auxBitacora.setAccionBitacora("Ver información de usuario");
        auxBitacora.setDatosBitacora("Usuario:" + usuarioSelecionado.getNombreUsuario()
                + ", Tipo: " + usuarioSelecionado.getTipoUsuario()
                + ", Empleado: " + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado());
        auxBitacora.setIdTableBitacora(usuarioSelecionado.getIdUsuario());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

        bitacoraBo.create(auxBitacora);

    }
    
    public String getMsgImagen() {
        return msgImagen;
    }

    public void setMsgImagen(String msgImagen) {
        this.msgImagen = msgImagen;
    }

    public Boolean getImagenEmpty() {
        return imagenEmpty;
    }

    public void setImagenEmpty(Boolean imagenEmpty) {
        this.imagenEmpty = imagenEmpty;
    }

    public TEmpleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(TEmpleado empleado) {
        this.empleado = empleado;
    }

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public String getNombrbe() {
        return Nombrbe;
    }

    public void setNombrbe(String Nombrbe) {
        this.Nombrbe = Nombrbe;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEditPass() {
        return editPass;
    }

    public void setEditPass(Boolean editPass) {
        this.editPass = editPass;
    }

    public TUsuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public List<TUsuario> getListaUsuarios() {

        return listaUsuarios;
    }

    public void setListaUsuarios(List<TUsuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getMsgTipo() {
        return msgTipo;
    }

    public void setMsgTipo(String msgTipo) {
        this.msgTipo = msgTipo;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public String getMsgClave() {
        return msgClave;
    }

    public void setMsgClave(String msgClave) {
        this.msgClave = msgClave;
    }

    public String getMsgClaveConfir() {
        return msgClaveConfir;
    }

    public void setMsgClaveConfir(String msgClaveConfir) {
        this.msgClaveConfir = msgClaveConfir;
    }

    public String getClaveConfir() {
        return claveConfir;
    }

    public void setClaveConfir(String claveConfir) {
        this.claveConfir = claveConfir;
    }

    public TUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(TUsuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public String getMsgEmpleado() {
        return msgEmpleado;
    }

    public void setMsgEmpleado(String msgEmpleado) {
        this.msgEmpleado = msgEmpleado;
    }

    public String getMsgArea() {
        return msgArea;
    }

    public void setMsgArea(String msgArea) {
        this.msgArea = msgArea;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public AreaBo getAreaBo() {
        return areaBo;
    }

    public void setAreaBo(AreaBo areaBo) {
        this.areaBo = areaBo;
    }

    public List<TArea> getListaArea() {

        return listaArea;
    }

    public void setListaArea(List<TArea> listaArea) {
        this.listaArea = listaArea;
    }

    public IUsuarioBo getUsuarioBo() {
        return usuarioBo;
    }

    public void setUsuarioBo(IUsuarioBo usuarioBo) {
        this.usuarioBo = usuarioBo;
    }

    public IEmpleadoBo getEmpleadoBo() {
        return empleadoBo;
    }

    public void setEmpleadoBo(IEmpleadoBo empleadoBo) {
        this.empleadoBo = empleadoBo;
    }

    public List<TEmpleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<TEmpleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public Boolean getShowImagen() {
        return showImagen;
    }

    public void setShowImagen(Boolean showImagen) {
        this.showImagen = showImagen;
    }

    public void init() {
        super.enableShowData();
        this.usuario = new TUsuario();
        this.empleado = new TEmpleado();
        this.showImagen = true;
        this.imagenEmpty = false;
        this.estadoFormulario = false;
        
        try {
            this.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crear() {

        try {
            usuario.setEstadoUsuario(true);
            usuario.setClaveUsuario(getHash(claveConfir));
            usuario.setTEmpleado(new TEmpleado(idEmpleado));
            this.usuarioBo.create(usuario);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_usuario");
            auxBitacora.setAccionBitacora("Agregar usuario");
            auxBitacora.setDatosBitacora("Usuario:" + usuario.getNombreUsuario()
                    + ", Tipo: " + usuario.getTipoUsuario());
            auxBitacora.setIdTableBitacora(usuario.getIdUsuario());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);
            this.limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario creado correctamente.", ""));
        } catch (Exception ex) {
            this.limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El  usuario no pudo ser creado.", ""));

        }

    }

    public void validarFormulario() {
        estadoFormulario = true;

        if (this.idArea == 0) {
            this.msgArea = "Área requerida";
            estadoFormulario = false;
        } else {
            this.msgArea = "";
        }

        if (this.idEmpleado == 0) {
            this.msgEmpleado = "Empleado requerido";
            estadoFormulario = false;
        } else {
            this.msgEmpleado = "";
        }

        if (this.usuario.getTipoUsuario() == 0) {
            this.msgTipo = "Tipo requerido";
            estadoFormulario = false;
        } else {
            this.msgTipo = "";
        }

        if (this.usuario.getNombreUsuario().length() < 6) {
            this.msgNombre = "El nombre de usuario debe contener mínimo 6 caracteres";
            estadoFormulario = false;
        } else {
            if (this.usuario.getNombreUsuario().length() > 16) {
                this.msgNombre = "El nombre de usuario debe contener  máximo  16 caracteres";
                estadoFormulario = false;
            } else {

                if (this.usuarioBo.getUsuarioValRep(this.usuario.getNombreUsuario()) != null) {
                    this.msgNombre = "El nombre de usuario no esta disponible";
                    estadoFormulario = false;
                } else {

                    this.msgNombre = "";

                }
            }
        }

        if (this.usuario.getClaveUsuario().length() < 8) {
            this.msgClave = "La contraseña debe contener mínimo 8 caracteres";
            estadoFormulario = false;
        } else {
            this.msgClave = "";
        }

        if (!this.usuario.getClaveUsuario().equals(this.claveConfir)) {
            this.msgClaveConfir = "Las contraseña deben ser iguales";
            estadoFormulario = false;
        } else {
            this.msgClaveConfir = "";
        }

    }

    public void darDeBaja() {

        try {
            this.usuarioSelecionado.setEstadoUsuario(false);
            this.usuarioBo.update(usuarioSelecionado);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_usuario");
            auxBitacora.setAccionBitacora("Dar de baja usuario");
            auxBitacora.setDatosBitacora("Usuario:" + usuarioSelecionado.getNombreUsuario()
                    + ", Tipo: " + usuarioSelecionado.getTipoUsuario()
                    + ", Empleado: " + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                    + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado());
            auxBitacora.setIdTableBitacora(usuarioSelecionado.getIdUsuario());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario dado de baja correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Usuario no pudo ser dado de baja.", ""));
        }

    }

    public void enableShowImagen() {
        this.estadoFormulario = false;
        this.imagenEmpty = true;
        this.setShowImagen(!this.getShowImagen());
        if(this.getShowImagen() == true) this.imagenEmpty = false;
    }

    public void guardarImagen(FileUploadEvent event) throws IOException {
        String tipo = event.getFile().getContentType();
        byte[] content = event.getFile().getContents();
        String b64 = Base64.encode(content);
        String guardar = "data:" + tipo + ";base64," + b64;
        this.empleado.setFotoEmpleado(guardar);
        this.showImagen = true;
        this.imagenEmpty = false;
        FacesMessage message = new FacesMessage("Foto Cargada");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void darDeAlta() {

        try {

            this.usuarioSelecionado.setEstadoUsuario(true);

            this.usuarioBo.update(usuarioSelecionado);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_usuario");
            auxBitacora.setAccionBitacora("Dar de alta usuario");
            auxBitacora.setDatosBitacora("Usuario:" + usuarioSelecionado.getNombreUsuario()
                    + ", Tipo: " + usuarioSelecionado.getTipoUsuario()
                    + ", Empleado: " + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                    + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado());
            auxBitacora.setIdTableBitacora(usuarioSelecionado.getIdUsuario());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario dado de alta correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Usuario no pudo ser dodo de alta.", ""));
        }
    }

    public void limpiar() {

        this.usuario = new TUsuario();
        
        this.idEmpleado = 0;
        this.msgArea = "";
        this.msgClave = "";
        this.msgClaveConfir = "";
        this.msgEmpleado = "";
        this.msgNombre = "";
        this.msgTipo = "";
        this.msgImagen = "";
        this.estadoFormulario = false;
        this.editPass = false;
        this.clave = "";
        this.claveConfir = "";
        this.updateListaArea();

    }

    public void enableShowDataBean() {
        this.enableShowData();
        this.limpiar();
    }

    public void actualizar() {

        try {

            if (editPass) {

                usuarioSelecionado.setClaveUsuario(getHash(claveConfir));
            }

            this.usuarioBo.update(usuarioSelecionado);
           
           

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_usuario");
            auxBitacora.setAccionBitacora("Modificar usuario");
            auxBitacora.setDatosBitacora("Usuario:" + usuarioSelecionado.getNombreUsuario()
                    + ", Tipo: " + usuarioSelecionado.getTipoUsuario()
                    + ", Empleado: " + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                    + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado());
            auxBitacora.setIdTableBitacora(usuarioSelecionado.getIdUsuario());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            bitacoraBo.create(auxBitacora);
            this.enableShowData();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario modificado correctamente.", ""));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El  usuario no pudo ser modificado.", ""));
        }

    }

    public void actualizarPerfil() {

        try {

            if (editPass) {

                usuarioSelecionado.setClaveUsuario(getHash(claveConfir));
            }

            usuarioSelecionado.setEstadoUsuario(this.usuarioBo.getUsuario(usuarioSelecionado.getIdUsuario()).getEstadoUsuario());

            usuarioSelecionado.setNombreUsuario(Nombrbe);
            this.empleado.setIdEmpleado(this.usuarioSelecionado.getTEmpleado().getIdEmpleado()); 
            this.empleadoBo.update(empleado);
            this.usuarioBo.update(usuarioSelecionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario modificado correctamente.", ""));
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_usuario");
            auxBitacora.setAccionBitacora("Modificar perfil usuario");
            auxBitacora.setDatosBitacora("Usuario:" + usuarioSelecionado.getNombreUsuario()
                    + ", Tipo: " + usuarioSelecionado.getTipoUsuario()
                    + ", Empleado: " + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                    + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado());
            auxBitacora.setIdTableBitacora(usuarioSelecionado.getIdUsuario());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

            bitacoraBo.create(auxBitacora);
            this.limpiar();
               


        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ".", ""));
        }

    }

    public void validarFormularioModificar() {

        estadoFormulario = true;

        if (this.usuarioSelecionado.getTipoUsuario() == 0) {
            this.msgTipo = "El tipo es requerido";
            estadoFormulario = false;
        } else {
            this.msgTipo = "";
        }

        if (this.usuarioSelecionado.getNombreUsuario().length() < 6) {
            this.msgNombre = "El nombre de usuario debe contener minimo 6 caracteres";
            estadoFormulario = false;
        } else {
            if (this.usuarioSelecionado.getNombreUsuario().length() > 16) {
                this.msgNombre = "El nombre de usuario debe contener  máximo 16 caracteres";
                estadoFormulario = false;
            } else {
                TUsuario usu = this.usuarioBo.getUsuarioValRep(this.usuarioSelecionado.getNombreUsuario());
                if (usu != null) {
                    if (usu.getIdUsuario() != usuarioSelecionado.getIdUsuario()) {
                        this.msgNombre = "El nombre de usuario no esta disponible";
                        estadoFormulario = false;
                    } else {

                        this.msgNombre = "";

                    }
                } else {

                    this.msgNombre = "";

                }

            }
        }

        if (editPass) {
            if (this.clave.length() < 8) {
                this.msgClave = "La contraseña debe contener mínimo 8 caracteres";
                estadoFormulario = false;
            } else {
                this.msgClave = "";
            }

            if (!this.clave.equals(this.claveConfir)) {
                this.msgClaveConfir = "Las contraseñas deben ser iguales";
                estadoFormulario = false;
            } else {
                this.msgClaveConfir = "";
            }
        }

    }

    public void validarFormularioModificarPerfil() {

        estadoFormulario = true;

        if(this.imagenEmpty == true){
            this.msgImagen = "Imágen requerida";
            estadoFormulario = false;
        }
        else{
            this.msgImagen = "";
        }
        if (this.usuarioSelecionado.getTipoUsuario() == 0) {
            this.msgTipo = "Tipo requerido";
            estadoFormulario = false;
        } else {
            this.msgTipo = "";
        }

        if (this.Nombrbe.length() < 6) {
            this.msgNombre = "El nombre de usuario debe contener mínimo 6 caracteres";
            estadoFormulario = false;
        } else {
            if (this.Nombrbe.length() > 16) {
                this.msgNombre = "El nombre de usuario debe contener máximo 16 caracteres";
                estadoFormulario = false;
            } else {
                TUsuario usu = this.usuarioBo.getUsuarioValRep(this.Nombrbe);
                if (usu != null) {
                    if (usu.getIdUsuario() != usuarioSelecionado.getIdUsuario()) {
                        this.msgNombre = "El nombre de usuario no esta disponible";
                        estadoFormulario = false;
                    } else {

                        this.msgNombre = "";

                    }
                } else {

                    this.msgNombre = "";
                     

                }

            }
        }

        if (editPass) {
            if (this.clave.length() < 8) {
                this.msgClave = "La contraseña debe contener mínimo 8 caracteres";
                estadoFormulario = false;
            } else {
                this.msgClave = "";
            }

            if (!this.clave.equals(this.claveConfir)) {
                this.msgClaveConfir = "Las contraseñas deben ser iguales";
                estadoFormulario = false;
            } else {
                this.msgClaveConfir = "";
            }
        } else {
            this.msgClave = "";
            this.msgClaveConfir = "";
            this.clave = "";
            this.claveConfir = "";
           
        }

    }

    public String tipoUser(Integer tipo) {

        if (tipo != null) {
            switch (tipo) {
                case 1:
                    return "Contador";
                case 2:
                    return "Asesor de crédito ";
                default:
                    return "Administrador";
            }
        } else {
            return "";
        }

    }

    /* Retorna un hash a partir de un tipo y un texto */
    public String getHash(String txt) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("SHA1");
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void generarUsuario() throws Exception {

        Map<String, Object> estadoUsuario = new HashMap();

        estadoUsuario.put("id_usuario", usuarioSelecionado.getIdUsuario());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/seguridad/UsuarioIndividual.jasper"));

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
        auxBitacora.setAccionBitacora("Generar reporte de usuario");
        auxBitacora.setDatosBitacora("Usuario:" + usuarioSelecionado.getNombreUsuario()
                + ", Tipo: " + usuarioSelecionado.getTipoUsuario()
                + ", Empleado: " + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado());
        auxBitacora.setIdTableBitacora(usuarioSelecionado.getIdUsuario());
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

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/seguridad/UsuarioIndividual.jasper"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), estadoUsuario, this.getConn());

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Usuario.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_usuario");
        auxBitacora.setAccionBitacora("Descargar reporte de usuario");
        auxBitacora.setDatosBitacora("Usuario:" + usuarioSelecionado.getNombreUsuario()
                + ", Tipo: " + usuarioSelecionado.getTipoUsuario()
                + ", Empleado: " + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado());
        auxBitacora.setIdTableBitacora(usuarioSelecionado.getIdUsuario());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

        bitacoraBo.create(auxBitacora);

    }

    public void enableShowCreateBean() {
        this.limpiar();
        super.enableShowCreate();
    }

}
