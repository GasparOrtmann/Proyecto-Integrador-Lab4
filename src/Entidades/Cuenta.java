package Entidades;


public class Cuenta {
	int IdCuenta;
	int IdUsuario;
	int IdTipoCuenta;
	String CBU;
	float saldo;
	String fechaAlta;
	boolean estado;
	
	public int getIdCuenta() {
		return IdCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		IdCuenta = idCuenta;
	}
	public int getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}
	public int getIdTipoCuenta() {
		return IdTipoCuenta;
	}
	public void setIdTipoCuenta(int idTipoCuenta) {
		IdTipoCuenta = idTipoCuenta;
	}
	public String getCBU() {
		return CBU;
	}
	public void setCBU(String cBU) {
		CBU = cBU;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
