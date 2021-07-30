package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.GestorDatos;
import model.Aula;
import model.Centro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TecnicoCrearAulaUI extends JFrame{
	
	private JPanel contentPane;
	private JLabel lblSaludo;
	private JLabel lblNombre;
	private JLabel lblTama�o;
	private JLabel lblCentro;
	private JComboBox comboBox;
	private JTextField txtNombre;
	private JTextField txtTama�o;
	private JButton btnCrearAula;
	
	private Vector<Centro> centros;
	private JButton btnVolver;
	
	public TecnicoCrearAulaUI () {
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 539, 455);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSaludo = new JLabel("Este es el centro de creacion de aulas");
		lblSaludo.setBounds(35, 32, 266, 16);
		contentPane.add(lblSaludo);
		
		lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(159, 107, 56, 16);
		contentPane.add(lblNombre);
		
		lblTama�o = new JLabel("Numero maximo de equipos:");
		lblTama�o.setBounds(42, 174, 173, 16);
		contentPane.add(lblTama�o);
		
		lblCentro = new JLabel("Centro :");
		lblCentro.setBounds(159, 249, 56, 16);
		contentPane.add(lblCentro);

		centros = GestorDatos.getInstance().getCentros();
		Vector<String> nombresCentros = new Vector<>();
		Iterator<Centro> itr = centros.iterator();
		try {
			while(itr.hasNext()) {
				Centro act = itr.next();
				nombresCentros.add(act.getNombre());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}			
		comboBox = new JComboBox(nombresCentros);
		comboBox.setBounds(264, 246, 183, 22);
		contentPane.add(comboBox);			
		
		txtNombre = new JTextField();
		txtNombre.setBounds(264, 104, 116, 22);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTama�o = new JTextField();
		txtTama�o.setBounds(264, 171, 116, 22);
		contentPane.add(txtTama�o);
		txtTama�o.setColumns(10);
		
		btnCrearAula = new JButton("CREAR AULA");
		btnCrearAula.setBounds(189, 338, 149, 25);
		contentPane.add(btnCrearAula);
		
		btnCrearAula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Comprobacion de datos
			    try{
			    	if (!txtNombre.getText().equals("")) {
			    		int tama�o = Integer.parseInt(txtTama�o.getText());
			    		
			    		// Probar
			    		Aula aula = new Aula(txtNombre.getText(), tama�o, tama�o, centros.get(comboBox.getSelectedIndex()));
			    		boolean flag = GestorDatos.getInstance().crearAula(aula);
			    		if (flag) {
			    			JOptionPane.showMessageDialog(null, "Se ha creado el aula "+txtNombre.getText()+" satisfactoriamente");
			    			txtNombre.setText("");
			    			txtTama�o.setText("");
			    		}else {
			    			JOptionPane.showMessageDialog(null, "El codigo del aula "+txtNombre.getText()+" ya esta en uso");
			    			txtNombre.setText("");
			    			txtTama�o.setText("");
			    		}
			    	}else {
			    		JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio");
			    	}
			        
			        
			        
			    }catch(NumberFormatException ne){
			    	JOptionPane.showMessageDialog(null, "El dato introducido en el tama�o no es correcto");
			    	txtTama�o.setText("");
			    }
				
			}});
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(189, 370, 149, 25);
		contentPane.add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}	
}
