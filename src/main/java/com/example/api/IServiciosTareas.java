/**
 * 
 */
package com.example.api;

import java.util.List;

import com.example.entities.Tarea;


/**
 * @author XY6585IP
 *
 */
public interface IServiciosTareas {

	public List<Tarea> todosLasTareas();

	public Tarea verTarea(int id);

	public void addTarea(Tarea tarea);

	public void updateTarea(Tarea tarea);

	public void deleteTarea(int id);

	public void AsignarTarea(int codEmpleado, int codTarea);

	public int contarTareas();
	
}
