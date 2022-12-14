package Entidades;


public class Cuenta {
	int IdCuenta;
	int IdUsuario;
	TipoCuenta IdTipoCuenta;
	String CBU;
	float saldo;
	String fechaAlta;
	boolean estado;
	
	public Cuenta() {
	}
	
	public Cuenta(int idCuenta) {
		IdCuenta = idCuenta;
	}
	
	public Cuenta(int idCuenta, int idUsuario, TipoCuenta idTipoCuenta, String cBU, float saldo, String fechaAlta,
			boolean estado) {
		super();
		IdCuenta = idCuenta;
		IdUsuario = idUsuario;
		IdTipoCuenta = idTipoCuenta;
		CBU = cBU;
		this.saldo = saldo;
		this.fechaAlta = fechaAlta;
		this.estado = estado;
	}

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
	public TipoCuenta getIdTipoCuenta() {
		return IdTipoCuenta;
	}
	public void setIdTipoCuenta(TipoCuenta idTipoCuenta) {
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

	@Override
	public String toString() {
		return "Cuenta [IdCuenta=" + IdCuenta + ", IdUsuario=" + IdUsuario + ", IdTipoCuenta=" + IdTipoCuenta + ", CBU="
				+ CBU + ", saldo=" + saldo + ", fechaAlta=" + fechaAlta + ", estado=" + estado + "]";
	}
	
	
}
