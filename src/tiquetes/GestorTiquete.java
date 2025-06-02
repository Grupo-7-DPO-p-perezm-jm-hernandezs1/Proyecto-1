package tiquetes;

import persistencia.CatalogoPrecios;
import persistencia.LlamadorGestorAtracciones;
import persistencia.PersistenciaFastPass;
import persistencia.PersistenciaTiquetes;
import atracciones_y_espectaculos.Atraccion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorTiquete {
    private CatalogoPrecios catalogoPrecios;
    private PersistenciaTiquetes persistencia;
    private int contador;
    private LlamadorGestorAtracciones llamadorGestorAtracciones;

    public GestorTiquete() {
        catalogoPrecios = new CatalogoPrecios();
        persistencia = new PersistenciaTiquetes();
        contador = persistencia.leerContador();  // Leemos el contador desde el archivo
        this.llamadorGestorAtracciones = new LlamadorGestorAtracciones();
    }

    public String crearIdentificador(String prefijo) {
        String id = prefijo + "-" + contador;
        contador++;  // Incrementamos el contador para el siguiente tiquete
        //Cambiar
        persistencia.escribirContador(contador);  // Guardamos el nuevo valor del contador en el archivo
        return id;
    }

    public String comprarTiqueteTemporada(LocalDate fechaInicio, LocalDate fechaFinal, Categoria categoria) {
        String id = crearIdentificador("TEMP");
        double precio = catalogoPrecios.getPrecio("TiqueteTemporada");

        TiqueteTemporada tiquete = new TiqueteTemporada(id, precio, fechaInicio, fechaFinal, categoria);

        persistencia.guardarTiquete(tiquete);

        return id;
    }

    public String comprarTiqueteGeneral(Categoria categoria) {
        String id = crearIdentificador("GENE");
        double precio = catalogoPrecios.getPrecio("TiqueteGeneral");

        TiqueteGeneral tiquete = new TiqueteGeneral(id, precio, categoria);

        persistencia.guardarTiquete(tiquete);

        return id;
    }

    public String comprarTiqueteBasico() {
        String id = crearIdentificador("BASI");
        double precio = catalogoPrecios.getPrecio("TiqueteBasico");

        TiqueteBasico tiquete = new TiqueteBasico(id, precio);

        persistencia.guardarTiquete(tiquete);

        return id;
    }

    public String comprarTiqueteIndividual(Atraccion atraccion) {
        String id = crearIdentificador("INDV");
        double precio = catalogoPrecios.getPrecio("TiqueteIndividual");

        TiqueteIndividual tiquete = new TiqueteIndividual(id, precio, atraccion);

        persistencia.guardarTiquete(tiquete);

        return id;
    }
    
    public List<String> getPrecios(){
    	double precioInd = catalogoPrecios.getPrecio("TiqueteIndividual"); 
    	double precioBasico = catalogoPrecios.getPrecio("TiqueteBasico");
    	double precioTempo = catalogoPrecios.getPrecio("TiqueteTemporada");
    	double precioGen = catalogoPrecios.getPrecio("TiqueteGeneral");
    	
    	String precioInStrig = String.valueOf(precioInd);
    	String precioBasStrig = String.valueOf(precioBasico);
    	String precioTempStrig = String.valueOf(precioTempo);
    	String precioGenStrig = String.valueOf(precioGen);
    	List<String> lista = new ArrayList<String>();
    	lista.add(precioBasStrig);
    	lista.add(precioTempStrig);
    	lista.add(precioGenStrig);
    	lista.add(precioInStrig);
    	 return lista  ;
    }
   
    public void cambiarPrecioTiquete(String tipo, double nuevoPrecio) {
        catalogoPrecios.setPrecio(tipo.toUpperCase(), nuevoPrecio);
    }

    public String comprarFastPass(LocalDate fechaValida) {
        // Generar el identificador único para el FastPass
        String id = crearIdentificador("FAST");

        // Obtener el precio del FastPass desde el CatalogoPrecios
        double precio = catalogoPrecios.getPrecio("FastPass");

        // Crear el objeto FastPass
        FastPass fastPass = new FastPass(id, fechaValida, precio);

        // Guardar el FastPass en el archivo correspondiente
        PersistenciaFastPass.guardarFastPass(fastPass);

        // Devolver el identificador del FastPass
        return id;
    }
}
