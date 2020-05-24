package com.example.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import io.swagger.annotations.ApiModel;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "Tarea")
@Entity
@Table(name = "tareas") 
@ApiModel
public class Tarea implements Serializable{

	@Override
	public String toString() {
		return "Tarea [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", inicio=" + inicio
				+ ", fin=" + fin + "]";
	}


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	
	private int id;
	
	@Column(name = "nombre", length = 100)
	private String nombre;
	
	@Column(name = "descripcion", length = 200)
	private String descripcion;
	
	@Column(name = "inicio", length = 10)
	private String inicio;
	
	@Column(name = "fin", length = 10)
	private String fin;
	
	
	public Tarea() {
		super();
	}

	public Tarea(String nombre, String descripcion, String inicio, String fin) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.inicio = inicio;
		this.fin = fin;
	}

	public Tarea(int id, String nombre, String descripcion, String inicio, String fin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.inicio = inicio;
		this.fin = fin;
	}

	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public String getNombre() {
		return nombre;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public String getInicio() {
		return inicio;
	}

	
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	
	public String getFin() {
		return fin;
	}

	
	public void setFin(String fin) {
		this.fin = fin;
	}

}
