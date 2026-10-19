package edu.seneca;

import edu.seneca.modelo.deportes.Competencia;
import edu.seneca.modelo.deportes.Equipo;
import edu.seneca.modelo.deportes.InscripcionEquipo;
import edu.seneca.modelo.deportes.Partido;
import edu.seneca.modelo.deportes.PartidoCompetencia;
import edu.seneca.modelo.deportes.PartidoInterno;
import edu.seneca.modelo.deportes.PartidoPractica;
import edu.seneca.modelo.estadisticas.EstadisticaBaloncesto;
import edu.seneca.modelo.estadisticas.EstadisticaFutbol;
import edu.seneca.modelo.estadisticas.EstadisticaIndividual;
import edu.seneca.modelo.estadisticas.EstadisticaPartido;
import edu.seneca.modelo.estadisticas.EstadisticaVoleibol;
import edu.seneca.modelo.instalaciones.Instalacion;
import edu.seneca.modelo.instalaciones.ReservaInstalacion;
import edu.seneca.modelo.usuarios.Empleado;
import edu.seneca.modelo.usuarios.Sancion;
import edu.seneca.modelo.usuarios.Socio;
import edu.seneca.modelo.usuarios.TipoTarjeta;
import edu.seneca.modelo.usuarios.Usuario;
import edu.seneca.modelo.ventas.Bebida;
import edu.seneca.modelo.ventas.LineaVenta;
import edu.seneca.modelo.ventas.Producto;
import edu.seneca.modelo.ventas.TipoUbicacion;
import edu.seneca.modelo.ventas.Venta;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClubDeportivoSeneca implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final double DESCUENTO_EMPLEADO = 0.15;

    private String nombre;
    private List<Usuario> usuarios;
    private List<Producto> productos;
    private List<Instalacion> instalaciones;
    private List<ReservaInstalacion> reservas;
    private List<Equipo> equipos;
    private List<Competencia> competencias;
    private List<Partido> partidos;
    private List<Venta> ventas;

    public ClubDeportivoSeneca(String nombre) {
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.instalaciones = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.equipos = new ArrayList<>();
        this.competencias = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }

    public boolean procesarReserva(String idSocio, String idInstalacion, LocalDate fecha, LocalTime hora, String idOponente) throws Exception {
        Socio socio = buscarSocio(idSocio);
        Instalacion instalacion = buscarInstalacion(idInstalacion);

        int jugadores = 1;
        if (idOponente != null) {
            Socio oponente = buscarSocio(idOponente);
            if (!socio.esNivelCompatible(oponente)) {
                return false;
            }
            jugadores = 2;
        }

        if (!instalacion.verificarDisponibilidad(fecha, hora)) {
            return false;
        }

        ReservaInstalacion nuevaReserva = new ReservaInstalacion(fecha, hora, jugadores);
        nuevaReserva.confirmar();
        socio.agregarReserva(nuevaReserva);
        instalacion.agregarReserva(nuevaReserva);
        reservas.add(nuevaReserva);
        return true;
    }

    public boolean procesarVenta(String idUsuario, List<String> carrito, String metodoPago, TipoUbicacion ubicacion) throws Exception {
        Usuario usuario = buscarUsuario(idUsuario);

        Venta nuevaVenta = new Venta(LocalDate.now(), idUsuario);
        for (String idProducto : carrito) {
            Producto producto = buscarProducto(idProducto);
            if (esBebidaCalienteProhibida(producto, ubicacion)) {
                return false;
            }
            try {
                producto.reducirStock(1);
            } catch (Exception e) {
                return false;
            }
            double subtotal = producto.getPrecioBase() + producto.getImpuesto();
            nuevaVenta.agregarLineaVenta(new LineaVenta(producto, 1, subtotal));
        }

        double descuento = 0;
        if (usuario instanceof Empleado) {
            descuento = DESCUENTO_EMPLEADO;
        }
        nuevaVenta.calcularTotalFinal(descuento);

        if (usuario instanceof Socio) {
            Socio socio = (Socio) usuario;
            socio.sumarPuntosFidelidad(nuevaVenta.calcularPuntosGenerados());
        }
        ventas.add(nuevaVenta);
        return true;
    }

    public void registrarEstadistica(String idPartido, String idSocio, Map<String, Integer> estadisticas) throws Exception {
        Partido partido = buscarPartido(idPartido);
        Socio socio = buscarSocio(idSocio);

        if (partido instanceof PartidoPractica) {
            PartidoPractica practica = (PartidoPractica) partido;
            int sets = valor(estadisticas, "setsGanados");
            int games = valor(estadisticas, "gamesGanados");
            int puntos = valor(estadisticas, "puntosTotales");
            practica.agregarEstadistica(new EstadisticaIndividual(idSocio, sets, games, puntos));
            return;
        }

        EstadisticaPartido estadistica;
        String deporte = partido.getDeporte();
        if (deporte.equals("Futbol")) {
            int goles = valor(estadisticas, "goles");
            int amarillas = valor(estadisticas, "amarillas");
            int rojas = valor(estadisticas, "rojas");
            estadistica = new EstadisticaFutbol(idSocio, goles, amarillas, rojas);
            evaluarReglasSancion(socio, amarillas, rojas);
        } else if (deporte.equals("Baloncesto")) {
            int puntos = valor(estadisticas, "puntos");
            int rebotes = valor(estadisticas, "rebotes");
            estadistica = new EstadisticaBaloncesto(idSocio, puntos, rebotes);
        } else if (deporte.equals("Voleibol")) {
            int aces = valor(estadisticas, "aces");
            int bloqueos = valor(estadisticas, "bloqueos");
            estadistica = new EstadisticaVoleibol(idSocio, aces, bloqueos);
        } else {
            throw new Exception("Deporte sin estadisticas: " + deporte);
        }

        if (partido instanceof PartidoInterno) {
            PartidoInterno interno = (PartidoInterno) partido;
            interno.agregarEstadistica(estadistica);
        } else if (partido instanceof PartidoCompetencia) {
            PartidoCompetencia competencia = (PartidoCompetencia) partido;
            competencia.agregarEstadistica(estadistica);
        }
    }

    public void evaluarReglasSancion(Socio socio, int amarillas, int rojas) {
        for (int i = 0; i < amarillas; i++) {
            socio.agregarSancion(new Sancion(TipoTarjeta.AMARILLA));
        }
        for (int i = 0; i < rojas; i++) {
            socio.agregarSancion(new Sancion(TipoTarjeta.ROJA));
        }

        int amarillasActivas = 0;
        boolean tieneRojaActiva = false;
        for (Sancion sancion : socio.getSanciones()) {
            if (sancion.isActiva() && sancion.getTipo() == TipoTarjeta.AMARILLA) {
                amarillasActivas++;
            }
            if (sancion.isActiva() && sancion.getTipo() == TipoTarjeta.ROJA) {
                tieneRojaActiva = true;
            }
        }
        if (amarillasActivas >= 2 || tieneRojaActiva) {
            socio.bloquearInscripcionProximoPartido();
        }
    }

    public boolean procesarInscripcion(String idSocio, String idEquipo, String posicion, int numCamiseta) throws Exception {
        Socio socio = buscarSocio(idSocio);
        Equipo equipo = buscarEquipo(idEquipo);

        if (!equipo.verificarCategoria(socio.getEdad())) {
            return false;
        }
        if (socio.tieneEquipoEnDeporte(equipo.getDeporte())) {
            return false;
        }

        InscripcionEquipo nuevaInscripcion;
        try {
            nuevaInscripcion = equipo.crearInscripcion(socio, posicion, numCamiseta);
        } catch (Exception e) {
            return false;
        }
        socio.vincularInscripcion(nuevaInscripcion);
        return true;
    }

    public Usuario buscarUsuario(String id) throws Exception {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        throw new Exception("no existe el usuario " + id);
    }

    public Socio buscarSocio(String id) throws Exception {
        Usuario usuario = buscarUsuario(id);
        if (usuario instanceof Socio) {
            return (Socio) usuario;
        }
        throw new Exception("el usuario " + id + " no es un socio");
    }

    public Instalacion buscarInstalacion(String id) throws Exception {
        for (Instalacion instalacion : instalaciones) {
            if (instalacion.getId().equals(id)) {
                return instalacion;
            }
        }
        throw new Exception("No existe la instalacion " + id);
    }

    public Producto buscarProducto(String id) throws Exception {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        throw new Exception("No existe el producto " + id);
    }

    public Equipo buscarEquipo(String id) throws Exception {
        for (Equipo equipo : equipos) {
            if (equipo.getId().equals(id)) {
                return equipo;
            }
        }
        throw new Exception("No existe el equipo " + id);
    }

    public Competencia buscarCompetencia(String nombre) throws Exception {
        for (Competencia competencia : competencias) {
            if (competencia.getNombre().equals(nombre)) {
                return competencia;
            }
        }
        throw new Exception("No existe la competencia " + nombre);
    }

    public Partido buscarPartido(String id) throws Exception {
        for (Partido partido : partidos) {
            if (partido.getId().equals(id)) {
                return partido;
            }
        }
        throw new Exception("No existe el partido " + id);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void agregarInstalacion(Instalacion instalacion) {
        instalaciones.add(instalacion);
    }

    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    public void agregarCompetencia(Competencia competencia) {
        competencias.add(competencia);
    }

    public void agregarPartido(Partido partido) {
        partidos.add(partido);
    }

    private boolean esBebidaCalienteProhibida(Producto producto, TipoUbicacion ubicacion) {
        if (!(producto instanceof Bebida)) {
            return false;
        }
        Bebida bebida = (Bebida) producto;
        if (!bebida.esBebidaCaliente()) {
            return false;
        }
        return ubicacion == TipoUbicacion.CANCHA_PADEL || ubicacion == TipoUbicacion.MESA_TECHADA;
    }

    private int valor(Map<String, Integer> estadisticas, String clave) {
        if (estadisticas.containsKey(clave)) {
            return estadisticas.get(clave);
        }
        return 0;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<ReservaInstalacion> getReservas() {
        return reservas;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public List<Venta> getVentas() {
        return ventas;
    }
}
