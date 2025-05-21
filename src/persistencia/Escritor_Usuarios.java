package persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import Usuarios.Cliente;
import Usuarios.Compra;
import Usuarios.Empleado;
import atracciones_y_espectaculos.Atraccion;

public class Escritor_Usuarios {

    public Escritor_Usuarios() {
    }
    
    public void escribirClientes(ArrayList<Cliente> clientes) {
        //File directorio = new File(".\\data\\");  // Carpeta, no el archivo
        
        

        

        try  {  // try-with-resources
        	File archivo = new File(".\\data\\clientes.txt");
        	PrintWriter escritor = new PrintWriter(archivo);
            for (Cliente cliente : clientes) {
                String linea = cliente.getLogin() + "--" + cliente.getPassword() + "--";

                ArrayList<Compra> historial = cliente.getHistorial();
                if (historial != null && !historial.isEmpty()) {
                    linea += "--";  // Separador antes del historial
                    
                    for (Compra compra : historial) {
                        linea += compra.isFechaVencida() + "," + compra.getNumeroTiquete() + "compra:";
                    }
                }

                escritor.println(linea);
                escritor.close();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    
    
}

