/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IClienteProyectoDao;
import com.sigaf.pojo.TClienteProyecto;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class ClienteProyectoDao implements IClienteProyectoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TClienteProyecto ClienteProyecto) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ClienteProyecto);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TClienteProyecto getClienteProyecto(Integer id) {
       
        Session session = this.sessionFactory.openSession();
        TClienteProyecto clienteProyecto =  (TClienteProyecto) session.createQuery("from TClienteProyecto where TCliente.idcliente =:id").setParameter("id", id).uniqueResult();
        session.close();
        return clienteProyecto;
    
    
    
    }

    @Override
    public List<TClienteProyecto> listClienteProyecto() {

        Session session = this.sessionFactory.openSession();
        List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto t inner join fetch t.TProyecto  inner join fetch t.TCliente").list();
        session.close();
        return listaClienteProyecto;

    }

    @Override
    public void update(TClienteProyecto ClienteProyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TClienteProyecto> listTClienteProyecto(Integer id) {

        if (id == 0) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.estado=1").list();
            session.close();
            return listaClienteProyecto;

        }
        
      

        if (id == 8) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where (f.TTipoCredito.idTipoCredito=8 OR f.TTipoCredito.idTipoCredito=9 OR f.TTipoCredito.idTipoCredito=4 ) AND f.estado=1").list();
            session.close();
            return listaClienteProyecto;

        } else if (id == 6) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=6 AND f.estado=1").list();
            session.close();
            return listaClienteProyecto;

        }else if (id == 1) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=1 AND f.estado=1").list();
            session.close();
            return listaClienteProyecto;

        }
        
        
        else if (id == 7) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=7 AND f.estado=1").list();
            session.close();
            return listaClienteProyecto;

        } else {// igual a 5

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=5 AND f.estado=1").list();
            session.close();
            return listaClienteProyecto;

        }

    }

    @Override
    public List<TClienteProyecto> listTClienteProyectoResolucion(Integer id) {

        if (id == 0) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.estado=2").list();
            session.close();
            return listaClienteProyecto;

        }

        if (id == 8) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where ((f.TTipoCredito.idTipoCredito=8 OR f.TTipoCredito.idTipoCredito=9 OR f.TTipoCredito.idTipoCredito=4) AND f.estado=2)").list();
            session.close();
            return listaClienteProyecto;

        } else if (id == 6) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=6 AND f.estado=2").list();
            session.close();
            return listaClienteProyecto;

        } else if (id == 7) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=7 AND f.estado=2").list();
            session.close();
            return listaClienteProyecto;

        }else if (id == 1) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=1 AND f.estado=2").list();
            session.close();
            return listaClienteProyecto;

        } else {// igual a 5

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=5 AND f.estado=2").list();
            session.close();
            return listaClienteProyecto;

        }

    }

    @Override
    public List<TClienteProyecto> listTClienteProyectoAprovados(Integer id) {

        if (id == 0) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.estado=3 or f.estado=5 or f.estado=6 or f.estado=7 ").list();
            session.close();
            return listaClienteProyecto;

        }

        if (id == 8) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where (f.TTipoCredito.idTipoCredito=8 OR f.TTipoCredito.idTipoCredito=9 OR f.TTipoCredito.idTipoCredito=4) AND (f.estado=3 or f.estado=5 or f.estado=6 or f.estado=7)").list();
            session.close();
            return listaClienteProyecto;

        } else if (id == 6) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=6 AND (f.estado=3 or f.estado=5 or f.estado=6 or f.estado=7)").list();
            session.close();
            return listaClienteProyecto;

        } else if (id == 7) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=7 AND (f.estado=3 or f.estado=5 or f.estado=6 or f.estado=7)").list();
            session.close();
            return listaClienteProyecto;

        }else if (id == 1) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=1 AND (f.estado=3 or f.estado=5 or f.estado=6 or f.estado=7)").list();
            session.close();
            return listaClienteProyecto;

        } else {// igual a 5

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=5 AND (f.estado=3 or f.estado=5 or f.estado=6 or f.estado=7 )").list();
            session.close();
            return listaClienteProyecto;

        }

    }

    @Override
    public List<TClienteProyecto> listTClienteProyectoAprovadosComprabacion(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.estado=3 AND p.TCliente.idCliente=:id").setParameter("id", id).list();
        session.close();
        return listaClienteProyecto;

    }

    @Override
    public List<TClienteProyecto> listTClienteProyectoAprovadosEjecutandose() {

        Session session = this.sessionFactory.openSession();
        List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.estado=5 or f.estado=6 or f.estado=7").list();
        session.close();
        return listaClienteProyecto;

    }

    @Override
    public List<TClienteProyecto> listTClienteProyectoNoAprobados(Integer id) {

        if (id == 0) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.estado=3 or f.estado=5 or f.estado=6 ").list();
            session.close();
            return listaClienteProyecto;

        }

        if (id == 8) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where (f.TTipoCredito.idTipoCredito=8 OR f.TTipoCredito.idTipoCredito=9 OR f.TTipoCredito.idTipoCredito=4) AND (f.estado=4)").list();
            session.close();
            return listaClienteProyecto;

        } else if (id == 6) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=6 AND (f.estado=4)").list();
            session.close();
            return listaClienteProyecto;

        } else if (id == 7) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=7 AND (f.estado=4)").list();
            session.close();
            return listaClienteProyecto;

        }else if (id == 1) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=1 AND (f.estado=4)").list();
            session.close();
            return listaClienteProyecto;

        } else {// igual a 5

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=5 AND (f.estado=4)").list();
            session.close();
            return listaClienteProyecto;

        }

    }

    @Override
    public List<TClienteProyecto> listTClienteProyectoTodas(Integer id) {

        if (id == 0) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.estado=3 or f.estado=5 or f.estado=6 or f.estado=6 ").list();
            session.close();
            return listaClienteProyecto;

        }

        if (id == 8) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where (f.TTipoCredito.idTipoCredito=8 OR f.TTipoCredito.idTipoCredito=9 OR f.TTipoCredito.idTipoCredito=4) AND (f.estado=3 or f.estado=5 or f.estado=6 or f.estado=4)").list();
            session.close();
            return listaClienteProyecto;

        } else if (id == 6) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=6 AND (f.estado=3 or f.estado=5 or f.estado=6 or f.estado=4)").list();
            session.close();
            return listaClienteProyecto;

        } else if (id == 7) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=7 AND (f.estado=3 or f.estado=5 or f.estado=6 or f.estado=4)").list();
            session.close();
            return listaClienteProyecto;

        }  else if (id == 1) {

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=1 AND (f.estado=3 or f.estado=5 or f.estado=6 or f.estado=4)").list();
            session.close();
            return listaClienteProyecto;

        } else {// igual a 5

            Session session = this.sessionFactory.openSession();
            List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.TTipoCredito.idTipoCredito=5 AND (f.estado=3 or f.estado=5 or f.estado=6 or f.estado=4)").list();
            session.close();
            return listaClienteProyecto;

        }

    }

    @Override
    public List<TClienteProyecto> listTClienteProyectoEjecutandose(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TClienteProyecto> listaClienteProyecto = session.createQuery("from TClienteProyecto p inner join fetch p.TProyecto f inner join fetch p.TCliente c where f.estado=5 or f.estado=6 or f.estado=7 or f.estado=3 ").list();
        session.close();
        return listaClienteProyecto;

    }

    @Override
    public void delete(TClienteProyecto clienteProyecto) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(clienteProyecto);
        session.getTransaction().commit();
        session.close();

    }

}
