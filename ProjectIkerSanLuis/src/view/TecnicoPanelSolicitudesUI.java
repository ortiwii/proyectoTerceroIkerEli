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

public class TecnicoPanelSolicitudesUI extends JFrame {
	
	private Tecnico tecnico;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable jtableSolicitudes;
	private Vector vColumnas;
	private Vector vContenido;
	private String valor;	
	private DefaultTableModel modelo;
	private JLabel lblSaludo;
	private JLabel lblTecnico;
	private JTextPane txtAtenderSolicitud;
	private JButton btnVolver;
	private JButton btnAtenderSolicitud;
	private JButton btnEliminarSolicitud;
	
	public TecnicoPanelSolicitudesUI (Tecnico tecnico) {
		this.tecnico = tecnico;
		
		//Datos de la tabla
		vContenido = gestorTablas.obtenerCuerpoSolicitudesTecnico(tecnico);
		
				
		initialize();
	}
	private void initialize() {
		
		setBounds(100, 100, 766, 472);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		vColumnas = gestorTablas.obtenerCabecerasSolicitudes();
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 91, 725, 149);
		contentPane.add(scrollPane);
		
		jtableSolicitudes = new JTable(vContenido,vColumnas);
		scrollPane.setViewportView(jtableSolicitudes);
		
		modelo = (DefaultTableModel) jtableSolicitudes.getModel();
		
		jtableSolicitudes.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				// Obtenemos el primer dato del renglon seleccionado
		        if (jtableSolicitudes.getSelectedRow() != -1) {		        	
		            valor = (String) modelo.getValueAt(jtableSolicitudes.getSelectedRow(), 0);
		            Solicitud solicitud = GestorDatos.getInstance().getSolicitud(valor);
		            btnAtenderSolicitud.setEnabled(true); 
		            btnEliminarSolicitud.setEnabled(true);
		            txtAtenderSolicitud.setEditable(true);
		            txtAtenderSolicitud.setText(solicitud.getDescripcion());
		        } 
				
			}
		});
		
		lblSaludo = new JLabel("PANEL DE SOLICITUDES SIN ATENDER");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(12, 27, 725, 19);
		contentPane.add(lblSaludo);
		
		lblTecnico = new JLabel("Tecnico : "+tecnico.getUser());
		lblTecnico.setBounds(12, 59, 177, 19);
		contentPane.add(lblTecnico);
		
		txtAtenderSolicitud = new JTextPane();
		txtAtenderSolicitud.setEditable(false);
		txtAtenderSolicitud.setBounds(296, 260, 441, 159);
		contentPane.add(txtAtenderSolicitud);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(42, 373, 185, 25);
		contentPane.add(btnVolver);
		
		btnAtenderSolicitud = new JButton("Atender Solicitud");
		btnAtenderSolicitud.setEnabled(false);
		btnAtenderSolicitud.setBounds(42, 289, 185, 25);
		contentPane.add(btnAtenderSolicitud);
		
		btnEliminarSolicitud = new JButton("Eliminar Solicitud");
		btnEliminarSolicitud.setEnabled(false);
		btnEliminarSolicitud.setBounds(42, 327, 185, 25);
		contentPane.add(btnEliminarSolicitud);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		btnAtenderSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtAtenderSolicitud.getText().equals("")) {					
					Solicitud solicitud = GestorDatos.getInstance().getSolicitud(valor);
					if (solicitud.getEstado().equals("n")) {
						solicitud.setEstado("a");
						solicitud.setFechaCierre(Calendar.getInstance());
						solicitud.setDescripcion(txtAtenderSolicitud.getText());
						GestorDatos.getInstance().actualizarSolicitud(solicitud, solicitud.getIdSolInc());
						tecnico.setPendientes(tecnico.getPendientes()-1);
						GestorDatos.getInstance().actualizarTecnico(tecnico);
						actualizar();
						JOptionPane.showMessageDialog(null, "Se ha atendido la solicitud "+solicitud.getIdSolInc());
						
					}else {
						JOptionPane.showMessageDialog(null, "La solicitud "+solicitud.getIdSolInc()+" ya esta atendida");
					}
				}else {
					JOptionPane.showMessageDialog(null, "La descripcion de la solicitud no puede estar vacia");
				}
			}});
		
		btnEliminarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Solicitud solicitud = GestorDatos.getInstance().getSolicitud(valor);
				GestorDatos.getInstance().eliminarSolicitud(solicitud);
				actualizar();
				tecnico.setPendientes(tecnico.getPendientes()-1);
				GestorDatos.getInstance().actualizarTecnico(tecnico);
				JOptionPane.showMessageDialog(null, "Se ha eliminado la solicitud "+solicitud.getIdSolInc());
				
			}});		
	}
	private void actualizar() {
		vContenido = gestorTablas.obtenerCuerpoSolicitudesTecnico(tecnico);
		modelo.setDataVector(vContenido, vColumnas);
		modelo.fireTableDataChanged();
	}

}
