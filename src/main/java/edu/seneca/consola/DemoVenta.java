package edu.seneca.consola;

import edu.seneca.ClubDeportivoSeneca;
import edu.seneca.modelo.ventas.LineaVenta;
import edu.seneca.modelo.ventas.Producto;
import edu.seneca.modelo.ventas.Snack;
import edu.seneca.modelo.ventas.TipoUbicacion;
import edu.seneca.modelo.ventas.Venta;

import java.util.ArrayList;
import java.util.List;

public class DemoVenta {

    public static void ejecutar(ClubDeportivoSeneca club) throws Exception {
        System.out.println();
        System.out.println("=== Ventas");

        System.out.println("Antes:");
        mostrarEstado(club);

        System.out.println();
        System.out.println("Caso: S1 compra 1 balon (P2) y 2 aguas (P6)");
        List<String> carrito = new ArrayList<>();
        carrito.add("P2");
        carrito.add("P6");
        carrito.add("P6");
        boolean ok = club.procesarVenta("S1", carrito, "Tarjeta", TipoUbicacion.CAFETERIA);
        if (ok) {
            System.out.println("Venta exitosa");
            mostrarUltimaVenta(club);
        } else {
            System.out.println("Venta rechazada");
        }

        System.out.println();
        System.out.println("Caso: el entrenador E1 compra 1 raqueta (P1) con 15% de descuento");
        carrito = new ArrayList<>();
        carrito.add("P1");
        ok = club.procesarVenta("E1", carrito, "Tarjeta", TipoUbicacion.CAFETERIA);
        if (ok) {
            System.out.println("Venta exitosa");
            mostrarUltimaVenta(club);
        } else {
            System.out.println("Venta rechazada");
        }

        System.out.println();
        System.out.println("Caso: S3 pide un cafe caliente (P5) dentro de la cancha de padel");
        carrito = new ArrayList<>();
        carrito.add("P5");
        ok = club.procesarVenta("S3", carrito, "Efectivo", TipoUbicacion.CANCHA_PADEL);
        if (ok) {
            System.out.println("Venta exitosa");
        } else {
            System.out.println("Venta rechazada: no se permiten bebidas calientes en la cancha de padel");
        }

        System.out.println();
        System.out.println("Caso: S4 pide un tubo de pelotas de padel (P4), que esta agotado");
        carrito = new ArrayList<>();
        carrito.add("P4");
        ok = club.procesarVenta("S4", carrito, "Tarjeta", TipoUbicacion.CAFETERIA);
        if (ok) {
            System.out.println("Venta exitosa");
        } else {
            System.out.println("Venta rechazada: no hay suficiente stock");
        }

        System.out.println();
        System.out.println("Caso: alergenos de los snacks");
        for (Producto producto : club.getProductos()) {
            if (producto instanceof Snack) {
                Snack snack = (Snack) producto;
                System.out.println("\t" + snack.getNombre() + " tiene: " + snack.getAlergenos());
                if (snack.contieneAlergeno("mani")) {
                    System.out.println("\tCuidado: " + snack.getNombre() + " contiene mani");
                }
            }
        }

        System.out.println();
        System.out.println("Despues:");
        mostrarEstado(club);
    }

    private static void mostrarUltimaVenta(ClubDeportivoSeneca club) {
        Venta venta = club.getVentas().get(club.getVentas().size() - 1);
        for (LineaVenta linea : venta.getLineas()) {
            System.out.println("\t" + linea.getCantidad() + " x " + linea.getProducto().getNombre() + " = " + linea.getSubtotal());
        }
        System.out.println("\tTotal a pagar: " + venta.getTotal());
    }

    private static void mostrarEstado(ClubDeportivoSeneca club) throws Exception {
        System.out.println("\tStock de raquetas (P1): " + club.buscarProducto("P1").getStock());
        System.out.println("\tStock de balones (P2): " + club.buscarProducto("P2").getStock());
        System.out.println("\tStock de pelotas de padel (P4): " + club.buscarProducto("P4").getStock());
        System.out.println("\tStock de cafe (P5): " + club.buscarProducto("P5").getStock());
        System.out.println("\tStock de agua (P6): " + club.buscarProducto("P6").getStock());
        System.out.println("\tPuntos de fidelidad de S1: " + club.buscarSocio("S1").getPuntosFidelidad());
        System.out.println("\tVentas registradas: " + club.getVentas().size());
    }
}
