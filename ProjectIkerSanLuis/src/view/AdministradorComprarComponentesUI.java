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
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import db.GestorDatos;
import model.Administrador;
import model.AlmacenProveedores;
import model.Centro;
import model.GestorTablas;
import model.Peticion;
import model.Proveedor;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class AdministradorComprarComponentesUI extends JFrame{

	private Administrador administrador;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	
	private DefaultTableModel modeloComponentes;
	private JScrollPane tablaComponentes;
	private JTable jtableComponentes;
	private Vector vColumnasComponentes;
	private Vector vContenidoComponentes;
	private String valorComponente;
	private JLabel lblSaludo;
	private JLabel lblCentro;
	private JTextPane txtConcepto;
	private JButton btnSolicitarCompra;
	private JButton btnVolver;
	private JComboBox cboxCentro;
	private JButton btnBuscar;
	private JLabel lblConcepto;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JButton btnVerListadoDe;
	
	public AdministradorComprarComponentesUI (Administrador administrador) {
		this.administrador = administrador;		
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
					
		setBounds(100, 100, 805, 597);		
		
		tablaComponentes = new JScrollPane();
		tablaComponentes.setBounds(34, 111, 716, 253);
		contentPane.add(tablaComponentes);	
		
		// Enseñar componentes
		try {					
			//Datos de la tabla componentes 

			
			vContenidoComponentes = new Vector<>();			
			vColumnasComponentes = gestorTablas.obtenerCabecerasAlmacen();
			contentPane.setLayout(null);
					
			jtableComponentes = new JTable(vContenidoComponentes,vColumnasComponentes);
			tablaComponentes.setViewportView(jtableComponentes);
			
			modeloComponentes = (DefaultTableModel) jtableComponentes.getModel();
			
			jtableComponentes.addMouseListener((MouseListener) new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					
			        if (jtableComponentes.getSelectedRow() != -1) {
			        	valorComponente = (String) modeloComponentes.getValueAt(jtableComponentes.getSelectedRow(), 0);					            
			        	btnSolicitarCompra.setEnabled(true);
			        	txtConcepto.setEditable(true);
			        	txtCantidad.setEnabled(true);
			        } 
					
				}
			});					
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 				
		
		lblSaludo = new JLabel("COMPONENTES DE LOS PROVEEDORES");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(34, 38, 713, 16);
		contentPane.add(lblSaludo);
		
		lblCentro = new JLabel("Centro : ");
		lblCentro.setBounds(34, 67, 76, 16);
		contentPane.add(lblCentro);
		
		txtConcepto = new JTextPane();
		txtConcepto.setEditable(false);
		txtConcepto.setBounds(34, 395, 420, 137);
		contentPane.add(txtConcepto);
		
		
		btnSolicitarCompra = new JButton("");
		if (administrador.isPermisos()) {
			btnSolicitarCompra.setText("Comprar componentes");
		}else {
			btnSolicitarCompra.setText("Solicitar compra");
		}
		btnSolicitarCompra.setEnabled(false);
		btnSolicitarCompra.setBounds(509, 479, 241, 25);
		contentPane.add(btnSolicitarCompra);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(509, 507, 241, 25);
		contentPane.add(btnVolver);
		
		Vector<Centro> centros = GestorDatos.getInstance().getCentros();
		cboxCentro = new JComboBox(centros);
		cboxCentro.setSelectedIndex(-1);
		cboxCentro.setBounds(122, 67, 389, 22);
		contentPane.add(cboxCentro);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(569, 67, 181, 25);
		contentPane.add(btnBuscar);
		
		lblConcepto = new JLabel("Concepto :");
		lblConcepto.setBounds(34, 377, 446, 16);
		contentPane.add(lblConcepto);
		
		lblCantidad = new JLabel("Cantidad :");
		lblCantidad.setBounds(509, 377, 241, 16);
		contentPane.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setEnabled(false);
		txtCantidad.setBounds(509, 406, 241, 22);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		btnVerListadoDe = new JButton("Ver listado de peticiones");
		btnVerListadoDe.setBounds(509, 451, 241, 25);
		contentPane.add(btnVerListadoDe);
		
		if (!administrador.isPermisos()) {
			btnVerListadoDe.setEnabled(false);
			btnVerListadoDe.setVisible(false);
		}

		cboxCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBuscar.setEnabled(true);
			}});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vContenidoComponentes = gestorTablas.obtenerCuerpoAlmacen((Centro) cboxCentro.getSelectedItem());
				modeloComponentes.setDataVector(vContenidoComponentes, vColumnasComponentes);
				modeloComponentes.fireTableDataChanged();
			}});
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorGestionProveedoresUI administradorGestionProveedoresUI = new AdministradorGestionProveedoresUI(administrador);
				administradorGestionProveedoresUI.setVisible(true);			
			}});
		
		btnSolicitarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlmacenProveedores componenteAlmacen = GestorDatos.getInstance().getComponenteAlmacen(Integer.parseInt(valorComponente));
				
					try {
						if (!txtConcepto.getText().equals("")) {
							if (Integer.parseInt(txtCantidad.getText()) <= componenteAlmacen.getCantidad()) {
								
								if (administrador.isPermisos()) { // Tiene permisos de compra
									Peticion peticion = GestorDatos.getInstance().generarPeticion(txtConcepto.getText(), componenteAlmacen, administrador, administrador, Integer.parseInt(txtCantidad.getText()), (Centro) cboxCentro.getSelectedItem());
									peticion.setEstado("c");
									GestorDatos.getInstance().actualizarPeticion(peticion, peticion.getIdPeticion());
									
									// TODO Notificar proveedor via email
									
									JOptionPane.showMessageDialog(null, "Se ha generado la solicitud de compra al proveedor "+componenteAlmacen.getLote().getProveedor().getUser());
									txtConcepto.setText("");
									txtConcepto.setEditable(false);
									txtCantidad.setText("");
									txtCantidad.setEnabled(false);
									btnSolicitarCompra.setEnabled(false);
									
								}else { // No tiene permisos de compra
									Administrador administradorAsignado = GestorDatos.getInstance().getAdministradorMenosOcupadoConPermisos((Centro) cboxCentro.getSelectedItem());
									Peticion peticion = GestorDatos.getInstance().generarPeticion(txtConcepto.getText(), componenteAlmacen, administrador, administradorAsignado, Integer.parseInt(txtCantidad.getText()), (Centro) cboxCentro.getSelectedItem());
									// TODO Notificar via email
									
									JOptionPane.showMessageDialog(null, "Se ha generado la peticion "+peticion.getIdPeticion()+", y se ha asignado al administrador con permisos "+administradorAsignado.getUser());
									txtConcepto.setText("");
									txtConcepto.setEditable(false);
									txtCantidad.setText("");
									txtCantidad.setEnabled(false);
									btnSolicitarCompra.setEnabled(false);
								}
							}else {
								JOptionPane.showMessageDialog(null, "No puedes pedir mas componentes de los disponibles");
							}
							
						}else {
							JOptionPane.showMessageDialog(null, "Tienes que añadir texto al concepto");
						}
					}catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "La cantidad no es numerica");
					}

			}});	
		
			btnVerListadoDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					AdministradorAtenderPeticionesUI administradorAtenderPeticionesUI = new AdministradorAtenderPeticionesUI(administrador);
					administradorAtenderPeticionesUI.setVisible(true);
				}});
			
	}
}
