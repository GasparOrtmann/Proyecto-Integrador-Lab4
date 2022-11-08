package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Localidad;
import Entidades.Nacionalidad;
import iDao.iDaoLocalidad;

public class DaoLocalidad implements iDaoLocalidad{

	@Override
	public List<Localidad> traerLista() {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		List<Localidad> lstLoc = new ArrayList<Localidad>();
		String query = "SELECT * FROM localidades";
		PreparedStatement pst;
		try {

			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String localidad = rs.getString(2);
				int idProvincia = rs.getInt(3);
				lstLoc.add(new Localidad(id, idProvincia,localidad));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstLoc; 
	}

}
