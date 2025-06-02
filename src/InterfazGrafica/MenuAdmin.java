package InterfazGrafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuAdmin extends JPanel implements ActionListener{
	private VentanaPrincipal ventanaPrincipal;
	private JButton verAtracciones;
	private JButton logOut;
	private JButton crearAtraccion;
	private JButton comprarTiquete;

	private static final String LOGOUT= "logOut";
	private static final String VER_ATRAC = "atracciones";
	private static final String CREAR_ATRAC = "crear_atrac";
	private static final String COMPRAR_TIQUETE = "comprar tiquete";
	
	public MenuAdmin(VentanaPrincipal parent) {
		
		setLayout(new GridLayout(8, 1));
		this.ventanaPrincipal = parent;
		
		logOut = new JButton("Cambiar de usuario");
	    logOut.setActionCommand(LOGOUT);
	    logOut.addActionListener(this);
	    add(logOut);
	    
	    add(Box.createVerticalStrut(10));
	    verAtracciones = new JButton("Ver Atracciones y Espectaculos");
	    verAtracciones.setActionCommand(VER_ATRAC);
	    verAtracciones.addActionListener(this);
	    add(verAtracciones);
	    add(Box.createVerticalStrut(10));
	    crearAtraccion = new JButton("Crear Atraccion");
	    crearAtraccion.setActionCommand(CREAR_ATRAC);
	    crearAtraccion.addActionListener(this);
	    add(crearAtraccion);
	    add(Box.createVerticalStrut(10));
	    comprarTiquete = new JButton("Comprar Tiquetes");
	    comprarTiquete.setActionCommand(COMPRAR_TIQUETE);
	    comprarTiquete.addActionListener(this);
	    add(comprarTiquete);
	    add(Box.createVerticalStrut(10));
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
		if(comando.equals(COMPRAR_TIQUETE)) {
			
			ventanaPrincipal.agregarVentanaTiquetes();
			
		}
	}
}
