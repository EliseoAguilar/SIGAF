/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IPoliticaBo;
import com.sigaf.Idao.IPoliticaDao;
import com.sigaf.pojo.TPolitica;
import java.util.List;

/**
 *
 * @author Genovés
 */
public class PoliticaBo implements IPoliticaBo {

    IPoliticaDao ipoliticaDao;

    public IPoliticaDao getIpoliticaDao() {
        return ipoliticaDao;
    }

    public void setIpoliticaDao(IPoliticaDao ipoliticaDao) {
        this.ipoliticaDao = ipoliticaDao;
    }

    @Override
    public void create(TPolitica politica) {
        this.ipoliticaDao.create(politica);
    }

    @Override
    public TPolitica getPolitica(Integer id) {

        return this.ipoliticaDao.getPolitica(id);
    }

    @Override
    public List<TPolitica> listPolitica(Integer id) {
        return this.ipoliticaDao.listPolitica(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Integer id) {
        this.ipoliticaDao.update(id);
    }

    @Override
    public TPolitica getPoliticaHistorica(Integer id) {

        return this.ipoliticaDao.getPoliticaHistorica(id);
    }

}
