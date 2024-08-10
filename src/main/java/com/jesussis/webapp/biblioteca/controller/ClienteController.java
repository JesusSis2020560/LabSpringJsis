package com.jesussis.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jesussis.webapp.biblioteca.model.Cliente;
import com.jesussis.webapp.biblioteca.Service.ClienteService;

@Controller
@RestController
@RequestMapping("")

public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> listarCliente(){
        return clienteService.listarCliente();
    }

    @GetMapping("cliente")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long nit){
        try {
            Cliente cliente = clienteService.buscarClientePorId(nit);

            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/cliente")
    public ResponseEntity<Map<String, String>> agregarCliente(Cliente cliente){
        Map<String, String> response = new HashMap<>();
        try {
            clienteService.guardarCliente(cliente);
            response.put("message", "se ha agregado el cliente con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al agregar el cliente");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("cliente")
    public ResponseEntity<Map<String, String>> editarCliente(@RequestParam Long nit, @RequestBody Cliente clienteNuevo){
        Map<String, String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClientePorId(nit);

            cliente.setApellido(clienteNuevo.getApellido());
            cliente.setNit(clienteNuevo.getNit());
            cliente.setNombre(clienteNuevo.getNombre());
            cliente.setNumeroT(clienteNuevo.getNumeroT());

            response.put("message", "El cliente ha sido modificado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al modificar a el cliente");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/cliente")
    public ResponseEntity<Map<String, String>> eliminarCliente(@RequestParam Long nit){
        Map<String, String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClientePorId(nit);
            clienteService.eliminarCliente(cliente);
            response.put("message", "cliente eliminado con exito");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar a el cliente");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
