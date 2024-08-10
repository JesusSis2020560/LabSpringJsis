package com.jesussis.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.jesussis.webapp.biblioteca.Service.CategoriaService;
import com.jesussis.webapp.biblioteca.model.Categoria;


@Controller
@RestController
@RequestMapping("")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;
 
    @GetMapping("/categorias")
    public List<Categoria> listaCategorias(){
        return categoriaService.listarCategorias();
    }
 
        @GetMapping("/categoria")
        public  ResponseEntity <Categoria> buscarCategoriaPorId(@RequestParam Long id){
            try{
                Categoria categoria = categoriaService.buscarCategoriaPorId(id);
                return ResponseEntity.ok(categoria);
            }catch(Exception e){
                return ResponseEntity.badRequest().body(null);

            }

        }
    @PostMapping("/categoria")
    public ResponseEntity<Map<String, Boolean>> agregarCategoria(@RequestBody Categoria categoria){
        Map<String, Boolean> response = new HashMap<>();
       try{
            categoriaService.guardarCategoria(categoria);
            response.put("Agregado!", Boolean.TRUE);
            return ResponseEntity.ok(response);
       }catch (Exception e){
            response.put("Agregado!", Boolean.FALSE);
            return ResponseEntity.badRequest().body(response);
       }
    }
    @PutMapping("/categoria")
    public ResponseEntity<Map<String, String>> editarCategoria(@RequestParam Long id, @RequestBody Categoria categoriaNueva){
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoriaVieja = categoriaService.buscarCategoriaPorId(id);
            categoriaVieja.setNombreCategoria(categoriaNueva.getNombreCategoria());
            categoriaService.guardarCategoria(categoriaVieja);
            response.put("message", "Modificado :3");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "no se puede hacer la  edición ");
            return ResponseEntity.badRequest().body(response);
        }
    }
 
    @DeleteMapping("/categoria")
    public ResponseEntity<Map<String, String>> eliminarCategoria(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoriaService.eliminarCategoria(categoria);
            response.put("message", "borrado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "no se puede eliminar compa");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
 