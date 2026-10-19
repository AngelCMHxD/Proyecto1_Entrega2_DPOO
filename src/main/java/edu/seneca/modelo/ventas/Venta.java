package edu.seneca.modelo.ventas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final double PORCENTAJE_PUNTOS = 0.02;

    private LocalDate fecha;
    private double total;
    private String idUsuario;
    private List<LineaVenta> lineas;

    public Venta(LocalDate fecha, String idUsuario) {
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.total = 0;
        this.lineas = new ArrayList<>();
    }

    public void agregarLineaVenta(LineaVenta linea) {
        lineas.add(linea);
    }

    public double calcularTotalFinal(double descuento) {
        double suma = 0;
        for (LineaVenta linea : lineas) {
            suma += linea.getSubtotal();
        }
        total = suma * (1 - descuento);
        return total;
    }

    public int calcularPuntosGenerados() {
        return (int) (total * PORCENTAJE_PUNTOS);
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    public List<LineaVenta> getLineas() {
        return lineas;
    }
}
