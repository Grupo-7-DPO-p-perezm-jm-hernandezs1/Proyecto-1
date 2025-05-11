package persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import Usuarios.Cliente;
import Usuarios.Compra;
import Usuarios.Empleado;

public class Escritor_Usuarios {

    public Escritor_Usuarios() {
        // Constructor vacío
    }

    public void escribirClientes(ArrayList<Cliente> clientes) {
       
        File carpeta = new File(".\\Proyecto-1-main\\data\\clientes.tx");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

       
        File archivo = new File(".\\Proyecto-1-main\\data\\clientes.tx");

        // Si el archivo no existe, lo creamos vacío
        if (!archivo.exists()) {
            try (PrintWriter writer = new PrintWriter(archivo)) {
                // Crea un archivo vacío si no existe
                System.out.println("Archivo no encontrado, creando archivo vacío...");
            } catch (IOException e) {
                System.err.println("Error al crear el archivo: " + e.getMessage());
            }
        }


        
        try (PrintWriter escritor = new PrintWriter(archivo)) {
            for (Cliente cliente : clientes) {
                StringBuilder linea = new StringBuilder();
                linea.append(cliente.getLogin()).append("--")
                     .append(cliente.getPassword());
                
                ArrayList<Compra> historial = cliente.getHistorial();
                for (Compra compra : historial) {
                    linea.append("--")
                         .append(compra.isFechaVencida()) 
                         .append("--")
                         .append(compra.getNumeroTiquete());
                }

                escritor.println(linea.toString());
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
    
    
}
