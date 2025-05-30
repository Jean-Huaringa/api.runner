package com.cibertec.runner.service.implement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.request.TrabajadorDTO;
import com.cibertec.runner.model.Trabajador;
import com.cibertec.runner.model.Usuario;
import com.cibertec.runner.repository.ITrabajadorRepository;
import com.cibertec.runner.repository.IUsuarioRepository;
import com.cibertec.runner.service.TrabajadorService;

@Service
public class TrabajadorServiceImp implements TrabajadorService {

    @Autowired
    private ITrabajadorRepository trabajadorRepo;

    @Autowired
    private IUsuarioRepository usuarioRepo;

    /// Registrar
    @Override
    public Map<String, Object> registrar(TrabajadorDTO trabajador) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            Usuario usu = usuarioRepo.save(trabajador.getUsuario());
            
            Trabajador t = new Trabajador();
            t.setId(usu.getId());
            t.setEstado("A");
            t.setHorasLaborales(trabajador.getHorasLaborales());
            t.setSalario(trabajador.getSalario());
            
            Trabajador guardado = trabajadorRepo.save(t);
            
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
            Optional<Trabajador> op = trabajadorRepo.findById(id);
            if (op.isPresent()) {
                Trabajador t = op.get();
                t.setEstado("I");
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
            List<Trabajador> lista = trabajadorRepo.findAll();
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
            List<Trabajador> lista = trabajadorRepo.findAll().stream()
                    .filter(t -> "A".equals(t.getEstado()))
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
            List<Trabajador> lista = trabajadorRepo.findAll().stream()
                    .filter(t -> "I".equals(t.getEstado()))
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
            Optional<Trabajador> trabajador = trabajadorRepo.findById(id);
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
