package Entidades;

public class Cuota {
	
	private int nroCuota;
	private Prestamo prestamo;
	private String fechaVto;
	private String fechaPago;
	
	
	public int getNroCuota() {
		return nroCuota;
	}
	public void setNroCuota(int nroCuota) {
		this.nroCuota = nroCuota;
	}
	public Prestamo getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}
	public String getFechaVto() {
		return fechaVto;
	}
	public void setFechaVto(String fechaVto) {
		this.fechaVto = fechaVto;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	public Cuota (Prestamo prestamo, int nroCuota ){
		this.prestamo=prestamo;
		this.nroCuota=nroCuota;
		
	}
	public Cuota(int nroCuota,Prestamo prestamo,String fechaPago,String fechaVto) {
		this.nroCuota = nroCuota;
		this.prestamo = prestamo;
		this.fechaPago = fechaPago;
		this.fechaVto = fechaVto;
	}
	
	
}
