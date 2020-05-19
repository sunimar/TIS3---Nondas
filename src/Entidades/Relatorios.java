package Entidades;

import DAO.ServicoDAO;
import DAO.VendasDAO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Relatorios {
	VendasDAO vendasDAO;
	ServicoDAO servicosDAO;

	public Relatorios() throws IOException {
		vendasDAO = new VendasDAO("VendasDAO");
		servicosDAO = new ServicoDAO("ServicoDAO");
	}

	public List<OrdemVenda> vendasPorData(String data) throws IOException {
		List<OrdemVenda> produtos = null;
		produtos = vendasDAO.getAll().stream().filter(a -> new SimpleDateFormat("MMyyyy").format(a.getData()).equals(data)).collect(Collectors.toList());
		return produtos;
	}

	public List<OrdemVenda> vendasPorProduto(String data, long id) throws IOException{
		List<OrdemVenda> produtos = null;
		produtos = 	vendasDAO.getAll().stream().filter(a -> new SimpleDateFormat("MMyyyy").format(a.getData()).equals(data)
				&& a.produtoExiste(id) == true).collect(Collectors.toList());
		return produtos;
	}

	public List<OrdemServico> servicosPorData (String data) throws IOException{
		List<OrdemServico> servs = null;
		servs = servicosDAO.getAll().stream().filter(a -> new SimpleDateFormat("MMyyyy").format(a.getData()).equals(data)).collect(Collectors.toList());
		return servs;
	}

	public List<OrdemServico> servicosPorCliente (String data, long cpfCnpj) throws IOException{
		List<OrdemServico> servs = null;
		servs = servicosDAO.getAll().stream().filter(a -> a.getCliente().getCpfCnpj() == cpfCnpj
				&& new SimpleDateFormat("MMyyyy").format(a.getData()).equals(data)).collect(Collectors.toList());
		return servs;
	}

}//class