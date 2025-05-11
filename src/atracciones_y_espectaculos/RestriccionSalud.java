package atracciones_y_espectaculos;


import java.util.ArrayList;


public class RestriccionSalud {
	private String nombre;
private ArrayList<String> atracciones;

public RestriccionSalud(String nombre, ArrayList<String> atracciones) {
    this.nombre = nombre;
    this.atracciones = atracciones;
}

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<String> getAtraccionesMecanica() {
		return atracciones;
	}
	public void setAtraccionesMecanica(ArrayList<String> atracciones) {
		this.atracciones = atracciones;
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
    }
	
}
