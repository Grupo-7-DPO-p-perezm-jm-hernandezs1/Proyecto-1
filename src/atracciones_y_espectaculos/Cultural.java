package atracciones_y_espectaculos;

import java.util.List;

public class Cultural extends Atraccion {

    private int edadMin;

    
    public Cultural(String nombre, 
                   String lugar, 
                   int numeroEmpleados, 
                   int minEdad, 
                   boolean funcionando, 
                   int cupoMaximo,
                   List<Restriccion_clima> restriccionesClima,
                   int edadMin) {
        super(nombre, lugar, cupoMaximo, restriccionesClima, numeroEmpleados, minEdad, funcionando);
        this.edadMin = edadMin;
        
     
        if (restriccionesClima != null) {
            for (Restriccion_clima restriccion : restriccionesClima) {
                restriccion.agregarAtraccion(this);
            }
        }
    }

    public int getEdadMin() {
        return edadMin;
    }

    public void setEdadMin(int edadMin) {
        this.edadMin = edadMin;
    }
    
    
    public void agregarRestriccionesClima(List<Restriccion_clima> nuevasRestricciones) {
        if (nuevasRestricciones != null) {
            for (Restriccion_clima restriccion : nuevasRestricciones) {
                if (!this.getRestriccionClima().contains(restriccion)) {
                    this.getRestriccionClima().add(restriccion);
                    restriccion.agregarAtraccion(this);
                }
            }
        }
    }
}