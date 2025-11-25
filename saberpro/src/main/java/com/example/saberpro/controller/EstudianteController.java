package com.example.saberpro.controller;

import com.example.saberpro.model.Alumno;
import com.example.saberpro.model.ResultadoSaberPro;
import com.example.saberpro.model.Usuario;
import com.example.saberpro.service.AlumnoService;
import com.example.saberpro.service.ResultadoSaberProService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {
    
    @Autowired
    private AlumnoService alumnoService;
    
    @Autowired
    private ResultadoSaberProService resultadoService;
    
    // Verificar acceso de estudiante
    @ModelAttribute
    public void verificarAcceso(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            throw new SecurityException("Acceso denegado");
        }
        model.addAttribute("usuarioNombre", usuario.getNombreCompleto());
        model.addAttribute("usuarioRol", usuario.getRol());
    }
    
    // Dashboard del estudiante
    @GetMapping("/dashboard")
    public String dashboard() {
        return "estudiante/dashboard";
    }
    
    // Mostrar formulario de búsqueda
    @GetMapping("/buscar")
    public String mostrarBusqueda() {
        return "estudiante/buscar";
    }
    
    // Procesar búsqueda y mostrar resultado
    @PostMapping("/resultado")
    public String verResultado(@RequestParam("identificacion") String identificacion,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        
        // Limpiar espacios
        identificacion = identificacion.trim();
        
        // Buscar alumno por documento
        Optional<Alumno> alumnoOpt = alumnoService.obtenerPorDocumento(identificacion);
        
        // Si no se encuentra por documento, buscar por registro
        if (alumnoOpt.isEmpty()) {
            alumnoOpt = alumnoService.obtenerPorRegistro(identificacion);
        }
        
        // Si no se encuentra el alumno
        if (alumnoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", 
                "No se encontró ningún alumno con la identificación: " + identificacion);
            return "redirect:/estudiante/buscar";
        }
        
        Alumno alumno = alumnoOpt.get();
        
        // Buscar resultado del alumno
        Optional<ResultadoSaberPro> resultadoOpt = resultadoService.obtenerPorAlumnoId(alumno.getId());
        
        // Si el alumno no tiene resultado
        if (resultadoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", 
                "El alumno " + alumno.getNombreCompleto() + " aún no tiene resultados registrados");
            return "redirect:/estudiante/buscar";
        }
        
        // Agregar datos al modelo y mostrar resultado
        model.addAttribute("alumno", alumno);
        model.addAttribute("resultado", resultadoOpt.get());
        
        return "estudiante/resultado";
    }
    
    // Ver todos los resultados
    @GetMapping("/resultados/todos")
    public String verTodosResultados(Model model) {
        model.addAttribute("resultados", resultadoService.obtenerTodos());
        return "estudiante/resultados-todos";
    }
    
    // Ver beneficios
    @GetMapping("/beneficios")
    public String verBeneficios(Model model) {
        model.addAttribute("conBeneficios", resultadoService.obtenerConBeneficios());
        return "estudiante/beneficios";
    }
}