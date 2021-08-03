package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;

public class Panel extends JFrame{
	private JLabel lblQuieresSerMi;
	private JButton btnSi;
	private JButton btnNo;
	private JLabel lblRespuesta;
	
	public Panel () {
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 285, 220);	
		getContentPane().setLayout(null);
		
		lblQuieresSerMi = new JLabel("Quieres ser mi novia ?");
		lblQuieresSerMi.setBounds(63, 70, 370, 16);
		getContentPane().add(lblQuieresSerMi);
		
		btnSi = new JButton("si");
		btnSi.setBounds(63, 99, 56, 25);
		getContentPane().add(btnSi);
		
		btnNo = new JButton("no");
		btnNo.setBounds(131, 99, 56, 25);
		getContentPane().add(btnNo);
		
		lblRespuesta = new JLabel("");
		lblRespuesta.setBounds(52, 137, 152, 16);
		getContentPane().add(lblRespuesta);
		
		btnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblRespuesta.setText("Enorabuena !! Ya tengo pareja");
			}});
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblRespuesta.setText("Enorabuena !! Ya tengo pareja");
			}});
		
		btnNo.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				if (btnNo.getText().equals("no")) {
					btnNo.setText("si");
					btnSi.setText("no");
				}
				
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});		
		btnSi.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				if (btnSi.getText().equals("no")) {
					btnNo.setText("no");
					btnSi.setText("si");
				}
				
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});		
		
	}
}
