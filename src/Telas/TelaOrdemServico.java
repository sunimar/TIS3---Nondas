package Telas;

import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class TelaOrdemServico extends JFrame {

	JPanel ui, dados,status,servicos;
	Date date;
	GridBagConstraints c = new GridBagConstraints();
	GridLayout grid;
	/* Dados. */
	JLabel jlData, jlCod,jlMarca, jlModelo, jlSerie, jlDef, jlVal;
	JTextField tfMarca, tfModelo, tfSerie, tfVal;
	JTextArea taDef;
	JButton btVol, btSalvar;

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
		JCheckBox [] arrayStatus = new JCheckBox[8];
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
		JCheckBox [] arrayServicos = new JCheckBox[8];
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
		jlVal = new JLabel("Valor Total: ");
		tfVal = new JTextField(40);
		btVol = new JButton("Cancelar e Voltar");
		btSalvar = new JButton("Salvar Ordem de Servico");
		servicos.add(jlVal);
		servicos.add(tfVal);
		servicos.add(btVol);
		servicos.add(btSalvar);
		
		/**botoes**/
		btVol.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		/*****************declaracao final para construir a tela***********************************/
		add(ui);
		ui.add(dados);
		ui.add(status);
		ui.add(servicos);
		
		setSize(700,500);
		setVisible(true);
	}//builder

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
		Date date = new Date();

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

		ui.add(dados);
	}

}
