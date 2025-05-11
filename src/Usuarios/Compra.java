package Usuarios;

public class Compra {

    private boolean fechaVencida;
    private String numeroTiquete;

    // Constructor con parámetros
    public Compra(boolean fechaVencida, String numeroTiquete) {
        this.fechaVencida = fechaVencida;
        this.numeroTiquete = numeroTiquete;
    }

    public boolean isFechaVencida() {
        return fechaVencida;
    }

    public void setFechaVencida(boolean fechaVencida) {
        this.fechaVencida = fechaVencida;
    }

    public String getNumeroTiquete() {
        return numeroTiquete;
    }

    public void setNumeroTiquete(String numeroTiquete) {
        this.numeroTiquete = numeroTiquete;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "fechaVencida=" + fechaVencida +
                ", numeroTiquete='" + numeroTiquete + '\'' +
                '}';
    }
}
