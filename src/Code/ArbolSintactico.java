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
        if (!gramatica.containsKey(simbolo)) {
            return new Nodo<>((E) simbolo, "terminal");
        }

        String[] produccion = gramatica.get(simbolo).get(0); // Primera producción
        Nodo<E> nodo = new Nodo<>((E) simbolo, "no-terminal");

        for (String s : produccion) {
            nodo.agregarHijo(construir(s));
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
