package com.jesussis.webapp.biblioteca.Service;

import java.util.List;

import com.jesussis.webapp.biblioteca.model.Categoria;
import com.jesussis.webapp.biblioteca.model.Cliente;
public interface IClienteService {

    public List<Categoria>  listarClientes();

    public Categoria  guardarCliente(Cliente cliente);

    public Categoria buscarClientePorId(Long id);
    
    public void eliminarCliente(Cliente cliente);
}
