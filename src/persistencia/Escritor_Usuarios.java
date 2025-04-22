package persistencia;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Usuarios.Cliente;
import Usuarios.Compra;
public class Escritor_Usuarios {
	
public Escritor_Usuarios(){
	
}
public void escribirClientes(ArrayList<Cliente> clientes) {
	try {
        File carpeta = new File("./data/");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        PrintWriter escritor = new PrintWriter(new File("./data/Clientes.txt"));
        for (Cliente cliente: clientes) {
        	String linea = cliente.getLogin()+"--"+
        				cliente.getPassword();
        	ArrayList<Compra> historial = cliente.getHistorial();
        	for(Compra compra: historial) {
        		linea=linea+"--"+compra.fechaVencida+"--"+compra.numeroTiquete;
        	}
        	 escritor.println(linea);
        }
        escritor.close();
	}
	
        catch (IOException e) {
	        System.err.println("Error al escribir el archivo: " + e.getMessage());
	    }
	}

}

