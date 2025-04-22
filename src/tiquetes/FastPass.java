package tiquetes;

import java.util.Date;

public class FastPass {
    private String identificador;
    private Date fechaValida;
    private double precio;

    public FastPass(String identificador, Date fechaValida, double precio) {
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

    public Date getFechaValida() {
        return fechaValida;
    }

    public void setFechaValida(Date fechaValida) {
        this.fechaValida = fechaValida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
