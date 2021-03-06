package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * TBitacora generated by hbm2java
 */
public class TBitacora  implements java.io.Serializable {


     private int idBitacora;
     private TUsuario TUsuario;
     private Date fechaBitacora;
     private Date horaBitacora;
     private String tableBitacora;
     private String accionBitacora;
     private Integer idTableBitacora;
     private String datosBitacora;

    public TBitacora() {
    }

	
    public TBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }
    public TBitacora(int idBitacora, TUsuario TUsuario, Date fechaBitacora, Date horaBitacora, String tableBitacora, String accionBitacora, Integer idTableBitacora, String datosBitacora) {
       this.idBitacora = idBitacora;
       this.TUsuario = TUsuario;
       this.fechaBitacora = fechaBitacora;
       this.horaBitacora = horaBitacora;
       this.tableBitacora = tableBitacora;
       this.accionBitacora = accionBitacora;
       this.idTableBitacora = idTableBitacora;
       this.datosBitacora = datosBitacora;
    }
   
    public int getIdBitacora() {
        return this.idBitacora;
    }
    
    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }
    public TUsuario getTUsuario() {
        return this.TUsuario;
    }
    
    public void setTUsuario(TUsuario TUsuario) {
        this.TUsuario = TUsuario;
    }
    public Date getFechaBitacora() {
        return this.fechaBitacora;
    }
    
    public void setFechaBitacora(Date fechaBitacora) {
        this.fechaBitacora = fechaBitacora;
    }
    public Date getHoraBitacora() {
        return this.horaBitacora;
    }
    
    public void setHoraBitacora(Date horaBitacora) {
        this.horaBitacora = horaBitacora;
    }
    public String getTableBitacora() {
        return this.tableBitacora;
    }
    
    public void setTableBitacora(String tableBitacora) {
        this.tableBitacora = tableBitacora;
    }
    public String getAccionBitacora() {
        return this.accionBitacora;
    }
    
    public void setAccionBitacora(String accionBitacora) {
        this.accionBitacora = accionBitacora;
    }

    public Integer getIdTableBitacora() {
        return idTableBitacora;
    }

    public void setIdTableBitacora(Integer idTableBitacora) {
        this.idTableBitacora = idTableBitacora;
    }



    public String getDatosBitacora() {
        return this.datosBitacora;
    }
    
    public void setDatosBitacora(String datosBitacora) {
        this.datosBitacora = datosBitacora;
    }




}


