/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TEmpleadoCargo;
import java.util.List;

/**
 *
 * @author Genoves
 */
public interface IEmpleadoCargoDao {
    
    public void create(TEmpleadoCargo empleadoCargo);

    public TEmpleadoCargo getTEmpleadoCargo(Integer id, Integer idEmpleado);

    public List<TEmpleadoCargo> listTEmpleadoCargo(Integer id);

    public void delete(Integer id);

    public void update(TEmpleadoCargo empleadoCargo);
    
}
