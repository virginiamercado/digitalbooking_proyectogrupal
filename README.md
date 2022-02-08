# Digital Booking - Certified Tech Dev - Proyecto Integrador
Somos un grupo de estudiantes de la academia de Digital House, integrado por [Nadina Bagaroff](https://github.com/NadiBajaroff), [Nicole Guillamon](https://github.com/nicoleguilla), [Virginia Mercado](https://github.com/virginiamercado), [Daniela Cuellar](https://github.com/DanielaCuellar11) y [Zoe Bussola](https://github.com/ZoeBussola) y este es nuestro proyecto final del primer año de estudio donde implementamos tecnologías de Front-End, Back-End, Base de Datos, Infraestructura y Testing para recrear una página de reservas de hoteles  similar a Booking.

### **Para ver la aplicación funcionando hacer [click aquí](http://ec2-54-87-12-130.compute-1.amazonaws.com/)** 💻 
 [Presentación final](https://docs.google.com/presentation/d/1KSuy2z797Krwmyx1-TiIOBY1bU5ucUnQh1qalRqkjIs/edit?usp=sharing)

## Construido con 🛠️

### **Front-End** 
- HTML5 - Para el maquetado
- CSS3 - Implementar estilos
- Javascript Puro - Usado para las funcionalidades

Librerías utilizadas:
- Jquery
- DateRangePicker
- DatePick

### **Back-End**
- Java
- Spring - Framework utilizado
- Spring Data JPA
- MySQL -  Gestor de base de datos
### **Infraestructura**
- AWS
- Instancia de EC2 
- Servidor Apache
- Base de datos RDS
- Bucket S3

### **Testing**
- Selenium 
- Instructivo para que quien desean clonar y levantar su proyecto, sepa paso a paso cómo hacerlo
 
## Pre-Requisitos 📋
- Tener un editor de Java (Ej: IntelliJ)
- Tener instalada alguna version de jdk o jre (Nosotras usamos la 11)
- Tener un gestor de Base de Datos (MySQL, MariaDB, otros)

## Paso a Paso 🔧
_Crear una base de datos llamada “digitalbooking”_  
`create database digitalbooking;`

_Crear los usuarios “root” y “password” y/o que sean igual a los del archivo application.properties_  
`create user 'root'@'%' identified by 'password';`

_A esos usuarios darle todos los permisos_  
`grant all on digitalbooking.* to 'root'@'%'`

Para ver ejemplos de Post para la base de Datos revisar el archivo postProductos.js


## Ejecutando las pruebas ⚙️

_Se realizan pruebas unitarias en la sección testing teniendo en cuenta el CRUD de cada entidad.
Para el testing exploratorio, se tienen en cuenta caminos alternos y la perspectiva del usuario final y se realiza la actualización del excel de todos los casos de pruebas implementados al igual que los defectos detectados por sprint._

[Planilla de casos de prueba y defectos](https://docs.google.com/spreadsheets/d/1Mvng11v88YwsaWPYzH-aaFvEQvSkCUOg/edit?usp=sharing&ouid=100841053948482273357&rtpof=true&sd=true)  
[Testing Exploratorio](https://docs.google.com/presentation/d/1oqRWRZ_gTioWJPpzdYnKBPaHBc46IDk3/edit?usp=sharing&ouid=100841053948482273357&rtpof=true&sd=true)  
[Reporte de defectos](https://docs.google.com/spreadsheets/d/1_u-T4uQzetErefkicg4TgO7H_0F49i0Avy1LTRAhbGk/edit?usp=sharing)  
[Reporte final testing](https://docs.google.com/document/d/12dAi4TEHz5JZ62LZTofpxCr--D1iYmvF/edit?usp=sharing&ouid=100841053948482273357&rtpof=true&sd=true)

## [Guía de infraestructura](https://docs.google.com/document/d/1crosqjpkewYFWXeJcFF3GrDuGrLNieiCC7vIUcqNAmg/edit?usp=sharing)

## Versionado 📌
Usamos [GitLab](https://gitlab.com/proyecto-integrador-0321/camada-1/grupo-2/) para el versionado

















