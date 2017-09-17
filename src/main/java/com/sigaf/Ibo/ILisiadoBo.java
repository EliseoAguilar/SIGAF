/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TLisiado;
import java.util.List;

/**
 *
 * @author Genovés
 */
public interface ILisiadoBo {

    public void create(TLisiado lisiado);

    public TLisiado getLisiado(Integer id);

    public List<TLisiado> listTLisiado();

    public void delete(TLisiado lisiado);

    public void update(TLisiado lisiado);

}
