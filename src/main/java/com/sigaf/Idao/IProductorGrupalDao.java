/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TProductorGrupal;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IProductorGrupalDao {

    public void create(TProductorGrupal ProductorGrupal);

    public TProductorGrupal getProductorGrupal(Integer id);
    
    public boolean getTProductorGrupalNombre(String nombre);

    public List<TProductorGrupal> listProductorGrupal();
    
    public List<TProductorGrupal> listProductorGrupalActivo();

    public void delete(Integer id);

    public void update(TProductorGrupal ProductorGrupal);
}
