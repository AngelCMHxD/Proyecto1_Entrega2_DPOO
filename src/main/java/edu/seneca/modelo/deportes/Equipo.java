package edu.seneca.modelo.deportes;

import edu.seneca.modelo.usuarios.Socio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String deporte;
    private String categoria;
    private int cupoMaximo;
    private List<InscripcionEquipo> inscripciones;

    public Equipo(String id, String deporte, String categoria, int cupoMaximo) {
        this.id = id;
        this.deporte = deporte;
        this.categoria = categoria;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>();
    }

    public String getDeporte() {
        return deporte;
    }

    public boolean verificarCategoria(int edad) {
        if (categoria.equals("Sub-13")) {
            return edad < 13;
        } else if (categoria.equals("Sub-15")) {
            return edad < 15;
        } else if (categoria.equals("Sub-17")) {
            return edad < 17;
        } else {
            return true;
        }
    }

    public boolean hayCupo() {
        return inscripciones.size() < cupoMaximo;
    }

    public InscripcionEquipo crearInscripcion(Socio socio, String posicion, int numero) throws Exception {
        if (!hayCupo()) {
            throw new Exception("El equipo " + id + " no tiene cupo");
        }
        for (InscripcionEquipo inscripcion : inscripciones) {
            if (inscripcion.getNumeroCamiseta() == numero) {
                throw new Exception("El numero " + numero + " ya esta en uso en " + id);
            }
        }
        InscripcionEquipo inscripcion = new InscripcionEquipo(socio, this, posicion, numero);
        inscripciones.add(inscripcion);
        return inscripcion;
    }

    public String getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public List<InscripcionEquipo> getInscripciones() {
        return inscripciones;
    }
}
