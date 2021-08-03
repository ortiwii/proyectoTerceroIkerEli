package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import db.GestorDatos;
import model.AlmacenProveedores;
import model.Lote;
import model.Proveedor;

public class ProveedorAñadirComponenteAlAlmacenUI extends JFrame {

	private Proveedor proveedor;
	private Lote lote;
	private JPanel contentPane;
	private JLabel lblSaludo;
	private JLabel lblNombre;
	private JLabel lblStock;
	private JLabel lblClase;
	private JLabel lblCantidad;
	private JLabel lblInformacion;
	private JTextField txtNombre;
	private JComboBox cboxStock;
	private JComboBox cboxClase;
	private JTextField txtCantidad;
	private JTextPane textPane;
	private JButton btnGuardar;
	private JButton btnVolver;
	private Vector<Lote> listaLotes;
	private JLabel lblIdentificador;
	private JTextField txtIdentificador;
	
	public ProveedorAñadirComponenteAlAlmacenUI (Proveedor proveedor) {		
		this.proveedor = proveedor;
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
					
		setBounds(100, 100, 357, 417);	
		contentPane.setLayout(null);
		
		lblSaludo = new JLabel("A\u00D1ADIR COMPONENTE AL ALMACEN");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(22, 25, 291, 16);
		contentPane.add(lblSaludo);
		
		lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(22, 107, 104, 16);
		contentPane.add(lblNombre);
		
		lblStock = new JLabel("Stock :");
		lblStock.setBounds(22, 136, 104, 16);
		contentPane.add(lblStock);
		
		lblClase = new JLabel("Clase :");
		lblClase.setBounds(22, 165, 104, 16);
		contentPane.add(lblClase);
		
		lblCantidad = new JLabel("Cantidad :");
		lblCantidad.setBounds(22, 194, 104, 16);
		contentPane.add(lblCantidad);
		
		lblInformacion = new JLabel("Informacion :");
		lblInformacion.setBounds(22, 222, 104, 16);
		contentPane.add(lblInformacion);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(130, 104, 183, 22);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		listaLotes = GestorDatos.getInstance().getLotesProveedor(proveedor);
		cboxStock = new JComboBox(listaLotes);
		cboxStock.setBounds(130, 133, 183, 22);
		contentPane.add(cboxStock);
		
		String[] listaClase = {"Hardware", "Software"};
		cboxClase = new JComboBox(listaClase);
		cboxClase.setBounds(130, 162, 183, 22);
		contentPane.add(cboxClase);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(130, 191, 183, 22);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		textPane = new JTextPane();
		textPane.setBounds(130, 222, 183, 71);
		contentPane.add(textPane);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(57, 321, 97, 25);
		contentPane.add(btnGuardar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(183, 321, 97, 25);
		contentPane.add(btnVolver);
		
		lblIdentificador = new JLabel("Identificador :");
		lblIdentificador.setBounds(22, 78, 104, 16);
		contentPane.add(lblIdentificador);
		
		txtIdentificador = new JTextField();
		txtIdentificador.setBounds(130, 75, 183, 22);
		contentPane.add(txtIdentificador);
		txtIdentificador.setColumns(10);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id = null;
				try {
					id = Integer.parseInt(txtIdentificador.getText());
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "El identificador debe ser numerico");
				}
				if (id != null) {
					if (!txtNombre.getText().equals("")) {
						if (cboxClase.getSelectedIndex() != -1) {
							if (cboxStock.getSelectedIndex() != -1) {
								try {
									Integer cant = Integer.parseInt(txtCantidad.getText());
									if (cant > 0) {
										AlmacenProveedores almacenProveedores = null;
										if (cboxClase.getSelectedIndex() == 0) {
											almacenProveedores = new AlmacenProveedores(id, (Lote) cboxStock.getSelectedItem(), cant, txtNombre.getText(), "h", textPane.getText());
										}else {
											almacenProveedores = new AlmacenProveedores(id, (Lote) cboxStock.getSelectedItem(), cant, txtNombre.getText(), "s", textPane.getText());
										}
										boolean flag = GestorDatos.getInstance().insertarComponenteAlmacen(almacenProveedores);
										if (flag) {
											JOptionPane.showMessageDialog(null, "Se ha añadido con exito al almacen");
										}else{
											JOptionPane.showMessageDialog(null, "No se ha podido añadir al almacen");
										}
										txtIdentificador.setText("");
										txtNombre.setText("");
										txtCantidad.setText("");
										textPane.setText("");
										cboxClase.setSelectedIndex(-1);
										cboxStock.setSelectedIndex(-1);
									}else {
										JOptionPane.showMessageDialog(null, "La cantidad tiene que ser mayor a 0");
										txtCantidad.setText("");
									}
								}catch (Exception ex) {
									JOptionPane.showMessageDialog(null, "La cantidad no es numerica");
									txtCantidad.setText("");
								}
							}else {
								JOptionPane.showMessageDialog(null, "Selecciona un stock");
							}
						}else {
							JOptionPane.showMessageDialog(null, "Selecciona la clase");
						}
					}else {
						JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio");
					}
				}
			}});
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ProveedorGestionAlmacenUI proveedorGestionAlmacenUI = new ProveedorGestionAlmacenUI(proveedor);
				proveedorGestionAlmacenUI.setVisible(true);
			}});

	}

}
