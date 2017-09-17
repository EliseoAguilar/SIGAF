/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TDepreciacion;
import java.util.List;

/**
 *
 * @author Eliseo Aguilar
 */
public interface IDepreciacionBo {
    
    public void create(TDepreciacion depreciacion);

    public TDepreciacion getDepreciacion(Integer id);

    public List<TDepreciacion> listDepreciacion(Integer id);

    public void delete(Integer id);

    public void update(TDepreciacion depreciacion);

    
}
