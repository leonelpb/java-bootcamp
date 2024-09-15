Proyecto Spring Boot - Gestión de Prendas
=========================================

Este proyecto es una aplicación sencilla de Spring Boot que gestiona información de prendas mediante el uso de una base de datos MySQL. Se utilizan las dependencias JPA para la interacción con la base de datos y Hibernate para el manejo de entidades.

Requisitos
----------

Antes de ejecutar la aplicación, asegúrate de tener instalados los siguientes requisitos:

-   Java 17 o superior
-   Maven
-   MySQL

Instalación y Configuración
---------------------------

### 1\. Clonar el Repositorio

bash


`git clone https://github.com/tu-usuario/tu-repositorio.git
cd tu-repositorio`

### 2\. Configurar la Base de Datos

La base de datos utilizada en este proyecto se llama `ropa_db`. Debes crearla en tu servidor MySQL local antes de ejecutar la aplicación.

#### Crear la Base de Datos en MySQL

sql


`CREATE DATABASE ropa_db;`

### 3\. Configurar el archivo `application.properties`

En el archivo `src/main/resources/application.properties`, asegúrate de configurar las credenciales correctas de tu base de datos MySQL:

properties


`spring.datasource.url=jdbc:mysql://localhost:3306/ropa_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true`

### 4\. Ejecutar el Proyecto

Después de configurar la base de datos y el archivo `application.properties`, puedes ejecutar la aplicación con:

bash


`mvn spring-boot:run`

Modelo de Datos
---------------

La entidad **Prenda** tiene las siguientes características:

-   **Id**: Identificador único de la prenda (generado automáticamente).
-   **Color**: Color de la prenda.
-   **Talla**: Talla de la prenda.
-   **Precio**: Precio de la prenda.

Hibernate creará automáticamente la tabla correspondiente llamada `tbl_prenda` en la base de datos con las columnas correspondientes a los atributos del modelo.

java

`@Entity
@Table(name = "tbl_prenda")
public class Prenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    private String talla;
    private Double precio;
}`

Métodos Disponibles
-------------------

El proyecto incluye dos métodos principales, que pueden ser probados utilizando Postman u otras herramientas similares.

### 1\. **GET** Obtener todas las prendas

Puedes obtener una lista de todas las prendas registradas en la base de datos usando el método `GET`:

-   **Endpoint**: `api/v1/prendas`
-   **Método HTTP**: `GET`
-   **Respuesta**: Una lista de objetos `Prenda` en formato JSON.

#### Ejemplo de uso en Postman:

-   Método: `GET`
-   URL: `http://localhost:8080/api/v1/prendas`

### 2\. **POST** Crear una nueva prenda

Puedes agregar una nueva prenda a la base de datos usando el método `POST`:

-   **Endpoint**: `api/v1/prendas`
-   **Método HTTP**: `POST`
-   **Cuerpo de la solicitud**: JSON con los atributos de la prenda (sin el `id`, ya que este se genera automáticamente).

#### Ejemplo de uso en Postman:

-   Método: `POST`
-   URL: `http://localhost:8080/api/v1/prendas`
-   Body:

    json

    `{
        "color": "Azul",
        "talla": "M",
        "precio": 50.0
    }`

Probando con Postman
--------------------

1.  Abre Postman y crea una nueva solicitud para probar los métodos `GET` y `POST`.
2.  Configura la URL de tu servidor local (`http://localhost:8080/api/v1/prendas`).
3.  Para `GET`, simplemente envía la solicitud.
4.  Para `POST`, asegúrate de seleccionar el tipo de cuerpo como `JSON` y agregar un cuerpo similar al del ejemplo.

Tecnologías Utilizadas
----------------------

-   **Spring Boot**: Framework para la creación de aplicaciones Java basadas en microservicios.
-   **JPA**: Java Persistence API para la persistencia de datos.
-   **Hibernate**: ORM que gestiona la creación de tablas y el mapeo entre objetos y tablas de base de datos.
-   **MySQL**: Base de datos relacional.
-   **Postman**: Herramienta para pruebas de APIs.
