package iNegocio;

import Entidades.Telefono;

public interface iNegocioTelefono {
	Telefono traerTelefonoCliente(int idUsuario);
	int crear(Telefono telefono);
	int modificar(Telefono telefono);
}
