package com.sigaf.pojo;
// Generated 10-feb-2017 20:31:39 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * TDonacionActivo generated by hbm2java
 */
public class TDonacionActivo  implements java.io.Serializable {


     private int iddonacionactivofijo;
     private String nombrebeneficiario;
     private Date fecha;
     private Integer idactivo;

    public TDonacionActivo() {
    }

	
    public TDonacionActivo(int iddonacionactivofijo) {
        this.iddonacionactivofijo = iddonacionactivofijo;
    }
    public TDonacionActivo(int iddonacionactivofijo, String nombrebeneficiario, Date fecha, Integer idactivo) {
       this.iddonacionactivofijo = iddonacionactivofijo;
       this.nombrebeneficiario = nombrebeneficiario;
       this.fecha = fecha;
       this.idactivo = idactivo;
    }
   
    public int getIddonacionactivofijo() {
        return this.iddonacionactivofijo;
    }
    
    public void setIddonacionactivofijo(int iddonacionactivofijo) {
        this.iddonacionactivofijo = iddonacionactivofijo;
    }
    public String getNombrebeneficiario() {
        return this.nombrebeneficiario;
    }
    
    public void setNombrebeneficiario(String nombrebeneficiario) {
        this.nombrebeneficiario = nombrebeneficiario;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Integer getIdactivo() {
        return this.idactivo;
    }
    
    public void setIdactivo(Integer idactivo) {
        this.idactivo = idactivo;
    }




}


