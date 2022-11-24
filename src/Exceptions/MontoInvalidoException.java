package Exceptions;

@SuppressWarnings("serial")
public class MontoInvalidoException extends Exception {
	
	
	public MontoInvalidoException() {
	
	}

	@Override
	public String getMessage() {
		
		return " No es válido. Debe estar compuesto por números. ";
	}
	
	

}
