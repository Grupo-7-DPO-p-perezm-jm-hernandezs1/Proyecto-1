package atracciones_y_espectaculos;

import java.util.ArrayList;

public class Cultural extends Atraccion {

    private int edadMin;

    
    public Cultural(String nombre, 
                   String lugar, 
                   int numeroEmpleados,  
                   boolean funcionando, 
                   int cupoMaximo,
                   ArrayList<Restriccion_clima> restriccionesClima,
                   int edadMin) {
        super(nombre, lugar, cupoMaximo, restriccionesClima, numeroEmpleados, funcionando);
        this.edadMin = edadMin;
        
     
        if (restriccionesClima != null) {
            for (Restriccion_clima restriccion : restriccionesClima) {
                restriccion.agregarAtraccion(this.getNombre());
            }
        }
    }

    public int getEdadMin() {
        return edadMin;
    }

    public void setEdadMin(int edadMin) {
        this.edadMin = edadMin;
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