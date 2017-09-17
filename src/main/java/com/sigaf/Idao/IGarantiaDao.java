/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TGarantia;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IGarantiaDao {
    
    public void create(TGarantia garantia);

    public TGarantia getTGarantia(Integer id);

    public List<TGarantia> listTGarantia();
    
    public List<TGarantia> listTGarantia(Integer id);

   public void delete(TGarantia garantia);

    public void update(TGarantia entidad);
    
}
