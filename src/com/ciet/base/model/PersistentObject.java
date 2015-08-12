package com.ciet.base.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
* PersistentObject.java
* 
* <P>Objeto base da persistencia para evitar escrita repetida dos atributos em comum
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
@MappedSuperclass
public abstract class PersistentObject implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "default_gen")
    protected Long id;

    public Long getId(){
        return id;
    }

    public void setId(Long id){ 
        this.id = id;
    }
}
