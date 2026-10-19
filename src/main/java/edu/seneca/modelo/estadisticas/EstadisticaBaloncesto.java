package edu.seneca.modelo.estadisticas;

public class EstadisticaBaloncesto extends EstadisticaPartido {

    private static final long serialVersionUID = 1L;

    private int puntos;
    private int rebotes;

    public EstadisticaBaloncesto(String idSocio, int puntos, int rebotes) {
        super(idSocio);
        this.puntos = puntos;
        this.rebotes = rebotes;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getRebotes() {
        return rebotes;
    }
}
