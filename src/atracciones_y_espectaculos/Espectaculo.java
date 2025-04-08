package atracciones_y_espectaculos;

import java.time.LocalDateTime;

public class Espectaculo {
	private LocalDateTime horario;
	private LocalDateTime fecha;
	

	public Espectaculo(LocalDateTime horario, LocalDateTime fecha) {
		super();
		this.horario = horario;
		this.fecha = fecha;
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
