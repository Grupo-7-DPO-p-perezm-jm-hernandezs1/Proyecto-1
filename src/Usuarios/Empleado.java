package Usuarios;

import java.util.ArrayList;

public class Empleado extends Persona{
	protected boolean cocinero;
	protected ArrayList<Especialidad> Especialidades;
	public Empleado(String login, String password, boolean cocinero, ArrayList<Especialidad> especialidades) {
		super(login, password);
		this.cocinero = cocinero;
		Especialidades = especialidades;
	}
	public boolean isCocinero() {
		return cocinero;
	}
	public void setCocinero(boolean cocinero) {
		this.cocinero = cocinero;
	}
	public ArrayList<Especialidad> getEspecialidades() {
		return Especialidades;
	}
	public void setEspecialidades(ArrayList<Especialidad> especialidades) {
		Especialidades = especialidades;
	}

}
