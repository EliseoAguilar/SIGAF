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
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eliseo
 */
@ManagedBean
@SessionScoped
public class LoginBean extends Actividad {

    private IBitacoraBo bitacoraBo;

    private IUsuarioBo usuarioBo;

    private String clave;

    private String claveConfir;

    private String nombreUsuario;

    private TUsuario usuarioActivo;

    private String msgValidate;

    private Boolean reset;

    private String mail;

    private String token;

    private Integer numeroRandom;

    private Boolean resetPassForm;

    private Boolean estadoFormulario;
    
    private Integer idEntidad;
    
    private Boolean predeterminado;

    public Boolean getPredeterminado() {
        return predeterminado;
    }

    public void setPredeterminado(Boolean predeterminado) {
        this.predeterminado = predeterminado;
    }
     
    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }
    
    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public String getClaveConfir() {
        return claveConfir;
    }

    public void setClaveConfir(String claveConfir) {
        this.claveConfir = claveConfir;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getNumeroRandom() {
        return numeroRandom;
    }

    public void setNumeroRandom(Integer numeroRandom) {
        this.numeroRandom = numeroRandom;
    }

    public Boolean getReset() {
        return reset;
    }

    public void setReset(Boolean reset) {
        this.msgValidate = "";
        this.mail = "";
        this.nombreUsuario = "";
        this.clave = "";
        this.reset = reset;
    }

    public TUsuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(TUsuario usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getMsgValidate() {
        return msgValidate;
    }

    public void setMsgValidate(String msgValidate) {
        this.msgValidate = msgValidate;
    }

    public IUsuarioBo getUsuarioBo() {
        return usuarioBo;
    }

    public void setUsuarioBo(IUsuarioBo usuarioBo) {
        this.usuarioBo = usuarioBo;
    }

    public void login() {

        this.usuarioActivo = this.usuarioBo.login(nombreUsuario, getHash(clave));

        if (this.usuarioActivo != null) {

            if (this.usuarioActivo.getEstadoUsuario()) {

                TBitacora auxBitacora = new TBitacora();
                auxBitacora.setTableBitacora("---");
                auxBitacora.setAccionBitacora("Inicio de sesión");
                auxBitacora.setDatosBitacora("Usuario:" + usuarioActivo.getNombreUsuario()
                        + ", Tipo: " + usuarioActivo.getTipoUsuario()
                        + ", Empleado: " + usuarioActivo.getTEmpleado().getNombreEmpleado()
                        + " " + usuarioActivo.getTEmpleado().getApellidoEmpleado());

                auxBitacora.setHoraBitacora(new Date());
                auxBitacora.setFechaBitacora(new Date());
                auxBitacora.setTUsuario(usuarioActivo);

                bitacoraBo.create(auxBitacora);

                this.limpiar();
                FacesContext.getCurrentInstance().getApplication().getNavigationHandler().
                        handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml");
            } else {

                this.usuarioActivo = null;
                this.msgValidate = "Usuario  inactivo, comuníquese con el administrador del sistema.";

            }

        } else {
            this.msgValidate = "Credenciales invalidas";
        }

    }

    public void logout() throws IOException {

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("---");
        auxBitacora.setAccionBitacora("Salir de sesión");
        auxBitacora.setDatosBitacora("Usuario:" + usuarioActivo.getNombreUsuario()
                + ", Tipo: " + usuarioActivo.getTipoUsuario()
                + ", Empleado: " + usuarioActivo.getTEmpleado().getNombreEmpleado()
                + " " + usuarioActivo.getTEmpleado().getApellidoEmpleado());

        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setTUsuario(usuarioActivo);

        bitacoraBo.create(auxBitacora);

        this.usuarioActivo = null;

        this.limpiar();

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        String url = request.getContextPath();

        FacesContext context = FacesContext.getCurrentInstance();

        ExternalContext externalContext = context.getExternalContext();

        Object session = externalContext.getSession(false);

        HttpSession httpSession = (HttpSession) session;

        httpSession.invalidate();

        context.getExternalContext().redirect(url);
    }

    public void resetPass() {

        TUsuario usuarioActivoAux = this.usuarioBo.resetPass(mail);

        if (usuarioActivoAux != null) {
            this.usuarioActivo = usuarioActivoAux;
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String url = request.getRequestURL().toString();
            numeroRandom = ThreadLocalRandom.current().nextInt(1, 10000);
            try {
                MailReset.enviaEmail(usuarioActivo, url + "?token=" + numeroRandom);
                this.msgValidate = "Correo para restablecer su contraseña enviado con éxito ";
            } catch (Exception e) {
                numeroRandom = 0;
                this.msgValidate = "Correo para restablecer su contraseña no pudo ser enviado";
            }

        } else {
            this.msgValidate = "El correo no corresponde a ninguno de nuestros registros";
        }

    }

    public Boolean getResetPassForm() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map params = ec.getRequestParameterMap();

        if (params.get("token") != null) {
            token = params.get("token").toString();
            resetPassForm = token.equals(numeroRandom.toString());

        } else {

            resetPassForm = false;

        }

        return resetPassForm;
    }

    public void setResetPassForm(Boolean resetPassForm) {
        this.resetPassForm = resetPassForm;
    }

    public void actualizar() {

        try {
            this.usuarioActivo.setClaveUsuario(getHash(claveConfir));

            this.usuarioBo.update(usuarioActivo);
            
              TBitacora auxBitacora = new TBitacora();
                auxBitacora.setTableBitacora("---");
                auxBitacora.setAccionBitacora("Recuperación de contraseña de usuario");
                auxBitacora.setDatosBitacora("Usuario:" + usuarioActivo.getNombreUsuario()
                    + ", Tipo: " + usuarioActivo.getTipoUsuario()
                    + ", Empleado: " + usuarioActivo.getTEmpleado().getNombreEmpleado()
                    + " " + usuarioActivo.getTEmpleado().getApellidoEmpleado());
           
                auxBitacora.setHoraBitacora(new Date());
                auxBitacora.setFechaBitacora(new Date());
                auxBitacora.setTUsuario(usuarioActivo);

                bitacoraBo.create(auxBitacora);

            this.limpiar();

            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().
                    handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml");

        } catch (Exception ex) {
            this.msgValidate = "Error al restablecer la contraseña de tu cuenta";
        }
    }

    public void validar() {

        if (this.clave.length() < 8) {
            this.msgValidate = "La contraseña debe contener nimimo 8 caracteres";
            estadoFormulario = false;
        } else {
            if (!this.clave.equals(this.claveConfir)) {
                this.msgValidate = "Las contraseña deben ser iguales";
                estadoFormulario = false;
            } else {
                this.actualizar();
                this.init();
            }
        }

    }

    public void init() {
        limpiar();
        this.idEntidad=0;
        this.predeterminado=false;

    }

    public void limpiar() {
        this.msgValidate = "";
        this.nombreUsuario = "";
        this.clave = "";
        this.mail = "";
        this.claveConfir = "";
        this.numeroRandom = 0;
        this.token = "";
        
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
}
