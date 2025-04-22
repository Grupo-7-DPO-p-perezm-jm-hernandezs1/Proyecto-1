package tiquetes;


public abstract class Tiquete {	
	public String identificador;
    public boolean usado;
    public double precio;


    public Tiquete(String identificador, double precio) {
        this.identificador = identificador;
        this.precio = precio;
        this.usado = false;
    }

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

    public boolean getUsado() {
    	return usado;
    }
    
}
