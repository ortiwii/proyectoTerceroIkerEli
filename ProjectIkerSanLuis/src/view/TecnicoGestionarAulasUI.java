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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import db.GestorDatos;
import model.Aula;
import model.GestorTablas;
import model.Tecnico;

public class TecnicoGestionarAulasUI extends JFrame{
	
	private Tecnico tecnico;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable jtableAulas;
	private Vector vColumnas;
	private Vector vContenido;
	private String valor;
	private int eleccion;
	private JLabel lblSaludo;
	private JButton btnEliminarAula;
	private JButton btnCrearAula;
	private JButton btnModificarAula;
	private JButton btnVolver;
	private JButton btnActualizar;
	private DefaultTableModel modelo;
	
	public TecnicoGestionarAulasUI(Tecnico tecnico) {
	
		this.tecnico = tecnico;				
		
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//Datos de la tabla
		vContenido = gestorTablas.obtenerCuerpoAulas(tecnico.getCentro());
		vColumnas = gestorTablas.obtenerCabecerasAulas();
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 76, 716, 279);
		
		contentPane.remove(scrollPane);
		
		contentPane.add(scrollPane);
		
		jtableAulas = new JTable(vContenido,vColumnas);
		scrollPane.setViewportView(jtableAulas);
		
		modelo = (DefaultTableModel) jtableAulas.getModel();
		
		jtableAulas.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				// Obtenemos el primer dato del renglon seleccionado
		        if (jtableAulas.getSelectedRow() != -1) {
		            valor = (String) modelo.getValueAt(jtableAulas.getSelectedRow(), 0);
		            eleccion = jtableAulas.getSelectedRow();
		            btnEliminarAula.setVisible(true);
		            btnModificarAula.setVisible(true);
//						            
//						            // Lo imprimimos en pantalla
//						            System.out.println(valor);
		        } 
				
			}
		});
		
		setContentPane(contentPane);							
		
		setBounds(100, 100, 822, 545);
		
		lblSaludo = new JLabel("Estas viendo las aulas del centro "+tecnico.getCentro().getNombre());
		lblSaludo.setBounds(46, 37, 570, 16);
		contentPane.add(lblSaludo);
		
		btnEliminarAula = new JButton("ELIMINAR AULA");
		btnEliminarAula.setBounds(304, 377, 174, 25);
		btnEliminarAula.setVisible(false);
		contentPane.add(btnEliminarAula);
		
		btnCrearAula = new JButton("CREAR AULA");
		btnCrearAula.setBounds(209, 444, 174, 25);
		contentPane.add(btnCrearAula);
		
		btnModificarAula = new JButton("MODIFICAR AULA");
		btnModificarAula.setBounds(304, 410, 174, 25);
		btnModificarAula.setVisible(false);
		contentPane.add(btnModificarAula);
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(388, 444, 176, 25);
		contentPane.add(btnVolver);
		
		btnActualizar = new JButton("<>");
		btnActualizar.setBounds(711, 38, 51, 25);
		contentPane.add(btnActualizar);
		
		btnEliminarAula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = GestorDatos.getInstance().eliminarAula(GestorDatos.getInstance().getAula(valor));
				if (flag) {
					JOptionPane.showMessageDialog(null, "Se ha eliminado la aula "+valor+" satisfactoriamente");
					actualizar();					
				}else {
					JOptionPane.showMessageDialog(null, "Ha habido un error borrando el aula "+valor);
				}
			}});
		
		btnModificarAula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoModificarAulaUI tecnicoModificarAulaUI = new TecnicoModificarAulaUI(GestorDatos.getInstance().getAula(valor));
				tecnicoModificarAulaUI.setVisible(true);
			}});
		
		btnCrearAula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TecnicoCrearAulaUI tecnicoCrearAulaUI = new TecnicoCrearAulaUI();
				tecnicoCrearAulaUI.setVisible(true);				
			}});
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar();
			}});
		

	}
	public void actualizar() {	
		vContenido = gestorTablas.obtenerCuerpoAulas(tecnico.getCentro());
		modelo.setDataVector(vContenido, vColumnas);
		modelo.fireTableDataChanged();
	}
}
