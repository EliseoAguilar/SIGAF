/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TActivo;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IActivoDao {
    
     public void create(TActivo Activo);

    public TActivo getActivo(Integer id);
    
    public List<TActivo> listActivo();
    
    public List<TActivo> listActivo(Integer id);

    public void delete(TActivo activo);

    public void update(TActivo Activo);
    
}
