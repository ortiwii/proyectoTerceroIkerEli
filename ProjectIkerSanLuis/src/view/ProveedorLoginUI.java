package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.GestorDatos;
import model.Proveedor;
import model.Usuario;
import java.awt.Font;

public class ProveedorLoginUI extends JFrame {
	private JLabel lblSaludo;
	private JButton btnVolver;

	public ProveedorLoginUI () {
		
			
						
		initialize();
	}
	private void initialize() {
		
		getContentPane().setLayout(null);
		setBounds(100, 100, 394, 364);
		
		JLabel lblUserName = new JLabel("Nombre de usuario del proveedor");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(67, 89, 227, 14);
		getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(139, 170, 79, 14);
		getContentPane().add(lblPassword);
		
		JTextField txtUserName = new JTextField();
		txtUserName.setBounds(105, 127, 142, 20);
		getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setBounds(105, 197, 142, 20);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnAcceder = new JButton("Acceder");
		btnAcceder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GestorDatos gestor = GestorDatos.getInstance();
				Proveedor proveedor = gestor.getProveedorUser(txtUserName.getText());
				if (proveedor != null && proveedor.getUser().equals(txtUserName.getText()) && proveedor.getPassw().equals(txtPassword.getText())) {				
					// El usuario y la contraseña son correctas
					// TODO pantalla proveedor					
					dispose();
					ProveedorUI proveedorUI = new ProveedorUI(proveedor);
					proveedorUI.setVisible(true);
					
					
								
				}else {
					// Algo esta mal
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
				}
					
				
			}});
		
			btnAcceder.setBounds(129, 246, 89, 23);
		getContentPane().add(btnAcceder);
		
		lblSaludo = new JLabel("INICIO DE SESION COMO PROVEEDOR");
		lblSaludo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSaludo.setBounds(42, 35, 294, 16);
		getContentPane().add(lblSaludo);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				LoginUI loginUI = new LoginUI ();
				loginUI.setVisible(true);
				dispose();
			}});
		
		btnVolver.setBounds(129, 280, 89, 24);
		getContentPane().add(btnVolver);
	}
}
