package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import db.GestorDatos;
import model.Aula;
import model.Componente;
import model.Equipo;
import model.Tecnico;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class TecnicoCrearEquipoUI extends JFrame {
	private Tecnico tecnico;
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
	private JComboBox cboxAula;
	private JLabel lblComponentes;
	private JComboBox cboxComponentes;
	private JButton btnAadir;
	private JTextPane txtComponentes;
	private JButton btnCrearEquipo;
	private JButton btnVolver;
	private Vector<Componente> componentesAñadidos = new Vector<>();
	
	public TecnicoCrearEquipoUI (Tecnico tecnico) {
		this.tecnico = tecnico;
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		setBounds(100, 100, 856, 578);	
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSaludo = new JLabel("CREAR UN NUEVO EQUIPO");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(36, 32, 201, 19);
		contentPane.add(lblSaludo);
		
		lblId = new JLabel("Identificador del equipo :");
		lblId.setBounds(36, 93, 143, 16);
		contentPane.add(lblId);
		
		lblNombre = new JLabel("Nombre del equipo :");
		lblNombre.setBounds(36, 156, 117, 16);
		contentPane.add(lblNombre);
		
		lblTipoDeAlmacenamiento = new JLabel("Almacenamiento :");
		lblTipoDeAlmacenamiento.setBounds(36, 226, 150, 16);
		contentPane.add(lblTipoDeAlmacenamiento);
		
		lblMemoriaRam = new JLabel("Memoria RAM :");
		lblMemoriaRam.setBounds(36, 296, 89, 16);
		contentPane.add(lblMemoriaRam);
		
		lblDireccionIp = new JLabel("Direccion IP :");
		lblDireccionIp.setBounds(36, 353, 76, 16);
		contentPane.add(lblDireccionIp);
		
		lblAulaAsignada = new JLabel("Aula :");
		lblAulaAsignada.setBounds(36, 420, 89, 16);
		contentPane.add(lblAulaAsignada);
		
		txtIdequipo = new JTextField();
		txtIdequipo.setBounds(227, 90, 116, 22);
		contentPane.add(txtIdequipo);
		txtIdequipo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(227, 153, 116, 22);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtAlmacenamiento = new JTextField();
		txtAlmacenamiento.setBounds(227, 223, 116, 22);
		contentPane.add(txtAlmacenamiento);
		txtAlmacenamiento.setColumns(10);
		
		txtRam = new JTextField();
		txtRam.setBounds(227, 293, 116, 22);
		contentPane.add(txtRam);
		txtRam.setColumns(10);
		
		txtIp = new JTextField();
		txtIp.setBounds(227, 350, 116, 22);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
		
		Vector<Aula> aulas = GestorDatos.getInstance().getAulas(this.tecnico.getCentro());
		
		cboxAula = new JComboBox(aulas);
		cboxAula.setBounds(227, 417, 117, 22);
		contentPane.add(cboxAula);
		
		lblComponentes = new JLabel("Componentes :");
		lblComponentes.setBounds(415, 93, 98, 16);
		contentPane.add(lblComponentes);
		
		Vector<Componente> componentes = GestorDatos.getInstance().getComponentesCentroLibres(tecnico.getCentro());
		cboxComponentes = new JComboBox(componentes);
		cboxComponentes.setBounds(552, 90, 237, 22);
		contentPane.add(cboxComponentes);
		
		btnAadir = new JButton("A\u00F1adir");
		btnAadir.setBounds(415, 152, 89, 25);
		contentPane.add(btnAadir);
		
		txtComponentes = new JTextPane();
		txtComponentes.setEditable(false);
		txtComponentes.setBounds(552, 152, 237, 250);
		contentPane.add(txtComponentes);
		
		btnCrearEquipo = new JButton("Crear equipo");
		btnCrearEquipo.setBounds(165, 478, 178, 25);
		contentPane.add(btnCrearEquipo);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(415, 478, 143, 25);
		contentPane.add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Componente componente = (Componente) cboxComponentes.getSelectedItem();
				if (!componentesAñadidos.contains(componente)) {
					componentesAñadidos.add(componente);
					componentes.remove(componente);
				}
				
//				cboxComponentes
				actualizarComponentes();
			}});
		
		btnCrearEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {					
					Integer id = Integer.parseInt(txtIdequipo.getText());
						
					Integer almacenamiento = Integer.parseInt(txtAlmacenamiento.getText());					
						
					Integer ram = Integer.parseInt(txtRam.getText());
						
						
					if (!txtNombre.getText().equals("") && !txtIp.getText().equals("") ) {
						// Guardar los datos
						
						Aula aula = (Aula) cboxAula.getSelectedItem();						
						if (aula != null) {
							Equipo equipo = new Equipo(id, txtNombre.getText(), almacenamiento, ram, txtIp.getText(), aula);							
							boolean flag = GestorDatos.getInstance().crearEquipo(equipo);
							if (flag) {
								// Actualizar componentes
								Iterator<Componente>itr=componentesAñadidos.iterator();
								while(itr.hasNext()) {
									Componente act = itr.next();
									act.setEquipo(equipo);
									boolean flag2 = GestorDatos.getInstance().actualizarComponente(act, act.getIdComponente());
									if (!flag2) {
										throw new Exception();
									}
									
								}
								
								JOptionPane.showMessageDialog(null, "El equipo "+equipo.getIdEquipo()+" se ha creado correctamente");							
								
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
						JOptionPane.showMessageDialog(null, "Los datos no son correctos");
					}
					catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Los datos no son correctos");
					}
					
					
				
			}});
	}
	private void actualizarComponentes() {
		txtComponentes.setText("");
		Iterator<Componente> itr = componentesAñadidos.iterator();
		String txt = "";
		while(itr.hasNext()) {
			Componente act = itr.next();
			txt = txt + act.getNombre() + "\n";
		}
		txtComponentes.setText(txt);
	}
}
