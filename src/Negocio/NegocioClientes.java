package Negocio;

import java.util.List;

import Dao.DaoClientes;
import Entidades.Usuario;
import iNegocio.iNegocioClientes;

public class NegocioClientes implements iNegocioClientes {

	@Override
	public List<Usuario> traerLista() {
		DaoClientes daoCli = new DaoClientes();
		return daoCli.traerLista();
	}

	@Override
	public boolean eliminarCliente(String id) {
		DaoClientes daoCli = new DaoClientes();
		return daoCli.eliminarCliente(id);
	}

}
