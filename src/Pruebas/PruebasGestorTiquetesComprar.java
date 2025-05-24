package Pruebas;
import org.junit.jupiter.api.Test.*;

import persistencia.CatalogoPrecios;
import persistencia.Escritor_Atracciones_Y_Espectaculos;
import persistencia.PersistenciaTiquetes;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tiquetes.TiqueteTemporada;
import tiquetes.TiqueteBasico;
import tiquetes.TiqueteGeneral;
import tiquetes.TiqueteIndividual;
import tiquetes.Categoria;
import tiquetes.GestorTiquete;
import tiquetes.Tiquete;
import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.Cultural;
import atracciones_y_espectaculos.GestorAtracciones;
import atracciones_y_espectaculos.Mecanica;
import atracciones_y_espectaculos.RestriccionSalud;
import atracciones_y_espectaculos.Restriccion_clima;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class PruebasGestorTiquetesComprar {
		private PersistenciaTiquetes persistencia;
		private GestorTiquete gestor;
		private GestorAtracciones gestorsin;
		private Categoria cate;
		private Mecanica mecanica;
		private Escritor_Atracciones_Y_Espectaculos escritorAYE;
		
		
		@BeforeEach
		void setUp() throws IOException {
			// Vaciar contenido de los archivos
			FileWriter fw1 = new FileWriter("./data/tiquetes.txt");
			fw1.write("");
			fw1.close();

			FileWriter fw2 = new FileWriter("./data/contador.txt");
			fw2.write("0");
			fw2.close();
			
			persistencia = new PersistenciaTiquetes(); 
			gestor = new GestorTiquete(); 
		}
		
		@Test
		void comprarTiqueteTemporadaTest() {
			Categoria cate = Categoria.Oro;
			LocalDate fechaInicio = LocalDate.of(2025, 5, 12);
	        LocalDate fechaFin = LocalDate.of(2025, 6, 12);
			gestor.comprarTiqueteTemporada(fechaInicio, fechaFin, cate);
			List<Tiquete> listaTiquetes = persistencia.leerTiquetes();
			int contador = persistencia.leerContador();
			int hola = contador-1;
			TiqueteTemporada tiqueteNuevo = (TiqueteTemporada) listaTiquetes.get(hola);
			assertEquals(tiqueteNuevo.getIdentificador(), "TEMP-"+hola);
			assertEquals(tiqueteNuevo.getFechaInicio(),fechaInicio );
			assertEquals(tiqueteNuevo.getFechaFinal(), fechaFin);
			assertEquals(tiqueteNuevo.getTipo(),  Categoria.Oro);
			assertEquals(tiqueteNuevo.getPrecio(), 10.00);
		}
		@Test
		void comprarTiqueteGeneralTest() {
			Categoria cate = Categoria.Oro;
			gestor.comprarTiqueteGeneral(cate);
			List<Tiquete> listaTiquetes = persistencia.leerTiquetes();
			int contador = persistencia.leerContador();
			int hola = contador-1;
			TiqueteGeneral tiqueteNuevo = (TiqueteGeneral) listaTiquetes.get(hola);
			assertEquals(tiqueteNuevo.getIdentificador(), "GENE-"+hola);
			assertEquals(tiqueteNuevo.getTipo(),  Categoria.Oro);
			assertEquals(tiqueteNuevo.getPrecio(), 1.00);
			
		}
		@Test
		void comprarTiqueteBasicoTest() {
			gestor.comprarTiqueteBasico();
			//Registrara un tiquete en el archivo Tiquete.txt
			
			List<Tiquete> listaTiquetes = persistencia.leerTiquetes();
			int contador = persistencia.leerContador();
			int hola = contador-1;
			Tiquete tiqueteNuevo = listaTiquetes.get(hola);
			
			assertEquals(tiqueteNuevo.getIdentificador(), "BASI-"+hola);
			assertEquals(tiqueteNuevo.getPrecio(), 2.00);

		}
		
		@Test
		void comprarTiqueteIndividualTest() throws IOException {
			//Crear Atra
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
			
			gestor.comprarTiqueteIndividual(mecanica);
			
			
			List<Tiquete> listaTiquetes = persistencia.leerTiquetes();
			System.out.println(listaTiquetes);
			int contador = persistencia.leerContador();
			int hola = contador-1;
			
			try {
			TiqueteIndividual tiqueteNuevo = (TiqueteIndividual) listaTiquetes.get(hola);
			assertEquals(tiqueteNuevo.getPrecio(), 90.0 );
			assertEquals(tiqueteNuevo.getIdentificador(), "INDV-"+hola );
			assertEquals(tiqueteNuevo.getAtraccionValida().getNombre(), "VelociRaptor");
			}
			catch(Exception e ) {
				e.printStackTrace();
			}
			
			
			
			
		}
}
