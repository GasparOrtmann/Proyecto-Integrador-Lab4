<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@page import="Entidades.Prestamo"%>
	<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Cliente/EstilosGenerales.css" rel="stylesheet" type="text/css">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/2fcd49ae61.js" crossorigin="anonymous"></script>
<title>Autorizacion de Prestamos</title>
</head>
<body>

<%@include file="MasterPageAdmin.jsp"%>

<div style="position: absolute;top:150px;left:400px;">

<form method="post" action="/TPINT_GRUPO_6_LAB4/ServletPrestamos" class="centrar-column">
		<h1>PRESTAMOS</h1>
		<div class="centrar-row">
			<label>Filtros:</label> <input /> <input type="submit"
				value="Filtrar">
		</div>
		<br/>
		<table class="table table-secondary table-striped">
			<tbody>
				 <thead class="table-dark">
					<td>Prestamo</td>
					<td>Cliente</td>
					<td>Monto Solicitado</td>
					<td>Total Adeudado</td>
					<td>Cuota Fija</td>
					<td>Cuotas</td>
					<td>Adeudadas</td>
					<td>Pagas</td>
					<td>Cuenta</td>
					<td>Fecha</td>
					<td>Estado</td>
				</thead>
				<%
			
				List<Prestamo> lstPrestamos = (List<Prestamo>)request.getAttribute("lstPrestamos");
				
				if(lstPrestamos!=null){
					for(Prestamo p : lstPrestamos){
						
				%>
				<tr>
					<td><%=p.getIdPrestamo()%></td>
					<td><%=p.getIdUsuario()%></td>
					<td><%=p.getMontoPrestamo()%></td>
					<td><%=p.getMontoTotalAdeudado()%></td>
					<td><%=p.getImporteCuotaFija()%></td>
					<td><%=p.getCantidadCuotas()%></td>
					<td><%=p.getCuotasAdeudadas()%></td>
					<td><%=p.getCuotasPagas()%></td>
					<td><%=p.getIdCuenta()%></td>
					<td><%=p.getFechaAlta()%></td>
					<td><%=p.getEstado()%></td>
				</tr>
				<%	
					}
				%>
			</tbody>
		</table>
		<br/>
		<br/>
		<br/>
		<br/>
		<div class="centrar-row">
		<h1>AUTORIZACIONES PENDIENTES</h1>
		<br/>
		Filtrar por cliente: <input type="text" name="txtFiltro"> <input type="submit" name="btnFiltrar" value="Filtrar"><br/>
		<br>
		<table class="table table-secondary table-striped">
			<tbody>
				<thead class="table-dark">
					<td>Ciente</td>
					<td>Cuenta</td>
					<td>Monto</td>
					<td>Cuotas</td>
					<td>Autorizar</td>
					<td>Rechazar</td>
				</thead>
				<% if(lstPrestamos!=null){
					
					for(Prestamo p : lstPrestamos){
						
						if(p.getEstado().equalsIgnoreCase("Pendiente")){
				%>
				<tr>
					<td><%=p.getIdUsuario()%></td>
					<td><%=p.getIdCuenta()%></td>
					<td><%=p.getMontoPrestamo()%></td>
					<td><%=p.getCantidadCuotas()%></td>
					<td><input type="submit" value="&#10004" name="btnAutorizar"></td>
					<td><input type="submit" value="&#10006" name="btnRechazar"></td>
				</tr>
				<%
				}	
				}
				}
				}
				%>
				</tbody>
		</table>
		</div>
		<br/>		
	</form>
</div>


<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>

</html>