package persistencia;

import java.io.*;
import java.util.HashMap;

import Turnos.*;
import Usuarios.Empleado;
import Usuarios.GestorPersonas;
import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.LugarTrabajo;

public class PersistenciaTurnos {
    private static final String ARCHIVO_TURNOS = "./data/semana_turnos.txt";

    public static void guardarSemana(SemanaTurnos semana) {
        try {
            File carpeta = new File("./data/");
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            PrintWriter escritor = new PrintWriter(new FileWriter(ARCHIVO_TURNOS));

            guardarDia("lunes", semana.getLunes(), escritor);
            guardarDia("martes", semana.getMartes(), escritor);
            guardarDia("miercoles", semana.getMiercoles(), escritor);
            guardarDia("jueves", semana.getJueves(), escritor);
            guardarDia("viernes", semana.getViernes(), escritor);
            guardarDia("sabado", semana.getSabado(), escritor);
            guardarDia("domingo", semana.getDomingo(), escritor);

            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void guardarDia(String dia, DiaTurnos diaTurnos, PrintWriter escritor) {
        for (Turno turno : diaTurnos.getMañana().values()) {
            escritor.println(dia + "--mañana--" + turno.empleado.getLogin() + "--" + turno.tipo + "--" + getNombreDestino(turno));
        }
        for (Turno turno : diaTurnos.getTarde().values()) {
            escritor.println(dia + "--tarde--" + turno.empleado.getLogin() + "--" + turno.tipo + "--" + getNombreDestino(turno));
        }
    }

    private static String getNombreDestino(Turno turno) {
        return turno.getTipo() == TipoTurno.ATRACCION
            ? turno.getAtraccion().getNombre()
            : turno.lugarTrabajo.getNombre();
    }

    public static SemanaTurnos cargarSemana(GestorPersonas gestorPersonas,
                                            HashMap<String, Atraccion> atracciones,
                                            HashMap<String, LugarTrabajo> lugares) {
        SemanaTurnos semana = new SemanaTurnos();

        try (BufferedReader lector = new BufferedReader(new FileReader(ARCHIVO_TURNOS))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("--");
                if (partes.length != 5) continue;

                String dia = partes[0];
                String franja = partes[1];
                String login = partes[2];
                TipoTurno tipo = TipoTurno.valueOf(partes[3]);
                String nombreDestino = partes[4];
                //Por si algo esto es para que el gestorPersonas.getUsuario retorna un usuario y lo convertimos en Empleado. :d
                Empleado empleado = (Empleado) gestorPersonas.getPersona(login);
                Object destino = tipo == TipoTurno.ATRACCION
                        ? atracciones.get(nombreDestino)
                        : lugares.get(nombreDestino);

                if (empleado != null && destino != null) {
                    DiaTurnos diaTurnos = obtenerDia(dia, semana);
                    Turno turno = new Turno(tipo, destino, empleado);
                    if (franja.equalsIgnoreCase("mañana")) {
                        diaTurnos.getMañana().put(login, turno);
                    } else if (franja.equalsIgnoreCase("tarde")) {
                        diaTurnos.getTarde().put(login, turno);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return semana;
    }

    private static DiaTurnos obtenerDia(String dia, SemanaTurnos semana) {
        return switch (dia.toLowerCase()) {
            case "lunes" -> semana.getLunes();
            case "martes" -> semana.getMartes();
            case "miercoles" -> semana.getMiercoles();
            case "jueves" -> semana.getJueves();
            case "viernes" -> semana.getViernes();
            case "sabado" -> semana.getSabado();
            case "domingo" -> semana.getDomingo();
            default -> throw new IllegalArgumentException("Día inválido: " + dia);
        };
    }
}
