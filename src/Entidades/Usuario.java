package Entidades;

public class Usuario {
	
	int idUsuario;
	boolean esAdmin;
	String nroCuil;
	String nroDni;
	String fechaNacimiento;
	String usuario;
	String password;
	String nombre;
	String apellido;
	String sexo;
	String fechaNac;
	Localidad idLocalidad;
	String calle;
	int altura;
	Nacionalidad idNacionalidad;
	String email;
	int cantCuentas;
	boolean estado;
	
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public boolean isEsAdmin() {
		return esAdmin;
	}
	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}
	public String getNroCuil() {
		return nroCuil;
	}
	public void setNroCuil(String nroCuil) {
		this.nroCuil = nroCuil;
	}
	public String getNroDni() {
		return nroDni;
	}
	public void setNroDni(String nroDni) {
		this.nroDni = nroDni;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	public Localidad getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(Localidad idLocalidad) {
		this.idLocalidad = idLocalidad;
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
	public Nacionalidad getIdNacionalidad() {
		return idNacionalidad;
	}
	public void setIdNacionalidad(Nacionalidad idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
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
