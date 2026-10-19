package edu.seneca.consola;

import edu.seneca.ClubDeportivoSeneca;
import edu.seneca.modelo.usuarios.Administrador;
import edu.seneca.modelo.usuarios.Empleado;
import edu.seneca.modelo.usuarios.Turno;
import edu.seneca.modelo.usuarios.Usuario;

import java.time.LocalDate;

public class DemoAdministrador {

    public static void ejecutar(ClubDeportivoSeneca club) throws Exception {
        System.out.println();
        System.out.println("=== Administrador");

        Administrador admin = (Administrador) club.buscarUsuario("A1");

        System.out.println();
        System.out.println("Caso: reporte de ventas del ultimo ano");
        LocalDate hoy = LocalDate.now();
        admin.generarReporteVentas(hoy.minusYears(1), hoy, club.getVentas());

        System.out.println();
        System.out.println("Caso: llegan 5 raquetas nuevas (P1)");
        System.out.println("Stock antes: " + club.buscarProducto("P1").getStock());
        admin.reabastecerInventario("P1", 5, club.getProductos());
        System.out.println("Stock despues: " + club.buscarProducto("P1").getStock());

        System.out.println();
        System.out.println("Caso: reabastecer un producto que no existe (P99)");
        try {
            admin.reabastecerInventario("P99", 5, club.getProductos());
        } catch (Exception e) {
            System.out.println("No se pudo: " + e.getMessage());
        }

        System.out.println();
        System.out.println("Caso: turnos de los empleados");
        for (Usuario usuario : club.getUsuarios()) {
            if (usuario instanceof Empleado) {
                Empleado empleado = (Empleado) usuario;
                System.out.println("\t" + empleado.getNombre() + " (" + empleado.getCargo() + ")");
                for (Turno turno : empleado.getTurnos()) {
                    System.out.println("\t\t" + turno.getDiaSemana() + " de " + turno.getHoraInicio() + " a " + turno.getHoraFin());
                }
            }
        }
    }
}
