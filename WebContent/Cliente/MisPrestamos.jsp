<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Entidades.Prestamo"%>
<%@page import="Entidades.Cuota"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.time.LocalDateTime" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="java.util.Date" %>
<%@page import="java.time.Instant" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Cliente/EstilosGenerales.css" rel="stylesheet" type="text/css">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/2fcd49ae61.js" crossorigin="anonymous"></script>


<title>Mis Prestamos</title>
</head>
<body>

<%@include file="MasterPageCliente.jsp"%>

<div style="position: absolute;top:150px;left:400px;">

<h1>MIS PRESTAMOS</h1><br><br>
		<div class="centrar-row" style="overflow-y: scroll;height:300px;">
		<table id="tblPrestamos" class="table table-secondary text-center table-striped">
			<tbody>
				 <thead class="table-dark">
					<td>Prestamo</td>
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
			
				List<Prestamo> lstPrestamos = (List<Prestamo>)request.getAttribute("lstMisPrestamos");
				
				if(lstPrestamos!=null){
					for(Prestamo p : lstPrestamos){
						
				%>
				<tr>
					<td><%=p.getIdPrestamo()%></td>
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
				}
				%>
			</tbody>
		</table>
</div>
<br><br>
<h1>MIS CUOTAS</h1><br><br>
		<div class="centrar-row" style="overflow-y: scroll;height:300px;">
		
		<% if(request.getParameter("btnPagar")!=null && request.getAttribute("pagarCuota")!=null) {
					
						Boolean pago= (Boolean)request.getAttribute("pagarCuota");
					if(pago){
					%>
						 <div class="alert alert-success  alert-dismissible fade show" style="width:auto;"role="alert">
						  	La cuota se debito de su cuenta correctamente.
						<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
						</div>  
					<% } %>
					<% if(!pago){
					%>
					 <div class="alert alert-danger alert-dismissible fade show" style="width:auto;"role="alert">
						  	La cuota no pudo debitarse verifique el saldo disponible.
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
						
					<% } }%>
		<table id="tblCuotas" class="table table-secondary text-center table-striped" >
			<tbody>
				<thead class="table-dark">
					<td>Prestamo</td>
					<td>Cuota</td>
					<td>Pago</td>
					<td>Vencimiento</td>
					<td>Pagar</td>
				</thead>
				<%
				List<Cuota> lstCuotas = (List<Cuota>)request.getAttribute("lstCuotas");
				
				if(lstCuotas!=null){
					for(Cuota c : lstCuotas){	
				%>
				<tr>
				<form method="post" action="/TPINT_GRUPO_6_LAB4/ServletPrestamos">
					<td><%=c.getPrestamo().getIdPrestamo()%><input type="hidden" name="idPrestamo" value="<%=c.getPrestamo().getIdPrestamo()%>"></td>
					<td><%=c.getNroCuota()%> <input type="hidden" name="nroCuota" value="<%=c.getNroCuota()%>"></td>
				<%	
				String estadoPago="";
				if(c.getFechaPago()==null) {
							estadoPago="Pendiente";		
				%>		
						<td><%=estadoPago%></td>
						<td><%=c.getFechaVto()%></td>
						<td><input type="submit" value="&#10004" name="btnPagar"></td>		
				</form>
				</tr>		
				<%}else { 
				estadoPago=c.getFechaPago();
				%>	
						<td><%=estadoPago%></td>
						<td><%=c.getFechaVto()%></td>
						<td></td>	
				</tr>
				<% }%>
				<%	
					}
					}
				%>
				</tbody>
		</table>
		
					
		
		</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>