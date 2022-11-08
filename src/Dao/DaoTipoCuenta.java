package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.TipoCuenta;
import iDao.iDaoTipoCuenta;

public class DaoTipoCuenta implements iDaoTipoCuenta {
	public List<TipoCuenta> traerTiposCuentas() {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		
		List<TipoCuenta> lstTipos= new ArrayList<TipoCuenta>();
		PreparedStatement pst;
		
		try {
			pst = cnn.prepareStatement("Select * from tipos_cuenta");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				TipoCuenta ts = new TipoCuenta(rs.getInt(1),rs.getNString(2));
				lstTipos.add(ts);
			}
			return lstTipos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstTipos;
	}
}
