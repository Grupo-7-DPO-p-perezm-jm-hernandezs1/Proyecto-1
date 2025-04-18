package empleados;

import java.util.ArrayList;

import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.Espectaculo;

public class Capacitacion {
private String tipo;
private ArrayList<Atraccion> atracciones;
private ArrayList<Espectaculo> espectaculos;
public Capacitacion(String tipo, ArrayList<Atraccion> atracciones, ArrayList<Espectaculo> espectaculos) {
	super();
	this.tipo = tipo;
	this.atracciones = atracciones;
	this.espectaculos = espectaculos;
	
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
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
// Metodos reales



}
