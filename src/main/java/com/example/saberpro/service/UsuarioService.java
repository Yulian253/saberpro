package com.example.saberpro.service;



import com.example.saberpro.model.Usuario;
import com.example.saberpro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    // Crear o actualizar usuario
    public Usuario guardar(Usuario usuario) {
        // En producción aquí deberías encriptar la contraseña con BCrypt
        // Por simplicidad, la guardamos tal cual
        return usuarioRepository.save(usuario);
    }
    
    // Registrar nuevo usuario
    public Usuario registrar(Usuario usuario) throws Exception {
        // Validar que no exista el username
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new Exception("El nombre de usuario ya existe");
        }
        
        // Validar que no exista el email
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new Exception("El email ya está registrado");
        }
        
        // Por defecto, los nuevos usuarios son estudiantes
        if (usuario.getRol() == null) {
            usuario.setRol(Usuario.Rol.ESTUDIANTE);
        }
        
        usuario.setActivo(true);
        return usuarioRepository.save(usuario);
    }
    
    // Login (autenticación simple)
    public Usuario login(String username, String password) throws Exception {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        
        if (usuarioOpt.isEmpty()) {
            throw new Exception("Usuario no encontrado");
        }
        
        Usuario usuario = usuarioOpt.get();
        
        if (!usuario.getActivo()) {
            throw new Exception("Usuario inactivo");
        }
        
        // Validar contraseña (sin encriptación por simplicidad)
        if (!usuario.getPassword().equals(password)) {
            throw new Exception("Contraseña incorrecta");
        }
        
        return usuario;
    }
    
    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }
    
    // Obtener usuario por ID
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    
    // Obtener usuario por username
    public Optional<Usuario> obtenerPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
    
    // Obtener usuarios por rol
    public List<Usuario> obtenerPorRol(Usuario.Rol rol) {
        return usuarioRepository.findByRol(rol);
    }
    
    // Eliminar usuario
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    // Activar/Desactivar usuario
    public void cambiarEstado(Long id, Boolean activo) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setActivo(activo);
            usuarioRepository.save(usuario);
        }
    }
}