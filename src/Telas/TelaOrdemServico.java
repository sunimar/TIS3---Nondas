package Telas;

import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class TelaOrdemServico extends JFrame {

	JPanel ui, dados,status,servicos;
	Date date;
	GridBagConstraints c = new GridBagConstraints();
	GridLayout grid;
	/* Dados. */
	JLabel jlData, jlCod,jlMarca, jlModelo, jlSerie, jlDef;
	JTextField tfMarca, tfModelo, tfSerie;
	JTextArea taDef;

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
		arrayStatus[1] = new JCheckBox ("Sem Chip");
		arrayStatus[2] = new JCheckBox ("Sem Chip");
		arrayStatus[3] = new JCheckBox ("Sem Chip");
		arrayStatus[4] = new JCheckBox ("Sem Chip");
		arrayStatus[5] = new JCheckBox ("Sem Chip");
		arrayStatus[6] = new JCheckBox ("Sem Chip");
		arrayStatus[7] = new JCheckBox ("Sem Chip");
		for(JCheckBox jc : arrayStatus) {
			status.add(jc);
		}
		/*************servicos***************/
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
		ui.setBorder(new TitledBorder("Ordem de servico"));
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
