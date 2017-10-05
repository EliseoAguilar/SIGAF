package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1



/**
 * TComercio generated by hbm2java
 */
public class TComercio  implements java.io.Serializable {


     private int idComercio;
     private TProyecto TProyecto;
     private String nombreComercio;
     private String giroComercio;
     private Integer tiempoComercio;
     private String registroComercio;
     private Integer empleadosComercio;
     private String telefonoComercio;
     private String direccionComercio;

    public TComercio() {
    }

	
    public TComercio(int idComercio) {
        this.idComercio = idComercio;
    }
    public TComercio(int idComercio, TProyecto TProyecto, String nombreComercio, String giroComercio, Integer tiempoComercio, String registroComercio, Integer empleadosComercio, String telefonoComercio, String direccionComercio) {
       this.idComercio = idComercio;
       this.TProyecto = TProyecto;
       this.nombreComercio = nombreComercio;
       this.giroComercio = giroComercio;
       this.tiempoComercio = tiempoComercio;
       this.registroComercio = registroComercio;
       this.empleadosComercio = empleadosComercio;
       this.telefonoComercio = telefonoComercio;
       this.direccionComercio = direccionComercio;
    }
   
    public int getIdComercio() {
        return this.idComercio;
    }
    
    public void setIdComercio(int idComercio) {
        this.idComercio = idComercio;
    }
    public TProyecto getTProyecto() {
        return this.TProyecto;
    }
    
    public void setTProyecto(TProyecto TProyecto) {
        this.TProyecto = TProyecto;
    }
    public String getNombreComercio() {
        return this.nombreComercio;
    }
    
    public void setNombreComercio(String nombreComercio) {
        this.nombreComercio = nombreComercio;
    }
    public String getGiroComercio() {
        return this.giroComercio;
    }
    
    public void setGiroComercio(String giroComercio) {
        this.giroComercio = giroComercio;
    }
    public Integer getTiempoComercio() {
        return this.tiempoComercio;
    }
    
    public void setTiempoComercio(Integer tiempoComercio) {
        this.tiempoComercio = tiempoComercio;
    }
    public String getRegistroComercio() {
        return this.registroComercio;
    }
    
    public void setRegistroComercio(String registroComercio) {
        this.registroComercio = registroComercio;
    }
    public Integer getEmpleadosComercio() {
        return this.empleadosComercio;
    }
    
    public void setEmpleadosComercio(Integer empleadosComercio) {
        this.empleadosComercio = empleadosComercio;
    }
    public String getTelefonoComercio() {
        return this.telefonoComercio;
    }
    
    public void setTelefonoComercio(String telefonoComercio) {
        this.telefonoComercio = telefonoComercio;
    }
    public String getDireccionComercio() {
        return this.direccionComercio;
    }
    
    public void setDireccionComercio(String direccionComercio) {
        this.direccionComercio = direccionComercio;
    }




}


