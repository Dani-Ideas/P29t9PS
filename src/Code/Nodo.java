package Code;

import java.util.ArrayList;
import java.util.List;

public class Nodo<E> {
    protected E dato;
    protected List<Nodo<E>> hijos = new ArrayList<>();
    protected String etiqueta;

    public Nodo(E dato, String etiqueta) {
        this.dato = dato;
        this.etiqueta = etiqueta;
    }

    public void agregarHijo(Nodo<E> hijo) {
        hijos.add(hijo);
    }
}
