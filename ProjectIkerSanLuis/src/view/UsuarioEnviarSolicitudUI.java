package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import db.GestorDatos;
import model.Componente;
import model.Equipo;
import model.GestorTablas;
import model.Solicitud;
import model.Usuario;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;


public class UsuarioEnviarSolicitudUI extends JFrame{

	private GestorTablas gestorTablas = new GestorTablas();
	private Usuario usuario;
	private JScrollPane tablaComponentes;
	private JTable jtableComponentes;
	private Vector vColumnasComponentes;
	private Vector vContenidoComponentes;
	private String valorComponente;
	private int eleccion;
	private JLabel lblSaludo_1;
	private JLabel lblInfo;
	private JButton btnVolver;
	private JTextPane txtDescripcion;
	
	public UsuarioEnviarSolicitudUI (Usuario usuario) {
		this.usuario = usuario;
		vContenidoComponentes = gestorTablas.obtenerCuerpoComponentesCentro(usuario.getCentro());
		initialize();
	}
	private void initialize() {
		getContentPane().setLayout(null);
		
		JLabel lblSaludo = new JLabel("ENV\u00CDO DE UNA SOLICITUD");
		lblSaludo.setBounds(292, 33, 267, 16);
		getContentPane().add(lblSaludo);
		
		tablaComponentes = new JScrollPane();
		tablaComponentes.setBounds(26, 122, 716, 128);
		getContentPane().add(tablaComponentes);
		//Datos de la tabla componentes 
		
		tablaComponentes.setVisible(true);
		vColumnasComponentes = gestorTablas.obtenerCabecerasComponentes();
		
		jtableComponentes = new JTable(vContenidoComponentes,vColumnasComponentes);
		tablaComponentes.setViewportView(jtableComponentes);
		
		DefaultTableModel modeloComponentes = (DefaultTableModel) jtableComponentes.getModel();
		
		jtableComponentes.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
		        if (jtableComponentes.getSelectedRow() != -1) {
		        	valorComponente = (String) modeloComponentes.getValueAt(jtableComponentes.getSelectedRow(), 0);
		        	eleccion = jtableComponentes.getSelectedRow();
		            txtDescripcion.setEditable(true);
		        	
		            System.out.println(valorComponente);
		        } 
				
			}
		});					
		
		
		
		JButton btnEnviarSolicitud = new JButton("ENVIAR SOLICITUD");
		btnEnviarSolicitud.setBounds(292, 448, 143, 25);
		btnEnviarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Componente componente = GestorDatos.getInstance().getComponente(Integer.parseInt(valorComponente));
				Solicitud solicitud = GestorDatos.getInstance().generarSolicitud(txtDescripcion.getText(), Calendar.getInstance(), usuario, componente);
				JOptionPane.showMessageDialog(null, "Se ha enviado la solicitud correctamente");
				vContenidoComponentes.remove(eleccion);
				modeloComponentes.fireTableDataChanged();
				
				
			}
		});
		getContentPane().add(btnEnviarSolicitud);
		setBounds(100, 100, 796, 571);
		
		lblSaludo_1 = new JLabel("Elige cual es el componente que quieres hacer la solicitud de instalaci\u00F3n :");
		lblSaludo_1.setBounds(25, 82, 717, 16);
		getContentPane().add(lblSaludo_1);
		
		lblInfo = new JLabel("A\u00F1ade una descripcion para la solicitud de instalacion :");
		lblInfo.setBounds(26, 274, 716, 16);
		getContentPane().add(lblInfo);
		
		btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		btnVolver.setBounds(292, 486, 143, 25);
		getContentPane().add(btnVolver);
		
		txtDescripcion = new JTextPane();
		txtDescripcion.setEditable(false);
		txtDescripcion.setBounds(26, 303, 716, 121);
		getContentPane().add(txtDescripcion);
	}
}
