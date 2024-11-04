# Reserva de Espacios de Baile y Clases - API

Esta API permite gestionar la reservación de espacios de baile y clases en academias, ofreciendo funcionalidades como crear usuarios, listar clases, reservar estudios y clases, y más.

## Tabla de Contenidos
- [Características](#características)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Documentación de la API](#documentación-de-la-api)
- [Pruebas](#pruebas)
- [Equipo](#equipo)

## Características
- Gestión de usuarios con roles (USER y ADMIN).
- CRUD completo para clases de baile y estudios de baile.
- Reservas de clases y estudios con disponibilidad.
- Autenticación y autorización con Spring Security.
- Documentación completa de la API con Swagger.

## Requisitos
- Java 17 o superior
- MySQL
- Maven

## Instalación

1. Clona el repositorio:
   ```bash
   git clone <URL-del-repositorio>
   ```
2. Configura la base de datos en src/main/resources/application.properties:
   ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/reserva_baile?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
    spring.datasource.username=tu_usuario_mysql
    spring.datasource.password=tu_contraseña_mysql
   ```
3. Compila y ejecuta la aplicación:
    ```bash
    ./mvnw spring-boot:run
    ```

## Uso
- La aplicación se ejecutará en http://localhost:8080.
- Accede a http://localhost:8080/swagger-ui/index.html para ver y probar la documentación de la API.

## Documentación de la API
- La documentación de la API está disponible en Swagger UI. Visita http://localhost:8080/swagger-ui/index.html para explorar los endpoints, ver sus parámetros y probar las solicitudes.

## Pruebas
- Pruebas unitarias con JUnit y Mockito.
- Pruebas de integración para verificar los endpoints REST.
- Ejecuta las pruebas con:
   ```bash
   ./mvnw test
   ```

## Equipo
- Fernando Romero
- Antonio Saravia
- Daniel Palza
- Georgiy Strashko

