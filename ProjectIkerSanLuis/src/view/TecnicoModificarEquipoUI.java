package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import db.GestorDatos;
import model.Aula;
import model.Equipo;
import model.GestorTablas;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;

public class TecnicoModificarEquipoUI extends JFrame{

	private GestorTablas gestorTablas = new GestorTablas();
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JTable jtableUsuarios;
	private Vector vColumnas;
	private Vector vContenido;
	private String valor;
	private int eleccion;
	private Equipo equipo;
	private JPanel contentPane;
	private JLabel lblSaludo;
	private JLabel lblId;
	private JLabel lblNombre;
	private JLabel lblTipoDeAlmacenamiento;
	private JLabel lblMemoriaRam;
	private JLabel lblDireccionIp;
	private JLabel lblAulaAsignada;
	private JTextField txtIdequipo;
	private JTextField txtNombre;
	private JTextField txtAlmacenamiento;
	private JTextField txtRam;
	private JTextField txtIp;
	private JTextField txtAula;
	private JButton btnAsignarUsuario;
	private JButton btnGuardar;
	private JButton btnVolver;
	private JButton btnAsignar;
	
	public TecnicoModificarEquipoUI (Equipo equipo) {
		this.equipo = equipo;
		
		
		initialize();
	}
	private void initialize() {	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		
		//Datos de la tabla
		vContenido = gestorTablas.obtenerCuerpoUsuariosPublico(); 
		vColumnas = gestorTablas.obtenerCabecerasUsuariosPublico();
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(394, 113, 534, 391);
		
		contentPane.remove(scrollPane);
		
		contentPane.add(scrollPane);
		
		jtableUsuarios = new JTable(vContenido,vColumnas);
		scrollPane.setViewportView(jtableUsuarios);
		
		modelo = (DefaultTableModel) jtableUsuarios.getModel();
		
		jtableUsuarios.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				// Obtenemos el primer dato del renglon seleccionado
		        if (jtableUsuarios.getSelectedRow() != -1) {
		            valor = (String) modelo.getValueAt(jtableUsuarios.getSelectedRow(), 0);
		            eleccion = jtableUsuarios.getSelectedRow();
		            
		        } 
				
			}
		});
		jtableUsuarios.setEnabled(false);
		setBounds(100, 100, 985, 579);	
		setContentPane(contentPane);		
		
		lblSaludo = new JLabel("MODIFICAR DATOS DEL EQUIPO");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(30, 25, 285, 29);
		contentPane.add(lblSaludo);
		
		lblId = new JLabel("Identificador del equipo :");
		lblId.setBounds(30, 116, 152, 16);
		contentPane.add(lblId);
		
		lblNombre = new JLabel("Nombre del equipo :");
		lblNombre.setBounds(30, 172, 152, 16);
		contentPane.add(lblNombre);
		
		lblTipoDeAlmacenamiento = new JLabel("Almacenamiento :");
		lblTipoDeAlmacenamiento.setBounds(30, 230, 152, 16);
		contentPane.add(lblTipoDeAlmacenamiento);
		
		lblMemoriaRam = new JLabel("Memoria RAM :");
		lblMemoriaRam.setBounds(30, 286, 161, 16);
		contentPane.add(lblMemoriaRam);
		
		lblDireccionIp = new JLabel("Direccion IP :");
		lblDireccionIp.setBounds(30, 348, 157, 16);
		contentPane.add(lblDireccionIp);
		
		lblAulaAsignada = new JLabel("Aula asignada :");
		lblAulaAsignada.setBounds(30, 397, 157, 16);
		contentPane.add(lblAulaAsignada);
		
		txtIdequipo = new JTextField();
		txtIdequipo.setBounds(219, 113, 137, 22);
		contentPane.add(txtIdequipo);
		txtIdequipo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(219, 169, 137, 22);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtAlmacenamiento = new JTextField();
		txtAlmacenamiento.setBounds(219, 227, 137, 22);
		contentPane.add(txtAlmacenamiento);
		txtAlmacenamiento.setColumns(10);
		
		txtRam = new JTextField();
		txtRam.setBounds(219, 283, 137, 22);
		contentPane.add(txtRam);
		txtRam.setColumns(10);
		
		txtIp = new JTextField();
		txtIp.setBounds(219, 345, 137, 22);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
		
		txtAula = new JTextField();
		txtAula.setBounds(219, 394, 137, 22);
		contentPane.add(txtAula);
		txtAula.setColumns(10);
		
		btnAsignarUsuario = new JButton("Asignar Usuario");
		btnAsignarUsuario.setBounds(219, 446, 137, 25);
		contentPane.add(btnAsignarUsuario);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(30, 446, 116, 25);
		contentPane.add(btnGuardar);
					
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(30, 479, 116, 25);
		contentPane.add(btnVolver);
		
		btnAsignar = new JButton("Asignar");
		btnAsignar.setEnabled(false);
		btnAsignar.setBounds(218, 479, 138, 25);
		contentPane.add(btnAsignar);
		
		btnVolver.addActionListener(new ActionListener() {					
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}}); 
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipo equipoPrevio = null;
				try {
					equipoPrevio = (Equipo) equipo.clone();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
				try {
										
					Integer id = Integer.parseInt(txtIdequipo.getText());
					equipo.setIdEquipo(id);
					
					Integer almacenamiento = Integer.parseInt(txtAlmacenamiento.getText());
					equipo.setAlmacenamiento(almacenamiento);
					
					Integer ram = Integer.parseInt(txtRam.getText());
					equipo.setRam(ram);
					
					if (!txtNombre.getText().equals("") && !txtIp.getText().equals("") && !txtAula.getText().equals("")) {
						// Guardar los datos
						equipo.setNombre(txtNombre.getText());
						equipo.setIp(txtIp.getText());
						Aula aula = GestorDatos.getInstance().getAula(txtAula.getText());
						if (aula != null) {
							equipo.setAula(aula);
							boolean flag = GestorDatos.getInstance().actualizarEquipo(equipo, equipoPrevio.getIdEquipo());
							if (flag) {
								JOptionPane.showMessageDialog(null, "El equipo se ha actualizado correctamente");							
								
							}else {
								throw new Exception();
							}
						}else {
							throw new Exception();
						}
						
					}else {						
						throw new Exception();
					}
					
					
				}catch (NumberFormatException ne) {					
					equipo = equipoPrevio;
					actualizarDatos();
					JOptionPane.showMessageDialog(null, "Los datos no son correctos");
//					ne.printStackTrace();
				}
				catch (Exception ex) {
					equipo = equipoPrevio;
					actualizarDatos();
					JOptionPane.showMessageDialog(null, "Los datos no son correctos");
//					ex.printStackTrace();
				}
				
				
				
			}});
		
		btnAsignarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				

				jtableUsuarios.setEnabled(true);
				btnAsignar.setEnabled(true);
				
			}});
		
		btnAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				Usuario usuario = GestorDatos.getInstance().getUsuarioCompleto(valor);
				usuario.setEquipo(equipo);
				GestorDatos.getInstance().actualizarUsuario(usuario);
				JOptionPane.showMessageDialog(null, "Se ha asignado el equipo "+equipo.getIdEquipo()+" a el usuario "+usuario.getUser()+" correctamente");
				btnAsignar.setEnabled(false);

				// Actualizar tabla
				vContenido = gestorTablas.obtenerCuerpoUsuariosPublico();
				modelo.setDataVector(vContenido, vColumnas);
				modelo.fireTableDataChanged();
			}});
		
		actualizarDatos();
		
	}
	
	private void actualizarDatos() {
		txtIdequipo.setText(Integer.toString(equipo.getIdEquipo()));
		txtNombre.setText(equipo.getNombre());
		txtAlmacenamiento.setText(Integer.toString(equipo.getAlmacenamiento()));
		txtRam.setText(Integer.toString(equipo.getRam()));
		txtIp.setText(equipo.getIp());
		txtAula.setText(equipo.getAula().getCodigo());
		
	}
}

