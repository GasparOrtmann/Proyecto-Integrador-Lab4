package Negocio;

import java.util.List; 

import Dao.DaoNacionalidad;
import Entidades.Nacionalidad;
import iNegocio.iNegocioNacionalidad;

public class NegocioNacionalidad implements iNegocioNacionalidad{

	@Override
	public List<Nacionalidad> traerLista() {
		DaoNacionalidad daoNac = new DaoNacionalidad();
		return daoNac.traerLista();
	}
	

}
