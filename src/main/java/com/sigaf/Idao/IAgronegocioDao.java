/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TAgronegocio;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IAgronegocioDao {

    public void create(TAgronegocio Agronegocio);

    public TAgronegocio getAgronegocio(Integer id);

    public List<TAgronegocio> listAgronegocio();
    
    public List<TAgronegocio> listAgronegocioCodigo(String codigo);
    
    public List<TAgronegocio> listAgronegocioDui(String codigo);

    public void delete(Integer id);

   public Integer getCorrelativo();

    public void update(TAgronegocio Agronegocio);

}
