<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
           	<%@page import="java.io.Console"%>
	<%@page import="iNegocio.iNegocioTipoCuenta"%>
	<%@page import="Negocio.NegocioTipoCuenta"%>
		<%@page import="iNegocio.iNegocioMovimientos"%>
	<%@page import="Negocio.NegocioMovimientos"%>
	<%@page import="Entidades.TipoCuenta"%>
	<%@page import="Entidades.Cuenta"%>
	<%@page import="Entidades.Movimiento"%>
	<%@page import="java.util.List"%>
	<%@page import="java.text.SimpleDateFormat"%>
	<%@page import="java.text.ParsePosition"%>
	<%@page import="java.sql.Date"%>
	<%@page session ="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="EstilosGenerales.css" rel="stylesheet" type="text/css">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/2fcd49ae61.js" crossorigin="anonymous"></script>
<title>Transferencia A Otra Cuenta</title>
</head>
<body>

<%@include file="MasterPageCliente.jsp"%>

<div style="position: absolute;top:150px;left:400px;">
	 <div>
		<form method="post" action="/TPINT_GRUPO_6_LAB4/ServletMovimientosExternos" class="centrar-column">
			<h1>Hacer Transferencia</h1>
			<% 
			List<Cuenta> listaCu = null; 
			String usuarioAtransferir = null;
			String cbuAtransferir = null;
				if (request.getAttribute("listaCuentas") != null) {
					listaCu = (List<Cuenta>) request.getAttribute("listaCuentas");
				}
				if(request.getAttribute("TransferenciaOk")!=null)
					{
								%><div class="centrar-row">Transferencia generada con exito!</div><%
					}
				if(request.getAttribute("usuarioAtransferir")!=null)
				{
					usuarioAtransferir = (String)request.getAttribute("usuarioAtransferir");
				}
				if(request.getAttribute("cbuAtransferir")!=null)
				{
					cbuAtransferir = (String)request.getAttribute("cbuAtransferir");
				}

			%>
			<div>
				<div>
					<br>
					<label>CBU:</label> <br> <input type="text"
						name="txtBuscar"> <input type="submit" value="Buscar"
						name="btnBuscar"> <br> <br>
				</div>
			</div>
			<div>
				<div><% if(request.getAttribute("usuarioAtransferir")!=null){ %>
					<div>
						<label><%= usuarioAtransferir %></label> <br>
					</div>
					<div>
						<input type="text" name="txtCBU" value="<%= cbuAtransferir%>"><br>
					</div>
					<div>
					<%	}%>
					</div><br><br>
					<div>
						<%
				if(listaCu != null)
				{
					int i=0;
					%><select name="ddlOrigen"><%
						for(Cuenta c : listaCu){
						i++;
					%>	
					
					<option value="<%=c.getCBU()%>">CBU: <%=c.getCBU()%></option> 
				
					<%}%>
					</select>
				<%
				}%>	&nbsp&nbsp<label>Dinero disponible: $9999</label><br><br>
					</div>
					<div>
						<input type="text" name="txtMonto"> <input
							type="submit" value="Transferir" name="btnTransferir">
					</div>
				</div>
			</div>
		</form>
	</div>
</div>


	
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>	
</body>
</html>