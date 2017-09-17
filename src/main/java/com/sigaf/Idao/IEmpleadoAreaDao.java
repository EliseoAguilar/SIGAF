/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TEmpleadoArea;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IEmpleadoAreaDao {
    
    public void create(TEmpleadoArea empleadoArea);

    public TEmpleadoArea getTEmpleadoArea(Integer id, Integer idEmpleado);

    public List<TEmpleadoArea> listTEmpleadoArea(Integer id);

    public void delete(Integer id);

    public void update(TEmpleadoArea empleadoArea);
    
    public Boolean correoRepetido(String correo, int idEnt);
    
}
