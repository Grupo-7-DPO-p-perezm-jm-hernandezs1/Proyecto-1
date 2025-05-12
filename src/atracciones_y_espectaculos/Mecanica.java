package atracciones_y_espectaculos;

import java.util.ArrayList;


public class Mecanica extends Atraccion {
    private double minAltura;
    private double maxAltura;
    private double minPeso;
    private double maxPeso;
    private RestriccionSalud restriccionSalud;
    private String nivelRiesgo;

    public Mecanica(String nombre, 
                   String lugar, 
                   int numeroEmpleados, 
                    
                   boolean funcionando,
                   int cupoMaximo,
                   ArrayList<Restriccion_clima> restriccionesClima,
                   double minAltura, 
                   double maxAltura, 
                   double minPeso, 
                   double maxPeso,
                   String nivelRiesgo,
                   RestriccionSalud restriccionSalud) {
        
        super(nombre, lugar, cupoMaximo, restriccionesClima, numeroEmpleados, funcionando);
        
        this.minAltura = minAltura;
        this.maxAltura = maxAltura;
        this.minPeso = minPeso;
        this.maxPeso = maxPeso;
        this.nivelRiesgo = nivelRiesgo;
        this.restriccionSalud = restriccionSalud;
        
 
        if (restriccionesClima != null) {
            for (Restriccion_clima restriccion : restriccionesClima) {
                restriccion.agregarAtraccion(this.getNombre());
            }
        }
        
        if (restriccionSalud != null) {
            restriccionSalud.getAtraccionesMecanica().add(this.getNombre());
        }
    }

  
    public double getMinAltura() {
        return minAltura;
    }

    public void setMinAltura(double minAltura) {
        this.minAltura = minAltura;
    }

    public double getMaxAltura() {
        return maxAltura;
    }

    public void setMaxAltura(double maxAltura) {
        this.maxAltura = maxAltura;
    }

    public double getMinPeso() {
        return minPeso;
    }

    public void setMinPeso(double minPeso) {
        this.minPeso = minPeso;
    }

    public double getMaxPeso() {
        return maxPeso;
    }

    public void setMaxPeso(double maxPeso) {
        this.maxPeso = maxPeso;
    }

    public RestriccionSalud getRestriccionSalud() {
        return restriccionSalud;
    }

    public void setRestriccionSalud(RestriccionSalud restriccionSalud) {
       
        if (this.restriccionSalud != null) {
            this.restriccionSalud.getAtraccionesMecanica().remove(this.getNombre());
        }
        
        this.restriccionSalud = restriccionSalud;
        
       
        if (restriccionSalud != null) {
            restriccionSalud.getAtraccionesMecanica().add(this.getNombre());
        }
    }
    

    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(String nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }
    
    
    public void agregarRestriccionesClima(ArrayList<Restriccion_clima> nuevasRestricciones) {
        if (nuevasRestricciones != null) {
            for (Restriccion_clima restriccion : nuevasRestricciones) {
                if (!this.getRestriccionClima().contains(restriccion)) {
                    this.getRestriccionClima().add(restriccion);
                    restriccion.agregarAtraccion(this.getNombre());
                }
            }
        }
    }
}