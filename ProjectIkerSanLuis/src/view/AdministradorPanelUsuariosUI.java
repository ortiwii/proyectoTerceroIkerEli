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
import model.Administrador;
import model.GestorTablas;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class AdministradorPanelUsuariosUI extends JFrame{

	private Administrador administrador;
	private DefaultTableModel modelo;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable jtableAulas;
	private Vector vColumnas;
	private Vector vContenido;
	private String valor;
	private int eleccion;
	private JButton btnVolver;
	private JButton btnCrearNuevoUsuario;
	private JButton btnModificarUsuario;
	private JLabel lblSaludo;
	private JLabel lblAdministrador;
	
	public AdministradorPanelUsuariosUI (Administrador administrador) {
		this.administrador = administrador;
		
		initialize();
	}
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//Datos de la tabla
		vContenido = gestorTablas.obtenerCuerpoUsuariosEncriptado();
		vColumnas = gestorTablas.obtenerCabecerasUsuarios();
		
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 113, 716, 279);
		
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
		            btnModificarUsuario.setEnabled(true);
		            
		        } 
				
			}
		});
		
		setContentPane(contentPane);									
		setBounds(100, 100, 822, 545);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(665, 437, 97, 25);
		contentPane.add(btnVolver);
		
		btnCrearNuevoUsuario = new JButton("Crear nuevo usuario");
		btnCrearNuevoUsuario.setBounds(46, 437, 170, 25);
		contentPane.add(btnCrearNuevoUsuario);
		
		btnModificarUsuario = new JButton("Modificar usuario");
		btnModificarUsuario.setEnabled(false);
		btnModificarUsuario.setBounds(247, 437, 170, 25);
		contentPane.add(btnModificarUsuario);
		
		lblSaludo = new JLabel("PANEL DE USUARIOS");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(46, 42, 716, 21);
		contentPane.add(lblSaludo);
		
		lblAdministrador = new JLabel("Administrador : "+administrador.getUser());
		lblAdministrador.setBounds(46, 76, 716, 16);
		contentPane.add(lblAdministrador);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();			
			}
		});
		
		btnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				dispose();
				AdministradorModificarUsuarioUI administradorModificarUsuarioUI = new AdministradorModificarUsuarioUI(administrador, GestorDatos.getInstance().getUsuarioCompleto(valor));
				administradorModificarUsuarioUI.setVisible(true);
			}
		});
		
		btnCrearNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdministradorCrearUsuarioUI administradorCrearUsuarioUI = new AdministradorCrearUsuarioUI(administrador);
				administradorCrearUsuarioUI.setVisible(true);
				
			}});
	}
}
