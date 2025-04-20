package atracciones_y_espectaculos;

import java.util.ArrayList;
import java.util.List;

public class Mecanica extends Atraccion {
    private int minAltura;
    private int maxAltura;
    private int minPeso;
    private int maxPeso;
    private RestriccionSalud restriccionSalud; 
    private String nivelRiesgo;

    public Mecanica(String nombre, String lugar, int cupoMaximo, 
            ArrayList<Restriccion_clima> restriccionClima,  
            int numeroEmpleados, int minEdad, boolean funcionando, 
            int minAltura, int maxAltura, int minPeso, int maxPeso,
            RestriccionSalud restriccionSalud, String nivelRiesgo) {
 
 super(nombre, lugar, cupoMaximo, restriccionClima, numeroEmpleados, minEdad, funcionando);
 
 this.minAltura = minAltura;
 this.maxAltura = maxAltura;
 this.minPeso = minPeso;
 this.maxPeso = maxPeso;
 this.restriccionSalud = restriccionSalud;
 this.nivelRiesgo = nivelRiesgo;
}

    // Getters y Setters
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

    public RestriccionSalud getRestriccionSalud() {
        return restriccionSalud;
    }

    public void setRestriccionSalud(RestriccionSalud restriccionSalud) {
        this.restriccionSalud = restriccionSalud;
    }

    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(String nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }
}