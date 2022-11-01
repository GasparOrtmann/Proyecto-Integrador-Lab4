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
<h1>Tranferencias Locales</h1>
<div class="centrar-row">
	<div>
		<div>
		<label>Desde:</label>
			<select>
				<option value="Cuenta1">Cuenta 1</option>
				<option value="Cuenta2">Cuenta 2</option>
				<option value="Cuenta3">Cuenta 3</option>
			</select>
		</div>
	</div>
	<div>
		<div>
		<label>A:</label>
			<select>
				<option value="Cuenta1">Cuenta 1</option>
				<option value="Cuenta2">Cuenta 2</option>
				<option value="Cuenta3">Cuenta 3</option>
			</select>
		</div>
	</div>
	<div>
		<input type="text" name="txtMonto"> <br>
		<input type="submit" name="btnEnviar">
	</div>
</div>
</form>
</body>
</html>