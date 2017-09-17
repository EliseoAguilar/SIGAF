/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TValorActivo;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public interface IValorActivoBo {
    
    public void create(TValorActivo valor);

    public TValorActivo getTValorActivo(Integer id);

    public List<TValorActivo> listTValorActivo();

    public void delete(Integer id);

    public void update(TValorActivo valor);
}
