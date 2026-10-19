package edu.seneca.modelo.ventas;

public class Bebida extends ProductoCafeteria {

    private static final long serialVersionUID = 1L;

    private boolean esCaliente;

    public Bebida(String id, String nombre, double precioBase, int stock, boolean esCaliente) {
        super(id, nombre, precioBase, stock);
        this.esCaliente = esCaliente;
    }

    public boolean esBebidaCaliente() {
        return esCaliente;
    }
}
