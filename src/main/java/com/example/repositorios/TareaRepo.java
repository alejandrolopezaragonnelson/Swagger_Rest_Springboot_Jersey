package com.example.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Tarea;

@Repository
public interface TareaRepo extends JpaRepository<Tarea, Integer>{

}
