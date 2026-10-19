package edu.seneca.modelo.estadisticas;

import java.io.Serializable;

public abstract class EstadisticaPartido implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String idSocio;

    protected EstadisticaPartido(String idSocio) {
        this.idSocio = idSocio;
    }

    public String getIdSocio() {
        return idSocio;
    }
}
