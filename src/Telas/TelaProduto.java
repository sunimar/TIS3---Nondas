package Telas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Entidades.ExcecaoValorInvalido;
import Entidades.Produto;
import DAO.ProdutoDAO;

public class TelaProduto extends JFrame{
	private static ProdutoDAO proDAO;
	static JFrame f;
	JPanel jp;
	Box hb, vb;

	Box[] hbFields;
	JLabel[] lbFields;
	JTextField[] tfFields;

	JButton btCreate, btRetrieve, btUpdate, btDelete, btShow, btVol;

	int lastSelected = -1;

	public TelaProduto(){
		f = new JFrame("Produto"); 
		jp = new JPanel();
		hb = Box.createHorizontalBox();
		vb = Box.createVerticalBox();
		btCreate   = new JButton("Criar");
		btRetrieve = new JButton("Recuperar");
		btUpdate   = new JButton("Atualizar");
		btDelete   = new JButton("Deletar");
		btShow     = new JButton("Mostar Tudo");
		btVol 	   = new JButton("Voltar");

		btVol.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//this.dispose();
			}
		});

		btCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btCreate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btRetrieve();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btUpdate.setEnabled(false);
		btUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btUpdate();
				} catch (NumberFormatException | IOException | ExcecaoValorInvalido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btDelete.setEnabled(false);
		btDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btDelete();
				} catch (NumberFormatException | IOException | ExcecaoValorInvalido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btShow();
			}
		});

		hbFields = new Box[6];
		lbFields = new JLabel[6];
		tfFields = new JTextField[6];

		hb.add(btCreate);
		hb.add(btRetrieve);
		hb.add(btUpdate);
		hb.add(btDelete);
		hb.add(btShow);
		hb.add(btVol);
		vb.add(hb);
		jp.add(vb);
		f.add(jp);


		lbFields[0] = new JLabel("ID: ");
		tfFields[0] = new JTextField(10);
		//tfFields[0].setEditable(false);
		hbFields[0] = Box.createHorizontalBox();
		hbFields[0].add(lbFields[0]);
		hbFields[0].add(tfFields[0]);
		vb.add(hbFields[0]);

		lbFields[1] = new JLabel("Nome: ");
		tfFields[1] = new JTextField(10);
		hbFields[1] = Box.createHorizontalBox();
		hbFields[1].add(lbFields[1]);
		hbFields[1].add(tfFields[1]);
		vb.add(hbFields[1]);

		lbFields[2] = new JLabel("Preco Compra: ");
		tfFields[2] = new JTextField(10);
		hbFields[2] = Box.createHorizontalBox();
		hbFields[2].add(lbFields[2]);
		hbFields[2].add(tfFields[2]);
		vb.add(hbFields[2]);

		lbFields[3] = new JLabel("Preco Venda: ");
		tfFields[3] = new JTextField(10);
		hbFields[3] = Box.createHorizontalBox();
		hbFields[3].add(lbFields[3]);
		hbFields[3].add(tfFields[3]);
		vb.add(hbFields[3]);

		add(jp);

		setSize(600,400);
		show();
	}

	public void btShow(){
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(400, 400));

		JTextArea taText = new JTextArea();
		taText.setEditable(false);
		JScrollPane spScroll = new JScrollPane(taText);
		spScroll.setMaximumSize(new Dimension(400, 400));
		spScroll.setPreferredSize(new Dimension(400, 400));

		taText.setText("");
		try{
			List<Produto> p = proDAO.getAll();
			for (Produto a : p)
				taText.append(a + "\n");
		} catch (Exception ex) {ex.printStackTrace();}

		panel.add(spScroll);
		UIManager.put("OptionPane.minimumSize",new Dimension(400, 400));
		JOptionPane.showMessageDialog(null, panel, "Produtos", JOptionPane.PLAIN_MESSAGE);
	}//show


	public void btCreate()throws IOException, NumberFormatException, ExcecaoValorInvalido{
		proDAO = new ProdutoDAO("ProdutoDAO");
		String id = tfFields[0].getText();
		String nome = tfFields[1].getText();
		String preCom = tfFields[2].getText();
		String preVen = tfFields[3].getText();

		if (id.equals("") || nome.equals("") || preCom.equals("") || preVen.equals("")){
			JOptionPane.showMessageDialog(null, "Os campos devem ser preenchidos!");
			return;
		}

		Produto prod = new Produto();
		prod.setIdProduto(Integer.parseInt(id));
		prod.setNome(nome);
		prod.setPrecoCompra(Float.parseFloat(preCom));
		prod.setPrecoVenda(Float.parseFloat(preVen));

		prod.print();
		proDAO.add(prod);

		JOptionPane.showMessageDialog(null, nome + " inserido com sucesso!");
	}//create

	public void btRetrieve() throws IOException{
		proDAO = new ProdutoDAO("ProdutoDAO");
		String id = "";
		while (id.equals(""))
			id = JOptionPane.showInputDialog("Digite o ID do produto");

		Produto prod = proDAO.get(id);

		tfFields[0].setText(id);
		tfFields[1].setText(prod.getNome());
		tfFields[2].setText(String.valueOf(prod.getPrecoCompra()));
		tfFields[3].setText(String.valueOf(prod.getPrecoVenda()));

		btUpdate.setEnabled(true);
		btDelete.setEnabled(true);
	}//retrieve

	public void btUpdate() throws IOException, NumberFormatException, ExcecaoValorInvalido{
		proDAO = new ProdutoDAO("ProdutoDAO");
		String id = tfFields[0].getText();
		String nome = tfFields[1].getText();
		String preCom = tfFields[2].getText();
		String preVen = tfFields[3].getText();

		if (id.equals("") || nome.equals("") || preCom.equals("") || preVen.equals("")){
			JOptionPane.showMessageDialog(null, "Os campos devem ser preenchidos!");
			return;
		}

		Produto prod = new Produto();
		prod.setIdProduto(Integer.parseInt(id));
		prod.setNome(nome);
		prod.setPrecoCompra(Float.parseFloat(preCom));
		prod.setPrecoVenda(Float.parseFloat(preVen));

		prod.print();
		proDAO.update(prod);

		JOptionPane.showMessageDialog(null, nome + " atualizado com sucesso!");
	}//update

	public void btDelete() throws IOException, NumberFormatException, ExcecaoValorInvalido{
		proDAO = new ProdutoDAO("ProdutoDAO");
		String id = tfFields[0].getText();
		String nome = tfFields[1].getText();
		String preCom = tfFields[2].getText();
		String preVen = tfFields[3].getText();

		if (id.equals("") || nome.equals("") || preCom.equals("") || preVen.equals("")){
			JOptionPane.showMessageDialog(null, "Os campos devem ser preenchidos!");
			return;
		}

		Produto prod = new Produto();
		prod.setIdProduto(Integer.parseInt(id));
		prod.setNome(nome);
		prod.setPrecoCompra(Float.parseFloat(preCom));
		prod.setPrecoVenda(Float.parseFloat(preVen));

		prod.print();
		proDAO.remove(prod);

		JOptionPane.showMessageDialog(null, nome + " excluido com sucesso!");

		btDelete.setEnabled(false);
		btUpdate.setEnabled(false);

		tfFields[0].setText("");
		tfFields[1].setText("");
		tfFields[2].setText("");
		tfFields[3].setText("");
	}//delete
}//class