package edu.seneca.modelo.usuarios;

import java.io.Serializable;

public class Sancion implements Serializable {

    private static final long serialVersionUID = 1L;

    private TipoTarjeta tipo;
    private int partidosSuspension;
    private boolean activa;

    public Sancion(TipoTarjeta tipo) {
        this.tipo = tipo;
        if (tipo == TipoTarjeta.ROJA) {
            this.partidosSuspension = 1;
        } else {
            this.partidosSuspension = 0;
        }
        this.activa = true;
    }

    public TipoTarjeta getTipo() {
        return tipo;
    }

    public boolean isActiva() {
        return activa;
    }
}
