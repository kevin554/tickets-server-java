package colaPrioridad;

import java.util.List;

public class ColaPrioridadSecuenciaNoOrdenada<E> extends ColaPrioridad<E> {

    private Nodo primero;
    private Nodo ultimo;

    public ColaPrioridadSecuenciaNoOrdenada(Comparador comparador) {
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
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
        }
    }

    @Override
    public E eliminar() throws Exception {
        Nodo<E> mayor = getNodoMayor();
        
        if (mayor == null) {
            throw new Exception("La cola se encuentra vacia");
        }
        
        Nodo<E> anterior = mayor.getAnterior();
        Nodo<E> siguiente = mayor.getSiguiente();
        
        if (anterior != null) {
            anterior.setSiguiente(siguiente);
        } else {
            primero = siguiente;
        }
        
        if (siguiente != null) {
            siguiente.setAnterior(anterior);
        } else {
            ultimo = anterior;
        }
        
        return mayor.getContenido();
    }

    @Override
    public E consultar() throws Exception {
        Nodo<E> mayor = getNodoMayor();
        
        if (mayor == null) {
            throw new Exception("La cola se encuentra vacia");
        }
        
        return mayor.getContenido();
    }

    @Override
    public boolean estaVacio() {
        return primero == null && ultimo == null;
    }

    private Nodo<E> getNodoMayor() {
        if (primero == null) {
            return null;
        }

        Nodo<E> mayor = primero;
        Nodo<E> actual = primero.getSiguiente();

        while (actual != null) {
            if (comparador.esMayor(actual.getContenido(),
                    mayor.getContenido())) {
                mayor = actual;
            }

            actual = actual.getSiguiente();
        }

        return mayor;
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
