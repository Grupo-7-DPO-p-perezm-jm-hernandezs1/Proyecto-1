package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Principal.ParquePrincipal;

public class LogInVentana extends JPanel {
  
    private JTextField usernameField;
    private JPasswordField passwordField;
    private VentanaPrincipal ventanaPrincipal;
    
 // Mejor usar JPasswordField para contraseñas

    public LogInVentana(VentanaPrincipal ventanaPrincipal) {
    	this.ventanaPrincipal =  ventanaPrincipal;   
    	setLayout(new BorderLayout());
        setName("Inicio de Sesión");
        
        // Panel principal con márgenes
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel para los campos de entrada
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 10, 10));
        
        // Campo Usuario
        JLabel usuarioLabel = new JLabel("Usuario:");
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 30));
        
        // Campo Contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));
        
        // Botón de login
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(e -> {
            // Aquí iría la lógica para validar el login
            char[] pass = passwordField.getPassword();
            System.out.println("Usuario: " + usernameField.getText());
            System.out.println("Contraseña: " + new String(pass));
            realizarLogin();
        });
        
        // Añadir componentes al formulario
        formPanel.add(usuarioLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(loginButton);
        
        // Centrar el formulario
        JPanel centerPanel = new JPanel();
        centerPanel.add(formPanel);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);
        
        
        setSize(600, 400); 
        
        setVisible(true);
    }
    private void realizarLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        // Llama al método de la ventana principal
        ventanaPrincipal.revistarUsuario(username, password);
    }
    public void limpiarCampos() {
        // Limpiar los campos de texto
    	usernameField.setText("");
    	passwordField.setText("");
        
        // Restablecer el foco al campo de usuario
    	usernameField.requestFocusInWindow();
        
        // Opcional: Limpiar mensajes de error si existen
       
    }
    
}