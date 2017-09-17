/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TPartida;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public interface IPartidaBo {
    
    public void create(TPartida partida);

    public TPartida getPartida(Integer id);

    public List<TPartida> listPartida(Integer id);

    public void delete(TPartida partida ,Integer id);

    public void update(TPartida partida);
    
    public Integer numeroPartida(Integer id);
    
}
