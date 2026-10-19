package edu.seneca.modelo.instalaciones;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaInstalacion implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate fecha;
    private LocalTime horaInicio;
    private int numJugadores;
    private EstadoReserva estado;

    public ReservaInstalacion(LocalDate fecha, LocalTime hora, int jugadores) {
        this.fecha = fecha;
        this.horaInicio = hora;
        this.numJugadores = jugadores;
        this.estado = EstadoReserva.CREADA;
    }


    public void confirmar() throws Exception {
        cambiarEstado(EstadoReserva.CREADA, EstadoReserva.CONFIRMADA);
    }

    public void rechazar() throws Exception {
        cambiarEstado(EstadoReserva.CREADA, EstadoReserva.RECHAZADA);
    }

    public void iniciar() throws Exception {
        cambiarEstado(EstadoReserva.CONFIRMADA, EstadoReserva.EN_CURSO);
    }

    public void cancelar() throws Exception {
        cambiarEstado(EstadoReserva.CONFIRMADA, EstadoReserva.CANCELADA);
    }

    public void penalizar() throws Exception {
        cambiarEstado(EstadoReserva.CONFIRMADA, EstadoReserva.PENALIZADA);
    }

    public void finalizar() throws Exception {
        cambiarEstado(EstadoReserva.EN_CURSO, EstadoReserva.FINALIZADA);
    }

    public boolean estaActiva() {
        return estado == EstadoReserva.CREADA || estado == EstadoReserva.CONFIRMADA || estado == EstadoReserva.EN_CURSO;
    }

    private void cambiarEstado(EstadoReserva esperado, EstadoReserva nuevo) throws Exception {
        if (estado != esperado) {
            throw new Exception("No se puede pasar de " + estado + " a " + nuevo);
        }
        estado = nuevo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public EstadoReserva getEstado() {
        return estado;
    }
}
