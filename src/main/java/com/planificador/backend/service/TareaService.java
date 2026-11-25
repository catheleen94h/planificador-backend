package com.planificador.backend.service;

import com.planificador.backend.exception.TareaNoEncontradaException;
import com.planificador.backend.model.Tarea;
import com.planificador.backend.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository repo;

    public TareaService(TareaRepository repo) {
        this.repo = repo;
    }

    public List<Tarea> listar() {
        return repo.findAll();
    }

    public Tarea crear(Tarea tarea) {
        tarea.setId(null); // evita que el cliente fuerce un id
        return repo.save(tarea);
    }

    public Tarea actualizar(Integer id, Tarea datos) {
        Tarea tarea = repo.findById(id)
                .orElseThrow(() -> new TareaNoEncontradaException(id));
        tarea.setNombre(datos.getNombre());
        tarea.setDescripcion(datos.getDescripcion());
        tarea.setAsignadoA(datos.getAsignadoA());
        tarea.setFechaEntrega(datos.getFechaEntrega());
        tarea.setEstado(datos.getEstado());
        return repo.save(tarea);
    }

    public void eliminar(Integer id) {
        if (!repo.existsById(id)) {
            throw new TareaNoEncontradaException(id);
        }
        repo.deleteById(id);
    }
}