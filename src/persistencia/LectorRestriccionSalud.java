package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import atracciones_y_espectaculos.RestriccionSalud;

public class LectorRestriccionSalud {

	public LectorRestriccionSalud() {
	}
	
	public ArrayList<RestriccionSalud> leerRestriccionSalud() throws IOException {
	    // Usar File.separator para mayor portabilidad
	    File archivo = new File(".\\src\\data\\restriccionesSalud.txt");
	    
	    if (!archivo.exists()) {
	        try (PrintWriter writer = new PrintWriter(archivo)) {
	            System.out.println("Archivo no encontrado, creando archivo vacío...");
	        }
	        
	    }
	    ArrayList<RestriccionSalud> restricciones = new ArrayList<>();

	    
	   


	    try (BufferedReader lector = new BufferedReader(new FileReader(".\\src\\data\\restriccionesSalud.txt"))) {
	        String linea;
	        while ((linea = lector.readLine()) != null) {
	            String[] partes = linea.split("--");
	            
	            
	            //System.out.println((partes.length)+" --Longitud");
	            if (partes.length != 2) {
	                System.err.println("Formato incorrecto en línea: " + linea);
	                continue;
	            }
	            
	           // System.out.println(partes[0]);
	            String tipo = partes[0];
	            String[] atraccionesString = partes[1].split(",");
	            ;
	            
	            ArrayList<String> atracciones = new ArrayList<>();
	            
	            for(String atraccion : atraccionesString) {
	                atracciones.add(atraccion.trim()); // trim() para eliminar espacios
	            }
	            
	            RestriccionSalud restriccion = new RestriccionSalud(tipo, atracciones);
	            restricciones.add(restriccion);
	        }
	    } catch (IOException e) {
	        System.err.println("Error al leer el archivo: " + e.getMessage());
	        throw e;
	    }

	    return restricciones;
	}

	
	
}
