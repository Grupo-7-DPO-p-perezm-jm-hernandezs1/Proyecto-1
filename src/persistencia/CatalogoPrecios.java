package persistencia;

import java.io.*;
import java.util.HashMap;

public class CatalogoPrecios {
    private static final String ARCHIVO_PRECIOS = "./data/precios.txt";
    private HashMap<String, Double> precios;

    public CatalogoPrecios() {
        precios = new HashMap<>();
        cargarPrecios();
    }

    private void cargarPrecios() {
        File archivo = new File(ARCHIVO_PRECIOS);
        if (!archivo.exists()) {
            guardarPrecios();
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("=");
                if (partes.length == 2) {
                    precios.put(partes[0], Double.parseDouble(partes[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarPrecios() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_PRECIOS))) {
            for (String tipo : precios.keySet()) {
                pw.println(tipo + "=" + precios.get(tipo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getPrecio(String tipo) {
        return precios.getOrDefault(tipo, 0.0);
    }

    public void setPrecio(String tipo, double precio) {
        precios.put(tipo, precio);
        guardarPrecios();
    }
    
}
