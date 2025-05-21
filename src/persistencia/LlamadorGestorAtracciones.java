package persistencia;

import java.io.IOException;
import java.util.ArrayList;

import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.Espectaculo;
import atracciones_y_espectaculos.GestorAtracciones;
import atracciones_y_espectaculos.RestriccionSalud;
import atracciones_y_espectaculos.Restriccion_clima;

public class LlamadorGestorAtracciones {
    private GestorAtracciones gestorAtracciones;
	private Lector_Atracciones_Y_Espectaculos lectorAYE;
	private LectorRerstriccion_clima lectorRestriccionClima;
	private LectorRestriccionSalud lectorRestriccionSalud;
	
	
	public LlamadorGestorAtracciones() {
		this.lectorAYE = new Lector_Atracciones_Y_Espectaculos();
		this.lectorRestriccionClima= new LectorRerstriccion_clima();
		this.lectorRestriccionSalud= new LectorRestriccionSalud();
		
		try {
			ArrayList <Atraccion> lecturaAtra = lectorAYE.leerAtracciones(".\\src\\\\data\\\\atracciones_y_espectaculos.txt");
			ArrayList <Espectaculo> lecturaEspe = lectorAYE.leerEspectaculos(".//src//data/atracciones_y_espectaculos.txt");
			ArrayList <Restriccion_clima>  lecturaResClima = lectorRestriccionClima.leerRestriccion_clima();
			ArrayList <RestriccionSalud> lecturaResSalud = lectorRestriccionSalud.leerRestriccionSalud();
			gestorAtracciones = new GestorAtracciones(lecturaAtra, lecturaEspe, lecturaResClima, lecturaResSalud);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}


	public GestorAtracciones getGestoratracciones() {
		return gestorAtracciones;
	}
	
	
	
}
