package edu.seneca.modelo.ventas;

import java.io.Serializable;

public class LineaVenta implements Serializable {

    private static final long serialVersionUID = 1L;

    private Producto producto;
    private int cantidad;
    private double subtotal;

    public LineaVenta(Producto producto, int cantidad, double subtotal) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }
}
