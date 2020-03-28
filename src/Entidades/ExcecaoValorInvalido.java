package Entidades;

public class ExcecaoValorInvalido extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcecaoValorInvalido(String mensagem) {
		super ("Valor Invalido: " + mensagem);
	}
}
