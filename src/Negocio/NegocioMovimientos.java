package Negocio;

import java.util.List;

import Dao.DaoMovimientos;
import Entidades.Movimiento;
import iNegocio.iNegocioMovimientos;

public class NegocioMovimientos implements iNegocioMovimientos{

	@Override
	public List<Movimiento> traerLista(int idCuenta) {
		DaoMovimientos daoMo = new DaoMovimientos();
		return daoMo.traerLista(idCuenta);
	}

	@Override
	public String traerTipoMovimiento(int Id) {
		DaoMovimientos daoMo = new DaoMovimientos();
		return daoMo.traerTipoMovimiento(Id);
	}

}
