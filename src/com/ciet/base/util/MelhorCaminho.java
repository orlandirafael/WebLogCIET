package com.ciet.base.util;

import java.util.Map;

/**
 * 
* MelhorCaminho.java
* 
* <P>Interface de configuração do grafo de acordo com a origem
* 
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public interface MelhorCaminho {
	
	public Map<String, VerticeComparable> montaGrafoPonderado(String origem) throws Exception;

}
