/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IPropiedadLisiadoBo;
import com.sigaf.Idao.IPropiedadLisiadoDao;
import com.sigaf.pojo.TPropiedadLisiado;
import java.util.List;

/**
 *
 * @author Genovés
 */
public class PropiedadLisiadoBo implements IPropiedadLisiadoBo{

    private IPropiedadLisiadoDao ipropiedadLisiadoDao;

    public IPropiedadLisiadoDao getIpropiedadLisiadoDao() {
        return ipropiedadLisiadoDao;
    }

    public void setIpropiedadLisiadoDao(IPropiedadLisiadoDao ipropiedadLisiadoDao) {
        this.ipropiedadLisiadoDao = ipropiedadLisiadoDao;
    }
    
    
    
    @Override
    public void create(TPropiedadLisiado propiedad) {
        
        this.ipropiedadLisiadoDao.create(propiedad);
    }

    @Override
    public TPropiedadLisiado getTPropiedadLisiado(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TPropiedadLisiado> listTPropiedadLisiado(Integer id) {
        
        return this.ipropiedadLisiadoDao.listTPropiedadLisiado(id);
        
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TPropiedadLisiado propiedad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
