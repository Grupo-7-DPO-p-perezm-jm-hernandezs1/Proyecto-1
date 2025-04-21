package Usuarios;

import java.util.ArrayList;

public class Cliente extends Persona{
	public ArrayList<Compra> historial;

	public Cliente(String login, String password, ArrayList<Compra> historial) {
		super(login, password);
		this.historial = historial;
	}

	public ArrayList<Compra> getHistorial() {
		return historial;
	}

	public void setHistorial(ArrayList<Compra> historial) {
		this.historial = historial;
	}
	
	
}
