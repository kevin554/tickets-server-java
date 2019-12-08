package colaPrioridad;

public class Nodo<E> {

    private E contenido;
    private Nodo<E> anterior;
    private Nodo<E> siguiente;

    public Nodo(E contenido) {
        this.contenido = contenido;
        this.anterior = null;
        this.siguiente = null;
    }

    // <editor-fold defaultstate="collapsed" desc="getters, setters y toString">
    
    public E getContenido() {
        return contenido;
    }

    public void setContenido(E contenido) {
        this.contenido = contenido;
    }

    public Nodo<E> getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo<E> anterior) {
        this.anterior = anterior;
    }

    public Nodo<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<E> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "Nodo{" + "contenido=" + contenido + ", anterior=" + anterior + ", siguiente=" + siguiente + '}';
    }
    
    // </editor-fold>

}