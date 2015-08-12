package com.ciet.base.bo;

import com.ciet.base.model.Aresta;
import com.ciet.base.model.Vertice;

/**
 * 
* ArestaBO.java
* 
* <P>Interface com os contratos referentes as regras de negocio da classe Aresta
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public interface ArestaBO {
	/**
	 * Regras de negocio referentes à busca de uma aresta pelos seus vertices
	 * @param vertice1
	 * @param vertice2
	 * @return
	 * @throws Exception
	 */
	public Aresta getArestaPorVertices(Vertice vertice1, Vertice vertice2) throws Exception;
	
	/**
	 * Regras de negocio referentes à persistencia de uma aresta no sistema
	 * @param aresta
	 * @return
	 * @throws Exception
	 */
	public Aresta gravarAresta(Aresta aresta) throws Exception;

}
