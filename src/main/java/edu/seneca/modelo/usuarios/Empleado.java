package edu.seneca.modelo.usuarios;

import java.util.ArrayList;
import java.util.List;

public abstract class Empleado extends Usuario {

    private static final long serialVersionUID = 1L;

    protected String cargo;
    protected List<Turno> turnos;

    protected Empleado(String id, String nombre, String login, String password, String cargo) {
        super(id, nombre, login, password);
        this.cargo = cargo;
        this.turnos = new ArrayList<>();
    }

    public void agregarTurno(Turno turno) {
        turnos.add(turno);
    }

    public String getCargo() {
        return cargo;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }
}
