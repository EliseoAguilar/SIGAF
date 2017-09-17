/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TCooperativa;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface ICooperativaDao {
    
    public void create(TCooperativa coopertativa);

    public TCooperativa getTCooperativa(Integer id);

    public List<TCooperativa> listTCooperativas();

     public void delete(TCooperativa cooperativa);

    public void update(TCooperativa cooperativa);
    

}
