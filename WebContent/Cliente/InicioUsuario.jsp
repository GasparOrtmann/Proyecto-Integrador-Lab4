<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@page import="Entidades.Cuenta"%>
	<%@page import="Entidades.Movimiento"%>
	<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Cliente/EstilosGenerales.css" rel="stylesheet"
	type="text/css">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"rel="stylesheet"integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"crossorigin="anonymous">
<script src="https://kit.fontawesome.com/2fcd49ae61.js"crossorigin="anonymous"></script>
<title>Inicio Usuario</title>

</head>
<body>



<%@include file="MasterPageCliente.jsp"%>

<div style="position: absolute;top:150px;left:400px; width:1400px;">
	<form method="post" action="/TPINT_GRUPO_6_LAB4/ServeletClientes" class="centrar-column">
	<%
	List<Cuenta> listaCuentas = null;
	List<Movimiento> listaMovimientos = null;
	List<Movimiento> listaHistorial = null;
	
	if (request.getAttribute("listaCuentas") != null) {
		listaCuentas = (List<Cuenta>) request.getAttribute("listaCuentas");
	}
	if (request.getAttribute("listaMovimientos") != null) {
		listaMovimientos = (List<Movimiento>) request.getAttribute("listaMovimientos");
	}
	if (request.getAttribute("listaHistorial") != null) {
		listaHistorial = (List<Movimiento>) request.getAttribute("listaHistorial");
	}
	%>
		
		<div class="containerContentData">
			<div class="containerCuentas">
			
			<%
			if(listaCuentas != null){ %>
				<div class="card-header">
				<div class="card-header-container">
					<h3 class="card-header-title">Tus cuentas</h3>
				</div>
			</div><%
				for(Cuenta c : listaCuentas){
			%>
				<div class="containerCuenta">
					<div class="containerCuentaPrincipal">
						<label>Cuenta N°: </label><input class="border-0 bg-transparent pe-none" name="getIdCuenta" value="<%=c.getIdCuenta()%>" style="
    				color: white; font-weight: bolder;"><label>$ <%=c.getSaldo() %></label>
					</div>
					<label><%=c.getIdTipoCuenta().getTipoCuenta() %></label>
					<div class="containerCuentaPrincipal">
						<label>CBU: <%=c.getCBU() %></label>
						<button type="submit" name="btnVerHistorial" value="<%=c.getIdCuenta()%>">Ver historial</button>
					</div>
				</div>
				<%
				}}
				%>
				<div>
			<%
			if(listaMovimientos!=null){
				int idCuenta = Integer.parseInt(request.getParameter("btnVerHistorial"));
				%>
				
				<div class="card-header">
				
				<div class="card-header-container" style="margin-right: auto;">
					<h3 class="card-header-title">Historial de Cuenta <%=idCuenta %></h3>
				</div>
				<div class="card-header">
					<button type="submit" name="btnVolver" >Volver</button>
				</div>
			</div>
				<table class="table">
				<thead>
				<tr class="th">
					<td>Número de Movimiento</td>
					<td>Tipo de Movimiento</td>
					<td>Fecha</td>
					<td>Importe</td>
					<td>Detalle</td>
				</tr>
				</thead>
			<tbody>
				 <%
				for (Movimiento m : listaMovimientos){
				%>
				<tr>
						<td><%=m.getIdMovimiento() %></td>
						<td><%=m.getIdTipoMovimiento().getTipoMovimiento() %></td>
						<td><%=m.getFecha() %></td>
						<td>$<%=m.getImporte()%></td>
						<td><%=m.getDetalle() %></td>
				</tr>
				<%
				}}
				%>
			</tbody>
		</table>
		</div>
			</div>
			<div class="containerActividad" style="overflow-y: scroll;">
			<div class="card-header">
				<div class="card-header-container">
					<h3 class="card-header-title">Tu actividad</h3>
				</div>
			</div>
			<%
			if(listaHistorial!=null){
				for (Movimiento h : listaHistorial){
			%> 
				<div class="containerActividadIndividual">
					<div class="movimiento">
					<label style="font-weight: bolder;"><%=h.getIdTipoMovimiento().getTipoMovimiento() %> (Cuenta <%=h.getIdCuenta().getIdCuenta() %>)</label>
					<label style="font-weight: bolder;">$ <%=h.getImporte() %></label>
					</div>
					<div class="containerCuentaPrincipal">
						<label><%=h.getDetalle() %></label>
						<label><%=h.getFecha() %></label>
					</div>
				</div>
				<%
				}}
				%>
			</div>
			</div>
			</form>
		</div>
	
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"crossorigin="anonymous"></script>
</body>
</html>