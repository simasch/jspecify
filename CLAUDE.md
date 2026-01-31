# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot 4.0.2 demonstration project exploring **Java null-safety** using JSpecify annotations with Error Prone and NullAway static analysis.

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

## Architecture

- Base package: `ch.martinelli.demo.jspecify`
- Entry point: `JspecifyApplication.java`
- Standard Spring Boot structure with Spring Data JDBC for data access
