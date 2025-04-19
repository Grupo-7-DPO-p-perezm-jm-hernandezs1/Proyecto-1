package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import tiquetes.Tiquete;

public class Lector_Tiquete {

    public Lector_Tiquete() {
    }

    public ArrayList<Tiquete> leerTiquetes() {
        ArrayList<Tiquete> rta = new ArrayList<Tiquete>();
        
        try {
            BufferedReader lector = new BufferedReader(new FileReader(new File("./data/tiquetesGuardados.txt")));
            String linea = lector.readLine();
            
            while (linea != null) {
                System.out.println(linea);
                String[] datos = linea.split("--");
                
                boolean usado = Boolean.parseBoolean(datos[0]);
                String exclusividad = datos[1];
                double valor = Double.parseDouble(datos[2]);
                
                Tiquete t = new Tiquete(usado, exclusividad, valor);
                rta.add(t);
                linea = lector.readLine();
            }
            
            lector.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return rta;
    }
}
