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
import model.Proveedor;
import javax.swing.JLabel;
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
		
		// Enseñar componentes
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
		btnModificarComponente.setBounds(248, 361, 167, 25);
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
				ProveedorAñadirComponenteAlAlmacenUI proveedorAñadirComponenteAlAlmacenUI = new ProveedorAñadirComponenteAlAlmacenUI(proveedor);
				proveedorAñadirComponenteAlAlmacenUI.setVisible(true);
				
			}});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vContenidoComponentes = gestorTablas.obtenerCuerpoAlmacen(proveedor, (String)cbox.getSelectedItem(), txtFiltro.getText());
				modeloComponentes.setDataVector(vContenidoComponentes, vColumnasComponentes);
				modeloComponentes.fireTableDataChanged();
			}});
				

	}

}
