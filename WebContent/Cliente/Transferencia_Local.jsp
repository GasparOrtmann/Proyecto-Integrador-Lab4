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
<title>Insert title here</title>
<link href="../styles.css" rel="stylesheet" type="text/css">
</head>
<body>

<%@include file="MasterPageCliente.jsp"%>

<div style="position: absolute;top:150px;left:400px;">
<form method="post" action="/TPINT_GRUPO_6_LAB4/ServletMovimientos" class="centrar-column">
<%List<Cuenta> listaCu = null; 

%><h1>Tranferencias Locales</h1><% 
if (request.getAttribute("listaCuentas") != null) {
	listaCu = (List<Cuenta>) request.getAttribute("listaCuentas");
}
if(request.getAttribute("TransferenciaOk")!=null)
	{
		%><div class="centrar-row">Transferencia generada con exito!</div><%
	}

%>
<div class="centrar-row">
	<div>
		<div>
		<label>Desde:</label>
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
				}%>
				
		</div>
	</div>
	<br>
	<div>
		<div>
		<label>A:</label>
				<% if(listaCu != null)
				{
					int i=0;
					%><select name="ddlDestino"><%
						for(Cuenta c : listaCu){
						i++;
					%>	
					
					<option value="<%=c.getCBU()%>">CBU: <%=c.getCBU()%></option>
					<%}
					
					%>
					</select>
				<%
				}%>
		</div>
	</div>
	<br>
	<div>
		<input type="text" name="txtMonto">
		<br><br>
		<input type="submit" name="btnTransferir" value="Transferir">
	</div>
</div>
</form>
</div>
	



</body>
</html>