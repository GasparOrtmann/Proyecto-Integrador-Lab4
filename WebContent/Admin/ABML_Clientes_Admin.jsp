
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ABML Clientes Admin</title>
<link href="../styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="MasterPageAdmin.jsp"%>

<div style="position: absolute;top:150px;left:400px;">
	<form class="centrar-column">
		<h1>Gestion Clientes</h1>
		<div class="centrar-row">
			<label>Filtros:</label> <input /> <input type="submit"
				value="Filtrar">
		</div>
		<table border="1">
			<tbody>
				<tr>
					<td>Cod Usuario</td>
					<td>Admin?</td>
					<td>DNI</td>
					<td>CUIL</td>
					<td>Usuario</td>
					<td>Nombre</td>
					<td>Apellido</td>
					<td>Sexo</td>
					<td>Fech. Nac.</td>
					<td>Nacionalidad</td>
					<td>Provincia</td>
					<td>Localidad</td>
					<td>Email</td>
					<td>Estado</td>
					<td></td>
					<td></td>
				</tr>
				<%
				for (int i = 0; i < 5; i++) {
				%>
				<tr>
					<td><%=i%></td>
					<td>Si</td>
					<td>42.123.123</td>
					<td>20-42.123.123-3</td>
					<td>UsuarioPrueba</td>
					<td>Usuario</td>
					<td>Prueba</td>
					<td>M</td>
					<td>10/10/2000</td>
					<td>Argentino</td>
					<td>Buenos Aires</td>
					<td>Tigre</td>
					<td>usuarioprueba@gmail.com</td>
					<td>activo</td>
					<td><input type="submit" value="Editar"></td>
					<td><input type="submit" value="Eliminar"></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<h2>Creacion/Edicion de Usuario</h2>
		<div class="centrar-row">
			<div class="centrar-column container-fields">
				<div>
					<label>DNI</label> <input>
				</div>
				<div>
					<label>CUIL</label> <input>
				</div>
				<div>
					<label>Usuario</label> <input>
				</div>
				<div>
					<label>Contraseña</label> <input type="password">
				</div>
				<div>
					<label>Nombre</label> <input>
				</div>
				<div>
					<label>Apellido</label> <input>
				</div>
			</div>
			<div class="centrar-column container-fields">
				<div>
					<label>Sexo</label> <select>
						<option value="Masculino">Masculino</option>
						<option value="Femenino">Femenino</option>
						<option value="Otro">Otro</option>
					</select>
				</div>
				<div>
					<label>Fecha de Nacimiento</label> <input type="date">
				</div>
				<div>
					<label>Nacionalidad</label> <select>
						<option>Seleccione nacionalidad</option>
					</select>
				</div>
				<div>
					<label>Localidad</label> <select>
						<option>Seleccione Localidad</option>
					</select>
				</div>
				<div>
					<label>Email</label> <input>
				</div>
				<div>
					<label>Estado</label> <input type="checkbox">
				</div>
				<div>
					<label>Admin?</label> <input type="checkbox">
				</div>
			</div>
		</div>
		<input type="submit" value="Enviar!">
	</form>
		</div>
</body>
</html>