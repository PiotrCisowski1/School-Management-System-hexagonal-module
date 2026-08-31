# 🏛️ Schedule Module — Hexagonal Architecture Showcase (PoC)

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Architecture](https://img.shields.io/badge/Architecture-Hexagonal%20%2F%20Ports%20%26%20Adapters-blue.svg)](#-architectural-overview--design-decisions)

> **Proof of Concept (PoC) & Portfolio Technical Demonstration**
> 
> A lightweight, isolated Java & Spring Boot backend module designed to demonstrate **Hexagonal Architecture (Ports and Adapters)**, **Domain-Driven Design (DDD)** concepts, clean code standards, and strict **Dependency Inversion**.

---

## 📌 Project Purpose & Context

This repository is **not** a full production application. It serves as an isolated architectural case study created for portfolio presentation and senior technical evaluation. 

The primary objective is to showcase:
* Pure domain isolation without framework pollution.
* Clear decoupling between business rules, application orchestration, and infrastructure adapters.
* Granular Use Case modeling following the Single Responsibility Principle (SRP).

---

## 🏗️ Architectural Overview & Key Design Decisions

The module enforces strict separation across three primary layers: `Domain`, `Application`, and `Infrastructure`.

```
        ┌─────────────────────────────────────────────────────────────┐
        │                        INFRASTRUCTURE                       │
        │   ┌───────────────────────┐     ┌──────────────────────┐    │
        │   │  REST Controllers     │     │  JPA Repositories    │    │
        │   │  Security (JWT)       │     │  External Mock DTOs  │    │
        │   └───────────┬───────────┘     └──────────▲───────────┘    │
        └───────────────┼────────────────────────────┼────────────────┘
                        │ (HTTP Request)             │ (Implements Port)
                        ▼                            │
        ┌────────────────────────────────────────────┴────────────────┐
        │                         APPLICATION                         │
        │   ┌────────────────────────────────────────────────────┐    │
        │   │  CreateScheduleService (@Service, @Transactional)  │    │
        │   │  MapStruct Mappers                                 │    │
        │   └──────────────────────────┬─────────────────────────┘    │
        └──────────────────────────────┼──────────────────────────────┘
                                       │ (Calls/Implements Ports)
                                       ▼
        ┌─────────────────────────────────────────────────────────────┐
        │                            DOMAIN                           │
        │   ┌────────────────────────────────────────────────────┐    │
        │   │  Entities: Schedule, ScheduleVersion               │    │
        │   │  Business Rules: Overlapping, Teacher Availability │    │
        │   │  Ports: CreateSchedulePort, PersistencePort        │    │
        │   └────────────────────────────────────────────────────┘    │
        └─────────────────────────────────────────────────────────────┘
```

### 1. Pure Domain Layer (Dependency Inversion)
* **Zero Framework Coupling:** The `pl.cisowski.domain` package is **100% framework-agnostic**. It contains no references to Spring, JPA, Jackson, MapStruct, or Lombok.
* **Pure Java SE:** All business logic, domain entities, value objects, and domain exceptions are implemented purely in Java (`java.time.*`, `java.util.*`).
* **Inward Dependency Rule:** Dependencies strictly point inward (`Infrastructure` $\rightarrow$ `Application` $\rightarrow$ `Domain`).

### 2. Granular Ports & Single Responsibility (SRP)
* **Input Ports (Use Cases):** Each action on the domain model is granular and defined in a dedicated interface (e.g., `CreateSchedulePort`), strictly adhering to SRP.
* **Output Ports:** Infrastructure mechanisms (database persistence, external service providers) are abstracted behind domain output interfaces (`SchedulePersistencePort`, `TeacherProviderPort`, etc.).

### 3. Decoupled External Models & Mock Providers
* **Bounded Context Boundaries:** External microservice data (Teachers, Classrooms, Subjects, Yearbooks) is provided via dedicated provider adapters.
* **Minimal Data Transfer:** External models are trimmed down exclusively to the fields required by this domain context; extraneous fields are ignored.

### 4. Pragmatic Application Layer
* Spring annotations (`@Service`, `@Transactional`) and MapStruct mappers are intentionally used within the `application` layer to balance real-world market pragmatism with architectural readability.

### 5. Security Integration
* JWT authentication and authorization (`@PreAuthorize`) are included within the incoming web adapters for security completeness, though in enterprise production setups this responsibility would typically be offloaded to an API Gateway or IAM provider.

---

## 🛠️ Tech Stack & Tooling

* **Language:** Java 17
* **Framework:** Spring Boot 3.x (Web, Security, Data JPA)
* **Persistence:** H2 / Relational JPA (Abstracted behind Domain Ports)
* **Mapping:** MapStruct
* **Testing:** JUnit 5, Mockito, AssertJ, Instancio
* **Build System:** Maven (Explicitly declared version tags for build reproducibility)

---

## 💡 Explicit Scope & Intentional Omissions (PoC Trade-offs)

To keep the focus strictly on clean architectural patterns and eliminate unnecessary code noise, the following elements were **intentionally omitted**:
* ✖ **Loggers:** Structured logging frameworks (SLF4J/Logback) were omitted to highlight pure business flow readability.
* ✖ **Database Migration Tools:** Liquibase / Flyway migration scripts omitted in favor of automatic JPA DDL generation.
* ✖ **OpenAPI / Swagger:** API documentation generators omitted for brevity.
* ✖ **Changelogs / CI-CD:** Infrastructure DevOps configs excluded to maintain focus on Java backend architecture.

---

## 🚀 Key Feature Showcase

### Schedule Creation Flow (`POST /version/{scheduleVersionId}`)
Executing the `CreateSchedulePort` triggers a multi-step domain validation sequence:
1. **Schedule Version Lookup & Enrichment:** Fetches and hydrates the targeted schedule version.
2. **Time Overlapping Check:** Ensures no conflicting appointment exists within the schedule version.
3. **Teacher Availability Check:** Verifies teacher time slots via `TeacherProviderPort`.
4. **Classroom Availability & Capacity Check:** Verifies room occupancy and ensures classroom capacity can accommodate the yearbook size.
5. **Persistence:** Saves the validated `Schedule` entity via `SchedulePersistencePort`.
