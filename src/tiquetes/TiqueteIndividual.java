package tiquetes;
import atracciones_y_espectaculos.Atraccion;

public class TiqueteIndividual extends Tiquete {
	public Atraccion atraccionValida;
	

	public TiqueteIndividual(String identificador, double precio, Atraccion atraccionValida) {
		super(identificador, precio);
		this.atraccionValida = atraccionValida;
	}

	public String adquirirTiqueteIndividual() {
		setIdentificador("INDV-"+identificador);
		return identificador;
	}
	
	public void setAtraccionValida(Atraccion atra) {
		this.atraccionValida = atra;
	}

	public Atraccion getAtraccionValida() {
		return atraccionValida;
	}
	
	
}
