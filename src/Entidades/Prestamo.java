package Entidades;

public class Prestamo {
	String codPrestamo;
	String codUsuario;
	String montoPrestamo;
	String montoTotalAdeudado;
	String importeCuotaFija;
	int cantidadCuotas;
	int cuotasAdeudadas;
	int cuotasPagas;
	String fechaAlta;
	boolean estado;
	public String getCodPrestamo() {
		return codPrestamo;
	}
	public void setCodPrestamo(String codPrestamo) {
		this.codPrestamo = codPrestamo;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getMontoPrestamo() {
		return montoPrestamo;
	}
	public void setMontoPrestamo(String montoPrestamo) {
		this.montoPrestamo = montoPrestamo;
	}
	public String getMontoTotalAdeudado() {
		return montoTotalAdeudado;
	}
	public void setMontoTotalAdeudado(String montoTotalAdeudado) {
		this.montoTotalAdeudado = montoTotalAdeudado;
	}
	public String getImporteCuotaFija() {
		return importeCuotaFija;
	}
	public void setImporteCuotaFija(String importeCuotaFija) {
		this.importeCuotaFija = importeCuotaFija;
	}
	public int getCantidadCuotas() {
		return cantidadCuotas;
	}
	public void setCantidadCuotas(int cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}
	public int getCuotasAdeudadas() {
		return cuotasAdeudadas;
	}
	public void setCuotasAdeudadas(int cuotasAdeudadas) {
		this.cuotasAdeudadas = cuotasAdeudadas;
	}
	public int getCuotasPagas() {
		return cuotasPagas;
	}
	public void setCuotasPagas(int cuotasPagas) {
		this.cuotasPagas = cuotasPagas;
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
