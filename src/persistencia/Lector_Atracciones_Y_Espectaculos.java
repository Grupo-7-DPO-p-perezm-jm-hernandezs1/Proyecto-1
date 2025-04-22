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
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

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
                    
                    // Restricciones de clima
                    List<Restriccion_clima> restriccionesClima = new ArrayList<>();
                    int idxRestriccionClima = Arrays.asList(partes).indexOf("restricion_clima");
                    if (idxRestriccionClima != -1) {
                        for (int i = idxRestriccionClima + 1; i < partes.length; i++) {
                            String tipoRestriccion = partes[i];
                            if (tipoRestriccion.startsWith("restricion_") || 
                                tipoRestriccion.equals("MECANICA") || 
                                tipoRestriccion.equals("CULTURAL")) break;
                            
                            Restriccion_clima restriccion = restriccionesClimaCache.computeIfAbsent(
                                tipoRestriccion, 
                                tipo -> new Restriccion_clima(tipo, new ArrayList<>(), new ArrayList<>())
                            );
                            restriccionesClima.add(restriccion);
                        }
                    }

                    // Restricciones de salud (solo para Mecanica)
                    RestriccionSalud restriccionSalud = null;
                    int idxRestriccionSalud = Arrays.asList(partes).indexOf("restricion_salud");
                    if (idxRestriccionSalud != -1) {
                        String nombreRestriccionSalud = partes[idxRestriccionSalud + 1];
                        restriccionSalud = restriccionesSaludCache.computeIfAbsent(
                            nombreRestriccionSalud,
                            nombre -> new RestriccionSalud(nombre, new ArrayList<>())
                        );
                    }

                    // Crear instancia específica
                    Atraccion atraccion;
                    if (linea.startsWith("MECANICA")) {
                        double maxAltura = Double.parseDouble(partes[partes.length - 6]);
                        double maxPeso = Double.parseDouble(partes[partes.length - 5]);
                        double minAltura = Double.parseDouble(partes[partes.length - 4]);
                        double minPeso = Double.parseDouble(partes[partes.length - 3]);
                        String nivelRiesgo = partes[partes.length - 2];
                        
                        atraccion = new Mecanica(
                            nombre,
                            lugar,
                            numEmpleados,
                            minEdad,
                            funcionando,
                            cupoMaximo,
                            minAltura,
                            maxAltura,
                            minPeso,
                            maxPeso,
                            nivelRiesgo,
                            restriccionSalud
                        );
                    } else {
                        int edadMin = Integer.parseInt(partes[partes.length - 1]);
                        atraccion = new Cultural(
                            nombre,
                            lugar,
                            numEmpleados,
                            minEdad,
                            funcionando,
                            cupoMaximo,
                            edadMin
                        );
                    }
                    
                    atraccion.setRestriccionClima( restriccionesClima);
                    atracciones.add(atraccion);
                    
                    // Vincular la atracción a sus restricciones
                    for (Restriccion_clima restriccion : restriccionesClima) {
                        restriccion.agregarAtraccion(atraccion);
                    }
                    
                    if (restriccionSalud != null && atraccion instanceof Mecanica) {
                        restriccionSalud.agregarAtraccion((Mecanica) atraccion);
                    }
                }
            }
        }
        return atracciones;
    }

  
}