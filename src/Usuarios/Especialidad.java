package Usuarios;

import java.util.ArrayList;
import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.LugarTrabajo;


public class Especialidad {
public String nombre;
public ArrayList<String> atracciones;
public ArrayList <String> lugaresTrabajo;
public Especialidad(String nombre, ArrayList<String> atracciones, ArrayList<String> lugaresTrabajo) {
	super();
	this.nombre = nombre;
	this.atracciones = atracciones;
	this.lugaresTrabajo = lugaresTrabajo;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public ArrayList<String> getAtracciones() {
	return atracciones;
}
public void setAtracciones(ArrayList<String> atracciones) {
	this.atracciones = atracciones;
}
public ArrayList<String> getLugaresTrabajo() {
	return lugaresTrabajo;
}
public void setLugaresTrabajo(ArrayList<String> lugaresTrabajo) {
	this.lugaresTrabajo = lugaresTrabajo;
}
public void agregarAtraccion(String atraccion) {
    atracciones.add(atraccion);
}

public void eliminarAtraccion(String nombre) {
    for (String atraccion : atracciones) {
        if (atraccion==nombre) {
            atracciones.remove(atraccion);
            return;
        }
    }
    return;
}
public void agregarLugarTrabajo(String lugarTrabajo) {
    lugaresTrabajo.add(lugarTrabajo);
}

public void eliminarLugarTrabajo(String nombre) {
    for (String lugarTrabajo : lugaresTrabajo) {
        if (lugarTrabajo ==(nombre)) {
            lugaresTrabajo.remove(lugarTrabajo);
            return;
        }
    }
    return;
}
}