/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IProductorIndividualBo;
import com.sigaf.Idao.IProductorIndividualDao;
import com.sigaf.pojo.TProductorIndividual;
import java.util.List;

/**
 *
 * @author yonat
 */
public class ProductorIndividualBo implements IProductorIndividualBo {

    private IProductorIndividualDao productorIndividualDao;

    public IProductorIndividualDao getProductorIndividualDao() {
        return productorIndividualDao;
    }

    public void setProductorIndividualDao(IProductorIndividualDao productorIndividualDao) {
        this.productorIndividualDao = productorIndividualDao;
    }

    @Override
    public void create(TProductorIndividual ProductorIndividual) {
        this.productorIndividualDao.create(ProductorIndividual);
    }

    @Override
    public TProductorIndividual getProdcutorGrupal(Integer id) {
        return null;
    }

    @Override
    public List<TProductorIndividual> listProductorIndividual() {
        return this.productorIndividualDao.listProductorIndividual();

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(TProductorIndividual ProductorIndividual) {
        this.productorIndividualDao.update(ProductorIndividual);
    }

    @Override
    public boolean getProdcutorIndividual(String dui) {
        return this.productorIndividualDao.getProdcutorIndividual(dui);
    }

    @Override
    public TProductorIndividual getProdcutorIndividual(Integer id) {
        return this.productorIndividualDao.getProdcutorIndividual(id);

    }

    @Override
    public TProductorIndividual getProdcutorIndividualRepre(Integer id) {
    return this.productorIndividualDao.getProdcutorIndividualRepre(id);
    
    }

    @Override
    public List<TProductorIndividual> listProductorIndividualActivos() {
       return this.productorIndividualDao.listProductorIndividualActivos();
    }

}
