# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot 4.0.2 demonstration project exploring **Java null-safety** using JSpecify annotations with Error Prone and NullAway static analysis. It's an educational project that contrasts null-safe vs. unsafe code side-by-side.

## Build Commands

```bash
# Build and run tests
./mvnw -B verify

# Run the application
./mvnw spring-boot:run

# Run tests only
./mvnw test

# Run a single test class
./mvnw test -Dtest=ClassName

# Run a single test method
./mvnw test -Dtest=ClassName#methodName
```

## Tech Stack

- **Java 25** - Latest Java version
- **Spring Boot 4.0.2** - Web framework with Spring Data JDBC
- **H2 Database** - In-memory database for development
- **JSpecify** - Null-safety annotations (`@Nullable`, `@NonNull`)
- **Error Prone + NullAway** - Static analysis for null-safety enforcement

## Null-Safety Configuration

The project enforces strict null-safety checking:

- **NullAway** is configured to scan `ch.martinelli.demo.jspecify` package
- **`failOnWarning=true`** - All null-safety warnings are treated as compile errors
- Use `@Nullable` for parameters/return types that can be null
- Unannotated types are assumed non-null by default
- `.mvn/jvm.config` contains required `--add-exports`/`--add-opens` for Error Prone on Java 9+

## Architecture

- Base package: `ch.martinelli.demo.jspecify`
- Entry point: `JspecifyApplication.java`
- Spring Data JDBC with custom `JdbcClient`-based repository (not Spring Data repositories)
- H2 in-memory database, schema defined in `src/main/resources/schema.sql`

### Safe vs. Unsafe Package Pattern

The project uses two parallel package hierarchies to demonstrate null-safety:

- **`safe/`** — `package-info.java` has `@NullMarked`. NullAway enforces null checks at compile time. Methods returning nullable values must be annotated `@Nullable`, and callers must null-check before dereferencing.
- **`unsafe/`** — `package-info.java` has `@NullUnmarked`. No compile-time null checking. Same logic compiles fine but throws `NullPointerException` at runtime.

Both packages share the same `EmployeeRepository` and `Employee` record, with separate `EmployeeService` implementations qualified as `"safeEmployeeService"` and `"unsafeEmployeeService"`. Tests inject the appropriate service using `@Qualifier`.
