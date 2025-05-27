package InterfazGrafica;

import javax.swing.JPanel;


public class ClienteVentana extends JPanel {
private MenuGenericoVentana menu;
private VentanaPrincipal ventanaPrincipal;

public ClienteVentana(VentanaPrincipal ventanaPrincipal) {
	this.ventanaPrincipal = ventanaPrincipal;
	
	
	menu = new MenuGenericoVentana(ventanaPrincipal);
	add(menu);
	
}
	

}
