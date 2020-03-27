package Entidades;

public class Produto {
	private int codProd;
	private double precoCusto;
	private double precovenda;

	public Produto() {}

	public int getCodProd() {
		return codProd;
	}

	public void setCodProd(int codProd) {
		this.codProd = codProd;
	}

	public double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public double getPrecovenda() {
		return precovenda;
	}

	public void setPrecovenda(double precovenda) {
		this.precovenda = precovenda;
	}


}
