/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TConfiguracion;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public interface IConfiguracionDao {
    
   public void create(TConfiguracion configuracion);
 
   public TConfiguracion getConfiguracion(Integer id);

   public List<TConfiguracion> listConfiguracion(Integer id);

   public void delete(Integer id);

   public void update(TConfiguracion configuracion);
    
}
