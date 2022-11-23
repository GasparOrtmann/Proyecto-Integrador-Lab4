package Entidades;

public class Prestamo {
	int idPrestamo;
	int idUsuario;
	float montoPrestamo;
	float montoTotalAdeudado;
	float importeCuotaFija;
	int cantidadCuotas;
	int cuotasAdeudadas;
	int cuotasPagas;
	int idCuenta;
	String fechaAlta;
	String estado;
	
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public float getMontoPrestamo() {
		return montoPrestamo;
	}
	public void setMontoPrestamo(float montoPrestamo) {
		this.montoPrestamo = montoPrestamo;
	}
	public float getMontoTotalAdeudado() {
		return montoTotalAdeudado;
	}
	public void setMontoTotalAdeudado(float montoTotalAdeudado) {
		this.montoTotalAdeudado = montoTotalAdeudado;
	}
	public float getImporteCuotaFija() {
		return importeCuotaFija;
	}
	public void setImporteCuotaFija(float importeCuotaFija) {
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	
	public Prestamo() {
		
		
	}
	public Prestamo(int idP, int idU, float montoP, float montoPAdeu, float cuotaFija, int cantCuotas, int cuotDeuda,
			int cuotPagas,int idCta, String fechaAlta2, String estado2) {
		
		this.idUsuario=idU;
		this.idPrestamo=idP;
		this.montoPrestamo=montoP;
		this.montoTotalAdeudado=montoPAdeu;
		this.importeCuotaFija=cuotaFija;
		this.cantidadCuotas=cantCuotas;
		this.cuotasAdeudadas=cuotDeuda;
		this.cuotasPagas=cuotPagas;
		this.idCuenta=idCta;
		this.fechaAlta=fechaAlta2;
		this.estado=estado2;
	}
	public Prestamo(int idPrestamo2) {
		this.idPrestamo=idPrestamo2;
	}

	
}
