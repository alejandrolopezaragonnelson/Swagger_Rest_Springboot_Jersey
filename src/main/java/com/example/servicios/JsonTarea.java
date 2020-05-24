package com.example.servicios;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.api.IServiciosTareas;
import com.example.entities.Tarea;
import com.example.main.GestionfinalApplication;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author XY6585IP
 *
 */
public class JsonTarea {

	private static JsonTarea json;


	@Autowired(required = true)
	private IServiciosTareas tareas;
	private List<Tarea> ta;

	public JsonTarea(IServiciosTareas tareas) {
		this.tareas = tareas;
		ta = new ArrayList<>();
		ta = tareas.todosLasTareas();
	}

	public JsonArray all() {

		JsonArray jsonArray =  new JsonArray();
		for(Tarea tarea: ta) {
			JsonObject jsonTarea = new JsonObject();
			jsonTarea.addProperty("id", tarea.getId());
			jsonTarea.addProperty("nombre", tarea.getNombre());
			jsonTarea.addProperty("descipcion", tarea.getDescripcion());
			jsonTarea.addProperty("inicio", tarea.getInicio());
			jsonTarea.addProperty("fin", tarea.getFin());
			jsonArray.add(jsonTarea);
		}

		return jsonArray;
	}

	public JsonObject serch(int id) {
		return tareaJson(id);
	}

	public Tarea alta(String tarea) {
		JsonObject json = (JsonObject) new JsonParser().parse(tarea);
		return jsonTarea(json);
	}

	
	public JsonObject tareaJson(int id) {
		Tarea tar = tareas.verTarea(id);
		JsonObject jsonTarea = new JsonObject();
		jsonTarea.addProperty("id",tar.getId());
		jsonTarea.addProperty("nombre",tar.getNombre());
		jsonTarea.addProperty("descripcion",tar.getDescripcion());
		jsonTarea.addProperty("inicio",tar.getInicio());
		jsonTarea.addProperty("fin",tar.getFin());
		return jsonTarea;
	}
	
	public Tarea jsonTarea(JsonObject json) {
		Tarea e = new Tarea(
				json.get("nombre").getAsString(), 
				json.get("descripcion").getAsString(), 
				json.get("inicio").getAsString(), 
				json.get("fin").getAsString());
		return e;
	}
	
	public Tarea update(String tarea) {
		
		JsonObject json = (JsonObject) new JsonParser().parse(tarea);
		Tarea e = new Tarea(
				json.get("nombre").getAsString(), 
				json.get("descripcion").getAsString(), 
				json.get("inicio").getAsString(), 
				json.get("fin").getAsString());
		
		return e;
	}
	
	
	

	public static JsonTarea getMethod(IServiciosTareas tareas) {
		if(json == null) {
			json = new JsonTarea(tareas);
		}
		return json;
	}
}
