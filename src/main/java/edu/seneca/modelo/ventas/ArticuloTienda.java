package edu.seneca.modelo.ventas;

public class ArticuloTienda extends Producto {

    private static final long serialVersionUID = 1L;

    private static final double IVA = 0.19;

    private String categoriaDeportiva;

    public ArticuloTienda(String id, String nombre, double precioBase, int stock, String categoriaDeportiva) {
        super(id, nombre, precioBase, stock, IVA);
        this.categoriaDeportiva = categoriaDeportiva;
    }
}
