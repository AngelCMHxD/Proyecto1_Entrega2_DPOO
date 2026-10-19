package edu.seneca.modelo.ventas;

import java.util.List;

public class Snack extends ProductoCafeteria {

    private static final long serialVersionUID = 1L;

    private List<String> alergenos;

    public Snack(String id, String nombre, double precioBase, int stock, List<String> alergenos) {
        super(id, nombre, precioBase, stock);
        this.alergenos = alergenos;
    }

    public boolean contieneAlergeno(String alergeno) {
        for (String actual : alergenos) {
            if (actual.equalsIgnoreCase(alergeno)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getAlergenos() {
        return alergenos;
    }
}
