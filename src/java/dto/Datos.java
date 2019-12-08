package dto;

import java.util.List;

public class Datos {

    private static Datos instancia;
    private List<Paciente> lista;

    public static Datos getOrCreate() {
        if (instancia == null) {
            instancia = new Datos();
        }
        
        return instancia;
    }
    
    private Datos() {
    }
    
    // <editor-fold defaultstate="collapsed" desc="getters y setters">

    public List<Paciente> getLista() {
        return lista;
    }

    public void setLista(List<Paciente> lista) {
        this.lista = lista;
    }
    
    // </editor-fold>
    
}