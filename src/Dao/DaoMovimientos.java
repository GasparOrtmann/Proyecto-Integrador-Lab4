package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Movimiento;
import Entidades.TipoMovimiento;
import iDao.iDaoMovimientos;

public class DaoMovimientos implements iDaoMovimientos{
	
	@Override
	public String traerTipoMovimiento (int Id) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		String query = "SELECT TipoMovimiento FROM tipos_movimiento WHERE IdTipoMovimiento ="+Id;
		PreparedStatement pst;
		String tm = null;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			tm = rs.getString("TipoMovimiento");
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return tm;
	}
	
	@Override
	public List<Movimiento> traerLista(int idCuenta){
		Connection cnn = Conexion.getConexion().getSQLConexion();
		List<Movimiento> lstMovs = new ArrayList<Movimiento>();
		String query = "SELECT * FROM movimientos WHERE IdCuenta="+idCuenta;
		
		PreparedStatement pst;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int idM = rs.getInt(1);
				String tm = traerTipoMovimiento(rs.getInt(2));
				TipoMovimiento idTM = new TipoMovimiento(rs.getInt(2),tm);
				int idC = rs.getInt(3);
				String fecha = rs.getString(4);
				float importe = rs.getFloat(5);
				String detalle = rs.getString(6);
				lstMovs.add(new Movimiento(idM, idTM, idC, fecha, importe, detalle));
				System.out.println(lstMovs);	
				System.out.println(idM);
				System.out.println(idTM);
				System.out.println(idC);
				System.out.println(fecha);
				System.out.println(importe);
				System.out.println(detalle);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstMovs;
	}
}
