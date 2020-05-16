package DAO;

import Entidades.OrdemServico;
import Entidades.OrdemVenda;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class VendasDAO implements DAO<OrdemVenda, String> {

	private final List<OrdemVenda> ordemVenda;
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public VendasDAO(String filename) throws IOException {
		ordemVenda = new ArrayList<OrdemVenda>();
		file = new File(filename);
		if (file.exists())
			readFromFile();
	}

	@Override
	public void add(OrdemVenda ov) {
		ordemVenda.add(ov);
		saveToFile();
	}

	@Override
	public OrdemVenda get(String chave) {
		for (OrdemVenda ov : ordemVenda) {
			if (Long.parseLong(chave) == ov.getCodVenda()) {
				return ov;
			}
		}
		return null;

	}

	@Override
	public List<OrdemVenda> getAll() {

		
		return ordemVenda;
	}

	@Override
	public void update(OrdemVenda ov) {
		
	int index = -1;
		
		for (OrdemVenda tes : ordemVenda) {
			if(tes.getCodVenda() == ov.getCodVenda()) {
				index = ordemVenda.indexOf(tes);
				break;
			}
		}

		if (index != -1) {
			ordemVenda.set(index, ov);
			saveToFile();
		}
	}

	@Override
	public void remove(OrdemVenda os) {
		
		int index = -1;
		
		for (OrdemVenda tes : ordemVenda) {
			if(tes.getCodVenda() == os.getCodVenda()) {
				index = ordemVenda.indexOf(tes);
				break;
			}
		}
		
		if (index != -1) {
			ordemVenda.remove(index);
		}
		saveToFile();

	}

	private void readFromFile() {
		OrdemVenda serv;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				serv = (OrdemVenda) inputFile.readObject();
				ordemVenda.add(serv);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler Ordem de Servico do disco!");
			e.printStackTrace();
		}
	}

	private void saveToFile() {
		try (FileOutputStream fos = new FileOutputStream(file, false);
				ObjectOutputStream outputFile = new ObjectOutputStream(fos)) {

			for (OrdemVenda p : ordemVenda) outputFile.writeObject(p);

			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar Ordem de Servico no disco!");
			e.printStackTrace();
		}
	}

	private void close() throws IOException {
		outputFile.close();
		fos.close();
	}

	@Override
	protected void finalize() throws Throwable {
		this.close();
	}

}
