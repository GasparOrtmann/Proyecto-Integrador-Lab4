package iDao;

import java.util.List;

import Entidades.Usuario;


public interface iDaoClientes {
	List<Usuario> traerLista ();
	boolean eliminarCliente (String id);
}
