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
import model.Aula;
import model.GestorTablas;
import model.Usuario;
import javax.swing.JLabel;

public class UsuarioMostrarAulasUI extends JFrame{
	
	private Usuario usuario;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable jtableAulas;
	private Vector vColumnas;
	private Vector vContenido;
	private String valor;
	private JButton btnVerEquipos;
	private JButton btnVolver;
	private JLabel lblSaludo;
	
	public UsuarioMostrarAulasUI (Usuario usuario) {
	
		this.usuario = usuario;
		System.out.println(usuario.toString());
		vContenido = gestorTablas.obtenerCuerpoAulas(usuario.getCentro());			
		initialize();
	}	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Datos de la tabla
		vColumnas = gestorTablas.obtenerCabecerasAulas();
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 49, 716, 279);
		contentPane.add(scrollPane);
		
		jtableAulas = new JTable(vContenido,vColumnas);
		scrollPane.setViewportView(jtableAulas);
		
		DefaultTableModel modelo = (DefaultTableModel) jtableAulas.getModel();
		
		jtableAulas.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				// Obtenemos el primer dato del renglon seleccionado
		        if (jtableAulas.getSelectedRow() != -1) {
		            valor = (String) modelo.getValueAt(jtableAulas.getSelectedRow(), 0);
		            
		            btnVerEquipos.setVisible(true);
		            
		            // Lo imprimimos en pantalla
		            System.out.println(valor);
		        } 
				
			}
		});
		
		
		
		
		
		setBounds(100, 100, 783, 475);
		
		
		btnVerEquipos = new JButton("VER EQUIPOS DEL AULA");
		btnVerEquipos.setBounds(295, 341, 171, 25);
		contentPane.add(btnVerEquipos);
		btnVerEquipos.setVisible(false);
				
		btnVerEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {					
					// TODO Ver equipos							
					String aulaCod = (String) modelo.getValueAt(jtableAulas.getSelectedRow(), 0);
					Aula aula = GestorDatos.getInstance().getAula(aulaCod);
					UsuarioVerEquiposAulaUI usuarioVerEquiposAulaUI = new UsuarioVerEquiposAulaUI(aula);
					usuarioVerEquiposAulaUI.setVisible(true);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 			
			}
		});
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(332, 379, 107, 25);
		contentPane.add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		lblSaludo = new JLabel("Estas viendo las aulas del centro "+usuario.getCentro().getNombre());
		lblSaludo.setBounds(22, 20, 427, 16);
		contentPane.add(lblSaludo);
	}
}