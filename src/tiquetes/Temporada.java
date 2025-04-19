package tiquetes;

import java.util.Date;

public class Temporada extends Tiquete{
	protected  Exclusividad exclusividad;
	protected Date fechaInicio;
	protected Date fechaFinal;
	public Temporada(boolean usado, Exclusividad exclusividad, double valor, Exclusividad exclusividad2, Date fechaInicio,
			Date fechaFinal) {
		super(usado, exclusividad, valor);
		this.exclusividad = exclusividad;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
	}
	public Exclusividad getExclusivid() {
		return exclusividad;
	}
	public void setExclusividad(Exclusividad exclusividad) {
		this.exclusividad = exclusividad;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	
	
	
	
}
