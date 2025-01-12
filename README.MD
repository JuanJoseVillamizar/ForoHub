
- [English](#english)
- [Español](#español)


## Español
# 🌐 Foro Hub - Desafio Backend

## Índice :
- [Descripción](#descripción)
- [Características](#características)
- [Diagrama de la Base de Datos](#diagrama-de-la-Base-de-Datos)
- [Instalación](#instalación)
- [Inicialización Automática de Datos](#inicialización-Automática-de-Datos)
- [Endpoints Principales](#endpoints-Principales)
- [Seguridad](#seguridad)
- [Estructura del Proyecto](#estructura-del-Proyecto)

## 📜Descripción
_Foro Hub_ es una aplicación backend diseñada para gestionar un foro interactivo donde los usuarios pueden crear, 
visualizar, y moderar temas y respuestas segun su curso categoria o subcategoria. El proyecto incluye permisos 
avanzados, roles predefinidos, y un sistema de inicialización automática de datos.

## ✨Características
- Gestión de usuarios, roles y permisos (Solo administradores y desarrolladores).
- Inicialización automática de datos con permisos, perfiles y usuarios básicos.
- CRUD de temas, respuestas, categorías ,subcategorías cursos.
- API REST construida con **Spring Boot**.
- Diagrama de base de datos visualizable.
- Seguridad y autenticación.
- Acceso restringido a ciertas funcionalidades según el rol y persmisos de usuario.
- Migración de base de datos".

## 📊Diagrama de la Base de Datos
![imagen](https://raw.githubusercontent.com/JuanJoseVillamizar/ForoHub/refs/heads/main/src/main/resources/img/db_foro.PNG)


## 🛠️Instalación
### Requisitos Previos
- Java 17 o superior instalado.
- Maven instalado.
- PostgreSQL configurado y en ejecución.

1. Crea una base de datos y comunicate con ella por las variables de entorno o modifica el properties.

2. Clona este repositorio:
   ```bash
   git clone https://github.com/JuanJoseVillamizar/ForoHub.git
   cd foro-hub
   ```
3. Configura las variables de entorno o el archivo `application.properties`:
   ```properties
   spring.datasource.url= jdbc:postgresql://{DB_HOST}/{DB_NAME_FORO}
   spring.datasource.username={DB_USER}
   spring.datasource.password={DB_PASSWORD}
   security.jwt.key.private=${PRIVATE_KEY}
   security.jwt.user.generator=${USER_GENERATOR}
   security.authjwt.backend=${YOUR AUTHORIZATHION}
   ```
4. Ejecuta el proyecto:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Accede a la aplicación en `http://localhost:8080`.

## 🔑Inicialización Automática de Datos
Al iniciar la aplicación por primera vez, se crean automáticamente:
- **Permisos**: Ejemplo: `CREATE_TOPIC`, `DELETE_USER`, etc.
- **Roles**: `DEVELOPER`, `ADMIN`, `MODERATOR`, `USER`.
- **Usuarios predeterminados**:
    - **Email**: `superAdmin@gmail.com` | **Contraseña**: `superAdmin1234`
    - **Email**: `adminForum@gmail.com` | **Contraseña**: `admin1234`

## 🚀Endpoints Principales
| Método | Endpoint                   | Descripción                              | Parámetros / Body       |
|--------|----------------------------|------------------------------------------|-------------------------|
| GET    | `/api/topics`              | Obtener todos los temas                  | -                       |
| GET    | `/api/courses/{id}/topics` | Obtener temas de un curso específico     | `{id}`: ID del curso    |
| POST   | `/api/topics`              | Crear un nuevo tema                      | `{title, content, ...}` |
| POST   | `/api/auth/signUp`         | Crear un nuevo usuario                   | `{email, password, ...}`|
| POST   | `/api/auth/login`          | Autenticar un usuario                    | `{email, password}`     |

### Documentación Interactiva
Puedes explorar y probar los endpoints usando la documentación generada por Swagger. Una vez tengas el projecto corriendo en tu maquina Accede a:
[]()

## 🛡️Seguridad
El backend implementa autenticación y autorización con:
- **Spring Security**: Para la app en general y la autenticación.
- **JWT Tokens**: Para proteger los endpoints.
- **BCrypt**: Para el cifrado de contraseñas.

## 📂Estructura del Proyecto
```plaintext
src/
├── main/
│   ├── java/
│   │   ├── juan/forohub/
│   │   │   ├── config/         # Configuración general
│   │   │   ├── controllers/    # Controladores REST
│   │   │   ├── entities/       # Entidades de la base de datos
│   │   │   ├── repository/     # Repositorios de JPA
│   │   │   └── services/       # Lógica de negocio
│   └── resources/
│       ├── application.properties
```
## ⚙️Tecnologías Usadas
### Backend
- **Java 17**: Lenguaje principal.
- **Spring Boot 3**: Framework para desarrollo rápido.
- **Spring Security**: Manejo de autenticación y autorización.
- **Spring Data JPA**: Persistencia de datos.
- **Spring Validation**: Validaciones.
- **Spring test**: Pruebas unitarias y pruebas de integración.

### Bases de Datos
- **PostgreSQL**: Base de datos relacional.
- **Flyway**: Migración y versionamiento de bases de datos.

### Herramientas y Utilidades
- **Swagger**: Documentación interactiva de la API.
- **Lombok**: Simplificación del código Java.
- **Maven**: Gestión de dependencias.
- **SonarQube**: Análisis de la calidad del código.

## 📝 Licencia
Este proyecto está licenciado bajo la licencia [MIT](LICENSE). Puedes usarlo, modificarlo y distribuirlo libremente, siempre y cuando proporciones el crédito correspondiente.



## English
# 🌐 Foro Hub - Backend Challenge

## Index:
- [Description](#description)
- [Features](#features)
- [Database Diagram](#database-diagram)
- [Installation](#installation)
- [Automatic Data Initialization](#automatic-data-initialization)
- [Main Endpoints](#main-endpoints)
- [Security](#security)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [License](#license)

---

## 📜Description
_Foro Hub_ is a backend application designed to manage an interactive forum where users can create, view, and moderate topics and replies based on courses, categories, or subcategories. The project includes advanced permissions, predefined roles, and a system for automatic data initialization.

---

## ✨Features
- Management of users, roles, and permissions (restricted to administrators and developers).
- Automatic data initialization with basic permissions, profiles, and users.
- CRUD for topics, replies, categories, subcategories, and courses.
- REST API built with **Spring Boot**.
- Visualizable database schema.
- Authentication and security.
- Restricted access to certain features based on user roles and permissions.
- Database migrations.

---

## 📊Database Diagram
![Database Schema](https://raw.githubusercontent.com/JuanJoseVillamizar/ForoHub/refs/heads/main/src/main/resources/img/db_foro.PNG)

---

## 🛠️Installation
### Prerequisites
- Java 17 or higher installed.
- Maven installed.
- PostgreSQL configured and running.

### Steps:
1. Create a database and connect to it using environment variables or by modifying the `application.properties` file.

2. Clone this repository:
   ```bash
   git clone https://github.com/JuanJoseVillamizar/ForoHub.git
   cd foro-hub
   ```

3. Configure your environment variables or update `application.properties`:
   ```properties
   spring.datasource.url= jdbc:postgresql://{DB_HOST}/{DB_NAME_FORO}
   spring.datasource.username={DB_USER}
   spring.datasource.password={DB_PASSWORD}
   security.jwt.key.private=${PRIVATE_KEY}
   security.jwt.user.generator=${USER_GENERATOR}
   security.authjwt.backend=${YOUR_AUTHORIZATION}
   ```

4. Run the project:
   ```bash
   ./mvnw spring-boot:run
   ```

5. Access the application at `http://localhost:8080`.

---

## 🔑Automatic Data Initialization
Upon first launch, the application automatically creates:
- **Permissions**: Examples: `CREATE_TOPIC`, `DELETE_USER`, etc.
- **Roles**: `DEVELOPER`, `ADMIN`, `MODERATOR`, `USER`.
- **Default Users**:
    - **Email**: `superAdmin@gmail.com` | **Password**: `superAdmin1234`
    - **Email**: `adminForum@gmail.com` | **Password**: `admin1234`

---

## 🚀Main Endpoints
| Method | Endpoint                   | Description                              | Parameters / Body       |
|--------|----------------------------|------------------------------------------|-------------------------|
| GET    | `/api/topics`              | Retrieve all topics                      | -                       |
| GET    | `/api/courses/{id}/topics` | Retrieve topics of a specific course     | `{id}`: Course ID       |
| POST   | `/api/topics`              | Create a new topic                       | `{title, content, ...}` |
| POST   | `/api/auth/signUp`         | Create a new user                        | `{email, password, ...}`|
| POST   | `/api/auth/login`          | Authenticate a user                      | `{email, password}`     |

### Interactive Documentation
You can explore and test the API endpoints using the Swagger-generated documentation. Once the project is running, access it at:  
[Swagger URL]() (Insert the actual URL here)

---

## 🛡️Security
The backend implements authentication and authorization using:
- **Spring Security**: General app security and authentication.
- **JWT Tokens**: To protect API endpoints.
- **BCrypt**: For password encryption.

---

## 📂Project Structure
```plaintext
src/
├── main/
│   ├── java/
│   │   ├── juan/forohub/
│   │   │   ├── config/         # General configuration
│   │   │   ├── controllers/    # REST Controllers
│   │   │   ├── entities/       # Database entities
│   │   │   ├── repository/     # JPA Repositories
│   │   │   └── services/       # Business logic
│   └── resources/
│       ├── application.properties
```

---

## ⚙️Technologies Used
### Backend
- **Java 17**: Main programming language.
- **Spring Boot 3**: Framework for rapid backend development.
- **Spring Security**: Handles authentication and authorization.
- **Spring Data JPA**: Data persistence.
- **Spring Validation**: Input validations.
- - **Spring test**: Test.

### Database
- **PostgreSQL**: Relational database.
- **Flyway**: Database migration and versioning.

### Tools and Utilities
- **Swagger**: Interactive API documentation.
- **Lombok**: Simplifies Java code.
- **Maven**: Dependency management.
- **SonarQube**: Code quality analysis.

---

## 📝License
This project is licensed under the [MIT License](LICENSE). You are free to use, modify, and distribute this project as long as proper credit is given.
