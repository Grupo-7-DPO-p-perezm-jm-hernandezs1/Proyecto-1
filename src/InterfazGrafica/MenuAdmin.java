package InterfazGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuAdmin extends JPanel implements ActionListener{
	private VentanaPrincipal ventanaPrincipal;
	private JButton verAtracciones;
	private JButton logOut;
	private JButton crearAtraccion;


	private static final String LOGOUT= "logOut";
	private static final String VER_ATRAC = "atracciones";
	private static final String CREAR_ATRAC = "crear_atrac";
	
	public MenuAdmin(VentanaPrincipal parent) {
		this.ventanaPrincipal = parent;
		
		logOut = new JButton("Cambiar de usuario");
	    logOut.setActionCommand(LOGOUT);
	    logOut.addActionListener(this);
	    add(logOut);
	    
	    verAtracciones = new JButton("Ver Atracciones y Espectaculos");
	    verAtracciones.setActionCommand(VER_ATRAC);
	    verAtracciones.addActionListener(this);
	    add(verAtracciones);
	    
	    crearAtraccion = new JButton("Crear Atraccion");
	    crearAtraccion.setActionCommand(CREAR_ATRAC);
	    crearAtraccion.addActionListener(this);
	    add(crearAtraccion);
	    
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(VER_ATRAC)) {
			ventanaPrincipal.agregarVentanaAtracciones();
			
		}
		//if(comando.equals(CREAR_ATRAC)) {
			//ventanaPrincipal.mostrarCompras();
		//}
		if(comando.equals(LOGOUT)) {
			ventanaPrincipal.volverLogIn();
			
		}
		
	}
}
