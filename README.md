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
