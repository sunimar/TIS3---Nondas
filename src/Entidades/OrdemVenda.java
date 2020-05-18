package Entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdemVenda implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date data;
	private long codVenda;
	public List<Produto> produtos;
	private float valorTotal;

	public OrdemVenda() {
		data = new Date();
		codVenda = 0;
		produtos = new ArrayList<Produto>();
		valorTotal = 0;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}
	
	public boolean produtoExiste(long id) {
		
		for(Produto i : this.produtos) {
			if(i.getIdProduto() == id) {
				return true;
			}
		}
		
		return false;
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
		String tmp = new SimpleDateFormat("EEE, dd 'de' MMM 'de' yyyy, HH:mm").format(this.data);
		return tmp + " " + this.codVenda + " " +  this.valorTotal;
	}

}
