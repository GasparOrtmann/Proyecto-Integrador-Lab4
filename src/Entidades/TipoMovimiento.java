package Entidades;

public class TipoMovimiento {
	
	int idTipoMovimiento;
	String tipoMovimiento;
	
	public TipoMovimiento(int idTM) {
		this.idTipoMovimiento=idTM;
	}
	
	public TipoMovimiento(int idTM, String tm) {
		this.idTipoMovimiento=idTM;
		this.tipoMovimiento=tm;
	}
	
	public int getIdTipoMovimiento() {
		return idTipoMovimiento;
	}
	public void setIdTipoMovimiento(int idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	
	
	
	
}
