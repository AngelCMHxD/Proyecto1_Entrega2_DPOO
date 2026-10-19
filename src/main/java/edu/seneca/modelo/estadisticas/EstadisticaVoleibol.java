package edu.seneca.modelo.estadisticas;

public class EstadisticaVoleibol extends EstadisticaPartido {

    private static final long serialVersionUID = 1L;

    private int aces;
    private int bloqueos;

    public EstadisticaVoleibol(String idSocio, int aces, int bloqueos) {
        super(idSocio);
        this.aces = aces;
        this.bloqueos = bloqueos;
    }
}
