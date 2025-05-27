package InterfazGrafica;

import javax.swing.JPanel;

public class AdminVentana extends JPanel{
private MenuAdmin menu;
private VentanaPrincipal ventanaPrincipal;
	public AdminVentana(VentanaPrincipal parent) {
		this.ventanaPrincipal = parent;
		//this.menu = new MenuAdmin(parent);
		menu = new MenuAdmin(parent);
		add(menu);
	}
	
}
