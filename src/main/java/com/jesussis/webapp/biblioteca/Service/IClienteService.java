package com.jesussis.webapp.biblioteca.Service;

import java.util.List;

import com.jesussis.webapp.biblioteca.model.Cliente;
public interface IClienteService {

    public List<Cliente> listarCliente();

    public Cliente guardarCliente(Cliente cliente);

    public Cliente buscarClientePorId(Long nit);

    public void eliminarCliente(Cliente cliente);
}
