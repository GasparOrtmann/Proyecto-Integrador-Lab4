<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.io.Console"%>
	<%@page import="iNegocio.iNegocioTipoCuenta"%>
	<%@page import="Negocio.NegocioTipoCuenta"%>
	<%@page import="Entidades.TipoCuenta"%>
	<%@page import="Entidades.Cuenta"%>
	<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ABML Cuentas Admin</title>
<link href="Cliente/EstilosGenerales.css" rel="stylesheet"
	type="text/css">
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

<div style="position: absolute;top:150px;left:400px;">
	<form method="POST" action="/TPINT_GRUPO_6_LAB4/ServletCuentas" class="centrar-column">
		<h1>Gestion Cuentas</h1>
		<div class="centrar-row">
			<label>Filtros:</label> <input /> <input type="submit"
				value="Filtrar">
		</div>
		<%
		List<Cuenta> listaCu = null;
			if (request.getAttribute("listaCuentas") != null) {
				listaCu = (List<Cuenta>) request.getAttribute("listaCuentas");
			}
			%>
		<table class="table table-striped">
				<thead class="table-dark">
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
				</thead>
			<tbody>
				<%
					if(listaCu != null){
					for (Cuenta c : listaCu) {
					%>
					<tr>
						<td><%=c.getIdCuenta() %></td>
						<td><%=c.getIdUsuario() %></td>
						<td><%=c.getIdTipoCuenta() %></td>
						<td><%=c.getCBU() %></td>
						<td><%=c.getSaldo() %></td>
						<td><%=c.getFechaAlta()%></td>
						<td><%=c.isEstado() %></td>
						<td><input type="submit" name="btnEditar" value="Editar"></td>
						<td><input type="submit" name="btnEliminar" value="Eliminar"></td>
					</tr>
					<%
					}}
					%>
			</tbody>
		</table>
		<h2>Creación de Cuenta</h2>
		<div class="centrar-row">
			<div class="centrar-column container-fields">
				<div>
					<label>CBU</label> <input type="text" name="txtCBU">
				</div>
				<div>
					<label>Saldo</label> <input type="text" name="txtSaldo">
				</div>
				<div>
					<label>Fecha Alta</label> <input type="text" name="inputFecha">
				</div>
				<div>
					<label>Estado</label> <input type="checkbox" name="chkEstado">
				</div>
				<div>
					<label>Tipo de cuenta</label> <select name=ddlTipoCuenta>
						<%
						iNegocioTipoCuenta cneg = new NegocioTipoCuenta();
					  	List<TipoCuenta> lstTipos= cneg.traerTiposCuentas();
					
					  for(TipoCuenta tc:lstTipos){
						  %>
							<option value="<%=tc.getIdTipoCuenta()%>"><%=tc.getTipoCuenta() %></option>
							<%} %>
					</select>
				</div>
			</div>
		</div>
		<input type="submit" value="Aceptar" name=btnAceptar>
	</form>
		</div>
</body>
</html>