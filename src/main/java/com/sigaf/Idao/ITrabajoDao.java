/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TTrabajo;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface ITrabajoDao {
    
    public void create(TTrabajo trabajo);

    public TTrabajo getTrabajo(Integer id);

    public List<TTrabajo> listTrabajo();

    public void delete(TTrabajo trabajo);

    public void update(TTrabajo trabajo);
    
}
