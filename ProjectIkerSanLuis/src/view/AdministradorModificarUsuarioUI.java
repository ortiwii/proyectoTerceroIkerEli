package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.GestorDatos;
import model.Administrador;
import model.Centro;
import model.Usuario;

import javax.swing.JComboBox;
import java.awt.Font;

public class AdministradorModificarUsuarioUI extends JFrame{
	
	private Administrador administrador;
	private Usuario usuario;
	private JPanel panel_1;
	private JLabel lblUsuario;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblEmail;
	private JLabel lblTipoDeUsuario;
	private JButton btnGuardar;
	private JButton btnVolver;
	private JTextField txtUsuario;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField textEmail;
	private JButton btnCambiarContraseña;
	private JLabel lblInfo;
	private JLabel lblSaludo;
	private JComboBox cboxTipos;
	private JLabel lblCentro;
	private JComboBox cboxCentros;
	private Vector<Centro> centros;
	private JButton btnEliminarUsuario;
	
	public AdministradorModificarUsuarioUI (Administrador administrador, Usuario usuario) {
		this.administrador = administrador;
		this.usuario = usuario;
		initialize();
	}
	private void initialize() {
		
		setBounds(100, 100, 662, 540);		
		getContentPane().setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 645, 493);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblUsuario = new JLabel("Usuario :");
		lblUsuario.setBounds(52, 105, 52, 16);
		panel_1.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(165, 102, 447, 22);
		txtUsuario.setText("Usuario");
		panel_1.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(52, 150, 54, 16);
		panel_1.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(165, 147, 447, 22);
		txtNombre.setText("Nombre");
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblApellidos = new JLabel("Apellidos :");
		lblApellidos.setBounds(44, 195, 60, 16);
		panel_1.add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(165, 192, 447, 22);
		txtApellidos.setText("Apellidos");
		panel_1.add(txtApellidos);
		txtApellidos.setColumns(10);
		
		lblEmail = new JLabel("Email :");
		lblEmail.setBounds(54, 242, 40, 16);
		panel_1.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(165, 239, 447, 22);
		textEmail.setText("Email");
		panel_1.add(textEmail);
		textEmail.setColumns(10);
		
		lblTipoDeUsuario = new JLabel("Tipo de Usuario :");
		lblTipoDeUsuario.setBounds(34, 289, 99, 16);
		panel_1.add(lblTipoDeUsuario);
		
		btnCambiarContraseña = new JButton("Quieres cambiar su contrase\u00F1a ?");
		btnCambiarContraseña.setBounds(145, 382, 272, 25);
		panel_1.add(btnCambiarContraseña);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(54, 382, 79, 25);
		panel_1.add(btnGuardar);

		
		lblInfo = new JLabel("");
		lblInfo.setBounds(0, 0, 0, 0);
		panel_1.add(lblInfo);
		panel_1.add(btnGuardar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(282, 420, 79, 25);
		panel_1.add(btnVolver);
		
		lblSaludo = new JLabel("MODIFICAR DATOS USUARIO");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(37, 30, 575, 16);
		panel_1.add(lblSaludo);
		
		String[] tipos = {"Usuario", "Tecnico", "Administrador"};
		cboxTipos = new JComboBox(tipos);
		cboxTipos.setBounds(165, 286, 447, 22);
		panel_1.add(cboxTipos);
		
		lblCentro = new JLabel("Centro :");
		lblCentro.setBounds(51, 337, 56, 16);
		panel_1.add(lblCentro);
		
		centros = GestorDatos.getInstance().getCentros();
		cboxCentros = new JComboBox(centros);
		cboxCentros.setBounds(165, 334, 447, 22);
		panel_1.add(cboxCentros);		
		
		btnEliminarUsuario = new JButton("Eliminar Usuario");
		btnEliminarUsuario.setBounds(429, 382, 160, 25);
		panel_1.add(btnEliminarUsuario);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministradorPanelUsuariosUI administradorPanelUsuariosUI = new AdministradorPanelUsuariosUI(administrador);
				administradorPanelUsuariosUI.setVisible(true);
				dispose();
			}});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Usuario usuarioPrevio = (Usuario) usuario.clone();
					if (!txtUsuario.getText().equals("") && !txtNombre.getText().equals("") && !txtApellidos.getText().equals("") && !textEmail.getText().equals("")) {
						usuario.setUser(txtUsuario.getText());
						usuario.setNombre(txtNombre.getText());
						usuario.setApellidos(txtApellidos.getText());
						usuario.setEmail(textEmail.getText());
						if (cboxTipos.getSelectedItem().equals("Usuario")) {
							usuario.setTipo("u");
						}else if (cboxTipos.getSelectedItem().equals("Tecnico")) {
							usuario.setTipo("t");
						}else if (cboxTipos.getSelectedItem().equals("Administrador")) {
							usuario.setTipo("a");
						}
						usuario.setCentro((Centro) cboxCentros.getSelectedItem());
						boolean flag = GestorDatos.getInstance().actualizarUsuario(usuario, usuarioPrevio.getUser());
						if (flag) {
							JOptionPane.showMessageDialog(null, "Los datos de el usuario "+usuarioPrevio.getUser()+" se han actualizdo correctamente");
						}else {
							JOptionPane.showMessageDialog(null, "Los datos no son correctos");
							usuario = usuarioPrevio;
							actualizar();
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Los datos no pueden estar vacios");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Los datos no son correctos ");
					actualizar();
				}

			}});
		
		btnEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				// 0 = si,1 = no 
				int eleccion = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar el usuario "+usuario.getUser()+"?", "Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (eleccion == 0) {
					boolean flag = GestorDatos.getInstance().eliminarUsuario(usuario);
					if (flag) {
						JOptionPane.showMessageDialog(null, "Se ha eliminado el usuario "+usuario.getUser()+" correctamente");
						dispose();
						AdministradorPanelUsuariosUI administradorPanelUsuariosUI = new AdministradorPanelUsuariosUI(administrador);
						administradorPanelUsuariosUI.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "No se ha podido eliminar el usuario");
					}
				}
			}});
		
		btnCambiarContraseña.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				dispose();
				AdministradorCambiarContraseñaUsuarioUI administradorCambiarContraseñaUsuarioUI = new AdministradorCambiarContraseñaUsuarioUI(administrador, usuario);
				administradorCambiarContraseñaUsuarioUI.setVisible(true);
			}});
		this.actualizar();
	}
	private void actualizar() {
		this.usuario = GestorDatos.getInstance().getUsuarioCompleto(this.usuario.getUser());		

		txtUsuario.setText(this.usuario.getUser());
		txtNombre.setText(usuario.getNombre());
		txtApellidos.setText(usuario.getApellidos());
		textEmail.setText(usuario.getEmail());
		if (usuario.getTipo().equals("u")) {
			cboxTipos.setSelectedIndex(0);
		}else if (usuario.getTipo().equals("t")) {
			cboxTipos.setSelectedIndex(1);
		}else if (usuario.getTipo().equals("a")) {
			cboxTipos.setSelectedIndex(2);
		}
		Iterator<Centro>itr = centros.iterator();
		boolean flag = false;
		while(itr.hasNext() && !flag) {
			Centro act = itr.next();
			if (act.getNombre().equals(usuario.getCentro().getNombre())) {
				flag = true;
				cboxCentros.setSelectedItem(act);
			}
		}
		
		
	}
}
