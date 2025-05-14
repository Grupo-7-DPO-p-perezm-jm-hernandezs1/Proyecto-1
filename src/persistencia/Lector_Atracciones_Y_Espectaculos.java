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
	                	//System.out.println(cosa);
	                    String[] partecita = cosa.split("##");
	                    String tipo = partecita[0];
	                    String[] atracciones1 = partecita[1].split(",");
	                    String[] espectaculos1 = partecita[2].split(",");
	                    ArrayList<String> atraccionesNombre = new ArrayList<String>();
	                    ArrayList<String> espectaculosNombre = new ArrayList<String>();
	                    
	                    for(String atraccion12 : atracciones1) {
	                    	//System.out.println(atraccion12);
	                        atraccionesNombre.add(atraccion12);
	                    }
	                    for(String atraccion12 : espectaculos1) {
	                        //espectaculosNombre.add(atraccion12);
	                        System.out.println(atraccion12);
	                    }
	                    
	                    Restriccion_clima restriccion_partecita = new Restriccion_clima(tipo, atraccionesNombre, espectaculosNombre);
	                    restriccionesClima.add(restriccion_partecita);
	                    //System.out.println(restriccionesClima);
	                }

	                // Crear atracción según el tipo
	                System.out.println(partes[0]);
	                String tipo = partes[0];
	                if (tipo.equals("MECANICA")) {
	                	//System.out.println(partes[7]);
	                	//System.out.println(partes[8]);
	                	//System.out.println(partes[9]);
	                	//System.out.println(partes[10]);
	                	//System.out.println(partes[11]);
	                	//System.out.println(partes[12]);
	                	//System.out.println(partes[13]);
	                	
	                	
	                    double alturaMax = Double.parseDouble(partes[7]);
	                    double pesoMax = Double.parseDouble(partes[8]);
	                    double alturaMin = Double.parseDouble(partes[9]);
	                    double pesoMin = Double.parseDouble(partes[10]);
	                    String nivelRiesgo = partes[11];
	                    String nombreSalud = partes[12];
	                    String[] nombreMecanicas = partes[13].split(",");
	                    ArrayList<String> nombreMecanicasFinal = new ArrayList<String>();
	                    
	                    for(String nombre1 : nombreMecanicas) {
	                        nombreMecanicasFinal.add(nombre1); // Corregido: nombre1 en lugar de nombre
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
	                	//System.out.println(partes[7]);
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
	                
	                atracciones.add(atraccion); // Añadir la atracción a la lista
	            }
	           
	        }
	    }
	    
	    
	    return atracciones;
	}
    public ArrayList<Espectaculo> leerEspectaculos(String rutaArchivo) throws IOException {
        ArrayList<Espectaculo> espectaculos = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.contains("fechas") && linea.contains("horario")) {
                    String[] partes = linea.split("--");
                    
                    
                    String nombre = "";
                    ArrayList<LocalDateTime> fechas = new ArrayList<>();
                    ArrayList<LocalDateTime> horarios = new ArrayList<>();
                    boolean enFechas = false;
                    boolean enHorarios = false;

                    for (int i = 0; i < partes.length; i++) {
                        String parte = partes[i];
                        
                        if (parte.equals("fechas")) {
                            enFechas = true;
                            enHorarios = false;
                            continue;
                        } else if (parte.equals("horario")) {
                            enFechas = false;
                            enHorarios = true;
                            continue;
                        }

                        if (enFechas) {
                            fechas.add(LocalDateTime.parse(parte, formatter));
                        } else if (enHorarios) {
                            
                            if (i == partes.length - 1) {
                                nombre = parte;
                            } else {
                                horarios.add(LocalDateTime.parse(parte, formatter));
                            }
                        }
                    }

           
                    if (!fechas.isEmpty() && !horarios.isEmpty() && !nombre.isEmpty()) {
                        Espectaculo espectaculo = new Espectaculo(
                            nombre,
                            horarios,
                            fechas,
                            true 
                        );
                        espectaculos.add(espectaculo);
                    }
                }
            }
        } catch (DateTimeParseException e) {
            System.err.println("Error al parsear fechas/horarios: " + e.getMessage());
        }
        return espectaculos;
    }
}
