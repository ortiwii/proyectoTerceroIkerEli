package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

import db.GestorDatos;

public class UsuarioEnviarIncidenciaUI extends JFrame {
	
	private Usuario usuario;
	private JTextArea txtDesc;
	
	public UsuarioEnviarIncidenciaUI (Usuario usuario) {
		this.usuario = usuario;
		initialize();
	}
	private void initialize() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{43, 65, 237, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{37, 14, 56, 20, 42, 84, 42, 23, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblSaludo = new JLabel("          ENV\u00CDO DE UNA INCIDENCIA");
		lblSaludo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblSaludo = new GridBagConstraints();
		gbc_lblSaludo.gridwidth = 3;
		gbc_lblSaludo.anchor = GridBagConstraints.WEST;
		gbc_lblSaludo.fill = GridBagConstraints.VERTICAL;
		gbc_lblSaludo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaludo.gridx = 2;
		gbc_lblSaludo.gridy = 1;
		getContentPane().add(lblSaludo, gbc_lblSaludo);
		
		JButton btnEnviarIncidencia = new JButton("ENVIAR INCIDENCIA");
		btnEnviarIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GestorDatos.getInstance().generarIncidencia(txtDesc.getText(), Calendar.getInstance(), usuario);
				
				JOptionPane.showMessageDialog(null, "Se ha enviado la incidencia correctamente");
				dispose();
				
//				Solicitud solicitud = new Solicitud();
//				solicitud.asignarSolicitud(txtId.getText(), txtDesc.getText());
//				dispose();
				
			}
		});
		
		JLabel lblIncidencia = new JLabel("Incidencia :");
		GridBagConstraints gbc_lblIncidencia = new GridBagConstraints();
		gbc_lblIncidencia.anchor = GridBagConstraints.NORTH;
		gbc_lblIncidencia.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblIncidencia.insets = new Insets(0, 0, 5, 5);
		gbc_lblIncidencia.gridx = 1;
		gbc_lblIncidencia.gridy = 3;
		getContentPane().add(lblIncidencia, gbc_lblIncidencia);
		
		txtDesc = new JTextArea();
		GridBagConstraints gbc_txtDesc = new GridBagConstraints();
		gbc_txtDesc.gridwidth = 4;
		gbc_txtDesc.gridheight = 3;
		gbc_txtDesc.fill = GridBagConstraints.BOTH;
		gbc_txtDesc.insets = new Insets(0, 0, 5, 5);
		gbc_txtDesc.gridx = 2;
		gbc_txtDesc.gridy = 3;
		getContentPane().add(txtDesc, gbc_txtDesc);
		GridBagConstraints gbc_btnEnviarIncidencia = new GridBagConstraints();
		gbc_btnEnviarIncidencia.insets = new Insets(0, 0, 5, 5);
		gbc_btnEnviarIncidencia.gridx = 2;
		gbc_btnEnviarIncidencia.gridy = 7;
		getContentPane().add(btnEnviarIncidencia, gbc_btnEnviarIncidencia);
		setBounds(100, 100, 499, 435);
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 5, 5);
		gbc_btnVolver.gridx = 2;
		gbc_btnVolver.gridy = 8;
		getContentPane().add(btnVolver, gbc_btnVolver);
		
		JLabel label = new JLabel("  ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = 6;
		gbc_label.gridy = 9;
		getContentPane().add(label, gbc_label);
	}

}
