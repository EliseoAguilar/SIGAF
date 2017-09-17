/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEmpleadoCargo;
import java.util.List;

/**
 *
 * @author Genoves
 */
public interface IEmpleadoDao {

    public void create(TEmpleado empleado);

    public TEmpleado getTEmpleado(Integer id);
    
    public Boolean getDUiTEmpleado(String dui);
    
     public List<TEmpleado> listEmpleadosOrSocios(Integer idEmpleadoArea, Integer tipo);
     
      public List<TEmpleado> listEmpleadosActivos(Integer idEmpleadoArea);


    public List<TEmpleadoCargo> listTEmpleadoCargo(Integer id);
    
    public List<TEmpleado> listTEmpleado();

    public void delete(Integer id);

    public void update(TEmpleado empleado);
 

}
