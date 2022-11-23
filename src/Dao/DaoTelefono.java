package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entidades.Telefono;
import iDao.iDaoTelefono;

public class DaoTelefono implements iDaoTelefono{

	@Override
	public Telefono traerTelefonoCliente(int idUsuario) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		Telefono telefono = new Telefono();
		String query = "SELECT * FROM telefonos WHERE IdUsuario=" + Integer.valueOf(idUsuario);
		PreparedStatement pst;
		try {

			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				telefono.setNroTelefono(rs.getString(1));
				telefono.setIdUsuario(rs.getInt(2));
				telefono.setEstado(rs.getBoolean(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return telefono;
	}

	@Override
	public int crear(Telefono telefono) {
		String estado = "False";
		if(telefono.isEstado()) {
			estado="True";
		}
		String query = "INSERT INTO telefonos (NroTelefono,IdUsuario,Estado) VALUES ("+
						"'"+telefono.getNroTelefono()+"'"+
						","+telefono.getIdUsuario()+
						","+estado+")";

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
	public int modificar(Telefono telefono) {
		String estado = "False";
		if(telefono.isEstado()) {
			estado="True";
		}
		String query = "UPDATE telefonos SET "+
						"NroTelefono='"+telefono.getNroTelefono()+"' "+
						",IdUsuario='"+telefono.getIdUsuario()+"' "+
						",Estado="+estado+" WHERE IdUsuario="+telefono.getIdUsuario();

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

}
