/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IAreaBo;
import com.sigaf.Idao.IAreaDao;
import com.sigaf.pojo.TArea;
import java.util.List;

/**
 *
 * @author Genoves
 */
public class AreaBo implements IAreaBo {

    private IAreaDao areaDao;

    public IAreaDao getAreaDao() {
        return areaDao;
    }

    public void setAreaDao(IAreaDao areaDao) {
        this.areaDao = areaDao;
    }

    @Override
    public void create(TArea area) {
        this.areaDao.create(area);
    }

    @Override
    public TArea getTArea(Integer id) {
        return this.areaDao.getTArea(id);
    }

    @Override
    public List<TArea> listArea(Integer id) {
        return areaDao.listTArea(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TArea area) {
        this.areaDao.update(area);
    }

    @Override
    public TArea getTAreaRepAct(Integer idEnt, Integer idAre, String codigo) {
        return this.areaDao.getTAreaRepAct(idEnt, idAre, codigo);
    }

    @Override
    public TArea getTAreaRep(Integer idEnt, String codigo) {
        return this.areaDao.getTAreaRep(idEnt, codigo);
    }

    @Override
    public List<TArea> listArea2(Integer id) {
        return areaDao.listArea2(id);
    }

}
