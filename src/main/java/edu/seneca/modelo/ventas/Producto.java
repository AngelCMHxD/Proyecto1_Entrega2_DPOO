package edu.seneca.modelo.ventas;

import java.io.Serializable;

public abstract class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String id;
    protected String nombre;
    protected double precioBase;
    protected int stock;
    protected double porcentajeImpuesto;

    protected Producto(String id, String nombre, double precioBase, int stock, double porcentajeImpuesto) {
        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.stock = stock;
        this.porcentajeImpuesto = porcentajeImpuesto;
    }

    public boolean verificarStock(int cantidad) {
        return stock >= cantidad;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public double getImpuesto() {
        return precioBase * porcentajeImpuesto;
    }

    public void reducirStock(int cantidad) throws Exception {
        if (!verificarStock(cantidad)) {
            throw new Exception("No hay suficiente stock de " + nombre);
        }
        stock -= cantidad;
    }

    public void aumentarStock(int cantidad) {
        stock += cantidad;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }
}
