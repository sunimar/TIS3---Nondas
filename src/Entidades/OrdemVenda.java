package Entidades;

import java.util.Date;
import java.util.List;

public class OrdemVenda {
	private Date data;
	private long codVenda;
	private List<Produto> produtos;
	private float valorTotal;

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

	public void setProduto(Produto prod) {
		this.produtos.add(prod);
	}

	public float getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal() {
		float temp=0;
		for(Produto prod : produtos) {
			temp = temp + prod.getPrecoVenda();
		}
		this.valorTotal = temp;
	}

	public long getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(long codVenda) {
		this.codVenda = codVenda;
	}

	public String toString() {
		return "Data: " + " " + this.data.toString() + " " + this.codVenda + " " +  this.valorTotal;
	}

}
