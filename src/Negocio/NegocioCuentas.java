package Negocio;

import java.util.List;

import Dao.DaoCuentas;
import Entidades.Cuenta;
import iNegocio.iNegocioCuentas;

public class NegocioCuentas implements iNegocioCuentas{

	@Override
	public List<Cuenta> traerLista() {
		DaoCuentas daoCu = new DaoCuentas();
		return daoCu.traerLista();
	}

}
