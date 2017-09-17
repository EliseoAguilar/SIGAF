/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IEmpleadoCargoBo;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Genoves 
 */
@Named(value = "empleadoCargoBean")
@RequestScoped   
public class EmpleadoCargoBean {

    /**
     * Creates a new instance of EmpleadoCargoBean
     */
    private IEmpleadoCargoBo iempleadoCargoBo;

    public IEmpleadoCargoBo getIempleadoCargoBo() {
        return iempleadoCargoBo;
    }

    public void setIempleadoCargoBo(IEmpleadoCargoBo iempleadoCargoBo) {
        this.iempleadoCargoBo = iempleadoCargoBo;
    }
    
    public void Init(){
    
    }

   
    
}
