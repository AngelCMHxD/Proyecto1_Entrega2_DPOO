package edu.seneca.modelo.entrenamiento;

import edu.seneca.modelo.instalaciones.Instalacion;
import edu.seneca.modelo.usuarios.Entrenador;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SesionEntrenamiento implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate fecha;
    private LocalTime hora;
    private int duracionMinutos;
    private Entrenador entrenador;
    private Instalacion instalacion;
    private List<RegistroAsistencia> registros;

    public SesionEntrenamiento(LocalDate fecha, LocalTime hora, int duracionMinutos, Entrenador entrenador, Instalacion instalacion) {
        this.fecha = fecha;
        this.hora = hora;
        this.duracionMinutos = duracionMinutos;
        this.entrenador = entrenador;
        this.instalacion = instalacion;
        this.registros = new ArrayList<>();
    }

    public void registrarAsistencia(RegistroAsistencia registro) {
        registros.add(registro);
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public List<RegistroAsistencia> getRegistros() {
        return registros;
    }
}
