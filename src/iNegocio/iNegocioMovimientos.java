package iNegocio;

import java.util.List;

import Entidades.Movimiento;

public interface iNegocioMovimientos {
	List<Movimiento> traerLista(int idCuenta);
	String traerTipoMovimiento(int Id);
	List<Movimiento> traerHistorial();
}
