package iNegocio;

import java.util.List;

import Entidades.Usuario;

public interface iNegocioClientes {
	List<Usuario> traerLista ();
	List<Usuario> traerListaConFiltro (String filtroAplicar);
	boolean eliminarCliente (String id);
	Usuario traerCliente (String id);
	int modificar(Usuario usuario);
	int agregar(Usuario usuario);
	int traerProxId();
	int cantClientes();
}
