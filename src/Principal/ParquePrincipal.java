package Principal;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import Turnos.DiaTurnos;
import Turnos.GestorTurnos;
import Turnos.SemanaTurnos;
import Turnos.TipoTurno;
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
import tiquetes.Categoria;
import tiquetes.GestorTiquete;
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
	public GestorAtracciones gestorAtracciones;
	// usuarios
	private LectorUsuario lectorU;
	private Escritor_Usuarios escritorU;
	public GestorPersonas gestorUsuarios;
	//Tiquetes
	public GestorTiquete gestorTiquetes;
	//Turnos
	public GestorTurnos gestorTurnos;
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
		
		//Tiquetes
		this.gestorTiquetes = new GestorTiquete();
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
    
    public void crearAtraccion () throws IOException {
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
    	
    	System.out.println("Funcionando (true o false): ");
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
		escritorAYE.escribirAtracciones(atraccionesActualizada);
		 
    	}
    public void crearAtraccionMecanica(String nombre, String lugar, String cupoMax, 
            String restriccionesClima, String numEmpleados, 
            String funcionando, String minAltura, String maxAltura, 
            String minPeso, String maxPeso, String restriccionSalud, 
            String nivelRiesgo) throws IOException, IllegalArgumentException {

// Validación de campos obligatorios
if (nombre == null || nombre.trim().isEmpty()) {
throw new IllegalArgumentException("El nombre no puede estar vacío");
}
if (lugar == null || lugar.trim().isEmpty()) {
throw new IllegalArgumentException("El lugar no puede estar vacío");
}

try {
// Parseo de valores numéricos con validación
int intCupoMax = parseAndValidatePositiveInt(cupoMax, "Cupo Máximo");
int intNumEmpleados = parseAndValidatePositiveInt(numEmpleados, "Número de Empleados");
boolean booleanFuncionando = parseBoolean(funcionando);

double intMinAltura = parseAndValidateDouble(minAltura, "Altura Mínima");
double intMaxAltura = parseAndValidateDouble(maxAltura, "Altura Máxima");
double intMinPeso = parseAndValidateDouble(minPeso, "Peso Mínimo");
double intMaxPeso = parseAndValidateDouble(maxPeso, "Peso Máximo");

// Validar que los máximos sean mayores que los mínimos
if (intMaxAltura <= intMinAltura) {
throw new IllegalArgumentException("La altura máxima debe ser mayor que la altura mínima");
}
if (intMaxPeso <= intMinPeso) {
throw new IllegalArgumentException("El peso máximo debe ser mayor que el peso mínimo");
}

// Procesar restricciones de clima
ArrayList<Restriccion_clima> restriccionClimaFinal = new ArrayList<>();
ArrayList<Restriccion_clima> restriccionesClimaObj = gestorAtracciones.getRestriccionesClima();

if (restriccionesClima != null && !restriccionesClima.trim().isEmpty()) {
for (Restriccion_clima restriccion : restriccionesClimaObj) {
if (restriccionesClima.equals(restriccion.getTipo())) {
restriccionClimaFinal.add(restriccion);
}
}
}

// Buscar restricción de salud
RestriccionSalud restriccionSaludfinal = null;
if (restriccionSalud != null && !restriccionSalud.trim().isEmpty()) {
restriccionSaludfinal = gestorAtracciones.buscarRestriccionSalud(restriccionSalud);
if (restriccionSaludfinal == null) {
throw new IllegalArgumentException("Restricción de salud no encontrada: " + restriccionSalud);
}
}

// Validar nivel de riesgo
if (nivelRiesgo == null || nivelRiesgo.trim().isEmpty()) {
throw new IllegalArgumentException("El nivel de riesgo no puede estar vacío");
}

// Crear la atracción
Mecanica atraccion = new Mecanica(
nombre.trim(), 
lugar.trim(),
intNumEmpleados,
booleanFuncionando,
intCupoMax,
restriccionClimaFinal, 
intMinAltura,
intMaxAltura,
intMinPeso,
intMaxPeso,
nivelRiesgo.trim(),
restriccionSaludfinal
);

// Agregar y guardar la atracción
gestorAtracciones.agregarAtraccion(gestorAtracciones.getAtracciones(), atraccion);
ArrayList<Atraccion> atraccionesActualizada = gestorAtracciones.getAtracciones();
escritorAYE.escribirAtracciones(atraccionesActualizada);

} catch (NumberFormatException e) {
throw new IllegalArgumentException("Formato numérico inválido: " + e.getMessage());
}
}

