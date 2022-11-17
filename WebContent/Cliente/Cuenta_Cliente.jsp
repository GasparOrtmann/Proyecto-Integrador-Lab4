<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Entidades.Usuario" %>
    <%@page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mi Perfil</title>
<link href="../styles.css" rel="stylesheet" type="text/css">
</head>
<body>

<%@include file="MasterPageCliente.jsp"%>

<div style="position: absolute;top:150px;left:400px;">

	<form class="centrar-column">
	<h1>Datos de Usuario</h1>
	
	 		<% 
	 			
          		if(miSession.getAttribute("usuarioIngresado")!=null){
          			
          			Usuario usuarioIngresado =(Usuario)miSession.getAttribute("usuarioIngresado");
          			int id = (int)usuarioIngresado.getIdUsuario();
          			String nUsuario = (String) usuarioIngresado.getUsuario();
          			String mail= (String) usuarioIngresado.getEmail();
          			String nombre= (String) usuarioIngresado.getNombre();
          			String apellido= (String) usuarioIngresado.getApellido();
          			String calle= (String) usuarioIngresado.getCalle();
          			int altura= (int) usuarioIngresado.getAltura();
          			
          			String direc = calle+String.valueOf(altura);
          			%><div class="centrar-row">
          			<div>
          				<label>Nombre de Usuario: </label> <%= nUsuario 
          				%><br><label>Mail:</label> <%= mail 
          				%><br><label>Nombre: </label> <%= nombre 
          				%><br><label>Apellido: </label> <%= apellido 
          				%><br><label>Domicilio:</label> <%=direc %>
          			</div>
          		</div>
          			<% 
          			}else{
          				%>error: no existe la sesion.<%
          			}
        	%> 

	</form>
</div>
</body>
</html>