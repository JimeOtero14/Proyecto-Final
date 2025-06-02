DROP DATABASE IF EXISTS itcelaya;
CREATE DATABASE itcelaya;
USE itcelaya;

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(50) NOT NULL,
    contrasena VARCHAR(10) NOT NULL
);

CREATE TABLE califParcial (
    id_parcial INT(1) PRIMARY KEY,
    calificacion INT(3) NOT NULL
);


CREATE TABLE carrera (
    id_carrera INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE maestro (
    cveMaestro VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    primer_apellido VARCHAR(50) NOT NULL,
    segundo_apellido VARCHAR(50),
    id_carrera INT NOT NULL,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_carrera) REFERENCES carrera(id_carrera)
);

CREATE TABLE materia (
    id_materia INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    oportunidad VARCHAR(10) NOT NULL,
    id_carrera INT NOT NULL,
    FOREIGN KEY (id_carrera) REFERENCES carrera(id_carrera)
);

CREATE TABLE alumno (
    noControl INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    primer_apellido VARCHAR(50) NOT NULL,
    segundo_apellido VARCHAR(50),
    genero VARCHAR(10) NOT NULL,
    fecha_nacimiento DATE,
    id_usuario INT NOT NULL,
    id_carrera INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_carrera) REFERENCES carrera(id_carrera)
);

CREATE TABLE grupo (
    id_grupo INT AUTO_INCREMENT,
    salon VARCHAR(10) NOT NULL,
    horario VARCHAR(10) NOT NULL,
    cveMaestro VARCHAR(10) NOT NULL,
    id_materia INT NOT NULL,
    PRIMARY KEY (id_grupo, cveMaestro, id_materia),
    FOREIGN KEY (cveMaestro) REFERENCES maestro(cveMaestro),
    FOREIGN KEY (id_materia) REFERENCES materia(id_materia)
);

CREATE TABLE kardex (
    noControl INT NOT NULL,
    id_grupo INT NOT NULL,
    cveMaestro VARCHAR(10) NOT NULL,
    id_materia INT NOT NULL,
    califCardex INT NOT NULL,
    PRIMARY KEY (noControl, id_grupo, cveMaestro, id_materia),
    FOREIGN KEY (noControl) REFERENCES alumno(noControl),
    FOREIGN KEY (id_grupo, cveMaestro, id_materia)
        REFERENCES grupo(id_grupo, cveMaestro, id_materia)
);

CREATE TABLE inscrito (
    noControl INT NOT NULL,
    id_grupo INT NOT NULL,
    cveMaestro VARCHAR(10) NOT NULL,
    id_materia INT NOT NULL,
    id_parcial INT NOT NULL,
    calificacion INT(3) NOT NULL,
    PRIMARY KEY (noControl, id_grupo, cveMaestro, id_materia, id_parcial),
    FOREIGN KEY (noControl) REFERENCES alumno(noControl),
    FOREIGN KEY (id_grupo, cveMaestro, id_materia)
        REFERENCES grupo(id_grupo, cveMaestro, id_materia)
);

