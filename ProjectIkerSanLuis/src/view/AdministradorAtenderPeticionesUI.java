package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import db.GestorDatos;
import model.Administrador;
import model.AlmacenProveedores;
import model.GestorTablas;
import model.Peticion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;

public class AdministradorAtenderPeticionesUI extends JFrame{

	private Administrador administrador;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	
	private DefaultTableModel modelo;
	private JScrollPane tabla;
	private JTable jtable;
	private Vector vColumnas;
	private Vector vContenido;
	private String valor;
	private JLabel lblAtenderPeticionesDe;
	private JLabel lblAdministrador;
	private JButton btnConfirmarCompra;
	private JButton btnDenegarCompra;
	private JButton btnVolver;
	
	public AdministradorAtenderPeticionesUI (Administrador administrador) {
		this.administrador = administrador;
			
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
					
		setBounds(100, 100, 805, 469);		
		
		tabla = new JScrollPane();
		tabla.setBounds(34, 111, 716, 253);
		contentPane.add(tabla);	
		
		// Enseñar componentes
		try {					
			//Datos de la tabla componentes 

			vContenido = gestorTablas.obtenerCuerpoPeticionesAdminNoAtendidas(administrador);
			vColumnas = gestorTablas.obtenerCabecerasPeticiones();
			contentPane.setLayout(null);
					
			jtable = new JTable(vContenido, vColumnas);
			tabla.setViewportView(jtable);
			
			modelo = (DefaultTableModel) jtable.getModel();
			
			jtable.addMouseListener((MouseListener) new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					
			        if (jtable.getSelectedRow() != -1) {
			        	valor = (String) modelo.getValueAt(jtable.getSelectedRow(), 0);					            
			        	btnConfirmarCompra.setEnabled(true);
			        	btnDenegarCompra.setEnabled(true);
			        } 
					
				}
			});					
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 			
		
		lblAtenderPeticionesDe = new JLabel("ATENDER PETICIONES DE COMPRA");
		lblAtenderPeticionesDe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAtenderPeticionesDe.setBounds(34, 33, 716, 16);
		contentPane.add(lblAtenderPeticionesDe);
		
		lblAdministrador = new JLabel("Administrador : "+administrador.getUser());
		lblAdministrador.setBounds(34, 68, 716, 16);
		contentPane.add(lblAdministrador);
		
		btnConfirmarCompra = new JButton("Confirmar Compra");
		btnConfirmarCompra.setEnabled(false);
		btnConfirmarCompra.setBounds(33, 377, 233, 25);
		contentPane.add(btnConfirmarCompra);
		
		btnDenegarCompra = new JButton("Denegar Compra");
		btnDenegarCompra.setEnabled(false);
		btnDenegarCompra.setBounds(278, 377, 232, 25);
		contentPane.add(btnDenegarCompra);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(653, 377, 97, 25);
		contentPane.add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorComprarComponentesUI administradorComprarComponentesUI = new AdministradorComprarComponentesUI(administrador);
				administradorComprarComponentesUI.setVisible(true);
			}});
		
		btnConfirmarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Peticion peticion = GestorDatos.getInstance().getPeticion(Integer.parseInt(valor));				
				// 0 = si,1 = no 
				int eleccion = JOptionPane.showConfirmDialog(null, "Seguro que quiere comprar "+peticion.getCantidad()+" de el componente "+peticion.getComonenteAlmacen().getIdComponente()+" que tiene el proveedor en el almacen?", "Confirmar compra", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (eleccion == 0) {
					peticion.setEstado("c");
					boolean flag = GestorDatos.getInstance().actualizarPeticion(peticion, peticion.getIdPeticion());
					if (flag) {
						JOptionPane.showMessageDialog(null, "Se ha confirmado la compra de la peticion "+peticion);
						// TODO NOTIFICAR PROVEEDOR POR EMAIL
					}else {
						JOptionPane.showMessageDialog(null, "No se ha podido confirmar la compra de la peticion "+peticion);
					}
					actualizar();
				}
			}});
		
		btnDenegarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Peticion peticion = GestorDatos.getInstance().getPeticion(Integer.parseInt(valor));				
				// 0 = si,1 = no 
				int eleccion = JOptionPane.showConfirmDialog(null, "Seguro que quiere rechazar la compra de "+peticion.getCantidad()+" unidades de el componente "+peticion.getComonenteAlmacen().getIdComponente()+" que tiene el proveedor en el almacen?", "Rechazar compra", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (eleccion == 0) {
					peticion.setEstado("r");
					boolean flag = GestorDatos.getInstance().actualizarPeticion(peticion, peticion.getIdPeticion());
					if (flag) {
						JOptionPane.showMessageDialog(null, "Se ha rechazado la compra de la peticion "+peticion);
					}else {
						JOptionPane.showMessageDialog(null, "No se ha podido rechazar la compra de la peticion "+peticion);
					}
					actualizar();
				}
			}});			
	}
	private void actualizar() {
		vContenido = gestorTablas.obtenerCuerpoPeticionesAdminNoAtendidas(administrador);
		modelo.setDataVector(vContenido, vColumnas);
		modelo.fireTableDataChanged();
	}
}
