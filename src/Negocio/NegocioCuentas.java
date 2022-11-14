package Negocio;

import java.util.List;

import Dao.DaoCuentas;
import Entidades.Cuenta;
import iDao.iDaoCuentas;
import iNegocio.iNegocioCuentas;

public class NegocioCuentas implements iNegocioCuentas{

	iDaoCuentas daoCu = new DaoCuentas();
	
	@Override
	public List<Cuenta> traerLista() {
		DaoCuentas daoCu = new DaoCuentas();
		return daoCu.traerLista();
	}

	@Override
	public Boolean agregarCuenta(Cuenta c) {
		if(daoCu.agregarCuenta(c)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean eliminarCuenta(String id) {
		DaoCuentas daoCu = new DaoCuentas();
		return daoCu.eliminarCuenta(id);
	}
	
	@Override
	public Integer traerProxId() {
		return daoCu.traerProxId();
	}

	@Override
	public int modificar(Cuenta c) {
		DaoCuentas daoCu = new DaoCuentas();
		return daoCu.modificar(c);	
	}

	@Override
	public Cuenta traerCuenta(String id) {
		DaoCuentas daoCu = new DaoCuentas();
		return daoCu.traerCuenta(id);		
	}

	@Override
	public List<Cuenta> FiltrarPorUsuario(String id,Boolean actividad) {
		
		return daoCu.filtrarPorUsuario(id,actividad);
	}

}
