<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entidades.Cuenta"%>
	<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Cliente/EstilosGenerales.css" rel="stylesheet" type="text/css">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/2fcd49ae61.js" crossorigin="anonymous"></script>
<title>Solicitar Prestamo</title>
</head>
<body>

<%@include file="MasterPageCliente.jsp"%>

<div style="position: absolute;top:150px;left:400px; width:1500px;">

<h1>SOLICITAR PRESTAMO</h1><br><br>
  <div class="conteiner-fluid">
	  <div class="row">
		  <div class="col-6" style="height:300px">
		  <form method="get" action="/TPINT_GRUPO_6_LAB4/ServletPrestamos">
		   <label>Ingrese el Monto a solicitar:</label> <input type="text" name="txtMontoPrestamo" required> <br><br>
		   <label>Seleccione la cantidad de Cuotas</label> <select name=ddlCuotas>
								<option value="12">12</option>
								<option value="24">24</option>
								<option value="48">48</option>
								<option value="36">36</option>
								</select><br><br>
		   <label>Cuenta donde se acreditara el Prestamo</label>  <select name=ddlCuentas>
									<% if(miSession.getAttribute("lstCuentasUsuario")!=null){
										List<Cuenta> lstCuentasUsuario = (List<Cuenta>)miSession.getAttribute("lstCuentasUsuario");
										for(Cuenta cuenta:lstCuentasUsuario){
						 			 %>
									<option value="<%=cuenta.getIdCuenta()%> - <%=cuenta.getCBU() %>"><%=cuenta.getIdCuenta()%> - <%=cuenta.getCBU() %></option>
									<%
									}
									}
									%>
								</select><br><br><br><br><br>
		   <input type="submit" value="Simular" name="btnSimularPrestamo"  class="btnSimularPrestamo">
		   </form>
		   </div>
		   <div class="col-6" style="color:white; background-color:#B4B1C4;width:600px; height:300px;border-radius:5px;">
		   <br>
		 
		   		<%
		   			if(request.getParameter("btnSimularPrestamo")!=null){
		   				
			   			float montoSolicitado = Float.parseFloat(miSession.getAttribute("montoSolicitado").toString());
			   			String cuentaAcreditacion = (String)miSession.getAttribute("idCuentaAcreditacion");
			   			int cantCuotas = Integer.parseInt(miSession.getAttribute("cantCuotas").toString());
			   			float interesPorcentajeTotal = cantCuotas/12*85;
			   			float interesTotal = interesPorcentajeTotal*montoSolicitado/100;
			   			float cuotaFija= (interesTotal+montoSolicitado)/cantCuotas;
			   			      miSession.setAttribute("cuotaFija",cuotaFija);
			   			float montoDevolver= (((((cantCuotas/12)*85)*montoSolicitado)/100)+montoSolicitado);
		   			
		   		%>
		   		<form method="post" action="/TPINT_GRUPO_6_LAB4/ServletPrestamos">
			   <p>Monto solicitado :  $<%=montoSolicitado%>.-<p>
			   <p>Cuenta donde se acreditara : <%=cuentaAcreditacion%><p>
			   <p>Cuota fija: $<%=cuotaFija%>.-<p> 
			   <p>Cantidad de cuotas: <%=cantCuotas %><p> 
			   <p>Interes: 85% TNA</p>
			   <p>Monto total a devolver: $<%=montoDevolver %>.-<p><br><br>
			    <input type="submit" value="Aceptar" name="btnAceptarPrestamo"  class="btnAceptarPrestamo">
			    </form>
			   <%} %> 
			    <% if(request.getParameter("btnAceptarPrestamo")!=null && request.getAttribute("confirmacionPrestamo")!=null){
				   Boolean confirmacion= (Boolean)request.getAttribute("confirmacionPrestamo");
			   		if(confirmacion){
               %>
                <div class="alert alert-danger alert-dismissible fade show" style="width:auto;"role="alert">
					  	El prestamo quedo correctamente derivado para su aprobación.
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>  
				<% } %>
			      <% if(!confirmacion){
               %>
                 <div class="alert alert-success alert-dismissible fade show" style="width:auto;"role="alert">
					  	El prestamo no puedo procesarse, reintente en unos minutos.
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
			  <% } }%>
			 
		   </div>
		 </div>
	</div>
	 
</div>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>