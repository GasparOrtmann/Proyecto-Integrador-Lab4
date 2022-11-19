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

	@Override
	public List<Movimiento> traerHistorial() {
		DaoMovimientos daoMo = new DaoMovimientos();
		return daoMo.traerHistorial();
	}

	public int cantTransacciones() {
		DaoMovimientos daoMo = new DaoMovimientos();
		return daoMo.cantTransacciones();
	}
	public Boolean generarTransferncia(int cuentaOrigen, int cuentaDestino, float monto) {
		DaoMovimientos daoMo = new DaoMovimientos();
		
		if(daoMo.generarTransferncia(cuentaOrigen,cuentaDestino,monto)>0){
			return true;
		}
		
		return false;
	}
}
