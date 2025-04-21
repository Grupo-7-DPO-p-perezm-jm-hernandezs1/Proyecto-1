package Usuarios;

import java.util.ArrayList;
import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.LugarTrabajo;


public class Especialidad {
public String nombre;
public ArrayList<Atraccion> atracciones;
public ArrayList <LugarTrabajo> lugaresTrabajo;
public Especialidad(String nombre, ArrayList<Atraccion> atracciones, ArrayList<LugarTrabajo> lugaresTrabajo) {
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
public ArrayList<Atraccion> getAtracciones() {
	return atracciones;
}
public void setAtracciones(ArrayList<Atraccion> atracciones) {
	this.atracciones = atracciones;
}
public ArrayList<LugarTrabajo> getLugaresTrabajo() {
	return lugaresTrabajo;
}
public void setLugaresTrabajo(ArrayList<LugarTrabajo> lugaresTrabajo) {
	this.lugaresTrabajo = lugaresTrabajo;
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
    return;
}
public void agregarLugarTrabajo(LugarTrabajo lugarTrabajo) {
    lugaresTrabajo.add(lugarTrabajo);
}

public void eliminarLugarTrabajo(String nombre) {
    for (LugarTrabajo lugarTrabajo : lugaresTrabajo) {
        if (lugarTrabajo.getNombre().equals(nombre)) {
            lugaresTrabajo.remove(lugarTrabajo);
            return;
        }
    }
    return;
}
}