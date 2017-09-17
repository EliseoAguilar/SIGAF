/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TPeriodo;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public interface IPeriodoBo {

    public void create(TPeriodo periodo);

    public TPeriodo getPeriodo(Integer id);

    public List<TPeriodo> listPeriodo(Integer id);

    public void delete(Integer id);

    public void update(TPeriodo periodo);
    
     public TPeriodo getPeriodoAbierto(Integer id);

}
