package com.example.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author XY6585IP
 *
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "Empleado")
@Entity
@Table(name = "empleados")
@ApiModel
public class Empleado implements Serializable{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id del esempleadostudiante", name="id", required=true, value="test id")
	private int id;
	
	@Column(name = "nombre", length = 100)
	@ApiModelProperty(notes = "Nombre del empleado", name="nombre", required=true, value="test nombre")
	private String nombre;
	
	@ApiModelProperty(notes = "Primer apellido del empleado", name="apellido1", required=true, value="test apellido1")
	@Column(name = "apellido1", length = 100) 
	private String apellido1;
	
	@ApiModelProperty(notes = "Segundo apellido del empleado", name="apellido2", required=true, value="test apellido2")
	@Column(name = "apellido2", length = 100)
	private String apellido2;
	
	@ApiModelProperty(notes = "DNI del empleado", name="dni", required=true, value="test dni")
	@Column(name = "dni", length = 9)
	private String dni;
	
	@ApiModelProperty(notes = "Alta del empleado", name="alta", required=true, value="test alta")
	@Column(name = "alta", length = 6)
	private String alta;
	
	@ApiModelProperty(notes = "Baja del empleado", name="baja", required=true, value="test baja")
	@Column(name = "baja", length = 6)
	private String baja;
	
	@JoinColumn(name = "id_Empleado", unique = true)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name ="empleado_tareas"
				,joinColumns = @JoinColumn(name="id_Empleado")
				,inverseJoinColumns = @JoinColumn(name =" id_tarea"))
	private List<Tarea> tareas = new ArrayList<Tarea>();

	public Empleado() {
		super();
	}
	
	public Empleado(String nombre, String apellido1, String apellido2, String dni, String alta, String baja) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.alta = alta;
		this.baja = baja;
	}
	
	public Empleado(int id, String nombre, String apellido1, String apellido2, String dni, String alta, String baja
			) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.alta = alta;
		this.baja = baja;
	}
	public Empleado(int id, String nombre, String apellido1, String apellido2, String dni, String alta, String baja,
			List<Tarea> tareas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.alta = alta;
		this.baja = baja;
		this.tareas = tareas;
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

	
	public String getApellido1() {
		return apellido1;
	}

	
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	
	public String getApellido2() {
		return apellido2;
	}

	
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}


	public String getDni() {
		return dni;
	}

	
	public void setDni(String dni) {
		this.dni = dni;
	}

	
	public String getAlta() {
		return alta;
	}

	public void setAlta(String alta) {
		this.alta = alta;
	}


	public String getBaja() {
		return baja;
	}

	
	public void setBaja(String baja) {
		this.baja = baja;
	}

	
	public List<Tarea> getTareas() {
		return tareas;
	}

	
	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", dni=" + dni + ", alta=" + alta + ", baja=" + baja + ", tareas=" + tareas + "]";
	}
	
	

}
