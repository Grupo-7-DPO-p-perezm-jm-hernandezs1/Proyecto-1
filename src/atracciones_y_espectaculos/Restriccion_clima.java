package atracciones_y_espectaculos;

import java.util.ArrayList;

public class Restriccion_clima {
    protected String tipo;
    protected ArrayList<Atraccion> atracciones;
    protected ArrayList<Espectaculo> espectaculos;
    
    public Restriccion_clima(String tipo, ArrayList<Atraccion> atracciones, ArrayList<Espectaculo> espectaculos) {
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
    
    public ArrayList<Atraccion> getAtracciones() {
        return atracciones;
    }
    
    public void setAtracciones(ArrayList<Atraccion> atracciones) {
        this.atracciones = atracciones;
    }
    
    public ArrayList<Espectaculo> getEspectaculos() {
        return espectaculos;
    }
    
    public void setEspectaculos(ArrayList<Espectaculo> espectaculos) {
        this.espectaculos = espectaculos;
    }
    
    public void agregarAtraccion(Atraccion atraccion) {
        atracciones.add(atraccion);
    }
    
    public void eliminarAtraccion(String nombre) {
        for (Atraccion atraccion : atracciones) {
            if (atraccion.getNombre().equals(nombre)) {
                atracciones.remove(atraccion);
                return;
            }
        }
    }
    
    public void agregarEspectaculo(Espectaculo espectaculo) {
        espectaculos.add(espectaculo);
    }
    
    public void eliminarEspectaculo(String nombre) {
        for (Espectaculo espectaculo : espectaculos) {
            if (espectaculo.getNombre().equals(nombre)) {
                espectaculos.remove(espectaculo);
                return;
            }
        }
    }
    
    public void activarRestriccion() {
        for (Atraccion atraccion : atracciones) {
            atraccion.setFuncionando(false);
        }
        for (Espectaculo espectaculo : espectaculos) {
            espectaculo.setFuncionando(false);
        }
    }
    
    public void quitarRestriccion() {
        for (Atraccion atraccion : atracciones) {
            atraccion.setFuncionando(true);
        }
        for (Espectaculo espectaculo : espectaculos) {
            espectaculo.setFuncionando(true);
        }
    }
}

