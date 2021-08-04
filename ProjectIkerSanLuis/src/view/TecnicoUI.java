package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Tecnico;
import model.Usuario;
import java.awt.Font;

public class TecnicoUI extends JFrame{
	
	Tecnico tecnico;
	private JButton btnEnseñarDatos;
	private JLabel lblBienvenido;
	private JButton btnGestionProveedores;
	private JButton btnPanelIncidencias;
	private JButton btnGestionarAulas;
	private JButton btnPanelSolicitudes;
	private JButton btnGestionComponentes;
	private JLabel lblPanelDeUsuario;
	private JButton btnGestionarEquipos;
	
	
	public TecnicoUI (Tecnico tecnico) {
		
		this.tecnico = tecnico;									
	
		initialize();
	}
	private void initialize() {
		
		setBounds(100, 100, 571, 317);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnGestionarEquipos = new JButton("GESTIONAR EQUIPOS");
		btnGestionarEquipos.setBounds(30, 151, 222, 25);
		
		
		btnEnseñarDatos = new JButton("MODIFICAR DATOS");
		btnEnseñarDatos.setBounds(288, 51, 222, 25);
		getContentPane().setLayout(null);
		
		lblBienvenido = new JLabel();
		lblBienvenido.setBounds(30, 55, 324, 16);
		getContentPane().add(lblBienvenido);
		getContentPane().add(btnEnseñarDatos);
		
		
		btnGestionarAulas = new JButton("GESTIONAR AULAS");
		btnGestionarAulas.setBounds(30, 113, 222, 25);
		getContentPane().add(btnGestionarAulas);
		getContentPane().add(btnGestionarEquipos);
		
		btnPanelSolicitudes = new JButton("PANEL DE SOLICITUDES");
		btnPanelSolicitudes.setBounds(288, 113, 222, 25);
		getContentPane().add(btnPanelSolicitudes);
		
		btnGestionComponentes = new JButton("GESTION DE COMPONENTES");
		btnGestionComponentes.setBounds(137, 189, 269, 25);
		getContentPane().add(btnGestionComponentes);
		
		btnGestionProveedores = new JButton("GESTION DE PROVEEDORES y COMPRAS");
		btnGestionProveedores.setBounds(137, 227, 269, 25);
		getContentPane().add(btnGestionProveedores);
		
		btnPanelIncidencias = new JButton("PANEL DE INCIDENCIAS");
		btnPanelIncidencias.setBounds(288, 151, 222, 25);
		getContentPane().add(btnPanelIncidencias);
		
		lblPanelDeUsuario = new JLabel("PANEL DE USUARIO");
		lblPanelDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPanelDeUsuario.setBounds(30, 26, 495, 16);
		getContentPane().add(lblPanelDeUsuario);
		
		lblBienvenido.setText("Hola " + tecnico.getUser() );
		
		btnGestionarEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoGestionarEquiposUI tecnicoGestionarEquiposUI = new TecnicoGestionarEquiposUI(tecnico);
				tecnicoGestionarEquiposUI.setVisible(true);				
			}
		});
				
		
		btnEnseñarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioModificarDatosUI usuarioModificarDatosUI = new UsuarioModificarDatosUI(tecnico);
				usuarioModificarDatosUI.setVisible(true);				
			}
		});
		
		
		btnGestionarAulas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TecnicoGestionarAulasUI tecnicoGestionarAulasUI = new TecnicoGestionarAulasUI(tecnico);
				tecnicoGestionarAulasUI.setVisible(true);
				
			}
		});
		btnPanelSolicitudes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoPanelSolicitudesUI tecnicoPanelSolicitudesUI = new TecnicoPanelSolicitudesUI(tecnico);
				tecnicoPanelSolicitudesUI.setVisible(true);
			}
		});
		
		btnPanelIncidencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoPanelIncidenciasUI tecnicoPanelIncidenciasUI = new TecnicoPanelIncidenciasUI(tecnico);
				tecnicoPanelIncidenciasUI.setVisible(true);
			}});
		
		btnGestionComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoGestionComponentesUI tecnicoGestionComponentesUI = new TecnicoGestionComponentesUI(tecnico);
				tecnicoGestionComponentesUI.setVisible(true);
			}
		});
		
		btnGestionProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				TecnicoGestionProveedoresUI tecnicoGestionProveedoresUI = new TecnicoGestionProveedoresUI(tecnico);
				tecnicoGestionProveedoresUI.setVisible(true);
			}
		});
	}
}

