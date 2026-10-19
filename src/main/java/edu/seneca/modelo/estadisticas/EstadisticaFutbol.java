package edu.seneca.modelo.estadisticas;

public class EstadisticaFutbol extends EstadisticaPartido {

    private static final long serialVersionUID = 1L;

    private int goles;
    private int amarillas;
    private int rojas;

    public EstadisticaFutbol(String idSocio, int goles, int amarillas, int rojas) {
        super(idSocio);
        this.goles = goles;
        this.amarillas = amarillas;
        this.rojas = rojas;
    }

    public int getGoles() {
        return goles;
    }

    public int getAmarillas() {
        return amarillas;
    }

    public int getRojas() {
        return rojas;
    }
}
