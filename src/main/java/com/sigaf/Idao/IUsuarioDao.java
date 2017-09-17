/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TUsuario;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public interface IUsuarioDao {

    public void create(TUsuario usuario);

    public TUsuario getUsuario(Integer id);

    public TUsuario getUsuarioValRep(String nombreUsuario);

    public List<TUsuario> listUsuario();

    public void delete(Integer id);

    public void update(TUsuario usuario);

    public TUsuario login(String nombre, String clave);

    public TUsuario resetPass(String mail);
}
