package persistencia;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.Cultural;
import atracciones_y_espectaculos.Espectaculo;
import atracciones_y_espectaculos.Mecanica;
import atracciones_y_espectaculos.Restriccion_clima;
public class Escritor_Atracciones_Y_Espectaculos {
	public Escritor_Atracciones_Y_Espectaculos() {
		
	}

	public void escribirAtracciones(ArrayList<Atraccion> atracciones) {
	    try {
	        File carpeta = new File("./data/");
	        if (!carpeta.exists()) {
	            carpeta.mkdirs();
	        }
	        PrintWriter escritor = new PrintWriter(new File("./data/Atracciones_y_Espectaculos.txt"));
	        
	        for (Atraccion atraccion : atracciones) {
	            String linea = atraccion.getCupoMaximo() + "--" +
	                          atraccion.getLugar() + "--" +
	                          atraccion.getMinEdad() + "--" +
	                          atraccion.getNombre() + "--" +
	                          atraccion.getNumeroEmpleados() + "--" +
	                          atraccion.isFuncionando();
	            
	            // Restricciones de clima
	            List<Restriccion_clima> restriccionesClima = atraccion.getRestriccionClima();
	            linea = linea + "--restricion_clima--";
	            for(Restriccion_clima restriccionClima: restriccionesClima) {
	                linea = linea + "--"+ restriccionClima.getTipo();
	            }

	            if (atraccion instanceof Mecanica) {
	                Mecanica mecanica = (Mecanica) atraccion;
	                linea = "MECANICA--" + linea + "--" +
	                        mecanica.getMaxAltura() + "--" +
	                        mecanica.getMaxPeso() + "--" +
	                        mecanica.getMinAltura() + "--" +
	                        mecanica.getMinPeso() + "--" +
	                        mecanica.getNivelRiesgo() + "--" +
	                        "restricion_salud--" + mecanica.getRestriccionSalud().getNombre();
	                
	            } else if (atraccion instanceof Cultural) {
	                Cultural cultural = (Cultural) atraccion;
	                linea = "CULTURAL--" + linea + "--" +
	                        cultural.getEdadMin();
	            }

	            escritor.println(linea);
	        }
	        escritor.close();
	    } catch (IOException e) {
	        System.err.println("Error al escribir el archivo: " + e.getMessage());
	    }
	}
	public void escribirEspectaculos(ArrayList<Espectaculo> espectaculos) {
		try {
	        File carpeta = new File("./data/");
	        if (!carpeta.exists()) {
	            carpeta.mkdirs();
	        }
	        PrintWriter escritor = new PrintWriter(new File("./data/Atracciones_y_Espectaculos.txt"));
	        
	        for (Espectaculo espectaculo : espectaculos) {
	        	ArrayList<LocalDateTime> fechas = espectaculo.getFechas();
	        	ArrayList<LocalDateTime> horarios = espectaculo.getHorario();
	            String linea = "fechas";
	            for (LocalDateTime fecha : fechas) {
	            	linea = linea+"--"+fecha;
	            }
	            linea = linea+"horario";
	            for (LocalDateTime horario : horarios) {
	            	linea = linea+"--"+horario;
	            }
	            escritor.println(linea);
	        }
	        escritor.close();
	    } catch (IOException e) {
	        System.err.println("Error al escribir el archivo: " + e.getMessage());
	    }
	}
}