package com.example.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Empleado;


@Repository
public interface EmpleadoRepo extends JpaRepository<Empleado, Integer>{
	
}
   