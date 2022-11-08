<%@page import="java.io.Console"%>
<%@page import="Entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParsePosition"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ABML Clientes Admin</title>
<link href="Cliente/EstilosGenerales.css" rel="stylesheet"
	type="text/css">
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script src="https://kit.fontawesome.com/2fcd49ae61.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<%@include file="MasterPageAdmin.jsp"%>

	<div style="position: absolute; top: 150px; left: 400px;">
		<form method="POST" action="/TPINT_GRUPO_6_LAB4/ServeletClientes"
			class="centrar-column">
			<h1>Gestion Clientes</h1>
			<div class="centrar-row">
				<label>Filtros:</label> <input /> <input type="submit"
					value="Filtrar">
			</div>
			<%
			List<Usuario> listaCli = null;
			Usuario clienteEditar = null;
			if (request.getAttribute("listaClientes") != null) {
				listaCli = (List<Usuario>) request.getAttribute("listaClientes");
			}
			if (request.getAttribute("listaClientes") != null) {
				clienteEditar = (Usuario) request.getAttribute("clienteEditar");
			}
			%>
			<table class="table table-striped">
				<thead class="table-dark">
					<tr>
						<td>Cod Usuario</td>
						<td>Admin?</td>
						<td>Usuario</td>
						<td>Nombre</td>
						<td>Apellido</td>
						<td>Sexo</td>
						<td>Fech. Nac.</td>
						<td>Nacionalidad</td>
						<td>Localidad</td>
						<td>Estado</td>
						<td></td>
						<td></td>
					</tr>
				</thead>
				<tbody>
					<%
					if (listaCli != null) {
						for (Usuario s : listaCli) {
					%>
					<tr>
						<td><input class="border-0 bg-transparent pe-none"
							name="codUsuarioCambios" value="<%=s.getIdUsuario()%>"></td>
						<td><%=s.isEsAdmin()%></td>
						<td><%=s.getUsuario()%></td>
						<td><%=s.getNombre()%></td>
						<td><%=s.getApellido()%></td>
						<td><%=s.getSexo()%></td>
						<td><%=s.getFechaNacimiento()%></td>
						<td><%=s.getIdNacionalidad().getIdNacionalidad()%></td>
						<td><%=s.getIdLocalidad().getIdLocalidad()%></td>
						<td><%=s.isEstado()%></td>
						<td><input type="submit" name="btnEditar" value="Editar"></td>
						<td><input type="submit" name="btnEliminar" value="Eliminar"></td>
					</tr>
					<%
					}
					}
					%>
				</tbody>
			</table>
			<%
			if (clienteEditar == null) {
			%>
			<h2>Creacion de Usuario</h2>
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
			<input type="submit" name="btnCrearUsuario" value="Enviar!">
			<%
			} else {
			%>
			<h2>Edicion de Usuario</h2>
			<div class="centrar-row">
				<div class="centrar-column container-fields">
					<div>
						<b>ID</b> <input class="pe-none border-0 bg-transparent"
							name="txtDni" type="text"
							value="<%=clienteEditar.getIdUsuario()%>">
					</div>
					<div>
						<b>DNI</b> <input name="txtDni" type="text"
							value="<%=clienteEditar.getNroDni()%>">
					</div>
					<div>
						<b>CUIL</b> <input name=txtCuil type="text"
							value="<%=clienteEditar.getNroCuil()%>">
					</div>
					<div>
						<b>Usuario</b> <input name="txtUsuario" type="text"
							value="<%=clienteEditar.getUsuario()%>">
					</div>
					<div>
						<b>Contraseña</b> <input type="password" name="txtContrasenia"
							value="<%=clienteEditar.getPassword()%>">
					</div>
					<div>
						<b>Nombre</b> <input name="txtNombre" type="text"
							value="<%=clienteEditar.getNombre()%>">
					</div>
					<div>
						<b>Apellido</b> <input name="txtApellido" type="text"
							value="<%=clienteEditar.getApellido()%>">
					</div>
				</div>
				<div class="centrar-column container-fields">
					<div>
						<b>Sexo</b> <select name="txtSexo">
							<option <%String sexoMasculino="";if(clienteEditar.getSexo().equals("Masculino")){sexoMasculino="selected";}%> <%=sexoMasculino %> value="Masculino">Masculino</option>
							<option <%String sexoFemenino="";if(clienteEditar.getSexo().equals("Femenino")){sexoFemenino="selected";}%> <%=sexoFemenino %> value="Femenino">Femenino</option>
							<option <%String sexoOtro="";if(clienteEditar.getSexo().equals("Otro")){sexoOtro="selected";}%> <%=sexoOtro %> value="Otro">Otro</option>
						</select>
					</div>
					<div>
						<%
						long date=new SimpleDateFormat("dd/MM/yyyy").parse(clienteEditar.getFechaNacimiento(),new ParsePosition(0)).getTime();
						Date dbDate=new Date(date);
						%>
						<b>Fecha de Nacimiento</b> <input type="date" name="txtFechaNac"
							value="<%=dbDate%>">
					</div>
					<div>
						<b>Nacionalidad</b> <select name="txtNacionalidad"
							value="<%=clienteEditar.getIdNacionalidad()%>">
							<option>Seleccione nacionalidad</option>
						</select>
					</div>
					<div>
						<b>Localidad</b> <select name="txtLocalidad"
							value="<%=clienteEditar.getIdLocalidad()%>">
							<option>Seleccione Localidad</option>
						</select>
					</div>
					<div>
						<b>Email</b> <input name="txtEmail" type="email"
							value="<%=clienteEditar.getEmail()%>">
					</div>
					<div>
						<b>Estado</b> <input type="checkbox" name="txtEstado"
							<%String checkedEstado = "";
							if (clienteEditar.isEstado()) {
								checkedEstado = "checked";
							}%>
							<%=checkedEstado%>>
					</div>
					<div>
						<b>Admin?</b> <input type="checkbox" name="txtAdmin"
							<%String checkedAdmin = "";
							if (clienteEditar.isEsAdmin()) {
								checkedAdmin = "checked";
							}%>
							<%=checkedAdmin%>>
					</div>
				</div>
			</div>
			<input type="submit" name="btnEditarUsuario" value="Enviar!">
			<%
			}
			%>
		</form>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
</body>
</html>