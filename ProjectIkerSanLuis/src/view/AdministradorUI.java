package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.Administrador;
import javax.swing.JLabel;
import java.awt.Font;

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
	private JLabel lblPanelDeAdministrador;
	
	public AdministradorUI (Administrador administrador) {
		this.administrador = administrador;
		initialize();
	}
	private void initialize() {
		
		setBounds(100, 100, 626, 343);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnGestionarEquipos = new JButton("GESTIONAR EQUIPOS");
		btnGestionarEquipos.setBounds(30, 241, 192, 25);
		
		btnEnseñarDatos = new JButton("MODIFICAR DATOS");
		btnEnseñarDatos.setBounds(30, 105, 192, 25);
		getContentPane().setLayout(null);
		
		lblBienvenido = new JLabel();
		lblBienvenido.setBounds(30, 56, 324, 16);
		getContentPane().add(lblBienvenido);
		getContentPane().add(btnEnseñarDatos);
		
		
		btnGestionarAulas = new JButton("GESTIONAR AULAS");
		btnGestionarAulas.setBounds(30, 203, 192, 25);
		getContentPane().add(btnGestionarAulas);
		getContentPane().add(btnGestionarEquipos);
		
		btnPanelSolicitudes = new JButton("PANEL DE SOLICITUDES");
		btnPanelSolicitudes.setBounds(315, 105, 209, 25);
		getContentPane().add(btnPanelSolicitudes);
		
		btnGestionComponentes = new JButton("GESTION DE COMPONENTES");
		btnGestionComponentes.setBounds(315, 203, 265, 25);
		getContentPane().add(btnGestionComponentes);
		
		btnGestionProveedores = new JButton("GESTION DE PROVEEDORES y COMPRAS");
		btnGestionProveedores.setBounds(315, 241, 265, 25);
		getContentPane().add(btnGestionProveedores);
		
		btnPanelIncidencias = new JButton("PANEL DE INCIDENCIAS");
		btnPanelIncidencias.setBounds(315, 143, 209, 25);
		getContentPane().add(btnPanelIncidencias);
		
		btnPanelDeUsuarios = new JButton("PANEL DE USUARIOS");
		btnPanelDeUsuarios.setBounds(30, 143, 192, 25);
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
		
		
		lblPanelDeAdministrador = new JLabel("PANEL DE ADMINISTRADOR");
		lblPanelDeAdministrador.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPanelDeAdministrador.setBounds(30, 27, 584, 16);
		getContentPane().add(lblPanelDeAdministrador);
	}
	
	
	

}


