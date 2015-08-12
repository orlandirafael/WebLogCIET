package com.ciet.base.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ciet.base.model.Aresta;
import com.ciet.base.model.Vertice;
import com.ciet.base.util.HibernateUtil;

/**
 * 
* ArestaDAOImpl.java
* 
* <P>Classe de implementação das manipulações dos dados das arestas em banco
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public class ArestaDAOImpl implements ArestaDAO{

	/**
	 * Persiste um objeto aresta no banco
	 */
	@Override
	public Aresta gravarAresta(Aresta aresta) {
    	try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(aresta);
            tx.commit();
            session.evict(aresta);
            session.close();
            return aresta;
    	}catch(Throwable t){
    		t.printStackTrace();
    	}
    	return null;
	}
	
	/**
	 * Busca um objeto aresta pelo vertices que fazem parte dele
	 */
	@Override
	public Aresta getArestaPorVertices(Vertice vertice1, Vertice vertice2) {
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query q = (Query)session.createQuery("from Aresta Where vert1.nome = :nome1 and vert2.nome = :nome2");
			q.setString("nome1", vertice1.getNome());
			q.setString("nome2", vertice2.getNome());
			Aresta aresta = (Aresta)q.uniqueResult();
			if(aresta!= null){
				session.evict(aresta);
			}
			session.close();
			return aresta;
		}catch(Throwable t){
			t.printStackTrace();
		}
		return null;
	}

}
