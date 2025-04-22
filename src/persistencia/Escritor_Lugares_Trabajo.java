package persistencia;
import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import atracciones_y_espectaculos.LugarTrabajo;

public class Escritor_Lugares_Trabajo {
	Escritor_Lugares_Trabajo (){
		
	}
	public void escribirLugaresTrabajo(ArrayList<LugarTrabajo> lugaresTrabajo) {
	    try {
	    	File carpeta = new File("./data/");
	        if (!carpeta.exists()) {
	            carpeta.mkdirs();
	        }
	        PrintWriter escritor = new PrintWriter(new File("./data/LugarTrabajo.txt"));
	        for (LugarTrabajo lugarTrabajo : lugaresTrabajo) {
	        	String linea = lugarTrabajo.getNombre()+"--"+
	        					lugarTrabajo.isAbierto()+"--"+
	        					lugarTrabajo.isCocina();
	        	escritor.println(linea);
	        }
	        escritor.close();
	        
	        
	    } catch (IOException e) {
	        System.err.println("Error al escribir el archivo: " + e.getMessage());
	    }
	}
	    
}


