package InterfazGrafica;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import Principal.ParquePrincipal;
import tiquetes.Categoria;

public class MostrarTiqueteGenerar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Categoria> comboBox;
	private ParquePrincipal parque;
	private Categoria cate;
	private JTextArea textoEspecificaciones; // ESTE es el que se usará visualmente

	/**
	 * Create the frame.
	 */
	public MostrarTiqueteGenerar(ParquePrincipal parque, Categoria cate) {
		this.cate = cate;
		this.parque = parque;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 600);
        getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
        JPanel panel_1 = new JPanel(new GridLayout(1, 2, 0, 0));
        getContentPane().add(panel_1);

        JPanel panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textoEspecificaciones = new JTextArea("Especificaciones:");
        textoEspecificaciones.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textoEspecificaciones.setEditable(false);
        textoEspecificaciones.setOpaque(false); 
        textoEspecificaciones.setFocusable(false);
        textoEspecificaciones.setLineWrap(true);
        textoEspecificaciones.setWrapStyleWord(true);
        panel_2.add(textoEspecificaciones);
        panel_1.add(panel_2);

        JPanel panel_3 = new JPanel();
        JLabel lblqr = new JLabel("Cargando QR...");
        panel_3.add(lblqr);
        panel_1.add(panel_3);

        try {
            ImageIcon qr = generarQR();
            lblqr.setIcon(qr);
            lblqr.setText("");
        } catch (IOException e) {
            e.printStackTrace();
            lblqr.setText("No se pudo generar el QR");
        }
        setVisible(true);
	}
	public ImageIcon generarQR() throws IOException {
        String id = parque.gestorTiquetes.comprarTiqueteGeneral(cate);
        LocalDate fecha = LocalDate.now();
        
        String contenido = "ID: " + id + "\nFecha de Expedición: \n " + fecha + "\nCategoría: " + cate;
        // Actualiza el texto en la etiqueta ya existente
        textoEspecificaciones.setText("Especificaciones:\n" + contenido);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(contenido, BarcodeFormat.QR_CODE, 300, 300);
            String rutaAGuardar = "./data/" + "qr" + id + ".png";
            Path path = FileSystems.getDefault().getPath(rutaAGuardar);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            return new ImageIcon(rutaAGuardar);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
