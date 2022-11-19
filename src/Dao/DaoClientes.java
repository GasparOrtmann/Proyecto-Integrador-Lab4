package Dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
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
				Localidad localidad = new Localidad(rs.getInt(17));
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

	@Override
	public Usuario traerCliente(String id) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		Usuario usuario = new Usuario();
		String query = "SELECT * FROM usuarios WHERE IdUsuario=" + Integer.valueOf(id);
		PreparedStatement pst;
		try {

			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				usuario.setIdUsuario(rs.getInt(1));
				usuario.setEsAdmin(rs.getBoolean(2));
				usuario.setUsuario(rs.getString(3));
				usuario.setPassword(rs.getString(4));
				usuario.setNombre(rs.getString(5));
				usuario.setApellido(rs.getString(6));
				usuario.setSexo(rs.getString(7));
				usuario.setFechaNacimiento(rs.getString(8));
				usuario.setCalle(rs.getString(9));
				usuario.setAltura(rs.getInt(10));
				usuario.setEmail(rs.getString(11));
				usuario.setNroCuil(rs.getString(12));
				usuario.setNroDni(rs.getString(13));
				usuario.setEstado(rs.getBoolean(14));
				usuario.setCantCuentas(rs.getInt(15));
				usuario.setIdNacionalidad(new Nacionalidad(rs.getInt(16)));
				usuario.setIdLocalidad(new Localidad(rs.getInt(17)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	public int traerProximoId() {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		String query = "Select MAX(IdUsuario) AS id from usuarios";
		Integer id = 0;

		PreparedStatement pst;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt("id");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return id + 1;
	}

	@Override
	public int modificar(Usuario usuario) {
		String anio = usuario.getFechaNacimiento().substring(0,4);
		String mes = usuario.getFechaNacimiento().substring(5,7);
		String dia = usuario.getFechaNacimiento().substring(8,10);
		String esAdmin = "";
		String estado = "";
		if(usuario.isEsAdmin()) {
			esAdmin="True";
		}else {
			esAdmin="False";
		}
		if(usuario.isEstado()) {
			estado="True";
		}else {
			estado="False";
		}
		String query = "UPDATE usuarios SET"+
						"  EsAdmin="+esAdmin+
						", Usuario='"+usuario.getNombre()+"'"+
						", Contrasenia='"+usuario.getPassword()+"'"+
						", Nombre='"+usuario.getNombre()+"'"+
						", Apellido='"+usuario.getApellido()+"'"+
						", Sexo='"+usuario.getSexo()+"'"+
						", FechaNacimiento='"+dia+"/"+mes+"/"+anio+"'"+
						", Calle='"+usuario.getCalle()+"'"+
						", Altura='"+usuario.getAltura()+"'"+
						", Email='"+usuario.getEmail()+"'"+
						", NroCuil='"+usuario.getNroCuil()+"'"+
						", NroDni='"+usuario.getNroDni()+"'"+
						", Estado="+estado+
						", IdNacionalidad='"+usuario.getIdNacionalidad().getIdNacionalidad()+"'"+
						", IdLocalidad='"+usuario.getIdLocalidad().getIdLocalidad()+"'"+
						" WHERE IdUsuario='"+usuario.getIdUsuario()+"'";

		Connection cn = Conexion.getConexion().getSQLConexion();
		int filas = 0;
		
		try {
			Statement st = (Statement) cn.createStatement();
			filas = st.executeUpdate(query);
			cn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			;
		}
		return filas;
	}

	@Override
	public int agregar(Usuario usuario) {
		String anio = usuario.getFechaNacimiento().substring(0,4);
		String mes = usuario.getFechaNacimiento().substring(5,7);
		String dia = usuario.getFechaNacimiento().substring(8,10);
		String esAdmin = "";
		String estado = "";
		if(usuario.isEsAdmin()) {
			esAdmin="True";
		}else {
			esAdmin="False";
		}
		if(usuario.isEstado()) {
			estado="True";
		}else {
			estado="False";
		}
		String query = "INSERT INTO usuarios (IdUsuario,EsAdmin,Usuario,Contrasenia, Nombre,Apellido,Sexo,FechaNacimiento,Calle,Altura,Email,NroCuil,NroDni,Estado,IdNacionalidad,IdLocalidad) VALUES ("+
						"'"+usuario.getIdUsuario()+"'"+
						","+esAdmin+
						",'"+usuario.getNombre()+"'"+
						",'"+usuario.getPassword()+"'"+
						",'"+usuario.getNombre()+"'"+
						",'"+usuario.getApellido()+"'"+
						",'"+usuario.getSexo()+"'"+
						",'"+dia+"/"+mes+"/"+anio+"'"+
						",'"+usuario.getCalle()+"'"+
						",'"+usuario.getAltura()+"'"+
						",'"+usuario.getEmail()+"'"+
						",'"+usuario.getNroCuil()+"'"+
						",'"+usuario.getNroDni()+"'"+
						","+estado+
						",'"+usuario.getIdNacionalidad().getIdNacionalidad()+"'"+
						",'"+usuario.getIdLocalidad().getIdLocalidad()+"')";

		Connection cn = Conexion.getConexion().getSQLConexion();
		int filas = 0;

		try {
			Statement st = (Statement) cn.createStatement();
			filas = st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
			;
		}
		return filas;
	}

	@Override
	public List<Usuario> traerListaConFiltro(String filtroAplicar) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		List<Usuario> lstUsuario = new ArrayList<Usuario>();
		String query = "SELECT * FROM usuarios "+filtroAplicar;
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
				Localidad localidad = new Localidad(rs.getInt(17));
				lstUsuario.add(new Usuario(id, esAdmin, cuil, dni, fechaNac, usuario, contrasenia, nombre, apellido,
						sexo, localidad, calle, altura, nacionalidad, email, cantCuentas, estado));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstUsuario;
	}
	@Override
	public int cantClientes() {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		String query = "SELECT COUNT(*) as cantClientes FROM usuarios";
		Integer cantClientes = 0;

		PreparedStatement pst;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			cantClientes = rs.getInt("cantClientes");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return cantClientes;
	}

}
