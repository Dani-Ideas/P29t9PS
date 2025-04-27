import Code.ArbolSintactico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        String archivoGramatica = "Gramatica.txt";
        String simboloInicial = "";

        try (BufferedReader br = new BufferedReader(new FileReader(archivoGramatica))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("->")) {
                    simboloInicial = linea.split("->")[0].trim();
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de gramática: " + e.getMessage());
            return;
        }

        if (simboloInicial.isEmpty()) {
            System.err.println("No se encontró un símbolo inicial en el archivo de gramática.");
            return;
        }

        // Paso 3: Crear instancia de ArbolSintactico
        ArbolSintactico<String> arbol = new ArbolSintactico<>(archivoGramatica, simboloInicial);

        // Paso 4: Usar el método imprimir
        arbol.imprimir();
    }
}