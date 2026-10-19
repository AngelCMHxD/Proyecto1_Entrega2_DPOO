package edu.seneca.persistencia;

import edu.seneca.ClubDeportivoSeneca;
import edu.seneca.modelo.deportes.Competencia;
import edu.seneca.modelo.deportes.Equipo;
import edu.seneca.modelo.deportes.PartidoCompetencia;
import edu.seneca.modelo.deportes.PartidoInterno;
import edu.seneca.modelo.deportes.PartidoPractica;
import edu.seneca.modelo.entrenamiento.RegistroAsistencia;
import edu.seneca.modelo.entrenamiento.SesionEntrenamiento;
import edu.seneca.modelo.instalaciones.Instalacion;
import edu.seneca.modelo.usuarios.Administrador;
import edu.seneca.modelo.usuarios.Entrenador;
import edu.seneca.modelo.usuarios.Fisioterapeuta;
import edu.seneca.modelo.usuarios.NivelDeportivo;
import edu.seneca.modelo.usuarios.Socio;
import edu.seneca.modelo.usuarios.Turno;
import edu.seneca.modelo.ventas.ArticuloTienda;
import edu.seneca.modelo.ventas.Bebida;
import edu.seneca.modelo.ventas.Snack;
import edu.seneca.modelo.ventas.TipoUbicacion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneradorDatosPrueba {

    public static void generar() throws Exception {
        PersistenciaClub.guardar(crearClub());
    }

    public static ClubDeportivoSeneca crearClub() throws Exception {
        ClubDeportivoSeneca club = new ClubDeportivoSeneca("Club Deportivo Seneca");
        agregarUsuarios(club);
        agregarInstalaciones(club);
        agregarProductos(club);
        agregarDeportes(club);
        agregarActividad(club);
        return club;
    }

    private static void agregarUsuarios(ClubDeportivoSeneca club) {
        club.agregarUsuario(new Socio("S1", "Laura Gomez", "laura", "laura123", NivelDeportivo.INTERMEDIO, LocalDate.of(2012, 5, 10)));
        club.agregarUsuario(new Socio("S2", "Mateo Rojas", "mateo", "mateo123", NivelDeportivo.AVANZADO, LocalDate.of(2003, 2, 21)));
        club.agregarUsuario(new Socio("S3", "Sofia Herrera", "sofia", "sofia123", NivelDeportivo.PRINCIPIANTE, LocalDate.of(2004, 9, 3)));
        club.agregarUsuario(new Socio("S4", "Daniel Castro", "daniel", "daniel123", NivelDeportivo.INTERMEDIO, LocalDate.of(2002, 11, 15)));
        club.agregarUsuario(new Socio("S5", "Valentina Ruiz", "valentina", "valentina123", NivelDeportivo.AVANZADO, LocalDate.of(2001, 7, 28)));

        Entrenador carlos = new Entrenador("E1", "Carlos Mejia", "carlos", "carlos123");
        Entrenador paula = new Entrenador("E2", "Paula Torres", "paula", "paula123");
        Fisioterapeuta andres = new Fisioterapeuta("E3", "Andres Pardo", "andres", "andres123");
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        for (String dia : dias) {
            carlos.agregarTurno(new Turno(dia, LocalTime.of(6, 0), LocalTime.of(14, 0)));
            paula.agregarTurno(new Turno(dia, LocalTime.of(12, 0), LocalTime.of(20, 0)));
            andres.agregarTurno(new Turno(dia, LocalTime.of(8, 0), LocalTime.of(16, 0)));
        }
        club.agregarUsuario(carlos);
        club.agregarUsuario(paula);
        club.agregarUsuario(andres);
        club.agregarUsuario(new Administrador("A1", "Marta Leon", "admin", "admin123"));
    }

    private static void agregarInstalaciones(ClubDeportivoSeneca club) {
        club.agregarInstalacion(new Instalacion("TEN-1", "Tenis", 4));
        club.agregarInstalacion(new Instalacion("TEN-2", "Tenis", 4));
        club.agregarInstalacion(new Instalacion("PAD-1", "Padel", 4));
        club.agregarInstalacion(new Instalacion("MESA-1", "Tenis de mesa", 4));
    }

    private static void agregarProductos(ClubDeportivoSeneca club) {
        club.agregarProducto(new ArticuloTienda("P1", "Raqueta de tenis", 250000, 8, "Tenis"));
        club.agregarProducto(new ArticuloTienda("P2", "Balon de futbol", 120000, 15, "Futbol"));
        club.agregarProducto(new ArticuloTienda("P3", "Uniforme de baloncesto", 90000, 20, "Baloncesto"));
        club.agregarProducto(new ArticuloTienda("P4", "Tubo de pelotas de padel", 35000, 0, "Padel"));
        club.agregarProducto(new Bebida("P5", "Cafe", 4000, 50, true));
        club.agregarProducto(new Bebida("P6", "Agua", 3000, 60, false));
        club.agregarProducto(new Bebida("P7", "Jugo de naranja", 6000, 25, false));
        List<String> alergenosBarra = new ArrayList<>();
        alergenosBarra.add("mani");
        club.agregarProducto(new Snack("P8", "Barra de mani", 5000, 40, alergenosBarra));

        List<String> alergenosGalletas = new ArrayList<>();
        alergenosGalletas.add("gluten");
        alergenosGalletas.add("lacteos");
        club.agregarProducto(new Snack("P9", "Galletas de avena", 4500, 40, alergenosGalletas));
    }

    private static void agregarDeportes(ClubDeportivoSeneca club) throws Exception {
        club.agregarEquipo(new Equipo("FUT-SUB15", "Futbol", "Sub-15", 22));
        club.agregarEquipo(new Equipo("FUT-MAY", "Futbol", "Mayores", 22));
        club.agregarEquipo(new Equipo("BAL-MAY", "Baloncesto", "Mayores", 12));
        club.agregarEquipo(new Equipo("VOL-MAY", "Voleibol", "Mayores", 14));

        Competencia cerros = new Competencia("Torneo de los Cerros", "Universidades de Bogota", "Liga", "Futbol", "Mayores");
        Competencia ascun = new Competencia("ASCUN Baloncesto", "ASCUN", "Grupos y eliminacion", "Baloncesto", "Mayores");
        cerros.inscribirEquipo(club.buscarEquipo("FUT-MAY"));
        ascun.inscribirEquipo(club.buscarEquipo("BAL-MAY"));
        club.agregarCompetencia(cerros);
        club.agregarCompetencia(ascun);
    }

    private static void agregarActividad(ClubDeportivoSeneca club) throws Exception {
        club.procesarInscripcion("S1", "FUT-SUB15", "Mediocampista", 8);
        club.procesarInscripcion("S2", "FUT-MAY", "Delantero", 9);
        club.procesarInscripcion("S4", "FUT-MAY", "Portero", 1);
        club.procesarInscripcion("S2", "BAL-MAY", "Base", 5);
        club.procesarInscripcion("S5", "VOL-MAY", "Armadora", 3);

        LocalDate fecha = LocalDate.of(2026, 10, 24);
        club.procesarReserva("S2", "TEN-1", fecha, LocalTime.of(9, 0), "S5");
        club.procesarReserva("S4", "PAD-1", fecha, LocalTime.of(16, 0), null);

        PartidoInterno interno = new PartidoInterno("PI-1", LocalDate.of(2026, 10, 10), "Futbol", club.buscarEquipo("FUT-MAY"), club.buscarEquipo("FUT-MAY"));
        interno.registrarResultado("2-1");
        club.agregarPartido(interno);
        Map<String, Integer> datosFutbol = new HashMap<>();
        datosFutbol.put("goles", 2);
        datosFutbol.put("amarillas", 1);
        club.registrarEstadistica("PI-1", "S2", datosFutbol);

        PartidoCompetencia partidoAscun = new PartidoCompetencia("PC-1", LocalDate.of(2026, 10, 12), "Baloncesto", club.buscarEquipo("BAL-MAY"), "Universidad Nacional", "Coliseo El Salitre");
        partidoAscun.registrarResultado("78-71");
        club.buscarCompetencia("ASCUN Baloncesto").registrarPartido(partidoAscun);
        club.agregarPartido(partidoAscun);
        Map<String, Integer> datosBaloncesto = new HashMap<>();
        datosBaloncesto.put("puntos", 21);
        datosBaloncesto.put("rebotes", 6);
        club.registrarEstadistica("PC-1", "S2", datosBaloncesto);

        PartidoPractica practica = new PartidoPractica("PP-1", fecha, "Tenis", club.getReservas().get(0));
        club.agregarPartido(practica);
        Map<String, Integer> datosMateo = new HashMap<>();
        datosMateo.put("setsGanados", 2);
        datosMateo.put("gamesGanados", 12);
        club.registrarEstadistica("PP-1", "S2", datosMateo);

        Map<String, Integer> datosValentina = new HashMap<>();
        datosValentina.put("setsGanados", 1);
        datosValentina.put("gamesGanados", 9);
        club.registrarEstadistica("PP-1", "S5", datosValentina);

        Entrenador carlos = (Entrenador) club.buscarUsuario("E1");
        SesionEntrenamiento sesion = carlos.planearSesion(LocalDate.of(2026, 10, 20), LocalTime.of(7, 0), 90, club.buscarInstalacion("TEN-2"));
        sesion.registrarAsistencia(new RegistroAsistencia(club.buscarSocio("S2"), "Asistio", 4, "Buen saque"));
        sesion.registrarAsistencia(new RegistroAsistencia(club.buscarSocio("S5"), "Llego tarde", 3, null));

        List<String> carritoLaura = new ArrayList<>();
        carritoLaura.add("P2");
        carritoLaura.add("P6");
        carritoLaura.add("P6");
        club.procesarVenta("S1", carritoLaura, "Tarjeta", TipoUbicacion.CAFETERIA);

        List<String> carritoPaula = new ArrayList<>();
        carritoPaula.add("P5");
        carritoPaula.add("P9");
        club.procesarVenta("E2", carritoPaula, "Efectivo", TipoUbicacion.CAFETERIA);
    }
}
