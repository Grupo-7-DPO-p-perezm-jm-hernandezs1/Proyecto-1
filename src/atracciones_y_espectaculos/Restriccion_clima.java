package atracciones_y_espectaculos;

import java.util.ArrayList;

public class Restriccion_clima {
    protected String tipo;
    protected ArrayList<String> atracciones;
    protected ArrayList<String> espectaculos;
    
    public Restriccion_clima(String tipo, ArrayList<String> atracciones, ArrayList<String> espectaculos) {
        super();
        this.tipo = tipo;
        this.atracciones = atracciones;
        this.espectaculos = espectaculos;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public ArrayList<String> getAtracciones() {
        return atracciones;
    }
    
    public void setAtracciones(ArrayList<String> atracciones) {
        this.atracciones = atracciones;
    }
    
    public ArrayList<String> getEspectaculos() {
        return espectaculos;
    }
    
    public void setEspectaculos(ArrayList<String> espectaculos) {
        this.espectaculos = espectaculos;
    }
    
    public void agregarAtraccion(String atraccionNombre) {
        atracciones.add(atraccionNombre);
    }
    
    public void eliminarAtraccion(String nombre) {
        for (String atraccion : atracciones) {
            if (atraccion==nombre) {
                atracciones.remove(atraccion);
                return;
            }
        }
    }
    
    public void agregarEspectaculo(String espectaculo) {
        espectaculos.add(espectaculo);
    }
    
    public void eliminarEspectaculo(String nombre) {
        for (String espectaculo : espectaculos) {
            if (espectaculo==nombre) {
                espectaculos.remove(espectaculo);
                return;
            }
        }
    }
    
   // public void activarRestriccion() {
       // for (Atraccion atraccion : atracciones) {
        //    atraccion.setFuncionando(false);
       // }
        //for (Espectaculo espectaculo : espectaculos) {
          //  espectaculo.setFuncionando(false);
      //  }
    //}
    
    //public void quitarRestriccion() {
      //  for (Atraccion atraccion : atracciones) {
       //     atraccion.setFuncionando(true);
     //   }
    //    for (Espectaculo espectaculo : espectaculos) {
       //     espectaculo.setFuncionando(true);
     //   }
   // }
    
}



