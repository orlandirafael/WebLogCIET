package com.ciet.base.dao;

import com.ciet.base.model.Mapa;

/**
 * 
* MapaDAO.java
* 
* <P>Interface com os contratos de manipulação dos dados dos mapas em banco
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public interface MapaDAO {
	
	/**
	 * Persiste um objeto mapa no banco
	 * @param mapa
	 * @return
	 */
	public Mapa gravarMapa(Mapa mapa);

	/**
	 * Busca um mapa pelo nome
	 * @param nome
	 * @return
	 */
	public Mapa getMapa(String nome);
	
	/**
	 * Busca o mapa mais recente cadastrado no sistema para quando realizar operações sem informa-lo
	 * @return
	 */
	public Mapa getMapaMaisRecente();
	
	/**
	 * Remove um mapa do sistema pelo seu nome
	 * @param nomeMapa
	 */
	public void removerMapa(String nomeMapa);
}
