package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class VerComprasVentana extends JPanel {

    private VentanaPrincipal parent;
    
    public VerComprasVentana(VentanaPrincipal ventanaPrincipal, ArrayList<ArrayList<String>> historial) {
        this.parent = ventanaPrincipal;
        initComponents(historial);
    }
    
    private void initComponents(ArrayList<ArrayList<String>> historial) {
        // Configuración principal del panel
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel principal con scroll
        JScrollPane scrollPane = createContentPanel(historial);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private JScrollPane createContentPanel(ArrayList<ArrayList<String>> historial) {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        
        if (historial == null || historial.isEmpty()) {
            contentPanel.add(createEmptyStatePanel());
        } else {
            contentPanel.add(createComprasList(historial));
        }
        
        // Configuración del scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        
        return scrollPane;
    }
    
    private JPanel createEmptyStatePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel label = new JLabel("No hay compras registradas", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.GRAY);
        
        panel.add(label, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        
        return panel;
    }
    
    private JPanel createComprasList(ArrayList<ArrayList<String>> historial) {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        
        int i = 1;
        for (ArrayList<String> compraData : historial) {
            listPanel.add(createCompraPanel(compraData, i++));
            listPanel.add(Box.createVerticalStrut(15));
        }
        
        return listPanel;
    }
    
    private JPanel createCompraPanel(ArrayList<String> compraData, int numeroCompra) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)), 
                "Compra #" + numeroCompra,
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                Color.DARK_GRAY
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        
        if (compraData == null || compraData.isEmpty()) {
            panel.add(createInfoLabel("Información no disponible"));
            return panel;
        }
        
        try {
            // Número de tiquete
            panel.add(createInfoLabel("Tiquete: " + compraData.get(0)));
            panel.add(Box.createVerticalStrut(5));
            
            // Estado de vencimiento
            String estado = "false".equalsIgnoreCase(compraData.get(1)) ? "No vencido" : "Vencido";
            Color colorEstado = "No vencido".equals(estado) ? new Color(0, 128, 0) : Color.RED;
            panel.add(createStatusLabel("Estado: " + estado, colorEstado));
            
            // Puedes agregar más campos aquí si los tienes
            if (compraData.size() > 2) {
                panel.add(Box.createVerticalStrut(5));
                panel.add(createInfoLabel("Fecha: " + compraData.get(2)));
            }
            
        } catch (IndexOutOfBoundsException e) {
            panel.add(createInfoLabel("Información incompleta"));
        }
        
        return panel;
    }
    
    private JLabel createInfoLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setForeground(Color.DARK_GRAY);
        label.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        return label;
    }
    
    private JLabel createStatusLabel(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(color);
        label.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        return label;
    }
}