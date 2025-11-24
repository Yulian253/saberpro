package com.example.saberpro.util;



import com.example.saberpro.model.Alumno;
import com.example.saberpro.model.ResultadoSaberPro;
import com.example.saberpro.model.Usuario;
import com.example.saberpro.repository.AlumnoRepository;
import com.example.saberpro.repository.ResultadoSaberProRepository;
import com.example.saberpro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private AlumnoRepository alumnoRepository;
    
    @Autowired
    private ResultadoSaberProRepository resultadoRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Crear usuarios de prueba si no existen
        if (usuarioRepository.count() == 0) {
            crearUsuariosPrueba();
        }
        
        // Cargar datos de los 37 alumnos
        if (alumnoRepository.count() == 0) {
            cargarDatosAlumnos();
        }
    }
    
    private void crearUsuariosPrueba() {
        // Coordinador
        Usuario coordinador = Usuario.builder()
                .username("coordinador")
                .password("123456")
                .nombreCompleto("Coordinador de Sistemas")
                .email("coordinador@uts.edu.co")
                .rol(Usuario.Rol.COORDINADOR)
                .activo(true)
                .build();
        usuarioRepository.save(coordinador);
        
        // Estudiante de prueba
        Usuario estudiante = Usuario.builder()
                .username("estudiante")
                .password("123456")
                .nombreCompleto("Estudiante de Prueba")
                .email("estudiante@uts.edu.co")
                .rol(Usuario.Rol.ESTUDIANTE)
                .activo(true)
                .build();
        usuarioRepository.save(estudiante);
        
        System.out.println("✅ Usuarios de prueba creados:");
        System.out.println("   Coordinador - Usuario: coordinador, Contraseña: 123456");
        System.out.println("   Estudiante - Usuario: estudiante, Contraseña: 123456");
    }
    
    private void cargarDatosAlumnos() {
        // Cargar los 37 alumnos con sus resultados reales
        cargarAlumno("", "BARBOSA", "", "", "", "", "", "EK20183007722", 200, "Nivel 4", 
                128, "Nivel 2", 182, "Nivel 3", 202, "Nivel 4", 206, "Nivel 4", 
                183, "Nivel 3", 185, "Nivel 3", 160, "Nivel 3", 197, "Nivel 4", "B1");
        
        cargarAlumno("", "QUINTERO", "", "", "", "", "", "EK20183140703", 165, "Nivel 3",
                125, "Nivel 1", 151, "Nivel 2", 179, "Nivel 3", 163, "Nivel 3",
                205, "Nivel 4", 182, "Nivel 3", 144, "Nivel 2", 136, "Nivel 2", "B2");
        
        cargarAlumno("", "PARRA", "", "", "", "", "", "EK20183040545", 164, "Nivel 3",
                159, "Nivel 3", 172, "Nivel 3", 182, "Nivel 3", 142, "Nivel 2",
                165, "Nivel 3", 167, "Nivel 3", 132, "Nivel 2", 148, "Nivel 2", "A2");
        
        cargarAlumno("", "ANAYA", "", "", "", "", "", "EK20183025381", 160, "Nivel 3",
                146, "Nivel 2", 199, "Nivel 4", 157, "Nivel 3", 149, "Nivel 2",
                147, "Nivel 2", 174, "Nivel 3", 127, "Nivel 2", 171, "Nivel 3", "A2");
        
        cargarAlumno("", "FLOR", "", "", "", "", "", "EK20183025335", 160, "Nivel 3",
                198, "Nivel 4", 153, "Nivel 2", 147, "Nivel 2", 157, "Nivel 3",
                146, "Nivel 2", 168, "Nivel 3", 114, "Nivel 1", 160, "Nivel 3", "A2");
        
        cargarAlumno("", "GARCIA", "", "", "", "", "", "EK20183122648", 157, "Nivel 3",
                179, "Nivel 3", 172, "Nivel 3", 158, "Nivel 3", 140, "Nivel 2",
                136, "Nivel 2", 128, "Nivel 2", 121, "Nivel 1", 142, "Nivel 2", "A1");
        
        cargarAlumno("", "MANOSALVA", "", "", "", "", "", "EK20183064605", 153, "Nivel 2",
                115, "Nivel 1", 152, "Nivel 2", 159, "Nivel 3", 172, "Nivel 3",
                165, "Nivel 3", 142, "Nivel 2", 118, "Nivel 1", 119, "Nivel 1", "A2");
        
        cargarAlumno("", "MENDOZA", "", "", "", "", "", "EK20183187351", 151, "Nivel 2",
                132, "Nivel 2", 123, "Nivel 1", 125, "Nivel 1", 169, "Nivel 3",
                204, "Nivel 4", 173, "Nivel 3", 127, "Nivel 2", 171, "Nivel 3", "B2");
        
        cargarAlumno("", "BELTRAN", "", "", "", "", "", "EK20183233820", 150, "Nivel 2",
                86, "Nivel 1", 187, "Nivel 3", 160, "Nivel 3", 171, "Nivel 3",
                148, "Nivel 2", 162, "Nivel 3", 125, "Nivel 1", 142, "Nivel 2", "A2");
        
        cargarAlumno("", "SANTAMARIA", "", "", "", "", "", "EK20183030016", 150, "Nivel 2",
                175, "Nivel 3", 149, "Nivel 2", 145, "Nivel 2", 158, "Nivel 3",
                125, "Nivel 1", 162, "Nivel 3", 76, "Nivel 1", 125, "Nivel 1", "A1");
        
        cargarAlumno("", "SANCHEZ", "", "", "", "", "", "EK20183047073", 149, "Nivel 2",
                209, "Nivel 4", 143, "Nivel 2", 117, "Nivel 1", 129, "Nivel 2",
                147, "Nivel 2", 137, "Nivel 2", 125, "Nivel 1", 136, "Nivel 2", "A2");
        
        cargarAlumno("", "ROMERO", "", "", "", "", "", "EK20183236451", 146, "Nivel 2",
                93, "Nivel 1", 183, "Nivel 3", 155, "Nivel 2", 164, "Nivel 3",
                133, "Nivel 2", 174, "Nivel 3", 130, "Nivel 2", 154, "Nivel 2", "A1");
        
        cargarAlumno("", "LUNA", "", "", "", "", "", "EK20183041714", 141, "Nivel 2",
                125, "Nivel 1", 157, "Nivel 3", 138, "Nivel 2", 135, "Nivel 2",
                152, "Nivel 2", 176, "Nivel 3", 128, "Nivel 2", 165, "Nivel 3", "A2");
        
        cargarAlumno("", "TRIANA", "", "", "", "", "", "EK20183187801", 141, "Nivel 2",
                150, "Nivel 2", 136, "Nivel 2", 145, "Nivel 2", 150, "Nivel 2",
                126, "Nivel 2", 148, "Nivel 2", 129, "Nivel 2", 131, "Nivel 2", "A1");
        
        cargarAlumno("", "SUAREZ", "", "", "", "", "", "EK20183176566", 140, "Nivel 2",
                128, "Nivel 2", 146, "Nivel 2", 146, "Nivel 2", 132, "Nivel 2",
                147, "Nivel 2", 130, "Nivel 2", 110, "Nivel 1", 125, "Nivel 1", "A2");
        
        cargarAlumno("", "GARCIA", "", "", "", "", "", "EK20183204427", 139, "Nivel 2",
                129, "Nivel 2", 138, "Nivel 2", 148, "Nivel 2", 146, "Nivel 2",
                135, "Nivel 2", 109, "Nivel 1", 107, "Nivel 1", 131, "Nivel 2", "A1");
        
        cargarAlumno("", "PINZON", "", "", "", "", "", "EK20183196280", 138, "Nivel 2",
                153, "Nivel 2", 123, "Nivel 1", 127, "Nivel 2", 147, "Nivel 2",
                140, "Nivel 2", 145, "Nivel 2", 143, "Nivel 2", 160, "Nivel 3", "A1");
        
        cargarAlumno("", "JAIMES", "", "", "", "", "", "EK20183173799", 137, "Nivel 2",
                166, "Nivel 3", 157, "Nivel 3", 124, "Nivel 1", 100, "Nivel 1",
                140, "Nivel 2", 100, "Nivel 1", 105, "Nivel 1", 113, "Nivel 1", "A1");
        
        cargarAlumno("", "NIÑO", "", "", "", "", "", "EK20183009565", 134, "Nivel 2",
                165, "Nivel 3", 137, "Nivel 2", 136, "Nivel 2", 118, "Nivel 1",
                116, "Nivel 1", 146, "Nivel 2", 122, "Nivel 1", 154, "Nivel 2", "A0");
        
        cargarAlumno("", "FABIAN", "", "", "", "", "", "EK20183117756", 133, "Nivel 2",
                139, "Nivel 2", 93, "Nivel 1", 168, "Nivel 3", 150, "Nivel 2",
                114, "Nivel 1", 102, "Nivel 1", 123, "Nivel 1", 94, "Nivel 1", "A0");
        
        cargarAlumno("", "HERNANDEZ", "", "", "", "", "", "EK20183044579", 132, "Nivel 2",
                116, "Nivel 1", 166, "Nivel 3", 136, "Nivel 2", 104, "Nivel 1",
                140, "Nivel 2", 158, "Nivel 3", 125, "Nivel 1", 154, "Nivel 2", "A1");
        
        cargarAlumno("", "LARIOS", "", "", "", "", "", "EK20183045760", 131, "Nivel 2",
                149, "Nivel 2", 123, "Nivel 1", 129, "Nivel 2", 121, "Nivel 1",
                131, "Nivel 2", 101, "Nivel 1", 102, "Nivel 1", 165, "Nivel 3", "A1");
        
        cargarAlumno("", "CALDERON", "", "", "", "", "", "EK20183034044", 130, "Nivel 2",
                127, "Nivel 2", 147, "Nivel 2", 134, "Nivel 2", 111, "Nivel 1",
                131, "Nivel 2", 65, "Nivel 1", 112, "Nivel 1", 94, "Nivel 1", "A1");
        
        cargarAlumno("", "VILLARREAL", "", "", "", "", "", "EK20183041521", 129, "Nivel 2",
                96, "Nivel 1", 162, "Nivel 3", 114, "Nivel 1", 131, "Nivel 2",
                144, "Nivel 2", 122, "Nivel 1", 112, "Nivel 1", 131, "Nivel 2", "A1");
        
        cargarAlumno("", "RESTREPO", "", "", "", "", "", "EK20183027436", 126, "Nivel 2",
                81, "Nivel 1", 134, "Nivel 2", 126, "Nivel 2", 149, "Nivel 2",
                139, "Nivel 2", 127, "Nivel 2", 136, "Nivel 2", 142, "Nivel 2", "A1");
        
        cargarAlumno("", "CACERES", "", "", "", "", "", "EK20183031592", 125, "Nivel 1",
                124, "Nivel 1", 135, "Nivel 2", 108, "Nivel 1", 92, "Nivel 1",
                165, "Nivel 3", 132, "Nivel 2", 104, "Nivel 1", 131, "Nivel 2", "A2");
        
        cargarAlumno("", "TABARES", "", "", "", "", "", "EK20183004153", 124, "Nivel 1",
                131, "Nivel 2", 131, "Nivel 2", 107, "Nivel 1", 88, "Nivel 1",
                162, "Nivel 3", 136, "Nivel 2", 112, "Nivel 1", 148, "Nivel 2", "A2");
        
        cargarAlumno("", "NARANJO", "", "", "", "", "", "EK20183030783", 122, "Nivel 1",
                166, "Nivel 3", 113, "Nivel 1", 113, "Nivel 1", 112, "Nivel 1",
                106, "Nivel 1", 135, "Nivel 2", 117, "Nivel 1", 119, "Nivel 1", "A0");
        
        cargarAlumno("", "PRADA", "", "", "", "", "", "EK20183024754", 122, "Nivel 1",
                119, "Nivel 1", 125, "Nivel 1", 137, "Nivel 2", 107, "Nivel 1",
                123, "Nivel 1", 83, "Nivel 1", 104, "Nivel 1", 119, "Nivel 1", "A1");
        
        cargarAlumno("", "VARGAS", "", "", "", "", "", "EK20183186200", 114, "Nivel 1",
                95, "Nivel 1", 120, "Nivel 1", 151, "Nivel 2", 86, "Nivel 1",
                119, "Nivel 1", 149, "Nivel 2", 103, "Nivel 1", 119, "Nivel 1", "A0");
        
        cargarAlumno("", "TORRES", "", "", "", "", "", "EK20183182410", 113, "Nivel 1",
                109, "Nivel 1", 105, "Nivel 1", 104, "Nivel 1", 103, "Nivel 1",
                142, "Nivel 2", 102, "Nivel 1", 135, "Nivel 2", 80, "Nivel 1", "A1");
        
        cargarAlumno("", "ORTIZ", "", "", "", "", "", "EK20183213735", 107, "Nivel 1",
                128, "Nivel 2", 81, "Nivel 1", 107, "Nivel 1", 102, "Nivel 1",
                119, "Nivel 1", 130, "Nivel 2", 111, "Nivel 1", 125, "Nivel 1", "A0");
        
        cargarAlumno("", "VILLAMIZAR", "", "", "", "", "", "EK20183065220", 106, "Nivel 1",
                134, "Nivel 2", 96, "Nivel 1", 92, "Nivel 1", 110, "Nivel 1",
                97, "Nivel 1", 83, "Nivel 1", 107, "Nivel 1", 119, "Nivel 1", "A0");
        
        cargarAlumno("", "RESTREPO", "", "", "", "0", "", "EK20183028123", 96, "Nivel 1",
                0, "Nivel 1", 117, "Nivel 1", 122, "Nivel 1", 105, "Nivel 1",
                137, "Nivel 2", 157, "Nivel 3", 96, "Nivel 1", 131, "Nivel 2", "A1");
        
        // Exámenes anulados
        cargarAlumnoAnulado("", "HIGUERA", "", "", "", "", "", "EK20183207870");
        cargarAlumnoAnulado("", "MATIZ", "", "", "", "", "", "EK20183144329");
        
        System.out.println("✅ Se cargaron 37 alumnos con sus resultados Saber Pro");
    }
    
    private void cargarAlumno(String primerNombre, String primerApellido, String segundoApellido,
                             String segundoNombre, String email, String telefono, String documento,
                             String registro, Integer puntaje, String nivelGeneral,
                             Integer pComunicacion, String nComunicacion,
                             Integer pRazonamiento, String nRazonamiento,
                             Integer pLectura, String nLectura,
                             Integer pCiudadanas, String nCiudadanas,
                             Integer pIngles, String nIngles,
                             Integer pFormulacion, String nFormulacion,
                             Integer pPensamiento, String nPensamiento,
                             Integer pDiseno, String nDiseno,
                             String nivelInglesCEFR) {
        
        Alumno alumno = Alumno.builder()
                .numeroDocumento(documento.isEmpty() ? registro : documento)
                .primerApellido(primerApellido)
                .segundoApellido(segundoApellido.isEmpty() ? null : segundoApellido)
                .primerNombre(primerNombre.isEmpty() ? primerApellido : primerNombre)
                .segundoNombre(segundoNombre.isEmpty() ? null : segundoNombre)
                .correoElectronico(email.isEmpty() ? null : email)
                .numeroTelefonico(telefono.isEmpty() ? null : telefono)
                .numeroRegistro(registro)
                .build();
        
        alumno = alumnoRepository.save(alumno);
        
        ResultadoSaberPro resultado = ResultadoSaberPro.builder()
                .alumno(alumno)
                .puntajeSaberPro(puntaje)
                .nivelSaberPro(nivelGeneral)
                .puntajeComunicacionEscrita(pComunicacion)
                .nivelComunicacionEscrita(nComunicacion)
                .puntajeRazonamientoCuantitativo(pRazonamiento)
                .nivelRazonamientoCuantitativo(nRazonamiento)
                .puntajeLecturaCritica(pLectura)
                .nivelLecturaCritica(nLectura)
                .puntajeCompetenciasCiudadanas(pCiudadanas)
                .nivelCompetenciasCiudadanas(nCiudadanas)
                .puntajeIngles(pIngles)
                .nivelIngles(nIngles)
                .nivelInglesCEFR(nivelInglesCEFR)
                .puntajeFormulacionProyectos(pFormulacion)
                .nivelFormulacionProyectos(nFormulacion)
                .puntajePensamientoCientifico(pPensamiento)
                .nivelPensamientoCientifico(nPensamiento)
                .puntajeDisenoSoftware(pDiseno)
                .nivelDisenoSoftware(nDiseno)
                .build();
        
        resultado.calcularBeneficios();
        resultadoRepository.save(resultado);
    }
    
    private void cargarAlumnoAnulado(String primerNombre, String primerApellido, 
                                    String segundoApellido, String segundoNombre,
                                    String email, String telefono, String documento, String registro) {
        
        Alumno alumno = Alumno.builder()
                .numeroDocumento(documento.isEmpty() ? registro : documento)
                .primerApellido(primerApellido)
                .segundoApellido(segundoApellido.isEmpty() ? null : segundoApellido)
                .primerNombre(primerNombre.isEmpty() ? primerApellido : primerNombre)
                .segundoNombre(segundoNombre.isEmpty() ? null : segundoNombre)
                .correoElectronico(email.isEmpty() ? null : email)
                .numeroTelefonico(telefono.isEmpty() ? null : telefono)
                .numeroRegistro(registro)
                .build();
        
        alumno = alumnoRepository.save(alumno);
        
        ResultadoSaberPro resultado = ResultadoSaberPro.builder()
                .alumno(alumno)
                .estadoExamen(ResultadoSaberPro.EstadoExamen.ANULADO)
                .build();
        
        resultado.calcularBeneficios();
        resultadoRepository.save(resultado);
    }
}