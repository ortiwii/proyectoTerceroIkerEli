package view;

import javax.swing.JFrame;

import model.Administrador;
import model.Centro;
import model.Proveedor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTextField;

import db.GestorDatos;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AdministradorModificarProveedorUI extends JFrame{
	
	private Proveedor proveedor;
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
	private JButton btnEliminarProveedor;
	private JButton btnVolver;
	private char visible;
	private char dot;
	private Vector<Centro> centros;
	
	public AdministradorModificarProveedorUI (Administrador administrador, Proveedor proveedor) {
		this.administrador = administrador;
		this.proveedor = proveedor;
		initialize();
	}
	private void initialize() {
		
		System.out.println(administrador.toString());
		System.out.println(proveedor.toString());
		setBounds(100, 100, 638, 419);
		getContentPane().setLayout(null);
		
		lblSaludo = new JLabel("MODIFICAR DATOS DEL PROVEEDOR");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(25, 25, 563, 16);
		getContentPane().add(lblSaludo);
		
		lblAdministrador = new JLabel("Administrador : "+administrador.getUser());
		lblAdministrador.setBounds(25, 54, 563, 16);
		getContentPane().add(lblAdministrador);
		
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
		comboBox.setBounds(139, 267, 449, 22);
		getContentPane().add(comboBox);
		
		btnVer = new JButton("ver");
		btnVer.setBounds(511, 159, 77, 25);
		getContentPane().add(btnVer);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(25, 322, 143, 25);
		getContentPane().add(btnGuardar);
		
		btnEliminarProveedor = new JButton("Eliminar Proveedor");
		btnEliminarProveedor.setBounds(222, 322, 211, 25);
		getContentPane().add(btnEliminarProveedor);
		
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
				AdministradorGestionProveedoresUI administradorGestionProveedoresUI = new AdministradorGestionProveedoresUI(administrador);
				administradorGestionProveedoresUI.setVisible(true);
			}});
		
		btnEliminarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 0 = si,1 = no 
				int eleccion = JOptionPane.showConfirmDialog(null, "Seguro que quieres borrar el proveedor "+proveedor.getUser()+" ?", "Confirmar eliminacion proveedor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (eleccion == 0) {
					boolean flag = GestorDatos.getInstance().eliminarProveedor(proveedor);
					if (flag) {
						JOptionPane.showMessageDialog(null, "El proveedor "+proveedor.getUser()+" se ha eliminado correctamente");
						dispose();
						AdministradorGestionProveedoresUI administradorGestionProveedoresUI = new AdministradorGestionProveedoresUI(administrador);
						administradorGestionProveedoresUI.setVisible(true);
						
					}else {
						JOptionPane.showMessageDialog(null, "No se ha podido eliminar el proveedor "+proveedor.getUser());
					}
				}
			}});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Proveedor provPrevio = (Proveedor) proveedor.clone();
					if (psw.getText().length() >= 4) {
						if (!txtUsuario.getText().equals("") && !txtEmail.getText().equals("") && comboBox.getSelectedIndex() != -1) {
							
							proveedor.setUser(txtUsuario.getText());
							proveedor.setEmail(txtEmail.getText());
							proveedor.setPassw(psw.getText());
							proveedor.setCentro((Centro) comboBox.getSelectedItem());
							boolean flag = GestorDatos.getInstance().actualizarProveedor(proveedor, provPrevio.getIdProveedor());
							if (flag) {
								JOptionPane.showMessageDialog(null, "Los datos del proveedor "+proveedor.getUser()+" se han actualizado correctamente");
							}else {
								JOptionPane.showMessageDialog(null, "Los datos no son correctos");
								proveedor = provPrevio;
								actualizar();
							}
						}else {
							JOptionPane.showMessageDialog(null, "Los datos no son correctos");
							proveedor = provPrevio;
							actualizar();
						}
					}else {
						JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 4 caracteres");
						actualizar();
					}
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Los datos no son correctos");
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
		boolean flag = false;
		while(itr.hasNext() && !flag) {
			Centro act = itr.next();
			if (act.getNombre().equals(proveedor.getCentro().getNombre())) {
				flag = true;
				comboBox.setSelectedItem(act);
			}
		}
	}

}
