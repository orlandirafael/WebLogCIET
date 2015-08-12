package com.ciet.base.dao;

import com.ciet.base.model.Aresta;
import com.ciet.base.model.Vertice;

/**
 * 
* ArestaDAO.java
* 
* <P>Interface com os contratos para manipulação dos dados das arestas em banco
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public interface ArestaDAO {
	/**
	 * Persiste um objeto aresta no banco
	 */
	public Aresta gravarAresta(Aresta aresta);
	/**
	 * Busca um objeto aresta pelo vertices que fazem parte dele
	 */
	public Aresta getArestaPorVertices(Vertice vertice1, Vertice vertice2);
	
}
