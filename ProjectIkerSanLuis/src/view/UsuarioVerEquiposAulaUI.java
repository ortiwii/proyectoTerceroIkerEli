package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import db.GestorDatos;
import model.Aula;
import model.Equipo;
import model.GestorTablas;
import model.Usuario;
import javax.swing.JLabel;

public class UsuarioVerEquiposAulaUI extends JFrame{
	private Aula aula;
	
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	private JScrollPane tablaEquipos;
	private JTable jtableEquipos;
	private Vector vColumnasEquipos;
	private Vector vContenidoEquipos;	
	private String valorEquipo;
	
	private JScrollPane tablaComponentes;
	private JTable jtableComponentes;
	private Vector vColumnasComponentes;
	private Vector vContenidoComponentes;
	private String valorComponente;
	
	private JButton btnVerComponentes;
	private JButton btnVolver;
	private JLabel lblSaludos;
	private JLabel lblInfocomponentes;
	
	public UsuarioVerEquiposAulaUI (Aula aula) {
	
		this.aula = aula;
		
		initialize();
	}	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Datos de la tabla
		
		vContenidoEquipos = gestorTablas.obtenerCuerpoEquipos(aula);	
		vColumnasEquipos = gestorTablas.obtenerCabecerasEquipos();
		contentPane.setLayout(null);
		
		tablaEquipos = new JScrollPane();
		tablaEquipos.setBounds(38, 47, 716, 162);
		contentPane.add(tablaEquipos);
		
		jtableEquipos = new JTable(vContenidoEquipos,vColumnasEquipos);
		tablaEquipos.setViewportView(jtableEquipos);
		
		DefaultTableModel modeloEquipos = (DefaultTableModel) jtableEquipos.getModel();
		
		jtableEquipos.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
		        if (jtableEquipos.getSelectedRow() != -1) {
		        	valorEquipo = (String) modeloEquipos.getValueAt(jtableEquipos.getSelectedRow(), 0);
		            
		            btnVerComponentes.setVisible(true);
		            
		            System.out.println(valorEquipo);
		        } 
				
			}
		});
						
		setBounds(100, 100, 800, 522);		
		
		btnVerComponentes = new JButton("VER COMPONENTES DEL EQUIPO");
		btnVerComponentes.setBounds(259, 397, 246, 31);
		contentPane.add(btnVerComponentes);
		btnVerComponentes.setVisible(false);

		tablaComponentes = new JScrollPane();
		tablaComponentes.setBounds(38, 256, 716, 128);
		tablaComponentes.setVisible(false);
		contentPane.add(tablaComponentes);		
		
		btnVerComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Enseñar componentes
				try {					
					//Datos de la tabla componentes 
					Equipo equipo = GestorDatos.getInstance().getEquipo(Integer.parseInt(valorEquipo));
					lblInfocomponentes.setText("Estos son los componentes del equipo "+valorEquipo);
//					System.out.println(equipo.toString());
					
					tablaComponentes.setVisible(true);
					vContenidoComponentes = gestorTablas.obtenerCuerpoComponentesEquipo(equipo);	
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
					
			}
		});
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(330, 439, 102, 25);
		contentPane.add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		
		
		
		lblSaludos = new JLabel("Estas viendo los equipos del aula "+this.aula.getCodigo());
		lblSaludos.setBounds(38, 18, 491, 16);
		contentPane.add(lblSaludos);
		
		lblInfocomponentes = new JLabel("");
		lblInfocomponentes.setBounds(38, 227, 716, 16);
		contentPane.add(lblInfocomponentes);
	}
}
