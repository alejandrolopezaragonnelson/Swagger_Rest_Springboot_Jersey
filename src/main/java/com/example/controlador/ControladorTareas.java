/**
 * 
 */
package com.example.controlador;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.api.IServiciosTareas;
import com.example.entities.Tarea;
import com.example.servicios.JsonTarea;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author XY6585IP
 *
 */
@Api(value = "Controlador Tareas", description = "Las tareas que serán asignadas a los empleados!!!!")
@Path("/tareas")
@Controller
public class ControladorTareas { 

	
	@Autowired(required = true)
	private IServiciosTareas tareas;
	private List<Tarea> ta;

	public ControladorTareas(IServiciosTareas tareas) {
		this.tareas = tareas;
		ta = new ArrayList<>();
		ta = tareas.todosLasTareas();
	}

	@GET
	@Produces("application/json")
	@ApiOperation(value = "Obteniendo todos las tareas", tags = "getAllTareas")
	public Response getAllTareas() {
		JsonArray jsonArray =  JsonTarea.getMethod(tareas).all();
		return Response.ok(jsonArray.toString()).build();
	} 

	@GET
	@Path("/buscar")
	@Produces("application/json")
	@ApiOperation(value = "Obteniendo todas las tareas", response = Tarea.class, tags = "getTareaById")
	public Response getTareaById(@QueryParam("id") int id) throws URISyntaxException{

		JsonObject jsonTarea = JsonTarea.getMethod(tareas).serch(id);
		Tarea tar = tareas.verTarea(id);
		if(tar.getId() != id) {
			return Response.status(404).entity("NOT FOUND").build();
		}else {

		}  
		return Response.ok(jsonTarea.toString()).build();
	}

	@POST
	@Path("/alta")
	@Consumes("application/json")
	@ApiOperation(value = "ALta tarea", response = Tarea.class, tags = "createTarea")
	public Response createTarea(@RequestBody() String tarea) throws URISyntaxException{
		Tarea t = JsonTarea.getMethod(tareas).alta(tarea);
		tareas.addTarea(t);
		return Response.status(201).entity(tarea).build();	

	} 
 
	@PUT 
	@Path("/update")  
	@Consumes("application/json")
	@Produces("application/json")
	@ApiOperation(value = "Modifica tarea", response = Tarea.class, tags = "updateTarea")
	public Response updateTarea(@QueryParam("id") int id, @RequestBody() String tarea) throws URISyntaxException{
		
		Tarea e = JsonTarea.getMethod(tareas).update(tarea);
		e.setId(id);
		tareas.updateTarea(e);
		JsonArray jsonArray =  JsonTarea.getMethod(tareas).all();
		
		return Response.status(200).entity(jsonArray.toString()).build();
	}

	@DELETE
	@Path("/delete")
	@ApiOperation(value = "Borra tarea", response = Tarea.class, tags = "deleteTarea")
	public Response deleteTarea(@QueryParam("id") int id) throws URISyntaxException {
		
		for(Tarea t: ta) {
			if(t.getId() == id) {
				tareas.deleteTarea(id);
				JsonArray jsonArray =  JsonTarea.getMethod(tareas).all();
				return Response.ok(jsonArray.toString()).build();
			}
		}
		return Response.status(404).entity("NOF FOUND").build();
		
	}

}
