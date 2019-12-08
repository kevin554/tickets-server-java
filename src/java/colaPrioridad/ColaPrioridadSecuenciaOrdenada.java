package colaPrioridad;

import java.util.List;

public class ColaPrioridadSecuenciaOrdenada<E> extends ColaPrioridad<E> {

    private Nodo<E> primero;
    private Nodo<E> ultimo;

    public ColaPrioridadSecuenciaOrdenada(Comparador comparador) {
        super(comparador);
        this.primero = null;
        this.ultimo = null;
    }

    @Override
    public void insertar(E obj) throws Exception {
        if (obj == null) {
            throw new Exception("El objeto no puede ser nulo");
        }

        Nodo<E> nuevo = new Nodo(obj);

        if (estaVacio()) {
            ultimo = nuevo;
            primero = ultimo;
        } else {
            Nodo<E> actual = primero;

            while (actual != null) {
                if (comparador.esMayor(nuevo.getContenido(), actual.getContenido())) {
                    Nodo<E> siguiente = actual.getSiguiente();
                    
                    if (siguiente == null) {
                        nuevo.setSiguiente(siguiente);
                        nuevo.setAnterior(actual);

                        actual.setSiguiente(nuevo);
                    } else if (!comparador.esMayor(nuevo.getContenido(), siguiente.getContenido())) {
                        nuevo.setSiguiente(siguiente);
                        nuevo.setAnterior(actual);

                        siguiente.setAnterior(nuevo);
                        actual.setSiguiente(nuevo);
                    }
                }

                if (actual.getSiguiente() == null &&
                        !comparador.esMayor(actual.getContenido(), nuevo.getContenido())) {
                    
                    ultimo.setSiguiente(actual);
                    actual.setAnterior(ultimo);
                    ultimo = actual;
                }

                actual = actual.getSiguiente();
            }
        }
    }

    @Override
    public E eliminar() throws Exception {
        if (estaVacio()) {
            throw new Exception("La cola esta vacia");
        }
        
        Nodo<E> nodo = ultimo;
        
        if (ultimo.getAnterior() != null) {
            ultimo.getAnterior().setSiguiente(null);
            ultimo = ultimo.getAnterior();
        } else {
            primero = null;
            ultimo = null;
        }       
        
        return nodo.getContenido();
    }

    @Override
    public E consultar() throws Exception {
        return ultimo.getContenido();
    }

    @Override
    public boolean estaVacio() {
        return primero == null && ultimo == null;
    }

    // <editor-fold defaultstate="collapsed" desc="getters y setters">
    
    public Nodo getPrimero() {
        return primero;
    }

    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }

    // </editor-fold>

    @Override
    public String toString() {
        Nodo<E> actual = primero;
        
        if (estaVacio()) {
            return "esta vacia";
        }
        
        StringBuilder sb = new StringBuilder();
        boolean primerNodo = true;
        
        
        while (actual != null) {
            if (primerNodo) {
                sb.append(actual.getContenido());
                
                primerNodo = false;
                actual = actual.getSiguiente();
                
                continue;
            }
            
            sb.append(", ");
            sb.append(actual.getContenido());
            
            actual = actual.getSiguiente();
        }
        
        return sb.toString();
    }

    @Override
    public List<E> todos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
