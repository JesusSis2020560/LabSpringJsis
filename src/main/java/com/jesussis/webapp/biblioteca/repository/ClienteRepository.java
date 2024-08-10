package com.jesussis.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesussis.webapp.biblioteca.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
