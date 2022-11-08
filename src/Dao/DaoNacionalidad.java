package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Localidad;
import Entidades.Nacionalidad;
import Entidades.Usuario;
import iDao.iDaoNacionalidad; 

public class DaoNacionalidad implements iDaoNacionalidad{

	@Override
	public List<Nacionalidad> traerLista() {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		List<Nacionalidad> lstNac = new ArrayList<Nacionalidad>();
		String query = "SELECT * FROM nacionalidades";
		PreparedStatement pst;
		try {

			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String nacionalidad = rs.getString(2);
				lstNac.add(new Nacionalidad(id, nacionalidad));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstNac;
	}

}
