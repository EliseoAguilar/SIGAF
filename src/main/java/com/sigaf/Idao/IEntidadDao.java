/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TEntidad;
import java.util.List;

/**
 *
 * @author Genoves
 */
public interface IEntidadDao {

    public void create(TEntidad entidad);

    public TEntidad getTEntidad(Integer id);

    public boolean getTEntidadCodigo(String codigo, String codigoAux);

    public List<TEntidad> listTEndidad();

    public List<TEntidad> listTEndidadCodigo();

    public List<TEntidad> listTEndidadTodos();
    
     public List<TEntidad> listTEndidadActiva();

    public void delete(Integer id);

    public void update(TEntidad entidad);

}
