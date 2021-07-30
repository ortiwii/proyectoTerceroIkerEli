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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;

public class TecnicoAsignarComponentesAEquipoUI extends JFrame{

	private Tecnico tecnico;
	private Equipo equipo;
	
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;	
	private JScrollPane tablaComponentes;
	DefaultTableModel modeloComponentes;
	private JTable jtableComponentes;
	private Vector vColumnasComponentes;
	private Vector vContenidoComponentes;
	private String valorComponente;
	private JLabel lblSaludo;
	private JLabel lblSaludo_1;
	private JLabel lblEquipo;
	private JButton btnAsignarComponente;
	private JButton btnVolver;
	
	public TecnicoAsignarComponentesAEquipoUI(Tecnico tecnico, Equipo equipo) {
		this.tecnico = tecnico;
		this.equipo = equipo;
		
	
		initialize();
	}
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
					
		setBounds(100, 100, 807, 442);
		
		
		tablaComponentes = new JScrollPane();
		tablaComponentes.setBounds(38, 127, 716, 161);
		tablaComponentes.setVisible(true);
		contentPane.add(tablaComponentes);		
		
		
		// Enseñar componentes
		try {					
			
			//Datos de la tabla componentes 
			
			tablaComponentes.setVisible(true);
			vContenidoComponentes = gestorTablas.obtenerCuerpoComponentesCentroLibres(tecnico.getCentro());
			vColumnasComponentes = gestorTablas.obtenerCabecerasComponentes();
			contentPane.setLayout(null);
			
			jtableComponentes = new JTable(vContenidoComponentes,vColumnasComponentes);
			tablaComponentes.setViewportView(jtableComponentes);
			
			modeloComponentes = (DefaultTableModel) jtableComponentes.getModel();
			
			jtableComponentes.addMouseListener((MouseListener) new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
			
			        if (jtableComponentes.getSelectedRow() != -1) {
			        	valorComponente = (String) modeloComponentes.getValueAt(jtableComponentes.getSelectedRow(), 0);					            
			        	
			        } 
			
				}});					
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		lblSaludo_1 = new JLabel("Asignar componentes nuevos a un equipo");
		lblSaludo_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo_1.setBounds(38, 30, 708, 29);
		contentPane.add(lblSaludo_1);
		
		lblEquipo = new JLabel("Equipo : "+equipo.getIdEquipo());
		lblEquipo.setBounds(38, 69, 201, 16);
		contentPane.add(lblEquipo);
		
		btnAsignarComponente = new JButton("Asignar Componente");
		btnAsignarComponente.setBounds(38, 312, 170, 25);
		contentPane.add(btnAsignarComponente);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(657, 312, 97, 25);
		contentPane.add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoComponentesEquipoUI tecnicoComponentesEquipoUI = new TecnicoComponentesEquipoUI(equipo, tecnico);
				tecnicoComponentesEquipoUI.setVisible(true);
				dispose();
			}});
		
		btnAsignarComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Componente componente = GestorDatos.getInstance().getComponente(Integer.parseInt(valorComponente));
				componente.setEquipo(equipo);
				GestorDatos.getInstance().actualizarComponente(componente, componente.getIdComponente());
				JOptionPane.showMessageDialog(null, "El componente "+componente.getIdComponente()+" se ha asignado correctamente al equipo "+equipo.getIdEquipo());
				
				vContenidoComponentes = gestorTablas.obtenerCuerpoComponentesCentroLibres(tecnico.getCentro());
				modeloComponentes.setDataVector(vContenidoComponentes, vColumnasComponentes);
				modeloComponentes.fireTableDataChanged();
			}});
	}
}
