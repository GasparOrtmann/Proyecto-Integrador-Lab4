package Exceptions;

@SuppressWarnings("serial")
public class MontoInvalidoException extends Exception {
	
	
	public MontoInvalidoException() {
	
	}

	@Override
	public String getMessage() {
		
		return " No es v�lido. Debe estar compuesto por n�meros. ";
	}
	
	

}
