package iNegocio;

import java.util.List;

import Entidades.Usuario;

public interface iNegocioClientes {
	List<Usuario> traerLista ();
	boolean eliminarCliente (String id);
}