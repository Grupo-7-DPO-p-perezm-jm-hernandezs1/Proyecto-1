package InterfazGrafica;

import javax.swing.JPanel;

public class EmpleadoVentana extends JPanel {

	private static final long serialVersionUID = 1L;
	private MenuGenericoVentana menu;
	private VentanaPrincipal ventanaPrincipal;
	/**
	 * Create the panel.
	 */
	public EmpleadoVentana(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		
		
		menu = new MenuGenericoVentana(ventanaPrincipal);
		add(menu);
	}
	
		
}
