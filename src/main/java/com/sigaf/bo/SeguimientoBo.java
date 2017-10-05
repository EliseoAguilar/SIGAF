/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.ISeguimientoBo;
import com.sigaf.Idao.ISeguimientoDao;
import com.sigaf.pojo.TSeguimiento;
import java.util.List;

/**
 *
 * @author Genovés
 */
public class SeguimientoBo implements ISeguimientoBo {
    
    private ISeguimientoDao iseguimientoDao;
    
    public ISeguimientoDao getIseguimientoDao() {
        return iseguimientoDao;
    }
    
    public void setIseguimientoDao(ISeguimientoDao iseguimientoDao) {
        this.iseguimientoDao = iseguimientoDao;
    }
    
    @Override
    public void create(TSeguimiento seguimiento) {
        this.iseguimientoDao.create(seguimiento);
    }
    
    @Override
    public TSeguimiento getSeguimiento(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TSeguimiento> listSeguimiento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(TSeguimiento seguimiento) {
        this.iseguimientoDao.update(seguimiento);
        
    }
    
    @Override
    public List<TSeguimiento> listaProyectoSeguimiento(int i) {
        return this.iseguimientoDao.listaProyectoSeguimiento(i);
    }

    @Override
    public void delete(TSeguimiento seguimiento) {
        this.iseguimientoDao.delete(seguimiento);
    
    }
    
}
