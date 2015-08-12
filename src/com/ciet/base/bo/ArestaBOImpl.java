package com.ciet.base.bo;

import com.ciet.base.dao.ArestaDAO;
import com.ciet.base.dao.ArestaDAOImpl;
import com.ciet.base.model.Aresta;
import com.ciet.base.model.Vertice;

/**
 * 
* ArestaBOImpl.java
* 
* <P>Implementação da camada BO aplicando as regras de negocio para a entidade Aresta
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public class ArestaBOImpl implements ArestaBO{

	private ArestaDAO arestaDAO = new ArestaDAOImpl();

	/**
	 * Regras de negocio referentes à persistencia de uma aresta no sistema
	 * @param aresta
	 * @return
	 * @throws Exception
	 */
	@Override
	public Aresta getArestaPorVertices(Vertice vertice1, Vertice vertice2) throws Exception{
		//Valida se o vertice1 não é nulo ou possui o campo nome nulo
		if(vertice1==null || vertice1.getNome()==null){
			throw new Exception("Valor do vertice 1 passado incorretamente");
		}
		//Valida se o vertice2 não é nulo ou possui o campo nome nulo
		if(vertice2==null || vertice2.getNome()==null){
			throw new Exception("Valor do vertice 2 passado incorretamente");
		}
		//Busca o vertice no banco
		return arestaDAO.getArestaPorVertices(vertice1, vertice2);
	}
	
	/**
	 * Regras de negocio referentes à persistencia de uma aresta no sistema
	 * @param aresta
	 * @return
	 * @throws Exception
	 */
	@Override
	public Aresta gravarAresta(Aresta aresta) throws Exception {
		try {
			//Verifica se uma aresta existe no sistema
			Aresta a = getArestaPorVertices(aresta.getVert1(), aresta.getVert2());
			if(a != null){
				aresta.setId(a.getId());//Caso exista o id é setado para que ela seja atualizada ao invés de criar um novo registro
			}
			return arestaDAO.gravarAresta(aresta);//Efetua a persistencia
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
