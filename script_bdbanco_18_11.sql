CREATE SCHEMA bdbanco;

USE bdbanco;


CREATE TABLE Provincias(
IdProvincia int NOT NULL,
Provincia varchar(60),
CONSTRAINT PK_Provincias PRIMARY KEY (IdProvincia)
);

CREATE TABLE Localidades(
IdLocalidad int NOT NULL,
Localidad varchar(60),
IdProvincia int NOT NULL,
CONSTRAINT PK_Localidades PRIMARY KEY (IdLocalidad,IdProvincia),
CONSTRAINT FK_Localidades_Provincias FOREIGN KEY (IdProvincia) REFERENCES Provincias (IdProvincia)
);

CREATE TABLE Nacionalidades(
IdNacionalidad int NOT NULL,
Nacionalidad varchar(60),
CONSTRAINT PK_Nacionalidades PRIMARY KEY (IdNacionalidad)
);

CREATE TABLE Tipos_Movimiento(
IdTipoMovimiento int NOT NULL,
TipoMovimiento varchar(60),
CONSTRAINT PK_Tipos_Movimiento PRIMARY KEY (IdTipoMovimiento)
);

CREATE TABLE Tipos_Cuenta(
IdTipoCuenta int NOT NULL,
TipoCuenta varchar(60),
CONSTRAINT PK_Tipos_Cuenta PRIMARY KEY (IdTipoCuenta)
);

CREATE TABLE Usuarios(
IdUsuario int NOT NULL,
EsAdmin bit,
Usuario varchar(60),
Contrasenia varchar(8),
Nombre varchar(60),
Apellido varchar(60),
Sexo   varchar(30),
FechaNacimiento varchar(10),
Calle varchar(60),
Altura int,
Email varchar(60),
NroCuil varchar(12),
NroDni varchar(8),
Estado bit,
CantidadCuentas int,
IdNacionalidad int NOT NULL,
IdLocalidad int NOT NULL,
CONSTRAINT PK_Usuarios PRIMARY KEY (IdUsuario),
CONSTRAINT FK_Usuarios_Nacionalidades FOREIGN KEY (IdNacionalidad) REFERENCES Nacionalidades (IdNacionalidad),
CONSTRAINT FK_Usuarios_Localidades FOREIGN KEY (IdLocalidad) REFERENCES Localidades (IdLocalidad)
);

CREATE TABLE Telefonos(
NroTelefono varchar(18) NOT NULL,
IdUsuario int NOT NULL,
Estado bit,
CONSTRAINT PK_Telefonos PRIMARY KEY (NroTelefono,IdUsuario),
CONSTRAINT FK_Telefonos_Usuarios FOREIGN KEY (IdUsuario) REFERENCES Usuarios (IdUsuario)
);

CREATE TABLE Cuentas(
IdCuenta int NOT NULL,
IdUsuario int NOT NULL,
IdTipoCuenta int NOT NULL,
CBU varchar(20),
Saldo float,
FechaAlta varchar(10),
Estado bit,
CONSTRAINT PK_Cuentas PRIMARY KEY (IdCuenta),
CONSTRAINT FK_Cuentas_Usuarios FOREIGN KEY (IdUsuario) REFERENCES Usuarios (IdUsuario),
CONSTRAINT FK_Cuentas_Tipos_Cuenta FOREIGN KEY (IdTipoCuenta) REFERENCES Tipos_Cuenta (IdTipoCuenta)
);

CREATE TABLE Prestamos(
IdPrestamo int NOT NULL,
IdUsuario int NOT NULL,
MontoPrestamo float,
MontoTotalAdeudado float,
ImporteCuotaFija float,
CantidadCuotas int,
CuotasAdeudadas int,
CuotasPagas int,
FechaAlta varchar(10),
Estado varchar(30),
CONSTRAINT PK_Prestamos PRIMARY KEY (IdPrestamo),
CONSTRAINT FK_Prestamos_Usuarios FOREIGN KEY (IdUsuario) REFERENCES Usuarios (IdUsuario)
);

CREATE TABLE Movimientos(
IdMovimiento int NOT NULL,
IdTipoMovimiento int NOT NULL,
IdCuenta int NOT NULL,
Fecha varchar(10),
Importe float,
Detalle varchar(60),
CONSTRAINT PK_Movimientos PRIMARY KEY (IdMovimiento),
CONSTRAINT FK_Movimientos_Tipos_Movimiento FOREIGN KEY (IdTipoMovimiento) REFERENCES Tipos_Movimiento (IdTipoMovimiento),
CONSTRAINT FK_Movimientos_Cuentas FOREIGN KEY (IdCuenta) REFERENCES Cuentas (IdCuenta)
);

USE `bdbanco`;
DROP procedure IF EXISTS `SP_agregarcuenta`;

