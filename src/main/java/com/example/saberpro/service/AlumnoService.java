package com.example.saberpro.service;



import com.example.saberpro.model.Alumno;
import com.example.saberpro.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlumnoService {
    
    @Autowired
    private AlumnoRepository alumnoRepository;
    
    // Crear o actualizar alumno
    public Alumno guardar(Alumno alumno) throws Exception {
        // Validar documento único (si es nuevo)
        if (alumno.getId() == null) {
            if (alumnoRepository.existsByNumeroDocumento(alumno.getNumeroDocumento())) {
                throw new Exception("Ya existe un alumno con ese número de documento");
            }
            if (alumnoRepository.existsByNumeroRegistro(alumno.getNumeroRegistro())) {
                throw new Exception("Ya existe un alumno con ese número de registro");
            }
        }
        
        return alumnoRepository.save(alumno);
    }
    
    // Obtener todos los alumnos
    public List<Alumno> obtenerTodos() {
        return alumnoRepository.findAll();
    }
    
    // Obtener alumno por ID
    public Optional<Alumno> obtenerPorId(Long id) {
        return alumnoRepository.findById(id);
    }
    
    // Obtener alumno por número de documento
    public Optional<Alumno> obtenerPorDocumento(String numeroDocumento) {
        return alumnoRepository.findByNumeroDocumento(numeroDocumento);
    }
    
    // Obtener alumno por número de registro
    public Optional<Alumno> obtenerPorRegistro(String numeroRegistro) {
        return alumnoRepository.findByNumeroRegistro(numeroRegistro);
    }
    
    // Buscar alumnos por término (nombre, apellido, documento, etc.)
    public List<Alumno> buscar(String termino) {
        if (termino == null || termino.trim().isEmpty()) {
            return obtenerTodos();
        }
        return alumnoRepository.buscarPorCualquierCampo(termino.trim());
    }
    
    // Eliminar alumno
    public void eliminar(Long id) {
        alumnoRepository.deleteById(id);
    }
    
    // Contar total de alumnos
    public long contarTotal() {
        return alumnoRepository.count();
    }
}