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

import DAO.CadastroClienteDAO;
import DAO.ExcecaoValorDuplicado;
import Entidades.Cliente;

public class TelaCliente extends JFrame{
	private static CadastroClienteDAO clienteDAO;
	JPanel jp;
	Box hb, vb;

	Box[] hbFields;
	JLabel[] lbFields;
	JTextField[] tfFields;

	JButton btCreate, btRetrieve, btUpdate, btDelete, btShow, btVol;

	int lastSelected = -1;

	public TelaCliente(){

		super("Cliente"); 
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
				if(TelaCadastros.f !=null && TelaCadastros.f.isVisible()==false) {
					TelaCadastros.f.setVisible(true);
				}
				dispose();
			}
		});

		btCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btCreate();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ExcecaoValorDuplicado e1) {
					e1.printStackTrace();
				}
			}
		});

		btRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btRetrieve();
				}catch(Exception e1) {e1.printStackTrace();}
			}
		});

		btUpdate.setEnabled(false);
		btUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btUpdate();
				}catch(Exception e1) {e1.printStackTrace();}
			}
		});

		btDelete.setEnabled(false);
		btDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btDelete();
				}catch(Exception e1) {e1.printStackTrace();}
			}
		});

		btShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btShow();
				}catch(Exception e1) {e1.printStackTrace();}
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

		lbFields[0] = new JLabel("CPF/CNPJ: ");
		tfFields[0] = new JTextField(10);
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

		lbFields[2] = new JLabel("Email: ");
		tfFields[2] = new JTextField(10);
		hbFields[2] = Box.createHorizontalBox();
		hbFields[2].add(lbFields[2]);
		hbFields[2].add(tfFields[2]);
		vb.add(hbFields[2]);

		lbFields[3] = new JLabel("Telefone: ");
		tfFields[3] = new JTextField(10);
		hbFields[3] = Box.createHorizontalBox();
		hbFields[3].add(lbFields[3]);
		hbFields[3].add(tfFields[3]);
		vb.add(hbFields[3]);


		add(jp);

		setSize(600,400);
		show();
	}
	
	public boolean validaCampos(String nome, String cpf_cnpj, String email, String tel) {
		if ((nome.equals("") || cpf_cnpj.equals("") || cpf_cnpj.equals("00000000000")
			|| email.equals("") || tel.equals("")) || cpf_cnpj.length() != 14 && cpf_cnpj.length() != 11) {
			System.out.println("leng: " + cpf_cnpj.length());
			return (false);
		}
		return (true);
	}

	public void btShow() throws IOException{
		clienteDAO = new CadastroClienteDAO("ClienteDao");

		JPanel panel = new JPanel();
		panel.setSize(new Dimension(400, 400));

		JTextArea taText = new JTextArea();
		taText.setEditable(false);
		JScrollPane spScroll = new JScrollPane(taText);
		spScroll.setMaximumSize(new Dimension(400, 400));
		spScroll.setPreferredSize(new Dimension(400, 400));

		taText.setText("");
		try{
			List<Cliente> l = clienteDAO.getAll();
			for (Cliente a : l)
				taText.append(a + "\n");
		} catch (Exception ex) {ex.printStackTrace();}

		panel.add(spScroll);
		UIManager.put("OptionPane.minimumSize",new Dimension(400, 400));
		JOptionPane.showMessageDialog(null, panel, "Clientes", JOptionPane.PLAIN_MESSAGE);
	}


	public void btCreate() throws IOException, ExcecaoValorDuplicado{
		clienteDAO = new CadastroClienteDAO("ClienteDao");

		String cpf = tfFields[0].getText();
		String nome = tfFields[1].getText();
		String email = tfFields[2].getText();
		String tel = tfFields[3].getText();

		if (validaCampos(nome, cpf, email, tel) != true) {
			JOptionPane.showMessageDialog(null, "Os campos devem ser preenchidos corretamente!");
			return;
		}

		Cliente cli = new Cliente();
		cli.setNome(nome);
		cli.setCpfCnpj(Long.parseLong(cpf));
		cli.setEmail(email);
		cli.setTelefone(tel);
		cli.print();

		try {
			clienteDAO.add(cli);
		} catch (ExcecaoValorDuplicado evd){
			JOptionPane.showMessageDialog(null, "Valor ja existente!");
			return;
		}
		
		TelaOrdemServico.cli = cli;
		TelaOrdemServico.jlCli.setText("<html>Cliente: " + cli.getNome() + "<br>Telefone: " + cli.getTelefone() + "</hmtl>");
		
		JOptionPane.showMessageDialog(null, nome + " inserido com sucesso!");
	}//create

	public void btRetrieve() throws IOException{
		clienteDAO = new CadastroClienteDAO("ClienteDao");

		String id = "";
		while (id.equals(""))
			id = JOptionPane.showInputDialog("Digite o cpf/cnpj do cliente");

		Cliente cli = clienteDAO.get(id);
		if(cli != null){
			tfFields[0].setText(id);
			tfFields[1].setText(cli.getNome());
			tfFields[2].setText(cli.getEmail());
			tfFields[3].setText(cli.getTelefone());

			TelaOrdemServico.cli = cli;
			TelaOrdemServico.jlCli.setText("<html>Cliente: " + cli.getNome() + "<br>Telefone: " + cli.getTelefone() + "</hmtl>");
			btUpdate.setEnabled(true);
			btDelete.setEnabled(true);
		}else {
			JOptionPane.showMessageDialog(null, "Cliente inexistente!");
		}
	}

	public void btUpdate() throws IOException{

		clienteDAO = new CadastroClienteDAO("ClienteDao");

		String cpf = tfFields[0].getText();
		String nome = tfFields[1].getText();
		String email = tfFields[2].getText();
		String tel = tfFields[3].getText();

		if (validaCampos(nome, cpf, email, tel) != true) {
			JOptionPane.showMessageDialog(null, "Os campos devem ser preenchidos corretamente!");
			return;
		}

		Cliente cli = new Cliente();
		cli.setNome(nome);
		cli.setCpfCnpj(Long.parseLong(cpf));
		cli.setEmail(email);
		cli.setTelefone(tel);
		cli.print();

		clienteDAO.update(cli);

		JOptionPane.showMessageDialog(null, nome + " alterado com sucesso!");
	}

	public void btDelete() throws IOException{

		clienteDAO = new CadastroClienteDAO("ClienteDao");

		String cpf = tfFields[0].getText();
		String nome = tfFields[1].getText();
		String email = tfFields[2].getText();
		String tel = tfFields[3].getText();

		if (validaCampos(nome, cpf, email, tel) != true) {
			JOptionPane.showMessageDialog(null, "Os campos devem ser preenchidos corretamente!");
			return;
		}

		Cliente cli = new Cliente();
		cli.setNome(nome);
		cli.setCpfCnpj(Long.parseLong(cpf));
		cli.setEmail(email);
		cli.setTelefone(tel);
		cli.print();

		clienteDAO.remove(cli);

		JOptionPane.showMessageDialog(null, nome + " excluido com sucesso!");

		btDelete.setEnabled(false);
		btUpdate.setEnabled(false);

		tfFields[0].setText("");
		tfFields[1].setText("");
		tfFields[2].setText("");
		tfFields[3].setText("");
	}//delete
}
