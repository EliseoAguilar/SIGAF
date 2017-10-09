/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TProductorIndividual;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IProductorIndividualBo {

    public void create(TProductorIndividual ProductorIndividual);

    public TProductorIndividual getProdcutorGrupal(Integer id);

    public List<TProductorIndividual> listProductorIndividual();
    
    public List<TProductorIndividual> listProductorIndividualActivos();

    public boolean getProdcutorIndividual(String dui);

    public TProductorIndividual getProdcutorIndividualRepre(Integer id);

    public TProductorIndividual getProdcutorIndividual(Integer id);

    public void delete(Integer id);

    public void update(TProductorIndividual ProductorIndividual);

}
