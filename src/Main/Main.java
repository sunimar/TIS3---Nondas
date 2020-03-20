package Main;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import javax.swing.JOptionPane;

public class Main{


	public static void main(String[] args) {
		try {

			
			new TelaPrincipal();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}//main

class TelaPrincipal extends JFrame{
	// JFrame 
	static JFrame f; 

	// JButton 
	static JButton btCad, btLan, btRel; 

	// label to display text 
	static JLabel l; 

	Box box;

	public TelaPrincipal() {
		// create a new frame with text field and button 
		f = new JFrame("Nondas Celulares"); 
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// create a label to display text 
		l = new JLabel("Selecione a opção desejada"); 

		// create a new buttons 
		btCad = new JButton("Cadastros"); 
		btLan = new JButton("Lançamentos"); 
		btRel = new JButton("Relatórios"); 

		btCad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//new TelaCadastros();

			}
		});

		// create a panel to add buttons 
		JPanel p = new JPanel(); 
		box = Box.createVerticalBox();
		// add buttons and textfield to panel 
		box.add(btCad); 
		box.add(btLan); 
		box.add(btRel); 
		box.add(l); 

		p.add(box);

		// add panel to frame 
		f.add(p); 

		// set the size of frame 
		f.setSize(300, 300); 

		f.show();
	}//home screen

	

