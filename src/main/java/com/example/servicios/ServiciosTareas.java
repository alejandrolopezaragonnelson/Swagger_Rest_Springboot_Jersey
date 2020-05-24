/**
 * 
 */
package com.example.servicios;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.api.*;
import com.example.entities.Tarea;
import com.example.main.GestionfinalApplication;
import com.example.repositorios.TareaRepo;

/**
 * @author XY6585IP
 *
 */
@Component
public class ServiciosTareas implements IServiciosTareas{

	private Logger salida = LoggerFactory.getLogger(GestionfinalApplication.class);

	@Autowired(required = true)
	private TareaRepo tareaRepo;

	public ServiciosTareas(TareaRepo tarea_repo) {
		this.tareaRepo = tarea_repo;
	}


	//-------------------- inicio tareas---------------------//
	public List<Tarea> todosLasTareas() { 
		return tareaRepo.findAll();
	}
	
	@Override
	public Tarea verTarea(int id) {
		Optional<Tarea> tarea;
		tarea = tareaRepo.findById(id);
		return tarea.orElse(null);
	}

	public void addTarea(Tarea tarea) {
		tareaRepo.saveAndFlush(tarea);
	}

	public void updateTarea(Tarea tarea) {
		tareaRepo.saveAndFlush(tarea);
	}

	public void deleteTarea(int id) {
		tareaRepo.deleteById(id);
	}
	
	

	public void AsignarTarea(int codEmpleado, int codTarea) {
		// db
	}


	@Override
	public int contarTareas() {
		int n = (int) tareaRepo.count();
		return n;
	}




}
