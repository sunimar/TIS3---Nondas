package Entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

public class Servico  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoServicos tipoServi�o;
	private long cpfCnpjCliente;
	private double valor; 
	private LocalDateTime entrada;
	private LocalDateTime previsaoEntrega;
	private LocalDateTime entrega;
	private int numOs;
	private Random random = new Random();
	
	public Servico(long cpfCnpjCliente, LocalDateTime entrada, TipoServicos tipoServi�o, double valor, LocalDateTime previsaoEntrega) {
		
		setEntrada(entrada); 
		setTipoServi�o(tipoServi�o);
		setValor(valor);
		setPrevisaoEntrega(previsaoEntrega);
		setNumOs();
		setEntrega(null);
		
	}

	public int getNumOs() {
		return numOs;
	}


	public void setNumOs() {
		this.numOs = random.nextInt(100000);
	}


	public TipoServicos getTipoServi�o() {
		return tipoServi�o;
	}


	public void setTipoServi�o(TipoServicos tipoServi�o) {
		this.tipoServi�o = tipoServi�o;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}


	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}


	public LocalDateTime getPrevisaoEntrega() {
		return previsaoEntrega;
	}


	public void setPrevisaoEntrega(LocalDateTime previsaoEntrega) {
		this.previsaoEntrega = previsaoEntrega;
	}


	public LocalDateTime getEntrega() {
		return entrega;
	}


	public void setEntrega(LocalDateTime entrega) {
		this.entrega = entrega;
	}

	public long getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}


	public void setCpfCnpjCliente(long cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}
	
	
}
