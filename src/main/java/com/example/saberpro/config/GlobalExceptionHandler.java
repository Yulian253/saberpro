package com.example.saberpro.config;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(SecurityException.class)
    public String handleSecurityException(SecurityException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "Acceso denegado. Por favor inicia sesión.");
        return "redirect:/login";
    }
    
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "Ha ocurrido un error: " + ex.getMessage());
        return "redirect:/login";
    }
}