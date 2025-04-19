package tiquetes;

import java.util.ArrayList;

import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.Espectaculo;

public class Exclusividad {
   protected String nombre;
   protected ArrayList<Atraccion> atracciones;
   protected ArrayList<Espectaculo> espectaculos;
   
public Exclusividad(String nombre, ArrayList<Atraccion> atracciones, ArrayList<Espectaculo> espectaculos) {
	super();
	this.nombre = nombre;
	this.atracciones = atracciones;
	this.espectaculos = espectaculos;
	
	
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
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