DELIMITER $$
USE `bdbanco`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_agregarcuenta`(IN sIdC int, IN sIdU int, IN sIdT int, IN sCBU varchar(20), IN sSaldo float, IN sFechaAlta varchar(10), IN sEstado bit)
BEGIN 
	INSERT INTO Cuentas (IdCuenta,IdUsuario,IdTipoCuenta,CBU,Saldo,FechaAlta,Estado) values (sIdc,sIdU,sIdT,sCBU,sSaldo,sFechaAlta,sEstado);
    END$$

DELIMITER ;

 USE `bdbanco`;
	DROP procedure IF EXISTS `SP_agregarPrestamo`;
	
	DELIMITER $$
	USE `bdbanco`$$
	CREATE PROCEDURE `SP_agregarPrestamo` (IN pId int, IN pIdUsu int, IN monto float,IN cuotaValor float, IN cantCuota int, IN fechaAlta varchar(10))
	BEGIN
	INSERT INTO Prestamos (IdPrestamo,IdUsuario,MontoPrestamo,MontoTotalAdeudado,ImporteCuotaFija,CantidadCuotas,CuotasAdeudadas,CuotasPagas,FechaAlta,Estado) 
					values (pId,pIdUsu,monto,null,cuotaValor,cantCuota,null,null,fechaAlta,'Pendiente');
	END$$
	
	DELIMITER ;

INSERT INTO nacionalidades (IdNacionalidad, Nacionalidad)
SELECT 1, 'Argentina' UNION
SELECT 2, 'Brasilera' UNION
SELECT 3, 'Uruguaya';

INSERT INTO provincias (IdProvincia, Provincia)
SELECT 1, 'Buenos Aires' UNION
SELECT 2, 'Santa Fe' UNION
SELECT 3, 'Corrientes' UNION
SELECT 4, 'Cordoba' UNION
SELECT 5, 'La Rioja' UNION
SELECT 6, 'Entre Rios';

INSERT INTO localidades (IdLocalidad, IdProvincia, Localidad)
SELECT 1,1,'Pacheco' UNION
SELECT 2,1,'Pilar' UNION
SELECT 3,1,'Tigre' UNION
SELECT 4,1,'San Isidro' UNION
SELECT 5,1,'Martinez';

INSERT INTO usuarios (IdUsuario, EsAdmin, Usuario, Contrasenia, Nombre, Apellido, Sexo, FechaNacimiento, Calle, Altura, Email, NroCuil, NroDni, Estado, CantidadCuentas, IdNacionalidad, IdLocalidad)
SELECT 1, 1, 'admin', 'admin', 'admin','admin', 'admin', '14/11/2022', 'admin', 1000, 'admin@gmail.com', '20439131080', '43913108', 1,1,1,1 UNION
SELECT 2, 0, 'gaspar', '12345', 'Gaspar','Ortmann', 'Masculino', '11/12/2001', 'Chubut', 1262, 'gasparortmann@gmail.com', '20430000000', '43000000', 1,1,1,2 UNION
SELECT 3, 0, 'lautaro', '12345', 'Lautaro','Roa', 'Masculino', '14/11/2022', 'Calle', 1000, 'lautaro@gmail.com', '20432222220', '43222222', 1,1,1,1 UNION
SELECT 4, 0, 'malena', '12345', 'Malena','Constancio', 'Femenino', '14/11/2022', 'Calle', 1000, 'malena@gmail.com', '20433333330', '43333333', 1,1,1,1 UNION
SELECT 5, 0, 'santi', '12345', 'Santiago','Britos', 'Masculino', '14/11/2022', 'Calle', 1000, 'santiago@gmail.com', '20434444440', '43444444', 1,1,1,1 UNION
SELECT 6, 0, 'agus', '12345', 'Agustin','Rojas', 'Masculino', '14/11/2022', 'Calle', 1000, 'agustin@gmail.com', '20435555550', '43555555', 1,1,1,1;

INSERT INTO tipos_cuenta (IdTipoCuenta, TipoCuenta)
SELECT 1, 'Cuenta Corriente' UNION
SELECT 2, 'Caja de Ahorro';

INSERT INTO cuentas (IdCuenta, IdUsuario, IdTipoCuenta, CBU, Saldo, FechaAlta, Estado)
SELECT 1,2,1,'83609888111000445766', 20000, '14/11/2022', 1 UNION
SELECT 2,3,1,'53406442111000416747', 30000, '14/11/2022', 1 UNION
SELECT 3,4,1,'17435149111000329362', 40000, '14/11/2022', 1 UNION
SELECT 4,5,1,'45680069111000732191', 25000, '14/11/2022', 1 UNION
SELECT 5,6,1,'64929389111000507162', 10000, '14/11/2022', 1;



INSERT INTO tipos_movimiento (IdTipoMovimiento, TipoMovimiento)
SELECT 1, 'Alta de cuenta' UNION
SELECT 2, 'Alta de préstamo' UNION
SELECT 3, 'Pago de préstamo' UNION
SELECT 4, 'Transferencia';