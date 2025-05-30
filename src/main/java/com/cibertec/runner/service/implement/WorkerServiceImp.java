package com.cibertec.runner.service.implement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.request.WorkerDTO;
import com.cibertec.runner.model.User;
import com.cibertec.runner.model.Worker;
import com.cibertec.runner.repository.IWorkerRepository;
import com.cibertec.runner.repository.IUserRepository;
import com.cibertec.runner.service.WorkerService;

@Service
public class WorkerServiceImp implements WorkerService {

    @Autowired
    private IWorkerRepository trabajadorRepo;

    @Autowired
    private IUserRepository usuarioRepo;

    /// Registrar
    @Override
    public Map<String, Object> registrar(WorkerDTO trabajador) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
        	User usu = usuarioRepo.save(trabajador.getUser());
            
            Worker t = new Worker();
            t.setId(usu.getId());
            t.setState(true);
            t.setSalary(trabajador.getSalary());
            
            Worker guardado = trabajadorRepo.save(t);
            
            respuesta.put("mensaje", "Trabajador registrado correctamente");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.CREATED);
            respuesta.put("trabajador", guardado);
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al registrar trabajador: " + e.getMessage());
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }


    /// Eliminación lógica
    @Override
    public Map<String, Object> eliminarLogico(Integer id) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            Optional<Worker> op = trabajadorRepo.findById(id);
            if (op.isPresent()) {
            	Worker t = op.get();
                t.setState(true);
                trabajadorRepo.save(t);
                respuesta.put("mensaje", "Trabajador eliminado lógicamente");
                respuesta.put("fecha", new Date());
                respuesta.put("status", HttpStatus.OK);
                respuesta.put("trabajador", t);
            } else {
                respuesta.put("mensaje", "Trabajador no encontrado");
                respuesta.put("fecha", new Date());
                respuesta.put("status", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al eliminar trabajador: " + e.getMessage());
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }

    /// Listar todos
    @Override
    public Map<String, Object> listarTodos() {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            List<Worker> lista = trabajadorRepo.findAll();
            respuesta.put("mensaje", "Listado de todos los trabajadores");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("trabajadores", lista);
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al listar trabajadores: " + e.getMessage());
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }

    /// Listar activos
    @Override
    public Map<String, Object> listarActivos() {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            List<Worker> lista = trabajadorRepo.findAll().stream()
                    .filter(t -> true == t.getState())
                    .toList();
            respuesta.put("mensaje", "Listado de trabajadores activos");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("trabajadores", lista);
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al listar trabajadores activos: " + e.getMessage());
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }

    /// Listar inactivos
    @Override
    public Map<String, Object> listarInactivos() {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            List<Worker> lista = trabajadorRepo.findAll().stream()
                    .filter(t -> false == t.getState())
                    .toList();
            respuesta.put("mensaje", "Listado de trabajadores inactivos");
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("trabajadores", lista);
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al listar trabajadores inactivos: " + e.getMessage());
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }

    /// Obtener por ID
    @Override
    public Map<String, Object> obtenerPorId(Integer id) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            Optional<Worker> trabajador = trabajadorRepo.findById(id);
            if (trabajador.isPresent()) {
                respuesta.put("mensaje", "Trabajador encontrado");
                respuesta.put("fecha", new Date());
                respuesta.put("status", HttpStatus.OK);
                respuesta.put("trabajador", trabajador.get());
            } else {
                respuesta.put("mensaje", "Trabajador no encontrado");
                respuesta.put("fecha", new Date());
                respuesta.put("status", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al buscar trabajador: " + e.getMessage());
            respuesta.put("fecha", new Date());
            respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }
}
