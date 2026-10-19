package edu.seneca.modelo.ventas;

public abstract class ProductoCafeteria extends Producto {

    private static final long serialVersionUID = 1L;

    private static final double IMPUESTO_CONSUMO = 0.08;

    protected ProductoCafeteria(String id, String nombre, double precioBase, int stock) {
        super(id, nombre, precioBase, stock, IMPUESTO_CONSUMO);
    }
}
