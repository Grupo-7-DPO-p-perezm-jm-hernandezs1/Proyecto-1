package atracciones_y_espectaculos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Espectaculo {
    private String nombre;
    private List<LocalDateTime> horario;

    private boolean funcionando = true;
    

    public Espectaculo(String nombre, ArrayList<LocalDateTime> horario, 
                      boolean funcionando) {
        this.nombre = nombre;
        this.horario = horario;
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
    public boolean isFuncionando() {
        return funcionando;
    }

    public void setFuncionando(boolean funcionando) {
        this.funcionando = funcionando;
    }
}