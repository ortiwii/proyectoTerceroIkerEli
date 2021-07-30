package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Usuario;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class UsuarioUI extends JFrame{
	Usuario usuario;
	private JButton btnListadoDeComponentes;
	private JButton btnListadoDeIncidencias;
	
	public UsuarioUI(Usuario usuario) {
		
		this.usuario = usuario;									
		
		setBounds(100, 100, 571, 465);
		
		JButton btnEnviarUnaSolicitud = new JButton("ENVIAR UNA SOLICITUD");
		btnEnviarUnaSolicitud.setBounds(30, 215, 324, 25);
		btnEnviarUnaSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UsuarioEnviarSolicitudUI usuarioEnviarSolicitudUI = new UsuarioEnviarSolicitudUI(usuario);
				usuarioEnviarSolicitudUI.setVisible(true);
				
			}
		});
		
		
		JButton btnEnseñarDatos = new JButton("MODIFICAR DATOS");
		btnEnseñarDatos.setBounds(30, 155, 324, 25);
		getContentPane().setLayout(null);
		
		JLabel lblBienvenido = new JLabel();
		lblBienvenido.setBounds(30, 134, 324, 16);
		getContentPane().add(lblBienvenido);
		getContentPane().add(btnEnseñarDatos);
		
		
		JButton btnEnviarIncidencia = new JButton("ENVIAR UNA INCIDENCIA");
		btnEnviarIncidencia.setBounds(30, 185, 324, 25);
		getContentPane().add(btnEnviarIncidencia);
		getContentPane().add(btnEnviarUnaSolicitud);
		
		JButton btnVerLista = new JButton("LISTADO DE TUS SOLICITUDES");
		btnVerLista.setBounds(30, 245, 324, 25);
		getContentPane().add(btnVerLista);
		
		JButton btnAulas = new JButton("AULAS");
		btnAulas.setBounds(30, 305, 324, 25);
		getContentPane().add(btnAulas);
		
		btnListadoDeComponentes = new JButton("LISTADO DE COMPONENTES");
		btnListadoDeComponentes.setBounds(30, 336, 324, 25);
		getContentPane().add(btnListadoDeComponentes);
		
		btnListadoDeIncidencias = new JButton("LISTADO DE TUS INCIDENCIAS");
		btnListadoDeIncidencias.setBounds(30, 273, 324, 25);
		getContentPane().add(btnListadoDeIncidencias);
		
		lblBienvenido.setText("Hola " + usuario.getUser() );
		
		btnEnseñarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioModificarDatosUI usuarioModificarDatosUI = new UsuarioModificarDatosUI(usuario);
				usuarioModificarDatosUI.setVisible(true);				
			}
		});
		
		
		btnEnviarIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioEnviarIncidenciaUI usuarioEnviarIncidenciaUI = new UsuarioEnviarIncidenciaUI(usuario);
				usuarioEnviarIncidenciaUI.setVisible(true);
				
					
				
			}
		});
		btnVerLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UsuarioMostrarSolicitudesUI usuarioMostrarSolicitudesUI = new UsuarioMostrarSolicitudesUI(usuario);
				usuarioMostrarSolicitudesUI.setVisible(true);
				
//				VMostrarSolicitudes mostrarSolicitudes = new VMostrarSolicitudes();
//				mostrarSolicitudes.setVisible(true);
				
			}
		});
		
		btnListadoDeIncidencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioMostrarIncidenciasUI usuarioMostrarIncidenciasUI = new UsuarioMostrarIncidenciasUI(usuario);
				usuarioMostrarIncidenciasUI.setVisible(true);
			}});
		
		btnAulas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioMostrarAulasUI usuarioMostrarAulasUI = new UsuarioMostrarAulasUI(usuario);
				usuarioMostrarAulasUI.setVisible(true);
			}
		});
		
		btnListadoDeComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioVerComponentesUI usuarioVerComponentesUI = new UsuarioVerComponentesUI(usuario.getCentro());
				usuarioVerComponentesUI.setVisible(true);
			}
		});
	}
}
