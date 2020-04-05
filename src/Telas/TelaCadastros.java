package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TelaCadastros {
	// JFrame 
	static JFrame f; 

	// JButton 
	static JButton btCli, btProd, btServ, btVol; 

	// label to display text 
	static JLabel l; 

	Box box;

	public TelaCadastros() {
		// create a new frame with text field and button 
		f = new JFrame("Cadastros"); 
		// create a label to display text 
		l = new JLabel("Selecione a opcao desejada:"); 

		// create a new buttons 
		btCli = new JButton("Cliente"); 
		btProd = new JButton("Produto"); 
		btServ = new JButton("Tipo de Servico"); 
		btVol = new JButton("Voltar");

		btCli.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaCliente();

			}
		});
		
		btProd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaProduto();

			}
		});
		
		btServ.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//new TelaServico();

			}
		});
		
		btVol.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();

			}
		});
		

		// create a panel to add buttons 
		JPanel p = new JPanel(); 
		box = Box.createVerticalBox();
		// add buttons and textfield to panel 
		box.add(l); 
		box.add(btCli); 
		box.add(btProd); 
		box.add(btServ); 
		box.add(btVol);

		p.add(box);

		// add panel to frame 
		f.add(p); 

		// set the size of frame 
		f.setSize(300, 300); 

		f.show();

	}


}//tela cadastros
