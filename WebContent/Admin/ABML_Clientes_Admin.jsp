<%@page import="java.io.Console"%>
<%@page import="Entidades.Usuario"%>
<%@page import="Entidades.Nacionalidad"%>
<%@page import="Entidades.Localidad"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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

	<div style="position: absolute; top: 150px; left: 300px;">
		<form method="POST" action="/TPINT_GRUPO_6_LAB4/ServeletClientes"
			class="centrar-column">
			<%
			List<Usuario> listaCli = null;
			Usuario clienteEditar = null;
			List<Nacionalidad> listaNac = null;
			List<Localidad> listaLoc = null;
			int proximoId = 0;
			int proximaPagina = 1;
			int filasAfectadasCrear = 0;
			int filasAfectadasEditar = 0;
			boolean filasAfectadasBorrar = false;

			int cantidadDeClientes = 3;

			if (request.getAttribute("listaClientes") != null) {
				listaCli = (List<Usuario>) request.getAttribute("listaClientes");
			}
			if (request.getAttribute("listaNacionalidades") != null) {
				listaNac = (List<Nacionalidad>) request.getAttribute("listaNacionalidades");
			}
			if (request.getAttribute("clienteEditar") != null) {
				clienteEditar = (Usuario) request.getAttribute("clienteEditar");
			}
			if (request.getAttribute("listaLocalidades") != null) {
				listaLoc = (List<Localidad>) request.getAttribute("listaLocalidades");
			}
			if (request.getAttribute("proximoId") != null) {
				proximoId = Integer.valueOf(request.getAttribute("proximoId").toString());
			}
			if (request.getAttribute("proximaPagina") != null) {
				proximaPagina = Integer.valueOf(request.getAttribute("proximaPagina").toString());
			}
			if (request.getAttribute("filasAfectadasCrear") != null) {
				filasAfectadasCrear = Integer.valueOf(request.getAttribute("filasAfectadasCrear").toString());
			}
			if (request.getAttribute("filasAfectadasEditar") != null) {
				filasAfectadasEditar = Integer.valueOf(request.getAttribute("filasAfectadasEditar").toString());
			}
			if (request.getAttribute("filasAfectadasBorrar") != null) {
				filasAfectadasBorrar = Boolean.valueOf(request.getAttribute("filasAfectadasBorrar").toString());
			}
			%>
			<%if(filasAfectadasCrear!=0){ %>
			<div class="alert alert-success alert-dismissible fade show" role="alert">
			  Cliente Creado Exitosamente!
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			<%} %>
			<%if(filasAfectadasBorrar){ %>
			<div class="alert alert-success alert-dismissible fade show" role="alert">
			  Se cambio exitosamente el estado del Cliente!
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			<%} %>
			<%if(filasAfectadasEditar!=0){ %>
			<div class="alert alert-success alert-dismissible fade show" role="alert">
			  Cliente Editado Exitosamente!
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			<%} %>
			<h1>Gestion Clientes</h1>
			
			<div class="centrar-row">
				<select name="ddlFiltro" id="filtro">
					<option value="todos">TODOS</option>
					<option value="filtrarPorId">ID</option>
					<option value="filtrarPorUsuario">Usuario</option>
					<option value="filtrarPorNombre">Nombre</option>
					<option value="filtrarPorApellido">Apellido</option>
				</select>
				<label id="tituloFiltro"></label> <input id="inputFiltro" name="inputFiltro"/> <input type="submit" name="filtrar" value="Filtrar">
				<script>
					function getCookie(cookieName) {
						  let cookie = {};
						  document.cookie.split(';').forEach(function(el) {
						    let [key,value] = el.split('=');
						    cookie[key.trim()] = value;
						  })
						  return cookie[cookieName];
						}
					
					let ddl = document.querySelector('#filtro');
					let input = document.getElementById('inputFiltro');
					let tituloFiltro = document.getElementById('tituloFiltro');
					
					
					document.addEventListener("DOMContentLoaded",(e)=>{
						let filtroSeleccionado = "";
						let filtroAplicado = getCookie("filtro");
						let filtroInputAplicado = getCookie("filtroInput");
						if(filtroAplicado==null){
							filtroAplicado=todos;
						}
						if(filtroInputAplicado==null){
							filtroInputAplicado="";
						}
						switch(filtroAplicado) {
							case "todos":
								filtroSeleccionado = "TODO";
								break;
							case "filtrarPorId":
								filtroSeleccionado = "ID";
								break;
							case "filtrarPorUsuario":
								filtroSeleccionado = "Usuario";
								break;
							case "filtrarPorNombre":
								filtroSeleccionado = "Nombre";
								break;
							case "filtrarPorApellido":
								filtroSeleccionado = "Apellido";
								break;
							default:
								filtroSeleccionado = "TODO";
								break;
						}
						tituloFiltro.innerHTML  = "Filtro <b>"+filtroSeleccionado+"</b>:"+filtroInputAplicado;
					})
					ddl.addEventListener("change",(e)=>{
						document.cookie = "filtro="+e.target.value;
						let filtroSeleccionado = "";
						let filtroAplicado = e.target.value;
						let filtroInputAplicado = getCookie("filtroInput");
						switch(filtroAplicado) {
							case "todos":
								filtroSeleccionado = "TODO";
								break;
							case "filtrarPorId":
								filtroSeleccionado = "ID";
								break;
							case "filtrarPorUsuario":
								filtroSeleccionado = "Usuario";
								break;
							case "filtrarPorNombre":
								filtroSeleccionado = "Nombre";
								break;
							case "filtrarPorApellido":
								filtroSeleccionado = "Apellido";
								break;
							default:
								filtroSeleccionado = "";
								break;
						}
						tituloFiltro.innerHTML  = "Filtro <b>"+filtroSeleccionado+"</b>:"+filtroInputAplicado;
					})
					input.addEventListener("change",(e)=>{
						let filtroAplicado = getCookie("filtro");
						let filtroSeleccionado = "";
						document.cookie = "filtroInput="+e.target.value;
						switch(filtroAplicado) {
						case "todos":
							filtroSeleccionado = "TODO";
							break;
						case "filtrarPorId":
							filtroSeleccionado = "ID";
							break;
						case "filtrarPorUsuario":
							filtroSeleccionado = "Usuario";
							break;
						case "filtrarPorNombre":
							filtroSeleccionado = "Nombre";
							break;
						case "filtrarPorApellido":
							filtroSeleccionado = "Apellido";
							break;
						default:
							filtroSeleccionado = "";
							break;
					}
						tituloFiltro.innerHTML  = "Filtro <b>"+filtroSeleccionado+"</b>:"+e.target.value;
					})
				</script>
			</div>
		</form>
		<form method="POST" action="/TPINT_GRUPO_6_LAB4/ServeletClientes"
			class="centrar-column">
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
						ArrayList<Usuario> copiaListaPaginada = (ArrayList<Usuario>) listaCli;
						for (int i = 0; i < copiaListaPaginada.size(); i++) {
							if (i >= (proximaPagina * cantidadDeClientes) - cantidadDeClientes
							&& i <= proximaPagina * cantidadDeClientes - 1) {
					%>
					<tr>
						<td><input class="border-0 bg-transparent pe-none"
							name="idModificar"
							value="<%=copiaListaPaginada.get(i).getIdUsuario()%>"></td>
						<td><%=copiaListaPaginada.get(i).isEsAdmin()%></td>
						<td><%=copiaListaPaginada.get(i).getUsuario()%></td>
						<td><%=copiaListaPaginada.get(i).getNombre()%></td>
						<td><%=copiaListaPaginada.get(i).getApellido()%></td>
						<td><%=copiaListaPaginada.get(i).getSexo()%></td>
						<td><%=copiaListaPaginada.get(i).getFechaNacimiento()%></td>
						<td><%=copiaListaPaginada.get(i).getIdNacionalidad().getIdNacionalidad()%></td>
						<td><%=copiaListaPaginada.get(i).getIdLocalidad().getIdLocalidad()%></td>
						<td><%=copiaListaPaginada.get(i).isEstado()%></td>
						<form method="POST"
							action="/TPINT_GRUPO_6_LAB4/ServeletClientes?idModificar=<%=copiaListaPaginada.get(i).getIdUsuario()%>">
							<td><input class="btn btn-secondary" type="submit"
								name="btnEditar" value="Editar"></td>
							<td><input class="btn btn-secondary" type="submit"
								name="btnEliminar" value="Eliminar"></td>
						</form>
					</tr>
					<%
					}
					}
					}
					%>
				</tbody>
			</table>
			</form>
			<%
			if (listaCli != null) {
				for (int i = 0; i < Math.ceil((float) listaCli.size() / cantidadDeClientes); i++) {
			%>
			<a href="/TPINT_GRUPO_6_LAB4/ServeletClientes?paginar=<%=i + 1%>"><%=i + 1%></a>
			<%
			}
			}
			%>
			<%
			if (clienteEditar == null) {
			%>
			<form method="POST" action="/TPINT_GRUPO_6_LAB4/ServeletClientes"
			class="centrar-column">
			<h2>Creacion de Cliente</h2>
			<div class="centrar-row">
				<div class="centrar-column container-fields">
					<div>
						<b>ID</b> <input class="pe-none border-0 bg-transparent"
							name="txtId" type="text" value="<%=proximoId%>" required>
					</div>
					<div>
						<b>DNI</b> <input type="number" name="txtDni" required>
					</div>
					<div>
						<b>CUIL</b> <input type="number" name="txtCuil" required>
					</div>
					<div>
						<b>Usuario</b> <input type="text" name="txtUsuario" required>
					</div>
					<div>
						<b>Contraseņa</b> <input type="password" name="txtContrasenia" required>
					</div>
					<div>
						<b>Nombre</b> <input type="text" name="txtNombre" required>
					</div>
					<div>
						<b>Apellido</b> <input type="text" name="txtApellido" required>
					</div>
				</div>
				<div class="centrar-column container-fields">
					<div>
						<b>Sexo</b> <select name="txtSexo" required>
							<option value="Masculino">Masculino</option>
							<option value="Femenino">Femenino</option>
							<option value="Otro">Otro</option>
						</select>
					</div>
					<div>
						<b>Fecha de Nacimiento</b> <input type="date" name="txtFechaNac" required>
					</div>
					<div>
						<b>Nacionalidad</b> <select name="txtNacionalidad" required>
							<%
							if (listaNac != null) {
								for (Nacionalidad n : listaNac) {
							%>
							<option value="<%=n.getIdNacionalidad()%>">
								<%=n.getNacionalidad()%></option>
							<%
							}
							}
							%>
						</select>
					</div>
					<div>
						<b>Localidad</b> <select name="txtLocalidad" required>
							<%
							if (listaLoc != null) {
								for (Localidad l : listaLoc) {
							%>
							<option value="<%=l.getIdLocalidad()%>">
								<%=l.getLocalidad()%></option>
							<%
							}
							}
							%>
						</select>
					</div>
					<div>
						<b>Calle</b> <input name="txtCalle" type="text" required>
					</div>
					<div>
						<b>Altura</b> <input name="txtAltura" type="number" required>
					</div>
					<div>
						<b>Email</b> <input type="email" name="txtEmail" required>
					</div>
					<div>
						<b>Estado</b> <input type="checkbox" name="txtEstado" >
					</div>
					<div>
						<b>Admin?</b> <input type="checkbox" name="txtAdmin" >
					</div>
					<input type="text" hidden name="txtCantCuentas" value="0">
				</div>
			</div>
			<input class="btn btn-secondary" type="submit" name="btnCrearUsuario"
				value="Crear!">
			<%
			} else {
			%>
			<h2>Edicion de Cliente</h2>
			<div class="centrar-row">
				<div class="centrar-column container-fields">
					<div>
						<b>ID</b> <input class="pe-none border-0 bg-transparent"
							name="txtId" type="text"
							value="<%=clienteEditar.getIdUsuario()%>">
					</div>
					<div>
						<b>DNI</b> <input name="txtDni" type="number"
							value="<%=clienteEditar.getNroDni()%>">
					</div>
					<div>
						<b>CUIL</b> <input name=txtCuil type="number"
							value="<%=clienteEditar.getNroCuil()%>">
					</div>
					<div>
						<b>Usuario</b> <input name="txtUsuario"
							class="pe-none border-0 bg-transparent" type="text"
							value="<%=clienteEditar.getUsuario()%>">
					</div>
					<div>
						<b>Contraseņa</b> <input type="password" name="txtContrasenia"
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
							<option
								<%String sexoMasculino = "";
if (clienteEditar.getSexo().equals("Masculino")) {
	sexoMasculino = "selected";
}%>
								<%=sexoMasculino%> value="Masculino">Masculino</option>
							<option
								<%String sexoFemenino = "";
if (clienteEditar.getSexo().equals("Femenino")) {
	sexoFemenino = "selected";
}%>
								<%=sexoFemenino%> value="Femenino">Femenino</option>
							<option
								<%String sexoOtro = "";
if (clienteEditar.getSexo().equals("Otro")) {
	sexoOtro = "selected";
}%>
								<%=sexoOtro%> value="Otro">Otro</option>
						</select>
					</div>
					<div>
						<%
						long date = new SimpleDateFormat("dd/MM/yyyy").parse(clienteEditar.getFechaNacimiento(), new ParsePosition(0))
								.getTime();
						Date dbDate = new Date(date);
						%>
						<b>Fecha de Nacimiento</b> <input type="date" name="txtFechaNac"
							value="<%=dbDate%>">
					</div>
					<div>
						<b>Nacionalidad</b> <select name="txtNacionalidad">
							<%
							for (Nacionalidad n : listaNac) {
							%>
							<option value="<%=n.getIdNacionalidad()%>"
								<%String nacionalidad = "";
if (clienteEditar.getIdNacionalidad().getIdNacionalidad() == n.getIdNacionalidad()) {
	nacionalidad = "selected";
}%>
								<%=nacionalidad%>>
								<%=n.getNacionalidad()%></option>
							<%
							}
							%>
						</select>
					</div>
					<div>
						<b>Localidad</b> <select name="txtLocalidad">
							<%
							for (Localidad l : listaLoc) {
							%>
							<option value="<%=l.getIdLocalidad()%>"
								<%String localidad = "";
if (clienteEditar.getIdLocalidad().getIdLocalidad() == l.getIdLocalidad()) {
	localidad = "selected";
}%>
								<%=localidad%>>
								<%=l.getLocalidad()%></option>
							<%
							}
							%>
						</select>
					</div>
					<div>
						<b>Calle</b> <input name="txtCalle" type="text"
							value="<%=clienteEditar.getCalle()%>">
					</div>
					<div>
						<b>Altura</b> <input name="txtAltura" type="number"
							value="<%=clienteEditar.getAltura()%>">
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
					<input type="text" hidden name="txtCantCuentas"
						value="<%=clienteEditar.getCantCuentas()%>">
				</div>
			</div>
			<input class="btn btn-secondary" type="submit"
				name="btnEditarUsuario" value="Editar!">
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