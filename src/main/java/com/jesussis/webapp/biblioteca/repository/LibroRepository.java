package com.jesussis.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesussis.webapp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
