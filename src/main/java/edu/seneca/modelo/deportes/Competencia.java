package edu.seneca.modelo.deportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Competencia implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String organizador;
    private String formato;
    private String deporte;
    private String categoria;
    private List<Equipo> equipos;
    private List<PartidoCompetencia> partidos;

    public Competencia(String nombre, String organizador, String formato, String deporte, String categoria) {
        this.nombre = nombre;
        this.organizador = organizador;
        this.formato = formato;
        this.deporte = deporte;
        this.categoria = categoria;
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public boolean inscribirEquipo(Equipo equipo) {
        if (!equipo.getDeporte().equals(deporte) || !equipo.getCategoria().equals(categoria) || equipos.contains(equipo)) {
            return false;
        }
        equipos.add(equipo);
        return true;
    }

    public void registrarPartido(PartidoCompetencia partido) {
        partidos.add(partido);
    }

    public String getNombre() {
        return nombre;
    }
}
