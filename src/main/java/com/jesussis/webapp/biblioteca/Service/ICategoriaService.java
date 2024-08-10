package com.jesussis.webapp.biblioteca.Service;

import java.util.List;

import com.jesussis.webapp.biblioteca.model.Categoria;
public interface ICategoriaService {

    public List<Categoria>  listarCategorias();

    public Categoria  guardarCategoria(Categoria categoria);

    public Categoria buscarCategoriaPorId(Long id);
    
    public void eliminarCategoria(Categoria categoria);
}
