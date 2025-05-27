package InterfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AtracEspecVentana extends JPanel implements ActionListener {
	private JButton salir;
	public final static String SALIR = "salir";
	private VentanaPrincipal parent;
    public AtracEspecVentana(VentanaPrincipal parent, 
                            ArrayList<String> atrac, 
                            ArrayList<String> espec) {
        setLayout(new BorderLayout(10, 10));
        this.parent = parent;
        
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Panel principal
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        
        // Panel atracciones
        JPanel panelAtracciones = crearListado(atrac, "Atracciones");
        mainPanel.add(panelAtracciones);
        
        // Panel espectáculos
        JPanel panelEspectaculos = crearListado(espec, "Espectáculos");
        mainPanel.add(panelEspectaculos);
        
        add(mainPanel, BorderLayout.CENTER);
        
        salir = new JButton("Volver");
        salir.setActionCommand(SALIR);
        salir.addActionListener(this);
        add(salir, BorderLayout.SOUTH);
    }
    
    private JPanel crearListado(ArrayList<String> items, String titulo) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        
        if(items == null || items.isEmpty()) {
            panel.add(new JLabel("No hay " + titulo.toLowerCase()));
            return panel;
        }
        
        for(String item : items) {
            JLabel label = new JLabel(item);
            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            panel.add(label);
            panel.add(Box.createVerticalStrut(5));
        }
        
        return panel;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals(SALIR)) {
			parent.quitarVentanaAtracciones();
		}
		
	}

	

}