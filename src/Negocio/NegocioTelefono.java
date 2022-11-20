package Negocio;

import Dao.DaoTelefono;
import Entidades.Telefono;
import iNegocio.iNegocioTelefono;

public class NegocioTelefono implements iNegocioTelefono{

	@Override
	public Telefono traerTelefonoCliente(int idUsuario) {
		DaoTelefono daoTel = new DaoTelefono();
		return daoTel.traerTelefonoCliente(idUsuario);
	}

	@Override
	public int crear(Telefono telefono) {
		DaoTelefono daoTel = new DaoTelefono();
		return daoTel.crear(telefono);
	}

	@Override
	public int modificar(Telefono telefono) {
		DaoTelefono daoTel = new DaoTelefono();
		return daoTel.modificar(telefono);
	}

}
