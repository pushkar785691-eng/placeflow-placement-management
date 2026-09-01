# PlaceFlow â€” Placement & Application Management System

A portfolio-ready, full-stack placement portal for students and placement administrators. PlaceFlow supports secure role-based access, job discovery, application tracking, resume links, and placement analytics.

## Features

- JWT authentication with `STUDENT` and `ADMIN` roles
- Students can browse/search jobs, apply once, and track application status
- Admins can create, edit, close, and delete jobs
- Admins can review applications and move them through a hiring pipeline
- Dashboard statistics for jobs, applications, and status distribution
- Responsive React interface with protected routes
- Validation, consistent API errors, pagination, and database constraints
- PostgreSQL for production, H2 for tests, Docker Compose for one-command startup

## Architecture

```text
React + TypeScript (Vite)  â†’  Spring Boot REST API  â†’  PostgreSQL
           :5173                     :8080                 :5432
```

## Quick start with Docker

1. Install Git and Docker Desktop.
2. Clone the repository and run:

```bash
docker compose up --build
```

3. Open `http://localhost:5173`.

Demo accounts seeded on first start:

| Role | Email | Password |
|---|---|---|
| Admin | `admin@placeflow.dev` | `Admin@123` |
| Student | `student@placeflow.dev` | `Student@123` |

Change demo credentials and `JWT_SECRET` before any public deployment.

## Local development

Requirements: Java 21, Maven 3.9+, Node 20+, PostgreSQL 16+.

```bash
# terminal 1
cd backend
mvn spring-boot:run

# terminal 2
cd frontend
npm install
npm run dev
```

The backend reads `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`, and `CORS_ORIGINS`. Defaults are suitable for the included Compose environment.

## API overview

| Method | Endpoint | Access |
|---|---|---|
| POST | `/api/auth/register`, `/api/auth/login` | Public |
| GET | `/api/jobs?search=&page=&size=` | Authenticated |
| POST/PUT/DELETE | `/api/jobs/**` | Admin |
| GET/POST | `/api/applications`, `/api/applications/jobs/{jobId}` | Student |
| GET | `/api/applications/admin` | Admin |
| PATCH | `/api/applications/{id}/status` | Admin |
| GET | `/api/dashboard` | Authenticated |

## Tests

```bash
cd backend && mvn test
cd frontend && npm run build
```

## Suggested interview talking points

- Why a modular monolith is appropriate at this scale
- JWT authentication and method-level authorization
- Unique constraints preventing duplicate applications
- Server-side pagination and search
- DTOs preventing entity leakage
- How notifications, object storage, Redis, or an audit log could be added later

## License

MIT

