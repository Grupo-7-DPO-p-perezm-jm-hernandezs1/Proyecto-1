package InterfazGrafica;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalLabelUI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;

import Principal.ParquePrincipal;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class mostrarTiquete extends JFrame {

	private static final long serialVersionUID = 1L;
    private ParquePrincipal parque;
    private JLabel lblNewLabel; // ESTE es el que se usará visualmente

    public mostrarTiquete(ParquePrincipal parque) {
        this.parque = parque;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        getContentPane().setLayout(new GridLayout(2, 1, 0, 0));

        JPanel panel_1 = new JPanel(new GridLayout(1, 2, 0, 0));
        getContentPane().add(panel_1);

        JPanel panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblNewLabel = new JLabel("Especificaciones:"); // Usa el atributo de clase
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_2.add(lblNewLabel);
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
        String id = parque.gestorTiquetes.comprarTiqueteBasico();
        LocalDate fecha = LocalDate.now();
        String contenido = "ID: " + id + " - Fecha de Expedicion: " + fecha;
        
        // Actualiza el texto en la etiqueta ya existente
        lblNewLabel.setText("<html>Especificaciones:<br/>" + contenido.replaceAll("\n", "<br/>") + "</html>");

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
