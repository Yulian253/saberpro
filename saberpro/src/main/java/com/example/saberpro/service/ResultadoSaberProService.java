package com.example.saberpro.service;



import com.example.saberpro.model.ResultadoSaberPro;
import com.example.saberpro.model.ResultadoSaberPro.EstadoExamen;
import com.example.saberpro.repository.ResultadoSaberProRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ResultadoSaberProService {
    
    @Autowired
    private ResultadoSaberProRepository resultadoRepository;
    
    // Guardar o actualizar resultado
    public ResultadoSaberPro guardar(ResultadoSaberPro resultado) {
        // Calcular beneficios automáticamente antes de guardar
        resultado.calcularBeneficios();
        return resultadoRepository.save(resultado);
    }
    
    // Obtener todos los resultados
    public List<ResultadoSaberPro> obtenerTodos() {
        return resultadoRepository.findAll();
    }
    
    // Obtener resultado por ID
    public Optional<ResultadoSaberPro> obtenerPorId(Long id) {
        return resultadoRepository.findById(id);
    }
    
    // Obtener resultado por ID de alumno
    public Optional<ResultadoSaberPro> obtenerPorAlumnoId(Long alumnoId) {
        return resultadoRepository.findByAlumnoId(alumnoId);
    }
    
    // Obtener resultados por estado
    public List<ResultadoSaberPro> obtenerPorEstado(EstadoExamen estado) {
        return resultadoRepository.findByEstadoExamen(estado);
    }
    
    // Obtener resultados con beneficios
    public List<ResultadoSaberPro> obtenerConBeneficios() {
        return resultadoRepository.findByExoneradoTrabajoGrado(true);
    }
    
    // Obtener top 10 mejores resultados
    public List<ResultadoSaberPro> obtenerTop10() {
        List<ResultadoSaberPro> resultados = resultadoRepository.findTop10ByOrderByPuntajeSaberProDesc();
        return resultados.size() > 10 ? resultados.subList(0, 10) : resultados;
    }
    
    // Obtener estadísticas generales
    public Map<String, Object> obtenerEstadisticas() {
        Map<String, Object> estadisticas = new HashMap<>();
        
        long total = resultadoRepository.count();
        long aprobados = resultadoRepository.contarPorEstado(EstadoExamen.APROBADO);
        long reprobados = resultadoRepository.contarPorEstado(EstadoExamen.REPROBADO);
        long anulados = resultadoRepository.contarPorEstado(EstadoExamen.ANULADO);
        Double promedio = resultadoRepository.obtenerPromedioGeneral();
        
        estadisticas.put("total", total);
        estadisticas.put("aprobados", aprobados);
        estadisticas.put("reprobados", reprobados);
        estadisticas.put("anulados", anulados);
        estadisticas.put("promedio", promedio != null ? Math.round(promedio * 100.0) / 100.0 : 0.0);
        
        // Porcentajes
        if (total > 0) {
            estadisticas.put("porcentajeAprobados", Math.round((aprobados * 100.0 / total) * 100.0) / 100.0);
            estadisticas.put("porcentajeReprobados", Math.round((reprobados * 100.0 / total) * 100.0) / 100.0);
            estadisticas.put("porcentajeAnulados", Math.round((anulados * 100.0 / total) * 100.0) / 100.0);
        }
        
        return estadisticas;
    }
    
    // Eliminar resultado
    public void eliminar(Long id) {
        resultadoRepository.deleteById(id);
    }
}