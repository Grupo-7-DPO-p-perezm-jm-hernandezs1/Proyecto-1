package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import atracciones_y_espectaculos.LugarTrabajo;


public class LectorLugarTrabajo {
	public LectorLugarTrabajo(){
		
	}
	public ArrayList<LugarTrabajo> leerLugarTrabajo() throws IOException {
	    // Usar File.separator para mayor portabilidad
	    File archivo = new File(".\\src\\data\\LugarTrabajo.txt");
	    
	    if (!archivo.exists()) {
	        try (PrintWriter writer = new PrintWriter(archivo)) {
	            System.out.println("Archivo no encontrado, creando archivo vacío...");
	        }
	    }
	    ArrayList<LugarTrabajo> lugaresTrabajo = new ArrayList<>();

	   


	    try (BufferedReader lector = new BufferedReader(new FileReader(".\\src\\data\\LugarTrabajo.txt"))) {
	        String linea;
	        while ((linea = lector.readLine()) != null) {
	            String[] partes = linea.split("--");
	            
	            
	            //System.out.println((partes.length)+" --Longitud");
	            if (partes.length != 3) {
	                System.err.println("Formato incorrecto en línea: " + linea);
	                continue;
	            }
	            
	           LugarTrabajo lugarTrabajo = new LugarTrabajo(partes[0], Boolean.parseBoolean(partes[1]), Boolean.parseBoolean(partes[2]));
	        
	        lugaresTrabajo.add(lugarTrabajo);
	        
	        }
	    } catch (IOException e) {
	        System.err.println("Error al leer el archivo: " + e.getMessage());
	        throw e;
	    }

	    return lugaresTrabajo;
	}

}
