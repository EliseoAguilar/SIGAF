/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TBitacora;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public interface IBitacoraDao {
    
    public void create(TBitacora bitacora);

    public TBitacora getBitacora(Integer id);

    public List<TBitacora> listBitacoraFechas(Integer id, Date fecha1 , Date fecha2);

    public List<TBitacora> listBitacora(Integer id);
    
    public void delete(Integer id);

    public void update(TBitacora bitacora);
}
