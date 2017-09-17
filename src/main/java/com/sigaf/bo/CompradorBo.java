/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.ICompradorBo;
import com.sigaf.Idao.IAreaCultivoDao;
import com.sigaf.Idao.ICompradorDao;
import com.sigaf.pojo.TComprador;
import java.util.List;

/**
 *
 * @author yonat
 */
public class CompradorBo implements ICompradorBo {

    private ICompradorDao CompradorDao;

    public ICompradorDao getCompradorDao() {
        return CompradorDao;
    }

    public void setCompradorDao(ICompradorDao CompradorDao) {
        this.CompradorDao = CompradorDao;
    }

    @Override
    public void create(TComprador Comprador) {

        this.CompradorDao.create(Comprador);

    }

    @Override
    public TComprador getComprador(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TComprador> listComprador() {
        return this.CompradorDao.listComprador();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TComprador Comprador) {
        this.CompradorDao.update(Comprador);
    }

    @Override
    public boolean getTCompradorCodigo(String codigo) {
    
    return this.CompradorDao.getTCompradorCodigo(codigo);
    }

}
