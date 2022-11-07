package Entidades;

public class Usuario {
	String codUsuario;
	boolean esAdmin;
	int DNI;
	int CUIL;
	String usuario;
	String password;
	String nombre;
	String apellido;
	String sexo;
	String fechaNac;
	Localidad codLocalidad;
	String calle;
	int altura;
	Nacionalidad codNacionalidad;
	String email;
	int cantCuentas;
	boolean estado;
	
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	public boolean isEsAdmin() {
		return esAdmin;
	}
	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}
	public int getDNI() {
		return DNI;
	}
	public void setDNI(int dNI) {
		DNI = dNI;
	}
	public int getCUIL() {
		return CUIL;
	}
	public void setCUIL(int cUIL) {
		CUIL = cUIL;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	public Localidad getCodLocalidad() {
		return codLocalidad;
	}
	public void setCodLocalidad(Localidad codLocalidad) {
		this.codLocalidad = codLocalidad;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public Nacionalidad getCodNacionalidad() {
		return codNacionalidad;
	}
	public void setCodNacionalidad(Nacionalidad codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCantCuentas() {
		return cantCuentas;
	}
	public void setCantCuentas(int cantCuentas) {
		this.cantCuentas = cantCuentas;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
