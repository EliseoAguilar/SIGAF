/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TEntidadProyecto;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IEntidadProyectoDao {
    
   public void create(TEntidadProyecto entidadProyecto);

    public TEntidadProyecto getTEntidadProyecto(Integer id);

    public List<TEntidadProyecto> listTEndidadProyectos();
    
    public List<TEntidadProyecto> listTEndidadProyectos(Integer id);
    
    public List<TEntidadProyecto> listTEndidadProyectosResolucion(Integer id);
    
    public List<TEntidadProyecto> listTEndidadProyectosAprovados(Integer id);
    
    public List<TEntidadProyecto> listTEndidadProyectosAprovadosEjecutandose(Integer id);
    
    public List<TEntidadProyecto> listTEndidadProyectosEjecutandose(Integer id);
    
    public List<TEntidadProyecto> listTEndidadProyectosTodas(Integer id);
    
    public List<TEntidadProyecto> listTEndidadProyectosNoAprobados();
    
    public List<TEntidadProyecto> listTEndidadProyectosAprovadosClinte(Integer id);

    public void delete(TEntidadProyecto entidadProyecto);

    public void update(TEntidadProyecto entidad);  
    
}
