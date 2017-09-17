package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TTipoCredito generated by hbm2java
 */
public class TTipoCredito  implements java.io.Serializable {


     private int idTipoCredito;
     private String nombre;
     private String descripcion;
     private Integer estado;
     private Set TProyectos = new HashSet(0);
     private Set TPoliticas = new HashSet(0);

    public TTipoCredito() {
    }

	
    public TTipoCredito(int idTipoCredito) {
        this.idTipoCredito = idTipoCredito;
    }
    public TTipoCredito(int idTipoCredito, String nombre, String descripcion, Integer estado, Set TProyectos, Set TPoliticas) {
       this.idTipoCredito = idTipoCredito;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.estado = estado;
       this.TProyectos = TProyectos;
       this.TPoliticas = TPoliticas;
    }
   
    public int getIdTipoCredito() {
        return this.idTipoCredito;
    }
    
    public void setIdTipoCredito(int idTipoCredito) {
        this.idTipoCredito = idTipoCredito;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getEstado() {
        return this.estado;
    }
    
    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    public Set getTProyectos() {
        return this.TProyectos;
    }
    
    public void setTProyectos(Set TProyectos) {
        this.TProyectos = TProyectos;
    }
    public Set getTPoliticas() {
        return this.TPoliticas;
    }
    
    public void setTPoliticas(Set TPoliticas) {
        this.TPoliticas = TPoliticas;
    }




}


