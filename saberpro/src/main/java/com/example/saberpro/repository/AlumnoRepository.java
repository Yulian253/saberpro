package com.example.saberpro.repository;

import com.example.saberpro.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    
    Optional<Alumno> findByNumeroDocumento(String numeroDocumento);
    
    Optional<Alumno> findByNumeroRegistro(String numeroRegistro);
    
    boolean existsByNumeroDocumento(String numeroDocumento);
    
    boolean existsByNumeroRegistro(String numeroRegistro);
    
    List<Alumno> findByPrimerApellidoContainingIgnoreCase(String apellido);
    
    List<Alumno> findByPrimerNombreContainingIgnoreCase(String nombre);
    
    @Query("SELECT a FROM Alumno a WHERE " +
           "LOWER(a.numeroDocumento) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(a.primerNombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(a.primerApellido) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(a.numeroRegistro) LIKE LOWER(CONCAT('%', :termino, '%'))")
    List<Alumno> buscarPorCualquierCampo(@Param("termino") String termino);
}