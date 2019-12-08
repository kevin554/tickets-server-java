package colaPrioridad;

import dto.Paciente;

public final class ComparadorPacientes implements Comparador<Paciente>{

    @Override
    public boolean esMayor(Paciente objA, Paciente objB) {
        int prioridadObjA = 1;
        int prioridadObjB = 1;
        
        // Nivel de Prioridad del Paciente A
        if (objA.getTieneHemorragia().equals("VERDADERO")) {
            prioridadObjA += 6;
        }
        
        if (objA.getEstaConsciente().equals("FALSO")) {
            prioridadObjA += 4;
        }
        
        if (objA.getEstaEmbarazada().equals("VERDADERO")) {
            prioridadObjA += 3;
        }
        
        if (objA.esDeTerceraEdad() && objB.esDeTerceraEdad()) {
            prioridadObjA += 2;
            prioridadObjB += 2;
        } else if (objA.esDeTerceraEdad()) {
            prioridadObjA += 2;
        } else if (objB.esDeTerceraEdad()) {
            prioridadObjB += 2;
        }
        
        // Nivel de Prioridad del Paciente B
        if (objB.getTieneHemorragia().equals("VERDADERO")) {
            prioridadObjB += 6;
        }
        
        if (objB.getEstaConsciente().equals("FALSO")) {
            prioridadObjB += 4;
        }
        
        if (objB.getEstaEmbarazada().equals("VERDADERO")) {
            prioridadObjB += 3;
        }
        
        return prioridadObjA > prioridadObjB;
    }

}