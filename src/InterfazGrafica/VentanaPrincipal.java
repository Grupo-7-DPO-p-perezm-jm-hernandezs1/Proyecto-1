package InterfazGrafica;

import javax.swing.JFrame;

import Principal.ParquePrincipal;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;








@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
private LogInVentana logIn;
private ParquePrincipal parque;
private ClienteVentana clienteVentana;
private AtracEspecVentana atracEspec;

public VentanaPrincipal() throws IOException {
	
	
	
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
        	}
        	if(respuesta.equals("empleado")) {
        		
        	}
        	
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
    

}

	
	

