package com.ciet.base.bo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ciet.base.dao.MapaDAO;
import com.ciet.base.dao.MapaDAOImpl;
import com.ciet.base.model.Aresta;
import com.ciet.base.model.Mapa;
import com.ciet.base.model.Vertice;
import com.ciet.base.util.CachedMap;
import com.ciet.base.util.NumericUtil;

/**
 * 
* MapaBOImpl.java
* 
* <P>Implementa��o da camada BO aplicando as regras de negocio para a entidade Mapa
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public class MapaBOImpl implements MapaBO{
	private MapaDAO mapaDAO = new MapaDAOImpl();
	private VerticeBO verticeBO = new VerticeBOImpl();
	private ArestaBO arestaBO = new ArestaBOImpl();
	private Object lock = new Object();//Objecto utilitario usado na sincroniza��o de metodos criticos em situa��es mult-thread 
	
	/**
	 * Regras de negocio referentes � persistencia de um mapa
	 * @param nomeMapa
	 * @param malha Recebe uma malha que � relativa a um set de Arestas
	 * @return
	 * @throws Exception
	 */
	@Override
	public Mapa gravarMapa(String nomeMapa, List<String> malha) throws Exception{
		Mapa mapa = getMapa(nomeMapa);//Verifica se o mapa j� existe
		
		List<Aresta> listaMalha = new ArrayList<Aresta>();
		//Se o mapa n�o existir uma nova instancia � criada
		if(mapa == null){
			mapa = new Mapa(nomeMapa, Calendar.getInstance());
		}
		
		synchronized (lock) {
			//Para cada aresta da malha, o metodo ir� montar as dependencias do objeto
			for(String arestaStr : malha){
				String[] dados = arestaStr.split(" ");//Tentar parsear as strings passadas pelo usuario
				if(dados.length==3){
					//N�o � poss�vel cadastrar vertices com nomes iguais
					if(dados[0].equals(dados[1])){
						throw new Exception("Os vertices devem ter nomes diferentes para configurar uma aresta");
					}
					//Verifica se a distancia � um dado n�merico e positivo
					if(NumericUtil.getInstance().isInteger(dados[2])){
						Integer distancia = Integer.valueOf(dados[2]);
						if(distancia <= 0){
							throw new Exception("A distancia deve ser maior que 0");
						}
						
						//Caso o vertice j� exista, o metodo gravar vai retornar o objeto persistido com seu id
						Vertice vert1 = verticeBO.gravarVertice(dados[0]);
						Vertice vert2 = verticeBO.gravarVertice(dados[1]);	
						//Caso aresta j� exista, o metodo gravar vai retornar o objeto persistido com seu id
						Aresta aresta = arestaBO.gravarAresta(new Aresta(vert1, vert2, distancia));
						listaMalha.add(aresta);
					}else{
						throw new Exception("Distancia deve ser numerica e inteira");
					}
				}else{
					throw new Exception("Dados da malha est�o em formato incompativel");
				}
			}
		}
		if(listaMalha.size()>0){
			mapa.setMalha(listaMalha);//Associa a malha ao mapa
		}

		//Grava o mapa no banco
		Mapa m = mapaDAO.gravarMapa(mapa);
		CachedMap.getInstance().resetCache(); //Reseta os caches em virtude da nova malha cadastrada
		return m;
	}
	
	/**
	 * Regras de negocio referentes � busca de um mapa no banco de dados pelo seu nome
	 * @param nomeMapa
	 * @return
	 */
	@Override
	public Mapa getMapa(String nome) {
		return mapaDAO.getMapa(nome);
	}

	/**
	 * Regras de negocio referentes � busca de um mapa cadastrado mais recente no banco
	 * @return
	 */
	@Override
	public Mapa getMapaMaisRecente() {
		return mapaDAO.getMapaMaisRecente();
	}

	/**
	 * Regras de negocio referentes � remo��o de um mapa do banco pelo nome
	 */
	@Override
	public void removerMapa(String nomeMapa) {
		mapaDAO.removerMapa(nomeMapa);
	}
	
}
