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
            return clientes;  // Devuelve la lista vacía sin procesar
        }

        // Si el archivo tiene contenido, procedemos a leer
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("--");

                // Validar que al menos haya login y password
                if (partes.length >= 2) {
                    String login = partes[0].trim();
                    String password = partes[1].trim();

                    Cliente cliente = new Cliente(login, password, new ArrayList<>());

                    // Leer pares de [fechaVencida, numeroTiquete]
                    for (int i = 2; i + 1 < partes.length; i += 2) {
                        try {
                            boolean fechaVencida = Boolean.parseBoolean(partes[i].trim());
                            String numeroTiquete = partes[i + 1].trim();
                            
                            Compra compra = new Compra(fechaVencida, numeroTiquete);
                            cliente.getHistorial().add(compra);
                        } catch (Exception e) {
                            System.err.println("Error al procesar compra para cliente " + login + ": " + e.getMessage());
                        }
                    }

                    clientes.add(cliente);
                } else {
                    System.err.println("Línea inválida: " + linea);
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            throw e;
        }

        return clientes;
    }  // <-- ¡Aquí faltaba esta llave de cierre!
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
                        if (!especialidadData[1].trim().equalsIgnoreCase("nada")) {
                            String[] attrs = especialidadData[1].split(",");
                            for (String attr : attrs) {
                                atracciones.add(attr.trim());
                            }
                        }
                        
                        
                        ArrayList<String> lugares = new ArrayList<>();
                        if (!especialidadData[2].trim().equalsIgnoreCase("nada")) {
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
