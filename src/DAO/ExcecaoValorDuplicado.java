package DAO;

public class ExcecaoValorDuplicado extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoValorDuplicado(String tipo, float cpfCnpj) {
		super (tipo + cpfCnpj + " já está cadastrado!");
	}

}
