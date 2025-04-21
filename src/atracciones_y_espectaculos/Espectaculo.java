package atracciones_y_espectaculos;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Espectaculo {
	private String nombre;
	private ArrayList<LocalDateTime> horario;
	private ArrayList<LocalDateTime> fechas;
	private boolean funcionando;
	public Espectaculo(String nombre, ArrayList<LocalDateTime> horario, ArrayList<LocalDateTime> fechas,
			boolean funcionando) {
		super();
		this.nombre = nombre;
		this.horario = horario;
		this.fechas = fechas;
		this.funcionando = funcionando;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<LocalDateTime> getHorario() {
		return horario;
	}
	public void setHorario(ArrayList<LocalDateTime> horario) {
		this.horario = horario;
	}
	public ArrayList<LocalDateTime> getFechas() {
		return fechas;
	}
	public void setFechas(ArrayList<LocalDateTime> fechas) {
		this.fechas = fechas;
	}
	public boolean isFuncionando() {
		return funcionando;
	}
	public void setFuncionando(boolean funcionando) {
		this.funcionando = funcionando;
	}
	
	
}
