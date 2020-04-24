package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaLancamentos {
	// JFrame 
	static JFrame f; 

	// JButton 
	static JButton btOv, btOs, btCon, btVol; 

	// label to display text 
	static JLabel l;

	Box box;

	public TelaLancamentos() {
		// create a new frame with text field and button 
		f = new JFrame("Lancamentos"); 
		// create a label to display text 
		l = new JLabel("Selecione a opcao desejada:"); 

		// create a new buttons 
		btOv = new JButton("Ordem de Venda"); 
		btOs = new JButton("Ordem de Servico");
		btCon = new JButton("Consultar Ordens");
		btVol = new JButton("Voltar");

		btOv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				try {
					new TelaOrdemVenda();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		btOs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				new TelaOrdemServico();
			}
		});

		btCon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//f.setVisible(false);
				//new TelaProduto();
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
		box.add(btOv); 
		box.add(btOs);
		box.add(btCon);
		box.add(btVol);

		p.add(box);

		// add panel to frame 
		f.add(p); 

		// set the size of frame 
		f.setSize(300, 300); 

		f.show();

	}//builder
}//tela lancamentos
