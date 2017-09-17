/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TPago;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IPagoDao {
    
    public void create(TPago pago);

    public TPago getTPago(Integer id);

    public List<TPago> listTPago(Integer id);
    
    public Integer getCorrelativo();

    public void delete(TPago pago);

    public void update(TPago pago);
    
}
