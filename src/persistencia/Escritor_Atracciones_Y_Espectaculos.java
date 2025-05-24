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

	public void escribirAtracciones(ArrayList<Atraccion> atracciones) throws IOException {
	    // Crear directorio si no existe
	    File carpeta = new File("./data/");
	    if (!carpeta.exists()) {
	        carpeta.mkdirs();
	    }

	    try (PrintWriter escritor = new PrintWriter(new File("./data/atracciones_y_espectaculos.txt"))) {
	        for (Atraccion atraccion : atracciones) {
	            StringBuilder linea = new StringBuilder();

	            // Determinar tipo de atracción
	            if (atraccion instanceof Mecanica) {
	                linea.append("MECANICA--");
	            } else if (atraccion instanceof Cultural) {
	                linea.append("CULTURAL--");
	            }

	            // Datos básicos de la atracción
	            linea.append(atraccion.getCupoMaximo()).append("--")
	                .append(atraccion.getLugar()).append("--")
	                .append(atraccion.getNombre()).append("--")
	                .append(atraccion.getNumeroEmpleados()).append("--")
	                .append(atraccion.isFuncionando()).append("--");

	            // Restricciones de clima
	            ArrayList<Restriccion_clima> restriccionesClima = atraccion.getRestriccionClima();
	            for (Restriccion_clima restriccion : restriccionesClima) {
	                linea.append(restriccion.getTipo()).append("##");

	                // Atracciones afectadas
	                ArrayList<String> atraccionesAfectadas = restriccion.getAtracciones();
	                if (atraccionesAfectadas.isEmpty()) {
	                    linea.append(",");
	                } else {
	                    for (int j = 0; j < atraccionesAfectadas.size(); j++) {
	                        linea.append(atraccionesAfectadas.get(j));
	                        if (j < atraccionesAfectadas.size() - 1) {
	                            linea.append(",");
	                        }
	                    }
	                }
	                linea.append("##");

	                // Espectáculos afectados
	                ArrayList<String> espectaculosAfectados = restriccion.getEspectaculos();
	                if (espectaculosAfectados.isEmpty()) {
	                    linea.append(",");
	                } else {
	                    for (int j = 0; j < espectaculosAfectados.size(); j++) {
	                        linea.append(espectaculosAfectados.get(j));
	                        if (j < espectaculosAfectados.size() - 1) {
	                            linea.append(",");
	                        }
	                    }
	                }
	            }

	            // Datos específicos según el tipo
	            if (atraccion instanceof Mecanica) {
	                Mecanica mecanica = (Mecanica) atraccion;
	                linea.append("--")
	                    .append(mecanica.getMaxAltura()).append("--")
	                    .append(mecanica.getMaxPeso()).append("--")
	                    .append(mecanica.getMinAltura()).append("--")
	                    .append(mecanica.getMinPeso()).append("--")
	                    .append(mecanica.getNivelRiesgo()).append("--")
	                    .append(mecanica.getRestriccionSalud().getNombre()).append("--");

	                // Atracciones mecánicas para restricción de salud
	                ArrayList<String> mecanicas = mecanica.getRestriccionSalud().getAtraccionesMecanica();
	                for (int i = 0; i < mecanicas.size(); i++) {
	                    if (i > 0) {
	                        linea.append(",");
	                    }
	                    linea.append(mecanicas.get(i));
	                }
	            } else if (atraccion instanceof Cultural) {
	                Cultural cultural = (Cultural) atraccion;
	                linea.append("--").append(cultural.getEdadMin());
	            }

	            escritor.println(linea.toString());
	        }
	    } catch (IOException e) {
	        System.err.println("Error al escribir el archivo: " + e.getMessage());
	        throw e;
	    }
	}

	public void escribirEspectaculos(ArrayList<Espectaculo> espectaculos) {
		try {
	        File carpeta = new File("./data/");
	        if (!carpeta.exists()) {
	            carpeta.mkdirs();
	        }
	        PrintWriter escritor = new PrintWriter(new File("./data/atracciones_y_espectaculos.txt"));
	        
	        for (Espectaculo espectaculo : espectaculos) {
	        	 
	      
	        	List<LocalDateTime> horarios = (ArrayList<LocalDateTime>) espectaculo.getHorario();
	            String linea = espectaculo.getNombre();
	            
	
	            
	            for (LocalDateTime horario : horarios) {
	            	linea = linea+","+horario;
	            }
	            linea= linea+"--"+espectaculo.isFuncionando();
	            escritor.println(linea);
	        }
	        escritor.close();
	    } catch (IOException e) {
	        System.err.println("Error al escribir el archivo: " + e.getMessage());
	    }
	}
}