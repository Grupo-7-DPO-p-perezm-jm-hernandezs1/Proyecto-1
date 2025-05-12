package Pruebas;
import org.junit.jupiter.api.Test.*;

import persistencia.CatalogoPrecios;
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
import atracciones_y_espectaculos.GestorAtracciones;
import atracciones_y_espectaculos.Restriccion_clima;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class PruebasGestorTiquetesComprar {
		private PersistenciaTiquetes persistencia;
		private GestorTiquete gestor;
		private GestorAtracciones gestorsin;
		private Categoria cate;
		private Atraccion atra;
		
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
		void comprarTiqueteIndividualTest() {
			
			ArrayList<String> we = new ArrayList<String>();
			ArrayList<String> wa = new ArrayList<String>();
			
			we.add("woo");
			wa.add("wii");
			
			Restriccion_clima a = new Restriccion_clima("N", wa, we);
			ArrayList<Restriccion_clima> arr = new ArrayList<Restriccion_clima>();
			arr.add(a);
			atra = new Atraccion("Island of Adventure", "a", 0, arr, 0, false);
			
			gestor.comprarTiqueteIndividual(atra);
			
			//TODO FALTA CORREGIR 
			
			List<Tiquete> listaTiquetes = persistencia.leerTiquetes();
			System.out.println(listaTiquetes);
			int contador = persistencia.leerContador();
			int hola = contador-1;
			
			
			try {
			TiqueteIndividual tiqueteNuevo = (TiqueteIndividual) listaTiquetes.get(hola);
			assertEquals(tiqueteNuevo.getAtraccionValida().getNombre(), "Island of Adventure");
			assertEquals(tiqueteNuevo.getPrecio(), 90.0 );
			assertEquals(tiqueteNuevo.getIdentificador(), "INDV-"+hola );
			}
			catch(Exception e ) {
				e.printStackTrace();
			}
			
			
			
			
		}
}
