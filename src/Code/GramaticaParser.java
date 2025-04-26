package Code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// los cometarios se dejaron pora que sea más facil leer la cocumentacion
public class GramaticaParser {
    public Map<String, List<String[]>> parsear(String archivo) {
        // 1. Estructura de datos principal
        Map<String, List<String[]>> gramatica = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            // 2. Lectura línea por línea
            while ((linea = br.readLine()) != null) {

                // 3. Dividir la regla en cabeza y cuerpo
                String[] partes = linea.split("->");
                String noTerminal = partes[0].trim(); // Ej: "S"

                // 4. Dividir las alternativas de producción
                String[] alternativas = partes[1].split("\\|"); // Ej: ["AB", "a"]

                List<String[]> producciones = new ArrayList<>();

                // 5. Procesar cada alternativa
                for (String alt : alternativas) {
                    // 6. Dividir símbolos por espacios
                    String[] simbolos = alt.trim().split("\\s+");
                    producciones.add(simbolos); // Ej: ["A","B"] y ["a"]
                }

                // 7. Almacenar en la estructura
                gramatica.put(noTerminal, producciones);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return gramatica;
    }
}