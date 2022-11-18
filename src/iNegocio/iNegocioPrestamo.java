package iNegocio;

import java.util.List;

import Entidades.Prestamo;

public interface iNegocioPrestamo {

	Boolean agregarPrestamo(Prestamo prestamo);
	int  traerProxId();
	List<Prestamo> traerListaPrestamos();
	
}
