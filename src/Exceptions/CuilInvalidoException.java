package Exceptions;

@SuppressWarnings("serial")
public class CuilInvalidoException extends Exception {

	public CuilInvalidoException() {
		
	}

	@Override
	public String getMessage() {
	
		return " No es v�lido, debe estar compuesto por 9 n�meros.";
 }

}
