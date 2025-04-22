package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atracciones_y_espectaculos.*;

public class Lector_Atracciones_Y_Espectaculos {

    private Map<String, Restriccion_clima> restriccionesClimaCache = new HashMap<>();
    private Map<String, RestriccionSalud> restriccionesSaludCache = new HashMap<>();

    public List<Atraccion> leerAtracciones(String rutaArchivo) throws IOException {
        List<Atraccion> atracciones = new ArrayList<>();
        Map<String, Restriccion_clima> restriccionesClimaCache = new HashMap<>();
        Map<String, RestriccionSalud> restriccionesSaludCache = new HashMap<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.startsWith("MECANICA") || linea.startsWith("CULTURAL")) {
                    String[] partes = linea.split("--");
                    
                    // Datos comunes
                    int cupoMaximo = Integer.parseInt(partes[2]);
                    String lugar = partes[3];
                    int minEdad = Integer.parseInt(partes[4]);
                    String nombre = partes[5];
                    int numEmpleados = Integer.parseInt(partes[6]);
                    boolean funcionando = Boolean.parseBoolean(partes[7]);
                    
                    // Procesar restricciones de clima
                    List<Restriccion_clima> restriccionesClima = new ArrayList<>();
                    int idxRestriccionClima = Arrays.asList(partes).indexOf("restriccion_clima");
                    if (idxRestriccionClima != -1) {
                        for (int i = idxRestriccionClima + 1; i < partes.length; i++) {
                            if (partes[i].equals("restriccion_salud") || 
                                partes[i].equals("MECANICA") || 
                                partes[i].equals("CULTURAL")) break;
                                
                            String tipo = partes[i];
                            Restriccion_clima restriccion = restriccionesClimaCache.computeIfAbsent(
                                tipo, 
                                t -> new Restriccion_clima(t, new ArrayList<>(), new ArrayList<>())
                            );
                            restriccionesClima.add(restriccion);
                        }
                    }

                    // Crear atracción según el tipo
                    Atraccion atraccion;
                    if (linea.startsWith("MECANICA")) {
                        // Procesar restricción de salud
                        RestriccionSalud restriccionSalud = null;
                        int idxRestriccionSalud = Arrays.asList(partes).indexOf("restriccion_salud");
                        if (idxRestriccionSalud != -1 && idxRestriccionSalud + 1 < partes.length) {
                            String nombreRestriccion = partes[idxRestriccionSalud + 1];
                            restriccionSalud = restriccionesSaludCache.computeIfAbsent(
                                nombreRestriccion,
                                n -> new RestriccionSalud(n, new ArrayList<>())
                            );
                        }

                        // Crear Mecanica con todas las restricciones
                        atraccion = new Mecanica(
                            nombre,
                            lugar,
                            numEmpleados,
                            minEdad,
                            funcionando,
                            cupoMaximo,
                            restriccionesClima,  // Restricciones de clima pasadas directamente
                            Double.parseDouble(partes[partes.length - 7]), // minAltura
                            Double.parseDouble(partes[partes.length - 6]), // maxAltura
                            Double.parseDouble(partes[partes.length - 5]), // minPeso
                            Double.parseDouble(partes[partes.length - 4]), // maxPeso
                            partes[partes.length - 3], // nivelRiesgo
                            restriccionSalud
                        );

                        // Vincular a restricción de salud si existe
                        if (restriccionSalud != null) {
                            restriccionSalud.getAtraccionesMecanica().add((Mecanica) atraccion);
                        }
                    } else {
                        // Crear Cultural con restricciones de clima
                        atraccion = new Cultural(
                            nombre,
                            lugar,
                            numEmpleados,
                            minEdad,
                            funcionando,
                            cupoMaximo,
                            restriccionesClima,  // Restricciones de clima pasadas directamente
                            Integer.parseInt(partes[partes.length - 1]) // edadMin
                        );
                    }

                    // Vincular atracción a sus restricciones de clima
                    for (Restriccion_clima rc : restriccionesClima) {
                        rc.agregarAtraccion(atraccion);
                    }

                    atracciones.add(atraccion);
                }
            }
        }
        return atracciones;
    }
    public List<Espectaculo> leerEspectaculos(String rutaArchivo) throws IOException {
        List<Espectaculo> espectaculos = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.contains("fechas") && linea.contains("horario")) {
                    String[] partes = linea.split("--");
                    
                    // Parsear fechas y horarios
                    List<LocalDateTime> fechas = new ArrayList<>();
                    List<LocalDateTime> horarios = new ArrayList<>();
                    boolean enFechas = false, enHorarios = false;
                    
                    for (String parte : partes) {
                        if (parte.equals("fechas")) {
                            enFechas = true;
                            enHorarios = false;
                        } else if (parte.equals("horario")) {
                            enFechas = false;
                            enHorarios = true;
                        } else if (enFechas) {
                            fechas.add(LocalDateTime.parse(parte, formatter));
                        } else if (enHorarios) {
                            horarios.add(LocalDateTime.parse(parte, formatter));
                        }
                    }
                    
                    // Constructor de Espectaculo ajustado
                    Espectaculo espectaculo = new Espectaculo(
                        fechas,
                        horarios
                        // Agrega otros parámetros si el constructor los requiere
                    );
                    espectaculos.add(espectaculo);
                }
            }
        }
        return espectaculos;
    }
}
}