package com.ciet.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.ciet.base.bo.MapaBO;
import com.ciet.base.bo.MapaBOImpl;
import com.ciet.base.model.Mapa;
import com.ciet.base.util.CachedMap;
import com.ciet.base.util.NumericUtil;
import com.ciet.base.util.VerticeComparable;

/**
 * 
* MapaWS.java
* 
* <P>Classe que engloba todos os web-services disponibilizados pelo sistema
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
@Path("/")
public class MapaWS{

	private MapaBO mapaBO = new MapaBOImpl(); 
	
	/**
	 * Web-service JAX-RS para cadastrar uma malha de dados. Se já existir um mapa com o mesmo nome cadastrado, o sistema irá alterar para o mapa para a
	 * relação nova que foi passada 
	 * @param nomeMapa Nome do mapa que será cadastrado
	 * @param malha Malha em formato List<String> onde cada registro consiste em uma string (VerticeA VerticeB Distancia) concatenados por um espaço simples 
	 * @return retorno informativo da malha cadastrada
	 */
	@GET
	@Path("/gravarMapa")
	@Produces("text/plain")
	public String gravarMapa(@QueryParam(value = "nome") String nomeMapa, @QueryParam(value = "item") List<String> malha) {
		try {
			Mapa mapa = mapaBO.gravarMapa(nomeMapa, malha); //Grava o mapa e recebe o objeto persistido como resposta
			return mapa.getNome()+" recebeu os valores " + malha;
		} catch (Exception e) {
			return e.getMessage();
		}
	}


	/**
	 * Metodo para remoção de um determinado mapa pelo seu nome. 
	 * A execução desse comando excluirá todos as arestas e vertices relacionados a ele.
	 * @param nomeMapa
	 */
	@GET
	@Path("/removerMapa")
	@Produces("text/plain")
	public void removerMapa(@QueryParam(value = "nome") String nomeMapa) {
		try {
			mapaBO.removerMapa(nomeMapa); //Remove um mapa pelo nome
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Web-service JAX-RS para buscar o melhor caminho na malha previamente cadastrada
	 * @param origem nodo inicial de onde o veiculo deverá partir
	 * @param destino nodo de chegada onde o veiculo irá chegar
	 * @param autonomia rendimento do veículo na estrada. Medida em (km/l)
	 * @param valorLitro valor do combustível a ser utilizado em (R$/l)
	 * @return Devolve uma string no formato 'O melhor caminho passa pela rota: (A-z) com distancia de X km e custo Y' 
	 */
	@GET
	@Path("/buscarMelhorCaminho")
	@Produces("text/plain")
	public String buscarMelhorCaminho(@QueryParam(value = "origem") String origem, @QueryParam(value = "destino") String destino, @QueryParam(value = "autonomia") String autonomia, @QueryParam(value = "valorLitro") String valorLitro) {
		try {
			if(!NumericUtil.getInstance().isInteger(autonomia)){
				return "Valor incorreto para o parametro autonomia"; 
			}
			if(!NumericUtil.getInstance().isDouble(valorLitro)){
				return "Valor incorreto para o parametro valor do litro"; 
			}
			if(Integer.valueOf(autonomia) <= 0){
				return "Autonomia deve ser maior que zero"; 
			}
			if(Double.valueOf(valorLitro) <= 0.0){
				return "Valor do litro de combustivel deve ser maior que zero"; 
			}
			
			//Acessa a classe de cache para buscar o melhor caminho. Se não existir a informação a própria classe se encarrega de buscar
			List<VerticeComparable> melhorCaminho = CachedMap.getInstance().getMelhorCaminho(origem, destino);

			//Monta a string de resposta para a requisição, exibindo o melhor caminho a ser percorrido e o valor a ser pago em combustível
			String retorno = "O melhor caminho passa pela rota: ";
			for(VerticeComparable v : melhorCaminho){
				retorno += v.getNome() + " ";
			}
			Integer distanciaTotal = melhorCaminho.get(melhorCaminho.size()-1).getDistancia(); //Distancia total que deverá ser percorrida
			Integer autonomiaInt = Integer.valueOf(autonomia); //Autonomia do veiculo convertida para Integer
			double valorDouble = Double.valueOf(valorLitro); //Valor do combustível convertido para Double
			String custo = NumericUtil.getInstance().rounder(((distanciaTotal.doubleValue()/autonomiaInt.doubleValue())*valorDouble)); //Custo do combustivel a ser utilizado no trajeto
			
			retorno += " com distancia de " + melhorCaminho.get(melhorCaminho.size()-1).getDistancia() + "km e custo "+custo;
			
			return retorno;
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}