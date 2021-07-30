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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class TecnicoSolicitarComponentesUI extends JFrame{
	
	private Tecnico tecnico;
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
	
	public TecnicoSolicitarComponentesUI (Tecnico tecnico) {
		this.tecnico = tecnico;
				
		
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

			
			vContenidoComponentes = gestorTablas.obtenerCuerpoAlmacen(tecnico.getCentro());			
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
		
		lblCentro = new JLabel("Centro : "+this.tecnico.getCentro().getNombre());
		lblCentro.setBounds(34, 67, 716, 16);
		contentPane.add(lblCentro);
		
		txtConcepto = new JTextPane();
		txtConcepto.setEditable(false);
		txtConcepto.setBounds(34, 389, 420, 137);
		contentPane.add(txtConcepto);
		
		btnSolicitarCompra = new JButton("Solicitar compra");
		btnSolicitarCompra.setEnabled(false);
		btnSolicitarCompra.setBounds(509, 413, 241, 25);
		contentPane.add(btnSolicitarCompra);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(509, 469, 241, 25);
		contentPane.add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoGestionProveedoresUI tecnicoGestionProveedoresUI = new TecnicoGestionProveedoresUI(tecnico);
				tecnicoGestionProveedoresUI.setVisible(true);				
				dispose();
			}});
		
		btnSolicitarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSolicitarCompra.setEnabled(false);
				JOptionPane.showMessageDialog(null, "Se ha solicitado la compra al tecnico : "+"<dynamic>");
				txtConcepto.setText("");
				txtConcepto.setEditable(false);
			}});
	}
}
