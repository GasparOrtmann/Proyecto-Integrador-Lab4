package iDao;

import Entidades.Telefono;

public interface iDaoTelefono {
	Telefono traerTelefonoCliente(int idUsuario);
	int crear(Telefono telefono);
	int modificar(Telefono telefono);
}
