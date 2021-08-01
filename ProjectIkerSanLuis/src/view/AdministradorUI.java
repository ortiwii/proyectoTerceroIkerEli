package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.Administrador;
import javax.swing.JLabel;

public class AdministradorUI extends JFrame {
	
	private Administrador administrador;
	private JLabel lblSaludo;
	private JButton btnEnseñarDatos;
	private JLabel lblBienvenido;
	private JButton btnGestionProveedores;
	private JButton btnPanelIncidencias;
	private JButton btnGestionarAulas;
	private JButton btnPanelSolicitudes;
	private JButton btnGestionComponentes;
	private JButton btnPanelDeUsuarios;
	
	public AdministradorUI (Administrador administrador) {
		this.administrador = administrador;
		initialize();
	}
	private void initialize() {
		
		setBounds(100, 100, 571, 465);
		
		JButton btnGestionarEquipos = new JButton("GESTIONAR EQUIPOS");
		btnGestionarEquipos.setBounds(30, 215, 324, 25);
		
		btnEnseñarDatos = new JButton("MODIFICAR DATOS");
		btnEnseñarDatos.setBounds(30, 119, 324, 25);
		getContentPane().setLayout(null);
		
		lblBienvenido = new JLabel();
		lblBienvenido.setBounds(30, 69, 324, 16);
		getContentPane().add(lblBienvenido);
		getContentPane().add(btnEnseñarDatos);
		
		
		btnGestionarAulas = new JButton("GESTIONAR AULAS");
		btnGestionarAulas.setBounds(30, 185, 324, 25);
		getContentPane().add(btnGestionarAulas);
		getContentPane().add(btnGestionarEquipos);
		
		btnPanelSolicitudes = new JButton("PANEL DE SOLICITUDES");
		btnPanelSolicitudes.setBounds(30, 245, 324, 25);
		getContentPane().add(btnPanelSolicitudes);
		
		btnGestionComponentes = new JButton("GESTION DE COMPONENTES");
		btnGestionComponentes.setBounds(30, 305, 324, 25);
		getContentPane().add(btnGestionComponentes);
		
		btnGestionProveedores = new JButton("GESTION DE PROVEEDORES y COMPRAS");
		btnGestionProveedores.setBounds(30, 336, 324, 25);
		getContentPane().add(btnGestionProveedores);
		
		btnPanelIncidencias = new JButton("PANEL DE INCIDENCIAS");
		btnPanelIncidencias.setBounds(30, 273, 324, 25);
		getContentPane().add(btnPanelIncidencias);
		
		btnPanelDeUsuarios = new JButton("PANEL DE USUARIOS");
		btnPanelDeUsuarios.setBounds(30, 150, 324, 25);
		getContentPane().add(btnPanelDeUsuarios);
		
		lblBienvenido.setText("Hola " + administrador.getUser() );
		
		btnEnseñarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioModificarDatosUI usuarioModificarDatosUI = new UsuarioModificarDatosUI(administrador);
				usuarioModificarDatosUI.setVisible(true);				
			}
		});
		
		btnPanelDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministradorPanelUsuariosUI administradorPanelUsuariosUI = new AdministradorPanelUsuariosUI(administrador);
				administradorPanelUsuariosUI.setVisible(true);
				
			}
		});
		
		btnGestionarEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoGestionarEquiposUI tecnicoGestionarEquiposUI = new TecnicoGestionarEquiposUI(administrador);
				tecnicoGestionarEquiposUI.setVisible(true);				
			}
		});
		
		btnGestionarAulas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TecnicoGestionarAulasUI tecnicoGestionarAulasUI = new TecnicoGestionarAulasUI(administrador);
				tecnicoGestionarAulasUI.setVisible(true);
				
			}
		});
		btnPanelSolicitudes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoPanelSolicitudesUI tecnicoPanelSolicitudesUI = new TecnicoPanelSolicitudesUI(administrador);
				tecnicoPanelSolicitudesUI.setVisible(true);
			}
		});
		
		btnPanelIncidencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoPanelIncidenciasUI tecnicoPanelIncidenciasUI = new TecnicoPanelIncidenciasUI(administrador);
				tecnicoPanelIncidenciasUI.setVisible(true);
			}});
		
		btnGestionComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoGestionComponentesUI tecnicoGestionComponentesUI = new TecnicoGestionComponentesUI(administrador);
				tecnicoGestionComponentesUI.setVisible(true);
			}
		});
		
		btnGestionProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				AdministradorGestionProveedoresUI administradorGestionProveedoresUI = new AdministradorGestionProveedoresUI(administrador);
				administradorGestionProveedoresUI.setVisible(true);
			}
		});
		
	}
	
	
	

}


