package edu.seneca.modelo.usuarios;

import edu.seneca.modelo.deportes.InscripcionEquipo;
import edu.seneca.modelo.instalaciones.ReservaInstalacion;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Socio extends Usuario {

    private static final long serialVersionUID = 1L;

    private NivelDeportivo nivel;
    private Map<String, Integer> rankingsElo;
    private int puntosFidelidad;
    private LocalDate fechaNacimiento;
    private List<Sancion> sanciones;
    private boolean bloqueadoProximoPartido;
    private List<ReservaInstalacion> reservas;
    private List<InscripcionEquipo> inscripciones;

    public Socio(String id, String nombre, String login, String password, NivelDeportivo nivel, LocalDate fechaNacimiento) {
        super(id, nombre, login, password);
        this.nivel = nivel;
        this.fechaNacimiento = fechaNacimiento;
        this.rankingsElo = new HashMap<>();
        this.puntosFidelidad = 0;
        this.sanciones = new ArrayList<>();
        this.bloqueadoProximoPartido = false;
        this.reservas = new ArrayList<>();
        this.inscripciones = new ArrayList<>();
    }

    public boolean esNivelCompatible(Socio otroSocio) {
        if (nivel == NivelDeportivo.PRINCIPIANTE && otroSocio.getNivel() == NivelDeportivo.AVANZADO) {
            return false;
        }
        if (nivel == NivelDeportivo.AVANZADO && otroSocio.getNivel() == NivelDeportivo.PRINCIPIANTE) {
            return false;
        }
        return true;
    }

    public void sumarPuntosFidelidad(int puntos) {
        puntosFidelidad += puntos;
    }

    public boolean tieneEquipoEnDeporte(String deporte) {
        for (InscripcionEquipo inscripcion : inscripciones) {
            if (inscripcion.getEquipo().getDeporte().equals(deporte)) {
                return true;
            }
        }
        return false;
    }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public void agregarSancion(Sancion sancion) {
        sanciones.add(sancion);
    }

    public void bloquearInscripcionProximoPartido() {
        bloqueadoProximoPartido = true;
    }

    public void vincularInscripcion(InscripcionEquipo inscripcion) {
        inscripciones.add(inscripcion);
    }

    public void agregarReserva(ReservaInstalacion reserva) {
        reservas.add(reserva);
    }

    public void actualizarRanking(String disciplina, int puntos) {
        int rankingActual = 0;
        if (rankingsElo.containsKey(disciplina)) {
            rankingActual = rankingsElo.get(disciplina);
        }
        rankingsElo.put(disciplina, rankingActual + puntos);
    }

    public NivelDeportivo getNivel() {
        return nivel;
    }

    public int getPuntosFidelidad() {
        return puntosFidelidad;
    }

    public List<Sancion> getSanciones() {
        return sanciones;
    }

    public boolean isBloqueadoProximoPartido() {
        return bloqueadoProximoPartido;
    }
}
