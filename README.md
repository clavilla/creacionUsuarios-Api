# user-Api

![Descripción alternativa](/src/main/resources/static/Diagrama_user-Api.jpg)

- Controlador (AuthenticationController): Este es el punto de entrada de la aplicación. Recibe las solicitudes HTTP, las maneja y devuelve las respuestas HTTP.  
- Servicio (AuthenticationService): Esta es la capa de lógica de negocio de la aplicación. Realiza las operaciones necesarias para manejar las solicitudes del controlador.  
- Repositorio (UserRepository): Esta es la capa de acceso a datos de la aplicación. Se comunica con la base de datos y realiza operaciones de crear usuario y buscar usuario por email en ella.  
- Modelo (UserRequestDto, UserResponseDto): Estos son los objetos de transferencia de datos que se utilizan para enviar y recibir datos desde y hacia el controlador.  
- Utilidades (ServiceUtils, JwtUtils): Estas son clases de utilidad que proporcionan funciones comunes que se utilizan en toda la aplicación, como la validación de correo electrónico y contraseña, y la generación de tokens JWT.  
- Base de datos (H2 Database): Esta es la base de datos en memoria que se utiliza para almacenar los datos de los usuarios.

## Creación de la base de datos y tablas
Se utiliza Flywaydb para la creación automática de la base de datos y las tablas al iniciar la aplicación. 
Los scripts de Flywaydb se encuentran en la carpeta `src/main/resources/db/migration`.
- V1__create_user_table.sql: Este script crea la tabla de usuario.
- V2__create_phone_table.sql: Este script crea la tabla de teléfonos.

## Indicaciones para ejecutar la aplicación con Postman
- Levantar la aplicación con el comando `mvn spring-boot:run`.
- Desde Postman enviar un POST a `http://localhost:8080/api/v1/auth/sign-up` con el siguiente body
```json
{
  "name": "Juan Rodriguez",
  "email": "juanrodriguez@gmail.com",
  "password": "Hunter@2",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
```
- Si se envía el request con un password que no cumple con las reglas de validación se obtendra este mensaje de error
```json
{
    "message": "Password does not meet the requirements"
}
```
- Si se envía el request con un email que no cumpla con las reglas de validación se obtendra este mensaje de error
```json
{
    "message": "Email does not meet the requirements"
}
```
- Si se envía el request con un email que ya existe en la base de datos se obtendra este mensaje de error
```json
{
    "message": "Email already exists"
}
```

## Indicaciones para ejecutar la aplicación con Swaggwer-UI
- Levantar la aplicación con el comando `mvn spring-boot:run`.
- Acceder a la URL `http://localhost:8080/api/v1/doc/swagger-ui/index.html` para ver la documentación de la API en Swagger-UI.
- En Swagger-UI se vera el endpoint disponible, los parámetros que aceptan y los códigos de respuesta que devuelven.
- El Json a enviar en el body del request se puede copiar desde la documentación de Swagger-UI.

## Requisitos de contraseña válida
- La contraseña debe tener al menos 8 caracteres.
- La contraseña debe tener al menos una letra mayúscula.
- La contraseña debe tener al menos una letra minúscula.
- La contraseña debe tener al menos un número.
- La contraseña debe contener al menos uno de estos caracteres especiales: ! @ # $ % ^ & * .
- La contraseña no debe contener espacios en blanco.
- La contraseña debe tener como mínimo 8 caracteres

## Detener la aplicación
Detener la aplicación con el comando `Ctrl + C` en la terminal donde se ejecutó el comando `mvn spring-boot:run`.