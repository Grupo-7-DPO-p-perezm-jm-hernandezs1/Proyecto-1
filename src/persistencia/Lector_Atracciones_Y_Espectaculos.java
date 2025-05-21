package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atracciones_y_espectaculos.*;

public class Lector_Atracciones_Y_Espectaculos {


	public ArrayList<Atraccion> leerAtracciones(String rutaArchivo) throws IOException {
	    ArrayList<Atraccion> atracciones = new ArrayList<>();

	    try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
	        String linea;
	        while ((linea = lector.readLine()) != null) {
	            if (linea.startsWith("MECANICA") || linea.startsWith("CULTURAL")) {
	            	System.out.println("Línea leída: " + linea);
	                
	                Atraccion atraccion;
	                String[] partes = linea.split("--");
	                System.out.println(partes[1]);
	                int cupoMaximo = Integer.parseInt(partes[1]);
	                
	                String lugar = partes[2];
	                String nombre = partes[3];
	                int numEmpleados = Integer.parseInt(partes[4]);
	                boolean funcionando = Boolean.parseBoolean(partes[5]);
	                String restricciones = partes[6];
	                String[] restriccion = restricciones.split(":restriccionClima:");
	                
	                ArrayList<Restriccion_clima> restriccionesClima = new ArrayList<Restriccion_clima>();
	                
	                for(String cosa: restriccion) {
	                    String[] partecita = cosa.split("##");
	                    String tipo = partecita[0];
	                    String[] atracciones1 = partecita[1].split(",");
	                    String[] espectaculos1 = partecita[2].split(",");
	                    ArrayList<String> atraccionesNombre = new ArrayList<String>();
	                    ArrayList<String> espectaculosNombre = new ArrayList<String>();
	                    
	                    for(String atraccion12 : atracciones1) {
	                        atraccionesNombre.add(atraccion12);
	                    }
	                    for(String atraccion12 : espectaculos1) {
	                        System.out.println(atraccion12);
	                    }
	                    
	                    Restriccion_clima restriccion_partecita = new Restriccion_clima(tipo, atraccionesNombre, espectaculosNombre);
	                    restriccionesClima.add(restriccion_partecita);
	                }

	                // Crear atracción según el tipo
	                System.out.println(partes[0]);
	                String tipo = partes[0];
	                if (tipo.equals("MECANICA")) {
	                    double alturaMax = Double.parseDouble(partes[7]);
	                    double pesoMax = Double.parseDouble(partes[8]);
	                    double alturaMin = Double.parseDouble(partes[9]);
	                    double pesoMin = Double.parseDouble(partes[10]);
	                    String nivelRiesgo = partes[11];
	                    String nombreSalud = partes[12];
	                    String[] nombreMecanicas = partes[13].split(",");
	                    ArrayList<String> nombreMecanicasFinal = new ArrayList<String>();
	                    
	                    for(String nombre1 : nombreMecanicas) {
	                        nombreMecanicasFinal.add(nombre1);
	                    }
	                    
	                    RestriccionSalud restriccionSalud = new RestriccionSalud(nombreSalud, nombreMecanicasFinal);

	                    atraccion = new Mecanica(
	                        nombre,
	                        lugar,
	                        numEmpleados,
	                        funcionando,
	                        cupoMaximo,
	                        restriccionesClima,
	                        alturaMin,
	                        alturaMax,
	                        pesoMin,
	                        pesoMax,
	                        nivelRiesgo,
	                        restriccionSalud
	                    );
	                } else {
	                    // Crear Cultural con restricciones de clima
	                    int minEdad = Integer.parseInt(partes[7]);
	                    
	                    atraccion = new Cultural(
	                        nombre,
	                        lugar,
	                        numEmpleados,
	                        funcionando,
	                        cupoMaximo,
	                        restriccionesClima, 
	                        minEdad
	                    );
	                }
	                System.out.println(atraccion.getLugar());
	                atracciones.add(atraccion);
	            }
	            System.out.println(atracciones);
	        }
	    } catch (IOException e) {
	        System.err.println("Error al leer el archivo: " + e.getMessage());
	        throw e;
	    }
	    
	    System.out.println(atracciones);
	    return atracciones;
	}
    public ArrayList<Espectaculo> leerEspectaculos(String rutaArchivo) throws IOException {
        ArrayList<Espectaculo> espectaculos = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
            	String[] partes = linea.split("--");
            	String nombre = partes[0];
            	boolean funcionando = Boolean.parseBoolean(partes[2]);
            	
            	String[] fechas = partes[1].split(",");
            	ArrayList<LocalDateTime> horario = new ArrayList<LocalDateTime>();
            	for(String fechasString: fechas) {
            		
            		LocalDateTime fecha = LocalDateTime.parse(fechasString);
            		horario.add(fecha);
            	}
            	Espectaculo espectaculo = new Espectaculo(nombre,horario,funcionando);
            	espectaculos.add(espectaculo);
            	
            }
        } catch (DateTimeParseException e) {
            System.err.println("Error al parsear fechas/horarios: " + e.getMessage());
        }
        return espectaculos;
    }
}
