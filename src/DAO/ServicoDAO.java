package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Entidades.OrdemServico;


public class ServicoDAO implements DAO<OrdemServico, String> {

	private final List<OrdemServico> ordemServicos;
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public ServicoDAO(String filename) throws IOException {
		ordemServicos = new ArrayList<OrdemServico>();
		file = new File(filename);
		if (file.exists())
			readFromFile();
	}

	@Override
	public void add(OrdemServico os) {
		ordemServicos.add(os);
		saveToFile();
	}

	@Override
	public OrdemServico get(String chave) {
		for (OrdemServico serv : ordemServicos) {
			if (Long.parseLong(chave) == serv.getCodServ()) {
				System.out.println(serv.getCodServ());
				return serv;
			}
		}
		return null;
	}

	@Override
	public List<OrdemServico> getAll() {

		return ordemServicos;
	}

	@Override
	public void update(OrdemServico os) {
		
	int index = -1;
		
		for (OrdemServico tes : ordemServicos) {
			if(tes.getCodServ() == os.getCodServ()) {
				index = ordemServicos.indexOf(tes);
				break;
			}
		}
		
		if (index != -1) {
			System.out.println("cheguei" + index);
			ordemServicos.set(index, os);
			saveToFile();
		}
	}

	@Override
	public void remove(OrdemServico os) {
		
		int index = -1;
		
		for (OrdemServico tes : ordemServicos) {
			if(tes.getCodServ() == os.getCodServ()) {
				index = ordemServicos.indexOf(tes);
				break;
			}
		}
		
		if (index != -1) {
			ordemServicos.remove(index);
		}
		saveToFile();

	}

	private void readFromFile() {
		OrdemServico serv;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				serv = (OrdemServico) inputFile.readObject();
				ordemServicos.add(serv);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler Ordem de Servico do disco!");
			e.printStackTrace();
		}
	}

	private void saveToFile() {
		try (FileOutputStream fos = new FileOutputStream(file, false);
				ObjectOutputStream outputFile = new ObjectOutputStream(fos)) {

			for (OrdemServico p : ordemServicos) outputFile.writeObject(p);

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
