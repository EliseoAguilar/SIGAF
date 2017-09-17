/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TReferencia;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IReferenciaBo {
    
    public void create(TReferencia Referencia);

    public TReferencia getReferencia(Integer id);
    
    public List<TReferencia> listReferencia();
    
    public List<TReferencia> listReferencia(Integer id);

    public void delete(TReferencia referencia);

    public void update(TReferencia Referencia);
    
    
}
