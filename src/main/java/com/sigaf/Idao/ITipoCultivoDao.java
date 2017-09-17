/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TTipoCultivo;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface ITipoCultivoDao {

    public void create(TTipoCultivo TipoCultivo);

    public TTipoCultivo getAreaCultivo(Integer id);

    public List<TTipoCultivo> listTipoCultivo();
    
    public boolean getTTipoCultivoNombre(String nombre);

    public void delete(Integer id);

    public void update(TTipoCultivo TipoCultivo);

}
