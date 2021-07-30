package view;

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

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;

import model.GestorTablas;
import model.Incidencia;
import model.Solicitud;
import model.Usuario;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

public class UsuarioMostrarSolicitudesUI extends JFrame{
	
//	public static Solicitud solicutud;
	//public static Solicitud conce=new Solicitud("", "");
	private Usuario usuario;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable jtableSolicitudes;
	private Vector vColumnas;
	private Vector vContenido;
	private String valor;
	private int eleccion;
	private JButton btnEliminarSolicitud;
	private JButton btnVolver;
	private JLabel lblSaludo;
	private JLabel lblInfo;
	
	public UsuarioMostrarSolicitudesUI(Usuario usuario) {
		
		this.usuario = usuario;
		vContenido = gestorTablas.obtenerCuerpoSolicitudes(usuario);		
		initialize();
	}
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Datos de la tabla
		vColumnas = gestorTablas.obtenerCabecerasSolicitudes();
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 56, 725, 252);
		contentPane.add(scrollPane);
		
		jtableSolicitudes = new JTable(vContenido,vColumnas);
		scrollPane.setViewportView(jtableSolicitudes);
		
		DefaultTableModel modelo = (DefaultTableModel) jtableSolicitudes.getModel();
		
		jtableSolicitudes.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				// Obtenemos el primer dato del renglon seleccionado
		        if (jtableSolicitudes.getSelectedRow() != -1) {
		            valor = (String) modelo.getValueAt(jtableSolicitudes.getSelectedRow(), 0);
		            eleccion = jtableSolicitudes.getSelectedRow();
		            btnEliminarSolicitud.setVisible(true);
		            
		            // Lo imprimimos en pantalla
		            System.out.println(valor);
		        } 
				
			}
		});
		
		
		
		
		setBounds(100, 100, 766, 472);
		
		
		btnEliminarSolicitud = new JButton("ELIMINAR SOLICITUD");
		btnEliminarSolicitud.setBounds(297, 357, 155, 25);
		contentPane.add(btnEliminarSolicitud);
		btnEliminarSolicitud.setVisible(false);
		
		
		btnEliminarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					// TODO Eliminar solicitud
					Solicitud solicitud = GestorDatos.getInstance().getSolicitud(valor);
					GestorDatos.getInstance().eliminarSolicitud(solicitud);;
					vContenido.remove(eleccion);
					modelo.fireTableDataChanged();
		            lblInfo.setText("Se ha eliminado la incidencia numero "+valor+" satisfactoriamente");
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});
		
		btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		btnVolver.setBounds(326, 387, 97, 25);
		contentPane.add(btnVolver);
		
		lblSaludo = new JLabel("Estas viendo las solicitudes del usuario "+this.usuario.getNombre());
		lblSaludo.setBounds(12, 27, 725, 16);
		contentPane.add(lblSaludo);
		
		lblInfo = new JLabel("");
		lblInfo.setBounds(12, 324, 725, 16);
		contentPane.add(lblInfo);
	}
}
