/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TBajaActivoFijo;
import java.util.List;

/**
 *
 * @author Eliseo Aguilar
 */
public interface IBajaActivoFijoDao {

    public void create(TBajaActivoFijo bajaActivoFijo);

    public TBajaActivoFijo getTBajaActivoFijo(Integer id);

    public List<TBajaActivoFijo> listBajaActivoFijo(Integer id);

    public void delete(Integer id);

    public void update(TBajaActivoFijo bajaActivoFijo);
}
