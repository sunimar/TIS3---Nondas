package Entidades;

import DAO.ServicoDAO;
import DAO.VendasDAO;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Relatorios {
    VendasDAO vendasDAO;
    ServicoDAO servicosDAO;

    public Relatorios() {
    }

    void vendasPorData(Date data) throws IOException {
        vendasDAO = new VendasDAO("VendasDAO");

        //Esse linha irá se tornar um array só com a informação solicitada no parâmetro data, usei o ForEach só de exemplo para testar.
        vendasDAO.getAll().stream().filter(a -> a.getData() == data).collect(Collectors.toList()).forEach(retorno -> {
            System.out.println(retorno.getData());
        });
    }

    void vendasPorProduto(long id) throws IOException{
        List<Produto> produtos = null;
        vendasDAO = new VendasDAO("VendasDAO");

        vendasDAO.getAll().stream().filter(a -> a.getProdutos() != null).collect(Collectors.toList());

    }

    void servicosPorData (Date data) throws IOException{
        servicosDAO = new ServicoDAO("ServicosDAO");

        //Esse linha irá se tornar um array só com a informação solicitada no parâmetro data, depois do método Collectors.toList() pode-se colocar o for each para iterar a tabela
        servicosDAO.getAll().stream().filter(a -> a.getData() == data).collect(Collectors.toList());
    }

    void servicosPorCliente (long cpfCnpj){
        servicosDAO = new ServicoDAO("ServicosDAO");

        servicosDAO.getAll().stream().filter(a -> a.getCliente().getCpfCnpj() == cpfCnpj).collect(Collectors.toList());

    }

    void servicoPorTipo (String tipoServico){
        servicosDAO = new ServicoDAO("ServicosDAO");

        servicosDAO.getAll().stream().filter(a -> a.getServicos() == tipoServico).collect(Collectors.toList());
    }





}
