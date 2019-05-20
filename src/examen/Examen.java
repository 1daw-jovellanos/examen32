package examen;

import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Examen {

    // Ejercicio2 
    public double calcularCosteTotalMateriales(String filename) {
        // Tu código aquí
        return 0; // Cambia esto;
    }

    // Ejercicio 3
    public void copiarEnMayusculas(String nombreFicheroEntrada, String nombreFicheroSalida)
            throws IOException {
        // tu codigo aquí
    }

    // Ejercicio 4
    public void imprimirGrandes(int radioMinimo) {
        final String URL = "jdbc:h2:tcp://localhost/./sistemasolar";
        final String USER = "sa";
        final String PASS = "sa";
    }

    // -----------------------------------------------------
    // Usa este run para tus pruebas
    public void run(){

        System.out.format("Coste de caseta: %d%n", calcularCosteTotalMateriales("caseta.csv"));
        try {
            copiarEnMayusculas("poema.txt", "poema-mays.txt");
        } catch (IOException ex) {
            System.err.println("copiarEnMayúsculas generó una excepción.");
            ex.printStackTrace();
        }
        imprimirGrandes(20000);
    }

    public static void main(String[] args) {
        new Examen().run();
    }

}
