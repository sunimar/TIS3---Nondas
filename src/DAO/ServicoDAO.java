package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Entidades.Produto;
import Entidades.Servico;

public class ServicoDAO implements DAO<Servico, String> {

	private List<Servico> servicos;
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public ServicoDAO(String filename) throws IOException {
		servicos = new ArrayList<Servico>();
		file = new File(filename);
		if (file.exists())
			readFromFile();
	}

	@Override
	public void add(Servico cooperativa) {
		servicos.add(cooperativa);
		saveToFile();
	}

	@Override
	public Servico get(String chave) {
		for (Servico serv : servicos) {
			if (Long.parseLong(chave) == serv.getCpfCnpjCliente()) {
				return serv;
			}
		}
		return null;

	}

	@Override
	public List<Servico> getAll() {

		return servicos;
	}

	@Override
	public void update(Servico os) {
		
	int index = -1;
		
		for (Servico tes : servicos) {
			if(tes.getNumOs() == os.getNumOs()) {
				index = servicos.indexOf(tes);
				break;
			}
		}
		
		if (index != -1) {
			servicos.set(index, os);
			saveToFile();
		}
	}

	@Override
	public void remove(Servico os) {
		
		int index = -1;
		
		for (Servico tes : servicos) {
			if(tes.getNumOs() == os.getNumOs()) {
				index = servicos.indexOf(tes);
				break;
			}
		}
		
		if (index != -1) {
			servicos.remove(index);
		}
		saveToFile();

	}

	private void readFromFile() {
		Servico serv;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				serv = (Servico) inputFile.readObject();
				servicos.add(serv);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler Ordem de Servico do disco!");
			e.printStackTrace();
		}
	}

	private void saveToFile() {
		try (FileOutputStream fos = new FileOutputStream(file, false);
				ObjectOutputStream outputFile = new ObjectOutputStream(fos)) {

			for (Servico p : servicos) {
				outputFile.writeObject(p);
			}
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
