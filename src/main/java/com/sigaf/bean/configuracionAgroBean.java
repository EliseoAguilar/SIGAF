/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;


import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@Named(value = "configuracionAgroBean")
@RequestScoped
@ManagedBean

public class configuracionAgroBean extends Actividad {

    
    public configuracionAgroBean() {
        
        super.enableShowCreate();
        
    }
    
     public void enableShowDataBean() {
       
        super.enableShowData();

    }
    
}
