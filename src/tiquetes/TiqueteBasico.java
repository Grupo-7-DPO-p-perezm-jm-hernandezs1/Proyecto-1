package tiquetes;

public class TiqueteBasico extends Tiquete {

	public TiqueteBasico(String identificador, double precio) {
		super(identificador, precio);
	}
	
	public String adquirirTiqueteBasico(){
		setIdentificador("BASI-"+identificador);
		return identificador;
	}
	
}
