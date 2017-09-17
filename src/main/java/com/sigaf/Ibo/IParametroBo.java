/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TParametro;
import com.sigaf.pojo.TParametroseguimiento;
import java.util.List;

/**
 *
 * @author Genovés
 */
public interface IParametroBo {
    
    public void create(TParametro paramentro);

    public TParametro getParametro(Integer id);
    
    public List<TParametroseguimiento> listParametro();
    
    public List<TParametro> listParametro2(Integer id);

    public void delete(TParametro paramentro);

    public void update(TParametro parametro);
    
    public Boolean getParametroRepetido(String nombre);
    
}
