package Entidades;

public class Localidad {
	int idLocalidad;
	int idProvincia;
	String localidad;
	
	public Localidad(int idLocalidad) {
		this.idLocalidad=idLocalidad;
	}
	public Localidad(int idLocalidad, int idProvincia, String localidad) {
		super();
		this.idLocalidad = idLocalidad;
		this.idProvincia = idProvincia;
		this.localidad = localidad;
	}
	public int getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(int idLocalidad) {
		idLocalidad = idLocalidad;
	}
	public int getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		idProvincia = idProvincia;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
}
