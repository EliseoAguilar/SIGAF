/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TDetallePartida;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public interface IDetallePartidaDao {
    
    public void create(TDetallePartida detallePartida);

    public TDetallePartida getDetallePartida(Integer id);

    public List<TDetallePartida> listDetallePartida(Integer id);

    public void delete(Integer id);

    public void update(TDetallePartida detallePartida);
}
