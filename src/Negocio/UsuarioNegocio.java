package Negocio;

import Dao.IUsuarioDao;
import Dao.UsuarioDao;
import Entidades.Usuario;

public class UsuarioNegocio implements IUsuarioNegocio {

	@Override
	public Usuario traerUsuario(String usuario, String contrasenia) {
		
		IUsuarioDao udao= new UsuarioDao();
		Usuario usuarioIngresado = new Usuario();
		usuarioIngresado=udao.traerUsuario(usuario, contrasenia);
		
		return usuarioIngresado;
		
	}
	


}
