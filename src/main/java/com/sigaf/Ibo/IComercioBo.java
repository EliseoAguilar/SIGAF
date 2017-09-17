/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TComercio;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IComercioBo {

    public void create(TComercio comercio);

    public TComercio getComercio(Integer id);

    public List<TComercio> listComercio();

    public void delete(TComercio comercio);

    public void update(TComercio comercio);

}
