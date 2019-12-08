package colaPrioridad;

import java.util.List;

public abstract class ColaPrioridad<E> {

    protected Comparador comparador;

    public ColaPrioridad(Comparador comparador) {
        this.comparador = comparador;
    }
    
    public abstract void insertar(E obj) throws Exception;
    
    public abstract E eliminar() throws Exception;
    
    public abstract E consultar() throws Exception;
    
    public abstract boolean estaVacio();

    public abstract List<E> todos();
    
    // <editor-fold defaultstate="collapsed" desc="getters y setters">
    
    public Comparador getComparador() {
        return comparador;
    }

    public void setComparador(Comparador comparador) {
        this.comparador = comparador;
    }
    
    // </editor-fold>
    
}