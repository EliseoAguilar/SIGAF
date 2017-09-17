/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TComprador;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface ICompradorBo {
    
    public void create(TComprador Comprador);

    public TComprador getComprador(Integer id);
    
    public boolean getTCompradorCodigo(String codigo);

    public List<TComprador> listComprador();

    public void delete(Integer id);

    public void update(TComprador Comprador);
    
}
