/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TPagoAgronegocio;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IPagoAgronegocioDao {
    
    public void create(TPagoAgronegocio PagoAgronegocio);

    public TPagoAgronegocio getPagoAgronegocio(Integer id);
    
    public boolean getTPagoAgronegocio(String nombre);
    
    public Integer getCorrelativo();

    public List<TPagoAgronegocio> listPagoAgronegocio();

    public void delete(Integer id);

    public void update(TPagoAgronegocio PagoAgronegocio);
    
}
