/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TEjercicio;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public interface IEjercicioDao {
    
    
   public void create(TEjercicio ejercicio);
 
   public TEjercicio getEjercicio(Integer id);
 
   public List<TEjercicio> listEjercicio(Integer id);

   public void delete(Integer id);

   public void update(TEjercicio ejercicio);
   
   public TEjercicio getEjercicioAbierto(Integer id);
   
}
