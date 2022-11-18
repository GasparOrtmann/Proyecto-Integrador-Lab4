package Negocio;

import java.util.List;

import Dao.DaoPrestamo;
import Entidades.Prestamo;
import iNegocio.iNegocioPrestamo;

public class NegocioPrestamo implements iNegocioPrestamo {

	DaoPrestamo pdao= new DaoPrestamo();
	
	@Override
	public Boolean agregarPrestamo(Prestamo prestamo) {

		if(pdao.agregarPrestamo(prestamo)) {
			
			return true;
			
		}
		return false;
		
	}

	@Override
	public int traerProxId() {
	  int id = pdao.traerProxId();
		return id;
	}

	@Override
	public List<Prestamo> traerListaPrestamos() {
		 List<Prestamo> lstPrestamos = pdao.traerListaPrestamos();
		return lstPrestamos;
	}
	

}
