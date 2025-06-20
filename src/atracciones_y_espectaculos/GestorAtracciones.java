package atracciones_y_espectaculos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import persistencia.Escritor_Atracciones_Y_Espectaculos;
import persistencia.Escritor_Lugares_Trabajo;

public class GestorAtracciones {
	protected ArrayList<Restriccion_clima> restriccionesClima;
	protected ArrayList<RestriccionSalud> restriccionesSalud;
	protected ArrayList<Atraccion> atracciones;
	protected ArrayList<Espectaculo> espectaculos;
	protected Escritor_Atracciones_Y_Espectaculos escritorAYE;
	protected Escritor_Lugares_Trabajo escritorLT;
	
	
	public GestorAtracciones(ArrayList<Atraccion> atracciones, ArrayList<Espectaculo> espectaculos,
			ArrayList<Restriccion_clima> restriccionesClima, ArrayList<RestriccionSalud> restriccionesSalud) {
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

	public void agregarAtraccion(ArrayList<Atraccion> atracciones, Atraccion atraccion) {
		atracciones.add(atraccion);
	}
	public void agregarRestriccionClimaNuevo(ArrayList<Restriccion_clima> restricciones, Restriccion_clima restriccion) {
		restricciones.add(restriccion);
		
}
	public void eliminarAtraccion(String nombre) {
		for (Atraccion atraccion : atracciones) {
			if (atraccion.getNombre().equals(nombre)) {
				atracciones.remove(atraccion);
				return;
			}
		}
	}
	public Restriccion_clima buscarRestriccionClima (String nombre) {
		Restriccion_clima respuesta = null; 
		for(Restriccion_clima restriccion: restriccionesClima ) {
			if (restriccion.getTipo().equals(nombre)) {
				respuesta  = restriccion;
			}
		}
		return respuesta;
	}
	public RestriccionSalud buscarRestriccionSalud(String nombre) {
		RestriccionSalud respuesta = null;
		for(RestriccionSalud restriccion : restriccionesSalud) {
			if(restriccion.getNombre().equals(nombre)) {
				respuesta = restriccion;
			}
		}
		return respuesta;
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
	
	

	public void registrarAtraccionMecanica(String nombre, String lugar, int numeroEmpleados,
			boolean funcionando, int cupoMaximo, ArrayList<Restriccion_clima> restriccionesClima, double minAltura,
			double maxAltura, double minPeso, double maxPeso, String nivelRiesgo, RestriccionSalud restriccionSalud) throws IOException {
		Mecanica mecanica = new Mecanica(nombre, lugar, numeroEmpleados, funcionando, cupoMaximo,
				restriccionesClima, minAltura, maxAltura, minPeso, maxPeso, nivelRiesgo, restriccionSalud);
		atracciones.add(mecanica);
		ArrayList<Atraccion> input = new ArrayList<Atraccion>();
		input.add(mecanica);
		escritorAYE.escribirAtracciones(input);
	}

	public void registrarAtraccionCultural(String nombre, String lugar, int numeroEmpleados, int minEdad,
			boolean funcionando, int cupoMaximo, ArrayList<Restriccion_clima> restriccionesClima, int edadMin) throws IOException {
		Cultural cultural = new Cultural(nombre, lugar, numeroEmpleados, funcionando, cupoMaximo,
				restriccionesClima, minEdad);
		atracciones.add(cultural);
		ArrayList<Atraccion> input = new ArrayList<Atraccion>();
		input.add(cultural);
		escritorAYE.escribirAtracciones(input);
	}

	public void activarRestriccionClima(String nombre) {
		for (Restriccion_clima restriccionClima : restriccionesClima) {
			if (restriccionClima.getTipo().equals(nombre)) {
			ArrayList <String>listaAtraccion = restriccionClima.getAtracciones();
			ArrayList <String> listaEspectaculo= restriccionClima.getEspectaculos();
				
				for(String atraccion: listaAtraccion) {
					for (Atraccion atraccion1: atracciones) {
						if (atraccion1.getNombre().equals(atraccion)) {
							atraccion1.setFuncionando(false);
						}
					}
				}
				for(String espectaculo: listaEspectaculo) {
					for (Espectaculo espectaculo1: espectaculos) {
						if (espectaculo1.getNombre().equals(espectaculo)) {
							espectaculo1.setFuncionando(false);
						}
					}
				

			}
			}
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
	public Espectaculo buscarEspectaculoPorNombre(String nombre) {
		for (Espectaculo espectaculo : espectaculos) {
			if (espectaculo.getNombre().equalsIgnoreCase(nombre)) {
				return espectaculo;
			}
		}
		return null;
	}
}