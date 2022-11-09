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

	@Override
	public Usuario traerCliente(String id) {
		DaoClientes daoCli = new DaoClientes();
		return daoCli.traerCliente(id);
	}

	@Override
	public int modificar(Usuario usuario) {
		DaoClientes daoCli = new DaoClientes();
		return daoCli.modificar(usuario);
	}

	@Override
	public int traerProxId() {
		DaoClientes daoCli = new DaoClientes();
		return daoCli.traerProximoId();
	}

	@Override
	public int agregar(Usuario usuario) {
		DaoClientes daoCli = new DaoClientes();
		return daoCli.agregar(usuario);
	}

	@Override
	public List<Usuario> traerListaConFiltro(String filtroAplicar) {
		DaoClientes daoCli = new DaoClientes();
		return daoCli.traerListaConFiltro(filtroAplicar);
	}

}
