package Telas;

import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

import DAO.CadastroClienteDAO;
import DAO.ServicoDAO;
import Entidades.Cliente;
import Entidades.OrdemServico;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class TelaOrdemServico extends JFrame {
	public static ServicoDAO servicoDAO;
	JPanel ui, dados,status,servicos;
	Date date;
	GridBagConstraints c = new GridBagConstraints();
	GridLayout grid;
	/* Dados. */
	JLabel jlData, jlCod,jlMarca, jlModelo, jlSerie, jlDef, jlVal;
	public static JLabel jlCli = new JLabel();
	public static Cliente cli;
	JCheckBox [] arrayStatus;
	JCheckBox [] arrayServicos;
	OrdemServico os;
	JTextField tfMarca, tfModelo, tfSerie, tfVal;
	JTextArea taDef;
	JButton btVol, btSalvar, btCli;

	public TelaOrdemServico() {
		super("Ordem de Servico");
		/***********panels iniciais*****************************/
		ui = new JPanel(new BorderLayout(4,4));
		ui.setBorder(new TitledBorder("Ordem de servico"));
		ui.setLayout(new GridLayout(0,3));

		dados = new JPanel();
		dados.setLayout(new GridBagLayout());
		dados.setBorder(new TitledBorder("Dados"));

		dadosPanel();

		status = new JPanel();
		status.setLayout(new BoxLayout(status, BoxLayout.Y_AXIS));
		status.setBorder(new TitledBorder("Status do aperelho:"));
		servicos = new JPanel();
		servicos.setLayout(new BoxLayout(servicos, BoxLayout.Y_AXIS));
		servicos.setBorder(new TitledBorder("Servicos a serem feitos:"));
		/***************componentes internos de cada panel****************************************************/
		/*************dados preenchidos no metodo dadosPanel******************/
		/*************status**********/
		arrayStatus = new JCheckBox[8];
		arrayStatus[0] = new JCheckBox ("Sem Chip");
		arrayStatus[1] = new JCheckBox ("Sem Cartao de memória");
		arrayStatus[2] = new JCheckBox ("Sem Bateria");
		arrayStatus[3] = new JCheckBox ("Sem Tampa Traseira");
		arrayStatus[4] = new JCheckBox ("Aparelho Nao Liga");
		arrayStatus[5] = new JCheckBox ("Aparelho Queimou");
		arrayStatus[6] = new JCheckBox ("Tela Quebrada");
		arrayStatus[7] = new JCheckBox ("Tela Sem Funcionar");
		for(JCheckBox jc : arrayStatus) {
			status.add(jc);
		}
		/*************servicos***************/
		arrayServicos = new JCheckBox[8];
		arrayServicos[0] = new JCheckBox ("Limpeza");
		arrayServicos[1] = new JCheckBox ("Troca de Bateria");
		arrayServicos[2] = new JCheckBox ("Troca do Slot do Chip");
		arrayServicos[3] = new JCheckBox ("Troca de Conector");
		arrayServicos[4] = new JCheckBox ("Desoxidacao");
		arrayServicos[5] = new JCheckBox ("Troca de Tela");
		arrayServicos[6] = new JCheckBox ("Troca de Camera");
		arrayServicos[7] = new JCheckBox ("Troca de Sensor de Digital");
		for(JCheckBox jc : arrayServicos) {
			servicos.add(jc);
		}
		btCli = new JButton("Incluir Cliente");
		jlCli = new JLabel("Ainda sem cliente");
		btVol = new JButton("Cancelar e Voltar");
		btSalvar = new JButton("Salvar Ordem de Servico");
		servicos.add(btCli);
		servicos.add(jlCli);
		servicos.add(btVol);
		servicos.add(btSalvar);

		/**botoes**/
		btVol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaLancamentos.f.setVisible(true);
				dispose();

			}
		});

		btCli.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaCliente();

			}
		});

		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					salvarOs();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}

			}
		});
		/*****************declaracao final para construir a tela***********************************/
		add(ui);
		ui.add(dados);
		ui.add(servicos);
		ui.add(status);

		setSize(700,500);
		setVisible(true);
	}//builder

	public void salvarOs() throws IOException {
		os = new OrdemServico();
		os.setData(date);
		os.setCodServ(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(date)));

		if(tfMarca.getText().isEmpty() || tfModelo.getText().isEmpty() || tfSerie.getText().isEmpty()
				|| tfVal.getText().isEmpty() || taDef.getText().isEmpty() || cli==null) {
			JOptionPane.showMessageDialog(null, " Preencha todos os campos corretamente! ");
			return;

			//Método para salvar a OS
			try{
				servicoDAO = new ServicoDAO("ServicoDao");
			} catch(Exception ex) {ex.printStackTrace();}

		}

		for(JCheckBox jc : arrayStatus) {
			if(jc.isSelected()) {
				os.setStatus(jc.getText());
			}
		}

		for(JCheckBox jc : arrayServicos) {
			if(jc.isSelected()) {
				os.setServico(jc.getText());
			}
		}

		if(os.getStatus().isEmpty() || os.getServicos().isEmpty()) {
			JOptionPane.showMessageDialog(null, " Marque as caixas necessárias! ");
			return;
		}

		os.setCliente(cli);

		//dao OS//
		System.out.println(os.toString());
	}

	public void dadosPanel() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3,3,3,3);
		c.weightx = 0;
		c.weighty = 0;

		ui = new JPanel(new BorderLayout(4,4));
		ui.setBorder(new TitledBorder(""));
		ui.setLayout(new GridLayout(0,3));

		dados = new JPanel();
		dados.setLayout(new GridBagLayout());
		dados.setBorder(new TitledBorder("Dados"));

		/* Fill Dados. */
		date = new Date();

		jlData = new JLabel();
		jlData.setText(new SimpleDateFormat("EEE, dd 'de' MMM 'de' yyyy, HH:mm").format(date));
		jlCod = new JLabel("Numero: " + new SimpleDateFormat("yyyyMMddHHmmss").format(date));

		c.gridx = 0; c.gridy = 0;
		dados.add(jlData, c);
		c.gridx = 0; c.gridy = 1;
		dados.add(jlCod, c);

		jlMarca    = new JLabel("Marca: ");     tfMarca  = new JTextField();
		jlModelo   = new JLabel("Modelo: ");    tfModelo = new JTextField();
		jlSerie    = new JLabel("Num Serie: "); tfSerie  = new JTextField();
		jlDef      = new JLabel("Defeitos: ");  taDef    = new JTextArea();
		jlVal = new JLabel("Valor Total: ");	tfVal = new JTextField(40);

		c.gridx = 0; c.gridy = 2;
		dados.add(jlMarca, c);

		c.gridx = 0; c.gridy = 3;
		dados.add(tfMarca, c);

		c.gridx = 0; c.gridy = 4;
		dados.add(jlModelo, c);

		c.gridx = 0; c.gridy = 5;
		dados.add(tfModelo, c);

		c.gridx = 0; c.gridy = 6;
		dados.add(jlSerie, c);

		c.gridx = 0; c.gridy = 7;
		dados.add(tfSerie, c);

		c.gridx = 0; c.gridy = 8;
		dados.add(jlDef, c);

		c.gridx = 0; c.gridy = 9;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0;
		c.gridwidth = 2;
		c.weightx = 1;
		c.weighty = 1;

		taDef.setLineWrap(true);

		JScrollPane spScroll = new JScrollPane(taDef);
		dados.add(spScroll, c);

		c.weightx = 0;
		c.weighty = 0;

		c.gridx = 0; c.gridy = 10;
		dados.add(jlVal, c);

		c.gridx = 0; c.gridy = 11;
		dados.add(tfVal, c);

		ui.add(dados);
	}

}//class
