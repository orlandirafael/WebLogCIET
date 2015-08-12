package com.ciet.base.bo;

import com.ciet.base.model.Vertice;

/**
 * 
* VerticeBO.java
* 
* <P>Interface com os contratos referentes as regras de negocio da classe Vertice
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public interface VerticeBO {

	/**
	 * Regras de negocio referentes à persistencia de um vertice
	 * @param nomeVertice
	 * @return
	 */
    public Vertice gravarVertice(String nomeVertice);
    
}
