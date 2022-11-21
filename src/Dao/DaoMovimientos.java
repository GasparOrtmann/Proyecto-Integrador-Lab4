package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

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
	public List<Movimiento> traerHistorial(int idUsuario){
		Connection cnn = Conexion.getConexion().getSQLConexion();
		List<Movimiento> lstTrans = new ArrayList<Movimiento>();
		String query = "SELECT IdMovimiento, IdTipoMovimiento, movimientos.IdCuenta, Fecha, Importe, Detalle FROM movimientos INNER JOIN cuentas ON movimientos.IdCuenta=cuentas.IdCuenta  WHERE cuentas.IdUsuario="+idUsuario+" ORDER BY Fecha DESC;";
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
	
	@Override
	public int movimientoPositivo(int cuentaOrigen, int cuentaDestino,float monto) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		int proxId=proxId();
		
		String nombre = buscarNombre(cuentaOrigen);
		String query = "INSERT INTO movimientos(IdMovimiento,IdTipoMovimiento,IdCuenta,Fecha,importe,Detalle)"
						+"VALUES ("+proxId+",4,"+cuentaDestino+",CURRENT_DATE(),"+monto+",'Recibiste $"+monto+" de "+nombre+"')";
		
		int filas=0;
			filas+=sumarSaldo(cuentaDestino,monto);
			
		if(filas==1)
		{
			try
			{
				Statement st = (Statement) cn.createStatement();
				filas += st.executeUpdate(query);
				cn.commit();
				
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	
		
		return filas;
	}

	@Override
	public int movimientoNegativo(int cuentaOrigen, int cuentaDestino,float monto) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		int proxId=proxId();
		
		String nombre = buscarNombre(cuentaDestino);
		String query = "INSERT INTO movimientos(IdMovimiento,IdTipoMovimiento,IdCuenta,Fecha,importe,Detalle)"
						+"VALUES ("+proxId+",4,"+cuentaOrigen+",CURRENT_DATE(),"+monto+",'Enviaste $"+monto+" a "+nombre+"')";
		
		int filas=0;
			filas+=restarSaldo(cuentaOrigen,monto);
			
		if(filas==1)
		{
			try
			{
				Statement st = (Statement) cn.createStatement();
				filas += st.executeUpdate(query);
				cn.commit();
				
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	
		
		return filas;
	}

	@Override
	public int generarTransferncia(int cuentaOrigen, int cuentaDestino, float monto) {
			
		int filas=0;
			filas+=movimientoNegativo(cuentaOrigen,cuentaDestino,monto);
			filas+=movimientoPositivo(cuentaDestino,cuentaDestino,monto);
		
		return filas;
	}

	@Override
	public int proxId() {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		
		String query = "Select MAX(IdMovimiento) AS id from movimientos";
		Integer id=0;
		
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
		return id+1;
	}

	@Override
	public int sumarSaldo(int idCuenta, float monto) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		String query = "UPDATE cuentas SET saldo = (saldo+"+monto+") WHERE IdCuenta ="+idCuenta;
		
		int filas=0;
		try 
		{
			Statement st = (Statement) cn.createStatement();
			 filas =st.executeUpdate(query);
			 cn.commit();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return filas;
	}

	@Override
	public int restarSaldo(int idCuenta, float monto) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		String query = "UPDATE cuentas SET saldo = (saldo-"+monto+") WHERE IdCuenta ="+idCuenta;
		
		int filas=0;
		try 
		{
			Statement st = (Statement) cn.createStatement();
			 filas =st.executeUpdate(query);
			 cn.commit();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return filas;
	}
	@Override
	public String buscarNombre(int id) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		
		String query = "SELECT Nombre as Nombre,Apellido as Apellido FROM usuarios INNER JOIN cuentas on usuarios.IdUsuario = cuentas.IdUsuario " + 
				"WHERE IdCuenta="+id;
		String nombre="";
		String apellido="";
		
		PreparedStatement pst;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			
				nombre = rs.getString("Nombre");
				apellido = rs.getString("Apellido");
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return nombre+" "+apellido;
	}
}
