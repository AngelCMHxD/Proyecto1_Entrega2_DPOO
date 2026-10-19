package edu.seneca.modelo.estadisticas;

import java.io.Serializable;

public class EstadisticaIndividual implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idSocio;
    private int setsGanados;
    private int gamesGanados;
    private int puntosTotales;

    public EstadisticaIndividual(String idSocio, int setsGanados, int gamesGanados, int puntosTotales) {
        this.idSocio = idSocio;
        this.setsGanados = setsGanados;
        this.gamesGanados = gamesGanados;
        this.puntosTotales = puntosTotales;
    }

    public String getIdSocio() {
        return idSocio;
    }

    public int getSetsGanados() {
        return setsGanados;
    }

    public int getGamesGanados() {
        return gamesGanados;
    }
}
