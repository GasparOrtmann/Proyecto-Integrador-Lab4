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
	public List<Movimiento> traerHistorial(int idUsuario) {
		DaoMovimientos daoMo = new DaoMovimientos();
		return daoMo.traerHistorial(idUsuario);
	}

	public int cantTransacciones() {
		DaoMovimientos daoMo = new DaoMovimientos();
		return daoMo.cantTransacciones();
	}
	public Boolean generarTransferncia(String cbuOrigen, String cbuDestino,float monto) {
		DaoMovimientos daoMo = new DaoMovimientos();
		
		if(daoMo.generarTransferncia(cbuOrigen,cbuDestino,monto)==4){
			return true;
		}
		
		return false;
	}

	@Override
	public int cantMovimientosUsuario(int idUsuario) {
		DaoMovimientos daoMo = new DaoMovimientos();
		return daoMo.cantMovimientosUsuario(idUsuario);
	}

	@Override
	public int cantMovimientosCuenta(int idCuenta) {
		DaoMovimientos daoMo = new DaoMovimientos();
		return daoMo.cantMovimientosCuenta(idCuenta);
	}

	@Override
	public Boolean agregarMovimiento(Movimiento m) {
		DaoMovimientos daoMo = new DaoMovimientos();
		if(daoMo.agregarMovimiento(m)) {
			return true;
		}
		return false;
	}

	@Override
	public int proxId() {
		DaoMovimientos daoMo = new DaoMovimientos();
		return daoMo.proxId();
	}

	@Override
	public String buscarUsuario(String cbu) {
		DaoMovimientos daoMo = new DaoMovimientos();
		return daoMo.buscarNombre(cbu);
	}
}
