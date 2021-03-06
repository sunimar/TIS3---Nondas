package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Entidades.Cliente;

public class CadastroClienteDAO implements DAO<Cliente, String> {

	private List<Cliente> clientes;
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public CadastroClienteDAO(String filename) throws IOException {
		clientes = new ArrayList<Cliente>();
		file = new File(filename);
		if (file.exists())
			readFromFile();
	}

	@Override
	public void add(Cliente cli) throws ExcecaoValorDuplicado{
		
		for (Cliente busca : clientes) {
			if (cli.getCpfCnpj() == busca.getCpfCnpj()) {
				throw new ExcecaoValorDuplicado("O CpfCnpj: ", cli.getCpfCnpj());
			}
			
			}
		clientes.add(cli);
		saveToFile();	
		
	}

	@Override
	public Cliente get(String chave) {
		for (Cliente cli : clientes) {
			if (Long.parseLong(chave) == cli.getCpfCnpj()) {
				return cli;
			}
		}
		return null;

	}

	@Override
	public List<Cliente> getAll() {

		return clientes;
	}

	@Override
	public void update(Cliente cli) {
		int index = -1;
		
		for (Cliente tes : clientes) {
			if(tes.getCpfCnpj() == cli.getCpfCnpj()) {
				index = clientes.indexOf(tes);
				break;
			}
		}
		if (index != -1) {
			clientes.set(index, cli);
			saveToFile();
		}
	}

	@Override
	public void remove(Cliente cli) {
		int index = -1;
		
		for (Cliente tes : clientes) {
			if(tes.getCpfCnpj() == cli.getCpfCnpj()) {
				index = clientes.indexOf(tes);
				break;
			}
		}
		
		if (index != -1) {
			clientes.remove(index);
		}
		saveToFile();

	}

	private void readFromFile() {
		Cliente cli;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				cli = (Cliente) inputFile.readObject();
				clientes.add(cli);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler Cliente do disco!");
			e.printStackTrace();
		}
	}

	private void saveToFile() {
		try (FileOutputStream fos = new FileOutputStream(file, false);
				ObjectOutputStream outputFile = new ObjectOutputStream(fos)) {

			for (Cliente p : clientes) {
				outputFile.writeObject(p);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar Clientes no disco!");
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
