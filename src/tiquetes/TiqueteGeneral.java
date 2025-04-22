package tiquetes;


public class TiqueteGeneral extends Tiquete {
	public Categoria tipo;
	
	public TiqueteGeneral(String identificador, double precio, Categoria tipo) {
		super(identificador, precio);
		this.tipo = tipo;
	}
	
	public String adquirirTiqueteGeneral(){
		setIdentificador("GENE-"+identificador);
		return identificador;
	}

	public Categoria getTipo() {
		return tipo;
	}

	public void setTipo(Categoria tipo) {
		this.tipo = tipo;
	}
	

}
