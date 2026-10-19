package edu.seneca.modelo.instalaciones;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Instalacion implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String disciplina;
    private int capacidadJugadores;
    private boolean disponible;
    private List<ReservaInstalacion> reservas;

    public Instalacion(String id, String disciplina, int capacidadJugadores) {
        this.id = id;
        this.disciplina = disciplina;
        this.capacidadJugadores = capacidadJugadores;
        this.disponible = true;
        this.reservas = new ArrayList<>();
    }

    public boolean verificarDisponibilidad(LocalDate fecha, LocalTime hora) {
        if (!disponible) {
            return false;
        }
        for (ReservaInstalacion reserva : reservas) {
            if (reserva.estaActiva() && reserva.getFecha().equals(fecha) && reserva.getHoraInicio().equals(hora)) {
                return false;
            }
        }
        return true;
    }

    public void agregarReserva(ReservaInstalacion reserva) {
        reservas.add(reserva);
    }

    public String getId() {
        return id;
    }

    public List<ReservaInstalacion> getReservas() {
        return reservas;
    }
}
