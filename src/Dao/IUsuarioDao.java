package Dao;



import Entidades.Usuario;

public interface IUsuarioDao {

	public Usuario traerUsuario(String usuario, String contrasenia);
}
