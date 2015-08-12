package com.ciet.base.bo;

import com.ciet.base.dao.VerticeDAO;
import com.ciet.base.dao.VerticeDAOImpl;
import com.ciet.base.model.Vertice;

/**
 * 
* VerticeBOImpl.java
* 
* <P>Implementa��o da camada BO aplicando as regras de negocio
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public class VerticeBOImpl implements VerticeBO {

	private VerticeDAO verticeDAO = new VerticeDAOImpl();
	
	/**
	 * Regras de negocio referentes � persistencia de um vertice
	 * @param nomeVertice
	 * @return
	 */
	@Override
	public Vertice gravarVertice(String nomeVertice) {
		try {
			Vertice vertice = verticeDAO.getVerticePorNome(nomeVertice);//Verifica se o vertice existe
			if(vertice != null){
				return vertice; //Como o vertice s� possui id e nome e o nome � unico, ent�o ele nunca ser� atualizado
			}
			vertice = new Vertice(nomeVertice); //Se n�o existir ser� criado um novo registro
			return verticeDAO.gravarVertice(vertice);
		} catch (Exception e) { //Tratamento de exce��o simplificado em virtude do tempo TODO Verificar as exce��es reais
			System.out.println("Vertice j� existe");
		}
		return null;
	}
	
}
