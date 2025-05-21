package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Usuarios.Cliente;
import Usuarios.Empleado;
import Usuarios.Especialidad;
import Usuarios.GestorPersonas;
import Usuarios.Persona;
import persistencia.Escritor_Usuarios;
import persistencia.LectorUsuario;

class PruebaGestorUsuario {
	private LectorUsuario lectorU;
	private Escritor_Usuarios escritorU;
	private GestorPersonas gestor;
	
	

	@BeforeEach
	void setUp() throws Exception {
		this.lectorU = new LectorUsuario();
		this.escritorU = new Escritor_Usuarios();
		ArrayList<Cliente> clientes = new ArrayList();
		ArrayList<Empleado> empleados = new ArrayList();
		clientes = lectorU.leerClientes(".\\src\\data\\clientes.txt");
		empleados = lectorU.leerEmpleados(".\\src\\data\\empleados.txt");
		ArrayList<Persona> personas = new ArrayList();
		personas.addAll(clientes);
		personas.addAll(empleados);
		ArrayList<Especialidad> especialidades = new ArrayList<Especialidad>();
		especialidades= lectorU.leerEspecialidades();
		this.gestor = new GestorPersonas(clientes,empleados,personas,especialidades);
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAlgo() throws Exception{
		
	}
}

