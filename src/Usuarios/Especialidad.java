package Usuarios;

import java.util.ArrayList;
import atracciones_y_espectaculos.Atraccion;


public class Especialidad {
public String nombre;
public ArrayList<Atraccion> atracciones;
public Especialidad(String nombre, ArrayList<Atraccion> atracciones) {
	super();
	this.nombre = nombre;
	this.atracciones = atracciones;
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
public void agregarAtraccion(Atraccion atraccion) {
    atracciones.add(atraccion);
}

public void eliminarAtraccion(String nombre) {
    for (Atraccion atraccion : atracciones) {
        if (atraccion.getNombre().equals(nombre)) {
            atracciones.remove(atraccion);
            return;
        }
    }
}

}
