package edu.seneca.modelo.deportes;

import edu.seneca.modelo.estadisticas.EstadisticaPartido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PartidoCompetencia extends Partido {

    private static final long serialVersionUID = 1L;

    private Equipo equipo;
    private String rivalExterno;
    private String escenario;
    private String marcadorFinal;
    private List<EstadisticaPartido> estadisticas;

    public PartidoCompetencia(String id, LocalDate fecha, String deporte, Equipo equipo, String rivalExterno, String escenario) {
        super(id, fecha, deporte);
        this.equipo = equipo;
        this.rivalExterno = rivalExterno;
        this.escenario = escenario;
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
