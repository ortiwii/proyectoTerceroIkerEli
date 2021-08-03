package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.GestorDatos;
import model.AlmacenProveedores;
import model.Lote;
import model.Proveedor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class ProveedorModificarComponenteAlmacenUI extends JFrame {
	
	private Proveedor proveedor;
	private AlmacenProveedores almacenProveedores;
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
	private JTextPane txtInfor;
	private JButton btnGuardar;
	private JButton btnVolver;
	private JButton btnEliminarComponente;
	private Vector<Lote> listaLotes = new Vector<>();
	
	public ProveedorModificarComponenteAlmacenUI (Proveedor proveedor, AlmacenProveedores almacenProveedores) {
		this.proveedor = proveedor;
		this.almacenProveedores = almacenProveedores;
		initialize();
	}
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
					
		setBounds(100, 100, 357, 404);	
		contentPane.setLayout(null);
		
		lblSaludo = new JLabel("MODIFICAR COMPONENTE ALMACEN");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(22, 25, 753, 16);
		contentPane.add(lblSaludo);
		
		lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(22, 75, 104, 16);
		contentPane.add(lblNombre);
		
		lblStock = new JLabel("Lote :");
		lblStock.setBounds(22, 104, 104, 16);
		contentPane.add(lblStock);
		
		lblClase = new JLabel("Clase :");
		lblClase.setBounds(22, 133, 104, 16);
		contentPane.add(lblClase);
		
		lblCantidad = new JLabel("Cantidad :");
		lblCantidad.setBounds(22, 162, 104, 16);
		contentPane.add(lblCantidad);
		
		lblInformacion = new JLabel("Informacion :");
		lblInformacion.setBounds(22, 190, 104, 16);
		contentPane.add(lblInformacion);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(130, 72, 183, 22);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		listaLotes = GestorDatos.getInstance().getLotesProveedor(proveedor);
		cboxStock = new JComboBox(listaLotes);
		cboxStock.setBounds(130, 101, 183, 22);
		contentPane.add(cboxStock);
		
		String[] listaClase = {"Hardware", "Software"};
		cboxClase = new JComboBox(listaClase);
		cboxClase.setBounds(130, 130, 183, 22);
		contentPane.add(cboxClase);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(130, 159, 183, 22);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		txtInfor = new JTextPane();
		txtInfor.setBounds(130, 190, 183, 71);
		contentPane.add(txtInfor);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(57, 310, 97, 25);
		contentPane.add(btnGuardar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(185, 310, 97, 25);
		contentPane.add(btnVolver);
		
		btnEliminarComponente = new JButton("Eliminar componente");
		btnEliminarComponente.setBounds(88, 274, 172, 25);
		contentPane.add(btnEliminarComponente);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ProveedorGestionAlmacenUI proveedorGestionAlmacenUI = new ProveedorGestionAlmacenUI(proveedor);
				proveedorGestionAlmacenUI.setVisible(true);
			}});
		
		btnEliminarComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarComponente();

			}});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtNombre.getText().equals("")) {
					try {
						Integer cant = Integer.parseInt(txtCantidad.getText());
						if (cant > 0) {
							AlmacenProveedores compAnterior = (AlmacenProveedores) almacenProveedores.clone();
							almacenProveedores.setNombre(txtNombre.getText());
							almacenProveedores.setCantidad(Integer.parseInt(txtCantidad.getText()));
							almacenProveedores.setInformacion(txtInfor.getText());
							almacenProveedores.setLote((Lote) cboxStock.getSelectedItem());
							if (cboxClase.getSelectedIndex() == 0) {
								almacenProveedores.setClase("h");
							}else {
								almacenProveedores.setClase("s");
							}
							
							boolean flag = GestorDatos.getInstance().actualizarAlmacen(almacenProveedores, almacenProveedores.getIdComponente());
							if (flag) {
								JOptionPane.showMessageDialog(null, "Componente del almacen actualizado");
							}else {
								JOptionPane.showMessageDialog(null, "No se ha podido actualizar el componente");
							}
							
						}else if (cant == 0) {
							// Eliminar 
							// 0 = si,1 = no 
							JOptionPane.showMessageDialog(null, "La cantidad es 0, con lo que se va a borrar el componente del almacen");
							eliminarComponente();
						}else {
							JOptionPane.showMessageDialog(null, "La cantidad no puede ser negativa");
							actualizar();
						}
						
						
					}catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "La cantidad debe estar en formato numerico");
						actualizar();
					}
				}else {
					JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio");
					actualizar();
				}
			}});
		
		this.actualizar();
	}
	private void actualizar() {
		txtNombre.setText(almacenProveedores.getNombre());
		boolean flag = false;
		for(int i = 0; i < listaLotes.size() && !flag; i ++) {
			Lote act = listaLotes.get(i);
			if (act.getIdStock() == almacenProveedores.getLote().getIdStock()) {
				cboxStock.setSelectedIndex(i);
				flag = true;
			}						
		}
		txtCantidad.setText(Integer.toString(almacenProveedores.getCantidad()));
		if (almacenProveedores.getClase().equals("h")) {
			cboxClase.setSelectedIndex(0);
		}else {
			cboxClase.setSelectedIndex(1);
		}
		txtInfor.setText(almacenProveedores.getInformacion());
		
	}
	private void eliminarComponente() {
		// 0 = si,1 = no 
		int eleccion = JOptionPane.showConfirmDialog(null, "Seguro que quieres eliminar "+almacenProveedores.getCantidad()+" unidades del componente "+almacenProveedores.getNombre()+" del almacen ?", "Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (eleccion == 0) {
			boolean flag = GestorDatos.getInstance().eliminarAlmacen(almacenProveedores);
			if (flag) {
				JOptionPane.showMessageDialog(null, "Se ha eliminado el componente "+almacenProveedores.getNombre()+" del almacen");
			}else {
				JOptionPane.showMessageDialog(null, "No se ha podido eliminado el componente "+almacenProveedores.getNombre()+" del almacen");
			}
		}else {
			actualizar();
		}
		dispose();
		ProveedorGestionAlmacenUI proveedorGestionAlmacenUI = new ProveedorGestionAlmacenUI(proveedor);
		proveedorGestionAlmacenUI.setVisible(true);
	}
}
