package Telas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import Entidades.Cliente;

public class TelaCliente extends JFrame{
	static JFrame f;
	JPanel jp;
	Box hb, vb;

	Box[] hbFields;
	JLabel[] lbFields;
	JTextField[] tfFields;

	JButton btCreate, btRetrieve, btUpdate, btDelete, btShow, btVol;

	int lastSelected = -1;

	public TelaCliente(){
		f = new JFrame("Cliente"); 
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
				btCreate();
			}
		});

		btRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btRetrieveAluno();
			}
		});

		btUpdate.setEnabled(false);
		btUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btUpdateAluno();
			}
		});

		btDelete.setEnabled(false);
		btDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btDeleteAluno();
			}
		});

		btShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btShowAlunos();
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
		tfFields[0].setEditable(false);
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

		lbFields[2] = new JLabel("CPF: ");
		tfFields[2] = new JTextField(10);
		hbFields[2] = Box.createHorizontalBox();
		hbFields[2].add(lbFields[2]);
		hbFields[2].add(tfFields[2]);
		vb.add(hbFields[2]);

		lbFields[3] = new JLabel("Email: ");
		tfFields[3] = new JTextField(10);
		hbFields[3] = Box.createHorizontalBox();
		hbFields[3].add(lbFields[3]);
		hbFields[3].add(tfFields[3]);
		vb.add(hbFields[3]);

		add(jp);
		
		setSize(600,400);
		show();
	}

	public void btShowAlunos(){
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(400, 400));

		JTextArea taText = new JTextArea();
		taText.setEditable(false);
		JScrollPane spScroll = new JScrollPane(taText);
		spScroll.setMaximumSize(new Dimension(400, 400));
		spScroll.setPreferredSize(new Dimension(400, 400));

		taText.setText("");
		try
		{
			List<Aluno> l = adao.getAll();
			for (Aluno a : l)
				taText.append(a + "\n");
		} catch (Exception ex) {ex.printStackTrace();}

		panel.add(spScroll);
		UIManager.put("OptionPane.minimumSize",new Dimension(400, 400));
		JOptionPane.showMessageDialog(null, panel, "Alunos", JOptionPane.PLAIN_MESSAGE);
	}


	public void btCreate(){
		
		String nome = tfFields[1].getText();
		String cpf = tfFields[2].getText();
		String email = tfFields[3].getText();

		if (nome.equals("") || cpf.equals("") || cpf.length()>11 || cpf.length()<11 || cpf.equals("00000000000")
				|| email.equals("")){
			JOptionPane.showMessageDialog(null, "Os campos: matricula, nome, disciplina e media devem ser preenchidos!");
			return;
		}

		Cliente cli = new Cliente();
		cli.setNome(nome);
		//cli.setCpf(Integer.parseInt(cpf));
		cli.setEmail(email);
		
		cli.print();
		
		JOptionPane.showMessageDialog(null, nome + " inserido com sucesso!");
	}//create

	public void btRetrieveAluno()
	{
		String id = "";
		while (id.equals(""))
			id = JOptionPane.showInputDialog("Digite o ID do aluno");

		Aluno aluno = adao.get(Integer.parseInt(id));
		tfFields[0].setText( (lastSelected = aluno.getId()) + "");
		tfFields[1].setText(aluno.getMatricula() + "");
		tfFields[2].setText(aluno.getNome());
		tfFields[3].setText(aluno.getDisciplina());
		tfFields[4].setText(aluno.getMediaDisciplina() + "");
		tfFields[5].setText(aluno.getStatusDisciplina() ? "true" : "false");

		btUpdate.setEnabled(true);
		btDelete.setEnabled(true);
	}

	public void btUpdateAluno()
	{
		String id = tfFields[0].getText();
		String matricula = tfFields[1].getText();
		String nome = tfFields[2].getText();
		String disciplina = tfFields[3].getText();
		String media = tfFields[4].getText();

		if (   matricula.equals("") || nome.equals("")
				|| disciplina.equals("") || media.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Os campos: matricula, nome, disciplina e media devem ser preenchidos!");
			return;
		}

		Aluno aluno = new Aluno(Integer.parseInt(matricula), nome, disciplina, Double.parseDouble(media), false);
		aluno.setStatusDisciplina( aluno.getStatusDisciplina() );
		aluno.setId(Integer.parseInt(id));
		try
		{
			adao.update(aluno);
		} catch (Exception e){e.printStackTrace();}

		JOptionPane.showMessageDialog(null, nome + " atualizado com sucesso!");
	}

	public void btDeleteAluno()
	{
		String id = tfFields[0].getText();
		String matricula = tfFields[1].getText();
		String nome = tfFields[2].getText();
		String disciplina = tfFields[3].getText();
		String media = tfFields[4].getText();

		if (   matricula.equals("") || nome.equals("")
				|| disciplina.equals("") || media.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Os campos: matricula, nome, disciplina e media devem ser preenchidos!");
			return;
		}

		Aluno aluno = new Aluno(Integer.parseInt(matricula), nome, disciplina, Double.parseDouble(media), false);
		aluno.setStatusDisciplina( aluno.getStatusDisciplina() );
		aluno.setId(Integer.parseInt(id));
		try
		{
			adao.delete(aluno);
		} catch (Exception e){e.printStackTrace();}

		JOptionPane.showMessageDialog(null, nome + " excluido com sucesso!");

		btDelete.setEnabled(false);
		btUpdate.setEnabled(false);

		tfFields[0].setText("");
		tfFields[1].setText("");
		tfFields[2].setText("");
		tfFields[3].setText("");
		tfFields[4].setText("");
		tfFields[5].setText("");
	}
	
	public void btShowAlunos() {
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(400, 400));

		JTextArea taText = new JTextArea();
		taText.setEditable(false);
		JScrollPane spScroll = new JScrollPane(taText);
		spScroll.setMaximumSize(new Dimension(400, 400));
		spScroll.setPreferredSize(new Dimension(400, 400));

		AlunoDAO adao = new AlunoDAO();

		taText.setText("");
		try
		{
			List<Aluno> l = adao.getAll();

			for (Aluno a : l)
				taText.append(a + "\n");
		} catch (Exception ex) {ex.printStackTrace();}

		panel.add(spScroll);
		UIManager.put("OptionPane.minimumSize",new Dimension(400, 400));
		JOptionPane.showMessageDialog(null, panel, "Lista de Alunos", JOptionPane.PLAIN_MESSAGE);
	}//show
}
