/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IPeriodoBo;
import com.sigaf.Idao.IPeriodoDao;

import com.sigaf.pojo.TPeriodo;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public class PeriodoBo implements IPeriodoBo{
    
    private IPeriodoDao periodoDao;

    public IPeriodoDao getPeriodoDao() {
        return periodoDao;
    }

    public void setPeriodoDao(IPeriodoDao periodoDao) {
        this.periodoDao = periodoDao;
    }
       
    @Override
    public void create(TPeriodo periodo) {
    periodoDao.create(periodo);
    }

    @Override
    public TPeriodo getPeriodo(Integer id) {
      return periodoDao.getPeriodo(id);
    }

    @Override
    public List<TPeriodo> listPeriodo(Integer id) {
    return periodoDao.listPeriodo(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TPeriodo periodo) {
    this.periodoDao.update(periodo);
    }





    @Override
    public TPeriodo getPeriodoAbierto(Integer id) {
    return periodoDao.getPeriodoAbierto(id);
    }
    
}
