package atracciones_y_espectaculos;

public class LugarTrabajo {
protected String nombre;
protected boolean cocina;
protected boolean abierto;
public LugarTrabajo(String nombre, boolean cocina, boolean abierto) {
	super();
	this.nombre = nombre;
	this.cocina = cocina;
	this.abierto = abierto;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public boolean isCocina() {
	return cocina;
}
public void setCocina(boolean cocina) {
	this.cocina = cocina;
}
public boolean isAbierto() {
	return abierto;
}
public void setAbierto(boolean abierto) {
	this.abierto = abierto;
}

}
