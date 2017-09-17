/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;
   
import com.sigaf.Ibo.IOcupacionBo;    
import javax.inject.Named;   
import javax.enterprise.context.RequestScoped;

/**   
 *
 * @author Genoves
 */
@Named(value = "ocupacionBean")
@RequestScoped
public class OcupacionBean extends Actividad{

    private IOcupacionBo iocupacionBo;
    public IOcupacionBo getIocupacionBo() {
        return iocupacionBo;
    }

    public void setIocupacionBo(IOcupacionBo iocupacionBo) {
        this.iocupacionBo = iocupacionBo;
    }
    /**
     * Creates a new instance of OcupacionBean
     */
    public OcupacionBean() {
    }
    
}
