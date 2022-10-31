<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="../styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<form class="centrar-column">
	<h1>Hacer Transferencia</h1>
	<div>
		<div>
			<br><label>Buscá CBU</label> <br> <input type="text" name="txtBuscarCBU"> 
			<input type="submit" value="Buscar" name="btnBuscarCBU">
			<br> <br>
		</div>
	</div>
	<div>
		<div>
			<div><label>Usuario Prueba</label> <br> </div>
			<div><label>42.123.123</label> <br> </div>
			<div><label>20-43.123.123-3</label> <br> <br> </div>
			<div><label>Dinero disponible: $9999</label></div>
			<div><input type="text" name="txtSaldoTransferir"> 
			<input type="submit" value="Enviar" name="btnEnviarTransferencia"> </div>
		</div>
	</div>
</form>
</body>
</html>