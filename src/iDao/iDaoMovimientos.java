package iDao;

import java.util.List;

import Entidades.Movimiento;

public interface iDaoMovimientos {

	List<Movimiento> traerLista(int idCuenta);

	String traerTipoMovimiento(int Id);

}
