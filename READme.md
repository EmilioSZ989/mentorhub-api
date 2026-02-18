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
controller/    -> Exposición de endpoints REST
service/       -> Interfaces de lógica de negocio
service/impl/  -> Implementaciones de lógica
repository/    -> Acceso a datos (JPA)
entity/        -> Modelado de base de datos
dto/           -> Objetos de transferencia de datos
security/      -> Configuración JWT y Spring Security
exception/     -> Manejador global de errores (GlobalExceptionHandler)
config/        -> Configuraciones generales (Swagger, perfiles)

```

---

# 🌍 Configuración por Entorno

MentorHub soporta ejecución multientorno mediante perfiles de Spring Boot, permitiendo un salto fluido entre desarrollo y producción.

## 🖥️ Entorno Local (Desarrollo)

**Perfil activo:** `local`

### Variables de entorno necesarias:

```bash
SPRING_PROFILES_ACTIVE=local
DB_URL_LOCAL=jdbc:postgresql://localhost:5432/mentorhub_db
DB_USERNAME_LOCAL=postgres
DB_PASSWORD_LOCAL=1234
JWT_SECRET=tu_clave_secreta_super_segura
JWT_EXPIRATION=86400000

```

### Ejecución:

```bash
./mvnw spring-boot:run

```

👉 **Swagger local:** `http://localhost:8080/swagger-ui/index.html`

---

## ☁️ Entorno Producción (Render + Neon)

**Perfil activo:** `prod`

El backend está desplegado en **Render** y la base de datos en **Neon (PostgreSQL Serverless)**.

### 🔑 Configuración en Render (Environment Variables)

Para el despliegue exitoso, se configuraron las siguientes variables en el panel de control:

* `SPRING_PROFILES_ACTIVE`: `prod`
* `DB_URL_PROD`: `jdbc:postgresql://ep-frosty-hill-aia4a3gi-pooler.c-4.us-east-1.aws.neon.tech/neondb?sslmode=require`
* `DB_USERNAME_PROD`: `neondb_owner`
* `DB_PASSWORD_PROD`: `********`
* `JWT_SECRET`: `[SECRET_KEY]`
* `JWT_EXPIRATION`: `86400000`

### 🚀 Endpoints Públicos

* **API Host:** `https://mentorhub-api-24gj.onrender.com`
* **Swagger Público:** [Ver Documentación Interactiva](https://mentorhub-api-24gj.onrender.com/swagger-ui/index.html)

---

# 🔐 Seguridad

* **JWT (Bearer Token):** Filtro personalizado (`JwtFilter`).
* **Encriptación:** Contraseñas protegidas con **BCrypt**.
* **SSL:** Obligatorio para conexiones en producción (Neon).
* **Roles:** Control de acceso mediante `@PreAuthorize`.

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

# 🗄️ Modelo de Datos (JPA)

### Entidades

* **User:** Gestiona perfiles y credenciales (ADMIN, MENTOR, STUDENT).
* **Mentorship:** Gestiona el ciclo de vida de la mentoría (PENDING, APPROVED, REJECTED).

### Relaciones

* Un **MENTOR** puede tener múltiples mentorías.
* Un **STUDENT** puede solicitar múltiples mentorías.
* Relaciones `@ManyToOne` correctamente mapeadas para integridad referencial.

---

# 🔄 Flujo de Prueba

1. **Crear usuario:** `POST /api/users`
2. **Login:** `POST /api/auth/login` -> Obtener Token.
3. **Autorizar:** Pegar token en el botón **"Authorize"** de Swagger.
4. **Gestionar:** Crear solicitudes como estudiante y aprobarlas como mentor.

---

# 📊 Estado del Proyecto

### ✅ Completado

* Arquitectura limpia y multientorno.
* Seguridad JWT robusta.
* Despliegue automatizado (CI/CD).
* Base de datos en la nube.

### 🔮 Mejoras Futuras

* **Paginación:** Uso de `Pageable`.
* **Soft delete:** Borrado lógico de registros.
* **Auditoría:** Registro de cambios con `Spring Data Envers`.
* **Tests:** Cobertura con JUnit y Mockito.

---

# 👨‍💻 Autor

**Emi** *Ingeniería Informática* *Enfocado en el desarrollo de arquitecturas backend escalables y seguras.*
