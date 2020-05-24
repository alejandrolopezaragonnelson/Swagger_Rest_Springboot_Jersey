package com.example.servicios;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.api.IServiciosEmple;
import com.example.entities.Empleado;
import com.example.main.GestionfinalApplication;
import com.example.repositorios.EmpleadoRepo;


@Component
public class ServiciosEmple implements IServiciosEmple {
	
	private Logger salida = LoggerFactory.getLogger(GestionfinalApplication.class);
	
	@Autowired(required = true)
	private EmpleadoRepo empleadoRepo;

	public ServiciosEmple(EmpleadoRepo empl_repo) {
		this.empleadoRepo = empl_repo;
	}
	
	
	public List<Empleado> todosLosEmpleados() {
		return empleadoRepo.findAll();
	}
	
	public void deleteEmpleado(int id) {
		empleadoRepo.deleteById(id);
	}


	@Override
	public Optional<Empleado> verEmpleado(int id) {
		Optional<Empleado> empleado;
		empleado = empleadoRepo.findById(id);
		if(empleado.isPresent()) {
			return empleado;
		}
		return Optional.empty();
	}


	@Override
	public void addEmpleado(Empleado empleado) {
		empleadoRepo.saveAndFlush(empleado);
	}


	@Override
	public void updateEmpleado(Empleado empleado) {
		empleadoRepo.save(empleado);
	}
	
}
