/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TActivoFijo;
import com.sigaf.pojo.TEmpleado;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public interface IActivoFijoBo {

    public void create(TActivoFijo activoFijo);

    public TActivoFijo getActivoFijo(Integer id);

    public List<TActivoFijo> listActivoFijo(Integer id);

    public void delete(Integer id);

    public void update(TActivoFijo activoFijo);

    public List<TActivoFijo> listActivoFijoTipo(Integer id);

}
