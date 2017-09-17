/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IComentarioBo;
import com.sigaf.Idao.IComentarioDao;
import com.sigaf.pojo.TComentario;
import java.util.List;

/**
 *
 * @author yonat
 */
public class ComentarioBo implements IComentarioBo {

    private IComentarioDao icomentarioDao;

    public IComentarioDao getIcomentarioDao() {
        return icomentarioDao;
    }

    public void setIcomentarioDao(IComentarioDao icomentarioDao) {
        this.icomentarioDao = icomentarioDao;
    }

    @Override
    public void create(TComentario asesoria) {

        this.icomentarioDao.create(asesoria);

    }

    @Override
    public TComentario getComentario(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TComentario asesoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TComentario> listComentario(Integer id) {
        return this.icomentarioDao.listComentario(id);
    }

}
