/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TIngreso;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IIngresoBo {

    public void create(TIngreso Ingreso);

    public TIngreso getIngreso(Integer id);

    public List<TIngreso> listIngreso();
    
    public List<TIngreso> listIngreso(Integer id);

    public void delete(TIngreso ingreso);

    public void update(TIngreso Ingreso);

}
