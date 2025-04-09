# SB Weather API

A simple RESTful Spring Boot application for managing weather locations and realtime weather data. This project also includes IP-based geolocation using the IP2Location BIN database.

---

## 🌍 Features

- **Location Management**
    - Create, update, delete (soft delete) locations
    - Validation for input fields (e.g. code length, required fields)
- **Realtime Weather Management**
    - Add/update weather data per location
    - One-to-one relationship with Location
- **IP Geolocation**
    - Detect location from IP using IP2Location
    - BIN file support (offline mode)
- **Error Handling**
    - Centralized with `@ControllerAdvice`
    - Custom exceptions and validation messages
- **DTO Mapping**
    - `ModelMapper` for converting entities to DTOs
- **Unit & Integration Tests**
    - JPA Repository tests
    - Weather and Location update scenarios

---

## 🪜 Technologies Used

- Java 21
- Spring Boot 3.4+
- Spring Data JPA
- MySQL
- Lombok
- ModelMapper
- IP2Location Java Library
- Maven
- Postman for manual API testing

---

## ⚙️ Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/your-username/sb-weather-api.git
cd sb-weather-api
```

### 2. Configure Database
Make sure MySQL is running. Create a database:
```sql
CREATE DATABASE weatherdb;
```

Update your credentials in `application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=your-password
```

### 3. Add IP2Location DB
Place your IP2Location BIN file here:
```
src/main/resources/ip2locdb/IP2LOCATION-LITE-DB3.BIN
```

### 4. Run the app
```bash
mvn spring-boot:run
```

---

## 🚀 Sample API Endpoints

### Location
- `GET /v1/locations` – List all active locations
- `GET /v1/locations/{code}` – Get location by code
- `POST /v1/locations` – Create location
- `PUT /v1/locations` – Update location
- `DELETE /v1/locations/{code}` – Soft delete location

### Realtime Weather
- `GET /v1/realtime` – Get weather by IP (based on geolocation)
- `PUT /v1/realtime/{locationCode}` – Update weather for a location

---

## ✅ Example JSON (Create Location)
```json
{
  "code": "NYC_USA",
  "cityName": "New York City",
  "regionName": "New York",
  "countryName": "United States of America",
  "countryCode": "US",
  "enabled": true
}
```

## 📚 Author
- Built with ❤️ by a Junior Java Developer as a learning project

## 🚫 Disclaimer
This is a personal educational project. The IP2Location database is used under its respective license.

