package com.planificador.backend.controller;

import com.planificador.backend.model.Nota;
import com.planificador.backend.repository.NotaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotaController {

    private final NotaRepository repo;

    public NotaController(NotaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Nota> listar() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Nota> crear(@Valid @RequestBody Nota nota) {
        nota.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(nota));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}