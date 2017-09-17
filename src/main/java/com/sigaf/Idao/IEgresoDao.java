/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TEgreso;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IEgresoDao {

    public void create(TEgreso Egreso);

    public TEgreso getEgreso(Integer id);

    public List<TEgreso> listEgreso();

    public List<TEgreso> listEgreso(Integer id);

    public void delete(TEgreso egreso);

    public void update(TEgreso Egreso);

}
