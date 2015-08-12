package com.ciet.base.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 * 
* Vertice.java
* 
* <P>Vertice é cada nodo que compõe o gráfico. Para fim de simplificação da persistencia, ele é composto simplesmente por id e nome
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
@Entity
@SequenceGenerator(name = "default_gen", sequenceName = "vertice_seq", allocationSize = 1)
public class Vertice extends PersistentObject implements Serializable{
	
    private static final long serialVersionUID = 1L;
    
    @Column(unique = true)
    private String nome;
    
	public Vertice(){
	}
    
	public Vertice(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
