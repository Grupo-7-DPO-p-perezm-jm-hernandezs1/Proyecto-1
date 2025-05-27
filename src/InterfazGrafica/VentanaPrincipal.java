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
private AtracEspecVentana atracEspec;
private String tipoUsuario;
private String user;
private VerComprasVentana verCompras;
public VentanaPrincipal() throws IOException {
	
	
	this.tipoUsuario = "";
	this.parque = new ParquePrincipal();
	this.clienteVentana= new ClienteVentana(this);
	
	
	
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
        		
        	}
        	if(respuesta.equals("cliente")) {
        		add(clienteVentana);
        		user = username;
        	}
        	if(respuesta.equals("empleado")) {
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
        this.remove(atracEspec); // Remueve el panel de atracciones
        atracEspec = null; // Libera memoria

        // Vuelve a mostrar el menú principal (o el contenido que había antes)
        // Método que debes implementar
        mostrarMenuPrincipal();
        // Actualiza la interfaz
        revalidate(); // Recalcula el layout
        repaint();   // Redibuja los componentes
    }
}
private void mostrarMenuPrincipal() {
	String respuesta = tipoUsuario;
	 if(!respuesta.equals("ninguno")) {
     	this.logIn.setVisible(false);
     	if(respuesta.equals("admin")) {
     		
     	}
     	if(respuesta.equals("cliente")) {
     		add(clienteVentana);
     	}
     	if(respuesta.equals("empleado")) {
     		
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
}

	
	


