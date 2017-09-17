/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TAreaCultivo;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IAreaCultivoDao {
    
    public void create(TAreaCultivo AreaCultivo);

    public TAreaCultivo getAreaCultivo(Integer id);
    
    public boolean getTAreaCultivoNombre(String nombre);

    public List<TAreaCultivo> listAreaCultivo();
    
    public List<TAreaCultivo> listAreaCultivoActivos();

    public void delete(Integer id);

    public void update(TAreaCultivo AreaCultivo);
    
   
    
    

    
}
