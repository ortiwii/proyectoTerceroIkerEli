package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import db.GestorDatos;
import model.GestorTablas;
import model.Peticion;
import model.Proveedor;
import model.Venta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class ProveedorGestionVentasUI extends JFrame{

	private Proveedor proveedor;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	
	private DefaultTableModel modelo;
	private JScrollPane tabla;
	private JTable jtable;
	private Vector vColumnas;
	private Vector vContenido;
	private String valor;
	private JLabel lblGestionDeVentas;
	private JLabel lblProveedor;
	private JButton btnAceptarPeticion;
	private JButton btnRechazarPeticion;
	private JButton btnVolver;
	private JLabel lblInfo;
	private JButton btnTrack;
	private JTextPane txtInfo;
	private JLabel lblInformacionRespectoA;
	
	public ProveedorGestionVentasUI (Proveedor proveedor) {
		this.proveedor = proveedor;
		
		
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
					
		setBounds(100, 100, 800, 493);				
		
		tabla = new JScrollPane();
		tabla.setBounds(41, 105, 716, 161);
		contentPane.add(tabla);		
		
		try {					
			
			vContenido = gestorTablas.obtenerCuerpoPeticionesProveedor(proveedor);
			vColumnas = gestorTablas.obtenerCabecerasPeticiones();
			contentPane.setLayout(null);
			
			jtable = new JTable(vContenido, vColumnas);
			tabla.setViewportView(jtable);
			
			modelo = (DefaultTableModel) jtable.getModel();
			
			jtable.addMouseListener((MouseListener) new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
			
					if (jtable.getSelectedRow() != -1) {
						valor = (String) modelo.getValueAt(jtable.getSelectedRow(), 0);					            
						btnAceptarPeticion.setEnabled(true);
						btnRechazarPeticion.setEnabled(true);
						txtInfo.setEnabled(true);
					} 			
				}});					
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
		lblGestionDeVentas = new JLabel("GESTION DE VENTAS");
		lblGestionDeVentas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGestionDeVentas.setBounds(41, 29, 716, 16);
		contentPane.add(lblGestionDeVentas);
		
		lblProveedor = new JLabel("Proveedor : "+proveedor.getUser());
		lblProveedor.setBounds(41, 76, 716, 16);
		contentPane.add(lblProveedor);
		
		btnAceptarPeticion = new JButton("Aceptar Peticion");
		btnAceptarPeticion.setEnabled(false);
		btnAceptarPeticion.setBounds(242, 403, 169, 25);
		contentPane.add(btnAceptarPeticion);
		
		btnRechazarPeticion = new JButton("Rechazar Peticion");
		btnRechazarPeticion.setEnabled(false);
		btnRechazarPeticion.setBounds(41, 403, 173, 25);
		contentPane.add(btnRechazarPeticion);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(660, 403, 97, 25);
		contentPane.add(btnVolver);
		
		lblInfo = new JLabel("Estos son las peticiones que tienes sin aceptar");
		lblInfo.setBounds(41, 58, 716, 16);
		contentPane.add(lblInfo);
		
		txtInfo = new JTextPane();
		txtInfo.setEnabled(false);
		txtInfo.setBounds(41, 310, 365, 75);
		contentPane.add(txtInfo);
		
		lblInformacionRespectoA = new JLabel("Informacion respecto a la peticion :");
		lblInformacionRespectoA.setBounds(41, 283, 365, 16);
		contentPane.add(lblInformacionRespectoA);
		
		btnTrack = new JButton("Track de los pedidos aceptados");
		btnTrack.setBounds(534, 72, 223, 25);
		contentPane.add(btnTrack);				
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});

		btnAceptarPeticion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Peticion peticion = GestorDatos.getInstance().getPeticion(Integer.parseInt(valor));
				// 0 = si,1 = no 
				int eleccion = JOptionPane.showConfirmDialog(null, "Seguro que quieres aceptar la peticion "+peticion.getIdPeticion()+"?", "Aceptar peticion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (eleccion == 0) {
					peticion.setEstado("v");
					GestorDatos.getInstance().actualizarPeticion(peticion, peticion.getIdPeticion());
					Venta venta = GestorDatos.getInstance().generarVenta(proveedor, Calendar.getInstance(), peticion, txtInfo.getText());
					if (venta != null) {
						JOptionPane.showMessageDialog(null, "Se ha aceptado la peticion "+peticion.getIdPeticion());
					}else {						
						JOptionPane.showMessageDialog(null, "No se ha podido aceptar la peticion "+peticion.getIdPeticion());
					}
					actualizar();
					
				}else {
					
				}
			}});
		
		btnRechazarPeticion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Peticion peticion = GestorDatos.getInstance().getPeticion(Integer.parseInt(valor));
				// 0 = si,1 = no 
				int eleccion = JOptionPane.showConfirmDialog(null, "Seguro que quieres rechazar la peticion "+peticion.getIdPeticion()+"?", "Rechazar peticion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (eleccion == 0) {
					peticion.setEstado("r"); // rechazado
					boolean flag = GestorDatos.getInstance().actualizarPeticion(peticion, peticion.getIdPeticion());
					if (flag) {
						JOptionPane.showMessageDialog(null, "Se ha rechazado la peticion "+peticion.getIdPeticion());
						
					}else {
						JOptionPane.showMessageDialog(null, "No se ha podido rechazar la peticion "+peticion.getIdPeticion());
					}
					actualizar();
				}
			}});
		
		btnTrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProveedorTrackPedidosUI proveedorTrackPedidosUI = new ProveedorTrackPedidosUI(proveedor);
				proveedorTrackPedidosUI.setVisible(true);				
			}});
		
	}
	private void actualizar() {
		vContenido = gestorTablas.obtenerCuerpoPeticionesProveedor(proveedor);
		modelo.setDataVector(vContenido, vColumnas);
		modelo.fireTableDataChanged();
		btnAceptarPeticion.setEnabled(false);
		btnRechazarPeticion.setEnabled(false);
		txtInfo.setEnabled(false);
		txtInfo.setText("");
	}
}
