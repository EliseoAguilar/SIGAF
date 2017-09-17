/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TAgropecuario;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IAgropecuarioDao {

    public void create(TAgropecuario Agropecuario);

    public TAgropecuario getAgropecuario(Integer id);

    public List<TAgropecuario> listAgropecuario();

     public void delete(TAgropecuario agropecuario);

    public void update(TAgropecuario Agropecuario);

}
