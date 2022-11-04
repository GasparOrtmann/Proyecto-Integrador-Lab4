<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<div class="centrar-row">
		<div>
			<label>Nombre de Usuario: usuarioPrueba</label> <br>
			<label>Mail: usuarioPrueba@gmail.com</label> <br>
			<label>Nombre: Usuario</label> <br>
			<label>Apellido: Prueba</label> <br>
			<label>Domicilio: Calle 1234</label> <br>
		</div>
	</div>
	</form>
</div>
</body>
</html>