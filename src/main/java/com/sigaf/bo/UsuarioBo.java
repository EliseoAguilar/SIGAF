/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IUsuarioBo;
import com.sigaf.Idao.IUsuarioDao;
import com.sigaf.pojo.TUsuario;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public class UsuarioBo implements IUsuarioBo {

    private IUsuarioDao usuarioDao;

    public IUsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(IUsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public void create(TUsuario usuario) {
        this.usuarioDao.create(usuario);
    }

    @Override
    public TUsuario getUsuario(Integer id) {
       return this.usuarioDao.getUsuario(id);
    }

    @Override
    public List<TUsuario> listUsuario() {

        return this.usuarioDao.listUsuario();

    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TUsuario usuario) {
        this.usuarioDao.update(usuario);
    }

    @Override
    public TUsuario login(String nombre, String clave) {
    return this.usuarioDao.login(nombre, clave);
    }
      @Override
      public TUsuario getUsuarioValRep(String nombreUsuario){
       return this.usuarioDao.getUsuarioValRep(nombreUsuario);
      }

    @Override
        public TUsuario resetPass(String mail){
         return this.usuarioDao.resetPass( mail);
          }
}
