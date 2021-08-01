package view;

import javax.swing.JFrame;

import model.Administrador;
import model.Usuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;

import db.GestorDatos;

import javax.swing.JButton;

public class AdministradorCambiarContraseñaUsuarioUI extends JFrame {
	
	private Administrador administrador;
	private Usuario usuario;
	private JLabel lblSaludo;
	private JLabel lblUsuario;
	private JLabel lblAntiguaContrasea;
	private JLabel lblNuevaContrasea;
	private JPasswordField pwdAntiguacontrasea;
	private JPasswordField pwdNuevacontrasea;
	private JButton btnVerantigua;
	private JButton btnVerNueva;
	private JButton btnCambiar;
	private JButton btnVolver;
	private char visible;
	private char dot;
	
	public AdministradorCambiarContraseñaUsuarioUI (Administrador administrador, Usuario usuario) {
		this.administrador = administrador;
		this.usuario = usuario;
		
		
		initialize();
	}
	private void initialize() {
		
		setBounds(100, 100, 604, 332);
		getContentPane().setLayout(null);
		
		lblSaludo = new JLabel("CAMBIAR CONTRASE\u00D1A USUARIO");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(29, 25, 525, 16);
		getContentPane().add(lblSaludo);
		
		lblUsuario = new JLabel("Usuario : "+usuario.getUser());
		lblUsuario.setBounds(29, 73, 530, 16);
		getContentPane().add(lblUsuario);
		
		lblAntiguaContrasea = new JLabel("Antigua Contrase\u00F1a :");
		lblAntiguaContrasea.setBounds(29, 127, 140, 16);
		getContentPane().add(lblAntiguaContrasea);
		
		lblNuevaContrasea = new JLabel("Nueva Contrase\u00F1a :");
		lblNuevaContrasea.setBounds(29, 189, 140, 16);
		getContentPane().add(lblNuevaContrasea);
		
		pwdAntiguacontrasea = new JPasswordField();
		pwdAntiguacontrasea.setBounds(181, 124, 275, 22);
		getContentPane().add(pwdAntiguacontrasea);
		
		pwdNuevacontrasea = new JPasswordField();
		pwdNuevacontrasea.setBounds(181, 186, 275, 22);
		getContentPane().add(pwdNuevacontrasea);
		
		btnVerantigua = new JButton("ver");
		btnVerantigua.setBounds(468, 123, 83, 25);
		getContentPane().add(btnVerantigua);
		
		btnVerNueva = new JButton("ver");
		btnVerNueva.setBounds(468, 185, 83, 25);
		getContentPane().add(btnVerNueva);
		
		btnCambiar = new JButton("Cambiar");
		btnCambiar.setBounds(128, 235, 132, 25);
		getContentPane().add(btnCambiar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(287, 235, 132, 25);
		getContentPane().add(btnVolver);		
		
		dot = pwdAntiguacontrasea.getEchoChar();
		visible = (char)0;
		pwdAntiguacontrasea.setText(usuario.getPassw());
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorPanelUsuariosUI administradorPanelUsuariosUI = new AdministradorPanelUsuariosUI(administrador);
				administradorPanelUsuariosUI.setVisible(true);				
			}});
		
		btnVerantigua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnVerantigua.getText().equals("ver")) {
					btnVerantigua.setText("ocultar");
					pwdAntiguacontrasea.setEchoChar(visible);
				}else { // ocultar
					btnVerantigua.setText("ver");
					pwdAntiguacontrasea.setEchoChar(dot);
				}
				
			}});
		
		btnVerNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnVerNueva.getText().equals("ver")) {
					btnVerNueva.setText("ocultar");
					pwdNuevacontrasea.setEchoChar(visible);
				}else { // ocultar
					btnVerNueva.setText("ver");
					pwdNuevacontrasea.setEchoChar(dot);
				}
			}});
		
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pwdNuevacontrasea.getText().length() >= 4) {
					// 0 = si,1 = no 
					int eleccion = JOptionPane.showConfirmDialog(null, "Seguro que quieres cambiar la contraseña a "+pwdNuevacontrasea.getText()+" ?", "Confirmar cambio de contrseña", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (eleccion == 0) {
						usuario.setPassw(pwdNuevacontrasea.getText());
						GestorDatos.getInstance().actualizarUsuario(usuario, usuario.getUser());
						JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente");
						dispose();
						AdministradorPanelUsuariosUI administradorPanelUsuariosUI = new AdministradorPanelUsuariosUI(administrador);
						administradorPanelUsuariosUI.setVisible(true);
					}

				}else {
					JOptionPane.showMessageDialog(null, "La contraseña debe ser minimo de 4 caracteres");
				}
			}});
	}
}
