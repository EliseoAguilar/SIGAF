/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TEmpleadoArea;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IEmpleadoAreaBo {
    
    public void create(TEmpleadoArea empleadoArea);

    public TEmpleadoArea getTEmpleadoArea(Integer id, Integer idEmpleado);

    public List<TEmpleadoArea> listTEmpleadoArea(Integer id);

    public void delete(Integer id);

    public void update(TEmpleadoArea empleadoArea);
    
    public Boolean correoRepetido(String correo, int idEnt);
    
}
