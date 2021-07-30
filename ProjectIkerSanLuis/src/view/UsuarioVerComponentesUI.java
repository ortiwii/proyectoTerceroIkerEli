package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import db.GestorDatos;
import model.Aula;
import model.Centro;
import model.Equipo;
import model.GestorTablas;

public class UsuarioVerComponentesUI extends JFrame {
	
	private Centro centro;
	
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	
	private JScrollPane tablaComponentes;
	private JTable jtableComponentes;
	private Vector vColumnasComponentes;
	private Vector vContenidoComponentes;
	private String valorComponente;
	
	private JButton btnVolver;
	private JLabel lblSaludos;
	
	public UsuarioVerComponentesUI(Centro centro) {		
	
		this.centro = centro;
		
		initialize();
	}	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
			
						
		setBounds(100, 100, 806, 332);				

		tablaComponentes = new JScrollPane();
		tablaComponentes.setBounds(38, 47, 716, 161);
		tablaComponentes.setVisible(false);
		contentPane.add(tablaComponentes);		
				
		// Enseñar componentes
		try {					
			//Datos de la tabla componentes 

			
			tablaComponentes.setVisible(true);
			vContenidoComponentes = gestorTablas.obtenerCuerpoComponentesCentro(centro);	
			vColumnasComponentes = gestorTablas.obtenerCabecerasComponentes();
			contentPane.setLayout(null);
					
			jtableComponentes = new JTable(vContenidoComponentes,vColumnasComponentes);
			tablaComponentes.setViewportView(jtableComponentes);
			
			DefaultTableModel modeloComponentes = (DefaultTableModel) jtableComponentes.getModel();
			
			jtableComponentes.addMouseListener((MouseListener) new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					
			        if (jtableComponentes.getSelectedRow() != -1) {
			        	valorComponente = (String) modeloComponentes.getValueAt(jtableComponentes.getSelectedRow(), 0);					            
			            
			            System.out.println(valorComponente);
			        } 
					
				}
			});					
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 		
					
			
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(344, 235, 102, 25);
		contentPane.add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		
		
		
		lblSaludos = new JLabel("Estas viendo los componentes del centro "+this.centro.getNombre());
		lblSaludos.setBounds(38, 18, 491, 16);
		contentPane.add(lblSaludos);
		
	}

}
