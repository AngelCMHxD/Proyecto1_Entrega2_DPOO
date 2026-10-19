package edu.seneca.modelo.deportes;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Partido implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String id;
    protected LocalDate fecha;
    protected String deporte;

    protected Partido(String id, LocalDate fecha, String deporte) {
        this.id = id;
        this.fecha = fecha;
        this.deporte = deporte;
    }

    public String getId() {
        return id;
    }

    public String getDeporte() {
        return deporte;
    }
}
