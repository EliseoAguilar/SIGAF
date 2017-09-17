/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TTipoActivo;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public interface ITipoActivoDao {

    public void create(TTipoActivo tipoActivo);

    public TTipoActivo getActivo(Integer id);

    public TTipoActivo getActivoRepAct(Integer idEnt, Integer idTip, String codigo);

    public TTipoActivo getActivoRep(Integer idEnt, String codigo);

    public List<TTipoActivo> listTipoActivo(Integer id);

    public void delete(Integer id);

    public void update(TTipoActivo tipoActivo);
}
