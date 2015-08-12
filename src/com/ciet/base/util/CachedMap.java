package com.ciet.base.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ciet.base.util.impl.MelhorCaminhoImpl;

/**
 * 
* CachedMap.java
* 
* <P>Classe singleton utilizada para guardar o cache de melhor caminho para várias entradas diferentes
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public class CachedMap {

	private static CachedMap instance = new CachedMap(); //Instancia unica da classe
	
	private String origemAtual = null; //Mantem a origem atual. Se ela for modificada, é necessário reprocessar o grafo
	private Map<String, VerticeComparable> cacheGrafo = new HashMap<String, VerticeComparable>(); //Cache do grafo processado para reduzir acesso ao banco
	private Map<String, List<VerticeComparable>> cacheMelhorCaminho = new HashMap<String, List<VerticeComparable>>(); //Cache do melhor caminho para reduzir acesso ao banco
	
	private MelhorCaminho melhorCaminho = new MelhorCaminhoImpl(); //Classe que efetua a tarefa de montar o grafo e encontrar o melhor caminho
	
	private Object lock = new Object(); //Atributo para garantir o lock em situações com multiplas threads
	
	public static CachedMap getInstance(){
		return instance;
	}
	
	//Construtor privado
	private CachedMap(){
	}
	
	/**
	 * Busca o melhor caminho nos caches. Se não existir ou se a origem e o destino forem novos, será recalculado sob demanda
	 * @param origem
	 * @param destino
	 * @return retorno uma lista com o melhor caminho encontrado
	 * @throws Exception
	 */
	public List<VerticeComparable> getMelhorCaminho(String origem, String destino) throws Exception{
		if(origem == null || destino == null){
			throw new Exception("Origem e destino não podem ser nulos");
		}
		if(!origem.equals(origemAtual)){//Se a origem for diferente, recalcula o grafo e zera o cache de melhor caminho encontrado
			synchronized (lock) {
				cacheGrafo = melhorCaminho.montaGrafoPonderado(origem);
				cacheMelhorCaminho = new HashMap<String, List<VerticeComparable>>();
			}
			origemAtual = origem;
		}
		
		if(!cacheGrafo.containsKey(destino)){
			throw new Exception("Destino inexistente na malha");
		}
		
		if(!cacheMelhorCaminho.containsKey(destino)){//Se não houver melhor caminho para o destino selecionado, recalcula
			synchronized (lock) {
				cacheMelhorCaminho.put(destino, cacheGrafo.get(destino).getMelhorCaminho());
			}
		}

		return cacheMelhorCaminho.get(destino);
	}
	
	//Metodo para resetar os caches externamente em caso de inclusão de nova malha
	public void resetCache(){
		origemAtual = null;
		synchronized (lock) {
			cacheGrafo = new HashMap<String, VerticeComparable>();
			cacheMelhorCaminho = new HashMap<String, List<VerticeComparable>>();			
		}
	}
}
