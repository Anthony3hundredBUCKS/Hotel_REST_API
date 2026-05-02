ment REST API

API приложение для управления данными об отелях.

## Технологии
* **Java 21**[cite: 1]
* **Spring Boot 3**[cite: 1]
* **Spring Data JPA**[cite: 1]
* **H2 Database** (In-memory)[cite: 1]
* **Liquibase** (Миграции БД)[cite: 1]
* **Maven**[cite: 1]

## Требования по ТЗ
* **Порт запуска:** 8092[cite: 1]
* **Префикс API:** `/property-view`[cite: 1]
* **База данных:** H2 (автоматическое создание таблиц через Liquibase)[cite: 1]

## Запуск проекта
Проект настроен для запуска через Maven. Из корневой папки проекта выполните команду:
```bash
mvn spring-boot:run
```[cite: 1]

## Доступные эндпоинты
Все методы имеют префикс `http://localhost:8092/property-view`[cite: 1]:
* `GET /hotels` — список всех отелей[cite: 1]
* `GET /hotels/{id}` — полная информация по ID[cite: 1]
* `GET /search` — поиск (параметры: name, brand, city, country, amenities)[cite: 1]
* `POST /hotels` — создание отеля[cite: 1]
* `POST /hotels/{id}/amenities` — добавление удобств[cite: 1]
* `GET /histogram/{param}` — статистика по параметрам[cite: 1]

## Тестирование
В проекте реализованы тесты контроллеров с использованием MockMvc.
