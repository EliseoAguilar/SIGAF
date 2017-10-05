/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import java.math.BigDecimal;

/**
 *
 * @author Eliseo Aguilar
 */
public class EstadoResultado {
  
    
    String NombreGrupo;
    
    String NombreCuenta;
    
    BigDecimal saldoCuenta;
    
    BigDecimal  saldoGrupo;

    public EstadoResultado() {
    }

    
    
    public EstadoResultado(String NombreGrupo, String NombreCuenta, BigDecimal saldoCuenta, BigDecimal saldoGrupo) {
        this.NombreGrupo = NombreGrupo;
        this.NombreCuenta = NombreCuenta;
        this.saldoCuenta = saldoCuenta;
        this.saldoGrupo = saldoGrupo;
    }

    public String getNombreGrupo() {
        return NombreGrupo;
    }

    public void setNombreGrupo(String NombreGrupo) {
        this.NombreGrupo = NombreGrupo;
    }

    public String getNombreCuenta() {
        return NombreCuenta;
    }

    public void setNombreCuenta(String NombreCuenta) {
        this.NombreCuenta = NombreCuenta;
    }

    public BigDecimal getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(BigDecimal saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    public BigDecimal getSaldoGrupo() {
        return saldoGrupo;
    }

    public void setSaldoGrupo(BigDecimal saldoGrupo) {
        this.saldoGrupo = saldoGrupo;
    }
    
    
    
    
    
    
    
    
    
}
