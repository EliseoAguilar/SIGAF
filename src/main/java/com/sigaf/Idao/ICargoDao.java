/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;


import com.sigaf.pojo.TCargo;
import java.util.List;

/**
 *
 * @author Genoves
 */
public interface ICargoDao {

    public void create(TCargo cargo);

    public TCargo getTCargo(Integer id);

    public List<TCargo> listTCargo(Integer id);

    public void delete(Integer id);

    public void update(TCargo cargo);

}
