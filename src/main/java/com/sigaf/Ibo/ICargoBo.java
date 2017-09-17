/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;


import com.sigaf.pojo.TCargo;
import java.util.List;

/**
 *
 * @author Genoves
 */
public interface ICargoBo {
    
    public void create(TCargo cargo);
 
   public TCargo getCargo(Integer id);
 
   public List<TCargo> listCargo(Integer id);

   public void delete(Integer id);

   public void update(TCargo cargo);

    
}
