package tiquetes;

import java.time.LocalDate;

public class FastPass {
    private String identificador;
    private LocalDate fechaValida;
    private double precio;

    public FastPass(String identificador, LocalDate fechaValida, double precio) {
        this.identificador = identificador;
        this.fechaValida = fechaValida;
        this.precio = precio;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public LocalDate getFechaValida() {
        return fechaValida;
    }

    public void setFechaValida(LocalDate fechaValida) {
        this.fechaValida = fechaValida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
