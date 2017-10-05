package com.sigaf.pojo;
// Generated 10-feb-2017 20:31:39 by Hibernate Tools 4.3.1



/**
 * TGarantiaSolidaria generated by hbm2java
 */
public class TGarantiaSolidaria  implements java.io.Serializable {


     private int idGarantiaSolidaria;
     private TLisiado TLisiado;
     private TTipoGarantia TTipoGarantia;
     private String nombreFiador;
     private String apellidoFiador;
     private String duiFiador;
     private String telefonoFiador;
     private String ingresosFiador;
     private String lugarTrabajo;

    public TGarantiaSolidaria() {
    }

	
    public TGarantiaSolidaria(int idGarantiaSolidaria) {
        this.idGarantiaSolidaria = idGarantiaSolidaria;
    }
    public TGarantiaSolidaria(int idGarantiaSolidaria, TLisiado TLisiado, TTipoGarantia TTipoGarantia, String nombreFiador, String apellidoFiador, String duiFiador, String telefonoFiador, String ingresosFiador, String lugarTrabajo) {
       this.idGarantiaSolidaria = idGarantiaSolidaria;
       this.TLisiado = TLisiado;
       this.TTipoGarantia = TTipoGarantia;
       this.nombreFiador = nombreFiador;
       this.apellidoFiador = apellidoFiador;
       this.duiFiador = duiFiador;
       this.telefonoFiador = telefonoFiador;
       this.ingresosFiador = ingresosFiador;
       this.lugarTrabajo = lugarTrabajo;
    }
   
    public int getIdGarantiaSolidaria() {
        return this.idGarantiaSolidaria;
    }
    
    public void setIdGarantiaSolidaria(int idGarantiaSolidaria) {
        this.idGarantiaSolidaria = idGarantiaSolidaria;
    }
    public TLisiado getTLisiado() {
        return this.TLisiado;
    }
    
    public void setTLisiado(TLisiado TLisiado) {
        this.TLisiado = TLisiado;
    }
    public TTipoGarantia getTTipoGarantia() {
        return this.TTipoGarantia;
    }
    
    public void setTTipoGarantia(TTipoGarantia TTipoGarantia) {
        this.TTipoGarantia = TTipoGarantia;
    }
    public String getNombreFiador() {
        return this.nombreFiador;
    }
    
    public void setNombreFiador(String nombreFiador) {
        this.nombreFiador = nombreFiador;
    }
    public String getApellidoFiador() {
        return this.apellidoFiador;
    }
    
    public void setApellidoFiador(String apellidoFiador) {
        this.apellidoFiador = apellidoFiador;
    }
    public String getDuiFiador() {
        return this.duiFiador;
    }
    
    public void setDuiFiador(String duiFiador) {
        this.duiFiador = duiFiador;
    }
    public String getTelefonoFiador() {
        return this.telefonoFiador;
    }
    
    public void setTelefonoFiador(String telefonoFiador) {
        this.telefonoFiador = telefonoFiador;
    }
    public String getIngresosFiador() {
        return this.ingresosFiador;
    }
    
    public void setIngresosFiador(String ingresosFiador) {
        this.ingresosFiador = ingresosFiador;
    }
    public String getLugarTrabajo() {
        return this.lugarTrabajo;
    }
    
    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }




}


