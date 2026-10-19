package edu.seneca.modelo.usuarios;

import java.io.Serializable;
import java.time.LocalTime;

public class Turno implements Serializable {

    private static final long serialVersionUID = 1L;

    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Turno(String diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public boolean esTurnoValido() {
        return horaInicio.isBefore(horaFin);
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }
}
