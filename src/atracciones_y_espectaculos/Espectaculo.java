package atracciones_y_espectaculos;

import java.time.LocalDateTime;
import java.util.List;

public class Espectaculo {
    private String nombre;
    private List<LocalDateTime> horario;
    private List<LocalDateTime> fechas;
    private boolean funcionando = true;

    public Espectaculo(String nombre, List<LocalDateTime> horario, List<LocalDateTime> fechas,
                      boolean funcionando) {
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

    public List<LocalDateTime> getHorario() {
        return horario;
    }

    public void setHorario(List<LocalDateTime> horario) {
        this.horario = horario;
    }

    public List<LocalDateTime> getFechas() {
        return fechas;
    }

    public void setFechas(List<LocalDateTime> fechas) {
        this.fechas = fechas;
    }

    public boolean isFuncionando() {
        return funcionando;
    }

    public void setFuncionando(boolean funcionando) {
        this.funcionando = funcionando;
    }
}
