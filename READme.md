# 🚀 MentorHub API

Backend profesional desarrollado con **Spring Boot** para la gestión de mentorías técnicas entre mentores y estudiantes.

Este proyecto demuestra:

* **Arquitectura por capas**
* **Seguridad con JWT**
* **Control de roles** (ADMIN, MENTOR, STUDENT)
* **Relaciones JPA** bien modeladas
* **Manejo global de errores**
* **Documentación con Swagger**
* **Buenas prácticas backend**

---

# 🧠 Descripción General

MentorHub es una API REST que permite:

* Registro y autenticación de usuarios.
* Gestión de solicitudes de mentoría.
* Aprobación / rechazo por parte del mentor.
* Visualización filtrada por rol.
* Seguridad basada en JWT.

---

# 🏗️ Arquitectura del Proyecto

El proyecto sigue una arquitectura por capas limpia:

```text
controller/
service/
service/impl/
repository/
entity/
dto/
security/
exception/
config/

```

### Principios aplicados:

* **Separación de responsabilidades:** Lógica de negocio aislada.
* **Controllers delgados:** Sin lógica pesada.
* **DTOs:** Para no exponer entidades directamente al cliente.
* **Manejo global de excepciones:** Respuestas de error estandarizadas.
* **Seguridad desacoplada:** Filtro JWT independiente.

---

# 🔐 Seguridad

Implementada con:

* Spring Security
* JWT (Bearer Token)
* Filtro personalizado (`JwtFilter`)
* Control de roles con `@PreAuthorize` o `hasRole`

**Roles disponibles:** `ADMIN`, `MENTOR`, `STUDENT`.

### Protección de endpoints

| Endpoint | Método | Rol requerido |
| --- | --- | --- |
| `/api/auth/login` | POST | Público |
| `/api/users` | POST | Público |
| `/api/users` | GET | ADMIN |
| `/api/mentorships` | POST | STUDENT |
| `/api/mentorships` | GET | ADMIN, MENTOR, STUDENT |
| `/api/mentorships/{id}/approve` | PATCH | MENTOR |
| `/api/mentorships/{id}/reject` | PATCH | MENTOR |

---

# 🗄️ Modelo de Base de Datos

## Entidades principales

### User

* `id` (PK)
* `name`
* `email` (unique)
* `password` (encriptado con BCrypt)
* `role` (ADMIN, MENTOR, STUDENT)
* `active` (boolean)
* `createdAt`

### Mentorship

* `id` (PK)
* `mentor_id` (FK → User)
* `student_id` (FK → User)
* `status` (PENDING, APPROVED, REJECTED)
* `createdAt`

## Relaciones

* Un **MENTOR** puede tener muchas mentorías.
* Un **STUDENT** puede solicitar muchas mentorías.
* Una mentoría pertenece a un mentor y a un estudiante de forma unívoca.

---

# 📘 Swagger

La documentación interactiva está disponible en:
👉 `http://localhost:8080/swagger-ui/index.html`

**Permite:**

* Probar endpoints en tiempo real.
* Enviar JWT desde el botón **"Authorize"**.
* Validar esquemas de roles y respuestas.

---

# ⚙️ Tecnologías Utilizadas

* **Java 17+**
* **Spring Boot 3+**
* **Spring Security**
* **JWT** (JSON Web Token)
* **Spring Data JPA**
* **PostgreSQL**
* **Swagger (OpenAPI 3)**
* **Maven**

---

# 🧪 Cómo Ejecutar el Proyecto

### 1️⃣ Clonar repositorio

```bash
git clone https://github.com/EmilioSZ989/mentorhub-api.git
cd mentorhub-api

```

### 2️⃣ Configurar base de datos

Editar el archivo `src/main/resources/application.yml` (o `application.properties`):

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/mentorhub_db
    username: tu_usuario
    password: tu_password

```

### 3️⃣ Ejecutar

Desde tu IDE (IntelliJ/VSCode) o terminal:

```bash
mvn spring-boot:run

```

El servidor estará disponible en: `http://localhost:8080`

---

# 🔄 Flujo de Prueba Completo

1. **Crear usuario:** `POST /api/users`
2. **Login:** `POST /api/auth/login`
* Copia el valor de `"token"` recibido.


3. **Enviar token:** En Swagger o Postman, añade el Header:
* `Authorization: Bearer TU_TOKEN`


4. **Probar endpoints protegidos:**
* Crear mentoría (como Student).
* Aprobar/Rechazar (como Mentor).
* Listar todo (como Admin).



---

# 📊 Estado Actual y Futuro

### ✅ Completado

* Autenticación JWT y roles.
* Gestión de estados de mentoría.
* Manejo de errores centralizado.
* Documentación técnica.

### 🔮 Mejoras Futuras

* **Paginación:** Implementación de `Pageable`.
* **Soft delete:** Para usuarios y solicitudes.
* **Auditoría:** Registro de quién modificó qué y cuándo (`Spring Data Envers`).
* **Métricas:** Panel para el ADMIN con estadísticas.
* **Tests:** Pruebas unitarias e integración con JUnit/Mockito.

---

# 🌍 Despliegue (Planeado)

* **Base de datos:** Neon Console (PostgreSQL Serverless).
* **Backend:** Render / Railway.
* **Acceso:** Swagger público para validación de reclutadores.

---

# 📈 Nivel del Proyecto

Este proyecto no es un CRUD básico. Demuestra un **Backend profesional** con seguridad real, modelado relacional correcto y una estructura escalable lista para entornos empresariales.

---

# 👨‍💻 Autor

**Emi**
*Ingeniería Informática*
*Proyecto práctico enfocado en arquitectura backend profesional.*
