package Code;

import java.util.List;
import java.util.Map;

public class ArbolSintactico<E> {
    protected Nodo<E> raiz;
    private Map<String, List<String[]>> gramatica;

    public ArbolSintactico(String archivoGramatica, String simboloInicial) {
        GramaticaParser parser = new GramaticaParser();
        gramatica = parser.parsear(archivoGramatica);
        this.raiz = construir(simboloInicial);
    }

    private Nodo<E> construir(String simbolo) {
        Nodo<E> nodo = new Nodo<>((E) simbolo, gramatica.containsKey(simbolo) ? "no-terminal" : "terminal");

        if (!gramatica.containsKey(simbolo)) {
            return nodo; // Es un símbolo terminal
        }

        List<String[]> producciones = gramatica.get(simbolo);
        for (String[] produccion : producciones) {
            Nodo<E> produccionNodo = new Nodo<>((E) String.join(" ", produccion), "produccion");
            for (String s : produccion) {
                produccionNodo.agregarHijo(construir(s));
            }
            nodo.agregarHijo(produccionNodo);
        }

        return nodo;
    }

    public void preorden(Nodo<E> n) {
        if (n != null) {
            System.out.println(n.dato + " " + n.etiqueta);
            for (Nodo<E> hijo : n.hijos) {
                preorden(hijo);
            }
        }
    }

    public Nodo<E> getRaiz() {
        return raiz;
    }

    public void imprimir() {
        imprimirRecursivo(raiz, "", true);
    }

    private void imprimirRecursivo(Nodo<E> nodo, String prefijo, boolean esUltimo) {
        if (nodo == null) return;
        System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + nodo.dato);
        for (int i = 0; i < nodo.hijos.size(); i++) {
            boolean ultimo = (i == nodo.hijos.size() - 1);
            imprimirRecursivo(nodo.hijos.get(i), prefijo + (esUltimo ? "    " : "│   "), ultimo);
        }
    }

}
