package Entidades;

import java.util.Date;
import java.util.List;

public class OrdemVenda {
	private Date data;
	private List<Produto> produtos;
	private double valorTotal;
	private long codVenda;
	
	public OrdemVenda() {}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public long getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(long codVenda) {
		this.codVenda = codVenda;
	}
	
	
	
}
