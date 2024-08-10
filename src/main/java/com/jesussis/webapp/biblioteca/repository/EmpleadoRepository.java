package com.jesussis.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesussis.webapp.biblioteca.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>  {

}
