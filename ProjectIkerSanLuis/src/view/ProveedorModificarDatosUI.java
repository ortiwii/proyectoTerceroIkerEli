package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import model.Administrador;
import model.Centro;
import model.Proveedor;
import model.Utils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.GestorDatos;

public class ProveedorModificarDatosUI extends JFrame{
	
	private Proveedor proveedor;
	private JLabel label;
	private Administrador administrador;
	private JLabel lblSaludo;
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
	
	public ProveedorModificarDatosUI (Proveedor proveedor) {
		this.proveedor = proveedor;
		initialize();
	}
	private void initialize() {

		setBounds(100, 100, 638, 419);
		getContentPane().setLayout(null);
		
		lblSaludo = new JLabel("MODIFICAR  MIS DATOS");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(25, 38, 563, 16);
		getContentPane().add(lblSaludo);
		
		lblUsuario = new JLabel("Usuario :");
		lblUsuario.setBounds(25, 105, 117, 16);
		getContentPane().add(lblUsuario);
		
		lblEmail = new JLabel("Email :");
		lblEmail.setBounds(25, 218, 117, 16);
		getContentPane().add(lblEmail);
		
		lblContrasea = new JLabel("Contrase\u00F1a :");
		lblContrasea.setBounds(25, 163, 117, 16);
		getContentPane().add(lblContrasea);
		
		lblCentro = new JLabel("Centro :");
		lblCentro.setBounds(25, 270, 117, 16);
		getContentPane().add(lblCentro);
		
		txtUsuario = new JTextField();
		txtUsuario.setEditable(false);
		txtUsuario.setBounds(139, 102, 449, 22);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		psw = new JPasswordField();
		psw.setBounds(139, 160, 354, 22);
		getContentPane().add(psw);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(139, 215, 449, 22);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		centros = GestorDatos.getInstance().getCentros();
		comboBox = new JComboBox(centros);
		comboBox.setEditable(true);
		comboBox.setBounds(139, 267, 449, 22);
		getContentPane().add(comboBox);
		
		btnVer = new JButton("ver");
		btnVer.setBounds(511, 159, 77, 25);
		getContentPane().add(btnVer);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(25, 322, 143, 25);
		getContentPane().add(btnGuardar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(491, 322, 97, 25);
		getContentPane().add(btnVolver);
		
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
			}});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Utils.ValidarMail(txtEmail.getText())) {
					if (psw.getText().length() >= 4) {
						// 0 = si,1 = no 
						int eleccion = JOptionPane.showConfirmDialog(null, "Seguro que quieres cambiar los datos ?", "Confirmar cambio", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (eleccion == 0) {
							
							proveedor.setEmail(txtEmail.getText());
							proveedor.setPassw(psw.getText());
							boolean flag = GestorDatos.getInstance().actualizarProveedor(proveedor, proveedor.getIdProveedor());
							if (flag) {
								JOptionPane.showMessageDialog(null, "Se han modificado los datos");
							}else {
								JOptionPane.showMessageDialog(null, "No se han modificado los datos");
							}							
						}
					}else {
						JOptionPane.showMessageDialog(null, "La contraseña tiene que tener al menos 4 caracteres");
						actualizar();
					}
				}else {
					JOptionPane.showMessageDialog(null, "El email no es correcto");
					actualizar();
				}
				
			}});
		
		this.actualizar();
	}
	private void actualizar() {
		txtUsuario.setText(proveedor.getUser());
		txtEmail.setText(proveedor.getEmail());
		psw.setText(proveedor.getPassw());
		Iterator<Centro>itr = centros.iterator();
		comboBox.setSelectedItem(proveedor.getCentro());
	}

}
