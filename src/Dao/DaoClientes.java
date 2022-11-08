package Dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import Entidades.Localidad;
import Entidades.Nacionalidad;
import Entidades.Usuario;
import iDao.iDaoClientes;

public class DaoClientes implements iDaoClientes {

	@Override
	public List<Usuario> traerLista() {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		List<Usuario> lstUsuario = new ArrayList<Usuario>();
		String query = "SELECT * FROM usuarios";
		PreparedStatement pst;
		try {

			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
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
				Localidad localidad = new Localidad(rs.getInt(16));
				lstUsuario.add(new Usuario(id, esAdmin, cuil, dni, fechaNac, usuario, contrasenia, nombre, apellido,
						sexo, localidad, calle, altura, nacionalidad, email, cantCuentas, estado));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstUsuario;
	}

	@Override
	public boolean eliminarCliente(String id) {
		String query = "UPDATE usuarios SET Estado = false WHERE IdUsuario=" + Integer.valueOf(id);

		Connection cnn = Conexion.getConexion().getSQLConexion();

		try {
			Statement st = (Statement) cnn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}