package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.Box;

@SuppressWarnings("serial")
public class CrearRestriccionClimaVentana extends JPanel implements ActionListener {
    private Map<String, JCheckBox> checkboxesAtracciones = new HashMap<>();
    private Map<String, JCheckBox> checkboxesEspectaculos = new HashMap<>();
    private VentanaPrincipal parent;
    private JButton crear, salir;
    private JTextField nombre;
    public final static String CREAR_RES = "crearRestriccion";
    public final static String SALIR = "salir";

    public CrearRestriccionClimaVentana(VentanaPrincipal parent, ArrayList<String> atracciones, ArrayList<String> espectaculos) {
        this.parent = parent;
        
        // Configuración principal del panel
        setLayout(new BorderLayout());
        
        // Panel de contenido con scroll
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // Panel para el nombre
        JPanel nombrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nombre = new JTextField(20);
        nombrePanel.add(new JLabel("Nombre:"));
        nombrePanel.add(nombre);
        add(nombrePanel, BorderLayout.NORTH);
        
        // Sección de Atracciones
        if (!atracciones.isEmpty()) {
            contentPanel.add(createSectionLabel("Atracciones"));
            for (String nombre : atracciones) {
                JCheckBox cb = createCheckbox(nombre);
                checkboxesAtracciones.put(nombre, cb);
                contentPanel.add(createItemPanel(cb));
            }
        }
        
        // Sección de Espectáculos
        if (!espectaculos.isEmpty()) {
            contentPanel.add(Box.createVerticalStrut(15));
            contentPanel.add(createSectionLabel("Espectáculos"));
            for (String nombre : espectaculos) {
                JCheckBox cb = createCheckbox(nombre);
                checkboxesEspectaculos.put(nombre, cb);
                contentPanel.add(createItemPanel(cb));
            }
        }
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        crear = new JButton("Crear");
        crear.setActionCommand(CREAR_RES);
        crear.addActionListener(this);
        
        salir = new JButton("Volver");
        salir.setActionCommand(SALIR);
        salir.addActionListener(this);
        
        buttonPanel.add(crear);
        buttonPanel.add(salir);
        
        // Agregar componentes al panel principal
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createSectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        label.setFont(label.getFont().deriveFont(Font.BOLD, 12f));
        return label;
    }

    private JCheckBox createCheckbox(String text) {
        JCheckBox cb = new JCheckBox(text);
        cb.setFont(new Font("Arial", Font.PLAIN, 12));
        return cb;
    }

    private JPanel createItemPanel(JCheckBox checkbox) {
        JPanel panel = new JPanel((LayoutManager) new FlowLayout());
        panel.add(checkbox);
        return panel;
    }
    
    /**
     * Obtiene las atracciones seleccionadas
     * @return ArrayList con nombres de atracciones seleccionadas
     */
    public ArrayList<String> getAtraccionesSeleccionadas() {
        return getSeleccionados(checkboxesAtracciones);
    }
    
    /**
     * Obtiene los espectáculos seleccionados
     * @return ArrayList con nombres de espectáculos seleccionados
     */
    public ArrayList<String> getEspectaculosSeleccionados() {
        return getSeleccionados(checkboxesEspectaculos);
    }
    
    /**
     * Obtiene todos los elementos seleccionados (atracciones + espectáculos)
     * @return ArrayList combinado
     */
    public ArrayList<String> getTodosSeleccionados() {
        ArrayList<String> todos = new ArrayList<>();
        todos.addAll(getAtraccionesSeleccionadas());
        todos.addAll(getEspectaculosSeleccionados());
        return todos;
    }
    
    private ArrayList<String> getSeleccionados(Map<String, JCheckBox> mapaCheckboxes) {
        ArrayList<String> seleccionados = new ArrayList<>();
        for (Map.Entry<String, JCheckBox> entry : mapaCheckboxes.entrySet()) {
            if (entry.getValue().isSelected()) {
                seleccionados.add(entry.getKey());
            }
        }
        return seleccionados;
    }
    
    /**
     * Selecciona o deselecciona todas las atracciones
     * @param seleccionar true para seleccionar todas, false para deseleccionar
     */
    public void seleccionarTodasAtracciones(boolean seleccionar) {
        setSeleccionTodos(checkboxesAtracciones, seleccionar);
    }
    
    /**
     * Selecciona o deselecciona todos los espectáculos
     * @param seleccionar true para seleccionar todos, false para deseleccionar
     */
    public void seleccionarTodosEspectaculos(boolean seleccionar) {
        setSeleccionTodos(checkboxesEspectaculos, seleccionar);
    }
    
    private void setSeleccionTodos(Map<String, JCheckBox> mapaCheckboxes, boolean seleccionar) {
        for (JCheckBox cb : mapaCheckboxes.values()) {
            cb.setSelected(seleccionar);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if(comando.equals(SALIR)) {
            parent.quitarCrearRestriccionClima();
            return;
        }
        if(comando.equals(CREAR_RES)) {
            ArrayList<String> atracciones = getAtraccionesSeleccionadas();
            ArrayList<String> espectaculos = getEspectaculosSeleccionados();
            String nombreRestriccion = nombre.getText().trim();
            
            if(nombreRestriccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un nombre para la restricción", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            parent.crearRestriccionClimaParque(nombreRestriccion, atracciones, espectaculos);
        }
    }
}

