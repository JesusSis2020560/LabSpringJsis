package com.jesussis.webapp.biblioteca.controller;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jesussis.webapp.biblioteca.model.Libro;
import com.jesussis.webapp.biblioteca.Service.LibroService;

@Controller
@RestController
@RequestMapping("")
public class LibroController {


    @Autowired
    LibroService LibroService;
@GetMapping("/libros")
    public ResponseEntity<List<Libro>> listarLibros(){
        try {
            return ResponseEntity.ok(LibroService.listarLibros());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        } 
    } 

    @GetMapping()
    public ResponseEntity<Libro> buscarLibro(@RequestParam Long id){
        try{
            return ResponseEntity.ok(LibroService.buscarLibroPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping("/libro")
    public ResponseEntity<Map<String,String>> agregarLibro(@RequestBody Libro libro){
        Map<String,String> response = new HashMap<>();
        try {
            LibroService.guardarLibro(libro);
            response.put("message", "Libro encontrado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("message", "Hubo un error al crear el libro");
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PutMapping("/libro")
    public ResponseEntity<Map<String, String>> editarLibro(@RequestParam Long id, @RequestBody Libro libroNuevo){
        Map<String, String> response = new HashMap<>();
        try {
            Libro libroViejo = LibroService.buscarLibroPorId(id);
            libroViejo.setAutor(libroNuevo.getAutor());
            libroViejo.setCategoria(libroNuevo.getCategoria());
            libroViejo.setCluster(libroNuevo.getCluster());
            libroViejo.setDisponibilidad(libroNuevo.getDisponibilidad());
            libroViejo.setEditorial(libroNuevo.getEditorial());
            libroViejo.setIsbn(libroNuevo.getIsbn());
            libroViejo.setNombre(libroNuevo.getNombre());
            libroViejo.setNumeroEstanteria(libroNuevo.getNumeroEstanteria());
            libroViejo.setSinopsis(libroNuevo.getSinopsis());
            LibroService.guardarLibro(libroViejo);
            response.put("message", "Modificado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "GG Error");
            response.put("err", "no se puede editar ");
            return ResponseEntity.badRequest().body(response);
        }
    }

        @DeleteMapping("/libro")
    public ResponseEntity<Map<String, String>> eliminarLibro(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Libro libroViejo = LibroService.buscarLibroPorId(id);
            LibroService.eliminarLibro(libroViejo);
            response.put("message", "borrado :3");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "GG Error");
            response.put("err", "no se pudo borrar :c");
            return ResponseEntity.badRequest().body(response);
        }
    }
}

