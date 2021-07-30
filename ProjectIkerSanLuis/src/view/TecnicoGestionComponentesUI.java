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
import model.GestorTablas;
import model.Tecnico;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;

public class TecnicoGestionComponentesUI extends JFrame{

	private Tecnico tecnico;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	
	private DefaultTableModel modeloComponentes;
	private JScrollPane tablaComponentes;
	private JTable jtableComponentes;
	private Vector vColumnasComponentes;
	private Vector vContenidoComponentes;
	private String valorComponente;
	
	private DefaultTableModel modeloComponentesAlmacen;
	private JScrollPane tablaComponentesAlmacen;
	private JTable jtableComponentesAlmacen;
	private Vector vColumnasComponentesAlmacen;
	private Vector vContenidoComponentesAlmacen;
	private String valorComponenteAlmacen;
	
	private JLabel lblSaludo;
	private JLabel lblCentro;
	private JButton btnDesvincularComponenteDel;
	private JButton btnVolver;
	private JButton btnAsignarComponentesDel;
	private JButton btnVincularAlCentro;
	
	public TecnicoGestionComponentesUI (Tecnico tecnico) {
		this.tecnico = tecnico;
				

		
		initialize();
	}
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
			
						
		setBounds(100, 100, 805, 597);				

		tablaComponentesAlmacen = new JScrollPane();
		tablaComponentesAlmacen.setBounds(35, 332, 716, 161);
		contentPane.add(tablaComponentesAlmacen);		
				
		// Enseñar componentes
		try {					
			//Datos de la tabla componentes Almacen

			
			tablaComponentesAlmacen.setVisible(false);
			vContenidoComponentesAlmacen = gestorTablas.obtenerCuerpoComponentesSinCentro();	
			vColumnasComponentesAlmacen = gestorTablas.obtenerCabecerasComponentes();
			contentPane.setLayout(null);
					
			jtableComponentesAlmacen = new JTable(vContenidoComponentesAlmacen,vColumnasComponentesAlmacen);
			tablaComponentesAlmacen.setViewportView(jtableComponentesAlmacen);
			
			modeloComponentesAlmacen = (DefaultTableModel) jtableComponentesAlmacen.getModel();
			
			jtableComponentesAlmacen.addMouseListener((MouseListener) new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					
			        if (jtableComponentesAlmacen.getSelectedRow() != -1) {
			        	valorComponenteAlmacen = (String) modeloComponentesAlmacen.getValueAt(jtableComponentesAlmacen.getSelectedRow(), 0);					            
			        	btnVincularAlCentro.setEnabled(true);
			        } 
					
				}
			});					
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 		
		
		tablaComponentes = new JScrollPane();
		tablaComponentes.setBounds(35, 111, 716, 161);
		tablaComponentes.setVisible(false);
		contentPane.add(tablaComponentes);		
				
		// Enseñar componentes
		try {					
			//Datos de la tabla componentes 

			
			tablaComponentes.setVisible(true);
			vContenidoComponentes = gestorTablas.obtenerCuerpoComponentesCentro(tecnico.getCentro());	
			vColumnasComponentes = gestorTablas.obtenerCabecerasComponentes();
			contentPane.setLayout(null);
					
			jtableComponentes = new JTable(vContenidoComponentes,vColumnasComponentes);
			tablaComponentes.setViewportView(jtableComponentes);
			
			modeloComponentes = (DefaultTableModel) jtableComponentes.getModel();
			
			jtableComponentes.addMouseListener((MouseListener) new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					
			        if (jtableComponentes.getSelectedRow() != -1) {
			        	valorComponente = (String) modeloComponentes.getValueAt(jtableComponentes.getSelectedRow(), 0);					            
			            btnDesvincularComponenteDel.setEnabled(true);			            
			        } 
					
				}
			});					
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 		
		
		lblSaludo = new JLabel("GESTION DE COMPONENTES DEL CENTRO");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(35, 34, 716, 19);
		contentPane.add(lblSaludo);
		
		lblCentro = new JLabel("Centro : "+tecnico.getCentro().getNombre());
		lblCentro.setBounds(35, 66, 716, 16);
		contentPane.add(lblCentro);
		
		btnDesvincularComponenteDel = new JButton("Desvincular  componente del Centro");
		btnDesvincularComponenteDel.setBounds(77, 285, 272, 25);
		contentPane.add(btnDesvincularComponenteDel);
		btnDesvincularComponenteDel.setEnabled(false);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(603, 506, 148, 25);
		contentPane.add(btnVolver);
		
		btnAsignarComponentesDel = new JButton("Asignar componentes del almacen al Centro");
		btnAsignarComponentesDel.setBounds(387, 285, 323, 25);
		contentPane.add(btnAsignarComponentesDel);
		
		btnVincularAlCentro = new JButton("Vincular al centro "+this.tecnico.getCentro().getNombre());
		btnVincularAlCentro.setBounds(77, 506, 272, 25);
		contentPane.add(btnVincularAlCentro);
		btnVincularAlCentro.setVisible(false);
		btnVincularAlCentro.setEnabled(false);
		
		btnDesvincularComponenteDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDesvincularComponenteDel.setEnabled(false);
				Componente componente = GestorDatos.getInstance().getComponente(Integer.parseInt(valorComponente));				
				componente.setCentro(null);				
				componente.setEquipo(null);
				boolean flag = GestorDatos.getInstance().actualizarComponente(componente, componente.getIdComponente());
				if (flag) {
					JOptionPane.showMessageDialog(null, "El componente "+componente.getIdComponente()+" se ha desvinculado de el centro "+tecnico.getCentro().getNombre());
				}else{
					JOptionPane.showMessageDialog(null, "No se ha podido desvincular el componente del centro");
				}
				actualizarComponentes();
				actualizarComponentesAlmacen();
			}});
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		btnAsignarComponentesDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAsignarComponentesDel.setEnabled(false);
				actualizarComponentesAlmacen();
				tablaComponentesAlmacen.setVisible(true);
				btnVincularAlCentro.setVisible(true);
				btnAsignarComponentesDel.setEnabled(false);
			}});	
		
		btnVincularAlCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Componente componente = GestorDatos.getInstance().getComponente(Integer.parseInt(valorComponenteAlmacen));
				componente.setCentro(tecnico.getCentro());
				boolean flag = GestorDatos.getInstance().actualizarComponente(componente, componente.getIdComponente());
				if (flag) {
					JOptionPane.showMessageDialog(null, "El componente "+componente.getIdComponente()+" se ha vinculado al centro "+tecnico.getCentro().getNombre());
				}else{
					JOptionPane.showMessageDialog(null, "No se ha podido vincular el componente al centro");
				}
				actualizarComponentes();
				actualizarComponentesAlmacen();
			}});
	}
	private void actualizarComponentesAlmacen() {
		vContenidoComponentesAlmacen = gestorTablas.obtenerCuerpoComponentesSinCentro();
		modeloComponentesAlmacen.setDataVector(vContenidoComponentesAlmacen, vColumnasComponentesAlmacen);
		modeloComponentesAlmacen.fireTableDataChanged();
	}
	private void actualizarComponentes() {
		vContenidoComponentes = gestorTablas.obtenerCuerpoComponentesCentro(tecnico.getCentro());
		modeloComponentes.setDataVector(vContenidoComponentes, vColumnasComponentes);
		modeloComponentes.fireTableDataChanged();
	}
}
