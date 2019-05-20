-- Ejecuta este script para crear la bd

CREATE TABLE planeta (
    nombre VARCHAR(15) PRIMARY KEY,
    radio DOUBLE
);
INSERT INTO planeta(nombre, radio) VALUES('Mercurio' , 2439);
INSERT INTO planeta(nombre, radio) VALUES('Venus',6051);
INSERT INTO planeta(nombre, radio) VALUES('Tierra',6371);
INSERT INTO planeta(nombre, radio) VALUES('Marte', 3389);
INSERT INTO planeta(nombre, radio) VALUES('Jupiter', 69911);
INSERT INTO planeta(nombre, radio) VALUES('Saturno',58232);
INSERT INTO planeta(nombre, radio) VALUES('Urano', 25362);
INSERT INTO planeta(nombre, radio) VALUES('Neptuno', 24622);

