package edu.seneca;

import edu.seneca.consola.DemoAdministrador;
import edu.seneca.consola.DemoAutenticacion;
import edu.seneca.consola.DemoEntrenamiento;
import edu.seneca.consola.DemoEstadisticas;
import edu.seneca.consola.DemoInscripcion;
import edu.seneca.consola.DemoReserva;
import edu.seneca.consola.DemoVenta;
import edu.seneca.persistencia.GeneradorDatosPrueba;
import edu.seneca.persistencia.PersistenciaClub;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!new File(PersistenciaClub.ARCHIVO).exists()) {
            try {
                GeneradorDatosPrueba.generar();
                System.out.println("Datos de prueba creados en " + PersistenciaClub.ARCHIVO);
            } catch (Exception e) {
                System.out.println("No se pudieron crear los datos: " + e.getMessage());
                return;
            }
        }

        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("Club Deportivo Seneca");
            System.out.println("1. Autenticacion");
            System.out.println("2. Reserva de instalacion");
            System.out.println("3. Ventas");
            System.out.println("4. Inscripcion equipos y competencias");
            System.out.println("5. Registro estadisticas y sanciones");
            System.out.println("6. Sesiones entrenamiento y fisioterapia");
            System.out.println("7. Administrador");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            String opcion = scanner.nextLine().trim();

            if (opcion.equals("0")) {
                salir = true;
            } else {
                try {
                    ClubDeportivoSeneca club = PersistenciaClub.cargar();
                    switch (opcion) {
                        case "1":
                            DemoAutenticacion.ejecutar(club);
                            break;
                        case "2":
                            DemoReserva.ejecutar(club);
                            break;
                        case "3":
                            DemoVenta.ejecutar(club);
                            break;
                        case "4":
                            DemoInscripcion.ejecutar(club);
                            break;
                        case "5":
                            DemoEstadisticas.ejecutar(club);
                            break;
                        case "6":
                            DemoEntrenamiento.ejecutar(club);
                            break;
                        case "7":
                            DemoAdministrador.ejecutar(club);
                            break;
                        default:
                            System.out.println("opcion no valida");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
