package Entidades;

public class Provincia {
	
	int idProvincia;
	String provincia;
	
	public Provincia(int idProvincia, String provincia) {
		super();
		this.idProvincia = idProvincia;
		this.provincia = provincia;
	}
	
	public int getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	
}
