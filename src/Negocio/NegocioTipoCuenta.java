package Negocio;

import java.util.List;

import Dao.DaoTipoCuenta;
import Entidades.TipoCuenta;
import iDao.iDaoTipoCuenta;
import iNegocio.iNegocioTipoCuenta;

public class NegocioTipoCuenta implements iNegocioTipoCuenta{
	iDaoTipoCuenta tcDao = new DaoTipoCuenta();
	
	@Override
	public List<TipoCuenta> traerTiposCuentas() {
		return tcDao.traerTiposCuentas();
	}
}
