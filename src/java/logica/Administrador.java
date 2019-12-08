package logica;

import colaPrioridad.ColaPrioridad;
import colaPrioridad.ColaPrioridadHeap;
import colaPrioridad.ColaPrioridadSecuenciaNoOrdenada;
import colaPrioridad.ColaPrioridadSecuenciaOrdenada;
import colaPrioridad.ComparadorPacientes;
import dto.Paciente;
import java.util.List;
import java.util.Observable;

public class Administrador extends Observable {

    private static Administrador instancia;
    private int siguienteTicket;
    private int siguienteID;
    private ColaPrioridad<Paciente> cola;

    public static Administrador getOrCreate() {
        if (instancia == null) {
            instancia = new Administrador();
            instancia.addObserver(new Notificador());
        }
        
        return instancia;
    }
    
    private Administrador() {
        siguienteTicket = 1;
        siguienteID = 1;
        cola = new ColaPrioridadHeap<>(new ComparadorPacientes());
    }
    
    public int siguienteTicket() {
        return ++siguienteTicket;
    }
    
    public int siguienteID() {
        return ++siguienteID;
    }
    
    public void agregarPaciente(Paciente objPaciente) {
        try {
            cola.insertar(objPaciente);
        } catch (Exception ex) {
            
        }
    }
    
    public Paciente siguientePaciente() {
//        try {
//            setChanged();
//            notifyObservers(cola.consultar());
//        } catch (Exception ex) {
//            
//        }
//        
//        return null;
        try {
            return cola.consultar();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public void despacharPaciente() {
        try {
            setChanged();
            notifyObservers(cola.eliminar());
        } catch (Exception ex) {
            
        }
    }
    
    public Paciente obtener(int ID) {
        List<Paciente> todos = cola.todos();
        
        for (Paciente paciente : todos) {
            if (paciente.getCodigoID() == ID) {
                return paciente;
            }
        }
        
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="getters y setters">
    
    public int getSiguienteTicket() {
        return siguienteTicket;
    }

    public void setSiguienteTicket(int siguienteTicket) {
        this.siguienteTicket = siguienteTicket;
    }

    public int getSiguienteID() {
        return siguienteID;
    }

    public void setSiguienteID(int siguienteID) {
        this.siguienteID = siguienteID;
    }

    public ColaPrioridad<Paciente> getCola() {
        return cola;
    }

    public void setCola(ColaPrioridad<Paciente> cola) {
        this.cola = cola;
    }
    
    // </editor-fold>
    
}