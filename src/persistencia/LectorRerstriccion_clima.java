package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import atracciones_y_espectaculos.Restriccion_clima;



public class LectorRerstriccion_clima {
	public LectorRerstriccion_clima(){
		
	}
	
	public ArrayList<Restriccion_clima> leerRestriccion_clima() throws IOException {
	    // Usar File.separator para mayor portabilidad
	    File archivo = new File(".\\src\\data\\restriccionesClima.txt");
	    
	    if (!archivo.exists()) {
	        try (PrintWriter writer = new PrintWriter(archivo)) {
	            System.out.println("Archivo no encontrado, creando archivo vacío...");
	        }
	    }
	    ArrayList<Restriccion_clima> restricciones = new ArrayList<>();

	   


	    try (BufferedReader lector = new BufferedReader(new FileReader(".\\src\\data\\restriccionesClima.txt"))) {
	        String linea;
	        while ((linea = lector.readLine()) != null) {
	            String[] partes = linea.split("\\.\\.\\.");
	            
	            
	           // System.out.println((partes.length)+" --Longitud");
	            if (partes.length != 3) {
	                System.err.println("Formato incorrecto en línea: " + linea);
	                continue;
	            }
	            
	           // System.out.println(partes[0]);
	            String tipo = partes[0];
	            String[] atraccionesString = partes[1].split(",");
	            String[] espectaculosString = partes[2].split(",");
	            
	            ArrayList<String> atracciones = new ArrayList<>();
	            ArrayList<String> espectaculos = new ArrayList<>();
	            
	            for(String atraccion : atraccionesString) {
	                atracciones.add(atraccion.trim()); // trim() para eliminar espacios
	            }
	            
	            for (String espectaculo : espectaculosString) {
	                espectaculos.add(espectaculo.trim());
	            }
	            
	            Restriccion_clima restriccion = new Restriccion_clima(tipo, atracciones, espectaculos);
	            restricciones.add(restriccion);
	        }
	    } catch (IOException e) {
	        System.err.println("Error al leer el archivo: " + e.getMessage());
	        throw e;
	    }

	    return restricciones;
	}
}
