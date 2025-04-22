package Turnos;
import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.LugarTrabajo;
import Usuarios.Empleado;


public class Turno {
	Atraccion atraccion;
	public LugarTrabajo lugarTrabajo;
	public Empleado empleado;
	public TipoTurno tipo;
	
	public Turno(TipoTurno tipo, Object destino, Empleado empleado) {
	    this.setTipo(tipo);
	    this.empleado = empleado;
	    if (tipo == TipoTurno.ATRACCION) {
	        this.atraccion = (Atraccion) destino;
	    } else {
	        this.lugarTrabajo = (LugarTrabajo) destino;
	    }
	}

	
	public void setAtraccion(Atraccion a) {
        this.atraccion = a;
    }

    public Atraccion getAtraccion() {
        return this.atraccion;
    }

	public TipoTurno getTipo() {
		return tipo;
	}

	public void setTipo(TipoTurno tipo) {
		this.tipo = tipo;
	}
	
}
