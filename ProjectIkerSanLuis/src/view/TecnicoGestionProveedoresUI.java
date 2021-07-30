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

import model.GestorTablas;
import model.Tecnico;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class TecnicoGestionProveedoresUI extends JFrame{

	private Tecnico tecnico;
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
	
	public TecnicoGestionProveedoresUI (Tecnico tecnico) {
		this.tecnico = tecnico;
		initialize();
	}
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
					
		setBounds(100, 100, 805, 336);				
		
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
			        } 
			
		}
			});					
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		btnGenerarPdf = new JButton("GENERAR PDF ");
		btnGenerarPdf.setEnabled(false);
		btnGenerarPdf.setBounds(41, 248, 136, 25);
		contentPane.add(btnGenerarPdf);
		
		lblGestionDeProveedores = new JLabel("GESTION DE PROVEEDORES ");
		lblGestionDeProveedores.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGestionDeProveedores.setBounds(41, 30, 716, 16);
		contentPane.add(lblGestionDeProveedores);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(660, 248, 97, 25);
		contentPane.add(btnVolver);
		
		btnSolicitarCompraDe = new JButton("Solicitar compra de algun componente");
		btnSolicitarCompraDe.setBounds(264, 248, 307, 25);
		contentPane.add(btnSolicitarCompraDe);
		
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
				TecnicoSolicitarComponentesUI tecnicoSolicitarComponentesUI = new TecnicoSolicitarComponentesUI(tecnico);
				tecnicoSolicitarComponentesUI.setVisible(true);
				dispose();
			}});
	}
}
