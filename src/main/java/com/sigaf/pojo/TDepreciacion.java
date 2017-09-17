package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * TDepreciacion generated by hbm2java
 */
public class TDepreciacion  implements java.io.Serializable {


     private int idDepreciacion;
     private TActivoFijo TActivoFijo;
     private TPartida TPartida;
     private BigDecimal valorDepreciacion;

    public TDepreciacion() {
    }

	
    public TDepreciacion(int idDepreciacion) {
        this.idDepreciacion = idDepreciacion;
    }
    public TDepreciacion(int idDepreciacion, TActivoFijo TActivoFijo, TPartida TPartida, BigDecimal valorDepreciacion) {
       this.idDepreciacion = idDepreciacion;
       this.TActivoFijo = TActivoFijo;
       this.TPartida = TPartida;
       this.valorDepreciacion = valorDepreciacion;
    }
   
    public int getIdDepreciacion() {
        return this.idDepreciacion;
    }
    
    public void setIdDepreciacion(int idDepreciacion) {
        this.idDepreciacion = idDepreciacion;
    }
    public TActivoFijo getTActivoFijo() {
        return this.TActivoFijo;
    }
    
    public void setTActivoFijo(TActivoFijo TActivoFijo) {
        this.TActivoFijo = TActivoFijo;
    }
    public TPartida getTPartida() {
        return this.TPartida;
    }
    
    public void setTPartida(TPartida TPartida) {
        this.TPartida = TPartida;
    }
    public BigDecimal getValorDepreciacion() {
        return this.valorDepreciacion;
    }
    
    public void setValorDepreciacion(BigDecimal valorDepreciacion) {
        this.valorDepreciacion = valorDepreciacion;
    }




}


