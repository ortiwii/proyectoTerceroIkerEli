package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.GestorDatos;
import model.Aula;
import model.Centro;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.CodingErrorAction;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;

public class TecnicoModificarAulaUI extends JFrame {
	private Aula aula;
	private JPanel contentPane;
	private JLabel lblCodigo;
	private JLabel lblTamaño;
	private JLabel lblCentro;
	private JLabel lblDisponibles;
	private JLabel lblSaludo;
	private JTextField txtCodigo;
	private JTextField txtTamaño;
	private JComboBox comboBox;
	private JTextField txtDisponibles;
	private JButton btnGuardar;
	private JButton btnVolver;
	private Vector<Centro>centros;
	
	public TecnicoModificarAulaUI (Aula aula) {
		this.aula = aula;
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 539, 455);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSaludo = new JLabel("Estas editando los datos del aula "+aula.getCodigo());
		lblSaludo.setBounds(46, 43, 322, 16);
		contentPane.add(lblSaludo);
		
		lblCodigo = new JLabel("Codigo :");
		lblCodigo.setBounds(46, 110, 56, 16);
		contentPane.add(lblCodigo);
		
		lblTamaño = new JLabel("Tama\u00F1o :");
		lblTamaño.setBounds(46, 163, 56, 16);
		contentPane.add(lblTamaño);
		
		lblCentro = new JLabel("Centro :");
		lblCentro.setBounds(46, 212, 56, 16);
		contentPane.add(lblCentro);
		
		lblDisponibles = new JLabel("Disponibles :");
		lblDisponibles.setBounds(46, 283, 81, 16);
		contentPane.add(lblDisponibles);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(160, 107, 300, 22);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtTamaño = new JTextField();
		txtTamaño.setBounds(160, 160, 300, 22);
		contentPane.add(txtTamaño);
		txtTamaño.setColumns(10);
		

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
		comboBox.setBounds(160, 212, 300, 22);
		contentPane.add(comboBox);
		
		txtDisponibles = new JTextField();
		txtDisponibles.setEditable(false);
		txtDisponibles.setBounds(160, 280, 300, 22);
		contentPane.add(txtDisponibles);
		txtDisponibles.setColumns(10);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBounds(136, 351, 97, 25);
		contentPane.add(btnGuardar);			
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(301, 351, 97, 25);
		contentPane.add(btnVolver);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Comprobamos los datos				
				if (!txtCodigo.getText().equals("")) {
					try {				
						Aula aulaPrevia = (Aula) aula.clone();
						int tamaño = Integer.parseInt(txtTamaño.getText());						
						Centro centro = centros.get(comboBox.getSelectedIndex());
						aula.setCentro(centro);
						if(aulaPrevia.getTamaño() != tamaño) { //Hay que actualizar los disponibles
							aula.setTamaño(tamaño);
							aula.setDisponibles(aula.getDisponibles()+(tamaño-aulaPrevia.getTamaño()));
						}						
						aula.setCodigo(txtCodigo.getText());
						boolean flag = GestorDatos.getInstance().actualizarAula(aula, aulaPrevia.getCodigo());
						if(flag) {
							JOptionPane.showMessageDialog(null, "El aula "+aula.getCodigo()+" se ha modificado satisfactoriamente");
							aula = aulaPrevia;
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "El codigo ya esta en uso o los datos no son correctos");
							aula = aulaPrevia;
							actualizar();
						}
						
						
					}catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(null, "El tamaño debe de ser de formato numerico");
						actualizar();
					}catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Los datos no son correctos");
						actualizar();
					}
					

				}else {
					JOptionPane.showMessageDialog(null, "Los datos no son correctos");
					actualizar();
				}
				
			}});
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		actualizar();
	}
	private void actualizar() {
		txtCodigo.setText(aula.getCodigo());
		txtTamaño.setText(Integer.toString(aula.getTamaño()));
		
		txtDisponibles.setText(Integer.toString(aula.getDisponibles()));
		
	}
}
