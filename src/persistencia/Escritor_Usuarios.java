package persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import Usuarios.Cliente;
import Usuarios.Compra;

public class Escritor_Usuarios {

    public Escritor_Usuarios() {
        // Constructor vacío
    }

    public void escribirClientes(ArrayList<Cliente> clientes) {
        // Asegurarse de que el directorio exista
        File carpeta = new File("./data/");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        // Archivo de destino
        File archivo = new File("./data/Clientes.txt");

        // Si el archivo no existe, lo creamos vacío
        if (!archivo.exists()) {
            try (PrintWriter writer = new PrintWriter(archivo)) {
                // Crea un archivo vacío si no existe
                System.out.println("Archivo no encontrado, creando archivo vacío...");
            } catch (IOException e) {
                System.err.println("Error al crear el archivo: " + e.getMessage());
            }
        }

        // Verificar si el archivo está vacío antes de escribir
        if (archivo.length() == 0) {
            System.out.println("El archivo está vacío. Escribiendo datos...");
        }

        // Usar try-with-resources para asegurarnos de que el escritor se cierre adecuadamente
        try (PrintWriter escritor = new PrintWriter(archivo)) {
            for (Cliente cliente : clientes) {
                // Usamos StringBuilder para mejorar la eficiencia en la concatenación de cadenas
                StringBuilder linea = new StringBuilder();
                linea.append(cliente.getLogin()).append("--")
                     .append(cliente.getPassword());

                // Procesar el historial de compras del cliente
                ArrayList<Compra> historial = cliente.getHistorial();
                for (Compra compra : historial) {
                    linea.append("--")
                         .append(compra.isFechaVencida()) // Uso de getter
                         .append("--")
                         .append(compra.getNumeroTiquete());
                }

                // Escribir la línea al archivo
                escritor.println(linea.toString());
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
