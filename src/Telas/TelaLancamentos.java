package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DAO.ServicoDAO;
import Entidades.OrdemServico;
import Entidades.Produto;

public class TelaLancamentos {
	// JFrame 
	static JFrame f; 

	// JButton 
	static JButton btOv, btOs, btConOs, btConOv, btVol; 

	// label to display text 
	static JLabel l;

	Box box;

	public TelaLancamentos() {
		// create a new frame with text field and button 
		f = new JFrame("Lancamentos"); 
		// create a label to display text 
		l = new JLabel("Selecione a opcao desejada:"); 

		// create a new buttons 
		btOv = new JButton("Ordem de Venda"); 
		btOs = new JButton("Ordem de Servico");
		btConOs = new JButton("Consultar Ordens de Serviço");
		btConOv = new JButton("Consultar Ordens de Venda");
		btVol = new JButton("Voltar");

		btOv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				try {
					new TelaOrdemVenda();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		btOs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				new TelaOrdemServico();
			}
		});

		btConOv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//f.setVisible(false);
				//new TelaProduto();
			}
		});
		
		btConOs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					consultaOs();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
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
		box.add(btOv); 
		box.add(btOs);
		box.add(btConOv);
		box.add(btConOs);
		box.add(btVol);

		p.add(box);

		// add panel to frame 
		f.add(p); 

		// set the size of frame 
		f.setSize(300, 300); 

		f.show();

	}//builder
	
	public void consultaOs() throws IOException {
		ServicoDAO serv = new ServicoDAO("ServicoDAO");
		
		JPanel jpOrdens = new JPanel();

		DefaultListModel listModelOrdens = new DefaultListModel();
		JList jlOrdens = new JList<Produto>(listModelOrdens);

		List<OrdemServico> ordens = serv.getAll();

		for(OrdemServico i : ordens) {
			listModelOrdens.addElement(i);
		}
		jpOrdens.add(jlOrdens);

		JOptionPane.showMessageDialog(null,jpOrdens,"Ordens de Serviço",JOptionPane.INFORMATION_MESSAGE);
		OrdemServico os = (OrdemServico) jlOrdens.getSelectedValue();
		
		if(os != null) {
			//System.out.println("works");
			TelaOrdemServico.os=os;
			System.out.println(os.toString());
			new TelaOrdemServico();
			f.dispose();
		}
		
	}
}//tela lancamentos
