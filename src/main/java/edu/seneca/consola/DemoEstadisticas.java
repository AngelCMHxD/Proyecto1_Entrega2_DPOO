package edu.seneca.consola;

import edu.seneca.ClubDeportivoSeneca;
import edu.seneca.modelo.deportes.PartidoCompetencia;
import edu.seneca.modelo.deportes.PartidoInterno;
import edu.seneca.modelo.deportes.PartidoPractica;
import edu.seneca.modelo.estadisticas.EstadisticaBaloncesto;
import edu.seneca.modelo.estadisticas.EstadisticaFutbol;
import edu.seneca.modelo.estadisticas.EstadisticaIndividual;
import edu.seneca.modelo.estadisticas.EstadisticaPartido;
import edu.seneca.modelo.usuarios.Sancion;
import edu.seneca.modelo.usuarios.Socio;

import java.util.HashMap;
import java.util.Map;

public class DemoEstadisticas {

    public static void ejecutar(ClubDeportivoSeneca club) throws Exception {
        System.out.println();
        System.out.println("=== Registro estadisticas y sanciones");

        System.out.println("Antes:");
        mostrarSocio(club.buscarSocio("S2"));
        mostrarSocio(club.buscarSocio("S4"));

        System.out.println();
        System.out.println("Caso: en el partido de futbol PI-1, S2 recibe su segunda amarilla");
        Map<String, Integer> datos = new HashMap<>();
        datos.put("goles", 0);
        datos.put("amarillas", 1);
        club.registrarEstadistica("PI-1", "S2", datos);
        mostrarSocio(club.buscarSocio("S2"));

        System.out.println();
        System.out.println("Caso: en el mismo partido, S4 recibe una roja");
        datos = new HashMap<>();
        datos.put("rojas", 1);
        club.registrarEstadistica("PI-1", "S4", datos);
        mostrarSocio(club.buscarSocio("S4"));

        System.out.println();
        System.out.println("Caso: en el partido de baloncesto PC-1, S4 hace 12 puntos y 3 rebotes");
        datos = new HashMap<>();
        datos.put("puntos", 12);
        datos.put("rebotes", 3);
        club.registrarEstadistica("PC-1", "S4", datos);
        PartidoCompetencia baloncesto = (PartidoCompetencia) club.buscarPartido("PC-1");
        for (EstadisticaPartido estadistica : baloncesto.getEstadisticas()) {
            EstadisticaBaloncesto stats = (EstadisticaBaloncesto) estadistica;
            System.out.println("\t" + stats.getIdSocio() + ": " + stats.getPuntos() + " puntos, " + stats.getRebotes() + " rebotes");
        }

        System.out.println();
        System.out.println("Caso: el partido de practica PP-1 admite maximo 4 jugadores");
        PartidoPractica practica = (PartidoPractica) club.buscarPartido("PP-1");
        System.out.println("Jugadores con estadisticas: " + practica.getEstadisticas().size());
        datos = new HashMap<>();
        datos.put("setsGanados", 1);
        datos.put("gamesGanados", 6);
        try {
            club.registrarEstadistica("PP-1", "S3", datos);
            System.out.println("Se agrego S3. Jugadores: " + practica.getEstadisticas().size());
            club.registrarEstadistica("PP-1", "S4", datos);
            System.out.println("Se agrego S4. Jugadores: " + practica.getEstadisticas().size());
            club.registrarEstadistica("PP-1", "S1", datos);
            System.out.println("Se agrego S1. Jugadores: " + practica.getEstadisticas().size());
        } catch (Exception e) {
            System.out.println("No se pudo agregar: " + e.getMessage());
        }
        for (EstadisticaIndividual stats : practica.getEstadisticas()) {
            System.out.println("\t" + stats.getIdSocio() + ": " + stats.getSetsGanados() + " sets, " + stats.getGamesGanados() + " games");
        }

        System.out.println();
        System.out.println("Estadisticas del partido de futbol PI-1 despues:");
        PartidoInterno futbol = (PartidoInterno) club.buscarPartido("PI-1");
        for (EstadisticaPartido estadistica : futbol.getEstadisticas()) {
            EstadisticaFutbol stats = (EstadisticaFutbol) estadistica;
            System.out.println("\t" + stats.getIdSocio() + ": " + stats.getGoles() + " goles, " + stats.getAmarillas() + " amarillas, " + stats.getRojas() + " rojas");
        }
    }

    private static void mostrarSocio(Socio socio) {
        String tarjetas = "";
        for (Sancion sancion : socio.getSanciones()) {
            tarjetas = tarjetas + sancion.getTipo() + " ";
        }
        if (tarjetas.isEmpty()) {
            tarjetas = "ninguna";
        }
        System.out.println("\t" + socio.getNombre() + " - tarjetas: " + tarjetas + " - bloqueado para el proximo partido: " + socio.isBloqueadoProximoPartido());
    }
}
