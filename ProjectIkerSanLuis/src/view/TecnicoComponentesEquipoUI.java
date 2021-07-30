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
import model.Componente;
import model.Equipo;
import model.GestorTablas;
import model.Tecnico;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class TecnicoComponentesEquipoUI extends JFrame{
	private Equipo equipo;
	private Tecnico tecnico;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	
	private JScrollPane tablaComponentes;
	DefaultTableModel modeloComponentes;
	private JTable jtableComponentes;
	private Vector vColumnasComponentes;
	private Vector vContenidoComponentes;
	private String valorComponente;
	private JLabel lblSaludo;
	private JLabel lblEquipo;
	private JButton btnVolver;
	private JButton btnAsignarNuevosComponentes;
	private JButton btnDesaComponente;
	
	
	public TecnicoComponentesEquipoUI (Equipo equipo, Tecnico tecnico) {
		this.equipo = equipo;
		this.tecnico = tecnico;
					
			

		
		initialize();
	}
	private void initialize() {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
					
		setBounds(100, 100, 807, 442);				
		
		tablaComponentes = new JScrollPane();
		tablaComponentes.setBounds(38, 106, 716, 161);
		tablaComponentes.setVisible(false);
		contentPane.add(tablaComponentes);		
		
		// Enseñar componentes
		try {					
			//Datos de la tabla componentes 
			
			tablaComponentes.setVisible(true);
			vContenidoComponentes = gestorTablas.obtenerCuerpoComponentesEquipo(equipo);
			vColumnasComponentes = gestorTablas.obtenerCabecerasComponentes();
			contentPane.setLayout(null);
			
			jtableComponentes = new JTable(vContenidoComponentes,vColumnasComponentes);
			tablaComponentes.setViewportView(jtableComponentes);
			
			modeloComponentes = (DefaultTableModel) jtableComponentes.getModel();
			
			jtableComponentes.addMouseListener((MouseListener) new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
			
			        if (jtableComponentes.getSelectedRow() != -1) {
			        	valorComponente = (String) modeloComponentes.getValueAt(jtableComponentes.getSelectedRow(), 0);					            
			            btnDesaComponente.setEnabled(true);
			        } 
			
				}});					
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lblSaludo = new JLabel("COMPONENTES DEL EQUIPO");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(38, 13, 440, 33);
		contentPane.add(lblSaludo);
		
		lblEquipo = new JLabel("Equipo : "+equipo.getIdEquipo());
		lblEquipo.setBounds(37, 59, 56, 16);
		contentPane.add(lblEquipo);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(657, 340, 97, 25);
		contentPane.add(btnVolver);
		
		btnAsignarNuevosComponentes = new JButton("Asignar nuevos componentes");
		btnAsignarNuevosComponentes.setBounds(38, 340, 214, 25);
		contentPane.add(btnAsignarNuevosComponentes);
		
		btnDesaComponente = new JButton("Desasignar componente");
		btnDesaComponente.setEnabled(false);
		btnDesaComponente.setBounds(285, 340, 193, 25);
		contentPane.add(btnDesaComponente);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		btnDesaComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Componente componente = GestorDatos.getInstance().getComponente(Integer.parseInt(valorComponente));
				componente.setEquipo(null);
				GestorDatos.getInstance().actualizarComponente(componente, componente.getIdComponente());	
				
				vContenidoComponentes = gestorTablas.obtenerCuerpoComponentesEquipo(equipo);
				modeloComponentes.setDataVector(vContenidoComponentes, vColumnasComponentes);
				modeloComponentes.fireTableDataChanged();
			}});
		
		btnAsignarNuevosComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoAsignarComponentesAEquipoUI tecnicoAsignarComponentesAEquipoUI = new TecnicoAsignarComponentesAEquipoUI(tecnico, equipo);
				tecnicoAsignarComponentesAEquipoUI.setVisible(true);
				dispose();
			}});
		
	}

}
