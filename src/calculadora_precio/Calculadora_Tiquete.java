package calculadora_precio;

import tiquetes.Temporada;
import tiquetes.Tiquete;

public class Calculadora_Tiquete {
	static double impuesto = 0.21;
	static double descuento = 0.30;
	protected Tiquete tiquete;
	public Calculadora_Tiquete(Tiquete tiquete) {
		super();
		this.tiquete = tiquete;
	}
	
	public static double getDescuento() {
		return descuento;
	}

	public static void setDescuento(double descuento) {
		Calculadora_Tiquete.descuento = descuento;
	}

	public static double getImpuesto() {
		return impuesto;
	}
	public static void setImpuesto(double impuesto) {
		Calculadora_Tiquete.impuesto = impuesto;
	}
	public Tiquete getTiquete() {
		return tiquete;
	}
	public void setTiquete(Tiquete tiquete) {
		this.tiquete = tiquete;
	}
	
	// calcular el precio segun impuesto y exclusividad
	public double getPrecio () {
		tiquete = getTiquete();
		String exclusividad = tiquete.getExclusividad();
		double respuesta = 0;
		if (exclusividad == "Familiar") {
			respuesta = 0;
		}
		if (exclusividad == "Oro") {
			respuesta = 0;
		}
		if (exclusividad == "Diamante") {
			respuesta = 0;
		}
		// precio de un dia 
		
		
		if (tiquete instanceof Temporada) {
	        int dias = Temporada.getTiempo();
	        respuesta= respuesta*dias*descuento;
	        
			
	    } 
	    	respuesta = respuesta +respuesta * getImpuesto();
	        return respuesta;
	    
		
	}
	
	
}
