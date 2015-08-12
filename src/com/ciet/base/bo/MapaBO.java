package com.ciet.base.bo;

import java.util.List;

import com.ciet.base.model.Mapa;


/**
 * 
* MapaBO.java
* 
* <P>Interface com os contratos referentes as regras de negocio da classe Mapa
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public interface MapaBO {
	/**
	 * Regras de negocio referentes à persistencia de um mapa
	 * @param nomeMapa
	 * @param malha Recebe uma malha que é relativa a um set de Arestas
	 * @return
	 * @throws Exception
	 */
    public Mapa gravarMapa(String nomeMapa, List<String> malha) throws Exception;
	/**
	 * Regras de negocio referentes à busca de um mapa no banco de dados pelo seu nome
	 * @param nomeMapa
	 * @return
	 */
    public Mapa getMapa(String nome);
	/**
	 * Regras de negocio referentes à busca de um mapa cadastrado mais recente no banco
	 * @return
	 */
    public Mapa getMapaMaisRecente();
	/**
	 * Regras de negocio referentes à remoção de um mapa do banco pelo nome
	 */
    public void removerMapa(String nomeMapa);

}
