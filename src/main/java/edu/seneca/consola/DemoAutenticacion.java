package edu.seneca.consola;

import edu.seneca.ClubDeportivoSeneca;
import edu.seneca.modelo.usuarios.Usuario;

public class DemoAutenticacion {

    public static void ejecutar(ClubDeportivoSeneca club) throws Exception {
        System.out.println();
        System.out.println("=== Autenticacion");

        System.out.println("Usuarios registrados:");
        for (Usuario usuario : club.getUsuarios()) {
            System.out.println("\t" + usuario.getId() + " - " + usuario.getNombre() + " (login: " + usuario.getLogin() + ")");
        }

        System.out.println();
        System.out.println("Caso: Laura entra con su password correcta");
        Usuario laura = club.buscarUsuario("S1");
        if (laura.autenticar("laura123")) {
            System.out.println("Login correcto");
        } else {
            System.out.println("Login incorrecto");
        }

        System.out.println();
        System.out.println("Caso: Laura entra con una password equivocada");
        if (laura.autenticar("clave-equivocada")) {
            System.out.println("Login correcto");
        } else {
            System.out.println("Login incorrecto");
        }

        System.out.println();
        System.out.println("Caso: el administrador entra con su password correcta");
        Usuario admin = club.buscarUsuario("A1");
        if (admin.autenticar("admin123")) {
            System.out.println("Login correcto");
        } else {
            System.out.println("Login incorrecto");
        }

        System.out.println();
        System.out.println("Caso: el administrador escribe la password en mayusculas");
        if (admin.autenticar("ADMIN123")) {
            System.out.println("Login correcto");
        } else {
            System.out.println("Login incorrecto");
        }
    }
}
