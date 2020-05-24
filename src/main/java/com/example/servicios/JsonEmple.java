package com.example.servicios;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.api.IServiciosEmple;
import com.example.api.IServiciosTareas;
import com.example.entities.Empleado;
import com.example.entities.Tarea;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author XY6585IP
 *
 */
public class JsonEmple {

	private static JsonEmple json;


	@Autowired(required = true)
	private IServiciosEmple emple;

	@Autowired(required = true)
	private IServiciosTareas tareas;

	public JsonEmple(IServiciosEmple empleados, IServiciosTareas tareas) {
		this.emple = empleados;
		this.tareas = tareas;
	}

	public JsonArray all() {
		JsonArray jsonArray =  new JsonArray();
		for(Empleado e:emple.todosLosEmpleados()) {
			JsonObject jsonEmpleado = new JsonObject();
			jsonEmpleado.addProperty("id", e.getId());
			jsonEmpleado.addProperty("nombre", e.getNombre());
			jsonEmpleado.addProperty("apellido1", e.getApellido1());
			jsonEmpleado.addProperty("apellido2", e.getApellido2());
			jsonEmpleado.addProperty("dni", e.getDni());
			jsonEmpleado.addProperty("alta", e.getAlta());
			jsonEmpleado.addProperty("baja", e.getBaja());
			
			
			JsonArray jsonArrayTareaEmple =  new JsonArray();
			for(Tarea t: e.getTareas()) {
				JsonObject TareaEmple = new JsonObject();
				TareaEmple.addProperty("id", t.getId());
				TareaEmple.addProperty("nombre", t.getNombre());
				TareaEmple.addProperty("descripcion", t.getDescripcion());
				TareaEmple.addProperty("inicio", t.getInicio());
				TareaEmple.addProperty("fin", t.getFin());
				jsonArrayTareaEmple.add(TareaEmple);
			}
			
			
			jsonEmpleado.add("Tarea", jsonArrayTareaEmple);
			jsonArray.add(jsonEmpleado);
		}
		return jsonArray;
	}

	public JsonArray serch(int id) {
		JsonArray jsonArray =  new JsonArray(); 
		Empleado em = emple.verEmpleado(id).get();
		JsonObject jsonEmple = EmpleadoJson(em);
		jsonEmple.add("Tarea",TareasEmpleado(em) );
		jsonArray.add(jsonEmple);
		return jsonArray;
	}
	
	public Empleado alta(String empleado) {
		JsonObject json = (JsonObject) new JsonParser().parse(empleado);
		return StringJsonEmpleado(json);
	}
	
	public Empleado update(String empleado) {
		JsonObject json = (JsonObject) new JsonParser().parse(empleado);
		return StringJsonEmpleado(json);
	}
	
	
	public Empleado StringJsonEmpleado(JsonObject json) {
		Empleado e = new Empleado(
				json.get("nombre").getAsString(), 
				json.get("apellido1").getAsString(), 
				json.get("apellido2").getAsString(), 
				json.get("dni").getAsString(), 
				json.get("alta").getAsString(), 
				json.get("baja").getAsString());
		return e;
	}
	
	public JsonObject EmpleadoJson(Empleado em) {
		JsonObject jsonEmpleado = new JsonObject();
		jsonEmpleado.addProperty("id", em.getId());
		jsonEmpleado.addProperty("nombre", em.getNombre());
		jsonEmpleado.addProperty("apellido1", em.getApellido1());
		jsonEmpleado.addProperty("apellido2", em.getApellido2());
		jsonEmpleado.addProperty("dni", em.getDni());
		jsonEmpleado.addProperty("alta", em.getAlta());
		jsonEmpleado.addProperty("baja", em.getBaja());
		return jsonEmpleado;	
	}
	
	public JsonArray TareasEmpleado(Empleado em) {
		JsonArray jsonArrayTareaEmple =  new JsonArray();
		for(Tarea t: em.getTareas()) {
			JsonObject TareaEmple = new JsonObject();
			TareaEmple.addProperty("id", t.getId());
			TareaEmple.addProperty("nombre", t.getNombre());
			TareaEmple.addProperty("descripcion", t.getDescripcion());
			TareaEmple.addProperty("inicio", t.getInicio());
			TareaEmple.addProperty("fin", t.getFin());
			jsonArrayTareaEmple.add(TareaEmple);
		}
		return jsonArrayTareaEmple;
	}

	
	public static JsonEmple getMethod(IServiciosEmple empleados, IServiciosTareas tareas) {
		if(json == null) {
			json = new JsonEmple(empleados, tareas);
		}
		return json;
	}
}
