package colaPrioridad;

import java.util.Arrays;
import java.util.List;

public class ColaPrioridadHeap<E> extends ColaPrioridad<E> {

    private int cantidad;
    private E[] elementos;

    public ColaPrioridadHeap(Comparador comparador) {
        super(comparador);

        this.cantidad = 0;
        this.elementos = (E[]) new Object[0];
    }

    @Override
    public void insertar(E obj) throws Exception {
        if (cantidad == elementos.length) {
            redimensionar();
        }

        elementos[cantidad] = obj;
        ordernarHaciaArriba();

        cantidad++;
    }

    @Override
    public E eliminar() throws Exception {
        if (estaVacio()) {
            throw new Exception("La cola esta vacia");
        }

        int i = cantidad - 1;

        E obj = consultar();

        elementos[0] = elementos[i];
        elementos[i] = null;

        if (i > 1) {
            ordenarHaciaAbajo();
        }

        cantidad--;

        return obj;
    }

    @Override
    public E consultar() throws Exception {
        if (estaVacio()) {
            throw new Exception("La cola esta vacia");
        }

        return elementos[0];
    }

    @Override
    public boolean estaVacio() {
        return cantidad == 0 || elementos[0] == null;
    }

    private void redimensionar() {
        elementos = Arrays.copyOf(elementos, (int) Math.pow(2, cantidad + 1));
    }

    private void ordernarHaciaArriba() {
        int posicion = cantidad;

        while ((posicion - 1) / 2 >= 0 && comparador.esMayor(elementos[posicion], padre(posicion))) {
            intercambiar(posicion, (posicion - 1) / 2);
            posicion = (posicion - 1) / 2;
        }
    }

    private void intercambiar(int nodo, int padre) {
        E temporal = elementos[padre];

        elementos[padre] = elementos[nodo];
        elementos[nodo] = temporal;
    }

    private E padre(int nodo) {
        return elementos[(nodo - 1) / 2];
    }

    private void ordenarHaciaAbajo() {
        int posicion = 0;

        while (comparador.esMayor(elementos[hijoMayor(posicion)],
                elementos[posicion])) {
            int temporal = hijoMayor(posicion);

            intercambiar(hijoMayor(posicion), posicion);

            posicion = temporal;
        }
    }

    private int hijoMayor(int nodo) {
        final E izquierdo = elementos[2 * nodo + 1];
        final E derecho = elementos[2 * nodo + 2];
        
        if (izquierdo == null && derecho == null) {
            return nodo;
        }

        if (izquierdo != null && derecho == null) {
            return 2 * nodo + 1;
        }

        if (izquierdo == null && derecho != null) {
            return 2 * nodo + 2;
        }

        return comparador.esMayor(izquierdo, derecho) ? 2 * nodo + 1
                : 2 * nodo + 2;
    }

    // <editor-fold defaultstate="collapsed" desc="getters y setters">
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public E[] getElementos() {
        return elementos;
    }

    public void setElementos(E[] elementos) {
        this.elementos = elementos;
    }

    // </editor-fold>

    @Override
    public List<E> todos() {
        return Arrays.asList(elementos);
    }
    
}
