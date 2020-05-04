package Telas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Entidades.OrdemServico;
import Entidades.OrdemVenda;
import Entidades.Produto;
import DAO.ProdutoDAO;

@SuppressWarnings("serial")
public class TelaOrdemVenda extends JFrame{
	//instanciar dao venda
	public static OrdemVenda ov = null;
	Date date;
	float preco = 0;
	Produto prod;
	ProdutoDAO prodDAO;
	JPanel ui, jpVenda, jpProd, jpEstoque;
	JButton btVol, btSalvar, btDel, btAddProd, btRemProd;
	JLabel jlData, jlCod, jlVal;
	@SuppressWarnings("rawtypes")
	JList carrinho, estoque;
	@SuppressWarnings("rawtypes")
	DefaultListModel listModelEstoque, listModelCarrinho;
	GridBagConstraints c = new GridBagConstraints();
	GridLayout grid;


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TelaOrdemVenda() throws IOException {
		super("Ordem de Venda");
		if(ov==null) {
			System.out.println("nova ov");
			ov = new OrdemVenda();
		}else {
			System.out.println("alteração de ov");
			//metodo que constroi o carrinho de acordo com o dao venda
		}
		
		prodDAO = new ProdutoDAO("ProdutoDAO");

		ui = new JPanel(new BorderLayout(4,4));
		ui.setBorder(new TitledBorder(""));
		ui.setLayout(new GridLayout(0,2));

		jpVenda = new JPanel();
		jpVenda.setLayout(new GridBagLayout());
		jpVenda.setBorder(new TitledBorder("Dados"));

		/*dados da venda*/
		dadosPanel();
		/*-------------------------------------------*/

		jpProd = new JPanel();
		jpProd.setLayout(new BoxLayout(jpProd, BoxLayout.Y_AXIS));
		jpProd.setBorder(new TitledBorder("Produtos:"));

		listModelCarrinho = new DefaultListModel();
		carrinho = new JList<Produto>(listModelCarrinho);



		/*----------------- botoes------------*/
		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvarOv();
			}
		});
		
		btDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//criar metodo de remoção
			}
		});
		
		btRemProd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remProd();
			}
		});

		btAddProd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addProd();
			}
		});

		btVol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ov = null;
				TelaLancamentos.f.setVisible(true);
				dispose();
			}
		});

		jpProd.add(carrinho);
		ui.add(jpProd);
		add(ui);

		setSize(700,500);
		setVisible(true);
	}//builder
	
	public void salvarOv() {
		//long oldCod = ov.getCodVenda();
		ov.setCodVenda(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(date)));
		
		Produto [] carrinho = null;
		
		carrinho = Arrays.stream(listModelCarrinho.toArray()).map(Produto.class::cast).toArray(Produto[]::new);
		
		for(int i=0; i<carrinho.length; i++) {
			System.out.println(carrinho[i].getNome());
			ov.setProduto(carrinho[i]);
		}
		
		ov.setValorTotal();
		//dao venda//
		/*
		if(oldCod == 0) {
			//servicoDAO.add(os);
			JOptionPane.showMessageDialog(null, os.getCodServ() +" "+ " salvo com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, os.getCodServ() +" "+ " alterado com sucesso!");
			//servicoDAO.update(os);
		}*/
		//
		System.out.println(ov.toString());
		TelaLancamentos.f.setVisible(true);
		dispose();
	}//salvar

	public void remProd() {
		
		prod = (Produto) carrinho.getSelectedValue();
		
		preco = preco - prod.getPrecoVenda();
		
		String nome = prod.getNome();
		
		listModelCarrinho.removeElement(prod);

		jlVal.setText("Valor Total: " + preco);
		
		JOptionPane.showMessageDialog(null, nome + " removido! ");

	}//rem prod

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addProd() {
		jpEstoque = new JPanel();

		listModelEstoque = new DefaultListModel();
		estoque = new JList<Produto>(listModelEstoque);

		List<Produto> produtos = prodDAO.getAll();

		for(Produto i : produtos) {
			listModelEstoque.addElement(i);
		}
		jpEstoque.add(estoque);

		JOptionPane.showMessageDialog(null,jpEstoque,"Estoque de Produtos",JOptionPane.INFORMATION_MESSAGE);
		List<Produto> produtosCarrinho = estoque.getSelectedValuesList();

		while(produtosCarrinho.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Selecione ao menos UM produto!");
			JOptionPane.showMessageDialog(null,jpEstoque,"Estoque de Produtos",JOptionPane.INFORMATION_MESSAGE);
			produtosCarrinho = estoque.getSelectedValuesList();
		}

		for(Produto i : produtosCarrinho) {
			listModelCarrinho.addElement(i);
			preco += i.getPrecoVenda();
		}
		jlVal.setText("Valor Total: " + preco);
		carrinho.setModel(listModelCarrinho);

	}//addprod

	public void dadosPanel() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3,3,3,3);
		c.weightx = 0;
		c.weighty = 0;

		/* Fill Dados. */
		date = new Date();

		jlData = new JLabel();
		jlData.setText(new SimpleDateFormat("EEE, dd 'de' MMM 'de' yyyy, HH:mm").format(ov.getData()));
		jlCod = new JLabel("Numero: " + new SimpleDateFormat("yyyyMMddHHmmss").format(ov.getData()));
		btAddProd = new JButton("Incluir Produto");
		btRemProd = new JButton("Remover Produto");
		btVol = new JButton("Cancelar e Voltar");
		btSalvar = new JButton("Salvar Ordem de Venda");
		btDel = new JButton("Deletar Ordem de Venda");
		if(ov.getCodVenda()==0) {
			btDel.setEnabled(false);
		}
		
		c.gridx = 0; c.gridy = 0;
		jpVenda.add(jlData, c);
		c.gridx = 0; c.gridy = 1;
		jpVenda.add(jlCod, c);


		jlVal = new JLabel("Valor Total: " + ov.getValorTotal());

		c.gridx = 0; c.gridy = 2;
		jpVenda.add(btAddProd, c);

		c.gridx = 0; c.gridy = 3;
		jpVenda.add(btRemProd, c);

		c.gridx = 0; c.gridy = 4;
		jpVenda.add(jlVal, c);

		c.gridx = 0; c.gridy = 5;
		jpVenda.add(btSalvar, c);

		c.gridx = 0; c.gridy = 6;
		jpVenda.add(btDel, c);
		
		c.gridx = 0; c.gridy = 7;
		jpVenda.add(btVol, c);

		ui.add(jpVenda);
	}

}//class
