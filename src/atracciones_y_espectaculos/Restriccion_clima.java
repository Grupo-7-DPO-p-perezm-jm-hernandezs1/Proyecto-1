package atracciones_y_espectaculos;

import java.util.ArrayList;

public class Restriccion_clima {
protected String tipo;
protected ArrayList<Atraccion> atracciones;
protected ArrayList<Espectaculo> espectaculos;
public Restriccion_clima(String tipo, ArrayList<Atraccion> atracciones, ArrayList<Espectaculo> espectaculos) {
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


}
