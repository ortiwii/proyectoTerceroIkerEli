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
import model.AlmacenProveedores;
import model.GestorTablas;
import model.Lote;
import model.Proveedor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ProveedorGestionAlmacenUI extends JFrame {
	
	private Proveedor proveedor;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	
	private DefaultTableModel modeloComponentes;
	private JScrollPane tablaComponentes;
	private JTable jtableComponentes;
	private Vector vColumnasComponentes;
	private Vector vContenidoComponentes;
	private String valorComponente;
	private JLabel lblSaludo;
	private JLabel lblProveedor;
	private JButton btnVolver;
	private JButton btnAadirNuevoComponente;
	private JButton btnModificarComponente;
	private JLabel lblFiltrarPor;
	private String[] listaOpciones = {"idComponenteAlmacen", "nombre", "lote", "clase"}; 
	private JComboBox cbox;
	private JTextField txtFiltro;
	private JButton btnBuscar;
	private JButton btnCrearLote;
	public ProveedorGestionAlmacenUI (Proveedor proveedor) {
		this.proveedor = proveedor;
		
		

		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
					
		setBounds(100, 100, 805, 464);		
		
		tablaComponentes = new JScrollPane();
		tablaComponentes.setBounds(35, 95, 716, 253);
		contentPane.add(tablaComponentes);	
		
		// Ense�ar componentes
		try {					
			//Datos de la tabla componentes 

			vContenidoComponentes = gestorTablas.obtenerCuerpoAlmacen(proveedor, "nombre", "");
			vColumnasComponentes = gestorTablas.obtenerCabecerasAlmacen();
			contentPane.setLayout(null);
					
			jtableComponentes = new JTable(vContenidoComponentes,vColumnasComponentes);
			tablaComponentes.setViewportView(jtableComponentes);
			
			modeloComponentes = (DefaultTableModel) jtableComponentes.getModel();
			
			jtableComponentes.addMouseListener((MouseListener) new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					
			        if (jtableComponentes.getSelectedRow() != -1) {
			        	valorComponente = (String) modeloComponentes.getValueAt(jtableComponentes.getSelectedRow(), 0);					            
			        	btnModificarComponente.setEnabled(true);
			        } 
					
				}
			});					
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 		
		
		lblSaludo = new JLabel("GESTION DEL ALMACEN");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(35, 31, 709, 16);
		contentPane.add(lblSaludo);
		
		lblProveedor = new JLabel("Proveedor : "+proveedor.getUser());
		lblProveedor.setBounds(45, 66, 298, 16);
		contentPane.add(lblProveedor);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(653, 361, 97, 25);
		contentPane.add(btnVolver);
		
		btnAadirNuevoComponente = new JButton("A\u00F1adir nuevo componente");
		btnAadirNuevoComponente.setBounds(35, 361, 189, 25);
		contentPane.add(btnAadirNuevoComponente);
		
		btnModificarComponente = new JButton("Modificar componente");
		btnModificarComponente.setEnabled(false);
		btnModificarComponente.setBounds(236, 361, 167, 25);
		contentPane.add(btnModificarComponente);
		
		lblFiltrarPor = new JLabel("Filtrar por :");
		lblFiltrarPor.setBounds(361, 32, 88, 16);
		contentPane.add(lblFiltrarPor);				
		
		cbox = new JComboBox(listaOpciones);
		cbox.setBounds(305, 60, 158, 22);
		contentPane.add(cbox);
		
		txtFiltro = new JTextField();
		txtFiltro.setBounds(475, 60, 167, 22);
		contentPane.add(txtFiltro);
		txtFiltro.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(654, 60, 97, 25);
		contentPane.add(btnBuscar);
		
		btnCrearLote = new JButton("Crear nuevo lote");
		btnCrearLote.setBounds(415, 361, 151, 25);
		contentPane.add(btnCrearLote);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		btnModificarComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AlmacenProveedores almacen = GestorDatos.getInstance().getComponenteAlmacen(Integer.parseInt(valorComponente));
				ProveedorModificarComponenteAlmacenUI proveedorModificarComponenteAlmacenUI = new ProveedorModificarComponenteAlmacenUI(proveedor, almacen);
				proveedorModificarComponenteAlmacenUI.setVisible(true);
			}});
		
		btnAadirNuevoComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ProveedorA�adirComponenteAlAlmacenUI proveedorA�adirComponenteAlAlmacenUI = new ProveedorA�adirComponenteAlAlmacenUI(proveedor);
				proveedorA�adirComponenteAlAlmacenUI.setVisible(true);
				
			}});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar();
			}});
		btnCrearLote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 0 = si,1 = no 
				int eleccion = JOptionPane.showConfirmDialog(null, "Seguro que quieres crear un nuevo lote ?", "Confirmar creacion de lote", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (eleccion == 0) {
					Lote lote = GestorDatos.getInstance().generarLote(proveedor);
					JOptionPane.showMessageDialog(null, "Se ha creado el Lote numero "+lote.getIdStock());
					actualizar();
				}
								
				
			}});
						
	}
	private void actualizar() {
		vContenidoComponentes = gestorTablas.obtenerCuerpoAlmacen(proveedor, (String)cbox.getSelectedItem(), txtFiltro.getText());
		modeloComponentes.setDataVector(vContenidoComponentes, vColumnasComponentes);
		modeloComponentes.fireTableDataChanged();
	}
}
