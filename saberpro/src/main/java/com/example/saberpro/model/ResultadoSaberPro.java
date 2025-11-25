package com.example.saberpro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "resultados_saber_pro")
public class ResultadoSaberPro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "alumno_id", nullable = false, unique = true)
    private Alumno alumno;
    
    private Integer puntajeSaberPro;
    private String nivelSaberPro;
    
    private Integer puntajeComunicacionEscrita;
    private String nivelComunicacionEscrita;
    
    private Integer puntajeRazonamientoCuantitativo;
    private String nivelRazonamientoCuantitativo;
    
    private Integer puntajeLecturaCritica;
    private String nivelLecturaCritica;
    
    private Integer puntajeCompetenciasCiudadanas;
    private String nivelCompetenciasCiudadanas;
    
    private Integer puntajeIngles;
    private String nivelIngles;
    private String nivelInglesCEFR;
    
    private Integer puntajeFormulacionProyectos;
    private String nivelFormulacionProyectos;
    
    private Integer puntajePensamientoCientifico;
    private String nivelPensamientoCientifico;
    
    private Integer puntajeDisenoSoftware;
    private String nivelDisenoSoftware;
    
    @Enumerated(EnumType.STRING)
    private EstadoExamen estadoExamen;
    
    private Boolean exoneradoTrabajoGrado = false;
    private Double notaTrabajoGrado;
    private Integer porcentajeBecaDerechosGrado = 0;
    private String descripcionBeneficio;
    
    public enum EstadoExamen {
        APROBADO,
        REPROBADO,
        ANULADO
    }
    
    // Constructor vacío
    public ResultadoSaberPro() {
    }
    
    // Método para calcular beneficios
    public void calcularBeneficios() {
        if (estadoExamen == EstadoExamen.ANULADO) {
            this.exoneradoTrabajoGrado = false;
            this.notaTrabajoGrado = null;
            this.porcentajeBecaDerechosGrado = 0;
            this.descripcionBeneficio = "Examen anulado - No aplica beneficios. Debe presentar nuevamente.";
            return;
        }
        
        if (puntajeSaberPro == null || puntajeSaberPro < 80) {
            // Puntaje menor a 80 - NO puede graduarse
            this.exoneradoTrabajoGrado = false;
            this.notaTrabajoGrado = null;
            this.porcentajeBecaDerechosGrado = 0;
            this.estadoExamen = EstadoExamen.REPROBADO;
            this.descripcionBeneficio = "¡PERDISTE EL SABER PRO! Puntaje menor a 80. No puedes graduarte. Debes presentar nuevamente el examen.";
        } else if (puntajeSaberPro >= 80 && puntajeSaberPro < 120) {
            // Puntaje entre 80 y 119 - Aprobó pero sin beneficios
            this.exoneradoTrabajoGrado = false;
            this.notaTrabajoGrado = null;
            this.porcentajeBecaDerechosGrado = 0;
            this.estadoExamen = EstadoExamen.REPROBADO;
            this.descripcionBeneficio = "No alcanzó puntaje mínimo para beneficios (120). Debe presentar nuevamente para obtener beneficios.";
        } else if (puntajeSaberPro >= 120 && puntajeSaberPro <= 150) {
            this.exoneradoTrabajoGrado = true;
            this.notaTrabajoGrado = 4.5;
            this.porcentajeBecaDerechosGrado = 0;
            this.estadoExamen = EstadoExamen.APROBADO;
            this.descripcionBeneficio = "Exonerado de trabajo de grado con nota 4.5";
        } else if (puntajeSaberPro >= 151 && puntajeSaberPro <= 170) {
            this.exoneradoTrabajoGrado = true;
            this.notaTrabajoGrado = 4.7;
            this.porcentajeBecaDerechosGrado = 50;
            this.estadoExamen = EstadoExamen.APROBADO;
            this.descripcionBeneficio = "Exonerado de trabajo de grado con nota 4.7 + 50% beca derechos de grado";
        } else if (puntajeSaberPro >= 171) {
            this.exoneradoTrabajoGrado = true;
            this.notaTrabajoGrado = 5.0;
            this.porcentajeBecaDerechosGrado = 100;
            this.estadoExamen = EstadoExamen.APROBADO;
            this.descripcionBeneficio = "Exonerado de trabajo de grado con nota 5.0 + 100% beca derechos de grado";
        }
    }
    
    // Método para verificar si necesita repetir el examen
    public boolean necesitaRepetirExamen() {
        return estadoExamen == EstadoExamen.ANULADO || 
               estadoExamen == EstadoExamen.REPROBADO ||
               (puntajeSaberPro != null && puntajeSaberPro < 120);
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Alumno getAlumno() {
        return alumno;
    }
    
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
    public Integer getPuntajeSaberPro() {
        return puntajeSaberPro;
    }
    
    public void setPuntajeSaberPro(Integer puntajeSaberPro) {
        this.puntajeSaberPro = puntajeSaberPro;
    }
    
    public String getNivelSaberPro() {
        return nivelSaberPro;
    }
    
    public void setNivelSaberPro(String nivelSaberPro) {
        this.nivelSaberPro = nivelSaberPro;
    }
    
    public Integer getPuntajeComunicacionEscrita() {
        return puntajeComunicacionEscrita;
    }
    
    public void setPuntajeComunicacionEscrita(Integer puntajeComunicacionEscrita) {
        this.puntajeComunicacionEscrita = puntajeComunicacionEscrita;
    }
    
    public String getNivelComunicacionEscrita() {
        return nivelComunicacionEscrita;
    }
    
    public void setNivelComunicacionEscrita(String nivelComunicacionEscrita) {
        this.nivelComunicacionEscrita = nivelComunicacionEscrita;
    }
    
    public Integer getPuntajeRazonamientoCuantitativo() {
        return puntajeRazonamientoCuantitativo;
    }
    
    public void setPuntajeRazonamientoCuantitativo(Integer puntajeRazonamientoCuantitativo) {
        this.puntajeRazonamientoCuantitativo = puntajeRazonamientoCuantitativo;
    }
    
    public String getNivelRazonamientoCuantitativo() {
        return nivelRazonamientoCuantitativo;
    }
    
    public void setNivelRazonamientoCuantitativo(String nivelRazonamientoCuantitativo) {
        this.nivelRazonamientoCuantitativo = nivelRazonamientoCuantitativo;
    }
    
    public Integer getPuntajeLecturaCritica() {
        return puntajeLecturaCritica;
    }
    
    public void setPuntajeLecturaCritica(Integer puntajeLecturaCritica) {
        this.puntajeLecturaCritica = puntajeLecturaCritica;
    }
    
    public String getNivelLecturaCritica() {
        return nivelLecturaCritica;
    }
    
    public void setNivelLecturaCritica(String nivelLecturaCritica) {
        this.nivelLecturaCritica = nivelLecturaCritica;
    }
    
    public Integer getPuntajeCompetenciasCiudadanas() {
        return puntajeCompetenciasCiudadanas;
    }
    
    public void setPuntajeCompetenciasCiudadanas(Integer puntajeCompetenciasCiudadanas) {
        this.puntajeCompetenciasCiudadanas = puntajeCompetenciasCiudadanas;
    }
    
    public String getNivelCompetenciasCiudadanas() {
        return nivelCompetenciasCiudadanas;
    }
    
    public void setNivelCompetenciasCiudadanas(String nivelCompetenciasCiudadanas) {
        this.nivelCompetenciasCiudadanas = nivelCompetenciasCiudadanas;
    }
    
    public Integer getPuntajeIngles() {
        return puntajeIngles;
    }
    
    public void setPuntajeIngles(Integer puntajeIngles) {
        this.puntajeIngles = puntajeIngles;
    }
    
    public String getNivelIngles() {
        return nivelIngles;
    }
    
    public void setNivelIngles(String nivelIngles) {
        this.nivelIngles = nivelIngles;
    }
    
    public String getNivelInglesCEFR() {
        return nivelInglesCEFR;
    }
    
    public void setNivelInglesCEFR(String nivelInglesCEFR) {
        this.nivelInglesCEFR = nivelInglesCEFR;
    }
    
    public Integer getPuntajeFormulacionProyectos() {
        return puntajeFormulacionProyectos;
    }
    
    public void setPuntajeFormulacionProyectos(Integer puntajeFormulacionProyectos) {
        this.puntajeFormulacionProyectos = puntajeFormulacionProyectos;
    }
    
    public String getNivelFormulacionProyectos() {
        return nivelFormulacionProyectos;
    }
    
    public void setNivelFormulacionProyectos(String nivelFormulacionProyectos) {
        this.nivelFormulacionProyectos = nivelFormulacionProyectos;
    }
    
    public Integer getPuntajePensamientoCientifico() {
        return puntajePensamientoCientifico;
    }
    
    public void setPuntajePensamientoCientifico(Integer puntajePensamientoCientifico) {
        this.puntajePensamientoCientifico = puntajePensamientoCientifico;
    }
    
    public String getNivelPensamientoCientifico() {
        return nivelPensamientoCientifico;
    }
    
    public void setNivelPensamientoCientifico(String nivelPensamientoCientifico) {
        this.nivelPensamientoCientifico = nivelPensamientoCientifico;
    }
    
    public Integer getPuntajeDisenoSoftware() {
        return puntajeDisenoSoftware;
    }
    
    public void setPuntajeDisenoSoftware(Integer puntajeDisenoSoftware) {
        this.puntajeDisenoSoftware = puntajeDisenoSoftware;
    }
    
    public String getNivelDisenoSoftware() {
        return nivelDisenoSoftware;
    }
    
    public void setNivelDisenoSoftware(String nivelDisenoSoftware) {
        this.nivelDisenoSoftware = nivelDisenoSoftware;
    }
    
    public EstadoExamen getEstadoExamen() {
        return estadoExamen;
    }
    
    public void setEstadoExamen(EstadoExamen estadoExamen) {
        this.estadoExamen = estadoExamen;
    }
    
    public Boolean getExoneradoTrabajoGrado() {
        return exoneradoTrabajoGrado;
    }
    
    public void setExoneradoTrabajoGrado(Boolean exoneradoTrabajoGrado) {
        this.exoneradoTrabajoGrado = exoneradoTrabajoGrado;
    }
    
    public Double getNotaTrabajoGrado() {
        return notaTrabajoGrado;
    }
    
    public void setNotaTrabajoGrado(Double notaTrabajoGrado) {
        this.notaTrabajoGrado = notaTrabajoGrado;
    }
    
    public Integer getPorcentajeBecaDerechosGrado() {
        return porcentajeBecaDerechosGrado;
    }
    
    public void setPorcentajeBecaDerechosGrado(Integer porcentajeBecaDerechosGrado) {
        this.porcentajeBecaDerechosGrado = porcentajeBecaDerechosGrado;
    }
    
    public String getDescripcionBeneficio() {
        return descripcionBeneficio;
    }
    
    public void setDescripcionBeneficio(String descripcionBeneficio) {
        this.descripcionBeneficio = descripcionBeneficio;
    }
    
    // Builder estático
    public static ResultadoBuilder builder() {
        return new ResultadoBuilder();
    }
    
    public static class ResultadoBuilder {
        private ResultadoSaberPro resultado = new ResultadoSaberPro();
        
        public ResultadoBuilder alumno(Alumno alumno) {
            resultado.alumno = alumno;
            return this;
        }
        
        public ResultadoBuilder puntajeSaberPro(Integer puntaje) {
            resultado.puntajeSaberPro = puntaje;
            return this;
        }
        
        public ResultadoBuilder nivelSaberPro(String nivel) {
            resultado.nivelSaberPro = nivel;
            return this;
        }
        
        public ResultadoBuilder puntajeComunicacionEscrita(Integer puntaje) {
            resultado.puntajeComunicacionEscrita = puntaje;
            return this;
        }
        
        public ResultadoBuilder nivelComunicacionEscrita(String nivel) {
            resultado.nivelComunicacionEscrita = nivel;
            return this;
        }
        
        public ResultadoBuilder puntajeRazonamientoCuantitativo(Integer puntaje) {
            resultado.puntajeRazonamientoCuantitativo = puntaje;
            return this;
        }
        
        public ResultadoBuilder nivelRazonamientoCuantitativo(String nivel) {
            resultado.nivelRazonamientoCuantitativo = nivel;
            return this;
        }
        
        public ResultadoBuilder puntajeLecturaCritica(Integer puntaje) {
            resultado.puntajeLecturaCritica = puntaje;
            return this;
        }
        
        public ResultadoBuilder nivelLecturaCritica(String nivel) {
            resultado.nivelLecturaCritica = nivel;
            return this;
        }
        
        public ResultadoBuilder puntajeCompetenciasCiudadanas(Integer puntaje) {
            resultado.puntajeCompetenciasCiudadanas = puntaje;
            return this;
        }
        
        public ResultadoBuilder nivelCompetenciasCiudadanas(String nivel) {
            resultado.nivelCompetenciasCiudadanas = nivel;
            return this;
        }
        
        public ResultadoBuilder puntajeIngles(Integer puntaje) {
            resultado.puntajeIngles = puntaje;
            return this;
        }
        
        public ResultadoBuilder nivelIngles(String nivel) {
            resultado.nivelIngles = nivel;
            return this;
        }
        
        public ResultadoBuilder nivelInglesCEFR(String nivel) {
            resultado.nivelInglesCEFR = nivel;
            return this;
        }
        
        public ResultadoBuilder puntajeFormulacionProyectos(Integer puntaje) {
            resultado.puntajeFormulacionProyectos = puntaje;
            return this;
        }
        
        public ResultadoBuilder nivelFormulacionProyectos(String nivel) {
            resultado.nivelFormulacionProyectos = nivel;
            return this;
        }
        
        public ResultadoBuilder puntajePensamientoCientifico(Integer puntaje) {
            resultado.puntajePensamientoCientifico = puntaje;
            return this;
        }
        
        public ResultadoBuilder nivelPensamientoCientifico(String nivel) {
            resultado.nivelPensamientoCientifico = nivel;
            return this;
        }
        
        public ResultadoBuilder puntajeDisenoSoftware(Integer puntaje) {
            resultado.puntajeDisenoSoftware = puntaje;
            return this;
        }
        
        public ResultadoBuilder nivelDisenoSoftware(String nivel) {
            resultado.nivelDisenoSoftware = nivel;
            return this;
        }
        
        public ResultadoBuilder estadoExamen(EstadoExamen estado) {
            resultado.estadoExamen = estado;
            return this;
        }
        
        public ResultadoSaberPro build() {
            return resultado;
        }
    }
}