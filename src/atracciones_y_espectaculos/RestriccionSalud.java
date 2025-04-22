package atracciones_y_espectaculos;


import java.util.List;

public class RestriccionSalud {
	private String nombre;
private List<Mecanica> atracciones;

public RestriccionSalud(String nombre, List<Mecanica> atracciones) {
    this.nombre = nombre;
    this.atracciones = atracciones;
}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Mecanica> getAtraccionesMecanica() {
		return atracciones;
	}
	public void setAtraccionesMecanica(List<Mecanica> atracciones) {
		this.atracciones = atracciones;
	}
	public void agregarAtraccion(Mecanica atraccion) {
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
