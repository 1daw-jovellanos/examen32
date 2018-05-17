/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import org.junit.*;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExamenTest {

    static Examen examen;
    private static final String URL = "jdbc:h2:mem:sistemasolartest";

    public ExamenTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        examen = new Examen();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @Test(timeout = 5000)
    public void test1CalcularCosteTotalMateriales() {
        System.out.println("EMPIEZA TEST EJERICIO 1.");
        assertEquals(99.2, examen.calcularCosteTotalMateriales("caseta.csv"), 0.01);
        assertEquals(9732, examen.calcularCosteTotalMateriales("chalet.csv"), 1);
        System.out.println("--> Termina test ejercicio 3. (+2 puntos. Pendiente de revisión)");
    }

    @Test(timeout = 5000)
    public void test2ObtenerLosMasHabladores() throws Exception {
        createBinFiles(); // Crear de nuevo los ficheros
        System.out.println("EMPIEZA TEST EJERICIO 2.");
        //Segur 1: 1123, 12, 4234, 15, 5232, 19, 8763, 22
        examen.obtenerLosMasHabladores("segur1.bin", "segur1out.bin");
        assertEquals("El fichero de salida no existe tras la ejecucion del metodo", true, new File("segur1out.bin").exists());
        try (DataInputStream fi = new DataInputStream(new FileInputStream("segur1out.bin"))) {
            int[] esperado = {1123, 12, 4234, 15, 5232, 19, 8763, 22};
            int[] leido = new int[8];
            for (int i = 0; i < 8; i++) {
                leido[i] = fi.readInt();
            }
            assertArrayEquals(esperado, leido);
        }

        //Segur 2: 7, 15, 9999, 18
        examen.obtenerLosMasHabladores("segur2.bin", "segur2out.bin");
        assertEquals("El fichero de salida no existe tras la ejecucion del metodo", true, new File("segur2out.bin").exists());
        try (DataInputStream fi = new DataInputStream(new FileInputStream("segur2out.bin"))) {
            int[] esperado = {7, 15, 9999, 18};
            int[] leido = new int[4];
            for (int i = 0; i < 4; i++) {
                leido[i] = fi.readInt();
            }
            assertArrayEquals(esperado, leido);
        }
        System.out.println("--> Termina test ejercicio 2. (+2 puntos. Pendiente de revisión)");
    }

    @Test(timeout = 5000)
    public void test3RadioMedio() throws Exception {
        System.out.println("EMPIEZA TEST EJERICIO 3.");
        createDatabase(); // Crea una base de datos idéntica a la de tus pruebas
        assertEquals(44531.75, examen.radioMedio(20000, URL), 0.01);
        createDatabase(); // Crea una base de datos idéntica a la de tus pruebas
        assertEquals(31758.16, examen.radioMedio(5000, URL), 0.01);
        System.out.println("--> Termina test ejercicio 3. (+2 puntos. Pendiente de revisión)");

    }
    











































































































































































    
    // --------------------------------------- Métodos auxiliares --------------------------------------
    // Crear base de datos de pruebas y ficheros binarios de pruebas.

    private void createDatabase() throws Exception {
        String sql = "DROP TABLE IF EXISTS planeta;\n"
                + "CREATE TABLE planeta (\n"
                + "    nombre VARCHAR(15) PRIMARY KEY,\n"
                + "    radio DOUBLE\n"
                + ");\n"
                + "INSERT INTO planeta(nombre, radio) VALUES('Mercurio' , 2439);\n"
                + "INSERT INTO planeta(nombre, radio) VALUES('Venus',6051);\n"
                + "INSERT INTO planeta(nombre, radio) VALUES('Tierra',6371);\n"
                + "INSERT INTO planeta(nombre, radio) VALUES('Marte', 3389);\n"
                + "INSERT INTO planeta(nombre, radio) VALUES('Jupiter', 69911);\n"
                + "INSERT INTO planeta(nombre, radio) VALUES('Saturno',58232);\n"
                + "INSERT INTO planeta(nombre, radio) VALUES('Urano', 25362);\n"
                + "INSERT INTO planeta(nombre, radio) VALUES('Neptuno', 24622);\n"
                + "\n"
                + "";
        Class.forName("org.h2.Driver");
        try (Connection conn = DriverManager.getConnection(URL, "sa", "")) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.execute();
            }
        }
    }

    public void createBinFiles() {
        try {

            try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("segur1.bin")))) {
                Random r = new Random();
                Map<Integer, Integer> m = new HashMap<>();
                m.put(1123, 12);
                m.put(4234, 15);
                m.put(5232, 19);
                m.put(8763, 22);
                for (int i = 0; i < 10000; i++) {
                    int n;
                    if (m.containsKey(i)) {
                        n = m.get(i);
                    } else {
                        n = r.nextInt(11);
                    }
                    out.writeInt(n);
                }
            }
            try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("segur2.bin")))) {
                Random r = new Random();
                Map<Integer, Integer> m = new HashMap<>();
                m.put(7, 15);
                m.put(9999, 18);
                for (int i = 0; i < 10000; i++) {
                    int n;
                    if (m.containsKey(i)) {
                        n = m.get(i);
                    } else {
                        n = r.nextInt(11);
                    }
                    out.writeInt(n);
                }
            }
            new File("segur1out.bin").delete();
            new File("segur2out.bin").delete();
                    
        } catch (IOException ex) {
           // Logger.getLogger(ExamenTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
