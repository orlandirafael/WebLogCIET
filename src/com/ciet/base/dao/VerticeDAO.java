package com.ciet.base.dao;

import com.ciet.base.model.Vertice;

/**
 * 
* VerticeDAO.java
* 
* <P>Interface com os contratos para manipulações dos dados dos vertices em banco
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public interface VerticeDAO {
	
	/**
	 * Persiste um objeto do tipo vertice no banco
	 */
	public Vertice gravarVertice(Vertice vertice);
	
	/**
	 * Buscar um objeto vertice do banco pelo seu nome
	 */
	public Vertice getVerticePorNome(String nome);
	
}
