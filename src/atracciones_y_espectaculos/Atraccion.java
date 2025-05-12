package atracciones_y_espectaculos;

import java.util.ArrayList;
import java.util.List;

public class Atraccion {
	protected String nombre;
	protected String lugar;
	protected int cupoMaximo;
	protected ArrayList <Restriccion_clima> restriccionClima;
	protected int numeroEmpleados;
	protected boolean funcionando= true;
	

	public Atraccion(String nombre, String lugar, int cupoMaximo, ArrayList<Restriccion_clima> restriccionClima,
			int numeroEmpleados, boolean funcionando) {
		super();
		this.nombre = nombre;
		this.lugar = lugar;
		this.cupoMaximo = cupoMaximo;
		this.restriccionClima = restriccionClima;
		this.numeroEmpleados = numeroEmpleados;
		this.funcionando = funcionando;
		
	}
	public boolean isFuncionando() {
		return funcionando;
	}
	public void setFuncionando(boolean funcionando) {
		this.funcionando = funcionando;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public int getCupoMaximo() {
		return cupoMaximo;
	}
	public void setCupoMaximo(int cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}
	public ArrayList<Restriccion_clima> getRestriccionClima() {
		return restriccionClima;
	}
	public void setRestriccionClima(ArrayList<Restriccion_clima> restriccionesClima) {
		this.restriccionClima = restriccionesClima;
	}
	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}
	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}
	
}