package InterfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Principal.ParquePrincipal;
import atracciones_y_espectaculos.Atraccion;
import atracciones_y_espectaculos.Espectaculo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JComboBox;

public class ComprarTiqueteIndividual extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private ParquePrincipal parque;
	private Atraccion atra;
	
	private static final String hola = "tiquete";
	/**
	 * Create the frame.
	 */
	public ComprarTiqueteIndividual(ParquePrincipal parque) {
		this.parque = parque;
		
		setTitle("Comprar tiquete Individual");
		setBounds(100, 100, 900, 650);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Comprar Tiquete Individual");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_2.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		
		JLabel lblNewLabel = new JLabel("Seleccionar atraccion:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		//Contenido del combobox
		ArrayList<String> atracciones = parque.atraccionesNombre();
		
		
		
		String[] opciones = atracciones.toArray(new String[0]);
		comboBox = new JComboBox<>(opciones);
		String seleccionada = (String) comboBox.getSelectedItem();
		
		atra = parque.gestorAtracciones.buscarAtraccionPorNombre(seleccionada);
		
		panel_5.add(comboBox);
		
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("Comprar ");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("QR");
		

	}
	public void actionPerformed(ActionEvent e) {
		
		if ("QR".equals(e.getActionCommand())) {
			mostrarTiqueteIndividual most = new mostrarTiqueteIndividual(parque, atra);
			most.setVisible(true);
		}
	}
}
