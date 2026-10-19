package edu.seneca.modelo.usuarios;

import java.io.Serializable;

public abstract class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String id;
    protected String nombre;
    protected String login;
    protected String passwordHash;

    protected Usuario(String id, String nombre, String login, String password) {
        this.id = id;
        this.nombre = nombre;
        this.login = login;
        this.passwordHash = String.valueOf(password.hashCode());
    }

    public boolean autenticar(String pass) {
        return passwordHash.equals(String.valueOf(pass.hashCode()));
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLogin() {
        return login;
    }
}
