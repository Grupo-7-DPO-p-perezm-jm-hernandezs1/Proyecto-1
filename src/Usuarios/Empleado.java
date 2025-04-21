package Usuarios;

import java.util.ArrayList;

public class Empleado extends Persona{
	protected boolean cocinero;
	protected ArrayList<Especialidad> especialidades;
	public Empleado(String login, String password, boolean cocinero, ArrayList<Especialidad> especialidades) {
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
	public ArrayList<Especialidad> getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(ArrayList<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}
	public void agregarEspecialidad(Especialidad especialidad) {
		especialidades.add(especialidad);
	}
	public void eliminarEspecialidad (String nombre) {
		for (Especialidad especialidad: especialidades) {
			if(especialidad.getNombre().equals(nombre)) {
				especialidades.remove(especialidad);
			}
		}
	}
}
