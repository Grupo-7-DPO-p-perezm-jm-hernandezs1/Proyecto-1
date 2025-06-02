package InterfazGrafica;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.border.EmptyBorder; 


public class MenuAdmin extends JPanel implements ActionListener {
    private VentanaPrincipal ventanaPrincipal;
    private JButton verAtracciones, logOut, crearAtraccion, crearResClima, crearResSalud;
    private JButton comprarTiquete;
    private static final String LOGOUT = "logOut";
    private static final String VER_ATRAC = "atracciones";
    private static final String CREAR_ATRAC = "crear_atrac";
    private static final String CREAR_RESCLIMA = "crear_resClima";
    private static final String CREAR_RESSALUD = "crear_resSalud";
    private static final String COMPRAR_TIQUETE = "comprar tiquete";
    
	
    public MenuAdmin(VentanaPrincipal parent) {
        this.ventanaPrincipal = parent;
        
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        
        
        JPanel mainButtonPanel = new JPanel();
        mainButtonPanel.setLayout(new GridLayout(0, 1, 10, 10)); // 1 columna, espacio entre botones
        
        
        verAtracciones = createMenuButton("Ver Atracciones y Espectáculos", VER_ATRAC);
        crearAtraccion = createMenuButton("Crear Atracción", CREAR_ATRAC);
        crearResClima = createMenuButton("Crear Restricción de Clima", CREAR_RESCLIMA);
        crearResSalud = createMenuButton("Crear Restricción de Salud", CREAR_RESSALUD);
        
        
        mainButtonPanel.add(verAtracciones);
        mainButtonPanel.add(crearAtraccion);
        mainButtonPanel.add(crearResClima);
        mainButtonPanel.add(crearResSalud);
        
        
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logOut = createMenuButton("Cambiar de usuario", LOGOUT);
        footerPanel.add(logOut);
        
        
        add(mainButtonPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
        
        comprarTiquete = createMenuButton("Comprar Tiquetes", COMPRAR_TIQUETE);
	    footerPanel.add(comprarTiquete);
    }
    private JButton createMenuButton(String text, String actionCommand) { 
        JButton button = new JButton(text); 
        button.setActionCommand(actionCommand); 
        button.addActionListener(this); 
        button.setPreferredSize(new Dimension(250, 40)); // Tamaño consistente 
        button.setFont(new Font("Arial", Font.PLAIN, 14)); 
        return button; 
    } 
	    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando) {
            case VER_ATRAC:
                ventanaPrincipal.agregarVentanaAtracciones();
                break;
            case CREAR_ATRAC:
                ventanaPrincipal.crearAtraccionMecanica();
                break;
            case CREAR_RESCLIMA:
                ventanaPrincipal.crearRestriccionClima();
                break;
            case CREAR_RESSALUD:
                ventanaPrincipal.crearRestriccionSalud();
                break;
            case LOGOUT:
                ventanaPrincipal.volverLogIn();
                break;
            case COMPRAR_TIQUETE:
			
			ventanaPrincipal.agregarVentanaTiquetes();
			break;
		
        }
    }
}
