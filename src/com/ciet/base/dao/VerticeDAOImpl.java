package com.ciet.base.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ciet.base.model.Vertice;
import com.ciet.base.util.HibernateUtil;

/**
 * 
* VerticeDAOImpl.java
* 
* <P>Classe de implementação das manipulações dos dados dos vertices em banco
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public class VerticeDAOImpl implements VerticeDAO{

	/**
	 * Persiste um objeto do tipo vertice no banco
	 */
	@Override
	public Vertice gravarVertice(Vertice vertice) {
    	try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(vertice);
            tx.commit();
            session.evict(vertice); //Metodo usado para remover o objeto do cache da sessão e evitar erros na manipulação nas camadas superiores 
            session.close();
            return vertice;
    	}catch(Throwable t){
    		t.printStackTrace();
    	}
    	return null;
    }

	/**
	 * Buscar um objeto vertice do banco pelo seu nome
	 */
	@Override
	public Vertice getVerticePorNome(String nome) {
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query q = session.createQuery("from Vertice where nome = :nome");
			q.setString("nome", nome);
			Vertice vertice = (Vertice) q.uniqueResult();
			session.close();
			return vertice;
		}catch(Throwable t){
			t.printStackTrace();
		}
		return null;
	}

}
