package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Proveedor;
import java.awt.Font;

public class ProveedorUI extends JFrame{
	
	private Proveedor proveedor;
	private JButton btnGestionCatalogos;
	private JLabel lblBienvenido;
	private JButton btnModificarDatos;
	private JButton btnGestionAlmacen;
	private JLabel lblPanelProveedores;
	private JLabel lblPanelProveedores_1;
	
	public ProveedorUI (Proveedor proveedor) {
		this.proveedor = proveedor;
		

		initialize();
	}
	private void initialize() {
		
		setBounds(100, 100, 571, 231);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnGestionVentas = new JButton("GESTION DE VENTAS");
		btnGestionVentas.setBounds(30, 137, 237, 25);		
		
		btnModificarDatos = new JButton("MODIFICAR DATOS");
		btnModificarDatos.setBounds(284, 99, 237, 25);
		getContentPane().setLayout(null);
		
		lblBienvenido = new JLabel();
		lblBienvenido.setBounds(30, 65, 324, 16);
		getContentPane().add(lblBienvenido);
		getContentPane().add(btnModificarDatos);
		
		
		btnGestionCatalogos = new JButton("GESTION DE CATALOGOS");
		btnGestionCatalogos.setBounds(30, 99, 237, 25);
		getContentPane().add(btnGestionCatalogos);
		getContentPane().add(btnGestionVentas);
		
		btnGestionAlmacen = new JButton("GESTION DEL ALMACEN");
		btnGestionAlmacen.setBounds(284, 137, 237, 25);
		getContentPane().add(btnGestionAlmacen);
		
		lblBienvenido.setText("Hola " + proveedor.getUser() );
		
		lblPanelProveedores_1 = new JLabel("PANEL PROVEEDORES");
		lblPanelProveedores_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPanelProveedores_1.setBounds(30, 35, 491, 16);
		getContentPane().add(lblPanelProveedores_1);
		
		btnGestionCatalogos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				UsuarioEnviarIncidenciaUI usuarioEnviarIncidenciaUI = new UsuarioEnviarIncidenciaUI(usuario);
//				usuarioEnviarIncidenciaUI.setVisible(true);				
			}
		});
		
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
		btnGestionAlmacen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ProveedorGestionAlmacenUI proveedorGestionAlmacenUI = new ProveedorGestionAlmacenUI(proveedor);
				proveedorGestionAlmacenUI.setVisible(true);
			}
		});
		
	}


}
