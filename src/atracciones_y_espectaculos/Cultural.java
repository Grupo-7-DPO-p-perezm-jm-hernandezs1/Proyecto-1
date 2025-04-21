package atracciones_y_espectaculos;

import java.util.ArrayList;

public class Cultural extends Atraccion{

	private  int edadMin;

	public Cultural(String nombre, String lugar, int cupoMaximo, 
            ArrayList<Restriccion_clima> restriccionClima,  
            int numeroEmpleados, int minEdad, boolean funcionando, int edadMin) {
		 super(nombre, lugar, cupoMaximo, restriccionClima, numeroEmpleados, minEdad, funcionando);
		
		this.edadMin = edadMin;
	}

	public int getEdadMin() {
		return edadMin;
	}

	public void setEdadMin(int edadMin) {
		this.edadMin = edadMin;
	}
	
}
