/**
 *
 */
package com.example.controlador;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.api.IServiciosEmple;
import com.example.api.IServiciosTareas;
import com.example.entities.Empleado;
import com.example.entities.Tarea;
import com.example.servicios.JsonEmple;
import com.google.gson.JsonArray;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "ControladorEmple", description = "Empleados!!!!")
@Path("/emple")
@Controller
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ControladorEmple {



	@Autowired(required = true)
	private IServiciosEmple emple;

	@Autowired(required = true)
	private IServiciosTareas tareas;

	private List<Empleado> empleados1;

	public ControladorEmple(IServiciosEmple empleados, IServiciosTareas tareas) {
		this.emple = empleados;
		this.tareas = tareas;
		empleados1 = new ArrayList<>();
		empleados1 = empleados.todosLosEmpleados();
	}

	@GET
	//@Produces("application/json")
	@ApiOperation(value = "Obteniendo todos los empleados", tags = "getAllUsers")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public Response getAllUsers() {
		JsonArray jsonArray = JsonEmple.getMethod(emple, tareas).all();
		return Response.ok(jsonArray.toString()).build();
	}

	@GET
	@Path("/buscar")
	//@Produces("application/json")
	@ApiOperation(value = "Obteniendo un solo empleado", response = Empleado.class, tags = "getUserById")
	public Response getUserById(@QueryParam("id") int id) throws URISyntaxException{

		Optional<Empleado> em = emple.verEmpleado(id);
		if(em.isPresent()) {
			if(em.get().getId() != id ) {
				return Response.status(404).entity("NOT FOUND").build();
			}else {
				JsonArray jsonArray =  JsonEmple.getMethod(emple, tareas).serch(id);
				return Response.ok(jsonArray.toString()).build();
			}
		}

		return Response.status(404).entity("NOT FOUND").build();
	}

	@POST
	@Path("/alta")
	//@Consumes("application/json")
	@ApiOperation(value = "Añadir empleado", response = Empleado.class, tags = "createUser")
	public Response createUser(@RequestBody() String empleado) throws URISyntaxException{

		Empleado e = JsonEmple.getMethod(emple, tareas).alta(empleado);
		emple.addEmpleado(e);

		return Response.status(201).entity(empleado).build();
	}

	@PUT
	@Path("/update")
	//@Consumes("application/json")
	//@Produces("application/json")
	@ApiOperation(value = "Añadir empleado", response = Empleado.class, tags = "updateEmpleado")
	public Response updateEmpleado(@QueryParam("id") int id, @RequestBody() String empleado) throws URISyntaxException{

		Empleado e = JsonEmple.getMethod(emple, tareas).update(empleado);
		e.setId(id);
		emple.updateEmpleado(e);

		return Response.status(200).entity(empleado).build();
	}

	@DELETE
	@Path("/delete")
	@ApiOperation(value = "Borrar empleado", response = Empleado.class, tags = "deleteEmpleado")
	public Response deleteEmpleado(@QueryParam("id") int id) throws URISyntaxException {

		for(Empleado e: empleados1) {
			if(e.getId() == id) {
				emple.deleteEmpleado(id);
				String s = String.format("Resource deleted successfully","File");
				return Response.status(204).entity(s).build();
			}
		}
		return Response.status(404).entity("NOF FOUND").build();
	}


	@PUT
	@Path("/asigna")
	//@Consumes("application/json")
	//@Produces("application/json")
	@ApiOperation(value = "Asignar tarea a un empleado", response = Empleado.class, tags = "assignWork")
	public Response assignWork(@QueryParam("ide") int ide, @QueryParam("idt") int idt) throws URISyntaxException{

		Empleado e = emple.verEmpleado(ide).get();
		Boolean existe = false;
		for(Tarea t: e.getTareas()) {
			if(t.getDescripcion().equals(tareas.verTarea(idt).getDescripcion())) {
				existe = true;
			}
		}

		if(!existe) {
			e.getTareas().add(tareas.verTarea(idt));
			emple.updateEmpleado(e);
			JsonArray jsonArray =  JsonEmple.getMethod(emple, tareas).serch(ide);
			return Response.status(200).entity(jsonArray.toString()).build();
		}

		return Response.status(400).entity(JsonEmple.getMethod(emple, tareas).serch(ide)).build();
	}

	@PUT
	@Path("/designar")
	//@Consumes("application/json")
	//@Produces("application/json")
	@ApiOperation(value = "Desasigna una tarea a un empleado", response = Empleado.class, tags = "disassignWork")
	public Response disassignWork(@QueryParam("ide") int ide,@QueryParam("idt") int idt) throws URISyntaxException{

		Empleado e = emple.verEmpleado(ide).get();
		if(e!=null) {
			Boolean existe = false;
			Tarea t1 = new Tarea();
			for(Tarea t: e.getTareas()) {
				if(t.getId() == tareas.verTarea(idt).getId()) {
					t1 = t;
					existe = true;
				}
			}
			if(Boolean.TRUE.equals(existe)) {
				e.getTareas().remove(t1);
				emple.updateEmpleado(e);
				JsonArray jsonArray =  JsonEmple.getMethod(emple, tareas).serch(ide);
				return Response.status(200).entity(jsonArray.toString()).build();
			}
		}
		return Response.status(400).entity(JsonEmple.getMethod(emple, tareas).serch(ide)).build();
	}
}
