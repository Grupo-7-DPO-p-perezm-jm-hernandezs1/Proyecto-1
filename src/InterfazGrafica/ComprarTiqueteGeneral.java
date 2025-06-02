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
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class ComprarTiqueteGeneral extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ParquePrincipal parque;
	private Categoria cate;
	private JComboBox<Categoria> comboBox;

	

	/**
	 * Create the frame.
	 */
	public ComprarTiqueteGeneral(ParquePrincipal parque) {
		setTitle("Comprar Tiquete General");
		this.parque = parque;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Comprar Tiquete General");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione la categoria del Tiquete");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_1);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		comboBox = new JComboBox<>(Categoria.values());
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		panel_4.add(comboBox);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Comprar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("QR");
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		 if ("QR".equals(e.getActionCommand())) {
		        Categoria cateSeleccionada = (Categoria) comboBox.getSelectedItem();
		        MostrarTiqueteGenerar most = new MostrarTiqueteGenerar(parque, cateSeleccionada);
		        most.setVisible(true);
		    }
		
	}

}
