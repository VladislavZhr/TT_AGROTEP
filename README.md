# TT_AGROTEP — Технічне завдання

Це проєкт на Java, реалізований за вимогами компанії AgroTep.  
Додаток дозволяє адміністратору керувати списком риб: додавати, переглядати та видаляти записи з прикріпленими фото.

---

## ⚙️ Технології

- Java 17
- Spring Boot 3
- Spring Security
- Thymeleaf + Bootstrap 5
- Maven
- MySQL 8
- Clean Architecture

---

## 🔧 Функціонал

- Додавання нової риби з кількома зображеннями (до 5)
- Перегляд списку риб
- Видалення записів
- Перевірка типу, розміру і вмісту зображення
- Авторизація: `ADMIN` — доступ до змін, `USER` — лише перегляд

---

## 🧱 Міграція бази

⚠️ **Програма працює лише з оновленою схемою бази після міграції.**

### Крок 1: Створити початкову таблицю `fish`  
Файл:  src/main/resources/sql/OldFishEntity.sql
### Крок 2: Виконати міграційний скрипт  
Файл:  src/main/resources/sql/NewFishEntity.sql


Скрипт:
- змінює тип поля `price` на `DECIMAL(10,2)`
- створює таблицю `fish_images` для збереження кількох зображень
- переносить дані з `fish.image_file_name`
- видаляє колонку `image_file_name`

---

## 🚀 Як запустити

1. Створіть базу `fishmarket` в MySQL
2. Виконайте обидва SQL-файли з секції "Міграція бази"
3. Запустіть додаток:
bash
mvn clean install
mvn spring-boot:run

Перейдіть у браузері:
http://localhost:8081/fish


Користувачі за замовчуванням:

| Роль  | Логін | Пароль |
|-------|-------|--------|
| Admin | admin | admin  |
| User  | user  | user   |

Структура Clean Architecture:
src/
├── domain/         - бізнес-моделі
├── application/    - use cases
├── infrastructure/ - контролери, маппери, валідація, persistence
└── resources/
    ├── templates/  - HTML (Thymeleaf)
    └── sql/        - міграції бази

Автор: VladislavZhr
