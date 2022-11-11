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
			cst.setString(6,c.getFechaAlta());
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
		String estado = "";
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
						", FechaAlta='"+c.getFechaAlta()+"'"+
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
	public List<Cuenta> filtrarPorUsuario(String id) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		List<Cuenta> lstCuenta = new ArrayList<Cuenta>();
		String query = "SELECT * FROM cuentas WHERE IdUsuario ="+id;
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
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCuenta;
	}
	
	
}
