package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.GestorDatos;
import model.Administrador;
import model.Centro;
import model.Proveedor;

public class AdministradorCrearProveedorUI extends JFrame{
	
	private Administrador administrador;
	private JLabel lblSaludo;
	private JLabel lblAdministrador;
	private JLabel lblUsuario;
	private JLabel lblEmail;
	private JLabel lblContrasea;
	private JLabel lblCentro;
	private JTextField txtUsuario;
	private JPasswordField psw;
	private JTextField txtEmail;
	private JComboBox comboBox;
	private JButton btnVer;
	private JButton btnGuardar;
	private JButton btnVolver;
	private char visible;
	private char dot;
	private Vector<Centro> centros;
	private JLabel lblIdProveedor;
	private JTextField txtId;
	
	public AdministradorCrearProveedorUI (Administrador administrador) {
		this.administrador = administrador;

		initialize();
	}
	private void initialize() {
		
		setBounds(100, 100, 639, 459);
		getContentPane().setLayout(null);
		
		lblSaludo = new JLabel("DAR DE ALTA UN PROVEEDOR");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(25, 25, 563, 16);
		getContentPane().add(lblSaludo);
		
		lblAdministrador = new JLabel("Administrador : "+administrador.getUser());
		lblAdministrador.setBounds(25, 54, 563, 16);
		getContentPane().add(lblAdministrador);
		
		lblUsuario = new JLabel("Usuario :");
		lblUsuario.setBounds(25, 157, 117, 16);
		getContentPane().add(lblUsuario);
		
		lblEmail = new JLabel("Email :");
		lblEmail.setBounds(25, 270, 117, 16);
		getContentPane().add(lblEmail);
		
		lblContrasea = new JLabel("Contrase\u00F1a :");
		lblContrasea.setBounds(25, 215, 117, 16);
		getContentPane().add(lblContrasea);
		
		lblCentro = new JLabel("Centro :");
		lblCentro.setBounds(25, 322, 117, 16);
		getContentPane().add(lblCentro);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(139, 154, 449, 22);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		psw = new JPasswordField();
		psw.setBounds(139, 212, 354, 22);
		getContentPane().add(psw);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(139, 267, 449, 22);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		centros = GestorDatos.getInstance().getCentros();
		comboBox = new JComboBox(centros);
		comboBox.setBounds(139, 319, 449, 22);
		comboBox.setSelectedIndex(-1);
		getContentPane().add(comboBox);
		
		btnVer = new JButton("ver");
		btnVer.setBounds(511, 211, 77, 25);
		getContentPane().add(btnVer);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(25, 374, 143, 25);
		getContentPane().add(btnGuardar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(491, 374, 97, 25);
		getContentPane().add(btnVolver);
		
		lblIdProveedor = new JLabel("Id Proveedor :");
		lblIdProveedor.setBounds(25, 110, 103, 16);
		getContentPane().add(lblIdProveedor);
		
		txtId = new JTextField();
		txtId.setBounds(139, 107, 449, 22);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		dot = psw.getEchoChar();
		visible = (char)0;
		
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnVer.getText().equals("ver")) {
					btnVer.setText("ocultar");
					psw.setEchoChar(visible);
				}else { // ocultar
					btnVer.setText("ver");
					psw.setEchoChar(dot);
				}				
			}});
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorGestionProveedoresUI administradorGestionProveedoresUI = new AdministradorGestionProveedoresUI(administrador);
				administradorGestionProveedoresUI.setVisible(true);
			}});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					if (psw.getText().length() >= 4) {
						if (!txtUsuario.getText().equals("") && !txtEmail.getText().equals("") && !txtId.getText().equals("") && comboBox.getSelectedIndex() != -1) {
							Proveedor proveedor = new Proveedor(txtId.getText(), txtUsuario.getText(), psw.getText(), txtEmail.getText(), (Centro) comboBox.getSelectedItem());						
					
							boolean flag = GestorDatos.getInstance().insertarProveedor(proveedor);
							if (flag) {
								JOptionPane.showMessageDialog(null, "Se ha creado el proveedor "+proveedor.getUser()+" correctamente");
								txtId.setText("");
								txtEmail.setText("");
								txtUsuario.setText("");
								comboBox.setSelectedIndex(-1);
								psw.setText("");
							}else {
								JOptionPane.showMessageDialog(null, "Los datos no son correctos");

							}
						}else {
							JOptionPane.showMessageDialog(null, "Los datos no son correctos");
						}
					}else {
						JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 4 caracteres");
					}
			}});
		
	
	}
	

}
