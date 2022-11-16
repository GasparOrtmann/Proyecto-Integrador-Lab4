package Entidades;

public class Movimiento {
	int idMovimiento;
	TipoMovimiento idTipoMovimiento;
	int idCuenta;
	String fecha;
	float importe;
	String detalle;
	
	public Movimiento() {
		
	}
	
	public Movimiento(int idMovimiento, TipoMovimiento idTipoMovimiento, int idCuenta, String fecha, float importe,
			String detalle) {
		super();
		this.idMovimiento = idMovimiento;
		this.idTipoMovimiento = idTipoMovimiento;
		this.idCuenta = idCuenta;
		this.fecha = fecha;
		this.importe = importe;
		this.detalle = detalle;
	}
	public int getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public TipoMovimiento getIdTipoMovimiento() {
		return idTipoMovimiento;
	}
	public void setIdTipoMovimiento(TipoMovimiento idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	
	
}
