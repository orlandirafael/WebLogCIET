package com.ciet.base.util;

import java.text.DecimalFormat;

/**
 * 
* NumericUtil.java
* 
* <P>Classe utilitaria com metodos relacionados a operações com números
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public class NumericUtil {

	private static NumericUtil instance = new NumericUtil(); //Instancia única
	
	private NumericUtil() {
	}
	
	public static NumericUtil getInstance(){
		return instance;
	}
	
	/**
	 * Testa se uma string é relativa a um Integer
	 * @param valor String a ser testada
	 * @return
	 */
	public boolean isInteger(String valor){
		try{
		    Integer.valueOf(valor);
		    return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Testa se uma string é relativa a um Double
	 * @param valor String a ser testada
	 * @return
	 */
	public boolean isDouble(String valor){
		try{
		    Double.valueOf(valor);
		    return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Formata um double para duas casas decimais
	 * @param valor
	 * @return
	 */
	public String rounder(Double valor){
		DecimalFormat f = new DecimalFormat("#.##");
		return f.format(valor);
	}
}
