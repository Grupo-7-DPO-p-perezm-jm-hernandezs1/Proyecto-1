package tiquetes;

import java.util.Date;

public class Temporada extends Tiquete{
	private String tipo;
	private Date fecha;
	public Temporada(boolean usado, String exclusividad, String tipo, Date fecha) {
		super(usado, exclusividad);
		this.tipo = tipo;
		this.fecha = fecha;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public static int getTiempo() {
		return 0;
		
	}
	
}
