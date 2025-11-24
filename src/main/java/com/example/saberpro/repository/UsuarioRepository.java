package com.example.saberpro.repository;



import com.example.saberpro.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Buscar usuario por username
    Optional<Usuario> findByUsername(String username);
    
    // Buscar usuario por email
    Optional<Usuario> findByEmail(String email);
    
    // Verificar si existe un username
    boolean existsByUsername(String username);
    
    // Verificar si existe un email
    boolean existsByEmail(String email);
    
    // Buscar usuarios por rol
    List<Usuario> findByRol(Usuario.Rol rol);
    
    // Buscar usuarios activos
    List<Usuario> findByActivo(Boolean activo);
}