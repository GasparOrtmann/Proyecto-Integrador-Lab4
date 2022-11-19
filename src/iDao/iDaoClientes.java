package iDao;

import java.util.List;

import Entidades.Usuario;


public interface iDaoClientes {
	List<Usuario> traerLista ();
	List<Usuario> traerListaConFiltro (String filtroAplicar);
	boolean eliminarCliente (String id);
	Usuario traerCliente (String id);
	int traerProximoId ();
	int modificar (Usuario usuario);
	int agregar (Usuario usuario);
	int cantClientes();
}
