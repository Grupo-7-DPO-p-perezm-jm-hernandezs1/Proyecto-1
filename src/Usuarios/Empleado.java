package Usuarios;

import java.util.ArrayList;

public class Empleado extends Persona{
	protected boolean cocinero;
	protected Especialidad especialidades;
	public Empleado(String login, String password, boolean cocinero, Especialidad especialidades) {
		super(login, password);
		this.cocinero = cocinero;
		this.especialidades = especialidades;
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