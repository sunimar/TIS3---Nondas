package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdemServico implements Serializable{

	private static final long serialVersionUID = 1L;
	private Date data;
	private long codServ;
	private String marca;
	private String modelo;
	private String numSerie;
	private List<String> status;
	private List<String> servicos;
	private double valorTotal;
	private Cliente cliente;
	private String defeitos;
	private String obs;

	public OrdemServico() {
		status = new ArrayList<String>();
		servicos = new ArrayList<String>();
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public long getCodServ() {
		return codServ;
	}

	public void setCodServ(long codServ) {
		this.codServ = codServ;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status.add(status);
	}

	public List<String> getServicos() {
		return servicos;
	}

	public void setServico(String servico) {
		this.servicos.add(servico);
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDefeitos() {
		return defeitos;
	}

	public void setDefeitos(String defeitos) {
		this.defeitos = defeitos;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String toString() {
		return "Data: " + this.data.toString() + " " +this.codServ  + " " + this.cliente.getNome();
	}

}
