package Entidades;

public class Nacionalidad {
	int idNacionalidad;
	String nacionalidad;
	
	public Nacionalidad(int idNacionalidad, String nacionalidad) {
		this.idNacionalidad=idNacionalidad;
		this.nacionalidad=nacionalidad;
	}
	public Nacionalidad(int idNacionalidad) {
		this.idNacionalidad=idNacionalidad;
	}
	
	public int getIdNacionalidad() {
		return idNacionalidad;
	}
	public void setIdNacionalidad(int idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	@Override
	public String toString() {
		return "Nacionalidad [idNacionalidad=" + idNacionalidad + ", nacionalidad=" + nacionalidad + "]";
	}
	
}
