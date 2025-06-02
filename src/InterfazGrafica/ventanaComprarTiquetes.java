package InterfazGrafica;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Principal.ParquePrincipal;

public class ventanaComprarTiquetes extends JFrame implements ActionListener {
	private static final String COMPRAR_TIQUETE = "comprar tiquete";
	private JComboBox<String> comboBox;
	private ParquePrincipal parque;
	private String valorCombobox;

	public ventanaComprarTiquetes(ParquePrincipal parque) {
		this.parque = parque;
		setSize(new Dimension(800, 800));
		setTitle("Comprar Tiquetes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(3, 1));
		// Panel tipo
		JPanel tipo = new JPanel();

		// Etiqueta
		JLabel label = new JLabel("Tipo de tiquete:");

		String[] tipos = { "Basico", "Individual", "General", "Temporada" };
		comboBox = new JComboBox<>(tipos);

		// Agregar al panel
		tipo.add(label);
		tipo.add(comboBox);

		// Agregar panel al frame
		add(tipo);

		// Panel Precio
		JPanel panelPrecio = new JPanel();
		panelPrecio.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel labelPrecio = new JLabel("Precio:");
		JLabel valorPrecio = new JLabel("$0");
		panelPrecio.add(labelPrecio);
		panelPrecio.add(valorPrecio);
		add(panelPrecio);

		// Lógica para actualizar precio
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccionado = (String) comboBox.getSelectedItem();
				// Conexion con la logica

				List<String> precios = parque.gestorTiquetes.getPrecios();
				String precioBasico = precios.get(0);
				String precioTempo = precios.get(1);
				String precioGeneral = precios.get(2);
				String precioIndividual = precios.get(3);
				valorCombobox = seleccionado;
				String precio = switch (seleccionado) {
				case "Basico" -> precioBasico;
				case "Individual" -> precioIndividual;
				case "General" -> precioGeneral;
				case "Temporada" -> precioTempo;
				default -> "0";
				};
				valorPrecio.setText(precio);
				
			}
		});

		// Panel boton
		JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton botonAbrir = new JButton("Ir a comprar tiquete");
		botonAbrir.addActionListener(this);
		panelBoton.add(botonAbrir);
		add(panelBoton);
		botonAbrir.setActionCommand(COMPRAR_TIQUETE);
	}

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if ( COMPRAR_TIQUETE.equals(comando)) {
			
			String seleccionado = (String) comboBox.getSelectedItem();
			switch (seleccionado) {
			case "Basico" -> {
				comprarTiqueteBasico frame = new comprarTiqueteBasico(parque);
				frame.setVisible(true);
			}
			case "Individual" -> {
				ComprarTiqueteIndividual frame = new ComprarTiqueteIndividual(parque);
				frame.setVisible(true);
			}
			case "General" -> {
				ComprarTiqueteGeneral frame = new ComprarTiqueteGeneral(parque);
				frame.setVisible(true);
			}
			case "Temporada" -> {
				ComprarTiqueteTemporada frame = new ComprarTiqueteTemporada(parque);
				frame.setVisible(true);
			}
		}
		}
		

	}
}
