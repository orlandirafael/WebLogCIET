package com.ciet.base.util.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.ciet.base.bo.MapaBO;
import com.ciet.base.bo.MapaBOImpl;
import com.ciet.base.model.Aresta;
import com.ciet.base.model.Mapa;
import com.ciet.base.model.Vertice;
import com.ciet.base.util.MelhorCaminho;
import com.ciet.base.util.VerticeComparable;

/**
 * 
* MelhorCaminhoImpl.java
* 
* <P>Implementação da interface MelhorCaminho.java
* Essa classe tem a função de executar o algoritmo de Dijkstra na malha em questão
* e prepara o grafo para encontrar o melhor caminho
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public class MelhorCaminhoImpl implements MelhorCaminho{

	private MapaBO mapaBO = new MapaBOImpl();

	//Metodo responsável por controlar todo o processo de montagem do grafo. Desde a busca no banco, formatação e processamento dos dados 
	@Override
	public Map<String, VerticeComparable> montaGrafoPonderado(String origem) throws Exception{
		Mapa mapa = mapaBO.getMapaMaisRecente(); //Busca a malha persistida no banco de dados
		if(mapa == null){
			throw new Exception("Nenhum mapa criado ainda");
		}
		if(mapa.getMalha().size() == 0){
			throw new Exception("Nenhuma malha criada ainda");
		}
		List<Aresta> malha = mapa.getMalha();
		Map<String, VerticeComparable> grafo = new HashMap<String, VerticeComparable>(malha.size()); //Instancia o grafo a ser configurado

		//Adiciona todos os vertices ao grafo
		for(Aresta aresta : malha){
			grafo.put(aresta.getVert1().getNome(), new VerticeComparable(aresta.getVert1().getNome()));
			grafo.put(aresta.getVert2().getNome(), new VerticeComparable(aresta.getVert2().getNome()));
		}

		//Testa se a origem informada existe na malha
		if (!grafo.containsKey(origem)) {
			throw new Exception("Origem informada inexistente na malha");
		}
		
		//Cadastra os vizinhos de cada vertice com suas distancias
		for (Aresta aresta : malha) {
			Vertice vertice1 = aresta.getVert1();
			Vertice vertice2 = aresta.getVert2();
			grafo.get(vertice1.getNome()).getVizinhos().put(grafo.get(vertice2.getNome()), aresta.getDistancia());
			grafo.get(vertice2.getNome()).getVizinhos().put(grafo.get(vertice1.getNome()), aresta.getDistancia());
		}

		//Prepara todos os vertices com os valores iniciais para o processamente do grafo  
		NavigableSet<VerticeComparable> arvore = inicializacao(grafo, origem);
		
		//Executa o algoritmo de dijkstra para encontrar o melhor caminho
		dijkstra(arvore);
		
		return grafo;
	}

	/**
	 * Metodo de inicialização dos vertices do grafo
	 * @param grafo Grafo previamente cadastrado no banco
	 * @param origem Vertice de partida
	 * @return Arvore para execução do algoritmo de dijkstra
	 */
	public NavigableSet<VerticeComparable> inicializacao(Map<String, VerticeComparable> grafo, String origem) {

		final VerticeComparable verticeInicial = grafo.get(origem);
		NavigableSet<VerticeComparable> arvore = new TreeSet<>();

		for (VerticeComparable vert2 : grafo.values()) {
			vert2.setAnterior(vert2 == verticeInicial ? verticeInicial : null);
			vert2.setDistancia(vert2 == verticeInicial ? 0 : Integer.MAX_VALUE);
			arvore.add(vert2);
		}

		return arvore;
	}

	/**
	 * Algoritmo dijkstra para encontrar o melhor caminho no mapa
	 * @param arvore
	 */
	private void dijkstra(final NavigableSet<VerticeComparable> arvore) {      
		VerticeComparable vert1;
		VerticeComparable vert2;

		while (!arvore.isEmpty()) {

			vert1 = arvore.pollFirst(); 
			if (vert1.getDistancia() == Integer.MAX_VALUE){ 
				break; 
			}

			for (Map.Entry<VerticeComparable, Integer> a : vert1.getVizinhos().entrySet()) {
				vert2 = a.getKey(); 

				int distanciaAcumulada = vert1.getDistancia() + a.getValue();
				if (distanciaAcumulada < vert2.getDistancia()) {
					arvore.remove(vert2);
					vert2.setDistancia(distanciaAcumulada);
					vert2.setAnterior(vert1);
					arvore.add(vert2);
				} 
			}
		}
	}
}