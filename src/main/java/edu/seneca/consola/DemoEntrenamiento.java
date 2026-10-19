package edu.seneca.consola;

import edu.seneca.ClubDeportivoSeneca;
import edu.seneca.modelo.entrenamiento.RegistroAsistencia;
import edu.seneca.modelo.entrenamiento.SesionEntrenamiento;
import edu.seneca.modelo.usuarios.Entrenador;
import edu.seneca.modelo.usuarios.Fisioterapeuta;

import java.time.LocalDate;
import java.time.LocalTime;

public class DemoEntrenamiento {

    public static void ejecutar(ClubDeportivoSeneca club) throws Exception {
        System.out.println();
        System.out.println("=== Sesiones entrenamiento y fisioterapia");

        Entrenador paula = (Entrenador) club.buscarUsuario("E2");
        System.out.println("Sesiones de Paula antes: " + paula.getSesiones().size());

        System.out.println();
        System.out.println("Caso: Paula planea una sesion de 60 minutos en la mesa MESA-1");
        LocalDate fecha = LocalDate.now().plusDays(2);
        SesionEntrenamiento sesion = paula.planearSesion(fecha, LocalTime.of(15, 0), 60, club.buscarInstalacion("MESA-1"));
        System.out.println("Sesion creada para el " + sesion.getFecha() + " a las " + sesion.getHora());

        System.out.println();
        System.out.println("Caso: al terminar, Paula registra la asistencia");
        sesion.registrarAsistencia(new RegistroAsistencia(club.buscarSocio("S1"), "Asistio", 5, "Gran avance"));
        sesion.registrarAsistencia(new RegistroAsistencia(club.buscarSocio("S3"), "Falto", 0, null));
        sesion.registrarAsistencia(new RegistroAsistencia(club.buscarSocio("S4"), "Llego tarde", 3, "Revisar saque"));
        for (RegistroAsistencia registro : sesion.getRegistros()) {
            System.out.println("\t" + registro.getSocio().getNombre() + ": " + registro.getEstado() + ", nota " + registro.getEvaluacion());
        }

        System.out.println();
        System.out.println("Caso: el fisioterapeuta Andres atiende a S5");
        Fisioterapeuta andres = (Fisioterapeuta) club.buscarUsuario("E3");
        andres.registrarAtencion(club.buscarSocio("S5"), "Molestia en el hombro derecho");
        for (String atencion : andres.getAtenciones()) {
            System.out.println("\t" + atencion);
        }

        System.out.println();
        System.out.println("Sesiones de Paula despues: " + paula.getSesiones().size());
    }
}
