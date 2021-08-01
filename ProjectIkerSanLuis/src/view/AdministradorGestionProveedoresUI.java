package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import db.GestorDatos;
import model.Administrador;
import model.GestorTablas;

public class AdministradorGestionProveedoresUI extends JFrame{
	
	private Administrador administrador;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	
	private DefaultTableModel modeloProveedores;
	private JScrollPane tablaProveedores;
	private JTable jtableProveedores;
	private Vector vColumnasProveedores;
	private Vector vContenidoProveedores;
	private String valorProveedores;
	private JButton btnGenerarPdf;
	private JLabel lblGestionDeProveedores;
	private JButton btnVolver;
	private JButton btnSolicitarCompraDe;
	private JButton btnModificarProveedor;
	private JButton btnCrearProveedor;
	
	public AdministradorGestionProveedoresUI (Administrador administrador) {
		this.administrador = administrador;
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
					
		setBounds(100, 100, 838, 417);				
		
		tablaProveedores = new JScrollPane();
		tablaProveedores.setBounds(41, 70, 716, 161);
		contentPane.add(tablaProveedores);		
		
		// Enseñar componentes
		try {					
			//Datos de la tabla componentes Almacen
			
			vContenidoProveedores = gestorTablas.obtenerCuerpoProveedoresPublico();
			vColumnasProveedores = gestorTablas.obtenerCabecerasProveedoresPublico();
			contentPane.setLayout(null);
			
			jtableProveedores = new JTable(vContenidoProveedores, vColumnasProveedores);
			tablaProveedores.setViewportView(jtableProveedores);
			
			modeloProveedores = (DefaultTableModel) jtableProveedores.getModel();
			
			jtableProveedores.addMouseListener((MouseListener) new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			
			        if (jtableProveedores.getSelectedRow() != -1) {
			        	valorProveedores = (String) modeloProveedores.getValueAt(jtableProveedores.getSelectedRow(), 0);					            
			        	btnGenerarPdf.setEnabled(true);
			        	btnModificarProveedor.setEnabled(true);
			        } 
			
		}
			});					
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		btnGenerarPdf = new JButton("GENERAR PDF ");
		btnGenerarPdf.setEnabled(false);
		btnGenerarPdf.setBounds(567, 244, 159, 25);
		contentPane.add(btnGenerarPdf);
		
		lblGestionDeProveedores = new JLabel("GESTION DE PROVEEDORES ");
		lblGestionDeProveedores.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGestionDeProveedores.setBounds(41, 30, 716, 16);
		contentPane.add(lblGestionDeProveedores);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(68, 320, 97, 25);
		contentPane.add(btnVolver);
		
		btnSolicitarCompraDe = new JButton("Solicitar compra de algun componente");
		btnSolicitarCompraDe.setBounds(68, 282, 307, 25);
		contentPane.add(btnSolicitarCompraDe);
		
		btnModificarProveedor = new JButton("Modificar Proveedor");
		btnModificarProveedor.setEnabled(false);
		btnModificarProveedor.setBounds(567, 282, 159, 25);
		contentPane.add(btnModificarProveedor);
		
		btnCrearProveedor = new JButton("Crear Proveedor");
		btnCrearProveedor.setBounds(68, 244, 159, 25);
		contentPane.add(btnCrearProveedor);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		btnGenerarPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				JOptionPane.showMessageDialog(null, "Se ha generado el catalogo de el proveedor "+"<dynamic>");
			}});
		
		btnSolicitarCompraDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorComprarComponentesUI administradorComprarComponentesUI = new AdministradorComprarComponentesUI(administrador);
				administradorComprarComponentesUI.setVisible(true);				
			}});	
		
		btnModificarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				
				AdministradorModificarProveedorUI administradorModificarProveedorUI = new AdministradorModificarProveedorUI(administrador, GestorDatos.getInstance().getProveedor(Integer.parseInt(valorProveedores)));
				administradorModificarProveedorUI.setVisible(true);
			}});
		
		btnCrearProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorCrearProveedorUI administradorCrearProveedorUI = new AdministradorCrearProveedorUI(administrador);
				administradorCrearProveedorUI.setVisible(true);
			}});
	}

}
