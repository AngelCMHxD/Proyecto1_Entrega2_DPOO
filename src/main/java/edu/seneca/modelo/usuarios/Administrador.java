package edu.seneca.modelo.usuarios;

import edu.seneca.modelo.ventas.ArticuloTienda;
import edu.seneca.modelo.ventas.LineaVenta;
import edu.seneca.modelo.ventas.Producto;
import edu.seneca.modelo.ventas.ProductoCafeteria;
import edu.seneca.modelo.ventas.Venta;

import java.time.LocalDate;
import java.util.List;

public class Administrador extends Empleado {

    private static final long serialVersionUID = 1L;

    public Administrador(String id, String nombre, String login, String password) {
        super(id, nombre, login, password, "Administrador");
    }

    public void generarReporteVentas(LocalDate fechaInicio, LocalDate fechaFin, List<Venta> ventas) {
        int cantidadVentas = 0;
        double totalTienda = 0;
        double totalCafeteria = 0;
        double totalGeneral = 0;

        for (Venta venta : ventas) {
            LocalDate fecha = venta.getFecha();
            boolean dentroDelPeriodo = !fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin);
            if (dentroDelPeriodo) {
                cantidadVentas++;
                totalGeneral += venta.getTotal();
                for (LineaVenta linea : venta.getLineas()) {
                    if (linea.getProducto() instanceof ArticuloTienda) {
                        totalTienda += linea.getSubtotal();
                    } else if (linea.getProducto() instanceof ProductoCafeteria) {
                        totalCafeteria += linea.getSubtotal();
                    }
                }
            }
        }

        System.out.println("Reporte de ventas del " + fechaInicio + " al " + fechaFin);
        System.out.println("\tVentas registradas: " + cantidadVentas);
        System.out.println("\tTienda (antes de descuentos): " + totalTienda);
        System.out.println("\tCafeteria (antes de descuentos): " + totalCafeteria);
        System.out.println("\tTotal cobrado: " + totalGeneral);
    }

    public void reabastecerInventario(String productoId, int cantidad, List<Producto> productos) throws Exception {
        for (Producto producto : productos) {
            if (producto.getId().equals(productoId)) {
                producto.aumentarStock(cantidad);
                return;
            }
        }
        throw new Exception("No existe el producto " + productoId);
    }
}
