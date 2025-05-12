package Usuarios;

import java.util.ArrayList;

public class GestorPersonas {
protected ArrayList<Cliente> clientes;
protected ArrayList<Empleado> empleados;
protected ArrayList<Persona> personas;
protected ArrayList<Especialidad> especialidades;
public GestorPersonas(ArrayList<Cliente> clientes, ArrayList<Empleado> empleados, ArrayList<Persona> personas,
		ArrayList<Especialidad> especialidades) {
	super();
	this.clientes = clientes;
	this.empleados = empleados;
	this.personas = personas;
	this.especialidades = especialidades;
}
public ArrayList<Cliente> getClientes() {
	return clientes;
}
public void setClientes(ArrayList<Cliente> clientes) {
	this.clientes = clientes;
}
public ArrayList<Empleado> getEmpleados() {
	return empleados;
}
public void setEmpleados(ArrayList<Empleado> empleados) {
	this.empleados = empleados;
}
public ArrayList<Persona> getPersonas() {
	return personas;
}
public void setPersonas(ArrayList<Persona> personas) {
	this.personas = personas;
}
public ArrayList<Especialidad> getEspecialidades() {
	return especialidades;
}
public void setEspecialidades(ArrayList<Especialidad> especialidades) {
	this.especialidades = especialidades;
}

public void crearCliente(String login, String password, ArrayList<Compra> historial) {
	Cliente cliente = new Cliente(login, password,historial);
	personas.add(cliente);
	clientes.add(cliente);
	
}
public void crearEmpleado(String login, String password, boolean cocinero, Especialidad especialidad) {
	Empleado empleado = new Empleado(login, password, cocinero, especialidad);
	empleados.add(empleado);
	personas.add(empleado);
}
public void asignarEspecialidad() {
	
}
public Persona getPersona(String login) {
	Persona respuesta = null;
	for(Persona persona : personas) {
		if(persona.getLogin().equals(login)) {
			respuesta = persona;
		}
	}
	return respuesta;
}
}
