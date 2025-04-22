package Usuarios;

public class Compra {
public String numeroTiquete;
public String fechaVencida;
public Compra(String numeroTiquete, String fechaVencida) {
	super();
	this.numeroTiquete = numeroTiquete;
	this.fechaVencida = fechaVencida;
}
public String getNumeroTiquete() {
	return numeroTiquete;
}
public void setNumeroTiquete(String numeroTiquete) {
	this.numeroTiquete = numeroTiquete;
}
public String getFechaVencida() {
	return fechaVencida;
}
public void setFechaVencida(String fechaVencida) {
	this.fechaVencida = fechaVencida;
}

}
