package tiquetes;

import java.time.LocalDate;

public class TiqueteTemporada extends Tiquete {
	
	public LocalDate fechaInicio;
	public LocalDate fechaFinal;
	public Categoria tipo;

	
	public TiqueteTemporada(String identificador, double precio, LocalDate fechaInicio, LocalDate fechaFinal, Categoria tipo) {
		super(identificador, precio);
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.tipo = tipo;
	}

	public String adquirirTiqueteTemporada() {		
		setIdentificador("TEMP-"+identificador);
		return identificador;	
	
	}
		
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Categoria getTipo() {
		return tipo;
	}
	public void setTipo(Categoria tipo) {
		this.tipo = tipo;
	}
	
	
	
	public void establecerFechas(LocalDate fechaInicio, LocalDate fechaFinal) {
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
	}
	public boolean validacionTiqueteTemporada(LocalDate fecha) {
	    return (fecha.isEqual(fechaInicio) || fecha.isAfter(fechaInicio)) &&
	           (fecha.isEqual(fechaFinal) || fecha.isBefore(fechaFinal));
	}
	
}
