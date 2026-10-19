package edu.seneca.modelo.deportes;

import edu.seneca.modelo.estadisticas.EstadisticaPartido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PartidoInterno extends Partido {

    private static final long serialVersionUID = 1L;

    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private String marcadorFinal;
    private List<EstadisticaPartido> estadisticas;

    public PartidoInterno(String id, LocalDate fecha, String deporte, Equipo equipoLocal, Equipo equipoVisitante) {
        super(id, fecha, deporte);
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.estadisticas = new ArrayList<>();
    }

    public void registrarResultado(String marcador) {
        marcadorFinal = marcador;
    }

    public void agregarEstadistica(EstadisticaPartido estadistica) {
        estadisticas.add(estadistica);
    }

    public List<EstadisticaPartido> getEstadisticas() {
        return estadisticas;
    }
}
