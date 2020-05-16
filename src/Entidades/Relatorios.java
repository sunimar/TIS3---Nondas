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

    public Relatorios() throws IOException {
        servicosDAO = new ServicoDAO("ServicosDAO");
        vendasDAO = new VendasDAO("VendasDAO");
    }

    List<OrdemVenda> vendasPorData(Date data) throws IOException {

        //Esse linha irá se tornar um array só com a informação solicitada no parâmetro data, usei o ForEach só de exemplo para testar.
         List<OrdemVenda> collect = vendasDAO.getAll().stream().filter(a -> a.getData() == data).collect(Collectors.toList());

         return collect;

    }

    List<OrdemVenda> vendasPorProduto(long id) throws IOException{

         List<OrdemVenda> collect = vendasDAO.getAll().stream().filter(a -> a.getProdutos().iterator().equals(id)).collect(Collectors.toList());

        return collect;

    }

    List<OrdemServico> servicosPorData (Date data) throws IOException{

        //Esse linha irá se tornar um array só com a informação solicitada no parâmetro data, depois do método Collectors.toList() pode-se colocar o for each para iterar a tabela
         List<OrdemServico> collect = servicosDAO.getAll().stream().filter(a -> a.getData() == data).collect(Collectors.toList());

      return collect;
    }

    List<OrdemServico> servicosPorCliente (long cpfCnpj) throws IOException {

        List<OrdemServico> collect = servicosDAO.getAll().stream().filter(a -> a.getCliente().getCpfCnpj() == cpfCnpj).collect(Collectors.toList());
        return collect;
    }

    List<OrdemServico> servicoPorTipo (String tipoServico) throws IOException {

        List<OrdemServico> collect = servicosDAO.getAll().stream().filter(a -> a.getServicos().iterator().equals(tipoServico)).collect(Collectors.toList());

        return collect;
    }

}
