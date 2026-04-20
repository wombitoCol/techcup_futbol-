# TECHCUP Fútbol ⚽

TECHCUP Fútbol es una plataforma web desarrollada para gestionar el torneo semestral de fútbol de los programas de Ingeniería de Sistemas, Ingeniería de Inteligencia Artificial, Ingeniería de Ciberseguridad e Ingeniería Estadística de la Escuela Colombiana de Ingeniería.

Actualmente, la organización del torneo se realiza mediante herramientas dispersas como formularios, hojas de cálculo y mensajería, lo que puede generar desorden, retrasos y falta de claridad en la información.  

Este proyecto busca centralizar la gestión del torneo en una única plataforma digital que permita organizar de manera eficiente a los jugadores, equipos y partidos.

---

## Objetivo del Proyecto

Diseñar e implementar una aplicación web que permita administrar de forma organizada y transparente todas las actividades relacionadas con el torneo de fútbol universitario.

---

## Funcionalidades Principales

- Registro de jugadores y creación de perfiles deportivos
- Creación y administración de equipos
- Búsqueda de jugadores por posición y características
- Registro y validación de pagos mediante comprobantes
- Gestión de partidos y registro de resultados
- Consulta de tabla de posiciones
- Generación automática de llaves eliminatorias
- Visualización de estadísticas del torneo

---

## Tecnologías Utilizadas

**Frontend**

- Figma:
https://pixso.net/app/design/eY3DOlkD23U2UYJmHkOs6A?showQuickFrame=true&new=1&icon_type=1&page-id=0%3A1 

**Backend**
- Spring Boot
- Java

**Gestión del Proyecto**
- Scrum
- Jira

**Control de Versiones**
- Git / GitHub

**Díagrama de contexto:**
<img width="969" height="559" alt="image" src="https://github.com/user-attachments/assets/d2a74db8-dc8d-4d4a-a7c7-1a7c3c3d0ad8" />
**Logs**
<img width="1713" height="413" alt="image" src="https://github.com/user-attachments/assets/684ece43-1f30-4a33-88cf-c5b2fd76d67b" />
**Prueba de autenticación JWT - Login exitoso (POST /api/auth/login)**
<img width="1571" height="898" alt="image" src="https://github.com/user-attachments/assets/5ad1d703-9d4a-4795-ac68-1037c5cdda8d" />
**Prueba de autenticación JWT - Login exitoso (GET /api/users/students)**
<img width="1614" height="1001" alt="image" src="https://github.com/user-attachments/assets/919c4d54-59a0-46f8-b584-69785ce447ba" />
**Prueba de autenticación JWT - Login fallido (POST /api/auth/login)**
<img width="1920" height="978" alt="image" src="https://github.com/user-attachments/assets/8f7d6192-7e90-49b0-857b-3c5b4ec2c377" />
**Prueba de autenticación JWT - Acceso sin token (GET /api/users)**
<img width="1920" height="919" alt="image" src="https://github.com/user-attachments/assets/cccca8aa-8ce0-4f71-84be-e17157bf399a" />


<img width="1912" height="978" alt="image" src="https://github.com/user-attachments/assets/8f4e7041-f93e-44a7-adb0-b9fda5df0c33" />


**Documentación API - Swagger**
<img width="1917" height="861" alt="image" src="https://github.com/user-attachments/assets/22152ae6-732e-4570-a051-190619f8d96c" />
<img width="1919" height="903" alt="image" src="https://github.com/user-attachments/assets/226cb66c-6895-40c9-a020-1514ed398ac6" />
<img width="1920" height="872" alt="image" src="https://github.com/user-attachments/assets/6a293770-5fa0-4c11-b560-87bb84ab4504" />




---

## Estructura del Proyecto

El sistema sigue una arquitectura por capas en el backend:


## Respuestas a preguntas 

Controller: Sirve para exponer los endpoints (API REST). Recibe las peticiones HTTP del cliente, delega la lógica al Service y retorna la respuesta HTTP adecuada.

Service: Contiene toda la lógica de negocio y las reglas de la aplicación. Actúa como puente entre el Controller y el Repository.

Repository: Se encarga del acceso a los datos (base de datos). Utiliza JPA/Hibernate para realizar operaciones CRUD sobre las entidades.(Pregunta repetida en el doc) 

Entity: Representa las tablas de la base de datos. Son objetos mapeados con JPA (ORM) que reflejan el modelo de datos.

DTO (Data Transfer Object): Son objetos planos usados para transferir datos entre el cliente y el servidor, ocultando detalles internos de las entidades y previniendo vulnerabilidades.

Exception: Contiene clases personalizadas para el manejo de errores y un @ControllerAdvice para capturar excepciones globalmente y devolver mensajes de error formateados al cliente.
