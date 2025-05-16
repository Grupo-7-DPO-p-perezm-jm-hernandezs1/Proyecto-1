package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.Espectaculo;
import atracciones_y_espectaculos.GestorAtracciones;
import atracciones_y_espectaculos.RestriccionSalud;
import atracciones_y_espectaculos.Restriccion_clima;
import persistencia.Escritor_Atracciones_Y_Espectaculos;
import persistencia.LectorLugarTrabajo;
import persistencia.LectorRerstriccion_clima;
import persistencia.LectorRestriccionSalud;
import persistencia.Lector_Atracciones_Y_Espectaculos;

class PruebaGestorAtracciones {

	private Escritor_Atracciones_Y_Espectaculos escritorAYE;
	private Lector_Atracciones_Y_Espectaculos lectorAYE;
	private LectorRerstriccion_clima lectorRestriccionClima;
	private LectorRestriccionSalud lectorRestriccionSalud;
	private LectorLugarTrabajo lectorLugarTrabajo;
	private GestorAtracciones gestor;
	
	@BeforeEach
	void setUp() throws Exception {
		this.escritorAYE = new Escritor_Atracciones_Y_Espectaculos();
		this.lectorAYE = new Lector_Atracciones_Y_Espectaculos();
		this.lectorRestriccionClima= new LectorRerstriccion_clima();
		this.lectorRestriccionSalud= new LectorRestriccionSalud();
		this.lectorLugarTrabajo = new LectorLugarTrabajo();
		ArrayList<Atraccion> atracciones = lectorAYE.leerAtracciones(".\\src\\data\\atracciones_y_espectaculos.txt");
		ArrayList<Espectaculo> espectaculos = lectorAYE.leerEspectaculos(".\\src\\data\\Espectaculos.txt");
		ArrayList <Restriccion_clima> restriccionesClima = lectorRestriccionClima.leerRestriccion_clima();
		ArrayList<RestriccionSalud> restriccionesSalud = lectorRestriccionSalud.leerRestriccionSalud();
		this.gestor = new GestorAtracciones(atracciones, espectaculos, restriccionesClima, restriccionesSalud);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testActivarRestriccionClima() {
		
		gestor.activarRestriccionClima("Lluvia");
		System.out.println(gestor.buscarAtraccionPorNombre("VelociRaptor").getNombre());
		System.out.println(gestor.buscarEspectaculoPorNombre("Factor X").getNombre());
	}

}
