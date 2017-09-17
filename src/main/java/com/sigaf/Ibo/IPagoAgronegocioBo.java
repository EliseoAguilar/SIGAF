/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TPagoAgronegocio;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IPagoAgronegocioBo {
    
    public void create(TPagoAgronegocio PagoAgronegocio);

    public TPagoAgronegocio getPagoAgronegocio(Integer id);
    
    public boolean getTPagoAgronegocio(String nombre);

    public List<TPagoAgronegocio> listPagoAgronegocio();
    
    public Integer getCorrelativo();

    public void delete(Integer id);

    public void update(TPagoAgronegocio PagoAgronegocio);
    
}
