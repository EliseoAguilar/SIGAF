/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TComentario;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IComentarioBo {

    public void create(TComentario asesoria);

    public TComentario getComentario(Integer id);

    public List<TComentario> listComentario(Integer id);

    public void delete(Integer id);

    public void update(TComentario asesoria);

}
