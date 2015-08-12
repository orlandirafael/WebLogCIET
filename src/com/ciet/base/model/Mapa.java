package com.ciet.base.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 
* Mapa.java
* 
* <P>O mapa é a classe que organiza a malha em um conjunto. O sistema poderá ter várias malhas diferentes que podem ser cadastradas pelo usuário
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
@Entity
@SequenceGenerator(name = "default_gen", sequenceName = "mapa_seq", allocationSize = 1)
public class Mapa extends PersistentObject implements Serializable{
	
    private static final long serialVersionUID = 1L;

    public Mapa(){
    }
    
	public Mapa(String nome, Calendar data) {
		this.nome = nome;
		this.data = data;
	}

	@Column
	private String nome;
	
	@Cascade(CascadeType.MERGE)
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Aresta> malha;

	@Temporal(TemporalType.DATE)
	private Calendar data;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public List<Aresta> getMalha() {
		return malha;
	}

	public void setMalha(List<Aresta> malha) {
		this.malha = malha;
	}

}
