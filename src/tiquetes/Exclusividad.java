package tiquetes;

import java.util.ArrayList;

import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.Espectaculo;

public class Exclusividad {
	protected ArrayList<Atraccion> atracciones;
	protected ArrayList<Espectaculo> espectaculos;
	protected Categoria tipo;

	public Exclusividad(String nombre, ArrayList<Atraccion> atracciones, ArrayList<Espectaculo> espectaculos,
			Categoria tipo) {

		this.atracciones = atracciones;
		this.espectaculos = espectaculos;
		this.tipo = tipo;

	}

	public Categoria getTipo() {
		return tipo;
	}

	public void setTipo(Categoria tipo) {
		this.tipo = tipo;
	}

	public ArrayList<Atraccion> getAtracciones() {
		return atracciones;
	}

	public void setAtracciones(ArrayList<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}

	public ArrayList<Espectaculo> getEspectaculos() {
		return espectaculos;
	}

	public void setEspectaculos(ArrayList<Espectaculo> espectaculos) {
		this.espectaculos = espectaculos;
	}
	

}
