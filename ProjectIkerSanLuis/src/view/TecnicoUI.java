package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Tecnico;
import model.Usuario;

public class TecnicoUI extends JFrame{
	
	Tecnico tecnico;
	private JButton btnEnseñarDatos;
	private JLabel lblBienvenido;
	private JButton btnGestionProveedores;
	private JButton btnPanelIncidencias;
	private JButton btnGestionarAulas;
	private JButton btnPanelSolicitudes;
	private JButton btnGestionComponentes;
	
	
	public TecnicoUI (Tecnico tecnico) {
		
		this.tecnico = tecnico;									
		
		setBounds(100, 100, 571, 465);
		
		JButton btnGestionarEquipos = new JButton("GESTIONAR EQUIPOS");
		btnGestionarEquipos.setBounds(30, 215, 324, 25);
		btnGestionarEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoGestionarEquiposUI tecnicoGestionarEquiposUI = new TecnicoGestionarEquiposUI(tecnico);
				tecnicoGestionarEquiposUI.setVisible(true);				
			}
		});
		
		
		btnEnseñarDatos = new JButton("MODIFICAR DATOS");
		btnEnseñarDatos.setBounds(30, 155, 324, 25);
		getContentPane().setLayout(null);
		
		lblBienvenido = new JLabel();
		lblBienvenido.setBounds(30, 134, 324, 16);
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
		
		lblBienvenido.setText("Hola " + tecnico.getUser() );
		
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

