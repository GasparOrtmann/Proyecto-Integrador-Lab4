package Entidades;

public class Movimiento {
	String codMovimiento;
	String codTipoMovimiento;
	String codCuenta;
	String fecha;
	String importe;
	String detalle;
	
	public String getCodMovimiento() {
		return codMovimiento;
	}
	public void setCodMovimiento(String codMovimiento) {
		this.codMovimiento = codMovimiento;
	}
	public String getCodTipoMovimiento() {
		return codTipoMovimiento;
	}
	public void setCodTipoMovimiento(String codTipoMovimiento) {
		this.codTipoMovimiento = codTipoMovimiento;
	}
	public String getCodCuenta() {
		return codCuenta;
	}
	public void setCodCuenta(String codCuenta) {
		this.codCuenta = codCuenta;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
}
