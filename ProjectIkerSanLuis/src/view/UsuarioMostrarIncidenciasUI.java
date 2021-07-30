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
import model.GestorTablas;
import model.Incidencia;
import model.Usuario;
import javax.swing.JLabel;

public class UsuarioMostrarIncidenciasUI extends JFrame{
	
	private Usuario usuario;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable jtableIncidencias;
	private Vector vColumnas;
	private Vector vContenido;
	private String valor;
	private int eleccion;
	private JButton btnEliminarIncidencia;
	private JLabel lblSaludo;
	private JButton btnVolver;
	private JLabel lblInfo;
	
	public UsuarioMostrarIncidenciasUI (Usuario usuario) {
		this.usuario = usuario;
		
		vContenido = gestorTablas.obtenerCuerpoIncidencias(usuario);		
		
		lblSaludo = new JLabel("Estas viendo las incidencias de el usuario "+usuario.getNombre());
		initialize();
	}
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Datos de la tabla
		vColumnas = gestorTablas.obtenerCabecerasIncidencias();
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 43, 716, 293);
		contentPane.add(scrollPane);
		
		jtableIncidencias = new JTable(vContenido,vColumnas);
		scrollPane.setViewportView(jtableIncidencias);
		
		DefaultTableModel modelo = (DefaultTableModel) jtableIncidencias.getModel();
		
		jtableIncidencias.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				// Obtenemos el primer dato del renglon seleccionado
		        if (jtableIncidencias.getSelectedRow() != -1) {
		            
		        	valor = (String) modelo.getValueAt(jtableIncidencias.getSelectedRow(), 0);
		            eleccion = jtableIncidencias.getSelectedRow();		            
		            btnEliminarIncidencia.setVisible(true);		            
		            
		        } 
				
			}
		});
		
		
		
		
		setBounds(100, 100, 801, 503);
		
		
		btnEliminarIncidencia = new JButton("ELIMINAR INCIDENCIA");
		btnEliminarIncidencia.setBounds(291, 383, 159, 25);
		contentPane.add(btnEliminarIncidencia);
		btnEliminarIncidencia.setVisible(false);
		
		
		btnEliminarIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
										
					System.out.println(valor);
					// TODO Eliminar incidencia
					Incidencia incidencia = GestorDatos.getInstance().getIncidencia(valor);
					GestorDatos.getInstance().eliminarIncidencia(incidencia);
					vContenido.remove(eleccion);
					modelo.fireTableDataChanged();
		            lblInfo.setText("Se ha eliminado la incidencia numero "+valor+" satisfactoriamente");
					
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});
		lblSaludo.setBounds(35, 13, 717, 16);
		contentPane.add(lblSaludo);
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(291, 421, 159, 22);
		contentPane.add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		lblInfo = new JLabel("");
		lblInfo.setBounds(36, 350, 716, 16);
		contentPane.add(lblInfo);
	}
		
	

}
