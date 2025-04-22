package atracciones_y_espectaculos;

import java.util.ArrayList;
import java.util.List;

public class GestorAtracciones {
	protected ArrayList<Restriccion_clima> restriccionesClima;
	protected ArrayList <RestriccionSalud> restriccionesSalud;
	protected ArrayList<Atraccion> atracciones;
	protected ArrayList<Espectaculo> espectaculos;
	public GestorAtracciones(ArrayList<Atraccion> atracciones, ArrayList<Espectaculo> espectaculos, ArrayList<Restriccion_clima> restriccionesClima,ArrayList <RestriccionSalud> restriccionesSalud ) {
		super();
		this.atracciones = atracciones;
		this.espectaculos = espectaculos;
		this.restriccionesClima = restriccionesClima;
		this.restriccionesSalud = restriccionesSalud;
	}

	public ArrayList<Restriccion_clima> getRestriccionesClima() {
		return restriccionesClima;
	}

	public void setRestriccionesClima(ArrayList<Restriccion_clima> restriccionesClima) {
		this.restriccionesClima = restriccionesClima;
	}

	public ArrayList<RestriccionSalud> getRestriccionesSalud() {
		return restriccionesSalud;
	}

	public void setRestriccionesSalud(ArrayList<RestriccionSalud> restriccionesSalud) {
		this.restriccionesSalud = restriccionesSalud;
	}

	public ArrayList<Atraccion> getAtracciones() {
		return atracciones;
	}
	public void setAtracciones(ArrayList<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}
	public ArrayList<Espectaculo> getEspectaculos() {
		return espectaculos;
	}
	public void setEspectaculos(ArrayList<Espectaculo> espectaculos) {
		this.espectaculos = espectaculos;
	}
	public void agregarAtraccion (ArrayList<Atraccion> atracciones, Atraccion atraccion) {
		atracciones.add(atraccion);
	}
	public void eliminarAtraccion(String nombre) {
	    for (Atraccion atraccion : atracciones) {
	        if (atraccion.getNombre().equals(nombre)) {
	            atracciones.remove(atraccion);
	            return;
	        }
	    }
	}
	public void agregarEspectaculo(Espectaculo espectaculo) {
	    espectaculos.add(espectaculo);
	}
	public void eliminarEspectaculo(String nombre) {
	    for (Espectaculo espectaculo : espectaculos) {
	        if (espectaculo.getNombre().equals(nombre)) {
	            espectaculos.remove(espectaculo);
	            return;
	        }
	    }
	}
	public void agregarRestriccionSalud(RestriccionSalud restriccionSalud) {
	    restriccionesSalud.add(restriccionSalud);
	}
	public void eliminarRestriccionSalud(String nombre) {
	    for (RestriccionSalud restriccionSalud : restriccionesSalud) {
	        if (restriccionSalud.getNombre().equals(nombre)) {
	            restriccionesSalud.remove(restriccionSalud);
	            return;
	        }
	    }
	}
	public void agregarRestriccionClima(Restriccion_clima restriccionClima) {
	    restriccionesClima.add(restriccionClima);
	}
	public void eliminarRestriccionClima(String nombre) {
	    for (Restriccion_clima restriccionClima : restriccionesClima) {
	        if (restriccionClima.getTipo().equals(nombre)) {
	            restriccionesClima.remove(restriccionClima);
	            return;
	        }
	    }
	}
	public void registrarAtraccionMecanica(String nombre, 
            String lugar, 
            int numeroEmpleados, 
            int minEdad, 
            boolean funcionando,
            int cupoMaximo,
            List<Restriccion_clima> restriccionesClima,
            double minAltura, 
            double maxAltura, 
            double minPeso, 
            double maxPeso,
            String nivelRiesgo,
            RestriccionSalud restriccionSalud) {
		Mecanica mecanica = new Mecanica (  nombre, 
                 lugar, 
                 numeroEmpleados, 
                 minEdad, 
                 funcionando,
                 cupoMaximo,
                restriccionesClima,
                 minAltura, 
                 maxAltura, 
                 minPeso, 
                 maxPeso,
                 nivelRiesgo,
                 restriccionSalud);
		atracciones.add(mecanica);
	}
	public void registrarAtraccionCultural(String nombre, 
            String lugar, 
            int numeroEmpleados, 
            int minEdad, 
            boolean funcionando, 
            int cupoMaximo,
            List<Restriccion_clima> restriccionesClima,
            int edadMin) {
		Cultural cultural = new Cultural (nombre, 
                lugar, 
                numeroEmpleados, 
                minEdad, 
                funcionando,
                cupoMaximo,
               restriccionesClima,
               minEdad);
		atracciones.add(cultural);
	}

	}
  public void activarRestriccionClima(String nombre) {
		for(Restriccion_clima restriccionClima: restriccionesClima ) {
			if(restriccionClima.getTipo().equals(nombre)) {
				restriccionClima.activarRestriccion();
			}
		}
		
	public Atraccion buscarAtraccionPorNombre(String nombre) {
	    for (Atraccion atraccion : atracciones) {
	        if (atraccion.getNombre().equalsIgnoreCase(nombre)) {
	            return atraccion;
	        }
	    }
	    return null; 
}
