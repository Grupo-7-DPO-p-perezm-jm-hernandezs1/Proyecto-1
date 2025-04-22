package persistencia;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import tiquetes.Tiquete;
import Usuarios.Cliente;
public class Escritor_tiquete {
    public Escritor_tiquete() {
        
    }
    
    public void escribirTiquetes(ArrayList<Tiquete> tiquetes) {
        try {
            File carpeta = new File("./data/");
            if(!carpeta.exists()) {
                carpeta.mkdirs();
            }
            
            PrintWriter escritor = new PrintWriter(new File("./data/tiquetes.txt"));
            
            // Escribir cada tiquete en el archivo (similar a como se hace con canciones)
            for (Tiquete tiquete : tiquetes) {
                
                escritor.println(tiquete.isUsado()+"--"+tiquete.getExclusividad()+"--"+tiquete.getValor());
                escritor.println(tiquete.getExclusividad());
                escritor.println(tiquete.getValor());
            }
            
            escritor.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}