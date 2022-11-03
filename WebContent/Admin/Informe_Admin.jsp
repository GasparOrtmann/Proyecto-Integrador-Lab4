<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="../styles.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div  style="background-color:white;height:50px; width:100%;margin:0; padding:0">
	<label>Logo y nombre del banco</label>
			<input type="submit" value="Cerrar Sesion" Name="btnCerrarSesion" style="w:55px;h:25px; float:right">
			<input type="submit" value="Cliente" style="w:55px;h:25px; float:right">
	</div>
	
	<div align="center" style="background-color:white;height:500px; width:25%;margin:0px;float:left;margin:0; padding:0" > 
			
			<label>Admin</label>
			
			<br><br>
			<ul>
				 <li> <a href="Inicio.aspx ">Inicio</a><br></li>
				 <li> <a href="Clientes.aspx">Clientes</a><br></li>
				 <li> <a href="Cuentas.aspx">Cuentas</a><br></li>
				 <li> <a href="Prestamos.aspx">Prestamos</a><br></li>
			</ul>
	</div>
	
	<div align="center" style="background-color:white; height: 250px; width:50%;float:left">
			<label>Montos de Ingreso y Egreso</label><br>
			<br><br>
			Desde: <input type="text" placeholder="dd/mm/aaaa" name="txtFechaInicio">
			Hasta: <input type="text" placeholder="dd/mm/aaaa" name="txtFechaFin"><br></br><br>
			
									<table border=1>
						<tr>
						<th>Ingreso</th>
						<th>Egreso</th>
						</tr>
						<tr>
						<td>$500000</td>
						<td>$900000</td>
						</tr>
						</table>	<br>
			<br>
			<input type="submit" name="btnCalcularIngresos" value="Calcular" style="width: 70px;height: 30px">
	</div>
	
	<div align="center" style="background-color:white; height: 250px; width:25%;float:left">
			<label>Nuevos Usuarios</label><br>
			<br>
			<input type="text" placeholder="50" name="txtNuevosUsuarios">
			<br><br>
			<label>Transferencias generadas</label><br>
			<br>
			<input type="text" placeholder="50860" name="txtTransferenciasGeneradas">
	</div>
	<div align="center" style="background-color:white; height: 250px; width:75%;float:left">
		<label>Calcular Ganancias por Interes</label><br>
		<br>
			<select>
			<option value="1">Enero</option>
			<option value="2">Febrero</option>
			<option value="3">Marzo</option>
			<option value="4">Abril</option>
			<option value="5">Mayo</option>
			<option value="6">Junio</option>
			<option value="7">Julio</option>
			<option value="8">Agosto</option>
			<option value="9">Septiembre</option>
			<option value="10">Octubre</option>
			<option value="11">Noviembre</option>
			<option value="12">Diciembre</option>
			</select><input type="text" placeholder="Resultado" name="txtGananciaInteres"><br>
			<br>
			<input type="submit" name="btnCalcularIntereses" value="Calcular" style="width: 70px;height: 30px">

	</div>
	
</body>
</html>