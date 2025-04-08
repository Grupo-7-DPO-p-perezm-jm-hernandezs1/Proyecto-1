package atracciones_y_espectaculos;

import java.util.ArrayList;

public class Cultural extends Atraccion{

	private  int edadMin;

	public Cultural(String lugar, int cupoMaximo, ArrayList<String> recomendaciones, ArrayList<String> restriccionClima,
			int numeroEmpleados, int minEdad, boolean deTemporada, int edadMin) {
		super(lugar, cupoMaximo, recomendaciones, restriccionClima, numeroEmpleados, minEdad, deTemporada);
		
		this.edadMin = edadMin;
	}

	public int getEdadMin() {
		return edadMin;
		//hola
	}

	public void setEdadMin(int edadMin) {
		this.edadMin = edadMin;
	}
	
}
