package com.example.saberpro.controller;

import com.example.saberpro.model.Usuario;
import com.example.saberpro.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    // Página principal - Redirecciona al login
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }
    
    // Mostrar formulario de login
    @GetMapping("/login")
    public String mostrarLogin(HttpSession session) {
        // Si ya está logueado, redirigir al dashboard
        if (session.getAttribute("usuario") != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (usuario.getRol() == Usuario.Rol.COORDINADOR) {
                return "redirect:/coordinador/dashboard";
            } else {
                return "redirect:/estudiante/dashboard";
            }
        }
        return "auth/login";
    }
    
    // Procesar login - CORREGIDO con nombres explícitos
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = usuarioService.login(username, password);
            
            // Guardar usuario en sesión
            session.setAttribute("usuario", usuario);
            session.setAttribute("usuarioId", usuario.getId());
            session.setAttribute("usuarioNombre", usuario.getNombreCompleto());
            session.setAttribute("usuarioRol", usuario.getRol());
            
            // Redirigir según el rol
            if (usuario.getRol() == Usuario.Rol.COORDINADOR) {
                return "redirect:/coordinador/dashboard";
            } else {
                return "redirect:/estudiante/dashboard";
            }
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
    }
    
    // Mostrar formulario de registro
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "auth/registro";
    }
    
    // Procesar registro
    @PostMapping("/registro")
    public String registrar(@ModelAttribute Usuario usuario,
                           RedirectAttributes redirectAttributes) {
        try {
            usuarioService.registrar(usuario);
            redirectAttributes.addFlashAttribute("success", 
                "Registro exitoso. Por favor inicia sesión.");
            return "redirect:/login";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/registro";
        }
    }
    
    // Cerrar sesión
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "Sesión cerrada exitosamente");
        return "redirect:/login";
    }
}