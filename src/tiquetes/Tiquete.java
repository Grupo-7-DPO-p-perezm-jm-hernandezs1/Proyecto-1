package tiquetes;

public class Tiquete {
	protected boolean usado;
	// protected int valor;
	protected String exclusividad;
	public Tiquete(boolean usado, String exclusividad) {
		super();
		this.usado = usado;
		this.exclusividad = exclusividad;
	}
	public boolean isUsado() {
		return usado;
	}
	public void setUsado(boolean usado) {
		this.usado = usado;
	}
	public String getExclusividad() {
		return exclusividad;
	}
	public void setExclusividad(String exclusividad) {
		this.exclusividad = exclusividad;
	}
	
	

}
