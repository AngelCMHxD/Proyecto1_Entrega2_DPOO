package edu.seneca.modelo.deportes;

import edu.seneca.modelo.estadisticas.EstadisticaIndividual;
import edu.seneca.modelo.instalaciones.ReservaInstalacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PartidoPractica extends Partido {

    private static final long serialVersionUID = 1L;

    private static final int MAX_ESTADISTICAS = 4;

    private ReservaInstalacion reserva;
    private List<EstadisticaIndividual> estadisticas;

    public PartidoPractica(String id, LocalDate fecha, String deporte, ReservaInstalacion reserva) {
        super(id, fecha, deporte);
        this.reserva = reserva;
        this.estadisticas = new ArrayList<>();
    }

    public void agregarEstadistica(EstadisticaIndividual estadistica) throws Exception {
        if (estadisticas.size() >= MAX_ESTADISTICAS) {
            throw new Exception("El partido " + id + " ya tiene " + MAX_ESTADISTICAS + " estadisticas");
        }
        estadisticas.add(estadistica);
    }

    public List<EstadisticaIndividual> getEstadisticas() {
        return estadisticas;
    }
}
