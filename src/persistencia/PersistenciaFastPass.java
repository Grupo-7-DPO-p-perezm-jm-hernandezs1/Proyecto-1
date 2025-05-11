package persistencia;

import tiquetes.FastPass;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersistenciaFastPass {

    private static final String archivo_fastpass = "./data/fastpass.txt";

    public static void guardarFastPass(FastPass fastPass) {
        try {
            File carpeta = new File("./data/");
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            PrintWriter escritor = new PrintWriter(new FileWriter(archivo_fastpass, true));

            escritor.println(fastPass.getIdentificador() + "--" + fastPass.getFechaValida() + "--" + fastPass.getPrecio());

            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<FastPass> leerFastPass() {
        List<FastPass> fastPasses = new ArrayList<>();
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo_fastpass));
            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split("--");
                String identificador = datos[0];
                Date fechaValida = new SimpleDateFormat("yyyy-MM-dd").parse(datos[1]); 
                double precio = Double.parseDouble(datos[2]);

                FastPass fastPass = new FastPass(identificador, fechaValida, precio);
                fastPasses.add(fastPass);
            }

            lector.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return fastPasses;
    }
}
