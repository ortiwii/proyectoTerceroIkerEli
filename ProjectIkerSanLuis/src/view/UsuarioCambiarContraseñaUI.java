package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Usuario;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;

import db.GestorDatos;
import javax.swing.SwingConstants;

public class UsuarioCambiarContraseñaUI extends JFrame{
	private Usuario usuario;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblSaludo;
	private JPanel panel_2;
	private JLabel lblAntiguaContrasea;
	private JLabel lblNuevaContrasea;
	private JLabel lblInfo;
	private JButton btnGuardar;
	private JButton btnVolver;
	private JLabel label;
	private JPasswordField pwdAntiguaContrasea;
	private JPasswordField pwdNuevaContrasea;
	
	public UsuarioCambiarContraseñaUI(Usuario usuario) {
		this.usuario = usuario;
		panel = new JPanel();
		
		setBounds(100, 100, 551, 358);		
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		lblSaludo = new JLabel("Cambiando contrase\u00F1a de usuario "+this.usuario.getUser());
		panel_1.add(lblSaludo);
		
		panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		lblAntiguaContrasea = new JLabel("Antigua Contrase\u00F1a :");
		GridBagConstraints gbc_lblAntiguaContrasea = new GridBagConstraints();
		gbc_lblAntiguaContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblAntiguaContrasea.gridx = 2;
		gbc_lblAntiguaContrasea.gridy = 1;
		panel_2.add(lblAntiguaContrasea, gbc_lblAntiguaContrasea);
		
		pwdAntiguaContrasea = new JPasswordField();
		GridBagConstraints gbc_pwdAntiguaContrasea = new GridBagConstraints();
		gbc_pwdAntiguaContrasea.gridwidth = 7;
		gbc_pwdAntiguaContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_pwdAntiguaContrasea.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdAntiguaContrasea.gridx = 4;
		gbc_pwdAntiguaContrasea.gridy = 1;
		panel_2.add(pwdAntiguaContrasea, gbc_pwdAntiguaContrasea);
		
		lblNuevaContrasea = new JLabel("Nueva Contrase\u00F1a :");
		GridBagConstraints gbc_lblNuevaContrasea = new GridBagConstraints();
		gbc_lblNuevaContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblNuevaContrasea.gridx = 2;
		gbc_lblNuevaContrasea.gridy = 3;
		panel_2.add(lblNuevaContrasea, gbc_lblNuevaContrasea);
		
		pwdNuevaContrasea = new JPasswordField();
		pwdNuevaContrasea.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_pwdNuevaContrasea = new GridBagConstraints();
		gbc_pwdNuevaContrasea.gridwidth = 7;
		gbc_pwdNuevaContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_pwdNuevaContrasea.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdNuevaContrasea.gridx = 4;
		gbc_pwdNuevaContrasea.gridy = 3;
		panel_2.add(pwdNuevaContrasea, gbc_pwdNuevaContrasea);
		
		lblInfo = new JLabel(" ");
		GridBagConstraints gbc_lblInfo = new GridBagConstraints();
		gbc_lblInfo.gridwidth = 7;
		gbc_lblInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblInfo.gridx = 2;
		gbc_lblInfo.gridy = 5;
		panel_2.add(lblInfo, gbc_lblInfo);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (usuario.getPassw().equals(pwdAntiguaContrasea.getText())){
					usuario.setPassw(pwdNuevaContrasea.getText());
					GestorDatos.getInstance().actualizarContraseñaUsuario(usuario);
					lblInfo.setText("Contraseña cambiada satisfactoriamente a "+pwdNuevaContrasea.getText());
				}else {
					lblInfo.setText("Contraseña incorrecta");
				}
			}});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuardar.gridx = 3;
		gbc_btnGuardar.gridy = 7;
		panel_2.add(btnGuardar, gbc_btnGuardar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});

		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 5, 5);
		gbc_btnVolver.gridx = 3;
		gbc_btnVolver.gridy = 8;
		panel_2.add(btnVolver, gbc_btnVolver);
		
		label = new JLabel("              ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = 11;
		gbc_label.gridy = 9;
		panel_2.add(label, gbc_label);
	}

}
