package Telas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import DAO.VendasDAO;
import DAO.ServicoDAO;
import DAO.ProdutoDAO;
import DAO.CadastroClienteDAO;
import Entidades.Cliente;
import Entidades.OrdemVenda;
import Entidades.OrdemServico;
import Entidades.Produto;
import Entidades.Relatorios;

public class TelaRelatorios {
	VendasDAO vendasDAO;
	OrdemVenda ov;
	ServicoDAO servDAO;
	OrdemServico os;
	Produto prod;
	Cliente cli;
	ProdutoDAO prodDAO;
	CadastroClienteDAO cliDAO;
	Relatorios ret;
	// JFrame 
	static JFrame f; 

	// JButton 
	static JButton btVendas01, btVendas02, btServ01, btServ02, btVol; 
	/*
	 * vendas01= vendas por data
	 * vendas02= vendas por produto
	 * serviços01= serviços e total por data
	 * serviços02= serviços por data e por cliente*/
	// label to display text 
	static JLabel l;

	Box box;

	public TelaRelatorios() throws IOException {
		ov = new OrdemVenda();
		vendasDAO = new VendasDAO("VendasDAO");
		os = new OrdemServico();
		servDAO = new ServicoDAO("ServicoDAO");
		prodDAO = new ProdutoDAO("ProdutoDAO");
		cliDAO = new CadastroClienteDAO("ClienteDAO");
		cli = new Cliente();
		ret = new Relatorios();
		// create a new frame with text field and button 
		f = new JFrame("Relatórios"); 
		// create a label to display text 
		l = new JLabel("Selecione a opção desejada:"); 
		// create a new buttons 
		btVendas01= new JButton("Faturamento de Vendas por mês/ano"); 
		btVendas02 = new JButton("Faturamento de Produto por mês/ano");
		btServ01 = new JButton("Faturamento de Serivços por mês/ano");
		btServ02 = new JButton("Faturamento por Cliente por mês/ano");
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
				try {
					vendas02();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//f.setVisible(false);
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

		btServ02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					servPorCliente();
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
		box.add(btServ02);
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
		double tmp = 0;

		while (data.equals("")) {
			data = JOptionPane.showInputDialog("Informe a data: mmaaaa");
		}

		List<OrdemServico> servs = ret.servicosPorData(data);
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
			System.out.println(i.getValorTotal());
			tmp += i.getValorTotal();
			taText.append(i + "\n");
		}//for

		if(tmp==0) {
			JOptionPane.showMessageDialog(null, "Não há Serviços nesse período!");
			f.dispose();
		}else {
			taText.append("Total de faturamento de Serviços: " + tmp);
			panel.add(spScroll);
			UIManager.put("OptionPane.minimumSize",new Dimension(400, 400));
			JOptionPane.showMessageDialog(null, panel, "Serviços no período de " + data, JOptionPane.PLAIN_MESSAGE);
		}
	}//serviços01

	public void servPorCliente()throws Exception{
		String data = "";
		float tmp = 0;
		//------------------seleciono a competencia---------------------------------------------------------------------------
		while (data.equals("")) {
			data = JOptionPane.showInputDialog("Informe a data: mmaaaa");
		}
		//----------------seleciono o cliente--------------------------------------------------------------------------
		JPanel jpEstoque = new JPanel();

		DefaultListModel listModelEstoque = new DefaultListModel();
		JList estoque = new JList<Produto>(listModelEstoque);

		List<Cliente> clis = cliDAO.getAll();

		for(Cliente i : clis) {
			listModelEstoque.addElement(i);
		}
		jpEstoque.add(estoque);
		JOptionPane.showMessageDialog(null, "Selecione um cliente:");
		JOptionPane.showMessageDialog(null,jpEstoque,"Lista de Clientes",JOptionPane.INFORMATION_MESSAGE);
		cli = (Cliente) estoque.getSelectedValue();
		System.out.println(cli.getNome() + cli.getCpfCnpj());
		List<OrdemServico> servs = ret.servicosPorCliente(data, cli.getCpfCnpj());
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
			tmp += i.getValorTotal();
			taText.append(i + "\n");
		}//for

		if(tmp==0) {
			JOptionPane.showMessageDialog(null, "Não há Serviços nesse período!");
			f.dispose();
		}else {
			taText.append("Total de faturamento de Serviços: " + tmp);
			panel.add(spScroll);
			UIManager.put("OptionPane.minimumSize",new Dimension(400, 400));
			JOptionPane.showMessageDialog(null, panel, "Serviços do CpfCnpj: " + cli.getCpfCnpj(), JOptionPane.PLAIN_MESSAGE);
		}
	}//serviços02 Serviço por Cliente.

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void vendas02() throws IOException {
		String data = "";
		float tmp = 0;
		//------------------seleciono a competencia---------------------------------------------------------------------------
		while (data.equals("")) {
			data = JOptionPane.showInputDialog("Informe a data: mmaaaa");
		}
		//----------------seleciono o produto--------------------------------------------------------------------------
		JPanel jpEstoque = new JPanel();

		DefaultListModel listModelEstoque = new DefaultListModel();
		JList estoque = new JList<Produto>(listModelEstoque);

		List<Produto> produtos = prodDAO.getAll();

		for(Produto i : produtos) {
			listModelEstoque.addElement(i);
		}
		jpEstoque.add(estoque);
		JOptionPane.showMessageDialog(null, "Selecione um produto");
		JOptionPane.showMessageDialog(null,jpEstoque,"Estoque de Produtos",JOptionPane.INFORMATION_MESSAGE);
		prod = (Produto) estoque.getSelectedValue();
		System.out.println(prod.getNome());
		//--------------------------------mostrar o relatorio----------------------------------------------------------------
		List<OrdemVenda> vendas = ret.vendasPorProduto(data, prod.getIdProduto());
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
			tmp += prod.getPrecoVenda();
			taText.append(i + "\n");
		}//for

		if(tmp==0) {
			JOptionPane.showMessageDialog(null, "Não há vendas nesse período!");
			f.dispose();
		}else {
			taText.append(prod.getNome()+ " faturou " + tmp + " no periodo de " + data);
			panel.add(spScroll);
			UIManager.put("OptionPane.minimumSize",new Dimension(400, 400));
			JOptionPane.showMessageDialog(null, panel, "Vendas no período de " + data, JOptionPane.PLAIN_MESSAGE);
		}
	}//vendas02

	public void vendas01()throws Exception{
		String data = "";
		float tmp = 0;


		while (data.equals("")) {
			data = JOptionPane.showInputDialog("Informe a data: mmaaaa");
		}
		List<OrdemVenda> vendas = ret.vendasPorData(data);
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
			tmp += i.getValorTotal();
			taText.append(i + "\n");
		}//for

		if(tmp==0) {
			JOptionPane.showMessageDialog(null, "Não há vendas nesse período!");
			f.dispose();
		}else {
			taText.append("Total de faturamento de vendas: " + tmp);
			panel.add(spScroll);
			UIManager.put("OptionPane.minimumSize",new Dimension(400, 400));
			JOptionPane.showMessageDialog(null, panel, "Vendas no período de " + data, JOptionPane.PLAIN_MESSAGE);
		}
	}//vendas01

}//telaRelatorios
