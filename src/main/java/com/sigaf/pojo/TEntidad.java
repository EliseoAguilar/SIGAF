package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TEntidad generated by hbm2java
 */
public class TEntidad  implements java.io.Serializable {


     private int idEntidad;
     private String nombreEntidad;
     private String giroEntidad;
     private String correoEntidad;
     private String direccionEntidad;
     private String nitEntidad;
     private String codigoEntidad;
     private String nombreRepresentanteEntidad;
     private String codigoClienteEntidad;
     private String telefonoEntidad;
     private String celularEntidad;
     private String misionEntidad;
     private String visionEntidad;
     private String actaEntidad;
     private Boolean estadoEntidad;
     private String credencialEntidad;
     private String logoEntidad;
     private String duiRepresentanteEntidad;
     private String nitRepresentanteEntidad;
     private String abreviacionEntidad;
     private String calificacionEntidad;
     private Set TTipoActivos = new HashSet(0);
   
     private Set TEjercicios = new HashSet(0);
     private Set TAreas = new HashSet(0);
     private Set TProveedors = new HashSet(0);
     private Set TProductoCooperativas = new HashSet(0);
     private Set TEntidadProyectos = new HashSet(0);
     private Set TConfiguracions = new HashSet(0);
     private Set TAgronegocios = new HashSet(0);
     private Set TCuentas = new HashSet(0);

    public TEntidad() {
    }

	
    public TEntidad(int idEntidad) {
        this.idEntidad = idEntidad;
    }
    public TEntidad(int idEntidad, String nombreEntidad, String giroEntidad, String correoEntidad, String direccionEntidad, String nitEntidad, String codigoEntidad, String nombreRepresentanteEntidad, String codigoClienteEntidad, String telefonoEntidad, String celularEntidad, String misionEntidad, String visionEntidad, String actaEntidad, Boolean estadoEntidad, String credencialEntidad, String logoEntidad, String duiRepresentanteEntidad, String nitRepresentanteEntidad, String abreviacionEntidad, String calificacionEntidad, Set TTipoActivos,  Set TEjercicios, Set TAreas, Set TProveedors, Set TProductoCooperativas, Set TEntidadProyectos, Set TConfiguracions, Set TAgronegocios, Set TCuentas) {
       this.idEntidad = idEntidad;
       this.nombreEntidad = nombreEntidad;
       this.giroEntidad = giroEntidad;
       this.correoEntidad = correoEntidad;
       this.direccionEntidad = direccionEntidad;
       this.nitEntidad = nitEntidad;
       this.codigoEntidad = codigoEntidad;
       this.nombreRepresentanteEntidad = nombreRepresentanteEntidad;
       this.codigoClienteEntidad = codigoClienteEntidad;
       this.telefonoEntidad = telefonoEntidad;
       this.celularEntidad = celularEntidad;
       this.misionEntidad = misionEntidad;
       this.visionEntidad = visionEntidad;
       this.actaEntidad = actaEntidad;
       this.estadoEntidad = estadoEntidad;
       this.credencialEntidad = credencialEntidad;
       this.logoEntidad = logoEntidad;
       this.duiRepresentanteEntidad = duiRepresentanteEntidad;
       this.nitRepresentanteEntidad = nitRepresentanteEntidad;
       this.abreviacionEntidad = abreviacionEntidad;
       this.calificacionEntidad = calificacionEntidad;
       this.TTipoActivos = TTipoActivos;
  
       this.TEjercicios = TEjercicios;
       this.TAreas = TAreas;
       this.TProveedors = TProveedors;
       this.TProductoCooperativas = TProductoCooperativas;
       this.TEntidadProyectos = TEntidadProyectos;
       this.TConfiguracions = TConfiguracions;
       this.TAgronegocios = TAgronegocios;
       this.TCuentas = TCuentas;
    }
   
    public int getIdEntidad() {
        return this.idEntidad;
    }
    
