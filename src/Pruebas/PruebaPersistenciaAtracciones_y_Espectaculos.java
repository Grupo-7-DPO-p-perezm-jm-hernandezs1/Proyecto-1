package Pruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.Cultural;
import atracciones_y_espectaculos.Espectaculo;
import atracciones_y_espectaculos.LugarTrabajo;
import atracciones_y_espectaculos.Mecanica;
import atracciones_y_espectaculos.RestriccionSalud;
import atracciones_y_espectaculos.Restriccion_clima;
import persistencia.Escritor_Atracciones_Y_Espectaculos;
import persistencia.LectorLugarTrabajo;
import persistencia.LectorRerstriccion_clima;
import persistencia.LectorRestriccionSalud;
import persistencia.Lector_Atracciones_Y_Espectaculos;

class PruebaPersistenciaAtracciones_y_Espectaculos {
	private Escritor_Atracciones_Y_Espectaculos escritorAYE;
	private Lector_Atracciones_Y_Espectaculos lectorAYE;
	private LectorRerstriccion_clima lectorRestriccionClima;
	private LectorRestriccionSalud lectorRestriccionSalud;
	private LectorLugarTrabajo lectorLugarTrabajo;
	@BeforeEach
	void setUp() throws Exception {
		this.escritorAYE = new Escritor_Atracciones_Y_Espectaculos();
		this.lectorAYE = new Lector_Atracciones_Y_Espectaculos();
		this.lectorRestriccionClima= new LectorRerstriccion_clima();
		this.lectorRestriccionSalud= new LectorRestriccionSalud();
		this.lectorLugarTrabajo = new LectorLugarTrabajo();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEscritorAtraccion() {
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		
		ArrayList<Restriccion_clima> restricciones_clima = new ArrayList<Restriccion_clima>();
		ArrayList<String> atraccionesNombreRestriccion =new ArrayList<String>();
		atraccionesNombreRestriccion.add("VelociRaptor");
		ArrayList<String> espectaculosNombreRestriccion =new ArrayList<String>();
		espectaculosNombreRestriccion.add("Cumbia");
		Restriccion_clima restriccion1 = new Restriccion_clima("Lluvia", atraccionesNombreRestriccion,espectaculosNombreRestriccion);
		restricciones_clima.add(restriccion1);
		
		ArrayList<String> nombresRestriccionSalud = new ArrayList<String>();
		nombresRestriccionSalud.add("VelociRaptor");
		nombresRestriccionSalud.add("Hulk");
		RestriccionSalud restriccionSaludPrueba= new RestriccionSalud("Cardio",nombresRestriccionSalud);
		
		// prueba de atraccion mecanica
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
        
        // preuba de atraccion Cultural
        String nombreCultural = "Cumbia"; 
        String lugarCultural = "Island of Adventure";
        int numeroEmpleadosCultural= 7;
        boolean funcionandoCultural= true;
        int cupoMaximoCultural = 60;
        ArrayList<Restriccion_clima> restriccionesClimaCultural = restricciones_clima;
		int edadMinima= 12;
		
		Cultural cultural = new Cultural(nombreCultural,lugarCultural,numeroEmpleadosCultural,funcionandoCultural,cupoMaximoCultural,restriccionesClimaCultural,edadMinima);
		Mecanica mecanica = new Mecanica(nombre,lugar,numeroEmpleados,funcionando,cupoMaximo,restriccionesClima, minAltura,maxAltura,minPeso,maxPeso,nivelRiesgo,restriccionSalud);
		atracciones.add(mecanica);
		atracciones.add(cultural);
		escritorAYE.escribirAtracciones(atracciones);
		
		
		
	}
	@Test
	void testLectorAtraccion() throws IOException {
		
		ArrayList<Atraccion> atracciones =lectorAYE.leerAtracciones(".\\src\\data\\atracciones_y_espectaculos.txt");
		assertEquals("Cumbia",atracciones.getFirst().getNombre(),"Incorrecto");
		System.out.println(atracciones.get(2).getNombre());
		assertEquals("Rock al Parque",atracciones.getLast().getNombre(),"Incorrecto");
		
		
	}
	@Test 
	void testLectorRestriccionClima() throws IOException {
		ArrayList<Restriccion_clima> restriccionesClima = lectorRestriccionClima.leerRestriccion_clima();
		assertEquals("Lluvia",restriccionesClima.getFirst().getTipo(),"Incorrecto");
	}
	@Test
	void testLectorRestriccionSalud() throws IOException{
		ArrayList<RestriccionSalud> restriccionesSalud = lectorRestriccionSalud.leerRestriccionSalud();
	}
	
	
	@Test
	void testLectorLugarTrabajo() throws IOException{
		ArrayList<LugarTrabajo> lugaresTrabajo = lectorLugarTrabajo.leerLugarTrabajo();
	}
	@Test
	void testLectorEspectaculo() throws IOException{
		ArrayList<Espectaculo> espectaculos = lectorAYE.leerEspectaculos(".\\src\\data\\Espectaculos.txt");
	}
}