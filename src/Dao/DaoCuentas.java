package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Cuenta;
import iDao.iDaoCuentas;

public class DaoCuentas implements iDaoCuentas{
	
	@Override
	public List<Cuenta> traerLista() {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		List<Cuenta> lstCuenta = new ArrayList<Cuenta>();
		String query = "SELECT * FROM cuentas";
		PreparedStatement pst;
		try {

			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int idC = rs.getInt(1);
				int idU = rs.getInt(2);
				int idTC = rs.getInt(3);
				String CBU = rs.getString(4);
				float saldo = rs.getFloat(5);
				String fechaAlta = rs.getString(6);
				boolean estado = rs.getBoolean(7);
				lstCuenta.add(new Cuenta(idC, idU, idTC, CBU, saldo, fechaAlta, estado));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCuenta;
	}
}
