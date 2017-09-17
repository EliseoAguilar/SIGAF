/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TCapacitacion;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface ICapacitacionDao {
    
    public void create(TCapacitacion capacitacion);

    public TCapacitacion getCapacitacion(Integer id);
    
    public List<TCapacitacion> listCapacitacion();

    public void delete(Integer id);

    public void update(TCapacitacion capacitacion);
    
}
