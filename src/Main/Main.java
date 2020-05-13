package Main;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.io.IOException;
import java.awt.*; 
import javax.swing.*;
import javax.swing.JOptionPane;
import Telas.TelaCadastros;
import Telas.TelaLancamentos;
import Telas.TelaRelatorios;

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
	static JButton btCad, btLan, btRel, btSai; 

	// label to display text 
	static JLabel l; 

	Box box;

	public TelaPrincipal() {
		// create a new frame with text field and button 
		f = new JFrame("Nondas Celulares"); 
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// create a label to display text 
		l = new JLabel("Selecione a op��o desejada:"); 

		// create a new buttons 
		btCad = new JButton("Cadastros"); 
		btLan = new JButton("Lan�amentos"); 
		btRel = new JButton("Relat�rios"); 
		btSai = new JButton("Sair");

		btCad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaCadastros();

			}
		});

		btLan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaLancamentos();
			}
		});
		
		btRel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new TelaRelatorios();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btSai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				System.exit(0);

			}
		});

		// create a panel to add buttons 
		JPanel p = new JPanel(); 
		box = Box.createVerticalBox();
		// add buttons and textfield to panel 
		box.add(l); 
		box.add(btCad); 
		box.add(btLan); 
		box.add(btRel); 
		box.add(btSai);


		p.add(box);

		// add panel to frame 
		f.add(p); 

		// set the size of frame 
		f.setSize(300, 300); 

		f.show();

	}//builder
}//home screen



