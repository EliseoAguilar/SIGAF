/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TCliente;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IClienteBo {
    
    public void create(TCliente Agronegocio);

    public TCliente getCliente(Integer id);
    
    public List<TCliente> listCliente();
    
    public List<TCliente> listClienteCodigo();
    
    public Boolean getDuiCliente(String dui);
    
    public Boolean getNitCliente(String nit);

    public void delete(TCliente cliente);

    public void update(TCliente Cliente);
    
}
