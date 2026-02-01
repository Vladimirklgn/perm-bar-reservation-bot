# Perm Bar Reservation Bot

Telegram-бот для бронирования столов в барах Перми (Искра и Пламя, Чеширский Кот, Шеймус, Смоки Дог).

## Технологии
- Java 22
- Spring Boot 4.0.2
- TelegramBots 6.9.7.1
- OkHttp для iiko API
- MySQL + JPA

## Запуск
1. Заполни `application.yml`: bot token, db credentials.
2. `mvn spring-boot:run` или через IDEA.

## Следующие шаги
- Mock для iiko API
- Машина состояний для бронирования
- Кнопки и календарь
