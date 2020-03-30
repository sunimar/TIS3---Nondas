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

public class ProdutoDAO implements DAO<Produto, Integer> {

	private List<Produto> produtos;
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public ProdutoDAO(String filename) throws IOException {
		produtos = new ArrayList<Produto>();
		file = new File(filename);
		if (file.exists())
			readFromFile();
	}

	@Override
	public void add(Produto cooperativa) {
		produtos.add(cooperativa);
		saveToFile();
	}

	@Override
	public Produto get(Integer chave) {
		for (Produto pro : produtos) {
			if (chave.longValue() == pro.getIdProduto()) {
				return pro;
			}
		}
		return null;

	}

	@Override
	public List<Produto> getAll() {

		return produtos;
	}

	@Override
	public void update(Produto os) {
		int index = produtos.indexOf(os);
		if (index != -1) {
			produtos.set(index, os);
			saveToFile();
		}
	}

	@Override
	public void remove(Produto os) {
		int index = produtos.indexOf(os);
		if (index != -1) {
			produtos.remove(index);
		}
		saveToFile();

	}

	private void readFromFile() {
		Produto pro;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				pro = (Produto) inputFile.readObject();
				produtos.add(pro);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler produto no disco!");
			e.printStackTrace();
		}
	}

	private void saveToFile() {
		try (FileOutputStream fos = new FileOutputStream(file, false);
				ObjectOutputStream outputFile = new ObjectOutputStream(fos)) {

			for (Produto p : produtos) {
				outputFile.writeObject(p);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar produto no disco!");
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
