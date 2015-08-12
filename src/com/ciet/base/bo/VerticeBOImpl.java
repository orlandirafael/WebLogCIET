package com.ciet.base.bo;

import com.ciet.base.dao.VerticeDAO;
import com.ciet.base.dao.VerticeDAOImpl;
import com.ciet.base.model.Vertice;

/**
 * 
* VerticeBOImpl.java
* 
* <P>Implementação da camada BO aplicando as regras de negocio
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public class VerticeBOImpl implements VerticeBO {

	private VerticeDAO verticeDAO = new VerticeDAOImpl();
	
	/**
	 * Regras de negocio referentes à persistencia de um vertice
	 * @param nomeVertice
	 * @return
	 */
	@Override
	public Vertice gravarVertice(String nomeVertice) {
		try {
			Vertice vertice = verticeDAO.getVerticePorNome(nomeVertice);//Verifica se o vertice existe
			if(vertice != null){
				return vertice; //Como o vertice só possui id e nome e o nome é unico, então ele nunca será atualizado
			}
			vertice = new Vertice(nomeVertice); //Se não existir será criado um novo registro
			return verticeDAO.gravarVertice(vertice);
		} catch (Exception e) { //Tratamento de exceção simplificado em virtude do tempo TODO Verificar as exceções reais
			System.out.println("Vertice já existe");
		}
		return null;
	}
	
}
