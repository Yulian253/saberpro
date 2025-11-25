package com.example.saberpro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "alumnos")
public class Alumno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El número de documento es obligatorio")
    @Column(unique = true, nullable = false, length = 20)
    private String numeroDocumento;
    
    @NotBlank(message = "El primer apellido es obligatorio")
    @Column(nullable = false, length = 50)
    private String primerApellido;
    
    @Column(length = 50)
    private String segundoApellido;
    
    @NotBlank(message = "El primer nombre es obligatorio")
    @Column(nullable = false, length = 50)
    private String primerNombre;
    
    @Column(length = 50)
    private String segundoNombre;
    
    @Email(message = "El email debe ser válido")
    @Column(length = 100)
    private String correoElectronico;
    
    @Column(length = 20)
    private String numeroTelefonico;
    
    @NotBlank(message = "El número de registro es obligatorio")
    @Column(unique = true, nullable = false, length = 20)
    private String numeroRegistro;
    
    @OneToOne(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
    private ResultadoSaberPro resultado;
    
    // Constructor vacío
    public Alumno() {
    }
    
    // Constructor con todos los campos
    public Alumno(Long id, String numeroDocumento, String primerApellido, String segundoApellido,
                  String primerNombre, String segundoNombre, String correoElectronico,
                  String numeroTelefonico, String numeroRegistro) {
        this.id = id;
        this.numeroDocumento = numeroDocumento;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.correoElectronico = correoElectronico;
        this.numeroTelefonico = numeroTelefonico;
        this.numeroRegistro = numeroRegistro;
    }
    
    // Método auxiliar para obtener nombre completo
    public String getNombreCompleto() {
        StringBuilder nombre = new StringBuilder();
        nombre.append(primerNombre);
        if (segundoNombre != null && !segundoNombre.isEmpty()) {
            nombre.append(" ").append(segundoNombre);
        }
        nombre.append(" ").append(primerApellido);
        if (segundoApellido != null && !segundoApellido.isEmpty()) {
            nombre.append(" ").append(segundoApellido);
        }
        return nombre.toString();
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNumeroDocumento() {
        return numeroDocumento;
    }
    
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    
    public String getPrimerApellido() {
        return primerApellido;
    }
    
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
    
    public String getSegundoApellido() {
        return segundoApellido;
    }
    
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }
    
    public String getPrimerNombre() {
        return primerNombre;
    }
    
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }
    
    public String getSegundoNombre() {
        return segundoNombre;
    }
    
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }
    
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }
    
    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }
    
    public String getNumeroRegistro() {
        return numeroRegistro;
    }
    
    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }
    
    public ResultadoSaberPro getResultado() {
        return resultado;
    }
    
    public void setResultado(ResultadoSaberPro resultado) {
        this.resultado = resultado;
    }
    
    // Builder estático
    public static AlumnoBuilder builder() {
        return new AlumnoBuilder();
    }
    
    public static class AlumnoBuilder {
        private Long id;
        private String numeroDocumento;
        private String primerApellido;
        private String segundoApellido;
        private String primerNombre;
        private String segundoNombre;
        private String correoElectronico;
        private String numeroTelefonico;
        private String numeroRegistro;
        
        public AlumnoBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public AlumnoBuilder numeroDocumento(String numeroDocumento) {
            this.numeroDocumento = numeroDocumento;
            return this;
        }
        
        public AlumnoBuilder primerApellido(String primerApellido) {
            this.primerApellido = primerApellido;
            return this;
        }
        
        public AlumnoBuilder segundoApellido(String segundoApellido) {
            this.segundoApellido = segundoApellido;
            return this;
        }
        
        public AlumnoBuilder primerNombre(String primerNombre) {
            this.primerNombre = primerNombre;
            return this;
        }
        
        public AlumnoBuilder segundoNombre(String segundoNombre) {
            this.segundoNombre = segundoNombre;
            return this;
        }
        
        public AlumnoBuilder correoElectronico(String correoElectronico) {
            this.correoElectronico = correoElectronico;
            return this;
        }
        
        public AlumnoBuilder numeroTelefonico(String numeroTelefonico) {
            this.numeroTelefonico = numeroTelefonico;
            return this;
        }
        
        public AlumnoBuilder numeroRegistro(String numeroRegistro) {
            this.numeroRegistro = numeroRegistro;
            return this;
        }
        
        public Alumno build() {
            return new Alumno(id, numeroDocumento, primerApellido, segundoApellido,
                             primerNombre, segundoNombre, correoElectronico,
                             numeroTelefonico, numeroRegistro);
        }
    }
}