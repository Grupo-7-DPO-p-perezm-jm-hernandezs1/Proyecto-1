package InterfazGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuGenericoVentana extends JPanel implements ActionListener{
private VentanaPrincipal ventanaPrincipal;
private JButton verAtracciones;
private JButton verCompras;

private static final String VER_ATRAC = "atracciones";
private static final String VER_COMPRAS = "compras";

public MenuGenericoVentana(VentanaPrincipal ventanaPrincipal) {
	this.ventanaPrincipal = ventanaPrincipal;
	
	verAtracciones = new JButton("Ver Atracciones y Espectaculos");
	verAtracciones.setActionCommand(VER_ATRAC);
	verAtracciones.addActionListener(this);
    add(verAtracciones);
	
	
	verCompras =  new JButton("Ver Compras");
	verCompras.setActionCommand(VER_COMPRAS);
	verCompras.addActionListener(this);
    add(verCompras);
	
	
}


@Override
public void actionPerformed(ActionEvent e) {

	String comando = e.getActionCommand();
	if (comando.equals(VER_ATRAC)) {
		ventanaPrincipal.agregarVentanaAtracciones();
		
	}
	
	
	
}


}
