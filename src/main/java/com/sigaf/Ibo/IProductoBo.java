/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TAreaCultivo;
import com.sigaf.pojo.TProducto;
import com.sigaf.pojo.TProductorIndividual;
import com.sigaf.pojo.TTipoCultivo;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IProductoBo {
    
    public void create(TProducto entidad);

    public TProducto getTProducto(Integer id);
    
    public TTipoCultivo getTCultivo(Integer id);
    
    public TAreaCultivo getTAreaCultivo(Integer id);
    
    public boolean getTProductoCodigo(String codigo,String codigoAux);

    public List<TProducto> listTProducto();

    public void delete(Integer id);

    public void update(TProducto entidad);
    
    public List<TProductorIndividual> listTProductorIndividual(); 
    
    public List<TAreaCultivo> listTAreaCultivo();
    
    public List<TTipoCultivo> listTTipoCultivo(Integer id); 
    
}
