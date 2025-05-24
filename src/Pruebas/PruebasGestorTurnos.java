package Pruebas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Turnos.*;
import Usuarios.Empleado;
import Usuarios.Especialidad;
import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.Cultural;
import atracciones_y_espectaculos.LugarTrabajo;
import atracciones_y_espectaculos.Mecanica;
import atracciones_y_espectaculos.RestriccionSalud;
import atracciones_y_espectaculos.Restriccion_clima;
import persistencia.Escritor_Atracciones_Y_Espectaculos;
import Usuarios.Compra;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class PruebasGestorTurnos {

    private GestorTurnos gestorTurnos;
    private Empleado empleado;
    private Atraccion atraccion;
    private LugarTrabajo lugarTrabajo;
    private Escritor_Atracciones_Y_Espectaculos escritorAYE;

    @BeforeEach
    public void setup() throws IOException {
    	gestorTurnos = new GestorTurnos();
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		ArrayList<Restriccion_clima> restricciones_clima = new ArrayList<Restriccion_clima>();
		ArrayList<String> atraccionesNombreRestriccion =new ArrayList<String>();
		atraccionesNombreRestriccion.add("VelociRaptor");
		ArrayList<String> nombresRestriccionSalud = new ArrayList<String>();
		nombresRestriccionSalud.add("VelociRaptor");
		ArrayList<String> espectaculosNombreRestriccion =new ArrayList<String>();
		espectaculosNombreRestriccion.add("Cumbia");
		Restriccion_clima restriccion1 = new Restriccion_clima("Lluvia", atraccionesNombreRestriccion,espectaculosNombreRestriccion);
		restricciones_clima.add(restriccion1);
		RestriccionSalud restriccionSaludPrueba= new RestriccionSalud("Cardio",nombresRestriccionSalud);
		String nombre = "VelociRaptor"; 
        String lugar = "Island of Adventure";
        int numeroEmpleados= 5;
        boolean funcionando= true;
        int cupoMaximo = 24;
        ArrayList<Restriccion_clima> restriccionesClima = restricciones_clima;
        double minAltura = 1.50;
        double maxAltura = 2.10;
        double minPeso = 30;
        double maxPeso = 100;
        String nivelRiesgo = "medio";
        RestriccionSalud restriccionSalud = restriccionSaludPrueba;
        Mecanica mecanica = new Mecanica(nombre,lugar,numeroEmpleados,funcionando,cupoMaximo,restriccionesClima, minAltura,maxAltura,minPeso,maxPeso,nivelRiesgo,restriccionSalud);
        String nombreCultural = "Cumbia"; 
        String lugarCultural = "Island of Adventure";
        int numeroEmpleadosCultural= 7;
        boolean funcionandoCultural= true;
        int cupoMaximoCultural = 60;
        ArrayList<Restriccion_clima> restriccionesClimaCultural = restricciones_clima;
		int edadMinima= 12;
		escritorAYE = new Escritor_Atracciones_Y_Espectaculos();
		Cultural cultural = new Cultural(nombreCultural,lugarCultural,numeroEmpleadosCultural,funcionandoCultural,cupoMaximoCultural,restriccionesClimaCultural,edadMinima);
		atracciones.add(mecanica);
		atracciones.add(cultural);
		escritorAYE.escribirAtracciones(atracciones);
		
		//Especialidad
		ArrayList<String> atraNombreEsp = new ArrayList<String>();
		atraNombreEsp.add("VelociRaptor");
		ArrayList<String> cultNombreEsp = new ArrayList<String>();
		cultNombreEsp.add("nada");
		Especialidad especialidades = new Especialidad("alto", atraNombreEsp, cultNombreEsp);
		ArrayList<Compra> wa = new ArrayList<Compra>();
		Compra comp = new Compra(false, "2");
		wa.add(comp);
		empleado = new Empleado("mateojosefo", "nea", true, especialidades, wa);
    }

    @Test
    public void testAsignarTurnoManana_Exito() {
        gestorTurnos.guardarTurnoSemana(TipoTurno.ATRACCION, atraccion, empleado , "lunes", "mañana");

        Turno turno = gestorTurnos.getSemana().getLunes().getMañana().get("mateojosefo");

        assertNotNull(turno);
        assertEquals(TipoTurno.ATRACCION, turno.getTipo());
        assertEquals(atraccion, turno.getAtraccion());
    }

    @Test
    public void testAsignarTurnoTarde_Exito() {
        gestorTurnos.guardarTurnoSemana(TipoTurno.LUGAR_TRABAJO, lugarTrabajo, empleado, "martes", "tarde");

        Turno turno = gestorTurnos.getSemana().getMartes().getTarde().get("mateojosefo");

        assertNotNull(turno);
        assertEquals(TipoTurno.LUGAR_TRABAJO, turno.getTipo());
        assertEquals(lugarTrabajo, turno.lugarTrabajo);
    }

    @Test
    public void testAsignarTurnoDobleMismaFranja_LanzaExcepcion() {
        gestorTurnos.guardarTurnoSemana(TipoTurno.ATRACCION, atraccion, empleado, "miercoles", "mañana");

        Exception ex = assertThrows(IllegalStateException.class, () -> {
            gestorTurnos.guardarTurnoSemana(TipoTurno.LUGAR_TRABAJO, lugarTrabajo, empleado, "miercoles", "mañana");
        });

        assertEquals("El empleado ya tiene un turno asignado en la mañana.", ex.getMessage());
    }

    @Test
    public void testAsignarTurnoFranjaInvalida_LanzaExcepcion() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            gestorTurnos.guardarTurnoSemana(TipoTurno.ATRACCION, atraccion, empleado, "jueves", "medianoche");
        });

        assertEquals("Franja horaria inválida: medianoche", ex.getMessage());
    }

    @Test
    public void testGuardarEnDiaInvalido_LanzaExcepcion() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            gestorTurnos.guardarTurnoSemana(TipoTurno.LUGAR_TRABAJO, lugarTrabajo, empleado, "noday", "mañana");
        });

        assertEquals("Día inválido: noday", ex.getMessage());
    }
}
