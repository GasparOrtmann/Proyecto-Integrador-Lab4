package Negocio;

import Dao.DaoCuota;
import Entidades.Cuota;
import iDao.iDaoCuota;
import iNegocio.iNegocioCuota;

public class NegocioCuota implements iNegocioCuota {

	iDaoCuota ctadao = new DaoCuota();
	
	@Override
	public Boolean pagarCuota(Cuota cuota, String fechaPago) {
		
		if(ctadao.pagarCuota(cuota, fechaPago)) {
			
			return true;
			
		}
		return false;
	}

}
