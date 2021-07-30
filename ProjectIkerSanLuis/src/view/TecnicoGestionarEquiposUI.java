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
import model.Equipo;
import model.GestorTablas;
import model.Tecnico;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TecnicoGestionarEquiposUI extends JFrame{
	
	private Tecnico tecnico;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	private JScrollPane tablaEquipos;
	private JTable jtableEquipos;
	private Vector vColumnasEquipos;
	private Vector vContenidoEquipos;	
	private String valorEquipo;
	private JButton btnInstalarEquipo;
	private JButton btnModificarEquipo;
	private JButton btnEliminarEquipo;
	private JButton btnVerComponentes;
	private JTextField txtBusqueda;
	private JButton btnBuscar;
	private JComboBox comboBox;
	private JLabel lblBuscar;
	private JButton btnVolver;
	
	public TecnicoGestionarEquiposUI (Tecnico tecnico) {
		this.tecnico = tecnico;
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 800, 436);		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Datos de la tabla
		
		vContenidoEquipos = gestorTablas.obtenerCuerpoEquipos();
		vColumnasEquipos = gestorTablas.obtenerCabecerasEquipos();
		contentPane.setLayout(null);
		
		tablaEquipos = new JScrollPane();
		tablaEquipos.setBounds(38, 78, 716, 188);
		contentPane.add(tablaEquipos);
		
		jtableEquipos = new JTable(vContenidoEquipos,vColumnasEquipos);
		tablaEquipos.setViewportView(jtableEquipos);
		
		DefaultTableModel modeloEquipos = (DefaultTableModel) jtableEquipos.getModel();
		
		jtableEquipos.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
		        if (jtableEquipos.getSelectedRow() != -1) {
		        	valorEquipo = (String) modeloEquipos.getValueAt(jtableEquipos.getSelectedRow(), 0);
		            
//		            btnVerComponentes.setVisible(true);
		        	btnEliminarEquipo.setEnabled(true);
		        	btnModificarEquipo.setEnabled(true);
		        	btnVerComponentes.setEnabled(true);
		            
		            System.out.println(valorEquipo);
		        } 
				
			}
		});
		
		btnInstalarEquipo = new JButton("INSTALAR UN NUEVO EQUIPO");
		btnInstalarEquipo.setBounds(113, 279, 235, 25);
		contentPane.add(btnInstalarEquipo);
		
		btnModificarEquipo = new JButton("MODIFICAR EQUIPO");
		btnModificarEquipo.setEnabled(false);
		btnModificarEquipo.setBounds(113, 317, 235, 25);
		contentPane.add(btnModificarEquipo);
		
		btnEliminarEquipo = new JButton("ELIMINAR EQUIPO");
		btnEliminarEquipo.setEnabled(false);
		btnEliminarEquipo.setBounds(428, 317, 235, 25);
		contentPane.add(btnEliminarEquipo);
		
		btnVerComponentes = new JButton("VER COMPONENTES DEL EQUIPO");
		btnVerComponentes.setEnabled(false);
		btnVerComponentes.setBounds(281, 355, 235, 25);
		contentPane.add(btnVerComponentes);
		
		txtBusqueda = new JTextField();
		txtBusqueda.setBounds(307, 30, 339, 22);
		contentPane.add(txtBusqueda);
		txtBusqueda.setColumns(10);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBounds(658, 29, 96, 25);
		contentPane.add(btnBuscar);
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(428, 279, 235, 25);
		contentPane.add(btnVolver);
		
		comboBox = new JComboBox(vColumnasEquipos);
		comboBox.setBounds(163, 30, 132, 22);
		contentPane.add(comboBox);
		
		lblBuscar = new JLabel("Buscar equipo :");
		lblBuscar.setBounds(38, 33, 113, 16);
		contentPane.add(lblBuscar);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cboxElection = (String) comboBox.getSelectedItem();
				try {
					if (cboxElection.equals("idEquipo") || cboxElection.equals("almacenamiento") || cboxElection.equals("ram")) { // Busqueda por id					
						Integer.parseInt(txtBusqueda.getText());																		
					}
					
					vContenidoEquipos = gestorTablas.obtenerCuerpoEquiposCondicionado(cboxElection, txtBusqueda.getText());
					modeloEquipos.setDataVector(vContenidoEquipos, vColumnasEquipos);
					modeloEquipos.fireTableDataChanged();
					
				}catch (Exception ex) {
					txtBusqueda.setText("");
					JOptionPane.showMessageDialog(null, "El atributo debe ser numerico");
				}
				
			}});
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
				
		btnModificarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoModificarEquipoUI tecnicoModificarEquipoUI = new TecnicoModificarEquipoUI(GestorDatos.getInstance().getEquipo(Integer.parseInt(valorEquipo)));
				tecnicoModificarEquipoUI.setVisible(true);
			}});
		
		btnInstalarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoCrearEquipoUI tecnicoCrearEquipoUI = new TecnicoCrearEquipoUI(tecnico);
				tecnicoCrearEquipoUI.setVisible(true);
			}});
		
		btnEliminarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipo equipo = GestorDatos.getInstance().getEquipo(Integer.parseInt(valorEquipo));
				boolean flag = GestorDatos.getInstance().eliminarEquipo(equipo);
				if (flag) {
					String cboxElection = (String) comboBox.getSelectedItem();
					vContenidoEquipos = gestorTablas.obtenerCuerpoEquiposCondicionado(cboxElection, txtBusqueda.getText());
					modeloEquipos.setDataVector(vContenidoEquipos, vColumnasEquipos);
					modeloEquipos.fireTableDataChanged();
					
					JOptionPane.showMessageDialog(null, "El equipo "+equipo.getIdEquipo()+" se ha eliminado correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Ha habido un problema al borrar el equipo "+equipo.getIdEquipo());
				}
			}});
		
		btnVerComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipo equipo = GestorDatos.getInstance().getEquipo(Integer.parseInt(valorEquipo));
				TecnicoComponentesEquipoUI tecnicoComponentesEquipoUI = new TecnicoComponentesEquipoUI(equipo, tecnico);
				tecnicoComponentesEquipoUI.setVisible(true);
			}});
		
	}
	
	
}
