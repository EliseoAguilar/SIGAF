/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TProyecto;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IProyectoDao {

    public void create(TProyecto proyecto);

    public TProyecto getTProyecto(Integer id);

    public Integer getCorrelativo();

    public Integer getCorrelativoProyecto();

    public List<TProyecto> listTProyecto();

    public List<TProyecto> listTProyecto(Integer id);

    public List<TProyecto> listTProyectoAprobados();
    
     public List<TProyecto> listTCreditos();

    public void delete(TProyecto proyecto);

    public void update(TProyecto proyecto);

}
