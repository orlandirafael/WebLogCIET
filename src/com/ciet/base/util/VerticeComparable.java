package com.ciet.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
* VerticeComparable.java
* 
* <P>Objeto utilitario para auxiliar no calculo e geração do grafo processado
* Essa classe implementa Comparable para auxiliar na varredura da malha e encontrar o 
* melhor caminho
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public class VerticeComparable implements Comparable<VerticeComparable>{
	private String nome;
	private VerticeComparable anterior; //Vertice que antece o atual em direção ao destino definido
	private Integer distancia; //Distancia ponderada que é calculada durante o processo de busca do melhor caminho
	private Map<VerticeComparable, Integer> vizinhos = new HashMap<VerticeComparable, Integer>(); //Guarda todos os vertices que possuem conexão com o atual 

	public VerticeComparable(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public VerticeComparable getAnterior() {
		return anterior;
	}

	public void setAnterior(VerticeComparable anterior) {
		this.anterior = anterior;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public Map<VerticeComparable, Integer> getVizinhos() {
		return vizinhos;
	}

	public void setVizinhos(Map<VerticeComparable, Integer> vizinhos) {
		this.vizinhos = vizinhos;
	}

	//Monta a lista com o melhor caminho a ser percorrido de forma recursiva 
	public List<VerticeComparable> getMelhorCaminho() {
		List<VerticeComparable> retorno = new ArrayList<>();
		if (this.anterior != null && this != this.anterior) {
			retorno.addAll(this.anterior.getMelhorCaminho());
		}
		retorno.add(this);
		return retorno;
	}

	//Metodo implementado para comparação
	public int compareTo(VerticeComparable verticeComparacao) {
		return Integer.compare(distancia, verticeComparacao.getDistancia());
	}
}