    public void setIdEntidad(int idEntidad) {
        this.idEntidad = idEntidad;
    }
    public String getNombreEntidad() {
        return this.nombreEntidad;
    }
    
    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }
    public String getGiroEntidad() {
        return this.giroEntidad;
    }
    
    public void setGiroEntidad(String giroEntidad) {
        this.giroEntidad = giroEntidad;
    }
    public String getCorreoEntidad() {
        return this.correoEntidad;
    }
    
    public void setCorreoEntidad(String correoEntidad) {
        this.correoEntidad = correoEntidad;
    }
    public String getDireccionEntidad() {
        return this.direccionEntidad;
    }
    
    public void setDireccionEntidad(String direccionEntidad) {
        this.direccionEntidad = direccionEntidad;
    }
    public String getNitEntidad() {
        return this.nitEntidad;
    }
    
    public void setNitEntidad(String nitEntidad) {
        this.nitEntidad = nitEntidad;
    }
    public String getCodigoEntidad() {
        return this.codigoEntidad;
    }
    
    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }
    public String getNombreRepresentanteEntidad() {
        return this.nombreRepresentanteEntidad;
    }
    
    public void setNombreRepresentanteEntidad(String nombreRepresentanteEntidad) {
        this.nombreRepresentanteEntidad = nombreRepresentanteEntidad;
    }
    public String getCodigoClienteEntidad() {
        return this.codigoClienteEntidad;
    }
    
    public void setCodigoClienteEntidad(String codigoClienteEntidad) {
        this.codigoClienteEntidad = codigoClienteEntidad;
    }
    public String getTelefonoEntidad() {
        return this.telefonoEntidad;
    }
    
    public void setTelefonoEntidad(String telefonoEntidad) {
        this.telefonoEntidad = telefonoEntidad;
    }
    public String getCelularEntidad() {
        return this.celularEntidad;
    }
    
    public void setCelularEntidad(String celularEntidad) {
        this.celularEntidad = celularEntidad;
    }
    public String getMisionEntidad() {
        return this.misionEntidad;
    }
    
    public void setMisionEntidad(String misionEntidad) {
        this.misionEntidad = misionEntidad;
    }
    public String getVisionEntidad() {
        return this.visionEntidad;
    }
    
    public void setVisionEntidad(String visionEntidad) {
        this.visionEntidad = visionEntidad;
    }
    public String getActaEntidad() {
        return this.actaEntidad;
    }
    
    public void setActaEntidad(String actaEntidad) {
        this.actaEntidad = actaEntidad;
    }
    public Boolean getEstadoEntidad() {
        return this.estadoEntidad;
    }
    
    public void setEstadoEntidad(Boolean estadoEntidad) {
        this.estadoEntidad = estadoEntidad;
    }
    public String getCredencialEntidad() {
        return this.credencialEntidad;
    }
    
    public void setCredencialEntidad(String credencialEntidad) {
        this.credencialEntidad = credencialEntidad;
    }
    public String getLogoEntidad() {
        return this.logoEntidad;
    }
    
    public void setLogoEntidad(String logoEntidad) {
        this.logoEntidad = logoEntidad;
    }
    public String getDuiRepresentanteEntidad() {
        return this.duiRepresentanteEntidad;
    }
    
    public void setDuiRepresentanteEntidad(String duiRepresentanteEntidad) {
        this.duiRepresentanteEntidad = duiRepresentanteEntidad;
    }
    public String getNitRepresentanteEntidad() {
        return this.nitRepresentanteEntidad;
    }
    
    public void setNitRepresentanteEntidad(String nitRepresentanteEntidad) {
        this.nitRepresentanteEntidad = nitRepresentanteEntidad;
    }
    public String getAbreviacionEntidad() {
        return this.abreviacionEntidad;
    }
    
    public void setAbreviacionEntidad(String abreviacionEntidad) {
        this.abreviacionEntidad = abreviacionEntidad;
    }
    public String getCalificacionEntidad() {
        return this.calificacionEntidad;
    }
    
    public void setCalificacionEntidad(String calificacionEntidad) {
        this.calificacionEntidad = calificacionEntidad;
    }
    public Set getTTipoActivos() {
        return this.TTipoActivos;
    }
    
    public void setTTipoActivos(Set TTipoActivos) {
        this.TTipoActivos = TTipoActivos;
    }
   
    public Set getTEjercicios() {
        return this.TEjercicios;
    }
    
    public void setTEjercicios(Set TEjercicios) {
        this.TEjercicios = TEjercicios;
    }
    public Set getTAreas() {
        return this.TAreas;
    }
    
    public void setTAreas(Set TAreas) {
        this.TAreas = TAreas;
    }
    public Set getTProveedors() {
        return this.TProveedors;
    }
    
    public void setTProveedors(Set TProveedors) {
        this.TProveedors = TProveedors;
    }
    public Set getTProductoCooperativas() {
        return this.TProductoCooperativas;
    }
    
    public void setTProductoCooperativas(Set TProductoCooperativas) {
        this.TProductoCooperativas = TProductoCooperativas;
    }
    public Set getTEntidadProyectos() {
        return this.TEntidadProyectos;
    }
    
    public void setTEntidadProyectos(Set TEntidadProyectos) {
        this.TEntidadProyectos = TEntidadProyectos;
    }
    public Set getTConfiguracions() {
        return this.TConfiguracions;
    }
    
    public void setTConfiguracions(Set TConfiguracions) {
        this.TConfiguracions = TConfiguracions;
    }
    public Set getTAgronegocios() {
        return this.TAgronegocios;
    }
    
    public void setTAgronegocios(Set TAgronegocios) {
        this.TAgronegocios = TAgronegocios;
    }
    public Set getTCuentas() {
        return this.TCuentas;
    }
    
    public void setTCuentas(Set TCuentas) {
        this.TCuentas = TCuentas;
    }




}