//Métodos auxiliares para validación
private int parseAndValidatePositiveInt(String value, String fieldName) {
if (value == null || value.trim().isEmpty()) {
throw new IllegalArgumentException(fieldName + " no puede estar vacío");
}
int num = Integer.parseInt(value.trim());
if (num <= 0) {
throw new IllegalArgumentException(fieldName + " debe ser un número positivo");
}
return num;
}

private double parseAndValidateDouble(String value, String fieldName) {
if (value == null || value.trim().isEmpty()) {
throw new IllegalArgumentException(fieldName + " no puede estar vacío");
}
return Double.parseDouble(value.trim());
}

private boolean parseBoolean(String value) {
if (value == null) return false;
String trimmed = value.trim().toLowerCase();
return trimmed.equals("true") || trimmed.equals("verdadero") || trimmed.equals("1") || trimmed.equals("sí");
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
    public String revisarUsuario(String username, String password) {
    	String respuesta = "ninguno";
    	Persona persona = gestorUsuarios.getPersona(username);
    	if(username.equals("admin")|| password.equals("admin")) {
    		respuesta = "admin";
    	}
    	if(persona != null) {
    		if(persona.getPassword().equals(password)) {
    			
    		
    		if (persona instanceof Cliente) {
    			respuesta = "cliente";
    		}
    		if(persona instanceof Empleado) {
    			respuesta = "empleado";
    		}
    	}
    	}
    	
    	return respuesta;
    }
    public ArrayList<String> atraccionesNombre(){
    	ArrayList<Atraccion> atracciones = gestorAtracciones.getAtracciones();
    	ArrayList <String> respuesta = new ArrayList<String>();
    	for(Atraccion atraccion : atracciones) {
    		respuesta.add(atraccion.getNombre());
    	}
    	
		return respuesta;
    	
    }
    public ArrayList<String> espectaculosNombre(){
    	ArrayList<Espectaculo> espectaculos = gestorAtracciones.getEspectaculos();
    	ArrayList<String> respuesta = new ArrayList<String>();
    	for(Espectaculo espectaculo : espectaculos) {
    		respuesta.add(espectaculo.getNombre());
    	}
    	
    	return respuesta;
    }

    public ArrayList<ArrayList<String>> verComprasCliente(Cliente cliente) {
        ArrayList<ArrayList<String>> respuesta = new ArrayList<>(); 
        ArrayList<Compra> historial = cliente.getHistorial();
        
        for (Compra compra : historial) {
            ArrayList<String> unidad = new ArrayList<>();
            unidad.add(compra.getNumeroTiquete()); 
            String usado = String.valueOf(compra.isFechaVencida()); 
            unidad.add(usado);  
            respuesta.add(unidad); 
        }
        
        return respuesta;
    }
    
    public ArrayList<String> restriccionesClimaString(){
    	ArrayList <String> respuesta = new ArrayList <String>();
    	ArrayList<Restriccion_clima> restriccionClima = gestorAtracciones.getRestriccionesClima();
    	for(Restriccion_clima restriccion: restriccionClima) {
    		respuesta.add(restriccion.getTipo());
    	}
    	return respuesta;
    }
    
    
    public ArrayList<String> restriccionSaludString(){
    	ArrayList <String> respuesta = new ArrayList <String>();
    	ArrayList<RestriccionSalud> restriccionSalud = gestorAtracciones.getRestriccionesSalud();
    	for(RestriccionSalud restriccion: restriccionSalud) {
    		respuesta.add(restriccion.getNombre());
    	}
    	return respuesta;
    }
    
    public void crearRestriccionClimaInter(String nombre, ArrayList<String> atracciones, ArrayList<String> espectaculos ) {
    	Restriccion_clima restriccion = new Restriccion_clima(nombre, atracciones, espectaculos);
    	gestorAtracciones.agregarRestriccionClima(restriccion);
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
    	gestorAtracciones.getRestriccionesClima().add(restriccion);
    	
    	
    	
    	
    	
    	
    	
    	
    }
    private static Cliente autenticarCliente(String login, String password) {
        for (Cliente cliente : clientes) {
            if (cliente.getLogin().equals(login) && cliente.getPassword().equals(password)) {
                return cliente;
            }
        }
        return null;
    }
    public String comprarTiquetesBas() {
    	return gestorTiquetes.comprarTiqueteBasico();
    }
    public String comprarTiquetesGen(String cat) {
    	if (cat.equals("Diamante")) {
    		Categoria categoria = Categoria.Diamante;
    		return gestorTiquetes.comprarTiqueteGeneral(categoria);
    	}
    	else if (cat.equals("Familiar")) {
    		Categoria categoria = Categoria.Familiar;
    		
    		return gestorTiquetes.comprarTiqueteGeneral(categoria);
    	}
    	else if (cat.equals("Oro")) {
    		Categoria categoria = Categoria.Oro;
    		
    		return gestorTiquetes.comprarTiqueteGeneral(categoria);
    	}
    	else {
    		return "-1";
    	}
    }
    public String comprarTiquetesIndividual(String atraccionABuscar) {
    	Atraccion atra = gestorAtracciones.buscarAtraccionPorNombre(atraccionABuscar);
    	if (atra != null){
    		return gestorTiquetes.comprarTiqueteIndividual(atra);
    	}
    	else {
    		return "-1";
    	}
    	
    }
    public String comprarTiquetesTemp(String cat, String temporada) {
    	Categoria categoria = Categoria.Familiar;
    	if (cat.toLowerCase().equals("diamante")) {
    		categoria = Categoria.Diamante;
    	}
    	else if (cat.toLowerCase().equals("oro") ) {
    		categoria = Categoria.Familiar;
    	}
    	else if (cat.toLowerCase().equals("familiar")) {
    		categoria = Categoria.Oro;
    	}
    	else {
    		return "-1";
    	}
        LocalDate PrimavIni = LocalDate.of(2025, 3, 21);
        LocalDate PrimavFin = LocalDate.of(2025, 6, 20);

        LocalDate VeranoIni = LocalDate.of(2025, 6, 21);
        LocalDate VeranoFin = LocalDate.of(2025, 9, 22);

        LocalDate OtonioIni = LocalDate.of(2025, 9, 23);
        LocalDate OtonioFin = LocalDate.of(2025, 12, 20);

        LocalDate InviernoIni = LocalDate.of(2025, 12, 21);
        LocalDate InviernoFin = LocalDate.of(2026, 3, 20);
    	
    	if(temporada.toLowerCase().equals("otoño")) {
    		
    		return gestorTiquetes.comprarTiqueteTemporada(OtonioIni, OtonioFin, categoria);
    		
    	}
    	else if(temporada.toLowerCase().equals("verano")) {
    		return gestorTiquetes.comprarTiqueteTemporada(VeranoIni, VeranoFin, categoria);
    	}
    	else if(temporada.toLowerCase().equals("invierno")) {
    		return gestorTiquetes.comprarTiqueteTemporada(InviernoIni, InviernoFin, categoria);
    	}
    	else if(temporada.toLowerCase().equals("primavera")) {
    		return gestorTiquetes.comprarTiqueteTemporada(PrimavIni, PrimavFin, categoria);
    	}
    	else{
    		return "-2";
    	}
    }
   
    public List<String> preciosTiquetes() {
    	
		return gestorTiquetes.getPrecios();
    	
    }
    
    public String comprarFastPass(String fecha) {
    	
    	try {
            String[] partes = fecha.split(",");
            int anio = Integer.parseInt(partes[0].trim());
            int mes = Integer.parseInt(partes[1].trim());
            int dia = Integer.parseInt(partes[2].trim());

            LocalDate fechaFastPass = LocalDate.of(anio, mes, dia);

         
            return gestorTiquetes.comprarFastPass(fechaFastPass);
        } catch (Exception e) {
            System.out.println("Fecha inválida. Asegúrese de usar el formato año,mes,día");
        }
		return "-1";
    }



	public Map<String, DiaTurnos> verTurnos() {
		
		SemanaTurnos semana = (SemanaTurnos) gestorTurnos.getSemana();
		Map<String, DiaTurnos> dias = new LinkedHashMap<>();
		dias.put("Lunes", semana.getLunes());
		dias.put("Martes", semana.getMartes());
		dias.put("Miércoles", semana.getMiercoles());
		dias.put("Jueves", semana.getJueves());
		dias.put("Viernes", semana.getViernes());
		dias.put("Sábado", semana.getSabado());
		dias.put("Domingo", semana.getDomingo());

		return dias;
		
	}



	public void crearTurno(Empleado empleado, Object destino, String tipoStr, String dia, String franja) {
        TipoTurno tipo;
        if (tipoStr.equalsIgnoreCase("atraccion")) {
            tipo = TipoTurno.ATRACCION;
        } else if (tipoStr.equalsIgnoreCase("lugar_trabajo")) {
            tipo = TipoTurno.LUGAR_TRABAJO;
        } else {
            throw new IllegalArgumentException("Tipo de turno inválido: " + tipoStr);
        }

        gestorTurnos.guardarTurnoSemana(tipo, destino, empleado, dia, franja);
        System.out.println("Turno asignado exitosamente a " + empleado.getLogin());
    }
}  