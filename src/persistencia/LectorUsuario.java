package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import Usuarios.Cliente;
import Usuarios.Compra;
import Usuarios.Empleado;
import Usuarios.Especialidad;

public class LectorUsuario {

    public LectorUsuario() {
    	
    
    }

    public ArrayList<Cliente> leerClientes(String rutaArchivo) throws IOException {
        File archivo = new File(rutaArchivo);
       
        if (!archivo.exists()) {
            try (PrintWriter writer = new PrintWriter(archivo)) {
                // Crea un archivo vacío si no existe
                System.out.println("Archivo no encontrado, creando archivo vacío...");
            }
        }

        ArrayList<Cliente> clientes = new ArrayList<>();

        // Verificar si el archivo está vacío antes de intentar leer
        if (archivo.length() == 0) {
            System.out.println("El archivo está vacío. Esperando información...");
            return clientes;  
        }

        
      
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("--");

                
                // Validar que al menos haya login y password
                if (partes.length >= 2) {
                    String login = partes[0].trim();
                    String password = partes[1].trim();
                    ArrayList<Compra> historial = new ArrayList<Compra>();
                 
                    String[] comprasString = partes[2].split("compra:");
                    for(String compraS: comprasString) {
                    	String[] compra= compraS.split(",");
                    	boolean vencido =  Boolean.parseBoolean(compra[0]);
                    	String identificador = compra[1];
                    	Compra finalCompra = new Compra(vencido, identificador);
                    	historial.add(finalCompra);
                    	
                    }
                    Cliente cliente = new Cliente(login, password, historial);
                    System.out.println(cliente.historial);

                    clientes.add(cliente);
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            throw e; // test donde la ruta no exista
        }

        return clientes;
    } 
    public ArrayList<Empleado> leerEmpleados(String rutaArchivo) throws IOException {
        ArrayList<Empleado> empleados = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        
        if (!archivo.exists()) {
            try (PrintWriter writer = new PrintWriter(archivo)) {
                System.out.println("Archivo no encontrado, creando archivo vacío...");
            }
        }
        
        if (archivo.length() == 0) {
            System.out.println("El archivo está vacío. Esperando información...");
            return empleados;
        }
        
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("--");
                
                if (partes.length >= 6) { 
                    String usuario = partes[0].trim();
                    String password = partes[1].trim();
                    boolean cocinero = Boolean.parseBoolean(partes[2].trim());
                    String nombre;
                    
                    nombre  = partes[3];
                    
                    
                    ArrayList<String> atracciones = new ArrayList<String>();
                    System.out.println(partes[4]);
                    if(!partes[4].isEmpty()) {
                    	 String[] lista=partes[4].split(",");
                    	for(String nombres: lista) {
                    		atracciones.add(nombres);
                    }
                    }
                    ArrayList<String> lugarestrabajo = new ArrayList<String>();
                    if(!partes[5].isEmpty()) {
                    String[] lugaresTrabajoS = partes[5].split(",");
                    for(String nombreS: lugaresTrabajoS) {
                    	lugarestrabajo.add(nombreS);
                    }
                    Especialidad especialidad = new Especialidad(nombre,atracciones,lugarestrabajo);
                    Empleado empleado= new Empleado(usuario,password,cocinero,especialidad);
                    empleados.add(empleado);
                    }
                }
               
            }
        }
        
      

    	
    	
    	
    	return empleados;
    	
    }
}