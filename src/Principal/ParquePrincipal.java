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
import atracciones_y_espectaculos.Cultural;
import atracciones_y_espectaculos.Espectaculo;
import atracciones_y_espectaculos.GestorAtracciones;
import atracciones_y_espectaculos.Mecanica;
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
                vistaAdmin admin = new vistaAdmin(parque);
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
    public void printComprasE(Empleado persona) {
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
    
    public void crearAtraccion () {
    	Atraccion atraccion;
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Mecanica o Cultural?");
    	String atra = scanner.nextLine();
    	System.out.println("nombre:");
    	String nombre = scanner.nextLine();
    	
    	System.out.println("Lugar:");
    	String lugar = scanner.nextLine();
    	
    	System.out.println("Cupo Maximo:");
    	int cupoMaximo = Integer.parseInt(scanner.nextLine());
    	
    	ArrayList<Restriccion_clima> restriccionClima = new ArrayList<Restriccion_clima>();
    	ArrayList<Restriccion_clima> restriccionClimaParque = gestorAtracciones.getRestriccionesClima();
    	System.out.println("Estas son las restricciones de clima vigentes: ");
    	for(Restriccion_clima restriccion: restriccionClimaParque) {
    		System.out.println("   - "+restriccion.getTipo());
    	}
    	System.out.println("Seleccione una: ");
    	String nombreRestriccion = scanner.nextLine();
    	for(Restriccion_clima restriccion: restriccionClimaParque) {
    		if(nombreRestriccion.equals(restriccion.getTipo())) {
    			restriccionClima.add(restriccion);
    		}
    	}
    	System.out.println("Numero de Empleados:");
    	int numeroEmpleados = Integer.parseInt(scanner.nextLine());
    	
    	System.out.println("Funcionando: ");
    	boolean funcionando = Boolean.parseBoolean(scanner.nextLine());
    	
    	if (atra.equals("Mecanica")){
    		System.out.println("Altura minima; ");
    		double minAltura = Double.parseDouble(scanner.nextLine());
    		
    		System.out.println("Altura maxima");
    		double maxAltura = Double.parseDouble(scanner.nextLine());
    		
    		System.out.println("Peso minimo: ");
    		double minPeso = Double.parseDouble(scanner.nextLine());
    		
    		System.out.println("Peso maximo: ");
    		double maxPeso = Double.parseDouble(scanner.nextLine());
    		
    		ArrayList<RestriccionSalud> saludes = gestorAtracciones.getRestriccionesSalud();
    		System.out.println("Restriccion de salud vigentes: ");
    		for(RestriccionSalud restriccion: saludes) {
    			System.out.println("   - "+restriccion.getNombre());
    		}
    		System.out.println("Restriccion Salud: ");
    		String nombreRestriccionSalud = scanner.nextLine();
    		RestriccionSalud restriccionSalud = null;
        	for(RestriccionSalud restriccion: saludes) {
        		if(restriccion.getNombre().equals(nombreRestriccionSalud)) {
        			restriccionSalud= restriccion;
        			
        		}
        	}
        	System.out.println("Nivel Riesgo: ");
    		String nivelRiesgo = scanner.nextLine();
        	
    	 atraccion = new Mecanica(nombre, lugar,numeroEmpleados,funcionando,cupoMaximo,restriccionClima, minAltura,maxAltura,minPeso,maxPeso,nivelRiesgo,restriccionSalud);
    	
    	}else {
    		System.out.println("Edad minima: ");
    		int edadMin = Integer.parseInt(scanner.nextLine());
    		
    		 atraccion = new Cultural(nombre, lugar,numeroEmpleados,funcionando,cupoMaximo,restriccionClima,edadMin);
    		 
    		
    	}
    	
    	gestorAtracciones.agregarAtraccion(gestorAtracciones.getAtracciones(), atraccion);
    	ArrayList<Atraccion>  atraccionesActualizada = gestorAtracciones.getAtracciones();
		 atraccionesActualizada.add(atraccion);
		 escritorAYE.escribirAtracciones(atraccionesActualizada);
		 scanner.close();
    	}
    public void verRestriccionClima() {
    	
    	ArrayList<Restriccion_clima> restricciones = gestorAtracciones.getRestriccionesClima();
    	for(Restriccion_clima restriccion : restricciones) {
    		System.out.println("Restricciones clima: "+restriccion.getTipo());
    		ArrayList<String> atraNombres = restriccion.getAtracciones();
    		ArrayList<String> espNombres = restriccion.getEspectaculos();
    		for(String atraccion : atraNombres) {
    			System.out.println("   - " + atraccion);
    		}
    		for(String espectaculo: espNombres) {
    			System.out.println("   - "+espectaculo);
    		}
    	}
    }
    public void verRestriccionSalud() {
    	ArrayList<RestriccionSalud> restricciones = gestorAtracciones.getRestriccionesSalud();
    	for(RestriccionSalud restriccion : restricciones) {
    		System.out.println("Restricciones clima: "+restriccion.getNombre());
    		ArrayList<String> atraNombres = restriccion.getAtraccionesMecanica();
    		
    		for(String atraccion : atraNombres) {
    			System.out.println("   - " + atraccion);
    		}
    	}
    }
    public void crearRestriccionClima(){
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("Nombre: ");
    	String nombre = scanner.nextLine();
    	System.out.println("Atracciones");
    	ArrayList<String> atracciones= new ArrayList<String>();
    	ArrayList<String> espectaculos= new ArrayList<String>();
    	boolean atracBool = true;
    	boolean especBool = true;
    	while(atracBool) {
    		System.out.println("Si quiere terminar, escriba terminar");
    		System.out.println("Nombre de atraccion: ");
    		String whileAtracciones = scanner.nextLine();
    		if (!whileAtracciones.equals("terminar")) {
    			atracciones.add(whileAtracciones);
    			
    		}else {
    			atracBool = false;
    		}
    	}
    	System.out.println("Espectaculos");
    	while(especBool) {
    		System.out.println("Si quiere terminar, escriba terminar");
    		System.out.println("Nombre de espectaculo: ");
    		String whileEspectaculo = scanner.nextLine();
    		if (!whileEspectaculo.equals("terminar")) {
    			espectaculos.add(whileEspectaculo);
    			
    		}else {
    			especBool = false;
    		}
    	}
    	Restriccion_clima restriccion = new Restriccion_clima(nombre, atracciones,espectaculos);
    	ArrayList<Restriccion_clima> restricciones = gestorAtracciones.getRestriccionesClima();
    	
    	
    	
    	
    	
    	
    	scanner.close();
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