package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
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
import model.Incidencia;
import model.Solicitud;
import model.Tecnico;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class TecnicoPanelIncidenciasUI extends JFrame {
	
	private Tecnico tecnico;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable jtableIncidencias;
	private Vector vColumnas;
	private Vector vContenido;
	private String valor;	
	private DefaultTableModel modelo;
	private JLabel lblSaludo;
	private JLabel lblTecnico;
	private JTextPane txtAtenderIncidencia;
	private JButton btnVolver;
	private JButton btnAtenderIncidencia;
	private JButton btnEliminarIncidencia;
	
	public TecnicoPanelIncidenciasUI (Tecnico tecnico) {
		this.tecnico = tecnico;
		
		//Datos de la tabla
		vContenido = gestorTablas.obtenerCuerpoIncidenciasTecnico(tecnico);
		
				
		initialize();
	}
	private void initialize() {
		
		setBounds(100, 100, 766, 472);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		vColumnas = gestorTablas.obtenerCabecerasIncidencias();
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 91, 725, 149);
		contentPane.add(scrollPane);
		
		jtableIncidencias = new JTable(vContenido,vColumnas);
		scrollPane.setViewportView(jtableIncidencias);
		
		modelo = (DefaultTableModel) jtableIncidencias.getModel();
		
		jtableIncidencias.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				// Obtenemos el primer dato del renglon seleccionado
		        if (jtableIncidencias.getSelectedRow() != -1) {		        	
		            valor = (String) modelo.getValueAt(jtableIncidencias.getSelectedRow(), 0);
		            Solicitud solicitud = GestorDatos.getInstance().getSolicitud(valor);
		            btnAtenderIncidencia.setEnabled(true); 
		            btnEliminarIncidencia.setEnabled(true);
		            txtAtenderIncidencia.setEditable(true);
		            txtAtenderIncidencia.setText(solicitud.getDescripcion());
		        } 
				
			}
		});
		
		lblSaludo = new JLabel("PANEL DE INCIDENCIAS SIN ATENDER");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(12, 27, 725, 19);
		contentPane.add(lblSaludo);
		
		lblTecnico = new JLabel("Tecnico : "+tecnico.getUser());
		lblTecnico.setBounds(12, 59, 177, 19);
		contentPane.add(lblTecnico);
		
		txtAtenderIncidencia = new JTextPane();
		txtAtenderIncidencia.setEditable(false);
		txtAtenderIncidencia.setBounds(296, 260, 441, 159);
		contentPane.add(txtAtenderIncidencia);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(42, 373, 185, 25);
		contentPane.add(btnVolver);
		
		btnAtenderIncidencia = new JButton("Atender Incidencia");
		btnAtenderIncidencia.setEnabled(false);
		btnAtenderIncidencia.setBounds(42, 289, 185, 25);
		contentPane.add(btnAtenderIncidencia);
		
		btnEliminarIncidencia = new JButton("Eliminar Incidencia");
		btnEliminarIncidencia.setEnabled(false);
		btnEliminarIncidencia.setBounds(42, 327, 185, 25);
		contentPane.add(btnEliminarIncidencia);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		btnAtenderIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtAtenderIncidencia.getText().equals("")) {
					Incidencia incidencia = GestorDatos.getInstance().getIncidencia(valor);
					if (incidencia.getEstado().equals("n")) {
						incidencia.setEstado("a");
						incidencia.setFechaCierre(Calendar.getInstance());
						incidencia.setDescripcion(txtAtenderIncidencia.getText());
						GestorDatos.getInstance().actualizarIncidencia(incidencia, incidencia.getIdSolInc());
						tecnico.setPendientes(tecnico.getPendientes()-1);
						GestorDatos.getInstance().actualizarTecnico(tecnico);
						actualizar();
						JOptionPane.showMessageDialog(null, "Se ha atendido la incidencia "+incidencia.getIdSolInc());
						
					}else {
						JOptionPane.showMessageDialog(null, "La incidencia "+incidencia.getIdSolInc()+" ya esta atendida");
					}
				}else {
					JOptionPane.showMessageDialog(null, "La descripcion de la incidencia no puede estar vacia");
				}
			}});
		
		btnEliminarIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Solicitud solicitud = GestorDatos.getInstance().getSolicitud(valor);
				GestorDatos.getInstance().eliminarSolicitud(solicitud);
				actualizar();
				tecnico.setPendientes(tecnico.getPendientes()-1);
				GestorDatos.getInstance().actualizarTecnico(tecnico);
				JOptionPane.showMessageDialog(null, "Se ha eliminado la incidencia "+solicitud.getIdSolInc());
				
			}});		
	}
	private void actualizar() {
		vContenido = gestorTablas.obtenerCuerpoSolicitudesTecnico(tecnico);
		modelo.setDataVector(vContenido, vColumnas);
		modelo.fireTableDataChanged();
	}

}
