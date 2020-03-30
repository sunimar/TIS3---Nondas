package Entidades;

public class Servico {
	private int codServ;
	private String nomeServ;
	private double precoServ;
	private Produto [] prodRel;

	public Servico() {}

	public int getCodServ() {
		return codServ;
	}

	public void setCodServ(int codServ) {
		this.codServ = codServ;
	}

	public String getNomeServ() {
		return nomeServ;
	}

	public void setNomeServ(String nomeServ) {
		this.nomeServ = nomeServ;
	}

	public double getPrecoServ() {
		return precoServ;
	}

	public void setPrecoServ(double precoServ) {
		this.precoServ = precoServ;
	}

	public void print() {
		System.out.println(this.nomeServ + this.precoServ);
	}
	
	
}
