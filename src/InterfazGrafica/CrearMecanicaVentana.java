package InterfazGrafica;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CrearMecanicaVentana extends JPanel implements ActionListener{
    private VentanaPrincipal parent;
    private JTextField nombre;
    private JTextField lugar;
    private JTextField cupoMax;
    private JComboBox<String> restriccionesClima;
    private JTextField numEmpleado;
    private JTextField funcionando;
    private JTextField minAltura;
    private JTextField maxAltura;
    private JTextField minPeso;
    private JTextField maxPeso;
    private JComboBox<String> restriccionSalud;
    private JTextField nivelRiesgo;
    private JButton crear;
    private JButton salir;
    public final static String CREAR = "crear";
    public final static String SALIR = "salir";
    
    
    public CrearMecanicaVentana(VentanaPrincipal parent, ArrayList<String> restriccionClimaString, ArrayList<String> restricionSaludString) {
        this.parent = parent;
        
        // Configurar el layout principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        int row = 0;
        
        // Nombre
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Nombre:"), gbc);
        
        gbc.gridx = 1;
        nombre = new JTextField(20);
        add(nombre, gbc);
        row++;
        
        // Lugar
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Lugar:"), gbc);
        
        gbc.gridx = 1;
        lugar = new JTextField(20);
        add(lugar, gbc);
        row++;
        
        // Cupo Máximo
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Cupo Máximo:"), gbc);
        
        gbc.gridx = 1;
        cupoMax = new JTextField(20);
        add(cupoMax, gbc);
        row++;
        
        // Restricciones de Clima
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Restricciones de Clima:"), gbc);
        
        gbc.gridx = 1;
        restriccionesClima = new JComboBox<>();
        for(String restriccion : restriccionClimaString) {
            restriccionesClima.addItem(restriccion);
        }
        add(restriccionesClima, gbc);
        row++;
        
        // Número de Empleados
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Número de Empleados:"), gbc);
        
        gbc.gridx = 1;
        numEmpleado = new JTextField(20);
        add(numEmpleado, gbc);
        row++;
        
        // Funcionando
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Funcionando (true/false):"), gbc);
        
        gbc.gridx = 1;
        funcionando = new JTextField(20);
        add(funcionando, gbc);
        row++;
        
        // Altura Mínima
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Altura Mínima:"), gbc);
        
        gbc.gridx = 1;
        minAltura = new JTextField(20);
        add(minAltura, gbc);
        row++;
        
        // Altura Máxima
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Altura Máxima:"), gbc);
        
        gbc.gridx = 1;
        maxAltura = new JTextField(20);
        add(maxAltura, gbc);
        row++;
        
        // Peso Mínimo
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Peso Mínimo:"), gbc);
        
        gbc.gridx = 1;
        minPeso = new JTextField(20);
        add(minPeso, gbc);
        row++;
        
        // Peso Máximo
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Peso Máximo:"), gbc);
        
        gbc.gridx = 1;
        maxPeso = new JTextField(20);
        add(maxPeso, gbc);
        row++;
        
        // Restricciones de Salud
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Restricciones de Salud:"), gbc);
        
        gbc.gridx = 1;
        restriccionSalud = new JComboBox<>();
        for(String restriccion : restricionSaludString) {
            restriccionSalud.addItem(restriccion);
        }
        add(restriccionSalud, gbc);
        row++;
        
        // Nivel de Riesgo
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Nivel de Riesgo:"), gbc);
        
        gbc.gridx = 1;
        nivelRiesgo = new JTextField(20);
        add(nivelRiesgo, gbc);
        
        crear = new JButton("Crear");
        crear.setActionCommand(CREAR);
        crear.addActionListener(this);
        add(crear);
        
        salir = new JButton("Volver");
        salir.setActionCommand(SALIR);
        salir.addActionListener(this);
        add(salir);
        
        
        // Añadir un panel vacío para empujar todo hacia arriba
        gbc.gridx = 0;
        gbc.gridy = row + 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JPanel(), gbc);
    }


	@Override
	public void actionPerformed(ActionEvent e) {
	    String comando = e.getActionCommand();
	    if(comando.equals(SALIR)) {
	        parent.quitarCrearMecanica();
	        return;
	    }
	    
	    if(comando.equals(CREAR)){
	        // Validar campos obligatorios
	        if(nombre.getText().trim().isEmpty() || lugar.getText().trim().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Nombre y lugar son campos obligatorios", 
	                "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        String restriccionClima = (String)restriccionesClima.getSelectedItem();
	        String restriccionSaludS = (String)restriccionSalud.getSelectedItem();
	        
	        try {
	            parent.crearAtraccionMecanicaParque(
	                nombre.getText().trim(),
	                lugar.getText().trim(),
	                cupoMax.getText().trim(),
	                restriccionClima,
	                numEmpleado.getText().trim(),
	                funcionando.getText().trim(),
	                minAltura.getText().trim(),  // Corregido: usar getText() en lugar de getSelectedText()
	                maxAltura.getText().trim(),
	                minPeso.getText().trim(),
	                maxPeso.getText().trim(),
	                restriccionSaludS,
	                nivelRiesgo.getText().trim()
	            );
	            
	            // Mensaje de éxito
	            JOptionPane.showMessageDialog(this, "Atracción creada exitosamente!", 
	                "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            
	            // Limpiar formulario o cerrar ventana
	            parent.quitarCrearMecanica();
	            
	        } catch (IOException e1) {
	            JOptionPane.showMessageDialog(this, "Error al guardar: " + e1.getMessage(), 
	                "Error", JOptionPane.ERROR_MESSAGE);
	            e1.printStackTrace();
	        } catch (IllegalArgumentException e2) {
	            JOptionPane.showMessageDialog(this, e2.getMessage(), 
	                "Error de validación", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception e3) {
	            JOptionPane.showMessageDialog(this, "Error inesperado: " + e3.getMessage(), 
	                "Error", JOptionPane.ERROR_MESSAGE);
	            e3.printStackTrace();
	        }
	    }
	}
   
}
