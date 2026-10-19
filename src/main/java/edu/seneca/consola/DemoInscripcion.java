package edu.seneca.consola;

import edu.seneca.ClubDeportivoSeneca;
import edu.seneca.modelo.deportes.Competencia;
import edu.seneca.modelo.deportes.Equipo;
import edu.seneca.modelo.deportes.InscripcionEquipo;

public class DemoInscripcion {

    public static void ejecutar(ClubDeportivoSeneca club) throws Exception {
        System.out.println();
        System.out.println("=== Inscripcion equipos y competencias");

        System.out.println("Equipos antes:");
        mostrarEquipos(club);

        System.out.println();
        System.out.println("Caso: S3 (22 anos) se inscribe en voleibol mayores");
        boolean ok = club.procesarInscripcion("S3", "VOL-MAY", "Libero", 7);
        if (ok) {
            System.out.println("Inscripcion exitosa");
        } else {
            System.out.println("Inscripcion rechazada");
        }

        System.out.println();
        System.out.println("Caso: S5 (25 anos) quiere entrar al equipo Sub-15");
        ok = club.procesarInscripcion("S5", "FUT-SUB15", "Defensa", 4);
        if (ok) {
            System.out.println("Inscripcion exitosa");
        } else {
            System.out.println("Inscripcion rechazada: no tiene la edad de la categoria");
        }

        System.out.println();
        System.out.println("Caso: S1 ya juega futbol Sub-15 y quiere entrar a futbol mayores");
        ok = club.procesarInscripcion("S1", "FUT-MAY", "Defensa", 14);
        if (ok) {
            System.out.println("Inscripcion exitosa");
        } else {
            System.out.println("Inscripcion rechazada: ya tiene un equipo de futbol");
        }

        System.out.println();
        System.out.println("Caso: S4 quiere la camiseta 5 en baloncesto, pero ya la tiene S2");
        ok = club.procesarInscripcion("S4", "BAL-MAY", "Escolta", 5);
        if (ok) {
            System.out.println("Inscripcion exitosa");
        } else {
            System.out.println("Inscripcion rechazada: el numero ya esta ocupado");
        }

        System.out.println();
        System.out.println("Caso: S4 pide la camiseta 11 en baloncesto");
        ok = club.procesarInscripcion("S4", "BAL-MAY", "Escolta", 11);
        if (ok) {
            System.out.println("Inscripcion exitosa");
        } else {
            System.out.println("Inscripcion rechazada");
        }

        System.out.println();
        System.out.println("Caso: inscribir el equipo Sub-15 en el Torneo de los Cerros (categoria Mayores)");
        Competencia cerros = club.buscarCompetencia("Torneo de los Cerros");
        ok = cerros.inscribirEquipo(club.buscarEquipo("FUT-SUB15"));
        if (ok) {
            System.out.println("Equipo inscrito");
        } else {
            System.out.println("Equipo rechazado: no es de la categoria del torneo");
        }

        System.out.println();
        System.out.println("Equipos despues:");
        mostrarEquipos(club);
    }

    private static void mostrarEquipos(ClubDeportivoSeneca club) {
        for (Equipo equipo : club.getEquipos()) {
            System.out.println("\t" + equipo.getId() + " (" + equipo.getInscripciones().size() + " de " + equipo.getCupoMaximo() + " cupos)");
            for (InscripcionEquipo inscripcion : equipo.getInscripciones()) {
                System.out.println("\t\t#" + inscripcion.getNumeroCamiseta() + " " + inscripcion.getSocio().getNombre() + " - " + inscripcion.getPosicion());
            }
        }
    }
}
