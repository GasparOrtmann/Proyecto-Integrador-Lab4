package Negocio;

import Dao.UsuarioDao;
import Entidades.Usuario;
import iDao.IUsuarioDao;
import iNegocio.IUsuarioNegocio;

public class UsuarioNegocio implements IUsuarioNegocio {

	@Override
	public Usuario traerUsuario(String usuario, String contrasenia) {
		
		IUsuarioDao udao= new UsuarioDao();
		Usuario usuarioIngresado = new Usuario();
		usuarioIngresado=udao.traerUsuario(usuario, contrasenia);
		
		return usuarioIngresado;
		
	}
	


}
