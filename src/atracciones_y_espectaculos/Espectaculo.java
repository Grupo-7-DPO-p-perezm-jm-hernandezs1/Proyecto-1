package atracciones_y_espectaculos;

import java.time.LocalDateTime;

public class Espectaculo {
	private String nombre;
	private LocalDateTime horario;
	private LocalDateTime fecha;
	private boolean funcionando;
	

	public Espectaculo(LocalDateTime horario, LocalDateTime fecha, String nombre, boolean funcionando) {
		super();
		this.nombre = nombre;
		this.horario = horario;
		this.fecha = fecha;
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

	public LocalDateTime getHorario() {
		return horario;
	}
	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	
}
