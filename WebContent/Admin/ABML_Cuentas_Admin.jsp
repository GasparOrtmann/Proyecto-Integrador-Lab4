<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ABML Cuentas Admin</title>
<link href="../styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="MasterPageAdmin.jsp"%>

<div style="position: absolute;top:150px;left:400px;">
	<form class="centrar-column">
		<h1>Gestion Cuentas</h1>
		<div class="centrar-row">
			<label>Filtros:</label> <input /> <input type="submit"
				value="Filtrar">
		</div>
		<table border="1">
			<tbody>
				<tr>
					<td>Cod Cuenta</td>
					<td>Cod Usuario</td>
					<td>Tipo de Cuenta</td>
					<td>CBU</td>
					<td>Saldo</td>
					<td>Fecha Alta</td>
					<td>Estado</td>
					<td></td>
					<td></td>
				</tr>
				<%
				for (int i = 0; i < 5; i++) {
				%>
				<tr>
					<td><%=i%></td>
					<td><%=i%></td>
					<td>Caja de ahorro</td>
					<td>123456789</td>
					<td>$10000</td>
					<td>10/10/2000</td>
					<td>Activo</td>
					<td><input type="submit" value="Editar"></td>
					<td><input type="submit" value="Eliminar"></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<h2>Creacion/Edicion de Cuenta</h2>
		<div class="centrar-row">
			<div class="centrar-column container-fields">
				<div>
					<label>CBU</label> <input>
				</div>
				<div>
					<label>Saldo</label> <input type="number">
				</div>
				<div>
					<label>Fecha Alta</label> <input type="date">
				</div>
				<div>
					<label>Estado</label> <input type="checkbox">
				</div>
				<div>
					<label>Tipo de cuenta</label> <select>
						<option value="CuentaCorriente">Cuenta corriente</option>
						<option value="CuentaDeAhorro">Caja de ahorro</option>
					</select>
				</div>
			</div>
		</div>
		<input type="submit" value="Enviar!">
	</form>
		</div>
</body>
</html>