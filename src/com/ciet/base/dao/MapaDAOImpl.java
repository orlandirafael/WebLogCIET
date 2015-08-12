package com.ciet.base.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ciet.base.model.Mapa;
import com.ciet.base.util.HibernateUtil;

/**
 * 
* MapaDAOImpl.java
* 
* <P>Classe de implementação das manipulações dos dados dos mapas em banco
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public class MapaDAOImpl implements MapaDAO{

	/**
	 * Persiste um objeto mapa no banco
	 * @param mapa
	 * @return
	 */
    @Override
    public Mapa gravarMapa(Mapa mapa) {
    	try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(mapa);
            tx.commit();
            session.close();
            return mapa;
    	}catch(Throwable t){
    		t.printStackTrace();
    	}
    	return null;
    }
    
	/**
	 * Busca um mapa pelo nome
	 * @param nome
	 * @return
	 */
	@Override
	public Mapa getMapa(String nome) {
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query q = (Query)session.createQuery("from Mapa where nome = :nome");
			q.setString("nome", nome);
			Mapa mapa = (Mapa)q.uniqueResult();
			if(mapa!= null){
				session.evict(mapa); //Metodo usado para remover o objeto do cache da sessão e evitar erros na manipulação nas camadas superiores	
			}
			session.close();
			return mapa;
		}catch(Throwable t){
			t.printStackTrace();
		}
		return null;
	}

	/**
	 * Busca o mapa mais recente cadastrado no sistema para quando realizar operações sem informa-lo
	 * @return
	 */
	@Override
	public Mapa getMapaMaisRecente() {
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query q = (Query)session.createQuery("from Mapa ORDER by data");
			q.setMaxResults(1);
			Mapa mapa = (Mapa)q.uniqueResult();
			if(mapa!= null){
				session.evict(mapa);//Metodo usado para remover o objeto do cache da sessão e evitar erros na manipulação nas camadas superiores
			}
			session.close();
			return mapa;
		}catch(Throwable t){
			t.printStackTrace();
		}
		return null;
	}

	/**
	 * Remove um mapa do sistema pelo seu nome
	 * @param nomeMapa
	 */
	@Override
	public void removerMapa(String nomeMapa) {
    	try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q = (Query)session.createQuery("DELETE FROM Mapa WHERE nome = :nome");
            q.setString("nome", nomeMapa);
            q.executeUpdate();
            tx.commit();
            session.close();
    	}catch(Throwable t){
    		t.printStackTrace();
    	}
	}

}
