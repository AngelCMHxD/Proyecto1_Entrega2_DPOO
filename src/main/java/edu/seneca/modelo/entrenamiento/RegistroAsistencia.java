package edu.seneca.modelo.entrenamiento;

import edu.seneca.modelo.usuarios.Socio;

import java.io.Serializable;

public class RegistroAsistencia implements Serializable {

    private static final long serialVersionUID = 1L;

    private Socio socio;
    private String estado;
    private int evaluacion;
    private String comentario;

    public RegistroAsistencia(Socio socio, String estado, int evaluacion, String comentario) {
        this.socio = socio;
        this.estado = estado;
        this.evaluacion = evaluacion;
        this.comentario = comentario;
    }

    public Socio getSocio() {
        return socio;
    }

    public String getEstado() {
        return estado;
    }

    public int getEvaluacion() {
        return evaluacion;
    }
}
