package Principal;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Usuarios.Cliente;
import Usuarios.Compra;
import Usuarios.Empleado;
import Usuarios.Especialidad;
import Usuarios.GestorPersonas;
import Usuarios.Persona;
import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.Espectaculo;
import atracciones_y_espectaculos.GestorAtracciones;
import atracciones_y_espectaculos.RestriccionSalud;
import atracciones_y_espectaculos.Restriccion_clima;
import persistencia.Escritor_Atracciones_Y_Espectaculos;
import persistencia.Escritor_Usuarios;
import persistencia.LectorLugarTrabajo;
import persistencia.LectorRerstriccion_clima;
import persistencia.LectorRestriccionSalud;
import persistencia.LectorUsuario;
import persistencia.Lector_Atracciones_Y_Espectaculos;
import vista.VistaUsuario;
import vista.vistaAdmin;
import vista.vistaEmpleado;
import vista.vistaGeneral;


public class ParquePrincipal{
	
	

    private static ArrayList<Cliente> clientes;
    
    // Atracciones y Espectaculos
    private Escritor_Atracciones_Y_Espectaculos escritorAYE;
	private Lector_Atracciones_Y_Espectaculos lectorAYE;
	private LectorRerstriccion_clima lectorRestriccionClima;
	private LectorRestriccionSalud lectorRestriccionSalud;
	private LectorLugarTrabajo lectorLugarTrabajo;
	private GestorAtracciones gestorAtracciones;
	// usuarios
	private LectorUsuario lectorU;
	private Escritor_Usuarios escritorU;
	private GestorPersonas gestorUsuarios;
    public ParquePrincipal() throws IOException
    {
    	
    	// atracciones y Espectaculos
    	this.escritorAYE = new Escritor_Atracciones_Y_Espectaculos();
		this.lectorAYE = new Lector_Atracciones_Y_Espectaculos();
		this.lectorRestriccionClima= new LectorRerstriccion_clima();
		this.lectorRestriccionSalud= new LectorRestriccionSalud();
		this.lectorLugarTrabajo = new LectorLugarTrabajo();
		ArrayList<Atraccion> atracciones = lectorAYE.leerAtracciones(".\\src\\data\\atracciones_y_espectaculos.txt");
		ArrayList<Espectaculo> espectaculos = lectorAYE.leerEspectaculos(".\\src\\data\\Espectaculos.txt");
		ArrayList <Restriccion_clima> restriccionesClima = lectorRestriccionClima.leerRestriccion_clima();
		ArrayList<RestriccionSalud> restriccionesSalud = lectorRestriccionSalud.leerRestriccionSalud();
		this.gestorAtracciones = new GestorAtracciones(atracciones, espectaculos, restriccionesClima, restriccionesSalud);
		
		// Usuarios
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
		this.gestorUsuarios = new GestorPersonas(clientes,empleados,personas,especialidades);
		
	}
    	
    	
    
    public static void main(String[] args) throws IOException {
    	ParquePrincipal parque = new ParquePrincipal();
        Scanner scanner = new Scanner(System.in);
        LectorUsuario lector = new LectorUsuario();
        
        try {
            clientes = lector.leerClientes("./data/Clientes.txt");
        } catch (IOException e) {
            System.err.println("No se pudo cargar la información de los usuarios.");
            return;
        }

        System.out.println("Bienvenido al Sistema del Parque de Diversiones");

        boolean loginExitoso = false;
        while (!loginExitoso) {
            System.out.print("Ingrese su usuario: ");
            String login = scanner.nextLine();
            
            
            //System.out.println(usuario.getLogin());
            
            System.out.print("Ingrese su contraseña: ");
            String password = scanner.nextLine();
            
            
            if (login.equals("admin") && password.equals("admin")) {
                vistaAdmin admin = new vistaAdmin();
                admin.verMenu();
                loginExitoso = true;
            } else {
            	
            	Persona usuario = parque.gestorUsuarios.getPersona(login);
            	if(usuario!= null) {
            	if(password.equals(usuario.getPassword())){
            		if(usuario instanceof Empleado) {
            		vistaEmpleado vistaE = new vistaEmpleado();
            		vistaE.verMenu();
            		}
            		if(usuario instanceof Cliente) {
            			VistaUsuario vistaCliente = new VistaUsuario((Cliente) usuario, parque);
                        vistaCliente.verMenu();
                		}
            	
            	}else {
            		System.out.println("   ");
            		System.out.println("    --Username or password does not exist--");
            		System.out.println("   ");
            		System.out.println("   ");
            	}
            	}else {
            		System.out.println("   ");
            		System.out.println("    --Username or password does not exist--");
            		System.out.println("   ");
            		System.out.println("   ");
            	}
            }
            
        }
    }
    public void printAtraEsp () {
    	ArrayList<Atraccion> atracciones = gestorAtracciones.getAtracciones();
    	ArrayList<Espectaculo> espectaculos = gestorAtracciones.getEspectaculos();
    	
    	System.out.println("Atracciones: ");
    	for(Atraccion atraccion: atracciones) {
    		System.out.println("   "+atraccion.getNombre());
    	}
    	System.out.println("Espectaculos: ");
    	for(Espectaculo espectaculo: espectaculos) {
    		System.out.println("   "+espectaculo.getNombre());
    	}
    	
    }
    public void printCompras(Cliente persona) {
    	ArrayList<Compra> historial = persona.historial;
    	int x = 1;
    	for(Compra compra : historial) {
    		System.out.println("Compra numero  "+x+";");
    		System.out.println(" ");
    		System.out.println("Identificador de tiquete: ");
    		System.out.println("   "+ compra.getNumeroTiquete());
    		
    		System.out.println("Validador: ");
    		if(compra.isFechaVencida()==false){
    				System.out.println("   No esta usado");
    		}else {
    			System.out.println("   Si esta vencida");
    		}
    		System.out.println(" ");
    		System.out.println(" ");
    		System.out.println(" ");
    		x++;
    		
    		
    	}
    }
    

    private static Cliente autenticarCliente(String login, String password) {
        for (Cliente cliente : clientes) {
            if (cliente.getLogin().equals(login) && cliente.getPassword().equals(password)) {
                return cliente;
            }
        }
        return null;
    }
}  