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
	public Integer traerProxId() {
		return daoCu.traerProxId();
	}

}
