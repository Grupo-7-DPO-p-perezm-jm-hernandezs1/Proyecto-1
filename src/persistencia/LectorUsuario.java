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
                
                if (partes.length >= 4) { 
                    String usuario = partes[0].trim();
                    String password = partes[1].trim();
                    boolean cocinero = Boolean.parseBoolean(partes[2].trim());
                    
                    
                    String[] especialidadData = partes[3].split("\\.\\.\\.");
                    if (especialidadData.length >= 3) {
                        String nombreEspecialidad = especialidadData[0].trim();
                        
                       
                        ArrayList<String> atracciones = new ArrayList<>();
                        if (!especialidadData[1].trim().equalsIgnoreCase("")) {
                            String[] attrs = especialidadData[1].split(",");
                            for (String attr : attrs) {
                                atracciones.add(attr.trim());
                            }
                        }
                        
                        
                        ArrayList<String> lugares = new ArrayList<>();
                        if (!especialidadData[2].trim().equalsIgnoreCase("")) {
                            String[] locs = especialidadData[2].split(",");
                            for (String loc : locs) {
                                lugares.add(loc.trim());
                            }
                        }
                        
                        Especialidad especialidad = new Especialidad(
                            nombreEspecialidad, 
                            atracciones, 
                            lugares
                        );
                        
                        empleados.add(new Empleado(usuario, password, cocinero, especialidad));
                    }
                }
            }
        }
        
      

    	
    	
    	
    	return empleados;
    	
    }
}