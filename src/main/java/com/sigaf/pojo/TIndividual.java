package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * TIndividual generated by hbm2java
 */
public class TIndividual  implements java.io.Serializable {


     private int idIndividual;
     private TProyecto TProyecto;
     private String lugarTrabajo;
     private BigDecimal salarioActual;
     private Integer tiempoTrabajo;

    public TIndividual() {
    }

	
    public TIndividual(int idIndividual) {
        this.idIndividual = idIndividual;
    }
    public TIndividual(int idIndividual, TProyecto TProyecto, String lugarTrabajo, BigDecimal salarioActual, Integer tiempoTrabajo) {
       this.idIndividual = idIndividual;
       this.TProyecto = TProyecto;
       this.lugarTrabajo = lugarTrabajo;
       this.salarioActual = salarioActual;
       this.tiempoTrabajo = tiempoTrabajo;
    }
   
    public int getIdIndividual() {
        return this.idIndividual;
    }
    
    public void setIdIndividual(int idIndividual) {
        this.idIndividual = idIndividual;
    }
    public TProyecto getTProyecto() {
        return this.TProyecto;
    }
    
    public void setTProyecto(TProyecto TProyecto) {
        this.TProyecto = TProyecto;
    }
    public String getLugarTrabajo() {
        return this.lugarTrabajo;
    }
    
    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }
    public BigDecimal getSalarioActual() {
        return this.salarioActual;
    }
    
    public void setSalarioActual(BigDecimal salarioActual) {
        this.salarioActual = salarioActual;
    }
    public Integer getTiempoTrabajo() {
        return this.tiempoTrabajo;
    }
    
    public void setTiempoTrabajo(Integer tiempoTrabajo) {
        this.tiempoTrabajo = tiempoTrabajo;
    }




}


