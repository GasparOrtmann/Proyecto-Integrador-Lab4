<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Autorizacion de Prestamos</title>
</head>
<body>

<%@include file="MasterPageAdmin.jsp"%>

<div style="position: absolute;top:150px;left:400px;">

<form class="centrar-column">
		<h1>Gestion Prestamos</h1>
		<div class="centrar-row">
			<label>Filtros:</label> <input /> <input type="submit"
				value="Filtrar">
		</div>
		<table border="1">
			<tbody>
				<tr>
					<td>Cod Prestamo</td>
					<td>Cod Usuario</td>
					<td>Monto Prestamo</td>
					<td>Monto Total Adeudado</td>
					<td>Importe cuota fija</td>
					<td>Cantidad Cuotas</td>
					<td>Cuotas adeudadas</td>
					<td>Cuotas pagas</td>
					<td>Fecha Alta.</td>
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
					<td>50000</td>
					<td>30000</td>
					<td>3000</td>
					<td>10</td>
					<td>3</td>
					<td>7</td>
					<td>10/10/2000</td>
					<td>activo</td>
					<td><input type="submit" value="Editar"></td>
					<td><input type="submit" value="Eliminar"></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<div class="centrar-row">
			<h1>Autorizacion de Prestamos</h1>
<br/>
<br/>
		Filtrar por cliente: <input type="text" name="txtFiltro"> <input type="submit" name="btnFiltrar" value="Filtrar"><br/>
		<br>
		<table border="1">
				<tr>
					<th>Ciente</th>
					<th>Cuenta</th>
					<th>Monto</th>
					<th>Cuotas</th>
					<th></th>
					<th></th>
				</tr>
				<tr>
					<td>Cliente x</td>
					<td>Cuenta 1</td>
					<td>$150000</td>
					<td>72</td>
					<td><input type="submit" value="autorizar"></td>
					<td><input type="submit" value="rechazar"></td>
				</tr>
					<tr>
					<td>Cliente 2</td>
					<td>Cuenta 2</td>
					<td>$150000</td>
					<td>12</td>
					<td><input type="submit" value="autorizar"></td>
					<td><input type="submit" value="rechazar"></td>
				</tr>
					<tr>
					<td>Cliente 3</td>
					<td>Cuenta 2</td>
					<td>$12000</td>
					<td>18</td>
					<td><input type="submit" value="autorizar"></td>
					<td><input type="submit" value="rechazar"></td>
				</tr>
</table>
		</div>
		<input type="submit" value="Enviar!">
	</form>
</div>
</body>

</html>