package InterfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Principal.ParquePrincipal;
import tiquetes.Categoria;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ComprarTiqueteTemporada extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ParquePrincipal parque;
	private JComboBox<Categoria> comboBox_1;
	private JComboBox<String> comboBox;


	/**
	 * Create the frame.
	 */
	public ComprarTiqueteTemporada(ParquePrincipal parque) {
		this.parque = parque;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Comprar Tiquete Temporada");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccionar Temporada");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_5.add(lblNewLabel_1);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		//Invierno, otoño, verano, primavera
		comboBox = new JComboBox<>(new String[] { "otoño", "verano", "primavera", "invierno" });
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_6.add(comboBox);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		
		JLabel lblNewLabel_2 = new JLabel("Seleccionar Categoria");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_7.add(lblNewLabel_2);
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		
		comboBox_1 = new JComboBox<>(Categoria.values());
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(comboBox_1);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Comprar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("QR");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		 if ("QR".equals(e.getActionCommand())) {
		        Categoria cateSeleccionada = (Categoria) comboBox_1.getSelectedItem();
		        String temporada = (String) comboBox.getSelectedItem();
		        MostrarTiqueteTemporada most = new MostrarTiqueteTemporada(parque, cateSeleccionada, temporada);
		        most.setVisible(true);
		    }
		
	}

}
