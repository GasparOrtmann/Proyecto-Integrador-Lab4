package Entidades;

public class TipoCuenta {
	
	int idTipoCuenta;
	String tipoCuenta;
	
	public TipoCuenta(int id) {
		this.idTipoCuenta=id;
	}
	public TipoCuenta(int id, String tipo) {
		this.idTipoCuenta=id;
		this.tipoCuenta=tipo;
	}
	public int getIdTipoCuenta() {
		return idTipoCuenta;
	}
	public void setIdTipoCuenta(int idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	
}
