package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TEjercicio generated by hbm2java
 */
public class TEjercicio  implements java.io.Serializable {


     private int idEjercicio;
     private TEntidad TEntidad;
     private Boolean estadoEjercicio;
     private Integer ahoEjercicio;
     private Date fechaCierre;
     private Set TPeriodos = new HashSet(0);
     private Set TEstructuras = new HashSet(0);

    public TEjercicio() {
    }

	
    public TEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }
    public TEjercicio(int idEjercicio, TEntidad TEntidad, Boolean estadoEjercicio, Integer ahoEjercicio,  Date fechaCierre, Set TPeriodos,Set TEstructuras) {
       this.idEjercicio = idEjercicio;
       this.TEntidad = TEntidad;
       this.estadoEjercicio = estadoEjercicio;
       this.ahoEjercicio = ahoEjercicio;
       this.fechaCierre=fechaCierre;
       this.TPeriodos = TPeriodos;
       this.TEstructuras=TEstructuras;
    }  

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }


    
    public int getIdEjercicio() {
        return this.idEjercicio;
    }
    
    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }
    public TEntidad getTEntidad() {
        return this.TEntidad;
    }
    
    public void setTEntidad(TEntidad TEntidad) {
        this.TEntidad = TEntidad;
    }
    public Boolean getEstadoEjercicio() {
        return this.estadoEjercicio;
    }
    
    public void setEstadoEjercicio(Boolean estadoEjercicio) {
        this.estadoEjercicio = estadoEjercicio;
    }
    public Integer getAhoEjercicio() {
        return this.ahoEjercicio;
    }
    
    public void setAhoEjercicio(Integer ahoEjercicio) {
        this.ahoEjercicio = ahoEjercicio;
    }
    public Set getTPeriodos() {
        return this.TPeriodos;
    }
    
    public void setTPeriodos(Set TPeriodos) {
        this.TPeriodos = TPeriodos;
    }

    public Set getTEstructuras() {
        return TEstructuras;
    }

    public void setTEstructuras(Set TEstructuras) {
        this.TEstructuras = TEstructuras;
    }




}


