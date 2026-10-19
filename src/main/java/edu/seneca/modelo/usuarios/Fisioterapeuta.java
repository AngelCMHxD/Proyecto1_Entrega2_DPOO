package edu.seneca.modelo.usuarios;

import java.util.ArrayList;
import java.util.List;

public class Fisioterapeuta extends Empleado {

    private static final long serialVersionUID = 1L;

    private List<String> atenciones;

    public Fisioterapeuta(String id, String nombre, String login, String password) {
        super(id, nombre, login, password, "Fisioterapeuta");
        this.atenciones = new ArrayList<>();
    }

    public void registrarAtencion(Socio socio, String descripcion) {
        atenciones.add(socio.getId() + " - " + socio.getNombre() + ": " + descripcion);
    }

    public List<String> getAtenciones() {
        return atenciones;
    }
}
