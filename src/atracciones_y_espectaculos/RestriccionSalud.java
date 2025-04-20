package atracciones_y_espectaculos;

import java.util.ArrayList;

public class RestriccionSalud {
	protected String nombre;
	protected ArrayList <Mecanica> atraccionesMecanica;
	public RestriccionSalud(String nombre, ArrayList<Mecanica> atraccionesMecanica) {
		super();
		this.nombre = nombre;
		this.atraccionesMecanica = atraccionesMecanica;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Mecanica> getAtraccionesMecanica() {
		return atraccionesMecanica;
	}
	public void setAtraccionesMecanica(ArrayList<Mecanica> atraccionesMecanica) {
		this.atraccionesMecanica = atraccionesMecanica;
	}
	
	
}
