package edu.seneca.modelo.usuarios;

import edu.seneca.modelo.entrenamiento.SesionEntrenamiento;
import edu.seneca.modelo.instalaciones.Instalacion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Entrenador extends Empleado {

    private static final long serialVersionUID = 1L;

    private List<SesionEntrenamiento> sesiones;

    public Entrenador(String id, String nombre, String login, String password) {
        super(id, nombre, login, password, "Entrenador");
        this.sesiones = new ArrayList<>();
    }

    public SesionEntrenamiento planearSesion(LocalDate fecha, LocalTime hora, int duracionMinutos, Instalacion instalacion) {
        SesionEntrenamiento sesion = new SesionEntrenamiento(fecha, hora, duracionMinutos, this, instalacion);
        sesiones.add(sesion);
        return sesion;
    }

    public List<SesionEntrenamiento> getSesiones() {
        return sesiones;
    }
}
