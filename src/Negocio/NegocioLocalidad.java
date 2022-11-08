package Negocio;

import java.util.List;

import Dao.DaoLocalidad;
import Entidades.Localidad;
import iNegocio.iNegocioLocalidad;

public class NegocioLocalidad implements iNegocioLocalidad{

	@Override
	public List<Localidad> traerLista() {
		DaoLocalidad daoLoc = new DaoLocalidad();
		return daoLoc.traerLista();
	}

}
