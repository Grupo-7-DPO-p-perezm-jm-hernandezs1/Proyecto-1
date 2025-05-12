package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Usuarios.Cliente;
import Usuarios.Compra;
import Usuarios.Empleado;
import Usuarios.Especialidad;
import persistencia.Escritor_Usuarios;
import persistencia.LectorUsuario;

class PruebaPersistenciaUsuario {
	private Escritor_Usuarios escritorUsuario;
    private LectorUsuario lectorUsuario;
    
	@BeforeEach
	void setUp() throws Exception {
		this.escritorUsuario = new Escritor_Usuarios();
		this.lectorUsuario = new LectorUsuario();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testLectorEmpleado() throws IOException {
		ArrayList<Empleado> empleados = lectorUsuario.leerEmpleados(".\\src\\data\\empleados.txt");
		
			}
	@Test
	void testLectorCliente() throws IOException{
		ArrayList<Cliente> clientes = lectorUsuario.leerClientes(".\\src\\data\\clientes.txt");
		
		
		
		
		
	}
	@Test
	void testEscritorCliente() {
		Compra compra1 = new Compra(false,"1");
		Compra compra2 = new Compra(false,"2");
		Compra compra3 = new Compra(false,"3");
		
		ArrayList<Compra> historial =new ArrayList<Compra> ();
		historial.add(compra1);
		historial.add(compra2);
		historial.add(compra3);
		
		Cliente clientePrueba = new Cliente("cliente","password",historial);
		ArrayList<Cliente> clientesPrueba = new ArrayList<Cliente>();
		clientesPrueba.add(clientePrueba);
		escritorUsuario.escribirClientes(clientesPrueba); // realizacion
	}
}