/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

/**
 *
 * @author (c) Raúl Granel Blasco - raul.granel@gmail.com
 */
public class UtilesApp {

    // Fichero de propiedades
    public static final String FICHERO_PRP_APP = "app.properties";

    // Puerto de Bloqueo por Defecto
    public static final String PUERTO_BLOQUEO = "54321";

    // Fichero (Por defecto) > Propiedades    
    public static Properties cargarPropiedades() {
        return cargarPropiedades(FICHERO_PRP_APP);
    }

    // Fichero > Propiedades
    public static Properties cargarPropiedades(String fichero) {
        // Propiedades
        Properties p;

        // Proceso de carga
        try (FileReader fr = new FileReader(fichero)) {
            // Crear propiedades de la BD
            p = new Properties();

            // Cargar propiedades
            p.load(fr);
        } catch (Exception e) {
            // Mensaje de error
            System.out.println(e.getLocalizedMessage());

            // No Propiedades
            p = null;
        }

        // Devuelve las propiedades
        return p;
    }

    // Propiedades > Fichero (Por defecto)
    public static boolean guardarPropiedades(Properties p) {
        return guardarPropiedades(p, FICHERO_PRP_APP);
    }

    // Propiedades > Fichero
    public static boolean guardarPropiedades(Properties p, String fichero) {
        // Semáforo Estado
        boolean procesoOK;

        // Proceso de salvaguarda de propiedades
        try (FileWriter fw = new FileWriter(fichero)) {
            // Guarda las propiedades
            p.store(fw, null);

            // Proceso OK
            procesoOK = true;
        } catch (Exception e) {
            // Mensaje de error
            System.out.println(e.getLocalizedMessage());

            // Error
            procesoOK = false;
        }

        // Devuelve Estado
        return procesoOK;
    }

    // Activa Instancia Única
    public static boolean activarInstancia(Properties p) {
        // Semaforo Estado
        boolean instanciaOK;

        try {
            // Obtener dato
            String dato = p.getProperty("puerto_bloqueo", PUERTO_BLOQUEO);

            // Puerto de bloqueo
            int puerto = Integer.parseInt(dato);

            // Abre un ServerSocket al puerto de bloqueo
            ServerSocket ss = new ServerSocket(puerto);

            // Marca Semáforo
            instanciaOK = true;
        } catch (IOException | NumberFormatException e) {
            // Marca Semáforo
            instanciaOK = false;

            // Mensaje Informativo
            System.out.println(e.getLocalizedMessage());
        }

        // Devuelve Estado
        return instanciaOK;
    }

}
