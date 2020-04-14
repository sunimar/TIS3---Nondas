package Telas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class TelaOrdemVenda extends JFrame{

	Date date;
	JPanel ui, pvenda;
	JButton btVol, btSalvar;
	GridBagConstraints c = new GridBagConstraints();
	GridLayout grid;


	public TelaOrdemVenda() {
		super("Ordem de Venda");
		
		ui = new JPanel(new BorderLayout(4,4));
		ui.setBorder(new TitledBorder("Ordem de Venda"));
		ui.setLayout(new GridLayout(0,3));
		
		pvenda = new JPanel();
		pvenda.setLayout(new GridBagLayout());
		pvenda.setBorder(new TitledBorder("Venda"));

		btVol = new JButton("Cancelar e Voltar");
		btSalvar = new JButton("Salvar Ordem de Servico");

		pvenda.add(btVol);
		pvenda.add(btSalvar);
		ui.add(pvenda);

		btVol.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		setSize(700,500);
		setVisible(true);
	}//builder

}//class
