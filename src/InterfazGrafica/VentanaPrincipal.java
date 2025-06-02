package InterfazGrafica;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Principal.ParquePrincipal;
import Usuarios.Cliente;
import Usuarios.Empleado;
import Usuarios.Persona;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;








@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
private LogInVentana logIn;
private ParquePrincipal parque;
private ClienteVentana clienteVentana;
private EmpleadoVentana empleadoVentana;
private AdminVentana adminVentana;
private AtracEspecVentana atracEspec;
private String tipoUsuario;
private String user;
private VerComprasVentana comprasPanel;
public VentanaPrincipal() throws IOException {
	
	
	this.tipoUsuario = "";
	this.parque = new ParquePrincipal();
	this.empleadoVentana = new EmpleadoVentana(this);
	this.clienteVentana= new ClienteVentana(this);
	this.adminVentana= new AdminVentana(this);
	
	setTitle("Bienvenido" );
	setLayout(new BorderLayout ());
	logIn = new LogInVentana(this);
	add(logIn);
	setDefaultCloseOperation( EXIT_ON_CLOSE );
    setSize( 800, 800 );
    setLocationRelativeTo( null );
    setVisible( true );
}
public static void main (String[] args) throws IOException {
	new VentanaPrincipal();
}
public void revistarUsuario(String username, String password) {
    // Asegúrate de que parque no es null
    if (parque != null) {
        String respuesta = parque.revisarUsuario(username, password);
        
        System.out.println(respuesta);
        
        if(!respuesta.equals("ninguno")) {
        	this.logIn.setVisible(false);
        	if(respuesta.equals("admin")) {
        		add(adminVentana);
        	}
        	if(respuesta.equals("cliente")) {
        		add(clienteVentana);
        		user = username;
        	}
        	if(respuesta.equals("empleado")) {
        		add(empleadoVentana);
        		user = username;
        	}
        	
        	tipoUsuario= respuesta;
        	
        }
        // Opcional: muestra un diálogo al usuario
        // JOptionPane.showMessageDialog(this, respuesta);
    } else {
        System.out.println("Error: parque no inicializado");
    }
    
    	
    }
public void agregarVentanaAtracciones() {
    // 1. Obtener las listas de nombres
    ArrayList<String> atracciones = parque.atraccionesNombre();
    ArrayList<String> espectaculos = parque.espectaculosNombre();
    
    // 2. Crear el panel (no ventana)
    atracEspec = new AtracEspecVentana(this, atracciones, espectaculos);
    
    // 3. Limpiar y mostrar el panel
    getContentPane().removeAll(); // Limpiar contenido anterior
    add(atracEspec); // Agregar el panel
    
    // 4. Actualizar la visualización
    revalidate();
    repaint();
}
public void quitarVentanaAtracciones() {
    if (atracEspec != null) {
        this.remove(atracEspec);
        atracEspec = null; 

        
        mostrarMenuPrincipal();
        // Actualiza la interfaz
        revalidate(); // Recalcula el layout
        repaint();   // Redibuja los componentes
    }
}
public void agregarVentanaTiquetes() {
	
	ventanaComprarTiquetes cpt = new ventanaComprarTiquetes(parque);
	cpt.setVisible(true);
}

public void quitarVentanaVerCompras() {
    // 1. Verificar y remover el panel de compras si existe
    if (comprasPanel != null) {
        this.remove(comprasPanel);
        comprasPanel = null;
    }
    
    // 2. Limpiar cualquier otro componente que pueda estar presente
    getContentPane().removeAll();
    
    // 3. Volver a mostrar el menú principal
    mostrarMenuPrincipal();
    
    // 4. Actualizar la interfaz
    revalidate();
    repaint();
    
    // Opcional: Forzar el enfoque en la ventana principal
    requestFocusInWindow();
}
private void mostrarMenuPrincipal() {
	String respuesta = tipoUsuario;
	 if(!respuesta.equals("ninguno")) {
     	this.logIn.setVisible(false);
     	if(respuesta.equals("admin")) {
     		add(adminVentana);
     	}
     	if(respuesta.equals("cliente")) {
     		add(clienteVentana);
     	}
     	if(respuesta.equals("empleado")) {
     		add(empleadoVentana);
     	}
	
}

}
public void mostrarCompras() {
    // 1. Obtener datos
    Persona persona = parque.gestorUsuarios.getPersona(user);
    if (persona == null || !(persona instanceof Cliente)) return;
    
    
    // 2. Limpiar contenido actual
    getContentPane().removeAll();
    
    // 3. Obtener compras (puede ser en un hilo separado si es lento)
    ArrayList<ArrayList<String>> compras = parque.verComprasCliente((Cliente)persona);
    
    // 4. Crear y mostrar panel
    VerComprasVentana comprasPanel = new VerComprasVentana(this, compras);
    getContentPane().add(new JScrollPane(comprasPanel), BorderLayout.CENTER);
    
    // 5. Actualizar UI
    revalidate();
    repaint();
}
public void volverLogIn() {
    // 1. Limpiar todos los componentes
    getContentPane().removeAll();
    
    // 2. Resetear variables de sesión
    this.logIn = null;
    this.user = null;
    this.tipoUsuario = null;
    
    // 3. Liberar recursos de los paneles
    this.clienteVentana = null;
    this.atracEspec = null;
    this.comprasPanel = null;
    this.empleadoVentana = null;
    
    // 4. Crear nueva instancia de login (o reusar la existente)
    if (this.logIn == null) {
        this.logIn = new LogInVentana(this);
    } else {
        this.logIn.limpiarCampos(); // Añade este método a LogInVentana
    }

    add(logIn);
    
    revalidate();
    repaint();

}
}

	
	


