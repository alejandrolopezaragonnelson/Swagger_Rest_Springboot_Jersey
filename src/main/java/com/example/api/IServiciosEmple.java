package com.example.api;

import java.util.List;
import java.util.Optional;

import com.example.entities.Empleado;


//@Service
public interface IServiciosEmple {
	
	public List<Empleado> todosLosEmpleados();
	
	public Optional<Empleado> verEmpleado(int id);
	
	public void addEmpleado(Empleado empleado);
	
	public void updateEmpleado(Empleado empleado);
	
	public void deleteEmpleado(int id);
}
