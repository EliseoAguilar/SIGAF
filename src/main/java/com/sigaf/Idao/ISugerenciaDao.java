/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TSugerencia;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface ISugerenciaDao {
    
    public void create(TSugerencia sugerencia);

    public TSugerencia getSugerencia(Integer id);

    public List<TSugerencia> listSugerencia(Integer id);

    public void delete(Integer id); 

    public void update(TSugerencia sugerencia);
    
}
