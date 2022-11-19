package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Cuenta;
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
		String query = "SELECT * FROM movimientos WHERE IdCuenta="+idCuenta+" ORDER BY Fecha DESC";
		PreparedStatement pst;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int idM = rs.getInt(1);
				String tm = traerTipoMovimiento(rs.getInt(2));
				TipoMovimiento idTM = new TipoMovimiento(rs.getInt(2),tm);
				Cuenta idC = new Cuenta(rs.getInt(3));
				String fecha = rs.getString(4);
				float importe = rs.getFloat(5);
				String detalle = rs.getString(6);
				lstMovs.add(new Movimiento(idM, idTM, idC, fecha, importe, detalle));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstMovs;
	}
	
	@Override
	public List<Movimiento> traerHistorial(){
		Connection cnn = Conexion.getConexion().getSQLConexion();
		List<Movimiento> lstTrans = new ArrayList<Movimiento>();
		String query = "SELECT * FROM movimientos ORDER BY Fecha DESC";
		PreparedStatement pst;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int idM = rs.getInt(1);
				String tm = traerTipoMovimiento(rs.getInt(2));
				TipoMovimiento idTM = new TipoMovimiento(rs.getInt(2),tm);
				Cuenta idC = new Cuenta(rs.getInt(3));
				String fecha = rs.getString(4);
				float importe = rs.getFloat(5);
				String detalle = rs.getString(6);
				lstTrans.add(new Movimiento(idM, idTM, idC, fecha, importe, detalle));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstTrans;
	}
	@Override
	public int cantTransacciones() {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		String query = "SELECT COUNT(*) as cantTransacciones FROM movimientos";
		Integer cantTransacciones = 0;

		PreparedStatement pst;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			cantTransacciones = rs.getInt("cantTransacciones");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return cantTransacciones;
	}
}
