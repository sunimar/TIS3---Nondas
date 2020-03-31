package Entidades;

import java.io.Serializable;
import java.util.Random;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	//private int codCli;
	private String nome;
	private long cpfCnpj;
	private String email;
	private String telefone;
	Random random = new Random();

	public Cliente(){}

	public Cliente(long cpfCnpj, String nome, String email, String telefone) {

		setCpfCnpj(cpfCnpj);
		setNome(nome);
		setEmail(email);
		setTelefone(telefone);
		//setCodCli();

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(long f) {
		this.cpfCnpj = f;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	public int getCodCli() {
		return codCli;
	}

	public void setCodCli() {
		this.codCli = random.nextInt(1000);
	}
	 */

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void print() {
		System.out.println(this.nome + " " + this.cpfCnpj + " " + this.email + " " + this.telefone);
	}


}//class cliente