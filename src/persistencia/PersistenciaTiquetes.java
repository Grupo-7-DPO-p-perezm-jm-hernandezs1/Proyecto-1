package persistencia;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tiquetes.Categoria;
import tiquetes.Tiquete;
import tiquetes.TiqueteBasico;
import tiquetes.TiqueteGeneral;
import tiquetes.TiqueteIndividual;
import tiquetes.TiqueteTemporada;
import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.GestorAtracciones;

public class PersistenciaTiquetes {
    private final File archivoContador;
    private final File archivoTiquetesUsados;
    private final File archivoTiquetesTotales;
    private GestorAtracciones gestoratracciones;
    

    public PersistenciaTiquetes() {
        File carpeta = new File("./data/");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        this.archivoContador = new File(carpeta, "contador.txt");
        this.archivoTiquetesUsados = new File(carpeta, "tiquete_usado.txt");
        this.archivoTiquetesTotales = new File(carpeta, "tiquetes.txt");

        try {
            leerContador(); 
        } catch (Exception e) {
            escribirContador(0);
        }

        try {
            if (!archivoTiquetesUsados.exists()) {
                archivoTiquetesUsados.createNewFile();
            }
            if (!archivoTiquetesTotales.exists()) {
                archivoTiquetesTotales.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Funcionalidad del contador
    public void incrementar() {
        int numeroActual = leerContador();
        escribirContador(numeroActual + 1);
    }

    public int getValorActual() {
        return leerContador();
    }

    public int leerContador() {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoContador))) {
            String linea = lector.readLine();
            return linea != null ? Integer.parseInt(linea.trim()) : 0;
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Error al leer el contador", e);
        }
    }

    public void escribirContador(int numero) {
        try (PrintWriter escritor = new PrintWriter(archivoContador)) {
            escritor.println(numero);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Tiquetes usados
    public void agregarTiqueteUsado(String codigo) {
        escribirEnArchivo(archivoTiquetesUsados, codigo);
    }

    public List<String> leerTiquetesUsados() {
        return leerDesdeArchivo(archivoTiquetesUsados);
    }

    //Tiquetes totales
    public List<Tiquete> leerTiquetes() {
        List<Tiquete> tiquetes = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivoTiquetesTotales));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("--");
                String id = partes[0];
                double precio = Double.parseDouble(partes[1]);

                if (id.startsWith("TEMP")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date inicio = sdf.parse(partes[2]);
                    Date fin = sdf.parse(partes[3]);
                    Categoria tipo = Categoria.valueOf(partes[4]);
                    TiqueteTemporada tt = new TiqueteTemporada(id, precio, inicio, fin, tipo);
                    tiquetes.add(tt);
                } else if (id.startsWith("GENE")) {
                    Categoria tipo = Categoria.valueOf(partes[2]);
                    TiqueteGeneral tg = new TiqueteGeneral(id, precio, tipo);
                    tiquetes.add(tg);
                } else if (id.startsWith("INDV")) {
                   
                	Atraccion atraccion = gestoratracciones.buscarAtraccionPorNombre(partes[2]);
                	if (atraccion == null) {
                	    System.err.println("No se encontró la atraccion con nombre: " + partes[2]);
                	    continue; 
                	}
                	TiqueteIndividual ti = new TiqueteIndividual(id, precio, atraccion);
                	tiquetes.add(ti);
                } else if (id.startsWith("BASI")) {
                    TiqueteBasico tb = new TiqueteBasico(id, precio);
                    tiquetes.add(tb);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tiquetes;
    }


    public void guardarTiquete(Tiquete tiquete) {
        try {
            FileWriter fw = new FileWriter(archivoTiquetesTotales, true);
            PrintWriter pw = new PrintWriter(fw);

            if (tiquete instanceof TiqueteTemporada) {
                TiqueteTemporada tt = (TiqueteTemporada) tiquete;
                String linea = String.format("%s--%.2f--%tF--%tF--%s",
                    tt.getIdentificador(),
                    tt.getPrecio(),
                    tt.fechaInicio,
                    tt.fechaFinal,
                    tt.getTipo().name()
                );
                pw.println(linea);
            } else if (tiquete instanceof TiqueteGeneral) {
                TiqueteGeneral tg = (TiqueteGeneral) tiquete;
                String linea = String.format("%s--%.2f--%s",
                    tg.getIdentificador(),
                    tg.getPrecio(),
                    tg.getTipo().name()
                );
                pw.println(linea);
            } else if (tiquete instanceof TiqueteIndividual) {
                TiqueteIndividual ti = (TiqueteIndividual) tiquete;
                String linea = String.format("%s--%.2f--%s",
                    ti.getIdentificador(),
                    ti.getPrecio(),
                    ti.getAtraccionValida().getNombre()
                );
                pw.println(linea);
            } else if (tiquete instanceof TiqueteBasico) {
                String linea = String.format("%s--%.2f",
                    tiquete.getIdentificador(),
                    tiquete.getPrecio()
                );
                pw.println(linea);
            }

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Utilidades compartidas
    private void escribirEnArchivo(File archivo, String linea) {
        try (FileWriter fw = new FileWriter(archivo, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(linea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> leerDesdeArchivo(File archivo) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineas;
    }
}
