package com.cibertec.runner.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.request.WorkerDTO;
import com.cibertec.runner.service.WorkerService;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    private WorkerService service;

    // Listados
    @GetMapping
    public Map<String, Object> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/activos")
    public Map<String, Object> listarActivos() {
        return service.listarActivos();
    }

    @GetMapping("/inactivos")
    public Map<String, Object> listarInactivos() {
        return service.listarInactivos();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public Map<String, Object> obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    // Registrar
    @PostMapping
    public Map<String, Object> registrar(@RequestBody WorkerDTO workerDTO) {
        return service.registrar(workerDTO);
    }

    // Eliminador lógico
    @DeleteMapping("/{id}")
    public Map<String, Object> eliminarLogico(@PathVariable Integer id) {
        return service.eliminarLogico(id);
    }
}
