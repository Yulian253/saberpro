package com.example.saberpro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Column(nullable = false)
    private String password;
    
    @NotBlank(message = "El nombre completo es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombreCompleto;
    
    @Email(message = "El email debe ser válido")
    @Column(unique = true, nullable = false)
    private String email;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;
    
    @Column(nullable = false)
    private Boolean activo = true;
    
    // Enum para roles
    public enum Rol {
        COORDINADOR,
        ESTUDIANTE
    }
    
    // Constructor vacío
    public Usuario() {
    }
    
    // Constructor con todos los campos
    public Usuario(Long id, String username, String password, String nombreCompleto, 
                   String email, Rol rol, Boolean activo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.rol = rol;
        this.activo = activo;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Rol getRol() {
        return rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public Boolean getActivo() {
        return activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    // Builder estático
    public static UsuarioBuilder builder() {
        return new UsuarioBuilder();
    }
    
    public static class UsuarioBuilder {
        private Long id;
        private String username;
        private String password;
        private String nombreCompleto;
        private String email;
        private Rol rol;
        private Boolean activo = true;
        
        public UsuarioBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public UsuarioBuilder username(String username) {
            this.username = username;
            return this;
        }
        
        public UsuarioBuilder password(String password) {
            this.password = password;
            return this;
        }
        
        public UsuarioBuilder nombreCompleto(String nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
            return this;
        }
        
        public UsuarioBuilder email(String email) {
            this.email = email;
            return this;
        }
        
        public UsuarioBuilder rol(Rol rol) {
            this.rol = rol;
            return this;
        }
        
        public UsuarioBuilder activo(Boolean activo) {
            this.activo = activo;
            return this;
        }
        
        public Usuario build() {
            return new Usuario(id, username, password, nombreCompleto, email, rol, activo);
        }
    }
}