package atracciones_y_espectaculos;

import java.util.ArrayList;

public class Parque {
private ArrayList<Atraccion> atracciones;
private ArrayList<Espectaculo> espectaculos;
public Parque(ArrayList<Atraccion> atracciones, ArrayList<Espectaculo> espectaculos) {
	super();
	this.atracciones = atracciones;
	this.espectaculos = espectaculos;
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
