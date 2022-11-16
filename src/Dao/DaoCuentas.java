package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entidades.Cuenta;
import Entidades.Localidad;
import Entidades.Nacionalidad;
import Entidades.TipoCuenta;
import Entidades.Usuario;
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
				TipoCuenta idTC = new TipoCuenta(rs.getInt(3));
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
	
		/*
		 USE `bdbanco`;
	DROP procedure IF EXISTS `SP_agregarcuenta`;
	
	DELIMITER $$
	USE `bdbanco`$$
	CREATE PROCEDURE `SP_agregarcuenta` (IN sIdC int, IN sIdU int, IN sIdT int, IN sCBU varchar(20), IN sSaldo float, IN sFechaAlta varchar(10), IN sEstado bit)
	BEGIN
	INSERT INTO Cuentas (IdCuenta,IdUsuario,IdTipoCuenta,CBU,Saldo,FechaAlta,Estado) values (sIdc,sIdU,sIdT,sCBU,sSaldo,sFechaAlta,sEstado);
	END$$
	
	DELIMITER ;
	*/
	@Override
	public Boolean agregarCuenta(Cuenta c) {
	Connection cn = Conexion.getConexion().getSQLConexion();
	String anio = c.getFechaAlta().substring(0,4);
	String mes = c.getFechaAlta().substring(5,7);
	String dia = c.getFechaAlta().substring(8,10);
	String fechaAlta = dia+"/"+mes+"/"+anio;
	String estado = "";
	if(c.isEstado()) {
		estado="1";
	}else {
		estado="0";
	}
	try {
			int filaAfectada=0;
			CallableStatement cst = cn.prepareCall("CALL SP_agregarCuenta(?,?,?,?,?,?,?)");
			cst.setInt(1,c.getIdCuenta());
			cst.setInt(2,c.getIdUsuario());
			cst.setInt(3,c.getIdTipoCuenta().getIdTipoCuenta());
			cst.setString(4,c.getCBU());
			cst.setFloat(5,c.getSaldo());
			cst.setString(6,fechaAlta);
			cst.setBoolean(7,c.isEstado());
			cst.executeUpdate();
			filaAfectada=cst.getUpdateCount();
			cn.commit();
			System.out.println(filaAfectada);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
		}
	
	return true;
	}
	
	@Override
	public boolean eliminarCuenta(String id) {
		String query = "UPDATE cuentas SET Estado = false WHERE IdCuenta=" + Integer.valueOf(id);

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
	public int modificar(Cuenta c) {
		String anio = c.getFechaAlta().substring(0,4);
		String mes = c.getFechaAlta().substring(5,7);
		String dia = c.getFechaAlta().substring(8,10);
		String fechaAlta = dia+"/"+mes+"/"+anio;
		String estado = "";
		System.out.println(fechaAlta);
		if(c.isEstado()) {
			estado="1";
		}else {
			estado="0";
		}
		String query = "UPDATE cuentas SET"+
						"  IdUsuario="+c.getIdUsuario()+
						", IdTipoCuenta="+c.getIdTipoCuenta().getIdTipoCuenta()+
						", CBU='"+c.getCBU()+"'"+
						", Saldo="+c.getSaldo()+
						", FechaAlta='"+fechaAlta+"'"+
						", Estado="+estado+
						" WHERE IdCuenta="+c.getIdCuenta();
		System.out.println(query);
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
	public Cuenta traerCuenta(String id) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		Cuenta c = new Cuenta();
		String query = "SELECT * FROM cuentas WHERE IdCuenta=" + Integer.valueOf(id);
		PreparedStatement pst;
		try {

			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				c.setIdCuenta(rs.getInt(1));
				c.setIdUsuario(rs.getInt(2));
				c.setIdTipoCuenta(new TipoCuenta(rs.getInt(3)));
				c.setCBU(rs.getString(4));
				c.setSaldo(rs.getFloat(5));
				c.setFechaAlta(rs.getString(6));
				c.setEstado(rs.getBoolean(7));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public String traerTipoCuenta (int Id) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		String query = "SELECT TipoCuenta FROM tipos_cuenta WHERE IdTipoCuenta ="+Id;
		PreparedStatement pst;
		String tc = null;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			tc = rs.getString("TipoCuenta");
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return tc;
	}
	
	@Override
	public List<Cuenta> traerCuentasUsuario(int idUsuario) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		List<Cuenta> lstCuenta = new ArrayList<Cuenta>();
		//Cuenta c = new Cuenta();
		String query = "SELECT * FROM cuentas WHERE IdUsuario=" + idUsuario + " AND Estado=1";
		PreparedStatement pst;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				
				int idC = rs.getInt(1);
				int idU = rs.getInt(2);
				TipoCuenta idTC = new TipoCuenta(rs.getInt(3), traerTipoCuenta(rs.getInt(3)));
				String CBU = rs.getString(4);
				float saldo = rs.getFloat(5);
				String fechaAlta = rs.getString(6);
				boolean estado = rs.getBoolean(7);
				lstCuenta.add(new Cuenta(idC, idU, idTC, CBU, saldo, fechaAlta, estado));
				/*
				c.setIdCuenta(rs.getInt(1));
				c.setIdUsuario(rs.getInt(2));
				c.setIdTipoCuenta(new TipoCuenta(rs.getInt(3), traerTipoCuenta(rs.getInt(3))));
				c.setCBU(rs.getString(4));
				c.setSaldo(rs.getFloat(5));
				c.setFechaAlta(rs.getString(6));
				c.setEstado(rs.getBoolean(7));
				lstCuenta.add(c);
				*/
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCuenta;
	}
	
	@Override
	public int cantCuentasUsuario(int idU) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		String query = "SELECT COUNT(IdUsuario) as cant FROM cuentas WHERE IdUsuario="+idU;
		PreparedStatement pst;
		int cant = 0;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			cant = rs.getInt("cant");
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return cant;
	}
	
	@Override
	public Integer traerProxId() {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		
		String query = "Select MAX(idCuenta) AS id from cuentas";
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
	public List<Cuenta> filtrarPorUsuario(String id,Boolean actividad) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		List<Cuenta> lstCuenta = new ArrayList<Cuenta>();
		String query = "SELECT * FROM cuentas WHERE IdUsuario ="+id+" AND Estado ="+actividad;
		PreparedStatement pst;
		try {

			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int idC = rs.getInt(1);
				int idU = rs.getInt(2);
				TipoCuenta idTC = new TipoCuenta(rs.getInt(3));
				String CBU = rs.getString(4);
				float saldo = rs.getFloat(5);
				String fechaAlta = rs.getString(6);
				boolean estado = rs.getBoolean(7);
				lstCuenta.add(new Cuenta(idC, idU, idTC, CBU, saldo, fechaAlta, estado));
				
				System.out.println(rs.getInt(1));
				System.out.println("estado: "+actividad);
				System.out.println(".........");
				System.out.println(query);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCuenta;
	}
}
