/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TSeguimiento;
import java.util.List;

/**
 *
 * @author Genovés
 */
public interface ISeguimientoDao {

    public void create(TSeguimiento seguimiento);

    public TSeguimiento getSeguimiento(Integer id);

    public List<TSeguimiento> listSeguimiento();

    public void delete(Integer id);

    public void update(TSeguimiento seguimiento);
    
     public List<TSeguimiento> listaProyectoSeguimiento(int i); 

}
