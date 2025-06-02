package InterfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Principal.ParquePrincipal;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JSeparator;

public class comprarTiqueteBasico extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ParquePrincipal parque;

	/**
	 * Create the frame.
	 */
	public comprarTiqueteBasico(ParquePrincipal parque) {
		this.parque = parque;
		setTitle("Comprar Tiquete Basico");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 10, 10));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Comprar Tiquete Basico");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(Box.createVerticalStrut(10));
		panel_1.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(100);
		panel.add(Box.createVerticalStrut(10));
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Comprar Tiquete");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.addActionListener(this);
		panel.add(btnNewButton);
		btnNewButton.setActionCommand("QR");
	}
	public void actionPerformed(ActionEvent e) {
		
		if ("QR".equals(e.getActionCommand())) {
			mostrarTiquete most = new mostrarTiquete(parque);
			most.setVisible(true);
		}
		
	}
}
