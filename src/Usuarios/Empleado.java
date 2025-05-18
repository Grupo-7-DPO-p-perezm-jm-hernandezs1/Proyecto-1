package Usuarios;

import java.util.ArrayList;

public class Empleado extends Persona{
	protected boolean cocinero;
	protected Especialidad especialidades;
	public ArrayList<Compra> historial;
	public Empleado(String login, String password, boolean cocinero, Especialidad especialidades,ArrayList<Compra> historial) {
		super(login, password);
		this.cocinero = cocinero;
		this.especialidades = especialidades;
		this.historial = historial;
	}
	
	

	public ArrayList<Compra> getHistorial() {
		return historial;
	}



	public void setHistorial(ArrayList<Compra> historial) {
		this.historial = historial;
	}



	public boolean isCocinero() {
		return cocinero;
	}
	public void setCocinero(boolean cocinero) {
		this.cocinero = cocinero;
	}
	public Especialidad getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(Especialidad especialidades) {
		this.especialidades = especialidades;
	}
	
	
	
		
	
}