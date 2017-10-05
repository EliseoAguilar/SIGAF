/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.pojo.TEntidad;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Aguilar
 */
@ManagedBean
@RequestScoped
public class ContablidadPredeterminarBean implements Serializable {

    /**
     * Creates a new instance of ContablidadPredeterminarBean
     */
    private IBitacoraBo bitacoraBo;

    private IEntidadBo entidadBo;

    private List<TEntidad> listaEntidades;

    private TEntidad entidadSeleccionada;

    private Integer idEntidad;
   
    private String msgEntidad;
    
    public void init() {
    this.idEntidad = 0;
    this.msgEntidad="";
    }
    
    /**
    * Metodo que actuliza la lista de entidades consultado a la Base de Datos
    */
    public void updateListaEntidades() {
        this.listaEntidades = this.entidadBo.listTEndidadTodos();
    }

    

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public IEntidadBo getEntidadBo() {
        return entidadBo;
    }

    public void setEntidadBo(IEntidadBo entidadBo) {
        this.entidadBo = entidadBo;
    }

    public List<TEntidad> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(List<TEntidad> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    public TEntidad getEntidadSeleccionada() {
        return entidadSeleccionada;
    }

    public void setEntidadSeleccionada(TEntidad entidadSeleccionada) {
        this.entidadSeleccionada = entidadSeleccionada;
    }

  /*
    * Se recorre la lista dado que necesitamos toda la informacion 
    * de la entidad. 
     */
    public void setIdEntidad(Integer idEntidad) {

        this.idEntidad = idEntidad;

        if(idEntidad == 0 ){
        msgEntidad ="Entidad requerida";
        }else{
        msgEntidad ="";
        }
            
          
        for (TEntidad tentidad : listaEntidades) {

            if (tentidad.getIdEntidad() == idEntidad) {
                this.entidadSeleccionada = tentidad;
                return;
            }
        }

    }

    public Integer getIdEntidad() {
        return idEntidad;
    }
    
    public void limpiar(){
    this.idEntidad = 0;
    this.msgEntidad="";
    }

    public String getMsgEntidad() {
        return msgEntidad;
    }

    public void setMsgEntidad(String msgEntidad) {
        this.msgEntidad = msgEntidad;
    }
    
   
    
    
    
}
