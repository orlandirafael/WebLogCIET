package com.ciet.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.ciet.base.bo.MapaBO;
import com.ciet.base.bo.MapaBOImpl;
import com.ciet.base.util.CachedMap;
import com.ciet.base.util.VerticeComparable;
/**
 * 
 * TestBasic.java
 * 
 * <P>Classe de testes básicos do sistema
 *  
 * @author Rafael Mandacaru Orlandi
 * @version 1.0
 */
public class TestBasic extends TestCase  {
	private MapaBO mapaBO = new MapaBOImpl();

	public void testIncluiBaseControlada() {
		List<String> malha = new ArrayList<String>();
		malha.add("A B 10");
		malha.add("B D 15");
		malha.add("A C 20");
		malha.add("C D 30");
		malha.add("B E 50");
		malha.add("D E 30");
		System.out.println("Iniciando o cadastramento de mapa controlado");
		
		try {
			mapaBO.gravarMapa("mapaControlado", malha);
			List<VerticeComparable> result = CachedMap.getInstance().getMelhorCaminho("A", "D");
			assertEquals("25", result.get(result.size()-1).getDistancia()+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void testFalhaCadastramentoDeMapas() {
		try {
			System.out.println("Iniciando o cadastramento de mapas");
			List<String> malha = new ArrayList<String>();
			for(int i=0; i< 200; i++){
				malha.add("nodo"+i+"   "+"nodo"+(i+1)+"    "+0);
			}
			mapaBO.gravarMapa("mapa_teste", malha);
		} catch (Exception e) {
			System.out.println("Erro esperado no cadastro de dados incompatíveis");
		}

	}

	public void testRemove(){
		System.out.println("Removendo a base cadastrada");
		mapaBO.removerMapa("mapa_para_remover");
	}
	
	public void testCadastramentoDeMapaParaRemover() {
		try {
			System.out.println("Iniciando o cadastramento de mapa a ser removido");
			List<String> malha = new ArrayList<String>();
			for(int i=0; i< 200; i++){
				malha.add("x"+i+" "+"x"+(i+1)+" "+(Math.round(Math.random()*100)+1));
			}
			mapaBO.gravarMapa("mapa_para_remover", malha);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
