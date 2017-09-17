/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TAsesoria;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IAsesoriaBo {
    
    public void create(TAsesoria asesoria);

    public TAsesoria getAsesoria(Integer id);
    
    public List<TAsesoria> listAsesoria();

    public void delete(Integer id);

    public void update(TAsesoria asesoria);
    
}
