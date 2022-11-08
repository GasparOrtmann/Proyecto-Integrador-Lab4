package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entidades.Localidad;
import Entidades.Nacionalidad;
import Entidades.Usuario;
import iDao.IUsuarioDao;

public class UsuarioDao implements IUsuarioDao {

	@Override
	public Usuario traerUsuario(String txtusuario, String txtcontrasenia) {
		
		Connection cnn = Conexion.getConexion().getSQLConexion();

		String query = "SELECT * FROM bdbanco.usuarios WHERE Contrasenia='"+txtusuario+"' AND Usuario= '"+txtcontrasenia+"'";
		
		Usuario usuarioIngresado = new Usuario();

		PreparedStatement pst;
		try {
		
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
		while(rs.next()) {
			
			int id = rs.getInt(1);
			boolean esAdmin = rs.getBoolean(2);
			String usuario = rs.getString(3);
			String contrasenia = rs.getString(4);
			String nombre = rs.getString(5);
			String apellido = rs.getString(6);
			String sexo = rs.getString(7);
			String fechaNac = rs.getString(8);
			String calle = rs.getString(9);
			int altura = rs.getInt(10);
			String email = rs.getString(11);
			String cuil = rs.getString(12);
			String dni = rs.getString(13);
			boolean estado = rs.getBoolean(14);
			int cantCuentas = rs.getInt(15);
			Nacionalidad nacionalidad = new Nacionalidad(rs.getInt(16));
			Localidad localidad = new Localidad(rs.getInt(17));
			
			usuarioIngresado.setIdUsuario(id); 
			usuarioIngresado.setEsAdmin(esAdmin);
			usuarioIngresado.setUsuario(usuario);
			usuarioIngresado.setPassword(contrasenia);
			usuarioIngresado.setNombre(nombre);
			usuarioIngresado.setApellido(apellido);
			usuarioIngresado.setSexo(sexo);
			usuarioIngresado.setFechaNacimiento(fechaNac);
			usuarioIngresado.setCalle(calle);
			usuarioIngresado.setAltura(altura);
			usuarioIngresado.setEmail(email);
			usuarioIngresado.setNroCuil(cuil);
			usuarioIngresado.setNroDni(dni);
			usuarioIngresado.setEstado(estado);
			usuarioIngresado.setCantCuentas(cantCuentas);
			usuarioIngresado.setIdNacionalidad(nacionalidad);
			usuarioIngresado.setIdLocalidad(localidad);
			
		}
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return usuarioIngresado;
	}

}
