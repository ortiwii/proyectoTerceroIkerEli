package view;

import javax.swing.JFrame;

import model.Administrador;
import model.Centro;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTextField;

import db.GestorDatos;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AdministradorCrearUsuarioUI extends JFrame {
	private Administrador administrador;
	private Vector<Centro> centros = new Vector<>();
	private JLabel lblSaludo;
	private JLabel lbLUsuario;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblEmail;
	private JLabel lblTipoDeUsuario;
	private JLabel lblCentro;
	private JTextField txtUsuario;
	private JLabel lblContraseña;
	private JPasswordField pwd;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtEmail;
	private JComboBox cboxTipo;
	private JComboBox cboxCentros;
	private JButton btnCrear;
	private JButton btnVolver;
	private JButton btnVer;
	private char visible;
	private char dot;
	
	public AdministradorCrearUsuarioUI(Administrador administrador) {
		this.administrador = administrador;
		
		initialize();
	}
	private void initialize() {
		
		setBounds(100, 100, 638, 477);
		getContentPane().setLayout(null);
		
		lblSaludo = new JLabel("CREAR USUARIO");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(24, 23, 596, 16);
		getContentPane().add(lblSaludo);
		
		lbLUsuario = new JLabel("Usuario :");
		lbLUsuario.setBounds(24, 60, 147, 16);
		getContentPane().add(lbLUsuario);
		
		lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(24, 147, 147, 16);
		getContentPane().add(lblNombre);
		
		lblApellidos = new JLabel("Apellidos :");
		lblApellidos.setBounds(24, 191, 147, 16);
		getContentPane().add(lblApellidos);
		
		lblEmail = new JLabel("Email :");
		lblEmail.setBounds(24, 231, 147, 16);
		getContentPane().add(lblEmail);
		
		lblTipoDeUsuario = new JLabel("Tipo de Usuario:");
		lblTipoDeUsuario.setBounds(24, 270, 147, 16);
		getContentPane().add(lblTipoDeUsuario);
		
		lblCentro = new JLabel("Centro :");
		lblCentro.setBounds(24, 315, 56, 16);
		getContentPane().add(lblCentro);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(183, 57, 394, 22);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblContraseña = new JLabel("Contrase\u00F1a :");
		lblContraseña.setBounds(24, 107, 147, 16);
		getContentPane().add(lblContraseña);
		
		pwd = new JPasswordField();
		pwd.setBounds(183, 104, 302, 22);
		getContentPane().add(pwd);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(183, 144, 394, 22);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(183, 188, 394, 22);
		getContentPane().add(txtApellidos);
		txtApellidos.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(183, 228, 394, 22);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		String[] tipos = {"Usuario", "Tecnico", "Administrador"};
		cboxTipo = new JComboBox(tipos);
		cboxTipo.setBounds(183, 267, 394, 22);
		getContentPane().add(cboxTipo);
		
		centros = GestorDatos.getInstance().getCentros();
		cboxCentros = new JComboBox(centros);
		cboxCentros.setBounds(183, 312, 394, 22);
		getContentPane().add(cboxCentros);
		
		btnCrear = new JButton("CREAR");
		btnCrear.setBounds(143, 372, 113, 25);
		getContentPane().add(btnCrear);
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(340, 372, 113, 25);
		getContentPane().add(btnVolver);
		
		cboxTipo.setSelectedIndex(-1);
		cboxCentros.setSelectedIndex(-1);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorPanelUsuariosUI administradorPanelUsuariosUI = new AdministradorPanelUsuariosUI(administrador);
				administradorPanelUsuariosUI.setVisible(true);
			}});
		
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pwd.getText().length() >= 4) {
					if (!txtUsuario.getText().equals("") && !pwd.getText().equals("") && !txtNombre.getText().equals("") && !txtApellidos.getText().equals("") && !txtEmail.getText().equals("") && 
							cboxTipo.getSelectedIndex() != -1 && cboxCentros.getSelectedIndex() != -1) {
						
						Usuario usuario = null;
						int election = cboxTipo.getSelectedIndex();
						if (election == 0) {
							usuario = new Usuario(txtUsuario.getText(), pwd.getText(), txtNombre.getText(), txtApellidos.getText(), txtEmail.getText(), "u", null, (Centro) cboxCentros.getSelectedItem());
						}else if (election == 1) {
							usuario = new Usuario(txtUsuario.getText(), pwd.getText(), txtNombre.getText(), txtApellidos.getText(), txtEmail.getText(), "t", null, (Centro) cboxCentros.getSelectedItem());
						}else if (election == 2) {
							usuario = new Usuario(txtUsuario.getText(), pwd.getText(), txtNombre.getText(), txtApellidos.getText(), txtEmail.getText(), "a", null, (Centro) cboxCentros.getSelectedItem());
						}
						boolean flag = GestorDatos.getInstance().insertarUsuario(usuario);
						if (flag) {
							JOptionPane.showMessageDialog(null, "Se ha creado el usuario "+usuario.getUser()+" correctamente");
							dispose();
							AdministradorPanelUsuariosUI administradorPanelUsuariosUI = new AdministradorPanelUsuariosUI(administrador);
							administradorPanelUsuariosUI.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "Los datos no son correctos");
						}
						
						
					}else {
						JOptionPane.showMessageDialog(null, "Los datos no son correctos");
					}
				}else {
					JOptionPane.showMessageDialog(null, "La contraseña tiene que tener al menos 4 caracteres");
				}
								
			}});
		
		btnVer = new JButton("ver");
		btnVer.setBounds(497, 103, 80, 25);
		getContentPane().add(btnVer);
		
		dot = pwd.getEchoChar();
		visible = (char)0;
		
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnVer.getText().equals("ver")) {
					btnVer.setText("ocultar");
					pwd.setEchoChar(visible);
				}else { // ocultar
					btnVer.setText("ver");
					pwd.setEchoChar(dot);
				}
				
			}});
		
		
	}

}
