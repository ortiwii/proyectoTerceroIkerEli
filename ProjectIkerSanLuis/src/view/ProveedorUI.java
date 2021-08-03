package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Proveedor;

public class ProveedorUI extends JFrame{
	
	private Proveedor proveedor;
	private JButton btnGestionCatalogos;
	private JLabel lblBienvenido;
	private JButton btnModificarDatos;
	private JButton btnAulas;
	private JButton btnGestionAlmacen;
	private JButton btnListadoDeComponentes;
	private JButton btnListadoDeIncidencias;
	
	public ProveedorUI (Proveedor proveedor) {
		this.proveedor = proveedor;
		
		setBounds(100, 100, 571, 465);
		
		JButton btnGestionVentas = new JButton("GESTION DE VENTAS");
		btnGestionVentas.setBounds(30, 202, 324, 25);		
		
		btnModificarDatos = new JButton("MODIFICAR DATOS");
		btnModificarDatos.setBounds(30, 126, 324, 25);
		getContentPane().setLayout(null);
		
		lblBienvenido = new JLabel();
		lblBienvenido.setBounds(30, 71, 324, 16);
		getContentPane().add(lblBienvenido);
		getContentPane().add(btnModificarDatos);
		
		
		btnGestionCatalogos = new JButton("GESTION DE CATALOGOS");
		btnGestionCatalogos.setBounds(30, 164, 324, 25);
		getContentPane().add(btnGestionCatalogos);
		getContentPane().add(btnGestionVentas);
		
		btnGestionAlmacen = new JButton("GESTION DEL ALMACEN");
		btnGestionAlmacen.setBounds(30, 240, 324, 25);
		getContentPane().add(btnGestionAlmacen);
		
		btnAulas = new JButton("AULAS");
		btnAulas.setBounds(30, 316, 324, 25);
		getContentPane().add(btnAulas);
		
		btnListadoDeComponentes = new JButton("LISTADO DE COMPONENTES");
		btnListadoDeComponentes.setBounds(30, 354, 324, 25);
		getContentPane().add(btnListadoDeComponentes);
		
		btnListadoDeIncidencias = new JButton("LISTADO DE TUS INCIDENCIAS");
		btnListadoDeIncidencias.setBounds(30, 278, 324, 25);
		getContentPane().add(btnListadoDeIncidencias);
		
		lblBienvenido.setText("Hola " + proveedor.getUser() );
		
		btnGestionVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProveedorGestionVentasUI proveedorGestionVentasUI = new ProveedorGestionVentasUI(proveedor);
				proveedorGestionVentasUI.setVisible(true);
				
				
			}
		});
		
		btnModificarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProveedorModificarDatosUI proveedorModificarDatosUI = new ProveedorModificarDatosUI(proveedor);
				proveedorModificarDatosUI.setVisible(true);
			}
		});
		
		
		btnGestionCatalogos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				UsuarioEnviarIncidenciaUI usuarioEnviarIncidenciaUI = new UsuarioEnviarIncidenciaUI(usuario);
//				usuarioEnviarIncidenciaUI.setVisible(true);
				
					
				
			}
		});
		btnGestionAlmacen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ProveedorGestionAlmacenUI proveedorGestionAlmacenUI = new ProveedorGestionAlmacenUI(proveedor);
				proveedorGestionAlmacenUI.setVisible(true);
			}
		});
		
		btnListadoDeIncidencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				UsuarioMostrarIncidenciasUI usuarioMostrarIncidenciasUI = new UsuarioMostrarIncidenciasUI(usuario);
//				usuarioMostrarIncidenciasUI.setVisible(true);
			}});
		
		btnAulas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				UsuarioMostrarAulasUI usuarioMostrarAulasUI = new UsuarioMostrarAulasUI(usuario);
//				usuarioMostrarAulasUI.setVisible(true);
			}
		});
		
		btnListadoDeComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				UsuarioVerComponentesUI usuarioVerComponentesUI = new UsuarioVerComponentesUI(usuario.getCentro());
//				usuarioVerComponentesUI.setVisible(true);
			}
		});
	}


}
