package iDao;

import java.util.List;

import Entidades.Prestamo;

public interface iDaoPrestamo {

	Boolean agregarPrestamo(Prestamo prestamo);
	int  traerProxId();
	List<Prestamo> traerListaPrestamos();
}
