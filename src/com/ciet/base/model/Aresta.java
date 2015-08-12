package com.ciet.base.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
* Aresta.java
* 
* <P>Classe que representa uma aresta no sistema.
* A aresta é constituida por dois vertices com uma distancia entre eles
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
@Entity
@SequenceGenerator(name = "default_gen", sequenceName = "aresta_seq", allocationSize = 1)
@Table(name="aresta", uniqueConstraints = @UniqueConstraint(columnNames = {"vert1_id", "vert2_id"}))
public class Aresta extends PersistentObject implements Serializable{
	
    private static final long serialVersionUID = 1L;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="vert1_id")
	private Vertice vert1;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="vert2_id")
	private Vertice vert2;

	@Column
	private Integer distancia; //Distancia entre os dois vertices
	
	public Aresta(){
	}
	
	public Aresta(Vertice vert1, Vertice vert2, Integer distancia){
		this.vert1 = vert1;
		this.vert2 = vert2;
		this.distancia = distancia;
	}

	public Vertice getVert1() {
		return vert1;
	}

	public void setVert1(Vertice vert1) {
		this.vert1 = vert1;
	}

	public Vertice getVert2() {
		return vert2;
	}


	public void setVert2(Vertice vert2) {
		this.vert2 = vert2;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

}
