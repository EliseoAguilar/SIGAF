/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TPropiedadLisiado;
import java.util.List;

/**
 *
 * @author Genovés
 */
public interface IPropiedadLisiadoBo {

    public void create(TPropiedadLisiado propiedad);

    public TPropiedadLisiado getTPropiedadLisiado(Integer id);

    public List<TPropiedadLisiado> listTPropiedadLisiado(Integer id);

    public void delete(Integer id);

    public void update(TPropiedadLisiado propiedad);

}
