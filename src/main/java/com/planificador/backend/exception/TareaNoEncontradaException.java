package com.planificador.backend.exception;

public class TareaNoEncontradaException extends RuntimeException {

    public TareaNoEncontradaException(Integer id) {
        super("No existe una tarea con id " + id);
    }
}