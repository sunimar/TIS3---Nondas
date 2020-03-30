package Entidades;


public class Produto{
	private int id;
	private String nome;
	private String descricao;
	private float precoCompra;
	private float precoVenda;

	public Produto(String nome, float precoCompra, float precoVenda) throws ExcecaoValorInvalido {
		this.setNome(nome);
		this.setDescricao(descricao);
		this.setPrecoCompra(precoCompra);
		this.setPrecoVenda(precoVenda);
	}

	public Produto(){}

	public String getNome() {
		return nome;
	}

	public float getPrecoCompra() {
		return precoCompra;
	}

	public float getPrecoVenda() {
		return precoVenda;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setPrecoCompra(float precoCompra)throws ExcecaoValorInvalido{

		if (precoCompra > 0) {
			this.precoCompra = precoCompra;
		}else {
			throw new ExcecaoValorInvalido(precoCompra + "Digite um valor valido!");
		}
	}

	public void setPrecoVenda(float precoVenda) throws ExcecaoValorInvalido {
		if (precoVenda > 0) {
			this.precoVenda = precoVenda;
		}else {
			throw new ExcecaoValorInvalido(precoCompra + "Digite um valor valido!");
		}
	}

	@Override
	public String toString() {
		return "Nome do Produto: " + nome + "\nPreco de Compra: " + precoCompra + "\nPreco de Venda: " + precoVenda;
	}

	public void print() {
		System.out.println(this.nome + this.precoCompra + this.precoVenda);
	}
}//class
