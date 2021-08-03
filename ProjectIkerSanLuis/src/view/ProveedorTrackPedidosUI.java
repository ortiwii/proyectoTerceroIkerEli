package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import db.GestorDatos;
import model.DateLabelFormatter;
import model.GestorTablas;
import model.Proveedor;
import model.Venta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ProveedorTrackPedidosUI extends JFrame {
	
	private Proveedor proveedor;
	private GestorTablas gestorTablas = new GestorTablas();
	private JPanel contentPane;
	
	private DefaultTableModel modelo;
	private JScrollPane tabla;
	private JTable jtable;
	private Vector vColumnas;
	private Vector vContenido;
	private String valor;
	private JLabel lblSaludo;
	private JLabel lblProveedor;
	private JButton btnVolver;
	private JComboBox cboxEstado;
	private JLabel lblActualizarEstado;
	private String [] estados = {"Aceptada", "En curso", "En almacen", "Enviada", "Entregada", "Cerrada"};
	private JButton btnActualizar;
	private JLabel lblFecha;
	
	private JDatePickerImpl datePicker; 
	
	public ProveedorTrackPedidosUI (Proveedor proveedor) {
		this.proveedor = proveedor;
		

		initialize();
	}
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
					
		setBounds(100, 100, 771, 398);				
		
		tabla = new JScrollPane();
		tabla.setBounds(26, 96, 701, 161);
		contentPane.add(tabla);
		
		try {					
			
			vContenido = gestorTablas.obtenerCuerpoVentas(proveedor);
			vColumnas = gestorTablas.obtenerCabecerasVentas();
			contentPane.setLayout(null);
			
			jtable = new JTable(vContenido, vColumnas);
			tabla.setViewportView(jtable);
			
			modelo = (DefaultTableModel) jtable.getModel();
			
			jtable.addMouseListener((MouseListener) new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {

					if (jtable.getSelectedRow() != -1) {
						valor = (String) modelo.getValueAt(jtable.getSelectedRow(), 0);					            
						cboxEstado.setEnabled(true);
						setEstado();
						
					}
				}});


		} catch (Exception e1) {
			e1.printStackTrace();
		} 

		lblSaludo = new JLabel("LISTADO DE VENTAS");
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSaludo.setBounds(26, 24, 716, 16);
		contentPane.add(lblSaludo);
		
		lblProveedor = new JLabel("Proveedor : "+proveedor.getUser());
		lblProveedor.setBounds(26, 55, 716, 16);
		contentPane.add(lblProveedor);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(597, 302, 130, 25);
		contentPane.add(btnVolver);
		
		cboxEstado = new JComboBox(estados);
		cboxEstado.setSelectedIndex(-1);
		cboxEstado.setEnabled(false);
		cboxEstado.setBounds(26, 303, 256, 22);
		contentPane.add(cboxEstado);
		
		lblActualizarEstado = new JLabel("Actualizar Estado :");
		lblActualizarEstado.setBounds(26, 270, 135, 16);
		contentPane.add(lblActualizarEstado);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(597, 270, 130, 25);
		contentPane.add(btnActualizar);		
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setLocation(332, 302);
		datePicker.setSize(223, 25);
		contentPane.add(datePicker);
		datePicker.setEnabled(false);
		datePicker.setVisible(false);
        contentPane.add(datePicker);
		
		lblFecha = new JLabel("Elige la fecha de cierre :");
		lblFecha.setBounds(329, 270, 226, 16);
		contentPane.add(lblFecha);
		lblFecha.setVisible(false);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});

		cboxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnActualizar.setEnabled(true);
				if (((String)cboxEstado.getSelectedItem()).equals("Cerrada")) {
					Venta venta = GestorDatos.getInstance().getVenta(Integer.parseInt(valor));
					datePicker.setEnabled(true);
					datePicker.setVisible(true);
					lblFecha.setVisible(true);
					if (venta.getFechaCierre() != null) {
						model.setValue(venta.getFechaCierre().getTime());
					}else {
						model.setValue(null);
					}
				}else {
					datePicker.setEnabled(false);
					datePicker.setVisible(false);
					lblFecha.setVisible(false);
				}
			}});
		
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Venta venta = GestorDatos.getInstance().getVenta(Integer.parseInt(valor));
					if (!venta.getEstado().equals((String) cboxEstado.getSelectedItem())) {
						venta.setEstado((String) cboxEstado.getSelectedItem());
						if (venta.getEstado().equals("Cerrada")) {
							
							SimpleDateFormat f = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
							Calendar fechaCierre = Calendar.getInstance();
							fechaCierre.setTime(f.parse(datePicker.getModel().getValue().toString()));
							venta.setFechaCierre(fechaCierre);							
						}
						boolean flag = GestorDatos.getInstance().actualizarVenta(venta, venta.getIdVenta());
						if (flag) {
							JOptionPane.showMessageDialog(null, "Se ha actualizado la venta");
						}else {
							JOptionPane.showMessageDialog(null, "No se ha podido actualizar la venta");
						}
					}
					actualizar();
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Tienes que elegir la fecha de cierre de la venta");					
				}
			}});
		

		
		
		
		
         

	}
	
	private void setEstado() {
		Venta venta = GestorDatos.getInstance().getVenta(Integer.parseInt(valor));
		boolean flag = false;
		for (int i = 0; i < estados.length && !flag; i++) {
			if (venta.getEstado().equals(estados[i])) {
				cboxEstado.setSelectedIndex(i);
				flag = true;
			}
		}
	}
	private void actualizar() {
		vContenido = gestorTablas.obtenerCuerpoVentas(proveedor);
		modelo.setDataVector(vContenido, vColumnas);
		modelo.fireTableDataChanged();
		btnActualizar.setEnabled(false);
		
		datePicker.setEnabled(false);
		datePicker.setVisible(false);
		lblFecha.setVisible(false);
		
	}
}
