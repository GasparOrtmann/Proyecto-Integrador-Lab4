<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="EstilosGenerales.css" rel="stylesheet" type="text/css">
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
		  <div class="col-6">
		   <label>Ingrese el Monto a solicitar:</label> <input type="text" name="txtMontoPrestamo"> <br><br>
		   <label>Seleccione la cantidad de Cuotas</label> <select name=ddlCuotas>
								<option value="1">12</option>
								<option value="2">24</option>
								<option value="3">48</option>
								<option value="4">36</option>
								</select><br><br>
		   <label>Cuenta donde se acreditara el Prestamo</label>  <select name=ddlCuenta>
								<option value="1">Caja de Ahorro CBU 123654789</option>
								<option value="2">Caja de Ahorro CBU 456987123</option>
								<option value="3">Caja de Ahorro CBU 112223336</option>
								</select><br><br><br>
		   <input type="submit" value="Simular" name="btnSimularPrestamo"  class="btnSimularPrestamo">
		   </div>
		   <div class="col-6" style="color:white; background-color:#B4B1C4;width:600px; height:206px;border-radius:5px;">
		   <br>
			   <p>Monto solicitado $150000<p>
			   <p>Cuota fija: $10596<p>
			   <p>Cantidad de cuotas: 48<p>
			   <p>Interes: 45% anual</p>
			   <p>Monto total a devolver: $270000<p>
			    <input type="submit" value="Aceptar" name="btnAceptarPrestamo"  class="btnAceptarPrestamo">
		   </div>
		 </div>
	</div>
</div>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>