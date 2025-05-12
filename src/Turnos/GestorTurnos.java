package Turnos;
import java.util.HashMap;

import Usuarios.Empleado;
import Usuarios.GestorPersonas;
import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.LugarTrabajo;
import persistencia.PersistenciaTurnos;

public class GestorTurnos {
	private SemanaTurnos semana = new SemanaTurnos();
	private PersistenciaTurnos persistencia = new PersistenciaTurnos();
    public void reiniciarSemana() {
        semana.reiniciarSemana();
    }

    public SemanaTurnos getSemana() {
        return semana;
    }
	
	public Turno crearTurno(TipoTurno tipoTurno, Object destino,  Empleado empleado ) {
		Turno nuevoTurno = new Turno(tipoTurno, destino, empleado);
		return nuevoTurno;
	}
	public void asignarTurno(TipoTurno tipoTurno, Object destino, Empleado empleado, String franja, DiaTurnos diaTurnos) {
	    Turno nuevoTurno = crearTurno(tipoTurno, destino, empleado);
	    String login = empleado.getLogin();
	    if (franja.equalsIgnoreCase("mañana")) {
	        if (diaTurnos.getMañana().containsKey(login)) {
	            throw new IllegalStateException("El empleado ya tiene un turno asignado en la mañana.");
	        }
	        diaTurnos.getMañana().put(login, nuevoTurno);
	    }
	    else if (franja.equalsIgnoreCase("tarde")) {
	    	if (diaTurnos.getMañana().containsKey(login)) {
	            throw new IllegalStateException("El empleado ya tiene un turno asignado en la mañana.");
	        }
	        diaTurnos.getMañana().put(login, nuevoTurno);
	    }
	    else {
	        throw new IllegalArgumentException("Franja horaria inválida: " + franja);
	    }
	    
	}
	
	public void guardarTurnoSemana(TipoTurno tipoTurno, Object destino, Empleado empleado, String dia, String franja) {
	    DiaTurnos diaTurnos;
	    switch (dia.toLowerCase()) {
	        case "lunes":
	            diaTurnos = semana.getLunes();
	            break;
	        case "martes":
	            diaTurnos = semana.getMartes();
	            break;
	        case "miercoles":
	            diaTurnos = semana.getMiercoles();
	            break;
	        case "jueves":
	            diaTurnos = semana.getJueves();
	            break;
	        case "viernes":
	            diaTurnos = semana.getViernes();
	            break;
	        case "sabado":
	            diaTurnos = semana.getSabado();
	            break;
	        case "domingo":
	            diaTurnos = semana.getDomingo();
	            break;
	        default:
	            throw new IllegalArgumentException("Día inválido: " + dia);
	    }

	    asignarTurno(tipoTurno, destino, empleado, franja, diaTurnos);
	   
	}
	public void guardarSemana() {
	    persistencia.guardarSemana(semana);
	}

}
