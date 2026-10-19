package edu.seneca.consola;

import edu.seneca.ClubDeportivoSeneca;
import edu.seneca.modelo.instalaciones.Instalacion;
import edu.seneca.modelo.instalaciones.ReservaInstalacion;

import java.time.LocalDate;
import java.time.LocalTime;

public class DemoReserva {

    public static void ejecutar(ClubDeportivoSeneca club) throws Exception {
        System.out.println();
        System.out.println("=== Reserva de instalacion");

        Instalacion cancha = club.buscarInstalacion("TEN-2");
        LocalDate manana = LocalDate.now().plusDays(1);
        LocalTime hora = LocalTime.of(10, 0);

        System.out.println("Reservas de TEN-2 antes:");
        mostrarReservas(cancha);

        System.out.println();
        System.out.println("Caso: S2 (Avanzado) reserva TEN-2 a las " + hora + " contra S4 (Intermedio)");
        boolean ok = club.procesarReserva("S2", "TEN-2", manana, hora, "S4");
        if (ok) {
            System.out.println("Reserva exitosa");
        } else {
            System.out.println("Reserva rechazada");
        }

        System.out.println();
        System.out.println("Caso: S5 intenta reservar la misma cancha a la misma hora");
        ok = club.procesarReserva("S5", "TEN-2", manana, hora, null);
        if (ok) {
            System.out.println("Reserva exitosa");
        } else {
            System.out.println("Reserva rechazada: la cancha ya esta ocupada");
        }

        System.out.println();
        System.out.println("Caso: S2 (Avanzado) quiere jugar contra S3 (Principiante)");
        ok = club.procesarReserva("S2", "TEN-2", manana, LocalTime.of(11, 0), "S3");
        if (ok) {
            System.out.println("Reserva exitosa");
        } else {
            System.out.println("Reserva rechazada: los niveles no son compatibles");
        }

        System.out.println();
        System.out.println("Caso: estados de la reserva que hizo S2");
        ReservaInstalacion reserva = cancha.getReservas().get(cancha.getReservas().size() - 1);
        System.out.println("Estado actual: " + reserva.getEstado());
        reserva.iniciar();
        System.out.println("Empieza el partido: " + reserva.getEstado());
        reserva.finalizar();
        System.out.println("Termina el partido: " + reserva.getEstado());
        try {
            reserva.cancelar();
        } catch (Exception e) {
            System.out.println("No se puede cancelar: " + e.getMessage());
        }

        System.out.println();
        System.out.println("Reservas de TEN-2 despues:");
        mostrarReservas(cancha);
    }

    private static void mostrarReservas(Instalacion cancha) {
        if (cancha.getReservas().isEmpty()) {
            System.out.println("\t(no hay reservas)");
        }
        for (ReservaInstalacion reserva : cancha.getReservas()) {
            System.out.println("\t" + reserva.getFecha() + " " + reserva.getHoraInicio() + " - " + reserva.getNumJugadores() + " jugadores - " + reserva.getEstado());
        }
    }
}
