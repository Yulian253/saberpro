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

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/coordinador")
public class CoordinadorController {
    
    @Autowired
    private AlumnoService alumnoService;
    
    @Autowired
    private ResultadoSaberProService resultadoService;
    
    // Verificar acceso - SIN restricción de rol estricta
    @ModelAttribute
    public void verificarAcceso(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            throw new SecurityException("Acceso denegado");
        }
        model.addAttribute("usuarioNombre", usuario.getNombreCompleto());
        model.addAttribute("usuarioRol", usuario.getRol());
    }
    
    // Dashboard del coordinador
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Map<String, Object> estadisticas = resultadoService.obtenerEstadisticas();
        model.addAttribute("estadisticas", estadisticas);
        model.addAttribute("top10", resultadoService.obtenerTop10());
        return "coordinador/dashboard";
    }
    
    // ============ CRUD DE ALUMNOS ============
    
    // Listar alumnos
    @GetMapping("/alumnos")
    public String listarAlumnos(@RequestParam(name = "buscar", required = false) String buscar, Model model) {
        List<Alumno> alumnos;
        if (buscar != null && !buscar.trim().isEmpty()) {
            alumnos = alumnoService.buscar(buscar);
            model.addAttribute("buscar", buscar);
        } else {
            alumnos = alumnoService.obtenerTodos();
        }
        model.addAttribute("alumnos", alumnos);
        model.addAttribute("totalAlumnos", alumnos.size());
        return "coordinador/alumnos/lista";
    }
    
    // Mostrar formulario para crear alumno
    @GetMapping("/alumnos/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("accion", "nuevo");
        return "coordinador/alumnos/formulario";
    }
    
    // Mostrar formulario para editar alumno
    @GetMapping("/alumnos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model, 
                                         RedirectAttributes redirectAttributes) {
        Optional<Alumno> alumnoOpt = alumnoService.obtenerPorId(id);
        if (alumnoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Alumno no encontrado");
            return "redirect:/coordinador/alumnos";
        }
        model.addAttribute("alumno", alumnoOpt.get());
        model.addAttribute("accion", "editar");
        return "coordinador/alumnos/formulario";
    }
    
    // Guardar alumno (crear o actualizar)
    @PostMapping("/alumnos/guardar")
    public String guardarAlumno(@ModelAttribute Alumno alumno, 
                               RedirectAttributes redirectAttributes) {
        try {
            alumnoService.guardar(alumno);
            redirectAttributes.addFlashAttribute("success", 
                alumno.getId() == null ? "Alumno creado exitosamente" : "Alumno actualizado exitosamente");
            return "redirect:/coordinador/alumnos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/coordinador/alumnos/nuevo";
        }
    }
    
    // Eliminar alumno
    @GetMapping("/alumnos/eliminar/{id}")
    public String eliminarAlumno(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            alumnoService.eliminar(id);
            redirectAttributes.addFlashAttribute("success", "Alumno eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar alumno: " + e.getMessage());
        }
        return "redirect:/coordinador/alumnos";
    }
    
    // Ver detalle de alumno
    @GetMapping("/alumnos/detalle/{id}")
    public String verDetalleAlumno(@PathVariable("id") Long id, Model model, 
                                  RedirectAttributes redirectAttributes) {
        Optional<Alumno> alumnoOpt = alumnoService.obtenerPorId(id);
        if (alumnoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Alumno no encontrado");
            return "redirect:/coordinador/alumnos";
        }
        
        Alumno alumno = alumnoOpt.get();
        model.addAttribute("alumno", alumno);
        
        Optional<ResultadoSaberPro> resultadoOpt = resultadoService.obtenerPorAlumnoId(id);
        resultadoOpt.ifPresent(resultado -> model.addAttribute("resultado", resultado));
        
        return "coordinador/alumnos/detalle";
    }
    
    // ============ GESTIÓN DE RESULTADOS ============
    
    // Mostrar formulario para agregar/editar resultado
    @GetMapping("/alumnos/{id}/resultado")
    public String gestionarResultado(@PathVariable("id") Long id, Model model, 
                                    RedirectAttributes redirectAttributes) {
        Optional<Alumno> alumnoOpt = alumnoService.obtenerPorId(id);
        if (alumnoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Alumno no encontrado");
            return "redirect:/coordinador/alumnos";
        }
        
        Alumno alumno = alumnoOpt.get();
        model.addAttribute("alumno", alumno);
        
        Optional<ResultadoSaberPro> resultadoOpt = resultadoService.obtenerPorAlumnoId(id);
        ResultadoSaberPro resultado = resultadoOpt.orElse(new ResultadoSaberPro());
        if (resultado.getId() == null) {
            resultado.setAlumno(alumno);
        }
        
        model.addAttribute("resultado", resultado);
        return "coordinador/alumnos/resultado-formulario";
    }
    
    // Guardar resultado
    @PostMapping("/alumnos/{id}/resultado/guardar")
    public String guardarResultado(@PathVariable("id") Long id,
                                  @ModelAttribute ResultadoSaberPro resultado,
                                  RedirectAttributes redirectAttributes) {
        try {
            Optional<Alumno> alumnoOpt = alumnoService.obtenerPorId(id);
            if (alumnoOpt.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Alumno no encontrado");
                return "redirect:/coordinador/alumnos";
            }
            
            resultado.setAlumno(alumnoOpt.get());
            resultadoService.guardar(resultado);
            redirectAttributes.addFlashAttribute("success", "Resultado guardado exitosamente");
            return "redirect:/coordinador/alumnos/detalle/" + id;
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar resultado: " + e.getMessage());
            return "redirect:/coordinador/alumnos/" + id + "/resultado";
        }
    }
    
    // ============ INFORMES ============
    
    // Informe básico de alumnos
    @GetMapping("/informes/basico")
    public String informeBasico(Model model) {
        List<Alumno> alumnos = alumnoService.obtenerTodos();
        model.addAttribute("alumnos", alumnos);
        return "coordinador/informes/basico";
    }
    
    // Informe detallado de alumnos
    @GetMapping("/informes/detallado")
    public String informeDetallado(Model model) {
        List<ResultadoSaberPro> resultados = resultadoService.obtenerTodos();
        model.addAttribute("resultados", resultados);
        Map<String, Object> estadisticas = resultadoService.obtenerEstadisticas();
        model.addAttribute("estadisticas", estadisticas);
        return "coordinador/informes/detallado";
    }
    
    // Informe de beneficios
    @GetMapping("/informes/beneficios")
    public String informeBeneficios(Model model) {
        List<ResultadoSaberPro> conBeneficios = resultadoService.obtenerConBeneficios();
        model.addAttribute("conBeneficios", conBeneficios);
        return "coordinador/informes/beneficios";
    }
}