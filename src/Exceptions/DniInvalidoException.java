package Exceptions;

public class DniInvalidoException {

	@SuppressWarnings("serial")
	public class DniInvalidoIOException extends Exception{

		public DniInvalidoIOException() {
		
	 }
	 

		@Override
		public String getMessage() {
		
			return " No es v�lido, debe estar compuesto por 7 u 8 n�meros.";
	 }
								
	}

}
