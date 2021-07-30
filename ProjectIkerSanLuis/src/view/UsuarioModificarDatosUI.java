package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import db.GestorDatos;
import model.Centro;
import model.Usuario;

public class UsuarioModificarDatosUI extends JFrame{
	
	private Usuario usuario;
	private JPanel panel;
	private JLabel lblSaludo;
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
	private JTextField txtTipoDeUsuario;
	private JButton btnQuieresCambiarTu;
	
	public UsuarioModificarDatosUI(Usuario usuario) {
		this.usuario = usuario;
		
		panel = new JPanel();
	
		setBounds(100, 100, 663, 533);		
		getContentPane().add(panel, BorderLayout.NORTH);
		
		lblSaludo = new JLabel("Estas modificando los datos de "+this.usuario.getUser());
		panel.add(lblSaludo);
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {30, 30, 0, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl_panel_1.rowHeights = new int[] {30, 0, 30, 0, 30, 0, 30, 0, 30, 0, 30, 30, 30, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel_1.setLayout(gbl_panel_1);
		
		lblUsuario = new JLabel("Usuario :");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 1;
		panel_1.add(lblUsuario, gbc_lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setEditable(false);
		txtUsuario.setText("Usuario");
		GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
		gbc_txtUsuario.gridwidth = 11;
		gbc_txtUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsuario.gridx = 3;
		gbc_txtUsuario.gridy = 1;
		panel_1.add(txtUsuario, gbc_txtUsuario);
		txtUsuario.setColumns(10);
		
		lblNombre = new JLabel("Nombre :");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 3;
		panel_1.add(lblNombre, gbc_lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setText("Nombre");
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridwidth = 11;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 0);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 3;
		panel_1.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		lblApellidos = new JLabel("Apellidos :");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 5;
		panel_1.add(lblApellidos, gbc_lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setText("Apellidos");
		GridBagConstraints gbc_txtApellidos = new GridBagConstraints();
		gbc_txtApellidos.gridwidth = 11;
		gbc_txtApellidos.insets = new Insets(0, 0, 5, 0);
		gbc_txtApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApellidos.gridx = 3;
		gbc_txtApellidos.gridy = 5;
		panel_1.add(txtApellidos, gbc_txtApellidos);
		txtApellidos.setColumns(10);
		
		lblEmail = new JLabel("Email :");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 7;
		panel_1.add(lblEmail, gbc_lblEmail);
		
		textEmail = new JTextField();
		textEmail.setText("Email");
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.gridwidth = 11;
		gbc_textEmail.insets = new Insets(0, 0, 5, 0);
		gbc_textEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmail.gridx = 3;
		gbc_textEmail.gridy = 7;
		panel_1.add(textEmail, gbc_textEmail);
		textEmail.setColumns(10);
		
		lblTipoDeUsuario = new JLabel("Tipo de Usuario :");
		GridBagConstraints gbc_lblTipoDeUsuario = new GridBagConstraints();
		gbc_lblTipoDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoDeUsuario.gridx = 1;
		gbc_lblTipoDeUsuario.gridy = 9;
		panel_1.add(lblTipoDeUsuario, gbc_lblTipoDeUsuario);
		
		txtTipoDeUsuario = new JTextField();
		txtTipoDeUsuario.setEditable(false);
		txtTipoDeUsuario.setText("Tipo de Usuario");
		GridBagConstraints gbc_txtTipoDeUsuario = new GridBagConstraints();
		gbc_txtTipoDeUsuario.gridwidth = 11;
		gbc_txtTipoDeUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_txtTipoDeUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTipoDeUsuario.gridx = 3;
		gbc_txtTipoDeUsuario.gridy = 9;
		panel_1.add(txtTipoDeUsuario, gbc_txtTipoDeUsuario);
		txtTipoDeUsuario.setColumns(10);
		
		btnQuieresCambiarTu = new JButton("Quieres cambiar tu contrase\u00F1a ?");
		btnQuieresCambiarTu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioCambiarContraseñaUI usuarioCambiarContraseñaUI = new UsuarioCambiarContraseñaUI(usuario);
				usuarioCambiarContraseñaUI.setVisible(true);
			}});
		
		GridBagConstraints gbc_btnQuieresCambiarTu = new GridBagConstraints();
		gbc_btnQuieresCambiarTu.gridwidth = 6;
		gbc_btnQuieresCambiarTu.insets = new Insets(0, 0, 5, 5);
		gbc_btnQuieresCambiarTu.gridx = 3;
		gbc_btnQuieresCambiarTu.gridy = 11;
		panel_1.add(btnQuieresCambiarTu, gbc_btnQuieresCambiarTu);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Usuario usuarioPrevio = (Usuario) usuario.clone();
					if (!txtUsuario.getText().equals("") && !txtNombre.getText().equals("") && !txtApellidos.getText().equals("") && !textEmail.getText().equals("")) {
						usuario.setNombre(txtNombre.getText());
						usuario.setApellidos(txtApellidos.getText());
						usuario.setEmail(textEmail.getText());					
						boolean flag = GestorDatos.getInstance().actualizarUsuario(usuario, usuarioPrevio.getUser());
						if (flag) {
							JOptionPane.showMessageDialog(null, "Los datos de el usuario "+usuarioPrevio.getUser()+" se han actualizdo correctamente");
						}else {
							JOptionPane.showMessageDialog(null, "Los datos no son correctos");
							UsuarioModificarDatosUI.this.usuario = usuarioPrevio;
							actualizar();
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Los datos no pueden estar vacios");
					}
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Los datos no son correctos");
				}
			}});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.gridwidth = 6;
		gbc_btnGuardar.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuardar.gridx = 3;
		gbc_btnGuardar.gridy = 13;
		panel_1.add(btnGuardar, gbc_btnGuardar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
			
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 0, 5);
		gbc_btnVolver.gridwidth = 6;
		gbc_btnVolver.gridx = 3;
		gbc_btnVolver.gridy = 14;
		panel_1.add(btnVolver, gbc_btnVolver);
		
		this.actualizar();
	}
	private void actualizar() {
		this.usuario = GestorDatos.getInstance().getUsuarioCompleto(this.usuario.getUser());
		
//		System.out.println(usuario.toString());
		this.txtUsuario.setText(this.usuario.getUser());
		txtNombre.setText(usuario.getNombre());
		txtApellidos.setText(usuario.getApellidos());
		txtNombre.setText(usuario.getNombre());
		if (usuario.getTipo().equals("u")) {
			this.txtTipoDeUsuario.setText("Usuario");		
		}else if (usuario.getTipo().equals("t")) {
			this.txtTipoDeUsuario.setText("Tecnico");		
		}else if (usuario.getTipo().equals("a")) {
			this.txtTipoDeUsuario.setText("Administrador");		
		}
		textEmail.setText(usuario.getEmail());
	}
	
	
}
