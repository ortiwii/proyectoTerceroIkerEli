package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.GestorDatos;
import model.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class LoginUI extends JFrame {
	private JButton btnProveedor;
	private JLabel lblSaludo;
	
	public LoginUI() {
		
		initialize();
	}
	private void initialize() {
		
		getContentPane().setLayout(null);
		setBounds(100, 100, 394, 364);
		
		JLabel lblUserName = new JLabel("Nombre de usuario");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(105, 89, 142, 14);
		getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(139, 149, 79, 14);
		getContentPane().add(lblPassword);
		
		JTextField txtUserName = new JTextField();
		txtUserName.setBounds(105, 116, 142, 20);
		getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setBounds(105, 176, 142, 20);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnAcceder = new JButton("Acceder");
		btnAcceder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GestorDatos gestor = GestorDatos.getInstance();
				Usuario user = gestor.getUsuarioCompleto(txtUserName.getText());
				if (user != null && user.getUser().equals(txtUserName.getText()) && user.getPassw().equals(txtPassword.getText())) {				
					// El usuario y la contraseña son correctas
					if (user.getTipo().equals("u")) { 
						// El usuario es de tipo usuario
						
						UsuarioUI usuarioUI = new UsuarioUI(user);
						usuarioUI.setVisible(true);
						dispose();
						
					}else if (user.getTipo().equals("t")) {
						// TODO El usuario es de tipo tecnico
						
						TecnicoUI tecnicoUI = new TecnicoUI(GestorDatos.getInstance().getTecnicoCompleto(user.getUser()));
						tecnicoUI.setVisible(true);
						dispose();
					}else if (user.getTipo().equals("a")) {
						// TODO El usuario es de tipo administrador
					}
								
				}else {
					// Algo esta mal
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
				}
					
				
			}});
		
		//					
		//				Usuario usuario = AccesoBD.obtenerDatosUsuario(txtUser.getText());
		//				
		//				if (usuario.getUser().equals(txtUser.getText())) {
		//					
		//					if(usuario.getPass().equals(txtPass.getText())){
		//						
		//						VInicio vInicio = new VInicio(usuario);
		//						vInicio.setVisible(true);
		//						dispose();
		//						
		//						
		//					}else {
		//						
		//						JOptionPane.showMessageDialog(null, "Contraseña incorrecta. Prueba de nuevo.");
		//						
		//					}
		//					
		//					
		//				}else {
		//					
		//					JOptionPane.showMessageDialog(null, "No existe ese nombre de usuario. Prueba de nuevo.");
		//					
		//				}											
		//					
		//			}
		//		});
				btnAcceder.setBounds(139, 261, 89, 23);
		getContentPane().add(btnAcceder);
		
		btnProveedor = new JButton("Acceder como proveedor");
		btnProveedor.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ProveedorLoginUI proveedorLoginUI = new ProveedorLoginUI();
				proveedorLoginUI.setVisible(true);
				dispose();
			}});
		
		btnProveedor.setBounds(79, 223, 201, 25);
		getContentPane().add(btnProveedor);
		
		lblSaludo = new JLabel("INICIO DE SESION");
		lblSaludo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSaludo.setBounds(104, 31, 156, 28);
		getContentPane().add(lblSaludo);
	}
}
