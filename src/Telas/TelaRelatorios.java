package Telas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import DAO.VendasDAO;
import DAO.ServicoDAO;
import Entidades.Cliente;
import Entidades.OrdemVenda;
import Entidades.OrdemServico;
import Entidades.Produto;

public class TelaRelatorios {
	VendasDAO vendasDAO;
	OrdemVenda ov;
	ServicoDAO servDAO;
	OrdemServico os;
	// JFrame 
	static JFrame f; 

	// JButton 
	static JButton btVendas01, btVendas02, btServ01, btVol; 
	/*
	 * vendas01= vendas por data
	 * vendas02= vendas por produto
	 * servi�os01= servi�os e total por data
	 * servi�os02= servi�os por data e por cliente*/
	// label to display text 
	static JLabel l;

	Box box;

	public TelaRelatorios() throws IOException {
		ov = new OrdemVenda();
		vendasDAO = new VendasDAO("VendasDAO");
		os = new OrdemServico();
		servDAO = new ServicoDAO("ServicoDAO");
		// create a new frame with text field and button 
		f = new JFrame("Relat�rios"); 
		// create a label to display text 
		l = new JLabel("Selecione a op��o desejada:"); 
		// create a new buttons 
		btVendas01= new JButton("Vendas e faturamento por m�s/ano"); 
		btVendas02 = new JButton("Vendas totais por produto");
		btServ01 = new JButton("Servi�os e vendas por m�s/ano");
		btVol = new JButton("Voltar");

		btVendas01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					vendas01();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//f.setVisible(false);
			}
		});

		btVendas02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				f.setVisible(false);
			}
		});

		btServ01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					serv01();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//f.setVisible(false);
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
		box.add(btVendas01); 
		box.add(btVendas02);
		box.add(btServ01);
		box.add(btVol);

		p.add(box);

		// add panel to frame 
		f.add(p); 

		// set the size of frame 
		f.setSize(300, 300); 

		f.show();
	}//builder

	public void serv01()throws Exception{
		String data = "";
		String comp1 = "";
		float tmp = 0;

		while (data.equals("")) {
			data = JOptionPane.showInputDialog("Informe a data: mmaaaa");
		}

		List<OrdemServico> servs = servDAO.getAll();
		/************************************************************/
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(400, 400));

		JTextArea taText = new JTextArea();
		taText.setEditable(false);
		JScrollPane spScroll = new JScrollPane(taText);
		spScroll.setMaximumSize(new Dimension(400, 400));
		spScroll.setPreferredSize(new Dimension(400, 400));
		/***************************************************************/
		taText.setText("");

		for (OrdemServico i : servs) {
			comp1 = new SimpleDateFormat("MMyyyy").format(i.getData());

			if(data.equals(comp1)) {
				tmp += i.getValorTotal();
				taText.append(i + "\n");
			}else {
				System.out.println("nao achou!");
			}
		}//for

		if(tmp==0) {
			JOptionPane.showMessageDialog(null, "N�o h� Servi�os nesse per�odo!");
			f.dispose();
		}else {
			taText.append("Total de faturamento de Servi�os: " + tmp);
			panel.add(spScroll);
			UIManager.put("OptionPane.minimumSize",new Dimension(400, 400));
			JOptionPane.showMessageDialog(null, panel, "Servi�os no per�odo de " + data, JOptionPane.PLAIN_MESSAGE);
		}
	}//vendas01

	public void vendas01()throws Exception{
		String data = "";
		String comp1 = "";
		float tmp = 0;

		while (data.equals("")) {
			data = JOptionPane.showInputDialog("Informe a data: mmaaaa");
		}
		//Date d = new Date();
		//data = new SimpleDateFormat("ddMMyyyy").format(d);
		//System.out.println(data);

		List<OrdemVenda> vendas = vendasDAO.getAll();
		/************************************************************/
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(400, 400));

		JTextArea taText = new JTextArea();
		taText.setEditable(false);
		JScrollPane spScroll = new JScrollPane(taText);
		spScroll.setMaximumSize(new Dimension(400, 400));
		spScroll.setPreferredSize(new Dimension(400, 400));
		/***************************************************************/
		taText.setText("");

		for (OrdemVenda i : vendas) {
			comp1 = new SimpleDateFormat("MMyyyy").format(i.getData());

			if(data.equals(comp1)) {
				tmp += i.getValorTotal();
				taText.append(i + "\n");
			}else {
				System.out.println("nao achou!");
			}
		}//for

		if(tmp==0) {
			JOptionPane.showMessageDialog(null, "N�o h� vendas nesse per�odo!");
			f.dispose();
		}else {
			taText.append("Total de faturamento de vendas: " + tmp);
			panel.add(spScroll);
			UIManager.put("OptionPane.minimumSize",new Dimension(400, 400));
			JOptionPane.showMessageDialog(null, panel, "Vendas no per�odo de " + data, JOptionPane.PLAIN_MESSAGE);
		}
	}//vendas01


}//telaRelatorios
