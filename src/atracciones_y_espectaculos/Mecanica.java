package atracciones_y_espectaculos;
import java.util.ArrayList;
public class Mecanica extends Atraccion {
	private int minAltura;
	private int maxAltura;
	private int minPeso;
	private int maxPeso;
	private ArrayList<String> restriccionesSalud;
	private String nivelRiesgo;
	
	public Mecanica(String lugar, int cupoMaximo, ArrayList<String> recomendaciones, ArrayList<String> restriccionClima,
			int numeroEmpleados, int minEdad, boolean deTemporada, int minAltura, int maxAltura, int minPeso,
			int maxPeso, ArrayList<String> restriccionesSalud, String nivelRiesgo) {
		super(lugar, cupoMaximo, recomendaciones, restriccionClima, numeroEmpleados, minEdad, deTemporada);
		this.minAltura = minAltura;
		this.maxAltura = maxAltura;
		this.minPeso = minPeso;
		this.maxPeso = maxPeso;
		this.restriccionesSalud = restriccionesSalud;
		this.nivelRiesgo = nivelRiesgo;
	}

	public int getMinAltura() {
		return minAltura;
	}

	public void setMinAltura(int minAltura) {
		this.minAltura = minAltura;
	}

	public int getMaxAltura() {
		return maxAltura;
	}

	public void setMaxAltura(int maxAltura) {
		this.maxAltura = maxAltura;
	}

	public int getMinPeso() {
		return minPeso;
	}

	public void setMinPeso(int minPeso) {
		this.minPeso = minPeso;
	}

	public int getMaxPeso() {
		return maxPeso;
	}

	public void setMaxPeso(int maxPeso) {
		this.maxPeso = maxPeso;
	}

	public ArrayList<String> getRestriccionesSalud() {
		return restriccionesSalud;
	}

	public void setRestriccionesSalud(ArrayList<String> restriccionesSalud) {
		this.restriccionesSalud = restriccionesSalud;
	}

	public String getNivelRiesgo() {
		return nivelRiesgo;
	}

	public void setNivelRiesgo(String nivelRiesgo) {
		this.nivelRiesgo = nivelRiesgo;
	}
	
	
	
}
