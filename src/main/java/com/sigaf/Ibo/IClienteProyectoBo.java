/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TClienteProyecto;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IClienteProyectoBo {

    public void create(TClienteProyecto ClienteProyecto);

    public TClienteProyecto getClienteProyecto(Integer id);

    public List<TClienteProyecto> listClienteProyecto();
    
    public List<TClienteProyecto> listTClienteProyecto(Integer id);
    
    public List<TClienteProyecto> listTClienteProyectoResolucion(Integer id);
    
    public List<TClienteProyecto> listTClienteProyectoAprovados(Integer id);
    
    public List<TClienteProyecto> listTClienteProyectoEjecutandose(Integer id);
    
     public List<TClienteProyecto> listTClienteProyectoTodas(Integer id);
    
    public List<TClienteProyecto> listTClienteProyectoNoAprobados(Integer id);
    
    public List<TClienteProyecto> listTClienteProyectoAprovadosComprabacion(Integer id);
    
    public List<TClienteProyecto> listTClienteProyectoAprovadosEjecutandose();

    public void delete(TClienteProyecto clienteProyecto);

    public void update(TClienteProyecto ClienteProyecto);

}
