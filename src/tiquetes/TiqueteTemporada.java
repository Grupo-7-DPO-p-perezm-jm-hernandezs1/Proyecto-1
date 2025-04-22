package tiquetes;

import java.util.Date;

public class TiqueteTemporada extends Tiquete {
	
	public Date fechaInicio;
	public Date fechaFinal;
	public Categoria tipo;

	
	public TiqueteTemporada(String identificador, double precio, Date fechaInicio, Date fechaFinal, Categoria tipo) {
		super(identificador, precio);
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.tipo = tipo;
	}

	public String adquirirTiqueteTemporada() {		
		setIdentificador("TEMP-"+identificador);
		return identificador;	
	
	}
		
	public Categoria getTipo() {
		return tipo;
	}
	public void setTipo(Categoria tipo) {
		this.tipo = tipo;
	}
	
	public void establecerFechas(Date fechaInicio, Date fechaFinal) {
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
	}
	public boolean validacionTiqueteTemporada(Date fecha) {
	    return !fecha.before(fechaInicio) && !fecha.after(fechaFinal);
	}
	
}
