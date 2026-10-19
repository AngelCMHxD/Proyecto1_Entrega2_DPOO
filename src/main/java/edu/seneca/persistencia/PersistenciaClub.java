package edu.seneca.persistencia;

import edu.seneca.ClubDeportivoSeneca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistenciaClub {

    public static final String ARCHIVO = "datos/club.dat";

    public static void guardar(ClubDeportivoSeneca club) throws Exception {
        new File("datos").mkdirs();

        ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO));
        salida.writeObject(club);
        salida.close();
    }

    public static ClubDeportivoSeneca cargar() throws Exception {
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ARCHIVO));
        ClubDeportivoSeneca club = (ClubDeportivoSeneca) entrada.readObject();
        entrada.close();
        return club;
    }
}
