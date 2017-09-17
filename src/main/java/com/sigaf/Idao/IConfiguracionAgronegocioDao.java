/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TConfiguracionAgronegocio;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IConfiguracionAgronegocioDao {

    public void create(TConfiguracionAgronegocio Configuracion);

    public TConfiguracionAgronegocio getConfiguracion(Integer id);

    public List<TConfiguracionAgronegocio> listConfiguracion();

    public void delete(Integer id);

    public void update(TConfiguracionAgronegocio Configuracion);

}
