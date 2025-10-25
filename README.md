# 🏷️ Price Drop Watcher

Price Drop Watcher is a **Spring Boot 3 / Java 21** application that monitors product prices, calculates **EMA (Exponential Moving Average)** trends, and triggers alerts when significant price drops occur.  
It demonstrates practical Spring features — **@Scheduled**, **@Async**, **@Transactional**, **@Cacheable**, and **@EventListener** — combined with clean architecture, caching, and JWT-based security.

---

## 🚀 Quick Start

### Prerequisites
- Java 21+
- Git
- No Maven installation required — use the included **Maven Wrapper (`./mvnw`)**

### Run (dev)
```bash
git clone https://github.com/<your-username>/price-drop-watcher.git
cd price-drop-watcher
./mvnw spring-boot:run
```

Then open:  
➡️ [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health) → should return `{ "status": "UP" }`.

### Build & Test
```bash
./mvnw clean package
./mvnw test
```

---

## 🧩 Tech Stack

| Layer | Technology |
|-------|-------------|
| **Backend** | Spring Boot 3.x, Java 21 |
| **Database** | H2 (in-memory, dev) |
| **Persistence** | Spring Data JPA |
| **Cache** | Caffeine |
| **Validation** | Jakarta Validation |
| **Security** | Spring Security + JWT |
| **Testing** | Spring Boot Test, JUnit 5 |
| **Build** | Maven Wrapper (`mvnw`) |

---

## 🏗️ Project Setup & Standards

This project follows clean engineering standards to ensure quality and reproducibility.

- **Maven Wrapper (`mvnw`)** — guarantees consistent Maven version across environments  
- **Spotless (Google Java Format)** — automatic code formatting  
- **Structured package layout:**  
  ```
  com.mike.pdw
  ├── config/
  ├── domain/
  │   ├── entity/
  │   └── repo/
  ├── service/
  ├── web/
  ├── providers/
  ├── algo/
  ├── events/
  └── jobs/
  ```
- **Actuator health check:** `/actuator/health`  
- **Git branching:** `feature/PDW-<number>-short-description`  
- **Commit convention:** `PDW-<number>: short message`

These standards make the project maintainable and CI/CD-ready.

## 📘 Code Quality

### Format & Check
```bash
./mvnw spotless:apply
./mvnw spotless:check
```

### Typical Maven Wrapper usage
```bash
./mvnw clean verify
```
✅ Wrapper ensures consistent Maven version (no global install needed).

---

## 💡 Why Maven Wrapper?

Maven Wrapper (`mvnw`) makes builds **reproducible and portable**:
- locks Maven version (e.g. 3.9.x)
- works without Maven preinstalled
- identical builds in CI/CD and local environments

Commit the following:
```
mvnw
mvnw.cmd
.mvn/wrapper/
```

---

## 🧰 Development Notes

### Run H2 Console (if enabled)
`http://localhost:8080/h2-console`  
JDBC: `jdbc:h2:mem:testdb`

### Common Problems
| Issue | Fix |
|-------|-----|
| Port 8080 in use | Change `server.port` in `application.yml` |
| Wrapper missing | `mvn -N io.takari:maven:wrapper -Dmaven=3.9.9` |
| Format check fails | `./mvnw spotless:apply` |

---

## 🧾 License
MIT License — use freely for educational or interview purposes.

---

## 👤 Author
Created by **Michał Świgut**  
For hotels purposes.  
LinkedIn: [https://www.linkedin.com/in/michalswigut/](https://www.linkedin.com/in/michalswigut/)

---

**Status:** ✅ PDW-1 completed — project bootstrapped and running  
Next: PDW-2 (structure) + PDW-3 (Spotless & quality)
