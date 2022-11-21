<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.io.Console"%>
	<%@page import="iNegocio.iNegocioTipoCuenta"%>
	<%@page import="Negocio.NegocioTipoCuenta"%>
	<%@page import="Entidades.TipoCuenta"%>
	<%@page import="Entidades.Cuenta"%>
	<%@page import="java.util.List"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="java.text.SimpleDateFormat"%>
	<%@page import="java.text.ParsePosition"%>
	<%@page import="java.sql.Date"%>
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
	<form method="post" action="/TPINT_GRUPO_6_LAB4/ServletCuentas" class="centrar-column">
	<%
	Cuenta cuentaEditar = null;
	List<Cuenta> listaCu = null;
	List<TipoCuenta> listaTipos = null;
	int filasModificadasEditar = 0;
	Boolean cuentaAgregada = false;
	Boolean limiteCuentas = false;
	Boolean tieneLetras;
	int cantidadDeCuentas = 3;
	int proximaPagina = 1;
	
	if (request.getAttribute("cuentaEditar") != null) {
		cuentaEditar = (Cuenta) request.getAttribute("cuentaEditar");
	}
	if (request.getAttribute("listaCuentas") != null) {
		listaCu = (List<Cuenta>) request.getAttribute("listaCuentas");
	}
	if (request.getAttribute("listaTiposCuentas") != null) {
		listaTipos = (List<TipoCuenta>) request.getAttribute("listaTiposCuentas");
	}
	if (request.getAttribute("filasModificadasEditar") != null) {
		filasModificadasEditar = Integer.valueOf(request.getAttribute("filasModificadasEditar").toString());
	}
	if (request.getAttribute("cuentaAgregada") != null){
		cuentaAgregada = Boolean.valueOf(request.getAttribute("cuentaAgregada").toString());
	}
	if (request.getAttribute("limiteCuentas") != null){
		limiteCuentas = Boolean.valueOf(request.getAttribute("limiteCuentas").toString());
	}
	if (request.getAttribute("proximaPagina") != null) {
		proximaPagina = Integer.valueOf(request.getAttribute("proximaPagina").toString());
	}
	if(request.getAttribute("tieneLetras")!= null)
	{
		%>*ingresar solo numeros
		<% 
	}
	%>
	
	<%if(cuentaAgregada){ %>
			<div class="alert alert-success alert-dismissible fade show" role="alert">
			  Se agregó exitosamente la cuenta del Cliente!
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			<%} %>
	<%if(limiteCuentas){ %>
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
			  El cliente no puede tener más de 3 cuentas!
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
			<%} %>
	<%if(filasModificadasEditar == 1){ %>
		<div class="alert alert-success alert-dismissible fade show" role="alert">
		  Se editó correctamente la cuenta!
		  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
	<%} %>
		<h1>Gestion Cuentas</h1>
		<div class="centrar-row">
			
				<label>Filtrar por usuario:</label> <input type="text" name=txtFUsuario /> &nbsp&nbsp
					Estado: <input type="checkbox" name="chkEstado" value="activo" id="ed1">&nbsp&nbsp&nbsp
					
				<input type="submit" value="Filtrar" name="Filtrar">  
				<br>
			
		</div>
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
						ArrayList<Cuenta> copiaListaPaginada = (ArrayList<Cuenta>) listaCu;
						for (int i = 0; i < copiaListaPaginada.size(); i++) {
							if (i >= (proximaPagina * cantidadDeCuentas) - cantidadDeCuentas
							&& i <= proximaPagina * cantidadDeCuentas - 1) {
					%>
					<tr>
						<td><input class="border-0 bg-transparent pe-none"
							name="getIdCuenta" value="<%=copiaListaPaginada.get(i).getIdCuenta()%>"></td>
						<td><%=copiaListaPaginada.get(i).getIdUsuario() %></td>
						<td><%=copiaListaPaginada.get(i).getIdTipoCuenta().getIdTipoCuenta() %></td>
						<td><%=copiaListaPaginada.get(i).getCBU() %></td>
						<td><%=copiaListaPaginada.get(i).getSaldo() %></td>
						<td><%=copiaListaPaginada.get(i).getFechaAlta()%></td>
						<td><%=copiaListaPaginada.get(i).isEstado() %></td>
						<form method="post" action="/TPINT_GRUPO_6_LAB4/ServletCuentas?getIdCuenta=<%=copiaListaPaginada.get(i).getIdCuenta()%>">
						<td><input type="submit" name="btnEditar" value="Editar"></td>
						<td><input type="submit" name="btnEliminar" value="Eliminar"></td>
						</form>
					</tr>
					<%
					}}}
					%>
			</tbody>
		</table>
		</form>
		<%
		if (listaCu != null) {
			for (int i = 0; i < Math.ceil((float) listaCu.size() / cantidadDeCuentas); i++) {
		%>
		<a href="/TPINT_GRUPO_6_LAB4/ServletCuentas?paginar=<%=i + 1%>"><%=i + 1%></a>
		<%
		}
		}
		%>
		
		<%
			if (cuentaEditar == null) {
			%>
		<form method="post" action="/TPINT_GRUPO_6_LAB4/ServletCuentas" class="centrar-column">
		<h2>Creación de Cuenta</h2>
		<div class="centrar-row">
			<div class="centrar-column container-fields">
			<div>
					<label>Cod Usuario</label> <input type="text" name="txtCodUsuario" required>
				</div>
				<div>
					<label>CBU</label> <input type="number" name="txtCBU" required>
				</div>
				<div>
					<label>Saldo: $</label><input class="pe-none border-0 bg-transparent" type="text" name="txtSaldo" value="10000">
				</div>
				<div>
					<label>Fecha Alta</label> <input type="date" name="inputFecha" required>
				</div>
				<div>
					<label>Estado</label> <input type="checkbox" name="chkEstado">
				</div>
				<div>
					<label>Tipo de cuenta</label> <select name=ddlTipoCuenta required>
						<%
						if(listaTipos != null){
					  		for(TipoCuenta tc:listaTipos){
						  %>
							<option value="<%=tc.getIdTipoCuenta()%>"><%=tc.getTipoCuenta() %></option>
							<%
							}
					  		}
							%>
					</select>
				</div>
			</div>
		</div>
		<input type="submit" value="Agregar" name=btnAgregar>
		<%
			} else {
			%>
			<h2>Edición de Cuenta</h2>
		<div class="centrar-row">
			<div class="centrar-column container-fields">
				<div>
					<label>Cod Cuenta</label> <input class="pe-none border-0 bg-transparent"
							name="txtCodCuenta" type="text"
							value="<%=cuentaEditar.getIdCuenta()%>">
				</div>
			<div>
					<label>Cod Usuario</label> <input type="text" name="txtCodUsuario" value="<%=cuentaEditar.getIdUsuario() %>" required>
				</div>
				<div>
					<label>CBU</label> <input type="number" name="txtCBU" value="<%=cuentaEditar.getCBU() %>" required>
				</div>
				<div>
					<label>Saldo</label> <input type="text" name="txtSaldo" value="<%=cuentaEditar.getSaldo() %>" required>
				</div>
				<div>
					<%
						long date = new SimpleDateFormat("dd/MM/yyyy").parse(cuentaEditar.getFechaAlta(), new ParsePosition(0))
								.getTime();
						Date dbDate = new Date(date);
						%>
					<label>Fecha Alta</label> <input type="date" name="inputFecha" value="<%=dbDate %>" required>
				</div>
				<div>
					<label>Estado</label> <input type="checkbox" name="chkEstado"
					<%String checkedEstado = "";
						if (cuentaEditar.isEstado()) {
							checkedEstado = "checked";
						}%>
							<%=checkedEstado%>>
				</div>
				<div>
					<label>Tipo de cuenta</label> <select name=ddlTipoCuenta>
					<% for (TipoCuenta tc : listaTipos){ %>
					<option value="<%=tc.getIdTipoCuenta()%>"
					<%String tipoCuenta="";
					if(cuentaEditar.getIdTipoCuenta().getIdTipoCuenta() == tc.getIdTipoCuenta()){
						tipoCuenta = "selected";
					}%>
						<%=tipoCuenta %>>
						<%=tc.getTipoCuenta() %></option>
						<%
						}
						%>
					</select>
				</div>
			</div>
		</div>
		<input type="submit" value="Editar" name=btnEditarCuenta>
		<%} %>
	</form>
		</div>
</body>
</html>