/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TOcupacion;
import java.util.List;

/**
 *
 * @author Genoves
 */
public interface IOcupacionDao {
    
    public void create(TOcupacion ocupacion);

    public TOcupacion getTOcupacion(Integer id, Integer idEmpleado);

    public List<TOcupacion> listTOcupacion(Integer id);

    public void delete(Integer id);

    public void update(TOcupacion ocupacion);
    
}
