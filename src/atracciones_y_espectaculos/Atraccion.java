package atracciones_y_espectaculos;

import java.util.ArrayList;

public class Atraccion {
	protected String lugar;
	protected int cupoMaximo;
	protected ArrayList<String> recomendaciones;
	protected ArrayList <String> restriccionClima;
	protected int numeroEmpleados;
	protected int minEdad;
	protected boolean deTemporada;
	
	
	public Atraccion(String lugar, int cupoMaximo, ArrayList<String> recomendaciones,
			ArrayList<String> restriccionClima, int numeroEmpleados, int minEdad, boolean deTemporada) {
		super();
		this.lugar = lugar;
		this.cupoMaximo = cupoMaximo;
		this.recomendaciones = recomendaciones;
		this.restriccionClima = restriccionClima;
		this.numeroEmpleados = numeroEmpleados;
		this.deTemporada = deTemporada;
	}
	
	
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public int getCupoMaximo() {
		return cupoMaximo;
	}
	public void setCupoMaximo(int cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}
	public ArrayList<String> getRecomendaciones() {
		return recomendaciones;
	}
	public void setRecomendaciones(ArrayList<String> recomendaciones) {
		this.recomendaciones = recomendaciones;
	}
	public ArrayList<String> getRestriccionClima() {
		return restriccionClima;
	}
	public void setRestriccionClima(ArrayList<String> restriccionClima) {
		this.restriccionClima = restriccionClima;
	}
	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}
	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}
	public boolean isDeTemporada() {
		return deTemporada;
	}
	public void setDeTemporada(boolean deTemporada) {
		this.deTemporada = deTemporada;
	}
	
	
}
