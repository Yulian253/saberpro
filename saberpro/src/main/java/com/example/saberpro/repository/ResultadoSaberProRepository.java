package com.example.saberpro.repository;

import com.example.saberpro.model.ResultadoSaberPro;
import com.example.saberpro.model.ResultadoSaberPro.EstadoExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultadoSaberProRepository extends JpaRepository<ResultadoSaberPro, Long> {
    
    Optional<ResultadoSaberPro> findByAlumnoId(Long alumnoId);
    
    List<ResultadoSaberPro> findByEstadoExamen(EstadoExamen estadoExamen);
    
    List<ResultadoSaberPro> findByExoneradoTrabajoGrado(Boolean exonerado);
    
    @Query("SELECT r FROM ResultadoSaberPro r WHERE r.puntajeSaberPro BETWEEN :min AND :max")
    List<ResultadoSaberPro> findByPuntajeRange(@Param("min") Integer min, @Param("max") Integer max);
    
    @Query("SELECT AVG(r.puntajeSaberPro) FROM ResultadoSaberPro r WHERE r.estadoExamen != 'ANULADO'")
    Double obtenerPromedioGeneral();
    
    @Query("SELECT COUNT(r) FROM ResultadoSaberPro r WHERE r.estadoExamen = :estado")
    Long contarPorEstado(@Param("estado") EstadoExamen estado);
    
    @Query("SELECT r FROM ResultadoSaberPro r WHERE r.estadoExamen != 'ANULADO' ORDER BY r.puntajeSaberPro DESC")
    List<ResultadoSaberPro> findTop10ByOrderByPuntajeSaberProDesc();
}